package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.SPUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 */
public class ReqMoveSure extends ReqBase {
    public String warehouseCode;
    private String goodsCode;
    private Integer moveQuantity;
    private String moveLocation;
    private String location;
    private String goodsStatus;
    private String gmtManufacture;
    private String extendOne;

    public ReqMoveSure() {
        warehouseCode = SPUtils.getInstance().getString(SPKEY.WAREHOUSE_CODE);
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getMoveQuantity() {
        return moveQuantity;
    }

    public void setMoveQuantity(Integer moveQuantity) {
        this.moveQuantity = moveQuantity;
    }

    public String getMoveLocation() {
        return moveLocation;
    }

    public void setMoveLocation(String moveLocation) {
        this.moveLocation = moveLocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGmtManufacture() {
        return gmtManufacture;
    }

    public void setGmtManufacture(String gmtManufacture) {
        this.gmtManufacture = gmtManufacture;
    }

    public String getExtendOne() {
        return extendOne;
    }

    public void setExtendOne(String extendOne) {
        this.extendOne = extendOne;
    }
}
