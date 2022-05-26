package com.arpa.wms.hly.bean.req;

import com.google.gson.annotations.SerializedName;

import com.arpa.wms.hly.bean.PickingItemVO;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/26 16:02
 */
public class ReqPickEdit {
    @SerializedName("pickingRegisterCode")
    private String code;
    @SerializedName("inventoryWithDistributionVOList")
    private List<PickingItemVO> items;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PickingItemVO> getItems() {
        return items;
    }

    public void setItems(List<PickingItemVO> items) {
        this.items = items;
    }
}
