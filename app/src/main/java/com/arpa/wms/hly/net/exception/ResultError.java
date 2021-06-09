package com.arpa.wms.hly.net.exception;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-05-10 14:06
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class ResultError extends Exception {
    private int code;
    private String message;

    public ResultError(Throwable cause) {
        super(cause);
    }

    public ResultError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
