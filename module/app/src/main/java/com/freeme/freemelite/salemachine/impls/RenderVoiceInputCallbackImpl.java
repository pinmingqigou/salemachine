package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.dueros.callbacks.RenderVoiceInputCallback;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;

public class RenderVoiceInputCallbackImpl extends BaseCallbackImpl implements RenderVoiceInputCallback {
    private BaseViewModel viewModel;

    public RenderVoiceInputCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        this.viewModel = viewModel;
    }

    @Override
    public void onRenderVoiceInput(String input, int type) {
        viewModel.handleRenderVoiceInputText(input,type);
    }
}
