package com.arpa.wms.hly.logic.home.truckload.vm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.TextUtils;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.OutboundVOS;
import com.arpa.wms.hly.bean.req.ReqTruckLoadConfirm;
import com.arpa.wms.hly.bean.req.ReqTruckLoadConfirm.OutboundItemDTOS;
import com.arpa.wms.hly.bean.res.ResTruckLoadConfirm;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * ViewModel：装车出厂（列表）
 * </p>
 */
@HiltViewModel
public class VMTruckLoadConfirm extends WrapDataViewModel {
    // header 独立置顶的操作
    //    public final ItemBinding<GoodsItemVO> itemBinding = ItemBinding.of(BR.data, R.layout.item_truck_load_confirm);
    //    public final ObservableField<OutboundVOS> headerData = new ObservableField<>();

    public final ObservableArrayList<Object> items = new ObservableArrayList<>();
    public final ItemBinding<Object> itemBinding = ItemBinding.of((itemBinding, position, item) -> {
        if (position == 0) {
            itemBinding.set(BR.header, R.layout.header_truck_load_confirm);
        } else {
            itemBinding.set(BR.data, R.layout.item_truck_load_confirm);
        }
    });
    public final ObservableField<ReqTruckLoadConfirm> request = new ObservableField<>(new ReqTruckLoadConfirm());
    public OutboundVOS headerData;

    @Inject
    public VMTruckLoadConfirm(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onStart() {
        super.onStart();
        requestData();
    }

    private void requestData() {
        showLoading();
        apiService.getTruckLoadConfirmDetail(Objects.requireNonNull(headerData.getCode()))
                .enqueue(new ResultCallback<ResTruckLoadConfirm>() {
                    @Override
                    public void onSuccess(ResTruckLoadConfirm data) {
                        items.add(headerData);
                        items.addAll(data.getOutboundItemVOList());
                        request.get().setCode(data.getCode());
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

    /**
     * 装车确认
     */
    public void confirm() {
        if (!validateInput()) return;

        showLoading();
        buildRequest();
        apiService.confirmTruckLoad(request.get())
                .enqueue(new ResultCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtils.showShort(R.string.request_success);
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

    @SuppressLint("LogNotTimber")
    private void buildRequest() {
        Objects.requireNonNull(request.get()).getOutboundItemDTOS().clear();
        for (int i = 1; i < items.size(); i++) {
            GoodsItemVO data = (GoodsItemVO) items.get(i);
            request.get().getOutboundItemDTOS().add(new OutboundItemDTOS(data.getCode(), data.getLoadQuantity()));
        }
    }

    private boolean validateInput() {
        boolean result = true;
        for (int i = 1; i < items.size(); i++) {
            if (TextUtils.isEmpty(((GoodsItemVO) items.get(i)).getLoadQuantity())) {
                ToastUtils.showShort("请输入第" + i + "条装车数量");
                result = false;
                break;
            }
        }
        return result;
    }
}
