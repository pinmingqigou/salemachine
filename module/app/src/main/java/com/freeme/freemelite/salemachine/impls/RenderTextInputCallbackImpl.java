package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.dueros.callbacks.RenderTextInputCallback;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;

public class RenderTextInputCallbackImpl extends BaseCallbackImpl implements RenderTextInputCallback {
    private BaseViewModel mBaseViewModel;

    public RenderTextInputCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onRenderTextInput(String input) {
        mBaseViewModel.handleRenderTextInputText(input);
    }
}
