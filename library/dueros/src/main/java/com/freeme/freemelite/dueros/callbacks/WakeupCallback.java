package com.freeme.freemelite.dueros.callbacks;

import android.content.Context;
/*
* 唤醒词唤醒成功的回调接口
* */
public interface WakeupCallback {
    void onWakeupSucceed(Context context,String wakeUpWord);
}
