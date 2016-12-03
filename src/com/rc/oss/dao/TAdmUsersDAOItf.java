package com.rc.oss.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmUsers;

/**  
 * @Title: TAdmUsersDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:49:03
 * @version V1.0  
 */

public interface TAdmUsersDAOItf {

	public abstract void save(TAdmUsers transientInstance);

	public abstract void delete(TAdmUsers persistentInstance);

	public abstract TAdmUsers findById(java.lang.Long id);

	public abstract List findByExample(TAdmUsers instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByLoginName(Object loginName);

	public abstract List findByName(Object name);

	public abstract List findByDepartment(Object department);

	public abstract List findByPassword(Object password);

	public abstract List findByTelephone(Object telephone);

	public abstract List findByEmail(Object email);

	public abstract List findByUserLevel(Object userLevel);

	public abstract List findByStatus(Object status);

	public abstract List findByAllowDelete(Object allowDelete);

	public abstract List findByIsAdminUser(Object isAdminUser);

	public abstract List findByWebid(Object webid);

	public abstract List findAll();

	public abstract TAdmUsers merge(TAdmUsers detachedInstance);

	public abstract void attachDirty(TAdmUsers instance);

	public abstract void attachClean(TAdmUsers instance);

	public Criteria getCriteria();
}