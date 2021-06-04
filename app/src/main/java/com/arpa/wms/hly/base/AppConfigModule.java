package com.arpa.wms.hly.base;

import android.content.Context;

import com.arpa.and.arch.config.FrameConfigModule;
import com.arpa.and.arch.di.module.ConfigModule;
import com.arpa.wms.hly.BuildConfig;
import com.arpa.wms.hly.net.interceptor.AddCookiesInterceptor;
import com.arpa.wms.hly.net.interceptor.ReceivedCookiesInterceptor;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 10:39
 *
 * <p>
 * App 基础设置模块
 * </p>
 */
public class AppConfigModule extends FrameConfigModule {
    @Override
    public void applyOptions(Context context, ConfigModule.Builder builder) {
        builder.okHttpClientOptions(okHttpBuilder ->
                okHttpBuilder.addInterceptor(new ReceivedCookiesInterceptor()).addInterceptor(new AddCookiesInterceptor())
        ).interceptorConfigOptions(interceptorBuilder -> interceptorBuilder.addGsonConverterFactory(true).addLog(BuildConfig.DEBUG));
    }
}
