package com.rc.portal.webapp.model;

/**  
 * @Title: Show.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-12-16 下午05:04:31
 * @version V1.0  
 */

public class Show {
	private String name;
	private String ishow;
	
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Show(String name, String ishow, String len) {
		super();
		this.name = name;
		this.ishow = ishow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIshow() {
		return ishow;
	}
	public void setIshow(String ishow) {
		this.ishow = ishow;
	}
}
