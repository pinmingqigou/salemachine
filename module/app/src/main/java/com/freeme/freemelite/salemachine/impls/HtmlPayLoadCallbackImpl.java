package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.dueros.callbacks.HtmlPayLoadCallback;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;

public class HtmlPayLoadCallbackImpl extends BaseCallbackImpl implements HtmlPayLoadCallback {
    private BaseViewModel baseViewModel;

    public HtmlPayLoadCallbackImpl(BaseViewModel baseViewModel) {
        super(baseViewModel);
        this.baseViewModel = baseViewModel;
    }

    @Override
    public void onHtmlPayload(String url, String token) {
        baseViewModel.handleHtmlPayLoad(url,token);
    }
}
