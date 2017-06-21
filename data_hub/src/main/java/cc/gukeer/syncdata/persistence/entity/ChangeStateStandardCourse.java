package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class ChangeStateStandardCourse implements Serializable {
    private String id;

    private String syncId;

    private String name;

    private String englishName;

    private String schoolId;

    private Integer sys;

    private Integer isDictionary;

    private String courseCodeId;

    private String courseCodeName;

    private String event;

    private String source;

    private String syncDate;

    private String syncDelFlag;

    private Long updateDate;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public Integer getSys() {
        return sys;
    }

    public void setSys(Integer sys) {
        this.sys = sys;
    }

    public Integer getIsDictionary() {
        return isDictionary;
    }

    public void setIsDictionary(Integer isDictionary) {
        this.isDictionary = isDictionary;
    }

    public String getCourseCodeId() {
        return courseCodeId;
    }

    public void setCourseCodeId(String courseCodeId) {
        this.courseCodeId = courseCodeId == null ? null : courseCodeId.trim();
    }

    public String getCourseCodeName() {
        return courseCodeName;
    }

    public void setCourseCodeName(String courseCodeName) {
        this.courseCodeName = courseCodeName == null ? null : courseCodeName.trim();
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

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate == null ? null : syncDate.trim();
    }

    public String getSyncDelFlag() {
        return syncDelFlag;
    }

    public void setSyncDelFlag(String syncDelFlag) {
        this.syncDelFlag = syncDelFlag == null ? null : syncDelFlag.trim();
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
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
        ChangeStateStandardCourse other = (ChangeStateStandardCourse) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEnglishName() == null ? other.getEnglishName() == null : this.getEnglishName().equals(other.getEnglishName()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getSys() == null ? other.getSys() == null : this.getSys().equals(other.getSys()))
            && (this.getIsDictionary() == null ? other.getIsDictionary() == null : this.getIsDictionary().equals(other.getIsDictionary()))
            && (this.getCourseCodeId() == null ? other.getCourseCodeId() == null : this.getCourseCodeId().equals(other.getCourseCodeId()))
            && (this.getCourseCodeName() == null ? other.getCourseCodeName() == null : this.getCourseCodeName().equals(other.getCourseCodeName()))
            && (this.getEvent() == null ? other.getEvent() == null : this.getEvent().equals(other.getEvent()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getSyncDate() == null ? other.getSyncDate() == null : this.getSyncDate().equals(other.getSyncDate()))
            && (this.getSyncDelFlag() == null ? other.getSyncDelFlag() == null : this.getSyncDelFlag().equals(other.getSyncDelFlag()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSyncId() == null) ? 0 : getSyncId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEnglishName() == null) ? 0 : getEnglishName().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getSys() == null) ? 0 : getSys().hashCode());
        result = prime * result + ((getIsDictionary() == null) ? 0 : getIsDictionary().hashCode());
        result = prime * result + ((getCourseCodeId() == null) ? 0 : getCourseCodeId().hashCode());
        result = prime * result + ((getCourseCodeName() == null) ? 0 : getCourseCodeName().hashCode());
        result = prime * result + ((getEvent() == null) ? 0 : getEvent().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getSyncDate() == null) ? 0 : getSyncDate().hashCode());
        result = prime * result + ((getSyncDelFlag() == null) ? 0 : getSyncDelFlag().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", englishName=").append(englishName);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", sys=").append(sys);
        sb.append(", isDictionary=").append(isDictionary);
        sb.append(", courseCodeId=").append(courseCodeId);
        sb.append(", courseCodeName=").append(courseCodeName);
        sb.append(", event=").append(event);
        sb.append(", source=").append(source);
        sb.append(", syncDate=").append(syncDate);
        sb.append(", syncDelFlag=").append(syncDelFlag);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}