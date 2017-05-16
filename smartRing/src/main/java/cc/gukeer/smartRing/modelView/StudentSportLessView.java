package cc.gukeer.smartRing.modelView;

import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2017/4/20.
 */
public class StudentSportLessView implements Serializable {
    String xsxm;//姓名
    String sportTime;//运动时间
    String sportCalorie;//运动耗能
    String targetSportTime;//目标运动时间

    @ExcelField(title = "姓名", align = 2, sort = 1, groups = {1, 2}, isnull = 1)
    public String getXsxm() {
        return xsxm;
    }

    public void setXsxm(String xsxm) {
        this.xsxm = xsxm;
    }
    @ExcelField(title = "运动时间(min)", align = 2, sort = 2, groups = {1, 2}, isnull = 1)
    public String getSportTime() {
        return sportTime;
    }

    public void setSportTime(String sportTime) {
        this.sportTime = sportTime;
    }
    @ExcelField(title = "运动耗能(cal)", align = 2, sort = 3, groups = {1, 2}, isnull = 1)
    public String getSportCalorie() {
        return sportCalorie;
    }

    public void setSportCalorie(String sportCalorie) {
        this.sportCalorie = sportCalorie;
    }
    @ExcelField(title = "目标运动时间(min)", align = 2, sort = 4, groups = {1, 2}, isnull = 1)
    public String getTargetSportTime() {
        return targetSportTime;
    }

    public void setTargetSportTime(String targetSportTime) {
        this.targetSportTime = targetSportTime;
    }
}
