package com.freeme.freemelite.dueros;

import android.content.Context;
import android.util.Log;

import com.baidu.duer.dcs.api.IConnectionStatusListener;
import com.baidu.duer.dcs.api.IDialogStateListener;
import com.baidu.duer.dcs.api.IDirectiveIntercepter;
import com.baidu.duer.dcs.api.IFinishedDirectiveListener;
import com.baidu.duer.dcs.api.IMessageSender;
import com.baidu.duer.dcs.api.IVoiceRequestListener;
import com.baidu.duer.dcs.api.player.ITTSPositionInfoListener;
import com.baidu.duer.dcs.componentapi.AbsDcsClient.IVoiceErrorListener;
import com.baidu.duer.dcs.componentapi.AbsDcsClient.IVolumeListener;
import com.baidu.duer.dcs.devicemodule.custominteraction.CustomUserInteractionDeviceModule;
import com.baidu.duer.dcs.framework.DcsSdkImpl;
import com.baidu.duer.dcs.framework.ILoginListener;
import com.baidu.duer.dcs.framework.internalapi.IDirectiveReceivedListener;
import com.baidu.duer.dcs.framework.internalapi.IErrorListener;
import com.baidu.duer.dcs.util.api.IDcsRequestBodySentListener;
import com.baidu.duer.dcs.util.devicemodule.textinput.message.TextInputPayload;
import com.baidu.duer.dcs.util.dispatcher.DialogRequestIdHandler;
import com.baidu.duer.dcs.util.message.DcsRequestBody;
import com.baidu.duer.dcs.util.message.Directive;
import com.baidu.duer.dcs.util.util.LogUtil;
import com.freeme.freemelite.dueros.devicemodule.offlineasr.OffLineDeviceModule;
import com.freeme.freemelite.dueros.devicemodule.screen.ScreenDeviceModule;
import com.freeme.freemelite.dueros.devicemodule.screen.extend.card.ScreenExtendDeviceModule;
import com.freeme.freemelite.dueros.devicemodule.screen.extend.card.message.RenderAudioListPlayload;
import com.freeme.freemelite.dueros.devicemodule.screen.extend.card.message.RenderPlayerInfoPayload;
import com.freeme.freemelite.dueros.devicemodule.screen.message.HtmlPayload;
import com.freeme.freemelite.dueros.devicemodule.screen.message.RenderHintPayload;
import com.freeme.freemelite.dueros.devicemodule.screen.message.RenderVoiceInputTextPayload;
import com.freeme.freemelite.dueros.oauth.RefreshTokenUtil;
import com.freeme.freemelite.dueros.subject.DialogStateSubject;
import com.freeme.freemelite.dueros.subject.HtmlPayLoadSubject;
import com.freeme.freemelite.dueros.subject.RenderCardSubject;
import com.freeme.freemelite.dueros.subject.RenderTextInputSubject;
import com.freeme.freemelite.dueros.subject.RenderVoiceInputSubject;
import com.freeme.freemelite.dueros.subject.VolumeSubject;
import com.freeme.freemelite.router.payload.RenderCardModel;

import java.util.HashMap;

public class DuerOsManager extends BaseDuerOsManager {
    private static final String TAG = "DuerOsManager";

    //端能力开始
    private ScreenDeviceModule mScreenDeviceModule;
    private ScreenExtendDeviceModule mScreenExtendDeviceModule;
    private OffLineDeviceModule mOffLineDeviceModule;
    private CustomUserInteractionDeviceModule mCustomUserInteractionDeviceModule;
    private IMessageSender mMessageSender;
    //端能力结束

    public DuerOsManager(Context context) {
        super(context);
    }

    public void sendQuery(String input) {
        getInternalApi().sendQuery(input);
    }


