package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.CVersionDAO;
import com.rc.portal.service.CVersionManager;
import com.rc.portal.vo.CVersion;
import com.rc.portal.vo.CVersionExample;

public class CVersionManagerImpl  implements CVersionManager {

    private CVersionDAO cversiondao;

    public CVersionManagerImpl() {
        super();
    }
    public void  setCversiondao(CVersionDAO cversiondao){
        this.cversiondao=cversiondao;
    }
    public CVersionDAO getCversiondao(){
        return this.cversiondao;
    }
    public     int countByExample(CVersionExample example) throws SQLException{
        return cversiondao. countByExample( example);
    }

    public     int deleteByExample(CVersionExample example) throws SQLException{
        return cversiondao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Integer id) throws SQLException{
        return cversiondao. deleteByPrimaryKey( id);
    }

    public     Integer insert(CVersion record) throws SQLException{
        return cversiondao. insert( record);
    }

    public     Integer insertSelective(CVersion record) throws SQLException{
        return cversiondao. insertSelective( record);
    }

    public     List selectByExample(CVersionExample example) throws SQLException{
        return cversiondao. selectByExample( example);
    }

    public     CVersion selectByPrimaryKey(Integer id) throws SQLException{
        return cversiondao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(CVersion record, CVersionExample example) throws SQLException{
        return cversiondao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(CVersion record, CVersionExample example) throws SQLException{
        return cversiondao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(CVersion record) throws SQLException{
        return cversiondao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(CVersion record) throws SQLException{
        return cversiondao. updateByPrimaryKey( record);
    }


}
