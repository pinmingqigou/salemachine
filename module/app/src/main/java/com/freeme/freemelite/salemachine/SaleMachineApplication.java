package com.freeme.freemelite.salemachine;

import android.support.multidex.MultiDexApplication;

import com.baidu.chenxiaojian.oauth.OAuthManager;
import com.uuch.android_zxinglibrary.ZxingWrapper;


public class SaleMachineApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        new OAuthManager().initOAuth(this);
        ZxingWrapper.init(this);
    }
}
