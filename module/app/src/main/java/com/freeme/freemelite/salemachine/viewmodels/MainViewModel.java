package com.freeme.freemelite.salemachine.viewmodels;

import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.freeme.freemelite.dueros.DuerOsManager;
import com.freeme.freemelite.dueros.subject.DialogStateSubject;
import com.freeme.freemelite.dueros.subject.VolumeSubject;
import com.freeme.freemelite.dueros.subject.WakeupSubject;
import com.freeme.freemelite.router.payload.ForgeryCardModel;
import com.freeme.freemelite.salemachine.SaleMachineCofig;
import com.freeme.freemelite.salemachine.impls.DialogStateCallbackImpl;
import com.freeme.freemelite.salemachine.impls.PaymentCallbackImpl;
import com.freeme.freemelite.salemachine.impls.VolumeCallbackImpl;
import com.freeme.freemelite.salemachine.impls.WakeupCallbackImpl;
import com.freeme.freemelite.salemachine.subject.PaymentSubject;
import com.freeme.freemelite.salemachine.subject.TextCardContentSubject;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends BaseViewModel {
    private static final String TAG = "MainViewModel";

    public static final int PERMISSION_REQUEST_CODE = 327;
    public DuerOsManager mDuerOsManager;

    public MutableLiveData<Integer> mFragmentSortWrapper = new MutableLiveData<>();
    private List<String> mRequestPermissions = new ArrayList<>();
    public MutableLiveData<List<String>> mRequestPermissionsWrapper = new MutableLiveData<>();
    public MutableLiveData<Integer> mDialogStateWrapper = new MutableLiveData<>();
    public MutableLiveData<Integer> mVolumeWrapper = new MutableLiveData<>();


    public Runnable mBackToStandbyRunnable = new Runnable() {
        @Override
        public void run() {
            mFragmentSortWrapper.postValue(SaleMachineCofig.FragmentShow.FRAGMENT_STANDBY);
        }
    };

    @Override
    public LifecycleObserver bindLifecycle(Context context, LifecycleOwner lifecycleOwner) {
        return new MainActivityLifecycle(context, lifecycleOwner);
    }

    @Override
    public void handleRenderTextInputText(String input) {
        super.handleRenderTextInputText(input);
    }

    @Override
    public void handleRenderVoiceInputText(String input, int type) {
        super.handleRenderVoiceInputText(input, type);
    }

    private void initPermission(Context context) {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA
        };
        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context, perm)) {
                mRequestPermissions.add(perm);
            }
        }
    }

    public void handlePaymentSuccessful(ForgeryCardModel forgeryCardModel) {
        mDuerOsManager.speakOffLine(forgeryCardModel.content);
    }


    class MainActivityLifecycle implements LifecycleObserver {

        private WakeupSubject mWakeupSubject;
        private WakeupCallbackImpl mWakeupCallback;
        private TextCardContentSubject mTextCardContentSubject;
        private PaymentSubject mPaymentSubject;
        private PaymentCallbackImpl mPaymentCallback;
        private DialogStateCallbackImpl mDialogStateCallback;
        private DialogStateSubject mDialogStateSubject;
        private VolumeSubject mVolumeSubject;
        private VolumeCallbackImpl mVolumeCallback;

        public MainActivityLifecycle(Context context, LifecycleOwner lifecycleOwner) {
            lifecycleOwner.getLifecycle().addObserver(this);

            mDuerOsManager = new DuerOsManager(context);
            initPermission(context);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void onCreate() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>onCreate:" + mRequestPermissions.size());
            mDuerOsManager.onCreate();
            if (mRequestPermissions.size() == 0) {
                mDuerOsManager.SdkRun();
            }

            //唤醒
            mWakeupSubject = new WakeupSubject();
            mWakeupCallback = new WakeupCallbackImpl(MainViewModel.this);

            //textcard 模式返回content数据为json格式
            mTextCardContentSubject = new TextCardContentSubject();
            mRequestPermissionsWrapper.postValue(mRequestPermissions);

            //支付成功
            mPaymentSubject = new PaymentSubject();
            mPaymentCallback = new PaymentCallbackImpl(MainViewModel.this);
            mPaymentSubject.register(mPaymentCallback);

            //会话状态
            mDialogStateCallback = new DialogStateCallbackImpl(MainViewModel.this);
            mDialogStateSubject = new DialogStateSubject();

            //音量
            mVolumeSubject = new VolumeSubject();
            mVolumeCallback = new VolumeCallbackImpl(MainViewModel.this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>onStart");
            mDuerOsManager.onStart();

            mWakeupSubject.register(mWakeupCallback);
            mDialogStateSubject.register(mDialogStateCallback);
            mVolumeSubject.register(mVolumeCallback);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>onResume");
            mDuerOsManager.onResume();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>onPause");
            //mDuerOsManager.onPause();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>onStop");
            //mDuerOsManager.onStop();
            mWakeupSubject.unregister(mWakeupCallback);
            mDialogStateSubject.unregister(mDialogStateCallback);
            mVolumeSubject.unregister(mVolumeCallback);

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>onDestroy");
            mPaymentSubject.unregister(mPaymentCallback);
            mDuerOsManager.onDestory();
        }
    }
}
