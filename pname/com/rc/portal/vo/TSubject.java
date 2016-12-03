package com.rc.portal.vo;

import java.util.Date;

public class TSubject {
    private Integer id;

    private String name;

    private String nameFile;

    private String templetePath;

    private String ztPath;

    private Integer ptype;

    private Date createDt;

    private Date lastDt;

    private String lastEdit;

    private byte[] ztContext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getTempletePath() {
        return templetePath;
    }

    public void setTempletePath(String templetePath) {
        this.templetePath = templetePath;
    }

    public String getZtPath() {
        return ztPath;
    }

    public void setZtPath(String ztPath) {
        this.ztPath = ztPath;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getLastDt() {
        return lastDt;
    }

    public void setLastDt(Date lastDt) {
        this.lastDt = lastDt;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }

    public byte[] getZtContext() {
        return ztContext;
    }

    public void setZtContext(byte[] ztContext) {
        this.ztContext = ztContext;
    }
}