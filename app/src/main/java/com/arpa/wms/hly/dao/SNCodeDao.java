package com.arpa.wms.hly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.arpa.wms.hly.bean.entity.SNCode;
import com.arpa.wms.hly.bean.entity.SNCodeEntity;

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
public interface SNCodeDao {
    /**
     * 插入一条序列号（去重）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SNCode snCode);

    /**
     * 插入一条序列号（去重）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SNCodeEntity searchHistory);

    @Query("select * from SNCodeEntity where taskCode= :taskCode and snCode= :snCode")
    Flowable<SNCodeEntity> exists(String taskCode, String snCode);

    @Query("select * from sncode where snCode= :snCode")
    SNCode exists(String snCode);

    /**
     * 删除一条序列号
     */
    @Query("delete from SNCodeEntity where taskCode= :taskCode and snCode= :snCode")
    Completable delete(String taskCode, String snCode);

    /**
     * 删除一批序列号
     */
    @Query("delete from SNCodeEntity where taskCode= :taskCode")
    Completable deleteByTask(String taskCode);

    /**
     * 查询任务号下的序列号数量
     */
    @Query("select count(*) from SNCodeEntity where taskCode= :taskCode")
    Single<Integer> count(String taskCode);

    /**
     * 获取所有序列号
     */
    @Query("SELECT * FROM SNCodeEntity where taskCode= :taskCode order by snCode desc")
    Single<List<SNCodeEntity>> getByTask(String taskCode);

    /**
     * 批量存储
     */
    @Insert
    Completable saveBatch(List<SNCodeEntity> items);

    /**
     * 查询任务号下的序列号数量
     */
    @Query("select sum(scanRatio) from SNCode where taskCode= :taskCode and taskItemCode=:itemCode")
    Integer countRadio(String taskCode, String itemCode);
}
