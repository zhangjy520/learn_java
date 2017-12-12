/**
 * 
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2017年12月12日 16:51:10
 * <p>2016-3-21
 * Company: gukeer
 * <p>
 * Author: lxsoft
 * <p>
 * Version: 1.0
 * <p>
 */
package cc.gukeer.smartBoard.persistence.entity;

import java.io.Serializable;

public class Course implements Serializable {

    private Integer id;
    private Integer cid;
    private String name;
    private Integer time;
    private Long aaaa;
    private Long ddddd;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Long getAaaa() {
        return aaaa;
    }

    public void setAaaa(Long aaaa) {
        this.aaaa = aaaa;
    }

    public Long getDdddd() {
        return ddddd;
    }

    public void setDdddd(Long ddddd) {
        this.ddddd = ddddd;
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
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getAaaa() == null ? other.getAaaa() == null : this.getAaaa().equals(other.getAaaa()))
            && (this.getDdddd() == null ? other.getDdddd() == null : this.getDdddd().equals(other.getDdddd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getAaaa() == null) ? 0 : getAaaa().hashCode());
        result = prime * result + ((getDdddd() == null) ? 0 : getDdddd().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cid=").append(cid);
        sb.append(", name=").append(name);
        sb.append(", time=").append(time);
        sb.append(", aaaa=").append(aaaa);
        sb.append(", ddddd=").append(ddddd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}