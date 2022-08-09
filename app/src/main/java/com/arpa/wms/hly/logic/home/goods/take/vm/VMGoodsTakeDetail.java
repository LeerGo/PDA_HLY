package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.logic.common.vm.VMPdaTaskDetail;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import java.util.Arrays;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货详情
 * </p>
 */
@HiltViewModel
public class VMGoodsTakeDetail extends VMPdaTaskDetail {

    @Inject
    public VMGoodsTakeDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        titles.addAll(Arrays.asList("待收货", "已收货"));
    }

    @Override
    protected void refreshHeader() {
        apiService.receiveDetailsAbove(headerData.getValue().getCode())
                .enqueue(new ResultCallback<ResTaskAssign>() {
                    @Override
                    public void onSuccess(ResTaskAssign data) {
                        if (null != data) {
                            headerData.postValue(data);
                        }
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage());
                        finish();
                    }
                });
    }
}
