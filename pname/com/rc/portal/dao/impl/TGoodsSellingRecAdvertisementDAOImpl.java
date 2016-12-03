package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TGoodsSellingRecAdvertisementDAO;
import com.rc.portal.vo.TGoodsSellingRecAdvertisement;
import com.rc.portal.vo.TGoodsSellingRecAdvertisementExample;

public class TGoodsSellingRecAdvertisementDAOImpl implements TGoodsSellingRecAdvertisementDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TGoodsSellingRecAdvertisementDAOImpl() {
        super();
    }

    public TGoodsSellingRecAdvertisementDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_goods_selling_rec_advertisement.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_goods_selling_rec_advertisement.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TGoodsSellingRecAdvertisement key = new TGoodsSellingRecAdvertisement();
        key.setId(id);
        int rows = sqlMapClient.delete("t_goods_selling_rec_advertisement.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TGoodsSellingRecAdvertisement record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_goods_selling_rec_advertisement.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TGoodsSellingRecAdvertisement record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_goods_selling_rec_advertisement.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TGoodsSellingRecAdvertisementExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_goods_selling_rec_advertisement.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TGoodsSellingRecAdvertisement selectByPrimaryKey(Long id) throws SQLException {
        TGoodsSellingRecAdvertisement key = new TGoodsSellingRecAdvertisement();
        key.setId(id);
        TGoodsSellingRecAdvertisement record = (TGoodsSellingRecAdvertisement) sqlMapClient.queryForObject("t_goods_selling_rec_advertisement.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TGoodsSellingRecAdvertisement record, TGoodsSellingRecAdvertisementExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_goods_selling_rec_advertisement.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TGoodsSellingRecAdvertisement record, TGoodsSellingRecAdvertisementExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_goods_selling_rec_advertisement.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TGoodsSellingRecAdvertisement record) throws SQLException {
        int rows = sqlMapClient.update("t_goods_selling_rec_advertisement.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TGoodsSellingRecAdvertisement record) throws SQLException {
        int rows = sqlMapClient.update("t_goods_selling_rec_advertisement.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TGoodsSellingRecAdvertisementExample {
        private Object record;

        public UpdateByExampleParms(Object record, TGoodsSellingRecAdvertisementExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TGoodsSellingRecAdvertisementExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_goods_selling_rec_advertisement.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
