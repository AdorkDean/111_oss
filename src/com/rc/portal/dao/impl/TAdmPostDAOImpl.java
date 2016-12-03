package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmPostDAO;
import com.rc.portal.vo.TAdmPost;
import com.rc.portal.vo.TAdmPostExample;

public class TAdmPostDAOImpl implements TAdmPostDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmPostDAOImpl() {
        super();
    }

    public TAdmPostDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmPostExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_POST.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmPostExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_POST.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmPost key = new TAdmPost();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_POST.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmPost record) throws SQLException {
        sqlMapClient.insert("T_ADM_POST.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmPost record) throws SQLException {
        sqlMapClient.insert("T_ADM_POST.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmPostExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_POST.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmPost selectByPrimaryKey(Long id) throws SQLException {
        TAdmPost key = new TAdmPost();
        key.setId(id);
        TAdmPost record = (TAdmPost) sqlMapClient.queryForObject("T_ADM_POST.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmPost record, TAdmPostExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_POST.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmPost record, TAdmPostExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_POST.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmPost record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_POST.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmPost record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_POST.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmPostExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmPostExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    public PageWraper selectByRepositoryByPage(TAdmPostExample example) throws SQLException {
    	PageWraper pw=null;
    	int count=countByRepository(example);
        List list = sqlMapClient.queryForList("T_ADM_POST.selectByExampleByPage", example);
        System.out.println("岗位分页查询list.size="+list.size());	
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
        return pw;
    }
}
