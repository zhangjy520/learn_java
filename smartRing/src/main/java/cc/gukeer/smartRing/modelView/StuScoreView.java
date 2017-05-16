package cc.gukeer.smartRing.modelView;


import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/8/19.
 */
public class StuScoreView implements Serializable {
    Integer test_id;//测试次序
    String item_name;//测试项目
    String create_date;//测试时间
    Integer stu_no;//学号
    String stu_mark;//测试成绩

    @ExcelField(title = "测试编号", align = 2, sort = 1, groups = {1, 2},isnull=1)
    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    @ExcelField(title = "测试项目", align = 2, sort = 2, groups = {1, 2},isnull=1)
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    @ExcelField(title = "测试时间", align = 2, sort = 3, groups = {1, 2},isnull=1)
    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    @ExcelField(title = "学生学号", align = 2, sort = 4, groups = {1, 2},isnull=1)
    public Integer getStu_no() {
        return stu_no;
    }

    public void setStu_no(Integer stu_no) {
        this.stu_no = stu_no;
    }
    @ExcelField(title = "测试成绩", align = 2, sort = 5, groups = {1, 2},isnull=1)
    public String getStu_mark() {
        return stu_mark;
    }

    public void setStu_mark(String stu_mark) {
        this.stu_mark = stu_mark;
    }
}
