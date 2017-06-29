package com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 * 班级日常课时
 */
public class GradeClassManage implements Serializable {
    private String manageId;//1
    private String classId;//1班
    private Integer morning;//早上4节课
    private Integer afternoon;//下午3节课

    public GradeClassManage(String manageId, String classId, Integer morning, Integer afternoon) {
        this.manageId = manageId;
        this.classId = classId;
        this.morning = morning;
        this.afternoon = afternoon;
    }

    public String getManageId() {
        return manageId;
    }

    public void setManageId(String manageId) {
        this.manageId = manageId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getMorning() {
        return morning;
    }

    public void setMorning(Integer morning) {
        this.morning = morning;
    }

    public Integer getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(Integer afternoon) {
        this.afternoon = afternoon;
    }
}
