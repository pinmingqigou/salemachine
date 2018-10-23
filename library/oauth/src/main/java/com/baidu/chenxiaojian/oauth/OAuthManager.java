package com.baidu.chenxiaojian.oauth;

import android.app.Application;
import android.content.Context;

import com.baidu.pass.gid.BaiduGIDConfig;
import com.baidu.pass.gid.BaiduGIDManager;
import com.baidu.pass.ndid.BaiduNDIDConfig;
import com.baidu.pass.ndid.BaiduNDIDManager;
import com.baidu.pass.ndid.base.utils.BaiduPassDomain;
import com.baidu.sapi2.SapiAccountManager;
import com.baidu.sapi2.SapiConfiguration;
import com.baidu.sapi2.utils.enums.Domain;

public class OAuthManager {
    public void initOAuth(Application context) {
        registerShareListeners(context);
        initSapiAccountManager(context);

        initNDIDSDK(context);
        initGIDSDK(context);

    }

    // 该方法主要是切换环境调试使用
    private Domain getDomain() {
        Domain domain = Domain.DOMAIN_ONLINE;
        return domain;
    }

    // passport的以下配置需要放在Application里面，如果有自己的Oauth的TPL、Appid、AppKey可以替换下面的，如果没有就用默认的。
    private void initSapiAccountManager(Context context) {
        final SapiConfiguration config = new SapiConfiguration.Builder(context)
                .setProductLineInfo("dcs_sdk", "1", "d514npepert8ipeezmkavf5069utrmhv")
                // 如果是百度系的App，向passport申请sofireSdkConfig。如果是外部importSofireSdk(false)
//                .sofireSdkConfig("200011", "71eb84d6dca8a124fd30685fb3a20acb", 1)
                .setRuntimeEnvironment(getDomain().forceHttps(true))
                .setSupportFaceLogin(false)
                .importSofireSdk(false)
//                .skin("file:///android_asset/sapi_theme/style.css")  // 设置厂商图标
                .debug(true)    // 日志开关
                .build();

        SapiAccountManager.getInstance().init(config);

    }

    // 该方法必须在Application子类中调用
    private void registerShareListeners(final Context context) {
        SapiAccountManager.registerSilentShareListener(new SapiAccountManager.SilentShareListener() {
            @Override
            public void onSilentShare() {
                // TODO 静默互通成功回调，在该回调中处理登录状态变化，以下部分应替换为接入方的业务逻辑
//                sendBroadcast(new Intent(ACTION_SILENT_SHARE));
//                SapiAccountManager.unregisterSilentShareListener();
            }
        });
        // 收到其他APP互通消息回调，在Application子类中初始化SAPI时可以删除该回调
        // 未在Application子类中初始化SAPI的情况下需要在该回调中初始化SAPI，否则不能正常互通
        SapiAccountManager.registerReceiveShareListener(new SapiAccountManager.ReceiveShareListener() {
            @Override
            public void onReceiveShare() {
                initSapiAccountManager(context);
            }
        });
    }

    private void initNDIDSDK(Application application) {
        BaiduNDIDConfig config = new BaiduNDIDConfig(application);
        // 切换Pass环境配置：DOMAIN_ONLINE（线上）、DOMAIN_QA（QA）、DOMAIN_RD（RD）
        config.setRuntimeEnvironment(BaiduPassDomain.DOMAIN_ONLINE);
        // 是否查看debug日志，发版需置为false
        config.debug(false);
        BaiduNDIDManager.setConfig(config);
    }

    private void initGIDSDK(Application application) {
        BaiduGIDConfig conf = new BaiduGIDConfig(application, "dcs_sdk", "1");
        conf.debug(true);
        conf.setRuntimeEnvironment(BaiduPassDomain.DOMAIN_ONLINE);
        BaiduGIDManager.setConfig(conf);
    }
}
