package com.rc.portal.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TAdmModules implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3593738616761317064L;

	private Long id;

    private String moduleName;

    private Long parentId;

    private Date createTime;

    private String memo;

    private Short moduleType;

    private Short forLog;

    private String url;

    private String moduleId;

    private Integer dispnum;

    private String moduleKey;
    
    private Integer editable;    

    private List<TAdmModules> list;
    
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

	public List<TAdmModules> getList() {
		return list;
	}

	public void setList(List<TAdmModules> list) {
		this.list = list;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Short getModuleType() {
        return moduleType;
    }

    public void setModuleType(Short moduleType) {
        this.moduleType = moduleType;
    }

    public Short getForLog() {
        return forLog;
    }

    public void setForLog(Short forLog) {
        this.forLog = forLog;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getDispnum() {
        return dispnum;
    }

    public void setDispnum(Integer dispnum) {
        this.dispnum = dispnum;
    }

    public String getModuleKey() {
        return moduleKey;
    }

    public void setModuleKey(String moduleKey) {
        this.moduleKey = moduleKey;
    }
}