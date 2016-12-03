package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmRescDAO;
import com.rc.portal.vo.TAdmResc;
import com.rc.portal.vo.TAdmRescExample;

public class TAdmRescDAOImpl implements TAdmRescDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmRescDAOImpl() {
        super();
    }

    public TAdmRescDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmRescExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_RESC.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmRescExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_RESC.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmResc key = new TAdmResc();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_RESC.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmResc record) throws SQLException {
        sqlMapClient.insert("T_ADM_RESC.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmResc record) throws SQLException {
        sqlMapClient.insert("T_ADM_RESC.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmRescExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_RESC.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmResc selectByPrimaryKey(Long id) throws SQLException {
        TAdmResc key = new TAdmResc();
        key.setId(id);
        TAdmResc record = (TAdmResc) sqlMapClient.queryForObject("T_ADM_RESC.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmResc record, TAdmRescExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_RESC.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmResc record, TAdmRescExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_RESC.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmResc record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_RESC.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmResc record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_RESC.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmRescExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmRescExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    
    public PageWraper selectByRepositoryByPage(TAdmRescExample example) throws SQLException {
    	PageWraper pw=null;
    	int count=this.countByRepository(example);
        List list = sqlMapClient.queryForList("T_ADM_RESC.ibatorgenerated_selectByPage", example);
        System.out.println("资源分页查询list.size="+list.size());	
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
        return pw;
    }

	public String selectMax() throws SQLException {
/*		String resString = (String) sqlMapClient.queryForObject("T_ADM_RESC.ibatorgenerated_maxByExample");
		System.out.println(".........................."+resString);*/
		return (String) sqlMapClient.queryForObject("T_ADM_RESC.ibatorgenerated_maxByExample");
	}
    
    
}
