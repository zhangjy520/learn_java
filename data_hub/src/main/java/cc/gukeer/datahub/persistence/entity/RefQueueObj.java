package cc.gukeer.datahub.persistence.entity;

import java.io.Serializable;

public class RefQueueObj implements Serializable {
    private String id;

    private String platAppId;

    private String objId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPlatAppId() {
        return platAppId;
    }

    public void setPlatAppId(String platAppId) {
        this.platAppId = platAppId == null ? null : platAppId.trim();
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId == null ? null : objId.trim();
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
        RefQueueObj other = (RefQueueObj) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlatAppId() == null ? other.getPlatAppId() == null : this.getPlatAppId().equals(other.getPlatAppId()))
            && (this.getObjId() == null ? other.getObjId() == null : this.getObjId().equals(other.getObjId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlatAppId() == null) ? 0 : getPlatAppId().hashCode());
        result = prime * result + ((getObjId() == null) ? 0 : getObjId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", platAppId=").append(platAppId);
        sb.append(", objId=").append(objId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}