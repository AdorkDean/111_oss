package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rc.portal.dao.TAdmUserRolesDAO;
import com.rc.portal.service.TAdmUserRolesManager;
import com.rc.portal.vo.TAdmUserRoles;
import com.rc.portal.vo.TAdmUserRolesExample;

public class TAdmUserRolesManagerImpl  implements TAdmUserRolesManager {

    private TAdmUserRolesDAO tadmuserrolesdao;

    public TAdmUserRolesManagerImpl() {
        super();
    }
    public void  setTadmuserrolesdao(TAdmUserRolesDAO tadmuserrolesdao){
        this.tadmuserrolesdao=tadmuserrolesdao;
    }
    public TAdmUserRolesDAO getTadmuserrolesdao(){
        return this.tadmuserrolesdao;
    }
    public     int countByRepository(TAdmUserRolesExample example) throws SQLException{
        return tadmuserrolesdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmUserRolesExample example) throws SQLException{
        return tadmuserrolesdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmuserrolesdao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmUserRoles record) throws SQLException{
        tadmuserrolesdao. insert( record);
    }

    public     void insertSelective(TAdmUserRoles record) throws SQLException{
        tadmuserrolesdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmUserRolesExample example) throws SQLException{
        return tadmuserrolesdao. selectByRepository( example);
    }

    public     TAdmUserRoles selectByPrimaryKey(Long id) throws SQLException{
        return tadmuserrolesdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmUserRoles record, TAdmUserRolesExample example) throws SQLException{
        return tadmuserrolesdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmUserRoles record, TAdmUserRolesExample example) throws SQLException{
        return tadmuserrolesdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmUserRoles record) throws SQLException{
        return tadmuserrolesdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmUserRoles record) throws SQLException{
        return tadmuserrolesdao. updateByPrimaryKey( record);
    }
    	
	public void afford_login_role(Long userid,Long operateUserid) throws SQLException {
		tadmuserrolesdao.afford_login_role(userid,operateUserid);
	}
	
	/**
	 * 修改用户权限，先删除，再添加，有异常回滚
	 * @param userid
	 * @param roleids
	 * @throws Exception
	 */
	public void editUserRoles(Long userid,String[] roleids,String[] showroleids)throws Exception {
		List<Long> list=new ArrayList<Long>();
		for(String roleid:showroleids){
			list.add(new Long(roleid));			
		}
		//先删掉当前页显示的角色，再增加选中的角色
		TAdmUserRolesExample tre=new TAdmUserRolesExample();
		tre.createCriteria().andUserIdEqualTo(userid).andRoleIdIn(list);;
		deleteByRepository(tre);
		
		if(roleids!=null){
			for(String roleid:roleids){
				TAdmUserRoles tu=new TAdmUserRoles();
				tu.setUserId(userid);
				tu.setRoleId(Long.parseLong(roleid));
				tu.setCreateTime(new Date());
				insert(tu);
			}		
		}
		
	}
}
