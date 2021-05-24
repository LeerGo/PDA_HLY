package com.arpa.wms.hly.bean.res;

import com.arpa.wms.hly.bean.BatchRuleBean;

import java.util.List;

public class ResMoveGoods {
    private String goodCode;
    private String goodsBarCode;
    private String goodsCode;
    private String goodsName;
    private String goodsUnit;
    private String location;
    private String locationName;
    private int toPage;
    private String unitName;
    private List<InventoryListBean> inventoryList;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getToPage() {
        return toPage;
    }

    public void setToPage(int toPage) {
        this.toPage = toPage;
    }

    public List<InventoryListBean> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<InventoryListBean> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public static class InventoryListBean {
        /**
         * code : 195d856e569747ac967e0d432425dfcc
         * containerBarCode : TP002
         * createdBy : 7d1c6e44b2934ab39d0a077f5337c182
         * expirationQuantity : 2
         * extendFive : 2020-10-04
         * extendFour : 1
         * extendOne : 1
         * extendSix : 2020-10-04
         * extendThree : 1
         * extendTwo : 1
         * gmtCreated : 2020-10-20 09:54:10
         * gmtExpire : 2020-10-06
         * gmtManufacture : 2020-10-04
         * gmtModified : 2020-10-20 09:54:10
         * gmtStock : 2020-10-20
         * goodCode : xy01
         * goodsBarCode : xy01
         * goodsCode : 01a324349b1d4f41bcef7f5acdea9c8a
         * goodsName : xy商品01
         * goodsStatus : 416362839df3487cae9189ac353e6a28
         * goodsStatusName : 状态名称1
         * goodsUnit : 98b9733923294c81a245442c172640e2
         * id : 301
         * inventoryCode : 195d856e569747ac967e0d432425dfcc
         * isLock : 0
         * location : e92125b0811941ee8433539aa732770e
         * lockName : 未锁定
         * lot : hz001|xy01|20201004|20201006|20201020|20201020003|xy供应商01|1|1|1|1|20201004|20201004
         * modifiedBy :
         * quantity : 15
         * receiveLot : 20201020003
         * remarks :
         * serialNumber : 1
         * shipmentCode : e3d5590b9a2343628f744b6618df2b66
         * shipmentName : 梦娇兰
         * supplier : xy供应商01
         * tempInventory : 0
         * unitName : 只
         * useQuantity : 0
         * version : 0
         * warehouseCode : 854e023b9edf4837a391b22030d6e65f
         * warehouseSpaceCode : 910e784c6d76452294092216d698483c
         */
        private String code;
        private String containerBarCode;
        private String createdBy;
        private String expirationQuantity;
        private String extendFive;
        private String extendFour;
        private String extendOne;
        private String extendSix;
        private String extendThree;
        private String extendTwo;
        private String gmtCreated;
        private String gmtExpire;
        private String gmtManufacture;
        private String gmtModified;
        private String gmtStock;
        private String goodCode;
        private String goodsBarCode;
        private String goodsCode;
        private String goodsName;
        private String goodsStatus;
        private String goodsStatusName;
        private String goodsUnit;
        private String id;
        private String inventoryCode;
        private String isLock;
        private String location;
        private String lockName;
        private String lot;
        private String modifiedBy;
        private String quantity;
        private String receiveLot;
        private String remarks;
        private String serialNumber;
        private String shipmentCode;
        private String shipmentName;
        private String supplier;
        private String tempInventory;
        private String unitName;
        private String useQuantity;
        private String version;
        private String warehouseCode;
        private String warehouseSpaceCode;
        private String inventoryContainerBarCode;
        private BatchRuleBean batchRule;
        private int canUseQuantity;
        private int supportNum;
        private int traysNum;

        public int getSupportNum() {
            return supportNum;
        }

        public void setSupportNum(int supportNum) {
            this.supportNum = supportNum;
        }

        public int getTraysNum() {
            return traysNum;
        }

        public void setTraysNum(int traysNum) {
            this.traysNum = traysNum;
        }

        public BatchRuleBean getBatchRule() {
            return batchRule;
        }

        public void setBatchRule(BatchRuleBean batchRule) {
            this.batchRule = batchRule;
        }

        public int getCanUseQuantity() {
            return canUseQuantity;
        }

        public void setCanUseQuantity(int canUseQuantity) {
            this.canUseQuantity = canUseQuantity;
        }

        public String getInventoryContainerBarCode() {
            return inventoryContainerBarCode;
        }

        public void setInventoryContainerBarCode(String inventoryContainerBarCode) {
            this.inventoryContainerBarCode = inventoryContainerBarCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getContainerBarCode() {
            return containerBarCode;
        }

        public void setContainerBarCode(String containerBarCode) {
            this.containerBarCode = containerBarCode;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getExpirationQuantity() {
            return expirationQuantity;
        }

        public void setExpirationQuantity(String expirationQuantity) {
            this.expirationQuantity = expirationQuantity;
        }

        public String getExtendFive() {
            return extendFive;
        }

        public void setExtendFive(String extendFive) {
            this.extendFive = extendFive;
        }

        public String getExtendFour() {
            return extendFour;
        }

        public void setExtendFour(String extendFour) {
            this.extendFour = extendFour;
        }

        public String getExtendOne() {
            return extendOne;
        }

        public void setExtendOne(String extendOne) {
            this.extendOne = extendOne;
        }

        public String getExtendSix() {
            return extendSix;
        }

        public void setExtendSix(String extendSix) {
            this.extendSix = extendSix;
        }

        public String getExtendThree() {
            return extendThree;
        }

        public void setExtendThree(String extendThree) {
            this.extendThree = extendThree;
        }

        public String getExtendTwo() {
            return extendTwo;
        }

        public void setExtendTwo(String extendTwo) {
            this.extendTwo = extendTwo;
        }

        public String getGmtCreated() {
            return gmtCreated;
        }

        public void setGmtCreated(String gmtCreated) {
            this.gmtCreated = gmtCreated;
        }

        public String getGmtExpire() {
            return gmtExpire;
        }

        public void setGmtExpire(String gmtExpire) {
            this.gmtExpire = gmtExpire;
        }

        public String getGmtManufacture() {
            return gmtManufacture;
        }

        public void setGmtManufacture(String gmtManufacture) {
            this.gmtManufacture = gmtManufacture;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getGmtStock() {
            return gmtStock;
        }

        public void setGmtStock(String gmtStock) {
            this.gmtStock = gmtStock;
        }

        public String getGoodCode() {
            return goodCode;
        }

        public void setGoodCode(String goodCode) {
            this.goodCode = goodCode;
        }

        public String getGoodsBarCode() {
            return goodsBarCode;
        }

        public void setGoodsBarCode(String goodsBarCode) {
            this.goodsBarCode = goodsBarCode;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsStatus() {
            return goodsStatus;
        }

        public void setGoodsStatus(String goodsStatus) {
            this.goodsStatus = goodsStatus;
        }

        public String getGoodsStatusName() {
            return goodsStatusName;
        }

        public void setGoodsStatusName(String goodsStatusName) {
            this.goodsStatusName = goodsStatusName;
        }

        public String getGoodsUnit() {
            return goodsUnit;
        }

        public void setGoodsUnit(String goodsUnit) {
            this.goodsUnit = goodsUnit;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInventoryCode() {
            return inventoryCode;
        }

        public void setInventoryCode(String inventoryCode) {
            this.inventoryCode = inventoryCode;
        }

        public String getIsLock() {
            return isLock;
        }

        public void setIsLock(String isLock) {
            this.isLock = isLock;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLockName() {
            return lockName;
        }

        public void setLockName(String lockName) {
            this.lockName = lockName;
        }

        public String getLot() {
            return lot;
        }

        public void setLot(String lot) {
            this.lot = lot;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getReceiveLot() {
            return receiveLot;
        }

        public void setReceiveLot(String receiveLot) {
            this.receiveLot = receiveLot;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getShipmentCode() {
            return shipmentCode;
        }

        public void setShipmentCode(String shipmentCode) {
            this.shipmentCode = shipmentCode;
        }

        public String getShipmentName() {
            return shipmentName;
        }

        public void setShipmentName(String shipmentName) {
            this.shipmentName = shipmentName;
        }

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public String getTempInventory() {
            return tempInventory;
        }

        public void setTempInventory(String tempInventory) {
            this.tempInventory = tempInventory;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getUseQuantity() {
            return useQuantity;
        }

        public void setUseQuantity(String useQuantity) {
            this.useQuantity = useQuantity;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getWarehouseCode() {
            return warehouseCode;
        }

        public void setWarehouseCode(String warehouseCode) {
            this.warehouseCode = warehouseCode;
        }

        public String getWarehouseSpaceCode() {
            return warehouseSpaceCode;
        }

        public void setWarehouseSpaceCode(String warehouseSpaceCode) {
            this.warehouseSpaceCode = warehouseSpaceCode;
        }
    }
}