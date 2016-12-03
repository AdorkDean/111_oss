package com.rc.oss.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmModules;

/**  
 * @Title: TAdmModulesDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:43:23
 * @version V1.0  
 */

public interface TAdmModulesDAOItf {

	public abstract void save(TAdmModules transientInstance);

	public abstract void delete(TAdmModules persistentInstance);

	public abstract TAdmModules findById(java.lang.Long id);

	public abstract List findByExample(TAdmModules instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByModuleName(Object moduleName);

	public abstract List findByMemo(Object memo);

	public abstract List findByModuleType(Object moduleType);

	public abstract List findByForLog(Object forLog);

	public abstract List findByUrl(Object url);

	public abstract List findByModuleId(Object moduleId);

	public abstract List findByDispnum(Object dispnum);

	public abstract List findByModuleKey(Object moduleKey);

	public abstract List findByEditable(Object editable);

	public abstract List findByWebid(Object webid);

	public abstract List findAll();

	public abstract TAdmModules merge(TAdmModules detachedInstance);

	public abstract void attachDirty(TAdmModules instance);

	public abstract void attachClean(TAdmModules instance);

	public Criteria getCriteria();
}