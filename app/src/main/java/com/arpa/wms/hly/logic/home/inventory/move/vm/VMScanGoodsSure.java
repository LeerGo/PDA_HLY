package com.arpa.wms.hly.logic.home.inventory.move.vm;

import android.app.Application;
import android.text.TextUtils;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.base.Result;
import com.arpa.wms.hly.bean.req.ReqMoveGoods;
import com.arpa.wms.hly.bean.req.ReqMoveSure;
import com.arpa.wms.hly.bean.res.ResMoveGoods;
import com.arpa.wms.hly.bean.res.ResMoveGoodsSure;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.NumberUtils;
import com.arpa.wms.hly.utils.ToastUtils;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-11 13:45
 *
 * <p>
 * ViewModel: 移位商品详情
 * </p>
 */
@HiltViewModel
public class VMScanGoodsSure extends WrapDataViewModel {
    public ObservableField<ResMoveGoodsSure> liveData = new ObservableField<>();
    public ObservableField<Integer> moveQuantity = new ObservableField<>();
    public ObservableField<String> moveLocation = new ObservableField<>();

    @Inject
    public VMScanGoodsSure(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    /**
     * 获取移位商品详情
     */
    public void getScanGoodsDetail(ReqMoveGoods reqMoveGoods) {
        updateStatus(StatusEvent.Status.LOADING);
        apiService.scanGoodsDetail(reqMoveGoods.toParams())
                .enqueue(new ResultCallback<ResMoveGoodsSure>() {
                    @Override
                    public void onSuccess(ResMoveGoodsSure data) {
                        updateStatus(StatusEvent.Status.SUCCESS);
                        liveData.set(data);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(StatusEvent.Status.ERROR);
                    }
                });
    }

    /**
     * 移位确认
     */
    public void moveConfirm() {
        if (null == moveQuantity.get()) {
            ToastUtils.showShort("请输入移位数量");
            return;
        }
        if (TextUtils.isEmpty(moveLocation.get())) {
            ToastUtils.showShort("请输入移入库位");
            return;
        }
        if (NumberUtils.isLarger(moveQuantity.get(), liveData.get().getQuantity())) {
            ToastUtils.showShort("填写数量不能大于移位数量");
            return;
        }

        updateStatus(StatusEvent.Status.LOADING);
        ReqMoveSure reqMoveSure = new ReqMoveSure();
        reqMoveSure.setCode(liveData.get().getCode());
        reqMoveSure.setContainerBarCode(liveData.get().getContainerBarCode());
        reqMoveSure.setMoveLocation(moveLocation.get());
        reqMoveSure.setMoveQuantity(moveQuantity.get().toString());
        apiService.scanGoodsSure(reqMoveSure)
                .enqueue(new ResultCallback<ResMoveGoods>() {
                    @Override
                    public void onResponse(Call<Result<ResMoveGoods>> call, Result<ResMoveGoods> result) {
                        super.onResponse(call, result);
                        ToastUtils.showShort(result.getMsg());
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(StatusEvent.Status.ERROR);
                    }

                    @Override
                    public void onSuccess(ResMoveGoods data) {
                        updateStatus(StatusEvent.Status.SUCCESS);
                        finish();
                    }
                });
    }
}
