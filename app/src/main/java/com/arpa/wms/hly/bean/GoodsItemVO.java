package com.arpa.wms.hly.bean;

import com.arpa.wms.hly.bean.res.ResGoodTakeConfirm;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 10:52
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class GoodsItemVO extends SelectItem {
    private String code;
    private String goodCode; // 商品编码
    private String goodsName; // 商品名称
    private String goodsUnitName; // 单位
    private String gmtManufacture; // 生产日期
    private String extendOne; // 产地
    private String extendTwo; // 特殊品项
    private Integer extendThree; // 拓展 3
    private Integer extendFour; // 拓展 4
    private String extendFive; // 拓展 5
    private String extendSix; // 拓展 6
    private int traysNum; // (整)托数
    private int pickingTraysNum; // (整)托数 - 已捡
    private Integer supportNum; // 码托数量
    private int recheckQuantity; // 复核数量
    private int pickingQuantity; // 拣货数量
    private Integer receivedQuantity; //  收货数量
    private int planQuantity; // 计划数量，在复核里是拣货数量
    private int quantity;
    private String containerBarCode; // 容器号
    private String loadQuantity;
    private String outboundCode; // 出库码，复核用
    private String receiveCode; // 收货码，收货用
    private String goodsStatus; //  商品状态 ID
    private String goodsStatusName; // 商品状态名称
    private String location; // 库位（有时也用作库位名称）
    private String locationName; // 库位名称
    private String tempInventoryCode; // 收货确认使用，临时库存code
    private String oldLocationName;// 拣货库位
    private String serialNumber; //序列号
    private String supplier;// 供应商
    private String gmtStock;// 存货日期
    private String gmtExpire; // 过期日期
    private int expirationQuantity; // 保质期
    private BatchRuleBean batchRule; // 批次规则
    private int inventoryQuantity; // 库存数量

    public int getPickingTraysNum() {
        return pickingTraysNum;
    }

    public void setPickingTraysNum(int pickingTraysNum) {
        this.pickingTraysNum = pickingTraysNum;
    }

    public String getOldLocationName() {
        return oldLocationName;
    }

    public void setOldLocationName(String oldLocationName) {
        this.oldLocationName = oldLocationName;
    }

    public Integer getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(Integer receivedQuantity) {
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

    public Integer getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(Integer supportNum) {
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

    /**
     * 获取待复核数量
     */
    public int getWaitRecheckQuantity() {
        return this.planQuantity - this.recheckQuantity;
    }

    /**
     * 获取待拣货数量
     */
    public int getWaitPickQuantity() {
        return this.planQuantity - this.receivedQuantity;
    }

    public boolean isPickFinish() {
        return pickingTraysNum == traysNum;
    }

    public int getPickingQuantity() {
        return pickingQuantity;
    }

    public void setPickingQuantity(int pickingQuantity) {
        this.pickingQuantity = pickingQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getContainerBarCode() {
        return containerBarCode;
    }

    public void setContainerBarCode(String containerBarCode) {
        this.containerBarCode = containerBarCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void increasePickingTraysNum() {
        this.pickingTraysNum++;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getGmtStock() {
        return gmtStock;
    }

    public void setGmtStock(String gmtStock) {
        this.gmtStock = gmtStock;
    }

    public String getGmtExpire() {
        return gmtExpire;
    }

    public void setGmtExpire(String gmtExpire) {
        this.gmtExpire = gmtExpire;
    }

    public int getExpirationQuantity() {
        return expirationQuantity;
    }

    public void setExpirationQuantity(int expirationQuantity) {
        this.expirationQuantity = expirationQuantity;
    }

    public BatchRuleBean getBatchRule() {
        return batchRule;
    }

    public void setBatchRule(BatchRuleBean batchRule) {
        this.batchRule = batchRule;
    }

    /**
     * fix: http://112.6.75.17:801/zentao/bug-view-26003.html
     */
    public void fromDetail(ResGoodTakeConfirm data) {
        this.setGmtManufacture(data.getGmtManufacture());
        this.setExtendOne(data.getExtendOne());
        this.setExtendTwo(data.getExtendTwo());
        this.setGmtExpire(data.getGmtExpire());
        this.setGmtStock(data.getGmtStock());
        this.setSerialNumber(data.getSerialNumber());
        this.setExtendFour(data.getExtendFour());
        this.setExtendFive(data.getExtendFive());
        this.setExtendSix(data.getExtendSix());
    }

    /**
     * 设置收货状态
     */
    public void setStatus(InventoryStatus status) {
        if (null == status) return;
        this.setGoodsStatus(status.getCode());
        this.setGoodsStatusName(status.getName());
        this.setLocation(status.getDefaultLocationName());
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }
}
