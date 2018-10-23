package com.freeme.freemelite.router.payload;

import com.freeme.freemelite.router.RouterConfig;

public class ForgeryCardModel implements BaseModel {
    public String content;

    @Override
    public int getConversationType() {
        return RouterConfig.CoversationType.FORGERY_CARD;
    }
}
