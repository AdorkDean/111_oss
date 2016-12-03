package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TjMemberDAO;
import com.rc.portal.vo.TjMember;
import com.rc.portal.vo.TjMemberExample;

public class TjMemberDAOImpl implements TjMemberDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TjMemberDAOImpl() {
        super();
    }

    public TjMemberDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TjMemberExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("tj_member.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TjMemberExample example) throws SQLException {
        int rows = sqlMapClient.delete("tj_member.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TjMember key = new TjMember();
        key.setId(id);
        int rows = sqlMapClient.delete("tj_member.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TjMember record) throws SQLException {
		return (Long)        sqlMapClient.insert("tj_member.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TjMember record) throws SQLException {
		return (Long)        sqlMapClient.insert("tj_member.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TjMemberExample example) throws SQLException {
        List list = sqlMapClient.queryForList("tj_member.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TjMember selectByPrimaryKey(Long id) throws SQLException {
        TjMember key = new TjMember();
        key.setId(id);
        TjMember record = (TjMember) sqlMapClient.queryForObject("tj_member.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TjMember record, TjMemberExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("tj_member.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TjMember record, TjMemberExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("tj_member.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TjMember record) throws SQLException {
        int rows = sqlMapClient.update("tj_member.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TjMember record) throws SQLException {
        int rows = sqlMapClient.update("tj_member.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TjMemberExample {
        private Object record;

        public UpdateByExampleParms(Object record, TjMemberExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TjMemberExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("tj_member.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
