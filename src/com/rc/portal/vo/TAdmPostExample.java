package com.rc.portal.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TAdmPostExample extends BaseModel{
    protected String orderByClause;

    protected List oredCriteria;

    public TAdmPostExample() {
        oredCriteria = new ArrayList();
    }

    protected TAdmPostExample(TAdmPostExample example) {
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

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return this;
        }

        public Criteria andRemarkIn(List values) {
            addCriterion("REMARK in", values, "remark");
            return this;
        }

        public Criteria andRemarkNotIn(List values) {
            addCriterion("REMARK not in", values, "remark");
            return this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return this;
        }

        public Criteria andCreateDtIsNull() {
            addCriterion("CREATE_DT is null");
            return this;
        }

        public Criteria andCreateDtIsNotNull() {
            addCriterion("CREATE_DT is not null");
            return this;
        }

        public Criteria andCreateDtEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DT =", value, "createDt");
            return this;
        }

        public Criteria andCreateDtNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DT <>", value, "createDt");
            return this;
        }

        public Criteria andCreateDtGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_DT >", value, "createDt");
            return this;
        }

        public Criteria andCreateDtGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DT >=", value, "createDt");
            return this;
        }

        public Criteria andCreateDtLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_DT <", value, "createDt");
            return this;
        }

        public Criteria andCreateDtLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DT <=", value, "createDt");
            return this;
        }

        public Criteria andCreateDtIn(List values) {
            addCriterionForJDBCDate("CREATE_DT in", values, "createDt");
            return this;
        }

        public Criteria andCreateDtNotIn(List values) {
            addCriterionForJDBCDate("CREATE_DT not in", values, "createDt");
            return this;
        }

        public Criteria andCreateDtBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_DT between", value1, value2, "createDt");
            return this;
        }

        public Criteria andCreateDtNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_DT not between", value1, value2, "createDt");
            return this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("ENABLE is null");
            return this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("ENABLE is not null");
            return this;
        }

        public Criteria andEnableEqualTo(Short value) {
            addCriterion("ENABLE =", value, "enable");
            return this;
        }

        public Criteria andEnableNotEqualTo(Short value) {
            addCriterion("ENABLE <>", value, "enable");
            return this;
        }

        public Criteria andEnableGreaterThan(Short value) {
            addCriterion("ENABLE >", value, "enable");
            return this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Short value) {
            addCriterion("ENABLE >=", value, "enable");
            return this;
        }

        public Criteria andEnableLessThan(Short value) {
            addCriterion("ENABLE <", value, "enable");
            return this;
        }

        public Criteria andEnableLessThanOrEqualTo(Short value) {
            addCriterion("ENABLE <=", value, "enable");
            return this;
        }

        public Criteria andEnableIn(List values) {
            addCriterion("ENABLE in", values, "enable");
            return this;
        }

        public Criteria andEnableNotIn(List values) {
            addCriterion("ENABLE not in", values, "enable");
            return this;
        }

        public Criteria andEnableBetween(Short value1, Short value2) {
            addCriterion("ENABLE between", value1, value2, "enable");
            return this;
        }

        public Criteria andEnableNotBetween(Short value1, Short value2) {
            addCriterion("ENABLE not between", value1, value2, "enable");
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
