package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TCategoryImgDAO;
import com.rc.portal.vo.TCategoryImg;
import com.rc.portal.vo.TCategoryImgExample;

public class TCategoryImgDAOImpl implements TCategoryImgDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TCategoryImgDAOImpl() {
        super();
    }

    public TCategoryImgDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TCategoryImgExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_category_img.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TCategoryImgExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_category_img.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TCategoryImg key = new TCategoryImg();
        key.setId(id);
        int rows = sqlMapClient.delete("t_category_img.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TCategoryImg record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_category_img.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TCategoryImg record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_category_img.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TCategoryImgExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_category_img.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TCategoryImg selectByPrimaryKey(Long id) throws SQLException {
        TCategoryImg key = new TCategoryImg();
        key.setId(id);
        TCategoryImg record = (TCategoryImg) sqlMapClient.queryForObject("t_category_img.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TCategoryImg record, TCategoryImgExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_category_img.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TCategoryImg record, TCategoryImgExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_category_img.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TCategoryImg record) throws SQLException {
        int rows = sqlMapClient.update("t_category_img.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TCategoryImg record) throws SQLException {
        int rows = sqlMapClient.update("t_category_img.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TCategoryImgExample {
        private Object record;

        public UpdateByExampleParms(Object record, TCategoryImgExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TCategoryImgExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_category_img.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
