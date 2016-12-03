package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmUserPostDAO;
import com.rc.oss.service.TAdmUserPostManagerItf;
import com.rc.oss.vo.TAdmUserPost;

/**  
 * @Title: TAdmUserPostManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:38:46
 * @version V1.0  
 */

public class TAdmUserPostManager implements TAdmUserPostManagerItf {
	private TAdmUserPostDAO tadmuserpostdao_oss;
	
	

	public TAdmUserPostDAO getTadmuserpostdao_oss() {
		return tadmuserpostdao_oss;
	}

	public void setTadmuserpostdao_oss(TAdmUserPostDAO tadmuserpostdao_oss) {
		this.tadmuserpostdao_oss = tadmuserpostdao_oss;
	}

	@Override
	public void attachClean(TAdmUserPost instance) {
		// TODO Auto-generated method stub
		tadmuserpostdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmUserPost instance) {
		// TODO Auto-generated method stub
		tadmuserpostdao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmUserPost persistentInstance) {
		// TODO Auto-generated method stub
		tadmuserpostdao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmuserpostdao_oss.findAll();
	}

	@Override
	public List findByExample(TAdmUserPost instance) {
		// TODO Auto-generated method stub
		return tadmuserpostdao_oss.findByExample(instance);
	}

	@Override
	public TAdmUserPost findById(Long id) {
		// TODO Auto-generated method stub
		return tadmuserpostdao_oss.findById(id);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmuserpostdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public TAdmUserPost merge(TAdmUserPost detachedInstance) {
		// TODO Auto-generated method stub
		return tadmuserpostdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmUserPost transientInstance) {
		// TODO Auto-generated method stub
		tadmuserpostdao_oss.save(transientInstance);
	}
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmuserpostdao_oss.getCriteria();
	}

	public void deleteAll(List<TAdmUserPost> persistentInstances) {
		// TODO Auto-generated method stub
		tadmuserpostdao_oss.deleteAll(persistentInstances);
	}

}
