package com.rc.oss.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TAdmUserRoles entity. @author MyEclipse Persistence Tools
 */

public class TAdmUserRoles implements java.io.Serializable {

	// Fields

	private Long id;
	private TAdmUsers TAdmUsers;
	private TAdmRoles TAdmRoles;
	private BigDecimal createBy;
	private Date createTime;
	private BigDecimal lastUpdateBy;
	private Date lastUpdateTime;

	// Constructors

	/** default constructor */
	public TAdmUserRoles() {
	}

	/** minimal constructor */
	public TAdmUserRoles(TAdmUsers TAdmUsers, TAdmRoles TAdmRoles, Date createTime) {
		this.TAdmUsers = TAdmUsers;
		this.TAdmRoles = TAdmRoles;
		this.createTime = createTime;
	}

	/** full constructor */
	public TAdmUserRoles(TAdmUsers TAdmUsers, TAdmRoles TAdmRoles, BigDecimal createBy, Date createTime, BigDecimal lastUpdateBy, Date lastUpdateTime) {
		this.TAdmUsers = TAdmUsers;
		this.TAdmRoles = TAdmRoles;
		this.createBy = createBy;
		this.createTime = createTime;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateTime = lastUpdateTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TAdmUsers getTAdmUsers() {
		return this.TAdmUsers;
	}

	public void setTAdmUsers(TAdmUsers TAdmUsers) {
		this.TAdmUsers = TAdmUsers;
	}

	public TAdmRoles getTAdmRoles() {
		return this.TAdmRoles;
	}

	public void setTAdmRoles(TAdmRoles TAdmRoles) {
		this.TAdmRoles = TAdmRoles;
	}

	public BigDecimal getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(BigDecimal createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(BigDecimal lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}