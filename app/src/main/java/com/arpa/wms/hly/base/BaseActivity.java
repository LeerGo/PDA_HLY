package com.arpa.wms.hly.base;

import android.os.Bundle;
import android.os.Message;

import com.arpa.wms.hly.utils.WeakHandler;

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
public abstract class BaseActivity extends AppCompatActivity implements WeakHandler.MessageListener {
    protected static WeakHandler<BaseActivity> sHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);

        initData();
        initViews();
        setViews();
    }

    protected abstract int getLayoutID();

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void setViews();

    @Override
    protected void onDestroy() {
        if (null != sHandler) {
            sHandler.clear();
        }
        super.onDestroy();
    }

    @Override
    public void handleMessage(Message msg) {
    }
}
