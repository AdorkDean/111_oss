package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmRescDAO;
import com.rc.oss.service.TAdmRescManagerItf;
import com.rc.oss.vo.TAdmResc;

/**  
 * @Title: TAdmRescManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:37:41
 * @version V1.0  
 */

public class TAdmRescManager implements TAdmRescManagerItf {
	private TAdmRescDAO tadmrescdao_oss;
	
	public TAdmRescDAO getTadmrescdao_oss() {
		return tadmrescdao_oss;
	}

	public void setTadmrescdao_oss(TAdmRescDAO tadmrescdao_oss) {
		this.tadmrescdao_oss = tadmrescdao_oss;
	}

	@Override
	public void attachClean(TAdmResc instance) {
		// TODO Auto-generated method stub
		tadmrescdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmResc instance) {
		// TODO Auto-generated method stub
		tadmrescdao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmResc persistentInstance) {
		// TODO Auto-generated method stub
		tadmrescdao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findAll();
	}

	@Override
	public List findByDescn(Object descn) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByDescn(descn);
	}

	@Override
	public List findByEditable(Object editable) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByEditable(editable);
	}

	@Override
	public List findByExample(TAdmResc instance) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByExample(instance);
	}

	@Override
	public TAdmResc findById(Long id) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findById(id);
	}

	@Override
	public List findByName(Object name) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByName(name);
	}

	@Override
	public List findByPriority(Object priority) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByPriority(priority);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public List findByResString(Object resString) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByResString(resString);
	}

	@Override
	public List findByResType(Object resType) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByResType(resType);
	}

	@Override
	public List findByWebid(Object webid) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.findByWebid(webid);
	}

	@Override
	public TAdmResc merge(TAdmResc detachedInstance) {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmResc transientInstance) {
		// TODO Auto-generated method stub
		tadmrescdao_oss.save(transientInstance);
	}	
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmrescdao_oss.getCriteria();
	}

}
