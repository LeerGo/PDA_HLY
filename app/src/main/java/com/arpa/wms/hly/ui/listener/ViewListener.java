package com.arpa.wms.hly.ui.listener;

import android.view.View;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-28 17:17
 *
 * <p>
 * 回调：View 的各种监听器
 * </p>
 */
public interface ViewListener {

    interface DataTransCallback <T> {
        void transfer(T data);
    }

    interface OnItemClickListener <T> {
        void onItemClick(View view, int position, T data);
    }
}
