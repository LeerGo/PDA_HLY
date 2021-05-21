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
public class GoodsItemVO {
    private String code;
    private String goodCode; // 商品编码
    private String goodsName; // 商品名称
    private String goodsUnitName; // 单位
    private String gmtManufacture; // 生产日期
    private String extendOne; // 产地
    private String extendTwo; // 特殊品项
    private int traysNum; // (整)托数
    private int supportNum; // 码托数量
    private int recheckQuantity; // 复核数量
    private int receivedQuantity; //  收货数量（收货确认用）
    private int itemReceivedQuantity; // 收货数量（收货详情列表用）
    private int planQuantity; // 计划数量，在复核里是拣货数量
    private String loadQuantity;
    private String outboundCode; // 出库码，复核用
    private String receiveCode; // 收货码，收货用
    private String goodsStatus; //  商品状态 ID
    private String goodsStatusName; // 商品状态名称
    private String location; // 库位
    private String tempInventoryCode; // 收货确认使用，临时库存code

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public String getTempInventoryCode() {
        return tempInventoryCode;
    }

    public void setTempInventoryCode(String tempInventoryCode) {
        this.tempInventoryCode = tempInventoryCode;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }

    public int getItemReceivedQuantity() {
        return itemReceivedQuantity;
    }

    public void setItemReceivedQuantity(int itemReceivedQuantity) {
        this.itemReceivedQuantity = itemReceivedQuantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGoodsStatusName() {
        return goodsStatusName;
    }

    public void setGoodsStatusName(String goodsStatusName) {
        this.goodsStatusName = goodsStatusName;
    }

    public int getTraysNum() {
        return traysNum;
    }

    public void setTraysNum(int traysNum) {
        this.traysNum = traysNum;
    }

    public int getRecheckQuantity() {
        return recheckQuantity;
    }

    public void setRecheckQuantity(int recheckQuantity) {
        this.recheckQuantity = recheckQuantity;
    }

    public int getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }

    public String getOutboundCode() {
        return outboundCode;
    }

    public void setOutboundCode(String outboundCode) {
        this.outboundCode = outboundCode;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getLoadQuantity() {
        return loadQuantity;
    }

    public void setLoadQuantity(String loadQuantity) {
        this.loadQuantity = loadQuantity;
    }

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
