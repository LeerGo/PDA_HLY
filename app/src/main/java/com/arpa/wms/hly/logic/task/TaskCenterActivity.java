package com.arpa.wms.hly.logic.task;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.widget.LinearLayout;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseActivity;
import com.arpa.wms.hly.ui.adapter.ViewPagerAdapter;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class TaskCenterActivity extends BaseActivity {
    private final String[] title = {"待指派", "已指派"};

    private TabLayout tlTab;
    private ViewPager2 vpContainer;

    private TabLayoutMediator tabLayoutMediator;
    private Fragment[] fragments;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_task_center;
    }

    @Override
    protected void initData() {
        fragments = new Fragment[title.length];
        fragments[0] = TaskAssignFragment.newInstance(0);
        fragments[1] = TaskAssignFragment.newInstance(1);
    }

    @Override
    protected void initViews() {
        tlTab = findViewById(R.id.tl_tab);
        vpContainer = findViewById(R.id.vp_container);
    }

    @Override
    protected void setViews() {
        ViewPagerAdapter myAdapter = new ViewPagerAdapter(this, fragments);
        vpContainer.setAdapter(myAdapter);
        vpContainer.setOffscreenPageLimit(1);

        LinearLayout linearLayout = (LinearLayout) tlTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.divider_line_vertical));
        linearLayout.setDividerPadding(30);

        tabLayoutMediator = new TabLayoutMediator(tlTab, vpContainer, (tab, position) -> tab.setText(title[position]));
        tabLayoutMediator.attach();
    }

    @Override
    protected void onDestroy() {
        tabLayoutMediator.detach();
        super.onDestroy();
    }
}