package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TGoodsSellingRecAdvertisement;
import com.rc.portal.vo.TGoodsSellingRecAdvertisementExample;

public interface TGoodsSellingRecAdvertisementDAO {
    int countByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException;

    int deleteByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TGoodsSellingRecAdvertisement record) throws SQLException;

    Long insertSelective(TGoodsSellingRecAdvertisement record) throws SQLException;

    List selectByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException;

    TGoodsSellingRecAdvertisement selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TGoodsSellingRecAdvertisement record, TGoodsSellingRecAdvertisementExample example) throws SQLException;

    int updateByExample(TGoodsSellingRecAdvertisement record, TGoodsSellingRecAdvertisementExample example) throws SQLException;

    int updateByPrimaryKeySelective(TGoodsSellingRecAdvertisement record) throws SQLException;

    int updateByPrimaryKey(TGoodsSellingRecAdvertisement record) throws SQLException;


}
