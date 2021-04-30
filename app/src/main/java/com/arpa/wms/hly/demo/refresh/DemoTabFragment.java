package com.arpa.wms.hly.demo.refresh;

import android.os.Bundle;

import com.arpa.and.wms.arch.base.BaseLazyFragment;
import com.arpa.wms.hly.BR;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.FragmentDemoTabBinding;
import com.arpa.wms.hly.logic.home.goods.take.VMGoodsTake;
import com.arpa.wms.hly.ui.decoration.ItemDecorationUtil;
import com.arpa.wms.hly.utils.Const.IntentKey;

import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-30 10:56
 *
 * <p>
 * 内容描述区域
 * </p>
 */
@AndroidEntryPoint
public class DemoTabFragment extends BaseLazyFragment<VMGoodsTake, FragmentDemoTabBinding> {

    public static DemoTabFragment newInstance(String text) {
        DemoTabFragment fragment = new DemoTabFragment();
        Bundle args = new Bundle();
        args.putString(IntentKey.INDEX, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_demo_tab;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setVariable(BR.viewModel, viewModel);
        viewBind.rvList.addItemDecoration(ItemDecorationUtil.getDividerTop10D10DP());
    }
}
