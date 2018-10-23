package com.freeme.freemelite.dueros.subject;

import android.content.Context;

import com.freeme.freemelite.dueros.callbacks.WakeupCallback;

import java.util.ArrayList;
import java.util.List;

public class WakeupSubject {
    private static List<WakeupCallback> sCallbacks = new ArrayList<>();

    public void register(WakeupCallback wakeupCallback) {
        if (wakeupCallback != null && !sCallbacks.contains(wakeupCallback)) {
            sCallbacks.add(wakeupCallback);
        }
    }

    public void unregister(WakeupCallback wakeupCallback) {
        if (wakeupCallback != null && sCallbacks.contains(wakeupCallback)) {
            sCallbacks.remove(wakeupCallback);
        }
    }

    public void handleWakeup(Context context, String wakeupWord) {
        for (WakeupCallback callback : sCallbacks) {
            callback.onWakeupSucceed(context, wakeupWord);
        }
    }
}
