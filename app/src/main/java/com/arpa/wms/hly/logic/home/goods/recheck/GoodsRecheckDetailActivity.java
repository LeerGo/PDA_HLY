package com.arpa.wms.hly.logic.home.goods.recheck;

import android.os.Bundle;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.databinding.ActivityPdataskRecheckDetailBinding;
import com.arpa.wms.hly.logic.home.goods.recheck.vm.VMGoodsRecheckDetail;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.Const.IntentKey;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;
import dagger.hilt.android.AndroidEntryPoint;

import static com.arpa.wms.hly.utils.Const.TASK_STATUS.TAKE_WAIT;
import static com.arpa.wms.hly.utils.Const.TASK_STATUS.TAKE_YET;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 2:01 PM
 *
 * <p>
 * 页面：商品待复核详情
 * </p>
 */
@AndroidEntryPoint
public class GoodsRecheckDetailActivity extends WrapBaseActivity<VMGoodsRecheckDetail, ActivityPdataskRecheckDetailBinding> {
    private final ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            viewModel.searchInfo.setStatus(position == 0 ? TAKE_WAIT : TAKE_YET);
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_pdatask_recheck_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setViewModel(viewModel);
        viewBind.viewpager.registerOnPageChangeCallback(pageChangeCallback);
        ResTaskAssign data = getIntent().getParcelableExtra(IntentKey.DATA);
        viewModel.headerData.setValue(data);
        viewModel.fragments.add(GoodsRecheckDetailFragment.newInstance(Const.TASK_STATUS.RECHECK_WAIT, data.getCode()));
        viewModel.fragments.add(GoodsRecheckDetailFragment.newInstance(Const.TASK_STATUS.RECHECK_YET, data.getCode()));
        viewBind.wsbSearch.setOnSearchClick(keyWord -> {
            viewModel.searchInfo.setKeyWord(keyWord);
            viewModel.sendSearchAction();
        });
        viewModel.headerData.observe(this, resTaskAssign -> viewBind.incHeader.setData(resTaskAssign));
    }

    @Override
    protected void onDestroy() {
        viewBind.viewpager.unregisterOnPageChangeCallback(pageChangeCallback);
        super.onDestroy();
    }
}
