package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TCutWordExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TCutWordExample() {
        oredCriteria = new ArrayList();


    }

    protected TCutWordExample(TCutWordExample example) {
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
            addCriterion("id is null");
            return this;


        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return this;


        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return this;


        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return this;


        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return this;


        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return this;


        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return this;


        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return this;


        }

        public Criteria andIdIn(List values) {
            addCriterion("id in", values, "id");
            return this;


        }

        public Criteria andIdNotIn(List values) {
            addCriterion("id not in", values, "id");
            return this;


        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return this;


        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return this;


        }

        public Criteria andCutwordIsNull() {
            addCriterion("cutword is null");
            return this;


        }

        public Criteria andCutwordIsNotNull() {
            addCriterion("cutword is not null");
            return this;


        }

        public Criteria andCutwordEqualTo(String value) {
            addCriterion("cutword =", value, "cutword");
            return this;


        }

        public Criteria andCutwordNotEqualTo(String value) {
            addCriterion("cutword <>", value, "cutword");
            return this;


        }

        public Criteria andCutwordGreaterThan(String value) {
            addCriterion("cutword >", value, "cutword");
            return this;


        }

        public Criteria andCutwordGreaterThanOrEqualTo(String value) {
            addCriterion("cutword >=", value, "cutword");
            return this;


        }

        public Criteria andCutwordLessThan(String value) {
            addCriterion("cutword <", value, "cutword");
            return this;


        }

        public Criteria andCutwordLessThanOrEqualTo(String value) {
            addCriterion("cutword <=", value, "cutword");
            return this;


        }

        public Criteria andCutwordLike(String value) {
            addCriterion("cutword like", value, "cutword");
            return this;


        }

        public Criteria andCutwordNotLike(String value) {
            addCriterion("cutword not like", value, "cutword");
            return this;


        }

        public Criteria andCutwordIn(List values) {
            addCriterion("cutword in", values, "cutword");
            return this;


        }

        public Criteria andCutwordNotIn(List values) {
            addCriterion("cutword not in", values, "cutword");
            return this;


        }

        public Criteria andCutwordBetween(String value1, String value2) {
            addCriterion("cutword between", value1, value2, "cutword");
            return this;


        }

        public Criteria andCutwordNotBetween(String value1, String value2) {
            addCriterion("cutword not between", value1, value2, "cutword");
            return this;


        }


    }


}
