package com.arpa.wms.hly;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.arpa.wms.hly.logic.LoginActivity;
import com.arpa.wms.hly.utils.WeakHandler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-21 2:02 PM
 *
 * <p>
 * 闪屏页
 * </p>
 */
public class SplashActivity extends AppCompatActivity implements WeakHandler.MessageListener {
    private static final int msgJump = 0x1;
    //设置成静态成员，否则会出现内存泄漏
    private static WeakHandler<SplashActivity> sHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sHandler = new WeakHandler<>(this);
        postEmptyMsgDelayed(msgJump, 1000);
    }

    private void postEmptyMsgDelayed(int msgType, long delay) {
        if (sHandler.hasMessages(msgType)) {
            sHandler.removeMessages(msgType);
        }
        sHandler.sendEmptyMessageDelayed(msgType, delay);
    }

    @Override
    protected void onDestroy() {
        sHandler.clear();
        super.onDestroy();
    }

    @Override
    public void handleMessage(Message msg) {
        // TODO: 这里要根据有无 token，跳转登录或首页 @lyf 2021-04-22 08:33:30
        if (msg.what == msgJump) {
            //            startActivity(new Intent(this, HomeActivity.class));
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
