package com.freeme.freemelite.dueros;

import com.baidu.duer.dcs.util.message.Directive;
import com.freeme.freemelite.router.payload.RenderCardModel;
import com.google.gson.Gson;

public class PayloadParser {
    public static RenderCardModel parseRenderCard(Directive directive){
        String directiveInfo = directive.toString();
        Gson gson = new Gson();
        RenderCardModel renderCardModel = gson.fromJson(directiveInfo, RenderCardModel.class);
        return renderCardModel;
    }
}
