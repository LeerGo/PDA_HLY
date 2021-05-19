package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 17:11
 */
public class ReqGoodRecheckDetail extends ReqBase {
    private int outboundStatus;
    private String outboundCode;

    public void setParams(int outboundStatus, String outboundCode) {
        this.outboundCode = outboundCode;
        this.outboundStatus = outboundStatus;
    }

    public int getOutboundStatus() {
        return outboundStatus;
    }

    public void setOutboundStatus(int outboundStatus) {
        this.outboundStatus = outboundStatus;
    }

    public String getOutboundCode() {
        return outboundCode;
    }

    public void setOutboundCode(String outboundCode) {
        this.outboundCode = outboundCode;
    }
}
