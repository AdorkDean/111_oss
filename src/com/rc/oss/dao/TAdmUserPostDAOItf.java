package com.rc.oss.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmUserPost;

/**  
 * @Title: TAdmUserPostDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:48:27
 * @version V1.0  
 */

public interface TAdmUserPostDAOItf {

	public abstract void save(TAdmUserPost transientInstance);

	public abstract void delete(TAdmUserPost persistentInstance);

	public abstract TAdmUserPost findById(java.lang.Long id);

	public abstract List findByExample(TAdmUserPost instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract TAdmUserPost merge(TAdmUserPost detachedInstance);

	public abstract void attachDirty(TAdmUserPost instance);

	public abstract void attachClean(TAdmUserPost instance);

	public Criteria getCriteria();
	public abstract void deleteAll(List<TAdmUserPost> persistentInstances) ;
}