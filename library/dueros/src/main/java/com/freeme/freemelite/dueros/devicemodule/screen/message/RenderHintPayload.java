package com.freeme.freemelite.dueros.devicemodule.screen.message;


import com.freeme.freemelite.dueros.devicemodule.screen.TokenPayload;

import java.io.Serializable;
import java.util.List;

public class RenderHintPayload extends TokenPayload implements Serializable {
    public List<String> cueWords;
}
