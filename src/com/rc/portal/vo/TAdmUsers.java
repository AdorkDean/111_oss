package com.rc.portal.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TAdmUsers implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6353239736808525866L;

	private Long id;

    private String loginName;

    private String name;

    private String department;

    private String password;

    private Date expireTime;

    private String telephone;

    private String email;

    private Short userLevel;

    private Short status;

    private BigDecimal createBy;

    private Date createTime;

    private BigDecimal lastEditBy;

    private Date lastEditTime;

    private Short allowDelete;

    private Short isAdminUser;
    
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Short userLevel) {
        this.userLevel = userLevel;
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

    public BigDecimal getLastEditBy() {
        return lastEditBy;
    }

    public void setLastEditBy(BigDecimal lastEditBy) {
        this.lastEditBy = lastEditBy;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Short getAllowDelete() {
        return allowDelete;
    }

    public void setAllowDelete(Short allowDelete) {
        this.allowDelete = allowDelete;
    }

    public Short getIsAdminUser() {
        return isAdminUser;
    }

    public void setIsAdminUser(Short isAdminUser) {
        this.isAdminUser = isAdminUser;
    }
}