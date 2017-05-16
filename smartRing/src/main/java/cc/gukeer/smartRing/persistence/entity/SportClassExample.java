package cc.gukeer.smartRing.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class SportClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SportClassExample() {
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

        public Criteria andSportClassIdIsNull() {
            addCriterion("sport_class_id is null");
            return (Criteria) this;
        }

        public Criteria andSportClassIdIsNotNull() {
            addCriterion("sport_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andSportClassIdEqualTo(String value) {
            addCriterion("sport_class_id =", value, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdNotEqualTo(String value) {
            addCriterion("sport_class_id <>", value, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdGreaterThan(String value) {
            addCriterion("sport_class_id >", value, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("sport_class_id >=", value, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdLessThan(String value) {
            addCriterion("sport_class_id <", value, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdLessThanOrEqualTo(String value) {
            addCriterion("sport_class_id <=", value, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdLike(String value) {
            addCriterion("sport_class_id like", value, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdNotLike(String value) {
            addCriterion("sport_class_id not like", value, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdIn(List<String> values) {
            addCriterion("sport_class_id in", values, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdNotIn(List<String> values) {
            addCriterion("sport_class_id not in", values, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdBetween(String value1, String value2) {
            addCriterion("sport_class_id between", value1, value2, "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSportClassIdNotBetween(String value1, String value2) {
            addCriterion("sport_class_id not between", value1, value2, "sportClassId");
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

        public Criteria andSportClassNameIsNull() {
            addCriterion("sport_class_name is null");
            return (Criteria) this;
        }

        public Criteria andSportClassNameIsNotNull() {
            addCriterion("sport_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andSportClassNameEqualTo(String value) {
            addCriterion("sport_class_name =", value, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameNotEqualTo(String value) {
            addCriterion("sport_class_name <>", value, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameGreaterThan(String value) {
            addCriterion("sport_class_name >", value, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("sport_class_name >=", value, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameLessThan(String value) {
            addCriterion("sport_class_name <", value, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameLessThanOrEqualTo(String value) {
            addCriterion("sport_class_name <=", value, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameLike(String value) {
            addCriterion("sport_class_name like", value, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameNotLike(String value) {
            addCriterion("sport_class_name not like", value, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameIn(List<String> values) {
            addCriterion("sport_class_name in", values, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameNotIn(List<String> values) {
            addCriterion("sport_class_name not in", values, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameBetween(String value1, String value2) {
            addCriterion("sport_class_name between", value1, value2, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andSportClassNameNotBetween(String value1, String value2) {
            addCriterion("sport_class_name not between", value1, value2, "sportClassName");
            return (Criteria) this;
        }

        public Criteria andXdIsNull() {
            addCriterion("xd is null");
            return (Criteria) this;
        }

        public Criteria andXdIsNotNull() {
            addCriterion("xd is not null");
            return (Criteria) this;
        }

        public Criteria andXdEqualTo(String value) {
            addCriterion("xd =", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdNotEqualTo(String value) {
            addCriterion("xd <>", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdGreaterThan(String value) {
            addCriterion("xd >", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdGreaterThanOrEqualTo(String value) {
            addCriterion("xd >=", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdLessThan(String value) {
            addCriterion("xd <", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdLessThanOrEqualTo(String value) {
            addCriterion("xd <=", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdLike(String value) {
            addCriterion("xd like", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdNotLike(String value) {
            addCriterion("xd not like", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdIn(List<String> values) {
            addCriterion("xd in", values, "xd");
            return (Criteria) this;
        }

        public Criteria andXdNotIn(List<String> values) {
            addCriterion("xd not in", values, "xd");
            return (Criteria) this;
        }

        public Criteria andXdBetween(String value1, String value2) {
            addCriterion("xd between", value1, value2, "xd");
            return (Criteria) this;
        }

        public Criteria andXdNotBetween(String value1, String value2) {
            addCriterion("xd not between", value1, value2, "xd");
            return (Criteria) this;
        }

        public Criteria andNjIsNull() {
            addCriterion("nj is null");
            return (Criteria) this;
        }

        public Criteria andNjIsNotNull() {
            addCriterion("nj is not null");
            return (Criteria) this;
        }

        public Criteria andNjEqualTo(Integer value) {
            addCriterion("nj =", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjNotEqualTo(Integer value) {
            addCriterion("nj <>", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjGreaterThan(Integer value) {
            addCriterion("nj >", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjGreaterThanOrEqualTo(Integer value) {
            addCriterion("nj >=", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjLessThan(Integer value) {
            addCriterion("nj <", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjLessThanOrEqualTo(Integer value) {
            addCriterion("nj <=", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjIn(List<Integer> values) {
            addCriterion("nj in", values, "nj");
            return (Criteria) this;
        }

        public Criteria andNjNotIn(List<Integer> values) {
            addCriterion("nj not in", values, "nj");
            return (Criteria) this;
        }

        public Criteria andNjBetween(Integer value1, Integer value2) {
            addCriterion("nj between", value1, value2, "nj");
            return (Criteria) this;
        }

        public Criteria andNjNotBetween(Integer value1, Integer value2) {
            addCriterion("nj not between", value1, value2, "nj");
            return (Criteria) this;
        }

        public Criteria andSportItemIdIsNull() {
            addCriterion("sport_item_id is null");
            return (Criteria) this;
        }

        public Criteria andSportItemIdIsNotNull() {
            addCriterion("sport_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andSportItemIdEqualTo(String value) {
            addCriterion("sport_item_id =", value, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdNotEqualTo(String value) {
            addCriterion("sport_item_id <>", value, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdGreaterThan(String value) {
            addCriterion("sport_item_id >", value, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("sport_item_id >=", value, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdLessThan(String value) {
            addCriterion("sport_item_id <", value, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdLessThanOrEqualTo(String value) {
            addCriterion("sport_item_id <=", value, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdLike(String value) {
            addCriterion("sport_item_id like", value, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdNotLike(String value) {
            addCriterion("sport_item_id not like", value, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdIn(List<String> values) {
            addCriterion("sport_item_id in", values, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdNotIn(List<String> values) {
            addCriterion("sport_item_id not in", values, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdBetween(String value1, String value2) {
            addCriterion("sport_item_id between", value1, value2, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andSportItemIdNotBetween(String value1, String value2) {
            addCriterion("sport_item_id not between", value1, value2, "sportItemId");
            return (Criteria) this;
        }

        public Criteria andTecherIdIsNull() {
            addCriterion("techer_id is null");
            return (Criteria) this;
        }

        public Criteria andTecherIdIsNotNull() {
            addCriterion("techer_id is not null");
            return (Criteria) this;
        }

        public Criteria andTecherIdEqualTo(String value) {
            addCriterion("techer_id =", value, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdNotEqualTo(String value) {
            addCriterion("techer_id <>", value, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdGreaterThan(String value) {
            addCriterion("techer_id >", value, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdGreaterThanOrEqualTo(String value) {
            addCriterion("techer_id >=", value, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdLessThan(String value) {
            addCriterion("techer_id <", value, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdLessThanOrEqualTo(String value) {
            addCriterion("techer_id <=", value, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdLike(String value) {
            addCriterion("techer_id like", value, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdNotLike(String value) {
            addCriterion("techer_id not like", value, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdIn(List<String> values) {
            addCriterion("techer_id in", values, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdNotIn(List<String> values) {
            addCriterion("techer_id not in", values, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdBetween(String value1, String value2) {
            addCriterion("techer_id between", value1, value2, "techerId");
            return (Criteria) this;
        }

        public Criteria andTecherIdNotBetween(String value1, String value2) {
            addCriterion("techer_id not between", value1, value2, "techerId");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeIsNull() {
            addCriterion("sport_class_time is null");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeIsNotNull() {
            addCriterion("sport_class_time is not null");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeEqualTo(Long value) {
            addCriterion("sport_class_time =", value, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeNotEqualTo(Long value) {
            addCriterion("sport_class_time <>", value, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeGreaterThan(Long value) {
            addCriterion("sport_class_time >", value, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("sport_class_time >=", value, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeLessThan(Long value) {
            addCriterion("sport_class_time <", value, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeLessThanOrEqualTo(Long value) {
            addCriterion("sport_class_time <=", value, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeIn(List<Long> values) {
            addCriterion("sport_class_time in", values, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeNotIn(List<Long> values) {
            addCriterion("sport_class_time not in", values, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeBetween(Long value1, Long value2) {
            addCriterion("sport_class_time between", value1, value2, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassTimeNotBetween(Long value1, Long value2) {
            addCriterion("sport_class_time not between", value1, value2, "sportClassTime");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceIsNull() {
            addCriterion("sport_class_place is null");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceIsNotNull() {
            addCriterion("sport_class_place is not null");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceEqualTo(String value) {
            addCriterion("sport_class_place =", value, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceNotEqualTo(String value) {
            addCriterion("sport_class_place <>", value, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceGreaterThan(String value) {
            addCriterion("sport_class_place >", value, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("sport_class_place >=", value, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceLessThan(String value) {
            addCriterion("sport_class_place <", value, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceLessThanOrEqualTo(String value) {
            addCriterion("sport_class_place <=", value, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceLike(String value) {
            addCriterion("sport_class_place like", value, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceNotLike(String value) {
            addCriterion("sport_class_place not like", value, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceIn(List<String> values) {
            addCriterion("sport_class_place in", values, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceNotIn(List<String> values) {
            addCriterion("sport_class_place not in", values, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceBetween(String value1, String value2) {
            addCriterion("sport_class_place between", value1, value2, "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceNotBetween(String value1, String value2) {
            addCriterion("sport_class_place not between", value1, value2, "sportClassPlace");
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

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Long value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Long value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Long value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Long value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Long value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Long> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Long> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Long value1, Long value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Long value1, Long value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
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

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Long value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Long value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Long value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Long value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Long value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Long value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Long> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Long> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Long value1, Long value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Long value1, Long value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andSportClassIdLikeInsensitive(String value) {
            addCriterion("upper(sport_class_id) like", value.toUpperCase(), "sportClassId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
            return (Criteria) this;
        }

        public Criteria andSportClassNameLikeInsensitive(String value) {
            addCriterion("upper(sport_class_name) like", value.toUpperCase(), "sportClassName");
            return (Criteria) this;
        }

        public Criteria andXdLikeInsensitive(String value) {
            addCriterion("upper(xd) like", value.toUpperCase(), "xd");
            return (Criteria) this;
        }

        public Criteria andSportItemIdLikeInsensitive(String value) {
            addCriterion("upper(sport_item_id) like", value.toUpperCase(), "sportItemId");
            return (Criteria) this;
        }

        public Criteria andTecherIdLikeInsensitive(String value) {
            addCriterion("upper(techer_id) like", value.toUpperCase(), "techerId");
            return (Criteria) this;
        }

        public Criteria andSportClassPlaceLikeInsensitive(String value) {
            addCriterion("upper(sport_class_place) like", value.toUpperCase(), "sportClassPlace");
            return (Criteria) this;
        }

        public Criteria andCreateByLikeInsensitive(String value) {
            addCriterion("upper(create_by) like", value.toUpperCase(), "createBy");
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