package com.arpa.wms.hly.logic.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseAdapter;
import com.arpa.wms.hly.base.BaseViewHolder;
import com.arpa.wms.hly.bean.MenuBean;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 4:20 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class HomeMenuAdapter extends BaseAdapter<MenuBean> {

    public HomeMenuAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, int position) {
        TextView tvMenu;
        ImageView ivIcon;
        tvMenu = holder.getView(R.id.tv_title);
        ivIcon = holder.getView(R.id.iv_icon);
        MenuBean data = getDataList().get(position);
        ivIcon.setImageResource(data.getImageRes());
        tvMenu.setText(data.getText());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_menu;
    }
}
