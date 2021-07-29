package com.arpa.wms.hly.bean.base;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 02:27<br/>
 *
 * <p>
 * 基础数据响应
 * </p>
 */
public class Result <T> {
    // sso 用 code 表示状态
    private int code = 200;
    // wms 用 status 表示状态
    private int status = 0;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return status == 0 && code == 200;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + status +
                ", message=" + msg +
                ", data=" + data +
                '}';
    }
}
