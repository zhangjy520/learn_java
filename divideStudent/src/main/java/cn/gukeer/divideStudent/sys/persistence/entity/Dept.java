package cn.gukeer.divideStudent.sys.persistence.entity;

import cn.gukeer.common.persistence.TreeEntity;

public class Dept extends TreeEntity<Dept>{

	private static final long serialVersionUID = 1L;

    private String mc;

    private String bh;

    private String px;

    private String ld;

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh == null ? null : bh.trim();
    }

    public String getPx() {
        return px;
    }

    public void setPx(String px) {
        this.px = px == null ? null : px.trim();
    }

    public String getLd() {
        return ld;
    }

    public void setLd(String ld) {
        this.ld = ld == null ? null : ld.trim();
    }

	@Override
	public Dept getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public void setParent(Dept parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
	}

	@Override
	public String toString() {
		return mc;
	}
	
	
}