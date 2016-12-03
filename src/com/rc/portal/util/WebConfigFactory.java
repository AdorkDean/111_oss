package com.rc.portal.util;

/**  
 * @Title: WebConfigFactory.java
 * @Description: 一些配置和工具的工场类
 * @author yinbinhome@163.com  
 * @date 2011-4-23 下午12:22:11
 * @version V1.0  
 */

public class WebConfigFactory {

	/**
	 * 获得更新全局权限工具类
	 * @return
	 */
	public static FlashAuthorization getFlashAuthorization(){
		return FlashAuthorization.getInstance();
	}
}
