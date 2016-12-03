package com.rc.portal.dao;
import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.vo.TAdmModules;
import com.rc.portal.vo.TAdmModulesExample;

public interface TAdmModulesDAO {
    int countByRepository(TAdmModulesExample example) throws SQLException;

    int deleteByRepository(TAdmModulesExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    void insert(TAdmModules record) throws SQLException;

    void insertSelective(TAdmModules record) throws SQLException;

    List selectByRepository(TAdmModulesExample example) throws SQLException;

    TAdmModules selectByPrimaryKey(Long id) throws SQLException;

    int updateByRepositorySelective(TAdmModules record, TAdmModulesExample example) throws SQLException;

    int updateByRepository(TAdmModules record, TAdmModulesExample example) throws SQLException;

    int updateByPrimaryKeySelective(TAdmModules record) throws SQLException;

    int updateByPrimaryKey(TAdmModules record) throws SQLException;
    
    PageWraper selectByRepositoryByPage(TAdmModulesExample example) throws SQLException;
    
    List selectParentByRepository(TAdmModulesExample example) throws SQLException;
}
