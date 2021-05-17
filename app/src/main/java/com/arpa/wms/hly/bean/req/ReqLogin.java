package com.arpa.wms.hly.bean.req;

import com.google.gson.annotations.SerializedName;

import android.annotation.SuppressLint;

import com.arpa.wms.hly.bean.base.ReqBase;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.Const.AppConfig;
import com.arpa.wms.hly.utils.Md5Utils;
import com.arpa.wms.hly.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:40 PM
 *
 * <p>
 * 请求：登录
 * </p>
 */
public class ReqLogin extends ReqBase {
    @SerializedName("client_id")
    public String clientID;
    @SerializedName("client_secret")
    public String clientSecret;
    @SerializedName("grant_type")
    public String grantType;
    @SerializedName("response_type")
    public String responseType;
    public String time;
    @SerializedName("device_id")
    public String deviceID;

    private String username;
    private String password;
    private String authorizeDataCode;

    @SuppressLint("SimpleDateFormat")
    public ReqLogin() {
        clientID = AppConfig.clientID;
        clientSecret = AppConfig.clientSecret;
        grantType = "password";
        responseType = "token";
        time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        deviceID = SPUtils.getInstance().getString(Const.SPKEY.DEVICE_ID);
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
        this.password = Md5Utils.getBase64(password + "_arpa_" + time);
    }

    public String getAuthorizeDataCode() {
        return authorizeDataCode;
    }

    public void setAuthorizeDataCode(String authorizeDataCode) {
        this.authorizeDataCode = authorizeDataCode;
    }
}
