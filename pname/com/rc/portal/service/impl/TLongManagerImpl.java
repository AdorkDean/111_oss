package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.rc.portal.dao.TLongCardDAO;
import com.rc.portal.dao.TLongDAO;
import com.rc.portal.service.TLongManager;
import com.rc.portal.vo.TLong;
import com.rc.portal.vo.TLongCard;
import com.rc.portal.vo.TLongExample;

public class TLongManagerImpl  implements TLongManager {

    private TLongDAO tlongdao;

    private TLongCardDAO tlongcarddao;
    
    public TLongManagerImpl() {
        super();
    }
    public void  setTlongdao(TLongDAO tlongdao){
        this.tlongdao=tlongdao;
    }
    public TLongDAO getTlongdao(){
        return this.tlongdao;
    }
    public void  setTlongcarddao(TLongCardDAO tlongcarddao){
        this.tlongcarddao=tlongcarddao;
    }
    public TLongCardDAO getTlongcarddao(){
        return this.tlongcarddao;
    }
    public     int countByExample(TLongExample example) throws SQLException{
        return tlongdao. countByExample( example);
    }

    public     int deleteByExample(TLongExample example) throws SQLException{
        return tlongdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tlongdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TLong record) throws SQLException{
        return tlongdao. insert( record);
    }

    public     Long insertSelective(TLong record) throws SQLException{
        return tlongdao. insertSelective( record);
    }

    public     List selectByExample(TLongExample example) throws SQLException{
        return tlongdao. selectByExample( example);
    }

    public     TLong selectByPrimaryKey(Long id) throws SQLException{
        return tlongdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TLong record, TLongExample example) throws SQLException{
        return tlongdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TLong record, TLongExample example) throws SQLException{
        return tlongdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TLong record) throws SQLException{
        return tlongdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TLong record) throws SQLException{
        return tlongdao. updateByPrimaryKey( record);
    }
	@Override
	public int updateLong(TLong record) throws SQLException {
		Integer  row = tlongdao.updateByPrimaryKeySelective(record);
		int quantity = record.getQuantity();
//        for(int i=0;i<quantity;i++){
//        	TLongCard card = new TLongCard();
//        	String uuid = UUID.randomUUID().toString().toUpperCase();
//        	String uuidp = UUID.randomUUID().toString().toUpperCase();
//        	String code = null;
//        	Integer number = 16;    //获取位数
//        	String password = null; //龙卡密码
//			if (record.getRule() != null && record.getRule().equals("0")) { //生成优惠券码
//				code =  BigDecimal
//								.valueOf(
//										Math.round((Math.random() + new Random(
//												10).nextDouble())
//												* Math.pow(10.0D,
//														number)))
//								.toString().substring(0, number);
//				password = BigDecimal
//						.valueOf(
//								Math.round((Math.random() + new Random(
//										10).nextDouble())
//										* Math.pow(10.0D,
//												number)))
//						.toString().substring(0, number);
//			} else {
//				code = (uuid + uuid).replaceAll("-", "")
//								.replaceAll("0|O", "K")
//								.substring(0, number);
//				password = (uuidp + uuidp).replaceAll("-", "")
//						.replaceAll("0|O", "K")
//						.substring(0, number);
//			}
//			card.setCreateTime(new Date());
//			card.setCard(code);
//			card.setIsUse(0);
//			card.setLongCardId(record.getId());
//			card.setPassword(password);
//			tlongcarddao.insertSelective(card);
//        }
		return row;
	}
	@Override
	public Long insertOrLongCard(TLong record) throws SQLException {
		Long id = tlongdao.insertSelective(record);
		int quantity = record.getQuantity();
        for(int i=0;i<quantity;i++){
        	TLongCard card = new TLongCard();
        	String uuid = UUID.randomUUID().toString().toUpperCase();
        	String uuidp = UUID.randomUUID().toString().toUpperCase();
        	String code = null;
        	Integer number = 16;    //获取位数
        	String password = null; //龙卡密码
			if (record.getRule() != null && record.getRule().equals("0")) { //生成优惠券码
				code =  BigDecimal
								.valueOf(
										Math.round((Math.random() + new Random(
												10).nextDouble())
												* Math.pow(10.0D,
														number)))
								.toString().substring(0, number);
				password = BigDecimal
						.valueOf(
								Math.round((Math.random() + new Random(
										10).nextDouble())
										* Math.pow(10.0D,
												number)))
						.toString().substring(0, number);
			} else {
				code = (uuid + uuid).replaceAll("-", "")
								.replaceAll("0|O", "K")
								.substring(0, number);
				password = (uuidp + uuidp).replaceAll("-", "")
						.replaceAll("0|O", "K")
						.substring(0, number);
			}
			card.setCreateTime(new Date());
			card.setCard(code);
			card.setIsUse(0);
			card.setLongCardId(record.getId());
			card.setPassword(password);
			tlongcarddao.insertSelective(card);
        }
		return id;
	}
	/**
	 * 生成龙卡
	 */
	public void createLongCardEdit(TLong record) throws SQLException {
		int quantity = record.getQuantity();
        for(int i=0;i<quantity;i++){
        	TLongCard card = new TLongCard();
        	String uuid = UUID.randomUUID().toString().toUpperCase();
        	String uuidp = UUID.randomUUID().toString().toUpperCase();
        	String code = null;
        	Integer number = 16;    //获取位数
        	String password = null; //龙卡密码
			if (record.getRule() != null && record.getRule().equals("0")) { //生成优惠券码
				code =  BigDecimal
								.valueOf(
										Math.round((Math.random() + new Random(
												10).nextDouble())
												* Math.pow(10.0D,
														number)))
								.toString().substring(0, number);
				password = BigDecimal
						.valueOf(
								Math.round((Math.random() + new Random(
										10).nextDouble())
										* Math.pow(10.0D,
												number)))
						.toString().substring(0, number);
			} else {
				code = (uuid + uuid).replaceAll("-", "")
								.replaceAll("0|O", "K")
								.substring(0, number);
				password = (uuidp + uuidp).replaceAll("-", "")
						.replaceAll("0|O", "K")
						.substring(0, number);
			}
			card.setCreateTime(new Date());
			card.setCard(code);
			card.setIsUse(0);
			card.setLongCardId(record.getId());
			card.setPassword(password);
			tlongcarddao.insertSelective(card);
        }
		
	}


}
