package cc.gukeer.attendance.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
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

        public Criteria andTeacheridIsNull() {
            addCriterion("teacherId is null");
            return (Criteria) this;
        }

        public Criteria andTeacheridIsNotNull() {
            addCriterion("teacherId is not null");
            return (Criteria) this;
        }

        public Criteria andTeacheridEqualTo(String value) {
            addCriterion("teacherId =", value, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridNotEqualTo(String value) {
            addCriterion("teacherId <>", value, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridGreaterThan(String value) {
            addCriterion("teacherId >", value, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridGreaterThanOrEqualTo(String value) {
            addCriterion("teacherId >=", value, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridLessThan(String value) {
            addCriterion("teacherId <", value, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridLessThanOrEqualTo(String value) {
            addCriterion("teacherId <=", value, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridLike(String value) {
            addCriterion("teacherId like", value, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridNotLike(String value) {
            addCriterion("teacherId not like", value, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridIn(List<String> values) {
            addCriterion("teacherId in", values, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridNotIn(List<String> values) {
            addCriterion("teacherId not in", values, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridBetween(String value1, String value2) {
            addCriterion("teacherId between", value1, value2, "teacherid");
            return (Criteria) this;
        }

        public Criteria andTeacheridNotBetween(String value1, String value2) {
            addCriterion("teacherId not between", value1, value2, "teacherid");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andClassBeginIsNull() {
            addCriterion("class_begin is null");
            return (Criteria) this;
        }

        public Criteria andClassBeginIsNotNull() {
            addCriterion("class_begin is not null");
            return (Criteria) this;
        }

        public Criteria andClassBeginEqualTo(Long value) {
            addCriterion("class_begin =", value, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginNotEqualTo(Long value) {
            addCriterion("class_begin <>", value, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginGreaterThan(Long value) {
            addCriterion("class_begin >", value, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginGreaterThanOrEqualTo(Long value) {
            addCriterion("class_begin >=", value, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginLessThan(Long value) {
            addCriterion("class_begin <", value, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginLessThanOrEqualTo(Long value) {
            addCriterion("class_begin <=", value, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginIn(List<Long> values) {
            addCriterion("class_begin in", values, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginNotIn(List<Long> values) {
            addCriterion("class_begin not in", values, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginBetween(Long value1, Long value2) {
            addCriterion("class_begin between", value1, value2, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassBeginNotBetween(Long value1, Long value2) {
            addCriterion("class_begin not between", value1, value2, "classBegin");
            return (Criteria) this;
        }

        public Criteria andClassEndIsNull() {
            addCriterion("class_end is null");
            return (Criteria) this;
        }

        public Criteria andClassEndIsNotNull() {
            addCriterion("class_end is not null");
            return (Criteria) this;
        }

        public Criteria andClassEndEqualTo(Long value) {
            addCriterion("class_end =", value, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndNotEqualTo(Long value) {
            addCriterion("class_end <>", value, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndGreaterThan(Long value) {
            addCriterion("class_end >", value, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndGreaterThanOrEqualTo(Long value) {
            addCriterion("class_end >=", value, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndLessThan(Long value) {
            addCriterion("class_end <", value, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndLessThanOrEqualTo(Long value) {
            addCriterion("class_end <=", value, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndIn(List<Long> values) {
            addCriterion("class_end in", values, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndNotIn(List<Long> values) {
            addCriterion("class_end not in", values, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndBetween(Long value1, Long value2) {
            addCriterion("class_end between", value1, value2, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassEndNotBetween(Long value1, Long value2) {
            addCriterion("class_end not between", value1, value2, "classEnd");
            return (Criteria) this;
        }

        public Criteria andClassPlaceIsNull() {
            addCriterion("class_place is null");
            return (Criteria) this;
        }

        public Criteria andClassPlaceIsNotNull() {
            addCriterion("class_place is not null");
            return (Criteria) this;
        }

        public Criteria andClassPlaceEqualTo(String value) {
            addCriterion("class_place =", value, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceNotEqualTo(String value) {
            addCriterion("class_place <>", value, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceGreaterThan(String value) {
            addCriterion("class_place >", value, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("class_place >=", value, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceLessThan(String value) {
            addCriterion("class_place <", value, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceLessThanOrEqualTo(String value) {
            addCriterion("class_place <=", value, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceLike(String value) {
            addCriterion("class_place like", value, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceNotLike(String value) {
            addCriterion("class_place not like", value, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceIn(List<String> values) {
            addCriterion("class_place in", values, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceNotIn(List<String> values) {
            addCriterion("class_place not in", values, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceBetween(String value1, String value2) {
            addCriterion("class_place between", value1, value2, "classPlace");
            return (Criteria) this;
        }

        public Criteria andClassPlaceNotBetween(String value1, String value2) {
            addCriterion("class_place not between", value1, value2, "classPlace");
            return (Criteria) this;
        }

        public Criteria andFlagWeekIsNull() {
            addCriterion("flag_week is null");
            return (Criteria) this;
        }

        public Criteria andFlagWeekIsNotNull() {
            addCriterion("flag_week is not null");
            return (Criteria) this;
        }

        public Criteria andFlagWeekEqualTo(String value) {
            addCriterion("flag_week =", value, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekNotEqualTo(String value) {
            addCriterion("flag_week <>", value, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekGreaterThan(String value) {
            addCriterion("flag_week >", value, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekGreaterThanOrEqualTo(String value) {
            addCriterion("flag_week >=", value, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekLessThan(String value) {
            addCriterion("flag_week <", value, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekLessThanOrEqualTo(String value) {
            addCriterion("flag_week <=", value, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekLike(String value) {
            addCriterion("flag_week like", value, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekNotLike(String value) {
            addCriterion("flag_week not like", value, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekIn(List<String> values) {
            addCriterion("flag_week in", values, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekNotIn(List<String> values) {
            addCriterion("flag_week not in", values, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekBetween(String value1, String value2) {
            addCriterion("flag_week between", value1, value2, "flagWeek");
            return (Criteria) this;
        }

        public Criteria andFlagWeekNotBetween(String value1, String value2) {
            addCriterion("flag_week not between", value1, value2, "flagWeek");
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

        public Criteria andMustIsNull() {
            addCriterion("must is null");
            return (Criteria) this;
        }

        public Criteria andMustIsNotNull() {
            addCriterion("must is not null");
            return (Criteria) this;
        }

        public Criteria andMustEqualTo(String value) {
            addCriterion("must =", value, "must");
            return (Criteria) this;
        }

        public Criteria andMustNotEqualTo(String value) {
            addCriterion("must <>", value, "must");
            return (Criteria) this;
        }

        public Criteria andMustGreaterThan(String value) {
            addCriterion("must >", value, "must");
            return (Criteria) this;
        }

        public Criteria andMustGreaterThanOrEqualTo(String value) {
            addCriterion("must >=", value, "must");
            return (Criteria) this;
        }

        public Criteria andMustLessThan(String value) {
            addCriterion("must <", value, "must");
            return (Criteria) this;
        }

        public Criteria andMustLessThanOrEqualTo(String value) {
            addCriterion("must <=", value, "must");
            return (Criteria) this;
        }

        public Criteria andMustLike(String value) {
            addCriterion("must like", value, "must");
            return (Criteria) this;
        }

        public Criteria andMustNotLike(String value) {
            addCriterion("must not like", value, "must");
            return (Criteria) this;
        }

        public Criteria andMustIn(List<String> values) {
            addCriterion("must in", values, "must");
            return (Criteria) this;
        }

        public Criteria andMustNotIn(List<String> values) {
            addCriterion("must not in", values, "must");
            return (Criteria) this;
        }

        public Criteria andMustBetween(String value1, String value2) {
            addCriterion("must between", value1, value2, "must");
            return (Criteria) this;
        }

        public Criteria andMustNotBetween(String value1, String value2) {
            addCriterion("must not between", value1, value2, "must");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andCourseIdLikeInsensitive(String value) {
            addCriterion("upper(course_id) like", value.toUpperCase(), "courseId");
            return (Criteria) this;
        }

        public Criteria andTeacheridLikeInsensitive(String value) {
            addCriterion("upper(teacherId) like", value.toUpperCase(), "teacherid");
            return (Criteria) this;
        }

        public Criteria andCourseNameLikeInsensitive(String value) {
            addCriterion("upper(course_name) like", value.toUpperCase(), "courseName");
            return (Criteria) this;
        }

        public Criteria andClassPlaceLikeInsensitive(String value) {
            addCriterion("upper(class_place) like", value.toUpperCase(), "classPlace");
            return (Criteria) this;
        }

        public Criteria andFlagWeekLikeInsensitive(String value) {
            addCriterion("upper(flag_week) like", value.toUpperCase(), "flagWeek");
            return (Criteria) this;
        }

        public Criteria andClassIdLikeInsensitive(String value) {
            addCriterion("upper(class_id) like", value.toUpperCase(), "classId");
            return (Criteria) this;
        }

        public Criteria andMustLikeInsensitive(String value) {
            addCriterion("upper(must) like", value.toUpperCase(), "must");
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