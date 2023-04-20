package com.arpa.wms.hly.logic;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.arpa.and.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.bean.entity.SplitRuleEntity;
import com.arpa.wms.hly.bean.req.ReqTaskList;
import com.arpa.wms.hly.dao.AppDatabase;
import com.arpa.wms.hly.dao.SplitRuleDao;
import com.arpa.wms.hly.utils.work.SyncWorker;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 2:20 PM
 *
 * <p>
 * ViewModel：登录
 * </p>
 */
@HiltViewModel
public class VMDemo extends WrapDataViewModel {
    private static final String TAG = "@@@@ VMDemo";
    private WorkManager mWorkManager;
    private final ReqTaskList reqTaskList = new ReqTaskList(10);

    @Inject
    public VMDemo(@NonNull Application application, BaseModel model) {
        super(application, model);
        mWorkManager = WorkManager.getInstance(application);
    }

    public void doWorker() {
        doLoop();
    }

    private void doLoop() {
        var tag = "LOOP-WORKER";
        // var info = mWorkManager.getWorkInfosByTag(tag);

        // var task = new PeriodicWorkRequest
        //         .Builder(LoopWorker.class, 20, TimeUnit.MINUTES)
        //         .addTag(tag).build();

        var task = new PeriodicWorkRequest
                .Builder(SyncWorker.class, 20, TimeUnit.MINUTES)
                .addTag(tag).build();
        mWorkManager.enqueueUniquePeriodicWork("定时轮训任务", ExistingPeriodicWorkPolicy.REPLACE, task);
    }

    private void doTest() {
        SplitRuleDao dao = getRoomDatabase(AppDatabase.class).splitRuleDao();
        dao.getLastTime().flatMap(it -> Observable.just(it.orElse(1L)))
                .flatMap(apiService::rxCheckVersion2)
                .subscribe();

    }

    private void doPage() {
        SplitRuleDao dao = getRoomDatabase(AppDatabase.class).splitRuleDao();
        reqTaskList.setAssign(0);
        apiService.rxCheckVersion()
                .flatMap(it -> Observable.range(1, 10))
                .subscribeOn(Schedulers.io())
                .flatMap(page -> {
                    Log.e(TAG, "doPage: 第 " + page + "页");
                    reqTaskList.setPageNum(page);
                    return apiService.rxPdaTasks(reqTaskList.toParams());
                })
                .takeWhile(it -> it.isSuccess() && !it.getData().isFinish())
                .map(result -> result.getData().getRecords()
                        .stream().map(resTaskAssign -> {
                            var entity = new SplitRuleEntity();
                            entity.convert(resTaskAssign);
                            return entity;
                        })
                        .collect(Collectors.toList()))
                .flatMap(list -> Observable.fromCompletable(dao.saveBatch(list)))
                .subscribe(new Observer<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        Log.e(TAG, "onComplete: 下一个");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: 数据存储完成");
                    }
                });

        /*apiService.rxCheckVersion()
                .flatMap(it -> Observable.range(1, 10))
                .subscribeOn(Schedulers.io())
                .flatMap(page -> {
                    reqTaskList.setPageNum(page);
                    return apiService.rxPdaTasks(reqTaskList.toParams());
                })
                .takeWhile(it -> it.isSuccess() && !it.getData().isFinish())
                .subscribe(new Observer<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResultPage<ResTaskAssign> result) {
                        Log.e(TAG, "onNext: 111111111111");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onNext: 33333333333");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onNext: 222222222222222");
                    }
                });*/
    }

    private void doWorkerSingle() {
        mWorkManager.beginUniqueWork(
                "PRE-SYNC",
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(SyncWorker.class)
        ).enqueue();
    }
}
