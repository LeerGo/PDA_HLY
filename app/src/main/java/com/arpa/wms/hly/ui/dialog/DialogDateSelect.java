package com.arpa.wms.hly.ui.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.BaseBottomDialogFragment;
import com.arpa.wms.hly.ui.listener.ViewListener.DataTransCallback;
import com.zyyoona7.picker.DatePickerView;
import com.zyyoona7.picker.ex.DayWheelView;
import com.zyyoona7.picker.ex.MonthWheelView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

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
        //获取月日 WheelView
        MonthWheelView monthWv3 = datePicker.getMonthWv();
        DayWheelView dayWv3 = datePicker.getDayWv();
        //注意：setIntegerNeedFormat(String integerFormat)方法 integerFormat 中必须包含并且只能包含一个格式说明符（format specifier）
        //更多请查看该方法参数说明
        monthWv3.setIntegerNeedFormat("%02d");
        dayWv3.setIntegerNeedFormat("%02d");
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

    @SuppressLint("SimpleDateFormat")
    private void setOptArea() {
        AppCompatButton btnCancel = (AppCompatButton) findViewById(R.id.apt_cancel);
        AppCompatButton btnSure = (AppCompatButton) findViewById(R.id.apt_sure);

        btnCancel.setOnClickListener(v -> dismiss());

        btnSure.setOnClickListener(v -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                listener.transfer(format2.format(Objects.requireNonNull(format.parse(datePicker.getSelectedDate()))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dismiss();
        });
    }
}