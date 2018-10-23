package com.freeme.freemelite.router;

public class RouterConfig {
    public class CoversationType {
        //未知
        public static final int UNKNOW = -1;

        //用户对话
        public static final int VOICE_INPUT = 0;

        //Htmlpayload
        public static final int HTML = 1;

        //RenderCard
        public static final int TEXT_CARD = 10;
        public static final int LIST_CARD = 11;
        public static final int STANDARD_CARD = 12;
        public static final int IMAGE_LIST_CARD = 13;


        //Extend card
        public static final int EXTEND_WEATHER = 100;

        //伪造
        public static final int FORGERY_CARD = 1000;
    }
}
