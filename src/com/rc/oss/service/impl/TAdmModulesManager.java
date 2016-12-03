package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.impl.TAdmModulesDAO;
import com.rc.oss.service.TAdmModulesManagerItf;
import com.rc.oss.vo.TAdmModules;

/**  
 * @Title: TAdmModulesManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:36:00
 * @version V1.0  
 */

public class TAdmModulesManager implements TAdmModulesManagerItf {
	private TAdmModulesDAO tadmodulesdao_oss;
	
	

	public TAdmModulesDAO getTadmodulesdao_oss() {
		return tadmodulesdao_oss;
	}

	public void setTadmodulesdao_oss(TAdmModulesDAO tadmodulesdao_oss) {
		this.tadmodulesdao_oss = tadmodulesdao_oss;
	}

	@Override
	public void attachClean(TAdmModules instance) {
		// TODO Auto-generated method stub
		tadmodulesdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmModules instance) {
		// TODO Auto-generated method stub
		tadmodulesdao_oss.attachDirty(instance);
	}

	@Override
	public void delete(TAdmModules persistentInstance) {
		// TODO Auto-generated method stub
		tadmodulesdao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findAll();
	}

	@Override
	public List findByDispnum(Object dispnum) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByDispnum(dispnum);
	}

	@Override
	public List findByEditable(Object editable) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByEditable(editable);
	}

	@Override
	public List findByExample(TAdmModules instance) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByExample(instance);
	}

	@Override
	public List findByForLog(Object forLog) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByForLog(forLog);
	}

	@Override
	public TAdmModules findById(Long id) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findById(id);
	}

	@Override
	public List findByMemo(Object memo) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByMemo(memo);
	}

	@Override
	public List findByModuleId(Object moduleId) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByModuleId(moduleId);
	}

	@Override
	public List findByModuleKey(Object moduleKey) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByModuleKey(moduleKey);
	}

	@Override
	public List findByModuleName(Object moduleName) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByModuleName(moduleName);
	}

	@Override
	public List findByModuleType(Object moduleType) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByModuleType(moduleType);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public List findByUrl(Object url) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByUrl(url);
	}

	@Override
	public List findByWebid(Object webid) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.findByWebid(webid);
	}

	@Override
	public TAdmModules merge(TAdmModules detachedInstance) {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmModules transientInstance) {
		// TODO Auto-generated method stub
		tadmodulesdao_oss.save(transientInstance);
	}

	@Override
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmodulesdao_oss.getCriteria();
	}

}
