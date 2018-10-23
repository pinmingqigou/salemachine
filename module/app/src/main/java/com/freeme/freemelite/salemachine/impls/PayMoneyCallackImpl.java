package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.salemachine.callback.PayMoneyCallback;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.ConversationViewModel;

public class PayMoneyCallackImpl extends BaseCallbackImpl implements PayMoneyCallback {

    private BaseViewModel mBaseViewModel;

    public PayMoneyCallackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onPayMoney(String money) {
        if (mBaseViewModel instanceof ConversationViewModel){
            ((ConversationViewModel) mBaseViewModel).mShowScanCode.setValue(true);
            ((ConversationViewModel) mBaseViewModel).mPayMoneyWrapper.setValue(money);
        }
    }
}
