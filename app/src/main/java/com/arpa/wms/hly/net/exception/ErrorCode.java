package com.arpa.wms.hly.net.exception;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-10 14:41
 *
 * <p>
 * Http 错误常量
 * </p>
 */
public interface ErrorCode {
    int UNKNOWN = -100;
    int JSON_ERROR = -200;
    int HTTP_ERROR = -300;
    int SERVER_ERROR = -400;
    // 业务  - token 失效
    int TOKEN_INVALID = 21332;
}
