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
    // @Expose(serialize = false)
    private String extendOne; // 产地
    // @Expose(serialize = false)
    private String extendTwo; // 特殊品项
    // @Expose(serialize = false)
    private String gmtManufacture; // 生产日期

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
