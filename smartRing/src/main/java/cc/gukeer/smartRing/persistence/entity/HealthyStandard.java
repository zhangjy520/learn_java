package cc.gukeer.smartRing.persistence.entity;

import java.io.Serializable;

public class HealthyStandard implements Serializable {
    private String id;

    private String schoolId;

    private String xd;

    private Integer nj;

    private Integer sportStandard;

    private Integer sleepStandard;

    private Integer asleepStandard;

    private Long createDate;

    private String createBy;

    private Long updateDate;

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

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd == null ? null : xd.trim();
    }

    public Integer getNj() {
        return nj;
    }

    public void setNj(Integer nj) {
        this.nj = nj;
    }

    public Integer getSportStandard() {
        return sportStandard;
    }

    public void setSportStandard(Integer sportStandard) {
        this.sportStandard = sportStandard;
    }

    public Integer getSleepStandard() {
        return sleepStandard;
    }

    public void setSleepStandard(Integer sleepStandard) {
        this.sleepStandard = sleepStandard;
    }

    public Integer getAsleepStandard() {
        return asleepStandard;
    }

    public void setAsleepStandard(Integer asleepStandard) {
        this.asleepStandard = asleepStandard;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
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
        HealthyStandard other = (HealthyStandard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getXd() == null ? other.getXd() == null : this.getXd().equals(other.getXd()))
            && (this.getNj() == null ? other.getNj() == null : this.getNj().equals(other.getNj()))
            && (this.getSportStandard() == null ? other.getSportStandard() == null : this.getSportStandard().equals(other.getSportStandard()))
            && (this.getSleepStandard() == null ? other.getSleepStandard() == null : this.getSleepStandard().equals(other.getSleepStandard()))
            && (this.getAsleepStandard() == null ? other.getAsleepStandard() == null : this.getAsleepStandard().equals(other.getAsleepStandard()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getXd() == null) ? 0 : getXd().hashCode());
        result = prime * result + ((getNj() == null) ? 0 : getNj().hashCode());
        result = prime * result + ((getSportStandard() == null) ? 0 : getSportStandard().hashCode());
        result = prime * result + ((getSleepStandard() == null) ? 0 : getSleepStandard().hashCode());
        result = prime * result + ((getAsleepStandard() == null) ? 0 : getAsleepStandard().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
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
        sb.append(", xd=").append(xd);
        sb.append(", nj=").append(nj);
        sb.append(", sportStandard=").append(sportStandard);
        sb.append(", sleepStandard=").append(sleepStandard);
        sb.append(", asleepStandard=").append(asleepStandard);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}