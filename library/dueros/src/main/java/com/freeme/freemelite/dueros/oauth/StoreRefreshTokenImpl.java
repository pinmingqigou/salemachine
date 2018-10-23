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

import com.baidu.duer.dcs.components.httpagent.HttpRequestFactory;
import com.baidu.duer.dcs.oauth.api.passport.IStoreRefreshToken;
import com.baidu.duer.dcs.util.http.CallInterface;
import com.baidu.duer.dcs.util.http.HttpConfig;
import com.baidu.duer.dcs.util.http.IHttpResponse;
import com.baidu.duer.dcs.util.http.IResponseBody;
import com.baidu.duer.dcs.util.http.callback.SimpleCallback;
import com.baidu.duer.dcs.util.util.CommonUtil;
import com.baidu.duer.dcs.util.util.LogUtil;
import com.baidu.sapi2.SapiAccountManager;
import com.baidu.sapi2.result.OAuthResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxiaojian on 18/5/15.
 * 应用层逻辑，第三方开发者根据自己需求自定义存储机制。
 */

public class StoreRefreshTokenImpl implements IStoreRefreshToken {

    private static final String TAG = StoreRefreshTokenImpl.class.getName();
    private IStoreFailCallback iStoreFailCallback;

    public StoreRefreshTokenImpl(IStoreFailCallback iStoreFailCallback) {
        this.iStoreFailCallback = iStoreFailCallback;
    }

    @Override
    public void storeAuthToken(String clientId, OAuthResult result) {
        final JSONObject requestData = new JSONObject();
        try {
            requestData.put("cuid", CommonUtil.getDeviceUniqueID());
            requestData.put("client_id", clientId);
            requestData.put("scope", "basic");
            requestData.put("access_token", result.accessToken);
            requestData.put("expires_in", result.expiresIn);
            requestData.put("refresh_token", result.refreshToken);
            requestData.put("session_key", result.sessionKey);
            requestData.put("session_secret", result.sessionSecret);
            Map<String, String> header = new HashMap<>();
            header.put("Cookie", "BDUSS=" + SapiAccountManager.getInstance().getSession().bduss);
            HttpRequestFactory.getHttpAgent().simpleRequest("POST",
                    HttpConfig.HTTP_STORE_AUTHTOKEN_URL,
                    header,
                    requestData.toString().getBytes(),
                    new SimpleCallback() {
                        @Override
                        public void onResponse(IHttpResponse response) {
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
                            LogUtil.ic(TAG, "onResponse " + bodyData);
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(bodyData);
                            } catch (JSONException e) {
                                onFail();
                                e.printStackTrace();
                            }
                            if (jsonObject == null) {
                                onFail();
                                return;
                            }
                            int status = jsonObject.optInt("status");
                            if (status != 0) {
                                onFail();
                                return;
                            }
                            RefreshTokenUtil.reset();
                        }

                        @Override
                        public void onFailure(CallInterface call, Exception e) {
                            LogUtil.ic(TAG, e.getMessage());
                            // 直接认为失败，重新登陆。
                            onFail();
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onFail() {
        if (iStoreFailCallback != null) {
            LogUtil.ic(TAG, "IStoreFailCallback onFail");
            iStoreFailCallback.onFail();
        }
        RefreshTokenUtil.reset();
    }

    public interface IStoreFailCallback {
        void onFail();
    }

}
