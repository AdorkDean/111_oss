package com.rc.portal.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.vo.TAdmRoles;
import com.rc.portal.vo.TAdmRolesExample;

public interface TAdmRolesDAO {
    int countByRepository(TAdmRolesExample example) throws SQLException;

    int deleteByRepository(TAdmRolesExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmRoles record) throws SQLException;

    void insertSelective(TAdmRoles record) throws SQLException;

    List selectByRepository(TAdmRolesExample example) throws SQLException;

    TAdmRoles selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmRoles record, TAdmRolesExample example) throws SQLException;

    int updateByRepository(TAdmRoles record, TAdmRolesExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmRoles record) throws SQLException;

    int updateByPrimaryKey(TAdmRoles record) throws SQLException;
    
    PageWraper selectByRepositoryByPage(TAdmRolesExample example) throws SQLException ;
}
