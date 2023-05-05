package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.RecheckItemVO;
import com.arpa.wms.hly.bean.entity.TaskItemEntity;
import com.arpa.wms.hly.bean.req.ReqGoodRecheckDetail;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.TaskItemDao;
import com.arpa.wms.hly.logic.home.goods.recheck.GoodsRecheckConfirmActivity;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.Const.IntentKey;
import com.arpa.wms.hly.utils.Const.TASK_STATUS;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:03 PM
 *
 * <p>
 * ViewModel: 商品待复核列表
 * </p>
 */
@HiltViewModel
public class VMGoodsRecheckDetailList extends WrapDataViewModel {
    public ObservableInt status = new ObservableInt();
    public ObservableBoolean refreshing = new ObservableBoolean();
    public ReqGoodRecheckDetail request = new ReqGoodRecheckDetail();
    public ObservableArrayList<RecheckItemVO> items = new ObservableArrayList<>();
    private final TaskItemDao dao;
    public String supplierName;

    @Inject
    public VMGoodsRecheckDetailList(@NonNull Application application, BaseModel model) {
        super(application, model);
        dao = getRoomDatabase(AppDatabase.class).taskItemDao();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        requestData();
    }

    public ItemBinding<RecheckItemVO> getItemBinding() {
        ItemBinding<RecheckItemVO> itemBinding;
        if (request.getRecheckStatus() == TASK_STATUS.RECHECK_WAIT) {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_recheck_detail_wait);
            itemBinding.bindExtra(BR.supplier, supplierName)
                    .bindExtra(BR.listener, (ViewListener.DataTransCallback<GoodsItemVO>) data -> {
                        Bundle bundle = new Bundle();
                        bundle.putString(IntentKey.OUTBOUND_CODE, data.getOutboundCode());
                        bundle.putString(IntentKey.OUTBOUND_ITEM_CODE, data.getCode());
                        startActivity(GoodsRecheckConfirmActivity.class, bundle);
                    })
                    .bindExtra(BR.focusCall, (ViewListener.FocusCallback<RecheckItemVO>) (isFocus, data) -> {
                        if (!isFocus) {
                            TaskItemEntity entity = new TaskItemEntity();
                            entity.convert(data);
                            dao.save(entity);
                        }
                    });
        } else {
            itemBinding = ItemBinding.of(BR.data, R.layout.item_goods_recheck_detail_yet);
            itemBinding.bindExtra(BR.supplier, supplierName);
        }

        return itemBinding;
    }

    /**
     * 结束作业
     */
    public void finishWork() {
        // TODO: 待实现 add by 李一方 2023-04-18 17:04:27
    }

    public void initParams(Bundle bundle) {
        status.set(bundle.getInt(Const.IntentKey.STATUS));
        String code = bundle.getString(Const.IntentKey.CODE);
        request.setParams(status.get(), code);
    }

    public void requestData() {
        updateStatus(StatusEvent.Status.LOADING);
        refreshing.set(true);
        apiService.recheckItemListBelow(request.toParams()).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(List<RecheckItemVO> data) {
                if (TASK_STATUS.RECHECK_WAIT == status.get()) {
                    data.forEach(it -> {
                        var task = dao.exists(request.getOutboundCode(), it.getCode());
                        if (null == task) {
                            task = new TaskItemEntity();
                            task.convert(it);
                            dao.save(task);
                        } else {
                            it.setRatio(task.getRatio());
                            it.setScanRatio(task.getScanRatio());
                        }
                    });
                }
                items.clear();
                items.addAll(data);
                updateStatus(StatusEvent.Status.SUCCESS);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                refreshing.set(false);
            }

            @Override
            public void onFailed(ResultError error) {
                updateStatus(StatusEvent.Status.ERROR);
                sendMessage(error.getMessage());
            }
        });
    }
}
