package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TOssOperateLogExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TOssOperateLogExample() {
        oredCriteria = new ArrayList();


    }

    protected TOssOperateLogExample(TOssOperateLogExample example) {
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

        public Criteria andModuleTypeIsNull() {
            addCriterion("module_type is null");
            return this;


        }

        public Criteria andModuleTypeIsNotNull() {
            addCriterion("module_type is not null");
            return this;


        }

        public Criteria andModuleTypeEqualTo(Integer value) {
            addCriterion("module_type =", value, "moduleType");
            return this;


        }

        public Criteria andModuleTypeNotEqualTo(Integer value) {
            addCriterion("module_type <>", value, "moduleType");
            return this;


        }

        public Criteria andModuleTypeGreaterThan(Integer value) {
            addCriterion("module_type >", value, "moduleType");
            return this;


        }

        public Criteria andModuleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("module_type >=", value, "moduleType");
            return this;


        }

        public Criteria andModuleTypeLessThan(Integer value) {
            addCriterion("module_type <", value, "moduleType");
            return this;


        }

        public Criteria andModuleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("module_type <=", value, "moduleType");
            return this;


        }

        public Criteria andModuleTypeIn(List values) {
            addCriterion("module_type in", values, "moduleType");
            return this;


        }

        public Criteria andModuleTypeNotIn(List values) {
            addCriterion("module_type not in", values, "moduleType");
            return this;


        }

        public Criteria andModuleTypeBetween(Integer value1, Integer value2) {
            addCriterion("module_type between", value1, value2, "moduleType");
            return this;


        }

        public Criteria andModuleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("module_type not between", value1, value2, "moduleType");
            return this;


        }

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return this;


        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return this;


        }

        public Criteria andRecordIdEqualTo(Long value) {
            addCriterion("record_id =", value, "recordId");
            return this;


        }

        public Criteria andRecordIdNotEqualTo(Long value) {
            addCriterion("record_id <>", value, "recordId");
            return this;


        }

        public Criteria andRecordIdGreaterThan(Long value) {
            addCriterion("record_id >", value, "recordId");
            return this;


        }

        public Criteria andRecordIdGreaterThanOrEqualTo(Long value) {
            addCriterion("record_id >=", value, "recordId");
            return this;


        }

        public Criteria andRecordIdLessThan(Long value) {
            addCriterion("record_id <", value, "recordId");
            return this;


        }

        public Criteria andRecordIdLessThanOrEqualTo(Long value) {
            addCriterion("record_id <=", value, "recordId");
            return this;


        }

        public Criteria andRecordIdIn(List values) {
            addCriterion("record_id in", values, "recordId");
            return this;


        }

        public Criteria andRecordIdNotIn(List values) {
            addCriterion("record_id not in", values, "recordId");
            return this;


        }

        public Criteria andRecordIdBetween(Long value1, Long value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return this;


        }

        public Criteria andRecordIdNotBetween(Long value1, Long value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
            return this;


        }

        public Criteria andOperationRemakeIsNull() {
            addCriterion("operation_remake is null");
            return this;


        }

        public Criteria andOperationRemakeIsNotNull() {
            addCriterion("operation_remake is not null");
            return this;


        }

        public Criteria andOperationRemakeEqualTo(String value) {
            addCriterion("operation_remake =", value, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeNotEqualTo(String value) {
            addCriterion("operation_remake <>", value, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeGreaterThan(String value) {
            addCriterion("operation_remake >", value, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeGreaterThanOrEqualTo(String value) {
            addCriterion("operation_remake >=", value, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeLessThan(String value) {
            addCriterion("operation_remake <", value, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeLessThanOrEqualTo(String value) {
            addCriterion("operation_remake <=", value, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeLike(String value) {
            addCriterion("operation_remake like", value, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeNotLike(String value) {
            addCriterion("operation_remake not like", value, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeIn(List values) {
            addCriterion("operation_remake in", values, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeNotIn(List values) {
            addCriterion("operation_remake not in", values, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeBetween(String value1, String value2) {
            addCriterion("operation_remake between", value1, value2, "operationRemake");
            return this;


        }

        public Criteria andOperationRemakeNotBetween(String value1, String value2) {
            addCriterion("operation_remake not between", value1, value2, "operationRemake");
            return this;


        }

        public Criteria andOperationUsernameIsNull() {
            addCriterion("operation_username is null");
            return this;


        }

        public Criteria andOperationUsernameIsNotNull() {
            addCriterion("operation_username is not null");
            return this;


        }

        public Criteria andOperationUsernameEqualTo(String value) {
            addCriterion("operation_username =", value, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameNotEqualTo(String value) {
            addCriterion("operation_username <>", value, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameGreaterThan(String value) {
            addCriterion("operation_username >", value, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("operation_username >=", value, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameLessThan(String value) {
            addCriterion("operation_username <", value, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameLessThanOrEqualTo(String value) {
            addCriterion("operation_username <=", value, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameLike(String value) {
            addCriterion("operation_username like", value, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameNotLike(String value) {
            addCriterion("operation_username not like", value, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameIn(List values) {
            addCriterion("operation_username in", values, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameNotIn(List values) {
            addCriterion("operation_username not in", values, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameBetween(String value1, String value2) {
            addCriterion("operation_username between", value1, value2, "operationUsername");
            return this;


        }

        public Criteria andOperationUsernameNotBetween(String value1, String value2) {
            addCriterion("operation_username not between", value1, value2, "operationUsername");
            return this;


        }

        public Criteria andOperationDtIsNull() {
            addCriterion("operation_dt is null");
            return this;


        }

        public Criteria andOperationDtIsNotNull() {
            addCriterion("operation_dt is not null");
            return this;


        }

        public Criteria andOperationDtEqualTo(Date value) {
            addCriterion("operation_dt =", value, "operationDt");
            return this;


        }

        public Criteria andOperationDtNotEqualTo(Date value) {
            addCriterion("operation_dt <>", value, "operationDt");
            return this;


        }

        public Criteria andOperationDtGreaterThan(Date value) {
            addCriterion("operation_dt >", value, "operationDt");
            return this;


        }

        public Criteria andOperationDtGreaterThanOrEqualTo(Date value) {
            addCriterion("operation_dt >=", value, "operationDt");
            return this;


        }

        public Criteria andOperationDtLessThan(Date value) {
            addCriterion("operation_dt <", value, "operationDt");
            return this;


        }

        public Criteria andOperationDtLessThanOrEqualTo(Date value) {
            addCriterion("operation_dt <=", value, "operationDt");
            return this;


        }

        public Criteria andOperationDtIn(List values) {
            addCriterion("operation_dt in", values, "operationDt");
            return this;


        }

        public Criteria andOperationDtNotIn(List values) {
            addCriterion("operation_dt not in", values, "operationDt");
            return this;


        }

        public Criteria andOperationDtBetween(Date value1, Date value2) {
            addCriterion("operation_dt between", value1, value2, "operationDt");
            return this;


        }

        public Criteria andOperationDtNotBetween(Date value1, Date value2) {
            addCriterion("operation_dt not between", value1, value2, "operationDt");
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
