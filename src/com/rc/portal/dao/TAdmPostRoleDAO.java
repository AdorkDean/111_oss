package com.rc.portal.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TAdmPostRole;
import com.rc.portal.vo.TAdmPostRoleExample;

public interface TAdmPostRoleDAO {
    int countByRepository(TAdmPostRoleExample example) throws SQLException;

    int deleteByRepository(TAdmPostRoleExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmPostRole record) throws SQLException;

    void insertSelective(TAdmPostRole record) throws SQLException;

    List selectByRepository(TAdmPostRoleExample example) throws SQLException;

    TAdmPostRole selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmPostRole record, TAdmPostRoleExample example) throws SQLException;

    int updateByRepository(TAdmPostRole record, TAdmPostRoleExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmPostRole record) throws SQLException;

    int updateByPrimaryKey(TAdmPostRole record) throws SQLException;
}
