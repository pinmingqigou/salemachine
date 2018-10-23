package com.freeme.freemelite.salemachine.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Process;
import android.util.Log;
import android.view.TextureView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CameraPartner {
    private static final String TAG = "CameraPartner";

    private int mCameraNum;
    private Context mContext;
    private SurfaceTextureListenerImpl mSurfaceTextureListener;
    private TextureView mTextureView;
    private Camera mCamera;
    private Camera.Parameters mParameters;
    private List<Camera.Size> mSupportedPreSizeList;
    private List<Camera.Size> mSupportedPicSizeList;
    private ArrayList<String> mPreviewSizeList;
    private ArrayList<String> mPictureSizeList;
    public int mCaptureWidth;
    public int mCaptureHeight;
    private int mX = 0;
    private int mY = 0;
    private int mCropWidth = 0;
    private int mCropHeight = 0;

    public CameraPartner(Context context, TextureView textureView) {
        mContext = context;
        mTextureView = textureView;
        if (checkCameraHardware()) {
            mCameraNum = Camera.getNumberOfCameras();
            mSurfaceTextureListener = new SurfaceTextureListenerImpl(this);
            textureView.setSurfaceTextureListener(mSurfaceTextureListener);
        } else {
            Log.e(TAG, ">>>>>>>>>>>>>>>Has not Camera!");
        }
    }

    public void initializationCarema(int cameraId) {
        openCamera(cameraId);
        initSizeList();
        initParameters();
    }


    public void setPreviewTexture(SurfaceTexture surfaceTexture) {
        if (mCamera != null) {
            try {
                mCamera.setPreviewTexture(surfaceTexture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDisplayOrientation(int degree) {
        if (mCamera != null) {
            mCamera.setDisplayOrientation(degree);
        }
        Log.i(TAG, "Set display orientation is : " + degree);
    }

    public void startPreview() {
        if (mCamera != null) {
            mCamera.setErrorCallback(mErrorCallback);
            mCamera.startPreview();
            Log.i(TAG, "Camera Preview has started!");
        }
    }

    /*
     *Camera.CameraInfo.CAMERA_FACING_BACK
     *
     *Camera.CameraInfo.CAMERA_FACING_FRONT
     * */
    private void openCamera(int cameraId) {
        try {
            if (mCamera == null) {
                mCamera = Camera.open(cameraId);
                CameraPreviewCallback cameraPreviewCallback = new CameraPreviewCallback(mContext, this);
                mCamera.setPreviewCallback(cameraPreviewCallback);
                Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>Camera has opened, cameraId is " + cameraId);
            }
        } catch (Exception e) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>Open Camera has exception:" + e);
        }
    }

    //Check whether the device has a camera
    private boolean checkCameraHardware() {
        if (mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;//has Camera
        } else {
            return false;// has not Camera
        }
    }

    private void initSizeList() {
        mParameters = getParameters();
        mSupportedPreSizeList = getSupportedPreviewSizes(mParameters);
        mSupportedPicSizeList = getSupportedPictureSizes(mParameters);
    }

    private Camera.Parameters getParameters() {
        if (mCamera != null) {
            return mCamera.getParameters();
        }
        return null;
    }

    private List<Camera.Size> getSupportedPreviewSizes(Camera.Parameters parameters) {
        if (parameters == null) {
            return null;
        }
        if (mPreviewSizeList == null) {
            mPreviewSizeList = new ArrayList<>();
        } else {
            mPreviewSizeList.clear();
        }
        mSupportedPreSizeList = new ArrayList<>();
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (equalsRate(size, 1.777f)) {
                mSupportedPreSizeList.add(size);
            }
        }
        for (Camera.Size size : mSupportedPreSizeList) {
            mPreviewSizeList.add(size.width + "×" + size.height);
        }
        return mSupportedPreSizeList;
    }


    private List<Camera.Size> getSupportedPictureSizes(Camera.Parameters parameters) {
        if (parameters == null) {
            return null;
        }
        if (mPictureSizeList == null) {
            mPictureSizeList = new ArrayList<>();
        } else {
            mPictureSizeList.clear();
        }
        mSupportedPicSizeList = new ArrayList<>();
        for (Camera.Size size : parameters.getSupportedPictureSizes()) {
            if (equalsRate(size, 1.777f)) {
                mSupportedPicSizeList.add(size);
            }
        }
        for (Camera.Size size : mSupportedPicSizeList) {
            mPictureSizeList.add(size.width + "×" + size.height);
        }
        return mSupportedPicSizeList;
    }

    private boolean equalsRate(Camera.Size size, float rate) {
        float f = (float) size.width / (float) size.height;
        if (Math.abs(f - rate) <= 0.1f) {
            return true;
        } else {
            return false;
        }
    }

    private void initParameters() {
        if (mSupportedPreSizeList != null && mSupportedPicSizeList != null) {
            mParameters.setPreviewSize(mSupportedPreSizeList.get(mSupportedPreSizeList.size() - 1).width,
                    mSupportedPreSizeList.get(mSupportedPreSizeList.size() - 1).height);
            Log.i(TAG, "initParameters: previewSize: " + mSupportedPreSizeList.get(mSupportedPreSizeList.size() - 1).width +
                    "," + mSupportedPreSizeList.get(mSupportedPreSizeList.size() - 1).height);
            mCaptureWidth = mSupportedPicSizeList.get(mSupportedPicSizeList.size() - 1).width;
            mCaptureHeight = mSupportedPicSizeList.get(mSupportedPicSizeList.size() - 1).height;
            mParameters.setPictureSize(mCaptureWidth, mCaptureHeight);
            Log.i(TAG, "initParameters: pictureSize: " + mCaptureWidth + "," + mCaptureHeight);
            setParameters();
        }
    }

    private void setParameters() {
        if (mCamera != null && mParameters != null) {
            mCamera.setParameters(mParameters);
        }
    }


    private Camera.ErrorCallback mErrorCallback = new Camera.ErrorCallback() {
        @Override
        public void onError(int error, Camera camera) {
            Log.e(TAG, "onError: got camera error callback: " + error);
            if (error == Camera.CAMERA_ERROR_SERVER_DIED) {
                android.os.Process.killProcess(Process.myPid());
            }
        }
    };
}
