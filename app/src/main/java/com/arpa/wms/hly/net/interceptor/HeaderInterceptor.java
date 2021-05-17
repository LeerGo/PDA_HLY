package com.arpa.wms.hly.net.interceptor;

import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.SPUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.arpa.wms.hly.net.ApiService.API.API_AUTHORIZATION;
import static com.arpa.wms.hly.net.ApiService.API.API_WAREHOUSE_AUTHORIZATION;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 10:42
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class HeaderInterceptor implements Interceptor {
    private final static List<String> blackList = Arrays.asList(API_AUTHORIZATION, API_WAREHOUSE_AUTHORIZATION);

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        HttpUrl url = request.url();

        if (!blackList.contains(url.url().getPath().substring(1))) {
            builder.addHeader("Authorization", "Bearer " + SPUtils.getInstance().getString(SPKEY.USER_TOKEN))
                    .addHeader("deviceId", SPUtils.getInstance().getString(SPKEY.DEVICE_ID));
        }
        return chain.proceed(builder.build());
    }
}
