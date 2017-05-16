package cc.gukeer.smartRing.persistence.entity;

import java.io.Serializable;

public class SportClass implements Serializable {
    private String id;

    private String sportClassId;

    private String schoolId;

    private String sportClassName;

    private String xd;

    private Integer nj;

    private String sportItemId;

    private String techerId;

    private Long sportClassTime;

    private String sportClassPlace;

    private Integer delFlag;

    private String createBy;

    private Long createDate;

    private String updateBy;

    private Long updateDate;

    private Long sort;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSportClassId() {
        return sportClassId;
    }

    public void setSportClassId(String sportClassId) {
        this.sportClassId = sportClassId == null ? null : sportClassId.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getSportClassName() {
        return sportClassName;
    }

    public void setSportClassName(String sportClassName) {
        this.sportClassName = sportClassName == null ? null : sportClassName.trim();
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

    public String getSportItemId() {
        return sportItemId;
    }

    public void setSportItemId(String sportItemId) {
        this.sportItemId = sportItemId == null ? null : sportItemId.trim();
    }

    public String getTecherId() {
        return techerId;
    }

    public void setTecherId(String techerId) {
        this.techerId = techerId == null ? null : techerId.trim();
    }

    public Long getSportClassTime() {
        return sportClassTime;
    }

    public void setSportClassTime(Long sportClassTime) {
        this.sportClassTime = sportClassTime;
    }

    public String getSportClassPlace() {
        return sportClassPlace;
    }

    public void setSportClassPlace(String sportClassPlace) {
        this.sportClassPlace = sportClassPlace == null ? null : sportClassPlace.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
        SportClass other = (SportClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSportClassId() == null ? other.getSportClassId() == null : this.getSportClassId().equals(other.getSportClassId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getSportClassName() == null ? other.getSportClassName() == null : this.getSportClassName().equals(other.getSportClassName()))
            && (this.getXd() == null ? other.getXd() == null : this.getXd().equals(other.getXd()))
            && (this.getNj() == null ? other.getNj() == null : this.getNj().equals(other.getNj()))
            && (this.getSportItemId() == null ? other.getSportItemId() == null : this.getSportItemId().equals(other.getSportItemId()))
            && (this.getTecherId() == null ? other.getTecherId() == null : this.getTecherId().equals(other.getTecherId()))
            && (this.getSportClassTime() == null ? other.getSportClassTime() == null : this.getSportClassTime().equals(other.getSportClassTime()))
            && (this.getSportClassPlace() == null ? other.getSportClassPlace() == null : this.getSportClassPlace().equals(other.getSportClassPlace()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSportClassId() == null) ? 0 : getSportClassId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getSportClassName() == null) ? 0 : getSportClassName().hashCode());
        result = prime * result + ((getXd() == null) ? 0 : getXd().hashCode());
        result = prime * result + ((getNj() == null) ? 0 : getNj().hashCode());
        result = prime * result + ((getSportItemId() == null) ? 0 : getSportItemId().hashCode());
        result = prime * result + ((getTecherId() == null) ? 0 : getTecherId().hashCode());
        result = prime * result + ((getSportClassTime() == null) ? 0 : getSportClassTime().hashCode());
        result = prime * result + ((getSportClassPlace() == null) ? 0 : getSportClassPlace().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sportClassId=").append(sportClassId);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", sportClassName=").append(sportClassName);
        sb.append(", xd=").append(xd);
        sb.append(", nj=").append(nj);
        sb.append(", sportItemId=").append(sportItemId);
        sb.append(", techerId=").append(techerId);
        sb.append(", sportClassTime=").append(sportClassTime);
        sb.append(", sportClassPlace=").append(sportClassPlace);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}