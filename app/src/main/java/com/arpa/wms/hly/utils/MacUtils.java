package com.arpa.wms.hly.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * Created by Administrator on 2016/12/29.
 */
public class MacUtils {

    /*
     * 整理获取手机唯一标识
     * **/
    public static String getMacOnly(Context context) {
        String mac = "hk" + getAndroidId(context) + "p" + getMac() + "a" + getDeviceID(context);
        try {
            mac = EncryptUtil.md5Digest(mac);
        } catch (Exception e) {
            e.printStackTrace();
            mac = "hk" + getAndroidId(context) + "p" + getMac() + "a" + getDeviceID(context);
        }
        return mac;
    }

    /**
     * 获取手机的MAC地址
     */
    private static String getMac() {
        String str = "";
        String macSerial = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            while (null != str) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if ("".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return macSerial;
    }

    private static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    private static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }

    /**
     * 获取Android id
     */
    private static String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
    }

    public static String getDeviceID(Context context) {
        String deviceID = "";
        try {
            //一共13位  如果位数不够可以继续添加其他信息
            deviceID = "" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                    Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                    Build.USER.length() % 10;
        } catch (Exception e) {
            return "";
        }
        return deviceID;
    }
}
