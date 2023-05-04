package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;
import androidx.recyclerview.widget.DiffUtil;

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
import com.arpa.wms.hly.utils.NumberUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;

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
public class VMGoodsRecheckDetailDiffList extends WrapDataViewModel {
    private static final String TAG = "@@@@ VMGoodsRecheckDetailDif";
    public ObservableInt status = new ObservableInt();
    public ReqGoodRecheckDetail request = new ReqGoodRecheckDetail();
    public DiffObservableList<RecheckItemVO> items = new DiffObservableList<>(new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull RecheckItemVO oldItem, @NonNull RecheckItemVO newItem) {
            return oldItem.getCode().equals(newItem.getCode());
        }

        @Override
        public boolean areContentsTheSame(@NonNull RecheckItemVO oldItem, @NonNull RecheckItemVO newItem) {
            return oldItem.getRecheckQuantity() == newItem.getRecheckQuantity()
                    || Objects.equals(oldItem.getScanRatio(), newItem.getScanRatio())
                    || NumberUtils.isEqual(oldItem.getRadio(), newItem.getRadio());
        }
    });
    private final TaskItemDao dao;
    public String supplierName;

    @Inject
    public VMGoodsRecheckDetailDiffList(@NonNull Application application, BaseModel model) {
        super(application, model);
        dao = getRoomDatabase(AppDatabase.class).taskItemDao();
    }

    @Override
    public void onResume() {
        super.onResume();
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
                            dao.insert(entity).subscribeOn(Schedulers.io()).subscribe();
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

    @Override
    public void onPause() {
        saveData();
        super.onPause();
    }

    private void saveData() {
        items.forEach(it -> {
            // NumberUtils.
        });
    }

    public void requestData() {
        updateStatus(StatusEvent.Status.LOADING);
        apiService.recheckItemListBelow(request.toParams()).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(List<RecheckItemVO> data) {
                updateStatus(StatusEvent.Status.SUCCESS);
                dao.getByTask(request.getOutboundCode())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        // .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(getApplication())))
                        .subscribe(it -> {
                            var tmp = data;
                            if (TASK_STATUS.RECHECK_WAIT == status.get() && !it.isEmpty()) {
                                tmp = data.stream().peek(m -> it.stream()
                                                .filter(m2 -> m2.getItemCode().equals(m.getCode()))
                                                .forEach(m2 -> {
                                                    m.setRadio(m2.getRadio());
                                                    m.setScanRatio(m2.getScanRatio());
                                                }))
                                        .collect(Collectors.toList());
                            }
                            items.update(tmp);
                        });
            }

            @Override
            public void onFailed(ResultError error) {
                updateStatus(StatusEvent.Status.ERROR);
                sendMessage(error.getMessage());
            }
        });
    }
}
