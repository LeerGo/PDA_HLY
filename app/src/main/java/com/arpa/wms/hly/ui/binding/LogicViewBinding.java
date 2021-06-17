package com.arpa.wms.hly.ui.binding;

import android.text.TextUtils;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.ui.widget.WidgetSearchBar;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_PICK;
import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_RECHECK;
import static com.arpa.wms.hly.utils.Const.ASSIGN_WORK.GOODS_TAKE;

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
