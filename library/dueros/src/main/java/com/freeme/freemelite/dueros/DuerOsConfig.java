package com.freeme.freemelite.dueros;

public class DuerOsConfig {
    // demo使用的CLIENT_ID，正式产品请用自己申请的CLIENT_ID
    public static final String CLIENT_ID = /*"d8ITlI9aeTPaGcxKKsZit8tq"*/"WZrpZSm6RWnPDKuhhmluD4WzOZtsGe1v";

    //语音PID
    public static final int PID = 729;
    // 唤醒配置
    public static final String WAKEUP_RES_PATH = "snowboy/common.res";
    public static final String WAKEUP_UMDL_PATH = "snowboy/xiaoduxiaodu_all_11272017.umdl";
    public static final String WAKEUP_SENSITIVITY = "0.35,0.35,0.40";
    public static final String WAKEUP_HIGH_SENSITIVITY = "0.45,0.45,0.55";
    // 唤醒成功后是否需要播放提示音
    public static final boolean ENABLE_PLAY_WARNING = true;

    public class RenderSort{
        public static final String RENDER_TEXT_INPUT = "TextInput";

        public static final String RENDER_WEATHER = "RenderWeather";
    }

    public class WakeupConfig{
        public static final String WAKEUP_WORD = "小度小度";
    }
}
