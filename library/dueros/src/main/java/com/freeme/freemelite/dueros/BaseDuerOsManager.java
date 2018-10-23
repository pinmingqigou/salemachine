package com.freeme.freemelite.dueros;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Environment;
import android.util.Log;

import com.baidu.duer.dcs.api.DcsSdkBuilder;
import com.baidu.duer.dcs.api.IDcsSdk;
import com.baidu.duer.dcs.api.IVoiceRequestListener;
import com.baidu.duer.dcs.api.asroffline.ASROffLineConfig;
import com.baidu.duer.dcs.api.asroffline.IASROffLineConfigProvider;
import com.baidu.duer.dcs.api.config.DcsConfig;
import com.baidu.duer.dcs.api.config.DefaultSdkConfigProvider;
import com.baidu.duer.dcs.api.config.SdkConfigProvider;
import com.baidu.duer.dcs.api.recorder.AudioRecordImpl;
import com.baidu.duer.dcs.api.wakeup.BaseWakeup;
import com.baidu.duer.dcs.api.wakeup.IWakeupAgent;
import com.baidu.duer.dcs.api.wakeup.IWakeupAgent.SimpleWakeUpAgentListener;
import com.baidu.duer.dcs.api.wakeup.IWakeupProvider;
import com.baidu.duer.dcs.framework.DcsSdkImpl;
import com.baidu.duer.dcs.framework.InternalApi;
import com.baidu.duer.dcs.oauth.api.code.OauthCodeImpl;
import com.baidu.duer.dcs.oauth.api.passport.PassportNormalLogin;
import com.baidu.duer.dcs.systeminterface.IOauth;
import com.baidu.duer.dcs.tts.TtsImpl;
import com.baidu.duer.dcs.util.util.StandbyDeviceIdUtil;
import com.baidu.duer.kitt.KittWakeUpServiceImpl;
import com.baidu.duer.kitt.WakeUpConfig;
import com.baidu.duer.kitt.WakeUpWord;
import com.baidu.speech.asr.SpeechConstant;
import com.freeme.freemelite.dueros.oauth.StoreRefreshTokenImpl;
import com.freeme.freemelite.dueros.oauth.StoreRefreshTokenImpl.IStoreFailCallback;
import com.freeme.freemelite.dueros.subject.WakeupSubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseDuerOsManager {
    private static final String TAG = "BaseDuerOsManager";
    public IDcsSdk mDcsSdk;
    public Context mContext;
    private IWakeupAgent mWakeupAgent;
    private SimpleWakeUpAgentListener mSimpleWakeUpAgentListener;
    public AudioRecordImpl mAudioRecorder;

    public BaseDuerOsManager(Context context) {
        mContext = context;
    }

    private void dcsSdkBuilder() {
        // 第一步初始化sdk
        mAudioRecorder = new AudioRecordImpl();
        IOauth oauth = getOauth(mContext);
        final BaseWakeup wakeup = new KittWakeUpServiceImpl(mAudioRecorder);
        //final BaseWakeup wakeup = new DroiWakeUp(mAudioRecorder);
        final IWakeupProvider wakeupProvider = new IWakeupProvider() {
            @Override
            public WakeUpConfig wakeUpConfig() {
                List<WakeUpWord> wakeupWordList = new ArrayList<>();
                wakeupWordList.add(new WakeUpWord(1, DuerOsConfig.WakeupConfig.WAKEUP_WORD));
                wakeupWordList.add(new WakeUpWord(2, DuerOsConfig.WakeupConfig.WAKEUP_WORD));
                wakeupWordList.add(new WakeUpWord(3, DuerOsConfig.WakeupConfig.WAKEUP_WORD));
                final List<String> umdlPaths = new ArrayList<>();
                umdlPaths.add(DuerOsConfig.WAKEUP_UMDL_PATH);
                return new WakeUpConfig.Builder()
                        .resPath(DuerOsConfig.WAKEUP_RES_PATH)
                        .umdlPath(umdlPaths)
                        .sensitivity(DuerOsConfig.WAKEUP_SENSITIVITY)
                        .highSensitivity(DuerOsConfig.WAKEUP_HIGH_SENSITIVITY)
                        .wakeUpWords(wakeupWordList)
                        .build();
            }

            @Override
            public boolean enableWarning() {
                return DuerOsConfig.ENABLE_PLAY_WARNING;
            }

            @Override
            public String warningSource() {
                // 每次在播放唤醒提示音前调用该方法
                // assets目录下的以assets://开头
                // 文件为绝对路径
                return "assets://ding.wav";
            }

            @Override
            public float volume() {
                // 每次在播放唤醒提示音前调用该方法
                // [0-1]
                return 0.8f;
            }

            @Override
            public boolean wakeAlways() {
                return true;
            }

            @Override
            public BaseWakeup wakeupImpl() {
                return wakeup;
            }

            @Override
            public int audioType() {
                // 用户自定义类型
                return AudioManager.STREAM_MUSIC;
            }
        };

        // SDK配置，ClientId、语音PID、代理等
        SdkConfigProvider sdkConfigProvider = getSdkConfigProvider();
        // 构造dcs sdk
        DcsSdkBuilder builder = new DcsSdkBuilder();
        mDcsSdk = builder.withSdkConfig(sdkConfigProvider)
                .withWakeupProvider(wakeupProvider)
                .withOauth(oauth)
                .withAudioRecorder(mAudioRecorder)
                .withDeviceId(StandbyDeviceIdUtil.getStandbyDeviceId())
                .withOpenDebug(BuildConfig.DEBUG)
                .build();
    }

    public void setWakeupConfig() {
        if (!enableWakeup()) {
            return;
        }
        // 离线识别配置
        final ASROffLineConfig asrOffLineConfig = new ASROffLineConfig();
        asrOffLineConfig.grammerPath = "assets:///baidu_speech_grammar.bsg";
        HashMap<String, Object> params = new HashMap<>();
        params.put(SpeechConstant.ASR_AUDIO_COMPRESSION_TYPE, 1);
        asrOffLineConfig.params = params;

        IASROffLineConfigProvider asrOffLineConfigProvider = new IASROffLineConfigProvider() {
            @Override
            public ASROffLineConfig getOfflineConfig() {
                return asrOffLineConfig;
            }
        };

        // 设置Oneshot
        getInternalApi().setSupportOneshot(false);
        getInternalApi().initWakeUp();
        getInternalApi().setAsrMode(DcsConfig.ASR_MODE_ONLINE);
        getInternalApi().setAsrOffLineConfigProvider(asrOffLineConfigProvider);
    }

    public InternalApi getInternalApi() {
        return ((DcsSdkImpl) mDcsSdk).getInternalApi();
    }

    private IOauth getOauth(Context context) {
        PassportNormalLogin passportNormalLogin = new PassportNormalLogin(context, DuerOsConfig.CLIENT_ID);
        passportNormalLogin.setIStoreRefreshToken(new StoreRefreshTokenImpl(mStoreFailCallback));
        if (context instanceof Activity) {
            return new OauthCodeImpl(DuerOsConfig.CLIENT_ID, (Activity) context);
        }
        return passportNormalLogin;
    }

    public void speakOffLine(String inputText) {
        Log.e(TAG, ">>>>>>>>>>>>>>>speakOffLine:" + inputText);
        getInternalApi().speakOfflineRequest(inputText);
    }

    private IStoreFailCallback mStoreFailCallback = new IStoreFailCallback() {
        @Override
        public void onFail() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>IStoreFailCallback onFail");
        }
    };

    private SdkConfigProvider getSdkConfigProvider() {
        return new DefaultSdkConfigProvider() {
            @Override
            public String clientId() {
                return DuerOsConfig.CLIENT_ID;
            }

            @Override
            public int pid() {
                return DuerOsConfig.PID;
            }

        };
    }

    private void cancelVoiceRequest() {
        mDcsSdk.getVoiceRequest().cancelVoiceRequest(new IVoiceRequestListener() {
            @Override
            public void onSucceed() {
                Log.d(TAG, "cancelVoiceRequest onSucceed");
            }
        });
    }

    //是否需要唤醒，如登录界面不需要唤醒
    public abstract boolean enableWakeup();


    public void onCreate() {
        dcsSdkBuilder();
        initWakeup();
        initTtsOffline();
    }

    public void initTtsOffline() {
        TtsImpl impl = getInternalApi().initLocalTts(mContext, null, null,
                "IllUEAvEH8NbKEODlATHnDrm",
                "lZCNHL67ehK86bC27YCEcc40VWWwXUHv", "11739997", null);
        impl.setSpeaker(0);
        String textFile = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/libbd_etts_text.dat.so";
        String speechMode = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/libbd_etts_speech_male.dat.so";
        impl.loadSpeechModel(speechMode, textFile);
        getInternalApi().setVolume(0.8f);
    }

    public void initWakeup() {
        setWakeupConfig();
        if (enableWakeup()) {
            mWakeupAgent = getInternalApi().getWakeupAgent();
            if (mWakeupAgent != null) {
                mSimpleWakeUpAgentListener = new SimpleWakeUpAgentListener() {
                    @Override
                    public void onWakeupSucceed(WakeUpWord wakeUpWord) {
                        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>SimpleWakeUpAgentListener.onWakeupSucceed#WakeUpWord:" + wakeUpWord.getWord());
                        new WakeupSubject().handleWakeup(mContext, wakeUpWord.getWord());
                    }
                };
                mWakeupAgent.addWakeupAgentListener(mSimpleWakeUpAgentListener);
            }

        }
    }

    public void onStart() {
    }

    public void onResume() {
        // 恢复tts，音乐等有关播放
        getInternalApi().resumeSpeaker();
        // 如果有唤醒，则恢复唤醒
        getInternalApi().startWakeup();
    }

    ;

    public void onPause() {
        // 停止tts，音乐等有关播放.
        getInternalApi().pauseSpeaker();
        // 如果有唤醒，则停止唤醒
        getInternalApi().stopWakeup(null);
        // 取消识别，不返回结果
        cancelVoiceRequest();
    }

    public void onStop() {

    }


    public void onDestory() {
        mDcsSdk.release();
    }
}
