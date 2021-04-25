package com.arpa.and.wms.arch.base;

import com.arpa.and.wms.arch.config.Constants;
import com.arpa.and.wms.arch.data.IDataRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 继承使用了BaseModel或其子类，你需要参照如下方式在构造函数上添加@Inject注解
 */
public class BaseModel implements IModel {

    private IDataRepository mDataRepository;

    @Inject
    public BaseModel(IDataRepository dataRepository) {
        this.mDataRepository = dataRepository;
    }

    @Override
    public void onDestroy() {
        mDataRepository = null;
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
