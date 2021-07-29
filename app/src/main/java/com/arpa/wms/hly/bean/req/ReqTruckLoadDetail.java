package com.arpa.wms.hly.bean.req;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 09:34
 */
public class ReqTruckLoadDetail {
    private String licensePlateNumber;
    private String driverName;
    private String driverPhone;

    public ReqTruckLoadDetail(String licensePlateNumber, String driverName, String driverPhone) {
        this.licensePlateNumber = licensePlateNumber;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
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
}
