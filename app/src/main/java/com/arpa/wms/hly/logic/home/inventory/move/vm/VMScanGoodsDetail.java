package com.arpa.wms.hly.logic.home.inventory.move.vm;

import android.app.Application;
import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.req.ReqMoveGoods;
import com.arpa.wms.hly.bean.res.ResMoveGoods;
import com.arpa.wms.hly.bean.res.ResMoveGoods.InventoryListBean;
import com.arpa.wms.hly.logic.home.inventory.move.ScanGoodsSureActivity;
import com.arpa.wms.hly.net.ResultCallback;
import com.arpa.wms.hly.net.ResultError;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const.IntentKey;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

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
public class VMScanGoodsDetail extends WrapDataViewModel {
    public ObservableField<ResMoveGoods> liveData = new ObservableField<>();
    // adapter 相关
    public BindingRecyclerViewAdapter<InventoryListBean> adapter = new BindingRecyclerViewAdapter<>();
    public ItemBinding<Object> itemBinding =
            ItemBinding.of(BR.data, R.layout.item_scan_goods_detail)
                    .bindExtra(BR.listener, (ViewListener.DataTransCallback<InventoryListBean>) this::jumpMoveSure);

    @Inject
    public VMScanGoodsDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    /**
     * 跳转移位确认
     */
    private void jumpMoveSure(InventoryListBean data) {
        Bundle bundle = new Bundle();
        ReqMoveGoods reqMoveGoods = new ReqMoveGoods();
        reqMoveGoods.setLot(data.getLot());
        reqMoveGoods.setCode(data.getCode());
        reqMoveGoods.setContainerBarCode(data.getContainerBarCode());
        reqMoveGoods.setGoodsBarCode(data.getGoodsBarCode());
        reqMoveGoods.setLocation(Objects.requireNonNull(liveData.get()).getLocation());
        bundle.putParcelable(IntentKey.DATA, reqMoveGoods);
        startActivity(ScanGoodsSureActivity.class, bundle);
        finish();
    }

    /**
     * 获取移位商品列表
     *
     * @param goodsBarCode
     *         商品条码
     * @param locationName
     *         移出库位
     * @param container
     *         容器
     */
    public void getScanGoodsList(String goodsBarCode, String locationName, String container) {
        updateStatus(StatusEvent.Status.LOADING);
        ReqMoveGoods reqMoveGoods = new ReqMoveGoods();
        reqMoveGoods.setContainerBarCode(container);
        reqMoveGoods.setGoodsBarCode(goodsBarCode);
        reqMoveGoods.setLocation(locationName);

        apiService.scanGoodsListDetail(reqMoveGoods.toParams())
                .enqueue(new ResultCallback<ResMoveGoods>() {
                    @Override
                    public void onSuccess(ResMoveGoods data) {
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
}
