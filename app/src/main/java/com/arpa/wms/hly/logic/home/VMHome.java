package com.arpa.wms.hly.logic.home;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.MenuBean;
import com.arpa.wms.hly.logic.mine.MineActivity;
import com.arpa.wms.hly.utils.Const.HOME_MENU;
import com.arpa.wms.hly.utils.Const.SPKEY;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:20 PM
 *
 * <p>
 * ViewModel：首页
 * </p>
 */
// TODO: 参照 MVVMHabit 的写法，使用 ViewAdapter+BindingCollectionAdapter 实现自动绑定 @lyf 2021-04-23 04:25:21
public class VMHome extends WrapDataViewModel {
    private final MutableLiveData<List<MenuBean>> menuLiveData = new MutableLiveData<>();
    private final ObservableField<String> account = new ObservableField<>();
    private final ObservableField<String> warehouse = new ObservableField<>();

    @ViewModelInject
    public VMHome(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initHeader();
        createMenu();
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
        // TODO: 首页的菜单是写死根据前端根据权限判断，还是直接后端下发，待定 与 @阎庆玉 后期沟通 @lyf 2021-04-22 10:27:14
        List<MenuBean> menuList = new ArrayList<>();
        menuList.add(new MenuBean(R.mipmap.ic_goods_take, "任务中心", HOME_MENU.TASK_CENTER));
        menuList.add(new MenuBean(R.mipmap.ic_goods_take, "收货", HOME_MENU.GOODS_TAKE));
        menuList.add(new MenuBean(R.mipmap.ic_goods_recheck, "复核", HOME_MENU.GOODS_RECHECK));
        menuList.add(new MenuBean(R.mipmap.ic_truck_load, "装车", HOME_MENU.TRUCK_LOAD));
        menuList.add(new MenuBean(R.mipmap.ic_inventory_move, "移位", HOME_MENU.INVENTORY_MOVE));
        menuList.add(new MenuBean(R.mipmap.ic_inventory_query, "库存查询", HOME_MENU.INVENTORY_QUERY));
        menuLiveData.postValue(menuList);
    }

    public void jumpMine() {
        startActivity(MineActivity.class);
    }

    public MutableLiveData<List<MenuBean>> getMenuLiveData() {
        return menuLiveData;
    }

    public ObservableField<String> getAccount() {
        return account;
    }

    public ObservableField<String> getWarehouse() {
        return warehouse;
    }
}
