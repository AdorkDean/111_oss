package com.rc.oss.service.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.rc.oss.dao.OpenSqlItf;
import com.rc.oss.dao.impl.TAdmModulesDAO;
import com.rc.oss.dao.impl.TAdmUsersModulesDAO;
import com.rc.oss.service.TAdmUsersModulesManagerItf;
import com.rc.oss.vo.TAdmUsers;
import com.rc.oss.vo.TAdmUsersModules;

/**  
 * @Title: TAdmUsersModulesManager.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2013-5-15 下午01:39:45
 * @version V1.0  
 */

public class TAdmUsersModulesManager implements TAdmUsersModulesManagerItf {
	private TAdmUsersModulesDAO tadmusersmodulesdao_oss;
	private OpenSqlItf openSqlDAO;
	private TAdmModulesDAO tadmodulesdao_oss;


	public OpenSqlItf getOpenSqlDAO() {
		return openSqlDAO;
	}

	public void setOpenSqlDAO(OpenSqlItf openSqlDAO) {
		this.openSqlDAO = openSqlDAO;
	}

	public TAdmModulesDAO getTadmodulesdao_oss() {
		return tadmodulesdao_oss;
	}

	public void setTadmodulesdao_oss(TAdmModulesDAO tadmodulesdaoOss) {
		tadmodulesdao_oss = tadmodulesdaoOss;
	}

	public TAdmUsersModulesDAO getTadmusersmodulesdao_oss() {
		return tadmusersmodulesdao_oss;
	}

	public void setTadmusersmodulesdao_oss(TAdmUsersModulesDAO tadmusersmodulesdao_oss) {
		this.tadmusersmodulesdao_oss = tadmusersmodulesdao_oss;
	}

	@Override
	public void attachClean(TAdmUsersModules instance) {
		// TODO Auto-generated method stub
		tadmusersmodulesdao_oss.attachClean(instance);
	}

	@Override
	public void attachDirty(TAdmUsersModules instance) {
		// TODO Auto-generated method stub
		tadmusersmodulesdao_oss.attachDirty(instance);
	}	

	@Override
	public void delete(TAdmUsersModules persistentInstance) {
		// TODO Auto-generated method stub
		tadmusersmodulesdao_oss.delete(persistentInstance);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return tadmusersmodulesdao_oss.findAll();
	}

	@Override
	public List findByExample(TAdmUsersModules instance) {
		// TODO Auto-generated method stub
		return tadmusersmodulesdao_oss.findByExample(instance);
	}

	@Override
	public TAdmUsersModules findById(Long id) {
		// TODO Auto-generated method stub
		return tadmusersmodulesdao_oss.findById(id);
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return tadmusersmodulesdao_oss.findByProperty(propertyName, value);
	}

	@Override
	public TAdmUsersModules merge(TAdmUsersModules detachedInstance) {
		// TODO Auto-generated method stub
		return tadmusersmodulesdao_oss.merge(detachedInstance);
	}

	@Override
	public void save(TAdmUsersModules transientInstance) {
		// TODO Auto-generated method stub
		tadmusersmodulesdao_oss.save(transientInstance);
	}
	
	public Criteria getCriteria() {
		// TODO Auto-generated method stub
		return tadmusersmodulesdao_oss.getCriteria();
	}

	public void deleteAll(List<TAdmUsersModules> persistentInstances) {
		// TODO Auto-generated method stub
		tadmusersmodulesdao_oss.deleteAll(persistentInstances);
	}

	@Override
	public void saveAdminMoudel(String userid, String[] moduleId, TAdmUsers user,String[]  moduleIds) {
		// TODO Auto-generated method stub
		for(String id:moduleIds){
			openSqlDAO.ByDynamicsDeleteHql("delete from TAdmUsersModules p where p.TAdmUsers.id="+Long.parseLong(userid)+" and p.TAdmModules.id="+id);
		}
		
		if(moduleId!=null){
			for(String moduleid:moduleId){
				TAdmUsersModules tr=new TAdmUsersModules();
				tr.setTAdmUsers(user);
				tr.setTAdmModules(tadmodulesdao_oss.findById(new Long(moduleid)));
				tadmusersmodulesdao_oss.save(tr);
			}
		}
	}

}
