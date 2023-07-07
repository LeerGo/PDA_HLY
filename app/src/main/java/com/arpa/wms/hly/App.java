package com.arpa.wms.hly;

import androidx.multidex.MultiDexApplication;

import com.arpa.wms.hly.net.ApiService.API;
import com.arpa.wms.hly.utils.Const;
import com.arpa.wms.hly.utils.Const.Header;
import com.arpa.wms.hly.utils.Md5Utils;
import com.arpa.wms.hly.utils.SPUtils;
import com.arpa.wms.hly.utils.Utils;
import com.king.retrofit.retrofithelper.RetrofitHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Date;
import java.util.UUID;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

import static com.arpa.wms.hly.utils.Const.Header.EQUIPMENT_CODE;
import static com.arpa.wms.hly.utils.Const.SPKEY.TEST_SERVER;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-22 1:08 PM
 *
 * <p>
 * 全局 App
 * </p>
 */
// TODO: 还需要一个状态页面（空数据、错误、警告） @lyf 2021-04-30 09:17:42
// TODO: 设计到分页加载的页面，再从上一个页面时，需要重新请求最后一页数据然后替换 @lyf 2021-04-30 03:13:00
@HiltAndroidApp
public class App extends MultiDexApplication {
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

        Utils.init(this);
        setRetrofit();
        initLogger();
        initBugly();
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), Const.BUGLY_ID, BuildConfig.DEBUG);
    }

    private void setRetrofit() {
        RetrofitHelper.getInstance().setBaseUrl(API.URL_WMS);
        RetrofitHelper.getInstance().putDomain(API.KEY_SSO, API.URL_SSO);
        RetrofitHelper.getInstance().setAddHeader(true);
        RetrofitHelper.getInstance().addHeader(Header.SOURCE_ID, Header.SOURCE_ANDROID);
        if (SPUtils.getInstance().contains(TEST_SERVER)) {
            RetrofitHelper.getInstance().putDomain(API.KEY_WMS, SPUtils.getInstance().getString(TEST_SERVER));
        }
        if (!SPUtils.getInstance().contains(EQUIPMENT_CODE)) {
            String uuid = Md5Utils.md5(new Date().getTime() + "_ARPA_ANDROID_" + UUID.randomUUID().toString());
            SPUtils.getInstance().put(EQUIPMENT_CODE, uuid);
        }
        RetrofitHelper.getInstance().addHeader(EQUIPMENT_CODE, SPUtils.getInstance().getString(EQUIPMENT_CODE));
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
}
