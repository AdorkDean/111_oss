package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.portal.dao.TAdmUserPostDAO;
import com.rc.portal.vo.TAdmUserPost;
import com.rc.portal.vo.TAdmUserPostExample;

public class TAdmUserPostDAOImpl implements TAdmUserPostDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmUserPostDAOImpl() {
        super();
    }

    public TAdmUserPostDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmUserPostExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_USER_POST.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmUserPostExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_USER_POST.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmUserPost key = new TAdmUserPost();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_USER_POST.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmUserPost record) throws SQLException {
        sqlMapClient.insert("T_ADM_USER_POST.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmUserPost record) throws SQLException {
        sqlMapClient.insert("T_ADM_USER_POST.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmUserPostExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_USER_POST.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmUserPost selectByPrimaryKey(Long id) throws SQLException {
        TAdmUserPost key = new TAdmUserPost();
        key.setId(id);
        TAdmUserPost record = (TAdmUserPost) sqlMapClient.queryForObject("T_ADM_USER_POST.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmUserPost record, TAdmUserPostExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_USER_POST.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmUserPost record, TAdmUserPostExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_USER_POST.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmUserPost record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_USER_POST.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmUserPost record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_USER_POST.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmUserPostExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmUserPostExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
