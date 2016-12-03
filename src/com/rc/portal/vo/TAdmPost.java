package com.rc.portal.vo;

import java.util.Date;

public class TAdmPost {
    private Long id;

    private String name;

    private String remark;

    private Date createDt;

    private Short enable;

    private Short webid;
    
    public Short getWebid() {
		return webid;
	}

	public void setWebid(Short webid) {
		this.webid = webid;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Short getEnable() {
        return enable;
    }

    public void setEnable(Short enable) {
        this.enable = enable;
    }
}