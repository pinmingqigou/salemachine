package com.freeme.freemelite.dueros.subject;

import com.freeme.freemelite.dueros.callbacks.LoginCallback;

import java.util.ArrayList;
import java.util.List;

public class LoginSubject {
    private static List<LoginCallback> callbacks = new ArrayList<>();

    public void register(LoginCallback callback) {
        if (callback != null) {
            callbacks.add(callback);
        }
    }

    public void unregister(LoginCallback callback) {
        if (callback != null) {
            callbacks.remove(callback);
        }
    }

    public void handleLoginSuccess(){
        for (LoginCallback callback : callbacks) {
            callback.onLoginSuccess();
        }
    }
}
