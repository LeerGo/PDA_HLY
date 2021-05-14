package com.arpa.wms.hly.bean;

public class MenuBean {
    private int imageRes;
    private String title;
    private String path;
    private String describe;

    public MenuBean(int imageRes, String title, String describe, String path) {
        this.imageRes = imageRes;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
