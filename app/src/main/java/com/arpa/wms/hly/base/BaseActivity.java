package com.arpa.wms.hly.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 3:50 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);

        initData();
        initViews();
        setViews();
    }

    protected abstract void setViews();

    protected abstract void initViews();

    protected abstract void initData();

    protected abstract int getLayoutID();
}
