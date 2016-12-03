package com.rc.oss.vo;

/**
 * TAdmRescRoles entity. @author MyEclipse Persistence Tools
 */

public class TAdmRescRoles implements java.io.Serializable {

	// Fields

	private Long id;
	private TAdmRoles TAdmRoles;
	private TAdmResc TAdmResc;

	// Constructors

	/** default constructor */
	public TAdmRescRoles() {
	}

	/** full constructor */
	public TAdmRescRoles(TAdmRoles TAdmRoles, TAdmResc TAdmResc) {
		this.TAdmRoles = TAdmRoles;
		this.TAdmResc = TAdmResc;
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

	public TAdmResc getTAdmResc() {
		return this.TAdmResc;
	}

	public void setTAdmResc(TAdmResc TAdmResc) {
		this.TAdmResc = TAdmResc;
	}

}