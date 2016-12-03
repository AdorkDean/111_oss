package com.rc.oss.vo;

/**
 * TAdmUsersModules entity. @author MyEclipse Persistence Tools
 */

public class TAdmUsersModules implements java.io.Serializable {

	// Fields

	private Long id;
	private TAdmModules TAdmModules;
	private TAdmUsers TAdmUsers;

	// Constructors

	/** default constructor */
	public TAdmUsersModules() {
	}

	/** full constructor */
	public TAdmUsersModules(TAdmModules TAdmModules, TAdmUsers TAdmUsers) {
		this.TAdmModules = TAdmModules;
		this.TAdmUsers = TAdmUsers;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TAdmModules getTAdmModules() {
		return this.TAdmModules;
	}

	public void setTAdmModules(TAdmModules TAdmModules) {
		this.TAdmModules = TAdmModules;
	}

	public TAdmUsers getTAdmUsers() {
		return this.TAdmUsers;
	}

	public void setTAdmUsers(TAdmUsers TAdmUsers) {
		this.TAdmUsers = TAdmUsers;
	}

}