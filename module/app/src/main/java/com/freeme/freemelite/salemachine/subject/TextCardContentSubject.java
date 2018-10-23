package com.freeme.freemelite.salemachine.subject;

import com.freeme.freemelite.salemachine.callback.TextCardContentCallback;
import com.freeme.freemelite.salemachine.models.TextCardContentModel;

import java.util.ArrayList;
import java.util.List;

public class TextCardContentSubject {
    private static List<TextCardContentCallback> callbacks = new ArrayList<>();

    public void register(TextCardContentCallback callback) {
        if (callback != null) {
            callbacks.add(callback);
        }
    }

    public void unregister(TextCardContentCallback callback) {
        if (callback != null) {
            callbacks.remove(callback);
        }
    }

    public void handleTextCardContent(TextCardContentModel textCardContentModel){
        for (TextCardContentCallback callback : callbacks) {
            callback.onTextCardContentCallback(textCardContentModel);
        }
    }
}
