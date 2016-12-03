package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.portal.dao.NbigTableDAO;
import com.rc.portal.vo.NbigTable;
import com.rc.portal.vo.NbigTableExample;

public class NbigTableDAOImpl implements NbigTableDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public NbigTableDAOImpl() {
        super();
    }

    public NbigTableDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(NbigTableExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("nbig_table.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(NbigTableExample example) throws SQLException {
        int rows = sqlMapClient.delete("nbig_table.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        NbigTable key = new NbigTable();
        key.setId(id);
        int rows = sqlMapClient.delete("nbig_table.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(NbigTable record) throws SQLException {
		return (Long)        sqlMapClient.insert("nbig_table.ibatorgenerated_insert", record);
    }

    public Long insertSelective(NbigTable record) throws SQLException {
		return (Long)        sqlMapClient.insert("nbig_table.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(NbigTableExample example) throws SQLException {
        List list = sqlMapClient.queryForList("nbig_table.ibatorgenerated_selectByExample", example);
        return list;
    }

    public NbigTable selectByPrimaryKey(Long id) throws SQLException {
        NbigTable key = new NbigTable();
        key.setId(id);
        NbigTable record = (NbigTable) sqlMapClient.queryForObject("nbig_table.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(NbigTable record, NbigTableExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("nbig_table.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(NbigTable record, NbigTableExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("nbig_table.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(NbigTable record) throws SQLException {
        int rows = sqlMapClient.update("nbig_table.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(NbigTable record) throws SQLException {
        int rows = sqlMapClient.update("nbig_table.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends NbigTableExample {
        private Object record;

        public UpdateByExampleParms(Object record, NbigTableExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	
}
