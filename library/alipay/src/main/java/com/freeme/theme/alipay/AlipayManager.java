package com.freeme.theme.alipay;

import android.util.Log;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;

public class AlipayManager {
    private static final String TAG = "AlipayManager";

    private DefaultAlipayClient mAlipayClient;

    public void init(){
        Log.e(TAG,">>>>>>>>>>>>>>>>>>init");
        mAlipayClient = DefaultAlipayClient.builder(AlipayConfig.SERVER_URL, AlipayConfig.APP_ID, AlipayConfig.PRIVATE_KEY).build();
    }

    public void pay(){
        Log.e(TAG,">>>>>>>>>>>>>>>>>>pay");
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        request.setBizModel(model);

        model.setOutTradeNo(System.currentTimeMillis()+"");
        model.setSubject("Iphone6 16G");
        model.setTotalAmount("0.01");
        model.setAuthCode("287282715746728286");//沙箱钱包中的付款码
        model.setScene("bar_code");

        AlipayTradePayResponse response = null;
        try {
            response = mAlipayClient.execute(request);
            System.out.println(response.getBody());
            System.out.println(response.getTradeNo());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
