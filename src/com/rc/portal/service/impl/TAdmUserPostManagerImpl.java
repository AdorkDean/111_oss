package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rc.portal.dao.TAdmUserPostDAO;
import com.rc.portal.service.TAdmUserPostManager;
import com.rc.portal.vo.TAdmUserPost;
import com.rc.portal.vo.TAdmUserPostExample;

public class TAdmUserPostManagerImpl  implements TAdmUserPostManager {

    private TAdmUserPostDAO tadmuserpostdao;

    public TAdmUserPostManagerImpl() {
        super();
    }
    public void  setTadmuserpostdao(TAdmUserPostDAO tadmuserpostdao){
        this.tadmuserpostdao=tadmuserpostdao;
    }
    public TAdmUserPostDAO getTadmuserpostdao(){
        return this.tadmuserpostdao;
    }
    public     int countByRepository(TAdmUserPostExample example) throws SQLException{
        return tadmuserpostdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmUserPostExample example) throws SQLException{
        return tadmuserpostdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmuserpostdao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmUserPost record) throws SQLException{
        tadmuserpostdao. insert( record);
    }

    public     void insertSelective(TAdmUserPost record) throws SQLException{
        tadmuserpostdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmUserPostExample example) throws SQLException{
        return tadmuserpostdao. selectByRepository( example);
    }

    public     TAdmUserPost selectByPrimaryKey(Long id) throws SQLException{
        return tadmuserpostdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmUserPost record, TAdmUserPostExample example) throws SQLException{
        return tadmuserpostdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmUserPost record, TAdmUserPostExample example) throws SQLException{
        return tadmuserpostdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmUserPost record) throws SQLException{
        return tadmuserpostdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmUserPost record) throws SQLException{
        return tadmuserpostdao. updateByPrimaryKey( record);
    }
    /**
	 * 修改用户岗位，先删除，再添加，有异常回滚
	 * @param userid
	 * @param roleids
	 * @throws Exception
	 */
	public void editUserPost(Long userid,String[] postids,String[] showpostids)throws Exception {
		List<Long> list=new ArrayList<Long>();
		for(String postid:showpostids){
			list.add(new Long(postid));			
		}
		//先删掉当前页显示的角色，再增加选中的角色
		TAdmUserPostExample tre=new TAdmUserPostExample();
		tre.createCriteria().andUseridEqualTo(userid).andPostidIn(list);;
		deleteByRepository(tre);
		
		if(postids!=null){
			for(String roleid:postids){
				TAdmUserPost tu=new TAdmUserPost();
				tu.setUserid(userid);
				tu.setPostid(Long.parseLong(roleid));
				insert(tu);
			}		
		}
		
	}}
