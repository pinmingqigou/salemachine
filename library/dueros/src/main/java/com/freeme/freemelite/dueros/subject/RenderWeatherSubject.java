package com.freeme.freemelite.dueros.subject;

import com.freeme.freemelite.dueros.callbacks.RenderWeatherCallback;
import com.freeme.freemelite.router.payload.RenderWeatherModel;

import java.util.ArrayList;
import java.util.List;

public class RenderWeatherSubject {
    private static List<RenderWeatherCallback> sCallbacks = new ArrayList<>();

    public void register(RenderWeatherCallback renderWeatherCallback) {
        if (renderWeatherCallback != null && !sCallbacks.contains(renderWeatherCallback)) {
            sCallbacks.add(renderWeatherCallback);
        }

    }

    public void unregister(RenderWeatherCallback renderWeatherCallback){
        if (renderWeatherCallback != null && sCallbacks.contains(renderWeatherCallback)) {
            sCallbacks.add(renderWeatherCallback);
        }

    }

    public void handleRenderWeather(RenderWeatherModel renderWeatherModel){
        for (RenderWeatherCallback callback : sCallbacks) {
            callback.onRenderWeather(renderWeatherModel);
        }
    }
}
