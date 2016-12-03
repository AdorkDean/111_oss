package com.rc.portal.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TAdmRescRolesExample {
    protected String orderByClause;

    protected List oredCriteria;

    public TAdmRescRolesExample() {
        oredCriteria = new ArrayList();
    }

    protected TAdmRescRolesExample(TAdmRescRolesExample example) {
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

        public Criteria andRescIdIsNull() {
            addCriterion("RESC_ID is null");
            return this;
        }

        public Criteria andRescIdIsNotNull() {
            addCriterion("RESC_ID is not null");
            return this;
        }

        public Criteria andRescIdEqualTo(Long value) {
            addCriterion("RESC_ID =", value, "rescId");
            return this;
        }

        public Criteria andRescIdNotEqualTo(Long value) {
            addCriterion("RESC_ID <>", value, "rescId");
            return this;
        }

        public Criteria andRescIdGreaterThan(Long value) {
            addCriterion("RESC_ID >", value, "rescId");
            return this;
        }

        public Criteria andRescIdGreaterThanOrEqualTo(Long value) {
            addCriterion("RESC_ID >=", value, "rescId");
            return this;
        }

        public Criteria andRescIdLessThan(Long value) {
            addCriterion("RESC_ID <", value, "rescId");
            return this;
        }

        public Criteria andRescIdLessThanOrEqualTo(Long value) {
            addCriterion("RESC_ID <=", value, "rescId");
            return this;
        }

        public Criteria andRescIdIn(List values) {
            addCriterion("RESC_ID in", values, "rescId");
            return this;
        }

        public Criteria andRescIdNotIn(List values) {
            addCriterion("RESC_ID not in", values, "rescId");
            return this;
        }

        public Criteria andRescIdBetween(Long value1, Long value2) {
            addCriterion("RESC_ID between", value1, value2, "rescId");
            return this;
        }

        public Criteria andRescIdNotBetween(Long value1, Long value2) {
            addCriterion("RESC_ID not between", value1, value2, "rescId");
            return this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("ROLE_ID is null");
            return this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("ROLE_ID is not null");
            return this;
        }

        public Criteria andRoleIdEqualTo(Long value) {
            addCriterion("ROLE_ID =", value, "roleId");
            return this;
        }

        public Criteria andRoleIdNotEqualTo(Long value) {
            addCriterion("ROLE_ID <>", value, "roleId");
            return this;
        }

        public Criteria andRoleIdGreaterThan(Long value) {
            addCriterion("ROLE_ID >", value, "roleId");
            return this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ROLE_ID >=", value, "roleId");
            return this;
        }

        public Criteria andRoleIdLessThan(Long value) {
            addCriterion("ROLE_ID <", value, "roleId");
            return this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Long value) {
            addCriterion("ROLE_ID <=", value, "roleId");
            return this;
        }

        public Criteria andRoleIdIn(List values) {
            addCriterion("ROLE_ID in", values, "roleId");
            return this;
        }

        public Criteria andRoleIdNotIn(List values) {
            addCriterion("ROLE_ID not in", values, "roleId");
            return this;
        }

        public Criteria andRoleIdBetween(Long value1, Long value2) {
            addCriterion("ROLE_ID between", value1, value2, "roleId");
            return this;
        }

        public Criteria andRoleIdNotBetween(Long value1, Long value2) {
            addCriterion("ROLE_ID not between", value1, value2, "roleId");
            return this;
        }
    }
}
