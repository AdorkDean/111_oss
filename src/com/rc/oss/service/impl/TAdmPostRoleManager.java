package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmPostRoleDAO;
import com.rc.oss.service.TAdmPostRoleManagerItf;
import com.rc.oss.vo.TAdmPostRole;

/**  
 * @Title: TAdmPostRoleManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:37:08
 * @version V1.0  
 */

public class TAdmPostRoleManager implements TAdmPostRoleManagerItf {
	private TAdmPostRoleDAO tadmpostroledao_oss;
	
	public TAdmPostRoleDAO getTadmpostroledao_oss() {
		return tadmpostroledao_oss;
	}

	public void setTadmpostroledao_oss(TAdmPostRoleDAO tadmpostroledao_oss) {
		this.tadmpostroledao_oss = tadmpostroledao_oss;
	}

	@Override
	public void attachClean(TAdmPostRole instance) {
		// TODO Auto-generated method stub
		tadmpostroledao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmPostRole instance) {
		// TODO Auto-generated method stub
		tadmpostroledao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmPostRole persistentInstance) {
		// TODO Auto-generated method stub
		tadmpostroledao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmpostroledao_oss.findAll();
	}

	@Override
	public List findByExample(TAdmPostRole instance) {
		// TODO Auto-generated method stub
		return tadmpostroledao_oss.findByExample(instance);
	}

	@Override
	public TAdmPostRole findById(Long id) {
		// TODO Auto-generated method stub
		return tadmpostroledao_oss.findById(id);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmpostroledao_oss.findByProperty(propertyName, value);
	}

	@Override
	public TAdmPostRole merge(TAdmPostRole detachedInstance) {
		// TODO Auto-generated method stub
		return tadmpostroledao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmPostRole transientInstance) {
		// TODO Auto-generated method stub
		tadmpostroledao_oss.save(transientInstance);
	}
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmpostroledao_oss.getCriteria();
	}

	public void deleteAll(List<TAdmPostRole> persistentInstances) {
		// TODO Auto-generated method stub
		tadmpostroledao_oss.deleteAll(persistentInstances);
		
	}

}
