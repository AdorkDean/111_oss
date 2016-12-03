package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.RxTReserveOrder;
import com.rc.portal.vo.RxTReserveOrderExample;

public interface RxTReserveOrderManager {
    int countByExample(RxTReserveOrderExample example) throws SQLException;

    int deleteByExample(RxTReserveOrderExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(RxTReserveOrder record) throws SQLException;

    Long insertSelective(RxTReserveOrder record) throws SQLException;

    List selectByExample(RxTReserveOrderExample example) throws SQLException;

    RxTReserveOrder selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(RxTReserveOrder record, RxTReserveOrderExample example) throws SQLException;

    int updateByExample(RxTReserveOrder record, RxTReserveOrderExample example) throws SQLException;

    int updateByPrimaryKeySelective(RxTReserveOrder record) throws SQLException;

    int updateByPrimaryKey(RxTReserveOrder record) throws SQLException;

   /**
    * 审核 或是 取消订单修改
    * @param reserverOtder
    * @return
    * @throws Exception
    */
    boolean changeOrderStatus(RxTReserveOrder reserverOtder) throws Exception;
    
    /**
     * 撤回订单
     * @return
     * @throws Exception
     */
    String revokeReserverOrder(RxTReserveOrder reserverOtder) throws Exception;
    

}
