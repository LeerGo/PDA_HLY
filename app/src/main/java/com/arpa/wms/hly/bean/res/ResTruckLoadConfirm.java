package com.arpa.wms.hly.bean.res;

import com.arpa.wms.hly.bean.OutboundItemVOList;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 10:52
 */
public class ResTruckLoadConfirm {
    private String code;
    private List<OutboundItemVOList> outboundItemVOList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OutboundItemVOList> getOutboundItemVOList() {
        return outboundItemVOList;
    }

    public void setOutboundItemVOList(List<OutboundItemVOList> outboundItemVOList) {
        this.outboundItemVOList = outboundItemVOList;
    }
}
