package cc.gukeer.open.persistence.entity;

import java.io.Serializable;

public class App implements Serializable {
    private String id;

    private String name;

    private String logo;

    private Integer category;

    private String targetUser;

    private String version;

    private Integer isFree;

    private String appScreenshot;

    private String appAbstruct;

    private String appUrl;

    private String demoAccount;

    private String userId;

    private String appSecret;

    private Integer delFlag;

    private Integer checkStatus;

    private Integer appRank;

    private Long createDate;

    private Long updateDate;

    private String clientId;

    private String clientSecret;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser == null ? null : targetUser.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getAppScreenshot() {
        return appScreenshot;
    }

    public void setAppScreenshot(String appScreenshot) {
        this.appScreenshot = appScreenshot == null ? null : appScreenshot.trim();
    }

    public String getAppAbstruct() {
        return appAbstruct;
    }

    public void setAppAbstruct(String appAbstruct) {
        this.appAbstruct = appAbstruct == null ? null : appAbstruct.trim();
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl == null ? null : appUrl.trim();
    }

    public String getDemoAccount() {
        return demoAccount;
    }

    public void setDemoAccount(String demoAccount) {
        this.demoAccount = demoAccount == null ? null : demoAccount.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getAppRank() {
        return appRank;
    }

    public void setAppRank(Integer appRank) {
        this.appRank = appRank;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
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
        App other = (App) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getTargetUser() == null ? other.getTargetUser() == null : this.getTargetUser().equals(other.getTargetUser()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getIsFree() == null ? other.getIsFree() == null : this.getIsFree().equals(other.getIsFree()))
            && (this.getAppScreenshot() == null ? other.getAppScreenshot() == null : this.getAppScreenshot().equals(other.getAppScreenshot()))
            && (this.getAppAbstruct() == null ? other.getAppAbstruct() == null : this.getAppAbstruct().equals(other.getAppAbstruct()))
            && (this.getAppUrl() == null ? other.getAppUrl() == null : this.getAppUrl().equals(other.getAppUrl()))
            && (this.getDemoAccount() == null ? other.getDemoAccount() == null : this.getDemoAccount().equals(other.getDemoAccount()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAppSecret() == null ? other.getAppSecret() == null : this.getAppSecret().equals(other.getAppSecret()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCheckStatus() == null ? other.getCheckStatus() == null : this.getCheckStatus().equals(other.getCheckStatus()))
            && (this.getAppRank() == null ? other.getAppRank() == null : this.getAppRank().equals(other.getAppRank()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getClientSecret() == null ? other.getClientSecret() == null : this.getClientSecret().equals(other.getClientSecret()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getTargetUser() == null) ? 0 : getTargetUser().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getIsFree() == null) ? 0 : getIsFree().hashCode());
        result = prime * result + ((getAppScreenshot() == null) ? 0 : getAppScreenshot().hashCode());
        result = prime * result + ((getAppAbstruct() == null) ? 0 : getAppAbstruct().hashCode());
        result = prime * result + ((getAppUrl() == null) ? 0 : getAppUrl().hashCode());
        result = prime * result + ((getDemoAccount() == null) ? 0 : getDemoAccount().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAppSecret() == null) ? 0 : getAppSecret().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCheckStatus() == null) ? 0 : getCheckStatus().hashCode());
        result = prime * result + ((getAppRank() == null) ? 0 : getAppRank().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getClientSecret() == null) ? 0 : getClientSecret().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", logo=").append(logo);
        sb.append(", category=").append(category);
        sb.append(", targetUser=").append(targetUser);
        sb.append(", version=").append(version);
        sb.append(", isFree=").append(isFree);
        sb.append(", appScreenshot=").append(appScreenshot);
        sb.append(", appAbstruct=").append(appAbstruct);
        sb.append(", appUrl=").append(appUrl);
        sb.append(", demoAccount=").append(demoAccount);
        sb.append(", userId=").append(userId);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", appRank=").append(appRank);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", clientId=").append(clientId);
        sb.append(", clientSecret=").append(clientSecret);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}