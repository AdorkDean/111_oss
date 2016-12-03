package com.rc.portal.service;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TAdmUserRoles;
import com.rc.portal.vo.TAdmUserRolesExample;

public interface TAdmUserRolesManager {
    int countByRepository(TAdmUserRolesExample example) throws SQLException;

    int deleteByRepository(TAdmUserRolesExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmUserRoles record) throws SQLException;

    void insertSelective(TAdmUserRoles record) throws SQLException;

    List selectByRepository(TAdmUserRolesExample example) throws SQLException;

    TAdmUserRoles selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmUserRoles record, TAdmUserRolesExample example) throws SQLException;

    int updateByRepository(TAdmUserRoles record, TAdmUserRolesExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmUserRoles record) throws SQLException;

    int updateByPrimaryKey(TAdmUserRoles record) throws SQLException;
    
    void afford_login_role(Long userid,Long operateUserid) throws SQLException;
    
    void editUserRoles(Long userid,String[] roleids,String[] showroleids)throws Exception;
}
