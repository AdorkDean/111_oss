package com.rc.oss.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TAdmPost entity. @author MyEclipse Persistence Tools
 */

public class TAdmPost implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String remark;
	private Date createDt;
	private Integer enable;
	private Integer webid;
	private Set TAdmPostRoles = new HashSet(0);
	private Set TAdmUserPosts = new HashSet(0);

	// Constructors

	/** default constructor */
	public TAdmPost() {
	}

	/** full constructor */
	public TAdmPost(String name, String remark, Date createDt, Integer enable, Integer webid, Set TAdmPostRoles, Set TAdmUserPosts) {
		this.name = name;
		this.remark = remark;
		this.createDt = createDt;
		this.enable = enable;
		this.webid = webid;
		this.TAdmPostRoles = TAdmPostRoles;
		this.TAdmUserPosts = TAdmUserPosts;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getWebid() {
		return this.webid;
	}

	public void setWebid(Integer webid) {
		this.webid = webid;
	}

	public Set getTAdmPostRoles() {
		return this.TAdmPostRoles;
	}

	public void setTAdmPostRoles(Set TAdmPostRoles) {
		this.TAdmPostRoles = TAdmPostRoles;
	}

	public Set getTAdmUserPosts() {
		return this.TAdmUserPosts;
	}

	public void setTAdmUserPosts(Set TAdmUserPosts) {
		this.TAdmUserPosts = TAdmUserPosts;
	}

}