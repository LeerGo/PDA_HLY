package com.arpa.wms.hly.logic.home.truckload.vm;

import android.app.Application;
import android.os.Bundle;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.OutboundVOS;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.bean.res.ResTruckLoad;
import com.arpa.wms.hly.logic.home.truckload.TruckLoadConfirmActivity;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
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
public class VMTruckLoadDetail extends WrapDataViewModel {
    public final ObservableField<ResTaskAssign> truckLoadHeader = new ObservableField<>();
    public final ItemBinding<Object> itemBinding =
            ItemBinding.of(BR.data, R.layout.item_truck_load_detail)
                    .bindExtra(BR.listener, (ViewListener.DataTransCallback<OutboundVOS>) data -> {
                        Bundle bundle = new Bundle();
                        data.setLicensePlateNumber(truckLoadHeader.get().getLicensePlateNumber());
                        bundle.putParcelable(Const.IntentKey.DATA, data);
                        startActivity(TruckLoadConfirmActivity.class, bundle);
                    });
    public final ObservableField<ResTruckLoad> truckLoadData = new ObservableField<>();
    public final ObservableBoolean refreshing = new ObservableBoolean(false);

    @Inject
    public VMTruckLoadDetail(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData(false);
    }

    public void requestData(boolean isRefreshMode) {
        if (isRefreshMode) refreshing.set(true);
        else showLoading();

        apiService.getTruckLoadList(Objects.requireNonNull(truckLoadHeader.get()).convert())
                .enqueue(new ResultCallback<ResTruckLoad>() {
                    @Override
                    public void onSuccess(ResTruckLoad data) {
                        truckLoadData.set(data);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                        if (isRefreshMode) refreshing.set(false);
                        else hideLoading();
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage());
                    }
                });
    }
}
