package com.freeme.freemelite.salemachine.callback;

import com.freeme.freemelite.router.payload.ForgeryCardModel;

public interface PaymentCallback {
    void onPaymentSuccessful(ForgeryCardModel forgeryCardModel);

    void onPaymentFailed();
}
