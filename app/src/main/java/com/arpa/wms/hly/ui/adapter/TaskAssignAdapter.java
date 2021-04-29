package com.arpa.wms.hly.ui.adapter;

import android.content.Context;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseAdapter;
import com.arpa.wms.hly.base.BaseViewHolder;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 11:14 AM
 *
 * <p>
 * Adapter：任务中心列表
 * </p>
 */
public class TaskAssignAdapter <TaskAssignBean> extends BaseAdapter<TaskAssignBean> {

    public TaskAssignAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        //        return super.getItemCount();
        return 20;
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_task_list;
    }
}
