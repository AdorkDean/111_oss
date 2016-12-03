package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExample;
import com.rc.portal.vo.TGoodsExtendWithBLOBs;
import com.rc.portal.webapp.model.GoodsFormBeanModel;
import com.rc.portal.webapp.model.PriceFormBeanModel;
import com.rc.portal.webapp.model.PriceIDFormBean;
import com.rc.portal.webapp.model.UserinfoModel;

public interface TGoodsManager {
    int countByExample(TGoodsExample example) throws SQLException;

    int deleteByExample(TGoodsExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TGoods record) throws SQLException;

    List<Long> insertSelective(PriceFormBeanModel priceformbeanmodel, TGoods record,TGoodsExtendWithBLOBs goodse,GoodsFormBeanModel goodsf, UserinfoModel user) throws SQLException;

    @SuppressWarnings("rawtypes")
	List selectByExample(TGoodsExample example) throws SQLException;

    Map<String, Object> selectByPrimaryKey1(Long id) throws Exception;
    TGoods selectByPrimaryKey(Long id) throws SQLException;
    int updateByExampleSelective(TGoods record, TGoodsExample example) throws SQLException;

    int updateByExample(TGoods record, TGoodsExample example) throws SQLException;

    long updateByPrimaryKeySelective1(PriceIDFormBean priceidformbean, PriceFormBeanModel priceformbeanmodel, TGoods record,TGoodsExtendWithBLOBs goodse,GoodsFormBeanModel goodsf,UserinfoModel user) throws SQLException;
    long updateByPrimaryKeySelective(TGoods record) throws SQLException;
    int updateByPrimaryKey(TGoods record) throws SQLException;
    long insertSpec(long id,String specName) throws SQLException;

    public List<Map<String, Object>> selectByPrimaryKey2(Map<String,Object> mapv,String type,String isRX) throws SQLException;
    
    public Long insertSelectiveByGoods(TGoods tGoods) throws SQLException;
    
	public int deleteGoodById(UserinfoModel user,long id) throws SQLException;

}
