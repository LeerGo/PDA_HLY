package com.arpa.wms.hly.logic.home;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseActivity;
import com.arpa.wms.hly.bean.MenuBean;
import com.arpa.wms.hly.logic.mine.MineActivity;
import com.arpa.wms.hly.ui.adapter.HomeMenuAdapter;
import com.arpa.wms.hly.ui.decoration.GridItemDecoration;
import com.arpa.wms.hly.utils.Const.HOME_MENU;
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
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        menuList = new ArrayList<>();
        // TODO: 首页的菜单是写死根据前端根据权限判断，还是直接后端下发，待定 与 @阎庆玉 后期沟通 @lyf 2021-04-22 10:27:14
        menuList.add(new MenuBean(R.mipmap.ic_goods_take, "任务中心", HOME_MENU.TASK_CENTER));
        menuList.add(new MenuBean(R.mipmap.ic_goods_take, "收货"));
        menuList.add(new MenuBean(R.mipmap.ic_goods_recheck, "复核"));
        menuList.add(new MenuBean(R.mipmap.ic_truck_load, "装车"));
        menuList.add(new MenuBean(R.mipmap.ic_inventory_move, "移位", HOME_MENU.INVENTORY_MOVE));
        menuList.add(new MenuBean(R.mipmap.ic_inventory_query, "库存查询"));
    }

    @Override
    protected void initViews() {
        adapter = new HomeMenuAdapter(this);
        adapter.addAll(menuList);

        rvMenu.addItemDecoration(new GridItemDecoration(DensityUtils.dip2px(this, 10)));
        rvMenu.setAdapter(adapter);
    }

    @Override
    protected void setViews() {
        adapter.setOnItemClickListener((view, position, data) -> {
            if (!TextUtils.isEmpty(data.getPath())) {
                Intent intent = new Intent();
                intent.setAction(data.getPath());
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.rl_mine})
    public void onClick(View view) {
        if (view.getId() == R.id.rl_mine) {
            startActivity(new Intent(this, MineActivity.class));
        }
    }
}