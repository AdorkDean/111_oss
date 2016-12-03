package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TCutWordDAO;
import com.rc.portal.vo.TCutWord;
import com.rc.portal.vo.TCutWordExample;

public class TCutWordDAOImpl implements TCutWordDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TCutWordDAOImpl() {
        super();
    }

    public TCutWordDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TCutWordExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_cut_word.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TCutWordExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_cut_word.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TCutWord key = new TCutWord();
        key.setId(id);
        int rows = sqlMapClient.delete("t_cut_word.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TCutWord record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_cut_word.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TCutWord record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_cut_word.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TCutWordExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_cut_word.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TCutWord selectByPrimaryKey(Long id) throws SQLException {
        TCutWord key = new TCutWord();
        key.setId(id);
        TCutWord record = (TCutWord) sqlMapClient.queryForObject("t_cut_word.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TCutWord record, TCutWordExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_cut_word.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TCutWord record, TCutWordExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_cut_word.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TCutWord record) throws SQLException {
        int rows = sqlMapClient.update("t_cut_word.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TCutWord record) throws SQLException {
        int rows = sqlMapClient.update("t_cut_word.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TCutWordExample {
        private Object record;

        public UpdateByExampleParms(Object record, TCutWordExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TCutWordExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_cut_word.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
