package com.arpa.wms.hly.utils;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022-07-12 10:42
 */
public class SoundMode {
    public static final int SUCCESS = 0;
    public static final int FAILED = 1;


    @IntDef({SUCCESS, FAILED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SOUND_MODE {}
}
