package com.freeme.freemelite.salemachine.camera;

import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.TextureView;

public class SurfaceTextureListenerImpl implements TextureView.SurfaceTextureListener {
    private static final String TAG = "SurfaceTureListenerImpl";

    private CameraPartner mCameraPartner;

    public SurfaceTextureListenerImpl(CameraPartner cameraPartner) {
        mCameraPartner = cameraPartner;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        Log.e(TAG,">>>>>>>>>>>>>>>>>>>>>onSurfaceTextureAvailable");
        mCameraPartner.setPreviewTexture(surfaceTexture);
        mCameraPartner.setDisplayOrientation(0);
        mCameraPartner.startPreview();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
        Log.e(TAG,">>>>>>>>>>>>>>>>>>>>>onSurfaceTextureSizeChanged");

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.e(TAG,">>>>>>>>>>>>>>>>>>>>>onSurfaceTextureDestroyed");

        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        Log.e(TAG,">>>>>>>>>>>>>>>>>>>>>onSurfaceTextureUpdated");
    }
}
