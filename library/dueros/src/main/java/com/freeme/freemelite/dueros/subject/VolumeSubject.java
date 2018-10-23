package com.freeme.freemelite.dueros.subject;

import com.freeme.freemelite.dueros.callbacks.VolumeCallback;

import java.util.ArrayList;
import java.util.List;

public class VolumeSubject {
    private static List<VolumeCallback> sCallbacks = new ArrayList<>();

    public void register(VolumeCallback callback) {
        if (callback != null && !sCallbacks.contains(callback)) {
            sCallbacks.add(callback);
        }
    }

    public void unregister(VolumeCallback callback) {
        if (sCallbacks.contains(callback)) {
            sCallbacks.remove(callback);
        }
    }

    public void handleVolumeEvent(int volume, int percent){
        for (VolumeCallback callback : sCallbacks) {
            callback.onVolume(volume,percent);
        }
    }
}
