package com.arpa.wms.hly.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 09:55
 */
public class OutboundVOS implements Parcelable {
    private String code;
    private String customerCode;
    private String customerName;
    private String receiveAddress;
    private Integer recheckQuantity;
    private String sendAddress;
    private String warehouseCode;
    private String licensePlateNumber;

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Integer getRecheckQuantity() {
        return recheckQuantity;
    }

    public void setRecheckQuantity(Integer recheckQuantity) {
        this.recheckQuantity = recheckQuantity;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
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
        dest.writeString(this.code);
        dest.writeString(this.customerCode);
        dest.writeString(this.customerName);
        dest.writeString(this.receiveAddress);
        dest.writeValue(this.recheckQuantity);
        dest.writeString(this.sendAddress);
        dest.writeString(this.warehouseCode);
        dest.writeString(this.licensePlateNumber);
    }

    public void readFromParcel(Parcel source) {
        this.code = source.readString();
        this.customerCode = source.readString();
        this.customerName = source.readString();
        this.receiveAddress = source.readString();
        this.recheckQuantity = (Integer) source.readValue(Integer.class.getClassLoader());
        this.sendAddress = source.readString();
        this.warehouseCode = source.readString();
        this.licensePlateNumber = source.readString();
    }

    public OutboundVOS() {
    }

    protected OutboundVOS(Parcel in) {
        this.code = in.readString();
        this.customerCode = in.readString();
        this.customerName = in.readString();
        this.receiveAddress = in.readString();
        this.recheckQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sendAddress = in.readString();
        this.warehouseCode = in.readString();
        this.licensePlateNumber = in.readString();
    }

    public static final Parcelable.Creator<OutboundVOS> CREATOR = new Parcelable.Creator<OutboundVOS>() {
        @Override
        public OutboundVOS createFromParcel(Parcel source) {
            return new OutboundVOS(source);
        }

        @Override
        public OutboundVOS[] newArray(int size) {
            return new OutboundVOS[size];
        }
    };
}
