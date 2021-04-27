package com.arpa.wms.hly.dao;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Deprecated
@Dao
public interface AppConfigDao {

    /**
     * 插入一条历史（去重）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AppConfig appConfig);

    /**
     * 删除历史
     */
    @Delete
    void delete(AppConfig appConfig);

    /**
     * 清空历史
     */
    @Query("DELETE FROM AppConfig")
    void deleteAll();

    /**
     * 获取所有历史
     */
    @Query("SELECT * FROM AppConfig")
    LiveData<List<AppConfig>> getAllHistory();
}
