package com.freeme.freemelite.router.payload;

import com.freeme.freemelite.router.RouterConfig;

import java.util.List;

public class RenderWeatherModel implements BaseModel{
    @Override
    public String toString() {
        return "RenderWeatherModel{" +
                "header=" + header +
                ", payload=" + payload +
                '}';
    }

    /**
     * header : {"namespace":"ai.dueros.device_interface.screen_extended_card","name":"RenderWeather","dialogRequestId":"31db0835-01a3-4223-8c93-c0202469f9a3","messageId":"NWI4ZTRhZWNjNzMxOTkwNDU="}
     * payload : {"country":"中国","province":"上海市","city":"上海","county":"上海","askingDay":"TUE","askingDate":"2018-09-04","weatherForecast":[{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/4e4a20a4462309f7c70a4225790e0cf3d7cad6a4.jpg"},"highTemperature":"32℃","lowTemperature":"24℃","day":"TUE","date":"2018-09-04","weatherCondition":"多云","windCondition":"西北风4-5级","currentTemperature":"31℃","currentPM25":"101","currentAirQuality":"轻度污染","indexes":[{"type":"CLOTHES","level":"炎热","suggestion":"很热，建议你穿短袖、短裤或短裙等夏季服装，请注意防晒"},{"type":"CAR_WASH","level":"较不宜","suggestion":"不太适合洗车，风力较大，汽车可能会蒙上污垢"},{"type":"TRIP","level":"适宜","suggestion":"天气较好，温度稍高，幸好风稍大，会缓解稍热的天气。适宜旅游，可不要错过机会呦！"},{"type":"INFLUENZA","level":"少发","suggestion":"各气象指标适宜，不太容易感冒"},{"type":"EXERCISE","level":"较适宜","suggestion":"天气较好，但因气温较高且风力较强，请适当降低运动强度并注意户外防风。"},{"type":"ULTRAVIOLET","level":"中等","suggestion":"有中等强度的紫外线，建议你涂抹防晒指数高于15的防晒霜"}],"pm25":"101","airQuality":"轻度污染"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/d0c8a786c9177f3eb060133a7bcf3bc79f3d56b5.jpg"},"highTemperature":"31℃","lowTemperature":"25℃","day":"WED","date":"2018-09-05","weatherCondition":"晴转多云","windCondition":"西北风3-4级","indexes":[{"type":"CLOTHES","level":"很热","suggestion":"比较热，建议你穿T恤衫、长裙、衬衫等。昼夜温差大，早晚可以多穿点"},{"type":"CAR_WASH","level":"较不适宜","suggestion":"不适合洗车，未来24小时内有雨，雨水可能会再次弄脏你的车"},{"type":"INFLUENZA","level":"易发","suggestion":"容易感冒，请注意衣服增减，多喝开水多保暖"},{"type":"ULTRAVIOLET","level":"强","suggestion":"紫外线辐射很强，建议涂抹防晒指数在20左右的防晒霜。尽量避免在上午10点到下午两点之间暴露在阳光下"}],"pm25":"36","airQuality":"优"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/0823dd54564e92586e4869d99782d158ccbf4e8d.jpg"},"highTemperature":"30℃","lowTemperature":"24℃","day":"THU","date":"2018-09-06","weatherCondition":"多云转小雨","windCondition":"东南风3-4级","indexes":[{"type":"CLOTHES","level":"很热","suggestion":"比较热，建议你穿T恤衫、长裙、衬衫等。昼夜温差大，早晚可以多穿点"},{"type":"CAR_WASH","level":"较不适宜","suggestion":"不适合洗车，未来24小时内有雨，雨水可能会再次弄脏你的车"},{"type":"INFLUENZA","level":"易发","suggestion":"容易感冒，请注意衣服增减，多喝开水多保暖"},{"type":"ULTRAVIOLET","level":"最弱","suggestion":"紫外线辐射弱，无需特别防护。如果长期出门，建议你涂抹防晒指数在8-12之间的防晒霜"}],"pm25":"36","airQuality":"优"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/0823dd54564e92586e4869d99782d158ccbf4e8d.jpg"},"highTemperature":"30℃","lowTemperature":"24℃","day":"THU","date":"2018-09-06","weatherCondition":"多云转小雨","windCondition":"东南风3-4级","indexes":[{"type":"CLOTHES","level":"很热","suggestion":"比较热，建议你穿T恤衫、长裙、衬衫等。昼夜温差大，早晚可以多穿点"},{"type":"CAR_WASH","level":"较不适宜","suggestion":"不适合洗车，未来24小时内有雨，雨水可能会再次弄脏你的车"},{"type":"INFLUENZA","level":"易发","suggestion":"容易感冒，请注意衣服增减，多喝开水多保暖"},{"type":"ULTRAVIOLET","level":"最弱","suggestion":"紫外线辐射弱，无需特别防护。如果长期出门，建议你涂抹防晒指数在8-12之间的防晒霜"}],"pm25":"36","airQuality":"优"}]}
     */

