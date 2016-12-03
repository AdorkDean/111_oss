package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TOssOperateLogDAO;
import com.rc.portal.service.TOssOperateLogManager;
import com.rc.portal.vo.TOssOperateLog;
import com.rc.portal.vo.TOssOperateLogExample;

public class TOssOperateLogManagerImpl  implements TOssOperateLogManager {

    private TOssOperateLogDAO tossoperatelogdao;

    public TOssOperateLogManagerImpl() {
        super();
    }
    public void  setTossoperatelogdao(TOssOperateLogDAO tossoperatelogdao){
        this.tossoperatelogdao=tossoperatelogdao;
    }
    public TOssOperateLogDAO getTossoperatelogdao(){
        return this.tossoperatelogdao;
    }
    public     int countByExample(TOssOperateLogExample example) throws SQLException{
        return tossoperatelogdao. countByExample( example);
    }

    public     int deleteByExample(TOssOperateLogExample example) throws SQLException{
        return tossoperatelogdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tossoperatelogdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TOssOperateLog record) throws SQLException{
        return tossoperatelogdao. insert( record);
    }

    public     Long insertSelective(TOssOperateLog record) throws SQLException{
        return tossoperatelogdao. insertSelective( record);
    }

    public     List selectByExample(TOssOperateLogExample example) throws SQLException{
        return tossoperatelogdao. selectByExample( example);
    }

    public     TOssOperateLog selectByPrimaryKey(Long id) throws SQLException{
        return tossoperatelogdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TOssOperateLog record, TOssOperateLogExample example) throws SQLException{
        return tossoperatelogdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TOssOperateLog record, TOssOperateLogExample example) throws SQLException{
        return tossoperatelogdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TOssOperateLog record) throws SQLException{
        return tossoperatelogdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TOssOperateLog record) throws SQLException{
        return tossoperatelogdao. updateByPrimaryKey( record);
    }


}
