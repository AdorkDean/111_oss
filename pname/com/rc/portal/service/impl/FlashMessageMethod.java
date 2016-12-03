package com.rc.portal.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.webwork.ServletActionContext;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 模板指令 - 瞬时消息
 */
public class FlashMessageMethod implements TemplateMethodModel {

	/** "瞬时消息" 属性名称 */
	public static final String FLASH_MESSAGE_ATTRIBUTE_NAME = "FlashMessageMethod.FLASH_MESSAGE_ATTRIBUTE_NAME";
	
	/** "瞬时消息" 用户自定义消息成功或者失败类型 */
	public static final String FLASH_MESSAGE = "FlashMessageMethod.FLASH_MESSAGE";
	
	/** 瞬时消息  **/
	@SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object attribute_name = request.getSession().getAttribute(FLASH_MESSAGE_ATTRIBUTE_NAME);
		
		Object message = request.getSession().getAttribute(FLASH_MESSAGE);
		//临时数据,用完马上销毁
		request.getSession().removeAttribute(FLASH_MESSAGE_ATTRIBUTE_NAME);
		request.getSession().removeAttribute(FLASH_MESSAGE);
		
		String alert = "";
		if(attribute_name!=null){
			List validFileNames = (List)attribute_name;
			if(validFileNames.size()>0){
				alert += validFileNames.toString()+"字段为[空]或[null]";
			}
		}
		
		if(message != null){
			boolean type = (Boolean)message;
			if(!alert.isEmpty()){
				alert += ",";
			}
			if(type){
				alert += "操作成功";
			}else{
				alert += "操作失败";
			}
		}
		
		if(message==null && attribute_name == null){
			return "";
		}else{
			return "alert('"+alert+"');";
		}
	}

}