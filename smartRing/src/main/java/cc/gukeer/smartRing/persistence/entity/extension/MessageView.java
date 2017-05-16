package cc.gukeer.smartRing.persistence.entity.extension;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2016/12/20.
 */
//public class MessageView extends RingMessage implements Serializable {
public class MessageView  implements Serializable {
    private Integer xd;
    private Integer nj;
    private Integer classId;
    private String studentName;
    private Integer sportStandard;
    private Integer sleepStandard;
    private Integer asleepStandard;
    private Double avgTime;
    private Double avgAsleepTime;
    private Double avgSleepTime;
    private Double avgCal;
    private Double avgSleepQuality;


    public Integer getXd() {
        return xd;
    }

    public void setXd(Integer xd) {
        this.xd = xd;
    }

    public Integer getNj() {
        return nj;
    }

    public void setNj(Integer nj) {
        this.nj = nj;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSportStandard() {
        return sportStandard;
    }

    public void setSportStandard(Integer sportStandard) {
        this.sportStandard = sportStandard;
    }

    public Integer getSleepStandard() {
        return sleepStandard;
    }

    public void setSleepStandard(Integer sleepStandard) {
        this.sleepStandard = sleepStandard;
    }

    public Integer getAsleepStandard() {
        return asleepStandard;
    }

    public void setAsleepStandard(Integer asleepStandard) {
        this.asleepStandard = asleepStandard;
    }


    public Double getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Double avgTime) {
        this.avgTime = avgTime;
    }

    public Double getAvgAsleepTime() {
        return avgAsleepTime;
    }

    public void setAvgAsleepTime(Double avgAsleepTime) {
        this.avgAsleepTime = avgAsleepTime;
    }

    public Double getAvgSleepTime() {
        return avgSleepTime;
    }

    public void setAvgSleepTime(Double avgSleepTime) {
        this.avgSleepTime = avgSleepTime;
    }

    public Double getAvgCal() {
        return avgCal;
    }

    public void setAvgCal(Double avgCal) {
        this.avgCal = avgCal;
    }

    public Double getAvgSleepQuality() {
        return avgSleepQuality;
    }

    public void setAvgSleepQuality(Double avgSleepQuality) {
        this.avgSleepQuality = avgSleepQuality;
    }
}
