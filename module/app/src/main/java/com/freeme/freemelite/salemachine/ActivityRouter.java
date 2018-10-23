package com.freeme.freemelite.salemachine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.freeme.freemelite.salemachine.ui.activities.FeatureActivity;
import com.freeme.freemelite.salemachine.ui.activities.MainActivity;
import com.uuch.android_zxinglibrary.SecondActivity;

public class ActivityRouter {
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;

    public static final String RESULT_TYPE = "result_type";
    public static final String RESULT_STRING = "result_string";
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILED = 2;

    public static void startFeatureActivity(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, FeatureActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static void startMainActivity(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static void startScanCodeActivity(Fragment fragment) {
        Intent intent = new Intent(fragment.getContext(), SecondActivity.class);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void startActivityByComponentName(Context context, ComponentName componentName){
        try {
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            ToastUtils.showShort("未安装此应用");
        }
    }

    public static void startSystemSetting(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showShort("未安装此应用");
        }
    }

    public static void startSystemCalendar(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            intent.setDataAndType(Uri.parse("content://com.android.calendar/"), "time/epoch");
            context.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showShort("未安装此应用");
        }
    }
}
