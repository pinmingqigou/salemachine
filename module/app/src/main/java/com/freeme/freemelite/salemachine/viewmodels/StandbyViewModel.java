package com.freeme.freemelite.salemachine.viewmodels;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

import com.freeme.freemelite.salemachine.ActivityRouter;
import com.freeme.freemelite.salemachine.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StandbyViewModel extends BaseViewModel {
    private static final String TAG = "StandbyViewModel";
    public MutableLiveData<String> mDateWrapper = new MutableLiveData<>();
    public MutableLiveData<String> mTimeWrapper = new MutableLiveData<>();
    public Context mContext;

    public List<Integer> getBannerImageUrls() {
        ArrayList<Integer> imageUrls = new ArrayList<>();
        imageUrls.add(R.mipmap.banner_01);
        imageUrls.add(R.mipmap.banner_02);
        imageUrls.add(R.mipmap.banner_03);
        imageUrls.add(R.mipmap.banner_04);
        imageUrls.add(R.mipmap.banner_05);
        imageUrls.add(R.mipmap.banner_06);
        return imageUrls;
    }

    @Override
    public LifecycleObserver bindLifecycle(Context context, LifecycleOwner lifecycleOwner) {
        mContext = context.getApplicationContext();
        return new StandbyLifecycle(context, lifecycleOwner);
    }

    //see data binding of method references
    public void onHomeButtonClick(View view){
        ActivityRouter.startFeatureActivity(mContext);
    }

    //see data bingding of listener bindings
    public void onHomeButtonClick(Context context){
        ActivityRouter.startFeatureActivity(mContext);
    }


    class StandbyLifecycle extends BroadcastReceiver implements LifecycleObserver {
        private Context mContext;

        public StandbyLifecycle(Context context, LifecycleOwner lifecycleOwner) {
            lifecycleOwner.getLifecycle().addObserver(this);
            mContext = context.getApplicationContext();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void onCreate() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onCreate:");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
            intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
            intentFilter.addAction(Intent.ACTION_TIME_TICK);
            mContext.registerReceiver(this, intentFilter);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            setDateNow();
            setTiemNow();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop() {
            mContext.unregisterReceiver(this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            setDateNow();
            setTiemNow();
        }

        private void setDateNow() {
            String date = new SimpleDateFormat("MM月dd日  EE").format(new Date());
            mDateWrapper.setValue(date);
        }

        private void setTiemNow() {
            Time t = new Time();
            t.setToNow();
            int hour = t.hour;
            int minute = t.minute;
            if (minute<10){
                mTimeWrapper.setValue(hour + ":0" + minute);
            }else {
                mTimeWrapper.setValue(hour + ":" + minute);
            }
        }
    }
}
