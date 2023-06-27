package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.SNCodeTip;
import com.arpa.wms.hly.bean.SNCutRule;
import com.arpa.wms.hly.databinding.ActivityGoodsRecheckBatchBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMSerialBatch;
import com.arpa.wms.hly.ui.dialog.DialogMultiRule;
import com.arpa.wms.hly.ui.dialog.DialogTips;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.WeakHandler;

import java.util.HashMap;
import java.util.List;

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
public class GoodsRecheckBatchActivity extends WrapBaseActivity<VMSerialBatch, ActivityGoodsRecheckBatchBinding>
        implements WeakHandler.MessageListener {
    private static WeakHandler<GoodsRecheckBatchActivity> sHandler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_recheck_batch;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        sHandler = new WeakHandler<>(this);
        viewBind.setViewModel(viewModel);
        viewModel.initParams(getIntent());
        viewModel.getSingleLiveEvent().observeForever(this::processEvent);
        viewBind.wiiInput.setOnTextChanged(data -> {
            if (!viewModel.isManually.get()) {
                postMsgDelayed(data);
            }
        });
        viewBind.btnManually.setOnClickListener(v ->
                postMsgDelayed(viewBind.wiiInput.getInputText())
        );
    }


    private void processEvent(Message message) {
        switch (message.what) {

            case Const.Message.MSG_DIALOG:
                showDialogFragment(new DialogTips("暂存数据导入", "当前页面存在暂存但未提交的数据，是否载入？", "丢弃", "加载",
                        () -> viewModel.init(), () -> viewModel.removeHistory()));
                break;

            case Const.Message.MSG_BATCH_VERIFY:
                SNCodeTip tip = (SNCodeTip) message.obj;

                showDialogFragment(new DialogTips("校验提示", tip.getTip(), "删除", "录入",
                        () -> viewModel.addSNCode(tip.getRule(), tip.getCode()), () -> {}));
                break;

            case Const.Message.MSG_BATCH_CONFIRM:
                showDialogFragment(new DialogTips("校验提示", (String) message.obj, this::finish));
                break;

            case Const.Message.MSG_BATCH_REPEAT:
                showDialogFragment(new DialogTips("校验提示", "该批次号已当前或其他单据录入", () -> {}));
                break;

            case Const.Message.MSG_MULTI_RULE:
                showDialogFragment(DialogMultiRule.newInstance((HashMap<Integer, List<SNCutRule>>) message.obj, viewModel.ruleSelect, data -> viewModel.multiRuleSel(data)));
                break;

            default:
                break;
        }
    }

    /**
     * 延迟发送消息，通知添加批次号 chip-view
     */
    private void postMsgDelayed(String msg) {
        Message message = new Message();
        message.what = Const.Message.MSG_ADD_TAG;
        message.obj = msg;
        if (sHandler.hasMessages(Const.Message.MSG_ADD_TAG)) {
            sHandler.removeMessages(Const.Message.MSG_ADD_TAG);
        }
        sHandler.sendMessageDelayed(message, 500);
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == Const.Message.MSG_ADD_TAG) {
            viewModel.onScan((String) msg.obj);
            viewBind.wiiInput.setInputText("");
        }
    }

    @Override
    protected void onDestroy() {
        sHandler.clear();
        super.onDestroy();
    }
}
