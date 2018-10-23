package com.freeme.freemelite.dueros.subject;

import com.freeme.freemelite.dueros.callbacks.DialogSateCallback;

import java.util.ArrayList;
import java.util.List;

public class DialogStateSubject {
    private static List<DialogSateCallback> callbacks = new ArrayList<>();

    public void register(DialogSateCallback callback) {
        if (callback != null) {
            callbacks.add(callback);
        }
    }

    public void unregister(DialogSateCallback callback) {
        if (callback != null) {
            callbacks.remove(callback);
        }
    }

    public void handleDialogIdel(){
        for (DialogSateCallback callback : callbacks) {
            callback.onIdle();
        }
    }

    public void handleDialogSpeaking(){
        for (DialogSateCallback callback : callbacks) {
            callback.onSpeaking();
        }
    }

    public void handleDialogListening(){
        for (DialogSateCallback callback : callbacks) {
            callback.onListening();
        }
    }

    public void handleDialogThinking(){
        for (DialogSateCallback callback : callbacks) {
            callback.onThinking();
        }
    }
}