    private HeaderBean header;
    private PayloadBean payload;

    @Override
    public int getConversationType() {
        return RouterConfig.CoversationType.EXTEND_WEATHER;
    }

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }


    public static class HeaderBean {
        /**
         * namespace : ai.dueros.device_interface.screen_extended_card
         * name : RenderWeather
         * dialogRequestId : 31db0835-01a3-4223-8c93-c0202469f9a3
         * messageId : NWI4ZTRhZWNjNzMxOTkwNDU=
         */

        private String namespace;
        private String name;
        private String dialogRequestId;
        private String messageId;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDialogRequestId() {
            return dialogRequestId;
        }

        public void setDialogRequestId(String dialogRequestId) {
            this.dialogRequestId = dialogRequestId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        @Override
        public String toString() {
            return "HeaderBean{" +
                    "namespace='" + namespace + '\'' +
                    ", name='" + name + '\'' +
                    ", dialogRequestId='" + dialogRequestId + '\'' +
                    ", messageId='" + messageId + '\'' +
                    '}';
        }
    }

    public static class PayloadBean {
        @Override
        public String toString() {
            return "PayloadBean{" +
                    "country='" + country + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", county='" + county + '\'' +
                    ", askingDay='" + askingDay + '\'' +
                    ", askingDate='" + askingDate + '\'' +
                    ", weatherForecast=" + weatherForecast +
                    '}';
        }

        /**
         * country : 中国
         * province : 上海市
         * city : 上海
         * county : 上海
         * askingDay : TUE
         * askingDate : 2018-09-04
         * weatherForecast : [{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/4e4a20a4462309f7c70a4225790e0cf3d7cad6a4.jpg"},"highTemperature":"32℃","lowTemperature":"24℃","day":"TUE","date":"2018-09-04","weatherCondition":"多云","windCondition":"西北风4-5级","currentTemperature":"31℃","currentPM25":"101","currentAirQuality":"轻度污染","indexes":[{"type":"CLOTHES","level":"炎热","suggestion":"很热，建议你穿短袖、短裤或短裙等夏季服装，请注意防晒"},{"type":"CAR_WASH","level":"较不宜","suggestion":"不太适合洗车，风力较大，汽车可能会蒙上污垢"},{"type":"TRIP","level":"适宜","suggestion":"天气较好，温度稍高，幸好风稍大，会缓解稍热的天气。适宜旅游，可不要错过机会呦！"},{"type":"INFLUENZA","level":"少发","suggestion":"各气象指标适宜，不太容易感冒"},{"type":"EXERCISE","level":"较适宜","suggestion":"天气较好，但因气温较高且风力较强，请适当降低运动强度并注意户外防风。"},{"type":"ULTRAVIOLET","level":"中等","suggestion":"有中等强度的紫外线，建议你涂抹防晒指数高于15的防晒霜"}],"pm25":"101","airQuality":"轻度污染"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/d0c8a786c9177f3eb060133a7bcf3bc79f3d56b5.jpg"},"highTemperature":"31℃","lowTemperature":"25℃","day":"WED","date":"2018-09-05","weatherCondition":"晴转多云","windCondition":"西北风3-4级","indexes":[{"type":"CLOTHES","level":"很热","suggestion":"比较热，建议你穿T恤衫、长裙、衬衫等。昼夜温差大，早晚可以多穿点"},{"type":"CAR_WASH","level":"较不适宜","suggestion":"不适合洗车，未来24小时内有雨，雨水可能会再次弄脏你的车"},{"type":"INFLUENZA","level":"易发","suggestion":"容易感冒，请注意衣服增减，多喝开水多保暖"},{"type":"ULTRAVIOLET","level":"强","suggestion":"紫外线辐射很强，建议涂抹防晒指数在20左右的防晒霜。尽量避免在上午10点到下午两点之间暴露在阳光下"}],"pm25":"36","airQuality":"优"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/0823dd54564e92586e4869d99782d158ccbf4e8d.jpg"},"highTemperature":"30℃","lowTemperature":"24℃","day":"THU","date":"2018-09-06","weatherCondition":"多云转小雨","windCondition":"东南风3-4级","indexes":[{"type":"CLOTHES","level":"很热","suggestion":"比较热，建议你穿T恤衫、长裙、衬衫等。昼夜温差大，早晚可以多穿点"},{"type":"CAR_WASH","level":"较不适宜","suggestion":"不适合洗车，未来24小时内有雨，雨水可能会再次弄脏你的车"},{"type":"INFLUENZA","level":"易发","suggestion":"容易感冒，请注意衣服增减，多喝开水多保暖"},{"type":"ULTRAVIOLET","level":"最弱","suggestion":"紫外线辐射弱，无需特别防护。如果长期出门，建议你涂抹防晒指数在8-12之间的防晒霜"}],"pm25":"36","airQuality":"优"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/0823dd54564e92586e4869d99782d158ccbf4e8d.jpg"},"highTemperature":"30℃","lowTemperature":"24℃","day":"THU","date":"2018-09-06","weatherCondition":"多云转小雨","windCondition":"东南风3-4级","indexes":[{"type":"CLOTHES","level":"很热","suggestion":"比较热，建议你穿T恤衫、长裙、衬衫等。昼夜温差大，早晚可以多穿点"},{"type":"CAR_WASH","level":"较不适宜","suggestion":"不适合洗车，未来24小时内有雨，雨水可能会再次弄脏你的车"},{"type":"INFLUENZA","level":"易发","suggestion":"容易感冒，请注意衣服增减，多喝开水多保暖"},{"type":"ULTRAVIOLET","level":"最弱","suggestion":"紫外线辐射弱，无需特别防护。如果长期出门，建议你涂抹防晒指数在8-12之间的防晒霜"}],"pm25":"36","airQuality":"优"}]
         */

        private String country;
        private String province;
        private String city;
        private String county;
        private String askingDay;
        private String askingDate;
        private List<WeatherForecastBean> weatherForecast;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getAskingDay() {
            return askingDay;
        }

        public void setAskingDay(String askingDay) {
            this.askingDay = askingDay;
        }

        public String getAskingDate() {
            return askingDate;
        }

        public void setAskingDate(String askingDate) {
            this.askingDate = askingDate;
        }

        public List<WeatherForecastBean> getWeatherForecast() {
            return weatherForecast;
        }

        public void setWeatherForecast(List<WeatherForecastBean> weatherForecast) {
            this.weatherForecast = weatherForecast;
        }

        public static class WeatherForecastBean {
            @Override
            public String toString() {
                return "WeatherForecastBean{" +
                        "weatherIcon=" + weatherIcon +
                        ", highTemperature='" + highTemperature + '\'' +
                        ", lowTemperature='" + lowTemperature + '\'' +
                        ", day='" + day + '\'' +
                        ", date='" + date + '\'' +
                        ", weatherCondition='" + weatherCondition + '\'' +
                        ", windCondition='" + windCondition + '\'' +
                        ", currentTemperature='" + currentTemperature + '\'' +
                        ", currentPM25='" + currentPM25 + '\'' +
                        ", currentAirQuality='" + currentAirQuality + '\'' +
                        ", pm25='" + pm25 + '\'' +
                        ", airQuality='" + airQuality + '\'' +
                        ", indexes=" + indexes +
                        '}';
            }

            /**
             * weatherIcon : {"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/4e4a20a4462309f7c70a4225790e0cf3d7cad6a4.jpg"}
             * highTemperature : 32℃
             * lowTemperature : 24℃
             * day : TUE
             * date : 2018-09-04
             * weatherCondition : 多云
             * windCondition : 西北风4-5级
             * currentTemperature : 31℃
             * currentPM25 : 101
             * currentAirQuality : 轻度污染
             * indexes : [{"type":"CLOTHES","level":"炎热","suggestion":"很热，建议你穿短袖、短裤或短裙等夏季服装，请注意防晒"},{"type":"CAR_WASH","level":"较不宜","suggestion":"不太适合洗车，风力较大，汽车可能会蒙上污垢"},{"type":"TRIP","level":"适宜","suggestion":"天气较好，温度稍高，幸好风稍大，会缓解稍热的天气。适宜旅游，可不要错过机会呦！"},{"type":"INFLUENZA","level":"少发","suggestion":"各气象指标适宜，不太容易感冒"},{"type":"EXERCISE","level":"较适宜","suggestion":"天气较好，但因气温较高且风力较强，请适当降低运动强度并注意户外防风。"},{"type":"ULTRAVIOLET","level":"中等","suggestion":"有中等强度的紫外线，建议你涂抹防晒指数高于15的防晒霜"}]
             * pm25 : 101
             * airQuality : 轻度污染
             */

            private WeatherIconBean weatherIcon;
            private String highTemperature;
            private String lowTemperature;
            private String day;
            private String date;
            private String weatherCondition;
            private String windCondition;
            private String currentTemperature;
            private String currentPM25;
            private String currentAirQuality;
            private String pm25;
            private String airQuality;
            private List<IndexesBean> indexes;

            public WeatherIconBean getWeatherIcon() {
                return weatherIcon;
            }

            public void setWeatherIcon(WeatherIconBean weatherIcon) {
                this.weatherIcon = weatherIcon;
            }

            public String getHighTemperature() {
                return highTemperature;
            }

            public void setHighTemperature(String highTemperature) {
                this.highTemperature = highTemperature;
            }

            public String getLowTemperature() {
                return lowTemperature;
            }

            public void setLowTemperature(String lowTemperature) {
                this.lowTemperature = lowTemperature;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeatherCondition() {
                return weatherCondition;
            }

            public void setWeatherCondition(String weatherCondition) {
                this.weatherCondition = weatherCondition;
            }

            public String getWindCondition() {
                return windCondition;
            }

            public void setWindCondition(String windCondition) {
                this.windCondition = windCondition;
            }

            public String getCurrentTemperature() {
                return currentTemperature;
            }

            public void setCurrentTemperature(String currentTemperature) {
                this.currentTemperature = currentTemperature;
            }

            public String getCurrentPM25() {
                return currentPM25;
            }

            public void setCurrentPM25(String currentPM25) {
                this.currentPM25 = currentPM25;
            }

            public String getCurrentAirQuality() {
                return currentAirQuality;
            }

            public void setCurrentAirQuality(String currentAirQuality) {
                this.currentAirQuality = currentAirQuality;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getAirQuality() {
                return airQuality;
            }

            public void setAirQuality(String airQuality) {
                this.airQuality = airQuality;
            }

            public List<IndexesBean> getIndexes() {
                return indexes;
            }

            public void setIndexes(List<IndexesBean> indexes) {
                this.indexes = indexes;
            }

            public static class WeatherIconBean {
                /**
                 * src : http://h.hiphotos.baidu.com/xiaodu/pic/item/4e4a20a4462309f7c70a4225790e0cf3d7cad6a4.jpg
                 */

                private String src;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                @Override
                public String toString() {
                    return "WeatherIconBean{" +
                            "src='" + src + '\'' +
                            '}';
                }
            }

            public static class IndexesBean {
                /**
                 * type : CLOTHES
                 * level : 炎热
                 * suggestion : 很热，建议你穿短袖、短裤或短裙等夏季服装，请注意防晒
                 */

                private String type;
                private String level;
                private String suggestion;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getSuggestion() {
                    return suggestion;
                }

                public void setSuggestion(String suggestion) {
                    this.suggestion = suggestion;
                }

                @Override
                public String toString() {
                    return "IndexesBean{" +
                            "type='" + type + '\'' +
                            ", level='" + level + '\'' +
                            ", suggestion='" + suggestion + '\'' +
                            '}';
                }
            }
        }
    }
}
