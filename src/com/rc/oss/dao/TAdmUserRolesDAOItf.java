package com.rc.oss.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmUserRoles;

/**  
 * @Title: TAdmUserRolesDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:48:43
 * @version V1.0  
 */

public interface TAdmUserRolesDAOItf {

	public abstract void save(TAdmUserRoles transientInstance);

	public abstract void delete(TAdmUserRoles persistentInstance);

	public abstract TAdmUserRoles findById(java.lang.Long id);

	public abstract List findByExample(TAdmUserRoles instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract TAdmUserRoles merge(TAdmUserRoles detachedInstance);

	public abstract void attachDirty(TAdmUserRoles instance);

	public abstract void attachClean(TAdmUserRoles instance);

	public Criteria getCriteria();
}