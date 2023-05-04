package com.arpa.wms.hly.ui.binding;

import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.ui.widget.WidgetPropsItem;
import com.arpa.wms.hly.ui.widget.WidgetSearchBar;
import com.arpa.wms.hly.utils.NumberUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.math.BigDecimal;

import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_LOADING;
import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_PICK;
import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_RECHECK;
import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_TAKE;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 15:59
 */
public class LogicViewBinding {
    @BindingAdapter(value = "taskType")
    public static void setTaskType(AppCompatImageView imageView, String taskType) {
        if (!TextUtils.isEmpty(taskType))
            switch (taskType) {
                case GOODS_TAKE:
                    imageView.setImageResource(R.mipmap.ic_task_goods_take);
                    break;
                case GOODS_RECHECK:
                    imageView.setImageResource(R.mipmap.ic_task_goods_recheck);
                    break;
                case GOODS_PICK:
                    imageView.setImageResource(R.mipmap.ic_task_goods_pick);
                    break;
                case GOODS_LOADING:
                    imageView.setImageResource(R.mipmap.ic_task_goods_load);
                    break;
            }
    }

    @BindingAdapter(value = "onSearch")
    public static void setOnSearch(WidgetSearchBar widgetSearchBar, ViewListener.DataTransCallback<String> listener) {
        if (null != listener) {
            widgetSearchBar.setOnSearchClick(listener::transfer);
        }
    }

    @BindingAdapter(value = "decimalValue")
    public static void setDecimalValue(WidgetPropsItem widget, BigDecimal value) {
        widget.setPropsValue(NumberUtils.parseDecimal(value));
    }

    @BindingAdapter(value = "srlEnableRefresh")
    public static void bindSmartRefreshLayoutEnable(SmartRefreshLayout refreshLayout, Boolean isEnableRefresh) {
        if (null != isEnableRefresh) {
            refreshLayout.setEnableRefresh(isEnableRefresh);
        }
    }
}
