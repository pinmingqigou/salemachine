package com.freeme.freemelite.dueros.subject;

import com.freeme.freemelite.dueros.callbacks.RenderTextInputCallback;

import java.util.ArrayList;
import java.util.List;

public class RenderTextInputSubject {
    private static List<RenderTextInputCallback> callbacks = new ArrayList<>();

    public void register(RenderTextInputCallback callback) {
        if (callback != null) {
            callbacks.add(callback);
        }
    }

    public void unregister(RenderTextInputCallback callback) {
        if (callback != null) {
            callbacks.remove(callback);
        }
    }

    public void handleRenderTextInput(String input) {
        for (RenderTextInputCallback callback : callbacks) {
            if (callback != null) {
                callback.onRenderTextInput(input);
            }
        }
    }
}
