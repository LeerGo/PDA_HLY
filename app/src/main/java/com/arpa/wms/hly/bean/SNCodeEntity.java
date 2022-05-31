package com.arpa.wms.hly.bean;

import java.util.Objects;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/31 10:14
 */
@Entity(indices = {@Index(value = "taskCode")})
public class SNCodeEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String taskCode;
    private String snCode;

    public SNCodeEntity() {
    }

    @Ignore
    public SNCodeEntity(String taskCode, String snCode) {
        this.taskCode = taskCode;
        this.snCode = snCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskCode, snCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SNCodeEntity)) return false;
        SNCodeEntity that = (SNCodeEntity) o;
        return taskCode.equals(that.taskCode) && snCode.equals(that.snCode);
    }

    @Override
    public String toString() {
        return "SNCodeEntity{" +
                "id=" + id +
                ", taskCode='" + taskCode + '\'' +
                ", snCode='" + snCode + '\'' +
                '}';
    }
}
