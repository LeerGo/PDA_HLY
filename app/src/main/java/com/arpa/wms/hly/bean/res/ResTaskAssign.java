package com.arpa.wms.hly.bean.res;

import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-17 13:44
 */
public class ResTaskAssign extends SelectItem {
    private String carQueueNumber;
    private String code;
    private String custodian;
    private String driverName;
    private String driverPhone;
    private int goodsQuantity;
    private String licensePlateNumber;
    private String taskTypeDes;
    private String customerName;
    private String forklift;
    private String stevedore;
    private int receivedQuantity;
    private int totalQuantity;
    private List<?> receiveItemVOList;

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<?> getReceiveItemVOList() {
        return receiveItemVOList;
    }

    public void setReceiveItemVOList(List<?> receiveItemVOList) {
        this.receiveItemVOList = receiveItemVOList;
    }

    public String getStevedore() {
        return stevedore;
    }

    public void setStevedore(String stevedore) {
        this.stevedore = stevedore;
    }

    public String getForklift() {
        return forklift;
    }

    public void setForklift(String forklift) {
        this.forklift = forklift;
    }

    public String getCarQueueNumber() {
        return carQueueNumber;
    }

    public void setCarQueueNumber(String carQueueNumber) {
        this.carQueueNumber = carQueueNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public int getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(int goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getTaskTypeDes() {
        return taskTypeDes;
    }

    public void setTaskTypeDes(String taskTypeDes) {
        this.taskTypeDes = taskTypeDes;
    }
}
