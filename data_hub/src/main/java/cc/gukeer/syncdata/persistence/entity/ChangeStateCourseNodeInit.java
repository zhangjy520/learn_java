package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class ChangeStateCourseNodeInit implements Serializable {
    private String id;

    private String syncId;

    private String schoolId;

    private String name;

    private String cycleId;

    private String cycleYear;

    private Integer cycleSemester;

    private Integer startWeek;

    private Integer endWeek;

    private String event;

    private String source;

    private Long syncDate;

    private String syncDelFlag;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId == null ? null : cycleId.trim();
    }

    public String getCycleYear() {
        return cycleYear;
    }

    public void setCycleYear(String cycleYear) {
        this.cycleYear = cycleYear == null ? null : cycleYear.trim();
    }

    public Integer getCycleSemester() {
        return cycleSemester;
    }

    public void setCycleSemester(Integer cycleSemester) {
        this.cycleSemester = cycleSemester;
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

    public Long getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Long syncDate) {
        this.syncDate = syncDate;
    }

    public String getSyncDelFlag() {
        return syncDelFlag;
    }

    public void setSyncDelFlag(String syncDelFlag) {
        this.syncDelFlag = syncDelFlag == null ? null : syncDelFlag.trim();
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
        ChangeStateCourseNodeInit other = (ChangeStateCourseNodeInit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCycleId() == null ? other.getCycleId() == null : this.getCycleId().equals(other.getCycleId()))
            && (this.getCycleYear() == null ? other.getCycleYear() == null : this.getCycleYear().equals(other.getCycleYear()))
            && (this.getCycleSemester() == null ? other.getCycleSemester() == null : this.getCycleSemester().equals(other.getCycleSemester()))
            && (this.getStartWeek() == null ? other.getStartWeek() == null : this.getStartWeek().equals(other.getStartWeek()))
            && (this.getEndWeek() == null ? other.getEndWeek() == null : this.getEndWeek().equals(other.getEndWeek()))
            && (this.getEvent() == null ? other.getEvent() == null : this.getEvent().equals(other.getEvent()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getSyncDate() == null ? other.getSyncDate() == null : this.getSyncDate().equals(other.getSyncDate()))
            && (this.getSyncDelFlag() == null ? other.getSyncDelFlag() == null : this.getSyncDelFlag().equals(other.getSyncDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSyncId() == null) ? 0 : getSyncId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCycleId() == null) ? 0 : getCycleId().hashCode());
        result = prime * result + ((getCycleYear() == null) ? 0 : getCycleYear().hashCode());
        result = prime * result + ((getCycleSemester() == null) ? 0 : getCycleSemester().hashCode());
        result = prime * result + ((getStartWeek() == null) ? 0 : getStartWeek().hashCode());
        result = prime * result + ((getEndWeek() == null) ? 0 : getEndWeek().hashCode());
        result = prime * result + ((getEvent() == null) ? 0 : getEvent().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getSyncDate() == null) ? 0 : getSyncDate().hashCode());
        result = prime * result + ((getSyncDelFlag() == null) ? 0 : getSyncDelFlag().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", cycleId=").append(cycleId);
        sb.append(", cycleYear=").append(cycleYear);
        sb.append(", cycleSemester=").append(cycleSemester);
        sb.append(", startWeek=").append(startWeek);
        sb.append(", endWeek=").append(endWeek);
        sb.append(", event=").append(event);
        sb.append(", source=").append(source);
        sb.append(", syncDate=").append(syncDate);
        sb.append(", syncDelFlag=").append(syncDelFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}