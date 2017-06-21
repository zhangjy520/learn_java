package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.CourseNode;
import cn.gukeer.platform.persistence.entity.CourseNodeInit;

import java.io.Serializable;

/**
 * Created by LL on 2017/5/26.
 */
public class CourseNodeInitView implements Serializable {
    private String id;

    private String schoolId;

    private String cycleId;

    private String cycleYear;

    private Integer cycleSemester;

    private Long morningStart;

    private Integer morningPersistence;

    private Integer commonPersistence;

    private Integer totalNode;

    private Long afternoonStart;

    private Long nightStart;

    private String monthStartEnd;

    private String monthStartEndName;

    private Long createTime;

    private String updateBy;

    private Integer delFlag;

   private CourseNodeInit courseNodeInit;

    public CourseNodeInit getCourseNodeInit() {
        return courseNodeInit;
    }

    public void setCourseNodeInit(CourseNodeInit courseNodeInit) {
        this.courseNodeInit = courseNodeInit;
    }

    private String _nightStart;

    private String _morningStart;

    private String _afternoonStart;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    public String getCycleYear() {
        return cycleYear;
    }

    public void setCycleYear(String cycleYear) {
        this.cycleYear = cycleYear;
    }

    public Integer getCycleSemester() {
        return cycleSemester;
    }

    public void setCycleSemester(Integer cycleSemester) {
        this.cycleSemester = cycleSemester;
    }

    public Long getMorningStart() {
        return morningStart;
    }

    public void setMorningStart(Long morningStart) {
        this.morningStart = morningStart;
    }

    public Integer getMorningPersistence() {
        return morningPersistence;
    }

    public void setMorningPersistence(Integer morningPersistence) {
        this.morningPersistence = morningPersistence;
    }

    public Integer getCommonPersistence() {
        return commonPersistence;
    }

    public void setCommonPersistence(Integer commonPersistence) {
        this.commonPersistence = commonPersistence;
    }

    public Integer getTotalNode() {
        return totalNode;
    }

    public void setTotalNode(Integer totalNode) {
        this.totalNode = totalNode;
    }

    public Long getAfternoonStart() {
        return afternoonStart;
    }

    public void setAfternoonStart(Long afternoonStart) {
        this.afternoonStart = afternoonStart;
    }

    public Long getNightStart() {
        return nightStart;
    }

    public void setNightStart(Long nightStart) {
        this.nightStart = nightStart;
    }

    public String getMonthStartEnd() {
        return monthStartEnd;
    }

    public void setMonthStartEnd(String monthStartEnd) {
        this.monthStartEnd = monthStartEnd;
    }

    public String getMonthStartEndName() {
        return monthStartEndName;
    }

    public void setMonthStartEndName(String monthStartEndName) {
        this.monthStartEndName = monthStartEndName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }



    public String get_nightStart() {
        return _nightStart;
    }

    public void set_nightStart(String _nightStart) {
        this._nightStart = _nightStart;
    }

    public String get_morningStart() {
        return _morningStart;
    }

    public void set_morningStart(String _morningStart) {
        this._morningStart = _morningStart;
    }

    public String get_afternoonStart() {
        return _afternoonStart;
    }

    public void set_afternoonStart(String _afternoonStart) {
        this._afternoonStart = _afternoonStart;
    }
}
