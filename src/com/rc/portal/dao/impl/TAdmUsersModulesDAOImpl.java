package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.portal.dao.TAdmUsersModulesDAO;
import com.rc.portal.vo.TAdmUsersModules;
import com.rc.portal.vo.TAdmUsersModulesExample;

public class TAdmUsersModulesDAOImpl implements TAdmUsersModulesDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmUsersModulesDAOImpl() {
        super();
    }

    public TAdmUsersModulesDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmUsersModulesExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_USERS_MODULES.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmUsersModulesExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_USERS_MODULES.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public void insert(TAdmUsersModules record) throws SQLException {
        sqlMapClient.insert("T_ADM_USERS_MODULES.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmUsersModules record) throws SQLException {
        sqlMapClient.insert("T_ADM_USERS_MODULES.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmUsersModulesExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_USERS_MODULES.ibatorgenerated_selectByExample", example);
        return list;
    }

    public int updateByRepositorySelective(TAdmUsersModules record, TAdmUsersModulesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_USERS_MODULES.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmUsersModules record, TAdmUsersModulesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_USERS_MODULES.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmUsersModulesExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmUsersModulesExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
