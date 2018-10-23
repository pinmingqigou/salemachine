package com.freeme.freemelite.salemachine.viewmodels;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

public class ScanCodeViewModel extends BaseViewModel {
    @Override
    public LifecycleObserver bindLifecycle(Context context, LifecycleOwner lifecycleOwner) {
        return new ScanCodeLifecycle(context, lifecycleOwner);
    }


    class ScanCodeLifecycle implements LifecycleObserver {
        public ScanCodeLifecycle(Context context, LifecycleOwner lifecycleOwner) {
            lifecycleOwner.getLifecycle().addObserver(this);
        }
    }
}
