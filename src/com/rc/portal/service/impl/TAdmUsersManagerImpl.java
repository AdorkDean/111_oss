package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmUsersDAO;
import com.rc.portal.service.TAdmUsersManager;
import com.rc.portal.vo.TAdmUsers;
import com.rc.portal.vo.TAdmUsersExample;

public class TAdmUsersManagerImpl  implements TAdmUsersManager {

    private TAdmUsersDAO tadmusersdao;

    public TAdmUsersManagerImpl() {
        super();
    }
    public void  setTadmusersdao(TAdmUsersDAO tadmusersdao){
        this.tadmusersdao=tadmusersdao;
    }
    public TAdmUsersDAO getTadmusersdao(){
        return this.tadmusersdao;
    }
    public     int countByRepository(TAdmUsersExample example) throws SQLException{
        return tadmusersdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmUsersExample example) throws SQLException{
        return tadmusersdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmusersdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TAdmUsers record) throws SQLException{
        return tadmusersdao. insert( record);
    }

    public     void insertSelective(TAdmUsers record) throws SQLException{
        tadmusersdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmUsersExample example) throws SQLException{
        return tadmusersdao. selectByRepository( example);
    }

    public     TAdmUsers selectByPrimaryKey(Long id) throws SQLException{
        return tadmusersdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmUsers record, TAdmUsersExample example) throws SQLException{
        return tadmusersdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmUsers record, TAdmUsersExample example) throws SQLException{
        return tadmusersdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmUsers record) throws SQLException{
        return tadmusersdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmUsers record) throws SQLException{
        return tadmusersdao. updateByPrimaryKey( record);
    }
	@Override
	public PageWraper selectByRepositorybypage(TAdmUsersExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return tadmusersdao.selectByRepositorybypage(example);
	}
}
