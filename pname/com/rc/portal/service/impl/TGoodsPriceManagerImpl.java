package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.rc.portal.dao.TGoodsBrokerageDAO;
import com.rc.portal.dao.TGoodsPriceDAO;
import com.rc.portal.dao.TOssOperateLogDAO;
import com.rc.portal.service.TGoodsPriceManager;
import com.rc.portal.vo.TGoodsBrokerage;
import com.rc.portal.vo.TGoodsBrokerageExample;
import com.rc.portal.vo.TGoodsPrice;
import com.rc.portal.vo.TGoodsPriceExample;
import com.rc.portal.vo.TOssOperateLog;
import com.rc.portal.webapp.model.UserinfoModel;

public class TGoodsPriceManagerImpl  implements TGoodsPriceManager {

    private TGoodsPriceDAO tgoodspricedao;
    private TGoodsBrokerageDAO tgoodsbrokeragedao;
    private TOssOperateLogDAO tossoperatelogdao;

    public TGoodsPriceManagerImpl() {
        super();
    }
    
    
    public TOssOperateLogDAO getTossoperatelogdao() {
		return tossoperatelogdao;
	}


	public void setTossoperatelogdao(TOssOperateLogDAO tossoperatelogdao) {
		this.tossoperatelogdao = tossoperatelogdao;
	}


	public TGoodsBrokerageDAO getTgoodsbrokeragedao() {
		return tgoodsbrokeragedao;
	}


	public void setTgoodsbrokeragedao(TGoodsBrokerageDAO tgoodsbrokeragedao) {
		this.tgoodsbrokeragedao = tgoodsbrokeragedao;
	}


	public void  setTgoodspricedao(TGoodsPriceDAO tgoodspricedao){
        this.tgoodspricedao=tgoodspricedao;
    }
    public TGoodsPriceDAO getTgoodspricedao(){
        return this.tgoodspricedao;
    }
    public     int countByExample(TGoodsPriceExample example) throws SQLException{
        return tgoodspricedao. countByExample( example);
    }

    public     int deleteByExample(TGoodsPriceExample example) throws SQLException{
        return tgoodspricedao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tgoodspricedao. deleteByPrimaryKey( id);
    }

    public     Long insert(TGoodsPrice record) throws SQLException{
        return tgoodspricedao. insert( record);
    }

    public     Long insertSelective(TGoodsPrice record) throws SQLException{
        return tgoodspricedao. insertSelective( record);
    }

    public     List selectByExample(TGoodsPriceExample example) throws SQLException{
        return tgoodspricedao. selectByExample( example);
    }

    public     TGoodsPrice selectByPrimaryKey(Long id) throws SQLException{
        return tgoodspricedao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TGoodsPrice record, TGoodsPriceExample example) throws SQLException{
        return tgoodspricedao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TGoodsPrice record, TGoodsPriceExample example) throws SQLException{
        return tgoodspricedao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TGoodsPrice record) throws SQLException{
        return tgoodspricedao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TGoodsPrice record) throws SQLException{
        return tgoodspricedao. updateByPrimaryKey( record);
    }
    
	public int setStatus(Integer type, Long goodsid,UserinfoModel user) throws Exception {
		Integer flag=0;
		if(type!=null&&type!=0){
			if(goodsid!=null&&goodsid!=0){
				TGoodsPriceExample tpe=new TGoodsPriceExample();
				if(type==1){
					tpe.createCriteria().andGoodsIdEqualTo(goodsid).andPlatformTypeEqualTo("111yao").andPriceTypeEqualTo("pc");
				}else if(type==2){
					tpe.createCriteria().andGoodsIdEqualTo(goodsid).andPlatformTypeEqualTo("111yao").andPriceTypeEqualTo("wap");
				}else if(type==3){
					tpe.createCriteria().andGoodsIdEqualTo(goodsid).andPlatformTypeEqualTo("111yao").andPriceTypeEqualTo("app");
				}
				List<TGoodsPrice> priceList = tgoodspricedao.selectByExample(tpe);
				if(priceList!=null&&priceList.size()>0){
					TGoodsPrice g=priceList.get(0);
					g.setStatus(2);
					flag = tgoodspricedao.updateByPrimaryKeySelective(g);
					TGoodsBrokerageExample gbe=new TGoodsBrokerageExample();
					gbe.createCriteria().andGoodsIdEqualTo(g.getGoodsId());
					List<TGoodsBrokerage> gbList = tgoodsbrokeragedao.selectByExample(gbe);
					if(gbList!=null&&gbList.size()>0){
						TGoodsBrokerage goodsBrokerage = gbList.get(0);
						goodsBrokerage.setAuditStatus(0);
						goodsBrokerage.setAuditRemark("商品下架佣金失效");
						goodsBrokerage.setApplyUser(user.getUsername());
						tgoodsbrokeragedao.updateByPrimaryKeySelective(goodsBrokerage);
						TOssOperateLog logs=new TOssOperateLog();
						logs.setModuleType(2);
						logs.setOperationRemake("商品下架导致佣金失效");
						logs.setRecordId(goodsBrokerage.getGoodsId());
						logs.setOperationDt(new Date());
						logs.setCreateDt(new Date());
						logs.setOperationUsername(user.getUsername());
						tossoperatelogdao.insertSelective(logs);
					}
					TOssOperateLog logs=new TOssOperateLog();
					logs.setModuleType(1);
					logs.setOperationRemake("操作商品下架");
					logs.setRecordId(g.getGoodsId());
					logs.setOperationDt(new Date());
					logs.setCreateDt(new Date());
					logs.setOperationUsername(user.getUsername());
					tossoperatelogdao.insertSelective(logs);
				}
			}
		}
		return flag;
	}


}
