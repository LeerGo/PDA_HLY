package com.arpa.wms.hly;

import android.app.Application;

import com.arpa.wms.hly.BuildConfig;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.Const.API;
import com.king.retrofit.retrofithelper.RetrofitHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import dagger.hilt.android.HiltAndroidApp;
import me.jessyan.autosize.AutoSizeConfig;
import timber.log.Timber;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 1:08 PM
 *
 * <p>
 * 内容描述区域
 * </p>
 */
@HiltAndroidApp
public class App extends Application {
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(android.R.color.transparent, R.color.grey);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            layout.setPrimaryColorsId(android.R.color.transparent, R.color.grey);//全局设置主题颜色
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setApiURL();
        initLogger();
        setAutoSize();
    }

    private void setApiURL() {

        RetrofitHelper.getInstance().setBaseUrl(API.URL_WMS);
        RetrofitHelper.getInstance().putDomain(API.URL_KEY, API.URL_AUTH);
    }

    private void initLogger() {
        //初始化日志打印
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodOffset(5)
                .tag(Const.LOG_TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected void log(int priority, String tag, String message, Throwable t) {
                if (BuildConfig.DEBUG) {
                    Logger.log(priority, tag, message, t);
                }
            }
        });
    }

    private void setAutoSize() {
        AutoSizeConfig.getInstance()
                .setLog(BuildConfig.DEBUG)
                .setBaseOnWidth(true);
    }
}
