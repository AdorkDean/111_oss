package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TjMemberLeaderAddCountDAO;
import com.rc.portal.vo.TjMemberLeaderAddCount;
import com.rc.portal.vo.TjMemberLeaderAddCountExample;

public class TjMemberLeaderAddCountDAOImpl implements TjMemberLeaderAddCountDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TjMemberLeaderAddCountDAOImpl() {
        super();
    }

    public TjMemberLeaderAddCountDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TjMemberLeaderAddCountExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("tj_member_leader_add_count.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TjMemberLeaderAddCountExample example) throws SQLException {
        int rows = sqlMapClient.delete("tj_member_leader_add_count.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TjMemberLeaderAddCount key = new TjMemberLeaderAddCount();
        key.setId(id);
        int rows = sqlMapClient.delete("tj_member_leader_add_count.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TjMemberLeaderAddCount record) throws SQLException {
		return (Long)        sqlMapClient.insert("tj_member_leader_add_count.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TjMemberLeaderAddCount record) throws SQLException {
		return (Long)        sqlMapClient.insert("tj_member_leader_add_count.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TjMemberLeaderAddCountExample example) throws SQLException {
        List list = sqlMapClient.queryForList("tj_member_leader_add_count.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TjMemberLeaderAddCount selectByPrimaryKey(Long id) throws SQLException {
        TjMemberLeaderAddCount key = new TjMemberLeaderAddCount();
        key.setId(id);
        TjMemberLeaderAddCount record = (TjMemberLeaderAddCount) sqlMapClient.queryForObject("tj_member_leader_add_count.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TjMemberLeaderAddCount record, TjMemberLeaderAddCountExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("tj_member_leader_add_count.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TjMemberLeaderAddCount record, TjMemberLeaderAddCountExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("tj_member_leader_add_count.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TjMemberLeaderAddCount record) throws SQLException {
        int rows = sqlMapClient.update("tj_member_leader_add_count.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TjMemberLeaderAddCount record) throws SQLException {
        int rows = sqlMapClient.update("tj_member_leader_add_count.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TjMemberLeaderAddCountExample {
        private Object record;

        public UpdateByExampleParms(Object record, TjMemberLeaderAddCountExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TjMemberLeaderAddCountExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("tj_member_leader_add_count.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
