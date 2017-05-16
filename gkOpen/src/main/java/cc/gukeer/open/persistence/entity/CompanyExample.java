package cc.gukeer.open.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class CompanyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CompanyExample() {
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

        public Criteria andBusinessNameIsNull() {
            addCriterion("business_name is null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNotNull() {
            addCriterion("business_name is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameEqualTo(String value) {
            addCriterion("business_name =", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotEqualTo(String value) {
            addCriterion("business_name <>", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThan(String value) {
            addCriterion("business_name >", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThanOrEqualTo(String value) {
            addCriterion("business_name >=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThan(String value) {
            addCriterion("business_name <", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThanOrEqualTo(String value) {
            addCriterion("business_name <=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLike(String value) {
            addCriterion("business_name like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotLike(String value) {
            addCriterion("business_name not like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIn(List<String> values) {
            addCriterion("business_name in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotIn(List<String> values) {
            addCriterion("business_name not in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameBetween(String value1, String value2) {
            addCriterion("business_name between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotBetween(String value1, String value2) {
            addCriterion("business_name not between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andLicenceNumIsNull() {
            addCriterion("licence_num is null");
            return (Criteria) this;
        }

        public Criteria andLicenceNumIsNotNull() {
            addCriterion("licence_num is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceNumEqualTo(String value) {
            addCriterion("licence_num =", value, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumNotEqualTo(String value) {
            addCriterion("licence_num <>", value, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumGreaterThan(String value) {
            addCriterion("licence_num >", value, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumGreaterThanOrEqualTo(String value) {
            addCriterion("licence_num >=", value, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumLessThan(String value) {
            addCriterion("licence_num <", value, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumLessThanOrEqualTo(String value) {
            addCriterion("licence_num <=", value, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumLike(String value) {
            addCriterion("licence_num like", value, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumNotLike(String value) {
            addCriterion("licence_num not like", value, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumIn(List<String> values) {
            addCriterion("licence_num in", values, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumNotIn(List<String> values) {
            addCriterion("licence_num not in", values, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumBetween(String value1, String value2) {
            addCriterion("licence_num between", value1, value2, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceNumNotBetween(String value1, String value2) {
            addCriterion("licence_num not between", value1, value2, "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteIsNull() {
            addCriterion("licence_site is null");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteIsNotNull() {
            addCriterion("licence_site is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteEqualTo(String value) {
            addCriterion("licence_site =", value, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteNotEqualTo(String value) {
            addCriterion("licence_site <>", value, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteGreaterThan(String value) {
            addCriterion("licence_site >", value, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteGreaterThanOrEqualTo(String value) {
            addCriterion("licence_site >=", value, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteLessThan(String value) {
            addCriterion("licence_site <", value, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteLessThanOrEqualTo(String value) {
            addCriterion("licence_site <=", value, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteLike(String value) {
            addCriterion("licence_site like", value, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteNotLike(String value) {
            addCriterion("licence_site not like", value, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteIn(List<String> values) {
            addCriterion("licence_site in", values, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteNotIn(List<String> values) {
            addCriterion("licence_site not in", values, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteBetween(String value1, String value2) {
            addCriterion("licence_site between", value1, value2, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteNotBetween(String value1, String value2) {
            addCriterion("licence_site not between", value1, value2, "licenceSite");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIsNull() {
            addCriterion("corporate_name is null");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIsNotNull() {
            addCriterion("corporate_name is not null");
            return (Criteria) this;
        }

        public Criteria andCorporateNameEqualTo(String value) {
            addCriterion("corporate_name =", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotEqualTo(String value) {
            addCriterion("corporate_name <>", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameGreaterThan(String value) {
            addCriterion("corporate_name >", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameGreaterThanOrEqualTo(String value) {
            addCriterion("corporate_name >=", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLessThan(String value) {
            addCriterion("corporate_name <", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLessThanOrEqualTo(String value) {
            addCriterion("corporate_name <=", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLike(String value) {
            addCriterion("corporate_name like", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotLike(String value) {
            addCriterion("corporate_name not like", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIn(List<String> values) {
            addCriterion("corporate_name in", values, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotIn(List<String> values) {
            addCriterion("corporate_name not in", values, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameBetween(String value1, String value2) {
            addCriterion("corporate_name between", value1, value2, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotBetween(String value1, String value2) {
            addCriterion("corporate_name not between", value1, value2, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityIsNull() {
            addCriterion("corporate_identity is null");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityIsNotNull() {
            addCriterion("corporate_identity is not null");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityEqualTo(String value) {
            addCriterion("corporate_identity =", value, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityNotEqualTo(String value) {
            addCriterion("corporate_identity <>", value, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityGreaterThan(String value) {
            addCriterion("corporate_identity >", value, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityGreaterThanOrEqualTo(String value) {
            addCriterion("corporate_identity >=", value, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityLessThan(String value) {
            addCriterion("corporate_identity <", value, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityLessThanOrEqualTo(String value) {
            addCriterion("corporate_identity <=", value, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityLike(String value) {
            addCriterion("corporate_identity like", value, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityNotLike(String value) {
            addCriterion("corporate_identity not like", value, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityIn(List<String> values) {
            addCriterion("corporate_identity in", values, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityNotIn(List<String> values) {
            addCriterion("corporate_identity not in", values, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityBetween(String value1, String value2) {
            addCriterion("corporate_identity between", value1, value2, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityNotBetween(String value1, String value2) {
            addCriterion("corporate_identity not between", value1, value2, "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNull() {
            addCriterion("capital is null");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNotNull() {
            addCriterion("capital is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalEqualTo(String value) {
            addCriterion("capital =", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotEqualTo(String value) {
            addCriterion("capital <>", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThan(String value) {
            addCriterion("capital >", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThanOrEqualTo(String value) {
            addCriterion("capital >=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThan(String value) {
            addCriterion("capital <", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThanOrEqualTo(String value) {
            addCriterion("capital <=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLike(String value) {
            addCriterion("capital like", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotLike(String value) {
            addCriterion("capital not like", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalIn(List<String> values) {
            addCriterion("capital in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotIn(List<String> values) {
            addCriterion("capital not in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalBetween(String value1, String value2) {
            addCriterion("capital between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotBetween(String value1, String value2) {
            addCriterion("capital not between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneIsNull() {
            addCriterion("company_phone is null");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneIsNotNull() {
            addCriterion("company_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneEqualTo(String value) {
            addCriterion("company_phone =", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneNotEqualTo(String value) {
            addCriterion("company_phone <>", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneGreaterThan(String value) {
            addCriterion("company_phone >", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("company_phone >=", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneLessThan(String value) {
            addCriterion("company_phone <", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneLessThanOrEqualTo(String value) {
            addCriterion("company_phone <=", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneLike(String value) {
            addCriterion("company_phone like", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneNotLike(String value) {
            addCriterion("company_phone not like", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneIn(List<String> values) {
            addCriterion("company_phone in", values, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneNotIn(List<String> values) {
            addCriterion("company_phone not in", values, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneBetween(String value1, String value2) {
            addCriterion("company_phone between", value1, value2, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneNotBetween(String value1, String value2) {
            addCriterion("company_phone not between", value1, value2, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameIsNull() {
            addCriterion("developer_name is null");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameIsNotNull() {
            addCriterion("developer_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameEqualTo(String value) {
            addCriterion("developer_name =", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameNotEqualTo(String value) {
            addCriterion("developer_name <>", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameGreaterThan(String value) {
            addCriterion("developer_name >", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameGreaterThanOrEqualTo(String value) {
            addCriterion("developer_name >=", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameLessThan(String value) {
            addCriterion("developer_name <", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameLessThanOrEqualTo(String value) {
            addCriterion("developer_name <=", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameLike(String value) {
            addCriterion("developer_name like", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameNotLike(String value) {
            addCriterion("developer_name not like", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameIn(List<String> values) {
            addCriterion("developer_name in", values, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameNotIn(List<String> values) {
            addCriterion("developer_name not in", values, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameBetween(String value1, String value2) {
            addCriterion("developer_name between", value1, value2, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameNotBetween(String value1, String value2) {
            addCriterion("developer_name not between", value1, value2, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneIsNull() {
            addCriterion("developer_phone is null");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneIsNotNull() {
            addCriterion("developer_phone is not null");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneEqualTo(String value) {
            addCriterion("developer_phone =", value, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneNotEqualTo(String value) {
            addCriterion("developer_phone <>", value, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneGreaterThan(String value) {
            addCriterion("developer_phone >", value, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("developer_phone >=", value, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneLessThan(String value) {
            addCriterion("developer_phone <", value, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneLessThanOrEqualTo(String value) {
            addCriterion("developer_phone <=", value, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneLike(String value) {
            addCriterion("developer_phone like", value, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneNotLike(String value) {
            addCriterion("developer_phone not like", value, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneIn(List<String> values) {
            addCriterion("developer_phone in", values, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneNotIn(List<String> values) {
            addCriterion("developer_phone not in", values, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneBetween(String value1, String value2) {
            addCriterion("developer_phone between", value1, value2, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneNotBetween(String value1, String value2) {
            addCriterion("developer_phone not between", value1, value2, "developerPhone");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdIsNull() {
            addCriterion("accessories_id is null");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdIsNotNull() {
            addCriterion("accessories_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdEqualTo(String value) {
            addCriterion("accessories_id =", value, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdNotEqualTo(String value) {
            addCriterion("accessories_id <>", value, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdGreaterThan(String value) {
            addCriterion("accessories_id >", value, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdGreaterThanOrEqualTo(String value) {
            addCriterion("accessories_id >=", value, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdLessThan(String value) {
            addCriterion("accessories_id <", value, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdLessThanOrEqualTo(String value) {
            addCriterion("accessories_id <=", value, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdLike(String value) {
            addCriterion("accessories_id like", value, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdNotLike(String value) {
            addCriterion("accessories_id not like", value, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdIn(List<String> values) {
            addCriterion("accessories_id in", values, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdNotIn(List<String> values) {
            addCriterion("accessories_id not in", values, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdBetween(String value1, String value2) {
            addCriterion("accessories_id between", value1, value2, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdNotBetween(String value1, String value2) {
            addCriterion("accessories_id not between", value1, value2, "accessoriesId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLikeInsensitive(String value) {
            addCriterion("upper(business_name) like", value.toUpperCase(), "businessName");
            return (Criteria) this;
        }

        public Criteria andLicenceNumLikeInsensitive(String value) {
            addCriterion("upper(licence_num) like", value.toUpperCase(), "licenceNum");
            return (Criteria) this;
        }

        public Criteria andLicenceSiteLikeInsensitive(String value) {
            addCriterion("upper(licence_site) like", value.toUpperCase(), "licenceSite");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLikeInsensitive(String value) {
            addCriterion("upper(corporate_name) like", value.toUpperCase(), "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateIdentityLikeInsensitive(String value) {
            addCriterion("upper(corporate_identity) like", value.toUpperCase(), "corporateIdentity");
            return (Criteria) this;
        }

        public Criteria andCapitalLikeInsensitive(String value) {
            addCriterion("upper(capital) like", value.toUpperCase(), "capital");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneLikeInsensitive(String value) {
            addCriterion("upper(company_phone) like", value.toUpperCase(), "companyPhone");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameLikeInsensitive(String value) {
            addCriterion("upper(developer_name) like", value.toUpperCase(), "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperPhoneLikeInsensitive(String value) {
            addCriterion("upper(developer_phone) like", value.toUpperCase(), "developerPhone");
            return (Criteria) this;
        }

        public Criteria andAccessoriesIdLikeInsensitive(String value) {
            addCriterion("upper(accessories_id) like", value.toUpperCase(), "accessoriesId");
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