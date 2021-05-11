package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SPUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 请求：位移商品
 * </p>
 */
public class ReqMoveGoods extends ReqBase {
    public String warehouseCode;
    private String location;
    private String containerBarCode;
    private String goodsBarCode;

    public ReqMoveGoods() {
        warehouseCode = SPUtils.getInstance().getString(Const.SPKEY.WAREHOUSE_CODE);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContainerBarCode() {
        return containerBarCode;
    }

    public void setContainerBarCode(String containerBarCode) {
        this.containerBarCode = containerBarCode;
    }

    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }
}
