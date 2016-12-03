package com.rc.oss.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmOperationLogsDAO;
import com.rc.oss.service.TAdmOperationLogsManagerItf;
import com.rc.oss.vo.TAdmOperationLogs;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.webapp.model.UserinfoModel;

/**  
 * @Title: TAdmOperationLogsManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:36:20
 * @version V1.0  
 */

public class TAdmOperationLogsManager implements TAdmOperationLogsManagerItf {
	private TAdmOperationLogsDAO tadmoperationlogdao_oss;
	
	

	public TAdmOperationLogsDAO getTadmoperationlogdao_oss() {
		return tadmoperationlogdao_oss;
	}

	public void setTadmoperationlogdao_oss(TAdmOperationLogsDAO tadmoperationlogdao_oss) {
		this.tadmoperationlogdao_oss = tadmoperationlogdao_oss;
	}

	@Override
	public void attachClean(TAdmOperationLogs instance) {
		// TODO Auto-generated method stub
		tadmoperationlogdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmOperationLogs instance) {
		// TODO Auto-generated method stub
		tadmoperationlogdao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmOperationLogs persistentInstance) {
		// TODO Auto-generated method stub
		tadmoperationlogdao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findAll();
	}

	@Override
	public List findByExample(TAdmOperationLogs instance) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findByExample(instance);
	}

	@Override
	public TAdmOperationLogs findById(Long id) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findById(id);
	}

	@Override
	public List findByIpAddress(Object ipAddress) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findByIpAddress(ipAddress);
	}

	@Override
	public List findByModuleId(Object moduleId) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findByModuleId(moduleId);
	}

	@Override
	public List findByObjType(Object objType) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findByObjType(objType);
	}

	@Override
	public List findByOperation(Object operation) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findByOperation(operation);
	}

	@Override
	public List findByOptId(Object optId) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findByOptId(optId);
	}

	@Override
	public List findByOptType(Object optType) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findByOptType(optType);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public TAdmOperationLogs merge(TAdmOperationLogs detachedInstance) {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmOperationLogs transientInstance) {
		// TODO Auto-generated method stub
		tadmoperationlogdao_oss.save(transientInstance);
	}
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmoperationlogdao_oss.getCriteria();
	}
	
	/**
	 * 记录日志
	 * @param request
	 * @param context
	 */
	public void record(HttpServletRequest request,String context){
		UserinfoModel userinfo =(UserinfoModel) request.getSession().getAttribute(ConstantUtil.logincookiename);
		String userid="-1";
		if(userinfo!=null){
			userid=userinfo.getUserid();
		}	
		TAdmOperationLogs logs=new TAdmOperationLogs();
		logs.setIpAddress(getRemortIP(request));
		logs.setOptId(new Long(userid));
		logs.setOptDate(new Date());
		logs.setOperation(context);		
		tadmoperationlogdao_oss.save(logs);
	}
	
	/**
	 * 记录日志,供登陆记录日志使用
	 * @param ip
	 * @param moduleid
	 * @param userid
	 * @param date
	 * @param context
	 */
	public void record(HttpServletRequest request,Long userid,String context){
		TAdmOperationLogs logs=new TAdmOperationLogs();
		logs.setIpAddress(getRemortIP(request)); 
		logs.setOptId(new Long(userid));
		logs.setOptDate(new Date());
		logs.setOperation(context);		
		tadmoperationlogdao_oss.save(logs);
	}
	
	public String getRemortIP(HttpServletRequest request) { 
	    if (request.getHeader("x-forwarded-for") == null) { 
	        return request.getRemoteAddr(); 
	    } 
	    return request.getHeader("x-forwarded-for"); 
	}  
}
