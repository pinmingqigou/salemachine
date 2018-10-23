package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.dueros.callbacks.VolumeCallback;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.MainViewModel;

public class VolumeCallbackImpl extends BaseCallbackImpl implements VolumeCallback{
    private BaseViewModel mBaseViewModel;

    public VolumeCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onVolume(int volume, int percent) {
        if (mBaseViewModel instanceof MainViewModel){
            ((MainViewModel) mBaseViewModel).mVolumeWrapper.postValue(volume);
        }
    }
}
