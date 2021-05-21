package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 17:11
 */
public class ReqGoodTakeDetail extends ReqBase {
    private int receiveStatus;
    private String receiveCode;
    private String receiveItemCode;

    public void setParams(String receiveCode, String receiveItemCode) {
        this.receiveCode = receiveCode;
        this.receiveItemCode = receiveItemCode;
    }

    public void setParams(int receiveStatus, String receiveCode) {
        this.receiveCode = receiveCode;
        this.receiveStatus = receiveStatus;
    }

    public String getReceiveItemCode() {
        return receiveItemCode;
    }

    public void setReceiveItemCode(String receiveItemCode) {
        this.receiveItemCode = receiveItemCode;
    }

    public int getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(int receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }
}
