package com.arpa.wms.hly.logic.common;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.res.ResPdaTask;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货详情
 * </p>
 */
public abstract class VMPdaTaskDetail extends WrapDataViewModel {
    public final ObservableField<String> searchHint = new ObservableField<>();
    public final ObservableField<ResPdaTask> data = new ObservableField<>();
    public final ObservableList<Fragment> fragments = new ObservableArrayList<>();
    public final ObservableList<String> titles = new ObservableArrayList<>();

    public VMPdaTaskDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        searchHint.set("请扫描/输入商品条码");
    }
}
