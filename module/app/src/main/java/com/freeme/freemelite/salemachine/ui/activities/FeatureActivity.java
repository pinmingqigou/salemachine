package com.freeme.freemelite.salemachine.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.freeme.freemelite.router.base.BaseAppCompatActivity;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.databinding.ActivityFeatureBinding;
import com.freeme.freemelite.salemachine.ui.view.adapter.AllAppsAdapter;
import com.freeme.freemelite.salemachine.viewmodels.FeatureViewModel;

public class FeatureActivity extends BaseAppCompatActivity {
    private static final String TAG = "FeatureActivity";

    private ActivityFeatureBinding mBinding;
    private FeatureViewModel mFeatureViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFeatureViewModel = ViewModelProviders.of(this).get(FeatureViewModel.class);
        mFeatureViewModel.bindLifecycle(this,this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_feature);
        mBinding.setLifecycleOwner(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBinding.getRoot().findViewById(R.id.microphone_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBinding.getRoot().findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//
//        mBinding.getRoot().findViewById(R.id.setting_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ActivityRouter.startSystemSetting(FeatureActivity.this);
//            }
//        });
//
//        mBinding.getRoot().findViewById(R.id.calendar_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ActivityRouter.startSystemCalendar(FeatureActivity.this);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AllAppsAdapter allAppsAdapter = new AllAppsAdapter(this,this,mFeatureViewModel);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        mBinding.allAppsContainer.setLayoutManager(gridLayoutManager);
        mBinding.allAppsContainer.setAdapter(allAppsAdapter);
    }
}
