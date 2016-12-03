package com.rc.portal.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TAdmRolesExample extends BaseModel{
    protected String orderByClause;

    protected List oredCriteria;

    public TAdmRolesExample() {
        oredCriteria = new ArrayList();
    }

    protected TAdmRolesExample(TAdmRolesExample example) {
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

        public Criteria andRoleNameIsNull() {
            addCriterion("ROLE_NAME is null");
            return this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("ROLE_NAME is not null");
            return this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("ROLE_NAME =", value, "roleName");
            return this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("ROLE_NAME <>", value, "roleName");
            return this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("ROLE_NAME >", value, "roleName");
            return this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_NAME >=", value, "roleName");
            return this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("ROLE_NAME <", value, "roleName");
            return this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("ROLE_NAME <=", value, "roleName");
            return this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("ROLE_NAME like", value, "roleName");
            return this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("ROLE_NAME not like", value, "roleName");
            return this;
        }

        public Criteria andRoleNameIn(List values) {
            addCriterion("ROLE_NAME in", values, "roleName");
            return this;
        }

        public Criteria andRoleNameNotIn(List values) {
            addCriterion("ROLE_NAME not in", values, "roleName");
            return this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("ROLE_NAME between", value1, value2, "roleName");
            return this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("ROLE_NAME not between", value1, value2, "roleName");
            return this;
        }

        public Criteria andRoleDescIsNull() {
            addCriterion("ROLE_DESC is null");
            return this;
        }

        public Criteria andRoleDescIsNotNull() {
            addCriterion("ROLE_DESC is not null");
            return this;
        }

        public Criteria andRoleDescEqualTo(String value) {
            addCriterion("ROLE_DESC =", value, "roleDesc");
            return this;
        }

        public Criteria andRoleDescNotEqualTo(String value) {
            addCriterion("ROLE_DESC <>", value, "roleDesc");
            return this;
        }

        public Criteria andRoleDescGreaterThan(String value) {
            addCriterion("ROLE_DESC >", value, "roleDesc");
            return this;
        }

        public Criteria andRoleDescGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_DESC >=", value, "roleDesc");
            return this;
        }

        public Criteria andRoleDescLessThan(String value) {
            addCriterion("ROLE_DESC <", value, "roleDesc");
            return this;
        }

        public Criteria andRoleDescLessThanOrEqualTo(String value) {
            addCriterion("ROLE_DESC <=", value, "roleDesc");
            return this;
        }

        public Criteria andRoleDescLike(String value) {
            addCriterion("ROLE_DESC like", value, "roleDesc");
            return this;
        }

        public Criteria andRoleDescNotLike(String value) {
            addCriterion("ROLE_DESC not like", value, "roleDesc");
            return this;
        }

        public Criteria andRoleDescIn(List values) {
            addCriterion("ROLE_DESC in", values, "roleDesc");
            return this;
        }

        public Criteria andRoleDescNotIn(List values) {
            addCriterion("ROLE_DESC not in", values, "roleDesc");
            return this;
        }

        public Criteria andRoleDescBetween(String value1, String value2) {
            addCriterion("ROLE_DESC between", value1, value2, "roleDesc");
            return this;
        }

        public Criteria andRoleDescNotBetween(String value1, String value2) {
            addCriterion("ROLE_DESC not between", value1, value2, "roleDesc");
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

        public Criteria andLastUpdateByIsNull() {
            addCriterion("LAST_UPDATE_BY is null");
            return this;
        }

        public Criteria andLastUpdateByIsNotNull() {
            addCriterion("LAST_UPDATE_BY is not null");
            return this;
        }

        public Criteria andLastUpdateByEqualTo(BigDecimal value) {
            addCriterion("LAST_UPDATE_BY =", value, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByNotEqualTo(BigDecimal value) {
            addCriterion("LAST_UPDATE_BY <>", value, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByGreaterThan(BigDecimal value) {
            addCriterion("LAST_UPDATE_BY >", value, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LAST_UPDATE_BY >=", value, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByLessThan(BigDecimal value) {
            addCriterion("LAST_UPDATE_BY <", value, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LAST_UPDATE_BY <=", value, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByIn(List values) {
            addCriterion("LAST_UPDATE_BY in", values, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByNotIn(List values) {
            addCriterion("LAST_UPDATE_BY not in", values, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAST_UPDATE_BY between", value1, value2, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateByNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAST_UPDATE_BY not between", value1, value2, "lastUpdateBy");
            return this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("LAST_UPDATE_TIME is null");
            return this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("LAST_UPDATE_TIME is not null");
            return this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME =", value, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME <>", value, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME >", value, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME >=", value, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME <", value, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME <=", value, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeIn(List values) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME in", values, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeNotIn(List values) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME not in", values, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME between", value1, value2, "lastUpdateTime");
            return this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_UPDATE_TIME not between", value1, value2, "lastUpdateTime");
            return this;
        }
        
        public Criteria andEditableIsNull() {
            addCriterion("EDITABLE is null");
            return this;
        }

        public Criteria andEditableIsNotNull() {
            addCriterion("EDITABLE is not null");
            return this;
        }

        public Criteria andEditableEqualTo(Integer value) {
            addCriterion("EDITABLE =", value, "editable");
            return this;
        }

        public Criteria andEditableNotEqualTo(Integer value) {
            addCriterion("EDITABLE <>", value, "editable");
            return this;
        }

        public Criteria andEditableGreaterThan(Integer value) {
            addCriterion("EDITABLE >", value, "editable");
            return this;
        }

        public Criteria andEditableGreaterThanOrEqualTo(Integer value) {
            addCriterion("EDITABLE >=", value, "editable");
            return this;
        }

        public Criteria andEditableLessThan(Integer value) {
            addCriterion("EDITABLE <", value, "editable");
            return this;
        }

        public Criteria andEditableLessThanOrEqualTo(Integer value) {
            addCriterion("EDITABLE <=", value, "editable");
            return this;
        }

        public Criteria andEditableIn(List values) {
            addCriterion("EDITABLE in", values, "editable");
            return this;
        }

        public Criteria andEditableNotIn(List values) {
            addCriterion("EDITABLE not in", values, "editable");
            return this;
        }

        public Criteria andEditableBetween(Integer value1, Integer value2) {
            addCriterion("EDITABLE between", value1, value2, "editable");
            return this;
        }

        public Criteria andEditableNotBetween(Integer value1, Integer value2) {
            addCriterion("EDITABLE not between", value1, value2, "editable");
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
