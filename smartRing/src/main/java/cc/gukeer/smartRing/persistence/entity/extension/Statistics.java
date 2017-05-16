package cc.gukeer.smartRing.persistence.entity.extension;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2016/12/20.
 */
public class Statistics implements Serializable{
    private Integer stuNum;//学生数目
    private Integer totalNum;//总数据数目
    private Double  avgSport;//平均运动时长
    private Double avgSleep;//平均睡眠时长
    private Integer avgAsleepTime;//平均入睡时间
    private Double avgSleepQuality;//平均睡眠质量
    private Double avgDeepSleep;//平均深度睡眠时长
    private Double avgCalories;//平均耗能
    private Double avgDistance;//平均距离
    private Double moreThanSportTime;//大于运动时间水平
    private Double moreThanSleepTime;//大于睡眠时长水平
    private Double moreThanAsleepTime;//大于入睡时间水平
    private Integer infoDate;//数据时间
    private Integer avgWalkDay;//平均步数

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Double getAvgSport() {
        return avgSport;
    }

    public void setAvgSport(Double avgSport) {
        this.avgSport = avgSport;
    }

    public Double getAvgSleep() {
        return avgSleep;
    }

    public void setAvgSleep(Double avgSleep) {
        this.avgSleep = avgSleep;
    }

    public Integer getAvgAsleepTime() {
        return avgAsleepTime;
    }

    public void setAvgAsleepTime(Integer avgAsleepTime) {
        this.avgAsleepTime = avgAsleepTime;
    }

    public Double getAvgDeepSleep() {
        return avgDeepSleep;
    }

    public void setAvgDeepSleep(Double avgDeepSleep) {
        this.avgDeepSleep = avgDeepSleep;
    }

    public Double getAvgCalories() {
        return avgCalories;
    }

    public void setAvgCalories(Double avgCalories) {
        this.avgCalories = avgCalories;
    }

    public Double getAvgDistance() {
        return avgDistance;
    }

    public void setAvgDistance(Double avgDistance) {
        this.avgDistance = avgDistance;
    }

    public Double getMoreThanSportTime() {
        return moreThanSportTime;
    }

    public void setMoreThanSportTime(Double moreThanSportTime) {
        this.moreThanSportTime = moreThanSportTime;
    }

    public Double getMoreThanSleepTime() {
        return moreThanSleepTime;
    }

    public void setMoreThanSleepTime(Double moreThanSleepTime) {
        this.moreThanSleepTime = moreThanSleepTime;
    }

    public Double getMoreThanAsleepTime() {
        return moreThanAsleepTime;
    }

    public void setMoreThanAsleepTime(Double moreThanAsleepTime) {
        this.moreThanAsleepTime = moreThanAsleepTime;
    }

    public Integer getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Integer infoDate) {
        this.infoDate = infoDate;
    }

    public Integer getAvgWalkDay() {
        return avgWalkDay;
    }

    public void setAvgWalkDay(Integer avgWalkDay) {
        this.avgWalkDay = avgWalkDay;
    }

    public Double getAvgSleepQuality() {
        return avgSleepQuality;
    }

    public void setAvgSleepQuality(Double avgSleepQuality) {
        this.avgSleepQuality = avgSleepQuality;
    }
}
