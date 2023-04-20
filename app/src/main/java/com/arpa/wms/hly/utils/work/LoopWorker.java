package com.arpa.wms.hly.utils.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.hilt.work.HiltWorker;
import androidx.work.WorkerParameters;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Single;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-20 16:05
 */
@HiltWorker
public class LoopWorker extends BaseWorker {
    private static final String TAG = "@@@@ LoopWorker";

    @AssistedInject
    public LoopWorker(@Assisted @NonNull Context context, @Assisted @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Single<Result> createWork() {
        Log.e(TAG, "createWork: ------------------");
        return Single.just(Result.success());
    }
}
