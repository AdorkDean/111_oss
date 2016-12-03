package com.rc.portal.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.portal.dao.TAdmUserRolesDAO;
import com.rc.portal.vo.TAdmUserRoles;
import com.rc.portal.vo.TAdmUserRolesExample;

public class TAdmUserRolesDAOImpl implements TAdmUserRolesDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TAdmUserRolesDAOImpl() {
        super();
    }

    public TAdmUserRolesDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByRepository(TAdmUserRolesExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("T_ADM_USER_ROLES.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByRepository(TAdmUserRolesExample example) throws SQLException {
        int rows = sqlMapClient.delete("T_ADM_USER_ROLES.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TAdmUserRoles key = new TAdmUserRoles();
        key.setId(id);
        int rows = sqlMapClient.delete("T_ADM_USER_ROLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(TAdmUserRoles record) throws SQLException {
        sqlMapClient.insert("T_ADM_USER_ROLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(TAdmUserRoles record) throws SQLException {
        sqlMapClient.insert("T_ADM_USER_ROLES.ibatorgenerated_insertSelective", record);
    }

    public List selectByRepository(TAdmUserRolesExample example) throws SQLException {
        List list = sqlMapClient.queryForList("T_ADM_USER_ROLES.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TAdmUserRoles selectByPrimaryKey(Long id) throws SQLException {
        TAdmUserRoles key = new TAdmUserRoles();
        key.setId(id);
        TAdmUserRoles record = (TAdmUserRoles) sqlMapClient.queryForObject("T_ADM_USER_ROLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByRepositorySelective(TAdmUserRoles record, TAdmUserRolesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_USER_ROLES.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByRepository(TAdmUserRoles record, TAdmUserRolesExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("T_ADM_USER_ROLES.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TAdmUserRoles record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_USER_ROLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TAdmUserRoles record) throws SQLException {
        int rows = sqlMapClient.update("T_ADM_USER_ROLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TAdmUserRolesExample {
        private Object record;

        public UpdateByExampleParms(Object record, TAdmUserRolesExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    /**
     * 给用户赋予登录权限
     * @param userid
     * @throws SQLException
     */
    public void afford_login_role(Long userid,Long operateUserid) throws SQLException{    	
    	TAdmUserRoles ur=new TAdmUserRoles();
    	ur.setRoleId(3L);
    	ur.setUserId(userid);
    	ur.setCreateTime(new Date());
    	ur.setLastUpdateTime(new Date());
    	ur.setLastUpdateBy(new BigDecimal(operateUserid));
    	ur.setCreateBy(new BigDecimal(operateUserid));
    	this.insert(ur);    	
    }
}
