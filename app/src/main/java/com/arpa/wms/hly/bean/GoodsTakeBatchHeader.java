package com.arpa.wms.hly.bean;

import com.arpa.wms.hly.bean.res.ResGoodTakeConfirm;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-13 14:25
 */
public class GoodsTakeBatchHeader {
    private int planQuantity; // 应收数量
    private String goodsName; // 商品名称
    private String goodsUnitName;// 商品单位
    private String licensePlateNumber;// 车牌号

    public void convert(ResGoodTakeConfirm raw) {
        setGoodsName(raw.getGoodsName());
        setPlanQuantity(raw.getPlanQuantity());
        setGoodsUnitName(raw.getGoodsUnitName());
        setLicensePlateNumber(raw.getReceive().getLicensePlateNumber());
    }

    public int getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(int planQuantity) {
        this.planQuantity = planQuantity;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }
}
