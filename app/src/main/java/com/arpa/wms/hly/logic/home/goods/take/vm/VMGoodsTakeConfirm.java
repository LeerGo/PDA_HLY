package com.arpa.wms.hly.logic.home.goods.take.vm;

import com.google.gson.Gson;

import android.app.Application;
import android.util.Log;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBindingRVAdapter;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.GoodsTakeBatchHeader;
import com.arpa.wms.hly.bean.req.ReqGoodTakeConfirm;
import com.arpa.wms.hly.bean.req.ReqGoodTakeDetail;
import com.arpa.wms.hly.bean.res.ResGoodTakeConfirm;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货确认
 * </p>
 */
@HiltViewModel
public class VMGoodsTakeConfirm extends WrapDataViewModel {
    public final ObservableList<Object> items = new ObservableArrayList<>();
    public final BindingRecyclerViewAdapter<Object> adapter = new WrapBindingRVAdapter<>();
    public final ReqGoodTakeDetail request = new ReqGoodTakeDetail();
    // adapter 相关
    private final OnItemBind<Object> onItemBind =
            (itemBinding, position, data) -> {
                if (position == 0) {
                    itemBinding.set(BR.data, R.layout.header_goods_take_confirm);
                } else {
                    itemBinding.set(BR.data, R.layout.item_goods_take_confirm);
                }
            };
    public final ItemBinding<Object> itemBinding = ItemBinding.of(onItemBind);
    public ResGoodTakeConfirm detail;
    public ReqGoodTakeConfirm requestConfirm;

    @Inject
    public VMGoodsTakeConfirm(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onStart() {
        super.onStart();
        requestData();
    }

    private void requestData() {
        updateStatus(StatusEvent.Status.LOADING);
        apiService.takeRegisterDetail(request.toParams())
                .enqueue(new ResultCallback<ResGoodTakeConfirm>() {
                    @Override
                    public void onSuccess(ResGoodTakeConfirm data) {
                        detail = data;
                        detail.setCode(request.getReceiveCode());
                        detail.setReceiveCode(request.getReceiveCode());

                        addHeaderData();
                        addBatchData();
                    }

                    /**
                     * 添加头部信息
                     */
                    private void addHeaderData() {
                        GoodsTakeBatchHeader header = new GoodsTakeBatchHeader();
                        header.convert(detail);
                        items.add(header);
                    }

                    /**
                     * 添加录入条目
                     */
                    private void addBatchData() {
                        List<GoodsItemVO> records = detail.getReceiveItemWithRegisterVOList();
                        if (records.isEmpty())
                            items.add(new GoodsItemVO());
                        else
                            items.addAll(records);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        hideLoading();
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        ToastUtils.showShort(error.getMessage());
                        finish();
                    }
                });
    }

    /**
     * 添加一个新的批次录入
     */
    public void addBatchItem() {
        if (check((GoodsItemVO) items.get(items.size() - 1)))
            items.add(new GoodsItemVO());
    }

    /**
     * 在添加一个新的录入批次前，进行合规检查，要符合一下条件
     * 1. 当前所有录入批次的收货数量 < 应收数量
     * 2. 上一条录入批次的所有参数都不得为空，全部录入了
     */
    private boolean check(GoodsItemVO item) {
        return true;
    }

    /**
     * 收货登记确认
     *
     * @param isWholeConfirm
     *         true - 整单确认
     */
    public void orderConfirm(boolean isWholeConfirm) {
        requestConfirm = detail;
        Log.e("@@@@ L112", "VMGoodsTakeConfirm:orderConfirm() -> request json = " + new Gson().toJson(requestConfirm));
        /*updateStatus(StatusEvent.Status.LOADING);
        ResultCallback<Object> callback = new ResultCallback<Object>() {
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
                ToastUtils.showShort(error.getMessage());
            }
        };

        if (isWholeConfirm) {
            apiService.takeWholeConfirm(detail).enqueue(callback);
        } else {
            apiService.takeSingleConfirm(detail).enqueue(callback);
        }*/
    }

    public void update(int position, GoodsItemVO data) {
        items.remove(position);
        items.add(position, data);
    }
}
