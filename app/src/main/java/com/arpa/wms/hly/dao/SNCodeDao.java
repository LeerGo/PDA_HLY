package com.arpa.wms.hly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.arpa.wms.hly.bean.entity.SNCode;

import java.util.List;

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
    void insert(SNCode snCode);

    /**
     * 检查是否已经录入
     */
    @Query("select * from sncode where snCode= :snCode")
    SNCode exists(String snCode);

    /**
     * 删除一条序列号
     */
    @Query("delete from SNCode where taskCode= :taskCode and taskItemCode= :itemCode and snCode= :snCode")
    void delete(String taskCode, String itemCode, String snCode);

    /**
     * 删除一批序列号
     */
    @Query("delete from SNCode where taskCode= :taskCode and taskItemCode= :itemCode")
    void deleteByTaskItem(String taskCode, String itemCode);

    /**
     * 删除一批序列号
     */
    @Query("delete from SNCode where taskCode= :taskCode and taskItemCode= :itemCode")
    void removeByTaskItem(String taskCode, String itemCode);

    /**
     * 查询任务号下的序列号数量
     */
    @Query("select count(*) from SNCode where taskCode= :taskCode and taskItemCode= :itemCode")
    Integer count(String taskCode, String itemCode);

    /**
     * 获取所有序列号
     */
    @Query("SELECT * FROM SNCode where taskCode= :taskCode and taskItemCode= :itemCode order by snCode desc")
    List<SNCode> getByTask(String taskCode, String itemCode);

    /**
     * 查询任务号下的序列号数量
     */
    @Query("select sum(scanRatio) from SNCode where taskCode= :taskCode and taskItemCode=:itemCode")
    int countRadio(String taskCode, String itemCode);
}
