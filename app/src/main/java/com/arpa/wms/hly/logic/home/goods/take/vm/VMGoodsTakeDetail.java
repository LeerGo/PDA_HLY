package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.res.ResPdaTask;
import com.arpa.wms.hly.logic.home.goods.take.GoodsTakeDetailFragment;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
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
    private final ObservableField<ResPdaTask> data = new ObservableField<>();
    private final ObservableList<Fragment> fragments = new ObservableArrayList<>();
    private final ObservableList<String> titles = new ObservableArrayList<>();

    @ViewModelInject
    public VMGoodsTakeDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        searchHint.set("请扫描/输入商品条码");
        fragments.add(GoodsTakeDetailFragment.newInstance("DemoTabFragment#1"));
        fragments.add(GoodsTakeDetailFragment.newInstance("DemoTabFragment#2"));
        titles.addAll(Arrays.asList("待收货", "已收货"));
    }

    public ObservableField<String> getSearchHint() {
        return searchHint;
    }

    public ObservableField<ResPdaTask> getData() {
        return data;
    }

    public void setData(ResPdaTask data) {
        this.data.set(data);
    }

    public ObservableList<Fragment> getFragments() {
        return fragments;
    }

    public ObservableList<String> getTitles() {
        return titles;
    }
}
