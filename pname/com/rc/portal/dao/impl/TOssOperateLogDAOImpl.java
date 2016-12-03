package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TOssOperateLogDAO;
import com.rc.portal.vo.TOssOperateLog;
import com.rc.portal.vo.TOssOperateLogExample;

public class TOssOperateLogDAOImpl implements TOssOperateLogDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TOssOperateLogDAOImpl() {
        super();
    }

    public TOssOperateLogDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TOssOperateLogExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_oss_operate_log.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TOssOperateLogExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_oss_operate_log.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TOssOperateLog key = new TOssOperateLog();
        key.setId(id);
        int rows = sqlMapClient.delete("t_oss_operate_log.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TOssOperateLog record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_oss_operate_log.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TOssOperateLog record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_oss_operate_log.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TOssOperateLogExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_oss_operate_log.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TOssOperateLog selectByPrimaryKey(Long id) throws SQLException {
        TOssOperateLog key = new TOssOperateLog();
        key.setId(id);
        TOssOperateLog record = (TOssOperateLog) sqlMapClient.queryForObject("t_oss_operate_log.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TOssOperateLog record, TOssOperateLogExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_oss_operate_log.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TOssOperateLog record, TOssOperateLogExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_oss_operate_log.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TOssOperateLog record) throws SQLException {
        int rows = sqlMapClient.update("t_oss_operate_log.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TOssOperateLog record) throws SQLException {
        int rows = sqlMapClient.update("t_oss_operate_log.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TOssOperateLogExample {
        private Object record;

        public UpdateByExampleParms(Object record, TOssOperateLogExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TOssOperateLogExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_oss_operate_log.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
