package com.rc.oss.service;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmPostRole;

/**  
 * @Title: TAdmPostRoleDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:46:36
 * @version V1.0  
 */

public interface TAdmPostRoleManagerItf {

	public abstract void save(TAdmPostRole transientInstance);

	public abstract void delete(TAdmPostRole persistentInstance);

	public abstract TAdmPostRole findById(java.lang.Long id);

	public abstract List findByExample(TAdmPostRole instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract TAdmPostRole merge(TAdmPostRole detachedInstance);

	public abstract void attachDirty(TAdmPostRole instance);

	public abstract void attachClean(TAdmPostRole instance);

	public Criteria getCriteria();
}