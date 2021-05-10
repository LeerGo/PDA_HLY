package com.arpa.and.wms.arch.base;

import android.app.Application;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * 继承使用了DataViewModel或其子类，你需要参照如下方式在构造函数上添加@ViewModelInject注解
 */
@HiltViewModel
public class DataViewModel extends BaseViewModel<BaseModel> {

    @Inject
    public DataViewModel(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    /**
     * 传入Class 获得{@link retrofit2.Retrofit#create(Class)} 对应的Class
     *
     * @return {@link retrofit2.Retrofit#create(Class)}
     */
    public <T> T getRetrofitService(Class<T> service) {
        return getModel().getRetrofitService(service);
    }

    /**
     * 传入Class 通过{@link Room#databaseBuilder},{@link RoomDatabase.Builder<T>#build()}获得对应的Class
     *
     * @return {@link RoomDatabase.Builder<T>#build()}
     */
    public <T extends RoomDatabase> T getRoomDatabase(@NonNull Class<T> database) {
        return getRoomDatabase(database, null);
    }

    /**
     * 传入Class 通过{@link Room#databaseBuilder},{@link RoomDatabase.Builder<T>#build()}获得对应的Class
     *
     * @return {@link RoomDatabase.Builder<T>#build()}
     */
    public <T extends RoomDatabase> T getRoomDatabase(@NonNull Class<T> database, @Nullable String dbName) {
        return getModel().getRoomDatabase(database, dbName);
    }
}
