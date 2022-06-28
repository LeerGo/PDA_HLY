package com.arpa.wms.hly.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022-06-25 14:51
 *
 * <p>
 * 日期工具类
 * </p>
 */
public class DateUtils {
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat formatBatch = new SimpleDateFormat("yyyyMMddHH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat formatBrief = new SimpleDateFormat("yyyy-MM-dd");

    private static final int MINUTE = 60;
    private static final int HOUR = MINUTE * MINUTE;
    private static final int DAY = 24 * HOUR;
    private static final int MONTH = 30 * DAY;

    /**
     * 计算时间差
     */
    public static String dateDiff(Date dateStart, Date dateEnd) {
        long diff = (dateEnd.getTime() - dateStart.getTime()) / 1000;
        long remain;
        int month = Math.toIntExact(diff / MONTH);
        remain = diff - (long) month * MONTH;
        int day = Math.toIntExact(remain / DAY);
        remain = remain - (long) day * DAY;
        int hour = Math.toIntExact(remain / HOUR);
        remain = remain - (long) hour * HOUR;
        int min = Math.toIntExact(remain / MINUTE);
        int sec = Math.toIntExact(remain - (long) min * MINUTE);

        StringBuilder sb = new StringBuilder();
        if (0 != month) sb.append(month).append("月").append(day).append("天");
        if (0 != day && 0 == month) sb.append(day).append("天");
        sb.append(hour).append("时").append(min).append("分").append(sec).append("秒\n");
        return sb.toString();
    }

    /**
     * 日期是否超过今天
     */
    public static boolean isMoreToday(Date date) {
        return date.getTime() > new Date().getTime();
    }

    public static boolean isDateValid(String dateToValidate) {
        if (TextUtils.isEmpty(dateToValidate)) return false;

        formatBrief.setLenient(false);

        try {
            formatBrief.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
