package cc.gukeer.smartRing.modelView;

import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2017/4/21.
 */
public class ASleepView implements Serializable {
    String days;//日期
    String time;//时间

    @ExcelField(title = "日期", align = 2, sort = 1, groups = {1, 2},isnull=1)
    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
    @ExcelField(title = "入睡时间", align = 2, sort = 2, groups = {1, 2},isnull=1)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
