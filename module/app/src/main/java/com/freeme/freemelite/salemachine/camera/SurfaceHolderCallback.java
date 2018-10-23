package com.freeme.freemelite.salemachine.camera;

import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceHolderCallback implements SurfaceHolder.Callback {
    private static final String TAG = "SurfaceHolderCallback";
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.e(TAG,">>>>>>>>>>>>>>>>>>>>surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.e(TAG,">>>>>>>>>>>>>>>>>>>>surfaceChanged");

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.e(TAG,">>>>>>>>>>>>>>>>>>>>surfaceDestroyed");

    }
}
