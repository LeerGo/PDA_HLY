package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.GoodsItemVO;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-20 14:32
 */
public class ReqGoodTakeConfirm {
    private String code;
    private String receiveCode;
    private List<GoodsItemVO> receiveItemWithRegisterVOList;

    public ReqGoodTakeConfirm() {
        this.receiveItemWithRegisterVOList = new ArrayList<>();
    }

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
        return receiveItemWithRegisterVOList;
    }

    public void setReceiveItemWithRegisterVOList(List<GoodsItemVO> receiveItemWithRegisterVOList) {
        this.receiveItemWithRegisterVOList = receiveItemWithRegisterVOList;
    }
}
