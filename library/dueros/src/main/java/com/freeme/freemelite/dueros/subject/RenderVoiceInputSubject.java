package com.freeme.freemelite.dueros.subject;

import com.freeme.freemelite.dueros.callbacks.RenderVoiceInputCallback;

import java.util.ArrayList;
import java.util.List;

public class RenderVoiceInputSubject {
    private static List<RenderVoiceInputCallback> callbacks = new ArrayList<>();

    public void register(RenderVoiceInputCallback callback) {
        if (callback != null) {
            callbacks.add(callback);
        }
    }

    public void unregister(RenderVoiceInputCallback callback) {
        if (callback != null) {
            callbacks.remove(callback);
        }
    }

    public void handleRenderVoiceInput(String input,int type) {
        for (RenderVoiceInputCallback callback : callbacks) {
            if (callback != null) {
                callback.onRenderVoiceInput(input,type);
            }
        }
    }
}
