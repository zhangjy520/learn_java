package com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 * 课表  ：1班  周一第一节课   语文  张老师  302教室
 */
public class ZTable implements Serializable {
    private String tableId;//课表id
    private String classId;//1班
    private String courseId;
    private String teacherId;
    private String timeId;
    private String roomId;

    public ZTable(String tableId, String classId, String courseId, String teacherId, String timeId, String roomId) {
        this.tableId = tableId;
        this.classId = classId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.timeId = timeId;
        this.roomId = roomId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
