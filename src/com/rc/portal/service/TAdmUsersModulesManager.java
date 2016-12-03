package com.rc.portal.service;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TAdmUsersModules;
import com.rc.portal.vo.TAdmUsersModulesExample;

public interface TAdmUsersModulesManager {
    int countByRepository(TAdmUsersModulesExample example) throws SQLException;

    int deleteByRepository(TAdmUsersModulesExample example) throws SQLException;

    void insert(TAdmUsersModules record) throws SQLException;

    void insertSelective(TAdmUsersModules record) throws SQLException;

    List selectByRepository(TAdmUsersModulesExample example) throws SQLException;

    int updateByRepositorySelective(TAdmUsersModules record, TAdmUsersModulesExample example) throws SQLException;

    int updateByRepository(TAdmUsersModules record, TAdmUsersModulesExample example) throws SQLException;
    
    public void editUserModules(Long userid,String[] moduleids,String[] moduleall)throws Exception;
}
