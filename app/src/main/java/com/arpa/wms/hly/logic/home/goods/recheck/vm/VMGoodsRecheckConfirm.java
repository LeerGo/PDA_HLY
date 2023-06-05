package com.arpa.wms.hly.logic.home.goods.recheck.vm;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.and.arch.base.livedata.StatusEvent.Status;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.GoodsItemVO;
import com.arpa.wms.hly.bean.entity.SNCode;
import com.arpa.wms.hly.bean.req.ReqGoodRecheckDetail;
import com.arpa.wms.hly.bean.req.ReqRecheckConfirm;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SNCodeDao;
import com.arpa.wms.hly.dao.TaskItemDao;
import com.arpa.wms.hly.logic.home.goods.recheck.GoodsRecheckBatchActivity;
import com.arpa.wms.hly.net.callback.ResultCallback;
import com.arpa.wms.hly.net.exception.ResultError;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.NumberUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

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
public class VMGoodsRecheckConfirm extends WrapDataViewModel {
    public ReqRecheckConfirm confirm = new ReqRecheckConfirm();
    public ReqGoodRecheckDetail request = new ReqGoodRecheckDetail();
    public ObservableField<GoodsItemVO> detail = new ObservableField<>();
    public ObservableField<String> recheckQuantity = new ObservableField<>();
    // 最新批次号
    public ObservableField<String> latestBatchNo = new ObservableField<>();
    // 最旧批次号
    public ObservableField<String> oldestBatchNo = new ObservableField<>();
    private final SNCodeDao snDao;
    private final TaskItemDao taskDao;
    public String obvBatchCode;
    private String taskCode;
    private String itemCode;

    @Inject
    public VMGoodsRecheckConfirm(@NonNull Application application, BaseModel model) {
        super(application, model);
        snDao = getRoomDatabase(AppDatabase.class).snCodeDao();
        taskDao = getRoomDatabase(AppDatabase.class).taskItemDao();
    }

    @Override
    public void onStart() {
        super.onStart();
        requestData();
    }

    private void requestData() {
        updateStatus(Status.LOADING);
        apiService.recheckRegisterDetail(request.toParams()).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(GoodsItemVO data) {
                detail.set(data);
                recheckQuantity.set(String.valueOf(detail.get().getWaitRecheckQuantity()));
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

    public void confirm() {
        var ratio = taskDao.getTaskRatio(taskCode, itemCode);
        if (NumberUtils.isLarger(detail.get().getScanningRatio(), ratio)) {
            Message msg = new Message();
            msg.what = Const.Message.MSG_DIALOG;
            msg.obj = "扫码比例低于仓库规定比例 " + NumberUtils.parseDecimal(detail.get().getScanningRatio()) + "%\n确认提交？";
            sendSingleLiveEvent(msg);
        } else {
            submit();
        }
    }

    public void record() {
        Bundle bundle = new Bundle();
        bundle.putString(Const.IntentKey.CODE, taskCode);
        bundle.putString(Const.IntentKey.OUTBOUND_ITEM_CODE, itemCode);
        bundle.putString(Const.IntentKey.GOODS_NAME, detail.get().getGoodsName());
        bundle.putString(Const.IntentKey.GOODS_CODE, detail.get().getGoodCode());
        bundle.putString(Const.IntentKey.GOODS_UNIT_NAME, detail.get().getGoodsUnitName());
        bundle.putInt(Const.IntentKey.GOODS_COUNT, detail.get().getWaitRecheckQuantity());
        bundle.putString(Const.IntentKey.DATE_MANUFACTURE, detail.get().getGmtManufacture());
        bundle.putString(Const.IntentKey.PLACE_ORIGIN, detail.get().getExtendOne());
        startActivity(GoodsRecheckBatchActivity.class, bundle);
    }

    public void submit() {
        if (TextUtils.isEmpty(recheckQuantity.get())) {
            sendMessage("请输入复核数量");
            return;
        }

        updateStatus(Status.LOADING);
        var tmp = snDao.getByTask(taskCode, itemCode);
        confirm.setBeachNumber(tmp.stream().map(SNCode::getSnCode).collect(Collectors.joining("\n")));
        confirm.setProductionDate(tmp.stream().map(SNCode::getProductionDate).collect(Collectors.joining("\n")));
        confirm.setRatio(tmp.stream().map(it -> String.valueOf(it.getScanRatio())).collect(Collectors.joining("\n")));
        tmp = null;
        confirm.setRecheckQuantity(recheckQuantity.get());
        confirm.setOutboundCode(taskCode);
        confirm.setOutboundItemCode(itemCode);
        apiService.recheckConfirm(confirm).enqueue(new ResultCallback<>() {
            @Override
            public void onSuccess(Object data) {
                snDao.deleteByTaskItem(taskCode, itemCode);
                taskDao.deleteByTaskItem(taskCode, itemCode);
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

    public void loadHistory() {
        List<SNCode> snCodes = snDao.getByTask(taskCode, itemCode);
        obvBatchCode = snCodes.stream().map(SNCode::getSnCode).collect(Collectors.joining("\n"));
        oldestBatchNo.set(snCodes.isEmpty() ? null : Collections.max(snCodes).getSnCode());
        latestBatchNo.set(snCodes.isEmpty() ? null : Collections.min(snCodes).getSnCode());
    }

    public void initParams(Intent intent) {
        taskCode = intent.getStringExtra(Const.IntentKey.OUTBOUND_CODE);
        itemCode = intent.getStringExtra(Const.IntentKey.OUTBOUND_ITEM_CODE);
        request.setParams(taskCode, itemCode);
    }
}
