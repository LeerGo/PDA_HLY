package com.arpa.wms.hly.bean.res;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-08-24 14:05
 */
public class ResVersion {
    private int deleted;
    private String id;
    private String updateContent;
    private String url;
    private String versionName;
    private int versionCode;
    private int whetherForce;

    public boolean isForceUpdate() {
        return this.whetherForce == 1;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getWhetherForce() {
        return whetherForce;
    }

    public void setWhetherForce(int whetherForce) {
        this.whetherForce = whetherForce;
    }
}
