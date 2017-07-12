package cc.gukeer.syncdata.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ChangeStateCourseNodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChangeStateCourseNodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSyncIdIsNull() {
            addCriterion("sync_id is null");
            return (Criteria) this;
        }

        public Criteria andSyncIdIsNotNull() {
            addCriterion("sync_id is not null");
            return (Criteria) this;
        }

        public Criteria andSyncIdEqualTo(String value) {
            addCriterion("sync_id =", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdNotEqualTo(String value) {
            addCriterion("sync_id <>", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdGreaterThan(String value) {
            addCriterion("sync_id >", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdGreaterThanOrEqualTo(String value) {
            addCriterion("sync_id >=", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdLessThan(String value) {
            addCriterion("sync_id <", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdLessThanOrEqualTo(String value) {
            addCriterion("sync_id <=", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdLike(String value) {
            addCriterion("sync_id like", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdNotLike(String value) {
            addCriterion("sync_id not like", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdIn(List<String> values) {
            addCriterion("sync_id in", values, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdNotIn(List<String> values) {
            addCriterion("sync_id not in", values, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdBetween(String value1, String value2) {
            addCriterion("sync_id between", value1, value2, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdNotBetween(String value1, String value2) {
            addCriterion("sync_id not between", value1, value2, "syncId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(String value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(String value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(String value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(String value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLike(String value) {
            addCriterion("school_id like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotLike(String value) {
            addCriterion("school_id not like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<String> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<String> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(String value1, String value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(String value1, String value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdIsNull() {
            addCriterion("course_node_init_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdIsNotNull() {
            addCriterion("course_node_init_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdEqualTo(String value) {
            addCriterion("course_node_init_id =", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdNotEqualTo(String value) {
            addCriterion("course_node_init_id <>", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdGreaterThan(String value) {
            addCriterion("course_node_init_id >", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_node_init_id >=", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdLessThan(String value) {
            addCriterion("course_node_init_id <", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdLessThanOrEqualTo(String value) {
            addCriterion("course_node_init_id <=", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdLike(String value) {
            addCriterion("course_node_init_id like", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdNotLike(String value) {
            addCriterion("course_node_init_id not like", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdIn(List<String> values) {
            addCriterion("course_node_init_id in", values, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdNotIn(List<String> values) {
            addCriterion("course_node_init_id not in", values, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdBetween(String value1, String value2) {
            addCriterion("course_node_init_id between", value1, value2, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdNotBetween(String value1, String value2) {
            addCriterion("course_node_init_id not between", value1, value2, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCycleIdIsNull() {
            addCriterion("cycle_id is null");
            return (Criteria) this;
        }

        public Criteria andCycleIdIsNotNull() {
            addCriterion("cycle_id is not null");
            return (Criteria) this;
        }

        public Criteria andCycleIdEqualTo(String value) {
            addCriterion("cycle_id =", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdNotEqualTo(String value) {
            addCriterion("cycle_id <>", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdGreaterThan(String value) {
            addCriterion("cycle_id >", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_id >=", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLessThan(String value) {
            addCriterion("cycle_id <", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLessThanOrEqualTo(String value) {
            addCriterion("cycle_id <=", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLike(String value) {
            addCriterion("cycle_id like", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdNotLike(String value) {
            addCriterion("cycle_id not like", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdIn(List<String> values) {
            addCriterion("cycle_id in", values, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdNotIn(List<String> values) {
            addCriterion("cycle_id not in", values, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdBetween(String value1, String value2) {
            addCriterion("cycle_id between", value1, value2, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdNotBetween(String value1, String value2) {
            addCriterion("cycle_id not between", value1, value2, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleYearIsNull() {
            addCriterion("cycle_year is null");
            return (Criteria) this;
        }

        public Criteria andCycleYearIsNotNull() {
            addCriterion("cycle_year is not null");
            return (Criteria) this;
        }

        public Criteria andCycleYearEqualTo(String value) {
            addCriterion("cycle_year =", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearNotEqualTo(String value) {
            addCriterion("cycle_year <>", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearGreaterThan(String value) {
            addCriterion("cycle_year >", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_year >=", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearLessThan(String value) {
            addCriterion("cycle_year <", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearLessThanOrEqualTo(String value) {
            addCriterion("cycle_year <=", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearLike(String value) {
            addCriterion("cycle_year like", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearNotLike(String value) {
            addCriterion("cycle_year not like", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearIn(List<String> values) {
            addCriterion("cycle_year in", values, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearNotIn(List<String> values) {
            addCriterion("cycle_year not in", values, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearBetween(String value1, String value2) {
            addCriterion("cycle_year between", value1, value2, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearNotBetween(String value1, String value2) {
            addCriterion("cycle_year not between", value1, value2, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterIsNull() {
            addCriterion("cycle_semester is null");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterIsNotNull() {
            addCriterion("cycle_semester is not null");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterEqualTo(Integer value) {
            addCriterion("cycle_semester =", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterNotEqualTo(Integer value) {
            addCriterion("cycle_semester <>", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterGreaterThan(Integer value) {
            addCriterion("cycle_semester >", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterGreaterThanOrEqualTo(Integer value) {
            addCriterion("cycle_semester >=", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterLessThan(Integer value) {
            addCriterion("cycle_semester <", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterLessThanOrEqualTo(Integer value) {
            addCriterion("cycle_semester <=", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterIn(List<Integer> values) {
            addCriterion("cycle_semester in", values, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterNotIn(List<Integer> values) {
            addCriterion("cycle_semester not in", values, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterBetween(Integer value1, Integer value2) {
            addCriterion("cycle_semester between", value1, value2, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterNotBetween(Integer value1, Integer value2) {
            addCriterion("cycle_semester not between", value1, value2, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andNodeIsNull() {
            addCriterion("node is null");
            return (Criteria) this;
        }

        public Criteria andNodeIsNotNull() {
            addCriterion("node is not null");
            return (Criteria) this;
        }

        public Criteria andNodeEqualTo(Integer value) {
            addCriterion("node =", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotEqualTo(Integer value) {
            addCriterion("node <>", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThan(Integer value) {
            addCriterion("node >", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("node >=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThan(Integer value) {
            addCriterion("node <", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThanOrEqualTo(Integer value) {
            addCriterion("node <=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeIn(List<Integer> values) {
            addCriterion("node in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotIn(List<Integer> values) {
            addCriterion("node not in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeBetween(Integer value1, Integer value2) {
            addCriterion("node between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotBetween(Integer value1, Integer value2) {
            addCriterion("node not between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNull() {
            addCriterion("node_name is null");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNotNull() {
            addCriterion("node_name is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNameEqualTo(String value) {
            addCriterion("node_name =", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotEqualTo(String value) {
            addCriterion("node_name <>", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThan(String value) {
            addCriterion("node_name >", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("node_name >=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThan(String value) {
            addCriterion("node_name <", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThanOrEqualTo(String value) {
            addCriterion("node_name <=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLike(String value) {
            addCriterion("node_name like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotLike(String value) {
            addCriterion("node_name not like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameIn(List<String> values) {
            addCriterion("node_name in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotIn(List<String> values) {
            addCriterion("node_name not in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameBetween(String value1, String value2) {
            addCriterion("node_name between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotBetween(String value1, String value2) {
            addCriterion("node_name not between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Long value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Long value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Long value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Long value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Long value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Long> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Long> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Long value1, Long value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Long value1, Long value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Long value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Long value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Long value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Long value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Long value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Long> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Long> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Long value1, Long value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Long value1, Long value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonIsNull() {
            addCriterion("morning_afternoon is null");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonIsNotNull() {
            addCriterion("morning_afternoon is not null");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonEqualTo(String value) {
            addCriterion("morning_afternoon =", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonNotEqualTo(String value) {
            addCriterion("morning_afternoon <>", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonGreaterThan(String value) {
            addCriterion("morning_afternoon >", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonGreaterThanOrEqualTo(String value) {
            addCriterion("morning_afternoon >=", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonLessThan(String value) {
            addCriterion("morning_afternoon <", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonLessThanOrEqualTo(String value) {
            addCriterion("morning_afternoon <=", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonLike(String value) {
            addCriterion("morning_afternoon like", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonNotLike(String value) {
            addCriterion("morning_afternoon not like", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonIn(List<String> values) {
            addCriterion("morning_afternoon in", values, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonNotIn(List<String> values) {
            addCriterion("morning_afternoon not in", values, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonBetween(String value1, String value2) {
            addCriterion("morning_afternoon between", value1, value2, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonNotBetween(String value1, String value2) {
            addCriterion("morning_afternoon not between", value1, value2, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andWeekIsNull() {
            addCriterion("week is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("week is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(Integer value) {
            addCriterion("week =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(Integer value) {
            addCriterion("week <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(Integer value) {
            addCriterion("week >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("week >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(Integer value) {
            addCriterion("week <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(Integer value) {
            addCriterion("week <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<Integer> values) {
            addCriterion("week in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<Integer> values) {
            addCriterion("week not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(Integer value1, Integer value2) {
            addCriterion("week between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("week not between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeIsNull() {
            addCriterion("summer_winter_time is null");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeIsNotNull() {
            addCriterion("summer_winter_time is not null");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeEqualTo(String value) {
            addCriterion("summer_winter_time =", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeNotEqualTo(String value) {
            addCriterion("summer_winter_time <>", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeGreaterThan(String value) {
            addCriterion("summer_winter_time >", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeGreaterThanOrEqualTo(String value) {
            addCriterion("summer_winter_time >=", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeLessThan(String value) {
            addCriterion("summer_winter_time <", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeLessThanOrEqualTo(String value) {
            addCriterion("summer_winter_time <=", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeLike(String value) {
            addCriterion("summer_winter_time like", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeNotLike(String value) {
            addCriterion("summer_winter_time not like", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeIn(List<String> values) {
            addCriterion("summer_winter_time in", values, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeNotIn(List<String> values) {
            addCriterion("summer_winter_time not in", values, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeBetween(String value1, String value2) {
            addCriterion("summer_winter_time between", value1, value2, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeNotBetween(String value1, String value2) {
            addCriterion("summer_winter_time not between", value1, value2, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andEventIsNull() {
            addCriterion("event is null");
            return (Criteria) this;
        }

        public Criteria andEventIsNotNull() {
            addCriterion("event is not null");
            return (Criteria) this;
        }

        public Criteria andEventEqualTo(String value) {
            addCriterion("event =", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotEqualTo(String value) {
            addCriterion("event <>", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThan(String value) {
            addCriterion("event >", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThanOrEqualTo(String value) {
            addCriterion("event >=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThan(String value) {
            addCriterion("event <", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThanOrEqualTo(String value) {
            addCriterion("event <=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLike(String value) {
            addCriterion("event like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotLike(String value) {
            addCriterion("event not like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventIn(List<String> values) {
            addCriterion("event in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotIn(List<String> values) {
            addCriterion("event not in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventBetween(String value1, String value2) {
            addCriterion("event between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotBetween(String value1, String value2) {
            addCriterion("event not between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSyncDateIsNull() {
            addCriterion("sync_date is null");
            return (Criteria) this;
        }

        public Criteria andSyncDateIsNotNull() {
            addCriterion("sync_date is not null");
            return (Criteria) this;
        }

        public Criteria andSyncDateEqualTo(Long value) {
            addCriterion("sync_date =", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotEqualTo(Long value) {
            addCriterion("sync_date <>", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateGreaterThan(Long value) {
            addCriterion("sync_date >", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateGreaterThanOrEqualTo(Long value) {
            addCriterion("sync_date >=", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateLessThan(Long value) {
            addCriterion("sync_date <", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateLessThanOrEqualTo(Long value) {
            addCriterion("sync_date <=", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateIn(List<Long> values) {
            addCriterion("sync_date in", values, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotIn(List<Long> values) {
            addCriterion("sync_date not in", values, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateBetween(Long value1, Long value2) {
            addCriterion("sync_date between", value1, value2, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotBetween(Long value1, Long value2) {
            addCriterion("sync_date not between", value1, value2, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagIsNull() {
            addCriterion("sync_del_flag is null");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagIsNotNull() {
            addCriterion("sync_del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagEqualTo(String value) {
            addCriterion("sync_del_flag =", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagNotEqualTo(String value) {
            addCriterion("sync_del_flag <>", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagGreaterThan(String value) {
            addCriterion("sync_del_flag >", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("sync_del_flag >=", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagLessThan(String value) {
            addCriterion("sync_del_flag <", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagLessThanOrEqualTo(String value) {
            addCriterion("sync_del_flag <=", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagLike(String value) {
            addCriterion("sync_del_flag like", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagNotLike(String value) {
            addCriterion("sync_del_flag not like", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagIn(List<String> values) {
            addCriterion("sync_del_flag in", values, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagNotIn(List<String> values) {
            addCriterion("sync_del_flag not in", values, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagBetween(String value1, String value2) {
            addCriterion("sync_del_flag between", value1, value2, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagNotBetween(String value1, String value2) {
            addCriterion("sync_del_flag not between", value1, value2, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andSyncIdLikeInsensitive(String value) {
            addCriterion("upper(sync_id) like", value.toUpperCase(), "syncId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdLikeInsensitive(String value) {
            addCriterion("upper(course_node_init_id) like", value.toUpperCase(), "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLikeInsensitive(String value) {
            addCriterion("upper(cycle_id) like", value.toUpperCase(), "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleYearLikeInsensitive(String value) {
            addCriterion("upper(cycle_year) like", value.toUpperCase(), "cycleYear");
            return (Criteria) this;
        }

        public Criteria andNodeNameLikeInsensitive(String value) {
            addCriterion("upper(node_name) like", value.toUpperCase(), "nodeName");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonLikeInsensitive(String value) {
            addCriterion("upper(morning_afternoon) like", value.toUpperCase(), "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeLikeInsensitive(String value) {
            addCriterion("upper(summer_winter_time) like", value.toUpperCase(), "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andEventLikeInsensitive(String value) {
            addCriterion("upper(event) like", value.toUpperCase(), "event");
            return (Criteria) this;
        }

        public Criteria andSourceLikeInsensitive(String value) {
            addCriterion("upper(source) like", value.toUpperCase(), "source");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagLikeInsensitive(String value) {
            addCriterion("upper(sync_del_flag) like", value.toUpperCase(), "syncDelFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}