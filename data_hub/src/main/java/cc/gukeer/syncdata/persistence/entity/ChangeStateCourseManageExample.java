package cc.gukeer.syncdata.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ChangeStateCourseManageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChangeStateCourseManageExample() {
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

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(String value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(String value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(String value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(String value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(String value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLike(String value) {
            addCriterion("course_id like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotLike(String value) {
            addCriterion("course_id not like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<String> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<String> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(String value1, String value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(String value1, String value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andXnIsNull() {
            addCriterion("xn is null");
            return (Criteria) this;
        }

        public Criteria andXnIsNotNull() {
            addCriterion("xn is not null");
            return (Criteria) this;
        }

        public Criteria andXnEqualTo(String value) {
            addCriterion("xn =", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnNotEqualTo(String value) {
            addCriterion("xn <>", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnGreaterThan(String value) {
            addCriterion("xn >", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnGreaterThanOrEqualTo(String value) {
            addCriterion("xn >=", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnLessThan(String value) {
            addCriterion("xn <", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnLessThanOrEqualTo(String value) {
            addCriterion("xn <=", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnLike(String value) {
            addCriterion("xn like", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnNotLike(String value) {
            addCriterion("xn not like", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnIn(List<String> values) {
            addCriterion("xn in", values, "xn");
            return (Criteria) this;
        }

        public Criteria andXnNotIn(List<String> values) {
            addCriterion("xn not in", values, "xn");
            return (Criteria) this;
        }

        public Criteria andXnBetween(String value1, String value2) {
            addCriterion("xn between", value1, value2, "xn");
            return (Criteria) this;
        }

        public Criteria andXnNotBetween(String value1, String value2) {
            addCriterion("xn not between", value1, value2, "xn");
            return (Criteria) this;
        }

        public Criteria andXqIsNull() {
            addCriterion("xq is null");
            return (Criteria) this;
        }

        public Criteria andXqIsNotNull() {
            addCriterion("xq is not null");
            return (Criteria) this;
        }

        public Criteria andXqEqualTo(Integer value) {
            addCriterion("xq =", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotEqualTo(Integer value) {
            addCriterion("xq <>", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqGreaterThan(Integer value) {
            addCriterion("xq >", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqGreaterThanOrEqualTo(Integer value) {
            addCriterion("xq >=", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLessThan(Integer value) {
            addCriterion("xq <", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLessThanOrEqualTo(Integer value) {
            addCriterion("xq <=", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqIn(List<Integer> values) {
            addCriterion("xq in", values, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotIn(List<Integer> values) {
            addCriterion("xq not in", values, "xq");
            return (Criteria) this;
        }

        public Criteria andXqBetween(Integer value1, Integer value2) {
            addCriterion("xq between", value1, value2, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotBetween(Integer value1, Integer value2) {
            addCriterion("xq not between", value1, value2, "xq");
            return (Criteria) this;
        }

        public Criteria andCourseTimeIsNull() {
            addCriterion("course_time is null");
            return (Criteria) this;
        }

        public Criteria andCourseTimeIsNotNull() {
            addCriterion("course_time is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTimeEqualTo(Integer value) {
            addCriterion("course_time =", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeNotEqualTo(Integer value) {
            addCriterion("course_time <>", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeGreaterThan(Integer value) {
            addCriterion("course_time >", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_time >=", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeLessThan(Integer value) {
            addCriterion("course_time <", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeLessThanOrEqualTo(Integer value) {
            addCriterion("course_time <=", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeIn(List<Integer> values) {
            addCriterion("course_time in", values, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeNotIn(List<Integer> values) {
            addCriterion("course_time not in", values, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeBetween(Integer value1, Integer value2) {
            addCriterion("course_time between", value1, value2, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("course_time not between", value1, value2, "courseTime");
            return (Criteria) this;
        }

        public Criteria andClassTypeIsNull() {
            addCriterion("class_type is null");
            return (Criteria) this;
        }

        public Criteria andClassTypeIsNotNull() {
            addCriterion("class_type is not null");
            return (Criteria) this;
        }

        public Criteria andClassTypeEqualTo(String value) {
            addCriterion("class_type =", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotEqualTo(String value) {
            addCriterion("class_type <>", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeGreaterThan(String value) {
            addCriterion("class_type >", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeGreaterThanOrEqualTo(String value) {
            addCriterion("class_type >=", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLessThan(String value) {
            addCriterion("class_type <", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLessThanOrEqualTo(String value) {
            addCriterion("class_type <=", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLike(String value) {
            addCriterion("class_type like", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotLike(String value) {
            addCriterion("class_type not like", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeIn(List<String> values) {
            addCriterion("class_type in", values, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotIn(List<String> values) {
            addCriterion("class_type not in", values, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeBetween(String value1, String value2) {
            addCriterion("class_type between", value1, value2, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotBetween(String value1, String value2) {
            addCriterion("class_type not between", value1, value2, "classType");
            return (Criteria) this;
        }

        public Criteria andPeriodIdIsNull() {
            addCriterion("period_id is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIdIsNotNull() {
            addCriterion("period_id is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodIdEqualTo(String value) {
            addCriterion("period_id =", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotEqualTo(String value) {
            addCriterion("period_id <>", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdGreaterThan(String value) {
            addCriterion("period_id >", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdGreaterThanOrEqualTo(String value) {
            addCriterion("period_id >=", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLessThan(String value) {
            addCriterion("period_id <", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLessThanOrEqualTo(String value) {
            addCriterion("period_id <=", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLike(String value) {
            addCriterion("period_id like", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotLike(String value) {
            addCriterion("period_id not like", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdIn(List<String> values) {
            addCriterion("period_id in", values, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotIn(List<String> values) {
            addCriterion("period_id not in", values, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdBetween(String value1, String value2) {
            addCriterion("period_id between", value1, value2, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotBetween(String value1, String value2) {
            addCriterion("period_id not between", value1, value2, "periodId");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(String value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(String value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(String value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(String value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(String value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLike(String value) {
            addCriterion("class_id like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotLike(String value) {
            addCriterion("class_id not like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<String> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<String> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(String value1, String value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(String value1, String value2) {
            addCriterion("class_id not between", value1, value2, "classId");
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

        public Criteria andSyncDateEqualTo(String value) {
            addCriterion("sync_date =", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotEqualTo(String value) {
            addCriterion("sync_date <>", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateGreaterThan(String value) {
            addCriterion("sync_date >", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateGreaterThanOrEqualTo(String value) {
            addCriterion("sync_date >=", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateLessThan(String value) {
            addCriterion("sync_date <", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateLessThanOrEqualTo(String value) {
            addCriterion("sync_date <=", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateLike(String value) {
            addCriterion("sync_date like", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotLike(String value) {
            addCriterion("sync_date not like", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateIn(List<String> values) {
            addCriterion("sync_date in", values, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotIn(List<String> values) {
            addCriterion("sync_date not in", values, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateBetween(String value1, String value2) {
            addCriterion("sync_date between", value1, value2, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotBetween(String value1, String value2) {
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

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Long value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Long value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Long value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Long value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Long value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Long> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Long> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Long value1, Long value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Long value1, Long value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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

        public Criteria andCourseIdLikeInsensitive(String value) {
            addCriterion("upper(course_id) like", value.toUpperCase(), "courseId");
            return (Criteria) this;
        }

        public Criteria andXnLikeInsensitive(String value) {
            addCriterion("upper(xn) like", value.toUpperCase(), "xn");
            return (Criteria) this;
        }

        public Criteria andClassTypeLikeInsensitive(String value) {
            addCriterion("upper(class_type) like", value.toUpperCase(), "classType");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLikeInsensitive(String value) {
            addCriterion("upper(period_id) like", value.toUpperCase(), "periodId");
            return (Criteria) this;
        }

        public Criteria andClassIdLikeInsensitive(String value) {
            addCriterion("upper(class_id) like", value.toUpperCase(), "classId");
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

        public Criteria andSyncDateLikeInsensitive(String value) {
            addCriterion("upper(sync_date) like", value.toUpperCase(), "syncDate");
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