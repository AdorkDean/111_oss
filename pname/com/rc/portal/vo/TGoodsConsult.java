package com.rc.portal.vo;

import java.util.Date;

import javax.persistence.Transient;

/**
 * 咨询 实体
 * 
 * @author user00
 * @modifyTime 2015-8-11 下午6:47:28
 */
public class TGoodsConsult {

	private Long id;

	/**
	 * 商品id
	 */
	private Long goodsId;

	/**
	 * 会员id
	 */
	private Long memberId;

	/**
	 * 咨询内容
	 */
	private String consultContent;

	/**
	 * 回复内容
	 */
	private String answer;

	/**
	 * 是否显示 0 显示 1 不显示
	 */
	private Integer isShow;

	/**
	 * 父id
	 */
	private Long parentId;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 回复时间
	 */
	private Date answerDate;

	/**
	 * 回复人
	 */
	private String answerUserName;

	/**
	 * 咨询用户名(非数据库字段)
	 */
	private String userName;

	/**
	 * 咨询商品名称(非数据库字段)
	 */
	private String goodsName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getConsultContent() {
		return consultContent;
	}

	public void setConsultContent(String consultContent) {
		this.consultContent = consultContent;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public String getAnswerUserName() {
		return answerUserName;
	}

	public void setAnswerUserName(String answerUserName) {
		this.answerUserName = answerUserName;
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

}