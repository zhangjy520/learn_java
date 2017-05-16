package cc.gukeer.smartRing.modelView;


import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/8/19.
 */
public class ScoreMapRuleView implements Serializable {

    String item_name;//项目名称
    String xd;//年级
    String nj;//年级
    String gender;//性别
    String mark;//成绩
    String score;//分数
    String level;//等级

    @ExcelField(title = "项目名称", align = 2, sort = 1, groups = {1, 2},isnull=1)
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    @ExcelField(title = "学段", align = 2, sort = 2, groups = {1, 2},isnull=1)
    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }
    @ExcelField(title = "年级", align = 2, sort = 3, groups = {1, 2},isnull=1)
    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }
    @ExcelField(title = "性别", align = 2, sort = 4, groups = {1, 2},isnull=1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @ExcelField(title = "成绩", align = 2, sort = 5, groups = {1, 2},isnull=1)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    @ExcelField(title = "分数", align = 2, sort = 6, groups = {1, 2},isnull=1)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    @ExcelField(title = "等级", align = 2, sort = 7, groups = {1, 2},isnull=1)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
