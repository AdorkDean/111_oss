package com.rc.oss.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TAdmModules entity. @author MyEclipse Persistence Tools
 */

public class TAdmModules implements java.io.Serializable {

	// Fields

	private Long id;
	private TAdmModules TAdmModules;
	private String moduleName;
	private Long parentid;
	private Date createTime;
	private String memo;
	private Integer moduleType;
	private Integer forLog;
	private String url;
	private String moduleId;
	private Integer dispnum;
	private String moduleKey;
	private Integer editable;
	private Integer webid;
//	private Set TAdmModuleses = new HashSet(0);
	private Set TAdmUsersModuleses = new HashSet(0);
	
	

	public TAdmModules(Long id, TAdmModules tAdmModules, String moduleName,
			Long parentid, Date createTime, String memo, Integer moduleType,
			Integer forLog, String url, String moduleId, Integer dispnum,
			String moduleKey, Integer editable, Integer webid,
			 Set tAdmUsersModuleses) {
		super();
		this.id = id;
		TAdmModules = tAdmModules;
		this.moduleName = moduleName;
		this.parentid = parentid;
		this.createTime = createTime;
		this.memo = memo;
		this.moduleType = moduleType;
		this.forLog = forLog;
		this.url = url;
		this.moduleId = moduleId;
		this.dispnum = dispnum;
		this.moduleKey = moduleKey;
		this.editable = editable;
		this.webid = webid;
//		TAdmModuleses = tAdmModuleses;
		TAdmUsersModuleses = tAdmUsersModuleses;
	}


	// Constructors

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}


	/** default constructor */
	public TAdmModules() {
	}

	/** minimal constructor */
	public TAdmModules(String moduleName, Date createTime, Integer forLog) {
		this.moduleName = moduleName;
		this.createTime = createTime;
		this.forLog = forLog;
	}

	/** full constructor */
	public TAdmModules(TAdmModules TAdmModules, String moduleName, Date createTime, String memo, Integer moduleType, Integer forLog, String url, String moduleId, Integer dispnum, String moduleKey,
			Integer editable, Integer webid,  Set TAdmUsersModuleses) {
		this.TAdmModules = TAdmModules;
		this.moduleName = moduleName;
		this.createTime = createTime;
		this.memo = memo;
		this.moduleType = moduleType;
		this.forLog = forLog;
		this.url = url;
		this.moduleId = moduleId;
		this.dispnum = dispnum;
		this.moduleKey = moduleKey;
		this.editable = editable;
		this.webid = webid;
		this.TAdmUsersModuleses = TAdmUsersModuleses;
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

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}

	public Integer getForLog() {
		return this.forLog;
	}

	public void setForLog(Integer forLog) {
		this.forLog = forLog;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getDispnum() {
		return this.dispnum;
	}

	public void setDispnum(Integer dispnum) {
		this.dispnum = dispnum;
	}

	public String getModuleKey() {
		return this.moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
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


	public Set getTAdmUsersModuleses() {
		return this.TAdmUsersModuleses;
	}

	public void setTAdmUsersModuleses(Set TAdmUsersModuleses) {
		this.TAdmUsersModuleses = TAdmUsersModuleses;
	}


}