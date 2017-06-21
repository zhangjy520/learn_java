package cc.gukeer.syncdata.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ChangeStateDailyHourExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChangeStateDailyHourExample() {
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

        public Criteria andSktsIsNull() {
            addCriterion("skts is null");
            return (Criteria) this;
        }

        public Criteria andSktsIsNotNull() {
            addCriterion("skts is not null");
            return (Criteria) this;
        }

        public Criteria andSktsEqualTo(Integer value) {
            addCriterion("skts =", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsNotEqualTo(Integer value) {
            addCriterion("skts <>", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsGreaterThan(Integer value) {
            addCriterion("skts >", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsGreaterThanOrEqualTo(Integer value) {
            addCriterion("skts >=", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsLessThan(Integer value) {
            addCriterion("skts <", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsLessThanOrEqualTo(Integer value) {
            addCriterion("skts <=", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsIn(List<Integer> values) {
            addCriterion("skts in", values, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsNotIn(List<Integer> values) {
            addCriterion("skts not in", values, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsBetween(Integer value1, Integer value2) {
            addCriterion("skts between", value1, value2, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsNotBetween(Integer value1, Integer value2) {
            addCriterion("skts not between", value1, value2, "skts");
            return (Criteria) this;
        }

        public Criteria andSwksIsNull() {
            addCriterion("swks is null");
            return (Criteria) this;
        }

        public Criteria andSwksIsNotNull() {
            addCriterion("swks is not null");
            return (Criteria) this;
        }

        public Criteria andSwksEqualTo(Integer value) {
            addCriterion("swks =", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksNotEqualTo(Integer value) {
            addCriterion("swks <>", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksGreaterThan(Integer value) {
            addCriterion("swks >", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksGreaterThanOrEqualTo(Integer value) {
            addCriterion("swks >=", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksLessThan(Integer value) {
            addCriterion("swks <", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksLessThanOrEqualTo(Integer value) {
            addCriterion("swks <=", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksIn(List<Integer> values) {
            addCriterion("swks in", values, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksNotIn(List<Integer> values) {
            addCriterion("swks not in", values, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksBetween(Integer value1, Integer value2) {
            addCriterion("swks between", value1, value2, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksNotBetween(Integer value1, Integer value2) {
            addCriterion("swks not between", value1, value2, "swks");
            return (Criteria) this;
        }

        public Criteria andXwksIsNull() {
            addCriterion("xwks is null");
            return (Criteria) this;
        }

        public Criteria andXwksIsNotNull() {
            addCriterion("xwks is not null");
            return (Criteria) this;
        }

        public Criteria andXwksEqualTo(Integer value) {
            addCriterion("xwks =", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksNotEqualTo(Integer value) {
            addCriterion("xwks <>", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksGreaterThan(Integer value) {
            addCriterion("xwks >", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksGreaterThanOrEqualTo(Integer value) {
            addCriterion("xwks >=", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksLessThan(Integer value) {
            addCriterion("xwks <", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksLessThanOrEqualTo(Integer value) {
            addCriterion("xwks <=", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksIn(List<Integer> values) {
            addCriterion("xwks in", values, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksNotIn(List<Integer> values) {
            addCriterion("xwks not in", values, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksBetween(Integer value1, Integer value2) {
            addCriterion("xwks between", value1, value2, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksNotBetween(Integer value1, Integer value2) {
            addCriterion("xwks not between", value1, value2, "xwks");
            return (Criteria) this;
        }

        public Criteria andKjcIsNull() {
            addCriterion("kjc is null");
            return (Criteria) this;
        }

        public Criteria andKjcIsNotNull() {
            addCriterion("kjc is not null");
            return (Criteria) this;
        }

        public Criteria andKjcEqualTo(Integer value) {
            addCriterion("kjc =", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcNotEqualTo(Integer value) {
            addCriterion("kjc <>", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcGreaterThan(Integer value) {
            addCriterion("kjc >", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcGreaterThanOrEqualTo(Integer value) {
            addCriterion("kjc >=", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcLessThan(Integer value) {
            addCriterion("kjc <", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcLessThanOrEqualTo(Integer value) {
            addCriterion("kjc <=", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcIn(List<Integer> values) {
            addCriterion("kjc in", values, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcNotIn(List<Integer> values) {
            addCriterion("kjc not in", values, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcBetween(Integer value1, Integer value2) {
            addCriterion("kjc between", value1, value2, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcNotBetween(Integer value1, Integer value2) {
            addCriterion("kjc not between", value1, value2, "kjc");
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

        public Criteria andXqEqualTo(String value) {
            addCriterion("xq =", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotEqualTo(String value) {
            addCriterion("xq <>", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqGreaterThan(String value) {
            addCriterion("xq >", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqGreaterThanOrEqualTo(String value) {
            addCriterion("xq >=", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLessThan(String value) {
            addCriterion("xq <", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLessThanOrEqualTo(String value) {
            addCriterion("xq <=", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLike(String value) {
            addCriterion("xq like", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotLike(String value) {
            addCriterion("xq not like", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqIn(List<String> values) {
            addCriterion("xq in", values, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotIn(List<String> values) {
            addCriterion("xq not in", values, "xq");
            return (Criteria) this;
        }

        public Criteria andXqBetween(String value1, String value2) {
            addCriterion("xq between", value1, value2, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotBetween(String value1, String value2) {
            addCriterion("xq not between", value1, value2, "xq");
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

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
            return (Criteria) this;
        }

        public Criteria andClassIdLikeInsensitive(String value) {
            addCriterion("upper(class_id) like", value.toUpperCase(), "classId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLikeInsensitive(String value) {
            addCriterion("upper(period_id) like", value.toUpperCase(), "periodId");
            return (Criteria) this;
        }

        public Criteria andXnLikeInsensitive(String value) {
            addCriterion("upper(xn) like", value.toUpperCase(), "xn");
            return (Criteria) this;
        }

        public Criteria andXqLikeInsensitive(String value) {
            addCriterion("upper(xq) like", value.toUpperCase(), "xq");
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