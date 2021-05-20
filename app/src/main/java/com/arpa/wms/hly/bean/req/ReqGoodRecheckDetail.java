package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 17:11
 */
public class ReqGoodRecheckDetail extends ReqBase {
    private int recheckStatus;
    private String outboundCode;
    private String outboundItemCode;

    public void setParams(String outboundCode, String outboundItemCode) {
        this.outboundCode = outboundCode;
        this.outboundItemCode = outboundItemCode;
    }

    public void setParams(int outboundStatus, String outboundCode) {
        this.outboundCode = outboundCode;
        this.recheckStatus = outboundStatus;
    }

    public String getOutboundItemCode() {
        return outboundItemCode;
    }

    public void setOutboundItemCode(String outboundItemCode) {
        this.outboundItemCode = outboundItemCode;
    }

    public int getRecheckStatus() {
        return recheckStatus;
    }

    public void setRecheckStatus(int recheckStatus) {
        this.recheckStatus = recheckStatus;
    }

    public String getOutboundCode() {
        return outboundCode;
    }

    public void setOutboundCode(String outboundCode) {
        this.outboundCode = outboundCode;
    }
}
