package com.rc.portal.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TAdmPostRoleExample {
    protected String orderByClause;

    protected List oredCriteria;

    public TAdmPostRoleExample() {
        oredCriteria = new ArrayList();
    }

    protected TAdmPostRoleExample(TAdmPostRoleExample example) {
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

        public Criteria andPostidIsNull() {
            addCriterion("POSTID is null");
            return this;
        }

        public Criteria andPostidIsNotNull() {
            addCriterion("POSTID is not null");
            return this;
        }

        public Criteria andPostidEqualTo(Long value) {
            addCriterion("POSTID =", value, "postid");
            return this;
        }

        public Criteria andPostidNotEqualTo(Long value) {
            addCriterion("POSTID <>", value, "postid");
            return this;
        }

        public Criteria andPostidGreaterThan(Long value) {
            addCriterion("POSTID >", value, "postid");
            return this;
        }

        public Criteria andPostidGreaterThanOrEqualTo(Long value) {
            addCriterion("POSTID >=", value, "postid");
            return this;
        }

        public Criteria andPostidLessThan(Long value) {
            addCriterion("POSTID <", value, "postid");
            return this;
        }

        public Criteria andPostidLessThanOrEqualTo(Long value) {
            addCriterion("POSTID <=", value, "postid");
            return this;
        }

        public Criteria andPostidIn(List values) {
            addCriterion("POSTID in", values, "postid");
            return this;
        }

        public Criteria andPostidNotIn(List values) {
            addCriterion("POSTID not in", values, "postid");
            return this;
        }

        public Criteria andPostidBetween(Long value1, Long value2) {
            addCriterion("POSTID between", value1, value2, "postid");
            return this;
        }

        public Criteria andPostidNotBetween(Long value1, Long value2) {
            addCriterion("POSTID not between", value1, value2, "postid");
            return this;
        }

        public Criteria andRoleidIsNull() {
            addCriterion("ROLEID is null");
            return this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("ROLEID is not null");
            return this;
        }

        public Criteria andRoleidEqualTo(Long value) {
            addCriterion("ROLEID =", value, "roleid");
            return this;
        }

        public Criteria andRoleidNotEqualTo(Long value) {
            addCriterion("ROLEID <>", value, "roleid");
            return this;
        }

        public Criteria andRoleidGreaterThan(Long value) {
            addCriterion("ROLEID >", value, "roleid");
            return this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(Long value) {
            addCriterion("ROLEID >=", value, "roleid");
            return this;
        }

        public Criteria andRoleidLessThan(Long value) {
            addCriterion("ROLEID <", value, "roleid");
            return this;
        }

        public Criteria andRoleidLessThanOrEqualTo(Long value) {
            addCriterion("ROLEID <=", value, "roleid");
            return this;
        }

        public Criteria andRoleidIn(List values) {
            addCriterion("ROLEID in", values, "roleid");
            return this;
        }

        public Criteria andRoleidNotIn(List values) {
            addCriterion("ROLEID not in", values, "roleid");
            return this;
        }

        public Criteria andRoleidBetween(Long value1, Long value2) {
            addCriterion("ROLEID between", value1, value2, "roleid");
            return this;
        }

        public Criteria andRoleidNotBetween(Long value1, Long value2) {
            addCriterion("ROLEID not between", value1, value2, "roleid");
            return this;
        }
    }
}
