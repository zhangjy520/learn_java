package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class ChangeStateCourseManage implements Serializable {
    private String id;

    private String syncId;

    private String courseId;

    private String xn;

    private Integer xq;

    private Integer courseTime;

    private String classType;

    private String periodId;

    private String classId;

    private String event;

    private String source;

    private String syncDate;

    private String syncDelFlag;

    private Long updateDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId == null ? null : syncId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn == null ? null : xn.trim();
    }

    public Integer getXq() {
        return xq;
    }

    public void setXq(Integer xq) {
        this.xq = xq;
    }

    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType == null ? null : classType.trim();
    }

    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId == null ? null : periodId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate == null ? null : syncDate.trim();
    }

    public String getSyncDelFlag() {
        return syncDelFlag;
    }

    public void setSyncDelFlag(String syncDelFlag) {
        this.syncDelFlag = syncDelFlag == null ? null : syncDelFlag.trim();
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
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
        ChangeStateCourseManage other = (ChangeStateCourseManage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getXn() == null ? other.getXn() == null : this.getXn().equals(other.getXn()))
            && (this.getXq() == null ? other.getXq() == null : this.getXq().equals(other.getXq()))
            && (this.getCourseTime() == null ? other.getCourseTime() == null : this.getCourseTime().equals(other.getCourseTime()))
            && (this.getClassType() == null ? other.getClassType() == null : this.getClassType().equals(other.getClassType()))
            && (this.getPeriodId() == null ? other.getPeriodId() == null : this.getPeriodId().equals(other.getPeriodId()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getEvent() == null ? other.getEvent() == null : this.getEvent().equals(other.getEvent()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getSyncDate() == null ? other.getSyncDate() == null : this.getSyncDate().equals(other.getSyncDate()))
            && (this.getSyncDelFlag() == null ? other.getSyncDelFlag() == null : this.getSyncDelFlag().equals(other.getSyncDelFlag()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSyncId() == null) ? 0 : getSyncId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getXn() == null) ? 0 : getXn().hashCode());
        result = prime * result + ((getXq() == null) ? 0 : getXq().hashCode());
        result = prime * result + ((getCourseTime() == null) ? 0 : getCourseTime().hashCode());
        result = prime * result + ((getClassType() == null) ? 0 : getClassType().hashCode());
        result = prime * result + ((getPeriodId() == null) ? 0 : getPeriodId().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getEvent() == null) ? 0 : getEvent().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getSyncDate() == null) ? 0 : getSyncDate().hashCode());
        result = prime * result + ((getSyncDelFlag() == null) ? 0 : getSyncDelFlag().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", syncId=").append(syncId);
        sb.append(", courseId=").append(courseId);
        sb.append(", xn=").append(xn);
        sb.append(", xq=").append(xq);
        sb.append(", courseTime=").append(courseTime);
        sb.append(", classType=").append(classType);
        sb.append(", periodId=").append(periodId);
        sb.append(", classId=").append(classId);
        sb.append(", event=").append(event);
        sb.append(", source=").append(source);
        sb.append(", syncDate=").append(syncDate);
        sb.append(", syncDelFlag=").append(syncDelFlag);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}