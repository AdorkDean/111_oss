package com.rc.portal.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TAdmUsersModules;
import com.rc.portal.vo.TAdmUsersModulesExample;

public interface TAdmUsersModulesDAO {
    int countByRepository(TAdmUsersModulesExample example) throws SQLException;

    int deleteByRepository(TAdmUsersModulesExample example) throws SQLException;

    void insert(TAdmUsersModules record) throws SQLException;

    void insertSelective(TAdmUsersModules record) throws SQLException;

    List selectByRepository(TAdmUsersModulesExample example) throws SQLException;

    int updateByRepositorySelective(TAdmUsersModules record, TAdmUsersModulesExample example) throws SQLException;

    int updateByRepository(TAdmUsersModules record, TAdmUsersModulesExample example) throws SQLException;
}
