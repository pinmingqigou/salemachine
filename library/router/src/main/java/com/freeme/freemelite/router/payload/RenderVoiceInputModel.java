package com.freeme.freemelite.router.payload;

import com.freeme.freemelite.router.RouterConfig;

public class RenderVoiceInputModel implements BaseModel {
    public String inputText;

    public int type;

    public int index;

    @Override
    public int getConversationType() {
        return RouterConfig.CoversationType.VOICE_INPUT;
    }
}
