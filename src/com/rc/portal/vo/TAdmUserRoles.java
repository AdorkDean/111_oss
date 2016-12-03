package com.rc.portal.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TAdmUserRoles implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7654064768504316645L;

	private Long id;

    private Long userId;

    private Long roleId;

    private BigDecimal createBy;

    private Date createTime;

    private BigDecimal lastUpdateBy;

    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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