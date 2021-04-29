package com.arpa.and.wms.arch.di.module;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.arpa.and.wms.arch.BuildConfig;
import com.arpa.and.wms.arch.config.AppliesOptions;
import com.arpa.and.wms.arch.http.InterceptorConfig;
import com.arpa.and.wms.arch.http.interceptor.logging.Level;
import com.arpa.and.wms.arch.http.interceptor.logging.LoggingInterceptor;
import com.king.retrofit.retrofithelper.RetrofitHelper;

import javax.inject.Singleton;

import androidx.annotation.Nullable;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
@InstallIn(SingletonComponent.class)
@Module
public class HttpModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, @Nullable AppliesOptions.RetrofitOptions options) {
        if (options != null) {
            options.applyOptions(builder);
        }
        return builder.build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder, @Nullable AppliesOptions.OkHttpClientOptions options) {
        if (options != null) {
            options.applyOptions(builder);
        }
        return builder.build();
    }

    @Singleton
    @Provides
    Gson provideGson(GsonBuilder builder, @Nullable AppliesOptions.GsonOptions options) {
        if (options != null) {
            options.applyOptions(builder);
        }
        return builder.create();
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(HttpUrl httpUrl, OkHttpClient client, Gson gson, InterceptorConfig config) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(httpUrl).client(client);
        if (config.isAddGsonConverterFactory()) {
            builder.addConverterFactory(GsonConverterFactory.create(gson));
        }
        // TODO: 增加 LivedataCallAdapterFactory @lyf 2021-04-26 03:20:31
        return builder;
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideClientBuilder(InterceptorConfig config) {
        OkHttpClient.Builder builder = RetrofitHelper.getInstance().createClientBuilder();

        if (config.isAddLog()) {
            builder.addInterceptor(new LoggingInterceptor
                    .Builder()//构建者模式
                    .loggable(BuildConfig.DEBUG) //是否开启日志打印
                    .setLevel(Level.BASIC) //打印的等级
                    .log(Platform.INFO) // 打印类型
                    .request("Net-Request") // request的Tag
                    .response("Net-Response")// Response的Tag
                    .build()
            );
        }

        return builder;
    }

    @Singleton
    @Provides
    GsonBuilder provideGsonBuilder() {
        return new GsonBuilder();
    }

    @Singleton
    @Provides
    InterceptorConfig provideInterceptorConfig(InterceptorConfig.Builder builder, @Nullable AppliesOptions.InterceptorConfigOptions options) {
        if (options != null) {
            options.applyOptions(builder);
        }
        return builder.build();
    }

    @Singleton
    @Provides
    InterceptorConfig.Builder provideInterceptorConfigBuilder() {
        return InterceptorConfig.newBuilder();
    }


}
