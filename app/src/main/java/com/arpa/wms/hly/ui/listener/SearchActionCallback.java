package com.arpa.wms.hly.ui.listener;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
import com.arpa.wms.hly.utils.KeyboardUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-11 10:15
 *
 * <p>
 * 键盘点击搜索的时候需要的行为
 * </p>
 */
public class SearchActionCallback implements TextView.OnEditorActionListener {
    private final DataTransCallback<String> onSearchListener;

    public SearchActionCallback(ViewListener.DataTransCallback<String> onSearchListener) {
        this.onSearchListener = onSearchListener;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            KeyboardUtils.hideSoftInput(v);
            String data = v.getText().toString().trim();
            if (!TextUtils.isEmpty(data)) onSearchListener.transfer(data);
        }
        return false;
    }
}