    public void unregisterListener() {
        mScreenDeviceModule.removeScreenListener(mScreenListener);
        mScreenExtendDeviceModule.removeListener(mScreenExtendListener);
        mOffLineDeviceModule.release();
        mCustomUserInteractionDeviceModule.release();


        mDcsSdk.removeConnectionStatusListener(mConnectionStatusListener);
        getInternalApi().removeErrorListener(mErrorListener);
        getInternalApi().removeRequestBodySentListener(mDcsRequestBodySentListener);
        mDcsSdk.getVoiceRequest().removeDialogStateListener(mDialogStateListener);
        getInternalApi().removeTTSPositionInfoListener(mTTSPositionInfoListener);
        getInternalApi().removeDirectiveReceivedListener(mDirectiveReceivedListener);
        getInternalApi().removeFinishedDirectiveListener(mFinishedDirectiveListener);
    }

    private void registerListener() {
        // 设置各种监听器
        mDcsSdk.addConnectionStatusListener(mConnectionStatusListener);
        // 错误
        getInternalApi().addErrorListener(mErrorListener);
        // event发送
        getInternalApi().addRequestBodySentListener(mDcsRequestBodySentListener);
        // 对话状态
        mDcsSdk.getVoiceRequest().addDialogStateListener(mDialogStateListener);
        // 语音文本同步
        getInternalApi().addTTSPositionInfoListener(mTTSPositionInfoListener);
        // 所有指令透传，建议在各自的DeviceModule中处理
        getInternalApi().addDirectiveReceivedListener(mDirectiveReceivedListener);
        // 指令执行完毕回调
        getInternalApi().addFinishedDirectiveListener(mFinishedDirectiveListener);
        // 语音音量回调监听
        getInternalApi().getDcsClient().addVolumeListener(mVolumeListener);
        getInternalApi().getDcsClient().addVoiceErrorListener(mVoiceErrorListener);
        getInternalApi().setDirectiveIntercepter(new IDirectiveIntercepter() {
            @Override
            public boolean onInterceptDirective(Directive directive) {
                return false;
            }
        });
    }

    public void beginVoiceRequest(final boolean vad) {
        // 必须先调用cancel
        mDcsSdk.getVoiceRequest().cancelVoiceRequest(new IVoiceRequestListener() {
            @Override
            public void onSucceed() {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>cancelVoiceRequest#onSucceed");
                mDcsSdk.getVoiceRequest().beginVoiceRequest(vad);
            }
        });
    }


    @Override
    public boolean enableWakeup() {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.open_log = true;
        //添加端能力
        mMessageSender = getInternalApi().getMessageSender();
        // 上屏
        mScreenDeviceModule = new ScreenDeviceModule(mMessageSender);
        mScreenDeviceModule.addScreenListener(mScreenListener);
        mDcsSdk.putDeviceModule(mScreenDeviceModule);

        mScreenExtendDeviceModule = new ScreenExtendDeviceModule(mMessageSender);
        mScreenExtendDeviceModule.addExtensionListener(mScreenExtensionListener);
        mScreenExtendDeviceModule.addListener(mScreenExtendListener);
        mDcsSdk.putDeviceModule(mScreenExtendDeviceModule);

        // 离线识别
        mOffLineDeviceModule = new OffLineDeviceModule(mContext.getApplicationContext());
        mDcsSdk.putDeviceModule(mOffLineDeviceModule);

        // 在线返回文本的播报，eg:你好，返回你好的播报
        DialogRequestIdHandler dialogRequestIdHandler = ((DcsSdkImpl) mDcsSdk).getProvider().getDialogRequestIdHandler();
        mCustomUserInteractionDeviceModule = new CustomUserInteractionDeviceModule(mMessageSender, dialogRequestIdHandler);
        mDcsSdk.putDeviceModule(mCustomUserInteractionDeviceModule);

        //添加其他自定义技能模块
        addOtherRenderModule();

        //SdkRun();
    }


