package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.CFeedbackDAO;
import com.rc.portal.vo.CFeedback;
import com.rc.portal.vo.CFeedbackExample;

public class CFeedbackDAOImpl implements CFeedbackDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public CFeedbackDAOImpl() {
        super();
    }

    public CFeedbackDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(CFeedbackExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("c_feedback.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(CFeedbackExample example) throws SQLException {
        int rows = sqlMapClient.delete("c_feedback.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) throws SQLException {
        CFeedback key = new CFeedback();
        key.setId(id);
        int rows = sqlMapClient.delete("c_feedback.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Integer insert(CFeedback record) throws SQLException {
		return (Integer)        sqlMapClient.insert("c_feedback.ibatorgenerated_insert", record);
    }

    public Integer insertSelective(CFeedback record) throws SQLException {
		return (Integer)        sqlMapClient.insert("c_feedback.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(CFeedbackExample example) throws SQLException {
        List list = sqlMapClient.queryForList("c_feedback.ibatorgenerated_selectByExample", example);
        return list;
    }

    public CFeedback selectByPrimaryKey(Integer id) throws SQLException {
        CFeedback key = new CFeedback();
        key.setId(id);
        CFeedback record = (CFeedback) sqlMapClient.queryForObject("c_feedback.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(CFeedback record, CFeedbackExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("c_feedback.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(CFeedback record, CFeedbackExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("c_feedback.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(CFeedback record) throws SQLException {
        int rows = sqlMapClient.update("c_feedback.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(CFeedback record) throws SQLException {
        int rows = sqlMapClient.update("c_feedback.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends CFeedbackExample {
        private Object record;

        public UpdateByExampleParms(Object record, CFeedbackExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(CFeedbackExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("c_feedback.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
