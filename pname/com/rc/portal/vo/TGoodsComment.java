package com.rc.portal.vo;

import java.util.Date;

import javax.persistence.Transient;

/**
 * 商品评论实体
 * 
 * @author user00
 * @modifyTime 2015-8-11 下午5:10:57
 */
public class TGoodsComment {

	private Long id;

	/**
	 * 总评分
	 */
	private Integer sumFraction;

	/**
	 * 类型 1 好评 2 中评 3 差评
	 */
	private Integer type;

	/**
	 * 卖家好评
	 */
	private Integer seller;

	/**
	 * 快递好评
	 */
	private Integer fastMail;

	/**
	 * 商品好评
	 */
	private Integer goods;

	/**
	 * 快递员好评
	 */
	private Integer fastMailPeople;

	/**
	 * 是否显示
	 */
	private Integer isShow;

	/**
	 * 评论内容
	 */
	private String comment;

	/**
	 * 会员id
	 */
	private Long memberId;

	/**
	 * 商品id
	 */
	private Long goodsId;

	/**
	 * 订单Id
	 */
	private Long orderId;

	/**
	 * 商品评论时间
	 */
	private Date createTime;

	/**
	 * 用户名(非数据库字段)
	 */
	private String userName;

	/**
	 * 商品名称(非数据库字段)
	 */
	private String goodsName;

	/**
	 * 订单编号(非数据库字段)
	 */
	private String orderSn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSumFraction() {
		return sumFraction;
	}

	public void setSumFraction(Integer sumFraction) {
		this.sumFraction = sumFraction;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSeller() {
		return seller;
	}

	public void setSeller(Integer seller) {
		this.seller = seller;
	}

	public Integer getFastMail() {
		return fastMail;
	}

	public void setFastMail(Integer fastMail) {
		this.fastMail = fastMail;
	}

	public Integer getGoods() {
		return goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public Integer getFastMailPeople() {
		return fastMailPeople;
	}

	public void setFastMailPeople(Integer fastMailPeople) {
		this.fastMailPeople = fastMailPeople;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Transient
	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

}