package com.rc.portal.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TAdmOperationLogsExample {
    protected String orderByClause;

    protected List oredCriteria;

    public TAdmOperationLogsExample() {
        oredCriteria = new ArrayList();
    }

    protected TAdmOperationLogsExample(TAdmOperationLogsExample example) {
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

        public Criteria andModuleIdIsNull() {
            addCriterion("MODULE_ID is null");
            return this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("MODULE_ID is not null");
            return this;
        }

        public Criteria andModuleIdEqualTo(Long value) {
            addCriterion("MODULE_ID =", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotEqualTo(Long value) {
            addCriterion("MODULE_ID <>", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdGreaterThan(Long value) {
            addCriterion("MODULE_ID >", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MODULE_ID >=", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdLessThan(Long value) {
            addCriterion("MODULE_ID <", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Long value) {
            addCriterion("MODULE_ID <=", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdIn(List values) {
            addCriterion("MODULE_ID in", values, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotIn(List values) {
            addCriterion("MODULE_ID not in", values, "moduleId");
            return this;
        }

        public Criteria andModuleIdBetween(Long value1, Long value2) {
            addCriterion("MODULE_ID between", value1, value2, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotBetween(Long value1, Long value2) {
            addCriterion("MODULE_ID not between", value1, value2, "moduleId");
            return this;
        }

        public Criteria andOptTypeIsNull() {
            addCriterion("OPT_TYPE is null");
            return this;
        }

        public Criteria andOptTypeIsNotNull() {
            addCriterion("OPT_TYPE is not null");
            return this;
        }

        public Criteria andOptTypeEqualTo(Short value) {
            addCriterion("OPT_TYPE =", value, "optType");
            return this;
        }

        public Criteria andOptTypeNotEqualTo(Short value) {
            addCriterion("OPT_TYPE <>", value, "optType");
            return this;
        }

        public Criteria andOptTypeGreaterThan(Short value) {
            addCriterion("OPT_TYPE >", value, "optType");
            return this;
        }

        public Criteria andOptTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("OPT_TYPE >=", value, "optType");
            return this;
        }

        public Criteria andOptTypeLessThan(Short value) {
            addCriterion("OPT_TYPE <", value, "optType");
            return this;
        }

        public Criteria andOptTypeLessThanOrEqualTo(Short value) {
            addCriterion("OPT_TYPE <=", value, "optType");
            return this;
        }

        public Criteria andOptTypeIn(List values) {
            addCriterion("OPT_TYPE in", values, "optType");
            return this;
        }

        public Criteria andOptTypeNotIn(List values) {
            addCriterion("OPT_TYPE not in", values, "optType");
            return this;
        }

        public Criteria andOptTypeBetween(Short value1, Short value2) {
            addCriterion("OPT_TYPE between", value1, value2, "optType");
            return this;
        }

        public Criteria andOptTypeNotBetween(Short value1, Short value2) {
            addCriterion("OPT_TYPE not between", value1, value2, "optType");
            return this;
        }

        public Criteria andOptIdIsNull() {
            addCriterion("OPT_ID is null");
            return this;
        }

        public Criteria andOptIdIsNotNull() {
            addCriterion("OPT_ID is not null");
            return this;
        }

        public Criteria andOptIdEqualTo(BigDecimal value) {
            addCriterion("OPT_ID =", value, "optId");
            return this;
        }

        public Criteria andOptIdNotEqualTo(BigDecimal value) {
            addCriterion("OPT_ID <>", value, "optId");
            return this;
        }

        public Criteria andOptIdGreaterThan(BigDecimal value) {
            addCriterion("OPT_ID >", value, "optId");
            return this;
        }

        public Criteria andOptIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OPT_ID >=", value, "optId");
            return this;
        }

        public Criteria andOptIdLessThan(BigDecimal value) {
            addCriterion("OPT_ID <", value, "optId");
            return this;
        }

        public Criteria andOptIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OPT_ID <=", value, "optId");
            return this;
        }

        public Criteria andOptIdIn(List values) {
            addCriterion("OPT_ID in", values, "optId");
            return this;
        }

        public Criteria andOptIdNotIn(List values) {
            addCriterion("OPT_ID not in", values, "optId");
            return this;
        }

        public Criteria andOptIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OPT_ID between", value1, value2, "optId");
            return this;
        }

        public Criteria andOptIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OPT_ID not between", value1, value2, "optId");
            return this;
        }

        public Criteria andOptDateIsNull() {
            addCriterion("OPT_DATE is null");
            return this;
        }

        public Criteria andOptDateIsNotNull() {
            addCriterion("OPT_DATE is not null");
            return this;
        }

        public Criteria andOptDateEqualTo(Date value) {
            addCriterionForJDBCDate("OPT_DATE =", value, "optDate");
            return this;
        }

        public Criteria andOptDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("OPT_DATE <>", value, "optDate");
            return this;
        }

        public Criteria andOptDateGreaterThan(Date value) {
            addCriterionForJDBCDate("OPT_DATE >", value, "optDate");
            return this;
        }

        public Criteria andOptDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OPT_DATE >=", value, "optDate");
            return this;
        }

        public Criteria andOptDateLessThan(Date value) {
            addCriterionForJDBCDate("OPT_DATE <", value, "optDate");
            return this;
        }

        public Criteria andOptDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("OPT_DATE <=", value, "optDate");
            return this;
        }

        public Criteria andOptDateIn(List values) {
            addCriterionForJDBCDate("OPT_DATE in", values, "optDate");
            return this;
        }

        public Criteria andOptDateNotIn(List values) {
            addCriterionForJDBCDate("OPT_DATE not in", values, "optDate");
            return this;
        }

        public Criteria andOptDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OPT_DATE between", value1, value2, "optDate");
            return this;
        }

        public Criteria andOptDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("OPT_DATE not between", value1, value2, "optDate");
            return this;
        }

        public Criteria andObjIdIsNull() {
            addCriterion("OBJ_ID is null");
            return this;
        }

        public Criteria andObjIdIsNotNull() {
            addCriterion("OBJ_ID is not null");
            return this;
        }

        public Criteria andObjIdEqualTo(BigDecimal value) {
            addCriterion("OBJ_ID =", value, "objId");
            return this;
        }

        public Criteria andObjIdNotEqualTo(BigDecimal value) {
            addCriterion("OBJ_ID <>", value, "objId");
            return this;
        }

        public Criteria andObjIdGreaterThan(BigDecimal value) {
            addCriterion("OBJ_ID >", value, "objId");
            return this;
        }

        public Criteria andObjIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OBJ_ID >=", value, "objId");
            return this;
        }

        public Criteria andObjIdLessThan(BigDecimal value) {
            addCriterion("OBJ_ID <", value, "objId");
            return this;
        }

        public Criteria andObjIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OBJ_ID <=", value, "objId");
            return this;
        }

        public Criteria andObjIdIn(List values) {
            addCriterion("OBJ_ID in", values, "objId");
            return this;
        }

        public Criteria andObjIdNotIn(List values) {
            addCriterion("OBJ_ID not in", values, "objId");
            return this;
        }

        public Criteria andObjIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OBJ_ID between", value1, value2, "objId");
            return this;
        }

        public Criteria andObjIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OBJ_ID not between", value1, value2, "objId");
            return this;
        }

        public Criteria andObjTypeIsNull() {
            addCriterion("OBJ_TYPE is null");
            return this;
        }

        public Criteria andObjTypeIsNotNull() {
            addCriterion("OBJ_TYPE is not null");
            return this;
        }

        public Criteria andObjTypeEqualTo(Short value) {
            addCriterion("OBJ_TYPE =", value, "objType");
            return this;
        }

        public Criteria andObjTypeNotEqualTo(Short value) {
            addCriterion("OBJ_TYPE <>", value, "objType");
            return this;
        }

        public Criteria andObjTypeGreaterThan(Short value) {
            addCriterion("OBJ_TYPE >", value, "objType");
            return this;
        }

        public Criteria andObjTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("OBJ_TYPE >=", value, "objType");
            return this;
        }

        public Criteria andObjTypeLessThan(Short value) {
            addCriterion("OBJ_TYPE <", value, "objType");
            return this;
        }

        public Criteria andObjTypeLessThanOrEqualTo(Short value) {
            addCriterion("OBJ_TYPE <=", value, "objType");
            return this;
        }

        public Criteria andObjTypeIn(List values) {
            addCriterion("OBJ_TYPE in", values, "objType");
            return this;
        }

        public Criteria andObjTypeNotIn(List values) {
            addCriterion("OBJ_TYPE not in", values, "objType");
            return this;
        }

        public Criteria andObjTypeBetween(Short value1, Short value2) {
            addCriterion("OBJ_TYPE between", value1, value2, "objType");
            return this;
        }

        public Criteria andObjTypeNotBetween(Short value1, Short value2) {
            addCriterion("OBJ_TYPE not between", value1, value2, "objType");
            return this;
        }

        public Criteria andOperationIsNull() {
            addCriterion("OPERATION is null");
            return this;
        }

        public Criteria andOperationIsNotNull() {
            addCriterion("OPERATION is not null");
            return this;
        }

        public Criteria andOperationEqualTo(String value) {
            addCriterion("OPERATION =", value, "operation");
            return this;
        }

        public Criteria andOperationNotEqualTo(String value) {
            addCriterion("OPERATION <>", value, "operation");
            return this;
        }

        public Criteria andOperationGreaterThan(String value) {
            addCriterion("OPERATION >", value, "operation");
            return this;
        }

        public Criteria andOperationGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATION >=", value, "operation");
            return this;
        }

        public Criteria andOperationLessThan(String value) {
            addCriterion("OPERATION <", value, "operation");
            return this;
        }

        public Criteria andOperationLessThanOrEqualTo(String value) {
            addCriterion("OPERATION <=", value, "operation");
            return this;
        }

        public Criteria andOperationLike(String value) {
            addCriterion("OPERATION like", value, "operation");
            return this;
        }

        public Criteria andOperationNotLike(String value) {
            addCriterion("OPERATION not like", value, "operation");
            return this;
        }

        public Criteria andOperationIn(List values) {
            addCriterion("OPERATION in", values, "operation");
            return this;
        }

        public Criteria andOperationNotIn(List values) {
            addCriterion("OPERATION not in", values, "operation");
            return this;
        }

        public Criteria andOperationBetween(String value1, String value2) {
            addCriterion("OPERATION between", value1, value2, "operation");
            return this;
        }

        public Criteria andOperationNotBetween(String value1, String value2) {
            addCriterion("OPERATION not between", value1, value2, "operation");
            return this;
        }

        public Criteria andIpAddressIsNull() {
            addCriterion("IP_ADDRESS is null");
            return this;
        }

        public Criteria andIpAddressIsNotNull() {
            addCriterion("IP_ADDRESS is not null");
            return this;
        }

        public Criteria andIpAddressEqualTo(String value) {
            addCriterion("IP_ADDRESS =", value, "ipAddress");
            return this;
        }

        public Criteria andIpAddressNotEqualTo(String value) {
            addCriterion("IP_ADDRESS <>", value, "ipAddress");
            return this;
        }

        public Criteria andIpAddressGreaterThan(String value) {
            addCriterion("IP_ADDRESS >", value, "ipAddress");
            return this;
        }

        public Criteria andIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("IP_ADDRESS >=", value, "ipAddress");
            return this;
        }

        public Criteria andIpAddressLessThan(String value) {
            addCriterion("IP_ADDRESS <", value, "ipAddress");
            return this;
        }

        public Criteria andIpAddressLessThanOrEqualTo(String value) {
            addCriterion("IP_ADDRESS <=", value, "ipAddress");
            return this;
        }

        public Criteria andIpAddressLike(String value) {
            addCriterion("IP_ADDRESS like", value, "ipAddress");
            return this;
        }

        public Criteria andIpAddressNotLike(String value) {
            addCriterion("IP_ADDRESS not like", value, "ipAddress");
            return this;
        }

        public Criteria andIpAddressIn(List values) {
            addCriterion("IP_ADDRESS in", values, "ipAddress");
            return this;
        }

        public Criteria andIpAddressNotIn(List values) {
            addCriterion("IP_ADDRESS not in", values, "ipAddress");
            return this;
        }

        public Criteria andIpAddressBetween(String value1, String value2) {
            addCriterion("IP_ADDRESS between", value1, value2, "ipAddress");
            return this;
        }

        public Criteria andIpAddressNotBetween(String value1, String value2) {
            addCriterion("IP_ADDRESS not between", value1, value2, "ipAddress");
            return this;
        }
    }
}
