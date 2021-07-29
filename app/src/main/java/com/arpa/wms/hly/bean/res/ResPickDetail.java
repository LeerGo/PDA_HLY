package com.arpa.wms.hly.bean.res;

import com.google.gson.annotations.SerializedName;

import com.arpa.wms.hly.bean.BatchRuleBean;
import com.arpa.wms.hly.bean.GoodsItemVO;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-07-13 14:21
 */
public class ResPickDetail {
    @SerializedName("pickingItemWithRegisterVOList")
    private List<GoodsItemVO> goods;
    private BatchRuleBean batchRule;

    public List<GoodsItemVO> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsItemVO> goods) {
        this.goods = goods;
    }

    public BatchRuleBean getBatchRule() {
        return batchRule;
    }

    public void setBatchRule(BatchRuleBean batchRule) {
        this.batchRule = batchRule;
    }
}
