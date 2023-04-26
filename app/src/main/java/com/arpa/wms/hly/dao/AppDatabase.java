package com.arpa.wms.hly.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.arpa.wms.hly.bean.entity.SNCodeEntity;
import com.arpa.wms.hly.bean.entity.TaskItemEntity;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/31 10:17
 *
 * <p>
 * App 数据库
 * </p>
 */
@Database(
        entities = {SNCodeEntity.class, TaskItemEntity.class},
        version = 3, exportSchema = false
)
@TypeConverters(value = {DateConverter.class, BigDecimalConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract SNCodeDao snCodeDao();

    public abstract TaskItemDao taskItemDao();

}
