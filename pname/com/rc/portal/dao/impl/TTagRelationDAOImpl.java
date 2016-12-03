package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TTagRelationDAO;
import com.rc.portal.vo.TTagRelation;
import com.rc.portal.vo.TTagRelationExample;

public class TTagRelationDAOImpl implements TTagRelationDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TTagRelationDAOImpl() {
        super();
    }

    public TTagRelationDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TTagRelationExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_tag_relation.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TTagRelationExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_tag_relation.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TTagRelation key = new TTagRelation();
        key.setId(id);
        int rows = sqlMapClient.delete("t_tag_relation.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TTagRelation record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_tag_relation.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TTagRelation record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_tag_relation.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TTagRelationExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_tag_relation.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TTagRelation selectByPrimaryKey(Long id) throws SQLException {
        TTagRelation key = new TTagRelation();
        key.setId(id);
        TTagRelation record = (TTagRelation) sqlMapClient.queryForObject("t_tag_relation.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TTagRelation record, TTagRelationExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_tag_relation.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TTagRelation record, TTagRelationExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_tag_relation.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TTagRelation record) throws SQLException {
        int rows = sqlMapClient.update("t_tag_relation.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TTagRelation record) throws SQLException {
        int rows = sqlMapClient.update("t_tag_relation.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TTagRelationExample {
        private Object record;

        public UpdateByExampleParms(Object record, TTagRelationExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TTagRelationExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_tag_relation.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
	
	public Long insertList(Map param) throws SQLException{
		return new Long(sqlMapClient.delete("t_tag_relation.insert_list", param));
	}
}
