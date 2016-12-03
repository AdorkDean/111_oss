package com.rc.portal.vo;

import java.util.Date;

public class TOssOperateLog {
    private Long id;

    private Integer moduleType;

    private Long recordId;

    private String operationRemake;

    private String operationUsername;

    private Date operationDt;

    private Date createDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getOperationRemake() {
        return operationRemake;
    }

    public void setOperationRemake(String operationRemake) {
        this.operationRemake = operationRemake;
    }

    public String getOperationUsername() {
        return operationUsername;
    }

    public void setOperationUsername(String operationUsername) {
        this.operationUsername = operationUsername;
    }

    public Date getOperationDt() {
        return operationDt;
    }

    public void setOperationDt(Date operationDt) {
        this.operationDt = operationDt;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }
}