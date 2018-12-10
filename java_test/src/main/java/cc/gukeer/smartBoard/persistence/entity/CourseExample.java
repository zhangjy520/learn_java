package cc.gukeer.smartBoard.persistence.entity;

import java.util.List;
import java.util.ArrayList;

public class CourseExample {

	protected String orderByClause;

	protected List<Criteria> oredCriteria;

	protected boolean distinct;

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
	
	    public Criteria andIdEqualTo(Integer value) {
	        addCriterion("id =", value, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdNotEqualTo(Integer value) {
	        addCriterion("id <>", value, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdGreaterThan(Integer value) {
	        addCriterion("id >", value, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
	        addCriterion("id >=", value, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdLessThan(Integer value) {
	        addCriterion("id <", value, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdLessThanOrEqualTo(Integer value) {
	        addCriterion("id <=", value, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdIn(List<Integer> values) {
	        addCriterion("id in", values, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdNotIn(List<Integer> values) {
	        addCriterion("id not in", values, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdBetween(Integer value1, Integer value2) {
	        addCriterion("id between", value1, value2, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andIdNotBetween(Integer value1, Integer value2) {
	        addCriterion("id not between", value1, value2, "id");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidIsNull() {
	        addCriterion("cid is null");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidIsNotNull() {
	        addCriterion("cid is not null");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidEqualTo(Integer value) {
	        addCriterion("cid =", value, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidNotEqualTo(Integer value) {
	        addCriterion("cid <>", value, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidGreaterThan(Integer value) {
	        addCriterion("cid >", value, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidGreaterThanOrEqualTo(Integer value) {
	        addCriterion("cid >=", value, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidLessThan(Integer value) {
	        addCriterion("cid <", value, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidLessThanOrEqualTo(Integer value) {
	        addCriterion("cid <=", value, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidIn(List<Integer> values) {
	        addCriterion("cid in", values, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidNotIn(List<Integer> values) {
	        addCriterion("cid not in", values, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidBetween(Integer value1, Integer value2) {
	        addCriterion("cid between", value1, value2, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andCidNotBetween(Integer value1, Integer value2) {
	        addCriterion("cid not between", value1, value2, "cid");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameIsNull() {
	        addCriterion("name is null");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameIsNotNull() {
	        addCriterion("name is not null");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameEqualTo(String value) {
	        addCriterion("name =", value, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameNotEqualTo(String value) {
	        addCriterion("name <>", value, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameGreaterThan(String value) {
	        addCriterion("name >", value, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameGreaterThanOrEqualTo(String value) {
	        addCriterion("name >=", value, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameLessThan(String value) {
	        addCriterion("name <", value, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameLessThanOrEqualTo(String value) {
	        addCriterion("name <=", value, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameLike(String value) {
	        addCriterion("name like", value, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameNotLike(String value) {
	        addCriterion("name not like", value, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameIn(List<String> values) {
	        addCriterion("name in", values, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameNotIn(List<String> values) {
	        addCriterion("name not in", values, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameBetween(String value1, String value2) {
	        addCriterion("name between", value1, value2, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameNotBetween(String value1, String value2) {
	        addCriterion("name not between", value1, value2, "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeIsNull() {
	        addCriterion("time is null");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeIsNotNull() {
	        addCriterion("time is not null");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeEqualTo(Integer value) {
	        addCriterion("time =", value, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeNotEqualTo(Integer value) {
	        addCriterion("time <>", value, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeGreaterThan(Integer value) {
	        addCriterion("time >", value, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
	        addCriterion("time >=", value, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeLessThan(Integer value) {
	        addCriterion("time <", value, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeLessThanOrEqualTo(Integer value) {
	        addCriterion("time <=", value, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeIn(List<Integer> values) {
	        addCriterion("time in", values, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeNotIn(List<Integer> values) {
	        addCriterion("time not in", values, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeBetween(Integer value1, Integer value2) {
	        addCriterion("time between", value1, value2, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andTimeNotBetween(Integer value1, Integer value2) {
	        addCriterion("time not between", value1, value2, "time");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddIsNull() {
	        addCriterion("dddd is null");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddIsNotNull() {
	        addCriterion("dddd is not null");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddEqualTo(String value) {
	        addCriterion("dddd =", value, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddNotEqualTo(String value) {
	        addCriterion("dddd <>", value, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddGreaterThan(String value) {
	        addCriterion("dddd >", value, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddGreaterThanOrEqualTo(String value) {
	        addCriterion("dddd >=", value, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddLessThan(String value) {
	        addCriterion("dddd <", value, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddLessThanOrEqualTo(String value) {
	        addCriterion("dddd <=", value, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddLike(String value) {
	        addCriterion("dddd like", value, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddNotLike(String value) {
	        addCriterion("dddd not like", value, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddIn(List<String> values) {
	        addCriterion("dddd in", values, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddNotIn(List<String> values) {
	        addCriterion("dddd not in", values, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddBetween(String value1, String value2) {
	        addCriterion("dddd between", value1, value2, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddNotBetween(String value1, String value2) {
	        addCriterion("dddd not between", value1, value2, "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andSIsNull() {
	        addCriterion("s is null");
	        return (Criteria) this;
	    }
	
	    public Criteria andSIsNotNull() {
	        addCriterion("s is not null");
	        return (Criteria) this;
	    }
	
	    public Criteria andSEqualTo(String value) {
	        addCriterion("s =", value, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSNotEqualTo(String value) {
	        addCriterion("s <>", value, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSGreaterThan(String value) {
	        addCriterion("s >", value, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSGreaterThanOrEqualTo(String value) {
	        addCriterion("s >=", value, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSLessThan(String value) {
	        addCriterion("s <", value, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSLessThanOrEqualTo(String value) {
	        addCriterion("s <=", value, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSLike(String value) {
	        addCriterion("s like", value, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSNotLike(String value) {
	        addCriterion("s not like", value, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSIn(List<String> values) {
	        addCriterion("s in", values, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSNotIn(List<String> values) {
	        addCriterion("s not in", values, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSBetween(String value1, String value2) {
	        addCriterion("s between", value1, value2, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andSNotBetween(String value1, String value2) {
	        addCriterion("s not between", value1, value2, "s");
	        return (Criteria) this;
	    }
	
	    public Criteria andNameLikeInsensitive(String value) {
	        addCriterion("upper(name) like", value.toUpperCase(), "name");
	        return (Criteria) this;
	    }
	
	    public Criteria andDdddLikeInsensitive(String value) {
	        addCriterion("upper(dddd) like", value.toUpperCase(), "dddd");
	        return (Criteria) this;
	    }
	
	    public Criteria andSLikeInsensitive(String value) {
	        addCriterion("upper(s) like", value.toUpperCase(), "s");
	        return (Criteria) this;
	    }
	}	public static class Criteria extends GeneratedCriteria {
	
	    protected Criteria() {
	        super();
	    }
	}	public static class Criterion {
	
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