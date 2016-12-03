package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.portal.dao.TAdmPostRoleDAO;
import com.rc.portal.vo.TAdmPostRole;
import com.rc.portal.vo.TAdmPostRoleExample;

public class TAdmPostRoleDAOImpl implements TAdmPostRoleDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
 
    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmPostRoleDAOImpl() {
        super();
    }

    public TAdmPostRoleDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmPostRoleExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_POST_ROLE.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmPostRoleExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_POST_ROLE.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmPostRole key = new TAdmPostRole();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_POST_ROLE.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmPostRole record) throws SQLException {
        sqlMapClient.insert("T_ADM_POST_ROLE.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmPostRole record) throws SQLException {
        sqlMapClient.insert("T_ADM_POST_ROLE.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmPostRoleExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_POST_ROLE.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmPostRole selectByPrimaryKey(Long id) throws SQLException {
        TAdmPostRole key = new TAdmPostRole();
        key.setId(id);
        TAdmPostRole record = (TAdmPostRole) sqlMapClient.queryForObject("T_ADM_POST_ROLE.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmPostRole record, TAdmPostRoleExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_POST_ROLE.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmPostRole record, TAdmPostRoleExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_POST_ROLE.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmPostRole record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_POST_ROLE.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmPostRole record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_POST_ROLE.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmPostRoleExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmPostRoleExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
