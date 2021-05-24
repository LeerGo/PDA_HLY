package com.arpa.wms.hly.logic.home.inventory.move.vm;

import android.app.Application;
import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.req.ReqMoveGoods;
import com.arpa.wms.hly.bean.req.ReqMoveLocation;
import com.arpa.wms.hly.bean.res.ResMoveGoods;
import com.arpa.wms.hly.bean.res.ResMoveLocation;
import com.arpa.wms.hly.logic.home.inventory.move.ScanGoodsActivity;
import com.arpa.wms.hly.logic.home.inventory.move.ScanGoodsDetailActivity;
import com.arpa.wms.hly.logic.home.inventory.move.ScanGoodsSureActivity;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const.IntentKey;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * ViewModel: 扫描移除库位、扫描移位商品
 * </p>
 */
@HiltViewModel
public class VMInventoryScan extends WrapDataViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> searchHint = new ObservableField<>();

    @Inject
    public VMInventoryScan(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    /**
     * 无计划扫描库位
     */
    public void scanLocations(String locationName) {
        updateStatus(StatusEvent.Status.LOADING);
        ReqMoveLocation reqMoveLocation = new ReqMoveLocation(locationName);
        apiService.scanLocation(reqMoveLocation.toParams())
                .enqueue(new ResultCallback<ResMoveLocation>() {
                    @Override
                    public void onSuccess(ResMoveLocation data) {
                        if (data.getToPage() == 1) {// 托盘（恒利源似乎没有）

                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString(IntentKey.LOCATION_NAME, locationName);
                            // wms2 里传的就是空值 @lyf 2021-05-11 10:49:38
                            bundle.putString(IntentKey.CONTAINER_CODE, "");
                            startActivity(ScanGoodsActivity.class, bundle);
                        }
                        updateStatus(StatusEvent.Status.SUCCESS);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(StatusEvent.Status.ERROR);
                    }
                });
    }

    /**
     * 无计划扫描移位商品
     */
    public void scanGoods(String goodsBarCode, String locationName, String container) {
        updateStatus(StatusEvent.Status.LOADING);
        // SHLSKW 111
        ReqMoveGoods reqMoveGoods = new ReqMoveGoods();
        reqMoveGoods.setContainerBarCode(container);
        reqMoveGoods.setGoodsBarCode(goodsBarCode);
        reqMoveGoods.setLocation(locationName);

        apiService.scanGoods(reqMoveGoods.toParams())
                .enqueue(new ResultCallback<ResMoveGoods>() {
                    @Override
                    public void onSuccess(ResMoveGoods data) {
                        updateStatus(StatusEvent.Status.SUCCESS);
                        Bundle bundle = new Bundle();
                        if (data.getToPage() == 0) {
                            // 跳转移位确认
                            startActivity(ScanGoodsSureActivity.class, bundle);
                        } else {
                            // 跳转移位详情
                            bundle.putString(IntentKey.CONTAINER_CODE, container);
                            bundle.putString(IntentKey.LOCATION_NAME, locationName);
                            bundle.putString(IntentKey.GOODS_BAR_CODE, goodsBarCode);
                            startActivity(ScanGoodsDetailActivity.class, bundle);
                            finish();
                        }
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage(), true);
                        updateStatus(StatusEvent.Status.ERROR);
                    }
                });
    }
}
