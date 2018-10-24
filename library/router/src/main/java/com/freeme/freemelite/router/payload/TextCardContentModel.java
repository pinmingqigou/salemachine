package com.freeme.freemelite.router.payload;

public class TextCardContentModel {

    /**
     * thingsBrand : null
     * things : 软中华
     * price : 65.0
     * content : 请您支付65.0元
     * num : 1
     * thingsCategory : null
     */

    private Object thingsBrand;
    private String things;
    private String price;
    private String content;
    private int num;
    private Object thingsCategory;

    public Object getThingsBrand() {
        return thingsBrand;
    }

    public void setThingsBrand(Object thingsBrand) {
        this.thingsBrand = thingsBrand;
    }

    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Object getThingsCategory() {
        return thingsCategory;
    }

    public void setThingsCategory(Object thingsCategory) {
        this.thingsCategory = thingsCategory;
    }
}
