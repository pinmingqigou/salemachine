package com.freeme.freemelite.dueros.devicemodule.screen.extend.card.message;


import com.freeme.freemelite.dueros.devicemodule.screen.TokenPayload;

import java.io.Serializable;

public class RenderDatePayload extends TokenPayload implements Serializable {
    public String datetime;
    public String timeZoneName;
    public String day;
}
