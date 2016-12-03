package com.rc.portal.util;


/**
 * @Title: Test.java
 * @Description:
 * @author yinbinhome@163.com
 * @date 2013-5-24 下午03:45:56
 * @version V1.0
 */

public class Test {
	public static void main(String arges[]) throws Exception {
		String path = new Test().getClass().getResource("/").getPath();
		System.out.println(path);
	}

	
}
