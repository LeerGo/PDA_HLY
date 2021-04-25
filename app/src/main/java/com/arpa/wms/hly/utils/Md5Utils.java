package com.arpa.wms.hly.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    public Md5Utils() {
    }

    public static String getBase64(String str) {
        String result = "";
        if(str != null) {
            try {
                result = new String(Base64.encode(str.getBytes("utf-8"), 2), "utf-8");
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
            }
        }

        return result;
    }

    public static String getFromBase64(String str) {
        String result = "";
        if(str != null) {
            try {
                result = new String(Base64.decode(str, 2), "utf-8");
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
            }
        }

        return result;
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        byte[] var3 = hash;
        int var4 = hash.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            if((b & 255) < 16) {
                hex.append("0");
            }

            hex.append(Integer.toHexString(b & 255));
        }

        return hex.toString().toLowerCase();
    }

    public static String md5(String string, int times) {
        if(TextUtils.isEmpty(string)) {
            return "";
        } else {
            String md5 = md5(string);

            for(int i = 0; i < times - 1; ++i) {
                md5 = md5(md5);
            }

            return md5(md5);
        }
    }

    public static String md5(File file) {
        if(file != null && file.isFile() && file.exists()) {
            FileInputStream in = null;
            String result = "";
            byte[] buffer = new byte[8192];

            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                in = new FileInputStream(file);

                int len;
                while((len = in.read(buffer)) != -1) {
                    md5.update(buffer, 0, len);
                }

                byte[] bytes = md5.digest();
                byte[] var7 = bytes;
                int var8 = bytes.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    byte b = var7[var9];
                    String temp = Integer.toHexString(b & 255);
                    if(temp.length() == 1) {
                        temp = "0" + temp;
                    }

                    result = result + temp;
                }
            } catch (Exception var20) {
                var20.printStackTrace();
            } finally {
                if(null != in) {
                    try {
                        in.close();
                    } catch (IOException var19) {
                        var19.printStackTrace();
                    }
                }

            }

            return result;
        } else {
            return "";
        }
    }
}
