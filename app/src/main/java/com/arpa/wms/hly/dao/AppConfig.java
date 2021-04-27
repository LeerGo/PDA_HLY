package com.arpa.wms.hly.dao;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Deprecated
@Entity
public class AppConfig {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long createTime;

    public AppConfig() {
        createTime = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

}