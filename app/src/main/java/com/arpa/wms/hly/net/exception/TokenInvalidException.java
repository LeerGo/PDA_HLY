package com.arpa.wms.hly.net.exception;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-17 10:42<br/>
 *
 * <p>
 * token 失效异常
 * </p>
 */
public class TokenInvalidException extends ServerProtocolException {

    public TokenInvalidException(int code, String msg) {
        super(code, msg);
    }
}