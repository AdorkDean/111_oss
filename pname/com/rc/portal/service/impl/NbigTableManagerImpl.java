package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.NbigTableDAO;
import com.rc.portal.service.NbigTableManager;
import com.rc.portal.vo.NbigTable;
import com.rc.portal.vo.NbigTableExample;

public class NbigTableManagerImpl  implements NbigTableManager {

    private NbigTableDAO nbigtabledao;

    public NbigTableManagerImpl() {
        super();
    }
    public void  setNbigtabledao(NbigTableDAO nbigtabledao){
        this.nbigtabledao=nbigtabledao;
    }
    public NbigTableDAO getNbigtabledao(){
        return this.nbigtabledao;
    }
    public     int countByExample(NbigTableExample example) throws SQLException{
        return nbigtabledao. countByExample( example);
    }

    public     int deleteByExample(NbigTableExample example) throws SQLException{
        return nbigtabledao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return nbigtabledao. deleteByPrimaryKey( id);
    }

    public     Long insert(NbigTable record) throws SQLException{
        return nbigtabledao. insert( record);
    }

    public     Long insertSelective(NbigTable record) throws SQLException{
        return nbigtabledao. insertSelective( record);
    }

    public     List selectByExample(NbigTableExample example) throws SQLException{
        return nbigtabledao. selectByExample( example);
    }

    public     NbigTable selectByPrimaryKey(Long id) throws SQLException{
        return nbigtabledao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(NbigTable record, NbigTableExample example) throws SQLException{
        return nbigtabledao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(NbigTable record, NbigTableExample example) throws SQLException{
        return nbigtabledao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(NbigTable record) throws SQLException{
        return nbigtabledao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(NbigTable record) throws SQLException{
        return nbigtabledao. updateByPrimaryKey( record);
    }


}
