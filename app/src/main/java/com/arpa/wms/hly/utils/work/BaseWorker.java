package com.arpa.wms.hly.utils.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.hilt.work.HiltWorker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.work.WorkerParameters;
import androidx.work.rxjava3.RxWorker;

import com.arpa.and.arch.config.Constants;
import com.arpa.and.arch.data.IDataRepository;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Single;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-19 13:47*
 */
@HiltWorker
public class BaseWorker extends RxWorker {
    @Inject
    IDataRepository mDataRepository;
    protected Context context;

    @AssistedInject
    public BaseWorker(@Assisted @NonNull Context context, @Assisted @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Single<Result> createWork() {
        return Single.just(Result.success());
    }

    /**
     * 传入Class 获得{@link retrofit2.Retrofit#create(Class)} 对应的Class
     *
     * @return {@link retrofit2.Retrofit#create(Class)}
     */
    public <T> T getRetrofitService(Class<T> service) {
        return mDataRepository.getRetrofitService(service);
    }


    /**
     * 传入Class 通过{@link Room#databaseBuilder},{@link RoomDatabase.Builder<T>#build()}获得对应的Class
     *
     * @return {@link RoomDatabase.Builder<T>#build()}
     */
    public <T extends RoomDatabase> T getRoomDatabase(@NonNull Class<T> database) {
        return getRoomDatabase(database, Constants.DEFAULT_DATABASE_NAME);
    }

    /**
     * 传入Class 通过{@link Room#databaseBuilder},{@link RoomDatabase.Builder<T>#build()}获得对应的Class
     *
     * @return {@link RoomDatabase.Builder<T>#build()}
     */
    public <T extends RoomDatabase> T getRoomDatabase(@NonNull Class<T> database, @Nullable String dbName) {
        return mDataRepository.getRoomDatabase(database, dbName);
    }
}
