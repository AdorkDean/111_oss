package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TCutWordDAO;
import com.rc.portal.service.TCutWordManager;
import com.rc.portal.vo.TCutWord;
import com.rc.portal.vo.TCutWordExample;

public class TCutWordManagerImpl  implements TCutWordManager {

    private TCutWordDAO tcutworddao;

    public TCutWordManagerImpl() {
        super();
    }
    public void  setTcutworddao(TCutWordDAO tcutworddao){
        this.tcutworddao=tcutworddao;
    }
    public TCutWordDAO getTcutworddao(){
        return this.tcutworddao;
    }
    public     int countByExample(TCutWordExample example) throws SQLException{
        return tcutworddao. countByExample( example);
    }

    public     int deleteByExample(TCutWordExample example) throws SQLException{
        return tcutworddao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tcutworddao. deleteByPrimaryKey( id);
    }

    public     Long insert(TCutWord record) throws SQLException{
        return tcutworddao. insert( record);
    }

    public     Long insertSelective(TCutWord record) throws SQLException{
        return tcutworddao. insertSelective( record);
    }

    public     List selectByExample(TCutWordExample example) throws SQLException{
        return tcutworddao. selectByExample( example);
    }

    public     TCutWord selectByPrimaryKey(Long id) throws SQLException{
        return tcutworddao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TCutWord record, TCutWordExample example) throws SQLException{
        return tcutworddao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TCutWord record, TCutWordExample example) throws SQLException{
        return tcutworddao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TCutWord record) throws SQLException{
        return tcutworddao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TCutWord record) throws SQLException{
        return tcutworddao. updateByPrimaryKey( record);
    }


}
