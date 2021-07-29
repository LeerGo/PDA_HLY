package com.arpa.wms.hly.net.interceptor;

import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

import static com.arpa.wms.hly.utils.Const.Header.TOKEN;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-06-04 04:19<br/>
 *
 * <p>
 * 拦截器：第一次登时，存储 cookie
 * </p>
 */
public class ResponseTokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers(TOKEN).isEmpty()) {
            SPUtils.getInstance().put(SPKEY.TOKEN_SSO, originalResponse.headers(TOKEN).get(0));
        }

        return originalResponse;
    }
}