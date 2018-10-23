package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.dueros.callbacks.DialogSateCallback;
import com.freeme.freemelite.salemachine.SaleMachineCofig;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.ConversationViewModel;
import com.freeme.freemelite.salemachine.viewmodels.MainViewModel;

public class DialogStateCallbackImpl extends BaseCallbackImpl implements DialogSateCallback {
    private BaseViewModel mBaseViewModel;
    public DialogStateCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onIdle() {
        if (mBaseViewModel instanceof ConversationViewModel){
            ((ConversationViewModel) mBaseViewModel).mDialogStateWrapper.postValue(SaleMachineCofig.DialogState.IDLE);
        }else if (mBaseViewModel instanceof MainViewModel){
            ((MainViewModel) mBaseViewModel).mDialogStateWrapper.postValue(SaleMachineCofig.DialogState.IDLE);
        }
    }

    @Override
    public void onListening() {
        if (mBaseViewModel instanceof ConversationViewModel){
            ((ConversationViewModel) mBaseViewModel).mDialogStateWrapper.postValue(SaleMachineCofig.DialogState.LISTENING);
        }else if (mBaseViewModel instanceof MainViewModel){
            ((MainViewModel) mBaseViewModel).mDialogStateWrapper.postValue(SaleMachineCofig.DialogState.LISTENING);
        }
    }

    @Override
    public void onSpeaking() {
        if (mBaseViewModel instanceof ConversationViewModel){
            ((ConversationViewModel) mBaseViewModel).mDialogStateWrapper.postValue(SaleMachineCofig.DialogState.SPEAKING);
        }else if (mBaseViewModel instanceof MainViewModel){
            ((MainViewModel) mBaseViewModel).mDialogStateWrapper.postValue(SaleMachineCofig.DialogState.SPEAKING);
        }
    }

    @Override
    public void onThinking() {
        if (mBaseViewModel instanceof ConversationViewModel){
            ((ConversationViewModel) mBaseViewModel).mDialogStateWrapper.postValue(SaleMachineCofig.DialogState.THINKING);
        }else if (mBaseViewModel instanceof MainViewModel){
            ((MainViewModel) mBaseViewModel).mDialogStateWrapper.postValue(SaleMachineCofig.DialogState.THINKING);
        }
    }
}
