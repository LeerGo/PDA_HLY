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
    // private static final String REX_YYYYMMDD= "((\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2}|[1-9]\\d{3})(((0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[1]\\d|2[0-8]))))|(((\\d{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)";
    private static final String REX_YYYYMMDD= "^\\d{4}-\\d{2}-\\d{2}$";
    private static final String REX_ADDRESS= "[A-Z]|[A-Z]\\d{1,2}";

    public static boolean isBatchNo(String data) {
        return data.matches(REX_BATCH_NO);
    }

    public static boolean is24Hour(String data) {
        return data.matches(REX_24_HOUR);
    }

    public static boolean isYYMMDD(String data){
        return data.matches(REX_YYYYMMDD);
    }

    public static boolean isAddress(String data){
        return data.matches(REX_ADDRESS);
    }
}
