package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TGoodsSellingRecAdvertisementDAO;
import com.rc.portal.service.TGoodsSellingRecAdvertisementManager;
import com.rc.portal.vo.TGoodsSellingRecAdvertisement;
import com.rc.portal.vo.TGoodsSellingRecAdvertisementExample;

public class TGoodsSellingRecAdvertisementManagerImpl  implements TGoodsSellingRecAdvertisementManager {

    private TGoodsSellingRecAdvertisementDAO tgoodssellingrecadvertisementdao;

    public TGoodsSellingRecAdvertisementManagerImpl() {
        super();
    }
    public void  setTgoodssellingrecadvertisementdao(TGoodsSellingRecAdvertisementDAO tgoodssellingrecadvertisementdao){
        this.tgoodssellingrecadvertisementdao=tgoodssellingrecadvertisementdao;
    }
    public TGoodsSellingRecAdvertisementDAO getTgoodssellingrecadvertisementdao(){
        return this.tgoodssellingrecadvertisementdao;
    }
    public     int countByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException{
        return tgoodssellingrecadvertisementdao. countByExample( example);
    }

    public     int deleteByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException{
        return tgoodssellingrecadvertisementdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tgoodssellingrecadvertisementdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TGoodsSellingRecAdvertisement record) throws SQLException{
        return tgoodssellingrecadvertisementdao. insert( record);
    }

    public     Long insertSelective(TGoodsSellingRecAdvertisement record) throws SQLException{
        return tgoodssellingrecadvertisementdao. insertSelective( record);
    }

    public     List selectByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException{
        return tgoodssellingrecadvertisementdao. selectByExample( example);
    }

    public     TGoodsSellingRecAdvertisement selectByPrimaryKey(Long id) throws SQLException{
        return tgoodssellingrecadvertisementdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TGoodsSellingRecAdvertisement record, TGoodsSellingRecAdvertisementExample example) throws SQLException{
        return tgoodssellingrecadvertisementdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TGoodsSellingRecAdvertisement record, TGoodsSellingRecAdvertisementExample example) throws SQLException{
        return tgoodssellingrecadvertisementdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TGoodsSellingRecAdvertisement record) throws SQLException{
        return tgoodssellingrecadvertisementdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TGoodsSellingRecAdvertisement record) throws SQLException{
        return tgoodssellingrecadvertisementdao. updateByPrimaryKey( record);
    }


}
