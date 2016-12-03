package com.rc.portal.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TAdmUsersExample extends BaseModel{
    protected String orderByClause;

    protected List oredCriteria;

    public TAdmUsersExample() {
        oredCriteria = new ArrayList();
    }

    protected TAdmUsersExample(TAdmUsersExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public List getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
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
    }

    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            addCriterion(condition, new Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List dateList = new ArrayList();
            Iterator iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new Date(((Date)iter.next()).getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new Date(value1.getTime()), new Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return this;
        }

        public Criteria andIdIn(List values) {
            addCriterion("ID in", values, "id");
            return this;
        }

        public Criteria andIdNotIn(List values) {
            addCriterion("ID not in", values, "id");
            return this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return this;
        }

        public Criteria andLoginNameIsNull() {
            addCriterion("LOGIN_NAME is null");
            return this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("LOGIN_NAME is not null");
            return this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("LOGIN_NAME =", value, "loginName");
            return this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("LOGIN_NAME <>", value, "loginName");
            return this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("LOGIN_NAME >", value, "loginName");
            return this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME >=", value, "loginName");
            return this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("LOGIN_NAME <", value, "loginName");
            return this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME <=", value, "loginName");
            return this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("LOGIN_NAME like", value, "loginName");
            return this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("LOGIN_NAME not like", value, "loginName");
            return this;
        }

        public Criteria andLoginNameIn(List values) {
            addCriterion("LOGIN_NAME in", values, "loginName");
            return this;
        }

        public Criteria andLoginNameNotIn(List values) {
            addCriterion("LOGIN_NAME not in", values, "loginName");
            return this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME between", value1, value2, "loginName");
            return this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME not between", value1, value2, "loginName");
            return this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return this;
        }

        public Criteria andNameIn(List values) {
            addCriterion("NAME in", values, "name");
            return this;
        }

        public Criteria andNameNotIn(List values) {
            addCriterion("NAME not in", values, "name");
            return this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("DEPARTMENT is null");
            return this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("DEPARTMENT is not null");
            return this;
        }

        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("DEPARTMENT =", value, "department");
            return this;
        }

        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("DEPARTMENT <>", value, "department");
            return this;
        }

        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("DEPARTMENT >", value, "department");
            return this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("DEPARTMENT >=", value, "department");
            return this;
        }

        public Criteria andDepartmentLessThan(String value) {
            addCriterion("DEPARTMENT <", value, "department");
            return this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("DEPARTMENT <=", value, "department");
            return this;
        }

        public Criteria andDepartmentLike(String value) {
            addCriterion("DEPARTMENT like", value, "department");
            return this;
        }

        public Criteria andDepartmentNotLike(String value) {
            addCriterion("DEPARTMENT not like", value, "department");
            return this;
        }

        public Criteria andDepartmentIn(List values) {
            addCriterion("DEPARTMENT in", values, "department");
            return this;
        }

        public Criteria andDepartmentNotIn(List values) {
            addCriterion("DEPARTMENT not in", values, "department");
            return this;
        }

        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("DEPARTMENT between", value1, value2, "department");
            return this;
        }

        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("DEPARTMENT not between", value1, value2, "department");
            return this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("PASSWORD is null");
            return this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("PASSWORD is not null");
            return this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("PASSWORD =", value, "password");
            return this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("PASSWORD <>", value, "password");
            return this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("PASSWORD >", value, "password");
            return this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PASSWORD >=", value, "password");
            return this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("PASSWORD <", value, "password");
            return this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("PASSWORD <=", value, "password");
            return this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("PASSWORD like", value, "password");
            return this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("PASSWORD not like", value, "password");
            return this;
        }

        public Criteria andPasswordIn(List values) {
            addCriterion("PASSWORD in", values, "password");
            return this;
        }

        public Criteria andPasswordNotIn(List values) {
            addCriterion("PASSWORD not in", values, "password");
            return this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("PASSWORD between", value1, value2, "password");
            return this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("PASSWORD not between", value1, value2, "password");
            return this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("EXPIRE_TIME is null");
            return this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("EXPIRE_TIME is not null");
            return this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterionForJDBCDate("EXPIRE_TIME =", value, "expireTime");
            return this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EXPIRE_TIME <>", value, "expireTime");
            return this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EXPIRE_TIME >", value, "expireTime");
            return this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EXPIRE_TIME >=", value, "expireTime");
            return this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterionForJDBCDate("EXPIRE_TIME <", value, "expireTime");
            return this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EXPIRE_TIME <=", value, "expireTime");
            return this;
        }

        public Criteria andExpireTimeIn(List values) {
            addCriterionForJDBCDate("EXPIRE_TIME in", values, "expireTime");
            return this;
        }

        public Criteria andExpireTimeNotIn(List values) {
            addCriterionForJDBCDate("EXPIRE_TIME not in", values, "expireTime");
            return this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EXPIRE_TIME between", value1, value2, "expireTime");
            return this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EXPIRE_TIME not between", value1, value2, "expireTime");
            return this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return this;
        }

        public Criteria andTelephoneIn(List values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return this;
        }

        public Criteria andTelephoneNotIn(List values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return this;
        }

        public Criteria andEmailIn(List values) {
            addCriterion("EMAIL in", values, "email");
            return this;
        }

        public Criteria andEmailNotIn(List values) {
            addCriterion("EMAIL not in", values, "email");
            return this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return this;
        }

        public Criteria andUserLevelIsNull() {
            addCriterion("USER_LEVEL is null");
            return this;
        }

        public Criteria andUserLevelIsNotNull() {
            addCriterion("USER_LEVEL is not null");
            return this;
        }

        public Criteria andUserLevelEqualTo(Short value) {
            addCriterion("USER_LEVEL =", value, "userLevel");
            return this;
        }

        public Criteria andUserLevelNotEqualTo(Short value) {
            addCriterion("USER_LEVEL <>", value, "userLevel");
            return this;
        }

        public Criteria andUserLevelGreaterThan(Short value) {
            addCriterion("USER_LEVEL >", value, "userLevel");
            return this;
        }

        public Criteria andUserLevelGreaterThanOrEqualTo(Short value) {
            addCriterion("USER_LEVEL >=", value, "userLevel");
            return this;
        }

        public Criteria andUserLevelLessThan(Short value) {
            addCriterion("USER_LEVEL <", value, "userLevel");
            return this;
        }

        public Criteria andUserLevelLessThanOrEqualTo(Short value) {
            addCriterion("USER_LEVEL <=", value, "userLevel");
            return this;
        }

        public Criteria andUserLevelIn(List values) {
            addCriterion("USER_LEVEL in", values, "userLevel");
            return this;
        }

        public Criteria andUserLevelNotIn(List values) {
            addCriterion("USER_LEVEL not in", values, "userLevel");
            return this;
        }

        public Criteria andUserLevelBetween(Short value1, Short value2) {
            addCriterion("USER_LEVEL between", value1, value2, "userLevel");
            return this;
        }

        public Criteria andUserLevelNotBetween(Short value1, Short value2) {
            addCriterion("USER_LEVEL not between", value1, value2, "userLevel");
            return this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("STATUS =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("STATUS <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("STATUS >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("STATUS >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("STATUS <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("STATUS <=", value, "status");
            return this;
        }

        public Criteria andStatusIn(List values) {
            addCriterion("STATUS in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List values) {
            addCriterion("STATUS not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return this;
        }

        public Criteria andCreateByEqualTo(BigDecimal value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return this;
        }

        public Criteria andCreateByNotEqualTo(BigDecimal value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return this;
        }

        public Criteria andCreateByGreaterThan(BigDecimal value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return this;
        }

        public Criteria andCreateByLessThan(BigDecimal value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return this;
        }

        public Criteria andCreateByLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return this;
        }

        public Criteria andCreateByIn(List values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return this;
        }

        public Criteria andCreateByNotIn(List values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return this;
        }

        public Criteria andCreateByBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return this;
        }

        public Criteria andCreateByNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME =", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <>", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >=", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <=", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeIn(List values) {
            addCriterionForJDBCDate("CREATE_TIME in", values, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotIn(List values) {
            addCriterionForJDBCDate("CREATE_TIME not in", values, "createTime");
            return this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME between", value1, value2, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME not between", value1, value2, "createTime");
            return this;
        }

        public Criteria andLastEditByIsNull() {
            addCriterion("LAST_EDIT_BY is null");
            return this;
        }

        public Criteria andLastEditByIsNotNull() {
            addCriterion("LAST_EDIT_BY is not null");
            return this;
        }

        public Criteria andLastEditByEqualTo(BigDecimal value) {
            addCriterion("LAST_EDIT_BY =", value, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByNotEqualTo(BigDecimal value) {
            addCriterion("LAST_EDIT_BY <>", value, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByGreaterThan(BigDecimal value) {
            addCriterion("LAST_EDIT_BY >", value, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LAST_EDIT_BY >=", value, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByLessThan(BigDecimal value) {
            addCriterion("LAST_EDIT_BY <", value, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LAST_EDIT_BY <=", value, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByIn(List values) {
            addCriterion("LAST_EDIT_BY in", values, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByNotIn(List values) {
            addCriterion("LAST_EDIT_BY not in", values, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAST_EDIT_BY between", value1, value2, "lastEditBy");
            return this;
        }

        public Criteria andLastEditByNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAST_EDIT_BY not between", value1, value2, "lastEditBy");
            return this;
        }

        public Criteria andLastEditTimeIsNull() {
            addCriterion("LAST_EDIT_TIME is null");
            return this;
        }

        public Criteria andLastEditTimeIsNotNull() {
            addCriterion("LAST_EDIT_TIME is not null");
            return this;
        }

        public Criteria andLastEditTimeEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_EDIT_TIME =", value, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_EDIT_TIME <>", value, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("LAST_EDIT_TIME >", value, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_EDIT_TIME >=", value, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeLessThan(Date value) {
            addCriterionForJDBCDate("LAST_EDIT_TIME <", value, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_EDIT_TIME <=", value, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeIn(List values) {
            addCriterionForJDBCDate("LAST_EDIT_TIME in", values, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeNotIn(List values) {
            addCriterionForJDBCDate("LAST_EDIT_TIME not in", values, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_EDIT_TIME between", value1, value2, "lastEditTime");
            return this;
        }

        public Criteria andLastEditTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_EDIT_TIME not between", value1, value2, "lastEditTime");
            return this;
        }

        public Criteria andAllowDeleteIsNull() {
            addCriterion("ALLOW_DELETE is null");
            return this;
        }

        public Criteria andAllowDeleteIsNotNull() {
            addCriterion("ALLOW_DELETE is not null");
            return this;
        }

        public Criteria andAllowDeleteEqualTo(Short value) {
            addCriterion("ALLOW_DELETE =", value, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteNotEqualTo(Short value) {
            addCriterion("ALLOW_DELETE <>", value, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteGreaterThan(Short value) {
            addCriterion("ALLOW_DELETE >", value, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteGreaterThanOrEqualTo(Short value) {
            addCriterion("ALLOW_DELETE >=", value, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteLessThan(Short value) {
            addCriterion("ALLOW_DELETE <", value, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteLessThanOrEqualTo(Short value) {
            addCriterion("ALLOW_DELETE <=", value, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteIn(List values) {
            addCriterion("ALLOW_DELETE in", values, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteNotIn(List values) {
            addCriterion("ALLOW_DELETE not in", values, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteBetween(Short value1, Short value2) {
            addCriterion("ALLOW_DELETE between", value1, value2, "allowDelete");
            return this;
        }

        public Criteria andAllowDeleteNotBetween(Short value1, Short value2) {
            addCriterion("ALLOW_DELETE not between", value1, value2, "allowDelete");
            return this;
        }

        public Criteria andIsAdminUserIsNull() {
            addCriterion("IS_ADMIN_USER is null");
            return this;
        }

        public Criteria andIsAdminUserIsNotNull() {
            addCriterion("IS_ADMIN_USER is not null");
            return this;
        }

        public Criteria andIsAdminUserEqualTo(Short value) {
            addCriterion("IS_ADMIN_USER =", value, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserNotEqualTo(Short value) {
            addCriterion("IS_ADMIN_USER <>", value, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserGreaterThan(Short value) {
            addCriterion("IS_ADMIN_USER >", value, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_ADMIN_USER >=", value, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserLessThan(Short value) {
            addCriterion("IS_ADMIN_USER <", value, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserLessThanOrEqualTo(Short value) {
            addCriterion("IS_ADMIN_USER <=", value, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserIn(List values) {
            addCriterion("IS_ADMIN_USER in", values, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserNotIn(List values) {
            addCriterion("IS_ADMIN_USER not in", values, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserBetween(Short value1, Short value2) {
            addCriterion("IS_ADMIN_USER between", value1, value2, "isAdminUser");
            return this;
        }

        public Criteria andIsAdminUserNotBetween(Short value1, Short value2) {
            addCriterion("IS_ADMIN_USER not between", value1, value2, "isAdminUser");
            return this;
        }
        
        public Criteria andWebidIsNull() {
            addCriterion("WEBID is null");
            return this;


        }

        public Criteria andWebidIsNotNull() {
            addCriterion("WEBID is not null");
            return this;


        }

        public Criteria andWebidEqualTo(Short value) {
            addCriterion("WEBID =", value, "webid");
            return this;


        }

        public Criteria andWebidNotEqualTo(Short value) {
            addCriterion("WEBID <>", value, "webid");
            return this;


        }

        public Criteria andWebidGreaterThan(Short value) {
            addCriterion("WEBID >", value, "webid");
            return this;


        }

        public Criteria andWebidGreaterThanOrEqualTo(Short value) {
            addCriterion("WEBID >=", value, "webid");
            return this;


        }

        public Criteria andWebidLessThan(Short value) {
            addCriterion("WEBID <", value, "webid");
            return this;


        }

        public Criteria andWebidLessThanOrEqualTo(Short value) {
            addCriterion("WEBID <=", value, "webid");
            return this;


        }

        public Criteria andWebidIn(List values) {
            addCriterion("WEBID in", values, "webid");
            return this;


        }

        public Criteria andWebidNotIn(List values) {
            addCriterion("WEBID not in", values, "webid");
            return this;


        }

        public Criteria andWebidBetween(Short value1, Short value2) {
            addCriterion("WEBID between", value1, value2, "webid");
            return this;


        }

        public Criteria andWebidNotBetween(Short value1, Short value2) {
            addCriterion("WEBID not between", value1, value2, "webid");
            return this;


        }
    }
}
