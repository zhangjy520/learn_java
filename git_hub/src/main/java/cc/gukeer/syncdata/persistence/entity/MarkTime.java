package cc.gukeer.syncdata.persistence.entity;

import java.io.Serializable;

public class MarkTime implements Serializable {
    private String id;

    private String tableName;

    private Long markMinTime;

    private String platIdentifier;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Long getMarkMinTime() {
        return markMinTime;
    }

    public void setMarkMinTime(Long markMinTime) {
        this.markMinTime = markMinTime;
    }

    public String getPlatIdentifier() {
        return platIdentifier;
    }

    public void setPlatIdentifier(String platIdentifier) {
        this.platIdentifier = platIdentifier == null ? null : platIdentifier.trim();
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
        MarkTime other = (MarkTime) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTableName() == null ? other.getTableName() == null : this.getTableName().equals(other.getTableName()))
            && (this.getMarkMinTime() == null ? other.getMarkMinTime() == null : this.getMarkMinTime().equals(other.getMarkMinTime()))
            && (this.getPlatIdentifier() == null ? other.getPlatIdentifier() == null : this.getPlatIdentifier().equals(other.getPlatIdentifier()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTableName() == null) ? 0 : getTableName().hashCode());
        result = prime * result + ((getMarkMinTime() == null) ? 0 : getMarkMinTime().hashCode());
        result = prime * result + ((getPlatIdentifier() == null) ? 0 : getPlatIdentifier().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tableName=").append(tableName);
        sb.append(", markMinTime=").append(markMinTime);
        sb.append(", platIdentifier=").append(platIdentifier);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}