package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.TGoodsDAO;
import com.rc.portal.dao.TGroupGoodsDAO;
import com.rc.portal.service.TGroupGoodsManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGroupGoods;
import com.rc.portal.vo.TGroupGoodsExample;

public class TGroupGoodsManagerImpl  implements TGroupGoodsManager {

    private TGroupGoodsDAO tgroupgoodsdao;
    private OpenSqlDAO opensqldao;
    private TGoodsDAO tgoodsdao;
    public TGoodsDAO getTgoodsdao() {
		return tgoodsdao;
	}
	public void setTgoodsdao(TGoodsDAO tgoodsdao) {
		this.tgoodsdao = tgoodsdao;
	}
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}

    public TGroupGoodsManagerImpl() {
        super();
    }
    public void  setTgroupgoodsdao(TGroupGoodsDAO tgroupgoodsdao){
        this.tgroupgoodsdao=tgroupgoodsdao;
    }
    public TGroupGoodsDAO getTgroupgoodsdao(){
        return this.tgroupgoodsdao;
    }
    public     int countByExample(TGroupGoodsExample example) throws SQLException{
        return tgroupgoodsdao. countByExample( example);
    }

    public     int deleteByExample(TGroupGoodsExample example) throws SQLException{
        return tgroupgoodsdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id,Long gid) throws SQLException{
    	int rows = tgroupgoodsdao. deleteByPrimaryKey( id);
    	TGoods record = new TGoods();
    	Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", gid);
		Integer con = (Integer) opensqldao.selectForObjectByMap(map, "t_goods.query_group_by_goodsid_count");
		record.setId(gid);
		if(0<con){
			record.setIsSuit(1);
		}else {
			record.setIsSuit(0);
		}
		tgoodsdao.updateByPrimaryKeySelective(record);
        return rows;
    }

    public     Long insert(TGroupGoods record) throws SQLException{
        return tgroupgoodsdao. insert( record);
    }

    public     Long insertSelective(TGroupGoods record) throws SQLException{
    	if(1==record.getMainGoods()){
    		TGroupGoodsExample example = new TGroupGoodsExample();
    		example.createCriteria().andGroupIdEqualTo(record.getGroupId()).andMainGoodsEqualTo(1);
    		List<TGroupGoods> list = tgroupgoodsdao.selectByExample(example);
    		for (TGroupGoods obj : list) {
				if(obj.getMainGoods()==1){
					obj.setMainGoods(0);
					tgroupgoodsdao.updateByPrimaryKeySelective(obj);
				}
			}
    	}
    	long id = tgroupgoodsdao. insertSelective( record);
    	TGoods obj = new TGoods();
    	obj.setId(record.getGroupId());
    	obj.setIsSuit(1);
    	tgoodsdao.updateByPrimaryKeySelective(obj);
        return id;
    }

    public     List selectByExample(TGroupGoodsExample example) throws SQLException{
        return tgroupgoodsdao. selectByExample( example);
    }

    public     TGroupGoods selectByPrimaryKey(Long id) throws SQLException{
        return tgroupgoodsdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TGroupGoods record, TGroupGoodsExample example) throws SQLException{
        return tgroupgoodsdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TGroupGoods record, TGroupGoodsExample example) throws SQLException{
        return tgroupgoodsdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TGroupGoods record) throws SQLException{
        return tgroupgoodsdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TGroupGoods record) throws SQLException{
        return tgroupgoodsdao. updateByPrimaryKey( record);
    }


}
