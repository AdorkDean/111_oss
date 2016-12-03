package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.portal.dao.TAdmRescRolesDAO;
import com.rc.portal.vo.TAdmRescRoles;
import com.rc.portal.vo.TAdmRescRolesExample;

public class TAdmRescRolesDAOImpl implements TAdmRescRolesDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmRescRolesDAOImpl() {
        super();
    }

    public TAdmRescRolesDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmRescRolesExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_RESC_ROLES.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmRescRolesExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_RESC_ROLES.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmRescRoles key = new TAdmRescRoles();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_RESC_ROLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmRescRoles record) throws SQLException {
        sqlMapClient.insert("T_ADM_RESC_ROLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmRescRoles record) throws SQLException {
        sqlMapClient.insert("T_ADM_RESC_ROLES.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmRescRolesExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_RESC_ROLES.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmRescRoles selectByPrimaryKey(Long id) throws SQLException {
        TAdmRescRoles key = new TAdmRescRoles();
        key.setId(id);
        TAdmRescRoles record = (TAdmRescRoles) sqlMapClient.queryForObject("T_ADM_RESC_ROLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmRescRoles record, TAdmRescRolesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_RESC_ROLES.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmRescRoles record, TAdmRescRolesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_RESC_ROLES.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmRescRoles record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_RESC_ROLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmRescRoles record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_RESC_ROLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmRescRolesExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmRescRolesExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
