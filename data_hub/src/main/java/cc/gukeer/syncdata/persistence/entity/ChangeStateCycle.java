package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class ChangeStateCycle implements Serializable {
    private String id;

    private String syncId;

    private String name;

    private Integer startWeek;

    private Integer endWeek;

    private String xn;

    private Integer xq;

    private String schoolId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }

    public Integer getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
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
        ChangeStateCycle other = (ChangeStateCycle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getStartWeek() == null ? other.getStartWeek() == null : this.getStartWeek().equals(other.getStartWeek()))
            && (this.getEndWeek() == null ? other.getEndWeek() == null : this.getEndWeek().equals(other.getEndWeek()))
            && (this.getXn() == null ? other.getXn() == null : this.getXn().equals(other.getXn()))
            && (this.getXq() == null ? other.getXq() == null : this.getXq().equals(other.getXq()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
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
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStartWeek() == null) ? 0 : getStartWeek().hashCode());
        result = prime * result + ((getEndWeek() == null) ? 0 : getEndWeek().hashCode());
        result = prime * result + ((getXn() == null) ? 0 : getXn().hashCode());
        result = prime * result + ((getXq() == null) ? 0 : getXq().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", startWeek=").append(startWeek);
        sb.append(", endWeek=").append(endWeek);
        sb.append(", xn=").append(xn);
        sb.append(", xq=").append(xq);
        sb.append(", schoolId=").append(schoolId);
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