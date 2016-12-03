package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TCategoryImgDAO;
import com.rc.portal.service.TCategoryImgManager;
import com.rc.portal.vo.TCategoryImg;
import com.rc.portal.vo.TCategoryImgExample;

public class TCategoryImgManagerImpl  implements TCategoryImgManager {

    private TCategoryImgDAO tcategoryimgdao;

    public TCategoryImgManagerImpl() {
        super();
    }
    public void  setTcategoryimgdao(TCategoryImgDAO tcategoryimgdao){
        this.tcategoryimgdao=tcategoryimgdao;
    }
    public TCategoryImgDAO getTcategoryimgdao(){
        return this.tcategoryimgdao;
    }
    public     int countByExample(TCategoryImgExample example) throws SQLException{
        return tcategoryimgdao. countByExample( example);
    }

    public     int deleteByExample(TCategoryImgExample example) throws SQLException{
        return tcategoryimgdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tcategoryimgdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TCategoryImg record) throws SQLException{
        return tcategoryimgdao. insert( record);
    }

    public     Long insertSelective(TCategoryImg record) throws SQLException{
        return tcategoryimgdao. insertSelective( record);
    }

    public     List selectByExample(TCategoryImgExample example) throws SQLException{
        return tcategoryimgdao. selectByExample( example);
    }

    public     TCategoryImg selectByPrimaryKey(Long id) throws SQLException{
        return tcategoryimgdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TCategoryImg record, TCategoryImgExample example) throws SQLException{
        return tcategoryimgdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TCategoryImg record, TCategoryImgExample example) throws SQLException{
        return tcategoryimgdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TCategoryImg record) throws SQLException{
        return tcategoryimgdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TCategoryImg record) throws SQLException{
        return tcategoryimgdao. updateByPrimaryKey( record);
    }


}
