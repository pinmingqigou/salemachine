package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.dueros.callbacks.RenderCardCallback;
import com.freeme.freemelite.router.payload.RenderCardModel;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.ConversationViewModel;

public class RenderCardCallbackImpl extends BaseCallbackImpl implements RenderCardCallback {
    BaseViewModel mBaseViewModel;

    public RenderCardCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel  = viewModel;
    }

    @Override
    public void onRenderCard(RenderCardModel renderCardModel) {
        if (mBaseViewModel instanceof ConversationViewModel){
            mBaseViewModel.handleRenderCardPayLoad(renderCardModel);
        }
    }
}
