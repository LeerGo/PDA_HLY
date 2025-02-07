package com.arpa.wms.hly.logic.home.goods.take.vm;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.MessageEvent;
import com.arpa.and.arch.base.livedata.StatusEvent;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBindingRVAdapter;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.GoodsTakeBatchHeader;
import com.arpa.wms.hly.bean.InventoryStatus;
import com.arpa.wms.hly.bean.req.ReqGoodTakeDetail;
import com.arpa.wms.hly.bean.res.ResGoodTakeConfirm;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.NumberUtils;
import com.arpa.wms.hly.utils.ToastUtils;

import java.util.List;

import javax.inject.Inject;

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
// TODO: 库位输入改为模糊搜索，待对接@陈港 add by 李一方 2023-02-01 13:49:54
@HiltViewModel
public class VMGoodsTakeConfirm extends WrapDataViewModel {
    public final ObservableArrayList<Object> items = new ObservableArrayList<>();
    public final BindingRecyclerViewAdapter<Object> adapter = new WrapBindingRVAdapter<>();
    public final ReqGoodTakeDetail request = new ReqGoodTakeDetail();
    // 弹窗通知
    public final MessageEvent dialogMsg = new MessageEvent();
    public ObservableField<String> scanText = new ObservableField<>();
    public ObservableField<ResGoodTakeConfirm> detail = new ObservableField<>();
    // adapter 相关
    private final OnItemBind<Object> onItemBind =
            (itemBinding, position, data) -> {
                if (data instanceof GoodsItemVO) {
                    itemBinding.set(BR.data, R.layout.item_goods_take_confirm)
                            .bindExtra(BR.rule, detail.get().getBatchRule());
                } else {
                    // FIX: 这里如果 variableId 相同，会出现 class cast exception @lyf 2021-06-03 08:22:22
                    itemBinding.set(BR.header, R.layout.header_goods_take_confirm);
                }
            };
    public final ItemBinding<Object> itemBinding = ItemBinding.of(onItemBind);
    // 供应商
    public String supplier;
    // 默认状态
    private InventoryStatus statusDefault;

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
        apiService.takeRegisterDetail(request.toParams()).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(ResGoodTakeConfirm data) {
                statusDefault = null;
                items.clear();

                setStatusDefault(data);
                setDetail(data);
                addHeaderData();
                addBatchData();
            }

            private void setDetail(ResGoodTakeConfirm data) {
                detail.set(data);
                detail.get().setCode(data.getCode());
                detail.get().setReceiveCode(request.getReceiveCode());
            }

            private void setStatusDefault(ResGoodTakeConfirm data) {
                for (InventoryStatus item : data.getInventoryStatusList()) {
                    if (item.getName().equals("合格")) {
                        statusDefault = item;
                        break;
                    }
                }
            }

            /**
             * 添加头部信息
             */
            private void addHeaderData() {
                GoodsTakeBatchHeader header = new GoodsTakeBatchHeader();
                header.convert(detail.get());
                items.add(header);
            }

