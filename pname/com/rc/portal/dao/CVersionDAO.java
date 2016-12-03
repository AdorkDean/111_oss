package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.CVersion;
import com.rc.portal.vo.CVersionExample;

public interface CVersionDAO {
    int countByExample(CVersionExample example) throws SQLException;

    int deleteByExample(CVersionExample example) throws SQLException;

    int deleteByPrimaryKey(Integer id) throws SQLException;

    Integer insert(CVersion record) throws SQLException;

    Integer insertSelective(CVersion record) throws SQLException;

    List selectByExample(CVersionExample example) throws SQLException;

    CVersion selectByPrimaryKey(Integer id) throws SQLException;

    int updateByExampleSelective(CVersion record, CVersionExample example) throws SQLException;

    int updateByExample(CVersion record, CVersionExample example) throws SQLException;

    int updateByPrimaryKeySelective(CVersion record) throws SQLException;

    int updateByPrimaryKey(CVersion record) throws SQLException;


}
