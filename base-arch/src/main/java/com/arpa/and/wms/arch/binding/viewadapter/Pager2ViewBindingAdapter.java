package com.arpa.and.wms.arch.binding.viewadapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-26 9:25 AM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class Pager2ViewBindingAdapter {

    @BindingAdapter("pageLimit")
    public static void setPageLimit(ViewPager2 viewPager, Integer pageLimit) {
        if (null != pageLimit)
            viewPager.setOffscreenPageLimit(pageLimit);
    }

    @BindingAdapter({"fragments", "tab", "titles"})
    public static void setFragments(ViewPager2 viewPager, final List<? extends Fragment> fragments, TabLayout tab, List<String> titles) {
        if (null != fragments) {
            viewPager.setAdapter(new FragmentStateAdapter((FragmentActivity) viewPager.getContext()) {
                @NonNull
                @Override
                public Fragment createFragment(int position) {
                    return fragments.get(position);
                }

                @Override
                public int getItemCount() {
                    return fragments.size();
                }
            });
            new TabLayoutMediator(tab, viewPager, (v, position) -> v.setText(titles.get(position))).attach();
        }
    }
}
