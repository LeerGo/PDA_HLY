package com.arpa.wms.hly.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.arpa.wms.hly.utils.DateUtils;
import com.arpa.wms.hly.utils.RexUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2022/5/31 10:14
 */
// TODO: 数据库变更过大，之前暂存的数据会丢失，告知项目、测试 add by 李一方 2022-06-27 10:59:50
@Entity(indices = {@Index(value = "taskCode")})
public class SNCodeEntity implements Comparable<SNCodeEntity>, Parcelable {
    public static final Parcelable.Creator<SNCodeEntity> CREATOR = new Parcelable.Creator<>() {
        @Override
        public SNCodeEntity createFromParcel(Parcel source) {return new SNCodeEntity(source);}

        @Override
        public SNCodeEntity[] newArray(int size) {return new SNCodeEntity[size];}
    };

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String taskCode;
    private String snCode; // 批次号
    private String briefDate; // 简要日期，格式 yyyy-MM-dd
    private String briefTime; // 简要时间，格式 HH:mm:ss
    private String placeOrigin; // 简要日期，格式 yyyy-MM-dd
    private Date date; // 转化为日期
    private boolean isOriginVerify; // 产地校验，true - 通过
    private boolean isDateVerify; // 生产日期校验, true - 通过
    private boolean isTimeVerify; // 生产时间校验，true - 通过

    public SNCodeEntity() {
    }

    @Ignore
    public SNCodeEntity(String taskCode, String snCode) {
        this.taskCode = taskCode;
        setSnCode(snCode);
    }

    protected SNCodeEntity(Parcel in) {
        this.id = in.readLong();
        this.taskCode = in.readString();
        this.snCode = in.readString();
        this.briefDate = in.readString();
        this.briefTime = in.readString();
        this.placeOrigin = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.isOriginVerify = in.readByte() != 0;
        this.isDateVerify = in.readByte() != 0;
        this.isTimeVerify = in.readByte() != 0;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
        setDate(formatBatchDate(batch2DateStr()));
        setPlaceOrigin(snCode.substring(8, snCode.indexOf(":") - 2));
    }

    private String batch2DateStr() {
        var date = snCode.substring(0, 8);
        int timeIndex = snCode.indexOf(":") - 2;
        var time = snCode.substring(timeIndex, timeIndex + 8);
        setBriefTime(time);
        setBriefDate(new StringBuilder(date).insert(4, "-").insert(7, "-").toString());
        return date.concat(time);
    }

    private Date formatBatchDate(String dateBatch) {
        try {
            return DateUtils.formatBatch.parse(dateBatch);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 校验产地、生产日期、生产时间合法性
     *
     * @param rawDate   目标生产日期
     * @param rawOrigin 目标产地
     */
    public void verify(String rawDate, String rawOrigin) {
        isDateVerify = briefDate.equals(rawDate);
        isOriginVerify = placeOrigin.equals(rawOrigin);
        isTimeVerify = RexUtils.is24Hour(briefTime);
    }

    /**
     * 日期是否超出当天
     *
     * @return true - 超出
     */
    public boolean isMoreToday() {
        return DateUtils.isMoreToday(date);
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    @Override
    public int compareTo(SNCodeEntity o) {
        return this.date.compareTo(o.getDate());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBriefDate() {
        return briefDate;
    }

    public void setBriefDate(String briefDate) {
        this.briefDate = briefDate;
    }

    public String getBriefTime() {
        return briefTime;
    }

    public void setBriefTime(String briefTime) {
        this.briefTime = briefTime;
    }

    public boolean isOriginVerify() {
        return isOriginVerify;
    }

    public void setOriginVerify(boolean originVerify) {
        isOriginVerify = originVerify;
    }

    public boolean isDateVerify() {
        return isDateVerify;
    }

    public void setDateVerify(boolean dateVerify) {
        isDateVerify = dateVerify;
    }

    public boolean isTimeVerify() {
        return isTimeVerify;
    }

    public void setTimeVerify(boolean timeVerify) {
        isTimeVerify = timeVerify;
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
        return "SNCodeEntity{" + "id=" + id + ", taskCode='" + taskCode + '\'' + ", snCode='" + snCode + '\'' + '}';
    }

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.taskCode);
        dest.writeString(this.snCode);
        dest.writeString(this.briefDate);
        dest.writeString(this.briefTime);
        dest.writeString(this.placeOrigin);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeByte(this.isOriginVerify ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDateVerify ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTimeVerify ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.taskCode = source.readString();
        this.snCode = source.readString();
        this.briefDate = source.readString();
        this.briefTime = source.readString();
        this.placeOrigin = source.readString();
        long tmpDate = source.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.isOriginVerify = source.readByte() != 0;
        this.isDateVerify = source.readByte() != 0;
        this.isTimeVerify = source.readByte() != 0;
    }
}
