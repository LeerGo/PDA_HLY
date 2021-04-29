package com.arpa.wms.hly.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 11:14 AM
 *
 * <p>
 * Adapter-Viewpager：任务中心
 * </p>
 */
public class ViewPagerAdapter extends FragmentStateAdapter {
    private Fragment[] fragments;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Fragment[] fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
