package com.arpa.wms.hly.logic.home.inventory.move.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.req.ReqMoveGoods;
import com.arpa.wms.hly.bean.res.ResMoveGoods;
import com.arpa.wms.hly.bean.res.ResMoveGoods.InventoryListBean;
import com.arpa.wms.hly.net.ResultCallback;
import com.arpa.wms.hly.net.ResultError;

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
    public ItemBinding<InventoryListBean> itemBinding = ItemBinding.of(BR.data, R.layout.item_scan_goods_detail);

    @Inject
    public VMScanGoodsDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

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
