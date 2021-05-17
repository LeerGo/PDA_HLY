package com.arpa.wms.hly.ui.binding;

import android.text.TextUtils;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.ui.widget.WidgetSearchBar;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
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
    // TODO: 待删除 @lyf 2021-05-14 09:39:23
    /*@BindingAdapter(value = "taskStatus")
    @Deprecated
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
    }*/
    @BindingAdapter(value = "taskType")
    @Deprecated
    public static void setTaskType(AppCompatImageView imageView, String taskType) {
        if (!TextUtils.isEmpty(taskType))
            switch (taskType) {
                case "收货":
                    imageView.setImageResource(R.mipmap.ic_task_goods_take);
                    break;
                case "复核":
                    imageView.setImageResource(R.mipmap.ic_task_goods_recheck);
                    break;
                case "拣货":
                    imageView.setImageResource(R.mipmap.ic_task_goods_pick);
                    break;
            }
    }

    @BindingAdapter(value = "truckStatus")
    public static void setTruckStatus(AppCompatTextView textView, String truckStatus) {
        if (!TextUtils.isEmpty(truckStatus))
            switch (truckStatus) {
                case "待装车":
                    textView.setBackgroundResource(R.drawable.shape_truck_status_1);
                    break;
                case "已装车":
                    textView.setBackgroundResource(R.drawable.shape_truck_status_2);
                    break;
                case "已取消":
                    textView.setBackgroundResource(R.drawable.shape_truck_status_3);
                    break;
                case "已完成":
                    textView.setBackgroundResource(R.drawable.shape_truck_status_4);
                    break;
                default:
                    break;
            }
    }


    // TODO: 替换现有的 onSearchClick @lyf 2021-05-12 09:15:15
    @BindingAdapter(value = "onSearch")
    public static void setOnSearch(WidgetSearchBar widgetSearchBar, ViewListener.DataTransCallback<String> listener) {
        if (null != listener) {
            widgetSearchBar.setOnSearchClick(data -> listener.transfer(data));
        }
    }
}