    public void SdkRun(){
        ((DcsSdkImpl) mDcsSdk).getInternalApi().login(new ILoginListener() {
            @Override
            public void onSucceed(String s) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>登陆成功");
                mDcsSdk.run(null);
            }

            @Override
            public void onFailed(String s) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>登陆失败");
            }

            @Override
            public void onCancel() {

            }
        });
    }

    private void addOtherRenderModule() {

    }

    @Override
    public void onStart() {
        super.onStart();
        registerListener();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterListener();
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }

    private ScreenDeviceModule.IScreenListener mScreenListener = new ScreenDeviceModule.IScreenListener() {
        @Override
        public void onRenderVoiceInputText(RenderVoiceInputTextPayload payload) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>onRenderVoiceInputText:" + payload.toString());
            int type = payload.type == RenderVoiceInputTextPayload.Type.FINAL ? 1 : 0;
            new RenderVoiceInputSubject().handleRenderVoiceInput(payload.text, type);
        }

        @Override
        public void onHtmlPayload(HtmlPayload htmlPayload) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>onHtmlPayload:" + htmlPayload.toString());
            new HtmlPayLoadSubject().handleHtmlPayload(htmlPayload.getUrl(), htmlPayload.getToken());
        }

        @Override
        public void onRenderCard(Directive directive) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>onRenderCard:" + directive.toString());
            RenderCardModel renderCardModel = PayloadParser.parseRenderCard(directive);
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RenderCardModel:" + renderCardModel.toString());
            new RenderCardSubject().handleRenderCardPayload(renderCardModel);
        }

        @Override
        public void onRenderHint(RenderHintPayload renderHintPayload) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onRenderHint:" + renderHintPayload.token + "/" + renderHintPayload.cueWords);
        }
    };


    private ScreenExtendDeviceModule.IScreenExtensionListener mScreenExtensionListener = new ScreenExtendDeviceModule.IScreenExtensionListener() {

        @Override
        public void onRenderPlayerInfo(RenderPlayerInfoPayload renderPlayerInfoPayload) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>onRenderPlayerInfo:" + renderPlayerInfoPayload.toString());
        }

        @Override
        public void onRenderAudioList(RenderAudioListPlayload renderAudioListPlayload) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>onRenderAudioList:" + renderAudioListPlayload.toString());
        }
    };


    private final ScreenExtendDeviceModule.IRenderExtendListener mScreenExtendListener = new ScreenExtendDeviceModule.IRenderExtendListener() {
        @Override
        public void onRenderDirective(Directive directive) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>IRenderExtendListener.onRenderDirective&directive:" + directive.toString());
        }

    };


    private IConnectionStatusListener mConnectionStatusListener = new IConnectionStatusListener() {
        @Override
        public void onConnectStatus(ConnectionStatus connectionStatus) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onConnectionStatusChange: " + connectionStatus);

        }
    };


    private IErrorListener mErrorListener = new IErrorListener() {
        @Override
        public void onErrorCode(IErrorListener.ErrorCode errorCode) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>onErrorCode:" + errorCode) ;
            if (errorCode == ErrorCode.VOICE_REQUEST_FAILED) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>语音请求失败");
            } else if (errorCode == ErrorCode.NETWORK_UNAVIABLE) {
                //  网络不可用
                Log.e(TAG, ">>>>>>>>>>>>>>>>网络不可用");
            } else if (errorCode == ErrorCode.LOGIN_FAILED) {
                // 未登录
                Log.e(TAG, ">>>>>>>>>>>>>>>>未登录");
            } else if (errorCode == ErrorCode.NETWORK_EXCEPTION) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>网络超时");
            } else if (errorCode == ErrorCode.SDK_VOICE_EXCEPTION) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>SDK语音错误");
            } else if (errorCode == ErrorCode.SDK_SERVER_EXCEPTION) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>SDK语音Server错误");
            } else if (errorCode == ErrorCode.SDK_VOICE_UNKNOWN_EXCEPTION) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>SDK语音未知错误");
                // 以下仅针对 passport 登陆情况下的账号刷新，非 passport 刷新请参看文档。
            } else if (errorCode == ErrorCode.UNAUTHORIZED_REQUEST_EXCEPTION) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>App - UNAUTHORIZED_REQUEST_EXCEPTION");
                RefreshTokenUtil.refreshToken(DuerOsConfig.CLIENT_ID, new RefreshTokenUtil.IRefreshTokenCallback() {
                    @Override
                    public void onResponse(HashMap<String, String> hashMap) {
                        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onResponse");
                        getInternalApi().refreshTokenSuccess(hashMap, DuerOsConfig.CLIENT_ID);
                    }

                    @Override
                    public void onFail() {
                        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>onFail");
                    }
                });
            } else if (errorCode == ErrorCode.DIRECTIVE_PENDING) {
                cancelVoiceRequest();
            }
        }
    };

    private void cancelVoiceRequest() {
        mDcsSdk.getVoiceRequest().cancelVoiceRequest(new IVoiceRequestListener() {
            @Override
            public void onSucceed() {
                Log.d(TAG, "cancelVoiceRequest onSucceed");
            }
        });
    }

    private IDcsRequestBodySentListener mDcsRequestBodySentListener = new IDcsRequestBodySentListener() {

        @Override
        public void onDcsRequestBody(DcsRequestBody dcsRequestBody) {
            String eventName = dcsRequestBody.getEvent().getHeader().getName();
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>eventName:" + eventName + "/" + dcsRequestBody.toJsonBody());
            if (eventName.endsWith(DuerOsConfig.RenderSort.RENDER_TEXT_INPUT)) {
                TextInputPayload textInputPayload = (TextInputPayload) dcsRequestBody.getEvent().getPayload();
                new RenderTextInputSubject().handleRenderTextInput(textInputPayload.getQuery());
            }
        }
    };


    IDialogStateListener mDialogStateListener = new IDialogStateListener() {
        @Override
        public void onDialogStateChanged(final DialogState dialogState) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>onDialogStateChanged: " + dialogState);
            DialogStateSubject dialogStateSubject = new DialogStateSubject();
            switch (dialogState) {
                case IDLE:
                    dialogStateSubject.handleDialogIdel();
                    break;
                case LISTENING:
                    dialogStateSubject.handleDialogListening();
                    break;
                case SPEAKING:
                    dialogStateSubject.handleDialogSpeaking();
                    break;
                case THINKING:
                    dialogStateSubject.handleDialogThinking();
                    break;
                default:
                    break;
            }
        }
    };


    ITTSPositionInfoListener mTTSPositionInfoListener = new ITTSPositionInfoListener() {
        @Override
        public void onPositionInfo(long pos, long playTimeMs, long mark) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>pos:" + pos + ",playTimeMs:" + playTimeMs + ",mark:" + mark);
        }
    };


    IDirectiveReceivedListener mDirectiveReceivedListener = new IDirectiveReceivedListener() {
        @Override
        public void onDirective(Directive directive) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>IDirectiveReceivedListener.onDirective$name:" + (directive == null ? null : directive.getName()));
        }
    };


    IFinishedDirectiveListener mFinishedDirectiveListener = new IFinishedDirectiveListener() {
        @Override
        public void onFinishedDirective() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>所有指令执行完毕");
        }
    };


    IVolumeListener mVolumeListener = new IVolumeListener() {
        @Override
        public void onVolume(int volume, int percent) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>IVolumeListener.onVolume:" + volume + "/" + percent);
            new VolumeSubject().handleVolumeEvent(volume, percent);
        }
    };


    IVoiceErrorListener mVoiceErrorListener = new IVoiceErrorListener() {
        @Override
        public void onVoiceError(int error, int subError) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>onVoiceError:" + error + " " + subError);
        }
    };

}
