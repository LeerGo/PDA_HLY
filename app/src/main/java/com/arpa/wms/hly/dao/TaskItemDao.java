package com.arpa.wms.hly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.arpa.wms.hly.bean.entity.TaskItemEntity;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/31 10:14
 */
@Dao
public interface TaskItemDao {
    /**
     * 插入一批任务明细记录
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(TaskItemEntity taskItem);

    /**
     * 删除一批任务明细记录
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(TaskItemEntity taskItem);

    @Query("select * from taskitementity where taskCode= :taskCode and itemCode= :itemCode")
    TaskItemEntity exists(String taskCode, String itemCode);

    /**
     * 获取所有明细详情
     */
    @Query("SELECT * FROM TaskItemEntity where taskCode= :taskCode ")
    Single<List<TaskItemEntity>> getByTask(String taskCode);

    /**
     * 删除一批任务明细记录
     */
    @Query("delete from TaskItemEntity where taskCode= :taskCode")
    Completable deleteByTask(String taskCode);

    /**
     * 删除一批任务明细记录
     */
    @Query("delete from TaskItemEntity where taskCode= :taskCode and itemCode= :itemCode")
    void deleteByTaskItem(String taskCode, String itemCode);

    /**
     * 更新扫码率
     */
    @Query("update TaskItemEntity set ratio=:ratio where taskCode= :taskCode and itemCode=:itemCode")
    void updateTaskRatio(String taskCode, String itemCode, BigDecimal ratio);

    /**
     * 更新换箱比
     */
    @Query("update TaskItemEntity set scanRatio=:ratio where taskCode= :taskCode and itemCode=:itemCode")
    void updateScanRatio(String taskCode, String itemCode, Integer ratio);

    /**
     * 获取换箱比
     */
    @Query("select scanRatio from taskitementity where taskCode= :taskCode and itemCode=:itemCode")
    Integer queryTaskRatio(String taskCode, String itemCode);
}
