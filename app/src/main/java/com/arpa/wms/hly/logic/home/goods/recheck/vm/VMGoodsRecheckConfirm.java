package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.text.TextUtils;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.req.ReqGoodRecheckDetail;
import com.arpa.wms.hly.bean.req.ReqRecheckConfirm;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.ToastUtils;

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
    public ObservableField<GoodsItemVO> detail = new ObservableField<>();
    public ReqRecheckConfirm confirm = new ReqRecheckConfirm();
    public ReqGoodRecheckDetail request = new ReqGoodRecheckDetail();
    public ArrayList<String> batchCodeList = new ArrayList<>();
    public ObservableField<String> obvBatchCode = new ObservableField<>();
    public ObservableField<String> recheckQuantity = new ObservableField<>();

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
        apiService.recheckRegisterDetail(request.toParams()).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(GoodsItemVO data) {
                detail.set(data);
                recheckQuantity.set(String.valueOf(detail.get().getWaitRecheckQuantity()));
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

    public void confirm() {
        if (TextUtils.isEmpty(recheckQuantity.get())) {
            ToastUtils.showShortSafe("请输入复核数量");
            return;
        }
        /*if (TextUtils.isEmpty(obvBatchCode.get())) {
            ToastUtils.showShortSafe("请录入批次");
            return;
        }*/

        updateStatus(Status.LOADING);
        confirm.setBeachNumber(obvBatchCode.get());
        confirm.setRecheckQuantity(recheckQuantity.get());
        confirm.setOutboundCode(request.getOutboundCode());
        confirm.setOutboundItemCode(request.getOutboundItemCode());
        apiService.recheckConfirm(confirm).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(Object data) {
                finish();
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
