package com.rc.portal.service;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.rc.portal.vo.RxTOrder;
import com.rc.portal.vo.RxTOrderExample;

public interface RxTOrderManager {
    int countByExample(RxTOrderExample example) throws SQLException;

    int deleteByExample(RxTOrderExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(RxTOrder record) throws SQLException;

    Long insertSelective(RxTOrder record) throws SQLException;

    List selectByExample(RxTOrderExample example) throws SQLException;

    RxTOrder selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(RxTOrder record, RxTOrderExample example) throws SQLException;

    int updateByExample(RxTOrder record, RxTOrderExample example) throws SQLException;

    int updateByPrimaryKeySelective(RxTOrder record) throws SQLException;

    int updateByPrimaryKey(RxTOrder record) throws SQLException;

	void cancelOrder(RxTOrder order) throws Exception;

	void finishOrder(RxTOrder order) throws Exception;

	void refuseOrder(RxTOrder order) throws SQLException;


    boolean createRxTOrder(long orderId,List<Map> orderList,String freight) throws Exception;

	void pushRxOrder(Long orderid) throws SQLException, ClientProtocolException, IOException, Exception;

}
