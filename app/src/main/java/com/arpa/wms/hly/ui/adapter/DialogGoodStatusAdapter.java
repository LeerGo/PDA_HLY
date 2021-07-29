package com.arpa.wms.hly.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseAdapter;
import com.arpa.wms.hly.base.BaseViewHolder;
import com.arpa.wms.hly.bean.InventoryStatus;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 4:18 PM
 *
 * <p>
 * Adapter：分配保管员、装卸工、叉车工等
 * </p>
 */
public class DialogGoodStatusAdapter extends BaseAdapter<InventoryStatus> {
    private int currentIndex;

    public DialogGoodStatusAdapter(Context context, int currentIndex) {
        super(context);
        this.currentIndex = currentIndex;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindItemHolder(BaseViewHolder holder, int position) {
        ImageView ivSelect = holder.getView(R.id.iv_check);
        TextView tvWarehouse = holder.getView(R.id.tv_name);

        InventoryStatus data = getDataList().get(position);
        ivSelect.setVisibility(position == currentIndex ? View.VISIBLE : View.GONE);
        tvWarehouse.setText(data.getName());
        holder.itemView.setOnClickListener(v -> {
            currentIndex = position;
            onItemClickListener.onItemClick(v, position, data);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dialog_select;
    }
}
