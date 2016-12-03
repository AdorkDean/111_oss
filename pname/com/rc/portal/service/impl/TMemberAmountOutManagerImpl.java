package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.TMemberAccountDAO;
import com.rc.portal.dao.TMemberAmountOutDAO;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.vo.TMemberAmountOutExample;

public class TMemberAmountOutManagerImpl  implements TMemberAmountOutManager {

    private TMemberAmountOutDAO tmemberamountoutdao;
    
    private TMemberAccountDAO tmemberaccountdao;

	private OpenSqlDAO opensqldao;
	

    public TMemberAmountOutManagerImpl() {
        super();
    }
    public void  setTmemberamountoutdao(TMemberAmountOutDAO tmemberamountoutdao){
        this.tmemberamountoutdao=tmemberamountoutdao;
    }
    public TMemberAmountOutDAO getTmemberamountoutdao(){
        return this.tmemberamountoutdao;
    }
    public void  setTmemberaccountdao(TMemberAccountDAO tmemberaccountdao){
        this.tmemberaccountdao=tmemberaccountdao;
    }
    public TMemberAccountDAO getTmemberaccountdao(){
        return this.tmemberaccountdao;
    }
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}

	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}

    public     int countByExample(TMemberAmountOutExample example) throws SQLException{
        return tmemberamountoutdao. countByExample( example);
    }

    public     int deleteByExample(TMemberAmountOutExample example) throws SQLException{
        return tmemberamountoutdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tmemberamountoutdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TMemberAmountOut record) throws SQLException{
        return tmemberamountoutdao. insert( record);
    }

    public     Long insertSelective(TMemberAmountOut record) throws SQLException{
        return tmemberamountoutdao. insertSelective( record);
    }

    public     List selectByExample(TMemberAmountOutExample example) throws SQLException{
        return tmemberamountoutdao. selectByExample( example);
    }

    public     TMemberAmountOut selectByPrimaryKey(Long id) throws SQLException{
        return tmemberamountoutdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TMemberAmountOut record, TMemberAmountOutExample example) throws SQLException{
        return tmemberamountoutdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TMemberAmountOut record, TMemberAmountOutExample example) throws SQLException{
        return tmemberamountoutdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TMemberAmountOut record) throws SQLException{
        return tmemberamountoutdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TMemberAmountOut record) throws SQLException{
        return tmemberamountoutdao. updateByPrimaryKey( record);
    }
	@Override
	public int finish(Long id) throws SQLException {
		int row = 0;
		TMemberAmountOut amountout = tmemberamountoutdao.selectByPrimaryKey(id);
		if(null != amountout){
			Map<String,Object> querMap = new HashMap<String,Object>();
			querMap.put("memberid", amountout.getMemberId());
			Map map = (Map) opensqldao.selectForObjectByMap(querMap, "t_member_account.selectForUpdate");
			if(null != map &&null != map.get("member_id")){
				BigDecimal freeze_amount = new BigDecimal(map.get("freeze_amount").toString());// 提现冻结金额
		    	if(freeze_amount.compareTo(amountout.getAmount())>=0){ //冻结金额大于提现申请
		    		TMemberAccount amount = new  TMemberAccount();
		    		amount.setMemberId(amountout.getMemberId());
		    		amount.setFreezeAmount(freeze_amount.subtract(amountout.getAmount())); //减去冻结金额
		    		BigDecimal total_amount = new BigDecimal(map.get("total_amount").toString());
		    		amount.setTotalAmount(total_amount.subtract(amountout.getAmount()));
		    		tmemberaccountdao.updateByPrimaryKeySelective(amount);
					amountout.setStatus(3);
					row = tmemberamountoutdao.updateByPrimaryKeySelective(amountout); //修改提现状态
		    	}
			}else{
				return row;
			}
		}
		return row;
	}
  
	@Override
	public int audit(TMemberAmountOut record) throws SQLException {
		int row = 0;
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("memberid", record.getMemberId());
		Map map = (Map) opensqldao.selectForObjectByMap(querMap, "t_member_account.selectForUpdate");
	    if(null != map && null != map.get("member_id")){
	    	BigDecimal freeze_amount = new BigDecimal(map.get("freeze_amount").toString());// 提现冻结金额
	    	if(freeze_amount.compareTo(record.getAmount())>=0){ //冻结金额大于提现申请
	    		TMemberAccount amount = new  TMemberAccount();
	    		amount.setMemberId(new Long(map.get("member_id").toString()));
	    		amount.setFreezeAmount(freeze_amount.subtract(record.getAmount())); //减去冻结金额
	    		BigDecimal remaining_amount = new BigDecimal(map.get("remaining_amount").toString());
	    		amount.setRemainingAmount(remaining_amount.add(record.getAmount()));
	    		tmemberaccountdao.updateByPrimaryKeySelective(amount);
	    	}
	    }
		row = tmemberamountoutdao.updateByPrimaryKeySelective(record);
		return row;
	}
	
public boolean checkAccount(TMemberAmountOut mao) throws SQLException {
	// TODO Auto-generated method stub
	return false;
}


}
