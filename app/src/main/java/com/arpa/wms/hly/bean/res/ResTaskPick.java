package com.arpa.wms.hly.bean.res;

import com.arpa.wms.hly.bean.GoodsItemVO;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-07-17 07:54
 */
public class ResTaskPick {
    Integer pickingQuantity;
    private List<GoodsItemVO> pickingItemWithRegisterVOList;

    public Integer getPickingQuantity() {
        return pickingQuantity;
    }

    public void setPickingQuantity(Integer pickingQuantity) {
        this.pickingQuantity = pickingQuantity;
    }

    public List<GoodsItemVO> getPickingItemWithRegisterVOList() {
        return pickingItemWithRegisterVOList;
    }

    public void setPickingItemWithRegisterVOList(List<GoodsItemVO> pickingItemWithRegisterVOList) {
        this.pickingItemWithRegisterVOList = pickingItemWithRegisterVOList;
    }
}
