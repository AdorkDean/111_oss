package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmRolesDAO;
import com.rc.portal.vo.TAdmRoles;
import com.rc.portal.vo.TAdmRolesExample;

public class TAdmRolesDAOImpl implements TAdmRolesDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmRolesDAOImpl() {
        super();
    }

    public TAdmRolesDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmRolesExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_ROLES.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmRolesExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_ROLES.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmRoles key = new TAdmRoles();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_ROLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmRoles record) throws SQLException {
        sqlMapClient.insert("T_ADM_ROLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmRoles record) throws SQLException {
        sqlMapClient.insert("T_ADM_ROLES.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmRolesExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_ROLES.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmRoles selectByPrimaryKey(Long id) throws SQLException {
        TAdmRoles key = new TAdmRoles();
        key.setId(id);
        TAdmRoles record = (TAdmRoles) sqlMapClient.queryForObject("T_ADM_ROLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmRoles record, TAdmRolesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_ROLES.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmRoles record, TAdmRolesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_ROLES.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmRoles record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_ROLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmRoles record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_ROLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmRolesExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmRolesExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    
    public PageWraper selectByRepositoryByPage(TAdmRolesExample example) throws SQLException {
    	PageWraper pw=null;
    	int count=this.countByRepository(example);
        List list = sqlMapClient.queryForList("T_ADM_ROLES.ibatorgenerated_selectByPage", example);
        System.out.println("模块分页查询list.size="+list.size());	
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
        return pw;
    }
}
