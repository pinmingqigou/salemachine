package com.freeme.freemelite.salemachine;

import com.freeme.freemelite.salemachine.models.TextCardContentModel;
import com.google.gson.Gson;

public class PayloadParser {
    public static TextCardContentModel parseTextCardContent(String content){
        Gson gson = new Gson();
        TextCardContentModel textCardContentModel = gson.fromJson(content, TextCardContentModel.class);
        return textCardContentModel;
    }
}
