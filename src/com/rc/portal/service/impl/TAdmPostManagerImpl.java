package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmPostDAO;
import com.rc.portal.service.TAdmPostManager;
import com.rc.portal.vo.TAdmPost;
import com.rc.portal.vo.TAdmPostExample;

public class TAdmPostManagerImpl  implements TAdmPostManager {

    private TAdmPostDAO tadmpostdao;

    public TAdmPostManagerImpl() {
        super();
    }
    public void  setTadmpostdao(TAdmPostDAO tadmpostdao){
        this.tadmpostdao=tadmpostdao;
    }
    public TAdmPostDAO getTadmpostdao(){
        return this.tadmpostdao;
    }
    public     int countByRepository(TAdmPostExample example) throws SQLException{
        return tadmpostdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmPostExample example) throws SQLException{
        return tadmpostdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmpostdao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmPost record) throws SQLException{
        tadmpostdao. insert( record);
    }

    public     void insertSelective(TAdmPost record) throws SQLException{
        tadmpostdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmPostExample example) throws SQLException{
        return tadmpostdao. selectByRepository( example);
    }

    public     TAdmPost selectByPrimaryKey(Long id) throws SQLException{
        return tadmpostdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmPost record, TAdmPostExample example) throws SQLException{
        return tadmpostdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmPost record, TAdmPostExample example) throws SQLException{
        return tadmpostdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmPost record) throws SQLException{
        return tadmpostdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmPost record) throws SQLException{
        return tadmpostdao. updateByPrimaryKey( record);
    }
    
	public PageWraper selectByRepositoryByPage(TAdmPostExample example) throws SQLException {
		return tadmpostdao.selectByRepositoryByPage(example);
	}
}
