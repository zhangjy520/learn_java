package cc.gukeer.smartRing.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class RingMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RingMessageExample() {
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

        public Criteria andRingMacIsNull() {
            addCriterion("ring_mac is null");
            return (Criteria) this;
        }

        public Criteria andRingMacIsNotNull() {
            addCriterion("ring_mac is not null");
            return (Criteria) this;
        }

        public Criteria andRingMacEqualTo(String value) {
            addCriterion("ring_mac =", value, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacNotEqualTo(String value) {
            addCriterion("ring_mac <>", value, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacGreaterThan(String value) {
            addCriterion("ring_mac >", value, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacGreaterThanOrEqualTo(String value) {
            addCriterion("ring_mac >=", value, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacLessThan(String value) {
            addCriterion("ring_mac <", value, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacLessThanOrEqualTo(String value) {
            addCriterion("ring_mac <=", value, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacLike(String value) {
            addCriterion("ring_mac like", value, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacNotLike(String value) {
            addCriterion("ring_mac not like", value, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacIn(List<String> values) {
            addCriterion("ring_mac in", values, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacNotIn(List<String> values) {
            addCriterion("ring_mac not in", values, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacBetween(String value1, String value2) {
            addCriterion("ring_mac between", value1, value2, "ringMac");
            return (Criteria) this;
        }

        public Criteria andRingMacNotBetween(String value1, String value2) {
            addCriterion("ring_mac not between", value1, value2, "ringMac");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNull() {
            addCriterion("student_name is null");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNotNull() {
            addCriterion("student_name is not null");
            return (Criteria) this;
        }

        public Criteria andStudentNameEqualTo(String value) {
            addCriterion("student_name =", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotEqualTo(String value) {
            addCriterion("student_name <>", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThan(String value) {
            addCriterion("student_name >", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThanOrEqualTo(String value) {
            addCriterion("student_name >=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThan(String value) {
            addCriterion("student_name <", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThanOrEqualTo(String value) {
            addCriterion("student_name <=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLike(String value) {
            addCriterion("student_name like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotLike(String value) {
            addCriterion("student_name not like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameIn(List<String> values) {
            addCriterion("student_name in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotIn(List<String> values) {
            addCriterion("student_name not in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameBetween(String value1, String value2) {
            addCriterion("student_name between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotBetween(String value1, String value2) {
            addCriterion("student_name not between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStationMacIsNull() {
            addCriterion("station_mac is null");
            return (Criteria) this;
        }

        public Criteria andStationMacIsNotNull() {
            addCriterion("station_mac is not null");
            return (Criteria) this;
        }

        public Criteria andStationMacEqualTo(String value) {
            addCriterion("station_mac =", value, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacNotEqualTo(String value) {
            addCriterion("station_mac <>", value, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacGreaterThan(String value) {
            addCriterion("station_mac >", value, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacGreaterThanOrEqualTo(String value) {
            addCriterion("station_mac >=", value, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacLessThan(String value) {
            addCriterion("station_mac <", value, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacLessThanOrEqualTo(String value) {
            addCriterion("station_mac <=", value, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacLike(String value) {
            addCriterion("station_mac like", value, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacNotLike(String value) {
            addCriterion("station_mac not like", value, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacIn(List<String> values) {
            addCriterion("station_mac in", values, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacNotIn(List<String> values) {
            addCriterion("station_mac not in", values, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacBetween(String value1, String value2) {
            addCriterion("station_mac between", value1, value2, "stationMac");
            return (Criteria) this;
        }

        public Criteria andStationMacNotBetween(String value1, String value2) {
            addCriterion("station_mac not between", value1, value2, "stationMac");
            return (Criteria) this;
        }

        public Criteria andLocationNameIsNull() {
            addCriterion("location_name is null");
            return (Criteria) this;
        }

        public Criteria andLocationNameIsNotNull() {
            addCriterion("location_name is not null");
            return (Criteria) this;
        }

        public Criteria andLocationNameEqualTo(String value) {
            addCriterion("location_name =", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotEqualTo(String value) {
            addCriterion("location_name <>", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameGreaterThan(String value) {
            addCriterion("location_name >", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameGreaterThanOrEqualTo(String value) {
            addCriterion("location_name >=", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLessThan(String value) {
            addCriterion("location_name <", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLessThanOrEqualTo(String value) {
            addCriterion("location_name <=", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameLike(String value) {
            addCriterion("location_name like", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotLike(String value) {
            addCriterion("location_name not like", value, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameIn(List<String> values) {
            addCriterion("location_name in", values, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotIn(List<String> values) {
            addCriterion("location_name not in", values, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameBetween(String value1, String value2) {
            addCriterion("location_name between", value1, value2, "locationName");
            return (Criteria) this;
        }

        public Criteria andLocationNameNotBetween(String value1, String value2) {
            addCriterion("location_name not between", value1, value2, "locationName");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(String value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(String value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(String value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(String value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(String value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(String value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLike(String value) {
            addCriterion("balance like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotLike(String value) {
            addCriterion("balance not like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<String> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<String> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(String value1, String value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(String value1, String value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andInfoDateIsNull() {
            addCriterion("info_date is null");
            return (Criteria) this;
        }

        public Criteria andInfoDateIsNotNull() {
            addCriterion("info_date is not null");
            return (Criteria) this;
        }

        public Criteria andInfoDateEqualTo(Long value) {
            addCriterion("info_date =", value, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateNotEqualTo(Long value) {
            addCriterion("info_date <>", value, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateGreaterThan(Long value) {
            addCriterion("info_date >", value, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateGreaterThanOrEqualTo(Long value) {
            addCriterion("info_date >=", value, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateLessThan(Long value) {
            addCriterion("info_date <", value, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateLessThanOrEqualTo(Long value) {
            addCriterion("info_date <=", value, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateIn(List<Long> values) {
            addCriterion("info_date in", values, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateNotIn(List<Long> values) {
            addCriterion("info_date not in", values, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateBetween(Long value1, Long value2) {
            addCriterion("info_date between", value1, value2, "infoDate");
            return (Criteria) this;
        }

        public Criteria andInfoDateNotBetween(Long value1, Long value2) {
            addCriterion("info_date not between", value1, value2, "infoDate");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayIsNull() {
            addCriterion("calories_day is null");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayIsNotNull() {
            addCriterion("calories_day is not null");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayEqualTo(Integer value) {
            addCriterion("calories_day =", value, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayNotEqualTo(Integer value) {
            addCriterion("calories_day <>", value, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayGreaterThan(Integer value) {
            addCriterion("calories_day >", value, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("calories_day >=", value, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayLessThan(Integer value) {
            addCriterion("calories_day <", value, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayLessThanOrEqualTo(Integer value) {
            addCriterion("calories_day <=", value, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayIn(List<Integer> values) {
            addCriterion("calories_day in", values, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayNotIn(List<Integer> values) {
            addCriterion("calories_day not in", values, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayBetween(Integer value1, Integer value2) {
            addCriterion("calories_day between", value1, value2, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andCaloriesDayNotBetween(Integer value1, Integer value2) {
            addCriterion("calories_day not between", value1, value2, "caloriesDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayIsNull() {
            addCriterion("distance_day is null");
            return (Criteria) this;
        }

        public Criteria andDistanceDayIsNotNull() {
            addCriterion("distance_day is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceDayEqualTo(Integer value) {
            addCriterion("distance_day =", value, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayNotEqualTo(Integer value) {
            addCriterion("distance_day <>", value, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayGreaterThan(Integer value) {
            addCriterion("distance_day >", value, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("distance_day >=", value, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayLessThan(Integer value) {
            addCriterion("distance_day <", value, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayLessThanOrEqualTo(Integer value) {
            addCriterion("distance_day <=", value, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayIn(List<Integer> values) {
            addCriterion("distance_day in", values, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayNotIn(List<Integer> values) {
            addCriterion("distance_day not in", values, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayBetween(Integer value1, Integer value2) {
            addCriterion("distance_day between", value1, value2, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andDistanceDayNotBetween(Integer value1, Integer value2) {
            addCriterion("distance_day not between", value1, value2, "distanceDay");
            return (Criteria) this;
        }

        public Criteria andJogDayIsNull() {
            addCriterion("jog_day is null");
            return (Criteria) this;
        }

        public Criteria andJogDayIsNotNull() {
            addCriterion("jog_day is not null");
            return (Criteria) this;
        }

        public Criteria andJogDayEqualTo(Integer value) {
            addCriterion("jog_day =", value, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayNotEqualTo(Integer value) {
            addCriterion("jog_day <>", value, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayGreaterThan(Integer value) {
            addCriterion("jog_day >", value, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("jog_day >=", value, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayLessThan(Integer value) {
            addCriterion("jog_day <", value, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayLessThanOrEqualTo(Integer value) {
            addCriterion("jog_day <=", value, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayIn(List<Integer> values) {
            addCriterion("jog_day in", values, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayNotIn(List<Integer> values) {
            addCriterion("jog_day not in", values, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayBetween(Integer value1, Integer value2) {
            addCriterion("jog_day between", value1, value2, "jogDay");
            return (Criteria) this;
        }

        public Criteria andJogDayNotBetween(Integer value1, Integer value2) {
            addCriterion("jog_day not between", value1, value2, "jogDay");
            return (Criteria) this;
        }

        public Criteria andRunDayIsNull() {
            addCriterion("run_day is null");
            return (Criteria) this;
        }

        public Criteria andRunDayIsNotNull() {
            addCriterion("run_day is not null");
            return (Criteria) this;
        }

        public Criteria andRunDayEqualTo(Integer value) {
            addCriterion("run_day =", value, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayNotEqualTo(Integer value) {
            addCriterion("run_day <>", value, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayGreaterThan(Integer value) {
            addCriterion("run_day >", value, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("run_day >=", value, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayLessThan(Integer value) {
            addCriterion("run_day <", value, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayLessThanOrEqualTo(Integer value) {
            addCriterion("run_day <=", value, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayIn(List<Integer> values) {
            addCriterion("run_day in", values, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayNotIn(List<Integer> values) {
            addCriterion("run_day not in", values, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayBetween(Integer value1, Integer value2) {
            addCriterion("run_day between", value1, value2, "runDay");
            return (Criteria) this;
        }

        public Criteria andRunDayNotBetween(Integer value1, Integer value2) {
            addCriterion("run_day not between", value1, value2, "runDay");
            return (Criteria) this;
        }

        public Criteria andDeepSleepIsNull() {
            addCriterion("deep_sleep is null");
            return (Criteria) this;
        }

        public Criteria andDeepSleepIsNotNull() {
            addCriterion("deep_sleep is not null");
            return (Criteria) this;
        }

        public Criteria andDeepSleepEqualTo(Integer value) {
            addCriterion("deep_sleep =", value, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepNotEqualTo(Integer value) {
            addCriterion("deep_sleep <>", value, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepGreaterThan(Integer value) {
            addCriterion("deep_sleep >", value, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepGreaterThanOrEqualTo(Integer value) {
            addCriterion("deep_sleep >=", value, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepLessThan(Integer value) {
            addCriterion("deep_sleep <", value, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepLessThanOrEqualTo(Integer value) {
            addCriterion("deep_sleep <=", value, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepIn(List<Integer> values) {
            addCriterion("deep_sleep in", values, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepNotIn(List<Integer> values) {
            addCriterion("deep_sleep not in", values, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepBetween(Integer value1, Integer value2) {
            addCriterion("deep_sleep between", value1, value2, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andDeepSleepNotBetween(Integer value1, Integer value2) {
            addCriterion("deep_sleep not between", value1, value2, "deepSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepIsNull() {
            addCriterion("shallow_sleep is null");
            return (Criteria) this;
        }

        public Criteria andShallowSleepIsNotNull() {
            addCriterion("shallow_sleep is not null");
            return (Criteria) this;
        }

        public Criteria andShallowSleepEqualTo(Integer value) {
            addCriterion("shallow_sleep =", value, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepNotEqualTo(Integer value) {
            addCriterion("shallow_sleep <>", value, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepGreaterThan(Integer value) {
            addCriterion("shallow_sleep >", value, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepGreaterThanOrEqualTo(Integer value) {
            addCriterion("shallow_sleep >=", value, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepLessThan(Integer value) {
            addCriterion("shallow_sleep <", value, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepLessThanOrEqualTo(Integer value) {
            addCriterion("shallow_sleep <=", value, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepIn(List<Integer> values) {
            addCriterion("shallow_sleep in", values, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepNotIn(List<Integer> values) {
            addCriterion("shallow_sleep not in", values, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepBetween(Integer value1, Integer value2) {
            addCriterion("shallow_sleep between", value1, value2, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andShallowSleepNotBetween(Integer value1, Integer value2) {
            addCriterion("shallow_sleep not between", value1, value2, "shallowSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepIsNull() {
            addCriterion("conscious_sleep is null");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepIsNotNull() {
            addCriterion("conscious_sleep is not null");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepEqualTo(Integer value) {
            addCriterion("conscious_sleep =", value, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepNotEqualTo(Integer value) {
            addCriterion("conscious_sleep <>", value, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepGreaterThan(Integer value) {
            addCriterion("conscious_sleep >", value, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepGreaterThanOrEqualTo(Integer value) {
            addCriterion("conscious_sleep >=", value, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepLessThan(Integer value) {
            addCriterion("conscious_sleep <", value, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepLessThanOrEqualTo(Integer value) {
            addCriterion("conscious_sleep <=", value, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepIn(List<Integer> values) {
            addCriterion("conscious_sleep in", values, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepNotIn(List<Integer> values) {
            addCriterion("conscious_sleep not in", values, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepBetween(Integer value1, Integer value2) {
            addCriterion("conscious_sleep between", value1, value2, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andConsciousSleepNotBetween(Integer value1, Integer value2) {
            addCriterion("conscious_sleep not between", value1, value2, "consciousSleep");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeIsNull() {
            addCriterion("awake_time is null");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeIsNotNull() {
            addCriterion("awake_time is not null");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeEqualTo(Integer value) {
            addCriterion("awake_time =", value, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeNotEqualTo(Integer value) {
            addCriterion("awake_time <>", value, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeGreaterThan(Integer value) {
            addCriterion("awake_time >", value, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("awake_time >=", value, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeLessThan(Integer value) {
            addCriterion("awake_time <", value, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeLessThanOrEqualTo(Integer value) {
            addCriterion("awake_time <=", value, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeIn(List<Integer> values) {
            addCriterion("awake_time in", values, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeNotIn(List<Integer> values) {
            addCriterion("awake_time not in", values, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeBetween(Integer value1, Integer value2) {
            addCriterion("awake_time between", value1, value2, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andAwakeTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("awake_time not between", value1, value2, "awakeTime");
            return (Criteria) this;
        }

        public Criteria andSleepQualityIsNull() {
            addCriterion("sleep_quality is null");
            return (Criteria) this;
        }

        public Criteria andSleepQualityIsNotNull() {
            addCriterion("sleep_quality is not null");
            return (Criteria) this;
        }

        public Criteria andSleepQualityEqualTo(Float value) {
            addCriterion("sleep_quality =", value, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityNotEqualTo(Float value) {
            addCriterion("sleep_quality <>", value, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityGreaterThan(Float value) {
            addCriterion("sleep_quality >", value, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityGreaterThanOrEqualTo(Float value) {
            addCriterion("sleep_quality >=", value, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityLessThan(Float value) {
            addCriterion("sleep_quality <", value, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityLessThanOrEqualTo(Float value) {
            addCriterion("sleep_quality <=", value, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityIn(List<Float> values) {
            addCriterion("sleep_quality in", values, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityNotIn(List<Float> values) {
            addCriterion("sleep_quality not in", values, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityBetween(Float value1, Float value2) {
            addCriterion("sleep_quality between", value1, value2, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andSleepQualityNotBetween(Float value1, Float value2) {
            addCriterion("sleep_quality not between", value1, value2, "sleepQuality");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeIsNull() {
            addCriterion("asleep_time is null");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeIsNotNull() {
            addCriterion("asleep_time is not null");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeEqualTo(Long value) {
            addCriterion("asleep_time =", value, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeNotEqualTo(Long value) {
            addCriterion("asleep_time <>", value, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeGreaterThan(Long value) {
            addCriterion("asleep_time >", value, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("asleep_time >=", value, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeLessThan(Long value) {
            addCriterion("asleep_time <", value, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeLessThanOrEqualTo(Long value) {
            addCriterion("asleep_time <=", value, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeIn(List<Long> values) {
            addCriterion("asleep_time in", values, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeNotIn(List<Long> values) {
            addCriterion("asleep_time not in", values, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeBetween(Long value1, Long value2) {
            addCriterion("asleep_time between", value1, value2, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andAsleepTimeNotBetween(Long value1, Long value2) {
            addCriterion("asleep_time not between", value1, value2, "asleepTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeIsNull() {
            addCriterion("sport_time is null");
            return (Criteria) this;
        }

        public Criteria andSportTimeIsNotNull() {
            addCriterion("sport_time is not null");
            return (Criteria) this;
        }

        public Criteria andSportTimeEqualTo(Integer value) {
            addCriterion("sport_time =", value, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeNotEqualTo(Integer value) {
            addCriterion("sport_time <>", value, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeGreaterThan(Integer value) {
            addCriterion("sport_time >", value, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sport_time >=", value, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeLessThan(Integer value) {
            addCriterion("sport_time <", value, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeLessThanOrEqualTo(Integer value) {
            addCriterion("sport_time <=", value, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeIn(List<Integer> values) {
            addCriterion("sport_time in", values, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeNotIn(List<Integer> values) {
            addCriterion("sport_time not in", values, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeBetween(Integer value1, Integer value2) {
            addCriterion("sport_time between", value1, value2, "sportTime");
            return (Criteria) this;
        }

        public Criteria andSportTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("sport_time not between", value1, value2, "sportTime");
            return (Criteria) this;
        }

        public Criteria andStepDayIsNull() {
            addCriterion("step_day is null");
            return (Criteria) this;
        }

        public Criteria andStepDayIsNotNull() {
            addCriterion("step_day is not null");
            return (Criteria) this;
        }

        public Criteria andStepDayEqualTo(Integer value) {
            addCriterion("step_day =", value, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayNotEqualTo(Integer value) {
            addCriterion("step_day <>", value, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayGreaterThan(Integer value) {
            addCriterion("step_day >", value, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_day >=", value, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayLessThan(Integer value) {
            addCriterion("step_day <", value, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayLessThanOrEqualTo(Integer value) {
            addCriterion("step_day <=", value, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayIn(List<Integer> values) {
            addCriterion("step_day in", values, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayNotIn(List<Integer> values) {
            addCriterion("step_day not in", values, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayBetween(Integer value1, Integer value2) {
            addCriterion("step_day between", value1, value2, "stepDay");
            return (Criteria) this;
        }

        public Criteria andStepDayNotBetween(Integer value1, Integer value2) {
            addCriterion("step_day not between", value1, value2, "stepDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayIsNull() {
            addCriterion("walk_day is null");
            return (Criteria) this;
        }

        public Criteria andWalkDayIsNotNull() {
            addCriterion("walk_day is not null");
            return (Criteria) this;
        }

        public Criteria andWalkDayEqualTo(Integer value) {
            addCriterion("walk_day =", value, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayNotEqualTo(Integer value) {
            addCriterion("walk_day <>", value, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayGreaterThan(Integer value) {
            addCriterion("walk_day >", value, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("walk_day >=", value, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayLessThan(Integer value) {
            addCriterion("walk_day <", value, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayLessThanOrEqualTo(Integer value) {
            addCriterion("walk_day <=", value, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayIn(List<Integer> values) {
            addCriterion("walk_day in", values, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayNotIn(List<Integer> values) {
            addCriterion("walk_day not in", values, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayBetween(Integer value1, Integer value2) {
            addCriterion("walk_day between", value1, value2, "walkDay");
            return (Criteria) this;
        }

        public Criteria andWalkDayNotBetween(Integer value1, Integer value2) {
            addCriterion("walk_day not between", value1, value2, "walkDay");
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

        public Criteria andLastUpdateIsNull() {
            addCriterion("last_update is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIsNotNull() {
            addCriterion("last_update is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateEqualTo(Long value) {
            addCriterion("last_update =", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateNotEqualTo(Long value) {
            addCriterion("last_update <>", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateGreaterThan(Long value) {
            addCriterion("last_update >", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateGreaterThanOrEqualTo(Long value) {
            addCriterion("last_update >=", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateLessThan(Long value) {
            addCriterion("last_update <", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateLessThanOrEqualTo(Long value) {
            addCriterion("last_update <=", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIn(List<Long> values) {
            addCriterion("last_update in", values, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateNotIn(List<Long> values) {
            addCriterion("last_update not in", values, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateBetween(Long value1, Long value2) {
            addCriterion("last_update between", value1, value2, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateNotBetween(Long value1, Long value2) {
            addCriterion("last_update not between", value1, value2, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andRingMacLikeInsensitive(String value) {
            addCriterion("upper(ring_mac) like", value.toUpperCase(), "ringMac");
            return (Criteria) this;
        }

        public Criteria andStudentIdLikeInsensitive(String value) {
            addCriterion("upper(student_id) like", value.toUpperCase(), "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentNameLikeInsensitive(String value) {
            addCriterion("upper(student_name) like", value.toUpperCase(), "studentName");
            return (Criteria) this;
        }

        public Criteria andStationMacLikeInsensitive(String value) {
            addCriterion("upper(station_mac) like", value.toUpperCase(), "stationMac");
            return (Criteria) this;
        }

        public Criteria andLocationNameLikeInsensitive(String value) {
            addCriterion("upper(location_name) like", value.toUpperCase(), "locationName");
            return (Criteria) this;
        }

        public Criteria andBalanceLikeInsensitive(String value) {
            addCriterion("upper(balance) like", value.toUpperCase(), "balance");
            return (Criteria) this;
        }

        public Criteria andVersionLikeInsensitive(String value) {
            addCriterion("upper(version) like", value.toUpperCase(), "version");
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