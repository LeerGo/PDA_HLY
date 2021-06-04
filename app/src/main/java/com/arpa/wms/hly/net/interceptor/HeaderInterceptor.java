package com.arpa.wms.hly.net.interceptor;

import com.arpa.wms.hly.utils.Const.SPKEY;
import com.arpa.wms.hly.utils.SPUtils;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.arpa.wms.hly.net.ApiService.API.API_SSO_LOGIN;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 10:42
 *
 * <p>
 * 拦截器：网络层处理 header 信息
 * </p>
 */
public class HeaderInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        HttpUrl url = request.url();
        String api = url.url().getPath().substring(1);

        // 除了 SSO Login 接口，header 都需要 token
        if (!API_SSO_LOGIN.equals(api)) {
            builder.addHeader("token", SPUtils.getInstance().getString(SPKEY.TOKEN_SSO));
        }
        // 前端请求后端，header里需要加上自己的标示code：比如是 android，需要传入source-id=2
        builder.addHeader("source-id", "2");
        return chain.proceed(builder.build());
    }
}
