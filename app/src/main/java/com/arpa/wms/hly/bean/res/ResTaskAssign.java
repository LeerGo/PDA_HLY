package com.arpa.wms.hly.bean.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.arpa.wms.hly.bean.SelectItem;
import com.arpa.wms.hly.bean.req.ReqTruckLoadDetail;
import com.arpa.wms.hly.utils.NumberUtils;

import java.math.BigDecimal;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-17 13:44
 */
public class ResTaskAssign extends SelectItem implements Parcelable {
    public static final Creator<ResTaskAssign> CREATOR = new Creator<>() {
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
    private int jobQuantity;
    private int assign;
    private int goodsQuantity;
    private String licensePlateNumber;
    private String taskTypeDes;
    private String customerName;
    private String forklift;
    private String stevedore;
    private int receivedQuantity;
    private int recheckQuantity;
    private int planQuantity;
    private int totalQuantity;
    private BigDecimal volume; // 方量
    private String supplierCode; // 供应商 code
    private String supplierName; // 供应商 名称
    private BigDecimal weight; // 重量
    private String carClassNum; // 车种车号
    private String gmtCreated;//指派时间
    private String assignTime;//下发时间
    private BigDecimal loadingCarHeight;//装车高度
    private String erpCode;
    private String jobType;

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public ResTaskAssign() {
    }

    protected ResTaskAssign(Parcel in) {
        this.carQueueNumber = in.readString();
        this.code = in.readString();
        this.sourceCode = in.readString();
        this.custodian = in.readString();
        this.driverName = in.readString();
        this.driverPhone = in.readString();
        this.jobQuantity = in.readInt();
        this.assign = in.readInt();
        this.goodsQuantity = in.readInt();
        this.licensePlateNumber = in.readString();
        this.taskTypeDes = in.readString();
        this.customerName = in.readString();
        this.forklift = in.readString();
        this.stevedore = in.readString();
        this.receivedQuantity = in.readInt();
        this.recheckQuantity = in.readInt();
        this.planQuantity = in.readInt();
        this.totalQuantity = in.readInt();
        this.volume = (BigDecimal) in.readSerializable();
        this.supplierCode = in.readString();
        this.supplierName = in.readString();
        this.weight = (BigDecimal) in.readSerializable();
        this.carClassNum = in.readString();
        this.gmtCreated = in.readString();
        this.assignTime = in.readString();
        this.loadingCarHeight = (BigDecimal) in.readSerializable();
        this.erpCode = in.readString();
    }

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public int getAssign() {
        return assign;
    }

    public void setAssign(int assign) {
        this.assign = assign;
    }

    public BigDecimal getLoadingCarHeight() {
        return loadingCarHeight;
    }

    public void setLoadingCarHeight(BigDecimal loadingCarHeight) {
        this.loadingCarHeight = loadingCarHeight;
    }

    public String getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getVolume() {
        return NumberUtils.parseDecimal(volume);
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getWeight() {
        return NumberUtils.parseDecimal(weight);
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

    public int getJobQuantity() {
        return jobQuantity;
    }

    public void setJobQuantity(int jobQuantity) {
        this.jobQuantity = jobQuantity;
    }

    public ReqTruckLoadDetail convert() {
        return new ReqTruckLoadDetail(licensePlateNumber, driverName, driverPhone);
    }

    /**
     * 任务中心跳转详情，需要转换一下字段赋值
     */
    public void toTaskTakeDetail() {
        this.receivedQuantity = this.jobQuantity;
        this.totalQuantity = this.goodsQuantity;
    }

    /**
     * 任务中心跳转拣货详情，需要转换一下字段赋值
     */
    public void toTaskPickDetail() {
        this.planQuantity = this.goodsQuantity;
    }

    /**
     * 跳转复核详情，header 部分 xml 是复用的，字段也重新赋值一下
     */
    public void toRecheckDetail() {
        this.receivedQuantity = this.recheckQuantity;
        this.totalQuantity = this.planQuantity;
    }

    public int getRecheckQuantity() {
        return recheckQuantity;
    }

    public void setRecheckQuantity(int recheckQuantity) {
        this.recheckQuantity = recheckQuantity;
    }

    public int getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(int planQuantity) {
        this.planQuantity = planQuantity;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCarClassNum() {
        return carClassNum;
    }

    public void setCarClassNum(String carClassNum) {
        this.carClassNum = carClassNum;
    }

    public String getErpCode() {
        return erpCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carQueueNumber);
        dest.writeString(this.code);
        dest.writeString(this.sourceCode);
        dest.writeString(this.custodian);
        dest.writeString(this.driverName);
        dest.writeString(this.driverPhone);
        dest.writeInt(this.jobQuantity);
        dest.writeInt(this.assign);
        dest.writeInt(this.goodsQuantity);
        dest.writeString(this.licensePlateNumber);
        dest.writeString(this.taskTypeDes);
        dest.writeString(this.customerName);
        dest.writeString(this.forklift);
        dest.writeString(this.stevedore);
        dest.writeInt(this.receivedQuantity);
        dest.writeInt(this.recheckQuantity);
        dest.writeInt(this.planQuantity);
        dest.writeInt(this.totalQuantity);
        dest.writeSerializable(this.volume);
        dest.writeString(this.supplierCode);
        dest.writeString(this.supplierName);
        dest.writeSerializable(this.weight);
        dest.writeString(this.carClassNum);
        dest.writeString(this.gmtCreated);
        dest.writeString(this.assignTime);
        dest.writeSerializable(this.loadingCarHeight);
        dest.writeString(this.erpCode);
    }

    public void readFromParcel(Parcel source) {
        this.carQueueNumber = source.readString();
        this.code = source.readString();
        this.sourceCode = source.readString();
        this.custodian = source.readString();
        this.driverName = source.readString();
        this.driverPhone = source.readString();
        this.jobQuantity = source.readInt();
        this.assign = source.readInt();
        this.goodsQuantity = source.readInt();
        this.licensePlateNumber = source.readString();
        this.taskTypeDes = source.readString();
        this.customerName = source.readString();
        this.forklift = source.readString();
        this.stevedore = source.readString();
        this.receivedQuantity = source.readInt();
        this.recheckQuantity = source.readInt();
        this.planQuantity = source.readInt();
        this.totalQuantity = source.readInt();
        this.volume = (BigDecimal) source.readSerializable();
        this.supplierCode = source.readString();
        this.supplierName = source.readString();
        this.weight = (BigDecimal) source.readSerializable();
        this.carClassNum = source.readString();
        this.gmtCreated = source.readString();
        this.assignTime = source.readString();
        this.loadingCarHeight = (BigDecimal) source.readSerializable();
        this.erpCode = source.readString();
    }
}
