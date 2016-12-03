package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.portal.vo.TTagRelation;
import com.rc.portal.vo.TTagRelationExample;

public interface TTagRelationDAO {
    int countByExample(TTagRelationExample example) throws SQLException;

    int deleteByExample(TTagRelationExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TTagRelation record) throws SQLException;

    Long insertSelective(TTagRelation record) throws SQLException;

    List selectByExample(TTagRelationExample example) throws SQLException;

    TTagRelation selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TTagRelation record, TTagRelationExample example) throws SQLException;

    int updateByExample(TTagRelation record, TTagRelationExample example) throws SQLException;

    int updateByPrimaryKeySelective(TTagRelation record) throws SQLException;

    int updateByPrimaryKey(TTagRelation record) throws SQLException;

    Long insertList(Map param) throws SQLException;
}
