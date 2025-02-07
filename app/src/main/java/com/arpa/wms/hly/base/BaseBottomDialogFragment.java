package com.arpa.wms.hly.base;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.arpa.and.arch.base.BaseDialogFragment;
import com.arpa.and.arch.base.BaseViewModel;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.utils.ToastUtils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-13 16:40
 *
 * <p>
 * Base: 针对底部弹窗的基础封装
 * </p>
 */
public abstract class BaseBottomDialogFragment<VM extends BaseViewModel, VDB extends ViewDataBinding>
        extends BaseDialogFragment<VM, VDB> {
    @Override
    protected void setWindow(Window window, float widthRatio) {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = getWidthPixels();
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.ArpaDialogAnimationTrans;
        window.setAttributes(lp);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        registerMessageEvent(ToastUtils::showShort);
    }

    @Override
    public boolean isBinding() {
        return false;
    }
}
