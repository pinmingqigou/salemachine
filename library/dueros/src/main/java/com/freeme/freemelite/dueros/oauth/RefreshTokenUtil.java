/*
 * *
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.freeme.freemelite.dueros.oauth;

import android.text.TextUtils;
import android.util.Log;

import com.baidu.duer.dcs.components.httpagent.HttpRequestFactory;
import com.baidu.duer.dcs.util.http.CallInterface;
import com.baidu.duer.dcs.util.http.HttpConfig;
import com.baidu.duer.dcs.util.http.IHttpResponse;
import com.baidu.duer.dcs.util.http.IResponseBody;
import com.baidu.duer.dcs.util.http.callback.SimpleCallback;
import com.baidu.duer.dcs.util.util.CommonUtil;
import com.baidu.sapi2.SapiAccount;
import com.baidu.sapi2.SapiAccountManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxiaojian on 18/5/28.
 * 应用层逻辑，第三方开发者根据自己需求自定义刷新机制。
 */
public class RefreshTokenUtil {

    private static final String TAG = RefreshTokenUtil.class.getName();

    private static int retryCount;
    private static long startTime;
    // 是否正在刷新
    private static boolean isRefreshingToken;

    public static void refreshToken(String clientId, final IRefreshTokenCallback callback) {
        if (isRefreshingToken) {
            return;
        }
        retryCount++;
        if (retryCount == 1) {
            startTime = System.currentTimeMillis();
        }
        Log.i(TAG, "retryCount " + retryCount + " extraOneHour " + extraOneHour());
        // 连续刷新失败，重新登陆
        if (extraOneHour()) {
            reset();
        } else {
            if (retryCount > 5) {
                onFail(callback);
                reset();
                return;
            }
        }

        isRefreshingToken = true;


        final JSONObject requestData = new JSONObject();
        try {
            requestData.put("cuid", CommonUtil.getDeviceUniqueID());
            requestData.put("client_id", clientId);
            requestData.put("scope", "basic");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Map<String, String> header = new HashMap<>();
        SapiAccount sapiAccount = SapiAccountManager.getInstance().getSession();
        if (sapiAccount != null) {
            header.put("Cookie", "BDUSS=" + sapiAccount.bduss);
        }
        HttpRequestFactory.getHttpAgent().simpleRequest("POST",
                HttpConfig.HTTP_REFRESHTOKEN_URL,
                header,
                requestData.toString().getBytes(),
                new SimpleCallback() {
                    @Override
                    public void onResponse(IHttpResponse response) {
                        isRefreshingToken = false;
                        IResponseBody body = response.body();
                        if (body == null) {
                            return;
                        }
                        String bodyData = null;
                        try {
                            bodyData = body.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i(TAG, "onResponse " + bodyData);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(bodyData);
                        } catch (JSONException e) {
                            onFail(callback);
                            e.printStackTrace();
                        }
                        if (jsonObject == null) {
                            onFail(callback);
                            return;
                        }
                        int status = jsonObject.optInt("status");
                        if (status != 0) {
                            onFail(callback);
                            return;
                        }

                        JSONObject dataObj = jsonObject.optJSONObject("data");
                        if (dataObj != null) {
                            final String accessToken = dataObj.optString("access_token");
                            String expiresIn = dataObj.optString("expires_in");
                            long absoluteTime = 0;
                            if (!TextUtils.isEmpty(expiresIn)) {
                                absoluteTime = Long.parseLong(expiresIn) * 1000;
                            }
                            long expiredTime = (absoluteTime - System.currentTimeMillis()) / 1000;
                            final HashMap<String, String> map = new HashMap<>();
                            map.put("access_token", accessToken);
                            map.put("expires_in", String.valueOf(expiredTime));
                            callback.onResponse(map);
                        } else {
                            onFail(callback);
                        }

                    }

                    @Override
                    public void onFailure(CallInterface call, Exception e) {
                        Log.i(TAG, "onFailure " + e.getMessage());
                        isRefreshingToken = false;
                        onFail(callback);
                        reset();
                    }

                    @Override
                    public void onCancel() {
                        isRefreshingToken = false;
                    }
                });
    }

    public static void reset() {
        startTime = System.currentTimeMillis();
        retryCount = 0;
    }

    private static boolean extraOneHour() {
        return (System.currentTimeMillis() - startTime) >= 60 * 60 * 1000;
    }

    private static void onFail(IRefreshTokenCallback callback) {
        if (callback != null) {
            callback.onFail();
        }
    }

    public interface IRefreshTokenCallback {
        void onResponse(HashMap<String, String> hashMap);

        void onFail();
    }
}

