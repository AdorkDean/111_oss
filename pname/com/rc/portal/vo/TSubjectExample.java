package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TSubjectExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TSubjectExample() {
        oredCriteria = new ArrayList();


    }

    protected TSubjectExample(TSubjectExample example) {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return this;


        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return this;


        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return this;


        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return this;


        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return this;


        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return this;


        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return this;


        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return this;


        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return this;


        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return this;


        }

        public Criteria andNameIn(List values) {
            addCriterion("name in", values, "name");
            return this;


        }

        public Criteria andNameNotIn(List values) {
            addCriterion("name not in", values, "name");
            return this;


        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return this;


        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return this;


        }

        public Criteria andNameFileIsNull() {
            addCriterion("name_file is null");
            return this;


        }

        public Criteria andNameFileIsNotNull() {
            addCriterion("name_file is not null");
            return this;


        }

        public Criteria andNameFileEqualTo(String value) {
            addCriterion("name_file =", value, "nameFile");
            return this;


        }

        public Criteria andNameFileNotEqualTo(String value) {
            addCriterion("name_file <>", value, "nameFile");
            return this;


        }

        public Criteria andNameFileGreaterThan(String value) {
            addCriterion("name_file >", value, "nameFile");
            return this;


        }

        public Criteria andNameFileGreaterThanOrEqualTo(String value) {
            addCriterion("name_file >=", value, "nameFile");
            return this;


        }

        public Criteria andNameFileLessThan(String value) {
            addCriterion("name_file <", value, "nameFile");
            return this;


        }

        public Criteria andNameFileLessThanOrEqualTo(String value) {
            addCriterion("name_file <=", value, "nameFile");
            return this;


        }

        public Criteria andNameFileLike(String value) {
            addCriterion("name_file like", value, "nameFile");
            return this;


        }

        public Criteria andNameFileNotLike(String value) {
            addCriterion("name_file not like", value, "nameFile");
            return this;


        }

        public Criteria andNameFileIn(List values) {
            addCriterion("name_file in", values, "nameFile");
            return this;


        }

        public Criteria andNameFileNotIn(List values) {
            addCriterion("name_file not in", values, "nameFile");
            return this;


        }

        public Criteria andNameFileBetween(String value1, String value2) {
            addCriterion("name_file between", value1, value2, "nameFile");
            return this;


        }

        public Criteria andNameFileNotBetween(String value1, String value2) {
            addCriterion("name_file not between", value1, value2, "nameFile");
            return this;


        }

        public Criteria andTempletePathIsNull() {
            addCriterion("templete_path is null");
            return this;


        }

        public Criteria andTempletePathIsNotNull() {
            addCriterion("templete_path is not null");
            return this;


        }

        public Criteria andTempletePathEqualTo(String value) {
            addCriterion("templete_path =", value, "templetePath");
            return this;


        }

        public Criteria andTempletePathNotEqualTo(String value) {
            addCriterion("templete_path <>", value, "templetePath");
            return this;


        }

        public Criteria andTempletePathGreaterThan(String value) {
            addCriterion("templete_path >", value, "templetePath");
            return this;


        }

        public Criteria andTempletePathGreaterThanOrEqualTo(String value) {
            addCriterion("templete_path >=", value, "templetePath");
            return this;


        }

        public Criteria andTempletePathLessThan(String value) {
            addCriterion("templete_path <", value, "templetePath");
            return this;


        }

        public Criteria andTempletePathLessThanOrEqualTo(String value) {
            addCriterion("templete_path <=", value, "templetePath");
            return this;


        }

        public Criteria andTempletePathLike(String value) {
            addCriterion("templete_path like", value, "templetePath");
            return this;


        }

        public Criteria andTempletePathNotLike(String value) {
            addCriterion("templete_path not like", value, "templetePath");
            return this;


        }

        public Criteria andTempletePathIn(List values) {
            addCriterion("templete_path in", values, "templetePath");
            return this;


        }

        public Criteria andTempletePathNotIn(List values) {
            addCriterion("templete_path not in", values, "templetePath");
            return this;


        }

        public Criteria andTempletePathBetween(String value1, String value2) {
            addCriterion("templete_path between", value1, value2, "templetePath");
            return this;


        }

        public Criteria andTempletePathNotBetween(String value1, String value2) {
            addCriterion("templete_path not between", value1, value2, "templetePath");
            return this;


        }

        public Criteria andZtPathIsNull() {
            addCriterion("zt_path is null");
            return this;


        }

        public Criteria andZtPathIsNotNull() {
            addCriterion("zt_path is not null");
            return this;


        }

        public Criteria andZtPathEqualTo(String value) {
            addCriterion("zt_path =", value, "ztPath");
            return this;


        }

        public Criteria andZtPathNotEqualTo(String value) {
            addCriterion("zt_path <>", value, "ztPath");
            return this;


        }

        public Criteria andZtPathGreaterThan(String value) {
            addCriterion("zt_path >", value, "ztPath");
            return this;


        }

        public Criteria andZtPathGreaterThanOrEqualTo(String value) {
            addCriterion("zt_path >=", value, "ztPath");
            return this;


        }

        public Criteria andZtPathLessThan(String value) {
            addCriterion("zt_path <", value, "ztPath");
            return this;


        }

        public Criteria andZtPathLessThanOrEqualTo(String value) {
            addCriterion("zt_path <=", value, "ztPath");
            return this;


        }

        public Criteria andZtPathLike(String value) {
            addCriterion("zt_path like", value, "ztPath");
            return this;


        }

        public Criteria andZtPathNotLike(String value) {
            addCriterion("zt_path not like", value, "ztPath");
            return this;


        }

        public Criteria andZtPathIn(List values) {
            addCriterion("zt_path in", values, "ztPath");
            return this;


        }

        public Criteria andZtPathNotIn(List values) {
            addCriterion("zt_path not in", values, "ztPath");
            return this;


        }

        public Criteria andZtPathBetween(String value1, String value2) {
            addCriterion("zt_path between", value1, value2, "ztPath");
            return this;


        }

        public Criteria andZtPathNotBetween(String value1, String value2) {
            addCriterion("zt_path not between", value1, value2, "ztPath");
            return this;


        }

        public Criteria andPtypeIsNull() {
            addCriterion("ptype is null");
            return this;


        }

        public Criteria andPtypeIsNotNull() {
            addCriterion("ptype is not null");
            return this;


        }

        public Criteria andPtypeEqualTo(Integer value) {
            addCriterion("ptype =", value, "ptype");
            return this;


        }

        public Criteria andPtypeNotEqualTo(Integer value) {
            addCriterion("ptype <>", value, "ptype");
            return this;


        }

        public Criteria andPtypeGreaterThan(Integer value) {
            addCriterion("ptype >", value, "ptype");
            return this;


        }

        public Criteria andPtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ptype >=", value, "ptype");
            return this;


        }

        public Criteria andPtypeLessThan(Integer value) {
            addCriterion("ptype <", value, "ptype");
            return this;


        }

        public Criteria andPtypeLessThanOrEqualTo(Integer value) {
            addCriterion("ptype <=", value, "ptype");
            return this;


        }

        public Criteria andPtypeIn(List values) {
            addCriterion("ptype in", values, "ptype");
            return this;


        }

        public Criteria andPtypeNotIn(List values) {
            addCriterion("ptype not in", values, "ptype");
            return this;


        }

        public Criteria andPtypeBetween(Integer value1, Integer value2) {
            addCriterion("ptype between", value1, value2, "ptype");
            return this;


        }

        public Criteria andPtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ptype not between", value1, value2, "ptype");
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

        public Criteria andLastDtIsNull() {
            addCriterion("last_dt is null");
            return this;


        }

        public Criteria andLastDtIsNotNull() {
            addCriterion("last_dt is not null");
            return this;


        }

        public Criteria andLastDtEqualTo(Date value) {
            addCriterion("last_dt =", value, "lastDt");
            return this;


        }

        public Criteria andLastDtNotEqualTo(Date value) {
            addCriterion("last_dt <>", value, "lastDt");
            return this;


        }

        public Criteria andLastDtGreaterThan(Date value) {
            addCriterion("last_dt >", value, "lastDt");
            return this;


        }

        public Criteria andLastDtGreaterThanOrEqualTo(Date value) {
            addCriterion("last_dt >=", value, "lastDt");
            return this;


        }

        public Criteria andLastDtLessThan(Date value) {
            addCriterion("last_dt <", value, "lastDt");
            return this;


        }

        public Criteria andLastDtLessThanOrEqualTo(Date value) {
            addCriterion("last_dt <=", value, "lastDt");
            return this;


        }

        public Criteria andLastDtIn(List values) {
            addCriterion("last_dt in", values, "lastDt");
            return this;


        }

        public Criteria andLastDtNotIn(List values) {
            addCriterion("last_dt not in", values, "lastDt");
            return this;


        }

        public Criteria andLastDtBetween(Date value1, Date value2) {
            addCriterion("last_dt between", value1, value2, "lastDt");
            return this;


        }

        public Criteria andLastDtNotBetween(Date value1, Date value2) {
            addCriterion("last_dt not between", value1, value2, "lastDt");
            return this;


        }

        public Criteria andLastEditIsNull() {
            addCriterion("last_edit is null");
            return this;


        }

        public Criteria andLastEditIsNotNull() {
            addCriterion("last_edit is not null");
            return this;


        }

        public Criteria andLastEditEqualTo(String value) {
            addCriterion("last_edit =", value, "lastEdit");
            return this;


        }

        public Criteria andLastEditNotEqualTo(String value) {
            addCriterion("last_edit <>", value, "lastEdit");
            return this;


        }

        public Criteria andLastEditGreaterThan(String value) {
            addCriterion("last_edit >", value, "lastEdit");
            return this;


        }

        public Criteria andLastEditGreaterThanOrEqualTo(String value) {
            addCriterion("last_edit >=", value, "lastEdit");
            return this;


        }

        public Criteria andLastEditLessThan(String value) {
            addCriterion("last_edit <", value, "lastEdit");
            return this;


        }

        public Criteria andLastEditLessThanOrEqualTo(String value) {
            addCriterion("last_edit <=", value, "lastEdit");
            return this;


        }

        public Criteria andLastEditLike(String value) {
            addCriterion("last_edit like", value, "lastEdit");
            return this;


        }

        public Criteria andLastEditNotLike(String value) {
            addCriterion("last_edit not like", value, "lastEdit");
            return this;


        }

        public Criteria andLastEditIn(List values) {
            addCriterion("last_edit in", values, "lastEdit");
            return this;


        }

        public Criteria andLastEditNotIn(List values) {
            addCriterion("last_edit not in", values, "lastEdit");
            return this;


        }

        public Criteria andLastEditBetween(String value1, String value2) {
            addCriterion("last_edit between", value1, value2, "lastEdit");
            return this;


        }

        public Criteria andLastEditNotBetween(String value1, String value2) {
            addCriterion("last_edit not between", value1, value2, "lastEdit");
            return this;


        }


    }


}
