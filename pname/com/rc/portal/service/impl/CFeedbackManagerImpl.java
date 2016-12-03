package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.CFeedbackDAO;
import com.rc.portal.service.CFeedbackManager;
import com.rc.portal.vo.CFeedback;
import com.rc.portal.vo.CFeedbackExample;

public class CFeedbackManagerImpl  implements CFeedbackManager {

    private CFeedbackDAO cfeedbackdao;

    public CFeedbackManagerImpl() {
        super();
    }
    public void  setCfeedbackdao(CFeedbackDAO cfeedbackdao){
        this.cfeedbackdao=cfeedbackdao;
    }
    public CFeedbackDAO getCfeedbackdao(){
        return this.cfeedbackdao;
    }
    public     int countByExample(CFeedbackExample example) throws SQLException{
        return cfeedbackdao. countByExample( example);
    }

    public     int deleteByExample(CFeedbackExample example) throws SQLException{
        return cfeedbackdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Integer id) throws SQLException{
        return cfeedbackdao. deleteByPrimaryKey( id);
    }

    public     Integer insert(CFeedback record) throws SQLException{
        return cfeedbackdao. insert( record);
    }

    public     Integer insertSelective(CFeedback record) throws SQLException{
        return cfeedbackdao. insertSelective( record);
    }

    public     List selectByExample(CFeedbackExample example) throws SQLException{
        return cfeedbackdao. selectByExample( example);
    }

    public     CFeedback selectByPrimaryKey(Integer id) throws SQLException{
        return cfeedbackdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(CFeedback record, CFeedbackExample example) throws SQLException{
        return cfeedbackdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(CFeedback record, CFeedbackExample example) throws SQLException{
        return cfeedbackdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(CFeedback record) throws SQLException{
        return cfeedbackdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(CFeedback record) throws SQLException{
        return cfeedbackdao. updateByPrimaryKey( record);
    }


}
