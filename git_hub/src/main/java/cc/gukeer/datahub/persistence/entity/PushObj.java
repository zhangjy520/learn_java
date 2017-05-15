package cc.gukeer.datahub.persistence.entity;

import java.io.Serializable;

public class PushObj implements Serializable {
    private String id;

    private String objTableName;

    private String objName;

    private Integer isAble;

    private String objAbstract;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getObjTableName() {
        return objTableName;
    }

    public void setObjTableName(String objTableName) {
        this.objTableName = objTableName == null ? null : objTableName.trim();
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName == null ? null : objName.trim();
    }

    public Integer getIsAble() {
        return isAble;
    }

    public void setIsAble(Integer isAble) {
        this.isAble = isAble;
    }

    public String getObjAbstract() {
        return objAbstract;
    }

    public void setObjAbstract(String objAbstract) {
        this.objAbstract = objAbstract == null ? null : objAbstract.trim();
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
        PushObj other = (PushObj) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getObjTableName() == null ? other.getObjTableName() == null : this.getObjTableName().equals(other.getObjTableName()))
            && (this.getObjName() == null ? other.getObjName() == null : this.getObjName().equals(other.getObjName()))
            && (this.getIsAble() == null ? other.getIsAble() == null : this.getIsAble().equals(other.getIsAble()))
            && (this.getObjAbstract() == null ? other.getObjAbstract() == null : this.getObjAbstract().equals(other.getObjAbstract()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getObjTableName() == null) ? 0 : getObjTableName().hashCode());
        result = prime * result + ((getObjName() == null) ? 0 : getObjName().hashCode());
        result = prime * result + ((getIsAble() == null) ? 0 : getIsAble().hashCode());
        result = prime * result + ((getObjAbstract() == null) ? 0 : getObjAbstract().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", objTableName=").append(objTableName);
        sb.append(", objName=").append(objName);
        sb.append(", isAble=").append(isAble);
        sb.append(", objAbstract=").append(objAbstract);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}