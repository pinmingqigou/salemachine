package com.freeme.freemelite.salemachine.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.freeme.freemelite.router.base.BaseFragment;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.camera.CameraPartner;
import com.freeme.freemelite.salemachine.databinding.FragmentScanCodeBinding;
import com.freeme.freemelite.salemachine.viewmodels.ScanCodeViewModel;

public class ScanCodeFragment extends BaseFragment {
    private static final String TAG = "ScanCodeFragment";

    private FragmentScanCodeBinding mBinding;
    private ScanCodeViewModel mScanCodeViewModel;
    private CameraPartner mCameraPartner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScanCodeViewModel = ViewModelProviders.of(this).get(ScanCodeViewModel.class);
        mScanCodeViewModel.bindLifecycle(getContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan_code, container, false);
        mBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mCameraPartner = new CameraPartner(getContext(),mBinding.capturePreview);
    }

    private void initView() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.qr_scale);
        mBinding.captureScanLine.startAnimation(animation);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCameraPartner.initializationCarema(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
