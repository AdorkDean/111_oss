package com.rc.oss.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TAdmRoles entity. @author MyEclipse Persistence Tools
 */

public class TAdmRoles implements java.io.Serializable {

	// Fields

	private Long id;
	private String roleName;
	private String roleDesc;
	private Integer status;
	private BigDecimal createBy;
	private Date createTime;
	private Long lastUpdateBy;
	private Date lastUpdateTime;
	private Integer editable;
	private Integer webid;
	private Set TAdmRescRoleses = new HashSet(0);
	private Set TAdmPostRoles = new HashSet(0);
	private Set TAdmUserRoleses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TAdmRoles() {
	}

	/** minimal constructor */
	public TAdmRoles(String roleName, Integer status, Date createTime) {
		this.roleName = roleName;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public TAdmRoles(String roleName, String roleDesc, Integer status, BigDecimal createBy, Date createTime, Long lastUpdateBy, Date lastUpdateTime, Integer editable, Integer webid,
			Set TAdmRescRoleses, Set TAdmPostRoles, Set TAdmUserRoleses) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.status = status;
		this.createBy = createBy;
		this.createTime = createTime;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateTime = lastUpdateTime;
		this.editable = editable;
		this.webid = webid;
		this.TAdmRescRoleses = TAdmRescRoleses;
		this.TAdmPostRoles = TAdmPostRoles;
		this.TAdmUserRoleses = TAdmUserRoleses;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Long getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getEditable() {
		return this.editable;
	}

	public void setEditable(Integer editable) {
		this.editable = editable;
	}

	public Integer getWebid() {
		return this.webid;
	}

	public void setWebid(Integer webid) {
		this.webid = webid;
	}

	public Set getTAdmRescRoleses() {
		return this.TAdmRescRoleses;
	}

	public void setTAdmRescRoleses(Set TAdmRescRoleses) {
		this.TAdmRescRoleses = TAdmRescRoleses;
	}

	public Set getTAdmPostRoles() {
		return this.TAdmPostRoles;
	}

	public void setTAdmPostRoles(Set TAdmPostRoles) {
		this.TAdmPostRoles = TAdmPostRoles;
	}

	public Set getTAdmUserRoleses() {
		return this.TAdmUserRoleses;
	}

	public void setTAdmUserRoleses(Set TAdmUserRoleses) {
		this.TAdmUserRoleses = TAdmUserRoleses;
	}

}