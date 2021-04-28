package com.arpa.wms.hly.logic.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.arpa.and.wms.arch.base.BaseActivity;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.ActivityHomeBinding;
import com.arpa.wms.hly.ui.adapter.HomeMenuAdapter;
import com.arpa.wms.hly.ui.decoration.GridItemDecoration;
import com.arpa.wms.hly.utils.DensityUtils;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 03:03<br/>
 *
 * <p>
 * 页面：首页
 * </p>
 */
@AndroidEntryPoint
public class HomeActivity extends BaseActivity<VMHome, ActivityHomeBinding> {
    private HomeMenuAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.vmHome, viewModel);

        viewModel.getMenuLiveData().observe(this, menuBeans -> {
            // TODO: 这里的 adapter 可以考虑为 mvvm 方式，这里可以用先不动了就 @lyf 2021-04-27 03:21:44
            adapter = new HomeMenuAdapter(this);
            adapter.addAll(menuBeans);
            adapter.setOnItemClickListener((view, position, data) -> {
                if (!TextUtils.isEmpty(data.getPath())) {
                    Intent intent = new Intent();
                    intent.setAction(data.getPath());
                    startActivity(intent);
                }
            });
            viewBind.rvMenu.addItemDecoration(new GridItemDecoration(DensityUtils.dip2px(10)));
            viewBind.rvMenu.setAdapter(adapter);
        });
    }
}