package com.arpa.wms.hly.logic.home;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.MenuBean;
import com.arpa.wms.hly.logic.mine.MineActivity;
import com.arpa.wms.hly.utils.Const.HOME_MENU;
import com.arpa.wms.hly.utils.Const.SPKEY;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import dagger.hilt.android.lifecycle.HiltViewModel;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:20 PM
 *
 * <p>
 * ViewModel：首页
 * </p>
 */
@HiltViewModel
public class VMHome extends WrapDataViewModel {
    private final ObservableField<String> account = new ObservableField<>();
    private final ObservableField<String> warehouse = new ObservableField<>();

    private final BindingRecyclerViewAdapter<MenuBean> adapter = new BindingRecyclerViewAdapter<>();
    private final ObservableArrayList<MenuBean> items = new ObservableArrayList<>();
    private final ItemBinding<MenuBean> itemBinding = ItemBinding.of(BR.data, R.layout.item_home_menu);

    @Inject
    public VMHome(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createMenu();
    }

    @Override
    public void onResume() {
        super.onResume();
        initHeader();
    }

    /**
     * 顶部信息
     */
    private void initHeader() {
        account.set("账号：" + spGetString(SPKEY.USER_NAME));
        warehouse.set("仓库：" + spGetString(SPKEY.WAREHOUSE_NAME));
    }

    /**
     * 创建菜单
     */
    private void createMenu() {
        items.add(new MenuBean(R.mipmap.ic_home_task_center, "任务中心", "Task Center", HOME_MENU.TASK_CENTER));
        items.add(new MenuBean(R.mipmap.ic_home_goods_take, "收货", "Receiving Goods", HOME_MENU.GOODS_TAKE));
        items.add(new MenuBean(R.mipmap.ic_home_goods_pick, "拣货", "Picking Goods", HOME_MENU.GOODS_PICK));
        items.add(new MenuBean(R.mipmap.ic_home_goods_recheck, "复核", "To Review", HOME_MENU.GOODS_RECHECK));
        items.add(new MenuBean(R.mipmap.ic_home_truck_load, "装车", "Loading", HOME_MENU.TRUCK_LOAD));
        items.add(new MenuBean(R.mipmap.ic_home_inventory_move, "移位", "Displacement", HOME_MENU.INVENTORY_MOVE));
        items.add(new MenuBean(R.mipmap.ic_home_inventory_query, "库存查询", "Inventory Query", HOME_MENU.INVENTORY_QUERY));
    }

    public void jumpMine() {
        startActivity(MineActivity.class);
    }


    public ObservableField<String> getAccount() {
        return account;
    }

    public ObservableField<String> getWarehouse() {
        return warehouse;
    }

    public ObservableArrayList<MenuBean> getItems() {
        return items;
    }

    public ItemBinding<MenuBean> getItemBinding() {
        return itemBinding;
    }

    public BindingRecyclerViewAdapter<MenuBean> getAdapter() {
        return adapter;
    }
}
