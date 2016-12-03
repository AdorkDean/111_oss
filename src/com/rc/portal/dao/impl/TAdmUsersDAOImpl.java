package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmUsersDAO;
import com.rc.portal.vo.TAdmUsers;
import com.rc.portal.vo.TAdmUsersExample;

public class TAdmUsersDAOImpl implements TAdmUsersDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmUsersDAOImpl() {
        super();
    }

    public TAdmUsersDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmUsersExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_USERS.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmUsersExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_USERS.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmUsers key = new TAdmUsers();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_USERS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TAdmUsers record) throws SQLException {
        return (Long)sqlMapClient.insert("T_ADM_USERS.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmUsers record) throws SQLException {
        sqlMapClient.insert("T_ADM_USERS.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmUsersExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_USERS.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmUsers selectByPrimaryKey(Long id) throws SQLException {
        TAdmUsers key = new TAdmUsers();
        key.setId(id);
        TAdmUsers record = (TAdmUsers) sqlMapClient.queryForObject("T_ADM_USERS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmUsers record, TAdmUsersExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_USERS.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmUsers record, TAdmUsersExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_USERS.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmUsers record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_USERS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmUsers record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_USERS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmUsersExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmUsersExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    
    public PageWraper selectByRepositorybypage(TAdmUsersExample example) throws SQLException {
    	PageWraper pw=null;
    	int count=this.countByRepository(example);
        List list = sqlMapClient.queryForList("T_ADM_USERS.ibatorgenerated_selectByPages", example);
        System.out.println("用户分页查询list.size="+list.size());	
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
        return pw;
    } 
}
