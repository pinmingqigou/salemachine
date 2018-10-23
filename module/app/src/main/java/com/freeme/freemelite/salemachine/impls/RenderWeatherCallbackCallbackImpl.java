package com.freeme.freemelite.salemachine.impls;

import com.freeme.freemelite.dueros.callbacks.RenderWeatherCallback;
import com.freeme.freemelite.router.payload.RenderWeatherModel;
import com.freeme.freemelite.salemachine.viewmodels.BaseViewModel;
import com.freeme.freemelite.salemachine.viewmodels.ConversationViewModel;

public class RenderWeatherCallbackCallbackImpl extends BaseCallbackImpl implements RenderWeatherCallback {
    private BaseViewModel mBaseViewModel;
    public RenderWeatherCallbackCallbackImpl(BaseViewModel viewModel) {
        super(viewModel);
        mBaseViewModel = viewModel;
    }

    @Override
    public void onRenderWeather(RenderWeatherModel renderWeatherModel) {
        if (mBaseViewModel instanceof ConversationViewModel){
            ((ConversationViewModel) mBaseViewModel).handleRenderWeatherPayLoad(renderWeatherModel);
        }
    }
}
