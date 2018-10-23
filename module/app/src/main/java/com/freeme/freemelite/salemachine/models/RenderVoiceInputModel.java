package com.freeme.freemelite.salemachine.models;

import com.freeme.freemelite.router.RouterConfig;
import com.freeme.freemelite.router.payload.BaseModel;

public class RenderVoiceInputModel implements BaseModel {
    public String inputText;

    public int type;

    public int index;

    @Override
    public int getConversationType() {
        return RouterConfig.CoversationType.VOICE_INPUT;
    }
}
