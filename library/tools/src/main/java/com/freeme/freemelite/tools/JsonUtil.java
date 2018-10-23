package com.freeme.freemelite.tools;

import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final String TAG = "JsonUtil";

    public static boolean isBadJson(String json) {
        return !isGoodJson(json);
    }

    public static boolean isGoodJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(json);
            return true;
        } catch (Exception e) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>read json tree error:" + e);
            return false;
        }
    }
}
