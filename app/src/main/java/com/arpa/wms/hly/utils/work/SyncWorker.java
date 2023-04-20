package com.arpa.wms.hly.utils.work;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.hilt.work.HiltWorker;
import androidx.work.WorkerParameters;

import com.arpa.wms.hly.R;
import com.arpa.wms.hly.bean.base.ResultPage;
import com.arpa.wms.hly.bean.entity.SplitRuleEntity;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.bean.res.ResTaskAssign;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SplitRuleDao;
import com.arpa.wms.hly.net.ApiService;

import java.util.List;
import java.util.stream.Collectors;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-19 13:37
 */
@HiltWorker
public class SyncWorker extends BaseWorker {
    private static final String TAG = "@@@@ SyncWorker";

    // 通知
    //定义notification实用的ID
    private static final String MESSAGES_CHANNEL = "messages";
    private static NotificationManager notificationManager;
    private static NotificationCompat.Builder builder;
    ;

    @AssistedInject
    public SyncWorker(@Assisted @NonNull Context context, @Assisted @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Single<Result> createWork() {
        ApiService apiService = getRetrofitService(ApiService.class);
        SplitRuleDao dao = getRoomDatabase(AppDatabase.class).splitRuleDao();
        ReqTaskList req = new ReqTaskList(20);
        req.setAssign(0);
        createNotification();
        return dao.getLastTime()
                .flatMap(it -> Observable.just(it.orElse(1L)))
                .flatMap(apiService::rxCheckVersion2)
                // .takeWhile(it -> random.nextBoolean())
                .flatMap(it -> Observable.range(1, 8))
                .flatMap(page -> requestPageData(apiService, req, page))
                .takeUntil(it -> it.isSuccess() && it.getData().isFinish())
                .map(result -> result.getData().getRecords().stream().map(SyncWorker::convert).collect(Collectors.toList()))
                .flatMapCompletable(it -> savePageData(dao, req, it))
                .toSingleDefault(Result.success())
                .onErrorReturnItem(Result.failure())
                .observeOn(Schedulers.io());
    }

    private void createNotification() {
        createMessageNotificationChannel();
        String title = "数据同步";
        // PendingIntent intent = WorkManager.getInstance(context).createCancelPendingIntent(getId());
        builder = new NotificationCompat.Builder(context, MESSAGES_CHANNEL);
        builder.setContentTitle(title)
                .setTicker(title)
                .setProgress(100, 0, false)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setOngoing(true);
        // .addAction(android.R.drawable.ic_delete, "取消同步", intent);
        notificationManager.notify(1, builder.build());
    }

    private void createMessageNotificationChannel() {
        CharSequence name = context.getString(R.string.app_name);
        NotificationChannel channel = new NotificationChannel(MESSAGES_CHANNEL, name, NotificationManager.IMPORTANCE_HIGH);
        notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    private static Completable savePageData(SplitRuleDao dao, ReqTaskList req, List<SplitRuleEntity> it) {
        Log.e(TAG, "createWork: 存储第：" + req.pageNum + "页");
        return dao.saveBatch(it);
    }

    private static Observable<ResultPage<ResTaskAssign>> requestPageData(ApiService apiService, ReqTaskList req, Integer page) {
        Log.e(TAG, "createWork: 第 " + page + "页");
        req.setPageNum(page);
        builder.setProgress(5, page, false)
                .setContentText( "1%");
        if (page == 5)
            builder.setAutoCancel(true);
        notificationManager.notify(1, builder.build());
        return apiService.rxPdaTasks(req.toParams());
    }

    @NonNull
    private static SplitRuleEntity convert(ResTaskAssign resTaskAssign) {
        var entity = new SplitRuleEntity();
        entity.convert(resTaskAssign);
        return entity;
    }
}
