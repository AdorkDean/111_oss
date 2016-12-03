package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmUserRolesDAO;
import com.rc.oss.service.TAdmUserRolesManagerItf;
import com.rc.oss.vo.TAdmUserRoles;

/**  
 * @Title: TAdmUserRolesManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:39:02
 * @version V1.0  
 */

public class TAdmUserRolesManager implements TAdmUserRolesManagerItf {
	private TAdmUserRolesDAO tadmuserolesdao_oss;
	
	public TAdmUserRolesDAO getTadmuserolesdao_oss() {
		return tadmuserolesdao_oss;
	}

	public void setTadmuserolesdao_oss(TAdmUserRolesDAO tadmuserolesdao_oss) {
		this.tadmuserolesdao_oss = tadmuserolesdao_oss;
	}

	@Override
	public void attachClean(TAdmUserRoles instance) {
		// TODO Auto-generated method stub
		tadmuserolesdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmUserRoles instance) {
		// TODO Auto-generated method stub
		tadmuserolesdao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmUserRoles persistentInstance) {
		// TODO Auto-generated method stub
		tadmuserolesdao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmuserolesdao_oss.findAll();
	}

	@Override
	public List findByExample(TAdmUserRoles instance) {
		// TODO Auto-generated method stub
		return tadmuserolesdao_oss.findByExample(instance);
	}

	@Override
	public TAdmUserRoles findById(Long id) {
		// TODO Auto-generated method stub
		return tadmuserolesdao_oss.findById(id);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmuserolesdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public TAdmUserRoles merge(TAdmUserRoles detachedInstance) {
		// TODO Auto-generated method stub
		return tadmuserolesdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmUserRoles transientInstance) {
		// TODO Auto-generated method stub
		tadmuserolesdao_oss.save(transientInstance);
	}
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmuserolesdao_oss.getCriteria();
	}

}
