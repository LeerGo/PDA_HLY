package com.arpa.wms.hly.net.exception;

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
// TODO: 网络异常需要统一处理，正对 50x、业务中的 token 失效等等 @lyf 2021-05-17 10:02:47
public class ErrorHandler {
    public static ResultError handleException(Throwable e) {
        ResultError error;
        if (e instanceof HttpException) {
            HttpException exp = (HttpException) e;
            error = new ResultError(exp.code(), exp.message());
        } else {
            error = new ResultError(ErrorCode.UNKNOWN, "未知错误");
        }
        return error;
    }
}
