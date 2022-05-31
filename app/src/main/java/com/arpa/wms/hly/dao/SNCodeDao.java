package com.arpa.wms.hly.dao;

import com.arpa.wms.hly.bean.SNCodeEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/31 10:14
 */
@Dao
public interface SNCodeDao {
    /**
     * 插入一条序列号（去重）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SNCodeEntity searchHistory);

    @Query("select * from SNCodeEntity where taskCode= :taskCode and snCode= :snCode")
    SNCodeEntity exists(String taskCode, String snCode);

    /**
     * 删除一条序列号
     */
    @Query("delete from SNCodeEntity where taskCode= :taskCode and snCode= :snCode")
    void delete(String taskCode, String snCode);

    /**
     * 删除一批序列号
     */
    @Query("delete from SNCodeEntity where taskCode= :taskCode")
    void deleteByTask(String taskCode);

    /**
     * 删除一批序列号
     */
    @Query("select count(*) from SNCodeEntity where taskCode= :taskCode")
    int count(String taskCode);

    /**
     * 获取所有序列号
     */
    @Query("SELECT * FROM SNCodeEntity where taskCode= :taskCode")
    List<SNCodeEntity> getByTask(String taskCode);

    /**
     * 批量存储
     */
    @Insert
    void saveBatch(List<SNCodeEntity> items);
}
