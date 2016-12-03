package com.rc.oss.service;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.vo.TAdmRoles;

/**  
 * @Title: TAdmRolesDAOItf.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午12:47:56
 * @version V1.0  
 */

public interface TAdmRolesManagerItf {

	public abstract void save(TAdmRoles transientInstance);

	public abstract void delete(TAdmRoles persistentInstance);

	public abstract TAdmRoles findById(java.lang.Long id);

	public abstract List findByExample(TAdmRoles instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByRoleName(Object roleName);

	public abstract List findByRoleDesc(Object roleDesc);

	public abstract List findByStatus(Object status);

	public abstract List findByLastUpdateBy(Object lastUpdateBy);

	public abstract List findByEditable(Object editable);

	public abstract List findByWebid(Object webid);

	public abstract List findAll();

	public abstract TAdmRoles merge(TAdmRoles detachedInstance);

	public abstract void attachDirty(TAdmRoles instance);

	public abstract void attachClean(TAdmRoles instance);

	public Criteria getCriteria();
}