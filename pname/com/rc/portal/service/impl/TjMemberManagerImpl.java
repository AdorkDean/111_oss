package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TjMemberDAO;
import com.rc.portal.service.TjMemberManager;
import com.rc.portal.vo.TjMember;
import com.rc.portal.vo.TjMemberExample;

public class TjMemberManagerImpl  implements TjMemberManager {

    private TjMemberDAO tjmemberdao;

    public TjMemberManagerImpl() {
        super();
    }
    public void  setTjmemberdao(TjMemberDAO tjmemberdao){
        this.tjmemberdao=tjmemberdao;
    }
    public TjMemberDAO getTjmemberdao(){
        return this.tjmemberdao;
    }
    public     int countByExample(TjMemberExample example) throws SQLException{
        return tjmemberdao. countByExample( example);
    }

    public     int deleteByExample(TjMemberExample example) throws SQLException{
        return tjmemberdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tjmemberdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TjMember record) throws SQLException{
        return tjmemberdao. insert( record);
    }

    public     Long insertSelective(TjMember record) throws SQLException{
        return tjmemberdao. insertSelective( record);
    }

    public     List selectByExample(TjMemberExample example) throws SQLException{
        return tjmemberdao. selectByExample( example);
    }

    public     TjMember selectByPrimaryKey(Long id) throws SQLException{
        return tjmemberdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TjMember record, TjMemberExample example) throws SQLException{
        return tjmemberdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TjMember record, TjMemberExample example) throws SQLException{
        return tjmemberdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TjMember record) throws SQLException{
        return tjmemberdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TjMember record) throws SQLException{
        return tjmemberdao. updateByPrimaryKey( record);
    }


}
