package com.arpa.wms.hly.logic.home.goods.take;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.databinding.ActivityPdataskTakeDetailBinding;
import com.arpa.wms.hly.logic.home.goods.take.vm.VMGoodsTakeDetail;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.Const.TASK_STATUS;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品收货详情
 * </p>
 */
// TODO: 搜索功能需要完成 @lyf 2021-05-19 10:47:41
@AndroidEntryPoint
public class GoodsTakeDetailActivity extends WrapBaseActivity<VMGoodsTakeDetail, ActivityPdataskTakeDetailBinding> {
    private final ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
//            viewModel.data.postValue(viewBind.wsbSearch.geT);
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_pdatask_take_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBind.setViewModel(viewModel);
        viewBind.viewpager.registerOnPageChangeCallback(pageChangeCallback);
        ResTaskAssign data = getIntent().getParcelableExtra(Const.IntentKey.DATA);
        viewModel.headerData.set(data);
        viewModel.fragments.add(GoodsTakeDetailFragment.newInstance(TASK_STATUS.TAKE_WAIT, data.getCode()));
        viewModel.fragments.add(GoodsTakeDetailFragment.newInstance(TASK_STATUS.TAKE_YET, data.getCode()));
        viewBind.wsbSearch.setOnSearchClick(keyWord -> viewModel.data.postValue(keyWord));

    }

    @Override
    protected void onDestroy() {
        viewBind.viewpager.unregisterOnPageChangeCallback(pageChangeCallback);
        super.onDestroy();
    }
}
