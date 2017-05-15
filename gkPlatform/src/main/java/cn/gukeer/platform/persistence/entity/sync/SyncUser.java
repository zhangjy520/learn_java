package cn.gukeer.platform.persistence.entity.sync;

import java.io.Serializable;

public class SyncUser implements Serializable {
    private String id;

    private String schoolId;

    private String username;

    private String password;

    private String name;

    private Integer refId;

    private Integer userType;

    private String photoUrl;

    private Integer loginFlag;

    private String loginMark;

    private Integer createBy;

    private Long createDate;

    private Integer updateBy;

    private Long updateDate;

    private String remarks;

    private Integer delFlag;
    
    private String event;
    
    private String versions;
    
    private Integer syncDate;
    
    private Integer syncDelFlag;

    private static final long serialVersionUID = 1L;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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
		this.loginMark = loginMark;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
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
		this.remarks = remarks;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public Integer getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Integer syncDate) {
		this.syncDate = syncDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSyncDelFlag() {
		return syncDelFlag;
	}

	public void setSyncDelFlag(Integer syncDelFlag) {
		this.syncDelFlag = syncDelFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
}

    