package com.arpa.wms.hly.logic.task.vm;

import android.app.Application;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.req.ReqPickDetail;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.bean.res.ResTaskPick;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

@HiltViewModel
public class VMTaskGoodsPick extends WrapDataViewModel {
    public final ReqPickDetail reqPickDetail = new ReqPickDetail();
    public final ItemBinding<GoodsItemVO> itemBinding = ItemBinding.of(BR.data, R.layout.item_task_goods_pick);
    public MutableLiveData<ResTaskAssign> headerData = new MutableLiveData<>();
    public ObservableBoolean refreshing = new ObservableBoolean();
    public ObservableBoolean isAutoRefresh = new ObservableBoolean();
    public ObservableField<ResTaskPick> resTaskPick = new ObservableField<>();

    @Inject
    public VMTaskGoodsPick(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onStart() {
        super.onStart();
        autoRefresh();
    }

    private void autoRefresh() {
        isAutoRefresh.set(true);
        refresh();
    }

    /**
     * 下拉刷新数据
     */
    public void refresh() {
        refreshing.set(true);
        requestData();
    }

    private void requestData() {
        if (!isAutoRefresh.get())
            updateStatus(Status.LOADING);
        apiService.pickingDetailTask(reqPickDetail.getSourceCode())
                .enqueue(new ResultCallback<ResTaskPick>() {
                    @Override
                    public void onSuccess(ResTaskPick data) {
                        resTaskPick.set(data);
                        ResTaskAssign task = headerData.getValue();
                        task.setRecheckQuantity(data.getPickingQuantity());
                        headerData.postValue(task);
                        updateStatus(Status.SUCCESS);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        refreshing.set(false);
                        isAutoRefresh.set(false);
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        sendMessage(error.getMessage());
                        updateStatus(Status.SUCCESS);
                    }
                });

    }
}