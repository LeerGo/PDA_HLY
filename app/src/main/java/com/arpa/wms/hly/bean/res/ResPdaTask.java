package com.arpa.wms.hly.bean.res;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 09:21<br/>
 *
 * <p>
 * Res：收货、复核任务列表
 * </p>
 */
public class ResPdaTask implements Parcelable {
    public static final Creator<ResPdaTask> CREATOR = new Creator<ResPdaTask>() {
        @Override
        public ResPdaTask createFromParcel(Parcel source) {
            return new ResPdaTask(source);
        }

        @Override
        public ResPdaTask[] newArray(int size) {
            return new ResPdaTask[size];
        }
    };
    private String assignBy;
    private String assignName;
    private String billTypeName;
    private String code;
    private String createdBy;
    private String createdName;
    private String gmtCreated;
    private String gmtModified;
    private Integer goodsQuantity;
    private Integer goodsTypeQuantity;
    private String groupCode;
    private Integer jobQuantity;
    private String jobStatus;
    private String modifiedBy;
    private String modifiedName;
    private String operatorBy;
    private String operatorName;
    private String shipmentName;
    private String sourceCode;
    private String supplierName;
    private String taskType;
    private String warehouseCode;
    private String container;
    private String location;
    private String countingCode;
    //    private String isSelect = "0";
    private Boolean isSelect = false;
    private String locationName;
    private String customerName;

    public ResPdaTask() {
    }

    protected ResPdaTask(Parcel in) {
        this.assignBy = in.readString();
        this.assignName = in.readString();
        this.billTypeName = in.readString();
        this.code = in.readString();
        this.createdBy = in.readString();
        this.createdName = in.readString();
        this.gmtCreated = in.readString();
        this.gmtModified = in.readString();
        this.goodsQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.goodsTypeQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.groupCode = in.readString();
        this.jobQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.jobStatus = in.readString();
        this.modifiedBy = in.readString();
        this.modifiedName = in.readString();
        this.operatorBy = in.readString();
        this.operatorName = in.readString();
        this.shipmentName = in.readString();
        this.sourceCode = in.readString();
        this.supplierName = in.readString();
        this.taskType = in.readString();
        this.warehouseCode = in.readString();
        this.container = in.readString();
        this.location = in.readString();
        this.countingCode = in.readString();
        this.isSelect = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.locationName = in.readString();
        this.customerName = in.readString();
    }

    public String getCountingCode() {
        return countingCode;
    }

    public void setCountingCode(String countingCode) {
        this.countingCode = countingCode;
    }

    public Boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Integer getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Integer goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public Integer getGoodsTypeQuantity() {
        return goodsTypeQuantity;
    }

    public void setGoodsTypeQuantity(Integer goodsTypeQuantity) {
        this.goodsTypeQuantity = goodsTypeQuantity;
    }

    public Integer getJobQuantity() {
        return jobQuantity;
    }

    public void setJobQuantity(Integer jobQuantity) {
        this.jobQuantity = jobQuantity;
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

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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

    public String getOperatorBy() {
        return operatorBy;
    }

    public void setOperatorBy(String operatorBy) {
        this.operatorBy = operatorBy;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.assignBy);
        dest.writeString(this.assignName);
        dest.writeString(this.billTypeName);
        dest.writeString(this.code);
        dest.writeString(this.createdBy);
        dest.writeString(this.createdName);
        dest.writeString(this.gmtCreated);
        dest.writeString(this.gmtModified);
        dest.writeValue(this.goodsQuantity);
        dest.writeValue(this.goodsTypeQuantity);
        dest.writeString(this.groupCode);
        dest.writeValue(this.jobQuantity);
        dest.writeString(this.jobStatus);
        dest.writeString(this.modifiedBy);
        dest.writeString(this.modifiedName);
        dest.writeString(this.operatorBy);
        dest.writeString(this.operatorName);
        dest.writeString(this.shipmentName);
        dest.writeString(this.sourceCode);
        dest.writeString(this.supplierName);
        dest.writeString(this.taskType);
        dest.writeString(this.warehouseCode);
        dest.writeString(this.container);
        dest.writeString(this.location);
        dest.writeString(this.countingCode);
        dest.writeValue(this.isSelect);
        dest.writeString(this.locationName);
        dest.writeString(this.customerName);
    }

    public void readFromParcel(Parcel source) {
        this.assignBy = source.readString();
        this.assignName = source.readString();
        this.billTypeName = source.readString();
        this.code = source.readString();
        this.createdBy = source.readString();
        this.createdName = source.readString();
        this.gmtCreated = source.readString();
        this.gmtModified = source.readString();
        this.goodsQuantity = (Integer) source.readValue(Integer.class.getClassLoader());
        this.goodsTypeQuantity = (Integer) source.readValue(Integer.class.getClassLoader());
        this.groupCode = source.readString();
        this.jobQuantity = (Integer) source.readValue(Integer.class.getClassLoader());
        this.jobStatus = source.readString();
        this.modifiedBy = source.readString();
        this.modifiedName = source.readString();
        this.operatorBy = source.readString();
        this.operatorName = source.readString();
        this.shipmentName = source.readString();
        this.sourceCode = source.readString();
        this.supplierName = source.readString();
        this.taskType = source.readString();
        this.warehouseCode = source.readString();
        this.container = source.readString();
        this.location = source.readString();
        this.countingCode = source.readString();
        this.isSelect = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.locationName = source.readString();
        this.customerName = source.readString();
    }
}