package cc.gukeer.smartRing.persistence.entity;

import java.io.Serializable;

public class StuScoreRef implements Serializable {
    private String testSeq;

    private Integer testId;

    private Integer studentNum;

    private String itemId;

    private String itemName;

    private String studentScore;

    private String studentMark;

    private String studentLevel;

    private String schoolId;

    private String createDate;

    private String createBy;

    private String updateDate;

    private String updateBy;

    private Integer expire;

    private Integer delFlag;

    private Integer scoreType;

    private String testDate;

    private static final long serialVersionUID = 1L;

    public String getTestSeq() {
        return testSeq;
    }

    public void setTestSeq(String testSeq) {
        this.testSeq = testSeq == null ? null : testSeq.trim();
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(String studentScore) {
        this.studentScore = studentScore == null ? null : studentScore.trim();
    }

    public String getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(String studentMark) {
        this.studentMark = studentMark == null ? null : studentMark.trim();
    }

    public String getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(String studentLevel) {
        this.studentLevel = studentLevel == null ? null : studentLevel.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getScoreType() {
        return scoreType;
    }

    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate == null ? null : testDate.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StuScoreRef other = (StuScoreRef) that;
        return (this.getTestSeq() == null ? other.getTestSeq() == null : this.getTestSeq().equals(other.getTestSeq()))
            && (this.getTestId() == null ? other.getTestId() == null : this.getTestId().equals(other.getTestId()))
            && (this.getStudentNum() == null ? other.getStudentNum() == null : this.getStudentNum().equals(other.getStudentNum()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getStudentScore() == null ? other.getStudentScore() == null : this.getStudentScore().equals(other.getStudentScore()))
            && (this.getStudentMark() == null ? other.getStudentMark() == null : this.getStudentMark().equals(other.getStudentMark()))
            && (this.getStudentLevel() == null ? other.getStudentLevel() == null : this.getStudentLevel().equals(other.getStudentLevel()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getExpire() == null ? other.getExpire() == null : this.getExpire().equals(other.getExpire()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getScoreType() == null ? other.getScoreType() == null : this.getScoreType().equals(other.getScoreType()))
            && (this.getTestDate() == null ? other.getTestDate() == null : this.getTestDate().equals(other.getTestDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTestSeq() == null) ? 0 : getTestSeq().hashCode());
        result = prime * result + ((getTestId() == null) ? 0 : getTestId().hashCode());
        result = prime * result + ((getStudentNum() == null) ? 0 : getStudentNum().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getStudentScore() == null) ? 0 : getStudentScore().hashCode());
        result = prime * result + ((getStudentMark() == null) ? 0 : getStudentMark().hashCode());
        result = prime * result + ((getStudentLevel() == null) ? 0 : getStudentLevel().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getExpire() == null) ? 0 : getExpire().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getScoreType() == null) ? 0 : getScoreType().hashCode());
        result = prime * result + ((getTestDate() == null) ? 0 : getTestDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", testSeq=").append(testSeq);
        sb.append(", testId=").append(testId);
        sb.append(", studentNum=").append(studentNum);
        sb.append(", itemId=").append(itemId);
        sb.append(", itemName=").append(itemName);
        sb.append(", studentScore=").append(studentScore);
        sb.append(", studentMark=").append(studentMark);
        sb.append(", studentLevel=").append(studentLevel);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", expire=").append(expire);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", scoreType=").append(scoreType);
        sb.append(", testDate=").append(testDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}