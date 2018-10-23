package com.freeme.freemelite.salemachine.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ImageUtils;
import com.freeme.freemelite.AsyncHandler;
import com.freeme.freemelite.salemachine.database.AllAppsDatabase;
import com.freeme.freemelite.salemachine.models.entity.AppEntity;
import com.freeme.freemelite.tools.PackageUtil;

import java.util.ArrayList;
import java.util.List;

public class FeatureViewModel extends BaseViewModel {
    private static final String TAG = "FeatureViewModel";

    public MutableLiveData<List<AppEntity>> mAppsWrapper = new MutableLiveData<>();
    private AllAppsDatabase mAllAppsDatabase;


    @Override
    public LifecycleObserver bindLifecycle(Context context, LifecycleOwner lifecycleOwner) {
        return new FeatureLifecycle(context, lifecycleOwner);
    }

    public void microphoneButtonClick(View view) {
        Context context = view.getContext();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>microphoneButtonClick:" + context);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    private void initAppsDatabase(final Context context) {
        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                PackageManager packageManager = context.getPackageManager();
                createAllappsDatabase(context);
                List<ResolveInfo> lanuchResolveInfo = PackageUtil.getLanuchResolveInfo(context);
                List<AppEntity> entities = new ArrayList<>();
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>initAppsDatabase:" + lanuchResolveInfo.size());
                for (ResolveInfo resolveInfo : lanuchResolveInfo) {
                    AppEntity appEntity = new AppEntity();
                    appEntity.setClassName(resolveInfo.activityInfo.name);
                    appEntity.setPackageName(resolveInfo.activityInfo.packageName);
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(resolveInfo.activityInfo.packageName, PackageManager.GET_META_DATA);
                        String appName = (String) packageManager.getApplicationLabel(applicationInfo);
                        Drawable icon = packageManager.getApplicationIcon(applicationInfo);
                        byte[] bytes = ImageUtils.drawable2Bytes(icon, Bitmap.CompressFormat.PNG);
                        appEntity.setAppName(appName);
                        appEntity.setIcon(bytes);
                    } catch (Exception e) {
                        e.printStackTrace();
                        appEntity.setAppName("未知");
                    }
                    if (mAllAppsDatabase.allAppsDao().loadSpecifiedApp(resolveInfo.activityInfo.packageName) == null) {
                        entities.add(appEntity);
                    }

                }
                mAllAppsDatabase.allAppsDao().insertApps(entities);
            }
        });
    }

    private void createAllappsDatabase(Context context) {
        if (mAllAppsDatabase == null) {
            mAllAppsDatabase = Room
                    .databaseBuilder(context, AllAppsDatabase.class, AllAppsDatabase.DatabaseConfig.DATABASE_FILE_NAME)
                    .build();
        }
    }

    private void getAllApps(final Context context) {
        AsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                createAllappsDatabase(context);
                List<AppEntity> entities = mAllAppsDatabase.allAppsDao().loadAllApps();
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>getAllApps:" + entities.size());
                mAppsWrapper.postValue(entities);
            }
        });
    }


    class FeatureLifecycle implements LifecycleObserver {
        private Context mContext;

        public FeatureLifecycle(Context context, LifecycleOwner lifecycleOwner) {
            lifecycleOwner.getLifecycle().addObserver(this);
            mContext = context.getApplicationContext();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void onCreate() {
            initAppsDatabase(mContext);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            getAllApps(mContext);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {

        }
    }
}
