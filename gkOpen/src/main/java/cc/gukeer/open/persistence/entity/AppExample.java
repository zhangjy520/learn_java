package cc.gukeer.open.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class AppExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppExample() {
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

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(Integer value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(Integer value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(Integer value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(Integer value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<Integer> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<Integer> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(Integer value1, Integer value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andTargetUserIsNull() {
            addCriterion("target_user is null");
            return (Criteria) this;
        }

        public Criteria andTargetUserIsNotNull() {
            addCriterion("target_user is not null");
            return (Criteria) this;
        }

        public Criteria andTargetUserEqualTo(String value) {
            addCriterion("target_user =", value, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserNotEqualTo(String value) {
            addCriterion("target_user <>", value, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserGreaterThan(String value) {
            addCriterion("target_user >", value, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserGreaterThanOrEqualTo(String value) {
            addCriterion("target_user >=", value, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserLessThan(String value) {
            addCriterion("target_user <", value, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserLessThanOrEqualTo(String value) {
            addCriterion("target_user <=", value, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserLike(String value) {
            addCriterion("target_user like", value, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserNotLike(String value) {
            addCriterion("target_user not like", value, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserIn(List<String> values) {
            addCriterion("target_user in", values, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserNotIn(List<String> values) {
            addCriterion("target_user not in", values, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserBetween(String value1, String value2) {
            addCriterion("target_user between", value1, value2, "targetUser");
            return (Criteria) this;
        }

        public Criteria andTargetUserNotBetween(String value1, String value2) {
            addCriterion("target_user not between", value1, value2, "targetUser");
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

        public Criteria andIsFreeIsNull() {
            addCriterion("is_free is null");
            return (Criteria) this;
        }

        public Criteria andIsFreeIsNotNull() {
            addCriterion("is_free is not null");
            return (Criteria) this;
        }

        public Criteria andIsFreeEqualTo(Integer value) {
            addCriterion("is_free =", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotEqualTo(Integer value) {
            addCriterion("is_free <>", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeGreaterThan(Integer value) {
            addCriterion("is_free >", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_free >=", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeLessThan(Integer value) {
            addCriterion("is_free <", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeLessThanOrEqualTo(Integer value) {
            addCriterion("is_free <=", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeIn(List<Integer> values) {
            addCriterion("is_free in", values, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotIn(List<Integer> values) {
            addCriterion("is_free not in", values, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeBetween(Integer value1, Integer value2) {
            addCriterion("is_free between", value1, value2, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_free not between", value1, value2, "isFree");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotIsNull() {
            addCriterion("app_screenshot is null");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotIsNotNull() {
            addCriterion("app_screenshot is not null");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotEqualTo(String value) {
            addCriterion("app_screenshot =", value, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotNotEqualTo(String value) {
            addCriterion("app_screenshot <>", value, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotGreaterThan(String value) {
            addCriterion("app_screenshot >", value, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotGreaterThanOrEqualTo(String value) {
            addCriterion("app_screenshot >=", value, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotLessThan(String value) {
            addCriterion("app_screenshot <", value, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotLessThanOrEqualTo(String value) {
            addCriterion("app_screenshot <=", value, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotLike(String value) {
            addCriterion("app_screenshot like", value, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotNotLike(String value) {
            addCriterion("app_screenshot not like", value, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotIn(List<String> values) {
            addCriterion("app_screenshot in", values, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotNotIn(List<String> values) {
            addCriterion("app_screenshot not in", values, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotBetween(String value1, String value2) {
            addCriterion("app_screenshot between", value1, value2, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotNotBetween(String value1, String value2) {
            addCriterion("app_screenshot not between", value1, value2, "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppAbstructIsNull() {
            addCriterion("app_abstruct is null");
            return (Criteria) this;
        }

        public Criteria andAppAbstructIsNotNull() {
            addCriterion("app_abstruct is not null");
            return (Criteria) this;
        }

        public Criteria andAppAbstructEqualTo(String value) {
            addCriterion("app_abstruct =", value, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructNotEqualTo(String value) {
            addCriterion("app_abstruct <>", value, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructGreaterThan(String value) {
            addCriterion("app_abstruct >", value, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructGreaterThanOrEqualTo(String value) {
            addCriterion("app_abstruct >=", value, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructLessThan(String value) {
            addCriterion("app_abstruct <", value, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructLessThanOrEqualTo(String value) {
            addCriterion("app_abstruct <=", value, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructLike(String value) {
            addCriterion("app_abstruct like", value, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructNotLike(String value) {
            addCriterion("app_abstruct not like", value, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructIn(List<String> values) {
            addCriterion("app_abstruct in", values, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructNotIn(List<String> values) {
            addCriterion("app_abstruct not in", values, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructBetween(String value1, String value2) {
            addCriterion("app_abstruct between", value1, value2, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppAbstructNotBetween(String value1, String value2) {
            addCriterion("app_abstruct not between", value1, value2, "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppUrlIsNull() {
            addCriterion("app_url is null");
            return (Criteria) this;
        }

        public Criteria andAppUrlIsNotNull() {
            addCriterion("app_url is not null");
            return (Criteria) this;
        }

        public Criteria andAppUrlEqualTo(String value) {
            addCriterion("app_url =", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlNotEqualTo(String value) {
            addCriterion("app_url <>", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlGreaterThan(String value) {
            addCriterion("app_url >", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlGreaterThanOrEqualTo(String value) {
            addCriterion("app_url >=", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlLessThan(String value) {
            addCriterion("app_url <", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlLessThanOrEqualTo(String value) {
            addCriterion("app_url <=", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlLike(String value) {
            addCriterion("app_url like", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlNotLike(String value) {
            addCriterion("app_url not like", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlIn(List<String> values) {
            addCriterion("app_url in", values, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlNotIn(List<String> values) {
            addCriterion("app_url not in", values, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlBetween(String value1, String value2) {
            addCriterion("app_url between", value1, value2, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlNotBetween(String value1, String value2) {
            addCriterion("app_url not between", value1, value2, "appUrl");
            return (Criteria) this;
        }

        public Criteria andDemoAccountIsNull() {
            addCriterion("demo_account is null");
            return (Criteria) this;
        }

        public Criteria andDemoAccountIsNotNull() {
            addCriterion("demo_account is not null");
            return (Criteria) this;
        }

        public Criteria andDemoAccountEqualTo(String value) {
            addCriterion("demo_account =", value, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountNotEqualTo(String value) {
            addCriterion("demo_account <>", value, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountGreaterThan(String value) {
            addCriterion("demo_account >", value, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountGreaterThanOrEqualTo(String value) {
            addCriterion("demo_account >=", value, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountLessThan(String value) {
            addCriterion("demo_account <", value, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountLessThanOrEqualTo(String value) {
            addCriterion("demo_account <=", value, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountLike(String value) {
            addCriterion("demo_account like", value, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountNotLike(String value) {
            addCriterion("demo_account not like", value, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountIn(List<String> values) {
            addCriterion("demo_account in", values, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountNotIn(List<String> values) {
            addCriterion("demo_account not in", values, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountBetween(String value1, String value2) {
            addCriterion("demo_account between", value1, value2, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andDemoAccountNotBetween(String value1, String value2) {
            addCriterion("demo_account not between", value1, value2, "demoAccount");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("app_secret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("app_secret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("app_secret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("app_secret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("app_secret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("app_secret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("app_secret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("app_secret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("app_secret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("app_secret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("app_secret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("app_secret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("app_secret not between", value1, value2, "appSecret");
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

        public Criteria andCheckStatusIsNull() {
            addCriterion("check_status is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("check_status is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Integer value) {
            addCriterion("check_status =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Integer value) {
            addCriterion("check_status <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Integer value) {
            addCriterion("check_status >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_status >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Integer value) {
            addCriterion("check_status <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Integer value) {
            addCriterion("check_status <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Integer> values) {
            addCriterion("check_status in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Integer> values) {
            addCriterion("check_status not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Integer value1, Integer value2) {
            addCriterion("check_status between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("check_status not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andAppRankIsNull() {
            addCriterion("app_rank is null");
            return (Criteria) this;
        }

        public Criteria andAppRankIsNotNull() {
            addCriterion("app_rank is not null");
            return (Criteria) this;
        }

        public Criteria andAppRankEqualTo(Integer value) {
            addCriterion("app_rank =", value, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankNotEqualTo(Integer value) {
            addCriterion("app_rank <>", value, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankGreaterThan(Integer value) {
            addCriterion("app_rank >", value, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_rank >=", value, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankLessThan(Integer value) {
            addCriterion("app_rank <", value, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankLessThanOrEqualTo(Integer value) {
            addCriterion("app_rank <=", value, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankIn(List<Integer> values) {
            addCriterion("app_rank in", values, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankNotIn(List<Integer> values) {
            addCriterion("app_rank not in", values, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankBetween(Integer value1, Integer value2) {
            addCriterion("app_rank between", value1, value2, "appRank");
            return (Criteria) this;
        }

        public Criteria andAppRankNotBetween(Integer value1, Integer value2) {
            addCriterion("app_rank not between", value1, value2, "appRank");
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

        public Criteria andClientIdIsNull() {
            addCriterion("client_Id is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("client_Id is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(String value) {
            addCriterion("client_Id =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(String value) {
            addCriterion("client_Id <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(String value) {
            addCriterion("client_Id >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(String value) {
            addCriterion("client_Id >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(String value) {
            addCriterion("client_Id <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(String value) {
            addCriterion("client_Id <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLike(String value) {
            addCriterion("client_Id like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotLike(String value) {
            addCriterion("client_Id not like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<String> values) {
            addCriterion("client_Id in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<String> values) {
            addCriterion("client_Id not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(String value1, String value2) {
            addCriterion("client_Id between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(String value1, String value2) {
            addCriterion("client_Id not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientSecretIsNull() {
            addCriterion("client_secret is null");
            return (Criteria) this;
        }

        public Criteria andClientSecretIsNotNull() {
            addCriterion("client_secret is not null");
            return (Criteria) this;
        }

        public Criteria andClientSecretEqualTo(String value) {
            addCriterion("client_secret =", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretNotEqualTo(String value) {
            addCriterion("client_secret <>", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretGreaterThan(String value) {
            addCriterion("client_secret >", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretGreaterThanOrEqualTo(String value) {
            addCriterion("client_secret >=", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretLessThan(String value) {
            addCriterion("client_secret <", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretLessThanOrEqualTo(String value) {
            addCriterion("client_secret <=", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretLike(String value) {
            addCriterion("client_secret like", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretNotLike(String value) {
            addCriterion("client_secret not like", value, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretIn(List<String> values) {
            addCriterion("client_secret in", values, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretNotIn(List<String> values) {
            addCriterion("client_secret not in", values, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretBetween(String value1, String value2) {
            addCriterion("client_secret between", value1, value2, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andClientSecretNotBetween(String value1, String value2) {
            addCriterion("client_secret not between", value1, value2, "clientSecret");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andLogoLikeInsensitive(String value) {
            addCriterion("upper(logo) like", value.toUpperCase(), "logo");
            return (Criteria) this;
        }

        public Criteria andTargetUserLikeInsensitive(String value) {
            addCriterion("upper(target_user) like", value.toUpperCase(), "targetUser");
            return (Criteria) this;
        }

        public Criteria andVersionLikeInsensitive(String value) {
            addCriterion("upper(version) like", value.toUpperCase(), "version");
            return (Criteria) this;
        }

        public Criteria andAppScreenshotLikeInsensitive(String value) {
            addCriterion("upper(app_screenshot) like", value.toUpperCase(), "appScreenshot");
            return (Criteria) this;
        }

        public Criteria andAppAbstructLikeInsensitive(String value) {
            addCriterion("upper(app_abstruct) like", value.toUpperCase(), "appAbstruct");
            return (Criteria) this;
        }

        public Criteria andAppUrlLikeInsensitive(String value) {
            addCriterion("upper(app_url) like", value.toUpperCase(), "appUrl");
            return (Criteria) this;
        }

        public Criteria andDemoAccountLikeInsensitive(String value) {
            addCriterion("upper(demo_account) like", value.toUpperCase(), "demoAccount");
            return (Criteria) this;
        }

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(user_id) like", value.toUpperCase(), "userId");
            return (Criteria) this;
        }

        public Criteria andAppSecretLikeInsensitive(String value) {
            addCriterion("upper(app_secret) like", value.toUpperCase(), "appSecret");
            return (Criteria) this;
        }

        public Criteria andClientIdLikeInsensitive(String value) {
            addCriterion("upper(client_Id) like", value.toUpperCase(), "clientId");
            return (Criteria) this;
        }

        public Criteria andClientSecretLikeInsensitive(String value) {
            addCriterion("upper(client_secret) like", value.toUpperCase(), "clientSecret");
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