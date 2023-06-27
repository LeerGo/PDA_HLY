package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.SNCodeTip;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMSerialDetail;
import com.arpa.wms.hly.ui.dialog.DialogTips;
import com.arpa.wms.hly.ui.listener.SimpleTextWatcher;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.WeakHandler;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品待复核、已复核列表
 * </p>
 */
@AndroidEntryPoint
public class GoodsRecheckDetailFragment1 extends GoodsRecheckDetailFragment2
        implements WeakHandler.MessageListener {
    private static WeakHandler<GoodsRecheckDetailFragment1> sHandler;
    private VMSerialDetail vmSerial;

    public static GoodsRecheckDetailFragment1 newInstance(int outboundStatus, String outboundCode) {
        GoodsRecheckDetailFragment1 fragment = new GoodsRecheckDetailFragment1();
        Bundle args = new Bundle();
        args.putInt(Const.IntentKey.STATUS, outboundStatus);
        args.putString(Const.IntentKey.CODE, outboundCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods_recheck_detail;
    }

    @Override
    public void onDestroy() {
        if (null != vmSerial) {
            vmSerial.release();
        }
        sHandler.clear();
        super.onDestroy();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        sHandler = new WeakHandler<>(this);
        viewModel.getSingleLiveEvent().observeForever(this::processEvent);
        combineVM();
        setViews();
    }

    @Override
    protected void combineVM() {
        super.combineVM();
        String code = requireArguments().getString(Const.IntentKey.CODE);
        observeSerial(code);
    }

    private void setViews() {
        viewBind.wsbSearch.setWatcher(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                postMsgDelayed(s.toString());
            }
        });
    }

    private void processEvent(Message message) {
        switch (message.what) {

            case Const.Message.MSG_BATCH_REPEAT:
                showDialogFragment(new DialogTips("校验提示", "该批次号已当前或其他单据录入", () -> {}));
                break;

            case Const.Message.MSG_BATCH_VERIFY:
                SNCodeTip tip = (SNCodeTip) message.obj;
                showDialogFragment(new DialogTips("校验提示", tip.getTip(), "删除", "录入",
                        () -> vmSerial.addSNCode(tip.getRule(), tip.getCode()), () -> {}));
                break;
            default:
                break;
        }
    }

    private void observeSerial(String code) {
        if (Const.TASK_STATUS.RECHECK_WAIT == recheckStatus) {
            vmSerial = obtainViewModel(VMSerialDetail.class);
            vmSerial.register(this, viewModel);
            vmSerial.setTaskCode(code);
            vmSerial.setItems(viewModel.items);
            viewBind.setVmSerial(vmSerial);
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
        if (Const.TASK_STATUS.RECHECK_WAIT == recheckStatus && msg.what == Const.Message.MSG_ADD_TAG) {
            viewBind.getVmSerial().onScan((String) msg.obj);
            viewBind.wsbSearch.setWsbText(null);
        }
    }
}
