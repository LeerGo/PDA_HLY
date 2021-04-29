package com.arpa.wms.hly.base;

import android.content.Context;

import com.arpa.and.wms.arch.config.FrameConfigModule;
import com.arpa.and.wms.arch.di.module.ConfigModule;
import com.arpa.wms.hly.net.HeaderInterceptor;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 10:39
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class AppConfigModule extends FrameConfigModule {
    @Override
    public void applyOptions(Context context, ConfigModule.Builder builder) {
        builder.okHttpClientOptions(okHttpBuilder -> okHttpBuilder.addInterceptor(new HeaderInterceptor()));
    }
}
