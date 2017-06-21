package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class ChangeStateClassRoom implements Serializable {
    private String id;

    private String syncId;

    private String classroomTypeId;

    private String classroomTypeName;

    private String areaId;

    private String area;

    private String no;

    private Integer layer;

    private String number;

    private Integer actnum;

    private Integer testnum;

    private Integer rnrs;

    private Integer sfxk;

    private String schoolId;

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

    public String getClassroomTypeId() {
        return classroomTypeId;
    }

    public void setClassroomTypeId(String classroomTypeId) {
        this.classroomTypeId = classroomTypeId == null ? null : classroomTypeId.trim();
    }

    public String getClassroomTypeName() {
        return classroomTypeName;
    }

    public void setClassroomTypeName(String classroomTypeName) {
        this.classroomTypeName = classroomTypeName == null ? null : classroomTypeName.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getActnum() {
        return actnum;
    }

    public void setActnum(Integer actnum) {
        this.actnum = actnum;
    }

    public Integer getTestnum() {
        return testnum;
    }

    public void setTestnum(Integer testnum) {
        this.testnum = testnum;
    }

    public Integer getRnrs() {
        return rnrs;
    }

    public void setRnrs(Integer rnrs) {
        this.rnrs = rnrs;
    }

    public Integer getSfxk() {
        return sfxk;
    }

    public void setSfxk(Integer sfxk) {
        this.sfxk = sfxk;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
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
        ChangeStateClassRoom other = (ChangeStateClassRoom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getClassroomTypeId() == null ? other.getClassroomTypeId() == null : this.getClassroomTypeId().equals(other.getClassroomTypeId()))
            && (this.getClassroomTypeName() == null ? other.getClassroomTypeName() == null : this.getClassroomTypeName().equals(other.getClassroomTypeName()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getLayer() == null ? other.getLayer() == null : this.getLayer().equals(other.getLayer()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getActnum() == null ? other.getActnum() == null : this.getActnum().equals(other.getActnum()))
            && (this.getTestnum() == null ? other.getTestnum() == null : this.getTestnum().equals(other.getTestnum()))
            && (this.getRnrs() == null ? other.getRnrs() == null : this.getRnrs().equals(other.getRnrs()))
            && (this.getSfxk() == null ? other.getSfxk() == null : this.getSfxk().equals(other.getSfxk()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
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
        result = prime * result + ((getClassroomTypeId() == null) ? 0 : getClassroomTypeId().hashCode());
        result = prime * result + ((getClassroomTypeName() == null) ? 0 : getClassroomTypeName().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getLayer() == null) ? 0 : getLayer().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getActnum() == null) ? 0 : getActnum().hashCode());
        result = prime * result + ((getTestnum() == null) ? 0 : getTestnum().hashCode());
        result = prime * result + ((getRnrs() == null) ? 0 : getRnrs().hashCode());
        result = prime * result + ((getSfxk() == null) ? 0 : getSfxk().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
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
        sb.append(", classroomTypeId=").append(classroomTypeId);
        sb.append(", classroomTypeName=").append(classroomTypeName);
        sb.append(", areaId=").append(areaId);
        sb.append(", area=").append(area);
        sb.append(", no=").append(no);
        sb.append(", layer=").append(layer);
        sb.append(", number=").append(number);
        sb.append(", actnum=").append(actnum);
        sb.append(", testnum=").append(testnum);
        sb.append(", rnrs=").append(rnrs);
        sb.append(", sfxk=").append(sfxk);
        sb.append(", schoolId=").append(schoolId);
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