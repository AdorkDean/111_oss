package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TjMemberLeaderAddCountExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TjMemberLeaderAddCountExample() {
        oredCriteria = new ArrayList();


    }

    protected TjMemberLeaderAddCountExample(TjMemberLeaderAddCountExample example) {
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

        public Criteria andDateStrIsNull() {
            addCriterion("date_str is null");
            return this;


        }

        public Criteria andDateStrIsNotNull() {
            addCriterion("date_str is not null");
            return this;


        }

        public Criteria andDateStrEqualTo(String value) {
            addCriterion("date_str =", value, "dateStr");
            return this;


        }

        public Criteria andDateStrNotEqualTo(String value) {
            addCriterion("date_str <>", value, "dateStr");
            return this;


        }

        public Criteria andDateStrGreaterThan(String value) {
            addCriterion("date_str >", value, "dateStr");
            return this;


        }

        public Criteria andDateStrGreaterThanOrEqualTo(String value) {
            addCriterion("date_str >=", value, "dateStr");
            return this;


        }

        public Criteria andDateStrLessThan(String value) {
            addCriterion("date_str <", value, "dateStr");
            return this;


        }

        public Criteria andDateStrLessThanOrEqualTo(String value) {
            addCriterion("date_str <=", value, "dateStr");
            return this;


        }

        public Criteria andDateStrLike(String value) {
            addCriterion("date_str like", value, "dateStr");
            return this;


        }

        public Criteria andDateStrNotLike(String value) {
            addCriterion("date_str not like", value, "dateStr");
            return this;


        }

        public Criteria andDateStrIn(List values) {
            addCriterion("date_str in", values, "dateStr");
            return this;


        }

        public Criteria andDateStrNotIn(List values) {
            addCriterion("date_str not in", values, "dateStr");
            return this;


        }

        public Criteria andDateStrBetween(String value1, String value2) {
            addCriterion("date_str between", value1, value2, "dateStr");
            return this;


        }

        public Criteria andDateStrNotBetween(String value1, String value2) {
            addCriterion("date_str not between", value1, value2, "dateStr");
            return this;


        }

        public Criteria andLeaderCountIsNull() {
            addCriterion("leader_count is null");
            return this;


        }

        public Criteria andLeaderCountIsNotNull() {
            addCriterion("leader_count is not null");
            return this;


        }

        public Criteria andLeaderCountEqualTo(Integer value) {
            addCriterion("leader_count =", value, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountNotEqualTo(Integer value) {
            addCriterion("leader_count <>", value, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountGreaterThan(Integer value) {
            addCriterion("leader_count >", value, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("leader_count >=", value, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountLessThan(Integer value) {
            addCriterion("leader_count <", value, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountLessThanOrEqualTo(Integer value) {
            addCriterion("leader_count <=", value, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountIn(List values) {
            addCriterion("leader_count in", values, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountNotIn(List values) {
            addCriterion("leader_count not in", values, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountBetween(Integer value1, Integer value2) {
            addCriterion("leader_count between", value1, value2, "leaderCount");
            return this;


        }

        public Criteria andLeaderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("leader_count not between", value1, value2, "leaderCount");
            return this;


        }

        public Criteria andMemberCountIsNull() {
            addCriterion("member_count is null");
            return this;


        }

        public Criteria andMemberCountIsNotNull() {
            addCriterion("member_count is not null");
            return this;


        }

        public Criteria andMemberCountEqualTo(Integer value) {
            addCriterion("member_count =", value, "memberCount");
            return this;


        }

        public Criteria andMemberCountNotEqualTo(Integer value) {
            addCriterion("member_count <>", value, "memberCount");
            return this;


        }

        public Criteria andMemberCountGreaterThan(Integer value) {
            addCriterion("member_count >", value, "memberCount");
            return this;


        }

        public Criteria andMemberCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_count >=", value, "memberCount");
            return this;


        }

        public Criteria andMemberCountLessThan(Integer value) {
            addCriterion("member_count <", value, "memberCount");
            return this;


        }

        public Criteria andMemberCountLessThanOrEqualTo(Integer value) {
            addCriterion("member_count <=", value, "memberCount");
            return this;


        }

        public Criteria andMemberCountIn(List values) {
            addCriterion("member_count in", values, "memberCount");
            return this;


        }

        public Criteria andMemberCountNotIn(List values) {
            addCriterion("member_count not in", values, "memberCount");
            return this;


        }

        public Criteria andMemberCountBetween(Integer value1, Integer value2) {
            addCriterion("member_count between", value1, value2, "memberCount");
            return this;


        }

        public Criteria andMemberCountNotBetween(Integer value1, Integer value2) {
            addCriterion("member_count not between", value1, value2, "memberCount");
            return this;


        }

        public Criteria andPowderCountIsNull() {
            addCriterion("powder_count is null");
            return this;


        }

        public Criteria andPowderCountIsNotNull() {
            addCriterion("powder_count is not null");
            return this;


        }

        public Criteria andPowderCountEqualTo(Integer value) {
            addCriterion("powder_count =", value, "powderCount");
            return this;


        }

        public Criteria andPowderCountNotEqualTo(Integer value) {
            addCriterion("powder_count <>", value, "powderCount");
            return this;


        }

        public Criteria andPowderCountGreaterThan(Integer value) {
            addCriterion("powder_count >", value, "powderCount");
            return this;


        }

        public Criteria andPowderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("powder_count >=", value, "powderCount");
            return this;


        }

        public Criteria andPowderCountLessThan(Integer value) {
            addCriterion("powder_count <", value, "powderCount");
            return this;


        }

        public Criteria andPowderCountLessThanOrEqualTo(Integer value) {
            addCriterion("powder_count <=", value, "powderCount");
            return this;


        }

        public Criteria andPowderCountIn(List values) {
            addCriterion("powder_count in", values, "powderCount");
            return this;


        }

        public Criteria andPowderCountNotIn(List values) {
            addCriterion("powder_count not in", values, "powderCount");
            return this;


        }

        public Criteria andPowderCountBetween(Integer value1, Integer value2) {
            addCriterion("powder_count between", value1, value2, "powderCount");
            return this;


        }

        public Criteria andPowderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("powder_count not between", value1, value2, "powderCount");
            return this;


        }

        public Criteria andLeaderSumIsNull() {
            addCriterion("leader_sum is null");
            return this;


        }

        public Criteria andLeaderSumIsNotNull() {
            addCriterion("leader_sum is not null");
            return this;


        }

        public Criteria andLeaderSumEqualTo(Integer value) {
            addCriterion("leader_sum =", value, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumNotEqualTo(Integer value) {
            addCriterion("leader_sum <>", value, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumGreaterThan(Integer value) {
            addCriterion("leader_sum >", value, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("leader_sum >=", value, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumLessThan(Integer value) {
            addCriterion("leader_sum <", value, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumLessThanOrEqualTo(Integer value) {
            addCriterion("leader_sum <=", value, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumIn(List values) {
            addCriterion("leader_sum in", values, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumNotIn(List values) {
            addCriterion("leader_sum not in", values, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumBetween(Integer value1, Integer value2) {
            addCriterion("leader_sum between", value1, value2, "leaderSum");
            return this;


        }

        public Criteria andLeaderSumNotBetween(Integer value1, Integer value2) {
            addCriterion("leader_sum not between", value1, value2, "leaderSum");
            return this;


        }

        public Criteria andMemberSumIsNull() {
            addCriterion("member_sum is null");
            return this;


        }

        public Criteria andMemberSumIsNotNull() {
            addCriterion("member_sum is not null");
            return this;


        }

        public Criteria andMemberSumEqualTo(Integer value) {
            addCriterion("member_sum =", value, "memberSum");
            return this;


        }

        public Criteria andMemberSumNotEqualTo(Integer value) {
            addCriterion("member_sum <>", value, "memberSum");
            return this;


        }

        public Criteria andMemberSumGreaterThan(Integer value) {
            addCriterion("member_sum >", value, "memberSum");
            return this;


        }

        public Criteria andMemberSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_sum >=", value, "memberSum");
            return this;


        }

        public Criteria andMemberSumLessThan(Integer value) {
            addCriterion("member_sum <", value, "memberSum");
            return this;


        }

        public Criteria andMemberSumLessThanOrEqualTo(Integer value) {
            addCriterion("member_sum <=", value, "memberSum");
            return this;


        }

        public Criteria andMemberSumIn(List values) {
            addCriterion("member_sum in", values, "memberSum");
            return this;


        }

        public Criteria andMemberSumNotIn(List values) {
            addCriterion("member_sum not in", values, "memberSum");
            return this;


        }

        public Criteria andMemberSumBetween(Integer value1, Integer value2) {
            addCriterion("member_sum between", value1, value2, "memberSum");
            return this;


        }

        public Criteria andMemberSumNotBetween(Integer value1, Integer value2) {
            addCriterion("member_sum not between", value1, value2, "memberSum");
            return this;


        }

        public Criteria andPowderSumIsNull() {
            addCriterion("powder_sum is null");
            return this;


        }

        public Criteria andPowderSumIsNotNull() {
            addCriterion("powder_sum is not null");
            return this;


        }

        public Criteria andPowderSumEqualTo(Integer value) {
            addCriterion("powder_sum =", value, "powderSum");
            return this;


        }

        public Criteria andPowderSumNotEqualTo(Integer value) {
            addCriterion("powder_sum <>", value, "powderSum");
            return this;


        }

        public Criteria andPowderSumGreaterThan(Integer value) {
            addCriterion("powder_sum >", value, "powderSum");
            return this;


        }

        public Criteria andPowderSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("powder_sum >=", value, "powderSum");
            return this;


        }

        public Criteria andPowderSumLessThan(Integer value) {
            addCriterion("powder_sum <", value, "powderSum");
            return this;


        }

        public Criteria andPowderSumLessThanOrEqualTo(Integer value) {
            addCriterion("powder_sum <=", value, "powderSum");
            return this;


        }

        public Criteria andPowderSumIn(List values) {
            addCriterion("powder_sum in", values, "powderSum");
            return this;


        }

        public Criteria andPowderSumNotIn(List values) {
            addCriterion("powder_sum not in", values, "powderSum");
            return this;


        }

        public Criteria andPowderSumBetween(Integer value1, Integer value2) {
            addCriterion("powder_sum between", value1, value2, "powderSum");
            return this;


        }

        public Criteria andPowderSumNotBetween(Integer value1, Integer value2) {
            addCriterion("powder_sum not between", value1, value2, "powderSum");
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
