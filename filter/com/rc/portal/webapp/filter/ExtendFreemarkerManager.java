package com.rc.portal.webapp.filter;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.webwork.views.freemarker.FreemarkerManager;
import com.rc.app.framework.webapp.util.CurrencyMethod;
import com.rc.portal.service.impl.FlashMessageMethod;
import com.rc.portal.service.impl.SmartObjectMethod;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;


public class ExtendFreemarkerManager extends FreemarkerManager{
	
	 protected Configuration createConfiguration(ServletContext servletContext)
		        throws TemplateException{
		 
        Configuration configuration = new Configuration();
        configuration.setTemplateLoader(getTemplateLoader(servletContext));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
        configuration.setSharedVariable("smartObjectMethod", context.getBean("smartobjectmethod", SmartObjectMethod.class));
        configuration.setSharedVariable("flash_message", new FlashMessageMethod());
        configuration.setSharedVariable("currency", CurrencyMethod.getInstance());
        configuration.setObjectWrapper(getObjectWrapper());
        if(com.opensymphony.webwork.config.Configuration.isSet("webwork.i18n.encoding"))
            configuration.setDefaultEncoding(com.opensymphony.webwork.config.Configuration.getString("webwork.i18n.encoding"));
        loadSettings(servletContext, configuration);
        return configuration;
    }

}
