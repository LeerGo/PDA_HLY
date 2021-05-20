package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.OutboundItemVOList;
import com.arpa.wms.hly.bean.req.ReqGoodRecheckDetail;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货确认
 * </p>
 */
@HiltViewModel
public class VMGoodsRecheckConfirm extends WrapDataViewModel {
    public ObservableField<OutboundItemVOList> detail = new ObservableField<>();
    public ReqGoodRecheckDetail request = new ReqGoodRecheckDetail();
    public ArrayList<String> batchCodeList = new ArrayList<>();
    public ObservableField<String> obvBatchCode = new ObservableField<>();

    @Inject
    public VMGoodsRecheckConfirm(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onStart() {
        super.onStart();
        requestData();
    }

    private void requestData() {
        updateStatus(Status.LOADING);
        apiService.recheckRegisterDetail(request.toParams())
                .enqueue(new ResultCallback<OutboundItemVOList>() {
                    @Override
                    public void onSuccess(OutboundItemVOList data) {
                        detail.set(data);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        hideLoading();
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage());
                    }
                });
    }
}
