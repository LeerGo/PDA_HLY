package com.arpa.wms.hly.logic.home.goods.take;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货详情
 * </p>
 */
public class VMGoodsTakeDetail extends WrapDataViewModel {
    private final ObservableField<String> searchHint = new ObservableField<>();

    @ViewModelInject
    public VMGoodsTakeDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        searchHint.set("请扫描/输入商品条码");
    }

    public ObservableField<String> getSearchHint() {
        return searchHint;
    }
}
