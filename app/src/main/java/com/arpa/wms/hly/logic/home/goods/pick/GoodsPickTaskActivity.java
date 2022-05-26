package com.arpa.wms.hly.logic.home.goods.pick;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.PickingItemVO;
import com.arpa.wms.hly.databinding.ActivityGoodsPickTaskBinding;
import com.arpa.wms.hly.ui.dialog.DialogPickEdit;
import com.arpa.wms.hly.ui.form.TFormScrollHelper;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

import java.util.List;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-07-12 17:45
 *
 * <p>
 * 页面：拣货
 * </p>
 */
@AndroidEntryPoint
public class GoodsPickTaskActivity extends WrapBaseActivity<VMGoodsPickTask, ActivityGoodsPickTaskBinding> implements ViewListener.DataTransCallback<List<PickingItemVO>> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_pick_task;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
        registerSingleLiveEvent(message -> {
            if (message.what == Const.Message.MSG_DIALOG) {
                showDialogFragment(new DialogPickEdit(this));
            }
        });

        TFormScrollHelper scrollHelper = new TFormScrollHelper();
        scrollHelper.connectScrollView(viewBind.osvTitle);
        scrollHelper.connectScrollView(viewBind.osvContent);
        scrollHelper.connectRecyclerView(viewBind.rvContent, TFormScrollHelper.LEFT_AREA);
        scrollHelper.connectRecyclerView(viewBind.rvOperate, TFormScrollHelper.RIGHT_AREA);
    }

    @Override
    public void transfer(List<PickingItemVO> data) {
        viewModel.pickEdit(data);
    }
}
