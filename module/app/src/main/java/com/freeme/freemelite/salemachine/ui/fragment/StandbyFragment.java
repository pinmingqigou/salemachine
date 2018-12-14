package com.freeme.freemelite.salemachine.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freeme.freemelite.router.base.BaseFragment;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.databinding.FragmentStandbyBinding;
import com.freeme.freemelite.salemachine.ui.view.GlideImageLoader;
import com.freeme.freemelite.salemachine.viewmodels.StandbyViewModel;

public class StandbyFragment extends BaseFragment {
    private static final String TAG = "StandbyFragment";
    private StandbyViewModel mStandbyViewModel;
    private FragmentStandbyBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onCreate");
        mStandbyViewModel = ViewModelProviders.of(this).get(StandbyViewModel.class);
        mStandbyViewModel.bindLifecycle(getContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_standby, container, false);
        mBinding = DataBindingUtil.bind(view);
        mBinding.setStandbyViewModel(mStandbyViewModel);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onViewCreated");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onAttach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>setUserVisibleHint：" + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onHiddenChanged：" + hidden);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onCreateView");
        mStandbyViewModel.mDateWrapper.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mBinding.dateTv.setText(s);
            }
        });

        mStandbyViewModel.mTimeWrapper.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mBinding.timeTv.setText(s);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onResume");
        setupBanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, ">>>>>>>>>>>>>>>>>onDestroy");
    }

    private void setupBanner() {
        //设置图片加载器
        mBinding.banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBinding.banner.setImages(mStandbyViewModel.getBannerImageUrls());
        //banner设置方法全部调用完毕时最后调用
        mBinding.banner.start();
    }
}
