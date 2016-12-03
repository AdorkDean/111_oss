package com.rc.oss.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TAdmUsers entity. @author MyEclipse Persistence Tools
 */

public class TAdmUsers implements java.io.Serializable {

	// Fields

	private Long id;
	private String loginName;
	private String name;
	private String department;
	private String password;
	private Date expireTime;
	private String telephone;
	private String email;
	private Integer userLevel;
	private Integer status;
	private BigDecimal createBy;
	private Date createTime;
	private BigDecimal lastEditBy;
	private Date lastEditTime;
	private Integer allowDelete;
	private Integer isAdminUser;
	private Integer webid;
//	private Set TAdmUserRoleses = new HashSet(0);
	private Set TAdmUsersModuleses = new HashSet(0);
	private Set TAdmUserPosts = new HashSet(0);

	// Constructors

	/** default constructor */
	public TAdmUsers() {
	}

	/** minimal constructor */
	public TAdmUsers(String loginName, String name, String password) {
		this.loginName = loginName;
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public TAdmUsers(String loginName, String name, String department, String password, Date expireTime, String telephone, String email, Integer userLevel, Integer status, BigDecimal createBy,
			Date createTime, BigDecimal lastEditBy, Date lastEditTime, Integer allowDelete, Integer isAdminUser, Integer webid, Set TAdmUsersModuleses, Set TAdmUserPosts) {
		this.loginName = loginName;
		this.name = name;
		this.department = department;
		this.password = password;
		this.expireTime = expireTime;
		this.telephone = telephone;
		this.email = email;
		this.userLevel = userLevel;
		this.status = status;
		this.createBy = createBy;
		this.createTime = createTime;
		this.lastEditBy = lastEditBy;
		this.lastEditTime = lastEditTime;
		this.allowDelete = allowDelete;
		this.isAdminUser = isAdminUser;
		this.webid = webid;
//		this.TAdmUserRoleses = TAdmUserRoleses;
		this.TAdmUsersModuleses = TAdmUsersModuleses;
		this.TAdmUserPosts = TAdmUserPosts;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
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

	public BigDecimal getLastEditBy() {
		return this.lastEditBy;
	}

	public void setLastEditBy(BigDecimal lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public Date getLastEditTime() {
		return this.lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Integer getAllowDelete() {
		return this.allowDelete;
	}

	public void setAllowDelete(Integer allowDelete) {
		this.allowDelete = allowDelete;
	}

	public Integer getIsAdminUser() {
		return this.isAdminUser;
	}

	public void setIsAdminUser(Integer isAdminUser) {
		this.isAdminUser = isAdminUser;
	}

	public Integer getWebid() {
		return this.webid;
	}

	public void setWebid(Integer webid) {
		this.webid = webid;
	}


	public Set getTAdmUsersModuleses() {
		return this.TAdmUsersModuleses;
	}

	public void setTAdmUsersModuleses(Set TAdmUsersModuleses) {
		this.TAdmUsersModuleses = TAdmUsersModuleses;
	}

	public Set getTAdmUserPosts() {
		return this.TAdmUserPosts;
	}

	public void setTAdmUserPosts(Set TAdmUserPosts) {
		this.TAdmUserPosts = TAdmUserPosts;
	}

}