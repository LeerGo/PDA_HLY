package com.arpa.wms.hly.logic.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseActivity;
import com.arpa.wms.hly.bean.MenuBean;
import com.arpa.wms.hly.logic.mine.MineActivity;
import com.arpa.wms.hly.ui.decoration.GridItemDecoration;
import com.arpa.wms.hly.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_warehouse)
    TextView tvWarehouse;

    private List<MenuBean> menuList;
    private HomeMenuAdapter adapter;

    @Override
    protected void setViews() {
        adapter.setOnItemClickListener((view, position, data) -> {
            switch (position) {
                case 0:// 收货
                    break;
                case 1:// 复核
                    break;
                case 2:// 装车
                    break;
                case 3:// 移位
                    break;
                case 4://库存查询
                    break;
            }
        });
    }

    @Override
    protected void initViews() {
        adapter = new HomeMenuAdapter(this);
        adapter.addAll(menuList);
        rvMenu.addItemDecoration(new GridItemDecoration(DensityUtils.dip2px(this, 10)));
        rvMenu.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        menuList = new ArrayList<>();
        menuList.add(new MenuBean(R.mipmap.ic_goods_take, "任务中心"));
        menuList.add(new MenuBean(R.mipmap.ic_goods_take, "收货"));
        menuList.add(new MenuBean(R.mipmap.ic_goods_recheck, "复核"));
        menuList.add(new MenuBean(R.mipmap.ic_truck_load, "装车"));
        menuList.add(new MenuBean(R.mipmap.ic_inventory_move, "移位"));
        menuList.add(new MenuBean(R.mipmap.ic_inventory_query, "库存查询"));
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    @OnClick({R.id.rl_mine})
    public void onClick(View view) {
        if (view.getId() == R.id.rl_mine) {
            startActivity(new Intent(this, MineActivity.class));
        }
    }
}