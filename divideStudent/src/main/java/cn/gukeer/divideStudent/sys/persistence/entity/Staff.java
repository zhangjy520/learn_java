package cn.gukeer.divideStudent.sys.persistence.entity;

import cn.gukeer.common.persistence.DataEntity;
import cn.gukeer.common.utils.excel.annotation.ExcelField;
import cn.gukeer.divideStudent.school.persistence.entity.School;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class Staff extends DataEntity<Staff>{

	private static final long serialVersionUID = 1L;
	
	private Dept dept = new Dept();
	private User user = new User();
	private School school = new School();
	
    private String jzgbh;

    private String xm;

    private Integer jzgly;

    private Integer xb;

    private Date csrq;

    private String jg;

    private Integer mz;

    private Integer zzmm;

    private Date rdsj;

    private Date cjgzsj;

    private Integer ysxl;

    private Date ysbysj;

    private String ysbyxx;

    private Integer zgxl;

    private Date zgbysj;

    private String zgbyxx;

    private String zgzy;

    private String yzy;

    private Integer zw;

    private Integer zc;

    private Date pzsj;

    private String pzxx;

    private Date lxsj;

    private String jtxxzz;

    private String zzdh;

    private String sj;

    private String sfz;

    private Integer ggjsjb;

    private Integer gzgw;

    private Date htkssj;

    private Date htjssj;

    private String cym;

    private String bgdh;

    private String jtyb;

    private Integer sfhq;

    private Integer sfzrjs;

    private Integer sfbzr;

    private Integer sf;

    private Integer wyyz;

    private Integer wysp;

    private Integer yxz;

    private Integer zgxz;

    private Integer zgxw;

    private Integer xwsl;

    private Integer zyjsgwfl;

    private Integer rjxkjb;

    private Integer rjxk;

    private Integer xq;

    private Integer szjb;

    private Integer xj;

    private Integer gzgwf;

    private Integer gwflf;

    private String yx;
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
//	@Length(min=0, max=64, message="学校长度必须介于 0 和 64 之间")
//	@ExcelField(title="所属学校", align=2, sort=8)
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Length(min=0, max=64, message="教职工编号长度必须介于 0 和 64 之间")
	@ExcelField(title="教职工编号", align=2, sort=8)
    public String getJzgbh() {
        return jzgbh;
    }

    public void setJzgbh(String jzgbh) {
        this.jzgbh = jzgbh == null ? null : jzgbh.trim();
    }

	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	@ExcelField(title="姓名", align=2, sort=9)
    public String getXm() {
        return xm;
    }
	
    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }
    
	@ExcelField(title="所属部门", align=2, sort=10)
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}


	@ExcelField(title="教职工来源", align=2, sort=11,dictType="rs_jzgly")
    public Integer getJzgly() {
        return jzgly;
    }

    public void setJzgly(Integer jzgly) {
        this.jzgly = jzgly;
    }

	@ExcelField(title="性别", align=2, sort=12,dictType="sex")
    public Integer getXb() {
        return xb;
    }

    public void setXb(Integer xb) {
        this.xb = xb;
    }

    
	@ExcelField(title="邮箱", align=2, sort=13)
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}
	
	@ExcelField(title="出生日期", align=2, sort=14)
    public Date getCsrq() {
        return csrq;
    }

    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }

	@Length(min=0, max=64, message="籍贯长度必须介于 0 和 64 之间")
	@ExcelField(title="籍贯", align=2, sort=15)
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg == null ? null : jg.trim();
    }

	@ExcelField(title="民族", align=2, sort=16)
    public Integer getMz() {
        return mz;
    }

    public void setMz(Integer mz) {
        this.mz = mz;
    }

	@ExcelField(title="政治面貌", align=2, sort=17,dictType="rs_zzmm")
    public Integer getZzmm() {
        return zzmm;
    }

    public void setZzmm(Integer zzmm) {
        this.zzmm = zzmm;
    }

	@ExcelField(title="入党时间", align=2, sort=18)
    public Date getRdsj() {
        return rdsj;
    }

    public void setRdsj(Date rdsj) {
        this.rdsj = rdsj;
    }

	@ExcelField(title="参加工作时间", align=2, sort=19)
    public Date getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(Date cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

	@ExcelField(title="原始学历", align=2, sort=18,dictType="rs_xl")
    public Integer getYsxl() {
        return ysxl;
    }

    public void setYsxl(Integer ysxl) {
        this.ysxl = ysxl;
    }

	@ExcelField(title="原始毕业时间", align=2, sort=20)
    public Date getYsbysj() {
        return ysbysj;
    }

    public void setYsbysj(Date ysbysj) {
        this.ysbysj = ysbysj;
    }

	@Length(min=0, max=64, message="原始毕业学校长度必须介于 0 和 64 之间")
	@ExcelField(title="原始毕业学校", align=2, sort=21)
    public String getYsbyxx() {
        return ysbyxx;
    }

    public void setYsbyxx(String ysbyxx) {
        this.ysbyxx = ysbyxx == null ? null : ysbyxx.trim();
    }

	@ExcelField(title="最高学历", align=2, sort=22,dictType="rs_xl")
    public Integer getZgxl() {
        return zgxl;
    }

    public void setZgxl(Integer zgxl) {
        this.zgxl = zgxl;
    }

	@ExcelField(title="最高毕业时间", align=2, sort=23)
    public Date getZgbysj() {
        return zgbysj;
    }

    public void setZgbysj(Date zgbysj) {
        this.zgbysj = zgbysj;
    }

	@Length(min=0, max=64, message="最高毕业学校长度必须介于 0 和 64 之间")
	@ExcelField(title="最高毕业学校", align=2, sort=24)
    public String getZgbyxx() {
        return zgbyxx;
    }

    public void setZgbyxx(String zgbyxx) {
        this.zgbyxx = zgbyxx == null ? null : zgbyxx.trim();
    }

	@Length(min=0, max=64, message="最高专业长度必须介于 0 和 64 之间")
	@ExcelField(title="最高专业", align=2, sort=25)
    public String getZgzy() {
        return zgzy;
    }

    public void setZgzy(String zgzy) {
        this.zgzy = zgzy == null ? null : zgzy.trim();
    }

	@Length(min=0, max=64, message="原专业长度必须介于 0 和 64 之间")
	@ExcelField(title="原专业", align=2, sort=26)
    public String getYzy() {
        return yzy;
    }

    public void setYzy(String yzy) {
        this.yzy = yzy == null ? null : yzy.trim();
    }

	@ExcelField(title="职务", align=2, sort=27 ,dictType="rs_zw")
    public Integer getZw() {
        return zw;
    }

    public void setZw(Integer zw) {
        this.zw = zw;
    }

	@ExcelField(title="职称", align=2, sort=28,dictType="rs_zc")
    public Integer getZc() {
        return zc;
    }

    public void setZc(Integer zc) {
        this.zc = zc;
    }

	@ExcelField(title="评职时间", align=2, sort=29)
    public Date getPzsj() {
        return pzsj;
    }

    public void setPzsj(Date pzsj) {
        this.pzsj = pzsj;
    }

	@Length(min=0, max=255, message="评职详细长度必须介于 0 和 255 之间")
	@ExcelField(title="评职详细", align=2, sort=30)
    public String getPzxx() {
        return pzxx;
    }

    public void setPzxx(String pzxx) {
        this.pzxx = pzxx == null ? null : pzxx.trim();
    }

	@ExcelField(title="来校时间", align=2, sort=31)
    public Date getLxsj() {
        return lxsj;
    }

    public void setLxsj(Date lxsj) {
        this.lxsj = lxsj;
    }

	@Length(min=0, max=255, message="家庭详细住址长度必须介于 0 和 255 之间")
	@ExcelField(title="家庭详细住址", align=2, sort=32)
    public String getJtxxzz() {
        return jtxxzz;
    }

    public void setJtxxzz(String jtxxzz) {
        this.jtxxzz = jtxxzz == null ? null : jtxxzz.trim();
    }

	@Length(min=0, max=64, message="住宅电话长度必须介于 0 和 64 之间")
	@ExcelField(title="住宅电话", align=2, sort=33)
    public String getZzdh() {
        return zzdh;
    }

    public void setZzdh(String zzdh) {
        this.zzdh = zzdh == null ? null : zzdh.trim();
    }

	@Length(min=0, max=64, message="手机长度必须介于 0 和 64 之间")
	@ExcelField(title="手机", align=2, sort=34)
    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj == null ? null : sj.trim();
    }

	@Length(min=0, max=64, message="身份证长度必须介于 0 和 64 之间")
	@ExcelField(title="身份证", align=2, sort=35)
    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz == null ? null : sfz.trim();
    }

	@ExcelField(title="骨干教师级别", align=2, sort=36,dictType="rs_ggjsjb")
    public Integer getGgjsjb() {
        return ggjsjb;
    }

    public void setGgjsjb(Integer ggjsjb) {
        this.ggjsjb = ggjsjb;
    }

	@ExcelField(title="工资岗位", align=2, sort=37,dictType="rs_gzgw")
    public Integer getGzgw() {
        return gzgw;
    }

    public void setGzgw(Integer gzgw) {
        this.gzgw = gzgw;
    }

    @ExcelField(title="合同开始时间", align=2, sort=38)
    public Date getHtkssj() {
        return htkssj;
    }

    public void setHtkssj(Date htkssj) {
        this.htkssj = htkssj;
    }

	@ExcelField(title="合同结束时间", align=2, sort=39)
    public Date getHtjssj() {
        return htjssj;
    }

    public void setHtjssj(Date htjssj) {
        this.htjssj = htjssj;
    }

	@Length(min=0, max=64, message="曾用名长度必须介于 0 和 64 之间")
	@ExcelField(title="曾用名", align=2, sort=40)
    public String getCym() {
        return cym;
    }

    public void setCym(String cym) {
        this.cym = cym == null ? null : cym.trim();
    }

	@Length(min=0, max=64, message="办公电话长度必须介于 0 和 64 之间")
	@ExcelField(title="办公电话", align=2, sort=41)
    public String getBgdh() {
        return bgdh;
    }

    public void setBgdh(String bgdh) {
        this.bgdh = bgdh == null ? null : bgdh.trim();
    }

	@Length(min=0, max=64, message="家庭邮编长度必须介于 0 和 64 之间")
	@ExcelField(title="家庭邮编", align=2, sort=42)
    public String getJtyb() {
        return jtyb;
    }

    public void setJtyb(String jtyb) {
        this.jtyb = jtyb == null ? null : jtyb.trim();
    }

	@ExcelField(title="是否华侨", align=2, sort=43,dictType="rs_sfhq")
    public Integer getSfhq() {
        return sfhq;
    }

    public void setSfhq(Integer sfhq) {
        this.sfhq = sfhq;
    }

	@ExcelField(title="是否专任教师", align=2, sort=44,dictType="rs_sfzrjs")
    public Integer getSfzrjs() {
        return sfzrjs;
    }

    public void setSfzrjs(Integer sfzrjs) {
        this.sfzrjs = sfzrjs;
    }

	@ExcelField(title="是否班主任", align=2, sort=45,dictType="rs_sfbzr")
    public Integer getSfbzr() {
        return sfbzr;
    }

    public void setSfbzr(Integer sfbzr) {
        this.sfbzr = sfbzr;
    }

	@ExcelField(title="身份", align=2, sort=46,dictType="rs_sf")
    public Integer getSf() {
        return sf;
    }

    public void setSf(Integer sf) {
        this.sf = sf;
    }

	@ExcelField(title="外语语种", align=2, sort=47,dictType="rs_wyyz")
    public Integer getWyyz() {
        return wyyz;
    }

    public void setWyyz(Integer wyyz) {
        this.wyyz = wyyz;
    }

	@ExcelField(title="外语水平", align=2, sort=48,dictType="rs_wysp")
    public Integer getWysp() {
        return wysp;
    }

    public void setWysp(Integer wysp) {
        this.wysp = wysp;
    }

	@ExcelField(title="原学制", align=2, sort=49,dictType="rs_xz")
    public Integer getYxz() {
        return yxz;
    }

    public void setYxz(Integer yxz) {
        this.yxz = yxz;
    }

	@ExcelField(title="最高学制", align=2, sort=50,dictType="rs_xz")
    public Integer getZgxz() {
        return zgxz;
    }

    public void setZgxz(Integer zgxz) {
        this.zgxz = zgxz;
    }

	@ExcelField(title="最高学位", align=2, sort=51,dictType="rs_xw")
    public Integer getZgxw() {
        return zgxw;
    }

    public void setZgxw(Integer zgxw) {
        this.zgxw = zgxw;
    }

	@ExcelField(title="学位数量", align=2, sort=52,dictType="rs_xwsl")
    public Integer getXwsl() {
        return xwsl;
    }

    public void setXwsl(Integer xwsl) {
        this.xwsl = xwsl;
    }

	@ExcelField(title="专业技术岗位分类", align=2, sort=53,dictType="rs_zyjsgwfl")
    public Integer getZyjsgwfl() {
        return zyjsgwfl;
    }

    public void setZyjsgwfl(Integer zyjsgwfl) {
        this.zyjsgwfl = zyjsgwfl;
    }

	@ExcelField(title="任教学科级别", align=2, sort=54,dictType="rs_rjxkjb")
    public Integer getRjxkjb() {
        return rjxkjb;
    }

    public void setRjxkjb(Integer rjxkjb) {
        this.rjxkjb = rjxkjb;
    }

	@ExcelField(title="任教学科", align=2, sort=55,dictType="rs_rjxk")
    public Integer getRjxk() {
        return rjxk;
    }

    public void setRjxk(Integer rjxk) {
        this.rjxk = rjxk;
    }

	@ExcelField(title="校区", align=2, sort=56,dictType="rs_xq")
    public Integer getXq() {
        return xq;
    }

    public void setXq(Integer xq) {
        this.xq = xq;
    }

	@ExcelField(title="实职级别", align=2, sort=57,dictType="rs_szjb")
    public Integer getSzjb() {
        return szjb;
    }

    public void setSzjb(Integer szjb) {
        this.szjb = szjb;
    }

	@ExcelField(title="薪级", align=2, sort=58,dictType="rs_xj")
    public Integer getXj() {
        return xj;
    }

    public void setXj(Integer xj) {
        this.xj = xj;
    }

	@ExcelField(title="工资岗位（副）", align=2, sort=59,dictType="rs_gzgw")
    public Integer getGzgwf() {
        return gzgwf;
    }

    public void setGzgwf(Integer gzgwf) {
        this.gzgwf = gzgwf;
    }

	@ExcelField(title="岗位分类（副）", align=2, sort=60,dictType="rs_gwfl")
    public Integer getGwflf() {
        return gwflf;
    }

    public void setGwflf(Integer gwflf) {
        this.gwflf = gwflf;
    }
}