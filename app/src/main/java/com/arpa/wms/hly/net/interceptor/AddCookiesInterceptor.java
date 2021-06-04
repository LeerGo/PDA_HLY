package com.arpa.wms.hly.net.interceptor;

import android.text.TextUtils;

import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-06-04 04:19<br/>
 *
 * <p>
 * 拦截器：添加 cookie
 * </p>
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String cookie = SPUtils.getInstance().getString(Const.SPKEY.COOKIE);

        if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}