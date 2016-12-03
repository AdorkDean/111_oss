package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmRescRolesDAO;
import com.rc.oss.service.TAdmRescRolesManagerItf;
import com.rc.oss.vo.TAdmRescRoles;

/**  
 * @Title: TAdmRescRolesManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:37:58
 * @version V1.0  
 */

public class TAdmRescRolesManager implements TAdmRescRolesManagerItf {
	private TAdmRescRolesDAO tadmrescroledao_oss;
	
	public TAdmRescRolesDAO getTadmrescroledao_oss() {
		return tadmrescroledao_oss;
	}

	public void setTadmrescroledao_oss(TAdmRescRolesDAO tadmrescroledao_oss) {
		this.tadmrescroledao_oss = tadmrescroledao_oss;
	}

	@Override
	public void attachClean(TAdmRescRoles instance) {
		// TODO Auto-generated method stub
		tadmrescroledao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmRescRoles instance) {
		// TODO Auto-generated method stub
		tadmrescroledao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmRescRoles persistentInstance) {
		// TODO Auto-generated method stub
		tadmrescroledao_oss.delete(persistentInstance);
	}
	public void deleteAll(List<TAdmRescRoles> persistentInstances) {
		// TODO Auto-generated method stub
		tadmrescroledao_oss.deleteAll(persistentInstances);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmrescroledao_oss.findAll();
	}

	@Override
	public List findByExample(TAdmRescRoles instance) {
		// TODO Auto-generated method stub
		return tadmrescroledao_oss.findByExample(instance);
	}

	@Override
	public TAdmRescRoles findById(Long id) {
		// TODO Auto-generated method stub
		return tadmrescroledao_oss.findById(id);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmrescroledao_oss.findByProperty(propertyName, value);
	}

	@Override
	public TAdmRescRoles merge(TAdmRescRoles detachedInstance) {
		// TODO Auto-generated method stub
		return tadmrescroledao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmRescRoles transientInstance) {
		// TODO Auto-generated method stub
		tadmrescroledao_oss.save(transientInstance);
	}
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmrescroledao_oss.getCriteria();
	}

}
