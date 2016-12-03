package com.rc.oss.service;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmUsers;
import com.rc.oss.vo.TAdmUsersModules;

/**  
 * @Title: TAdmUsersModulesDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:49:16
 * @version V1.0  
 */

public interface TAdmUsersModulesManagerItf {

	public abstract void save(TAdmUsersModules transientInstance);

	public abstract void delete(TAdmUsersModules persistentInstance);

	public abstract TAdmUsersModules findById(java.lang.Long id);

	public abstract List findByExample(TAdmUsersModules instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract TAdmUsersModules merge(TAdmUsersModules detachedInstance);

	public abstract void attachDirty(TAdmUsersModules instance);

	public abstract void attachClean(TAdmUsersModules instance);

	public Criteria getCriteria();
	
	public void saveAdminMoudel(String userid, String[] moduleId, TAdmUsers user,String[]  moduleIds);
}