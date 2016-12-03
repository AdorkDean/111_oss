package com.rc.oss.service;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmRescRoles;

/**  
 * @Title: TAdmRescRolesDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:47:29
 * @version V1.0  
 */

public interface TAdmRescRolesManagerItf {

	public abstract void save(TAdmRescRoles transientInstance);

	public abstract void delete(TAdmRescRoles persistentInstance);

	public abstract TAdmRescRoles findById(java.lang.Long id);

	public abstract List findByExample(TAdmRescRoles instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract TAdmRescRoles merge(TAdmRescRoles detachedInstance);

	public abstract void attachDirty(TAdmRescRoles instance);

	public abstract void attachClean(TAdmRescRoles instance);

	public Criteria getCriteria();
}