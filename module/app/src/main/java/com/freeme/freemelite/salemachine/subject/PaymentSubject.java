package com.freeme.freemelite.salemachine.subject;

import com.freeme.freemelite.router.payload.ForgeryCardModel;
import com.freeme.freemelite.salemachine.callback.PaymentCallback;

import java.util.ArrayList;
import java.util.List;

public class PaymentSubject {
    private static List<PaymentCallback> callbacks = new ArrayList<>();

    public void register(PaymentCallback callback) {
        if (callback != null) {
            callbacks.add(callback);
        }
    }

    public void unregister(PaymentCallback callback) {
        if (callback != null) {
            callbacks.remove(callback);
        }
    }

    public void handlePaymentSuccessful(ForgeryCardModel forgeryCardModel){
        for (PaymentCallback callback : callbacks) {
            callback.onPaymentSuccessful(forgeryCardModel);
        }
    }
}
