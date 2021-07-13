package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;
import android.text.TextUtils;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBindingRVAdapter;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.GoodsTakeBatchHeader;
import com.arpa.wms.hly.bean.req.ReqGoodTakeDetail;
import com.arpa.wms.hly.bean.res.ResGoodTakeConfirm;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.NumberUtils;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
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
    public final ObservableArrayList<Object> items = new ObservableArrayList<>();
    public final BindingRecyclerViewAdapter<Object> adapter = new WrapBindingRVAdapter<>();
    public final ReqGoodTakeDetail request = new ReqGoodTakeDetail();
    public ResGoodTakeConfirm detail;
    // adapter 相关
    private final OnItemBind<Object> onItemBind =
            (itemBinding, position, data) -> {
                if (data instanceof GoodsItemVO) {
                    itemBinding.set(BR.data, R.layout.item_goods_take_confirm).bindExtra(BR.rule, detail.getBatchRule());
                } else {
                    // FIX: 这里如果 variableId 相同，会出现 class cast exception @lyf 2021-06-03 08:22:22
                    itemBinding.set(BR.header, R.layout.header_goods_take_confirm);
                }
            };
    public final ItemBinding<Object> itemBinding = ItemBinding.of(onItemBind);
    public String supplier; // 供应商

    @Inject
    public VMGoodsTakeConfirm(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onStart() {
        super.onStart();
        requestData();
    }

    /**
     * 请求数据
     */
    public void requestData() {
        updateStatus(StatusEvent.Status.LOADING);
        apiService.takeRegisterDetail(request.toParams())
                .enqueue(new ResultCallback<ResGoodTakeConfirm>() {
                    @Override
                    public void onSuccess(ResGoodTakeConfirm data) {
                        items.clear();

                        detail = data;
                        detail.setCode(data.getCode());
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
                        if (null != records && !records.isEmpty()) items.addAll(records);
                        addBatchItem();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        hideLoading();
                    }

                    @Override
                    public void onFailed(ResultError error) {
                        ToastUtils.showShort(error.getMessage());
                        if (null == detail) finish();
                    }
                });
    }

    /**
     * 添加一个新的批次录入条目
     */
    private void addBatchItem() {
        GoodsItemVO batchItem = new GoodsItemVO();
        batchItem.fromDetail(detail);
        batchItem.setExpirationQuantity(detail.getExpirationQuantity());
        if (null != detail && detail.getBatchRule().getSupplier() == 1)
            batchItem.setSupplier(supplier);
        items.add(batchItem);
    }

    /**
     * 添加一个新的批次录入
     */
    public void addBatch() {
        if (validateInput()) addBatchItem();
    }

    /**
     * 在添加一个新的录入批次前，进行合规检查，要符合一下条件
     * 1. 当前所有录入批次的收货数量 < 应收数量
     * 2. 上一条录入批次的所有参数都不得为空，全部录入了
     */
    private boolean validateInput() {
        int batchGoodsCount = 0;// 批次数量计数
        boolean result = true;

        for (int i = 1; i < items.size(); i++) {
            GoodsItemVO data = (GoodsItemVO) items.get(i);
            batchGoodsCount += NumberUtils.parseInteger(data.getReceivedQuantity());
            // 条件#1
            if (batchGoodsCount > detail.getPlanQuantity()) {
                ToastUtils.showShort("已录入批次收货数量超过应收数量");
                result = false;
                break;
            }
            // 条件#2
            if (!validateItem(data)) {
                ToastUtils.showShort("请完整录入第" + i + "条收货批次数据");
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 验证每一条的数据是否都完整录入
     */
    private boolean validateItem(GoodsItemVO item) {
        if (TextUtils.isEmpty(item.getGoodsStatus())) return false;
        if (item.getReceivedQuantity() == 0) return false;
        if (item.getSupportNum() == 0) return false;
        if (TextUtils.isEmpty(item.getLocation())) return false;
        if (detail.getBatchRule().getGmtManufacture() == 1) {
            if (TextUtils.isEmpty(item.getGmtManufacture())) return false;
        }
        if (detail.getBatchRule().getGmtStock() == 1) {
            if (TextUtils.isEmpty(item.getGmtStock())) return false;
        }
        if (detail.getBatchRule().getGmtExpire() == 1) {
            if (TextUtils.isEmpty(item.getGmtExpire())) return false;
        }
        if (detail.getBatchRule().getSerialNumber() == 1) {
            if (TextUtils.isEmpty(item.getSerialNumber())) return false;
        }
        if (detail.getBatchRule().getSupplier() == 1) {
            if (TextUtils.isEmpty(item.getSupplier())) return false;
        }
        if (detail.getBatchRule().getExtendOne() == 1) {
            if (TextUtils.isEmpty(item.getExtendOne())) return false;
        }
        if (detail.getBatchRule().getExtendTwo() == 1) {
            if (TextUtils.isEmpty(item.getExtendTwo())) return false;
        }
        if (detail.getBatchRule().getExtendThree() == 1) {
            if (null == item.getExtendThree()) return false;
        }
        if (detail.getBatchRule().getExtendFour() == 1) {
            if (null == item.getExtendFour()) return false;
        }
        if (detail.getBatchRule().getExtendFive() == 1) {
            if (TextUtils.isEmpty(item.getExtendFive())) return false;
        }
        if (detail.getBatchRule().getExtendSix() == 1) {
            if (TextUtils.isEmpty(item.getExtendSix())) return false;
        }
        return true;
    }

    /**
     * 收货登记确认
     *
     * @param isWholeConfirm
     *         true - 整单确认
     */
    public void orderConfirm(boolean isWholeConfirm) {
        if (!validateInput()) return;

        updateStatus(StatusEvent.Status.LOADING);

        if (!detail.getReceiveItemWithRegisterVOList().isEmpty())
            detail.getReceiveItemWithRegisterVOList().clear();
        for (int i = 1; i < items.size(); i++) {
            detail.getReceiveItemWithRegisterVOList().add((GoodsItemVO) items.get(i));
        }

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
        }
    }

    /**
     * 更新指定位置数据
     */
    public void update(int position, GoodsItemVO data) {
        items.set(position, data);
    }
}