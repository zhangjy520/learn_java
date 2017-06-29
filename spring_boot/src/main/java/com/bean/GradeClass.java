package com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 * 班级
 */
public class GradeClass implements Serializable {
    private String classId;//1
    private String className;//1班

    public GradeClass(String classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
