/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.zsfb.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.gukeer.common.persistence.DataEntity;
import cn.gukeer.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生Entity
 *
 * @author xiangfusheng
 * @version 2016-07-11
 */
public class ZsStudent extends DataEntity<ZsStudent> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String xsxm; // 学生姓名
    private String xmpy; // 姓名拼音
    private String ywm; // 学生英文名
    private String jzxm; // 家长姓名
    private String jtdz; // 家庭地址
    private String xq; // 小区
    private String yxx; // 原学校
    private String sfdkxx;  //是否对口学校
    private String bj; // 班级
    private String xsxb; // 性别
    private Integer xssg; // 身高
    private Integer xstz; // 体重
    private String xjh; // 学籍号
    private String xh; // 学号
    private String qgxjh; // 全国学籍号
    private String jyid; // 教育ID号
    private Date csrq; // 出生日期
    private String syd; // 生源地
    private String yxzjlx; // 有效证件类型
    private String yxzjh; // 有效证件号
    private String sfjs; // 是否军属
    private String sfjszn; // 是否教师子女
    private String sfsbjd; // 是否随班就读
    private String sfsqzn; // 是否随迁子女
    private String sfwjzn;  //是否外籍子女
    private String sfsbt; // 是否双胞胎
    private String sbtxh; // 双胞胎序号
    private String xslb; // 学生类别
    private String gb; // 国别
    private String mz; // 民族
    private String jg; // 籍贯
    private String zzmm; // 政治面貌
    private String jdfs; // 就读方式
    private String xzz; // 现住址
    private String txdz; // 通讯地址
    private String hkxz; // 户口性质
    private String hkszd; // 户口所在地
    private String hkszdxxdz; // 户口所在地-详细地址
    private String sfabshkxsdd; // 是否按本市户口学生对待
    private String zslb; // 招生类别
    private String xszp; // 学生照片
    private String dyzh; // 学生对应用户账户id
    private String jzzh; // 学生家长账户id
    private String rxnd; // 入学年度
    private String taskId; // 入学年度
    private String zf; // 综合素质
    private String sfcm;//是否重名

    public ZsStudent() {
        super();
    }

    public ZsStudent(ZsStudent zsStudent, String type) {
        super();
        this.xsxb = zsStudent.getXsxb();
        this.zf = zsStudent.getZf();
        this.bj = zsStudent.getBj();
        this.taskId = zsStudent.getTaskId();
        this.sfjs = zsStudent.getSfjs();
        this.yxx = zsStudent.getYxx();
        this.jtdz = zsStudent.getJtdz();
        this.xq = zsStudent.getXq();
        this.sfsbt = "0";
        if (type.equals("js")) {
            this.sfjs = "0";
        } else if (type.equals("jszn")) {
            this.sfjszn = "0";
        } else if (type.equals("sbjd")) {
            this.sfsbjd = "0";
        } else if (type.equals("sqzn")) {
            this.sfsqzn = "0";
        } else if (type.equals("wjzn")) {
            this.sfwjzn = "0";
        }
    }

    public ZsStudent(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "学生姓名长度必须介于 0 和 64 之间")
    @ExcelField(title = "学生姓名", align = 2, sort = 7, groups = {1, 2})
    public String getXsxm() {
        return xsxm;
    }

    public void setXsxm(String xsxm) {
        this.xsxm = xsxm;
    }

    @Length(min = 0, max = 64, message = "姓名拼音长度必须介于 0 和 64 之间")
    public String getXmpy() {
        return xmpy;
    }

    public void setXmpy(String xmpy) {
        this.xmpy = xmpy;
    }

    @Length(min = 0, max = 64, message = "学生英文名长度必须介于 0 和 64 之间")
    public String getYwm() {
        return ywm;
    }

    public void setYwm(String ywm) {
        this.ywm = ywm;
    }

    @Length(min = 0, max = 64, message = "家长姓名长度必须介于 0 和 64 之间")
    @ExcelField(title = "家长姓名", align = 2, sort = 14, groups = {1, 2})
    public String getJzxm() {
        return jzxm;
    }

    public void setJzxm(String jzxm) {
        this.jzxm = jzxm;
    }

    @Length(min = 0, max = 64, message = "家庭地址长度必须介于 0 和 64 之间")
    @ExcelField(title = "家庭地址", align = 2, sort = 10, groups = {1})
    public String getJtdz() {
        return jtdz;
    }

    public void setJtdz(String jtdz) {
        this.jtdz = jtdz;
    }

    @Length(min = 0, max = 64, message = "小区长度必须介于 0 和 64 之间")
    @ExcelField(title = "小区", align = 2, sort = 11, groups = {1})
    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    @Length(min = 0, max = 255, message = "原学校长度必须介于 0 和255 之间")
    @ExcelField(title = "原学校", align = 2, sort = 12, groups = {2})
    public String getYxx() {
        return yxx;
    }

    public void setYxx(String yxx) {
        this.yxx = yxx;
    }

    @Length(min = 0, max = 1, message = "是否对口学校长度必须介于 0 和 1 之间")
    @ExcelField(title = "对口学校", align = 2, sort = 13, dictType = "yes_no", groups = {2})
    public String getSfdkxx() {
        return sfdkxx;
    }

    public void setSfdkxx(String sfdkxx) {
        this.sfdkxx = sfdkxx;
    }

    @Length(min = 0, max = 64, message = "班级长度必须介于 0 和 64 之间")
    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    @Length(min = 0, max = 1, message = "性别长度必须介于 0 和 1 之间")
    @ExcelField(title = "性别", align = 2, sort = 8, dictType = "sex", groups = {1, 2})
    public String getXsxb() {
        return xsxb;
    }

    public void setXsxb(String xsxb) {
        this.xsxb = xsxb;
    }

    @Length(min = 0, max = 11, message = "身高长度必须介于 0 和 11 之间")
    public Integer getXssg() {
        return xssg;
    }

    public void setXssg(Integer xssg) {
        this.xssg = xssg;
    }

    @Length(min = 0, max = 11, message = "体重长度必须介于 0 和 11 之间")
    public Integer getXstz() {
        return xstz;
    }

    public void setXstz(Integer xstz) {
        this.xstz = xstz;
    }

    @Length(min = 0, max = 64, message = "学籍号长度必须介于 0 和 64 之间")
    @ExcelField(title = "学籍号", align = 2, sort = 9, groups = {2})
    public String getXjh() {
        return xjh;
    }

    public void setXjh(String xjh) {
        this.xjh = xjh;
    }

    @Length(min = 0, max = 64, message = "学号长度必须介于 0 和 64 之间")
    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    @Length(min = 0, max = 64, message = "全国学籍号长度必须介于 0 和 64 之间")
    public String getQgxjh() {
        return qgxjh;
    }

    public void setQgxjh(String qgxjh) {
        this.qgxjh = qgxjh;
    }

    @Length(min = 0, max = 64, message = "教育ID号长度必须介于 0 和 64 之间")
    public String getJyid() {
        return jyid;
    }

    public void setJyid(String jyid) {
        this.jyid = jyid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCsrq() {
        return csrq;
    }

    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }

    @Length(min = 0, max = 64, message = "生源地长度必须介于 0 和 64 之间")
    public String getSyd() {
        return syd;
    }

    public void setSyd(String syd) {
        this.syd = syd;
    }

    @Length(min = 0, max = 64, message = "有效证件类型长度必须介于 0 和 64 之间")
    public String getYxzjlx() {
        return yxzjlx;
    }

    public void setYxzjlx(String yxzjlx) {
        this.yxzjlx = yxzjlx;
    }

    @Length(min = 0, max = 64, message = "有效证件号长度必须介于 0 和 64 之间")
    public String getYxzjh() {
        return yxzjh;
    }

    public void setYxzjh(String yxzjh) {
        this.yxzjh = yxzjh;
    }

    @Length(min = 0, max = 1, message = "是否是军属长度必须介于 0 和 1 之间")
    @ExcelField(title = "军人子女", align = 2, sort = 21, dictType = "yes_no", groups = {1, 2})
    public String getSfjs() {
        return sfjs;
    }

    public void setSfjs(String sfjs) {
        this.sfjs = sfjs;
    }

    @Length(min = 0, max = 1, message = "是否教师子女长度必须介于 0 和 1 之间")
    @ExcelField(title = "教师子女", align = 2, sort = 22, dictType = "yes_no", groups = {1, 2})
    public String getSfjszn() {
        return sfjszn;
    }

    public void setSfjszn(String sfjszn) {
        this.sfjszn = sfjszn;
    }

    @Length(min = 0, max = 1, message = "是否随班就读长度必须介于 0 和 1 之间")
    @ExcelField(title = "随班就读", align = 2, sort = 17, dictType = "yes_no", groups = {1, 2})
    public String getSfsbjd() {
        return sfsbjd;
    }

    public void setSfsbjd(String sfsbjd) {
        this.sfsbjd = sfsbjd;
    }

    @Length(min = 0, max = 1, message = "是否随迁子女长度必须介于 0 和 1 之间")
    @ExcelField(title = "随迁子女", align = 2, sort = 16, dictType = "yes_no", groups = {1})
    public String getSfsqzn() {
        return sfsqzn;
    }

    public void setSfsqzn(String sfsqzn) {
        this.sfsqzn = sfsqzn;
    }

    @Length(min = 0, max = 1, message = "是否外籍子女长度必须介于 0 和 1 之间")
    @ExcelField(title = "外籍子女", align = 2, sort = 23, dictType = "yes_no", groups = {1})
    public String getSfwjzn() {
        return sfwjzn;
    }

    public void setSfwjzn(String sfwjzn) {
        this.sfwjzn = sfwjzn;
    }

    @Length(min = 0, max = 1, message = "是否是双胞胎长度必须介于 0 和 1 之间")
    @ExcelField(title = "双(多)胞胎", align = 2, sort = 18, dictType = "yes_no", groups = {1, 2})
    public String getSfsbt() {
        return sfsbt;
    }

    public void setSfsbt(String sfsbt) {
        this.sfsbt = sfsbt;
    }

    @Length(min = 0, max = 64, message = "双胞胎编号长度必须介于 0 和 64 之间")
    @ExcelField(title = "双胞胎编号", align = 2, sort = 19, groups = {1, 2})
    public String getSbtxh() {
        return sbtxh;
    }

    public void setSbtxh(String sbtxh) {
        this.sbtxh = sbtxh;
    }

    @Length(min = 0, max = 1, message = "学生类别长度必须介于 0 和 1 之间")
    public String getXslb() {
        return xslb;
    }

    public void setXslb(String xslb) {
        this.xslb = xslb;
    }

    @Length(min = 0, max = 50, message = "国别长度必须介于 0 和 50 之间")
    public String getGb() {
        return gb;
    }

    public void setGb(String gb) {
        this.gb = gb;
    }

    @Length(min = 0, max = 50, message = "民族长度必须介于 0 和 50之间")
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    @Length(min = 0, max = 50, message = "籍贯长度必须介于 0 和 50 之间")
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    @Length(min = 0, max = 1, message = "政治面貌长度必须介于 0 和 1 之间")
    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    @Length(min = 0, max = 1, message = "就读方式长度必须介于 0 和 1 之间")
    public String getJdfs() {
        return jdfs;
    }

    public void setJdfs(String jdfs) {
        this.jdfs = jdfs;
    }

    @Length(min = 0, max = 500, message = "现住址长度必须介于 0 和 500 之间")
    public String getXzz() {
        return xzz;
    }

    public void setXzz(String xzz) {
        this.xzz = xzz;
    }

    @Length(min = 0, max = 500, message = "通讯地址长度必须介于 0 和 500 之间")
    public String getTxdz() {
        return txdz;
    }

    public void setTxdz(String txdz) {
        this.txdz = txdz;
    }

    @Length(min = 0, max = 64, message = "户口性质长度必须介于 0 和 64 之间")
    public String getHkxz() {
        return hkxz;
    }

    public void setHkxz(String hkxz) {
        this.hkxz = hkxz;
    }

    @Length(min = 0, max = 500, message = "户口所在地长度必须介于 0 和 500 之间")
    public String getHkszd() {
        return hkszd;
    }

    public void setHkszd(String hkszd) {
        this.hkszd = hkszd;
    }

    @Length(min = 0, max = 500, message = "户口所在地-详细地址长度必须介于 0 和 500之间")
    public String getHkszdxxdz() {
        return hkszdxxdz;
    }

    public void setHkszdxxdz(String hkszdxxdz) {
        this.hkszdxxdz = hkszdxxdz;
    }

    @Length(min = 0, max = 1, message = "是否按本市户口学生对待长度必须介于 0 和 1 之间")
    public String getSfabshkxsdd() {
        return sfabshkxsdd;
    }

    public void setSfabshkxsdd(String sfabshkxsdd) {
        this.sfabshkxsdd = sfabshkxsdd;
    }

    @Length(min = 0, max = 500, message = "招生类别长度必须介于 0 和 500 之间")
    public String getZslb() {
        return zslb;
    }

    public void setZslb(String zslb) {
        this.zslb = zslb;
    }

    @Length(min = 0, max = 500, message = "学生照片长度必须介于 0 和 500 之间")
    public String getXszp() {
        return xszp;
    }

    public void setXszp(String xszp) {
        this.xszp = xszp;
    }

    @Length(min = 0, max = 64, message = "学生对应用户账户id长度必须介于 0 和 64 之间")
    public String getDyzh() {
        return dyzh;
    }

    public void setDyzh(String dyzh) {
        this.dyzh = dyzh;
    }

    @Length(min = 0, max = 64, message = "学生家长账户id长度必须介于 0 和 64 之间")
    public String getJzzh() {
        return jzzh;
    }

    public void setJzzh(String jzzh) {
        this.jzzh = jzzh;
    }

    @Length(min = 0, max = 10, message = "入学年度长度必须介于 0 和 10 之间")
    public String getRxnd() {
        return rxnd;
    }

    public void setRxnd(String rxnd) {
        this.rxnd = rxnd;
    }

    @Length(min = 0, max = 64, message = "任务ID长度必须介于 0 和 64 之间")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Length(min = 0, max = 64, message = "综合素质长度必须介于 0 和64之间")
    @ExcelField(title = "综合素质", align = 2, sort = 15, groups = {2})
    public String getZf() {
        return zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

    @Length(min = 0, max = 64, message = "是否重名长度必须介于 0 和64之间")
    @ExcelField(title = "重名", align = 2, sort = 20, groups = {1, 2})
    public String getSfcm() {
        return sfcm;
    }

    public void setSfcm(String sfcm) {
        this.sfcm = sfcm;
    }
}