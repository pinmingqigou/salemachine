package com.freeme.freemelite.salemachine.subject;

import com.freeme.freemelite.salemachine.callback.PayMoneyCallback;

import java.util.ArrayList;
import java.util.List;

public class PayMoneySubject {
    private static List<PayMoneyCallback> callbacks = new ArrayList<>();

    public void register(PayMoneyCallback callback) {
        if (callback != null) {
            callbacks.add(callback);
        }
    }

    public void unregister(PayMoneyCallback callback) {
        if (callback != null) {
            callbacks.remove(callback);
        }
    }

    public void handlePayMoneyEvent(String money){
        for (PayMoneyCallback callback : callbacks) {
            callback.onPayMoney(money);
        }
    }
}
