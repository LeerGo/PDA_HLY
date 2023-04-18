package com.arpa.wms.hly.bean;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.arpa.wms.hly.BR;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-12 15:45
 */
public class SelectItem extends BaseObservable {
    private boolean isSelect;

    @Bindable
    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
        notifyPropertyChanged(BR.select);
    }
}
