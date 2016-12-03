package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TTagDAO;
import com.rc.portal.dao.TTagRelationDAO;
import com.rc.portal.service.TTagManager;
import com.rc.portal.vo.TTag;
import com.rc.portal.vo.TTagExample;
import com.rc.portal.vo.TTagRelationExample;

public class TTagManagerImpl  implements TTagManager {

    private TTagDAO ttagdao;
    
    private TTagRelationDAO ttagrelationdao;

    public TTagManagerImpl() {
        super();
    }
    public void  setTtagdao(TTagDAO ttagdao){
        this.ttagdao=ttagdao;
    }
    public TTagDAO getTtagdao(){
        return this.ttagdao;
    }
    
    public TTagRelationDAO getTtagrelationdao() {
		return ttagrelationdao;
	}
	public void setTtagrelationdao(TTagRelationDAO ttagrelationdao) {
		this.ttagrelationdao = ttagrelationdao;
	}
	public     int countByExample(TTagExample example) throws SQLException{
        return ttagdao. countByExample( example);
    }

    public     int deleteByExample(TTagExample example) throws SQLException{
        return ttagdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
    	
    	TTagRelationExample example = new TTagRelationExample();
    	
    	example.createCriteria().andTagIdEqualTo(id);
    	
    	ttagrelationdao.deleteByExample(example);
    	
        return ttagdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TTag record) throws SQLException{
        return ttagdao. insert( record);
    }

    public     Long insertSelective(TTag record) throws SQLException{
        return ttagdao. insertSelective( record);
    }

    public     List selectByExample(TTagExample example) throws SQLException{
        return ttagdao. selectByExample( example);
    }

    public     TTag selectByPrimaryKey(Long id) throws SQLException{
        return ttagdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TTag record, TTagExample example) throws SQLException{
        return ttagdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TTag record, TTagExample example) throws SQLException{
        return ttagdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TTag record) throws SQLException{
        return ttagdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TTag record) throws SQLException{
        return ttagdao. updateByPrimaryKey( record);
    }


}
