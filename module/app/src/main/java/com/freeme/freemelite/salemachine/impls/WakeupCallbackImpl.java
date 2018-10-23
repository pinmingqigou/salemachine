package com.freeme.freemelite.salemachine.impls;

import android.content.Context;

import com.freeme.freemelite.dueros.callbacks.WakeupCallback;
import com.freeme.freemelite.salemachine.SaleMachineCofig;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.MainViewModel;

public class WakeupCallbackImpl extends BaseCallbackImpl implements WakeupCallback {
    private BaseViewModel mBaseViewModel;

    public WakeupCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onWakeupSucceed(Context context, String wakeUpWord) {
        if (mBaseViewModel instanceof MainViewModel){
            ((MainViewModel) mBaseViewModel).mFragmentSortWrapper.postValue(SaleMachineCofig.FragmentShow.FRAGMENT_CONVERSATION);
        }
    }
}
