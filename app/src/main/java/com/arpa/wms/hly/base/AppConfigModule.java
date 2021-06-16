package com.arpa.wms.hly.base;

import android.content.Context;

import com.arpa.and.arch.config.FrameConfigModule;
import com.arpa.and.arch.di.module.ConfigModule;
import com.arpa.and.arch.http.interceptor.logging.Level;
import com.arpa.and.arch.http.interceptor.logging.LoggingInterceptor;
import com.arpa.wms.hly.net.interceptor.RequestTokenInterceptor;
import com.arpa.wms.hly.net.interceptor.ResponseTokenInterceptor;

import okhttp3.internal.platform.Platform;

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
                okHttpBuilder
                        .addInterceptor(new ResponseTokenInterceptor())
                        .addInterceptor(new RequestTokenInterceptor())
                        .addInterceptor(new LoggingInterceptor
                                .Builder()//构建者模式
                                .loggable(true) //是否开启日志打印
                                .setLevel(Level.BASIC) //打印的等级
                                .log(Platform.INFO) // 打印类型
                                .request("Net-Request") // request的Tag
                                .response("Net-Response")// Response的Tag
                                .build())
        ).interceptorConfigOptions(interceptorBuilder -> interceptorBuilder.addLog(false));
    }
}
