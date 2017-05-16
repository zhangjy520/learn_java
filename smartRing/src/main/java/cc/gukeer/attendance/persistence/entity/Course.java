package cc.gukeer.attendance.persistence.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private String id;

    private String courseId;

    private String teacherid;

    private String courseName;

    private Long classBegin;

    private Long classEnd;

    private String classPlace;

    private String flagWeek;

    private String classId;

    private String must;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Long getClassBegin() {
        return classBegin;
    }

    public void setClassBegin(Long classBegin) {
        this.classBegin = classBegin;
    }

    public Long getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(Long classEnd) {
        this.classEnd = classEnd;
    }

    public String getClassPlace() {
        return classPlace;
    }

    public void setClassPlace(String classPlace) {
        this.classPlace = classPlace == null ? null : classPlace.trim();
    }

    public String getFlagWeek() {
        return flagWeek;
    }

    public void setFlagWeek(String flagWeek) {
        this.flagWeek = flagWeek == null ? null : flagWeek.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getMust() {
        return must;
    }

    public void setMust(String must) {
        this.must = must == null ? null : must.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getTeacherid() == null ? other.getTeacherid() == null : this.getTeacherid().equals(other.getTeacherid()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getClassBegin() == null ? other.getClassBegin() == null : this.getClassBegin().equals(other.getClassBegin()))
            && (this.getClassEnd() == null ? other.getClassEnd() == null : this.getClassEnd().equals(other.getClassEnd()))
            && (this.getClassPlace() == null ? other.getClassPlace() == null : this.getClassPlace().equals(other.getClassPlace()))
            && (this.getFlagWeek() == null ? other.getFlagWeek() == null : this.getFlagWeek().equals(other.getFlagWeek()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getMust() == null ? other.getMust() == null : this.getMust().equals(other.getMust()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getTeacherid() == null) ? 0 : getTeacherid().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getClassBegin() == null) ? 0 : getClassBegin().hashCode());
        result = prime * result + ((getClassEnd() == null) ? 0 : getClassEnd().hashCode());
        result = prime * result + ((getClassPlace() == null) ? 0 : getClassPlace().hashCode());
        result = prime * result + ((getFlagWeek() == null) ? 0 : getFlagWeek().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getMust() == null) ? 0 : getMust().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", teacherid=").append(teacherid);
        sb.append(", courseName=").append(courseName);
        sb.append(", classBegin=").append(classBegin);
        sb.append(", classEnd=").append(classEnd);
        sb.append(", classPlace=").append(classPlace);
        sb.append(", flagWeek=").append(flagWeek);
        sb.append(", classId=").append(classId);
        sb.append(", must=").append(must);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}