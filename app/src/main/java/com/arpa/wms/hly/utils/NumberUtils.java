package com.arpa.wms.hly.utils;

import java.math.BigDecimal;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-11 16:44
 */
public class NumberUtils {

    private static BigDecimal non(BigDecimal num) {
        if (null == num)
            return BigDecimal.valueOf(Long.MIN_VALUE);
        return num;
    }

    public static boolean isLarger(BigDecimal aNum, BigDecimal bNum) {
        return non(aNum).compareTo(non(bNum)) > 0;
    }

    public static boolean isLarger(String aNum, String bNum) {
        return new BigDecimal(aNum).compareTo(new BigDecimal(bNum)) > 0;
    }

    public static boolean isLarger(int aNum, int bNum) {
        return new BigDecimal(aNum).compareTo(new BigDecimal(bNum)) > 0;
    }

    public static boolean isLarger(String aNum, int bNum) {
        return new BigDecimal(aNum).compareTo(new BigDecimal(bNum)) > 0;
    }

    public static int parseInteger(Integer value) {
        return (null == value) ? 0 : value;
    }

    public static boolean isZero(BigDecimal num) {
        return null != num && BigDecimal.ZERO.compareTo(num) == 0;
    }

    public static String parseDecimal(BigDecimal value) {
        if (null != value) {
            // fix: 在 0.000 或是 0 的情况下 stripTrailingZeros 不生效的问题
            if (value.compareTo(BigDecimal.ZERO) == 0) {
                return "0";
            } else {
                return value.stripTrailingZeros().toPlainString();
            }
        }
        return "";
    }

    public static boolean isEqual(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) == 0;
    }
}
