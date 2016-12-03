package com.rc.portal.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.vo.TAdmPost;
import com.rc.portal.vo.TAdmPostExample;

public interface TAdmPostDAO {
    int countByRepository(TAdmPostExample example) throws SQLException;

    int deleteByRepository(TAdmPostExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmPost record) throws SQLException;

    void insertSelective(TAdmPost record) throws SQLException;

    List selectByRepository(TAdmPostExample example) throws SQLException;

    TAdmPost selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmPost record, TAdmPostExample example) throws SQLException;

    int updateByRepository(TAdmPost record, TAdmPostExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmPost record) throws SQLException;

    int updateByPrimaryKey(TAdmPost record) throws SQLException;
    
    public PageWraper selectByRepositoryByPage(TAdmPostExample example) throws SQLException;
}
