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
package com.baidu.chenxiaojian.oauth.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.baidu.chenxiaojian.oauth.R;

/**
 * Created by chenxiaojian on 18/3/9.
 */

public class QrCodeImageDialog extends Dialog {

    private ImageView qrCodeImageView ;

    public QrCodeImageDialog(Context context) {
        super(context, R.style.BeautyDialog);
        init();
    }

    public QrCodeImageDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected QrCodeImageDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public void init() {
        setContentView(R.layout.layout_qrcode_image_dialog);
        qrCodeImageView = (ImageView) findViewById(R.id.sapi_iv_qrcode_image);
    }

    public void setQrCodeBitmap(Bitmap qrCodeBitmap) {
        if (qrCodeBitmap != null) {
            this.qrCodeImageView.setImageBitmap(qrCodeBitmap);
        }
    }

}
