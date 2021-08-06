package com.arpa.wms.hly.bean;

public class MenuBean {
    private int imageRes;
    private Class<?> clz;
    private String title;
    private String describe;

    public MenuBean(int imageRes, String title, String describe, Class<?> clz) {
        this.imageRes = imageRes;
        this.clz = clz;
        this.title = title;
        this.describe = describe;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
