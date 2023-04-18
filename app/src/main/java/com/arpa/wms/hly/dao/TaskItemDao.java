package com.arpa.wms.hly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.arpa.wms.hly.bean.entity.TaskItemEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/31 10:14
 */
@Dao
public interface TaskItemDao {
    /**
     * 删除一批任务明细记录
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(TaskItemEntity taskItem);

    @Query("select * from taskitementity where taskCode= :taskCode and itemCode= :itemCode")
    Flowable<TaskItemEntity> exists(String taskCode, String itemCode);

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
}
