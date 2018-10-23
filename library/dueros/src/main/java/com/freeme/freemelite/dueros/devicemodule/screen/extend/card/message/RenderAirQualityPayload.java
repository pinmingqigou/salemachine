package com.freeme.freemelite.dueros.devicemodule.screen.extend.card.message;


import com.freeme.freemelite.dueros.devicemodule.screen.TokenPayload;

import java.io.Serializable;

public class RenderAirQualityPayload extends TokenPayload implements Serializable {
    public String city;
    public String currentTemperature;
    public String pm25;
    public String airQuality;
    public String day;
    public String date;
    public String dateDescription;
    public String tips;
}
