package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.logic.common.vm.VMPdaTaskDetail;
import com.arpa.wms.hly.logic.home.goods.recheck.GoodsRecheckDetailFragment;

import java.util.Arrays;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待复核列表
 * </p>
 */
@HiltViewModel
public class VMGoodsRecheckDetailList extends VMPdaTaskDetail {

    @Inject
    public VMGoodsRecheckDetailList(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        fragments.add(GoodsRecheckDetailFragment.newInstance("DemoTabFragment#1"));
        fragments.add(GoodsRecheckDetailFragment.newInstance("DemoTabFragment#2"));
        titles.addAll(Arrays.asList("待复核", "已复核"));
    }
}
