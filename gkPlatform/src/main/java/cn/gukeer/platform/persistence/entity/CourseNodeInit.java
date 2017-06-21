package cn.gukeer.platform.persistence.entity;

import java.io.Serializable;

public class CourseNodeInit implements Serializable {
    private String id;

    private String schoolId;

    private String cycleId;

    private String cycleYear;

    private Integer cycleSemester;

    private Long morningStart;

    private Integer morningPersistence;

    private Integer commonPersistence;

    private Integer totalNode;

    private Long afternoonStart;

    private Long nightStart;

    private String monthStartEnd;

    private String monthStartEndName;

    private Long createTime;

    private String updateBy;

    private Integer delFlag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
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

    public Long getMorningStart() {
        return morningStart;
    }

    public void setMorningStart(Long morningStart) {
        this.morningStart = morningStart;
    }

    public Integer getMorningPersistence() {
        return morningPersistence;
    }

    public void setMorningPersistence(Integer morningPersistence) {
        this.morningPersistence = morningPersistence;
    }

    public Integer getCommonPersistence() {
        return commonPersistence;
    }

    public void setCommonPersistence(Integer commonPersistence) {
        this.commonPersistence = commonPersistence;
    }

    public Integer getTotalNode() {
        return totalNode;
    }

    public void setTotalNode(Integer totalNode) {
        this.totalNode = totalNode;
    }

    public Long getAfternoonStart() {
        return afternoonStart;
    }

    public void setAfternoonStart(Long afternoonStart) {
        this.afternoonStart = afternoonStart;
    }

    public Long getNightStart() {
        return nightStart;
    }

    public void setNightStart(Long nightStart) {
        this.nightStart = nightStart;
    }

    public String getMonthStartEnd() {
        return monthStartEnd;
    }

    public void setMonthStartEnd(String monthStartEnd) {
        this.monthStartEnd = monthStartEnd == null ? null : monthStartEnd.trim();
    }

    public String getMonthStartEndName() {
        return monthStartEndName;
    }

    public void setMonthStartEndName(String monthStartEndName) {
        this.monthStartEndName = monthStartEndName == null ? null : monthStartEndName.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
        CourseNodeInit other = (CourseNodeInit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getCycleId() == null ? other.getCycleId() == null : this.getCycleId().equals(other.getCycleId()))
            && (this.getCycleYear() == null ? other.getCycleYear() == null : this.getCycleYear().equals(other.getCycleYear()))
            && (this.getCycleSemester() == null ? other.getCycleSemester() == null : this.getCycleSemester().equals(other.getCycleSemester()))
            && (this.getMorningStart() == null ? other.getMorningStart() == null : this.getMorningStart().equals(other.getMorningStart()))
            && (this.getMorningPersistence() == null ? other.getMorningPersistence() == null : this.getMorningPersistence().equals(other.getMorningPersistence()))
            && (this.getCommonPersistence() == null ? other.getCommonPersistence() == null : this.getCommonPersistence().equals(other.getCommonPersistence()))
            && (this.getTotalNode() == null ? other.getTotalNode() == null : this.getTotalNode().equals(other.getTotalNode()))
            && (this.getAfternoonStart() == null ? other.getAfternoonStart() == null : this.getAfternoonStart().equals(other.getAfternoonStart()))
            && (this.getNightStart() == null ? other.getNightStart() == null : this.getNightStart().equals(other.getNightStart()))
            && (this.getMonthStartEnd() == null ? other.getMonthStartEnd() == null : this.getMonthStartEnd().equals(other.getMonthStartEnd()))
            && (this.getMonthStartEndName() == null ? other.getMonthStartEndName() == null : this.getMonthStartEndName().equals(other.getMonthStartEndName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getCycleId() == null) ? 0 : getCycleId().hashCode());
        result = prime * result + ((getCycleYear() == null) ? 0 : getCycleYear().hashCode());
        result = prime * result + ((getCycleSemester() == null) ? 0 : getCycleSemester().hashCode());
        result = prime * result + ((getMorningStart() == null) ? 0 : getMorningStart().hashCode());
        result = prime * result + ((getMorningPersistence() == null) ? 0 : getMorningPersistence().hashCode());
        result = prime * result + ((getCommonPersistence() == null) ? 0 : getCommonPersistence().hashCode());
        result = prime * result + ((getTotalNode() == null) ? 0 : getTotalNode().hashCode());
        result = prime * result + ((getAfternoonStart() == null) ? 0 : getAfternoonStart().hashCode());
        result = prime * result + ((getNightStart() == null) ? 0 : getNightStart().hashCode());
        result = prime * result + ((getMonthStartEnd() == null) ? 0 : getMonthStartEnd().hashCode());
        result = prime * result + ((getMonthStartEndName() == null) ? 0 : getMonthStartEndName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", cycleId=").append(cycleId);
        sb.append(", cycleYear=").append(cycleYear);
        sb.append(", cycleSemester=").append(cycleSemester);
        sb.append(", morningStart=").append(morningStart);
        sb.append(", morningPersistence=").append(morningPersistence);
        sb.append(", commonPersistence=").append(commonPersistence);
        sb.append(", totalNode=").append(totalNode);
        sb.append(", afternoonStart=").append(afternoonStart);
        sb.append(", nightStart=").append(nightStart);
        sb.append(", monthStartEnd=").append(monthStartEnd);
        sb.append(", monthStartEndName=").append(monthStartEndName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}