package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TSubjectDAO;
import com.rc.portal.vo.TSubject;
import com.rc.portal.vo.TSubjectExample;

public class TSubjectDAOImpl implements TSubjectDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TSubjectDAOImpl() {
        super();
    }

    public TSubjectDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TSubjectExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_subject.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TSubjectExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_subject.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) throws SQLException {
        TSubject key = new TSubject();
        key.setId(id);
        int rows = sqlMapClient.delete("t_subject.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public int insert(TSubject record) throws SQLException {
		return (Integer)        sqlMapClient.insert("t_subject.ibatorgenerated_insert", record);
    }

    public int insertSelective(TSubject record) throws SQLException {
		return (Integer)        sqlMapClient.insert("t_subject.ibatorgenerated_insertSelective", record);
    }

    public List selectByExampleWithBLOBs(TSubjectExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_subject.ibatorgenerated_selectByExampleWithBLOBs", example);
        return list;
    }

    public List selectByExampleWithoutBLOBs(TSubjectExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_subject.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TSubject selectByPrimaryKey(Integer id) throws SQLException {
        TSubject key = new TSubject();
        key.setId(id);
        TSubject record = (TSubject) sqlMapClient.queryForObject("t_subject.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TSubject record, TSubjectExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_subject.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExampleWithBLOBs(TSubject record, TSubjectExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_subject.ibatorgenerated_updateByExampleWithBLOBs", parms);
        return rows;
    }

    public int updateByExampleWithoutBLOBs(TSubject record, TSubjectExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_subject.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TSubject record) throws SQLException {
        int rows = sqlMapClient.update("t_subject.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKeyWithBLOBs(TSubject record) throws SQLException {
        int rows = sqlMapClient.update("t_subject.ibatorgenerated_updateByPrimaryKeyWithBLOBs", record);
        return rows;
    }

    public int updateByPrimaryKeyWithoutBLOBs(TSubject record) throws SQLException {
        int rows = sqlMapClient.update("t_subject.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TSubjectExample {
        private Object record;

        public UpdateByExampleParms(Object record, TSubjectExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TSubjectExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_subject.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
