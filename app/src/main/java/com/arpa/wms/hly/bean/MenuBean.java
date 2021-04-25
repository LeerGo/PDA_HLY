package com.arpa.wms.hly.bean;

public class MenuBean {
    private int imageRes;
    private String text;
    private String path;

    public MenuBean(int imageRes, String text) {
        this(imageRes, text, null);
    }

    public MenuBean(int imageRes, String text, String path) {
        this.imageRes = imageRes;
        this.path = path;
        this.text = text;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
