package com.rc.oss.service;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmOperationLogs;

/**  
 * @Title: TAdmOperationLogsDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:54:04
 * @version V1.0  
 */

public interface TAdmOperationLogsManagerItf {

	public abstract void save(TAdmOperationLogs transientInstance);

	public abstract void delete(TAdmOperationLogs persistentInstance);

	public abstract TAdmOperationLogs findById(java.lang.Long id);

	public abstract List findByExample(TAdmOperationLogs instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByModuleId(Object moduleId);

	public abstract List findByOptType(Object optType);

	public abstract List findByOptId(Object optId);

	public abstract List findByObjType(Object objType);

	public abstract List findByOperation(Object operation);

	public abstract List findByIpAddress(Object ipAddress);

	public abstract List findAll();

	public abstract TAdmOperationLogs merge(TAdmOperationLogs detachedInstance);

	public abstract void attachDirty(TAdmOperationLogs instance);

	public abstract void attachClean(TAdmOperationLogs instance);

	public Criteria getCriteria();
}