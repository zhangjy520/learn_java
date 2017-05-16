package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class DetailObjColumn implements Serializable {
    private String id;

    private String name;

    private String belong;

    private String detailObjId;

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

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public String getDetailObjId() {
        return detailObjId;
    }

    public void setDetailObjId(String detailObjId) {
        this.detailObjId = detailObjId == null ? null : detailObjId.trim();
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
        DetailObjColumn other = (DetailObjColumn) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getBelong() == null ? other.getBelong() == null : this.getBelong().equals(other.getBelong()))
            && (this.getDetailObjId() == null ? other.getDetailObjId() == null : this.getDetailObjId().equals(other.getDetailObjId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getBelong() == null) ? 0 : getBelong().hashCode());
        result = prime * result + ((getDetailObjId() == null) ? 0 : getDetailObjId().hashCode());
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
        sb.append(", belong=").append(belong);
        sb.append(", detailObjId=").append(detailObjId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}