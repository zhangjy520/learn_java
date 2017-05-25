package cn.gukeer.divideStudent.zsfb.persistence.entity;

import java.io.Serializable;

public class ZsAnalysis implements Serializable {
    private static final long serialVersionUID = 1L;

    public Integer getMale() {
        return male;
    }

    public void setMale(Integer male) {
        this.male = male;
    }

    public Integer getFemale() {
        return female;
    }

    public void setFemale(Integer female) {
        this.female = female;
    }

    public Integer getSumPeople() {
        return sumPeople;
    }

    public void setSumPeople(Integer sumPeople) {
        this.sumPeople = sumPeople;
    }

    public Integer getSqzn() {
        return sqzn;
    }

    public void setSqzn(Integer sqzn) {
        this.sqzn = sqzn;
    }

    public Integer getSbjd() {
        return sbjd;
    }

    public void setSbjd(Integer sbjd) {
        this.sbjd = sbjd;
    }

    public Integer getCm() {
        return cm;
    }

    public void setCm(Integer cm) {
        this.cm = cm;
    }

    public Integer getSbt() {
        return sbt;
    }

    public void setSbt(Integer sbt) {
        this.sbt = sbt;
    }

    public Integer getSbtCount() {
        return sbtCount;
    }

    public void setSbtCount(Integer sbtCount) {
        this.sbtCount = sbtCount;
    }

    public Integer getJszn() {
        return jszn;
    }

    public void setJszn(Integer jszn) {
        this.jszn = jszn;
    }

    public Integer getWjzn() {
        return wjzn;
    }

    public void setWjzn(Integer wjzn) {
        this.wjzn = wjzn;
    }

    public Integer getJs() {
        return js;
    }

    public void setJs(Integer js) {
        this.js = js;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getBj() {
        return bj;
    }

    public void setBj(Integer bj) {
        this.bj = bj;
    }

    public Integer getFdkxx() {
        return fdkxx;
    }

    public void setFdkxx(Integer fdkxx) {
        this.fdkxx = fdkxx;
    }

    public String getYxx() {
        return yxx;
    }

    public void setYxx(String yxx) {
        this.yxx = yxx;
    }

    public Integer getYxxCount() {
        return yxxCount;
    }

    public void setYxxCount(Integer yxxCount) {
        this.yxxCount = yxxCount;
    }

    private Integer male;   //男生人数
    private Integer female; //女生人数
    private Integer sumPeople;  //人数总和
    private Integer sqzn;   //随迁子女人数
    private Integer sbjd; //残疾人数
    private Integer cm; //重名人数
    private Integer sbt;    //双胞胎几对
    private Integer sbtCount;   //双胞胎人数
    private Integer jszn;   //教师子女人数
    private Integer wjzn;   //外籍子女人数
    private Integer js;   //军属人数
    private Integer a;   //a人数
    private Integer b;   //b人数
    private Integer c;   //c人数
    private Integer bj; // 班级
    private Integer fdkxx; // 非对口学校
    private String yxx;
    private Integer yxxCount;//对口学校统计
}
