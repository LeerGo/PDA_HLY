package com.arpa.wms.hly.demo.refresh;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityDemoTabBinding;
import com.arpa.wms.hly.utils.Const;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-30 10:26
 *
 * <p>
 * 内容描述区域
 * </p>
 */
@Deprecated
@AndroidEntryPoint
public class DemoTabActivity extends BaseActivity<VMDemoTab, ActivityDemoTabBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_demo_tab;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.viewModel, viewModel);
        viewModel.setData(getIntent().getParcelableExtra(Const.IntentKey.DATA));
    }
}
