package com.rc.portal.vo;

import java.io.Serializable;

public class TAdmUsersModules implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4758190163296989429L;

	private Long id;

    private Long userid;

    private Long moduleid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getModuleid() {
        return moduleid;
    }

    public void setModuleid(Long moduleid) {
        this.moduleid = moduleid;
    }
}