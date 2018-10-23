package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.router.payload.ForgeryCardModel;
import com.freeme.freemelite.salemachine.callback.PaymentCallback;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.MainViewModel;

public class PaymentCallbackImpl extends BaseCallbackImpl implements PaymentCallback {
    private BaseViewModel mBaseViewModel;

    public PaymentCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onPaymentSuccessful(ForgeryCardModel forgeryCardModel) {
        if (mBaseViewModel instanceof MainViewModel){
            ((MainViewModel) mBaseViewModel).handlePaymentSuccessful(forgeryCardModel);
        }
    }

    @Override
    public void onPaymentFailed() {

    }
}
