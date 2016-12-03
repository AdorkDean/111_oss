package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.rc.portal.dao.TCouponCardDAO;
import com.rc.portal.dao.TCouponDAO;
import com.rc.portal.dao.TCouponRelevanceDAO;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.util.DateUtil;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TCouponCard;
import com.rc.portal.vo.TCouponExample;
import com.rc.portal.vo.TCouponRelevance;

public class TCouponManagerImpl  implements TCouponManager {

    private TCouponDAO tcoupondao;

    private TCouponCardDAO tcouponcarddao;
    
    private TCouponRelevanceDAO tcouponrelevancedao;

    public TCouponManagerImpl() {
        super();
    }
    
    public void  setTcouponrelevancedao(TCouponRelevanceDAO tcouponrelevancedao){
        this.tcouponrelevancedao=tcouponrelevancedao;
    }
    public TCouponRelevanceDAO getTcouponrelevancedao(){
        return this.tcouponrelevancedao;
    }
    
    public void  setTcouponcarddao(TCouponCardDAO tcouponcarddao){
        this.tcouponcarddao=tcouponcarddao;
    }
    public TCouponCardDAO getTcouponcarddao(){
        return this.tcouponcarddao;
    }
    
    public void  setTcoupondao(TCouponDAO tcoupondao){
        this.tcoupondao=tcoupondao;
    }
    public TCouponDAO getTcoupondao(){
        return this.tcoupondao;
    }
    public     int countByExample(TCouponExample example) throws SQLException{
        return tcoupondao. countByExample( example);
    }

    public     int deleteByExample(TCouponExample example) throws SQLException{
        return tcoupondao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tcoupondao. deleteByPrimaryKey( id);
    }

    public     Long insert(TCoupon record) throws SQLException{
        return tcoupondao. insert( record);
    }

    public     Long insertSelective(TCoupon record) throws SQLException{
        return tcoupondao. insertSelective( record);
    }

    public     List selectByExample(TCouponExample example) throws SQLException{
        return tcoupondao. selectByExample( example);
    }

    public     TCoupon selectByPrimaryKey(Long id) throws SQLException{
        return tcoupondao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TCoupon record, TCouponExample example) throws SQLException{
        return tcoupondao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TCoupon record, TCouponExample example) throws SQLException{
        return tcoupondao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TCoupon record) throws SQLException{
        return tcoupondao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TCoupon record) throws SQLException{
        return tcoupondao. updateByPrimaryKey( record);
    }
	@Override
	public Long insertOrTCouponCard(TCoupon record,String tid) throws SQLException {
		Long id = tcoupondao. insertSelective(record);
	    int quantity = record.getQuantity();
        for(int i=0;i<quantity;i++){
        	TCouponCard card = new TCouponCard();
        	String uuid = UUID.randomUUID().toString().toUpperCase();
        	String code = null;
        	Integer number = record.getCodeLength() != null ? record
					.getCodeLength() : 10;    //获取位数
			if (record.getRule() != null && record.getRule() == 0) { //生成优惠券码
				code = record.getPrefix()
						+ BigDecimal
								.valueOf(
										Math.round((Math.random() + new Random(
												10).nextDouble())
												* Math.pow(10.0D,
														number)))
								.toString().substring(0, number);
			} else {
				code = record.getPrefix()
						+ (uuid + uuid).replaceAll("-", "")
								.replaceAll("0|O", "K")
								.substring(0, number);
			}
			card.setCreateTime(new Date());
			card.setStartTime(record.getStartTime()); //优惠劵开始时间
			if(record.getIsDelay()==1){
				long sd = record.getStartTime().getTime(); //获取优惠劵开始时间
				long dd = new Date().getTime(); //获取当前时间
				if(sd>dd){
					card.setEndTime(DateUtil.dateAddDays(record.getStartTime(), record.getDelayDay()));
				}else{
					card.setEndTime(DateUtil.dateAddDays(new Date(), record.getDelayDay()));
				}
			}else{
				card.setEndTime(record.getEndTime());
			}
			card.setCardNo(code);
			card.setIsUse(0);
			card.setTicketId(id);
			tcouponcarddao.insertSelective(card);
        }
        if(null != tid && !("").equals(tid)){
        	TCouponRelevance tcr = new TCouponRelevance();
        	tcr.setCouponId(id);
        	tcr.setRelevanceId(new Long(tid));
        	tcouponrelevancedao.insertSelective(tcr);
        }
		return id;
	}

	@Override
	public int updateConpon(TCoupon record) throws SQLException {
		int row = tcoupondao. updateByPrimaryKeySelective(record);
//		int quantity = record.getQuantity();
//        for(int i=0;i<quantity;i++){
//        	TCouponCard card = new TCouponCard();
//        	String uuid = UUID.randomUUID().toString().toUpperCase();
//        	String code = null;
//        	Integer number = record.getCodeLength() != null ? record
//					.getCodeLength() : 10;    //获取位数
//			if (record.getRule() != null && record.getRule() == 0) { //生成优惠券码
//				code = record.getPrefix()
//						+ BigDecimal
//								.valueOf(
//										Math.round((Math.random() + new Random(
//												10).nextDouble())
//												* Math.pow(10.0D,
//														number)))
//								.toString().substring(0, number);
//			} else {
//				code = record.getPrefix()
//						+ (uuid + uuid).replaceAll("-", "")
//								.replaceAll("0|O", "K")
//								.substring(0, number);
//			}
//			card.setCreateTime(new Date());
//			card.setCardNo(code);
//			card.setIsUse(0);
//			card.setTicketId(record.getId());
//			tcouponcarddao.insertSelective(card);
//        }
		return row;
	}

	@Override
	public void createCardEdit(TCoupon record) throws SQLException {
		int quantity = record.getQuantity();
        for(int i=0;i<quantity;i++){
        	TCouponCard card = new TCouponCard();
        	String uuid = UUID.randomUUID().toString().toUpperCase();
        	String code = null;
        	Integer number = record.getCodeLength() != null ? record
					.getCodeLength() : 10;    //获取位数
			if (record.getRule() != null && record.getRule() == 0) { //生成优惠券码
				code = record.getPrefix()
						+ BigDecimal
								.valueOf(
										Math.round((Math.random() + new Random(
												10).nextDouble())
												* Math.pow(10.0D,
														number)))
								.toString().substring(0, number);
			} else {
				code = record.getPrefix()
						+ (uuid + uuid).replaceAll("-", "")
								.replaceAll("0|O", "K")
								.substring(0, number);
			}
			card.setCreateTime(new Date());
			card.setStartTime(record.getStartTime()); //优惠劵开始时间
			if(record.getIsDelay()==1){   //判断优惠劵是否延期使用 
				long sd = record.getStartTime().getTime(); //获取优惠劵开始时间
				long dd = new Date().getTime(); //获取当前时间
				if(sd>dd){
					card.setEndTime(DateUtil.dateAddDays(record.getStartTime(), record.getDelayDay()));
				}else{
					card.setEndTime(DateUtil.dateAddDays(new Date(), record.getDelayDay()));
				}
			}else{
				card.setEndTime(record.getEndTime());
			}
			card.setCardNo(code);
			card.setIsUse(0);
			card.setTicketId(record.getId());
			tcouponcarddao.insertSelective(card);
        }
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.dateAddDays(new Date(), 3));
	}
}
