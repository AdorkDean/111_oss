package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rc.portal.dao.TAdmUsersModulesDAO;
import com.rc.portal.service.TAdmUsersModulesManager;
import com.rc.portal.vo.TAdmUsersModules;
import com.rc.portal.vo.TAdmUsersModulesExample;

public class TAdmUsersModulesManagerImpl  implements TAdmUsersModulesManager {

    private TAdmUsersModulesDAO tadmusersmodulesdao;

    public TAdmUsersModulesManagerImpl() {
        super();
    }
    public void  setTadmusersmodulesdao(TAdmUsersModulesDAO tadmusersmodulesdao){
        this.tadmusersmodulesdao=tadmusersmodulesdao;
    }
    public TAdmUsersModulesDAO getTadmusersmodulesdao(){
        return this.tadmusersmodulesdao;
    }
    public     int countByRepository(TAdmUsersModulesExample example) throws SQLException{
        return tadmusersmodulesdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmUsersModulesExample example) throws SQLException{
        return tadmusersmodulesdao. deleteByRepository( example);
    }

    public     void insert(TAdmUsersModules record) throws SQLException{
        tadmusersmodulesdao. insert( record);
    }

    public     void insertSelective(TAdmUsersModules record) throws SQLException{
        tadmusersmodulesdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmUsersModulesExample example) throws SQLException{
        return tadmusersmodulesdao. selectByRepository( example);
    }

    public     int updateByRepositorySelective(TAdmUsersModules record, TAdmUsersModulesExample example) throws SQLException{
        return tadmusersmodulesdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmUsersModules record, TAdmUsersModulesExample example) throws SQLException{
        return tadmusersmodulesdao. updateByRepository( record, example);
    }
    
    /**
     * 修改用户的模块信息，先删除，再添加，有异常回滚
     * @param userid
     * @param moduleids
     * @throws Exception 
     * @throws Exception 
     */
    public void editUserModules(Long userid,String[] moduleids,String[] moduleall) throws Exception{
    		List<Long> list=new ArrayList<Long>();
    		for(String id:moduleall){
    			list.add(new Long(id));
    		}
			TAdmUsersModulesExample tum=new TAdmUsersModulesExample();
			tum.createCriteria().andUseridEqualTo(userid).andModuleidIn(list);
			deleteByRepository(tum);
			for(String moduleid:moduleids){
				TAdmUsersModules tm=new TAdmUsersModules();
				tm.setModuleid(Long.parseLong(moduleid));
				tm.setUserid(userid);
				insert(tm);
			}
    }
}
