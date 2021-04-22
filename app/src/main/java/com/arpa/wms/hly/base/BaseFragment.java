package com.arpa.wms.hly.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 3:50 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), null);
        ButterKnife.bind(this, view);

        initData();
        initViews();
        setViews();
        return view;
    }

    protected abstract int getLayoutID();

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void setViews();
}
