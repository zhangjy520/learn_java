/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.school.persistence.entity;

import cn.gukeer.common.persistence.DataEntity;
import cn.gukeer.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

/**
 * 学校Entity
 * @author xiangfusheng
 * @version 2016-06-14
 */
public class School extends DataEntity<School> {
	
	private static final long serialVersionUID = 1L;
	private String xxmc;		// 学校名称
	private String xxxd;		// 学校学段
	private String xxlx;		// 学校类型
	private String ssdq;		// 所属地区
	private String yydz;		// 应用地址
	private String dlhz;		// 登录后缀
	private String yxhz;		// 邮箱后缀
	private String sync;		// sync
	private String sylx;		// 使用类型
	private String ktqssj;		// 开通起始时间
	private String ktzzsj;		// 开通终止时间
	private String xxym;		// 学校域名
	private String logo;		// 学校logo
	private String xxbs;		// 学校标识
	private String xxpt;		// 学校平台
	private String xxwz;		// 学校网站
	private String xxdz;		// 学校地址
	
	public School() {
		super();
	}

	public School(String id){
		super(id);
	}

	@Length(min=0, max=64, message="学校名称长度必须介于 0 和 64 之间")
	@ExcelField(title="学校名称", align=2, sort=7)
	public String getXxmc() {
		return this.xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	
	@Length(min=0, max=1, message="学校学段长度必须介于 0 和 1 之间")
	@ExcelField(title="学校学段", align=2, sort=8)
	public String getXxxd() {
		return xxxd;
	}

	public void setXxxd(String xxxd) {
		this.xxxd = xxxd;
	}
	
	@Length(min=0, max=1, message="学校类型长度必须介于 0 和 1 之间")
	@ExcelField(title="学校类型", align=2, sort=9)
	public String getXxlx() {
		return this.xxlx;
	}

	public void setXxlx(String xxlx) {
		this.xxlx = xxlx;
	}
	
	@Length(min=0, max=64, message="所属地区长度必须介于 0 和 64 之间")
	@ExcelField(title="所属地区", align=2, sort=10)
	public String getSsdq() {
		return this.ssdq;
	}

	public void setSsdq(String ssdq) {
		this.ssdq = ssdq;
	}
	
	@Length(min=0, max=64, message="应用地址长度必须介于 0 和 64 之间")
	@ExcelField(title="应用地址", align=2, sort=11)
	public String getYydz() {
		return this.yydz;
	}

	public void setYydz(String yydz) {
		this.yydz = yydz;
	}
	
	@Length(min=0, max=64, message="登录后缀长度必须介于 0 和 64 之间")
	@ExcelField(title="登录后缀", align=2, sort=12)
	public String getDlhz() {
		return this.dlhz;
	}

	public void setDlhz(String dlhz) {
		this.dlhz = dlhz;
	}
	
	@Length(min=0, max=64, message="邮箱后缀长度必须介于 0 和 64 之间")
	@ExcelField(title="邮箱后缀", align=2, sort=13)
	public String getYxhz() {
		return this.yxhz;
	}

	public void setYxhz(String yxhz) {
		this.yxhz = yxhz;
	}
	
	@Length(min=0, max=64, message="sync长度必须介于 0 和 64 之间")
	@ExcelField(title="sync", align=2, sort=14)
	public String getSync() {
		return this.sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}
	
	@Length(min=0, max=1, message="使用类型长度必须介于 0 和 1 之间")
	@ExcelField(title="使用类型", align=2, sort=15)
	public String getSylx() {
		return this.sylx;
	}

	public void setSylx(String sylx) {
		this.sylx = sylx;
	}
	
	@ExcelField(title="开通起始时间", align=2, sort=16)
	public String getKtqssj() {
		return this.ktqssj;
	}

	public void setOpenKtqssj(String ktqssj) {
		this.ktqssj = ktqssj;
	}
	
	@ExcelField(title="开通终止时间", align=2, sort=17)
	public String getKtzzsj() {
		return this.ktzzsj;
	}

	public void setKtzzsj(String ktzzsj) {
		this.ktzzsj = ktzzsj;
	}
	
	@Length(min=0, max=64, message="学校域名长度必须介于 0 和 64 之间")
	@ExcelField(title="学校域名", align=2, sort=18)
	public String getXxym() {
		return this.xxym;
	}

	public void setXxym(String xxym) {
		this.xxym = xxym;
	}
	
	@Length(min=0, max=64, message="学校logo长度必须介于 0 和 64 之间")
	@ExcelField(title="学校logo", align=2, sort=19)
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Length(min=0, max=64, message="学校标识长度必须介于 0 和 64 之间")
	@ExcelField(title="学校标识", align=2, sort=20)
	public String getXxbs() {
		return this.xxbs;
	}

	public void setXxbs(String xxbs) {
		this.xxbs = xxbs;
	}
	
	@Length(min=0, max=1, message="学校平台长度必须介于 0 和 1 之间")
	@ExcelField(title="学校平台", align=2, sort=21)
	public String getXxpt() {
		return this.xxpt;
	}

	public void setXxpt(String xxpt) {
		this.xxpt = xxpt;
	}
	
	@Length(min=0, max=64, message="学校网站长度必须介于 0 和 64 之间")
	@ExcelField(title="学校网站", align=2, sort=22)
	public String getXxwz() {
		return this.xxwz;
	}

	public void setXxwz(String xxwz) {
		this.xxwz = xxwz;
	}
	
	@Length(min=0, max=64, message="学校地址长度必须介于 0 和 64 之间")
	@ExcelField(title="学校地址", align=2, sort=23)
	public String getXxdz() {
		return this.xxdz;
	}

	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	
}