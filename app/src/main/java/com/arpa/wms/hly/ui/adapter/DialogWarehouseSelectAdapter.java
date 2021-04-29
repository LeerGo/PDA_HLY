package com.arpa.wms.hly.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseAdapter;
import com.arpa.wms.hly.base.BaseViewHolder;
import com.arpa.wms.hly.bean.res.ResWarehouse;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 4:18 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class DialogWarehouseSelectAdapter extends BaseAdapter<ResWarehouse> {
    private int curSelect = -1;

    public DialogWarehouseSelectAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, int position) {
        ImageView ivSelect = holder.getView(R.id.iv_select);
        TextView tvWarehouse = holder.getView(R.id.tv_warehouse);

        ResWarehouse data = getDataList().get(position);
        ivSelect.setSelected(curSelect == position);
        tvWarehouse.setText(data.getName());
        // TODO: 存下来 warehouseCode @lyf 2021-04-25 04:56:34
        // MyPreferenceManager.commitString("CANGKU", List.get(position).getCode());
        holder.itemView.setOnClickListener(v -> {
            curSelect = position;
            notifyDataSetChanged();
            onItemClickListener.onItemClick(v, position, data);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_warehouse_select;
    }
}
