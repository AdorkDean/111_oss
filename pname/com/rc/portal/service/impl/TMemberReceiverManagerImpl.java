package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TMemberReceiverDAO;
import com.rc.portal.service.TMemberReceiverManager;
import com.rc.portal.vo.TMemberReceiver;
import com.rc.portal.vo.TMemberReceiverExample;

public class TMemberReceiverManagerImpl  implements TMemberReceiverManager {

    private TMemberReceiverDAO tmemberreceiverdao;

    public TMemberReceiverManagerImpl() {
        super();
    }
    public void  setTmemberreceiverdao(TMemberReceiverDAO tmemberreceiverdao){
        this.tmemberreceiverdao=tmemberreceiverdao;
    }
    public TMemberReceiverDAO getTmemberreceiverdao(){
        return this.tmemberreceiverdao;
    }
    public     int countByExample(TMemberReceiverExample example) throws SQLException{
        return tmemberreceiverdao. countByExample( example);
    }

    public     int deleteByExample(TMemberReceiverExample example) throws SQLException{
        return tmemberreceiverdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tmemberreceiverdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TMemberReceiver record) throws SQLException{
        return tmemberreceiverdao. insert( record);
    }

    public     Long insertSelective(TMemberReceiver record) throws SQLException{
        return tmemberreceiverdao. insertSelective( record);
    }

    public     List selectByExample(TMemberReceiverExample example) throws SQLException{
        return tmemberreceiverdao. selectByExample( example);
    }

    public     TMemberReceiver selectByPrimaryKey(Long id) throws SQLException{
        return tmemberreceiverdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TMemberReceiver record, TMemberReceiverExample example) throws SQLException{
        return tmemberreceiverdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TMemberReceiver record, TMemberReceiverExample example) throws SQLException{
        return tmemberreceiverdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TMemberReceiver record) throws SQLException{
        return tmemberreceiverdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TMemberReceiver record) throws SQLException{
        return tmemberreceiverdao. updateByPrimaryKey( record);
    }


}
