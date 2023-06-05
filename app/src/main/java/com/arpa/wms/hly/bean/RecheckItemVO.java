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
    private BigDecimal ratio;
    // 扫码比例
    private Integer scanRatio;

    public RecheckItemVO() {
        ratio = BigDecimal.ZERO;
        scanRatio = 1;
    }

    @Bindable
    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
        notifyPropertyChanged(BR.ratio);
    }

    @Bindable
    public Integer getScanRatio() {
        return scanRatio;
    }

    public void setScanRatio(Integer scanRatio) {
        this.scanRatio = scanRatio;
        notifyPropertyChanged(BR.scanRatio);
    }

    @Override
    public String toString() {
        return "RecheckItemVO{" +
                "radio=" + ratio +
                ", scanRatio=" + scanRatio +
                '}';
    }
}
