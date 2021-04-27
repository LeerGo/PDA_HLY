package com.arpa.wms.hly.dao;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Deprecated
@Database(entities = {AppConfig.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppConfigDao appConfigDao();
}
