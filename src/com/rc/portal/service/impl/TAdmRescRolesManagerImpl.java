package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rc.portal.dao.TAdmRescRolesDAO;
import com.rc.portal.service.TAdmRescRolesManager;
import com.rc.portal.vo.TAdmRescRoles;
import com.rc.portal.vo.TAdmRescRolesExample;

public class TAdmRescRolesManagerImpl  implements TAdmRescRolesManager {

    private TAdmRescRolesDAO tadmrescrolesdao;

    public TAdmRescRolesManagerImpl() {
        super();
    }
    public void  setTadmrescrolesdao(TAdmRescRolesDAO tadmrescrolesdao){
        this.tadmrescrolesdao=tadmrescrolesdao;
    }
    public TAdmRescRolesDAO getTadmrescrolesdao(){
        return this.tadmrescrolesdao;
    }
    public     int countByRepository(TAdmRescRolesExample example) throws SQLException{
        return tadmrescrolesdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmRescRolesExample example) throws SQLException{
        return tadmrescrolesdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmrescrolesdao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmRescRoles record) throws SQLException{
        tadmrescrolesdao. insert( record);
    }

    public     void insertSelective(TAdmRescRoles record) throws SQLException{
        tadmrescrolesdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmRescRolesExample example) throws SQLException{
        return tadmrescrolesdao. selectByRepository( example);
    }

    public     TAdmRescRoles selectByPrimaryKey(Long id) throws SQLException{
        return tadmrescrolesdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmRescRoles record, TAdmRescRolesExample example) throws SQLException{
        return tadmrescrolesdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmRescRoles record, TAdmRescRolesExample example) throws SQLException{
        return tadmrescrolesdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmRescRoles record) throws SQLException{
        return tadmrescrolesdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmRescRoles record) throws SQLException{
        return tadmrescrolesdao. updateByPrimaryKey( record);
    }

    /**
     * 修改权限所拥有的资源，先删除，再增加，异常回滚
     * @param roleid
     * @param rescids
     * @throws Exception 
     * @throws Exception
     */
    public void editRoleResc(Long roleid,String[] rescids,String[] resciddels) throws Exception{
			TAdmRescRolesExample te=new TAdmRescRolesExample();
			List<Long>  list=new ArrayList<Long>();
			for(String rescid:resciddels){
				list.add(Long.parseLong(rescid));
			}
			te.createCriteria().andRoleIdEqualTo(roleid).andRescIdIn(list);
			deleteByRepository(te);
			if(rescids!=null){
				for(String rescid:rescids){
					TAdmRescRoles tr=new TAdmRescRoles();
					tr.setRescId(Long.parseLong(rescid));
					tr.setRoleId(roleid);
					insert(tr);
				}
			}
    }}
