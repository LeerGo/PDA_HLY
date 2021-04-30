package com.arpa.wms.hly.ui.decoration;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.utils.Utils;

import androidx.annotation.DrawableRes;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-30 09:43
 *
 * <p>
 * RecyclerView 分割线工具
 * </p>
 */
public class ItemDecorationUtil {
    private DividerItemDecoration itemDecoration;

    public ItemDecorationUtil() {
        itemDecoration = new DividerItemDecoration(Utils.getContext(), DividerItemDecoration.VERTICAL);
    }

    /**
     * 内部类方式获取单例
     */
    public static ItemDecorationUtil getInstance() {
        return ItemDecorationUtil.SingleHolder.ins;
    }

    public static DividerItemDecoration getDividerBottom10DP() {
        return getDividerBottom(R.drawable.divider_line_vertical_10dp);
    }

    public static DividerItemDecoration getDividerBottom(@DrawableRes int resID) {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(Utils.getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(Utils.getContext().getResources().getDrawable(resID));
        return itemDecoration;
    }

    public static DividerItemDecoration getDividerTop10D10DP() {
        return getDividerTop(R.drawable.divider_line_vertical_10dp);
    }

    public static DividerItemDecoration getDividerTop(@DrawableRes int resID) {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(Utils.getContext(), DividerItemDecoration.SHOW_DIVIDER_BEGINNING, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(Utils.getContext().getResources().getDrawable(resID));
        return itemDecoration;
    }

    private static class SingleHolder {
        private static final ItemDecorationUtil ins = new ItemDecorationUtil();
    }
}
