package com.rc.portal.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by wangsheng on 2015/3/26.
 */
public class SpringServiceUtils {

    public static <T> T getBean( ServletContext servletContext, String name, Class<T> type )
    {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return (T)webApplicationContext.getBean( name, type );
    }
    public static <T> T getBean( HttpServlet httpServlet, String name, Class<T> type )
    {
        return getBean( httpServlet.getServletContext(), name, type );
    }

    public static <T> T getBean( String springXml, String name, Class<T> type )
    {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(springXml);
        return (T) classPathXmlApplicationContext.getBean(name, type);
    }

    public static <T> T getBean( String name, Class<T> type )
    {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext-*.xml");
        return (T) classPathXmlApplicationContext.getBean(name, type);
    }

}
