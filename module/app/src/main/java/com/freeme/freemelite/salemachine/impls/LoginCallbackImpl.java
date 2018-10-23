package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.dueros.callbacks.LoginCallback;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.MainViewModel;

public class LoginCallbackImpl extends BaseCallbackImpl implements LoginCallback{
    BaseViewModel mBaseViewModel;

    public LoginCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onLoginSuccess() {
        if (mBaseViewModel instanceof MainViewModel){
            ((MainViewModel) mBaseViewModel).handleLoginSuccessEvent();
        }
    }

    @Override
    public void onLoginFail() {

    }

    @Override
    public void onLoginCancel() {

    }
}
