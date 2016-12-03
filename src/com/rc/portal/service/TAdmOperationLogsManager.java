package com.rc.portal.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rc.portal.vo.TAdmOperationLogs;
import com.rc.portal.vo.TAdmOperationLogsExample;

public interface TAdmOperationLogsManager {
    int countByRepository(TAdmOperationLogsExample example) throws SQLException;

    int deleteByRepository(TAdmOperationLogsExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmOperationLogs record) throws SQLException;

    void insertSelective(TAdmOperationLogs record) throws SQLException;

    List selectByRepository(TAdmOperationLogsExample example) throws SQLException;

    TAdmOperationLogs selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmOperationLogs record, TAdmOperationLogsExample example) throws SQLException;

    int updateByRepository(TAdmOperationLogs record, TAdmOperationLogsExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmOperationLogs record) throws SQLException;

    int updateByPrimaryKey(TAdmOperationLogs record) throws SQLException;
    
    void record(HttpServletRequest request,String context);
    
    void record(HttpServletRequest request,Long userid,String context);
}
