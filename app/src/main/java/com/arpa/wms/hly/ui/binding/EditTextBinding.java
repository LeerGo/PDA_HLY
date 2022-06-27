package com.arpa.wms.hly.ui.binding;

import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.ListenerUtil;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.listener.SimpleTextWatcher;

import java.math.BigDecimal;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 15:59
 */
public class EditTextBinding {

    @BindingAdapter(value = "textValue")
    public static void setTextValue(AppCompatEditText widget, BigDecimal value) {
        if (null != value) {
            String sValue = value.stripTrailingZeros().toPlainString();
            if (!sValue.equals(String.valueOf(widget.getText()))) {
                widget.setText(sValue);
            }
        }
    }

    @InverseBindingAdapter(attribute = "textValue", event = "textValueAttrChanged")
    public static BigDecimal getTextValue(AppCompatEditText widget) {
        if (null != widget && !TextUtils.isEmpty(widget.getText()))
            return new BigDecimal(widget.getText().toString());
        return null;
    }

    @BindingAdapter(value = "textValueAttrChanged")
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
