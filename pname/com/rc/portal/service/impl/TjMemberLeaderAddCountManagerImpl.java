package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TjMemberLeaderAddCountDAO;
import com.rc.portal.service.TjMemberLeaderAddCountManager;
import com.rc.portal.vo.TjMemberLeaderAddCount;
import com.rc.portal.vo.TjMemberLeaderAddCountExample;

public class TjMemberLeaderAddCountManagerImpl  implements TjMemberLeaderAddCountManager {

    private TjMemberLeaderAddCountDAO tjmemberleaderaddcountdao;

    public TjMemberLeaderAddCountManagerImpl() {
        super();
    }
    public void  setTjmemberleaderaddcountdao(TjMemberLeaderAddCountDAO tjmemberleaderaddcountdao){
        this.tjmemberleaderaddcountdao=tjmemberleaderaddcountdao;
    }
    public TjMemberLeaderAddCountDAO getTjmemberleaderaddcountdao(){
        return this.tjmemberleaderaddcountdao;
    }
    public     int countByExample(TjMemberLeaderAddCountExample example) throws SQLException{
        return tjmemberleaderaddcountdao. countByExample( example);
    }

    public     int deleteByExample(TjMemberLeaderAddCountExample example) throws SQLException{
        return tjmemberleaderaddcountdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tjmemberleaderaddcountdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TjMemberLeaderAddCount record) throws SQLException{
        return tjmemberleaderaddcountdao. insert( record);
    }

    public     Long insertSelective(TjMemberLeaderAddCount record) throws SQLException{
        return tjmemberleaderaddcountdao. insertSelective( record);
    }

    public     List selectByExample(TjMemberLeaderAddCountExample example) throws SQLException{
        return tjmemberleaderaddcountdao. selectByExample( example);
    }

    public     TjMemberLeaderAddCount selectByPrimaryKey(Long id) throws SQLException{
        return tjmemberleaderaddcountdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TjMemberLeaderAddCount record, TjMemberLeaderAddCountExample example) throws SQLException{
        return tjmemberleaderaddcountdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TjMemberLeaderAddCount record, TjMemberLeaderAddCountExample example) throws SQLException{
        return tjmemberleaderaddcountdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TjMemberLeaderAddCount record) throws SQLException{
        return tjmemberleaderaddcountdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TjMemberLeaderAddCount record) throws SQLException{
        return tjmemberleaderaddcountdao. updateByPrimaryKey( record);
    }


}
