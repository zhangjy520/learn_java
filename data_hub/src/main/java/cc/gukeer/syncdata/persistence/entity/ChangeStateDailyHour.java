package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class ChangeStateDailyHour implements Serializable {
    private String id;

    private String syncId;

    private String schoolId;

    private String classId;

    private Integer skts;

    private Integer swks;

    private Integer xwks;

    private Integer kjc;

    private String periodId;

    private String xn;

    private String xq;

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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public Integer getSkts() {
        return skts;
    }

    public void setSkts(Integer skts) {
        this.skts = skts;
    }

    public Integer getSwks() {
        return swks;
    }

    public void setSwks(Integer swks) {
        this.swks = swks;
    }

    public Integer getXwks() {
        return xwks;
    }

    public void setXwks(Integer xwks) {
        this.xwks = xwks;
    }

    public Integer getKjc() {
        return kjc;
    }

    public void setKjc(Integer kjc) {
        this.kjc = kjc;
    }

    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId == null ? null : periodId.trim();
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn == null ? null : xn.trim();
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq == null ? null : xq.trim();
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
        ChangeStateDailyHour other = (ChangeStateDailyHour) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getSkts() == null ? other.getSkts() == null : this.getSkts().equals(other.getSkts()))
            && (this.getSwks() == null ? other.getSwks() == null : this.getSwks().equals(other.getSwks()))
            && (this.getXwks() == null ? other.getXwks() == null : this.getXwks().equals(other.getXwks()))
            && (this.getKjc() == null ? other.getKjc() == null : this.getKjc().equals(other.getKjc()))
            && (this.getPeriodId() == null ? other.getPeriodId() == null : this.getPeriodId().equals(other.getPeriodId()))
            && (this.getXn() == null ? other.getXn() == null : this.getXn().equals(other.getXn()))
            && (this.getXq() == null ? other.getXq() == null : this.getXq().equals(other.getXq()))
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
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getSkts() == null) ? 0 : getSkts().hashCode());
        result = prime * result + ((getSwks() == null) ? 0 : getSwks().hashCode());
        result = prime * result + ((getXwks() == null) ? 0 : getXwks().hashCode());
        result = prime * result + ((getKjc() == null) ? 0 : getKjc().hashCode());
        result = prime * result + ((getPeriodId() == null) ? 0 : getPeriodId().hashCode());
        result = prime * result + ((getXn() == null) ? 0 : getXn().hashCode());
        result = prime * result + ((getXq() == null) ? 0 : getXq().hashCode());
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
        sb.append(", schoolId=").append(schoolId);
        sb.append(", classId=").append(classId);
        sb.append(", skts=").append(skts);
        sb.append(", swks=").append(swks);
        sb.append(", xwks=").append(xwks);
        sb.append(", kjc=").append(kjc);
        sb.append(", periodId=").append(periodId);
        sb.append(", xn=").append(xn);
        sb.append(", xq=").append(xq);
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