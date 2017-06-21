package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class CourseNodeInitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseNodeInitExample() {
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

        public Criteria andMorningStartIsNull() {
            addCriterion("morning_start is null");
            return (Criteria) this;
        }

        public Criteria andMorningStartIsNotNull() {
            addCriterion("morning_start is not null");
            return (Criteria) this;
        }

        public Criteria andMorningStartEqualTo(Long value) {
            addCriterion("morning_start =", value, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartNotEqualTo(Long value) {
            addCriterion("morning_start <>", value, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartGreaterThan(Long value) {
            addCriterion("morning_start >", value, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartGreaterThanOrEqualTo(Long value) {
            addCriterion("morning_start >=", value, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartLessThan(Long value) {
            addCriterion("morning_start <", value, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartLessThanOrEqualTo(Long value) {
            addCriterion("morning_start <=", value, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartIn(List<Long> values) {
            addCriterion("morning_start in", values, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartNotIn(List<Long> values) {
            addCriterion("morning_start not in", values, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartBetween(Long value1, Long value2) {
            addCriterion("morning_start between", value1, value2, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningStartNotBetween(Long value1, Long value2) {
            addCriterion("morning_start not between", value1, value2, "morningStart");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceIsNull() {
            addCriterion("morning_persistence is null");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceIsNotNull() {
            addCriterion("morning_persistence is not null");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceEqualTo(Integer value) {
            addCriterion("morning_persistence =", value, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceNotEqualTo(Integer value) {
            addCriterion("morning_persistence <>", value, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceGreaterThan(Integer value) {
            addCriterion("morning_persistence >", value, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("morning_persistence >=", value, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceLessThan(Integer value) {
            addCriterion("morning_persistence <", value, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceLessThanOrEqualTo(Integer value) {
            addCriterion("morning_persistence <=", value, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceIn(List<Integer> values) {
            addCriterion("morning_persistence in", values, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceNotIn(List<Integer> values) {
            addCriterion("morning_persistence not in", values, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceBetween(Integer value1, Integer value2) {
            addCriterion("morning_persistence between", value1, value2, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andMorningPersistenceNotBetween(Integer value1, Integer value2) {
            addCriterion("morning_persistence not between", value1, value2, "morningPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceIsNull() {
            addCriterion("common_persistence is null");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceIsNotNull() {
            addCriterion("common_persistence is not null");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceEqualTo(Integer value) {
            addCriterion("common_persistence =", value, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceNotEqualTo(Integer value) {
            addCriterion("common_persistence <>", value, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceGreaterThan(Integer value) {
            addCriterion("common_persistence >", value, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("common_persistence >=", value, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceLessThan(Integer value) {
            addCriterion("common_persistence <", value, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceLessThanOrEqualTo(Integer value) {
            addCriterion("common_persistence <=", value, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceIn(List<Integer> values) {
            addCriterion("common_persistence in", values, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceNotIn(List<Integer> values) {
            addCriterion("common_persistence not in", values, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceBetween(Integer value1, Integer value2) {
            addCriterion("common_persistence between", value1, value2, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andCommonPersistenceNotBetween(Integer value1, Integer value2) {
            addCriterion("common_persistence not between", value1, value2, "commonPersistence");
            return (Criteria) this;
        }

        public Criteria andTotalNodeIsNull() {
            addCriterion("total_node is null");
            return (Criteria) this;
        }

        public Criteria andTotalNodeIsNotNull() {
            addCriterion("total_node is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNodeEqualTo(Integer value) {
            addCriterion("total_node =", value, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeNotEqualTo(Integer value) {
            addCriterion("total_node <>", value, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeGreaterThan(Integer value) {
            addCriterion("total_node >", value, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_node >=", value, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeLessThan(Integer value) {
            addCriterion("total_node <", value, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeLessThanOrEqualTo(Integer value) {
            addCriterion("total_node <=", value, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeIn(List<Integer> values) {
            addCriterion("total_node in", values, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeNotIn(List<Integer> values) {
            addCriterion("total_node not in", values, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeBetween(Integer value1, Integer value2) {
            addCriterion("total_node between", value1, value2, "totalNode");
            return (Criteria) this;
        }

        public Criteria andTotalNodeNotBetween(Integer value1, Integer value2) {
            addCriterion("total_node not between", value1, value2, "totalNode");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartIsNull() {
            addCriterion("afternoon_start is null");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartIsNotNull() {
            addCriterion("afternoon_start is not null");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartEqualTo(Long value) {
            addCriterion("afternoon_start =", value, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartNotEqualTo(Long value) {
            addCriterion("afternoon_start <>", value, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartGreaterThan(Long value) {
            addCriterion("afternoon_start >", value, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartGreaterThanOrEqualTo(Long value) {
            addCriterion("afternoon_start >=", value, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartLessThan(Long value) {
            addCriterion("afternoon_start <", value, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartLessThanOrEqualTo(Long value) {
            addCriterion("afternoon_start <=", value, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartIn(List<Long> values) {
            addCriterion("afternoon_start in", values, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartNotIn(List<Long> values) {
            addCriterion("afternoon_start not in", values, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartBetween(Long value1, Long value2) {
            addCriterion("afternoon_start between", value1, value2, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andAfternoonStartNotBetween(Long value1, Long value2) {
            addCriterion("afternoon_start not between", value1, value2, "afternoonStart");
            return (Criteria) this;
        }

        public Criteria andNightStartIsNull() {
            addCriterion("night_start is null");
            return (Criteria) this;
        }

        public Criteria andNightStartIsNotNull() {
            addCriterion("night_start is not null");
            return (Criteria) this;
        }

        public Criteria andNightStartEqualTo(Long value) {
            addCriterion("night_start =", value, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartNotEqualTo(Long value) {
            addCriterion("night_start <>", value, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartGreaterThan(Long value) {
            addCriterion("night_start >", value, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartGreaterThanOrEqualTo(Long value) {
            addCriterion("night_start >=", value, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartLessThan(Long value) {
            addCriterion("night_start <", value, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartLessThanOrEqualTo(Long value) {
            addCriterion("night_start <=", value, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartIn(List<Long> values) {
            addCriterion("night_start in", values, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartNotIn(List<Long> values) {
            addCriterion("night_start not in", values, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartBetween(Long value1, Long value2) {
            addCriterion("night_start between", value1, value2, "nightStart");
            return (Criteria) this;
        }

        public Criteria andNightStartNotBetween(Long value1, Long value2) {
            addCriterion("night_start not between", value1, value2, "nightStart");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndIsNull() {
            addCriterion("month_start_end is null");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndIsNotNull() {
            addCriterion("month_start_end is not null");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndEqualTo(String value) {
            addCriterion("month_start_end =", value, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNotEqualTo(String value) {
            addCriterion("month_start_end <>", value, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndGreaterThan(String value) {
            addCriterion("month_start_end >", value, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndGreaterThanOrEqualTo(String value) {
            addCriterion("month_start_end >=", value, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndLessThan(String value) {
            addCriterion("month_start_end <", value, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndLessThanOrEqualTo(String value) {
            addCriterion("month_start_end <=", value, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndLike(String value) {
            addCriterion("month_start_end like", value, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNotLike(String value) {
            addCriterion("month_start_end not like", value, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndIn(List<String> values) {
            addCriterion("month_start_end in", values, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNotIn(List<String> values) {
            addCriterion("month_start_end not in", values, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndBetween(String value1, String value2) {
            addCriterion("month_start_end between", value1, value2, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNotBetween(String value1, String value2) {
            addCriterion("month_start_end not between", value1, value2, "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameIsNull() {
            addCriterion("month_start_end_name is null");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameIsNotNull() {
            addCriterion("month_start_end_name is not null");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameEqualTo(String value) {
            addCriterion("month_start_end_name =", value, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameNotEqualTo(String value) {
            addCriterion("month_start_end_name <>", value, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameGreaterThan(String value) {
            addCriterion("month_start_end_name >", value, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameGreaterThanOrEqualTo(String value) {
            addCriterion("month_start_end_name >=", value, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameLessThan(String value) {
            addCriterion("month_start_end_name <", value, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameLessThanOrEqualTo(String value) {
            addCriterion("month_start_end_name <=", value, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameLike(String value) {
            addCriterion("month_start_end_name like", value, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameNotLike(String value) {
            addCriterion("month_start_end_name not like", value, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameIn(List<String> values) {
            addCriterion("month_start_end_name in", values, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameNotIn(List<String> values) {
            addCriterion("month_start_end_name not in", values, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameBetween(String value1, String value2) {
            addCriterion("month_start_end_name between", value1, value2, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameNotBetween(String value1, String value2) {
            addCriterion("month_start_end_name not between", value1, value2, "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
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

        public Criteria andMonthStartEndLikeInsensitive(String value) {
            addCriterion("upper(month_start_end) like", value.toUpperCase(), "monthStartEnd");
            return (Criteria) this;
        }

        public Criteria andMonthStartEndNameLikeInsensitive(String value) {
            addCriterion("upper(month_start_end_name) like", value.toUpperCase(), "monthStartEndName");
            return (Criteria) this;
        }

        public Criteria andUpdateByLikeInsensitive(String value) {
            addCriterion("upper(update_by) like", value.toUpperCase(), "updateBy");
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