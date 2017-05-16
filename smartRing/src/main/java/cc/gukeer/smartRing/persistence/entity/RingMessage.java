package cc.gukeer.smartRing.persistence.entity;

import java.io.Serializable;

public class RingMessage implements Serializable {
    private String id;

    private String ringMac;

    private String studentId;

    private  String studentName;

    private String stationMac;

    private String locationName;

    private String balance;

    private Integer level;

    private String version;

    private Long infoDate;

    private Integer caloriesDay;

    private Integer distanceDay;

    private Integer jogDay;

    private Integer runDay;

    private Integer deepSleep;

    private Integer shallowSleep;

    private Integer consciousSleep;

    private Integer awakeTime;

    private Float sleepQuality;

    private Long asleepTime;

    private Integer sportTime;

    private Integer stepDay;

    private Integer walkDay;

    private Integer delFlag;

    private Long createDate;

    private Long lastUpdate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRingMac() {
        return ringMac;
    }

    public void setRingMac(String ringMac) {
        this.ringMac = ringMac == null ? null : ringMac.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStationMac() {
        return stationMac;
    }

    public void setStationMac(String stationMac) {
        this.stationMac = stationMac == null ? null : stationMac.trim();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Long getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Long infoDate) {
        this.infoDate = infoDate;
    }

    public Integer getCaloriesDay() {
        return caloriesDay;
    }

    public void setCaloriesDay(Integer caloriesDay) {
        this.caloriesDay = caloriesDay;
    }

    public Integer getDistanceDay() {
        return distanceDay;
    }

    public void setDistanceDay(Integer distanceDay) {
        this.distanceDay = distanceDay;
    }

    public Integer getJogDay() {
        return jogDay;
    }

    public void setJogDay(Integer jogDay) {
        this.jogDay = jogDay;
    }

    public Integer getRunDay() {
        return runDay;
    }

    public void setRunDay(Integer runDay) {
        this.runDay = runDay;
    }

    public Integer getDeepSleep() {
        return deepSleep;
    }

    public void setDeepSleep(Integer deepSleep) {
        this.deepSleep = deepSleep;
    }

    public Integer getShallowSleep() {
        return shallowSleep;
    }

    public void setShallowSleep(Integer shallowSleep) {
        this.shallowSleep = shallowSleep;
    }

    public Integer getConsciousSleep() {
        return consciousSleep;
    }

    public void setConsciousSleep(Integer consciousSleep) {
        this.consciousSleep = consciousSleep;
    }

    public Integer getAwakeTime() {
        return awakeTime;
    }

    public void setAwakeTime(Integer awakeTime) {
        this.awakeTime = awakeTime;
    }

    public Float getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(Float sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public Long getAsleepTime() {
        return asleepTime;
    }

    public void setAsleepTime(Long asleepTime) {
        this.asleepTime = asleepTime;
    }

    public Integer getSportTime() {
        return sportTime;
    }

    public void setSportTime(Integer sportTime) {
        this.sportTime = sportTime;
    }

    public Integer getStepDay() {
        return stepDay;
    }

    public void setStepDay(Integer stepDay) {
        this.stepDay = stepDay;
    }

    public Integer getWalkDay() {
        return walkDay;
    }

    public void setWalkDay(Integer walkDay) {
        this.walkDay = walkDay;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
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
        RingMessage other = (RingMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRingMac() == null ? other.getRingMac() == null : this.getRingMac().equals(other.getRingMac()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getStudentName() == null ? other.getStudentName() == null : this.getStudentName().equals(other.getStudentName()))
            && (this.getStationMac() == null ? other.getStationMac() == null : this.getStationMac().equals(other.getStationMac()))
            && (this.getLocationName() == null ? other.getLocationName() == null : this.getLocationName().equals(other.getLocationName()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getInfoDate() == null ? other.getInfoDate() == null : this.getInfoDate().equals(other.getInfoDate()))
            && (this.getCaloriesDay() == null ? other.getCaloriesDay() == null : this.getCaloriesDay().equals(other.getCaloriesDay()))
            && (this.getDistanceDay() == null ? other.getDistanceDay() == null : this.getDistanceDay().equals(other.getDistanceDay()))
            && (this.getJogDay() == null ? other.getJogDay() == null : this.getJogDay().equals(other.getJogDay()))
            && (this.getRunDay() == null ? other.getRunDay() == null : this.getRunDay().equals(other.getRunDay()))
            && (this.getDeepSleep() == null ? other.getDeepSleep() == null : this.getDeepSleep().equals(other.getDeepSleep()))
            && (this.getShallowSleep() == null ? other.getShallowSleep() == null : this.getShallowSleep().equals(other.getShallowSleep()))
            && (this.getConsciousSleep() == null ? other.getConsciousSleep() == null : this.getConsciousSleep().equals(other.getConsciousSleep()))
            && (this.getAwakeTime() == null ? other.getAwakeTime() == null : this.getAwakeTime().equals(other.getAwakeTime()))
            && (this.getSleepQuality() == null ? other.getSleepQuality() == null : this.getSleepQuality().equals(other.getSleepQuality()))
            && (this.getAsleepTime() == null ? other.getAsleepTime() == null : this.getAsleepTime().equals(other.getAsleepTime()))
            && (this.getSportTime() == null ? other.getSportTime() == null : this.getSportTime().equals(other.getSportTime()))
            && (this.getStepDay() == null ? other.getStepDay() == null : this.getStepDay().equals(other.getStepDay()))
            && (this.getWalkDay() == null ? other.getWalkDay() == null : this.getWalkDay().equals(other.getWalkDay()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getLastUpdate() == null ? other.getLastUpdate() == null : this.getLastUpdate().equals(other.getLastUpdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRingMac() == null) ? 0 : getRingMac().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getStudentName() == null) ? 0 : getStudentName().hashCode());
        result = prime * result + ((getStationMac() == null) ? 0 : getStationMac().hashCode());
        result = prime * result + ((getLocationName() == null) ? 0 : getLocationName().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getInfoDate() == null) ? 0 : getInfoDate().hashCode());
        result = prime * result + ((getCaloriesDay() == null) ? 0 : getCaloriesDay().hashCode());
        result = prime * result + ((getDistanceDay() == null) ? 0 : getDistanceDay().hashCode());
        result = prime * result + ((getJogDay() == null) ? 0 : getJogDay().hashCode());
        result = prime * result + ((getRunDay() == null) ? 0 : getRunDay().hashCode());
        result = prime * result + ((getDeepSleep() == null) ? 0 : getDeepSleep().hashCode());
        result = prime * result + ((getShallowSleep() == null) ? 0 : getShallowSleep().hashCode());
        result = prime * result + ((getConsciousSleep() == null) ? 0 : getConsciousSleep().hashCode());
        result = prime * result + ((getAwakeTime() == null) ? 0 : getAwakeTime().hashCode());
        result = prime * result + ((getSleepQuality() == null) ? 0 : getSleepQuality().hashCode());
        result = prime * result + ((getAsleepTime() == null) ? 0 : getAsleepTime().hashCode());
        result = prime * result + ((getSportTime() == null) ? 0 : getSportTime().hashCode());
        result = prime * result + ((getStepDay() == null) ? 0 : getStepDay().hashCode());
        result = prime * result + ((getWalkDay() == null) ? 0 : getWalkDay().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getLastUpdate() == null) ? 0 : getLastUpdate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ringMac=").append(ringMac);
        sb.append(", studentId=").append(studentId);
        sb.append(", studentName=").append(studentName);
        sb.append(", stationMac=").append(stationMac);
        sb.append(", locationName=").append(locationName);
        sb.append(", balance=").append(balance);
        sb.append(", level=").append(level);
        sb.append(", version=").append(version);
        sb.append(", infoDate=").append(infoDate);
        sb.append(", caloriesDay=").append(caloriesDay);
        sb.append(", distanceDay=").append(distanceDay);
        sb.append(", jogDay=").append(jogDay);
        sb.append(", runDay=").append(runDay);
        sb.append(", deepSleep=").append(deepSleep);
        sb.append(", shallowSleep=").append(shallowSleep);
        sb.append(", consciousSleep=").append(consciousSleep);
        sb.append(", awakeTime=").append(awakeTime);
        sb.append(", sleepQuality=").append(sleepQuality);
        sb.append(", asleepTime=").append(asleepTime);
        sb.append(", sportTime=").append(sportTime);
        sb.append(", stepDay=").append(stepDay);
        sb.append(", walkDay=").append(walkDay);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createDate=").append(createDate);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}