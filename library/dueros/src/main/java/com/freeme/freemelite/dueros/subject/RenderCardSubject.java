package com.freeme.freemelite.dueros.subject;

import com.freeme.freemelite.dueros.callbacks.RenderCardCallback;
import com.freeme.freemelite.router.payload.RenderCardModel;

import java.util.ArrayList;
import java.util.List;

public class RenderCardSubject {
    private static List<RenderCardCallback> sCallbacks = new ArrayList<>();

    public void register(RenderCardCallback cardCallback) {
        if (cardCallback != null) {
            sCallbacks.add(cardCallback);
        }
    }

    public void unregister(RenderCardCallback cardCallback) {
        if (cardCallback != null && sCallbacks.contains(cardCallback)) {
            sCallbacks.remove(cardCallback);
        }
    }

    public void handleRenderCardPayload(RenderCardModel renderCardModel){
        for (RenderCardCallback callback : sCallbacks) {
            callback.onRenderCard(renderCardModel);
        }
    }
}
