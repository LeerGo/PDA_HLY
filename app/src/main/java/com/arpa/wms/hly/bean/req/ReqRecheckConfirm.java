package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.SNCodeVO;
import com.arpa.wms.hly.bean.base.ReqBase;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-06-28 17:01
 */
public class ReqRecheckConfirm extends ReqBase {
    private String outboundCode;
    private String outboundItemCode;
    private String recheckQuantity;
    private List<SNCodeVO> beachNumber;

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

    public List<SNCodeVO> getBeachNumber() {
        return beachNumber;
    }

    public void setBeachNumber(List<SNCodeVO> beachNumber) {
        this.beachNumber = beachNumber;
    }
}
