package com.arpa.wms.hly.logic.home.inventory.move;

import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseActivity;
import com.arpa.wms.hly.ui.widget.WidgetTitleBar;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.WeakHandler;

import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 3:30 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public abstract class InventoryScanActivity extends BaseActivity {
    private static final int MSG_SCAN = 0x1;

    @BindView(R.id.wtb_title)
    WidgetTitleBar wtbTitle;
    @BindView(R.id.et_scan)
    AppCompatEditText etScan;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_inventory_scan;
    }

    @Override
    protected void initData() {
        sHandler = new WeakHandler<>(this);
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void setViews() {
        wtbTitle.setTitle(getTitleBar());
        etScan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Message message = new Message();
                message.what = MSG_SCAN;
                message.obj = s.toString();
                if (sHandler.hasMessages(MSG_SCAN)) sHandler.removeMessages(MSG_SCAN);
                sHandler.sendMessageDelayed(message, Const.HANDLER_DELAY_TIME);
            }
        });
    }

    protected abstract String getTitleBar();

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what == MSG_SCAN) {
            requestData((String) msg.obj);
        }
    }

    protected abstract void requestData(String scanCode);
}
