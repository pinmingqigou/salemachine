package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.salemachine.callback.TextCardContentCallback;
import com.freeme.freemelite.router.payload.TextCardContentModel;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.ConversationViewModel;

public class TextCardContentCallbackImpl extends BaseCallbackImpl implements TextCardContentCallback {
    private BaseViewModel mBaseViewModel;
    public TextCardContentCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onTextCardContentCallback(TextCardContentModel textCardContentModel) {
        if (mBaseViewModel instanceof ConversationViewModel){
            ((ConversationViewModel) mBaseViewModel).mShowScanCode.setValue(true);
            ((ConversationViewModel) mBaseViewModel).mTextCardContentModelWrapper.setValue(textCardContentModel);
        }
    }
}
