package com.arpa.wms.hly.net.exception;

import com.google.gson.JsonParseException;

import android.content.Intent;

import com.arpa.wms.hly.logic.LoginActivity;
import com.arpa.wms.hly.utils.ToastUtils;
import com.arpa.wms.hly.utils.Utils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-17 09:42
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class ErrorHandler {
    public static ResultError handleException(Throwable e) {
        ResultError error = new ResultError(e);
        if (e instanceof HttpException) {
            HttpException exp = (HttpException) e;
            if (exp.code() >= 400 && exp.code() < 500)
                error.setInfo(exp.code(), "服务器资源丢失");
            if (exp.code() >= 500)
                error.setInfo(exp.code(), "服务器异常");
        } else if (e instanceof TokenInvalidException) {
            tokenValid((TokenInvalidException) e);
        } else if (e instanceof ServerProtocolException) {
            ServerProtocolException exp = (ServerProtocolException) e;
            error.setInfo(exp.getCode(), exp.getMsg());
        } else if (e instanceof JSONException || e instanceof JsonParseException || e instanceof ParseException) {
            error.setInfo(ErrorCode.JSON_ERROR, "JSON 解析异常");
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            error.setInfo(ErrorCode.HTTP_ERROR, "请求超时");
        } else if (e instanceof ConnectException) {
            error.setInfo(ErrorCode.HTTP_ERROR, "请求异常");
        } else {
            error = new ResultError(ErrorCode.UNKNOWN, "未知错误");
        }
        return error;
    }

    /**
     * 处理 token 失效，跳转登录
     */
    private static void tokenValid(TokenInvalidException e) {
        ToastUtils.showShort(e.getMessage());
        Intent intent = new Intent(Utils.getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getContext().startActivity(intent);
    }
}
