package com.arpa.wms.hly.bean.res;

import com.arpa.wms.hly.bean.OutboundVOS;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 09:35
 */
public class ResTruckLoad {
    private String driverName;
    private String driverPhone;
    private String licensePlateNumber;
    private BigDecimal expectHeight;
    private List<OutboundVOS> outboundVOS;

    public ResTruckLoad() {
        expectHeight = new BigDecimal("0.0");
    }

    public String getExpectHeight() {
        return new DecimalFormat("0.00").format(expectHeight).toString();
    }

    public void setExpectHeight(BigDecimal expectHeight) {
        this.expectHeight = expectHeight;
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

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public List<OutboundVOS> getOutboundVOS() {
        return outboundVOS;
    }

    public void setOutboundVOS(List<OutboundVOS> outboundVOS) {
        this.outboundVOS = outboundVOS;
    }
}
