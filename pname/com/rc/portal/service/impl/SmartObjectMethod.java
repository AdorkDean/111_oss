package com.rc.portal.service.impl;

import java.lang.reflect.Method;
import java.util.List;

import com.danga.MemCached.MemCachedClient;
import com.rc.commons.util.StringUtil;
import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.webapp.util.MemCachedUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 获取相关联对象属性
 * @author liutianling
 *
 */
public class SmartObjectMethod implements TemplateMethodModel{

	private OpenSqlDAO opensqldao;
	
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}

	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}

	/**
	 * 默认传递2个参数
	 * 1.对象主键ID
	 * 2.对象的实例名称(.属性名称   可选  返回属性或者返回对象)
	 */
	@SuppressWarnings("rawtypes")
	public Object exec(List args) throws TemplateModelException {
		try {
			if(args == null || args.size() !=2 || !StringUtil.isNumeric(args.get(0).toString().trim()) || StringUtil.isNumeric(args.get(1).toString().trim()) ){
				return "";
			}
			
			
			Class<?> model = Class.forName("com.rc.portal.vo." + args.get(1).toString().trim().split("\\.")[0]);
			Object selectObjectByObject = null;
			
			MemCachedClient cache = MemCachedUtil.getmcc();
			//主键规则-类路劲_主键ID
//			if(cache.keyExists("com.rc.portal.vo." + args.get(1).toString().trim().split("\\.")[0]+"_" + args.get(0).toString())){				
//				selectObjectByObject = cache.get("com.rc.portal.vo." + args.get(1).toString().trim().split("\\.")[0]+"_" + args.get(0).toString());
//			}else{
				
				Object newInstance = model.newInstance();
				Method method = model.getDeclaredMethod("setId", java.lang.Long.class);
				
				method.invoke(newInstance, new Long(args.get(0).toString()));
				
				//将尸体类名转换成默认规则向生成的sqlmap name_space规则名称
				char [] voName = args.get(1).toString().trim().split("\\.")[0].toCharArray();
				StringBuffer sqlMapNameSpace = new StringBuffer();
				for(int i = 0;i < voName.length ;i++){
					
					if(voName[i] >= 'A' && voName[i] <= 'Z'&& i != 0){
						sqlMapNameSpace.append(("_" + voName[i]));
					}else{
						sqlMapNameSpace.append(voName[i]);
					}
				}
				
				selectObjectByObject = opensqldao.selectObjectByObject(newInstance, sqlMapNameSpace.toString().trim().toLowerCase()+".ibatorgenerated_selectByPrimaryKey");
				
//				cache.set("com.rc.portal.vo." + args.get(1).toString().trim().split("\\.")[0]+"_" + args.get(0).toString(), selectObjectByObject);
//			}
			
			if(selectObjectByObject==null){
				return "";
			}
			
			if(args.get(1).toString().contains(".")){
				Method[] declaredMethods = model.getDeclaredMethods();
				
				for (Method method2 : declaredMethods) {
					if(method2.getName().equalsIgnoreCase("get"+args.get(1).toString().trim().split("\\.")[1])){						
						return method2.invoke(selectObjectByObject, null).toString();
					}
				}
			}else{
				return selectObjectByObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}
