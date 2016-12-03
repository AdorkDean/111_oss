package com.rc.portal.vo;

import java.io.Serializable;

public class TAdmResc implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5824839335867637675L;

	private Long id;

    private String name;

    private String resType;

    private String resString;

    private Long priority;

    private String descn;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getResString() {
        return resString;
    }

    public void setResString(String resString) {
        this.resString = resString;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }
}