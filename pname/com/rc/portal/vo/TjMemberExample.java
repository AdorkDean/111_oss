package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TjMemberExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TjMemberExample() {
        oredCriteria = new ArrayList();


    }

    protected TjMemberExample(TjMemberExample example) {
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

        public Criteria andDateDayIsNull() {
            addCriterion("date_day is null");
            return this;


        }

        public Criteria andDateDayIsNotNull() {
            addCriterion("date_day is not null");
            return this;


        }

        public Criteria andDateDayEqualTo(Date value) {
            addCriterion("date_day =", value, "dateDay");
            return this;


        }

        public Criteria andDateDayNotEqualTo(Date value) {
            addCriterion("date_day <>", value, "dateDay");
            return this;


        }

        public Criteria andDateDayGreaterThan(Date value) {
            addCriterion("date_day >", value, "dateDay");
            return this;


        }

        public Criteria andDateDayGreaterThanOrEqualTo(Date value) {
            addCriterion("date_day >=", value, "dateDay");
            return this;


        }

        public Criteria andDateDayLessThan(Date value) {
            addCriterion("date_day <", value, "dateDay");
            return this;


        }

        public Criteria andDateDayLessThanOrEqualTo(Date value) {
            addCriterion("date_day <=", value, "dateDay");
            return this;


        }

        public Criteria andDateDayIn(List values) {
            addCriterion("date_day in", values, "dateDay");
            return this;


        }

        public Criteria andDateDayNotIn(List values) {
            addCriterion("date_day not in", values, "dateDay");
            return this;


        }

        public Criteria andDateDayBetween(Date value1, Date value2) {
            addCriterion("date_day between", value1, value2, "dateDay");
            return this;


        }

        public Criteria andDateDayNotBetween(Date value1, Date value2) {
            addCriterion("date_day not between", value1, value2, "dateDay");
            return this;


        }

        public Criteria andPcDayAddIsNull() {
            addCriterion("pc_day_add is null");
            return this;


        }

        public Criteria andPcDayAddIsNotNull() {
            addCriterion("pc_day_add is not null");
            return this;


        }

        public Criteria andPcDayAddEqualTo(Integer value) {
            addCriterion("pc_day_add =", value, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddNotEqualTo(Integer value) {
            addCriterion("pc_day_add <>", value, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddGreaterThan(Integer value) {
            addCriterion("pc_day_add >", value, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddGreaterThanOrEqualTo(Integer value) {
            addCriterion("pc_day_add >=", value, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddLessThan(Integer value) {
            addCriterion("pc_day_add <", value, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddLessThanOrEqualTo(Integer value) {
            addCriterion("pc_day_add <=", value, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddIn(List values) {
            addCriterion("pc_day_add in", values, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddNotIn(List values) {
            addCriterion("pc_day_add not in", values, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddBetween(Integer value1, Integer value2) {
            addCriterion("pc_day_add between", value1, value2, "pcDayAdd");
            return this;


        }

        public Criteria andPcDayAddNotBetween(Integer value1, Integer value2) {
            addCriterion("pc_day_add not between", value1, value2, "pcDayAdd");
            return this;


        }

        public Criteria andWapDayAddIsNull() {
            addCriterion("wap_day_add is null");
            return this;


        }

        public Criteria andWapDayAddIsNotNull() {
            addCriterion("wap_day_add is not null");
            return this;


        }

        public Criteria andWapDayAddEqualTo(Integer value) {
            addCriterion("wap_day_add =", value, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddNotEqualTo(Integer value) {
            addCriterion("wap_day_add <>", value, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddGreaterThan(Integer value) {
            addCriterion("wap_day_add >", value, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddGreaterThanOrEqualTo(Integer value) {
            addCriterion("wap_day_add >=", value, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddLessThan(Integer value) {
            addCriterion("wap_day_add <", value, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddLessThanOrEqualTo(Integer value) {
            addCriterion("wap_day_add <=", value, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddIn(List values) {
            addCriterion("wap_day_add in", values, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddNotIn(List values) {
            addCriterion("wap_day_add not in", values, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddBetween(Integer value1, Integer value2) {
            addCriterion("wap_day_add between", value1, value2, "wapDayAdd");
            return this;


        }

        public Criteria andWapDayAddNotBetween(Integer value1, Integer value2) {
            addCriterion("wap_day_add not between", value1, value2, "wapDayAdd");
            return this;


        }

        public Criteria andIosDayAddIsNull() {
            addCriterion("ios_day_add is null");
            return this;


        }

        public Criteria andIosDayAddIsNotNull() {
            addCriterion("ios_day_add is not null");
            return this;


        }

        public Criteria andIosDayAddEqualTo(Integer value) {
            addCriterion("ios_day_add =", value, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddNotEqualTo(Integer value) {
            addCriterion("ios_day_add <>", value, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddGreaterThan(Integer value) {
            addCriterion("ios_day_add >", value, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddGreaterThanOrEqualTo(Integer value) {
            addCriterion("ios_day_add >=", value, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddLessThan(Integer value) {
            addCriterion("ios_day_add <", value, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddLessThanOrEqualTo(Integer value) {
            addCriterion("ios_day_add <=", value, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddIn(List values) {
            addCriterion("ios_day_add in", values, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddNotIn(List values) {
            addCriterion("ios_day_add not in", values, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddBetween(Integer value1, Integer value2) {
            addCriterion("ios_day_add between", value1, value2, "iosDayAdd");
            return this;


        }

        public Criteria andIosDayAddNotBetween(Integer value1, Integer value2) {
            addCriterion("ios_day_add not between", value1, value2, "iosDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddIsNull() {
            addCriterion("android_day_add is null");
            return this;


        }

        public Criteria andAndroidDayAddIsNotNull() {
            addCriterion("android_day_add is not null");
            return this;


        }

        public Criteria andAndroidDayAddEqualTo(Integer value) {
            addCriterion("android_day_add =", value, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddNotEqualTo(Integer value) {
            addCriterion("android_day_add <>", value, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddGreaterThan(Integer value) {
            addCriterion("android_day_add >", value, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddGreaterThanOrEqualTo(Integer value) {
            addCriterion("android_day_add >=", value, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddLessThan(Integer value) {
            addCriterion("android_day_add <", value, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddLessThanOrEqualTo(Integer value) {
            addCriterion("android_day_add <=", value, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddIn(List values) {
            addCriterion("android_day_add in", values, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddNotIn(List values) {
            addCriterion("android_day_add not in", values, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddBetween(Integer value1, Integer value2) {
            addCriterion("android_day_add between", value1, value2, "androidDayAdd");
            return this;


        }

        public Criteria andAndroidDayAddNotBetween(Integer value1, Integer value2) {
            addCriterion("android_day_add not between", value1, value2, "androidDayAdd");
            return this;


        }

        public Criteria andDayAddSumIsNull() {
            addCriterion("day_add_sum is null");
            return this;


        }

        public Criteria andDayAddSumIsNotNull() {
            addCriterion("day_add_sum is not null");
            return this;


        }

        public Criteria andDayAddSumEqualTo(Integer value) {
            addCriterion("day_add_sum =", value, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumNotEqualTo(Integer value) {
            addCriterion("day_add_sum <>", value, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumGreaterThan(Integer value) {
            addCriterion("day_add_sum >", value, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_add_sum >=", value, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumLessThan(Integer value) {
            addCriterion("day_add_sum <", value, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumLessThanOrEqualTo(Integer value) {
            addCriterion("day_add_sum <=", value, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumIn(List values) {
            addCriterion("day_add_sum in", values, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumNotIn(List values) {
            addCriterion("day_add_sum not in", values, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumBetween(Integer value1, Integer value2) {
            addCriterion("day_add_sum between", value1, value2, "dayAddSum");
            return this;


        }

        public Criteria andDayAddSumNotBetween(Integer value1, Integer value2) {
            addCriterion("day_add_sum not between", value1, value2, "dayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumIsNull() {
            addCriterion("yesterday_add_sum is null");
            return this;


        }

        public Criteria andYesterdayAddSumIsNotNull() {
            addCriterion("yesterday_add_sum is not null");
            return this;


        }

        public Criteria andYesterdayAddSumEqualTo(Integer value) {
            addCriterion("yesterday_add_sum =", value, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumNotEqualTo(Integer value) {
            addCriterion("yesterday_add_sum <>", value, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumGreaterThan(Integer value) {
            addCriterion("yesterday_add_sum >", value, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("yesterday_add_sum >=", value, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumLessThan(Integer value) {
            addCriterion("yesterday_add_sum <", value, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumLessThanOrEqualTo(Integer value) {
            addCriterion("yesterday_add_sum <=", value, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumIn(List values) {
            addCriterion("yesterday_add_sum in", values, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumNotIn(List values) {
            addCriterion("yesterday_add_sum not in", values, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumBetween(Integer value1, Integer value2) {
            addCriterion("yesterday_add_sum between", value1, value2, "yesterdayAddSum");
            return this;


        }

        public Criteria andYesterdayAddSumNotBetween(Integer value1, Integer value2) {
            addCriterion("yesterday_add_sum not between", value1, value2, "yesterdayAddSum");
            return this;


        }

        public Criteria andPcCumulativeAddIsNull() {
            addCriterion("pc_cumulative_add is null");
            return this;


        }

        public Criteria andPcCumulativeAddIsNotNull() {
            addCriterion("pc_cumulative_add is not null");
            return this;


        }

        public Criteria andPcCumulativeAddEqualTo(Long value) {
            addCriterion("pc_cumulative_add =", value, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddNotEqualTo(Long value) {
            addCriterion("pc_cumulative_add <>", value, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddGreaterThan(Long value) {
            addCriterion("pc_cumulative_add >", value, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddGreaterThanOrEqualTo(Long value) {
            addCriterion("pc_cumulative_add >=", value, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddLessThan(Long value) {
            addCriterion("pc_cumulative_add <", value, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddLessThanOrEqualTo(Long value) {
            addCriterion("pc_cumulative_add <=", value, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddIn(List values) {
            addCriterion("pc_cumulative_add in", values, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddNotIn(List values) {
            addCriterion("pc_cumulative_add not in", values, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddBetween(Long value1, Long value2) {
            addCriterion("pc_cumulative_add between", value1, value2, "pcCumulativeAdd");
            return this;


        }

        public Criteria andPcCumulativeAddNotBetween(Long value1, Long value2) {
            addCriterion("pc_cumulative_add not between", value1, value2, "pcCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddIsNull() {
            addCriterion("wap_cumulative_add is null");
            return this;


        }

        public Criteria andWapCumulativeAddIsNotNull() {
            addCriterion("wap_cumulative_add is not null");
            return this;


        }

        public Criteria andWapCumulativeAddEqualTo(Long value) {
            addCriterion("wap_cumulative_add =", value, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddNotEqualTo(Long value) {
            addCriterion("wap_cumulative_add <>", value, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddGreaterThan(Long value) {
            addCriterion("wap_cumulative_add >", value, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddGreaterThanOrEqualTo(Long value) {
            addCriterion("wap_cumulative_add >=", value, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddLessThan(Long value) {
            addCriterion("wap_cumulative_add <", value, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddLessThanOrEqualTo(Long value) {
            addCriterion("wap_cumulative_add <=", value, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddIn(List values) {
            addCriterion("wap_cumulative_add in", values, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddNotIn(List values) {
            addCriterion("wap_cumulative_add not in", values, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddBetween(Long value1, Long value2) {
            addCriterion("wap_cumulative_add between", value1, value2, "wapCumulativeAdd");
            return this;


        }

        public Criteria andWapCumulativeAddNotBetween(Long value1, Long value2) {
            addCriterion("wap_cumulative_add not between", value1, value2, "wapCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddIsNull() {
            addCriterion("ios_cumulative_add is null");
            return this;


        }

        public Criteria andIosCumulativeAddIsNotNull() {
            addCriterion("ios_cumulative_add is not null");
            return this;


        }

        public Criteria andIosCumulativeAddEqualTo(Long value) {
            addCriterion("ios_cumulative_add =", value, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddNotEqualTo(Long value) {
            addCriterion("ios_cumulative_add <>", value, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddGreaterThan(Long value) {
            addCriterion("ios_cumulative_add >", value, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddGreaterThanOrEqualTo(Long value) {
            addCriterion("ios_cumulative_add >=", value, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddLessThan(Long value) {
            addCriterion("ios_cumulative_add <", value, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddLessThanOrEqualTo(Long value) {
            addCriterion("ios_cumulative_add <=", value, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddIn(List values) {
            addCriterion("ios_cumulative_add in", values, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddNotIn(List values) {
            addCriterion("ios_cumulative_add not in", values, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddBetween(Long value1, Long value2) {
            addCriterion("ios_cumulative_add between", value1, value2, "iosCumulativeAdd");
            return this;


        }

        public Criteria andIosCumulativeAddNotBetween(Long value1, Long value2) {
            addCriterion("ios_cumulative_add not between", value1, value2, "iosCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddIsNull() {
            addCriterion("android_cumulative_add is null");
            return this;


        }

        public Criteria andAndroidCumulativeAddIsNotNull() {
            addCriterion("android_cumulative_add is not null");
            return this;


        }

        public Criteria andAndroidCumulativeAddEqualTo(Long value) {
            addCriterion("android_cumulative_add =", value, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddNotEqualTo(Long value) {
            addCriterion("android_cumulative_add <>", value, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddGreaterThan(Long value) {
            addCriterion("android_cumulative_add >", value, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddGreaterThanOrEqualTo(Long value) {
            addCriterion("android_cumulative_add >=", value, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddLessThan(Long value) {
            addCriterion("android_cumulative_add <", value, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddLessThanOrEqualTo(Long value) {
            addCriterion("android_cumulative_add <=", value, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddIn(List values) {
            addCriterion("android_cumulative_add in", values, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddNotIn(List values) {
            addCriterion("android_cumulative_add not in", values, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddBetween(Long value1, Long value2) {
            addCriterion("android_cumulative_add between", value1, value2, "androidCumulativeAdd");
            return this;


        }

        public Criteria andAndroidCumulativeAddNotBetween(Long value1, Long value2) {
            addCriterion("android_cumulative_add not between", value1, value2, "androidCumulativeAdd");
            return this;


        }

        public Criteria andDayCumulativeSumIsNull() {
            addCriterion("day_cumulative_sum is null");
            return this;


        }

        public Criteria andDayCumulativeSumIsNotNull() {
            addCriterion("day_cumulative_sum is not null");
            return this;


        }

        public Criteria andDayCumulativeSumEqualTo(Long value) {
            addCriterion("day_cumulative_sum =", value, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumNotEqualTo(Long value) {
            addCriterion("day_cumulative_sum <>", value, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumGreaterThan(Long value) {
            addCriterion("day_cumulative_sum >", value, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumGreaterThanOrEqualTo(Long value) {
            addCriterion("day_cumulative_sum >=", value, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumLessThan(Long value) {
            addCriterion("day_cumulative_sum <", value, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumLessThanOrEqualTo(Long value) {
            addCriterion("day_cumulative_sum <=", value, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumIn(List values) {
            addCriterion("day_cumulative_sum in", values, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumNotIn(List values) {
            addCriterion("day_cumulative_sum not in", values, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumBetween(Long value1, Long value2) {
            addCriterion("day_cumulative_sum between", value1, value2, "dayCumulativeSum");
            return this;


        }

        public Criteria andDayCumulativeSumNotBetween(Long value1, Long value2) {
            addCriterion("day_cumulative_sum not between", value1, value2, "dayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumIsNull() {
            addCriterion("yesterday_cumulative_sum is null");
            return this;


        }

        public Criteria andYesterdayCumulativeSumIsNotNull() {
            addCriterion("yesterday_cumulative_sum is not null");
            return this;


        }

        public Criteria andYesterdayCumulativeSumEqualTo(Long value) {
            addCriterion("yesterday_cumulative_sum =", value, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumNotEqualTo(Long value) {
            addCriterion("yesterday_cumulative_sum <>", value, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumGreaterThan(Long value) {
            addCriterion("yesterday_cumulative_sum >", value, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumGreaterThanOrEqualTo(Long value) {
            addCriterion("yesterday_cumulative_sum >=", value, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumLessThan(Long value) {
            addCriterion("yesterday_cumulative_sum <", value, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumLessThanOrEqualTo(Long value) {
            addCriterion("yesterday_cumulative_sum <=", value, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumIn(List values) {
            addCriterion("yesterday_cumulative_sum in", values, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumNotIn(List values) {
            addCriterion("yesterday_cumulative_sum not in", values, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumBetween(Long value1, Long value2) {
            addCriterion("yesterday_cumulative_sum between", value1, value2, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andYesterdayCumulativeSumNotBetween(Long value1, Long value2) {
            addCriterion("yesterday_cumulative_sum not between", value1, value2, "yesterdayCumulativeSum");
            return this;


        }

        public Criteria andDayActiveIsNull() {
            addCriterion("day_active is null");
            return this;


        }

        public Criteria andDayActiveIsNotNull() {
            addCriterion("day_active is not null");
            return this;


        }

        public Criteria andDayActiveEqualTo(Integer value) {
            addCriterion("day_active =", value, "dayActive");
            return this;


        }

        public Criteria andDayActiveNotEqualTo(Integer value) {
            addCriterion("day_active <>", value, "dayActive");
            return this;


        }

        public Criteria andDayActiveGreaterThan(Integer value) {
            addCriterion("day_active >", value, "dayActive");
            return this;


        }

        public Criteria andDayActiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_active >=", value, "dayActive");
            return this;


        }

        public Criteria andDayActiveLessThan(Integer value) {
            addCriterion("day_active <", value, "dayActive");
            return this;


        }

        public Criteria andDayActiveLessThanOrEqualTo(Integer value) {
            addCriterion("day_active <=", value, "dayActive");
            return this;


        }

        public Criteria andDayActiveIn(List values) {
            addCriterion("day_active in", values, "dayActive");
            return this;


        }

        public Criteria andDayActiveNotIn(List values) {
            addCriterion("day_active not in", values, "dayActive");
            return this;


        }

        public Criteria andDayActiveBetween(Integer value1, Integer value2) {
            addCriterion("day_active between", value1, value2, "dayActive");
            return this;


        }

        public Criteria andDayActiveNotBetween(Integer value1, Integer value2) {
            addCriterion("day_active not between", value1, value2, "dayActive");
            return this;


        }

        public Criteria andPcDaypayAddIsNull() {
            addCriterion("pc_daypay_add is null");
            return this;


        }

        public Criteria andPcDaypayAddIsNotNull() {
            addCriterion("pc_daypay_add is not null");
            return this;


        }

        public Criteria andPcDaypayAddEqualTo(Integer value) {
            addCriterion("pc_daypay_add =", value, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddNotEqualTo(Integer value) {
            addCriterion("pc_daypay_add <>", value, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddGreaterThan(Integer value) {
            addCriterion("pc_daypay_add >", value, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddGreaterThanOrEqualTo(Integer value) {
            addCriterion("pc_daypay_add >=", value, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddLessThan(Integer value) {
            addCriterion("pc_daypay_add <", value, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddLessThanOrEqualTo(Integer value) {
            addCriterion("pc_daypay_add <=", value, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddIn(List values) {
            addCriterion("pc_daypay_add in", values, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddNotIn(List values) {
            addCriterion("pc_daypay_add not in", values, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddBetween(Integer value1, Integer value2) {
            addCriterion("pc_daypay_add between", value1, value2, "pcDaypayAdd");
            return this;


        }

        public Criteria andPcDaypayAddNotBetween(Integer value1, Integer value2) {
            addCriterion("pc_daypay_add not between", value1, value2, "pcDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddIsNull() {
            addCriterion("wap_daypay_add is null");
            return this;


        }

        public Criteria andWapDaypayAddIsNotNull() {
            addCriterion("wap_daypay_add is not null");
            return this;


        }

        public Criteria andWapDaypayAddEqualTo(Integer value) {
            addCriterion("wap_daypay_add =", value, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddNotEqualTo(Integer value) {
            addCriterion("wap_daypay_add <>", value, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddGreaterThan(Integer value) {
            addCriterion("wap_daypay_add >", value, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddGreaterThanOrEqualTo(Integer value) {
            addCriterion("wap_daypay_add >=", value, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddLessThan(Integer value) {
            addCriterion("wap_daypay_add <", value, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddLessThanOrEqualTo(Integer value) {
            addCriterion("wap_daypay_add <=", value, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddIn(List values) {
            addCriterion("wap_daypay_add in", values, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddNotIn(List values) {
            addCriterion("wap_daypay_add not in", values, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddBetween(Integer value1, Integer value2) {
            addCriterion("wap_daypay_add between", value1, value2, "wapDaypayAdd");
            return this;


        }

        public Criteria andWapDaypayAddNotBetween(Integer value1, Integer value2) {
            addCriterion("wap_daypay_add not between", value1, value2, "wapDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddIsNull() {
            addCriterion("android_daypay_add is null");
            return this;


        }

        public Criteria andAndroidDaypayAddIsNotNull() {
            addCriterion("android_daypay_add is not null");
            return this;


        }

        public Criteria andAndroidDaypayAddEqualTo(Integer value) {
            addCriterion("android_daypay_add =", value, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddNotEqualTo(Integer value) {
            addCriterion("android_daypay_add <>", value, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddGreaterThan(Integer value) {
            addCriterion("android_daypay_add >", value, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddGreaterThanOrEqualTo(Integer value) {
            addCriterion("android_daypay_add >=", value, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddLessThan(Integer value) {
            addCriterion("android_daypay_add <", value, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddLessThanOrEqualTo(Integer value) {
            addCriterion("android_daypay_add <=", value, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddIn(List values) {
            addCriterion("android_daypay_add in", values, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddNotIn(List values) {
            addCriterion("android_daypay_add not in", values, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddBetween(Integer value1, Integer value2) {
            addCriterion("android_daypay_add between", value1, value2, "androidDaypayAdd");
            return this;


        }

        public Criteria andAndroidDaypayAddNotBetween(Integer value1, Integer value2) {
            addCriterion("android_daypay_add not between", value1, value2, "androidDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddIsNull() {
            addCriterion("ios_daypay_add is null");
            return this;


        }

        public Criteria andIosDaypayAddIsNotNull() {
            addCriterion("ios_daypay_add is not null");
            return this;


        }

        public Criteria andIosDaypayAddEqualTo(Integer value) {
            addCriterion("ios_daypay_add =", value, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddNotEqualTo(Integer value) {
            addCriterion("ios_daypay_add <>", value, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddGreaterThan(Integer value) {
            addCriterion("ios_daypay_add >", value, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddGreaterThanOrEqualTo(Integer value) {
            addCriterion("ios_daypay_add >=", value, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddLessThan(Integer value) {
            addCriterion("ios_daypay_add <", value, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddLessThanOrEqualTo(Integer value) {
            addCriterion("ios_daypay_add <=", value, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddIn(List values) {
            addCriterion("ios_daypay_add in", values, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddNotIn(List values) {
            addCriterion("ios_daypay_add not in", values, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddBetween(Integer value1, Integer value2) {
            addCriterion("ios_daypay_add between", value1, value2, "iosDaypayAdd");
            return this;


        }

        public Criteria andIosDaypayAddNotBetween(Integer value1, Integer value2) {
            addCriterion("ios_daypay_add not between", value1, value2, "iosDaypayAdd");
            return this;


        }

        public Criteria andDaypaySumIsNull() {
            addCriterion("daypay_sum is null");
            return this;


        }

        public Criteria andDaypaySumIsNotNull() {
            addCriterion("daypay_sum is not null");
            return this;


        }

        public Criteria andDaypaySumEqualTo(Integer value) {
            addCriterion("daypay_sum =", value, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumNotEqualTo(Integer value) {
            addCriterion("daypay_sum <>", value, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumGreaterThan(Integer value) {
            addCriterion("daypay_sum >", value, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumGreaterThanOrEqualTo(Integer value) {
            addCriterion("daypay_sum >=", value, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumLessThan(Integer value) {
            addCriterion("daypay_sum <", value, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumLessThanOrEqualTo(Integer value) {
            addCriterion("daypay_sum <=", value, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumIn(List values) {
            addCriterion("daypay_sum in", values, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumNotIn(List values) {
            addCriterion("daypay_sum not in", values, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumBetween(Integer value1, Integer value2) {
            addCriterion("daypay_sum between", value1, value2, "daypaySum");
            return this;


        }

        public Criteria andDaypaySumNotBetween(Integer value1, Integer value2) {
            addCriterion("daypay_sum not between", value1, value2, "daypaySum");
            return this;


        }


    }


}
