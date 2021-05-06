package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.logic.common.vm.VMPdaTaskDetail;
import com.arpa.wms.hly.logic.home.goods.take.GoodsTakeDetailFragment;

import java.util.Arrays;

import androidx.annotation.NonNull;
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
public class VMGoodsTakeDetail extends VMPdaTaskDetail {

    @ViewModelInject
    public VMGoodsTakeDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        fragments.add(GoodsTakeDetailFragment.newInstance("DemoTabFragment#1"));
        fragments.add(GoodsTakeDetailFragment.newInstance("DemoTabFragment#2"));
        titles.addAll(Arrays.asList("待收货", "已收货"));
    }
}
