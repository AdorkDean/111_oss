package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmRolesDAO;
import com.rc.portal.service.TAdmRolesManager;
import com.rc.portal.vo.TAdmRoles;
import com.rc.portal.vo.TAdmRolesExample;

public class TAdmRolesManagerImpl  implements TAdmRolesManager {

    private TAdmRolesDAO tadmrolesdao;

    public TAdmRolesManagerImpl() {
        super();
    }
    public void  setTadmrolesdao(TAdmRolesDAO tadmrolesdao){
        this.tadmrolesdao=tadmrolesdao;
    }
    public TAdmRolesDAO getTadmrolesdao(){
        return this.tadmrolesdao;
    }
    public     int countByRepository(TAdmRolesExample example) throws SQLException{
        return tadmrolesdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmRolesExample example) throws SQLException{
        return tadmrolesdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmrolesdao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmRoles record) throws SQLException{
        tadmrolesdao. insert( record);
    }

    public     void insertSelective(TAdmRoles record) throws SQLException{
        tadmrolesdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmRolesExample example) throws SQLException{
        return tadmrolesdao. selectByRepository( example);
    }

    public     TAdmRoles selectByPrimaryKey(Long id) throws SQLException{
        return tadmrolesdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmRoles record, TAdmRolesExample example) throws SQLException{
        return tadmrolesdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmRoles record, TAdmRolesExample example) throws SQLException{
        return tadmrolesdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmRoles record) throws SQLException{
        return tadmrolesdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmRoles record) throws SQLException{
        return tadmrolesdao. updateByPrimaryKey( record);
    }
	@Override
	public PageWraper selectByRepositoryByPage(TAdmRolesExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return tadmrolesdao.selectByRepositoryByPage(example);
	}
}
