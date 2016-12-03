package com.rc.portal.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TAdmRoles implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1825451498881575061L;

	private Long id;

    private String roleName;

    private String roleDesc;

    private Short status;

    private BigDecimal createBy;

    private Date createTime;

    private BigDecimal lastUpdateBy;

    private Date lastUpdateTime;

    private Integer editable;
    
    private Short webid;
    
    public Short getWebid() {
		return webid;
	}

	public void setWebid(Short webid) {
		this.webid = webid;
	}

	public Integer getEditable() {
		return editable;
	}

	public void setEditable(Integer editable) {
		this.editable = editable;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public BigDecimal getCreateBy() {
        return createBy;
    }

    public void setCreateBy(BigDecimal createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(BigDecimal lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}