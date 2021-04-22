package com.arpa.wms.hly.logic.mine;

import com.arpa.wms.hly.BuildConfig;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseActivity;
import com.arpa.wms.hly.ui.widget.WidgetMineMenu;

import butterknife.BindView;

public class MineActivity extends BaseActivity {
    @BindView(R.id.wmm_account)
    WidgetMineMenu wmmAccount;
    @BindView(R.id.wmm_warehouse)
    WidgetMineMenu wmmWarehouse;
    @BindView(R.id.wmm_version)
    WidgetMineMenu wmmVersion;

    @Override
    protected void setViews() {
        wmmVersion.setDesc("v" + BuildConfig.VERSION_NAME);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_mine;
    }
}