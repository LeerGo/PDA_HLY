package com.arpa.wms.hly.utils;

import android.util.Base64;

import java.security.MessageDigest;

public class EncryptUtil {
    private static final String UTF8 = "utf-8";

    /**
     * MD5加密
     */
    public static String md5Digest(String src) throws Exception {
        // 定义数字签名方法, 可用：MD5, SHA-1
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(src.getBytes(UTF8));
        return byte2HexStr(b);
    }

    /**
     * 字节数组转化为大写16进制字符串
     */
    private static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * SHA加密
     */
    public static String shaDigest(String mess) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(mess.getBytes(UTF8));
        byte[] digest = md.digest();
        return byte2HexStr(digest);
    }

    /**
     * BASE64编码,注意:可能在末尾会有"\n",在http中使用时需要去掉.
     */
    public static String base64Encoder(String src) throws Exception {
        byte[] b = Base64.encode(src.getBytes(UTF8), Base64.DEFAULT);
        String str = new String(b);
        return str.replaceAll("\n", "");//必须替换"\n","\r\n",否则http错误.;
    }

    /**
     * BASE64解码
     */
    public static String base64Decoder(String dest) throws Exception {
        return new String(Base64.decode(dest, Base64.DEFAULT), UTF8);
    }
}
