package com.arpa.and.wms.arch.data;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.arpa.and.wms.arch.config.AppliesOptions.RoomDatabaseOptions;
import com.arpa.and.wms.arch.config.Constants;
import com.arpa.and.wms.arch.util.Preconditions;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.Lazy;
import retrofit2.Retrofit;

/**
 * 统一管理数据业务层
 */
@Singleton
public class DataRepository implements IDataRepository {

    @Inject
    Lazy<Retrofit> mRetrofit;

    @Inject
    Application mApplication;

    @Inject
    RoomDatabaseOptions mRoomDatabaseOptions;

    /**
     * 缓存 Retrofit Service
     */
    private LruCache<String, Object> mRetrofitServiceCache;
    /**
     * 缓存 RoomDatabase
     */
    private LruCache<String, Object> mRoomDatabaseCache;

    @Inject
    public DataRepository() {
    }

    /**
     * 提供上下文{@link Context}
     *
     * @return {@link #mApplication}
     */
    @Override
    public Context getContext() {
        return mApplication;
    }

    /**
     * 传入Class 通过{@link Retrofit#create(Class)} 获得对应的Class
     *
     * @return {@link Retrofit#create(Class)}
     */
    @Override
    public <T> T getRetrofitService(@NonNull Class<T> service) {
        if (mRetrofitServiceCache == null) {
            mRetrofitServiceCache = new LruCache<>(Constants.DEFAULT_RETROFIT_SERVICE_MAX_SIZE);
        }
        Preconditions.checkNotNull(mRetrofitServiceCache);

        T retrofitService = (T) mRetrofitServiceCache.get(service.getCanonicalName());
        if (retrofitService == null) {
            synchronized (mRetrofitServiceCache) {
                if (retrofitService == null) {
                    retrofitService = mRetrofit.get().create(service);
                    //缓存
                    mRetrofitServiceCache.put(service.getCanonicalName(), retrofitService);
                }

            }
        }

        return retrofitService;
    }

    /**
     * 传入Class 通过{@link Room#databaseBuilder},{@link RoomDatabase.Builder<T>#build()}获得对应的Class
     *
     * @param dbName
     *         为{@code null}时，默认为{@link Constants#DEFAULT_DATABASE_NAME}
     *
     * @return {@link RoomDatabase.Builder<T>#build()}
     */
    @Override
    public <T extends RoomDatabase> T getRoomDatabase(@NonNull Class<T> database, @Nullable String dbName) {
        if (mRoomDatabaseCache == null) {
            mRoomDatabaseCache = new LruCache<>(Constants.DEFAULT_ROOM_DATABASE_MAX_SIZE);
        }
        Preconditions.checkNotNull(mRoomDatabaseCache);

        T roomDatabase = (T) mRoomDatabaseCache.get(database.getCanonicalName());
        if (roomDatabase == null) {
            synchronized (mRoomDatabaseCache) {
                if (roomDatabase == null) {
                    RoomDatabase.Builder<T> builder = Room.databaseBuilder(getContext().getApplicationContext(), database, TextUtils.isEmpty(dbName) ? Constants.DEFAULT_DATABASE_NAME : dbName);
                    if (mRoomDatabaseOptions != null) {
                        mRoomDatabaseOptions.applyOptions(builder);
                    }
                    roomDatabase = builder.build();
                    //缓存
                    mRoomDatabaseCache.put(database.getCanonicalName(), roomDatabase);
                }
            }
        }
        return roomDatabase;
    }

}
