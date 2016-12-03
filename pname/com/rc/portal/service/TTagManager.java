package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TTag;
import com.rc.portal.vo.TTagExample;

public interface TTagManager {
    int countByExample(TTagExample example) throws SQLException;

    int deleteByExample(TTagExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TTag record) throws SQLException;

    Long insertSelective(TTag record) throws SQLException;

    List selectByExample(TTagExample example) throws SQLException;

    TTag selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TTag record, TTagExample example) throws SQLException;

    int updateByExample(TTag record, TTagExample example) throws SQLException;

    int updateByPrimaryKeySelective(TTag record) throws SQLException;

    int updateByPrimaryKey(TTag record) throws SQLException;



}
