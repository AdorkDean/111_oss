package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmPostDAO;
import com.rc.oss.service.TAdmPostManagerItf;
import com.rc.oss.vo.TAdmPost;

/**  
 * @Title: TAdmPostManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:36:33
 * @version V1.0  
 */

public class TAdmPostManager implements TAdmPostManagerItf {
	private TAdmPostDAO tadmpostdao_oss;
	


	public TAdmPostDAO getTadmpostdao_oss() {
		return tadmpostdao_oss;
	}

	public void setTadmpostdao_oss(TAdmPostDAO tadmpostdao_oss) {
		this.tadmpostdao_oss = tadmpostdao_oss;
	}

	@Override
	public void attachClean(TAdmPost instance) {
		// TODO Auto-generated method stub
		tadmpostdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmPost instance) {
		// TODO Auto-generated method stub
		tadmpostdao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmPost persistentInstance) {
		// TODO Auto-generated method stub
		tadmpostdao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.findAll();
	}

	@Override
	public List findByEnable(Object enable) {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.findByEnable(enable);
	}

	@Override
	public List findByExample(TAdmPost instance) {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.findByExample(instance);
	}

	@Override
	public TAdmPost findById(Long id) {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.findById(id);
	}

	@Override
	public List findByName(Object name) {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.findByName(name);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public List findByRemark(Object remark) {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.findByRemark(remark);
	}

	@Override
	public List findByWebid(Object webid) {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.findByWebid(webid);
	}

	@Override
	public TAdmPost merge(TAdmPost detachedInstance) {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmPost transientInstance) {
		// TODO Auto-generated method stub
		tadmpostdao_oss.save(transientInstance);
	}
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmpostdao_oss.getCriteria();
	}

}
