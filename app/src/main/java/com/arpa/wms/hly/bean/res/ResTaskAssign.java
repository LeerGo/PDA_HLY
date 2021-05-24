package com.arpa.wms.hly.bean.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.arpa.wms.hly.bean.SelectItem;
import com.arpa.wms.hly.bean.req.ReqTruckLoadDetail;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-17 13:44
 */
public class ResTaskAssign extends SelectItem implements Parcelable {
    public static final Parcelable.Creator<ResTaskAssign> CREATOR = new Parcelable.Creator<ResTaskAssign>() {
        @Override
        public ResTaskAssign createFromParcel(Parcel source) {
            return new ResTaskAssign(source);
        }

        @Override
        public ResTaskAssign[] newArray(int size) {
            return new ResTaskAssign[size];
        }
    };

    private String carQueueNumber;
    private String code;
    private String sourceCode;
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
    private BigDecimal volume;// 方量
    private BigDecimal weight;// 重量

    public ResTaskAssign() {
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    protected ResTaskAssign(Parcel in) {
        this.carQueueNumber = in.readString();
        this.code = in.readString();
        this.custodian = in.readString();
        this.driverName = in.readString();
        this.driverPhone = in.readString();
        this.goodsQuantity = in.readInt();
        this.licensePlateNumber = in.readString();
        this.taskTypeDes = in.readString();
        this.customerName = in.readString();
        this.forklift = in.readString();
        this.stevedore = in.readString();
        this.receivedQuantity = in.readInt();
        this.totalQuantity = in.readInt();
    }

    public String getVolume() {
        return new DecimalFormat("0.00").format(volume);
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getWeight() {
        return new DecimalFormat("0.00").format(weight);
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carQueueNumber);
        dest.writeString(this.code);
        dest.writeString(this.custodian);
        dest.writeString(this.driverName);
        dest.writeString(this.driverPhone);
        dest.writeInt(this.goodsQuantity);
        dest.writeString(this.licensePlateNumber);
        dest.writeString(this.taskTypeDes);
        dest.writeString(this.customerName);
        dest.writeString(this.forklift);
        dest.writeString(this.stevedore);
        dest.writeInt(this.receivedQuantity);
        dest.writeInt(this.totalQuantity);
    }

    public void readFromParcel(Parcel source) {
        this.carQueueNumber = source.readString();
        this.code = source.readString();
        this.custodian = source.readString();
        this.driverName = source.readString();
        this.driverPhone = source.readString();
        this.goodsQuantity = source.readInt();
        this.licensePlateNumber = source.readString();
        this.taskTypeDes = source.readString();
        this.customerName = source.readString();
        this.forklift = source.readString();
        this.stevedore = source.readString();
        this.receivedQuantity = source.readInt();
        this.totalQuantity = source.readInt();
    }

    public ReqTruckLoadDetail convert() {
        return new ReqTruckLoadDetail(licensePlateNumber, driverName, driverPhone);
    }
}
