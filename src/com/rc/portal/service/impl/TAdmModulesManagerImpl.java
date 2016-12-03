package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmModulesDAO;
import com.rc.portal.service.TAdmModulesManager;
import com.rc.portal.vo.TAdmModules;
import com.rc.portal.vo.TAdmModulesExample;

public class TAdmModulesManagerImpl  implements TAdmModulesManager {

    private TAdmModulesDAO tadmmodulesdao;

    public TAdmModulesManagerImpl() {
        super();
    }
    public void  setTadmmodulesdao(TAdmModulesDAO tadmmodulesdao){
        this.tadmmodulesdao=tadmmodulesdao;
    }
    public TAdmModulesDAO getTadmmodulesdao(){
        return this.tadmmodulesdao;
    }
    public     int countByRepository(TAdmModulesExample example) throws SQLException{
        return tadmmodulesdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmModulesExample example) throws SQLException{
        return tadmmodulesdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmmodulesdao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmModules record) throws SQLException{
        tadmmodulesdao. insert( record);
    }

    public     void insertSelective(TAdmModules record) throws SQLException{
        tadmmodulesdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmModulesExample example) throws SQLException{
        return tadmmodulesdao. selectByRepository( example);
    }

    public     TAdmModules selectByPrimaryKey(Long id) throws SQLException{
        return tadmmodulesdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmModules record, TAdmModulesExample example) throws SQLException{
        return tadmmodulesdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmModules record, TAdmModulesExample example) throws SQLException{
        return tadmmodulesdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmModules record) throws SQLException{
        return tadmmodulesdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmModules record) throws SQLException{
        return tadmmodulesdao. updateByPrimaryKey( record);
    }

    
    public PageWraper selectByRepositoryByPage(TAdmModulesExample example) throws SQLException{
    	return tadmmodulesdao.selectByRepositoryByPage(example);
    }
	@Override
	public List selectParentByRepository(TAdmModulesExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return tadmmodulesdao.selectParentByRepository(example);
	}}
