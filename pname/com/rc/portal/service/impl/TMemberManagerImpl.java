package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TMemberAccountDAO;
import com.rc.portal.dao.TMemberBaseMessageExtDAO;
import com.rc.portal.dao.TMemberDAO;
import com.rc.portal.dao.TMemberHobbyDAO;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberHobby;

public class TMemberManagerImpl  implements TMemberManager {

    private TMemberDAO tmemberdao;

    private TMemberAccountDAO tmemberaccountdao;
    
    private TMemberBaseMessageExtDAO tmemberbasemessageextdao;
    
    private TMemberHobbyDAO tmemberhobbydao;
    
    public TMemberManagerImpl() {
        super();
    }
    public void  setTmemberaccountdao(TMemberAccountDAO tmemberaccountdao){
        this.tmemberaccountdao=tmemberaccountdao;
    }
    public TMemberAccountDAO getTmemberaccountdao(){
        return this.tmemberaccountdao;
    }
    public void  setTmemberdao(TMemberDAO tmemberdao){
        this.tmemberdao=tmemberdao;
    }
    public TMemberDAO getTmemberdao(){
        return this.tmemberdao;
    }
    public     int countByExample(TMemberExample example) throws SQLException{
        return tmemberdao. countByExample( example);
    }

    public     int deleteByExample(TMemberExample example) throws SQLException{
        return tmemberdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tmemberdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TMember record) throws SQLException{
        return tmemberdao. insert( record);
    }

    public     Long insertSelective(TMember record) throws SQLException{
        return tmemberdao. insertSelective( record);
    }

    public     List selectByExample(TMemberExample example) throws SQLException{
        return tmemberdao. selectByExample( example);
    }

    public     TMember selectByPrimaryKey(Long id) throws SQLException{
        return tmemberdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TMember record, TMemberExample example) throws SQLException{
        return tmemberdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TMember record, TMemberExample example) throws SQLException{
        return tmemberdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TMember record) throws SQLException{
        return tmemberdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TMember record) throws SQLException{
        return tmemberdao. updateByPrimaryKey( record);
    }
    
    
    
	public TMemberBaseMessageExtDAO getTmemberbasemessageextdao() {
		return tmemberbasemessageextdao;
	}
	public void setTmemberbasemessageextdao(
			TMemberBaseMessageExtDAO tmemberbasemessageextdao) {
		this.tmemberbasemessageextdao = tmemberbasemessageextdao;
	}
	public TMemberHobbyDAO getTmemberhobbydao() {
		return tmemberhobbydao;
	}
	public void setTmemberhobbydao(TMemberHobbyDAO tmemberhobbydao) {
		this.tmemberhobbydao = tmemberhobbydao;
	}
	@Override
	public Long insertMember(TMember record) throws SQLException {
		Long id  = tmemberdao. insertSelective( record);
		TMemberAccount account = new TMemberAccount();
		account.setMemberId(id);
		account.setFreezeAmount(new BigDecimal(0));
		account.setRemainingAmount(new BigDecimal(0));
		account.setWaitAmount(new BigDecimal(0));
		account.setTotalAmount(new BigDecimal(0));
		tmemberaccountdao.insertSelective(account);
		return id;
	}
	
//	后台会员修改信息
	public int saveMember(TMember tmember, TMemberBaseMessageExt base,
			TMemberHobby hobby) throws Exception {
		Integer flag=0;
		if(tmember!=null&&tmember.getId()!=null){
			if(base!=null&&base.getMemberId()!=null){
				if(hobby!=null&&hobby.getMemberId()!=null){
					int i=0;
					int j=0;
					int m=0;
					TMemberBaseMessageExt mbme = tmemberbasemessageextdao.selectByPrimaryKey(base.getMemberId());
					TMemberHobby mh = tmemberhobbydao.selectByPrimaryKey(hobby.getMemberId());
					i = tmemberdao.updateByPrimaryKeySelective(tmember);
					if(mbme!=null){
						j =tmemberbasemessageextdao.updateByPrimaryKeySelective(base);
					}else{
						tmemberbasemessageextdao.insertSelective(base);
						j=1;
					}
					if(mh!=null){
						m =tmemberhobbydao.updateByPrimaryKeySelective(hobby);
					}else{
						tmemberhobbydao.insertSelective(hobby);
						m=1;
					}
					if(i>0&&j>0&&m>0){
						flag=1;
					}
				}
			}
		}
		return flag;
	}


}