            /**
             * 添加录入条目
             */
            private void addBatchData() {
                List<GoodsItemVO> records = detail.get().getReceiveItemWithRegisterVOList();
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
                if (null == detail.get()) finish();
            }
        });
    }

    /**
     * 添加一个新的批次录入条目
     */
    private void addBatchItem() {
        GoodsItemVO batchItem = new GoodsItemVO();
        batchItem.fromDetail(detail.get());
        batchItem.setStatus(statusDefault);
        batchItem.setExpirationQuantity(detail.get().getExpirationQuantity());
        if (null != detail && detail.get().getBatchRule().getSupplier() == 1)
            batchItem.setSupplier(supplier);
        items.add(batchItem);
    }

    /**
     * 添加一个新的批次录入
     */
    public void addBatch() {
        if (validateInput(true)) addBatchItem();
    }

    /**
     * 收货登记确认
     *
     * @param isWholeConfirm
     *         true - 整单确认
     */
    public void orderConfirm(boolean isWholeConfirm) {
        if (!validateInput(false)) return;

        updateStatus(StatusEvent.Status.LOADING);

        if (!detail.get().getReceiveItemWithRegisterVOList().isEmpty())
            detail.get().getReceiveItemWithRegisterVOList().clear();
        for (int i = 1; i < items.size(); i++) {
            detail.get().getReceiveItemWithRegisterVOList().add((GoodsItemVO) items.get(i));
        }

        ResultCallback<Object> callback = new ResultCallback<>() {
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
            apiService.takeWholeConfirm(detail.get()).enqueue(callback);
        } else {
            apiService.takeSingleConfirm(detail.get()).enqueue(callback);
        }
    }

    /**
     * 在添加一个新的录入批次前，进行合规检查，要符合一下条件
     * 1. 当前所有录入批次的收货数量 < 应收数量
     * 2. 上一条录入批次的所有参数都不得为空，全部录入了
     */
    private boolean validateInput(boolean isAddBatch) {
        int batchGoodsCount = 0;// 批次数量计数
        boolean result = true;

        for (int i = 1; i < items.size(); i++) {
            GoodsItemVO data = (GoodsItemVO) items.get(i);
            batchGoodsCount += NumberUtils.parseInteger(data.getReceivedQuantity());
            // 条件#1
            if (batchGoodsCount > detail.get().getPlanQuantity() - detail.get().getReceivedQuantity()) {
                ToastUtils.showShort("已录入批次收货数量超过应收数量");
                result = false;
                break;
            }
            // 条件#1#只有在添加批次时才校验
            if (batchGoodsCount == detail.get().getPlanQuantity() - detail.get().getReceivedQuantity() && isAddBatch) {
                dialogMsg.postValue("当前商品已全部收货，不能添加收货批次。");
                result = false;
                break;
            }
            // 条件#3
            if (!validateItem(data)) {
                ToastUtils.showShort("请完整录入第" + i + "条收货批次数据");
                result = false;
                break;
            }
        }
        if (!isAddBatch) {
            if (detail.get().getBatchRule().getExtendThree() == 1 && null == detail.get().getExtendThree()) {
                sendMessage("请输入扫码比例");
                result = false;
            }
        }
        return result;
    }

    /**
     * 验证每一条的数据是否都完整录入
     */
    private boolean validateItem(GoodsItemVO item) {
        if (TextUtils.isEmpty(item.getGoodsStatus())) return false;
        if (null == item.getReceivedQuantity()) return false;
        if (null == item.getSupportNum()) return false;
        if (TextUtils.isEmpty(item.getLocation())) return false;
        if (detail.get().getBatchRule().getGmtManufacture() == 1) {
            if (TextUtils.isEmpty(item.getGmtManufacture())) return false;
        }
        if (detail.get().getBatchRule().getGmtStock() == 1) {
            if (TextUtils.isEmpty(item.getGmtStock())) return false;
        }
        if (detail.get().getBatchRule().getGmtExpire() == 1) {
            if (TextUtils.isEmpty(item.getGmtExpire())) return false;
        }
        if (detail.get().getBatchRule().getSerialNumber() == 1) {
            if (TextUtils.isEmpty(item.getSerialNumber())) return false;
        }
        if (detail.get().getBatchRule().getSupplier() == 1) {
            if (TextUtils.isEmpty(item.getSupplier())) return false;
        }
        if (detail.get().getBatchRule().getExtendOne() == 1) {
            if (TextUtils.isEmpty(item.getExtendOne())) return false;
        }
        if (detail.get().getBatchRule().getExtendTwo() == 1) {
            if (TextUtils.isEmpty(item.getExtendTwo())) return false;
        }
        if (detail.get().getBatchRule().getExtendFour() == 1) {
            if (null == item.getExtendFour()) return false;
        }
        if (detail.get().getBatchRule().getExtendFive() == 1) {
            if (TextUtils.isEmpty(item.getExtendFive())) return false;
        }
        if (detail.get().getBatchRule().getExtendSix() == 1) {
            if (TextUtils.isEmpty(item.getExtendSix())) return false;
        }
        return true;
    }

    /**
     * 更新指定位置数据
     */
    public void update(int position, GoodsItemVO data) {
        items.set(position, data);
    }
}
