package com.rc.oss.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TAdmOperationLogs entity. @author MyEclipse Persistence Tools
 */

public class TAdmOperationLogs implements java.io.Serializable {

	// Fields

	private Long id;
	private Long moduleId;
	private Short optType;
	private Long optId;
	private Date optDate;
	private BigDecimal objId;
	private Short objType;
	private String operation;
	private String ipAddress;

	// Constructors

	/** default constructor */
	public TAdmOperationLogs() {
	}

	/** minimal constructor */
	public TAdmOperationLogs(Long optId, Date optDate, String operation, String ipAddress) {
		this.optId = optId;
		this.optDate = optDate;
		this.operation = operation;
		this.ipAddress = ipAddress;
	}

	/** full constructor */
	public TAdmOperationLogs(Long moduleId, Short optType, Long optId, Date optDate, BigDecimal objId, Short objType, String operation, String ipAddress) {
		this.moduleId = moduleId;
		this.optType = optType;
		this.optId = optId;
		this.optDate = optDate;
		this.objId = objId;
		this.objType = objType;
		this.operation = operation;
		this.ipAddress = ipAddress;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Short getOptType() {
		return this.optType;
	}

	public void setOptType(Short optType) {
		this.optType = optType;
	}

	public Long getOptId() {
		return this.optId;
	}

	public void setOptId(Long optId) {
		this.optId = optId;
	}

	public Date getOptDate() {
		return this.optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public BigDecimal getObjId() {
		return this.objId;
	}

	public void setObjId(BigDecimal objId) {
		this.objId = objId;
	}

	public Short getObjType() {
		return this.objType;
	}

	public void setObjType(Short objType) {
		this.objType = objType;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}