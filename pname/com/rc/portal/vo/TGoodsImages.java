package com.rc.portal.vo;

public class TGoodsImages {
    private Long id;
    /**
     * 商品id
     */
    private Long goodsid;
    
    /**
	 * 标题
	 */
    private String titel;
    /**
     * 原图路径
     */
    private String artworkUrl;
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 应用类型 1 pc  2 ios  3  安卓
     */
    private Integer userType;
    /**
     * 是否默认 0 否 1 是
     */
    private Integer isdefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }
}