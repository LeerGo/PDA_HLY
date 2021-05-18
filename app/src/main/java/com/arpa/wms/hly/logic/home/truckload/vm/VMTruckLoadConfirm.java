package com.arpa.wms.hly.logic.home.truckload.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.OutboundVOS;
import com.arpa.wms.hly.bean.res.ResTruckLoadConfirm;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
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
    public final ObservableArrayList<Object> items = new ObservableArrayList<>();
    //    public final ItemBinding<OutboundItemVOList> itemBinding = ItemBinding.of(BR.data, R.layout.item_truck_load_confirm);
    public final ItemBinding<Object> itemBinding = ItemBinding.of((itemBinding, position, item) -> {
        if (position == 0) {
            itemBinding.set(BR.data, R.layout.header_truck_load_confirm);
        } else {
            itemBinding.set(BR.data, R.layout.item_truck_load_confirm);
        }
    });
    //    public final ObservableField<OutboundVOS> headerData = new ObservableField<>();
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
