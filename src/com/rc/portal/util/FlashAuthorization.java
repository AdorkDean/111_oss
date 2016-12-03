package com.rc.portal.util;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.FilterSecurityInterceptor;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**  
 * @Title: FlashAuthorization.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2011-4-23 下午12:18:21
 * @version V1.0  
 */

public class FlashAuthorization {
	private final Logger log = Logger.getLogger(getClass());
	
	private static FlashAuthorization instance=null;
	
	private FlashAuthorization(){};
	
	public static synchronized FlashAuthorization getInstance(){
		if(instance!=null){
			return instance;
		}else{
			return new FlashAuthorization();
		}
	}	
	
	/**
	 * 刷新上下文权限
	 * @param context
	 * @return
	 */
	public boolean flash(ServletContext context){
	    try {
			ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(context);
			FactoryBean factoryBean = (FactoryBean) ctx.getBean("&filterInvocationDefinitionSource");
			FilterInvocationDefinitionSource fids = (FilterInvocationDefinitionSource) factoryBean.getObject();
			FilterSecurityInterceptor filter = (FilterSecurityInterceptor) ctx.getBean("filterSecurityInterceptor");
			filter.setObjectDefinitionSource(fids);
		}catch (Exception e) {
			e.printStackTrace();
			log.info("【权限】刷新内存失败！");
			return false;
		}	    
		log.info("【权限】刷新内存成功！");
	    return true;
	}
}
