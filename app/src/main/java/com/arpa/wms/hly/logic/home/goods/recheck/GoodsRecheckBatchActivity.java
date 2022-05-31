package com.arpa.wms.hly.logic.home.goods.recheck;

import com.google.android.material.chip.Chip;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityGoodsRecheckBatchBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckBatch;
import com.arpa.wms.hly.ui.dialog.DialogTips;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.Const.IntentKey;
import com.arpa.wms.hly.utils.WeakHandler;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品复核 - 批次登记
 * </p>
 */
@AndroidEntryPoint
public class GoodsRecheckBatchActivity
        extends WrapBaseActivity<VMGoodsRecheckBatch, ActivityGoodsRecheckBatchBinding>
        implements WeakHandler.MessageListener {

    private static final int MSG_ADD_TAG = 0x1;
    private static WeakHandler<GoodsRecheckBatchActivity> sHandler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_recheck_batch;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        sHandler = new WeakHandler<>(GoodsRecheckBatchActivity.this);
        viewBind.setViewModel(viewModel);
        viewModel.initData(getIntent());
        viewModel.getSingleLiveEvent().observeForever(message -> {
            switch (message.what) {

                case Const.Message.MSG_DIALOG:
                    showDialogFragment(new DialogTips("暂存数据导入", "当前页面存在暂存但未提交的数据，是否载入？", "丢弃", "加载",
                            () -> viewModel.restoreRecords(),
                            () -> viewModel.discardRecords()));
                    break;

                case Const.Message.MSG_RESTORE:
                    restoreCodes();
                    viewModel.calcRadio();
                    break;

                case Const.Message.MSG_FINISH_RESULT:
                    finishResult();
                    break;

                default:
                    break;
            }
        });

        setViews();
    }

    private void setViews() {
        viewBind.acbSure.setOnClickListener(v -> finishResult());
        viewBind.acbSave.setOnClickListener(v -> viewModel.saveAll());
        viewBind.wiiInput.setOnTextChanged(this::postMsgDelayed);
    }

    private void finishResult() {
        Intent data = new Intent();
        data.putStringArrayListExtra(IntentKey.DATA, viewModel.parseCodeList());
        setResult(RESULT_OK, data);
        finish();
    }

    /**
     * 延迟发送消息，通知添加批次号 chip-view
     */
    private void postMsgDelayed(String msg) {
        Message message = new Message();
        message.what = MSG_ADD_TAG;
        message.obj = msg;
        if (sHandler.hasMessages(MSG_ADD_TAG)) {
            sHandler.removeMessages(MSG_ADD_TAG);
        }
        sHandler.sendMessageDelayed(message, 500);
    }

    private void restoreCodes() {
        if (!viewModel.codeList.isEmpty()) {
            for (int i = viewModel.codeList.size() - 1; i >= 0; i--) {
                addTagView(viewModel.codeList.get(i).getSnCode(), true);
            }
        }
    }

    /**
     * 添加 view
     */
    private void addTagView(String text, boolean isRestore) {
        if (!isRestore && viewModel.inputInvalid(text)) return;
        Chip chip = new Chip(this);
        chip.setCloseIconVisible(true);
        chip.setText(text);
        chip.setTextColor(Color.WHITE);
        chip.setChipBackgroundColorResource(R.color.colorPrimary);
        chip.setCloseIconTintResource(R.color.white);
        chip.setOnCloseIconClickListener(v -> {
            viewModel.optTagData(text, false);
            viewBind.cgBatchTags.removeView(v);
        });
        if (!isRestore) viewModel.optTagData(text, true);
        viewBind.cgBatchTags.addView(chip, 0);
    }

    private void addTagView(String text) {
        addTagView(text, false);
    }

    @Override
    protected void onDestroy() {
        sHandler.clear();
        super.onDestroy();
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == MSG_ADD_TAG) {
            addTagView((String) msg.obj);
            viewBind.wiiInput.setInputText("");
        }
    }
}

