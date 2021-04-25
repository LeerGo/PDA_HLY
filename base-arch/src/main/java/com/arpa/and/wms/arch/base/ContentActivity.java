package com.arpa.and.wms.arch.base;

import android.content.Intent;
import android.os.Bundle;

import com.arpa.and.wms.arch.R;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * 用于容纳Fragment的通用Activity，相当于一个Fragment容器，通过{@link Intent} 传递参数和标识，然后实现{@link #switchFragment}处理对应的逻辑
 */
public abstract class ContentActivity extends BaseActivity<DataViewModel, ViewDataBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        switchFragment(getIntent());
    }

    /**
     * 切换Fragment
     *
     * @param intent
     *         通过{@link Intent} 传递参数和标记来判断对应展示某个{@link Fragment}
     */
    protected abstract void switchFragment(Intent intent);

    /**
     * 不使用DataBinding {@link DataBindingUtil}
     */
    @Override
    public boolean isBinding() {
        return false;
    }

    /**
     * 通过{@link #getSupportFragmentManager()}将布局替换成{@link Fragment}，如在{@link #switchFragment(Intent)}方法中使用
     */
    protected void replaceFragment(Fragment fragment) {
        replaceFragment(R.id.fragmentContent, fragment);
    }

    /**
     * 通过{@link #getSupportFragmentManager()}将布局替换成{@link Fragment}
     */
    protected void replaceFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }
}