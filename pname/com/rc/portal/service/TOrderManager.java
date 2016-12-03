package com.rc.portal.service;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TOrderExample;
import com.rc.portal.vo.TOrderShipment;

public interface TOrderManager {
    int countByExample(TOrderExample example) throws SQLException;

    int deleteByExample(TOrderExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TOrder record) throws SQLException;

    Long insertSelective(TOrder record) throws SQLException;

    List selectByExample(TOrderExample example) throws SQLException;

    TOrder selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TOrder record, TOrderExample example) throws SQLException;

    int updateByExample(TOrder record, TOrderExample example) throws SQLException;

    int updateByPrimaryKeySelective(TOrder record) throws SQLException;

    int updateByPrimaryKey(TOrder record) throws SQLException;

    
    long saveOrder(TOrder order,String goodsids,String goodsSums,String receiverId) throws SQLException;


    Map<String,Object> orderGoodsPrice(String goodsids,String goodsSums,String deliveryId) throws SQLException;
    
    void updateOrder(TOrder order) throws SQLException;
    
    
    /**
     * 订单支付
     * @param orderid
     * @throws SQLException
     */
    void payOrder(long orderid,long orderFlowId) throws SQLException;
    
    /**
     * 订单发货
     * @param orderid
     * @param orderShipment
     * @throws SQLException
     */
    void shipmentOrder(long orderid,TOrderShipment orderShipment) throws SQLException;
    
    /**
     * 取消订单
     * @param order
     * @throws SQLException
     */
    void cancelOrder(TOrder order) throws Exception;
    
    /**
     * 确认收获
     * @param orderId
     * @throws Exception
     */
    void finishOrder(TOrder order,String publicServiceUrl) throws Exception;
    
    /**
     * 推送订单到海典
     * @throws Exception
     */
    void pushOrder(TOrder order) throws Exception;

    /**
     * 通过接口调用
     * @param order
     * @param tMember
     * @throws SQLException
     * @throws ClientProtocolException
     * @throws IOException
     * @throws Exception
     */
	void pushOrderByHttp(TOrder order, TMember tMember) throws SQLException, ClientProtocolException, IOException,
			Exception;
    
    
}
