package com.rc.oss.vo;

/**
 * TAdmPostRole entity. @author MyEclipse Persistence Tools
 */

public class TAdmPostRole implements java.io.Serializable {

	// Fields

	private Long id;
	private TAdmRoles TAdmRoles;
	private TAdmPost TAdmPost;

	// Constructors

	/** default constructor */
	public TAdmPostRole() {
	}

	/** full constructor */
	public TAdmPostRole(TAdmRoles TAdmRoles, TAdmPost TAdmPost) {
		this.TAdmRoles = TAdmRoles;
		this.TAdmPost = TAdmPost;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TAdmRoles getTAdmRoles() {
		return this.TAdmRoles;
	}

	public void setTAdmRoles(TAdmRoles TAdmRoles) {
		this.TAdmRoles = TAdmRoles;
	}

	public TAdmPost getTAdmPost() {
		return this.TAdmPost;
	}

	public void setTAdmPost(TAdmPost TAdmPost) {
		this.TAdmPost = TAdmPost;
	}

}