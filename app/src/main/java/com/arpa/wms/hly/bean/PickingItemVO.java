package com.arpa.wms.hly.bean;

import android.text.TextUtils;

import com.arpa.wms.hly.BR;

import java.math.BigDecimal;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/26 16:03
 */
public class PickingItemVO extends BaseObservable {
    private String location;// 库位
    private BigDecimal quantity; // 数量
    private BigDecimal traysNum; // 托数

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.location);
    }

    @Bindable
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

    @Bindable
    public BigDecimal getTraysNum() {
        return traysNum;
    }

    public void setTraysNum(BigDecimal traysNum) {
        this.traysNum = traysNum;
        notifyPropertyChanged(BR.traysNum);
    }

    public boolean inputValid() {
        return !TextUtils.isEmpty(location) && null != quantity && null != traysNum;
    }
}
