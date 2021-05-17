package com.arpa.wms.hly.net.exception;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-17 10:42<br/>
 *
 * <p>
 * 自定义服务器协议异常
 * </p>
 */
public class ServerProtocolException extends RuntimeException {
    private int code;
    private String msg;

    public ServerProtocolException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
