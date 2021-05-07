package com.arpa.wms.hly.ui.binding;

import android.text.TextUtils;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.ui.widget.WidgetSearchBar;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 15:59
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class LogicViewBinding {
    @BindingAdapter(value = "taskStatus")
    public static void setTaskStatus(AppCompatImageView imageView, String taskStatus) {
        if (!TextUtils.isEmpty(taskStatus))
            switch (taskStatus) {
                case "采购":
                    imageView.setImageResource(R.mipmap.ic_goods_status_buy);
                    break;
                case "退货":
                    imageView.setImageResource(R.mipmap.ic_goods_status_return);
                    break;
                case "调拨":
                    imageView.setImageResource(R.mipmap.ic_goods_status_trans);
                    break;
            }
    }

    @BindingAdapter(value = "onSearch")
    public static void setOnSearch(WidgetSearchBar widgetSearchBar, ViewListener.DataClickListener<String> listener) {
        if (null != listener) {
            widgetSearchBar.setOnSearchClick(data -> listener.transfer(data));
        }
    }
}
