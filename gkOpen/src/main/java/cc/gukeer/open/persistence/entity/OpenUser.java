package cc.gukeer.open.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class OpenUser implements Serializable {
    private String id;

    private String username;

    private String password;

    private Integer status;

    private Integer refId;

    private Integer userType;

    private Integer loginFlag;

    private String loginMark;

    private String createBy;

    private Long createDate;

    private String updateBy;

    private Long updateDate;

    private String remarks;

    private Integer agreementNum;

    private Integer delFlag;

    private Integer registerStatus;

    private Long currentTimeSendemail;

    private String random;

    private Date lastLoginTime;

    private String companyId;

    private String personalId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getLoginMark() {
        return loginMark;
    }

    public void setLoginMark(String loginMark) {
        this.loginMark = loginMark == null ? null : loginMark.trim();
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

    public Integer getAgreementNum() {
        return agreementNum;
    }

    public void setAgreementNum(Integer agreementNum) {
        this.agreementNum = agreementNum;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    public Long getCurrentTimeSendemail() {
        return currentTimeSendemail;
    }

    public void setCurrentTimeSendemail(Long currentTimeSendemail) {
        this.currentTimeSendemail = currentTimeSendemail;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random == null ? null : random.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId == null ? null : personalId.trim();
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
        OpenUser other = (OpenUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRefId() == null ? other.getRefId() == null : this.getRefId().equals(other.getRefId()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getLoginFlag() == null ? other.getLoginFlag() == null : this.getLoginFlag().equals(other.getLoginFlag()))
            && (this.getLoginMark() == null ? other.getLoginMark() == null : this.getLoginMark().equals(other.getLoginMark()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getAgreementNum() == null ? other.getAgreementNum() == null : this.getAgreementNum().equals(other.getAgreementNum()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getRegisterStatus() == null ? other.getRegisterStatus() == null : this.getRegisterStatus().equals(other.getRegisterStatus()))
            && (this.getCurrentTimeSendemail() == null ? other.getCurrentTimeSendemail() == null : this.getCurrentTimeSendemail().equals(other.getCurrentTimeSendemail()))
            && (this.getRandom() == null ? other.getRandom() == null : this.getRandom().equals(other.getRandom()))
            && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getPersonalId() == null ? other.getPersonalId() == null : this.getPersonalId().equals(other.getPersonalId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRefId() == null) ? 0 : getRefId().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getLoginFlag() == null) ? 0 : getLoginFlag().hashCode());
        result = prime * result + ((getLoginMark() == null) ? 0 : getLoginMark().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getAgreementNum() == null) ? 0 : getAgreementNum().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getRegisterStatus() == null) ? 0 : getRegisterStatus().hashCode());
        result = prime * result + ((getCurrentTimeSendemail() == null) ? 0 : getCurrentTimeSendemail().hashCode());
        result = prime * result + ((getRandom() == null) ? 0 : getRandom().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getPersonalId() == null) ? 0 : getPersonalId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", status=").append(status);
        sb.append(", refId=").append(refId);
        sb.append(", userType=").append(userType);
        sb.append(", loginFlag=").append(loginFlag);
        sb.append(", loginMark=").append(loginMark);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", agreementNum=").append(agreementNum);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", registerStatus=").append(registerStatus);
        sb.append(", currentTimeSendemail=").append(currentTimeSendemail);
        sb.append(", random=").append(random);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", companyId=").append(companyId);
        sb.append(", personalId=").append(personalId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}