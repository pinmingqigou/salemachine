package com.freeme.freemelite.dueros.devicemodule.screen.extend.card.message;


import com.freeme.freemelite.dueros.devicemodule.screen.TokenPayload;

import java.io.Serializable;

public class RenderStockPayload extends TokenPayload implements Serializable {
    public String token;
    public double changeInPrice;
    public double changeInPercentage;
    public double marketPrice;
    public String marketStatus;
    public String marketName;
    public String name;
    public String datetime;
    public double openPrice;
    public double previousClosePrice;
    public double dayHighPrice;
    public double dayLowPrice;
    public double priceEarningRatio;
    public long marketCap;
    public long dayVolume;
}
