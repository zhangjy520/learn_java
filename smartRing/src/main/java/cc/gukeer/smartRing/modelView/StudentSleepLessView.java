package cc.gukeer.smartRing.modelView;

import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2017/4/21.
 */
public class StudentSleepLessView implements Serializable {

    String xsxm;//学生姓名
    String sleepTime;//睡眠时长
    String asleepTime;//入睡时间
    Double asleepQuality;//睡眠质量
    String targetAsleepTime;//目标入睡时间
    String targetSleepTime;//目标入睡时长


    @ExcelField(title = "姓名", align = 2, sort = 1, groups = {1, 2}, isnull = 1)
    public String getXsxm() {
        return xsxm;
    }

    public void setXsxm(String xsxm) {
        this.xsxm = xsxm;
    }
    @ExcelField(title = "入睡时间", align = 2, sort = 2, groups = {1, 2}, isnull = 1)
    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }
    @ExcelField(title = "睡眠时长", align = 2, sort = 3, groups = {1, 2}, isnull = 1)
    public String getAsleepTime() {
        return asleepTime;
    }

    public void setAsleepTime(String asleepTime) {
        this.asleepTime = asleepTime;
    }
    @ExcelField(title = "睡眠质量", align = 2, sort = 4, groups = {1, 2}, isnull = 1)
    public Double getAsleepQuality() {
        return asleepQuality;
    }

    public void setAsleepQuality(Double asleepQuality) {
        this.asleepQuality = asleepQuality;
    }
    @ExcelField(title = "目标入睡时间", align = 2, sort = 5, groups = {1, 2}, isnull = 1)
    public String getTargetAsleepTime() {
        return targetAsleepTime;
    }

    public void setTargetAsleepTime(String targetAsleepTime) {
        this.targetAsleepTime = targetAsleepTime;
    }
    @ExcelField(title = "目标睡眠时长", align = 2, sort = 6, groups = {1, 2}, isnull = 1)
    public String getTargetSleepTime() {
        return targetSleepTime;
    }

    public void setTargetSleepTime(String targetSleepTime) {
        this.targetSleepTime = targetSleepTime;
    }

}
