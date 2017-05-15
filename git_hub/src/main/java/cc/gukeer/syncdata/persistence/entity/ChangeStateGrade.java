package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class ChangeStateGrade implements Serializable {
    private String id;

    private String syncId;

    private String schoolId;

    private String name;

    private String xd;

    private Integer nj;

    private Integer bh;

    private Integer bjlx;

    private String xq;

    private Long rxnd;

    private Integer masterId;

    private String masterName;

    private Long updateDate;

    private String event;

    private String source;

    private String syncDelFlag;

    private Long syncDate;

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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public Integer getBjlx() {
        return bjlx;
    }

    public void setBjlx(Integer bjlx) {
        this.bjlx = bjlx;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq == null ? null : xq.trim();
    }

    public Long getRxnd() {
        return rxnd;
    }

    public void setRxnd(Long rxnd) {
        this.rxnd = rxnd;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName == null ? null : masterName.trim();
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
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

    public String getSyncDelFlag() {
        return syncDelFlag;
    }

    public void setSyncDelFlag(String syncDelFlag) {
        this.syncDelFlag = syncDelFlag == null ? null : syncDelFlag.trim();
    }

    public Long getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Long syncDate) {
        this.syncDate = syncDate;
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
        ChangeStateGrade other = (ChangeStateGrade) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getXd() == null ? other.getXd() == null : this.getXd().equals(other.getXd()))
            && (this.getNj() == null ? other.getNj() == null : this.getNj().equals(other.getNj()))
            && (this.getBh() == null ? other.getBh() == null : this.getBh().equals(other.getBh()))
            && (this.getBjlx() == null ? other.getBjlx() == null : this.getBjlx().equals(other.getBjlx()))
            && (this.getXq() == null ? other.getXq() == null : this.getXq().equals(other.getXq()))
            && (this.getRxnd() == null ? other.getRxnd() == null : this.getRxnd().equals(other.getRxnd()))
            && (this.getMasterId() == null ? other.getMasterId() == null : this.getMasterId().equals(other.getMasterId()))
            && (this.getMasterName() == null ? other.getMasterName() == null : this.getMasterName().equals(other.getMasterName()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getEvent() == null ? other.getEvent() == null : this.getEvent().equals(other.getEvent()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getSyncDelFlag() == null ? other.getSyncDelFlag() == null : this.getSyncDelFlag().equals(other.getSyncDelFlag()))
            && (this.getSyncDate() == null ? other.getSyncDate() == null : this.getSyncDate().equals(other.getSyncDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSyncId() == null) ? 0 : getSyncId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getXd() == null) ? 0 : getXd().hashCode());
        result = prime * result + ((getNj() == null) ? 0 : getNj().hashCode());
        result = prime * result + ((getBh() == null) ? 0 : getBh().hashCode());
        result = prime * result + ((getBjlx() == null) ? 0 : getBjlx().hashCode());
        result = prime * result + ((getXq() == null) ? 0 : getXq().hashCode());
        result = prime * result + ((getRxnd() == null) ? 0 : getRxnd().hashCode());
        result = prime * result + ((getMasterId() == null) ? 0 : getMasterId().hashCode());
        result = prime * result + ((getMasterName() == null) ? 0 : getMasterName().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getEvent() == null) ? 0 : getEvent().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getSyncDelFlag() == null) ? 0 : getSyncDelFlag().hashCode());
        result = prime * result + ((getSyncDate() == null) ? 0 : getSyncDate().hashCode());
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
        sb.append(", schoolId=").append(schoolId);
        sb.append(", name=").append(name);
        sb.append(", xd=").append(xd);
        sb.append(", nj=").append(nj);
        sb.append(", bh=").append(bh);
        sb.append(", bjlx=").append(bjlx);
        sb.append(", xq=").append(xq);
        sb.append(", rxnd=").append(rxnd);
        sb.append(", masterId=").append(masterId);
        sb.append(", masterName=").append(masterName);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", event=").append(event);
        sb.append(", source=").append(source);
        sb.append(", syncDelFlag=").append(syncDelFlag);
        sb.append(", syncDate=").append(syncDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}