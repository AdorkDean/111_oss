package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TOssOperateLog;
import com.rc.portal.vo.TOssOperateLogExample;

public interface TOssOperateLogManager {
    int countByExample(TOssOperateLogExample example) throws SQLException;

    int deleteByExample(TOssOperateLogExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TOssOperateLog record) throws SQLException;

    Long insertSelective(TOssOperateLog record) throws SQLException;

    List selectByExample(TOssOperateLogExample example) throws SQLException;

    TOssOperateLog selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TOssOperateLog record, TOssOperateLogExample example) throws SQLException;

    int updateByExample(TOssOperateLog record, TOssOperateLogExample example) throws SQLException;

    int updateByPrimaryKeySelective(TOssOperateLog record) throws SQLException;

    int updateByPrimaryKey(TOssOperateLog record) throws SQLException;



}
