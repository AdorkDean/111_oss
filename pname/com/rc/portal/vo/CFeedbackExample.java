package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class CFeedbackExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public CFeedbackExample() {
        oredCriteria = new ArrayList();


    }

    protected CFeedbackExample(CFeedbackExample example) {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return this;


        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return this;


        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return this;


        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return this;


        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return this;


        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
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

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return this;


        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return this;


        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return this;


        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return this;


        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return this;


        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return this;


        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return this;


        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return this;


        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return this;


        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return this;


        }

        public Criteria andCreateDateIn(List values) {
            addCriterion("create_date in", values, "createDate");
            return this;


        }

        public Criteria andCreateDateNotIn(List values) {
            addCriterion("create_date not in", values, "createDate");
            return this;


        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return this;


        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return this;


        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return this;


        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return this;


        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return this;


        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return this;


        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return this;


        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return this;


        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return this;


        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return this;


        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return this;


        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return this;


        }

        public Criteria andMessageIn(List values) {
            addCriterion("message in", values, "message");
            return this;


        }

        public Criteria andMessageNotIn(List values) {
            addCriterion("message not in", values, "message");
            return this;


        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return this;


        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
            return this;


        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return this;


        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return this;


        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return this;


        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return this;


        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return this;


        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return this;


        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return this;


        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return this;


        }

        public Criteria andUserIdIn(List values) {
            addCriterion("user_id in", values, "userId");
            return this;


        }

        public Criteria andUserIdNotIn(List values) {
            addCriterion("user_id not in", values, "userId");
            return this;


        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return this;


        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return this;


        }

        public Criteria andFromPhoneIsNull() {
            addCriterion("from_phone is null");
            return this;


        }

        public Criteria andFromPhoneIsNotNull() {
            addCriterion("from_phone is not null");
            return this;


        }

        public Criteria andFromPhoneEqualTo(String value) {
            addCriterion("from_phone =", value, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneNotEqualTo(String value) {
            addCriterion("from_phone <>", value, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneGreaterThan(String value) {
            addCriterion("from_phone >", value, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("from_phone >=", value, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneLessThan(String value) {
            addCriterion("from_phone <", value, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneLessThanOrEqualTo(String value) {
            addCriterion("from_phone <=", value, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneLike(String value) {
            addCriterion("from_phone like", value, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneNotLike(String value) {
            addCriterion("from_phone not like", value, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneIn(List values) {
            addCriterion("from_phone in", values, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneNotIn(List values) {
            addCriterion("from_phone not in", values, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneBetween(String value1, String value2) {
            addCriterion("from_phone between", value1, value2, "fromPhone");
            return this;


        }

        public Criteria andFromPhoneNotBetween(String value1, String value2) {
            addCriterion("from_phone not between", value1, value2, "fromPhone");
            return this;


        }

        public Criteria andCollectIsNull() {
            addCriterion("collect is null");
            return this;


        }

        public Criteria andCollectIsNotNull() {
            addCriterion("collect is not null");
            return this;


        }

        public Criteria andCollectEqualTo(Integer value) {
            addCriterion("collect =", value, "collect");
            return this;


        }

        public Criteria andCollectNotEqualTo(Integer value) {
            addCriterion("collect <>", value, "collect");
            return this;


        }

        public Criteria andCollectGreaterThan(Integer value) {
            addCriterion("collect >", value, "collect");
            return this;


        }

        public Criteria andCollectGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect >=", value, "collect");
            return this;


        }

        public Criteria andCollectLessThan(Integer value) {
            addCriterion("collect <", value, "collect");
            return this;


        }

        public Criteria andCollectLessThanOrEqualTo(Integer value) {
            addCriterion("collect <=", value, "collect");
            return this;


        }

        public Criteria andCollectIn(List values) {
            addCriterion("collect in", values, "collect");
            return this;


        }

        public Criteria andCollectNotIn(List values) {
            addCriterion("collect not in", values, "collect");
            return this;


        }

        public Criteria andCollectBetween(Integer value1, Integer value2) {
            addCriterion("collect between", value1, value2, "collect");
            return this;


        }

        public Criteria andCollectNotBetween(Integer value1, Integer value2) {
            addCriterion("collect not between", value1, value2, "collect");
            return this;


        }

        public Criteria andNetTypeIsNull() {
            addCriterion("net_type is null");
            return this;


        }

        public Criteria andNetTypeIsNotNull() {
            addCriterion("net_type is not null");
            return this;


        }

        public Criteria andNetTypeEqualTo(String value) {
            addCriterion("net_type =", value, "netType");
            return this;


        }

        public Criteria andNetTypeNotEqualTo(String value) {
            addCriterion("net_type <>", value, "netType");
            return this;


        }

        public Criteria andNetTypeGreaterThan(String value) {
            addCriterion("net_type >", value, "netType");
            return this;


        }

        public Criteria andNetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("net_type >=", value, "netType");
            return this;


        }

        public Criteria andNetTypeLessThan(String value) {
            addCriterion("net_type <", value, "netType");
            return this;


        }

        public Criteria andNetTypeLessThanOrEqualTo(String value) {
            addCriterion("net_type <=", value, "netType");
            return this;


        }

        public Criteria andNetTypeLike(String value) {
            addCriterion("net_type like", value, "netType");
            return this;


        }

        public Criteria andNetTypeNotLike(String value) {
            addCriterion("net_type not like", value, "netType");
            return this;


        }

        public Criteria andNetTypeIn(List values) {
            addCriterion("net_type in", values, "netType");
            return this;


        }

        public Criteria andNetTypeNotIn(List values) {
            addCriterion("net_type not in", values, "netType");
            return this;


        }

        public Criteria andNetTypeBetween(String value1, String value2) {
            addCriterion("net_type between", value1, value2, "netType");
            return this;


        }

        public Criteria andNetTypeNotBetween(String value1, String value2) {
            addCriterion("net_type not between", value1, value2, "netType");
            return this;


        }

        public Criteria andSystemTypeIsNull() {
            addCriterion("system_type is null");
            return this;


        }

        public Criteria andSystemTypeIsNotNull() {
            addCriterion("system_type is not null");
            return this;


        }

        public Criteria andSystemTypeEqualTo(String value) {
            addCriterion("system_type =", value, "systemType");
            return this;


        }

        public Criteria andSystemTypeNotEqualTo(String value) {
            addCriterion("system_type <>", value, "systemType");
            return this;


        }

        public Criteria andSystemTypeGreaterThan(String value) {
            addCriterion("system_type >", value, "systemType");
            return this;


        }

        public Criteria andSystemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("system_type >=", value, "systemType");
            return this;


        }

        public Criteria andSystemTypeLessThan(String value) {
            addCriterion("system_type <", value, "systemType");
            return this;


        }

        public Criteria andSystemTypeLessThanOrEqualTo(String value) {
            addCriterion("system_type <=", value, "systemType");
            return this;


        }

        public Criteria andSystemTypeLike(String value) {
            addCriterion("system_type like", value, "systemType");
            return this;


        }

        public Criteria andSystemTypeNotLike(String value) {
            addCriterion("system_type not like", value, "systemType");
            return this;


        }

        public Criteria andSystemTypeIn(List values) {
            addCriterion("system_type in", values, "systemType");
            return this;


        }

        public Criteria andSystemTypeNotIn(List values) {
            addCriterion("system_type not in", values, "systemType");
            return this;


        }

        public Criteria andSystemTypeBetween(String value1, String value2) {
            addCriterion("system_type between", value1, value2, "systemType");
            return this;


        }

        public Criteria andSystemTypeNotBetween(String value1, String value2) {
            addCriterion("system_type not between", value1, value2, "systemType");
            return this;


        }

        public Criteria andClientTypeIsNull() {
            addCriterion("client_type is null");
            return this;


        }

        public Criteria andClientTypeIsNotNull() {
            addCriterion("client_type is not null");
            return this;


        }

        public Criteria andClientTypeEqualTo(String value) {
            addCriterion("client_type =", value, "clientType");
            return this;


        }

        public Criteria andClientTypeNotEqualTo(String value) {
            addCriterion("client_type <>", value, "clientType");
            return this;


        }

        public Criteria andClientTypeGreaterThan(String value) {
            addCriterion("client_type >", value, "clientType");
            return this;


        }

        public Criteria andClientTypeGreaterThanOrEqualTo(String value) {
            addCriterion("client_type >=", value, "clientType");
            return this;


        }

        public Criteria andClientTypeLessThan(String value) {
            addCriterion("client_type <", value, "clientType");
            return this;


        }

        public Criteria andClientTypeLessThanOrEqualTo(String value) {
            addCriterion("client_type <=", value, "clientType");
            return this;


        }

        public Criteria andClientTypeLike(String value) {
            addCriterion("client_type like", value, "clientType");
            return this;


        }

        public Criteria andClientTypeNotLike(String value) {
            addCriterion("client_type not like", value, "clientType");
            return this;


        }

        public Criteria andClientTypeIn(List values) {
            addCriterion("client_type in", values, "clientType");
            return this;


        }

        public Criteria andClientTypeNotIn(List values) {
            addCriterion("client_type not in", values, "clientType");
            return this;


        }

        public Criteria andClientTypeBetween(String value1, String value2) {
            addCriterion("client_type between", value1, value2, "clientType");
            return this;


        }

        public Criteria andClientTypeNotBetween(String value1, String value2) {
            addCriterion("client_type not between", value1, value2, "clientType");
            return this;


        }

        public Criteria andQudaoSnIsNull() {
            addCriterion("qudao_sn is null");
            return this;


        }

        public Criteria andQudaoSnIsNotNull() {
            addCriterion("qudao_sn is not null");
            return this;


        }

        public Criteria andQudaoSnEqualTo(String value) {
            addCriterion("qudao_sn =", value, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnNotEqualTo(String value) {
            addCriterion("qudao_sn <>", value, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnGreaterThan(String value) {
            addCriterion("qudao_sn >", value, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnGreaterThanOrEqualTo(String value) {
            addCriterion("qudao_sn >=", value, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnLessThan(String value) {
            addCriterion("qudao_sn <", value, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnLessThanOrEqualTo(String value) {
            addCriterion("qudao_sn <=", value, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnLike(String value) {
            addCriterion("qudao_sn like", value, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnNotLike(String value) {
            addCriterion("qudao_sn not like", value, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnIn(List values) {
            addCriterion("qudao_sn in", values, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnNotIn(List values) {
            addCriterion("qudao_sn not in", values, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnBetween(String value1, String value2) {
            addCriterion("qudao_sn between", value1, value2, "qudaoSn");
            return this;


        }

        public Criteria andQudaoSnNotBetween(String value1, String value2) {
            addCriterion("qudao_sn not between", value1, value2, "qudaoSn");
            return this;


        }

        public Criteria andQudaoNameIsNull() {
            addCriterion("qudao_name is null");
            return this;


        }

        public Criteria andQudaoNameIsNotNull() {
            addCriterion("qudao_name is not null");
            return this;


        }

        public Criteria andQudaoNameEqualTo(String value) {
            addCriterion("qudao_name =", value, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameNotEqualTo(String value) {
            addCriterion("qudao_name <>", value, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameGreaterThan(String value) {
            addCriterion("qudao_name >", value, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameGreaterThanOrEqualTo(String value) {
            addCriterion("qudao_name >=", value, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameLessThan(String value) {
            addCriterion("qudao_name <", value, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameLessThanOrEqualTo(String value) {
            addCriterion("qudao_name <=", value, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameLike(String value) {
            addCriterion("qudao_name like", value, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameNotLike(String value) {
            addCriterion("qudao_name not like", value, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameIn(List values) {
            addCriterion("qudao_name in", values, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameNotIn(List values) {
            addCriterion("qudao_name not in", values, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameBetween(String value1, String value2) {
            addCriterion("qudao_name between", value1, value2, "qudaoName");
            return this;


        }

        public Criteria andQudaoNameNotBetween(String value1, String value2) {
            addCriterion("qudao_name not between", value1, value2, "qudaoName");
            return this;


        }

        public Criteria andFenbielvIsNull() {
            addCriterion("fenbielv is null");
            return this;


        }

        public Criteria andFenbielvIsNotNull() {
            addCriterion("fenbielv is not null");
            return this;


        }

        public Criteria andFenbielvEqualTo(String value) {
            addCriterion("fenbielv =", value, "fenbielv");
            return this;


        }

        public Criteria andFenbielvNotEqualTo(String value) {
            addCriterion("fenbielv <>", value, "fenbielv");
            return this;


        }

        public Criteria andFenbielvGreaterThan(String value) {
            addCriterion("fenbielv >", value, "fenbielv");
            return this;


        }

        public Criteria andFenbielvGreaterThanOrEqualTo(String value) {
            addCriterion("fenbielv >=", value, "fenbielv");
            return this;


        }

        public Criteria andFenbielvLessThan(String value) {
            addCriterion("fenbielv <", value, "fenbielv");
            return this;


        }

        public Criteria andFenbielvLessThanOrEqualTo(String value) {
            addCriterion("fenbielv <=", value, "fenbielv");
            return this;


        }

        public Criteria andFenbielvLike(String value) {
            addCriterion("fenbielv like", value, "fenbielv");
            return this;


        }

        public Criteria andFenbielvNotLike(String value) {
            addCriterion("fenbielv not like", value, "fenbielv");
            return this;


        }

        public Criteria andFenbielvIn(List values) {
            addCriterion("fenbielv in", values, "fenbielv");
            return this;


        }

        public Criteria andFenbielvNotIn(List values) {
            addCriterion("fenbielv not in", values, "fenbielv");
            return this;


        }

        public Criteria andFenbielvBetween(String value1, String value2) {
            addCriterion("fenbielv between", value1, value2, "fenbielv");
            return this;


        }

        public Criteria andFenbielvNotBetween(String value1, String value2) {
            addCriterion("fenbielv not between", value1, value2, "fenbielv");
            return this;


        }

        public Criteria andEquipmentIsNull() {
            addCriterion("equipment is null");
            return this;


        }

        public Criteria andEquipmentIsNotNull() {
            addCriterion("equipment is not null");
            return this;


        }

        public Criteria andEquipmentEqualTo(String value) {
            addCriterion("equipment =", value, "equipment");
            return this;


        }

        public Criteria andEquipmentNotEqualTo(String value) {
            addCriterion("equipment <>", value, "equipment");
            return this;


        }

        public Criteria andEquipmentGreaterThan(String value) {
            addCriterion("equipment >", value, "equipment");
            return this;


        }

        public Criteria andEquipmentGreaterThanOrEqualTo(String value) {
            addCriterion("equipment >=", value, "equipment");
            return this;


        }

        public Criteria andEquipmentLessThan(String value) {
            addCriterion("equipment <", value, "equipment");
            return this;


        }

        public Criteria andEquipmentLessThanOrEqualTo(String value) {
            addCriterion("equipment <=", value, "equipment");
            return this;


        }

        public Criteria andEquipmentLike(String value) {
            addCriterion("equipment like", value, "equipment");
            return this;


        }

        public Criteria andEquipmentNotLike(String value) {
            addCriterion("equipment not like", value, "equipment");
            return this;


        }

        public Criteria andEquipmentIn(List values) {
            addCriterion("equipment in", values, "equipment");
            return this;


        }

        public Criteria andEquipmentNotIn(List values) {
            addCriterion("equipment not in", values, "equipment");
            return this;


        }

        public Criteria andEquipmentBetween(String value1, String value2) {
            addCriterion("equipment between", value1, value2, "equipment");
            return this;


        }

        public Criteria andEquipmentNotBetween(String value1, String value2) {
            addCriterion("equipment not between", value1, value2, "equipment");
            return this;


        }

        public Criteria andClientVersionIsNull() {
            addCriterion("client_version is null");
            return this;


        }

        public Criteria andClientVersionIsNotNull() {
            addCriterion("client_version is not null");
            return this;


        }

        public Criteria andClientVersionEqualTo(String value) {
            addCriterion("client_version =", value, "clientVersion");
            return this;


        }

        public Criteria andClientVersionNotEqualTo(String value) {
            addCriterion("client_version <>", value, "clientVersion");
            return this;


        }

        public Criteria andClientVersionGreaterThan(String value) {
            addCriterion("client_version >", value, "clientVersion");
            return this;


        }

        public Criteria andClientVersionGreaterThanOrEqualTo(String value) {
            addCriterion("client_version >=", value, "clientVersion");
            return this;


        }

        public Criteria andClientVersionLessThan(String value) {
            addCriterion("client_version <", value, "clientVersion");
            return this;


        }

        public Criteria andClientVersionLessThanOrEqualTo(String value) {
            addCriterion("client_version <=", value, "clientVersion");
            return this;


        }

        public Criteria andClientVersionLike(String value) {
            addCriterion("client_version like", value, "clientVersion");
            return this;


        }

        public Criteria andClientVersionNotLike(String value) {
            addCriterion("client_version not like", value, "clientVersion");
            return this;


        }

        public Criteria andClientVersionIn(List values) {
            addCriterion("client_version in", values, "clientVersion");
            return this;


        }

        public Criteria andClientVersionNotIn(List values) {
            addCriterion("client_version not in", values, "clientVersion");
            return this;


        }

        public Criteria andClientVersionBetween(String value1, String value2) {
            addCriterion("client_version between", value1, value2, "clientVersion");
            return this;


        }

        public Criteria andClientVersionNotBetween(String value1, String value2) {
            addCriterion("client_version not between", value1, value2, "clientVersion");
            return this;


        }


    }


}
