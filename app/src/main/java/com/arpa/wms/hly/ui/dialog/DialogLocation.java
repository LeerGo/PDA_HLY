package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.arpa.and.arch.base.BaseDialogFragment;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.databinding.DialogLocationBinding;
import com.arpa.wms.hly.ui.dialog.vm.VMDialogLocation;
import com.arpa.wms.hly.ui.listener.ViewListener;
import com.arpa.wms.hly.utils.Const;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-02-02 13:56
 *
 * <p>
 * dialog：库位选择
 * </p>
 */
@AndroidEntryPoint
public class DialogLocation extends BaseDialogFragment<VMDialogLocation, DialogLocationBinding> {
    private final ViewListener.DataTransCallback<String> callback;

    public DialogLocation(ViewListener.DataTransCallback<String> callback) {
        this.callback = callback;
    }

    @Override
    protected void setWindow(Window window, float widthRatio) {
        super.setWindow(window, widthRatio);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = getWidthPixels();
        lp.height = (int) (getHeightPixels() * 0.6);
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.ArpaDialogAnimationTrans;
        window.setAttributes(lp);
    }

    @Override
    public boolean isBinding() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_location;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewBind.setViewModel(viewModel);
        registerSingleLiveEvent(msg -> {
            if (msg.what == Const.Message.MSG_DIALOG_DISMISS) {
                String data = (String) msg.obj;
                if (null != callback && !TextUtils.isEmpty(data))
                    callback.transfer(data);
                dismiss();
            }
        });
    }
}
