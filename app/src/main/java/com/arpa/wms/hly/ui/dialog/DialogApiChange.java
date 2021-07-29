package com.arpa.wms.hly.ui.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseBottomDialogFragment;
import com.arpa.wms.hly.net.ApiService.API;
import com.arpa.wms.hly.utils.SPUtils;
import com.arpa.wms.hly.utils.ToastUtils;
import com.king.retrofit.retrofithelper.RetrofitHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import static com.arpa.wms.hly.utils.Const.SPKEY.TEST_SERVER;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:58 PM
 *
 * <p>
 * Dialog: 更换 IP
 * </p>
 */
public class DialogApiChange extends BaseBottomDialogFragment {
    private final View.OnClickListener onIPChange;

    public DialogApiChange() {
        this(null);
    }

    public DialogApiChange(View.OnClickListener onIPChange) {
        this.onIPChange = onIPChange;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_api_change;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setOptArea();
    }


    @SuppressLint("SetTextI18n")
    private void setOptArea() {
        AppCompatButton btnCancel = (AppCompatButton) findViewById(R.id.apt_cancel);
        AppCompatButton btnSure = (AppCompatButton) findViewById(R.id.apt_sure);
        EditText etIP = (EditText) findViewById(R.id.et_ip);
        AppCompatTextView tvIP = (AppCompatTextView) findViewById(R.id.tv_current);
        String cip = SPUtils.getInstance().getString(TEST_SERVER);
        tvIP.setText("当前环境：" + (TextUtils.isEmpty(cip) ? API.URL_WMS : cip));

        btnCancel.setOnClickListener(v -> dismiss());
        btnSure.setOnClickListener(v -> {
            String ip = "http://" + etIP.getText().toString();
            if (validIP(ip)) {
                RetrofitHelper.getInstance().putDomain(API.KEY_WMS, ip);
                SPUtils.getInstance().put(TEST_SERVER, ip);
                ToastUtils.showShortSafe("新 IP 已即时生效，请进行后续操作");
                if (null != onIPChange) onIPChange.onClick(v);
                dismiss();
            } else {
                ToastUtils.showShortSafe("输入的IP不合法");
            }
        });
    }

    private boolean validIP(String ip) {
        String pattern = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(ip);
        return m.find();
    }
}