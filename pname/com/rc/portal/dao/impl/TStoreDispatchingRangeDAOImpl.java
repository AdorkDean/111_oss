package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TStoreDispatchingRangeDAO;
import com.rc.portal.vo.TStoreDispatchingRange;
import com.rc.portal.vo.TStoreDispatchingRangeExample;

public class TStoreDispatchingRangeDAOImpl implements TStoreDispatchingRangeDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TStoreDispatchingRangeDAOImpl() {
        super();
    }

    public TStoreDispatchingRangeDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TStoreDispatchingRangeExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_store_dispatching_range.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TStoreDispatchingRangeExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_store_dispatching_range.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TStoreDispatchingRange key = new TStoreDispatchingRange();
        key.setId(id);
        int rows = sqlMapClient.delete("t_store_dispatching_range.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TStoreDispatchingRange record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_store_dispatching_range.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TStoreDispatchingRange record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_store_dispatching_range.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TStoreDispatchingRangeExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_store_dispatching_range.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TStoreDispatchingRange selectByPrimaryKey(Long id) throws SQLException {
        TStoreDispatchingRange key = new TStoreDispatchingRange();
        key.setId(id);
        TStoreDispatchingRange record = (TStoreDispatchingRange) sqlMapClient.queryForObject("t_store_dispatching_range.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TStoreDispatchingRange record, TStoreDispatchingRangeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_store_dispatching_range.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TStoreDispatchingRange record, TStoreDispatchingRangeExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_store_dispatching_range.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TStoreDispatchingRange record) throws SQLException {
        int rows = sqlMapClient.update("t_store_dispatching_range.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TStoreDispatchingRange record) throws SQLException {
        int rows = sqlMapClient.update("t_store_dispatching_range.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TStoreDispatchingRangeExample {
        private Object record;

        public UpdateByExampleParms(Object record, TStoreDispatchingRangeExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TStoreDispatchingRangeExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_store_dispatching_range.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
