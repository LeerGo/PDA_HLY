package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.GoodsItemVO;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-20 14:32
 */
public class ReqGoodTakeConfirm {
    private String code;
    private String receiveCode;
    private List<GoodsItemVO> receiveRegisterVOList;

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<GoodsItemVO> getReceiveItemWithRegisterVOList() {
        return receiveRegisterVOList;
    }

    public void setReceiveItemWithRegisterVOList(List<GoodsItemVO> receiveRegisterVOList) {
        this.receiveRegisterVOList = receiveRegisterVOList;
    }
}
