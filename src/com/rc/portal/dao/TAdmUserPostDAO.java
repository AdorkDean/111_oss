package com.rc.portal.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TAdmUserPost;
import com.rc.portal.vo.TAdmUserPostExample;

public interface TAdmUserPostDAO {
    int countByRepository(TAdmUserPostExample example) throws SQLException;

    int deleteByRepository(TAdmUserPostExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmUserPost record) throws SQLException;

    void insertSelective(TAdmUserPost record) throws SQLException;

    List selectByRepository(TAdmUserPostExample example) throws SQLException;

    TAdmUserPost selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmUserPost record, TAdmUserPostExample example) throws SQLException;

    int updateByRepository(TAdmUserPost record, TAdmUserPostExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmUserPost record) throws SQLException;

    int updateByPrimaryKey(TAdmUserPost record) throws SQLException;
}
