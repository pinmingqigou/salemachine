package com.freeme.freemelite.salemachine;

public class SaleMachineCofig {
    public static final long CONVERSATION_IDLE_TIME = 60000;


    public class RenderVoiceInputTextPayloadType {
        public static final int INTERMEDIATE = 0;
        public static final int FINAL = 1;
    }

    public class FragmentShow {
        public static final int UNKNOW = -1;

        public static final int FRAGMENT_STANDBY = 0;

        public static final int FRAGMENT_CONVERSATION = 1;
    }

    public class DialogState{
        public static final int IDLE = 0;

        public static final int LISTENING = 1;

        public static final int SPEAKING  = 2;

        public static final int THINKING = 3;
    }
}
