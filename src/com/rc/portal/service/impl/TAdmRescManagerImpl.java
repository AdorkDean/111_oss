package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TAdmRescDAO;
import com.rc.portal.service.TAdmRescManager;
import com.rc.portal.vo.TAdmResc;
import com.rc.portal.vo.TAdmRescExample;

public class TAdmRescManagerImpl  implements TAdmRescManager {

    private TAdmRescDAO tadmrescdao;

    public TAdmRescManagerImpl() {
        super();
    }
    public void  setTadmrescdao(TAdmRescDAO tadmrescdao){
        this.tadmrescdao=tadmrescdao;
    }
    public TAdmRescDAO getTadmrescdao(){
        return this.tadmrescdao;
    }
    public     int countByRepository(TAdmRescExample example) throws SQLException{
        return tadmrescdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmRescExample example) throws SQLException{
        return tadmrescdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmrescdao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmResc record) throws SQLException{
        tadmrescdao. insert( record);
    }

    public     void insertSelective(TAdmResc record) throws SQLException{
        tadmrescdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmRescExample example) throws SQLException{
        return tadmrescdao. selectByRepository( example);
    }

    public     TAdmResc selectByPrimaryKey(Long id) throws SQLException{
        return tadmrescdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmResc record, TAdmRescExample example) throws SQLException{
        return tadmrescdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmResc record, TAdmRescExample example) throws SQLException{
        return tadmrescdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmResc record) throws SQLException{
        return tadmrescdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmResc record) throws SQLException{
        return tadmrescdao. updateByPrimaryKey( record);
    }
	@Override
	public PageWraper selectByRepositoryByPage(TAdmRescExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return tadmrescdao.selectByRepositoryByPage(example);
	}
	//最大
	public String selectMax() throws SQLException {
		String resString = tadmrescdao.selectMax();
		String max = "1";
		if(resString.equals(null)||null==resString||resString.equals("")||resString==""){
		}else{
		   Long l =	Long.valueOf(resString)+1;
		   max = l.toString();
		}		
		return max;
	}
}
