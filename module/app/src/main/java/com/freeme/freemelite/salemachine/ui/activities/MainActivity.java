package com.freeme.freemelite.salemachine.ui.activities;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.freeme.freemelite.AsyncHandler;
import com.freeme.freemelite.router.base.BaseAppCompatActivity;
import com.freeme.freemelite.salemachine.R;
import com.freeme.freemelite.salemachine.SaleMachineCofig;
import com.freeme.freemelite.salemachine.ui.fragment.ConversationFragment;
import com.freeme.freemelite.salemachine.ui.fragment.StandbyFragment;
import com.freeme.freemelite.salemachine.viewmodels.MainViewModel;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseAppCompatActivity {
    private static final String TAG = "MainActivity";

    private MainViewModel mMainViewModel;
    private StandbyFragment mStandbyFragment;
    private ConversationFragment mConversationFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mMainViewModel.bindLifecycle(this, this);

        DataBindingUtil.setContentView(this, R.layout.activity_main);

        initFragment();
        showStandbyFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainViewModel.mFragmentSortWrapper.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer fragmentSort) {
                if (fragmentSort != null && fragmentSort == SaleMachineCofig.FragmentShow.FRAGMENT_CONVERSATION) {
                    showConversationFragment();
                } else if (fragmentSort != null && fragmentSort == SaleMachineCofig.FragmentShow.FRAGMENT_STANDBY) {
                    showStandbyFragment();
                }
            }
        });

        mMainViewModel.mRequestPermissionsWrapper.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> permissions) {
                if (permissions != null && !permissions.isEmpty()) {
                    String tmpList[] = new String[permissions.size()];
                    ActivityCompat.requestPermissions(MainActivity.this, permissions.toArray(tmpList), MainViewModel.PERMISSION_REQUEST_CODE);
                }
            }
        });

        mMainViewModel.mDialogStateWrapper.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null) {
                    if (integer == SaleMachineCofig.DialogState.IDLE) {
                        AsyncHandler.postDelayed(mMainViewModel.mBackToStandbyRunnable, SaleMachineCofig.CONVERSATION_IDLE_TIME);
                    } else {
                        AsyncHandler.removeRunnable(mMainViewModel.mBackToStandbyRunnable);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean recordAudioEnable = false;
        boolean readPhoneState = false;
        boolean writeExternalStorageEnable = false;
        boolean cameraEnable = false;
        if (requestCode == MainViewModel.PERMISSION_REQUEST_CODE) {
            List<String> permissionList = Arrays.asList(permissions);
            for (int i = 0; i < permissionList.size(); i++) {
                String permission = permissionList.get(i);
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onRequestPermissionsResult:" + permission);
                if (TextUtils.equals(permission, Manifest.permission.RECORD_AUDIO) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    recordAudioEnable = true;
                } else if (TextUtils.equals(permission, Manifest.permission.READ_PHONE_STATE) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    readPhoneState = true;
                } else if (TextUtils.equals(permission, Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    writeExternalStorageEnable = true;
                } else if (TextUtils.equals(permission, Manifest.permission.CAMERA) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    cameraEnable = true;
                }
            }

            if (recordAudioEnable && readPhoneState && writeExternalStorageEnable && cameraEnable) {
                Log.e(TAG, ">>>>>>>>>>>>>>>>>>>onRequestPermissionsResult:enable");
                mMainViewModel.mDuerOsManager.onPause();
                mMainViewModel.mDuerOsManager.onStop();
                mMainViewModel.mDuerOsManager.onDestory();
                mMainViewModel.mDuerOsManager.onCreate();
                mMainViewModel.mDuerOsManager.SdkRun();
                mMainViewModel.mDuerOsManager.onStart();
                mMainViewModel.mDuerOsManager.onResume();
            } else {
                finish();
            }
        }

    }

    private void showConversationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container,mConversationFragment)
                .addToBackStack(MainActivity.class.getSimpleName())
                .commit();
    }

    private void initFragment() {
        mStandbyFragment = new StandbyFragment();
        mConversationFragment = new ConversationFragment();
    }

    private void showStandbyFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, mStandbyFragment)
                .addToBackStack(MainActivity.class.getSimpleName())
                .commit();
    }
}
