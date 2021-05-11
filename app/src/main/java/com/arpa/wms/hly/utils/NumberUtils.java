package com.arpa.wms.hly.utils;

import java.math.BigDecimal;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-11 16:44
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class NumberUtils {
    public static boolean isLarger(String aNum, String bNum) {
        return new BigDecimal(aNum).compareTo(new BigDecimal(bNum)) > 0;
    }

    public static boolean isLarger(int aNum, int bNum) {
        return new BigDecimal(aNum).compareTo(new BigDecimal(bNum)) > 0;
    }

    public static boolean isLarger(String aNum, int bNum) {
        return new BigDecimal(aNum).compareTo(new BigDecimal(bNum)) > 0;
    }
}
