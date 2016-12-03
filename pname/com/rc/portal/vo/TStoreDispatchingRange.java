package com.rc.portal.vo;

import java.util.Date;

public class TStoreDispatchingRange {
    private Long id;

    private String latLonRange;

    private String storeLatLon;

    private String storeHdId;

    private String name;

    private String address;

    private Date createDt;

    private Integer sort;

    private Date startDt;

    private Date endDt;

    private Long areaCityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatLonRange() {
        return latLonRange;
    }

    public void setLatLonRange(String latLonRange) {
        this.latLonRange = latLonRange;
    }

    public String getStoreLatLon() {
        return storeLatLon;
    }

    public void setStoreLatLon(String storeLatLon) {
        this.storeLatLon = storeLatLon;
    }

    public String getStoreHdId() {
        return storeHdId;
    }

    public void setStoreHdId(String storeHdId) {
        this.storeHdId = storeHdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public Long getAreaCityId() {
        return areaCityId;
    }

    public void setAreaCityId(Long areaCityId) {
        this.areaCityId = areaCityId;
    }
}