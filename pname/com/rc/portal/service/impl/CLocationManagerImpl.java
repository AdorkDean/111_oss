package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.CLocationDAO;
import com.rc.portal.service.CLocationManager;
import com.rc.portal.vo.CLocation;
import com.rc.portal.vo.CLocationExample;

public class CLocationManagerImpl  implements CLocationManager {

    private CLocationDAO clocationdao;

    public CLocationManagerImpl() {
        super();
    }
    public void  setClocationdao(CLocationDAO clocationdao){
        this.clocationdao=clocationdao;
    }
    public CLocationDAO getClocationdao(){
        return this.clocationdao;
    }
    public     int countByExample(CLocationExample example) throws SQLException{
        return clocationdao. countByExample( example);
    }

    public     int deleteByExample(CLocationExample example) throws SQLException{
        return clocationdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Integer id) throws SQLException{
        return clocationdao. deleteByPrimaryKey( id);
    }

    public     Long insert(CLocation record) throws SQLException{
        return clocationdao. insert( record);
    }

    public     Long insertSelective(CLocation record) throws SQLException{
        return clocationdao. insertSelective( record);
    }

    public     List selectByExample(CLocationExample example) throws SQLException{
        return clocationdao. selectByExample( example);
    }

    public     CLocation selectByPrimaryKey(Integer id) throws SQLException{
        return clocationdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(CLocation record, CLocationExample example) throws SQLException{
        return clocationdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(CLocation record, CLocationExample example) throws SQLException{
        return clocationdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(CLocation record) throws SQLException{
        return clocationdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(CLocation record) throws SQLException{
        return clocationdao. updateByPrimaryKey( record);
    }


}
