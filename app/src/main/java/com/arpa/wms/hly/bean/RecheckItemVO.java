package com.arpa.wms.hly.bean;

import androidx.databinding.Bindable;

import com.arpa.wms.hly.BR;

import java.math.BigDecimal;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-18 10:52
 */
public class RecheckItemVO extends GoodsItemVO {
    // 扫码率
    private BigDecimal radio;
    // 扫码比例
    private BigDecimal scanRatio;

    @Bindable
    public BigDecimal getRadio() {
        return radio;
    }

    public void setRadio(BigDecimal radio) {
        this.radio = radio;
        notifyPropertyChanged(BR.radio);
    }

    @Bindable
    public BigDecimal getScanRatio() {
        return scanRatio;
    }

    public void setScanRatio(BigDecimal scanRatio) {
        this.scanRatio = scanRatio;
        notifyPropertyChanged(BR.scanRatio);
    }
}
