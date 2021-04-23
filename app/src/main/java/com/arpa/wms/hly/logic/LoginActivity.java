package com.arpa.wms.hly.logic;

import android.content.Intent;
import android.view.View;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.aop.SingleClick;
import com.arpa.wms.hly.base.BaseActivity;
import com.arpa.wms.hly.logic.home.HomeActivity;

import butterknife.OnClick;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 2:04 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void setViews() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @SingleClick
    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}
