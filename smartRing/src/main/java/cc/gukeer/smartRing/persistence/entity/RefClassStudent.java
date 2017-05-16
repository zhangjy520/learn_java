package cc.gukeer.smartRing.persistence.entity;

import java.io.Serializable;

public class RefClassStudent implements Serializable {
    private String sportClassId;

    private String studentId;

    private Long sort;

    private static final long serialVersionUID = 1L;

    public String getSportClassId() {
        return sportClassId;
    }

    public void setSportClassId(String sportClassId) {
        this.sportClassId = sportClassId == null ? null : sportClassId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
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
        RefClassStudent other = (RefClassStudent) that;
        return (this.getSportClassId() == null ? other.getSportClassId() == null : this.getSportClassId().equals(other.getSportClassId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSportClassId() == null) ? 0 : getSportClassId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sportClassId=").append(sportClassId);
        sb.append(", studentId=").append(studentId);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}