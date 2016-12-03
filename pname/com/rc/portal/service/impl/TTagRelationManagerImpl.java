package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.TTagRelationDAO;
import com.rc.portal.service.TTagRelationManager;
import com.rc.portal.vo.TTagRelation;
import com.rc.portal.vo.TTagRelationExample;

public class TTagRelationManagerImpl  implements TTagRelationManager {

    private TTagRelationDAO ttagrelationdao;

    public TTagRelationManagerImpl() {
        super();
    }
    public void  setTtagrelationdao(TTagRelationDAO ttagrelationdao){
        this.ttagrelationdao=ttagrelationdao;
    }
    public TTagRelationDAO getTtagrelationdao(){
        return this.ttagrelationdao;
    }
    public     int countByExample(TTagRelationExample example) throws SQLException{
        return ttagrelationdao. countByExample( example);
    }

    public     int deleteByExample(TTagRelationExample example) throws SQLException{
        return ttagrelationdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return ttagrelationdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TTagRelation record) throws SQLException{
        return ttagrelationdao. insert( record);
    }

    public     Long insertSelective(TTagRelation record) throws SQLException{
        return ttagrelationdao. insertSelective( record);
    }

    public     List selectByExample(TTagRelationExample example) throws SQLException{
        return ttagrelationdao. selectByExample( example);
    }

    public     TTagRelation selectByPrimaryKey(Long id) throws SQLException{
        return ttagrelationdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TTagRelation record, TTagRelationExample example) throws SQLException{
        return ttagrelationdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TTagRelation record, TTagRelationExample example) throws SQLException{
        return ttagrelationdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TTagRelation record) throws SQLException{
        return ttagrelationdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TTagRelation record) throws SQLException{
        return ttagrelationdao. updateByPrimaryKey( record);
    }


    public Long insertList(Map param) throws SQLException{
    	return ttagrelationdao. insertList(param);
    }
}
