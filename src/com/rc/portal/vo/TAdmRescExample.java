package com.rc.portal.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TAdmRescExample extends BaseModel{
    protected String orderByClause;

    protected List oredCriteria;

    public TAdmRescExample() {
        oredCriteria = new ArrayList();
    }

    protected TAdmRescExample(TAdmRescExample example) {
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

        public Criteria andResTypeIsNull() {
            addCriterion("RES_TYPE is null");
            return this;
        }

        public Criteria andResTypeIsNotNull() {
            addCriterion("RES_TYPE is not null");
            return this;
        }

        public Criteria andResTypeEqualTo(String value) {
            addCriterion("RES_TYPE =", value, "resType");
            return this;
        }

        public Criteria andResTypeNotEqualTo(String value) {
            addCriterion("RES_TYPE <>", value, "resType");
            return this;
        }

        public Criteria andResTypeGreaterThan(String value) {
            addCriterion("RES_TYPE >", value, "resType");
            return this;
        }

        public Criteria andResTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RES_TYPE >=", value, "resType");
            return this;
        }

        public Criteria andResTypeLessThan(String value) {
            addCriterion("RES_TYPE <", value, "resType");
            return this;
        }

        public Criteria andResTypeLessThanOrEqualTo(String value) {
            addCriterion("RES_TYPE <=", value, "resType");
            return this;
        }

        public Criteria andResTypeLike(String value) {
            addCriterion("RES_TYPE like", value, "resType");
            return this;
        }

        public Criteria andResTypeNotLike(String value) {
            addCriterion("RES_TYPE not like", value, "resType");
            return this;
        }

        public Criteria andResTypeIn(List values) {
            addCriterion("RES_TYPE in", values, "resType");
            return this;
        }

        public Criteria andResTypeNotIn(List values) {
            addCriterion("RES_TYPE not in", values, "resType");
            return this;
        }

        public Criteria andResTypeBetween(String value1, String value2) {
            addCriterion("RES_TYPE between", value1, value2, "resType");
            return this;
        }

        public Criteria andResTypeNotBetween(String value1, String value2) {
            addCriterion("RES_TYPE not between", value1, value2, "resType");
            return this;
        }

        public Criteria andResStringIsNull() {
            addCriterion("RES_STRING is null");
            return this;
        }

        public Criteria andResStringIsNotNull() {
            addCriterion("RES_STRING is not null");
            return this;
        }

        public Criteria andResStringEqualTo(String value) {
            addCriterion("RES_STRING =", value, "resString");
            return this;
        }

        public Criteria andResStringNotEqualTo(String value) {
            addCriterion("RES_STRING <>", value, "resString");
            return this;
        }

        public Criteria andResStringGreaterThan(String value) {
            addCriterion("RES_STRING >", value, "resString");
            return this;
        }

        public Criteria andResStringGreaterThanOrEqualTo(String value) {
            addCriterion("RES_STRING >=", value, "resString");
            return this;
        }

        public Criteria andResStringLessThan(String value) {
            addCriterion("RES_STRING <", value, "resString");
            return this;
        }

        public Criteria andResStringLessThanOrEqualTo(String value) {
            addCriterion("RES_STRING <=", value, "resString");
            return this;
        }

        public Criteria andResStringLike(String value) {
            addCriterion("RES_STRING like", value, "resString");
            return this;
        }

        public Criteria andResStringNotLike(String value) {
            addCriterion("RES_STRING not like", value, "resString");
            return this;
        }

        public Criteria andResStringIn(List values) {
            addCriterion("RES_STRING in", values, "resString");
            return this;
        }

        public Criteria andResStringNotIn(List values) {
            addCriterion("RES_STRING not in", values, "resString");
            return this;
        }

        public Criteria andResStringBetween(String value1, String value2) {
            addCriterion("RES_STRING between", value1, value2, "resString");
            return this;
        }

        public Criteria andResStringNotBetween(String value1, String value2) {
            addCriterion("RES_STRING not between", value1, value2, "resString");
            return this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("PRIORITY is null");
            return this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("PRIORITY is not null");
            return this;
        }

        public Criteria andPriorityEqualTo(Long value) {
            addCriterion("PRIORITY =", value, "priority");
            return this;
        }

        public Criteria andPriorityNotEqualTo(Long value) {
            addCriterion("PRIORITY <>", value, "priority");
            return this;
        }

        public Criteria andPriorityGreaterThan(Long value) {
            addCriterion("PRIORITY >", value, "priority");
            return this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Long value) {
            addCriterion("PRIORITY >=", value, "priority");
            return this;
        }

        public Criteria andPriorityLessThan(Long value) {
            addCriterion("PRIORITY <", value, "priority");
            return this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Long value) {
            addCriterion("PRIORITY <=", value, "priority");
            return this;
        }

        public Criteria andPriorityIn(List values) {
            addCriterion("PRIORITY in", values, "priority");
            return this;
        }

        public Criteria andPriorityNotIn(List values) {
            addCriterion("PRIORITY not in", values, "priority");
            return this;
        }

        public Criteria andPriorityBetween(Long value1, Long value2) {
            addCriterion("PRIORITY between", value1, value2, "priority");
            return this;
        }

        public Criteria andPriorityNotBetween(Long value1, Long value2) {
            addCriterion("PRIORITY not between", value1, value2, "priority");
            return this;
        }

        public Criteria andDescnIsNull() {
            addCriterion("DESCN is null");
            return this;
        }

        public Criteria andDescnIsNotNull() {
            addCriterion("DESCN is not null");
            return this;
        }

        public Criteria andDescnEqualTo(String value) {
            addCriterion("DESCN =", value, "descn");
            return this;
        }

        public Criteria andDescnNotEqualTo(String value) {
            addCriterion("DESCN <>", value, "descn");
            return this;
        }

        public Criteria andDescnGreaterThan(String value) {
            addCriterion("DESCN >", value, "descn");
            return this;
        }

        public Criteria andDescnGreaterThanOrEqualTo(String value) {
            addCriterion("DESCN >=", value, "descn");
            return this;
        }

        public Criteria andDescnLessThan(String value) {
            addCriterion("DESCN <", value, "descn");
            return this;
        }

        public Criteria andDescnLessThanOrEqualTo(String value) {
            addCriterion("DESCN <=", value, "descn");
            return this;
        }

        public Criteria andDescnLike(String value) {
            addCriterion("DESCN like", value, "descn");
            return this;
        }

        public Criteria andDescnNotLike(String value) {
            addCriterion("DESCN not like", value, "descn");
            return this;
        }

        public Criteria andDescnIn(List values) {
            addCriterion("DESCN in", values, "descn");
            return this;
        }

        public Criteria andDescnNotIn(List values) {
            addCriterion("DESCN not in", values, "descn");
            return this;
        }

        public Criteria andDescnBetween(String value1, String value2) {
            addCriterion("DESCN between", value1, value2, "descn");
            return this;
        }

        public Criteria andDescnNotBetween(String value1, String value2) {
            addCriterion("DESCN not between", value1, value2, "descn");
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
