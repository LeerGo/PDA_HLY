package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.logic.common.vm.VMPdaTaskDetail;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.Arrays;

import javax.inject.Inject;

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
public class VMGoodsRecheckDetail extends VMPdaTaskDetail {

    @Inject
    public VMGoodsRecheckDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        titles.addAll(Arrays.asList("待复核", "已复核"));
    }

    @Override
    protected void refreshHeader() {
        apiService.recheckItemList(headerData.getValue().getCode())
                .enqueue(new ResultCallback<>() {
                    @Override
                    public void onSuccess(ResTaskAssign data) {
                        data.toRecheckDetail();
                        headerData.postValue(data);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        ToastUtils.showShort(error.getMessage());
                    }
                });
    }
}
