package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TTagDAO;
import com.rc.portal.vo.TTag;
import com.rc.portal.vo.TTagExample;

public class TTagDAOImpl implements TTagDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TTagDAOImpl() {
        super();
    }

    public TTagDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TTagExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_tag.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TTagExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_tag.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TTag key = new TTag();
        key.setId(id);
        int rows = sqlMapClient.delete("t_tag.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TTag record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_tag.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TTag record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_tag.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TTagExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_tag.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TTag selectByPrimaryKey(Long id) throws SQLException {
        TTag key = new TTag();
        key.setId(id);
        TTag record = (TTag) sqlMapClient.queryForObject("t_tag.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TTag record, TTagExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_tag.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TTag record, TTagExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_tag.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TTag record) throws SQLException {
        int rows = sqlMapClient.update("t_tag.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TTag record) throws SQLException {
        int rows = sqlMapClient.update("t_tag.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TTagExample {
        private Object record;

        public UpdateByExampleParms(Object record, TTagExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TTagExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_tag.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
