package com.rc.portal.service;

import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.vo.TAdmUsers;
import com.rc.portal.vo.TAdmUsersExample;

public interface TAdmUsersManager {
    int countByRepository(TAdmUsersExample example) throws SQLException;

    int deleteByRepository(TAdmUsersExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TAdmUsers record) throws SQLException;

    void insertSelective(TAdmUsers record) throws SQLException;

    List selectByRepository(TAdmUsersExample example) throws SQLException;

    TAdmUsers selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmUsers record, TAdmUsersExample example) throws SQLException;

    int updateByRepository(TAdmUsers record, TAdmUsersExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmUsers record) throws SQLException;

    int updateByPrimaryKey(TAdmUsers record) throws SQLException;
    
    PageWraper selectByRepositorybypage(TAdmUsersExample example) throws SQLException;
}
