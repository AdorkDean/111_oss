package com.rc.oss.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmPost;

/**  
 * @Title: TAdmPostDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:46:15
 * @version V1.0  
 */

public interface TAdmPostDAOItf {

	public abstract void save(TAdmPost transientInstance);

	public abstract void delete(TAdmPost persistentInstance);

	public abstract TAdmPost findById(java.lang.Long id);

	public abstract List findByExample(TAdmPost instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByName(Object name);

	public abstract List findByRemark(Object remark);

	public abstract List findByEnable(Object enable);

	public abstract List findByWebid(Object webid);

	public abstract List findAll();

	public abstract TAdmPost merge(TAdmPost detachedInstance);

	public abstract void attachDirty(TAdmPost instance);

	public abstract void attachClean(TAdmPost instance);

	public Criteria getCriteria();
}