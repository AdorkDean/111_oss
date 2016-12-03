package com.rc.portal.service;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TAdmRescRoles;
import com.rc.portal.vo.TAdmRescRolesExample;

public interface TAdmRescRolesManager {
    int countByRepository(TAdmRescRolesExample example) throws SQLException;

    int deleteByRepository(TAdmRescRolesExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmRescRoles record) throws SQLException;

    void insertSelective(TAdmRescRoles record) throws SQLException;

    List selectByRepository(TAdmRescRolesExample example) throws SQLException;

    TAdmRescRoles selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmRescRoles record, TAdmRescRolesExample example) throws SQLException;

    int updateByRepository(TAdmRescRoles record, TAdmRescRolesExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmRescRoles record) throws SQLException;

    int updateByPrimaryKey(TAdmRescRoles record) throws SQLException;
    
    void editRoleResc(Long roleid,String[] rescids,String[] resciddels) throws Exception;
}
