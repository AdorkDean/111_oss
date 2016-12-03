package com.rc.portal.vo;

import java.util.Date;

public class CFeedback {
    private Integer id;

    private Date createDate;

    private String message;

    private Integer userId;

    private String fromPhone;

    private Integer collect;

    private String netType;

    private String systemType;

    private String clientType;

    private String qudaoSn;

    private String qudaoName;

    private String fenbielv;

    private String equipment;

    private String clientVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getQudaoSn() {
        return qudaoSn;
    }

    public void setQudaoSn(String qudaoSn) {
        this.qudaoSn = qudaoSn;
    }

    public String getQudaoName() {
        return qudaoName;
    }

    public void setQudaoName(String qudaoName) {
        this.qudaoName = qudaoName;
    }

    public String getFenbielv() {
        return fenbielv;
    }

    public void setFenbielv(String fenbielv) {
        this.fenbielv = fenbielv;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }
}