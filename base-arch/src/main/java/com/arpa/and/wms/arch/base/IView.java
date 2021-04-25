package com.arpa.and.wms.arch.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

/**
 * 用来规范{@link BaseActivity} 和{@link BaseFragment} 风格。
 */
public interface IView <VM extends ViewModel> {

    /**
     * 根布局id
     */
    @LayoutRes
    int getLayoutId();

    /**
     * 初始化数据
     */
    void initData(@Nullable Bundle savedInstanceState);

    /**
     * 是否使用DataBinding
     */
    boolean isBinding();

    /**
     * 创建ViewModel
     */
    VM createViewModel();

}
