package cn.gukeer.platform.persistence.entity;

import java.io.Serializable;

/**
 * Created by LL on 2017/5/10.
 */
public class A_CourseClassHour implements Serializable{
    private String sectionId;
    private Integer nj;
    private Integer courseHour;

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getNj() {
        return nj;
    }

    public void setNj(Integer nj) {
        this.nj = nj;
    }

    public Integer getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(Integer courseHour) {
        this.courseHour = courseHour;
    }
}
