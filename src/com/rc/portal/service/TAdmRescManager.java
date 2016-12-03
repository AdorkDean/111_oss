package com.rc.portal.service;

import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.vo.TAdmResc;
import com.rc.portal.vo.TAdmRescExample;

public interface TAdmRescManager {
    int countByRepository(TAdmRescExample example) throws SQLException;

    int deleteByRepository(TAdmRescExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmResc record) throws SQLException;

    void insertSelective(TAdmResc record) throws SQLException;

    List selectByRepository(TAdmRescExample example) throws SQLException;

    TAdmResc selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmResc record, TAdmRescExample example) throws SQLException;

    int updateByRepository(TAdmResc record, TAdmRescExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmResc record) throws SQLException;

    int updateByPrimaryKey(TAdmResc record) throws SQLException;
    
    PageWraper selectByRepositoryByPage(TAdmRescExample example) throws SQLException;
    
    String selectMax()throws SQLException;
}
