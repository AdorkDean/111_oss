package com.rc.portal.vo;

import java.io.Serializable;

public class TAdmRescRoles implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8820967956515660749L;

	private Long id;

    private Long rescId;

    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRescId() {
        return rescId;
    }

    public void setRescId(Long rescId) {
        this.rescId = rescId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}