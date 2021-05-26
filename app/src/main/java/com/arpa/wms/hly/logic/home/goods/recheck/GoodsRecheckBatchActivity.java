package com.arpa.wms.hly.logic.home.goods.recheck;

import com.google.android.material.chip.Chip;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityGoodsRecheckBatchBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckBatch;
import com.arpa.wms.hly.utils.Const.IntentKey;
import com.arpa.wms.hly.utils.ToastUtils;
import com.arpa.wms.hly.utils.WeakHandler;

import java.util.ArrayList;

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

        setViews();
        restoreCodes();
        viewModel.goodName.set(getIntent().getStringExtra(IntentKey.GOODS_NAME));
        viewModel.goodUnitName.set(getIntent().getStringExtra(IntentKey.GOODS_UNIT_NAME));
    }

    private void setViews() {
        viewBind.acbSure.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putStringArrayListExtra(IntentKey.DATA, viewModel.codeList);
            setResult(RESULT_OK, data);
            finish();
        });
        viewBind.wiiInput.setOnTextChanged(this::postMsgDelayed);
    }

    private void restoreCodes() {
        ArrayList<String> codeList = getIntent().getStringArrayListExtra(IntentKey.DATA);
        if (!codeList.isEmpty()) {
            for (int i = codeList.size() - 1; i >= 0; i--) {
                addTagView(codeList.get(i));
            }
        }
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

    /**
     * 添加 view
     */
    private void addTagView(String text) {
        if (TextUtils.isEmpty(text)) return;
        if (viewModel.codeList.contains(text)) {
            ToastUtils.showShort("该批次号已录入");
            return;
        }
        Chip chip = new Chip(this);
        chip.setCloseIconVisible(true);
        chip.setText(text);
        chip.setTextColor(Color.WHITE);
        chip.setChipBackgroundColorResource(R.color.colorPrimary);
        chip.setCloseIconTintResource(R.color.white);
        chip.setOnCloseIconClickListener(v -> {
            viewModel.codeList.remove(text);
            viewBind.cgBatchTags.removeView(v);
        });
        viewModel.codeList.add(0, text);
        viewBind.cgBatchTags.addView(chip, 0);
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == MSG_ADD_TAG) {
            addTagView((String) msg.obj);
            viewBind.wiiInput.setInputText("");
        }
    }

    @Override
    protected void onDestroy() {
        sHandler.clear();
        super.onDestroy();
    }
}
