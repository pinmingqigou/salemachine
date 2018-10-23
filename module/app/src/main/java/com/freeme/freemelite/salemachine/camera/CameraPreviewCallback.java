package com.freeme.freemelite.salemachine.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;

public class CameraPreviewCallback implements Camera.PreviewCallback {
    private static final String TAG = "CameraPreviewCallback";

    private CameraPartner mCameraPartner;
    private Context mContext;

    public CameraPreviewCallback(Context context, CameraPartner cameraPartner) {
        mCameraPartner = cameraPartner;
        mContext = context;
    }

    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>onPreviewFrame");

    }
}
