package com.arpa.wms.hly.utils.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.hilt.work.HiltWorker;
import androidx.work.WorkerParameters;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Single;

@HiltWorker
public class SyncWorker extends BaseWorker {
    private static final String TAG = "@@@@ SyncWorker";

    @AssistedInject
    public SyncWorker(@Assisted @NonNull Context context, @Assisted @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Single<Result> createWork() {
        /*ApiService apiService = getRetrofitService(ApiService.class);
        SplitRuleDao dao = getRoomDatabase(AppDatabase.class).splitRuleDao();
        apiService.rxCheckVersion()
                .flatMap(it->{

                })
                .observeOn(Schedulers.io())
                //Schedulers.newThread:总是启用新线程，并在新线程执行操作 在Android中则改为在Android主线程：.subscribeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());*/
        return super.createWork();
    }
}
