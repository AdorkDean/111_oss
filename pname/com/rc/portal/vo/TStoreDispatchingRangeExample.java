package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TStoreDispatchingRangeExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TStoreDispatchingRangeExample() {
        oredCriteria = new ArrayList();


    }

    protected TStoreDispatchingRangeExample(TStoreDispatchingRangeExample example) {
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

        public Criteria andLatLonRangeIsNull() {
            addCriterion("lat_lon_range is null");
            return this;


        }

        public Criteria andLatLonRangeIsNotNull() {
            addCriterion("lat_lon_range is not null");
            return this;


        }

        public Criteria andLatLonRangeEqualTo(String value) {
            addCriterion("lat_lon_range =", value, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeNotEqualTo(String value) {
            addCriterion("lat_lon_range <>", value, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeGreaterThan(String value) {
            addCriterion("lat_lon_range >", value, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeGreaterThanOrEqualTo(String value) {
            addCriterion("lat_lon_range >=", value, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeLessThan(String value) {
            addCriterion("lat_lon_range <", value, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeLessThanOrEqualTo(String value) {
            addCriterion("lat_lon_range <=", value, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeLike(String value) {
            addCriterion("lat_lon_range like", value, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeNotLike(String value) {
            addCriterion("lat_lon_range not like", value, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeIn(List values) {
            addCriterion("lat_lon_range in", values, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeNotIn(List values) {
            addCriterion("lat_lon_range not in", values, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeBetween(String value1, String value2) {
            addCriterion("lat_lon_range between", value1, value2, "latLonRange");
            return this;


        }

        public Criteria andLatLonRangeNotBetween(String value1, String value2) {
            addCriterion("lat_lon_range not between", value1, value2, "latLonRange");
            return this;


        }

        public Criteria andStoreLatLonIsNull() {
            addCriterion("store_lat_lon is null");
            return this;


        }

        public Criteria andStoreLatLonIsNotNull() {
            addCriterion("store_lat_lon is not null");
            return this;


        }

        public Criteria andStoreLatLonEqualTo(String value) {
            addCriterion("store_lat_lon =", value, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonNotEqualTo(String value) {
            addCriterion("store_lat_lon <>", value, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonGreaterThan(String value) {
            addCriterion("store_lat_lon >", value, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonGreaterThanOrEqualTo(String value) {
            addCriterion("store_lat_lon >=", value, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonLessThan(String value) {
            addCriterion("store_lat_lon <", value, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonLessThanOrEqualTo(String value) {
            addCriterion("store_lat_lon <=", value, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonLike(String value) {
            addCriterion("store_lat_lon like", value, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonNotLike(String value) {
            addCriterion("store_lat_lon not like", value, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonIn(List values) {
            addCriterion("store_lat_lon in", values, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonNotIn(List values) {
            addCriterion("store_lat_lon not in", values, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonBetween(String value1, String value2) {
            addCriterion("store_lat_lon between", value1, value2, "storeLatLon");
            return this;


        }

        public Criteria andStoreLatLonNotBetween(String value1, String value2) {
            addCriterion("store_lat_lon not between", value1, value2, "storeLatLon");
            return this;


        }

        public Criteria andStoreHdIdIsNull() {
            addCriterion("store_hd_id is null");
            return this;


        }

        public Criteria andStoreHdIdIsNotNull() {
            addCriterion("store_hd_id is not null");
            return this;


        }

        public Criteria andStoreHdIdEqualTo(String value) {
            addCriterion("store_hd_id =", value, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdNotEqualTo(String value) {
            addCriterion("store_hd_id <>", value, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdGreaterThan(String value) {
            addCriterion("store_hd_id >", value, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdGreaterThanOrEqualTo(String value) {
            addCriterion("store_hd_id >=", value, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdLessThan(String value) {
            addCriterion("store_hd_id <", value, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdLessThanOrEqualTo(String value) {
            addCriterion("store_hd_id <=", value, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdLike(String value) {
            addCriterion("store_hd_id like", value, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdNotLike(String value) {
            addCriterion("store_hd_id not like", value, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdIn(List values) {
            addCriterion("store_hd_id in", values, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdNotIn(List values) {
            addCriterion("store_hd_id not in", values, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdBetween(String value1, String value2) {
            addCriterion("store_hd_id between", value1, value2, "storeHdId");
            return this;


        }

        public Criteria andStoreHdIdNotBetween(String value1, String value2) {
            addCriterion("store_hd_id not between", value1, value2, "storeHdId");
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return this;


        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return this;


        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return this;


        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return this;


        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return this;


        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return this;


        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return this;


        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return this;


        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return this;


        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return this;


        }

        public Criteria andAddressIn(List values) {
            addCriterion("address in", values, "address");
            return this;


        }

        public Criteria andAddressNotIn(List values) {
            addCriterion("address not in", values, "address");
            return this;


        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return this;


        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return this;


        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return this;


        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return this;


        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return this;


        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return this;


        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return this;


        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return this;


        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return this;


        }

        public Criteria andSortIn(List values) {
            addCriterion("sort in", values, "sort");
            return this;


        }

        public Criteria andSortNotIn(List values) {
            addCriterion("sort not in", values, "sort");
            return this;


        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return this;


        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return this;


        }

        public Criteria andStartDtIsNull() {
            addCriterion("start_dt is null");
            return this;


        }

        public Criteria andStartDtIsNotNull() {
            addCriterion("start_dt is not null");
            return this;


        }

        public Criteria andStartDtEqualTo(Date value) {
            addCriterion("start_dt =", value, "startDt");
            return this;


        }

        public Criteria andStartDtNotEqualTo(Date value) {
            addCriterion("start_dt <>", value, "startDt");
            return this;


        }

        public Criteria andStartDtGreaterThan(Date value) {
            addCriterion("start_dt >", value, "startDt");
            return this;


        }

        public Criteria andStartDtGreaterThanOrEqualTo(Date value) {
            addCriterion("start_dt >=", value, "startDt");
            return this;


        }

        public Criteria andStartDtLessThan(Date value) {
            addCriterion("start_dt <", value, "startDt");
            return this;


        }

        public Criteria andStartDtLessThanOrEqualTo(Date value) {
            addCriterion("start_dt <=", value, "startDt");
            return this;


        }

        public Criteria andStartDtIn(List values) {
            addCriterion("start_dt in", values, "startDt");
            return this;


        }

        public Criteria andStartDtNotIn(List values) {
            addCriterion("start_dt not in", values, "startDt");
            return this;


        }

        public Criteria andStartDtBetween(Date value1, Date value2) {
            addCriterion("start_dt between", value1, value2, "startDt");
            return this;


        }

        public Criteria andStartDtNotBetween(Date value1, Date value2) {
            addCriterion("start_dt not between", value1, value2, "startDt");
            return this;


        }

        public Criteria andEndDtIsNull() {
            addCriterion("end_dt is null");
            return this;


        }

        public Criteria andEndDtIsNotNull() {
            addCriterion("end_dt is not null");
            return this;


        }

        public Criteria andEndDtEqualTo(Date value) {
            addCriterion("end_dt =", value, "endDt");
            return this;


        }

        public Criteria andEndDtNotEqualTo(Date value) {
            addCriterion("end_dt <>", value, "endDt");
            return this;


        }

        public Criteria andEndDtGreaterThan(Date value) {
            addCriterion("end_dt >", value, "endDt");
            return this;


        }

        public Criteria andEndDtGreaterThanOrEqualTo(Date value) {
            addCriterion("end_dt >=", value, "endDt");
            return this;


        }

        public Criteria andEndDtLessThan(Date value) {
            addCriterion("end_dt <", value, "endDt");
            return this;


        }

        public Criteria andEndDtLessThanOrEqualTo(Date value) {
            addCriterion("end_dt <=", value, "endDt");
            return this;


        }

        public Criteria andEndDtIn(List values) {
            addCriterion("end_dt in", values, "endDt");
            return this;


        }

        public Criteria andEndDtNotIn(List values) {
            addCriterion("end_dt not in", values, "endDt");
            return this;


        }

        public Criteria andEndDtBetween(Date value1, Date value2) {
            addCriterion("end_dt between", value1, value2, "endDt");
            return this;


        }

        public Criteria andEndDtNotBetween(Date value1, Date value2) {
            addCriterion("end_dt not between", value1, value2, "endDt");
            return this;


        }

        public Criteria andAreaCityIdIsNull() {
            addCriterion("area_city_id is null");
            return this;


        }

        public Criteria andAreaCityIdIsNotNull() {
            addCriterion("area_city_id is not null");
            return this;


        }

        public Criteria andAreaCityIdEqualTo(Long value) {
            addCriterion("area_city_id =", value, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdNotEqualTo(Long value) {
            addCriterion("area_city_id <>", value, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdGreaterThan(Long value) {
            addCriterion("area_city_id >", value, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("area_city_id >=", value, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdLessThan(Long value) {
            addCriterion("area_city_id <", value, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdLessThanOrEqualTo(Long value) {
            addCriterion("area_city_id <=", value, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdIn(List values) {
            addCriterion("area_city_id in", values, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdNotIn(List values) {
            addCriterion("area_city_id not in", values, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdBetween(Long value1, Long value2) {
            addCriterion("area_city_id between", value1, value2, "areaCityId");
            return this;


        }

        public Criteria andAreaCityIdNotBetween(Long value1, Long value2) {
            addCriterion("area_city_id not between", value1, value2, "areaCityId");
            return this;


        }


    }


}
