package com.arpa.wms.hly.utils.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.hilt.work.HiltWorker;
import androidx.work.WorkerParameters;

import com.arpa.wms.hly.bean.entity.SplitRuleEntity;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SplitRuleDao;
import com.arpa.wms.hly.net.ApiService;

import java.util.Random;
import java.util.stream.Collectors;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-19 13:37
 */
@HiltWorker
public class PreSyncWorker extends BaseWorker {
    private static final String TAG = "@@@@ PreSyncWorker";

    @AssistedInject
    public PreSyncWorker(@Assisted @NonNull Context context, @Assisted @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Single<Result> createWork() {
        Random random = new Random();
        ApiService apiService = getRetrofitService(ApiService.class);
        SplitRuleDao dao = getRoomDatabase(AppDatabase.class).splitRuleDao();
        ReqTaskList req = new ReqTaskList(20);
        req.setAssign(0);


        return dao.getLastTime()
                .flatMap(it -> Observable.just(it.orElse(1L)))
                .flatMap(apiService::rxCheckVersion2)
                // .takeWhile(it -> random.nextBoolean())
                .flatMap(it -> Observable.range(1, 8))
                .flatMap(page -> {
                    Log.e(TAG, "createWork: 第 " + page + "页");
                    req.setPageNum(page);
                    return apiService.rxPdaTasks(req.toParams());
                })
                .takeUntil(it -> it.isSuccess() && it.getData().isFinish())
                .map(result -> result.getData().getRecords()
                        .stream().map(resTaskAssign -> {
                            var entity = new SplitRuleEntity();
                            entity.convert(resTaskAssign);
                            return entity;
                        })
                        .collect(Collectors.toList()))
                .flatMapCompletable(it -> {
                    Log.e(TAG, "createWork: 存储第：" + req.pageNum + "页");
                    return dao.saveBatch(it);
                })
                .toSingleDefault(Result.success())
                .onErrorReturnItem(Result.failure())
                .observeOn(Schedulers.io());

        /*return dao.getLastTime()
                .flatMap(it -> Observable.just(it.orElse(1L)))
                .flatMap(apiService::rxCheckVersion2)
                // .takeWhile(it -> random.nextBoolean())
                .flatMap(it -> Observable.range(1, 8))
                .flatMap(page -> {
                    Log.e(TAG, "doPage: 第 " + page + "页");
                    req.setPageNum(page);
                    return apiService.rxPdaTasks(req.toParams());
                })
                .takeWhile(it -> it.isSuccess() && !it.getData().isFinish())
                .map(result -> result.getData().getRecords()
                        .stream().map(resTaskAssign -> {
                            var entity = new SplitRuleEntity();
                            entity.convert(resTaskAssign);
                            return entity;
                        })
                        .collect(Collectors.toList()))
                .flatMapCompletable(dao::saveBatch)
                .toSingleDefault(Result.success())
                .onErrorReturnItem(Result.failure())
                .observeOn(Schedulers.io());*/
    }
}
