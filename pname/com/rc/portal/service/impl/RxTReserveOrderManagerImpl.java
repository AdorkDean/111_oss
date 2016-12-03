package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.RxTReserveOrderDAO;
import com.rc.portal.service.RxTReserveOrderManager;
import com.rc.portal.vo.RxTReserveOrder;
import com.rc.portal.vo.RxTReserveOrderExample;

public class RxTReserveOrderManagerImpl  implements RxTReserveOrderManager {

    private RxTReserveOrderDAO rxtreserveorderdao;
    
    private  OpenSqlDAO opensqldao;

    public RxTReserveOrderManagerImpl() {
        super();
    }
    public void  setRxtreserveorderdao(RxTReserveOrderDAO rxtreserveorderdao){
        this.rxtreserveorderdao=rxtreserveorderdao;
    }
    public RxTReserveOrderDAO getRxtreserveorderdao(){
        return this.rxtreserveorderdao;
    }
    public     int countByExample(RxTReserveOrderExample example) throws SQLException{
        return rxtreserveorderdao. countByExample( example);
    }

    public     int deleteByExample(RxTReserveOrderExample example) throws SQLException{
        return rxtreserveorderdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return rxtreserveorderdao. deleteByPrimaryKey( id);
    }

    public     Long insert(RxTReserveOrder record) throws SQLException{
        return rxtreserveorderdao. insert( record);
    }

    public     Long insertSelective(RxTReserveOrder record) throws SQLException{
        return rxtreserveorderdao. insertSelective( record);
    }

    public     List selectByExample(RxTReserveOrderExample example) throws SQLException{
        return rxtreserveorderdao. selectByExample( example);
    }

    public     RxTReserveOrder selectByPrimaryKey(Long id) throws SQLException{
        return rxtreserveorderdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(RxTReserveOrder record, RxTReserveOrderExample example) throws SQLException{
        return rxtreserveorderdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(RxTReserveOrder record, RxTReserveOrderExample example) throws SQLException{
        return rxtreserveorderdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(RxTReserveOrder record) throws SQLException{
        return rxtreserveorderdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(RxTReserveOrder record) throws SQLException{
        return rxtreserveorderdao. updateByPrimaryKey( record);
    }
	public boolean changeOrderStatus(RxTReserveOrder reserverOtder)
			throws Exception {
		boolean flag = true;
		try{
			if((reserverOtder.getOrderStatus()!=null&&reserverOtder.getOrderStatus().intValue()==9)||(reserverOtder.getIsEnd()!=null&&reserverOtder.getIsEnd().intValue()==1)){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", reserverOtder.getGoodid());
				Map<String,Object> goodMap = (Map<String, Object>) this.opensqldao.selectForObjectByMap(paramMap, "t_goods.selectByPrimaryKeyForUpdate");
				paramMap.put("quantity", reserverOtder.getNum());
				this.opensqldao.updateByMap_drug(paramMap, "t_goods.updateGoodAddStockById");
			}
			this.rxtreserveorderdao.updateByPrimaryKeySelective(reserverOtder);
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}
	public String revokeReserverOrder(RxTReserveOrder reserverOtder) throws Exception {
		String flag ="1";
		try{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", reserverOtder.getGoodid());
			Map<String,Object> goodMap = (Map<String, Object>) this.opensqldao.selectForObjectByMap(paramMap, "t_goods.selectByPrimaryKeyForUpdate");
			if(((Long)goodMap.get("stock")).intValue()>=reserverOtder.getNum()){
				paramMap.put("quantity", reserverOtder.getNum());
				this.opensqldao.updateByMap_drug(paramMap, "t_goods.updateGoodStockById");
				reserverOtder.setOrderStatus(0);
				reserverOtder.setAuditStatus(0);
				reserverOtder.setCancelDt(null);
				reserverOtder.setCancelRemark(null);
				reserverOtder.setIsEnd(0);
				this.rxtreserveorderdao.updateByPrimaryKey(reserverOtder);
			}else{
				flag="2";//库存不足
			}
		}catch(Exception e){
			flag="0";
			e.printStackTrace();
		}
		return flag;
	}

	

}
