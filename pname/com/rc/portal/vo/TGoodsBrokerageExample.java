package com.rc.portal.vo;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TGoodsBrokerageExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TGoodsBrokerageExample() {
        oredCriteria = new ArrayList();


    }

    protected TGoodsBrokerageExample(TGoodsBrokerageExample example) {
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

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return this;


        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return this;


        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goods_id =", value, "goodsId");
            return this;


        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goods_id <>", value, "goodsId");
            return this;


        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goods_id >", value, "goodsId");
            return this;


        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_id >=", value, "goodsId");
            return this;


        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goods_id <", value, "goodsId");
            return this;


        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_id <=", value, "goodsId");
            return this;


        }

        public Criteria andGoodsIdIn(List values) {
            addCriterion("goods_id in", values, "goodsId");
            return this;


        }

        public Criteria andGoodsIdNotIn(List values) {
            addCriterion("goods_id not in", values, "goodsId");
            return this;


        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return this;


        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return this;


        }

        public Criteria andBrokerageIsNull() {
            addCriterion("brokerage is null");
            return this;


        }

        public Criteria andBrokerageIsNotNull() {
            addCriterion("brokerage is not null");
            return this;


        }

        public Criteria andBrokerageEqualTo(Long value) {
            addCriterion("brokerage =", value, "brokerage");
            return this;


        }

        public Criteria andBrokerageNotEqualTo(Long value) {
            addCriterion("brokerage <>", value, "brokerage");
            return this;


        }

        public Criteria andBrokerageGreaterThan(Long value) {
            addCriterion("brokerage >", value, "brokerage");
            return this;


        }

        public Criteria andBrokerageGreaterThanOrEqualTo(Long value) {
            addCriterion("brokerage >=", value, "brokerage");
            return this;


        }

        public Criteria andBrokerageLessThan(Long value) {
            addCriterion("brokerage <", value, "brokerage");
            return this;


        }

        public Criteria andBrokerageLessThanOrEqualTo(Long value) {
            addCriterion("brokerage <=", value, "brokerage");
            return this;


        }

        public Criteria andBrokerageIn(List values) {
            addCriterion("brokerage in", values, "brokerage");
            return this;


        }

        public Criteria andBrokerageNotIn(List values) {
            addCriterion("brokerage not in", values, "brokerage");
            return this;


        }

        public Criteria andBrokerageBetween(Long value1, Long value2) {
            addCriterion("brokerage between", value1, value2, "brokerage");
            return this;


        }

        public Criteria andBrokerageNotBetween(Long value1, Long value2) {
            addCriterion("brokerage not between", value1, value2, "brokerage");
            return this;


        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return this;


        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return this;


        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return this;


        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return this;


        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return this;


        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return this;


        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return this;


        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return this;


        }

        public Criteria andWeightIn(List values) {
            addCriterion("weight in", values, "weight");
            return this;


        }

        public Criteria andWeightNotIn(List values) {
            addCriterion("weight not in", values, "weight");
            return this;


        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return this;


        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return this;


        }

        public Criteria andRebateAmountIsNull() {
            addCriterion("rebate_amount is null");
            return this;


        }

        public Criteria andRebateAmountIsNotNull() {
            addCriterion("rebate_amount is not null");
            return this;


        }

        public Criteria andRebateAmountEqualTo(BigDecimal value) {
            addCriterion("rebate_amount =", value, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountNotEqualTo(BigDecimal value) {
            addCriterion("rebate_amount <>", value, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountGreaterThan(BigDecimal value) {
            addCriterion("rebate_amount >", value, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_amount >=", value, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountLessThan(BigDecimal value) {
            addCriterion("rebate_amount <", value, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate_amount <=", value, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountIn(List values) {
            addCriterion("rebate_amount in", values, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountNotIn(List values) {
            addCriterion("rebate_amount not in", values, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_amount between", value1, value2, "rebateAmount");
            return this;


        }

        public Criteria andRebateAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate_amount not between", value1, value2, "rebateAmount");
            return this;


        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return this;


        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return this;


        }

        public Criteria andAuditStatusEqualTo(Integer value) {
            addCriterion("audit_status =", value, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusNotEqualTo(Integer value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusGreaterThan(Integer value) {
            addCriterion("audit_status >", value, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusLessThan(Integer value) {
            addCriterion("audit_status <", value, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusLessThanOrEqualTo(Integer value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusIn(List values) {
            addCriterion("audit_status in", values, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusNotIn(List values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusBetween(Integer value1, Integer value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return this;


        }

        public Criteria andAuditStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
            return this;


        }

        public Criteria andAuditDtIsNull() {
            addCriterion("audit_dt is null");
            return this;


        }

        public Criteria andAuditDtIsNotNull() {
            addCriterion("audit_dt is not null");
            return this;


        }

        public Criteria andAuditDtEqualTo(Date value) {
            addCriterion("audit_dt =", value, "auditDt");
            return this;


        }

        public Criteria andAuditDtNotEqualTo(Date value) {
            addCriterion("audit_dt <>", value, "auditDt");
            return this;


        }

        public Criteria andAuditDtGreaterThan(Date value) {
            addCriterion("audit_dt >", value, "auditDt");
            return this;


        }

        public Criteria andAuditDtGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_dt >=", value, "auditDt");
            return this;


        }

        public Criteria andAuditDtLessThan(Date value) {
            addCriterion("audit_dt <", value, "auditDt");
            return this;


        }

        public Criteria andAuditDtLessThanOrEqualTo(Date value) {
            addCriterion("audit_dt <=", value, "auditDt");
            return this;


        }

        public Criteria andAuditDtIn(List values) {
            addCriterion("audit_dt in", values, "auditDt");
            return this;


        }

        public Criteria andAuditDtNotIn(List values) {
            addCriterion("audit_dt not in", values, "auditDt");
            return this;


        }

        public Criteria andAuditDtBetween(Date value1, Date value2) {
            addCriterion("audit_dt between", value1, value2, "auditDt");
            return this;


        }

        public Criteria andAuditDtNotBetween(Date value1, Date value2) {
            addCriterion("audit_dt not between", value1, value2, "auditDt");
            return this;


        }

        public Criteria andAuditRemarkIsNull() {
            addCriterion("audit_remark is null");
            return this;


        }

        public Criteria andAuditRemarkIsNotNull() {
            addCriterion("audit_remark is not null");
            return this;


        }

        public Criteria andAuditRemarkEqualTo(String value) {
            addCriterion("audit_remark =", value, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkNotEqualTo(String value) {
            addCriterion("audit_remark <>", value, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkGreaterThan(String value) {
            addCriterion("audit_remark >", value, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("audit_remark >=", value, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkLessThan(String value) {
            addCriterion("audit_remark <", value, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkLessThanOrEqualTo(String value) {
            addCriterion("audit_remark <=", value, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkLike(String value) {
            addCriterion("audit_remark like", value, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkNotLike(String value) {
            addCriterion("audit_remark not like", value, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkIn(List values) {
            addCriterion("audit_remark in", values, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkNotIn(List values) {
            addCriterion("audit_remark not in", values, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkBetween(String value1, String value2) {
            addCriterion("audit_remark between", value1, value2, "auditRemark");
            return this;


        }

        public Criteria andAuditRemarkNotBetween(String value1, String value2) {
            addCriterion("audit_remark not between", value1, value2, "auditRemark");
            return this;


        }

        public Criteria andRemakIsNull() {
            addCriterion("remak is null");
            return this;


        }

        public Criteria andRemakIsNotNull() {
            addCriterion("remak is not null");
            return this;


        }

        public Criteria andRemakEqualTo(String value) {
            addCriterion("remak =", value, "remak");
            return this;


        }

        public Criteria andRemakNotEqualTo(String value) {
            addCriterion("remak <>", value, "remak");
            return this;


        }

        public Criteria andRemakGreaterThan(String value) {
            addCriterion("remak >", value, "remak");
            return this;


        }

        public Criteria andRemakGreaterThanOrEqualTo(String value) {
            addCriterion("remak >=", value, "remak");
            return this;


        }

        public Criteria andRemakLessThan(String value) {
            addCriterion("remak <", value, "remak");
            return this;


        }

        public Criteria andRemakLessThanOrEqualTo(String value) {
            addCriterion("remak <=", value, "remak");
            return this;


        }

        public Criteria andRemakLike(String value) {
            addCriterion("remak like", value, "remak");
            return this;


        }

        public Criteria andRemakNotLike(String value) {
            addCriterion("remak not like", value, "remak");
            return this;


        }

        public Criteria andRemakIn(List values) {
            addCriterion("remak in", values, "remak");
            return this;


        }

        public Criteria andRemakNotIn(List values) {
            addCriterion("remak not in", values, "remak");
            return this;


        }

        public Criteria andRemakBetween(String value1, String value2) {
            addCriterion("remak between", value1, value2, "remak");
            return this;


        }

        public Criteria andRemakNotBetween(String value1, String value2) {
            addCriterion("remak not between", value1, value2, "remak");
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

        public Criteria andApplyUserIsNull() {
            addCriterion("apply_user is null");
            return this;


        }

        public Criteria andApplyUserIsNotNull() {
            addCriterion("apply_user is not null");
            return this;


        }

        public Criteria andApplyUserEqualTo(String value) {
            addCriterion("apply_user =", value, "applyUser");
            return this;


        }

        public Criteria andApplyUserNotEqualTo(String value) {
            addCriterion("apply_user <>", value, "applyUser");
            return this;


        }

        public Criteria andApplyUserGreaterThan(String value) {
            addCriterion("apply_user >", value, "applyUser");
            return this;


        }

        public Criteria andApplyUserGreaterThanOrEqualTo(String value) {
            addCriterion("apply_user >=", value, "applyUser");
            return this;


        }

        public Criteria andApplyUserLessThan(String value) {
            addCriterion("apply_user <", value, "applyUser");
            return this;


        }

        public Criteria andApplyUserLessThanOrEqualTo(String value) {
            addCriterion("apply_user <=", value, "applyUser");
            return this;


        }

        public Criteria andApplyUserLike(String value) {
            addCriterion("apply_user like", value, "applyUser");
            return this;


        }

        public Criteria andApplyUserNotLike(String value) {
            addCriterion("apply_user not like", value, "applyUser");
            return this;


        }

        public Criteria andApplyUserIn(List values) {
            addCriterion("apply_user in", values, "applyUser");
            return this;


        }

        public Criteria andApplyUserNotIn(List values) {
            addCriterion("apply_user not in", values, "applyUser");
            return this;


        }

        public Criteria andApplyUserBetween(String value1, String value2) {
            addCriterion("apply_user between", value1, value2, "applyUser");
            return this;


        }

        public Criteria andApplyUserNotBetween(String value1, String value2) {
            addCriterion("apply_user not between", value1, value2, "applyUser");
            return this;


        }

        public Criteria andApplyDtIsNull() {
            addCriterion("apply_dt is null");
            return this;


        }

        public Criteria andApplyDtIsNotNull() {
            addCriterion("apply_dt is not null");
            return this;


        }

        public Criteria andApplyDtEqualTo(Date value) {
            addCriterion("apply_dt =", value, "applyDt");
            return this;


        }

        public Criteria andApplyDtNotEqualTo(Date value) {
            addCriterion("apply_dt <>", value, "applyDt");
            return this;


        }

        public Criteria andApplyDtGreaterThan(Date value) {
            addCriterion("apply_dt >", value, "applyDt");
            return this;


        }

        public Criteria andApplyDtGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_dt >=", value, "applyDt");
            return this;


        }

        public Criteria andApplyDtLessThan(Date value) {
            addCriterion("apply_dt <", value, "applyDt");
            return this;


        }

        public Criteria andApplyDtLessThanOrEqualTo(Date value) {
            addCriterion("apply_dt <=", value, "applyDt");
            return this;


        }

        public Criteria andApplyDtIn(List values) {
            addCriterion("apply_dt in", values, "applyDt");
            return this;


        }

        public Criteria andApplyDtNotIn(List values) {
            addCriterion("apply_dt not in", values, "applyDt");
            return this;


        }

        public Criteria andApplyDtBetween(Date value1, Date value2) {
            addCriterion("apply_dt between", value1, value2, "applyDt");
            return this;


        }

        public Criteria andApplyDtNotBetween(Date value1, Date value2) {
            addCriterion("apply_dt not between", value1, value2, "applyDt");
            return this;


        }


    }


}
