package com.rc.oss.vo;

/**  
 * @Title: Mytest.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-16 下午03:28:21
 * @version V1.0  
 */

public class Mytest {
	private String name;
	private Long age;
	public Mytest(String name, Long age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	
	
}
