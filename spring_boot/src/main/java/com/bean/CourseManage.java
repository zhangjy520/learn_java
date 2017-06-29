package com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 * 课程安排，每个学期几个课时
 */
public class CourseManage implements Serializable {
    private String courseId;//1
    private String courseName;//语文
    private Integer courseTime;//一个学期要上100节语文课学完，假如20周，每周得安排5节，假如101节，最后一周上6节

    public CourseManage(String courseId, String courseName, Integer courseTime) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTime = courseTime;
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

    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }
}
