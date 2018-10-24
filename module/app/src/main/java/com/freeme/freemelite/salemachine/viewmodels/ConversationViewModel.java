package com.freeme.freemelite.salemachine.viewmodels;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.freeme.freemelite.dueros.subject.DialogStateSubject;
import com.freeme.freemelite.dueros.subject.HtmlPayLoadSubject;
import com.freeme.freemelite.dueros.subject.RenderCardSubject;
import com.freeme.freemelite.dueros.subject.RenderTextInputSubject;
import com.freeme.freemelite.dueros.subject.RenderVoiceInputSubject;
import com.freeme.freemelite.router.payload.BaseModel;
import com.freeme.freemelite.router.payload.HtmlPayLoadModel;
import com.freeme.freemelite.router.payload.RenderCardModel;
import com.freeme.freemelite.router.payload.RenderVoiceInputModel;
import com.freeme.freemelite.router.payload.TextCardContentModel;
import com.freeme.freemelite.salemachine.SaleMachineCofig;
import com.freeme.freemelite.salemachine.impls.DialogStateCallbackImpl;
import com.freeme.freemelite.salemachine.impls.HtmlPayLoadCallbackImpl;
import com.freeme.freemelite.salemachine.impls.PayMoneyCallackImpl;
import com.freeme.freemelite.salemachine.impls.RenderCardCallbackImpl;
import com.freeme.freemelite.salemachine.impls.RenderTextInputCallbackImpl;
import com.freeme.freemelite.salemachine.impls.RenderVoiceInputCallbackImpl;
import com.freeme.freemelite.salemachine.impls.TextCardContentCallbackImpl;
import com.freeme.freemelite.salemachine.subject.PayMoneySubject;
import com.freeme.freemelite.salemachine.subject.TextCardContentSubject;

import java.util.ArrayList;
import java.util.List;

public class ConversationViewModel extends BaseViewModel {
    private static final String TAG = "ConversationViewModel";

    public MutableLiveData<Integer> mSessionPromptContainerVisibility = new MutableLiveData<>();

    public MutableLiveData<BaseModel> mModelWrapper = new MutableLiveData<>();

    public MutableLiveData<TextCardContentModel> mTextCardContentModelWrapper = new MutableLiveData<>();

    public MutableLiveData<String> mPayMoneyWrapper = new MutableLiveData<>();

    public MutableLiveData<Boolean> mShowScanCode = new MutableLiveData<>();

    public MutableLiveData<Integer> mDialogStateWrapper = new MutableLiveData<>();

    public List<String> getSessionPrompts() {
        List<String> sessionPrompts = new ArrayList<>();
        sessionPrompts.add("\"买一瓶农夫山泉\"");
        sessionPrompts.add("\"来一盒22块钱的利群\"");
        sessionPrompts.add("\"来一瓶二锅头\"");
        sessionPrompts.add("\"来瓶沙宣洗发水\"");
        return sessionPrompts;
    }

    public void onMicrophoneClick(View view) {
        showConversation();
    }

    public void showConversation() {
        mSessionPromptContainerVisibility.setValue(View.GONE);
    }

    public void showSessionPrompt() {
        mSessionPromptContainerVisibility.setValue(View.VISIBLE);
    }

    @Override
    public void handleRenderVoiceInputText(String input, int type) {
        Integer sessionViewVisibility = mSessionPromptContainerVisibility.getValue();
        if (sessionViewVisibility != null && sessionViewVisibility == View.VISIBLE) {
            if (type == SaleMachineCofig.RenderVoiceInputTextPayloadType.FINAL) {
                showConversation();
            }
        }

        setInputModelWrapper(input, type);
    }

    private void setInputModelWrapper(String input, int type) {
        RenderVoiceInputModel renderVoiceInputModel = new RenderVoiceInputModel();
        renderVoiceInputModel.inputText = input;
        renderVoiceInputModel.type = type;
        mModelWrapper.setValue(renderVoiceInputModel);
    }

    @Override
    public void handleRenderTextInputText(String input) {
        super.handleRenderTextInputText(input);
        //文字输入请求与语音输入类似
        Integer sessionViewVisibility = mSessionPromptContainerVisibility.getValue();
        if (sessionViewVisibility != null && sessionViewVisibility == View.VISIBLE) {
            showConversation();
        }
        setInputModelWrapper(input, -1);
    }

