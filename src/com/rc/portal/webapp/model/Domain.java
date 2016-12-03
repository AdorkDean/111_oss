package com.rc.portal.webapp.model;

/**  
 * @Title: Domain.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2012-12-26 上午10:06:20
 * @version V1.0  
 */

public class Domain {
	private Long id;
	private String name;
	private String domain;
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
