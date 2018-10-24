package com.freeme.freemelite.router.payload;

import com.freeme.freemelite.router.RouterConfig;

public class HtmlPayLoadModel implements BaseModel {
    public String url;

    public String token;

    @Override
    public int getConversationType() {
        return RouterConfig.CoversationType.HTML;
    }
}
