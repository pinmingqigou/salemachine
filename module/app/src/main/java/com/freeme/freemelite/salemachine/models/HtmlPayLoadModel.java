package com.freeme.freemelite.salemachine.models;

import com.freeme.freemelite.router.RouterConfig;
import com.freeme.freemelite.router.payload.BaseModel;

public class HtmlPayLoadModel implements BaseModel {
    public String url;

    public String token;

    @Override
    public int getConversationType() {
        return RouterConfig.CoversationType.HTML;
    }
}
