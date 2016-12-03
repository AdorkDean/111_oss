package com.rc.portal.webapp.model;

/**  
 * @Title: ModuleModel.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2012-12-19 下午09:25:21
 * @version V1.0  
 */

public class ModuleModel {
	private Long id;
	private String modulename;
	private Long enable;
	private Long parentid;
	private Long userid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public Long getEnable() {
		return enable;
	}
	public void setEnable(Long enable) {
		this.enable = enable;
	}
	public Long getParentid() {
		return parentid;
	}
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	
}
