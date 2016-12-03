package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.portal.dao.TAdmOperationLogsDAO;
import com.rc.portal.vo.TAdmOperationLogs;
import com.rc.portal.vo.TAdmOperationLogsExample;

public class TAdmOperationLogsDAOImpl implements TAdmOperationLogsDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmOperationLogsDAOImpl() {
        super();
    }

    public TAdmOperationLogsDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmOperationLogsExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_OPERATION_LOGS.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmOperationLogsExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_OPERATION_LOGS.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmOperationLogs key = new TAdmOperationLogs();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_OPERATION_LOGS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmOperationLogs record) throws SQLException {
        sqlMapClient.insert("T_ADM_OPERATION_LOGS.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmOperationLogs record) throws SQLException {
        sqlMapClient.insert("T_ADM_OPERATION_LOGS.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmOperationLogsExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_OPERATION_LOGS.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmOperationLogs selectByPrimaryKey(Long id) throws SQLException {
        TAdmOperationLogs key = new TAdmOperationLogs();
        key.setId(id);
        TAdmOperationLogs record = (TAdmOperationLogs) sqlMapClient.queryForObject("T_ADM_OPERATION_LOGS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmOperationLogs record, TAdmOperationLogsExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_OPERATION_LOGS.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmOperationLogs record, TAdmOperationLogsExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_OPERATION_LOGS.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmOperationLogs record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_OPERATION_LOGS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmOperationLogs record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_OPERATION_LOGS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmOperationLogsExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmOperationLogsExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
