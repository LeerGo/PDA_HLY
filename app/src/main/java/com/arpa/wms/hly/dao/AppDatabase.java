package com.arpa.wms.hly.dao;

import com.arpa.wms.hly.bean.SNCodeEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/31 10:17
 *
 * <p>
 * App 数据库
 * </p>
 */
@Database(entities = {SNCodeEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SNCodeDao snCodeDao();
}
