package com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 * 教师
 */
public class Teacher implements Serializable {
    private String teacherId;//1
    private String teacherName;//张老师

    public Teacher(String teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
