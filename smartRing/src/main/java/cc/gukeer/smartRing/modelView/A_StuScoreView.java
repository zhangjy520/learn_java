package cc.gukeer.smartRing.modelView;


import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/8/19.
 */
public class A_StuScoreView implements Serializable {
    String itemName;//项目
    String testCount;//测试次序
    String testDate;//测试时间
    String xsxm;//学生姓名
    String stuNum;//学号
    String mark;//成绩
    String itemUnit;//项目单位
    String stuScore;//分数
    String stuLevel;//等级

    @ExcelField(title = "测试项目", align = 2, sort = 1, groups = {1, 2})
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    @ExcelField(title = "测试次序", align = 2, sort = 2, groups = {1, 2})
    public String getTestCount() {
        return testCount;
    }

    public void setTestCount(String testCount) {
        this.testCount = testCount;
    }
    @ExcelField(title = "测试时间", align = 2, sort = 3, groups = {1, 2})
    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
    @ExcelField(title = "学生姓名", align = 2, sort = 4, groups = {1, 2})
    public String getXsxm() {
        return xsxm;
    }

    public void setXsxm(String xsxm) {
        this.xsxm = xsxm;
    }
    @ExcelField(title = "学号", align = 2, sort = 5, groups = {1, 2})
    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }
    @ExcelField(title = "测试成绩", align = 2, sort = 6, groups = {1, 2})
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    @ExcelField(title = "测试单位", align = 2, sort = 7, groups = {1, 2})
    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }
    @ExcelField(title = "分数", align = 2, sort = 8, groups = {1, 2})
    public String getStuScore() {
        return stuScore;
    }

    public void setStuScore(String stuScore) {
        this.stuScore = stuScore;
    }
    @ExcelField(title = "等级", align = 2, sort = 9, groups = {1, 2})
    public String getStuLevel() {
        return stuLevel;
    }

    public void setStuLevel(String stuLevel) {
        this.stuLevel = stuLevel;
    }
}
