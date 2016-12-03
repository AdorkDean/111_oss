package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.CLocationCityDAO;
import com.rc.portal.service.CLocationCityManager;
import com.rc.portal.vo.CLocationCity;
import com.rc.portal.vo.CLocationCityExample;

public class CLocationCityManagerImpl  implements CLocationCityManager {

    private CLocationCityDAO clocationcitydao;

    public CLocationCityManagerImpl() {
        super();
    }
    public void  setClocationcitydao(CLocationCityDAO clocationcitydao){
        this.clocationcitydao=clocationcitydao;
    }
    public CLocationCityDAO getClocationcitydao(){
        return this.clocationcitydao;
    }
    public     int countByExample(CLocationCityExample example) throws SQLException{
        return clocationcitydao. countByExample( example);
    }

    public     int deleteByExample(CLocationCityExample example) throws SQLException{
        return clocationcitydao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Integer id) throws SQLException{
        return clocationcitydao. deleteByPrimaryKey( id);
    }

    public     Long insert(CLocationCity record) throws SQLException{
        return clocationcitydao. insert( record);
    }

    public     Long insertSelective(CLocationCity record) throws SQLException{
        return clocationcitydao. insertSelective( record);
    }

    public     List selectByExample(CLocationCityExample example) throws SQLException{
        return clocationcitydao. selectByExample( example);
    }

    public     CLocationCity selectByPrimaryKey(Integer id) throws SQLException{
        return clocationcitydao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(CLocationCity record, CLocationCityExample example) throws SQLException{
        return clocationcitydao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(CLocationCity record, CLocationCityExample example) throws SQLException{
        return clocationcitydao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(CLocationCity record) throws SQLException{
        return clocationcitydao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(CLocationCity record) throws SQLException{
        return clocationcitydao. updateByPrimaryKey( record);
    }


}
