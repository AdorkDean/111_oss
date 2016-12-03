package com.rc.portal.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TAdmOperationLogs {
    private Long id;

    private Long moduleId;

    private Short optType;

    private BigDecimal optId;

    private Date optDate;

    private BigDecimal objId;

    private Short objType;

    private String operation;

    private String ipAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Short getOptType() {
        return optType;
    }

    public void setOptType(Short optType) {
        this.optType = optType;
    }

    public BigDecimal getOptId() {
        return optId;
    }

    public void setOptId(BigDecimal optId) {
        this.optId = optId;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public BigDecimal getObjId() {
        return objId;
    }

    public void setObjId(BigDecimal objId) {
        this.objId = objId;
    }

    public Short getObjType() {
        return objType;
    }

    public void setObjType(Short objType) {
        this.objType = objType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}