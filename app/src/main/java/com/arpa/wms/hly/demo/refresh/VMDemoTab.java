package com.arpa.wms.hly.demo.refresh;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.res.ResPdaTask;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.hilt.lifecycle.ViewModelInject;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:20 PM
 *
 * <p>
 * ViewModel：登录
 * </p>
 */
public class VMDemoTab extends WrapDataViewModel {
    private final ObservableField<ResPdaTask> data = new ObservableField<>();
    private final ObservableList<DemoTabFragment> fragments = new ObservableArrayList<>();
    private final ObservableList<String> titles = new ObservableArrayList<>();

    @ViewModelInject
    public VMDemoTab(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fragments.add(DemoTabFragment.newInstance("DemoTabFragment#1"));
        fragments.add(DemoTabFragment.newInstance("DemoTabFragment#2"));
        titles.addAll(Arrays.asList("待指派", "已指派"));
    }

    public ObservableField<ResPdaTask> getData() {
        return data;
    }

    public void setData(ResPdaTask data) {
        this.data.set(data);
    }

    public ObservableList<DemoTabFragment> getFragments() {
        return fragments;
    }

    public ObservableList<String> getTitles() {
        return titles;
    }
}
