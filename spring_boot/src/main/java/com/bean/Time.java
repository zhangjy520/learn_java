package com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 * 时间片
 */
public class Time implements Serializable {
    private String timeId;//1
    private String timeName;//【1，1】周一第一节课

    public Time(String timeId, String timeName) {
        this.timeId = timeId;
        this.timeName = timeName;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    @Override
    public String toString() {
        return "Time{" +
                "timeId='" + timeId + '\'' +
                ", timeName='" + timeName + '\'' +
                '}';
    }
}
