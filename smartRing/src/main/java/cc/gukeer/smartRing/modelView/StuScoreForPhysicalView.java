package cc.gukeer.smartRing.modelView;


import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/8/19.
 */
public class StuScoreForPhysicalView implements Serializable {
    Integer stuNo;//学号
    String itemName;//测试项目
    String testTime;//测试时间
    String stuMark;//测试成绩

    @ExcelField(title = "学号", align = 2, sort = 1, groups = {1, 2}, isnull = 1)
    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    @ExcelField(title = "项目", align = 2, sort = 2, groups = {1, 2}, isnull = 1)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @ExcelField(title = "测试时间", align = 2, sort = 3, groups = {1, 2}, isnull = 1)
    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    @ExcelField(title = "测试成绩", align = 2, sort = 4, groups = {1, 2}, isnull = 1)
    public String getStuMark() {
        return stuMark;
    }

    public void setStuMark(String stuMark) {
        this.stuMark = stuMark;
    }
}
