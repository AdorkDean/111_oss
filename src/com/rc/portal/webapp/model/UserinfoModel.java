
package com.rc.portal.webapp.model;


import com.rc.app.framework.webapp.model.BaseModel;

public class UserinfoModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4379432702482463173L;
	private String userid;
    private String username;
    private String name;
    private String usertype;
    private String paypasswd;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getPaypasswd() {
		return paypasswd;
	}
	public void setPaypasswd(String paypasswd) {
		this.paypasswd = paypasswd;
	}
    
}