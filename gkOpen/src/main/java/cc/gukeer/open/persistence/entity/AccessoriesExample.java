package cc.gukeer.open.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class AccessoriesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccessoriesExample() {
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

        public Criteria andIdentityPhotoIsNull() {
            addCriterion("identity_photo is null");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoIsNotNull() {
            addCriterion("identity_photo is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoEqualTo(String value) {
            addCriterion("identity_photo =", value, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoNotEqualTo(String value) {
            addCriterion("identity_photo <>", value, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoGreaterThan(String value) {
            addCriterion("identity_photo >", value, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("identity_photo >=", value, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoLessThan(String value) {
            addCriterion("identity_photo <", value, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoLessThanOrEqualTo(String value) {
            addCriterion("identity_photo <=", value, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoLike(String value) {
            addCriterion("identity_photo like", value, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoNotLike(String value) {
            addCriterion("identity_photo not like", value, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoIn(List<String> values) {
            addCriterion("identity_photo in", values, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoNotIn(List<String> values) {
            addCriterion("identity_photo not in", values, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoBetween(String value1, String value2) {
            addCriterion("identity_photo between", value1, value2, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoNotBetween(String value1, String value2) {
            addCriterion("identity_photo not between", value1, value2, "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLicenceScanIsNull() {
            addCriterion("licence_scan is null");
            return (Criteria) this;
        }

        public Criteria andLicenceScanIsNotNull() {
            addCriterion("licence_scan is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceScanEqualTo(String value) {
            addCriterion("licence_scan =", value, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanNotEqualTo(String value) {
            addCriterion("licence_scan <>", value, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanGreaterThan(String value) {
            addCriterion("licence_scan >", value, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanGreaterThanOrEqualTo(String value) {
            addCriterion("licence_scan >=", value, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanLessThan(String value) {
            addCriterion("licence_scan <", value, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanLessThanOrEqualTo(String value) {
            addCriterion("licence_scan <=", value, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanLike(String value) {
            addCriterion("licence_scan like", value, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanNotLike(String value) {
            addCriterion("licence_scan not like", value, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanIn(List<String> values) {
            addCriterion("licence_scan in", values, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanNotIn(List<String> values) {
            addCriterion("licence_scan not in", values, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanBetween(String value1, String value2) {
            addCriterion("licence_scan between", value1, value2, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andLicenceScanNotBetween(String value1, String value2) {
            addCriterion("licence_scan not between", value1, value2, "licenceScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanIsNull() {
            addCriterion("works_scan is null");
            return (Criteria) this;
        }

        public Criteria andWorksScanIsNotNull() {
            addCriterion("works_scan is not null");
            return (Criteria) this;
        }

        public Criteria andWorksScanEqualTo(String value) {
            addCriterion("works_scan =", value, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanNotEqualTo(String value) {
            addCriterion("works_scan <>", value, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanGreaterThan(String value) {
            addCriterion("works_scan >", value, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanGreaterThanOrEqualTo(String value) {
            addCriterion("works_scan >=", value, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanLessThan(String value) {
            addCriterion("works_scan <", value, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanLessThanOrEqualTo(String value) {
            addCriterion("works_scan <=", value, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanLike(String value) {
            addCriterion("works_scan like", value, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanNotLike(String value) {
            addCriterion("works_scan not like", value, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanIn(List<String> values) {
            addCriterion("works_scan in", values, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanNotIn(List<String> values) {
            addCriterion("works_scan not in", values, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanBetween(String value1, String value2) {
            addCriterion("works_scan between", value1, value2, "worksScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanNotBetween(String value1, String value2) {
            addCriterion("works_scan not between", value1, value2, "worksScan");
            return (Criteria) this;
        }

        public Criteria andQualificationIsNull() {
            addCriterion("qualification is null");
            return (Criteria) this;
        }

        public Criteria andQualificationIsNotNull() {
            addCriterion("qualification is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationEqualTo(String value) {
            addCriterion("qualification =", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationNotEqualTo(String value) {
            addCriterion("qualification <>", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationGreaterThan(String value) {
            addCriterion("qualification >", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationGreaterThanOrEqualTo(String value) {
            addCriterion("qualification >=", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationLessThan(String value) {
            addCriterion("qualification <", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationLessThanOrEqualTo(String value) {
            addCriterion("qualification <=", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationLike(String value) {
            addCriterion("qualification like", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationNotLike(String value) {
            addCriterion("qualification not like", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationIn(List<String> values) {
            addCriterion("qualification in", values, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationNotIn(List<String> values) {
            addCriterion("qualification not in", values, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationBetween(String value1, String value2) {
            addCriterion("qualification between", value1, value2, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationNotBetween(String value1, String value2) {
            addCriterion("qualification not between", value1, value2, "qualification");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andIdentityPhotoLikeInsensitive(String value) {
            addCriterion("upper(identity_photo) like", value.toUpperCase(), "identityPhoto");
            return (Criteria) this;
        }

        public Criteria andLogoLikeInsensitive(String value) {
            addCriterion("upper(logo) like", value.toUpperCase(), "logo");
            return (Criteria) this;
        }

        public Criteria andLicenceScanLikeInsensitive(String value) {
            addCriterion("upper(licence_scan) like", value.toUpperCase(), "licenceScan");
            return (Criteria) this;
        }

        public Criteria andWorksScanLikeInsensitive(String value) {
            addCriterion("upper(works_scan) like", value.toUpperCase(), "worksScan");
            return (Criteria) this;
        }

        public Criteria andQualificationLikeInsensitive(String value) {
            addCriterion("upper(qualification) like", value.toUpperCase(), "qualification");
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