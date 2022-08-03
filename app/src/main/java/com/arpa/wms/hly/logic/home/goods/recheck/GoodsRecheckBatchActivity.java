package com.arpa.wms.hly.logic.home.goods.recheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityGoodsRecheckBatchBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckBatch;
import com.arpa.wms.hly.ui.dialog.DialogTips;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.Const.IntentKey;
import com.arpa.wms.hly.utils.WeakHandler;

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
        viewBind.wiiInput.addOnEditor((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                postMsgDelayed(v.getText().toString());
            }
            return false;
        });
        viewModel.getSingleLiveEvent().observeForever(message -> {
            switch (message.what) {

                case Const.Message.MSG_DIALOG:
                    showDialogFragment(new DialogTips("暂存数据导入", "当前页面存在暂存但未提交的数据，是否载入？", "丢弃", "加载",
                            () -> viewModel.restoreRecords(),
                            () -> viewModel.discardRecords()));
                    break;

                case Const.Message.MSG_BATCH_VERIFY:
                    String[] obj = (String[]) message.obj;
                    showDialogFragment(new DialogTips("校验提示", obj[0], "删除", "录入",
                            () -> viewModel.addData(), () -> {}));
                    break;

                case Const.Message.MSG_BATCH_CONFIRM:
                    showDialogFragment(new DialogTips("校验提示", (String) message.obj, this::finishResult));
                    break;

                case Const.Message.MSG_FINISH_RESULT:
                    finishResult();
                    break;

                case Const.Message.MSG_BATCH_REPEAT:
                    showDialogFragment(new DialogTips("校验提示", "该批次号已录入", () -> {}));
                    break;

                default:
                    break;
            }
        });
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
        sHandler.sendMessage(message);
    }

    private void finishResult() {
        Intent data = new Intent();
        data.putParcelableArrayListExtra(IntentKey.DATA, viewModel.codeList);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    protected void onDestroy() {
        sHandler.clear();
        super.onDestroy();
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == MSG_ADD_TAG) {
            viewModel.isFocus.set(false);
            viewModel.addTag((String) msg.obj);
            viewBind.wiiInput.setInputText("");
        }
    }
}
