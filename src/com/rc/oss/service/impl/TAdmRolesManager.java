package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmRescRolesDAO;
import com.rc.oss.dao.impl.TAdmRolesDAO;
import com.rc.oss.service.TAdmRolesManagerItf;
import com.rc.oss.vo.TAdmRoles;

/**  
 * @Title: TAdmRolesManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:38:15
 * @version V1.0  
 */

public class TAdmRolesManager implements TAdmRolesManagerItf {
	private TAdmRolesDAO tadmrolesdao_oss;
	private TAdmRescRolesDAO tadmrescroledao_oss;
	


	public TAdmRolesDAO getTadmrolesdao_oss() {
		return tadmrolesdao_oss;
	}

	public void setTadmrolesdao_oss(TAdmRolesDAO tadmrolesdao_oss) {
		this.tadmrolesdao_oss = tadmrolesdao_oss;
	}

	@Override
	public void attachClean(TAdmRoles instance) {
		// TODO Auto-generated method stub
		tadmrolesdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmRoles instance) {
		// TODO Auto-generated method stub
		tadmrolesdao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmRoles persistentInstance) {
		// TODO Auto-generated method stub
		tadmrolesdao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findAll();
	}

	@Override
	public List findByEditable(Object editable) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findByEditable(editable);
	}

	@Override
	public List findByExample(TAdmRoles instance) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findByExample(instance);
	}

	@Override
	public TAdmRoles findById(Long id) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findById(id);
	}

	@Override
	public List findByLastUpdateBy(Object lastUpdateBy) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findByLastUpdateBy(lastUpdateBy);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public List findByRoleDesc(Object roleDesc) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findByRoleDesc(roleDesc);
	}

	@Override
	public List findByRoleName(Object roleName) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findByRoleName(roleName);
	}

	@Override
	public List findByStatus(Object status) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findByStatus(status);
	}

	@Override
	public List findByWebid(Object webid) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.findByWebid(webid);
	}

	@Override
	public TAdmRoles merge(TAdmRoles detachedInstance) {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmRoles transientInstance) {
		// TODO Auto-generated method stub
		tadmrolesdao_oss.save(transientInstance);
	}	
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmrolesdao_oss.getCriteria();
	}

}
