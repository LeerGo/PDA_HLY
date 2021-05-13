package com.arpa.wms.hly.ui.dialog;

import android.os.Bundle;
import android.util.TypedValue;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseBottomDialogFragment;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
import com.zyyoona7.picker.DatePickerView;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import static com.zyyoona7.wheel.WheelView.DIVIDER_TYPE_FILL;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-25 3:58 PM
 *
 * <p>
 * Dialog: 选择日期
 * </p>
 */
public class DialogDateSelect extends BaseBottomDialogFragment {
    private final DataTransCallback<String> listener;
    private DatePickerView datePicker;

    public DialogDateSelect(DataTransCallback<String> listener) {
        this.listener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_date_select;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setDatePicker();
        setOptArea();
    }

    private void setDatePicker() {
        Calendar cal = Calendar.getInstance();

        datePicker = (DatePickerView) findViewById(R.id.dpv_date);
        datePicker.setShowDivider(true);
        datePicker.setTextSize(24, true);
        datePicker.setDividerType(DIVIDER_TYPE_FILL);
        datePicker.setDividerHeight(1);
        datePicker.setDividerColorRes(R.color.grey_e6e6e6);
        datePicker.setVisibleItems(5);
        datePicker.setNormalItemTextColorRes(R.color.color_b6bed2);
        datePicker.setSelectedItemTextColorRes(R.color.color_434c67);
        datePicker.setCurved(false);
        datePicker.setAutoFitTextSize(true);
        datePicker.setLabelTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        datePicker.setSelectedDay(cal.get(Calendar.DAY_OF_MONTH));
        datePicker.setSelectedMonth(cal.get(Calendar.MONTH));
        datePicker.setSelectedYear(cal.get(Calendar.YEAR));
    }

    private void setOptArea() {
        AppCompatButton btnCancel = (AppCompatButton) findViewById(R.id.apt_cancel);
        AppCompatButton btnSure = (AppCompatButton) findViewById(R.id.apt_sure);

        btnCancel.setOnClickListener(v -> dismiss());
        btnSure.setOnClickListener(v -> {
            listener.transfer(datePicker.getSelectedDate());
            dismiss();
        });
    }
}