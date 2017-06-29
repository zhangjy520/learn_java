package com.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 * 教师授课信息安排
 */
public class TeacherManage implements Serializable {
    private String manageId;//安排id
    private String teacherId;//1
    private List<String> classIds;//教哪些班级
    private List<String> courseIds;//教哪些课程

    public TeacherManage(String manageId, String teacherId, List<String> classIds, List<String> courseIds) {
        this.manageId = manageId;
        this.teacherId = teacherId;
        this.classIds = classIds;
        this.courseIds = courseIds;
    }

    public String getManageId() {
        return manageId;
    }

    public void setManageId(String manageId) {
        this.manageId = manageId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<String> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<String> classIds) {
        this.classIds = classIds;
    }

    public List<String> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<String> courseIds) {
        this.courseIds = courseIds;
    }
}
