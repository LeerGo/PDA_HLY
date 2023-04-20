package com.arpa.wms.hly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.arpa.wms.hly.bean.entity.SplitRuleEntity;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

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
public interface SplitRuleDao {
    /**
     * 批量存储
     */
    @Insert
    Completable saveBatch(List<SplitRuleEntity> items);

    @Query("SELECT timestamp FROM SplitRuleEntity ORDER BY timestamp desc LIMIT 1")
    Observable<Optional<Long>> getLastTime();
}
