package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TSubjectDAO;
import com.rc.portal.service.TSubjectManager;
import com.rc.portal.vo.TSubject;
import com.rc.portal.vo.TSubjectExample;

public class TSubjectManagerImpl  implements TSubjectManager {

    private TSubjectDAO tsubjectdao;

    public TSubjectManagerImpl() {
        super();
    }
    public void  setTsubjectdao(TSubjectDAO tsubjectdao){
        this.tsubjectdao=tsubjectdao;
    }
    public TSubjectDAO getTsubjectdao(){
        return this.tsubjectdao;
    }
    public     int countByExample(TSubjectExample example) throws SQLException{
        return tsubjectdao. countByExample( example);
    }

    public     int deleteByExample(TSubjectExample example) throws SQLException{
        return tsubjectdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Integer id) throws SQLException{
        return tsubjectdao. deleteByPrimaryKey( id);
    }

    public     int insert(TSubject record) throws SQLException{
        return tsubjectdao. insert( record);
    }

    public     int insertSelective(TSubject record) throws SQLException{
        return tsubjectdao. insertSelective( record);
    }

    public     List selectByExampleWithBLOBs(TSubjectExample example) throws SQLException{
        return tsubjectdao. selectByExampleWithBLOBs( example);
    }

    public     List selectByExampleWithoutBLOBs(TSubjectExample example) throws SQLException{
        return tsubjectdao. selectByExampleWithoutBLOBs( example);
    }

    public     TSubject selectByPrimaryKey(Integer id) throws SQLException{
        return tsubjectdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TSubject record, TSubjectExample example) throws SQLException{
        return tsubjectdao. updateByExampleSelective( record, example);
    }

    public     int updateByExampleWithBLOBs(TSubject record, TSubjectExample example) throws SQLException{
        return tsubjectdao. updateByExampleWithBLOBs( record, example);
    }

    public     int updateByExampleWithoutBLOBs(TSubject record, TSubjectExample example) throws SQLException{
        return tsubjectdao. updateByExampleWithoutBLOBs( record, example);
    }

    public     int updateByPrimaryKeySelective(TSubject record) throws SQLException{
        return tsubjectdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKeyWithBLOBs(TSubject record) throws SQLException{
        return tsubjectdao. updateByPrimaryKeyWithBLOBs( record);
    }

    public     int updateByPrimaryKeyWithoutBLOBs(TSubject record) throws SQLException{
        return tsubjectdao. updateByPrimaryKeyWithoutBLOBs( record);
    }


}
