package com.freeme.freemelite.salemachine.viewmodels;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.freeme.freemelite.router.payload.RenderCardModel;

public abstract class BaseViewModel extends ViewModel {
    public void handleRenderVoiceInputText(String input, int type){

    }

    public void handleRenderTextInputText(String input){

    }

    public void handleHtmlPayLoad(String url, String token){

    }

    public void handleRenderCardPayLoad(RenderCardModel renderCardModel){

    }

    public abstract LifecycleObserver bindLifecycle(Context context, LifecycleOwner lifecycleOwner);
}
