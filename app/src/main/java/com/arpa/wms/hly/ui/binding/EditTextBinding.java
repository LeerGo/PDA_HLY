package com.arpa.wms.hly.ui.binding;

import android.text.TextUtils;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.SimpleTextWatcher;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.ListenerUtil;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 15:59
 */
public class EditTextBinding {

    @BindingAdapter(value = "app:textValue")
    public static void setTextValue(AppCompatEditText widget, Integer value) {
        if (null != value && !value.toString().equals(String.valueOf(widget.getText())))
            widget.setText(value.toString());
    }

    @InverseBindingAdapter(attribute = "app:textValue", event = "app:textValueAttrChanged")
    public static Integer getTextValue(AppCompatEditText widget) {
        if (null != widget && !TextUtils.isEmpty(widget.getText()))
            return Integer.parseInt(String.valueOf(widget.getText()));
        return null;
    }

    @BindingAdapter(value = "app:textValueAttrChanged")
    public static void setListener(AppCompatEditText widget, InverseBindingListener listener) {
        if (null != listener) {
            SimpleTextWatcher watcher = new SimpleTextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    super.onTextChanged(s, start, before, count);
                    listener.onChange();
                }
            };
            SimpleTextWatcher old = ListenerUtil.trackListener(widget, watcher, R.id.textWatcher);
            if (null != old) widget.removeTextChangedListener(old);
            widget.addTextChangedListener(watcher);
        }
    }
}
