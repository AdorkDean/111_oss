package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TStoreDispatchingRange;
import com.rc.portal.vo.TStoreDispatchingRangeExample;

public interface TStoreDispatchingRangeManager {
    int countByExample(TStoreDispatchingRangeExample example) throws SQLException;

    int deleteByExample(TStoreDispatchingRangeExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TStoreDispatchingRange record) throws SQLException;

    Long insertSelective(TStoreDispatchingRange record) throws SQLException;

    List selectByExample(TStoreDispatchingRangeExample example) throws SQLException;

    TStoreDispatchingRange selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TStoreDispatchingRange record, TStoreDispatchingRangeExample example) throws SQLException;

    int updateByExample(TStoreDispatchingRange record, TStoreDispatchingRangeExample example) throws SQLException;

    int updateByPrimaryKeySelective(TStoreDispatchingRange record) throws SQLException;

    int updateByPrimaryKey(TStoreDispatchingRange record) throws SQLException;

	int saveStore(TStoreDispatchingRange store) throws Exception;



}
