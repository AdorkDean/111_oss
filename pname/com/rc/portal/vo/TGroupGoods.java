package com.rc.portal.vo;

public class TGroupGoods {
    private Long id;
    /**
     * 套餐id（商品id）
     */
    private Long groupId;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 是否主商品 0 否 1 是
     */
    private Integer mainGoods;
    /**
     * (主从)商品id
     */
    private Long goodsid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getMainGoods() {
        return mainGoods;
    }

    public void setMainGoods(Integer mainGoods) {
        this.mainGoods = mainGoods;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }
}