package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class ChangeStateCourseNode implements Serializable {
    private String id;

    private String syncId;

    private String schoolId;

    private String courseNodeInitId;

    private String cycleId;

    private String cycleYear;

    private Integer cycleSemester;

    private Integer node;

    private String nodeName;

    private Long startTime;

    private Long endTime;

    private String morningAfternoon;

    private Integer week;

    private String summerWinterTime;

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

    public String getCourseNodeInitId() {
        return courseNodeInitId;
    }

    public void setCourseNodeInitId(String courseNodeInitId) {
        this.courseNodeInitId = courseNodeInitId == null ? null : courseNodeInitId.trim();
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

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getMorningAfternoon() {
        return morningAfternoon;
    }

    public void setMorningAfternoon(String morningAfternoon) {
        this.morningAfternoon = morningAfternoon == null ? null : morningAfternoon.trim();
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getSummerWinterTime() {
        return summerWinterTime;
    }

    public void setSummerWinterTime(String summerWinterTime) {
        this.summerWinterTime = summerWinterTime == null ? null : summerWinterTime.trim();
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
        ChangeStateCourseNode other = (ChangeStateCourseNode) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getCourseNodeInitId() == null ? other.getCourseNodeInitId() == null : this.getCourseNodeInitId().equals(other.getCourseNodeInitId()))
            && (this.getCycleId() == null ? other.getCycleId() == null : this.getCycleId().equals(other.getCycleId()))
            && (this.getCycleYear() == null ? other.getCycleYear() == null : this.getCycleYear().equals(other.getCycleYear()))
            && (this.getCycleSemester() == null ? other.getCycleSemester() == null : this.getCycleSemester().equals(other.getCycleSemester()))
            && (this.getNode() == null ? other.getNode() == null : this.getNode().equals(other.getNode()))
            && (this.getNodeName() == null ? other.getNodeName() == null : this.getNodeName().equals(other.getNodeName()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getMorningAfternoon() == null ? other.getMorningAfternoon() == null : this.getMorningAfternoon().equals(other.getMorningAfternoon()))
            && (this.getWeek() == null ? other.getWeek() == null : this.getWeek().equals(other.getWeek()))
            && (this.getSummerWinterTime() == null ? other.getSummerWinterTime() == null : this.getSummerWinterTime().equals(other.getSummerWinterTime()))
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
        result = prime * result + ((getCourseNodeInitId() == null) ? 0 : getCourseNodeInitId().hashCode());
        result = prime * result + ((getCycleId() == null) ? 0 : getCycleId().hashCode());
        result = prime * result + ((getCycleYear() == null) ? 0 : getCycleYear().hashCode());
        result = prime * result + ((getCycleSemester() == null) ? 0 : getCycleSemester().hashCode());
        result = prime * result + ((getNode() == null) ? 0 : getNode().hashCode());
        result = prime * result + ((getNodeName() == null) ? 0 : getNodeName().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getMorningAfternoon() == null) ? 0 : getMorningAfternoon().hashCode());
        result = prime * result + ((getWeek() == null) ? 0 : getWeek().hashCode());
        result = prime * result + ((getSummerWinterTime() == null) ? 0 : getSummerWinterTime().hashCode());
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
        sb.append(", courseNodeInitId=").append(courseNodeInitId);
        sb.append(", cycleId=").append(cycleId);
        sb.append(", cycleYear=").append(cycleYear);
        sb.append(", cycleSemester=").append(cycleSemester);
        sb.append(", node=").append(node);
        sb.append(", nodeName=").append(nodeName);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", morningAfternoon=").append(morningAfternoon);
        sb.append(", week=").append(week);
        sb.append(", summerWinterTime=").append(summerWinterTime);
        sb.append(", event=").append(event);
        sb.append(", source=").append(source);
        sb.append(", syncDate=").append(syncDate);
        sb.append(", syncDelFlag=").append(syncDelFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}