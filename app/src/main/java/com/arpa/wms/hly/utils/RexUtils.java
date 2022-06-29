package com.arpa.wms.hly.utils;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022-06-25 14:44
 *
 * <p>
 * 正则工具
 * </p>
 */
public class RexUtils {
    /**
     * 批次号规则匹配
     */
    private static final String REX_BATCH_NO = "\\d{8}([A-Za-z]\\d{1,2}|[A-Za-z]{1,2})\\d{2}:\\d{2}:\\d{2}\\d+";
    /**
     * 24 小时制匹配
     */
    private static final String REX_24_HOUR = "(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d";

    public static boolean isBatchNo(String data) {
        return data.matches(REX_BATCH_NO);
    }

    public static boolean is24Hour(String data) {
        return data.matches(REX_24_HOUR);
    }
}
