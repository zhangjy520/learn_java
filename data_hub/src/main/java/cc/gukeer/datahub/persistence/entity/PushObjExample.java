package cc.gukeer.datahub.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class PushObjExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PushObjExample() {
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

        public Criteria andObjTableNameIsNull() {
            addCriterion("obj_table_name is null");
            return (Criteria) this;
        }

        public Criteria andObjTableNameIsNotNull() {
            addCriterion("obj_table_name is not null");
            return (Criteria) this;
        }

        public Criteria andObjTableNameEqualTo(String value) {
            addCriterion("obj_table_name =", value, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameNotEqualTo(String value) {
            addCriterion("obj_table_name <>", value, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameGreaterThan(String value) {
            addCriterion("obj_table_name >", value, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("obj_table_name >=", value, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameLessThan(String value) {
            addCriterion("obj_table_name <", value, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameLessThanOrEqualTo(String value) {
            addCriterion("obj_table_name <=", value, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameLike(String value) {
            addCriterion("obj_table_name like", value, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameNotLike(String value) {
            addCriterion("obj_table_name not like", value, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameIn(List<String> values) {
            addCriterion("obj_table_name in", values, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameNotIn(List<String> values) {
            addCriterion("obj_table_name not in", values, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameBetween(String value1, String value2) {
            addCriterion("obj_table_name between", value1, value2, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjTableNameNotBetween(String value1, String value2) {
            addCriterion("obj_table_name not between", value1, value2, "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjNameIsNull() {
            addCriterion("obj_name is null");
            return (Criteria) this;
        }

        public Criteria andObjNameIsNotNull() {
            addCriterion("obj_name is not null");
            return (Criteria) this;
        }

        public Criteria andObjNameEqualTo(String value) {
            addCriterion("obj_name =", value, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameNotEqualTo(String value) {
            addCriterion("obj_name <>", value, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameGreaterThan(String value) {
            addCriterion("obj_name >", value, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameGreaterThanOrEqualTo(String value) {
            addCriterion("obj_name >=", value, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameLessThan(String value) {
            addCriterion("obj_name <", value, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameLessThanOrEqualTo(String value) {
            addCriterion("obj_name <=", value, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameLike(String value) {
            addCriterion("obj_name like", value, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameNotLike(String value) {
            addCriterion("obj_name not like", value, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameIn(List<String> values) {
            addCriterion("obj_name in", values, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameNotIn(List<String> values) {
            addCriterion("obj_name not in", values, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameBetween(String value1, String value2) {
            addCriterion("obj_name between", value1, value2, "objName");
            return (Criteria) this;
        }

        public Criteria andObjNameNotBetween(String value1, String value2) {
            addCriterion("obj_name not between", value1, value2, "objName");
            return (Criteria) this;
        }

        public Criteria andIsAbleIsNull() {
            addCriterion("is_able is null");
            return (Criteria) this;
        }

        public Criteria andIsAbleIsNotNull() {
            addCriterion("is_able is not null");
            return (Criteria) this;
        }

        public Criteria andIsAbleEqualTo(Integer value) {
            addCriterion("is_able =", value, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleNotEqualTo(Integer value) {
            addCriterion("is_able <>", value, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleGreaterThan(Integer value) {
            addCriterion("is_able >", value, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_able >=", value, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleLessThan(Integer value) {
            addCriterion("is_able <", value, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleLessThanOrEqualTo(Integer value) {
            addCriterion("is_able <=", value, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleIn(List<Integer> values) {
            addCriterion("is_able in", values, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleNotIn(List<Integer> values) {
            addCriterion("is_able not in", values, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleBetween(Integer value1, Integer value2) {
            addCriterion("is_able between", value1, value2, "isAble");
            return (Criteria) this;
        }

        public Criteria andIsAbleNotBetween(Integer value1, Integer value2) {
            addCriterion("is_able not between", value1, value2, "isAble");
            return (Criteria) this;
        }

        public Criteria andObjAbstractIsNull() {
            addCriterion("obj_abstract is null");
            return (Criteria) this;
        }

        public Criteria andObjAbstractIsNotNull() {
            addCriterion("obj_abstract is not null");
            return (Criteria) this;
        }

        public Criteria andObjAbstractEqualTo(String value) {
            addCriterion("obj_abstract =", value, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractNotEqualTo(String value) {
            addCriterion("obj_abstract <>", value, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractGreaterThan(String value) {
            addCriterion("obj_abstract >", value, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractGreaterThanOrEqualTo(String value) {
            addCriterion("obj_abstract >=", value, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractLessThan(String value) {
            addCriterion("obj_abstract <", value, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractLessThanOrEqualTo(String value) {
            addCriterion("obj_abstract <=", value, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractLike(String value) {
            addCriterion("obj_abstract like", value, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractNotLike(String value) {
            addCriterion("obj_abstract not like", value, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractIn(List<String> values) {
            addCriterion("obj_abstract in", values, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractNotIn(List<String> values) {
            addCriterion("obj_abstract not in", values, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractBetween(String value1, String value2) {
            addCriterion("obj_abstract between", value1, value2, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andObjAbstractNotBetween(String value1, String value2) {
            addCriterion("obj_abstract not between", value1, value2, "objAbstract");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andObjTableNameLikeInsensitive(String value) {
            addCriterion("upper(obj_table_name) like", value.toUpperCase(), "objTableName");
            return (Criteria) this;
        }

        public Criteria andObjNameLikeInsensitive(String value) {
            addCriterion("upper(obj_name) like", value.toUpperCase(), "objName");
            return (Criteria) this;
        }

        public Criteria andObjAbstractLikeInsensitive(String value) {
            addCriterion("upper(obj_abstract) like", value.toUpperCase(), "objAbstract");
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