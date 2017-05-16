package cc.gukeer.smartRing.modelView;


import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/8/19.
 */
public class A_ScoreMapRuleView implements Serializable {

    String itemName;//项目名称
    String gradeName ;//年级
    String gender;//性别
    String mark;//成绩
    String itemUnit;//单位
    String score;//分数
    String level;//等级

    @ExcelField(title = "项目名称", align = 2, sort = 1, groups = {1, 2})
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @ExcelField(title = "年级", align = 2, sort = 2, groups = {1, 2})
    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @ExcelField(title = "性别", align = 2, sort = 3, groups = {1, 2})
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ExcelField(title = "成绩", align = 2, sort = 4, groups = {1, 2})
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @ExcelField(title = "单位", align = 2, sort = 5, groups = {1, 2})
    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    @ExcelField(title = "分数", align = 2, sort = 6, groups = {1, 2})
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @ExcelField(title = "等级", align = 2, sort = 7, groups = {1, 2})
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
