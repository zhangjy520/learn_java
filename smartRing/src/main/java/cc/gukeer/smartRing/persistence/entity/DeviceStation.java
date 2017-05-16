package cc.gukeer.smartRing.persistence.entity;

import java.io.Serializable;

public class DeviceStation implements Serializable {
    private String id;

    private String stationMac;

    private String screenMac;

    private String areaId;

    private String schoolId;

    private Integer category;

    private String classId;

    private String className;

    private Integer type;

    private Integer status;

    private Integer stepFrequency;

    private Integer sleepFrequency;

    private Integer positionCycle;

    private Long lastUpdate;

    private String createBy;

    private Long createDate;

    private String updateBy;

    private Long updateDate;

    private String remarks;

    private Integer delFlag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStationMac() {
        return stationMac;
    }

    public void setStationMac(String stationMac) {
        this.stationMac = stationMac == null ? null : stationMac.trim();
    }

    public String getScreenMac() {
        return screenMac;
    }

    public void setScreenMac(String screenMac) {
        this.screenMac = screenMac == null ? null : screenMac.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStepFrequency() {
        return stepFrequency;
    }

    public void setStepFrequency(Integer stepFrequency) {
        this.stepFrequency = stepFrequency;
    }

    public Integer getSleepFrequency() {
        return sleepFrequency;
    }

    public void setSleepFrequency(Integer sleepFrequency) {
        this.sleepFrequency = sleepFrequency;
    }

    public Integer getPositionCycle() {
        return positionCycle;
    }

    public void setPositionCycle(Integer positionCycle) {
        this.positionCycle = positionCycle;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        DeviceStation other = (DeviceStation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStationMac() == null ? other.getStationMac() == null : this.getStationMac().equals(other.getStationMac()))
            && (this.getScreenMac() == null ? other.getScreenMac() == null : this.getScreenMac().equals(other.getScreenMac()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getClassName() == null ? other.getClassName() == null : this.getClassName().equals(other.getClassName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStepFrequency() == null ? other.getStepFrequency() == null : this.getStepFrequency().equals(other.getStepFrequency()))
            && (this.getSleepFrequency() == null ? other.getSleepFrequency() == null : this.getSleepFrequency().equals(other.getSleepFrequency()))
            && (this.getPositionCycle() == null ? other.getPositionCycle() == null : this.getPositionCycle().equals(other.getPositionCycle()))
            && (this.getLastUpdate() == null ? other.getLastUpdate() == null : this.getLastUpdate().equals(other.getLastUpdate()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStationMac() == null) ? 0 : getStationMac().hashCode());
        result = prime * result + ((getScreenMac() == null) ? 0 : getScreenMac().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStepFrequency() == null) ? 0 : getStepFrequency().hashCode());
        result = prime * result + ((getSleepFrequency() == null) ? 0 : getSleepFrequency().hashCode());
        result = prime * result + ((getPositionCycle() == null) ? 0 : getPositionCycle().hashCode());
        result = prime * result + ((getLastUpdate() == null) ? 0 : getLastUpdate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
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
        sb.append(", stationMac=").append(stationMac);
        sb.append(", screenMac=").append(screenMac);
        sb.append(", areaId=").append(areaId);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", category=").append(category);
        sb.append(", classId=").append(classId);
        sb.append(", className=").append(className);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", stepFrequency=").append(stepFrequency);
        sb.append(", sleepFrequency=").append(sleepFrequency);
        sb.append(", positionCycle=").append(positionCycle);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}