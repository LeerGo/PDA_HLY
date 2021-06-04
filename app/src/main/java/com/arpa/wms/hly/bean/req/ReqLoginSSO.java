package com.arpa.wms.hly.bean.req;

import com.arpa.wms.hly.bean.base.ReqBase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-06-04 08:46
 */
public class ReqLoginSSO extends ReqBase {
    public static final Integer LOGIN_TYPE_USERNAME = 1;
    public static final Integer LOGIN_TYPE_MOBILE = 2;
    public static final Integer LOGIN_TYPE_USERNAME_AND_MOBILE = 3;

    private String username;
    private String password;
    private int loginType;

    public ReqLoginSSO(String username, String password) {
        this.username = username;
        this.password = password;
        // 登录类型： 1用户名登录，2手机号登录，3验证所有
        // FIXME: 暂时默认为用户名登录，实际场景要判断是否为手机号 @lyf 2021-06-04 08:52:15
        this.loginType = LOGIN_TYPE_USERNAME;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
}
