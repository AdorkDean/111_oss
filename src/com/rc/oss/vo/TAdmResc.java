package com.rc.oss.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * TAdmResc entity. @author MyEclipse Persistence Tools
 */

public class TAdmResc implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String resType;
	private String resString;
	private Long priority;
	private String descn;
	private Integer editable;
	private Integer webid;
	private Set TAdmRescRoleses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TAdmResc() {
	}

	/** minimal constructor */
	public TAdmResc(String resString) {
		this.resString = resString;
	}

	/** full constructor */
	public TAdmResc(String name, String resType, String resString, Long priority, String descn, Integer editable, Integer webid, Set TAdmRescRoleses) {
		this.name = name;
		this.resType = resType;
		this.resString = resString;
		this.priority = priority;
		this.descn = descn;
		this.editable = editable;
		this.webid = webid;
		this.TAdmRescRoleses = TAdmRescRoleses;
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

	public String getResType() {
		return this.resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResString() {
		return this.resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public Long getPriority() {
		return this.priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public String getDescn() {
		return this.descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
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

}