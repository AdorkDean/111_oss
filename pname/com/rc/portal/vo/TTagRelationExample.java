package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TTagRelationExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TTagRelationExample() {
        oredCriteria = new ArrayList();


    }

    protected TTagRelationExample(TTagRelationExample example) {
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

        public Criteria andTagIdIsNull() {
            addCriterion("tag_id is null");
            return this;


        }

        public Criteria andTagIdIsNotNull() {
            addCriterion("tag_id is not null");
            return this;


        }

        public Criteria andTagIdEqualTo(Long value) {
            addCriterion("tag_id =", value, "tagId");
            return this;


        }

        public Criteria andTagIdNotEqualTo(Long value) {
            addCriterion("tag_id <>", value, "tagId");
            return this;


        }

        public Criteria andTagIdGreaterThan(Long value) {
            addCriterion("tag_id >", value, "tagId");
            return this;


        }

        public Criteria andTagIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tag_id >=", value, "tagId");
            return this;


        }

        public Criteria andTagIdLessThan(Long value) {
            addCriterion("tag_id <", value, "tagId");
            return this;


        }

        public Criteria andTagIdLessThanOrEqualTo(Long value) {
            addCriterion("tag_id <=", value, "tagId");
            return this;


        }

        public Criteria andTagIdIn(List values) {
            addCriterion("tag_id in", values, "tagId");
            return this;


        }

        public Criteria andTagIdNotIn(List values) {
            addCriterion("tag_id not in", values, "tagId");
            return this;


        }

        public Criteria andTagIdBetween(Long value1, Long value2) {
            addCriterion("tag_id between", value1, value2, "tagId");
            return this;


        }

        public Criteria andTagIdNotBetween(Long value1, Long value2) {
            addCriterion("tag_id not between", value1, value2, "tagId");
            return this;


        }

        public Criteria andRelationIdIsNull() {
            addCriterion("relation_id is null");
            return this;


        }

        public Criteria andRelationIdIsNotNull() {
            addCriterion("relation_id is not null");
            return this;


        }

        public Criteria andRelationIdEqualTo(Long value) {
            addCriterion("relation_id =", value, "relationId");
            return this;


        }

        public Criteria andRelationIdNotEqualTo(Long value) {
            addCriterion("relation_id <>", value, "relationId");
            return this;


        }

        public Criteria andRelationIdGreaterThan(Long value) {
            addCriterion("relation_id >", value, "relationId");
            return this;


        }

        public Criteria andRelationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("relation_id >=", value, "relationId");
            return this;


        }

        public Criteria andRelationIdLessThan(Long value) {
            addCriterion("relation_id <", value, "relationId");
            return this;


        }

        public Criteria andRelationIdLessThanOrEqualTo(Long value) {
            addCriterion("relation_id <=", value, "relationId");
            return this;


        }

        public Criteria andRelationIdIn(List values) {
            addCriterion("relation_id in", values, "relationId");
            return this;


        }

        public Criteria andRelationIdNotIn(List values) {
            addCriterion("relation_id not in", values, "relationId");
            return this;


        }

        public Criteria andRelationIdBetween(Long value1, Long value2) {
            addCriterion("relation_id between", value1, value2, "relationId");
            return this;


        }

        public Criteria andRelationIdNotBetween(Long value1, Long value2) {
            addCriterion("relation_id not between", value1, value2, "relationId");
            return this;


        }

        public Criteria andCreateDtIsNull() {
            addCriterion("create_dt is null");
            return this;


        }

        public Criteria andCreateDtIsNotNull() {
            addCriterion("create_dt is not null");
            return this;


        }

        public Criteria andCreateDtEqualTo(Date value) {
            addCriterion("create_dt =", value, "createDt");
            return this;


        }

        public Criteria andCreateDtNotEqualTo(Date value) {
            addCriterion("create_dt <>", value, "createDt");
            return this;


        }

        public Criteria andCreateDtGreaterThan(Date value) {
            addCriterion("create_dt >", value, "createDt");
            return this;


        }

        public Criteria andCreateDtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_dt >=", value, "createDt");
            return this;


        }

        public Criteria andCreateDtLessThan(Date value) {
            addCriterion("create_dt <", value, "createDt");
            return this;


        }

        public Criteria andCreateDtLessThanOrEqualTo(Date value) {
            addCriterion("create_dt <=", value, "createDt");
            return this;


        }

        public Criteria andCreateDtIn(List values) {
            addCriterion("create_dt in", values, "createDt");
            return this;


        }

        public Criteria andCreateDtNotIn(List values) {
            addCriterion("create_dt not in", values, "createDt");
            return this;


        }

        public Criteria andCreateDtBetween(Date value1, Date value2) {
            addCriterion("create_dt between", value1, value2, "createDt");
            return this;


        }

        public Criteria andCreateDtNotBetween(Date value1, Date value2) {
            addCriterion("create_dt not between", value1, value2, "createDt");
            return this;


        }


    }


}
