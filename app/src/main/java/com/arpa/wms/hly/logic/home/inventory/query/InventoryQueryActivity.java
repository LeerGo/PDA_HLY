package com.arpa.wms.hly.logic.home.inventory.query;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityInventoryQueryBinding;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 界面：库存查询
 * </p>
 */
@AndroidEntryPoint
public class InventoryQueryActivity extends BaseActivity<VMInventoryQuery, ActivityInventoryQueryBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_inventory_query;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    //    @Override
    //    public void handleMessage(Message msg) {

    //        etScan.addTextChangedListener(new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence s, int start, int before, int count) {
    //
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable s) {
    //                Message message = new Message();
    //                message.what = MSG_SCAN;
    //                message.obj = s.toString();
    //                if (sHandler.hasMessages(MSG_SCAN)) sHandler.removeMessages(MSG_SCAN);
    //                sHandler.sendMessageDelayed(message, Const.HANDLER_DELAY_TIME);
    //            }
    //        });

    //        super.handleMessage(msg);
    //        if (msg.what == MSG_SCAN) {
    //            requestData((String) msg.obj);
    //        }
    //    }
}
