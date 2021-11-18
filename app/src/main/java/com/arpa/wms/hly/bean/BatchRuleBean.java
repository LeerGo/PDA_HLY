package com.arpa.wms.hly.bean;

import com.arpa.wms.hly.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class BatchRuleBean extends BaseObservable {
    private String code;
    private String shipmentCode;
    private String shipmentName;
    private String name;
    private int lot;
    private int gmtManufacture;
    private int gmtExpire;
    private int gmtStock;
    private int supplier;
    private int serialNumber;
    private int extendOne;
    private int extendTwo;
    private int extendThree;
    private int extendFour;
    private int extendFive;
    private int extendSix;
    private String state;
    private String remarks;
    private String id;
    private String deleted;
    private String createdBy;
    private String createdName;
    private String modifiedName;
    private String modifiedBy;
    private String gmtCreated;
    private String gmtModified;
    private String containerBarCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public int getGmtManufacture() {
        return gmtManufacture;
    }

    public void setGmtManufacture(int gmtManufacture) {
        this.gmtManufacture = gmtManufacture;
    }

    public int getGmtExpire() {
        return gmtExpire;
    }

    public void setGmtExpire(int gmtExpire) {
        this.gmtExpire = gmtExpire;
    }

    public int getGmtStock() {
        return gmtStock;
    }

    public void setGmtStock(int gmtStock) {
        this.gmtStock = gmtStock;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getExtendOne() {
        return extendOne;
    }

    public void setExtendOne(int extendOne) {
        this.extendOne = extendOne;
    }

    public int getExtendTwo() {
        return extendTwo;
    }

    public void setExtendTwo(int extendTwo) {
        this.extendTwo = extendTwo;
    }

    @Bindable
    public int getExtendThree() {
        return extendThree;
    }

    public void setExtendThree(int extendThree) {
        this.extendThree = extendThree;
        notifyPropertyChanged(BR.extendThree);
    }

    public int getExtendFour() {
        return extendFour;
    }

    public void setExtendFour(int extendFour) {
        this.extendFour = extendFour;
    }

    public int getExtendFive() {
        return extendFive;
    }

    public void setExtendFive(int extendFive) {
        this.extendFive = extendFive;
    }

    public int getExtendSix() {
        return extendSix;
    }

    public void setExtendSix(int extendSix) {
        this.extendSix = extendSix;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
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

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public String getContainerBarCode() {
        return containerBarCode;
    }

    public void setContainerBarCode(String containerBarCode) {
        this.containerBarCode = containerBarCode;
    }
}