    @Override
    public LifecycleObserver bindLifecycle(Context context, LifecycleOwner lifecycleOwner) {
        return new ConversationLifecycle(lifecycleOwner, context);
    }


    public void handleHtmlPayLoad(String url, String token) {
        HtmlPayLoadModel htmlPayLoadModel = new HtmlPayLoadModel();
        htmlPayLoadModel.token = token;
        htmlPayLoadModel.url = url;
        mModelWrapper.setValue(htmlPayLoadModel);
    }

    public void handleRenderCardPayLoad(RenderCardModel renderCardModel) {
        mModelWrapper.setValue(renderCardModel);
    }

    class ConversationLifecycle implements LifecycleObserver {
        private RenderVoiceInputSubject mRenderVoiceInputSubject;
        private RenderVoiceInputCallbackImpl mRenderVoiceInputCallback;
        private HtmlPayLoadSubject mHtmlPayLoadSubject;
        private HtmlPayLoadCallbackImpl mHtmlPayLoad;
        private RenderCardSubject mRenderCardSubject;
        private RenderCardCallbackImpl mRenderCardCallback;
        private RenderTextInputSubject mRenderTextInputSubject;
        private RenderTextInputCallbackImpl mRenderTextInputCallback;
        private final TextCardContentCallbackImpl mTextCardContentCallback;
        private final TextCardContentSubject mTextCardContentSubject;
        private final DialogStateCallbackImpl mDialogStateCallback;
        private final DialogStateSubject mDialogStateSubject;
        private final PayMoneyCallackImpl mPayMoneyCallack;
        private final PayMoneySubject mPayMoneySubject;


        public ConversationLifecycle(LifecycleOwner lifecycleOwner, Context context) {
            lifecycleOwner.getLifecycle().addObserver(this);

            //voice input
            mRenderVoiceInputSubject = new RenderVoiceInputSubject();
            mRenderVoiceInputCallback = new RenderVoiceInputCallbackImpl(ConversationViewModel.this);

            //text input
            mRenderTextInputSubject = new RenderTextInputSubject();
            mRenderTextInputCallback = new RenderTextInputCallbackImpl(ConversationViewModel.this);


            //html
            mHtmlPayLoadSubject = new HtmlPayLoadSubject();
            mHtmlPayLoad = new HtmlPayLoadCallbackImpl(ConversationViewModel.this);

            //card
            mRenderCardSubject = new RenderCardSubject();
            mRenderCardCallback = new RenderCardCallbackImpl(ConversationViewModel.this);

            //content json
            mTextCardContentCallback = new TextCardContentCallbackImpl(ConversationViewModel.this);
            mTextCardContentSubject = new TextCardContentSubject();

            //dialog state
            mDialogStateCallback = new DialogStateCallbackImpl(ConversationViewModel.this);
            mDialogStateSubject = new DialogStateSubject();

            //支付命令
            mPayMoneyCallack = new PayMoneyCallackImpl(ConversationViewModel.this);
            mPayMoneySubject = new PayMoneySubject();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void onCreat() {
            showSessionPrompt();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onStart");
            mRenderVoiceInputSubject.register(mRenderVoiceInputCallback);
            mRenderTextInputSubject.register(mRenderTextInputCallback);
            mHtmlPayLoadSubject.register(mHtmlPayLoad);
            mRenderCardSubject.register(mRenderCardCallback);
            mTextCardContentSubject.register(mTextCardContentCallback);
            mDialogStateSubject.register(mDialogStateCallback);
            mPayMoneySubject.register(mPayMoneyCallack);
        }


        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onStop");
            mRenderVoiceInputSubject.unregister(mRenderVoiceInputCallback);
            mRenderTextInputSubject.unregister(mRenderTextInputCallback);
            mHtmlPayLoadSubject.unregister(mHtmlPayLoad);
            mRenderCardSubject.unregister(mRenderCardCallback);
            mTextCardContentSubject.unregister(mTextCardContentCallback);
            mDialogStateSubject.unregister(mDialogStateCallback);
            mPayMoneySubject.unregister(mPayMoneyCallack);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
        }
    }

}
