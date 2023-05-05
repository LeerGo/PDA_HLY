package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.databinding.ActivityGoodsRecheckBatchBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMSerialBatch;
import com.arpa.wms.hly.utils.Const;

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
// public class GoodsRecheckBatchActivity extends WrapBaseActivity<VMGoodsRecheckBatch, ActivityGoodsRecheckBatchBinding> {
public class GoodsRecheckBatchActivity extends WrapBaseActivity<VMSerialBatch, ActivityGoodsRecheckBatchBinding> {
    // private static final int MSG_ADD_TAG = 0x1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_recheck_batch;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewModel.initParams(getIntent());
        viewModel.getSingleLiveEvent().observeForever(this::processEvent);
        // observerSerial(getIntent());
    }

    /*private void observerSerial(Intent intent) {
        VMSerialBatch vmSerial = obtainViewModel(VMSerialBatch.class);
        vmSerial.register(this, viewModel);
        vmSerial.setTaskCode(intent.getStringExtra(Const.IntentKey.CODE));
        vmSerial.setItemCode(intent.getStringExtra(Const.IntentKey.OUTBOUND_ITEM_CODE));
        vmSerial.setGoodsCode(intent.getStringExtra(Const.IntentKey.GOODS_CODE));
        vmSerial.setItems(viewModel.items);
        viewBind.setVmSerial(vmSerial);

    }*/

    //<editor-fold desc="processEvent">
    private void processEvent(Message message) {
        switch (message.what) {

            // case Const.Message.MSG_DIALOG:
            //     showDialogFragment(new DialogTips("暂存数据导入", "当前页面存在暂存但未提交的数据，是否载入？", "丢弃", "加载",
            //             () -> viewModel.restoreRecords(),
            //             () -> viewModel.discardRecords()));
            //     break;
            //
            // case Const.Message.MSG_BATCH_VERIFY:
            //     String[] obj = (String[]) message.obj;
            //     showDialogFragment(new DialogTips("校验提示", obj[0], "删除", "录入",
            //             () -> viewModel.addData(), () -> {}));
            //     break;
            //
            // case Const.Message.MSG_BATCH_CONFIRM:
            //     showDialogFragment(new DialogTips("校验提示", (String) message.obj, this::finishResult));
            //     break;
            //
            // case Const.Message.MSG_FINISH_RESULT:
            //     finishResult();
            //     break;
            //
            // case Const.Message.MSG_BATCH_REPEAT:
            //     showDialogFragment(new DialogTips("校验提示", "该批次号已录入", () -> {}));
            //     break;
            //
            // case Const.Message.MSG_BATCH_SAVE:
            //     showDialogFragment(new DialogTips("提示", "扫描数据是否需要暂存？", () -> viewModel.saveAll(), () -> viewModel.finish()));
            //     break;

            default:
                break;
        }
    }
    //</editor-fold>

    private void finishResult() {
        // Intent data = new Intent();
        // data.putParcelableArrayListExtra(IntentKey.DATA, viewModel.codeList);
        // setResult(RESULT_OK, data);
        // finish();
    }

    // @Override
    // public void handleMessage(Message msg) {
    //     if (msg.what == MSG_ADD_TAG) {
    //         viewModel.isFocus.set(false);
    //         viewModel.addTag((String) msg.obj);
    //         viewBind.wiiInput.setInputText("");
    //     }
    // }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        viewModel.sendSingleLiveEvent(Const.Message.MSG_BATCH_SAVE);
    }
}
