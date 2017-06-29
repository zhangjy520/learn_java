package com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 * 课程
 */
public class Course implements Serializable {
    private String courseId;//1
    private String courseName;//语文

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
