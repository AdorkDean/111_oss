package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.CVersionDAO;
import com.rc.portal.vo.CVersion;
import com.rc.portal.vo.CVersionExample;

public class CVersionDAOImpl implements CVersionDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public CVersionDAOImpl() {
        super();
    }

    public CVersionDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(CVersionExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("c_version.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(CVersionExample example) throws SQLException {
        int rows = sqlMapClient.delete("c_version.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) throws SQLException {
        CVersion key = new CVersion();
        key.setId(id);
        int rows = sqlMapClient.delete("c_version.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Integer insert(CVersion record) throws SQLException {
		return (Integer)        sqlMapClient.insert("c_version.ibatorgenerated_insert", record);
    }

    public Integer insertSelective(CVersion record) throws SQLException {
		return (Integer)        sqlMapClient.insert("c_version.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(CVersionExample example) throws SQLException {
        List list = sqlMapClient.queryForList("c_version.ibatorgenerated_selectByExample", example);
        return list;
    }

    public CVersion selectByPrimaryKey(Integer id) throws SQLException {
        CVersion key = new CVersion();
        key.setId(id);
        CVersion record = (CVersion) sqlMapClient.queryForObject("c_version.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(CVersion record, CVersionExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("c_version.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(CVersion record, CVersionExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("c_version.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(CVersion record) throws SQLException {
        int rows = sqlMapClient.update("c_version.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(CVersion record) throws SQLException {
        int rows = sqlMapClient.update("c_version.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends CVersionExample {
        private Object record;

        public UpdateByExampleParms(Object record, CVersionExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(CVersionExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("c_version.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
