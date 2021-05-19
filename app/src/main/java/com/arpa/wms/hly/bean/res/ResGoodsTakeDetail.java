package com.arpa.wms.hly.bean.res;

import com.arpa.wms.hly.bean.BatchRuleBean;

import java.util.List;

@Deprecated
public class ResGoodsTakeDetail {
    private String assignBy;
    private String assignName;
    private String billTypeName;
    private String code;
    private String createdBy;
    private String createdName;
    private String gmtCreated;
    private String gmtModified;
    private String goodsQuantity;
    private String goodsTypeQuantity;
    private String groupCode;
    private String jobQuantity;
    private String jobStatus;
    private String modifiedBy;
    private String modifiedName;
    private String operatorName;
    private String shipmentName;
    private String sourceCode;
    private String supplierName;
    private String taskType;
    private String warehouseCode;
    private String billingUnit;
    private List<ItemsBean> items;

    public String getBillingUnit() {
        return billingUnit;
    }

    public void setBillingUnit(String billingUnit) {
        this.billingUnit = billingUnit;
    }

    public String getAssignBy() {
        return assignBy;
    }

    public void setAssignBy(String assignBy) {
        this.assignBy = assignBy;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(String goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getGoodsTypeQuantity() {
        return goodsTypeQuantity;
    }

    public void setGoodsTypeQuantity(String goodsTypeQuantity) {
        this.goodsTypeQuantity = goodsTypeQuantity;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getJobQuantity() {
        return jobQuantity;
    }

    public void setJobQuantity(String jobQuantity) {
        this.jobQuantity = jobQuantity;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * code : 9fe4d56b640344d189a625e31927e7f0
         * receiveCode : SH202010300001
         * goodsCode : d75238d683d94bfda8c567c08ecbea3d
         * goodCode : xy02
         * goodsName : xy测试商品02
         * goodsBarCode : xy02
         * goodsUnit : 3d17cccd30be47ec8a83cafbfd12589b
         * spec : spec2
         * expirationQuantity : 2
         * basicUnit : 98b9733923294c81a245442c172640e2
         * basicUnitName : 只
         * planQuantity : 2.0
         * receivedQuantity : 0.0
         * inboundQuantity : 0.0
         * unitConvertQuantity : 10
         * status : 0
         * remarks :
         * receiveRegisterVOList : []
         * cancelQuantity : 0
         * canReturnQuantity : 0
         */

        private String code;
        private String receiveCode;
        private String goodsCode;
        private String goodCode;
        private String goodsName;
        private String goodsBarCode;
        private String goodsUnit;
        private String spec;
        private String expirationQuantity;
        private String basicUnit;
        private String basicUnitName;
        private Integer planQuantity;
        private Integer receivedQuantity;
        private String inboundQuantity;
        private String unitConvertQuantity;
        private String status;
        private String remarks;
        private String cancelQuantity;
        private String canReturnQuantity;
        private String goodsStatusName;
        private String gmtManufacture;
        private String gmtExpire;
        private String gmtStock;
        private String receiveLot;
        private String recommendLocationName;
        private String putawayBoardQuantity;
        private BatchRuleBean batchRule;
        private String containerBarCode;
        private String supplier;
        private String serialNumber;
        private String extendOne;
        private String extendTwo;
        private String extendThree;
        private String extendFour;
        private String extendFive;
        private String extendSix;
        private List<?> receiveRegisterVOList;
        private String putawayQuantity;
        private String putawayCode;
        private String locationSerialNumber;

        public String getPutawayBoardQuantity() {
            return putawayBoardQuantity;
        }

        public void setPutawayBoardQuantity(String putawayBoardQuantity) {
            this.putawayBoardQuantity = putawayBoardQuantity;
        }

        public BatchRuleBean getBatchRule() {
            return batchRule;
        }

        public void setBatchRule(BatchRuleBean batchRule) {
            this.batchRule = batchRule;
        }

        public String getRecommendLocationName() {
            return recommendLocationName;
        }

        public void setRecommendLocationName(String recommendLocationName) {
            this.recommendLocationName = recommendLocationName;
        }

        public String getReceiveLot() {
            return receiveLot;
        }

        public void setReceiveLot(String receiveLot) {
            this.receiveLot = receiveLot;
        }

        public String getGmtStock() {
            return gmtStock;
        }

        public void setGmtStock(String gmtStock) {
            this.gmtStock = gmtStock;
        }

        public String getLocationSerialNumber() {
            return locationSerialNumber;
        }

        public void setLocationSerialNumber(String locationSerialNumber) {
            this.locationSerialNumber = locationSerialNumber;
        }

        public String getPutawayCode() {
            return putawayCode;
        }

        public void setPutawayCode(String putawayCode) {
            this.putawayCode = putawayCode;
        }

        public String getPutawayQuantity() {
            return putawayQuantity;
        }

        public void setPutawayQuantity(String putawayQuantity) {
            this.putawayQuantity = putawayQuantity;
        }

        public String getContainerBarCode() {
            return containerBarCode;
        }

        public void setContainerBarCode(String containerBarCode) {
            this.containerBarCode = containerBarCode;
        }

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
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

        public String getExtendThree() {
            return extendThree;
        }

        public void setExtendThree(String extendThree) {
            this.extendThree = extendThree;
        }

        public String getExtendFour() {
            return extendFour;
        }

        public void setExtendFour(String extendFour) {
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

        public String getGoodsStatusName() {
            return goodsStatusName;
        }

        public void setGoodsStatusName(String goodsStatusName) {
            this.goodsStatusName = goodsStatusName;
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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getReceiveCode() {
            return receiveCode;
        }

        public void setReceiveCode(String receiveCode) {
            this.receiveCode = receiveCode;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getGoodCode() {
            return goodCode;
        }

        public void setGoodCode(String goodCode) {
            this.goodCode = goodCode;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsBarCode() {
            return goodsBarCode;
        }

        public void setGoodsBarCode(String goodsBarCode) {
            this.goodsBarCode = goodsBarCode;
        }

        public String getGoodsUnit() {
            return goodsUnit;
        }

        public void setGoodsUnit(String goodsUnit) {
            this.goodsUnit = goodsUnit;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getExpirationQuantity() {
            return expirationQuantity;
        }

        public void setExpirationQuantity(String expirationQuantity) {
            this.expirationQuantity = expirationQuantity;
        }

        public String getBasicUnit() {
            return basicUnit;
        }

        public void setBasicUnit(String basicUnit) {
            this.basicUnit = basicUnit;
        }

        public String getBasicUnitName() {
            return basicUnitName;
        }

        public void setBasicUnitName(String basicUnitName) {
            this.basicUnitName = basicUnitName;
        }

        public Integer getPlanQuantity() {
            return planQuantity;
        }

        public void setPlanQuantity(Integer planQuantity) {
            this.planQuantity = planQuantity;
        }

        public Integer getReceivedQuantity() {
            return receivedQuantity;
        }

        public void setReceivedQuantity(Integer receivedQuantity) {
            this.receivedQuantity = receivedQuantity;
        }

        public String getInboundQuantity() {
            return inboundQuantity;
        }

        public void setInboundQuantity(String inboundQuantity) {
            this.inboundQuantity = inboundQuantity;
        }

        public String getUnitConvertQuantity() {
            return unitConvertQuantity;
        }

        public void setUnitConvertQuantity(String unitConvertQuantity) {
            this.unitConvertQuantity = unitConvertQuantity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCancelQuantity() {
            return cancelQuantity;
        }

        public void setCancelQuantity(String cancelQuantity) {
            this.cancelQuantity = cancelQuantity;
        }

        public String getCanReturnQuantity() {
            return canReturnQuantity;
        }

        public void setCanReturnQuantity(String canReturnQuantity) {
            this.canReturnQuantity = canReturnQuantity;
        }

        public List<?> getReceiveRegisterVOList() {
            return receiveRegisterVOList;
        }

        public void setReceiveRegisterVOList(List<?> receiveRegisterVOList) {
            this.receiveRegisterVOList = receiveRegisterVOList;
        }
    }
}