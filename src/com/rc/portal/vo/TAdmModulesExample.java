package com.rc.portal.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TAdmModulesExample extends BaseModel{
    protected String orderByClause;

    protected List oredCriteria;

    public TAdmModulesExample() {
        oredCriteria = new ArrayList();
    }

    protected TAdmModulesExample(TAdmModulesExample example) {
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

        public Criteria andModuleNameIsNull() {
            addCriterion("MODULE_NAME is null");
            return this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("MODULE_NAME is not null");
            return this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("MODULE_NAME =", value, "moduleName");
            return this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("MODULE_NAME <>", value, "moduleName");
            return this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("MODULE_NAME >", value, "moduleName");
            return this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_NAME >=", value, "moduleName");
            return this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("MODULE_NAME <", value, "moduleName");
            return this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("MODULE_NAME <=", value, "moduleName");
            return this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("MODULE_NAME like", value, "moduleName");
            return this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("MODULE_NAME not like", value, "moduleName");
            return this;
        }

        public Criteria andModuleNameIn(List values) {
            addCriterion("MODULE_NAME in", values, "moduleName");
            return this;
        }

        public Criteria andModuleNameNotIn(List values) {
            addCriterion("MODULE_NAME not in", values, "moduleName");
            return this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("MODULE_NAME between", value1, value2, "moduleName");
            return this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("MODULE_NAME not between", value1, value2, "moduleName");
            return this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return this;
        }

        public Criteria andParentIdIn(List values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return this;
        }

        public Criteria andParentIdNotIn(List values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
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

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return this;
        }

        public Criteria andMemoIn(List values) {
            addCriterion("MEMO in", values, "memo");
            return this;
        }

        public Criteria andMemoNotIn(List values) {
            addCriterion("MEMO not in", values, "memo");
            return this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return this;
        }

        public Criteria andModuleTypeIsNull() {
            addCriterion("MODULE_TYPE is null");
            return this;
        }

        public Criteria andModuleTypeIsNotNull() {
            addCriterion("MODULE_TYPE is not null");
            return this;
        }

        public Criteria andModuleTypeEqualTo(Short value) {
            addCriterion("MODULE_TYPE =", value, "moduleType");
            return this;
        }

        public Criteria andModuleTypeNotEqualTo(Short value) {
            addCriterion("MODULE_TYPE <>", value, "moduleType");
            return this;
        }

        public Criteria andModuleTypeGreaterThan(Short value) {
            addCriterion("MODULE_TYPE >", value, "moduleType");
            return this;
        }

        public Criteria andModuleTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("MODULE_TYPE >=", value, "moduleType");
            return this;
        }

        public Criteria andModuleTypeLessThan(Short value) {
            addCriterion("MODULE_TYPE <", value, "moduleType");
            return this;
        }

        public Criteria andModuleTypeLessThanOrEqualTo(Short value) {
            addCriterion("MODULE_TYPE <=", value, "moduleType");
            return this;
        }

        public Criteria andModuleTypeIn(List values) {
            addCriterion("MODULE_TYPE in", values, "moduleType");
            return this;
        }

        public Criteria andModuleTypeNotIn(List values) {
            addCriterion("MODULE_TYPE not in", values, "moduleType");
            return this;
        }

        public Criteria andModuleTypeBetween(Short value1, Short value2) {
            addCriterion("MODULE_TYPE between", value1, value2, "moduleType");
            return this;
        }

        public Criteria andModuleTypeNotBetween(Short value1, Short value2) {
            addCriterion("MODULE_TYPE not between", value1, value2, "moduleType");
            return this;
        }

        public Criteria andForLogIsNull() {
            addCriterion("FOR_LOG is null");
            return this;
        }

        public Criteria andForLogIsNotNull() {
            addCriterion("FOR_LOG is not null");
            return this;
        }

        public Criteria andForLogEqualTo(Short value) {
            addCriterion("FOR_LOG =", value, "forLog");
            return this;
        }

        public Criteria andForLogNotEqualTo(Short value) {
            addCriterion("FOR_LOG <>", value, "forLog");
            return this;
        }

        public Criteria andForLogGreaterThan(Short value) {
            addCriterion("FOR_LOG >", value, "forLog");
            return this;
        }

        public Criteria andForLogGreaterThanOrEqualTo(Short value) {
            addCriterion("FOR_LOG >=", value, "forLog");
            return this;
        }

        public Criteria andForLogLessThan(Short value) {
            addCriterion("FOR_LOG <", value, "forLog");
            return this;
        }

        public Criteria andForLogLessThanOrEqualTo(Short value) {
            addCriterion("FOR_LOG <=", value, "forLog");
            return this;
        }

        public Criteria andForLogIn(List values) {
            addCriterion("FOR_LOG in", values, "forLog");
            return this;
        }

        public Criteria andForLogNotIn(List values) {
            addCriterion("FOR_LOG not in", values, "forLog");
            return this;
        }

        public Criteria andForLogBetween(Short value1, Short value2) {
            addCriterion("FOR_LOG between", value1, value2, "forLog");
            return this;
        }

        public Criteria andForLogNotBetween(Short value1, Short value2) {
            addCriterion("FOR_LOG not between", value1, value2, "forLog");
            return this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return this;
        }

        public Criteria andUrlIn(List values) {
            addCriterion("URL in", values, "url");
            return this;
        }

        public Criteria andUrlNotIn(List values) {
            addCriterion("URL not in", values, "url");
            return this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
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

        public Criteria andModuleIdEqualTo(String value) {
            addCriterion("MODULE_ID =", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotEqualTo(String value) {
            addCriterion("MODULE_ID <>", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdGreaterThan(String value) {
            addCriterion("MODULE_ID >", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_ID >=", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdLessThan(String value) {
            addCriterion("MODULE_ID <", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(String value) {
            addCriterion("MODULE_ID <=", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdLike(String value) {
            addCriterion("MODULE_ID like", value, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotLike(String value) {
            addCriterion("MODULE_ID not like", value, "moduleId");
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

        public Criteria andModuleIdBetween(String value1, String value2) {
            addCriterion("MODULE_ID between", value1, value2, "moduleId");
            return this;
        }

        public Criteria andModuleIdNotBetween(String value1, String value2) {
            addCriterion("MODULE_ID not between", value1, value2, "moduleId");
            return this;
        }

        public Criteria andDispnumIsNull() {
            addCriterion("DISPNUM is null");
            return this;
        }

        public Criteria andDispnumIsNotNull() {
            addCriterion("DISPNUM is not null");
            return this;
        }

        public Criteria andDispnumEqualTo(Integer value) {
            addCriterion("DISPNUM =", value, "dispnum");
            return this;
        }

        public Criteria andDispnumNotEqualTo(Integer value) {
            addCriterion("DISPNUM <>", value, "dispnum");
            return this;
        }

        public Criteria andDispnumGreaterThan(Integer value) {
            addCriterion("DISPNUM >", value, "dispnum");
            return this;
        }

        public Criteria andDispnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISPNUM >=", value, "dispnum");
            return this;
        }

        public Criteria andDispnumLessThan(Integer value) {
            addCriterion("DISPNUM <", value, "dispnum");
            return this;
        }

        public Criteria andDispnumLessThanOrEqualTo(Integer value) {
            addCriterion("DISPNUM <=", value, "dispnum");
            return this;
        }

        public Criteria andDispnumIn(List values) {
            addCriterion("DISPNUM in", values, "dispnum");
            return this;
        }

        public Criteria andDispnumNotIn(List values) {
            addCriterion("DISPNUM not in", values, "dispnum");
            return this;
        }

        public Criteria andDispnumBetween(Integer value1, Integer value2) {
            addCriterion("DISPNUM between", value1, value2, "dispnum");
            return this;
        }

        public Criteria andDispnumNotBetween(Integer value1, Integer value2) {
            addCriterion("DISPNUM not between", value1, value2, "dispnum");
            return this;
        }

        public Criteria andModuleKeyIsNull() {
            addCriterion("MODULE_KEY is null");
            return this;
        }

        public Criteria andModuleKeyIsNotNull() {
            addCriterion("MODULE_KEY is not null");
            return this;
        }

        public Criteria andModuleKeyEqualTo(String value) {
            addCriterion("MODULE_KEY =", value, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyNotEqualTo(String value) {
            addCriterion("MODULE_KEY <>", value, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyGreaterThan(String value) {
            addCriterion("MODULE_KEY >", value, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_KEY >=", value, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyLessThan(String value) {
            addCriterion("MODULE_KEY <", value, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyLessThanOrEqualTo(String value) {
            addCriterion("MODULE_KEY <=", value, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyLike(String value) {
            addCriterion("MODULE_KEY like", value, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyNotLike(String value) {
            addCriterion("MODULE_KEY not like", value, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyIn(List values) {
            addCriterion("MODULE_KEY in", values, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyNotIn(List values) {
            addCriterion("MODULE_KEY not in", values, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyBetween(String value1, String value2) {
            addCriterion("MODULE_KEY between", value1, value2, "moduleKey");
            return this;
        }

        public Criteria andModuleKeyNotBetween(String value1, String value2) {
            addCriterion("MODULE_KEY not between", value1, value2, "moduleKey");
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
