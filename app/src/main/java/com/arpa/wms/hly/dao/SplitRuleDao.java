package com.arpa.wms.hly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.arpa.wms.hly.bean.entity.SplitRuleEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-18 17:56
 *
 * <p>
 * dao：规则切分
 * </p>
 */
@Dao
public abstract class SplitRuleDao {
    /**
     * 批量存储
     */
    @Insert
    public abstract Completable saveBatch(List<SplitRuleEntity> items);

    /**
     * 删除所有
     */
    @Query("DELETE FROM SplitRuleEntity")
    public abstract void deleteAll();


    // 重置自增主键的值
    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'SplitRuleEntity'")
    public abstract void resetId();

    @Transaction
    public void resetTable() {
        deleteAll();
        resetId();
    }
}
