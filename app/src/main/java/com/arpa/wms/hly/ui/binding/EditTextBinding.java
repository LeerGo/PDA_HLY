package com.arpa.wms.hly.ui.binding;

import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.ListenerUtil;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.ui.filter.InputFilterMinMax;
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

    @BindingAdapter(value = "textIntVal")
    public static void setTextIntVal(AppCompatEditText widget, Integer value) {
        if (null != value) {
            String sValue = String.valueOf(value);
            if (!sValue.equals(String.valueOf(widget.getText()))) {
                widget.setText(sValue);
            }
        }
    }

    @BindingAdapter(value = {"maxValue", "minValue"}, requireAll = false)
    public static void setLimitValue(AppCompatEditText view, Integer maxValue, Integer minValue) {
        var min = null == minValue ? 0 : minValue;
        var max = null == maxValue ? Integer.MAX_VALUE : maxValue;
        view.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        view.setFilters(new InputFilter[]{new InputFilterMinMax(min, max)});
    }

    @BindingAdapter("onFocusChanged")
    public static void bindFocusEvent(AppCompatEditText view, View.OnFocusChangeListener listener) {
        view.setOnFocusChangeListener(listener);
    }

    @InverseBindingAdapter(attribute = "textValue", event = "textValueAttrChanged")
    public static BigDecimal getTextValue(AppCompatEditText widget) {
        if (null != widget && !TextUtils.isEmpty(widget.getText()))
            return new BigDecimal(widget.getText().toString());
        return null;
    }

    @InverseBindingAdapter(attribute = "textIntVal", event = "textValueAttrChanged")
    public static Integer getTextIntVal(AppCompatEditText widget) {
        if (null != widget && !TextUtils.isEmpty(widget.getText()))
            return Integer.parseInt(widget.getText().toString());
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
