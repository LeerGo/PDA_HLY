package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.SNCodeEntity;
import com.arpa.wms.hly.bean.req.ReqGoodRecheckDetail;
import com.arpa.wms.hly.bean.req.ReqRecheckConfirm;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

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
    public ReqRecheckConfirm confirm = new ReqRecheckConfirm();
    public ReqGoodRecheckDetail request = new ReqGoodRecheckDetail();
    public ObservableField<GoodsItemVO> detail = new ObservableField<>();
    public String obvBatchCode;
    public ArrayList<SNCodeEntity> batchCodeList = new ArrayList<>();
    ;
    public ObservableField<String> recheckQuantity = new ObservableField<>();
    // 最新批次号
    public ObservableField<String> latestBatchNo = new ObservableField<>();
    // 最旧批次号
    public ObservableField<String> oldestBatchNo = new ObservableField<>();

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

    // FIXME: 不动生产数据，临时屏蔽操作 add by 李一方 2022-06-27 01:54:05
    public void confirm() {
        if (TextUtils.isEmpty(recheckQuantity.get())) {
            ToastUtils.showShortSafe("请输入复核数量");
            return;
        }

        updateStatus(Status.LOADING);
        confirm.setBeachNumber(obvBatchCode);
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

    public void setBatchCode(ArrayList<SNCodeEntity> result) {
        batchCodeList = result;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < batchCodeList.size(); i++) {
            sb.append(batchCodeList.get(i).getSnCode());
            if (i < batchCodeList.size() - 1) sb.append("\n");
        }
        obvBatchCode = sb.toString();
        oldestBatchNo.set(result.isEmpty() ? null : Collections.max(result).getSnCode());
        latestBatchNo.set(result.isEmpty() ? null : Collections.min(result).getSnCode());
    }
}
