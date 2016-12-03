package com.rc.portal.vo;

import java.util.Date;

public class TCategoryImg {
    private Long id;

    private Long categoryId;

    private String imgUrl;

    private Integer isShow;

    private Integer isDelete;

    private Integer isWap;

    private Integer isApp;

    private Integer isPc;

    private Date createDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsWap() {
        return isWap;
    }

    public void setIsWap(Integer isWap) {
        this.isWap = isWap;
    }

    public Integer getIsApp() {
        return isApp;
    }

    public void setIsApp(Integer isApp) {
        this.isApp = isApp;
    }

    public Integer getIsPc() {
        return isPc;
    }

    public void setIsPc(Integer isPc) {
        this.isPc = isPc;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }
}