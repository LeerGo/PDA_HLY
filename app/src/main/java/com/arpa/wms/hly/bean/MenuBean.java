package com.arpa.wms.hly.bean;

/**
 * Created by TYY on 2019/1/21.
 */
public class MenuBean {
    private int imageRes;
    private int count;
    private String text;

    public MenuBean(int imageRes, String text) {
        this(imageRes, text, 0);
    }

    public MenuBean(int imageRes, String text, int count) {
        this.imageRes = imageRes;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
