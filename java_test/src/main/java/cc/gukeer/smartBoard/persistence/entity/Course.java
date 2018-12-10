package cc.gukeer.smartBoard.persistence.entity;

import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String dddd;

	private String name;

	private String s;

	private Integer cid;

	private String asdssssssssss;

	private String dcccccc;

	private Integer time;

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

	public String getDddd() {
	    return dddd;
	}

	public void setDddd(String dddd) {
	    this.dddd = dddd == null ? null : dddd.trim();
	}

	public String getS() {
	    return s;
	}

	public void setS(String s) {
	    this.s = s == null ? null : s.trim();
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
	    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid())) && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime())) && (this.getDddd() == null ? other.getDddd() == null : this.getDddd().equals(other.getDddd())) && (this.getS() == null ? other.getS() == null : this.getS().equals(other.getS()));
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
	    result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
	    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
	    result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
	    result = prime * result + ((getDddd() == null) ? 0 : getDddd().hashCode());
	    result = prime * result + ((getS() == null) ? 0 : getS().hashCode());
	    return result;
	}

	@Override
	public String toString() {
		CourseExample a = new CourseExample();
		CourseExample.Criteria c = a.createCriteria();
	    StringBuilder sb = new StringBuilder();
	    sb.append(getClass().getSimpleName());
	    sb.append(" [");
	    sb.append("Hash = ").append(hashCode());
	    sb.append(", id=").append(id);
	    sb.append(", cid=").append(cid);
	    sb.append(", name=").append(name);
	    sb.append(", time=").append(time);
	    sb.append(", dddd=").append(dddd);
	    sb.append(", s=").append(s);
	    sb.append(", serialVersionUID=").append(serialVersionUID);
	    sb.append("]");
	    return sb.toString();
	}

	public String getAsdssssssssss() {
	    return asdssssssssss;
	}

	public void setAsdssssssssss(String asdssssssssss) {
	    this.asdssssssssss = asdssssssssss == null ? null : asdssssssssss.trim();
	}

	public String getDcccccc() {
	    return dcccccc;
	}

	public void setDcccccc(String dcccccc) {
	    this.dcccccc = dcccccc == null ? null : dcccccc.trim();
	}


}