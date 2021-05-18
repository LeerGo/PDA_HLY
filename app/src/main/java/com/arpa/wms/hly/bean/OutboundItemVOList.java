package com.arpa.wms.hly.bean;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 10:52
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class OutboundItemVOList {
    private String code;
    private String goodsName; // 商品名称
    private String goodsUnitName; // 单位
    private String extendOne; // 产地
    private String gmtManufacture; // 生产日期
    private String extendTwo; // 特殊品项
    private int planQuantity; // 计划数量

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getExtendOne() {
        return extendOne;
    }

    public void setExtendOne(String extendOne) {
        this.extendOne = extendOne;
    }

    public String getGmtManufacture() {
        return gmtManufacture;
    }

    public void setGmtManufacture(String gmtManufacture) {
        this.gmtManufacture = gmtManufacture;
    }

    public String getExtendTwo() {
        return extendTwo;
    }

    public void setExtendTwo(String extendTwo) {
        this.extendTwo = extendTwo;
    }

    public int getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(int planQuantity) {
        this.planQuantity = planQuantity;
    }
}
