package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rc.portal.dao.TAdmPostRoleDAO;
import com.rc.portal.service.TAdmPostRoleManager;
import com.rc.portal.vo.TAdmPostRole;
import com.rc.portal.vo.TAdmPostRoleExample;

public class TAdmPostRoleManagerImpl  implements TAdmPostRoleManager {

    private TAdmPostRoleDAO tadmpostroledao;

    public TAdmPostRoleManagerImpl() {
        super();
    }
    public void  setTadmpostroledao(TAdmPostRoleDAO tadmpostroledao){
        this.tadmpostroledao=tadmpostroledao;
    }
    public TAdmPostRoleDAO getTadmpostroledao(){
        return this.tadmpostroledao;
    }
    public     int countByRepository(TAdmPostRoleExample example) throws SQLException{
        return tadmpostroledao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmPostRoleExample example) throws SQLException{
        return tadmpostroledao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmpostroledao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmPostRole record) throws SQLException{
        tadmpostroledao. insert( record);
    }

    public     void insertSelective(TAdmPostRole record) throws SQLException{
        tadmpostroledao. insertSelective( record);
    }

    public     List selectByRepository(TAdmPostRoleExample example) throws SQLException{
        return tadmpostroledao. selectByRepository( example);
    }

    public     TAdmPostRole selectByPrimaryKey(Long id) throws SQLException{
        return tadmpostroledao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmPostRole record, TAdmPostRoleExample example) throws SQLException{
        return tadmpostroledao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmPostRole record, TAdmPostRoleExample example) throws SQLException{
        return tadmpostroledao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmPostRole record) throws SQLException{
        return tadmpostroledao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmPostRole record) throws SQLException{
        return tadmpostroledao. updateByPrimaryKey( record);
    }
    
    
    /**
	 * 修改用户权限，先删除，再添加，有异常回滚
	 * @param userid
	 * @param roleids
	 * @throws Exception
	 */
	public void editPostRoles(Long postid,String[] roleids,String[] showroleids)throws Exception {
		List<Long> list=new ArrayList<Long>();
		for(String roleid:showroleids){
			list.add(new Long(roleid));			
		}
		//先删掉当前页显示的角色，再增加选中的角色
		TAdmPostRoleExample tre=new TAdmPostRoleExample();
		tre.createCriteria().andPostidEqualTo(postid).andRoleidIn(list);;
		deleteByRepository(tre);
		
		if(roleids!=null){
			for(String roleid:roleids){
				TAdmPostRole tu=new TAdmPostRole();
				tu.setPostid(postid);
				tu.setRoleid(Long.parseLong(roleid));
				insert(tu);
			}		
		}
		
	}}
