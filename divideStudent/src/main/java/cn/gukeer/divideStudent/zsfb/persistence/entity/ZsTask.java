/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.zsfb.persistence.entity;

import cn.gukeer.common.persistence.DataEntity;

import java.io.Serializable;

/**
 * 分班任务Entity
 * @author xiangfusheng
 * @version 2016-07-11
 */
public class ZsTask extends DataEntity<ZsTask> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String scsjqr;		// 上传数据确认
	private String fbcg;		// 分班成功
	private String rxnf;		// 入学年份
	private String rwmc;        //任务名称
	
	public ZsTask() {
		super();
	}

	public ZsTask(String id){
		super(id);
	}

	public String getScsjqr() {
		return scsjqr;
	}

	public void setScsjqr(String scsjqr) {
		this.scsjqr = scsjqr;
	}

	public String getFbcg() {
		return fbcg;
	}

	public void setFbcg(String fbcg) {
		this.fbcg = fbcg;
	}

	public String getRxnf() {
		return rxnf;
	}

	public void setRxnf(String rxnf) {
		this.rxnf = rxnf;
	}

	public String getRwmc() {
		return rwmc;
	}

	public void setRwmc(String rwmc) {
		this.rwmc = rwmc;
	}

	

	
}