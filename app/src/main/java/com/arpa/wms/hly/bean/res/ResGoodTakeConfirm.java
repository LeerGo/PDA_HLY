package com.arpa.wms.hly.bean.res;

import com.google.gson.annotations.Expose;

import com.arpa.wms.hly.bean.BatchRuleBean;
import com.arpa.wms.hly.bean.InventoryStatus;
import com.arpa.wms.hly.bean.req.ReqGoodTakeConfirm;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-20 14:39
 */
public class ResGoodTakeConfirm extends ReqGoodTakeConfirm {
    @Expose(serialize = false)
    private int planQuantity; // 应收数量
    @Expose(serialize = false)
    private String goodsName; // 商品名称
    @Expose(serialize = false)
    private String goodsUnitName;// 商品单位
    @Expose(serialize = false)
    private ReceiveBean receive; // 取车牌号
    @Expose(serialize = false)
    private List<InventoryStatus> inventoryStatusList; // 去状态列表
    @Expose(serialize = false)
    private BatchRuleBean batchRule; // 规则列表
    @Expose(serialize = false)
    private int expirationQuantity; // 保质期
    @Expose(serialize = false)
    private String extendOne; // 产地
    @Expose(serialize = false)
    private String extendTwo; // 特殊品项
    @Expose(serialize = false)
    private Integer extendThree; // 拓展 3
    @Expose(serialize = false)
    private Integer extendFour; // 拓展 4
    @Expose(serialize = false)
    private String extendFive; // 拓展 5
    @Expose(serialize = false)
    private String extendSix; // 拓展 6
    @Expose(serialize = false)
    private String gmtManufacture; // 生产日期
    @Expose(serialize = false)
    private String gmtExpire; // 过期日期
    @Expose(serialize = false)
    private String gmtStock; // 存货日期
    @Expose(serialize = false)
    private String serialNumber; // 序列号
    @Expose(serialize = false)
    private int receivedQuantity; // 已收货数量

    public ReceiveBean getReceive() {
        return receive;
    }

    public void setReceive(ReceiveBean receive) {
        this.receive = receive;
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

    public List<InventoryStatus> getInventoryStatusList() {
        return inventoryStatusList;
    }

    public void setInventoryStatusList(List<InventoryStatus> inventoryStatusList) {
        this.inventoryStatusList = inventoryStatusList;
    }

    public BatchRuleBean getBatchRule() {
        return batchRule;
    }

    public void setBatchRule(BatchRuleBean batchRule) {
        this.batchRule = batchRule;
    }

    public int getExpirationQuantity() {
        return expirationQuantity;
    }

    public void setExpirationQuantity(int expirationQuantity) {
        this.expirationQuantity = expirationQuantity;
    }

    public String getExtendOne() {
        return extendOne;
    }

    public void setExtendOne(String extendOne) {
        this.extendOne = extendOne;
    }

    public String getExtendTwo() {
        return extendTwo;
    }

    public void setExtendTwo(String extendTwo) {
        this.extendTwo = extendTwo;
    }

    public String getGmtManufacture() {
        return gmtManufacture;
    }

    public void setGmtManufacture(String gmtManufacture) {
        this.gmtManufacture = gmtManufacture;
    }

    public String getGmtExpire() {
        return gmtExpire;
    }

    public void setGmtExpire(String gmtExpire) {
        this.gmtExpire = gmtExpire;
    }

    public String getGmtStock() {
        return gmtStock;
    }

    public void setGmtStock(String gmtStock) {
        this.gmtStock = gmtStock;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public Integer getExtendThree() {
        return extendThree;
    }

    public void setExtendThree(Integer extendThree) {
        this.extendThree = extendThree;
    }

    public Integer getExtendFour() {
        return extendFour;
    }

    public void setExtendFour(Integer extendFour) {
        this.extendFour = extendFour;
    }

    public String getExtendFive() {
        return extendFive;
    }

    public void setExtendFive(String extendFive) {
        this.extendFive = extendFive;
    }

    public String getExtendSix() {
        return extendSix;
    }

    public void setExtendSix(String extendSix) {
        this.extendSix = extendSix;
    }

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public static class ReceiveBean {
        private String licensePlateNumber;

        public String getLicensePlateNumber() {
            return licensePlateNumber;
        }

        public void setLicensePlateNumber(String licensePlateNumber) {
            this.licensePlateNumber = licensePlateNumber;
        }
    }

}
