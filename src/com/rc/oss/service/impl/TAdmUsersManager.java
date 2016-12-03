package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmUsersDAO;
import com.rc.oss.service.TAdmUsersManagerItf;
import com.rc.oss.vo.TAdmUsers;

/**  
 * @Title: TAdmUsersManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:39:18
 * @version V1.0  
 */

public class TAdmUsersManager implements TAdmUsersManagerItf {
	private TAdmUsersDAO tadmuserdao_oss;
	
	

	public TAdmUsersDAO getTadmuserdao_oss() {
		return tadmuserdao_oss;
	}

	public void setTadmuserdao_oss(TAdmUsersDAO tadmuserdao_oss) {
		this.tadmuserdao_oss = tadmuserdao_oss;
	}

	@Override
	public void attachClean(TAdmUsers instance) {
		// TODO Auto-generated method stub
		tadmuserdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmUsers instance) {
		// TODO Auto-generated method stub
		tadmuserdao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmUsers persistentInstance) {
		// TODO Auto-generated method stub
		tadmuserdao_oss.delete(persistentInstance);
	}	

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findAll();
	}

	@Override
	public List findByAllowDelete(Object allowDelete) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByAllowDelete(allowDelete);
	}

	@Override
	public List findByDepartment(Object department) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByDepartment(department);
	}

	@Override
	public List findByEmail(Object email) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByEmail(email);
	}

	@Override
	public List findByExample(TAdmUsers instance) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByExample(instance);
	}

	@Override
	public TAdmUsers findById(Long id) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findById(id);
	}

	@Override
	public List findByIsAdminUser(Object isAdminUser) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByIsAdminUser(isAdminUser);
	}

	@Override
	public List findByLoginName(Object loginName) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByLoginName(loginName);
	}

	@Override
	public List findByName(Object name) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByName(name);
	}

	@Override
	public List findByPassword(Object password) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByPassword(password);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public List findByStatus(Object status) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByStatus(status);
	}

	@Override
	public List findByTelephone(Object telephone) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByTelephone(telephone);
	}

	@Override
	public List findByUserLevel(Object userLevel) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByUserLevel(userLevel);
	}

	@Override
	public List findByWebid(Object webid) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.findByWebid(webid);
	}

	@Override
	public TAdmUsers merge(TAdmUsers detachedInstance) {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmUsers transientInstance) {
		// TODO Auto-generated method stub
		tadmuserdao_oss.save(transientInstance);
	}	
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmuserdao_oss.getCriteria();
	}

}
