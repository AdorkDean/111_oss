package com.rc.oss.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmResc;

/**  
 * @Title: TAdmRescDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:47:06
 * @version V1.0  
 */

public interface TAdmRescDAOItf {

	public abstract void save(TAdmResc transientInstance);

	public abstract void delete(TAdmResc persistentInstance);

	public abstract TAdmResc findById(java.lang.Long id);

	public abstract List findByExample(TAdmResc instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByName(Object name);

	public abstract List findByResType(Object resType);

	public abstract List findByResString(Object resString);

	public abstract List findByPriority(Object priority);

	public abstract List findByDescn(Object descn);

	public abstract List findByEditable(Object editable);

	public abstract List findByWebid(Object webid);

	public abstract List findAll();

	public abstract TAdmResc merge(TAdmResc detachedInstance);

	public abstract void attachDirty(TAdmResc instance);

	public abstract void attachClean(TAdmResc instance);

	public Criteria getCriteria();
}