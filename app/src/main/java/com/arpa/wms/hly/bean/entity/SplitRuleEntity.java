package com.arpa.wms.hly.bean.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.arpa.wms.hly.bean.res.ResTaskAssign;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2023-04-18 17:55
 *
 * <p>
 * 切分规则
 * </p>
 */
@Entity
public class SplitRuleEntity {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String code;
    private Long timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    // FIXME: 测试方法，待删除 add by 李一方 2023-04-20 10:04:52
    public void convert(ResTaskAssign data) {
        this.code = data.getCode();
        this.timestamp = System.currentTimeMillis();
    }
}
