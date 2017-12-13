package entity;

import java.io.Serializable;

public class Course implements Serializable {

	private String name;

	private Integer avc;

	private String dcccccc;

	private Integer time;

	private Integer cid;

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String dddd;

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

	public String getDcccccc() {
	    return dcccccc;
	}

	public void setDcccccc(String dcccccc) {
	    this.dcccccc = dcccccc == null ? null : dcccccc.trim();
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
	    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid())) && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime())) && (this.getDddd() == null ? other.getDddd() == null : this.getDddd().equals(other.getDddd())) && (this.getDcccccc() == null ? other.getDcccccc() == null : this.getDcccccc().equals(other.getDcccccc()));
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
	    result = prime * result + ((getDcccccc() == null) ? 0 : getDcccccc().hashCode());
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
	    sb.append(", dddd=").append(dddd);
	    sb.append(", dcccccc=").append(dcccccc);
	    sb.append(", serialVersionUID=").append(serialVersionUID);
	    sb.append("]");
	    return sb.toString();
	}


}