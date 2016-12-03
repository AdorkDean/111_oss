package com.rc.oss.vo;

/**
 * TAdmUserPost entity. @author MyEclipse Persistence Tools
 */

public class TAdmUserPost implements java.io.Serializable {

	// Fields

	private Long id;
	private TAdmPost TAdmPost;
	private TAdmUsers TAdmUsers;

	// Constructors

	/** default constructor */
	public TAdmUserPost() {
	}

	/** full constructor */
	public TAdmUserPost(TAdmPost TAdmPost, TAdmUsers TAdmUsers) {
		this.TAdmPost = TAdmPost;
		this.TAdmUsers = TAdmUsers;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TAdmPost getTAdmPost() {
		return this.TAdmPost;
	}

	public void setTAdmPost(TAdmPost TAdmPost) {
		this.TAdmPost = TAdmPost;
	}

	public TAdmUsers getTAdmUsers() {
		return this.TAdmUsers;
	}

	public void setTAdmUsers(TAdmUsers TAdmUsers) {
		this.TAdmUsers = TAdmUsers;
	}

}