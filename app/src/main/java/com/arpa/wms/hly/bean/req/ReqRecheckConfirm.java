package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-06-28 17:01
 */
public class ReqRecheckConfirm extends ReqBase {
    private String outboundCode;
    private String outboundItemCode;
    private String recheckQuantity;
    private String beachNumber;
    private String ratio;// 换箱比例
    private String productionDate;// 生产日期

    public String getOutboundCode() {
        return outboundCode;
    }

    public void setOutboundCode(String outboundCode) {
        this.outboundCode = outboundCode;
    }

    public String getOutboundItemCode() {
        return outboundItemCode;
    }

    public void setOutboundItemCode(String outboundItemCode) {
        this.outboundItemCode = outboundItemCode;
    }

    public String getRecheckQuantity() {
        return recheckQuantity;
    }

    public void setRecheckQuantity(String recheckQuantity) {
        this.recheckQuantity = recheckQuantity;
    }

    public String getBeachNumber() {
        return beachNumber;
    }

    public void setBeachNumber(String beachNumber) {
        this.beachNumber = beachNumber;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
}
