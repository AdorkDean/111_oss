package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmModulesDAO;
import com.rc.portal.vo.TAdmModules;
import com.rc.portal.vo.TAdmModulesExample;

public class TAdmModulesDAOImpl implements TAdmModulesDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmModulesDAOImpl() {
        super();
    }

    public TAdmModulesDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmModulesExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_MODULES.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmModulesExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_MODULES.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmModules key = new TAdmModules();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_MODULES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmModules record) throws SQLException {
        sqlMapClient.insert("T_ADM_MODULES.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmModules record) throws SQLException {
        sqlMapClient.insert("T_ADM_MODULES.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmModulesExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_MODULES.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmModules selectByPrimaryKey(Long id) throws SQLException {
        TAdmModules key = new TAdmModules();
        key.setId(id);
        TAdmModules record = (TAdmModules) sqlMapClient.queryForObject("T_ADM_MODULES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmModules record, TAdmModulesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_MODULES.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmModules record, TAdmModulesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_MODULES.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmModules record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_MODULES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmModules record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_MODULES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmModulesExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmModulesExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    public PageWraper selectByRepositoryByPage(TAdmModulesExample example) throws SQLException {
    	PageWraper pw=null;
    	int count=this.countByRepository(example);
        List list = sqlMapClient.queryForList("T_ADM_MODULES.ibatorgenerated_selectParentByExample", example);
        System.out.println("模块分页查询list.size="+list.size());	
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
        return pw;
    }
    
    
    /**
     * 模块查找，增加父模块ID,父模块名字
     * @param example
     * @return
     * @throws SQLException
     */
    public List selectParentByRepository(TAdmModulesExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_MODULES.ibatorgenerated_selectParentByExample", example);
        return list;
    }
}
