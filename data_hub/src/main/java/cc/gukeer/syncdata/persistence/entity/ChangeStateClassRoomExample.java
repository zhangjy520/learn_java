package cc.gukeer.syncdata.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ChangeStateClassRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChangeStateClassRoomExample() {
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

        public Criteria andClassroomTypeIdIsNull() {
            addCriterion("classroom_type_id is null");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdIsNotNull() {
            addCriterion("classroom_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdEqualTo(String value) {
            addCriterion("classroom_type_id =", value, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdNotEqualTo(String value) {
            addCriterion("classroom_type_id <>", value, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdGreaterThan(String value) {
            addCriterion("classroom_type_id >", value, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("classroom_type_id >=", value, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdLessThan(String value) {
            addCriterion("classroom_type_id <", value, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdLessThanOrEqualTo(String value) {
            addCriterion("classroom_type_id <=", value, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdLike(String value) {
            addCriterion("classroom_type_id like", value, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdNotLike(String value) {
            addCriterion("classroom_type_id not like", value, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdIn(List<String> values) {
            addCriterion("classroom_type_id in", values, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdNotIn(List<String> values) {
            addCriterion("classroom_type_id not in", values, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdBetween(String value1, String value2) {
            addCriterion("classroom_type_id between", value1, value2, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeIdNotBetween(String value1, String value2) {
            addCriterion("classroom_type_id not between", value1, value2, "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameIsNull() {
            addCriterion("classroom_type_name is null");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameIsNotNull() {
            addCriterion("classroom_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameEqualTo(String value) {
            addCriterion("classroom_type_name =", value, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameNotEqualTo(String value) {
            addCriterion("classroom_type_name <>", value, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameGreaterThan(String value) {
            addCriterion("classroom_type_name >", value, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("classroom_type_name >=", value, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameLessThan(String value) {
            addCriterion("classroom_type_name <", value, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameLessThanOrEqualTo(String value) {
            addCriterion("classroom_type_name <=", value, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameLike(String value) {
            addCriterion("classroom_type_name like", value, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameNotLike(String value) {
            addCriterion("classroom_type_name not like", value, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameIn(List<String> values) {
            addCriterion("classroom_type_name in", values, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameNotIn(List<String> values) {
            addCriterion("classroom_type_name not in", values, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameBetween(String value1, String value2) {
            addCriterion("classroom_type_name between", value1, value2, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameNotBetween(String value1, String value2) {
            addCriterion("classroom_type_name not between", value1, value2, "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("area_id like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("area_id not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andNoIsNull() {
            addCriterion("no is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("no is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("no =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("no <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("no >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("no >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("no <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("no <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("no like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("no not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("no in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("no not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("no between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("no not between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andLayerIsNull() {
            addCriterion("layer is null");
            return (Criteria) this;
        }

        public Criteria andLayerIsNotNull() {
            addCriterion("layer is not null");
            return (Criteria) this;
        }

        public Criteria andLayerEqualTo(Integer value) {
            addCriterion("layer =", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotEqualTo(Integer value) {
            addCriterion("layer <>", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerGreaterThan(Integer value) {
            addCriterion("layer >", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerGreaterThanOrEqualTo(Integer value) {
            addCriterion("layer >=", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerLessThan(Integer value) {
            addCriterion("layer <", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerLessThanOrEqualTo(Integer value) {
            addCriterion("layer <=", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerIn(List<Integer> values) {
            addCriterion("layer in", values, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotIn(List<Integer> values) {
            addCriterion("layer not in", values, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerBetween(Integer value1, Integer value2) {
            addCriterion("layer between", value1, value2, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotBetween(Integer value1, Integer value2) {
            addCriterion("layer not between", value1, value2, "layer");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andActnumIsNull() {
            addCriterion("actnum is null");
            return (Criteria) this;
        }

        public Criteria andActnumIsNotNull() {
            addCriterion("actnum is not null");
            return (Criteria) this;
        }

        public Criteria andActnumEqualTo(Integer value) {
            addCriterion("actnum =", value, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumNotEqualTo(Integer value) {
            addCriterion("actnum <>", value, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumGreaterThan(Integer value) {
            addCriterion("actnum >", value, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("actnum >=", value, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumLessThan(Integer value) {
            addCriterion("actnum <", value, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumLessThanOrEqualTo(Integer value) {
            addCriterion("actnum <=", value, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumIn(List<Integer> values) {
            addCriterion("actnum in", values, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumNotIn(List<Integer> values) {
            addCriterion("actnum not in", values, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumBetween(Integer value1, Integer value2) {
            addCriterion("actnum between", value1, value2, "actnum");
            return (Criteria) this;
        }

        public Criteria andActnumNotBetween(Integer value1, Integer value2) {
            addCriterion("actnum not between", value1, value2, "actnum");
            return (Criteria) this;
        }

        public Criteria andTestnumIsNull() {
            addCriterion("testnum is null");
            return (Criteria) this;
        }

        public Criteria andTestnumIsNotNull() {
            addCriterion("testnum is not null");
            return (Criteria) this;
        }

        public Criteria andTestnumEqualTo(Integer value) {
            addCriterion("testnum =", value, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumNotEqualTo(Integer value) {
            addCriterion("testnum <>", value, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumGreaterThan(Integer value) {
            addCriterion("testnum >", value, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("testnum >=", value, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumLessThan(Integer value) {
            addCriterion("testnum <", value, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumLessThanOrEqualTo(Integer value) {
            addCriterion("testnum <=", value, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumIn(List<Integer> values) {
            addCriterion("testnum in", values, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumNotIn(List<Integer> values) {
            addCriterion("testnum not in", values, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumBetween(Integer value1, Integer value2) {
            addCriterion("testnum between", value1, value2, "testnum");
            return (Criteria) this;
        }

        public Criteria andTestnumNotBetween(Integer value1, Integer value2) {
            addCriterion("testnum not between", value1, value2, "testnum");
            return (Criteria) this;
        }

        public Criteria andRnrsIsNull() {
            addCriterion("rnrs is null");
            return (Criteria) this;
        }

        public Criteria andRnrsIsNotNull() {
            addCriterion("rnrs is not null");
            return (Criteria) this;
        }

        public Criteria andRnrsEqualTo(Integer value) {
            addCriterion("rnrs =", value, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsNotEqualTo(Integer value) {
            addCriterion("rnrs <>", value, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsGreaterThan(Integer value) {
            addCriterion("rnrs >", value, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsGreaterThanOrEqualTo(Integer value) {
            addCriterion("rnrs >=", value, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsLessThan(Integer value) {
            addCriterion("rnrs <", value, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsLessThanOrEqualTo(Integer value) {
            addCriterion("rnrs <=", value, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsIn(List<Integer> values) {
            addCriterion("rnrs in", values, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsNotIn(List<Integer> values) {
            addCriterion("rnrs not in", values, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsBetween(Integer value1, Integer value2) {
            addCriterion("rnrs between", value1, value2, "rnrs");
            return (Criteria) this;
        }

        public Criteria andRnrsNotBetween(Integer value1, Integer value2) {
            addCriterion("rnrs not between", value1, value2, "rnrs");
            return (Criteria) this;
        }

        public Criteria andSfxkIsNull() {
            addCriterion("sfxk is null");
            return (Criteria) this;
        }

        public Criteria andSfxkIsNotNull() {
            addCriterion("sfxk is not null");
            return (Criteria) this;
        }

        public Criteria andSfxkEqualTo(Integer value) {
            addCriterion("sfxk =", value, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkNotEqualTo(Integer value) {
            addCriterion("sfxk <>", value, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkGreaterThan(Integer value) {
            addCriterion("sfxk >", value, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkGreaterThanOrEqualTo(Integer value) {
            addCriterion("sfxk >=", value, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkLessThan(Integer value) {
            addCriterion("sfxk <", value, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkLessThanOrEqualTo(Integer value) {
            addCriterion("sfxk <=", value, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkIn(List<Integer> values) {
            addCriterion("sfxk in", values, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkNotIn(List<Integer> values) {
            addCriterion("sfxk not in", values, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkBetween(Integer value1, Integer value2) {
            addCriterion("sfxk between", value1, value2, "sfxk");
            return (Criteria) this;
        }

        public Criteria andSfxkNotBetween(Integer value1, Integer value2) {
            addCriterion("sfxk not between", value1, value2, "sfxk");
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

        public Criteria andClassroomTypeIdLikeInsensitive(String value) {
            addCriterion("upper(classroom_type_id) like", value.toUpperCase(), "classroomTypeId");
            return (Criteria) this;
        }

        public Criteria andClassroomTypeNameLikeInsensitive(String value) {
            addCriterion("upper(classroom_type_name) like", value.toUpperCase(), "classroomTypeName");
            return (Criteria) this;
        }

        public Criteria andAreaIdLikeInsensitive(String value) {
            addCriterion("upper(area_id) like", value.toUpperCase(), "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaLikeInsensitive(String value) {
            addCriterion("upper(area) like", value.toUpperCase(), "area");
            return (Criteria) this;
        }

        public Criteria andNoLikeInsensitive(String value) {
            addCriterion("upper(no) like", value.toUpperCase(), "no");
            return (Criteria) this;
        }

        public Criteria andNumberLikeInsensitive(String value) {
            addCriterion("upper(number) like", value.toUpperCase(), "number");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
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