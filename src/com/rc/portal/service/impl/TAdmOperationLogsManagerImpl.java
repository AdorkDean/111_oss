package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rc.portal.dao.TAdmOperationLogsDAO;
import com.rc.portal.service.TAdmOperationLogsManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.vo.TAdmOperationLogs;
import com.rc.portal.vo.TAdmOperationLogsExample;
import com.rc.portal.webapp.model.UserinfoModel;

public class TAdmOperationLogsManagerImpl  implements TAdmOperationLogsManager {

    private TAdmOperationLogsDAO tadmoperationlogsdao;

    public TAdmOperationLogsManagerImpl() {
        super();
    }
    public void  setTadmoperationlogsdao(TAdmOperationLogsDAO tadmoperationlogsdao){
        this.tadmoperationlogsdao=tadmoperationlogsdao;
    }
    public TAdmOperationLogsDAO getTadmoperationlogsdao(){
        return this.tadmoperationlogsdao;
    }
    public     int countByRepository(TAdmOperationLogsExample example) throws SQLException{
        return tadmoperationlogsdao. countByRepository( example);
    }

    public     int deleteByRepository(TAdmOperationLogsExample example) throws SQLException{
        return tadmoperationlogsdao. deleteByRepository( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tadmoperationlogsdao. deleteByPrimaryKey( id);
    }

    public     void insert(TAdmOperationLogs record) throws SQLException{
        tadmoperationlogsdao. insert( record);
    }

    public     void insertSelective(TAdmOperationLogs record) throws SQLException{
        tadmoperationlogsdao. insertSelective( record);
    }

    public     List selectByRepository(TAdmOperationLogsExample example) throws SQLException{
        return tadmoperationlogsdao. selectByRepository( example);
    }

    public     TAdmOperationLogs selectByPrimaryKey(Long id) throws SQLException{
        return tadmoperationlogsdao. selectByPrimaryKey( id);
    }

    public     int updateByRepositorySelective(TAdmOperationLogs record, TAdmOperationLogsExample example) throws SQLException{
        return tadmoperationlogsdao. updateByRepositorySelective( record, example);
    }

    public     int updateByRepository(TAdmOperationLogs record, TAdmOperationLogsExample example) throws SQLException{
        return tadmoperationlogsdao. updateByRepository( record, example);
    }

    public     int updateByPrimaryKeySelective(TAdmOperationLogs record) throws SQLException{
        return tadmoperationlogsdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TAdmOperationLogs record) throws SQLException{
        return tadmoperationlogsdao. updateByPrimaryKey( record);
    }

	/**
	 * 记录日志
	 * @param request
	 * @param context
	 */
	public void record(HttpServletRequest request,String context){
		UserinfoModel userinfo =(UserinfoModel) request.getSession().getAttribute(ConstantUtil.logincookiename);
		String userid="-1";
		if(userinfo!=null){
			userid=userinfo.getUserid();
		}	
		TAdmOperationLogs logs=new TAdmOperationLogs();
		logs.setIpAddress(getRemortIP(request));
		logs.setOptId(new BigDecimal(userid));
		logs.setOptDate(new Date());
		logs.setOperation(context);		
		try {
			tadmoperationlogsdao.insert(logs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 记录日志,供登陆记录日志使用
	 * @param ip
	 * @param moduleid
	 * @param userid
	 * @param date
	 * @param context
	 */
	public void record(HttpServletRequest request,Long userid,String context){
		TAdmOperationLogs logs=new TAdmOperationLogs();
		logs.setIpAddress(getRemortIP(request)); 
		logs.setOptId(new BigDecimal(userid));
		logs.setOptDate(new Date());
		logs.setOperation(context);		
		try {
			tadmoperationlogsdao.insert(logs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getRemortIP(HttpServletRequest request) { 
	    if (request.getHeader("x-forwarded-for") == null) { 
	        return request.getRemoteAddr(); 
	    } 
	    return request.getHeader("x-forwarded-for"); 
	}  }
