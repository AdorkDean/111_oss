package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.commons.freemarker.InfoUtil;
import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.TCategoryDAO;
import com.rc.portal.dao.TCategoryGoodsDAO;
import com.rc.portal.dao.TFreightDAO;
import com.rc.portal.dao.TGoodFreightDAO;
import com.rc.portal.dao.TGoodsBrokerageDAO;
import com.rc.portal.dao.TGoodsDAO;
import com.rc.portal.dao.TGoodsExtendDAO;
import com.rc.portal.dao.TGoodsImagesDAO;
import com.rc.portal.dao.TGoodsPremiumsDAO;
import com.rc.portal.dao.TGoodsPriceDAO;
import com.rc.portal.dao.TGoodsPropertyDAO;
import com.rc.portal.dao.TGroupGoodsDAO;
import com.rc.portal.dao.TOssOperateLogDAO;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.util.DateUtil;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TCategoryExample;
import com.rc.portal.vo.TCategoryGoods;
import com.rc.portal.vo.TCategoryGoodsExample;
import com.rc.portal.vo.TFreight;
import com.rc.portal.vo.TGoodFreight;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsBrokerage;
import com.rc.portal.vo.TGoodsBrokerageExample;
import com.rc.portal.vo.TGoodsExample;
import com.rc.portal.vo.TGoodsExtendExample;
import com.rc.portal.vo.TGoodsExtendWithBLOBs;
import com.rc.portal.vo.TGoodsImages;
import com.rc.portal.vo.TGoodsImagesExample;
import com.rc.portal.vo.TGoodsPremiums;
import com.rc.portal.vo.TGoodsPremiumsExample;
import com.rc.portal.vo.TGoodsPrice;
import com.rc.portal.vo.TGoodsProperty;
import com.rc.portal.vo.TGroupGoods;
import com.rc.portal.vo.TGroupGoodsExample;
import com.rc.portal.vo.TOssOperateLog;
import com.rc.portal.webapp.model.GoodsFormBeanModel;
import com.rc.portal.webapp.model.GoodsGroupModel;
import com.rc.portal.webapp.model.GoodsGroupModel2;
import com.rc.portal.webapp.model.GoodsPremiumsModel;
import com.rc.portal.webapp.model.GroupModel;
import com.rc.portal.webapp.model.NewGoodsFormBean;
import com.rc.portal.webapp.model.PriceFormBeanModel;
import com.rc.portal.webapp.model.PriceIDFormBean;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.ImageSrcUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class TGoodsManagerImpl implements TGoodsManager
{

	private TGoodsDAO tgoodsdao;
	private TGoodsExtendDAO tgoodsextenddao;
	private TGoodsImagesDAO tgoodsimagesdao;
	private TGroupGoodsDAO tgroupgoodsdao;
	private TGoodsPremiumsDAO tgoodspremiumsdao;
	private OpenSqlDAO opensqldao;
	private TCategoryGoodsDAO tcategorygoodsdao;
	private TCategoryDAO tcategorydao;
	private TGoodsPropertyDAO tgoodspropertydao;
	private TFreightDAO tfreightdao;
	private TGoodFreightDAO tgoodfreightdao;
	private TGoodsPriceDAO tgoodspricedao;
	private TOssOperateLogDAO tossoperatelogdao;
    private TGoodsBrokerageDAO tgoodsbrokeragedao;

    
    
    
	public TGoodsBrokerageDAO getTgoodsbrokeragedao() {
		return tgoodsbrokeragedao;
	}

	public void setTgoodsbrokeragedao(TGoodsBrokerageDAO tgoodsbrokeragedao) {
		this.tgoodsbrokeragedao = tgoodsbrokeragedao;
	}

	public TOssOperateLogDAO getTossoperatelogdao() {
		return tossoperatelogdao;
	}

	public void setTossoperatelogdao(TOssOperateLogDAO tossoperatelogdao) {
		this.tossoperatelogdao = tossoperatelogdao;
	}

	public TGoodsPriceDAO getTgoodspricedao()
	{
		return tgoodspricedao;
	}

	public void setTgoodspricedao(TGoodsPriceDAO tgoodspricedao)
	{
		this.tgoodspricedao = tgoodspricedao;
	}

	public TFreightDAO getTfreightdao()
	{
		return tfreightdao;
	}

	public void setTfreightdao(TFreightDAO tfreightdao)
	{
		this.tfreightdao = tfreightdao;
	}

	public TGoodFreightDAO getTgoodfreightdao()
	{
		return tgoodfreightdao;
	}

	public void setTgoodfreightdao(TGoodFreightDAO tgoodfreightdao)
	{
		this.tgoodfreightdao = tgoodfreightdao;
	}

	public TGoodsPropertyDAO getTgoodspropertydao()
	{
		return tgoodspropertydao;
	}

	public void setTgoodspropertydao(TGoodsPropertyDAO tgoodspropertydao)
	{
		this.tgoodspropertydao = tgoodspropertydao;
	}

	public TCategoryDAO getTcategorydao()
	{
		return tcategorydao;
	}

	public void setTcategorydao(TCategoryDAO tcategorydao)
	{
		this.tcategorydao = tcategorydao;
	}

	public TCategoryGoodsDAO getTcategorygoodsdao()
	{
		return tcategorygoodsdao;
	}

	public void setTcategorygoodsdao(TCategoryGoodsDAO tcategorygoodsdao)
	{
		this.tcategorygoodsdao = tcategorygoodsdao;
	}

	public OpenSqlDAO getOpensqldao()
	{
		return opensqldao;
	}

	public void setOpensqldao(OpenSqlDAO opensqldao)
	{
		this.opensqldao = opensqldao;
	}

	public TGoodsPremiumsDAO getTgoodspremiumsdao()
	{
		return tgoodspremiumsdao;
	}

	public void setTgoodspremiumsdao(TGoodsPremiumsDAO tgoodspremiumsdao)
	{
		this.tgoodspremiumsdao = tgoodspremiumsdao;
	}

	public TGroupGoodsDAO getTgroupgoodsdao()
	{
		return tgroupgoodsdao;
	}

	public void setTgroupgoodsdao(TGroupGoodsDAO tgroupgoodsdao)
	{
		this.tgroupgoodsdao = tgroupgoodsdao;
	}

	public TGoodsImagesDAO getTgoodsimagesdao()
	{
		return tgoodsimagesdao;
	}

	public void setTgoodsimagesdao(TGoodsImagesDAO tgoodsimagesdao)
	{
		this.tgoodsimagesdao = tgoodsimagesdao;
	}

	public TGoodsExtendDAO getTgoodsextenddao()
	{
		return tgoodsextenddao;
	}

	public void setTgoodsextenddao(TGoodsExtendDAO tgoodsextenddao)
	{
		this.tgoodsextenddao = tgoodsextenddao;
	}

	public TGoodsManagerImpl()
	{
		super();
	}

	public void setTgoodsdao(TGoodsDAO tgoodsdao)
	{
		this.tgoodsdao = tgoodsdao;
	}

	public TGoodsDAO getTgoodsdao()
	{
		return this.tgoodsdao;
	}

	public int countByExample(TGoodsExample example) throws SQLException
	{
		return tgoodsdao.countByExample(example);
	}

	public int deleteByExample(TGoodsExample example) throws SQLException
	{
		return tgoodsdao.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Long id) throws SQLException
	{
		return tgoodsdao.deleteByPrimaryKey(id);
	}

	public Long insert(TGoods record) throws SQLException
	{
		return tgoodsdao.insert(record);
	}

	public List selectByExample(TGoodsExample example) throws SQLException
	{
		return tgoodsdao.selectByExample(example);
	}

	public int updateByExampleSelective(TGoods record, TGoodsExample example)
			throws SQLException
	{
		return tgoodsdao.updateByExampleSelective(record, example);
	}

	public int updateByExample(TGoods record, TGoodsExample example)
			throws SQLException
	{
		return tgoodsdao.updateByExample(record, example);
	}

	public int updateByPrimaryKey(TGoods record) throws SQLException
	{
		return tgoodsdao.updateByPrimaryKey(record);
	}

	public TGoods selectByPrimaryKey(Long id) throws SQLException
	{
		return tgoodsdao.selectByPrimaryKey(id);
	}

	public long updateByPrimaryKeySelective(TGoods record) throws SQLException
	{
		return tgoodsdao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 添加规格
	 */
	public long insertSpec(long id, String specName) throws SQLException
	{
		TGoods goods = tgoodsdao.selectByPrimaryKey(id);
		goods.setCreateDt(new Date());
		goods.setId(null);
		goods.setSpec(specName);
		goods.setIsRelease(0);
		goods.setBarCode("");
		goods.setApprovalNumber("");
		goods.setGoodsno("");
		goods.setGoodscode(System.currentTimeMillis() + "1");
		long nid = tgoodsdao.insertSelective(goods);
		TGoodsProperty record = new TGoodsProperty();
		record.setGoodsid(nid);
		record.setEvaluate(0);
		record.setSales(0);
		record.setAttention(0);
		tgoodspropertydao.insertSelective(record);
		TGoodsExtendExample example = new TGoodsExtendExample();
		example.createCriteria().andGoodsidEqualTo(id);
		List<TGoodsExtendWithBLOBs> liste = tgoodsextenddao.selectByExampleWithBLOBs(example);
		if (null != liste && 0 < liste.size())
		{
			TGoodsExtendWithBLOBs goodse = liste.get(0);
			goodse.setGoodsid(nid);
			tgoodsextenddao.insertSelective(goodse);
		}
		// 分类
		TCategoryGoodsExample examplec = new TCategoryGoodsExample();
		examplec.createCriteria().andGoodsidEqualTo(id);
		List<TCategoryGoods> listc = tcategorygoodsdao.selectByExample(examplec);
		for (TCategoryGoods obj : listc)
		{
			obj.setId(null);
			obj.setGoodsid(nid);
			tcategorygoodsdao.insertSelective(obj);
		}
		// 赠品
		TGoodsPremiumsExample examplep = new TGoodsPremiumsExample();
		examplep.createCriteria().andGoodsIdEqualTo(id);
		List<TGoodsPremiums> listp = tgoodspremiumsdao.selectByExample(examplep);
		for (TGoodsPremiums obj : listp)
		{
			obj.setId(null);
			obj.setGoodsId(nid);
			tgoodspremiumsdao.insertSelective(obj);
		}
		// 组合
		TGroupGoodsExample exampleg = new TGroupGoodsExample();
		exampleg.createCriteria().andGroupIdEqualTo(id);
		List<TGroupGoods> listg = tgroupgoodsdao.selectByExample(exampleg);
		for (TGroupGoods obj : listg)
		{
			obj.setId(null);
			obj.setGroupId(nid);
			tgroupgoodsdao.insertSelective(obj);
		}
		TGoodsImagesExample examplei = new TGoodsImagesExample();
		examplei.createCriteria().andGoodsidEqualTo(id);
		List<TGoodsImages> listi = tgoodsimagesdao.selectByExample(examplei);
		for (TGoodsImages obj : listi)
		{
			obj.setId(null);
			obj.setGoodsid(nid);
			tgoodsimagesdao.insertSelective(obj);
		}
		
		//价格封装计算
		
		BigDecimal pc_price = null;
		BigDecimal wap_price = null;
		BigDecimal app_price = null;
		
		Integer pc_status = null;
		Integer wap_status = null;
		Integer app_status = null;
		
		Integer pc_isshow = null;
		Integer wap_isshow = null;
		Integer app_isshow = null;
		
		Integer isPcTop = null;
		Integer isWapTop = null;
		Integer isAppTop = null;
		
		Date pcUpDate = null;
		Date pcDownDate = null;
		
		Date wapUpDate = null;
		Date wapDownDate = null;
		
		Date appUpDate = null;
		Date appDownDate = null;
		
		Map<String, Object> mPrice = new HashMap<String, Object>();
		mPrice.put("goodsId", id);
		List<Map<String, Object>> priceDatas = opensqldao.selectForListByMap(mPrice, "t_goods.selectPriceDateByGoodsId");
		for(Map<String, Object> mp : priceDatas)
		{
			String price_type = mp.get("price_type")+"";
			if(price_type.trim().equals("pc"))
			{
				pc_price = new BigDecimal(mp.get("price")+"");
				pc_status = Integer.parseInt(mp.get("status")+"");
				pc_isshow = Integer.parseInt(mp.get("is_show")+"");
				isPcTop = Integer.parseInt(mp.get("is_top")+"");
				pcUpDate = DateUtil.strDateConvertToDate(mp.get("up_dt")+"", "yyyy-MM-dd HH:mm:ss");
				pcDownDate = DateUtil.strDateConvertToDate(mp.get("down_dt")+"", "yyyy-MM-dd HH:mm:ss");
			}
			if(price_type.trim().equals("wap"))
			{
				wap_price = new BigDecimal(mp.get("price")+"");
				wap_status = Integer.parseInt(mp.get("status")+"");
				wap_isshow = Integer.parseInt(mp.get("is_show")+"");
				isWapTop = Integer.parseInt(mp.get("is_top")+"");
				wapUpDate = DateUtil.strDateConvertToDate(mp.get("up_dt")+"", "yyyy-MM-dd HH:mm:ss");
				wapDownDate = DateUtil.strDateConvertToDate(mp.get("down_dt")+"", "yyyy-MM-dd HH:mm:ss");
			}
			if(price_type.trim().equals("app"))
			{
				app_price = new BigDecimal(mp.get("price")+"");
				app_status = Integer.parseInt(mp.get("status")+"");
				app_isshow = Integer.parseInt(mp.get("is_show")+"");
				isAppTop = Integer.parseInt(mp.get("is_top")+"");
				appUpDate = DateUtil.strDateConvertToDate(mp.get("up_dt")+"", "yyyy-MM-dd HH:mm:ss");
				appDownDate = DateUtil.strDateConvertToDate(mp.get("down_dt")+"", "yyyy-MM-dd HH:mm:ss");
			}
		}
		TGoodsPrice pc_TGoodsPrice = new TGoodsPrice();
		pc_TGoodsPrice.setCreateDt(new Date());
		pc_TGoodsPrice.setUpDt(pcUpDate);
		pc_TGoodsPrice.setDownDt(pcDownDate);
		pc_TGoodsPrice.setGoodsId(nid);
		pc_TGoodsPrice.setIsShow(pc_isshow);
		pc_TGoodsPrice.setIsTop(isPcTop);
		pc_TGoodsPrice.setPlatformType("111yao");
		pc_TGoodsPrice.setPrice(pc_price);
		pc_TGoodsPrice.setPriceType("pc");
		pc_TGoodsPrice.setStatus(pc_status);
		tgoodspricedao.insert(pc_TGoodsPrice);
		
		TGoodsPrice wap_TGoodsPrice = new TGoodsPrice();
		wap_TGoodsPrice.setCreateDt(new Date());
		wap_TGoodsPrice.setUpDt(wapUpDate);
		wap_TGoodsPrice.setDownDt(wapDownDate);
		wap_TGoodsPrice.setGoodsId(nid);
		wap_TGoodsPrice.setIsShow(wap_isshow);
		wap_TGoodsPrice.setIsTop(isWapTop);
		wap_TGoodsPrice.setPlatformType("111yao");
		wap_TGoodsPrice.setPrice(wap_price);
		wap_TGoodsPrice.setPriceType("wap");
		wap_TGoodsPrice.setStatus(wap_status);
		tgoodspricedao.insert(wap_TGoodsPrice);
		
		TGoodsPrice app_TGoodsPrice = new TGoodsPrice();
		app_TGoodsPrice.setCreateDt(new Date());
		app_TGoodsPrice.setUpDt(appUpDate);
		app_TGoodsPrice.setDownDt(appDownDate);
		app_TGoodsPrice.setGoodsId(nid);
		app_TGoodsPrice.setIsShow(app_isshow);
		app_TGoodsPrice.setIsTop(isAppTop);
		app_TGoodsPrice.setPlatformType("111yao");
		app_TGoodsPrice.setPrice(app_price);
		app_TGoodsPrice.setPriceType("app");
		app_TGoodsPrice.setStatus(app_status);
		tgoodspricedao.insert(app_TGoodsPrice);
		return nid;
	}

	/**
	 * 修改商品
	 * @param priceformbeanmodel
	 * @param record
	 * @param goodse
	 * @param goodsf
	 * @return
	 * @throws SQLException
	 */
	public long updateByPrimaryKeySelective1(PriceIDFormBean priceidformbean, PriceFormBeanModel priceformbeanmodel, TGoods record,TGoodsExtendWithBLOBs goodse, GoodsFormBeanModel goodsf,UserinfoModel user) throws SQLException
	{
		record.setBrandId(record.getBrandId() == null ? 0l : record.getBrandId());
		record.setManufacturerId(record.getManufacturerId() == null ? 0l : record.getManufacturerId());
		record.setDoseId(record.getDoseId() == null ? 0l : record.getDoseId());
		record.setIsRelease(0);
		tgoodsdao.updateByPrimaryKeySelective(record);
		TGoodsExtendExample example = new TGoodsExtendExample();
		example.createCriteria().andGoodsidEqualTo(record.getId());
		String goodsDescribes = goodse.getGoodsDescribes();
		String instructions = goodse.getInstructions();
		if(StringUtils.hasText(goodsDescribes))
		{
			if(goodsDescribes.indexOf("img") > 0)
			{
				String imageDomain = InfoUtil.getInstance().getInfo("config", "image_domain");
				if(goodsDescribes.indexOf("http://ui1.111yao.com") > 0)
				{
					goodsDescribes = goodsDescribes.replaceAll("http://ui1.111yao.com", "");
				}
				goodsDescribes = ImageSrcUtil.replaceAllImageSrcPros(goodsDescribes, imageDomain, 1);
			}
			goodse.setGoodsDescribe(goodsDescribes.getBytes());
		}
		if (StringUtils.hasText(instructions))
		{
			if(instructions.indexOf("img") > 0)
			{
				String imageDomain = InfoUtil.getInstance().getInfo("config", "image_domain");
				instructions = ImageSrcUtil.replaceAllImageSrcPros(instructions, imageDomain, 1);
			}
			goodse.setInstruction(instructions.getBytes());
		}
		tgoodsextenddao.updateByExampleSelective(goodse, example);

		//处理商品分类
		String goodsCategoryId_str = goodsf.getCategoryId();
		if(StringUtils.hasText(goodsCategoryId_str))
		{
			TCategoryGoodsExample examplec = new TCategoryGoodsExample();
			examplec.createCriteria().andGoodsidEqualTo(record.getId()).andTypeEqualTo(2);
			List<TCategoryGoods> list = tcategorygoodsdao.selectByExample(examplec);
			if (null != list && 0 < list.size())
			{
				TCategoryGoods category = list.get(0);
				category.setCategoryid(Long.parseLong(goodsCategoryId_str));
				tcategorygoodsdao.updateByPrimaryKeySelective(category);
			}
		} 
		else
		{
			String goodsCategoryId1_str = goodsf.getCategoryId1();
			if(StringUtils.hasText(goodsCategoryId1_str))
			{
				TCategoryGoods category = new TCategoryGoods();
				category.setType(2);
				category.setGoodsid(record.getId());
				category.setCategoryid(Long.parseLong(goodsCategoryId1_str));
				tcategorygoodsdao.insertSelective(category);
			}
		}
		
		//医药馆分类
		String yiyaoguanId_str = goodsf.getYiyaoguanId();
		if(StringUtils.hasText(yiyaoguanId_str))
		{
			if (!goodsf.getYiyaoguanId().equals(goodsf.getYiyaoguanId1()))
			{
				String id = goodsf.getYiyaoguanId();
				String id1 = goodsf.getYiyaoguanId1();
				String[] ids = id.split(",");
				String[] ids1 = id1.split(",");
				List<Long> listn = getRemoveAll(ids, ids1, 1);
				List<Long> listn1 = getRemoveAll(ids, ids1, 2);
				if (null != listn && 0 < listn.size())
				{
					TCategoryGoodsExample exampletg = new TCategoryGoodsExample();
					exampletg.createCriteria().andCategoryidIn(listn).andGoodsidEqualTo(record.getId()).andTypeEqualTo(1);
					tcategorygoodsdao.deleteByExample(exampletg);
				}
				if (null != listn1 && 0 < listn1.size())
				{
					for (Long strid : listn1)
					{
						TCategoryGoods obj = new TCategoryGoods();
						obj.setCategoryid(strid);
						obj.setGoodsid(record.getId());
						obj.setType(1);
						tcategorygoodsdao.insertSelective(obj);
					}
				}

			}
		} 
		else
		{
			if (StringUtils.hasText(goodsf.getYiyaoguanId1()))
			{
				String yygid = goodsf.getYiyaoguanId1();
				String[] yygids = yygid.split(",");
				for (String id : yygids)
				{
					TCategoryGoods category = new TCategoryGoods();
					category.setType(1);
					category.setGoodsid(record.getId());
					category.setCategoryid(Long.parseLong(id));
					tcategorygoodsdao.insertSelective(category);
				}
			}
		}
		
		//商品价格实体更新
		
		BigDecimal pc_price = priceformbeanmodel.getPcPrice();
		BigDecimal wap_price = priceformbeanmodel.getWapPrice();
		BigDecimal app_price = priceformbeanmodel.getAppPrice();
		
		//PC平台
		if(pc_price != null)
		{
			TGoodsPrice pc_TGoodsPrice = tgoodspricedao.selectByPrimaryKey(priceidformbean.getPc_priceId());
			pc_TGoodsPrice.setPrice(pc_price);
			Integer pcStatus = priceformbeanmodel.getPcStatus();
			Integer isPcShow = priceformbeanmodel.getIsPcShow();
			Integer isPcTop = priceformbeanmodel.getIsPcTop();
			if(pcStatus != null)
			{
				pc_TGoodsPrice.setStatus(1);
			}
			else
			{
				pc_TGoodsPrice.setStatus(2);
			}
			if(isPcShow != null)
			{
				pc_TGoodsPrice.setIsShow(1);
			}
			else
			{
				pc_TGoodsPrice.setIsShow(0);
			}
			if(isPcTop != null)
			{
				pc_TGoodsPrice.setIsTop(1);
			}
			else
			{
				pc_TGoodsPrice.setIsTop(2);
			}
			
			String pcupdate = priceformbeanmodel.getPcUpdate();
			String pcdowndate = priceformbeanmodel.getPcDowndate();
			if(pcupdate != null)
			{
				pc_TGoodsPrice.setUpDt(DateUtil.strDateConvertToDate(pcupdate, "yyyy-MM-dd HH:mm:ss"));
			}
			if(pcdowndate != null)
			{
				pc_TGoodsPrice.setDownDt(DateUtil.strDateConvertToDate(pcdowndate, "yyyy-MM-dd HH:mm:ss"));
			}
			tgoodspricedao.updateByPrimaryKey(pc_TGoodsPrice);
		}
		
		//WAP平台
		if(wap_price != null)
		{
			TGoodsPrice wap_TGoodsPrice = tgoodspricedao.selectByPrimaryKey(priceidformbean.getWap_priceId());
			wap_TGoodsPrice.setPrice(wap_price);
			Integer wapStatus = priceformbeanmodel.getWapStatus();
			Integer isWapShow = priceformbeanmodel.getIsWapShow();
			Integer isWapTop = priceformbeanmodel.getIsWapTop();
			if(wapStatus != null)
			{
				wap_TGoodsPrice.setStatus(1);
			}
			else
			{
				wap_TGoodsPrice.setStatus(2);
			}
			if(isWapShow != null)
			{
				wap_TGoodsPrice.setIsShow(1);
			}
			else
			{
				wap_TGoodsPrice.setIsShow(0);
			}
			if(isWapTop != null)
			{
				wap_TGoodsPrice.setIsTop(1);
			}
			else
			{
				wap_TGoodsPrice.setIsTop(2);
			}
			
			String wapupdate = priceformbeanmodel.getWapUpdate();
			String wapdowndate = priceformbeanmodel.getWapDowndate();
			if(wapupdate != null)
			{
				wap_TGoodsPrice.setUpDt(DateUtil.strDateConvertToDate(wapupdate, "yyyy-MM-dd HH:mm:ss"));
			}
			if(wapdowndate != null)
			{
				wap_TGoodsPrice.setDownDt(DateUtil.strDateConvertToDate(wapdowndate, "yyyy-MM-dd HH:mm:ss"));
			}
			tgoodspricedao.updateByPrimaryKey(wap_TGoodsPrice);
		}
		
		//APP平台
		if(app_price != null)
		{
			TGoodsPrice app_TGoodsPrice = tgoodspricedao.selectByPrimaryKey(priceidformbean.getApp_priceId());
			app_TGoodsPrice.setPrice(app_price);
			Integer appStatus = priceformbeanmodel.getAppStatus();
			Integer isAppShow = priceformbeanmodel.getIsAppShow();
			Integer isAppTop = priceformbeanmodel.getIsAppTop();
			if(appStatus != null)
			{
				app_TGoodsPrice.setStatus(1);
			}
			else
			{
				app_TGoodsPrice.setStatus(2);
			}
			if(isAppShow != null)
			{
				app_TGoodsPrice.setIsShow(1);
			}
			else
			{
				app_TGoodsPrice.setIsShow(0);
			}
			if(isAppTop != null)
			{
				app_TGoodsPrice.setIsTop(1);
			}
			else
			{
				app_TGoodsPrice.setIsTop(2);
			}
			String appupdate = priceformbeanmodel.getAppUpdate();
			String appdowndate = priceformbeanmodel.getAppDowndate();
			if(appupdate != null)
			{
				app_TGoodsPrice.setUpDt(DateUtil.strDateConvertToDate(appupdate, "yyyy-MM-dd HH:mm:ss"));
			}
			if(appdowndate != null)
			{
				app_TGoodsPrice.setDownDt(DateUtil.strDateConvertToDate(appdowndate, "yyyy-MM-dd HH:mm:ss"));
			}
			tgoodspricedao.updateByPrimaryKey(app_TGoodsPrice);
		}
		
		TGoodsBrokerageExample gbe=new TGoodsBrokerageExample();
		gbe.createCriteria().andGoodsIdEqualTo(record.getId());
		List<TGoodsBrokerage> gbList = tgoodsbrokeragedao.selectByExample(gbe);
		if(gbList!=null&&gbList.size()>0){
			TGoodsBrokerage goodsBrokerage = gbList.get(0);
			goodsBrokerage.setAuditStatus(0);
			goodsBrokerage.setAuditRemark("商品修改佣金失效");
			goodsBrokerage.setApplyUser(user.getUsername());
			tgoodsbrokeragedao.updateByPrimaryKeySelective(goodsBrokerage);
			TOssOperateLog logs=new TOssOperateLog();
			logs.setModuleType(2);
			logs.setOperationRemake("商品修改导致佣金失效");
			logs.setRecordId(goodsBrokerage.getGoodsId());
			logs.setOperationDt(new Date());
			logs.setCreateDt(new Date());
			logs.setOperationUsername(user.getUsername());
			tossoperatelogdao.insertSelective(logs);
		}
		
		TOssOperateLog logs=new TOssOperateLog();
		logs.setModuleType(1);
		logs.setOperationRemake("修改商品");
		logs.setRecordId(record.getId());
		logs.setOperationDt(new Date());
		logs.setCreateDt(new Date());
		logs.setOperationUsername(user.getUsername());
		tossoperatelogdao.insertSelective(logs);
		return record.getId();
	}

	private List<Long> getRemoveAll(String[] ids, String[] ids1, int type)
	{

		List<Long> listnl = new ArrayList<Long>();
		List<Long> listnl1 = new ArrayList<Long>();
		for (String str : ids)
		{
			listnl.add(Long.parseLong(str));
		}
		for (String str : ids1)
		{
			listnl1.add(Long.parseLong(str));
		}
		if (type == 1)
		{
			listnl.removeAll(listnl1);
			return listnl;
		} else
		{
			listnl1.removeAll(listnl);
			return listnl1;
		}
	}

	/**
	 * 根据ID查看商品详情
	 */
	public Map<String, Object> selectByPrimaryKey1(Long id) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		TGoods goods = tgoodsdao.selectByPrimaryKey(id);
		TGoodsExtendExample example = new TGoodsExtendExample();
		example.createCriteria().andGoodsidEqualTo(id);
		List<TGoodsExtendWithBLOBs> liste = tgoodsextenddao.selectByExampleWithBLOBs(example);
		TGoodsExtendWithBLOBs goodse = null;
		if(null != liste && 0 < liste.size())
		{
			goodse = liste.get(0);
			byte[] instructions = goodse.getInstruction();
			if (null != instructions)
			{
				String ints = new String(instructions);
				if(ints.indexOf("img") > 0)
				{
					String imageDomain = InfoUtil.getInstance().getInfo("config", "image_domain");
					ints = ImageSrcUtil.replaceAllImageSrcPros(ints, imageDomain, 2);
				}
				goodse.setInstruction(ints.getBytes());
				goodse.setInstructions(ints);
			}
			byte[] goodsDescribes = goodse.getGoodsDescribe();
			if (null != goodsDescribes)
			{
				String gDescribes = new String(goodsDescribes);
				if(gDescribes.indexOf("img") > 0)
				{
					String imageDomain = InfoUtil.getInstance().getInfo("config", "image_domain");
					gDescribes = ImageSrcUtil.replaceAllImageSrcPros(gDescribes, imageDomain, 2);
				}
				goodse.setGoodsDescribe(gDescribes.getBytes());
				goodse.setGoodsDescribes(gDescribes);
			}
		}
		TGoodsImagesExample examplei = new TGoodsImagesExample();
		examplei.createCriteria().andGoodsidEqualTo(id);
		List<TGoodsImages> listi = tgoodsimagesdao.selectByExample(examplei);
		TGoodsPremiumsExample examplep = new TGoodsPremiumsExample();
		examplep.createCriteria().andGoodsIdEqualTo(id);
		Map<String, Object> valmap = new HashMap<String, Object>();
		valmap.put("id", id);
		List<GoodsPremiumsModel> listp = opensqldao.selectForListByMap(valmap,"t_goods.query_property_by_goodsid");
		List<GoodsGroupModel> listg = opensqldao.selectForListByMap(valmap,"t_goods.query_group_by_goodsid");
		
		GoodsFormBeanModel goodsf = new GoodsFormBeanModel();
		//商品分类
		TCategoryGoodsExample examplec = new TCategoryGoodsExample();
		examplec.createCriteria().andGoodsidEqualTo(id).andTypeEqualTo(2);
		List<TCategoryGoods> listc = tcategorygoodsdao.selectByExample(examplec);
		if (null != listc && 0 < listc.size())
		{
			TCategoryGoods obj = listc.get(0);
			goodsf.setCategoryId(obj.getCategoryid() + "");
			TCategory cobj = tcategorydao.selectByPrimaryKey(obj.getCategoryid());
			String apis = cobj.getAllParentId();
			String apiv = apis.substring(1);
			if (StringUtils.hasText(apiv))
			{
				String[] apisv = apiv.split(",");
				if (null != apisv && 0 < apisv.length)
				{
					for (int i = 0; i < apisv.length; i++)
					{
						if (3 == apisv.length)
						{
							if (0 == i)
							{
								goodsf.setFl1(getfl(i,Long.parseLong(apisv[1]), 0l, 2));
							} else if (1 == i)
							{
								goodsf.setFl2(getfl(i,Long.parseLong(apisv[2]),Long.parseLong(apisv[1]), 2));
							} else if (2 == i)
							{
								goodsf.setFl3(getfl(i, obj.getCategoryid(),Long.parseLong(apisv[2]), 2));
							}
						} 
						else if (2 == apisv.length)
						{
							if (0 == i)
							{
								goodsf.setFl1(getfl(i,Long.parseLong(apisv[1]), 0l, 2));
							} 
							else if (1 == i)
							{
								goodsf.setFl2(getfl(i, obj.getCategoryid(),Long.parseLong(apisv[1]), 2));
							}
						} 
						else if (1 == apisv.length)
						{
							goodsf.setFl1(getfl(i, obj.getCategoryid(),Long.parseLong(apisv[0]), 2));
						}
					}
				}
			}
		} else
		{
			goodsf.setFl1(getNewfl("goods_category_id"));
		}
		//医药馆
		listc.clear();
		examplec.clear();
		examplec.createCriteria().andGoodsidEqualTo(id).andTypeEqualTo(1);
		listc = tcategorygoodsdao.selectByExample(examplec);
		List<Long> ids = new ArrayList<Long>();
		String yiyaoguanId = "";
		for (TCategoryGoods obj : listc)
		{
			yiyaoguanId += obj.getCategoryid() + ",";
			ids.add(obj.getCategoryid());
		}
		goodsf.setList4(ids);
		goodsf.setYiyaoguanId(yiyaoguanId);
		goodsf = getYiyaoguan(goodsf);
		goodsf.setBrand(getSelectOption1("selectBrandByMap",goods.getBrandId() != null ? goods.getBrandId() : 0));
		goodsf.setDose(getSelectOption("selectDoseByMap",goods.getDoseId() != null ? goods.getDoseId() : 0));

		if (null != goods.getManufacturerId())
		{
			valmap.clear();
			valmap.put("id", goods.getManufacturerId());
			Map<String, Object> obj = (Map<String, Object>) opensqldao.selectForObjectByMap(valmap,"t_goods.selectManufacturerByMap");
			if (null != obj)
			{
				goodsf.setManufacturer((String) obj.get("name"));
				goodsf.setManufacturerId((Long) obj.get("id") + "");
			}
		}
		
		//价格封装计算
		Integer pc_priceId = null;
		Integer wap_priceId = null;
		Integer app_priceId = null;
		
		BigDecimal pc_price = null;
		BigDecimal wap_price = null;
		BigDecimal app_price = null;
		
		Integer pc_status = null;
		Integer wap_status = null;
		Integer app_status = null;
		
		Integer pc_isshow = null;
		Integer wap_isshow = null;
		Integer app_isshow = null;
		
		Integer isPcTop = null;
		Integer isWapTop = null;
		Integer isAppTop = null;
		
		Date pcUpDate = null;
		Date pcDownDate = null;
		
		Date wapUpDate = null;
		Date wapDownDate = null;
		
		Date appUpDate = null;
		Date appDownDate = null;
		
		Map<String, Object> mPrice = new HashMap<String, Object>();
		mPrice.put("goodsId", id);
		List<Map<String, Object>> priceDatas = opensqldao.selectForListByMap(mPrice, "t_goods.selectPriceDateByGoodsId");
		for(Map<String, Object> mp : priceDatas)
		{
			String price_type = mp.get("price_type")+"";
			if(price_type.trim().equals("pc"))
			{
				try
				{
					pc_price = new BigDecimal(mp.get("price")+"");
					pc_status = Integer.parseInt(mp.get("status")+"");
					pc_isshow = Integer.parseInt(mp.get("is_show")+"");
					isPcTop = Integer.parseInt(mp.get("is_top")+"");
					if(mp.get("up_dt")!=null){
						pcUpDate = DateUtil.strDateConvertToDate(mp.get("up_dt")+"", "yyyy-MM-dd HH:mm:ss");
					}
					if(mp.get("down_dt")!=null){
						pcDownDate = DateUtil.strDateConvertToDate(mp.get("down_dt")+"", "yyyy-MM-dd HH:mm:ss");
					}
				}
				catch(Exception e)
				{
					pc_price = new BigDecimal(0);
					pc_status = 1;
					pc_isshow = 1;
					isPcTop = 2;
				}
				pc_priceId = Integer.parseInt(mp.get("id")+"");
			}
			if(price_type.trim().equals("wap"))
			{
				try
				{
					wap_price = new BigDecimal(mp.get("price")+"");
					wap_status = Integer.parseInt(mp.get("status")+"");
					wap_isshow = Integer.parseInt(mp.get("is_show")+"");
					isWapTop = Integer.parseInt(mp.get("is_top")+"");
					if(mp.get("up_dt")!=null){
						wapUpDate = DateUtil.strDateConvertToDate(mp.get("up_dt")+"", "yyyy-MM-dd HH:mm:ss");
					}
					if(mp.get("down_dt")!=null){
						wapDownDate = DateUtil.strDateConvertToDate(mp.get("down_dt")+"", "yyyy-MM-dd HH:mm:ss");
					}
				}
				catch(Exception e)
				{
					wap_price = new BigDecimal(0);
					wap_status = 1;
					wap_isshow = 1;
					isWapTop = 2;
				}
				wap_priceId = Integer.parseInt(mp.get("id")+"");
			}
			if(price_type.trim().equals("app"))
			{
				try
				{
					app_price = new BigDecimal(mp.get("price")+"");
					app_status = Integer.parseInt(mp.get("status")+"");
					app_isshow = Integer.parseInt(mp.get("is_show")+"");
					isAppTop = Integer.parseInt(mp.get("is_top")+"");
					if(mp.get("up_dt")!=null){
						appUpDate = DateUtil.strDateConvertToDate(mp.get("up_dt")+"", "yyyy-MM-dd HH:mm:ss");
					}
					if(mp.get("down_dt")!=null){
						appDownDate = DateUtil.strDateConvertToDate(mp.get("down_dt")+"", "yyyy-MM-dd HH:mm:ss");
					}
				}
				catch(Exception e)
				{
					app_price = new BigDecimal(0);
					app_status = 1;
					app_isshow = 1;
					isAppTop = 2;
				}
				app_priceId = Integer.parseInt(mp.get("id")+"");
			}
		}
		NewGoodsFormBean newGoods = new NewGoodsFormBean(goods, pc_priceId, wap_priceId, app_priceId, pc_price, wap_price, app_price, isPcTop, isWapTop, isAppTop, pc_status, wap_status, app_status, pc_isshow, wap_isshow, app_isshow, pcUpDate, pcDownDate, wapUpDate, wapDownDate, appUpDate, appDownDate);
		map.put("listp", listp);
		map.put("listi", listi);
		map.put("goods", goods);
		map.put("goodse", goodse);
		map.put("listg", listg);
		map.put("goodsf", goodsf);
		map.put("newGoods", newGoods);
		return map;
	}
	
	private GoodsFormBeanModel getYiyaoguan(GoodsFormBeanModel goodsf)
			throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("syskey", "goods_category_id");
		String flid = null;
		Object obj1 = opensqldao.selectForObjectByMap(map,
				"t_sys_parameter.query_sysc");
		if (null != obj1)
		{
			Map<String, String> objmap = (Map<String, String>) obj1;
			flid = objmap.get("sys_value");
		}
		TCategoryExample example = new TCategoryExample();
		example.createCriteria().andIdNotEqualTo(Long.parseLong(flid))
				.andCategoryLevelEqualTo(1);
		List<TCategory> list1 = tcategorydao.selectByExample(example);
		if (null != list1 && 0 < list1.size())
		{
			example.clear();
			List<Long> listid = new ArrayList<Long>();
			for (TCategory obj : list1)
			{
				listid.add(obj.getId());
			}
			if (null != listid && 0 < listid.size())
			{
				example.createCriteria().andParentIdIn(listid);
				List<TCategory> list2 = tcategorydao.selectByExample(example);
				example.clear();
				List<Long> listid1 = new ArrayList<Long>();
				for (TCategory obj : list2)
				{
					listid1.add(obj.getId());
				}
				if (null != listid1 && 0 < listid1.size())
				{
					example.createCriteria().andParentIdIn(listid1);
					List<TCategory> list3 = tcategorydao
							.selectByExample(example);
					List<Long> listid2 = new ArrayList<Long>();
					for (TCategory obj : list3)
					{
						listid2.add(obj.getId());
					}
					if (null != listid2 && 0 < listid2.size())
					{
						example.clear();
						example.createCriteria().andParentIdIn(listid2);
						List<TCategory> list4 = tcategorydao
								.selectByExample(example);
						goodsf.setList4s(list4);
					}
					goodsf.setList3(list3);
				}
				goodsf.setList2(list2);
			}
			goodsf.setList1(list1);
		}
		example.clear();
		String yygid = goodsf.getYiyaoguanId();
		if (StringUtils.hasText(yygid))
		{
			String[] ids = yygid.split(",");
			List<Long> list = new ArrayList<Long>();
			for (String id : ids)
			{
				list.add(Long.parseLong(id));
			}
			example.createCriteria().andIdIn(list);
			List<TCategory> list4 = tcategorydao.selectByExample(example);
			String yiyaoguanName = "";
			for (TCategory obj : list4)
			{
				yiyaoguanName += obj.getCategoryName() + ",";
			}
			goodsf.setYiyaoguanName(yiyaoguanName);
		}
		return goodsf;
	}

	private String getSelectOption(String sqlMap, long ids)
	{
		String data = "";
		List<Map<String, Object>> flmap = opensqldao.selectForListByMap(
				new HashMap(), "t_goods." + sqlMap);
		for (Map<String, Object> obj : flmap)
		{
			long id = (Long) obj.get("id");
			String name = (String) obj.get("name");
			if (ids == id)
			{
				data = data + "<option value='" + id + "' selected='selected'>"
						+ name + "</option>";
			} else
			{
				data = data + "<option value='" + id + "'>" + name
						+ "</option>";
			}
		}
		return data;
	}

	private String getSelectOption1(String sqlMap, long ids)
	{
		String data = "";
		List<Map<String, Object>> flmap = opensqldao.selectForListByMap(
				new HashMap(), "t_goods." + sqlMap);
		for (Map<String, Object> obj : flmap)
		{
			long id = (Long) obj.get("id");
			String name = (String) obj.get("name");
			String pinyin = (String) obj.get("pinyin");
			if (ids == id)
			{
				data = data + "<option value='" + id + "' selected='selected'>"
						+ pinyin + "|" + name + "</option>";
			} else
			{
				data = data + "<option value='" + id + "'>" + pinyin + "|"
						+ name + "</option>";
			}
		}
		return data;
	}

	public String getNewfl(String sqlmap)
	{
		String data = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("syskey", sqlmap);
		Object obj1 = opensqldao.selectForObjectByMap(map,
				"t_sys_parameter.query_sysc");
		String flid = "";
		if (null != obj1)
		{
			Map<String, String> objmap = (Map<String, String>) obj1;
			flid = objmap.get("sys_value");
		}
		map.clear();
		map.put("clevel", 2);
		map.put("pid", Long.parseLong(flid));
		List<Map<String, Object>> flmap = opensqldao.selectForListByMap(map,
				"t_category.selectCategoryByMap");
		for (Map<String, Object> obj : flmap)
		{
			long id = (Long) obj.get("id");
			String name = (String) obj.get("category_name");
			data = data + "<option value='" + id + "'>" + name + "</option>";
		}
		return data;
	}

	public String getfl(int i, long cid, long sid, int type)
	{
		String sqlmap = "";
		if (type == 1)
		{
			// sqlmap="goods_yiyaoguan_id";
		} else if (type == 2)
		{
			sqlmap = "goods_category_id";
		}
		String fl = "";
		String flid = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (i == 0)
		{
			map.put("syskey", sqlmap);
			Object obj1 = opensqldao.selectForObjectByMap(map,
					"t_sys_parameter.query_sysc");
			if (null != obj1)
			{
				Map<String, String> objmap = (Map<String, String>) obj1;
				flid = objmap.get("sys_value");
			}
			map.clear();
			map.put("clevel", 2);
			map.put("pid", Long.parseLong(flid));
		} else
		{
			map.put("pid", sid);
		}
		List<Map<String, Object>> flmap = opensqldao.selectForListByMap(map,
				"t_category.selectCategoryByMap");
		for (Map<String, Object> obj2 : flmap)
		{
			long id1 = (Long) obj2.get("id");
			String name = (String) obj2.get("category_name");
			if (cid == id1)
			{
				fl = fl + "<option value='" + id1 + "' selected='selected'>"
						+ name + "</option>";
			} else
			{
				fl = fl + "<option value='" + id1 + "'>" + name + "</option>";
			}
		}
		return fl;
	}

	/**
	 * 添加商品
	 * @param priceformbeanmodel :商品价格Bean
	 * @param record             :商品
	 * @param goodse             :商品扩展
	 * @param goodsf             :商品Bean
	 * @return
	 * @throws SQLException
	 */
	public List<Long> insertSelective(PriceFormBeanModel priceformbeanmodel, TGoods record,TGoodsExtendWithBLOBs goodse, GoodsFormBeanModel goodsf, UserinfoModel user) throws SQLException
	{
		String sku = System.currentTimeMillis() + "";
		String sizes = record.getSpec();
		List<Long> list = new ArrayList<Long>();
		if (StringUtils.hasText(sizes))
		{
			String[] sizez = sizes.split(",");
			for (int i = 0; i < sizez.length; i++)
			{
				if (StringUtils.hasText(sizez[i]) || i == 0)
				{
					//保存商品主表信息
					record.setId(null);
					record.setSkuId(sku);
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("len", 8);
					String returnSn = (String) opensqldao.selectForObjectByMap(paramMap, "t_goods.p_t_goods_goodscode");
					while (returnSn == null || "".equals(returnSn) || "-1".equals(returnSn))
					{
						returnSn = (String) opensqldao.selectForObjectByMap(paramMap, "t_goods.p_t_goods_goodscode");
					}
					record.setGoodscode(returnSn);
					record.setSpec(sizez[i]);
					if (StringUtils.hasText(goodsf.getGoodsgids()) && StringUtils.hasText(goodsf.getGoodsgidsm()))
					{
						record.setIsSuit(1);
					} else
					{
						record.setIsSuit(0);
					}
					record.setCreateDt(new Date());
					long gid = tgoodsdao.insertSelective(record);
					
					//商品价格、上下架状态、是否显示、置顶持久化数据
					BigDecimal pc_price = priceformbeanmodel.getPcPrice();
					BigDecimal wap_price = priceformbeanmodel.getWapPrice();
					BigDecimal app_price = priceformbeanmodel.getAppPrice();
					
					//PC平台
					if(pc_price != null)
					{
						TGoodsPrice pc_TGoodsPrice = new TGoodsPrice();
						pc_TGoodsPrice.setGoodsId(gid);
						pc_TGoodsPrice.setPlatformType("111yao");
						pc_TGoodsPrice.setPrice(pc_price);
						pc_TGoodsPrice.setPriceType("pc");
						pc_TGoodsPrice.setCreateDt(new Date());
						
						Integer pcStatus = priceformbeanmodel.getPcStatus();
						Integer isPcShow = priceformbeanmodel.getIsPcShow();
						Integer isPcTop = priceformbeanmodel.getIsPcTop();
						if(pcStatus != null)
						{
							pc_TGoodsPrice.setStatus(1);
						}
						else
						{
							pc_TGoodsPrice.setStatus(2);
						}
						if(isPcShow != null)
						{
							pc_TGoodsPrice.setIsShow(1);
						}
						else
						{
							pc_TGoodsPrice.setIsShow(0);
						}
						if(isPcTop != null)
						{
							pc_TGoodsPrice.setIsTop(1);
						}
						else
						{
							pc_TGoodsPrice.setIsTop(2);
						}
						
						String pcupdate = priceformbeanmodel.getPcUpdate();
						String pcdowndate = priceformbeanmodel.getPcDowndate();
						if(pcupdate != null)
						{
							pc_TGoodsPrice.setUpDt(DateUtil.strDateConvertToDate(pcupdate, "yyyy-MM-dd HH:mm:ss"));
						}
						if(pcdowndate != null)
						{
							pc_TGoodsPrice.setDownDt(DateUtil.strDateConvertToDate(pcdowndate, "yyyy-MM-dd HH:mm:ss"));
						}
						tgoodspricedao.insert(pc_TGoodsPrice);
					}
					//WAP平台
					if(wap_price != null)
					{
						TGoodsPrice wap_TGoodsPrice = new TGoodsPrice();
						wap_TGoodsPrice.setGoodsId(gid);
						wap_TGoodsPrice.setPlatformType("111yao");
						wap_TGoodsPrice.setPrice(wap_price);
						wap_TGoodsPrice.setPriceType("wap");
						wap_TGoodsPrice.setCreateDt(new Date());
						Integer wapStatus = priceformbeanmodel.getWapStatus();
						Integer isWapShow = priceformbeanmodel.getIsWapShow();
						Integer isWapTop = priceformbeanmodel.getIsWapTop();
						if(wapStatus != null)
						{
							wap_TGoodsPrice.setStatus(1);
						}
						else
						{
							wap_TGoodsPrice.setStatus(2);
						}
						if(isWapShow != null)
						{
							wap_TGoodsPrice.setIsShow(1);
						}
						else
						{
							wap_TGoodsPrice.setIsShow(0);
						}
						if(isWapTop != null)
						{
							wap_TGoodsPrice.setIsTop(1);
						}
						else
						{
							wap_TGoodsPrice.setIsTop(2);
						}
						
						String wapupdate = priceformbeanmodel.getWapUpdate();
						String wapdowndate = priceformbeanmodel.getWapDowndate();
						if(wapupdate != null)
						{
							wap_TGoodsPrice.setUpDt(DateUtil.strDateConvertToDate(wapupdate, "yyyy-MM-dd HH:mm:ss"));
						}
						if(wapdowndate != null)
						{
							wap_TGoodsPrice.setDownDt(DateUtil.strDateConvertToDate(wapdowndate, "yyyy-MM-dd HH:mm:ss"));
						}
						tgoodspricedao.insert(wap_TGoodsPrice);
					}
					//APP平台
					if(app_price != null)
					{
						TGoodsPrice app_TGoodsPrice = new TGoodsPrice();
						app_TGoodsPrice.setGoodsId(gid);
						app_TGoodsPrice.setPlatformType("111yao");
						app_TGoodsPrice.setPrice(app_price);
						app_TGoodsPrice.setPriceType("app");
						app_TGoodsPrice.setCreateDt(new Date());
						Integer appStatus = priceformbeanmodel.getAppStatus();
						Integer isAppShow = priceformbeanmodel.getIsAppShow();
						Integer isAppTop = priceformbeanmodel.getIsAppTop();
						if(appStatus != null)
						{
							app_TGoodsPrice.setStatus(1);
						}
						else
						{
							app_TGoodsPrice.setStatus(2);
						}
						if(isAppShow != null)
						{
							app_TGoodsPrice.setIsShow(1);
						}
						else
						{
							app_TGoodsPrice.setIsShow(0);
						}
						if(isAppTop != null)
						{
							app_TGoodsPrice.setIsTop(1);
						}
						else
						{
							app_TGoodsPrice.setIsTop(2);
						}
						String appupdate = priceformbeanmodel.getAppUpdate();
						String appdowndate = priceformbeanmodel.getAppDowndate();
						if(appupdate != null)
						{
							app_TGoodsPrice.setUpDt(DateUtil.strDateConvertToDate(appupdate, "yyyy-MM-dd HH:mm:ss"));
						}
						if(appdowndate != null)
						{
							app_TGoodsPrice.setDownDt(DateUtil.strDateConvertToDate(appdowndate, "yyyy-MM-dd HH:mm:ss"));
						}
						tgoodspricedao.insert(app_TGoodsPrice);
					}
					
					//持久化商品属性信息
					TGoodsProperty tp = new TGoodsProperty();
					tp.setGoodsid(gid);
					tp.setEvaluate(0);
					tp.setSales(0);
					tp.setAttention(0);
					tgoodspropertydao.insertSelective(tp);
					list.add(gid);
					String goodsDescribes = goodse.getGoodsDescribes();
					String instructions = goodse.getInstructions();
					if (StringUtils.hasText(goodsDescribes))
					{
						if(goodsDescribes.indexOf("img") > 0)
						{
							String imageDomain = InfoUtil.getInstance().getInfo("config", "image_domain");
							goodsDescribes = ImageSrcUtil.replaceAllImageSrcPros(goodsDescribes, imageDomain, 1);
						}
						goodse.setGoodsDescribe(goodsDescribes.getBytes());
					}
					if (StringUtils.hasText(instructions))
					{
						if(instructions.indexOf("img") > 0)
						{
							String imageDomain = InfoUtil.getInstance().getInfo("config", "image_domain");
							instructions = ImageSrcUtil.replaceAllImageSrcPros(instructions, imageDomain, 1);
						}
						goodse.setInstruction(instructions.getBytes());
					}
					goodse.setGoodsid(gid);
					tgoodsextenddao.insertSelective(goodse);
					if (StringUtils.hasText(goodsf.getGoodsimgid()))
					{
						addImgs(goodsf.getGoodsimgid(), gid, i);
					}
					if (StringUtils.hasText(goodsf.getGoodsgids()) && StringUtils.hasText(goodsf.getGoodsgidsm()))
					{
						addGroup(goodsf.getGoodsgids(), goodsf.getGoodsgidsm(),gid);
					}
					if (StringUtils.hasText(goodsf.getGoodsgids()))
					{
						addPremiums(goodsf.getGoodsgids(), gid);
					}
					if (StringUtils.hasText(goodsf.getCategoryId()))
					{
						TCategoryGoods category = new TCategoryGoods();
						category.setGoodsid(gid);
						category.setCategoryid(Long.parseLong(goodsf.getCategoryId()));
						category.setType(2);
						tcategorygoodsdao.insertSelective(category);
					}
					if (StringUtils.hasText(goodsf.getYiyaoguanId()))
					{
						String yyg = goodsf.getYiyaoguanId();
						String[] yygs = yyg.split(",");
						for (String yygid : yygs)
						{
							TCategoryGoods category = new TCategoryGoods();
							category.setGoodsid(gid);
							category.setCategoryid(Long.parseLong(yygid));
							category.setType(1);
							tcategorygoodsdao.insertSelective(category);
						}
					}
					List fList = tfreightdao.selectByExample(null);
					if (null != fList && fList.size() > 0)
					{
						TFreight freight = (TFreight) fList.get(0);
						TGoodFreight goodFreight = new TGoodFreight();
						goodFreight.setGoodId(gid);
						goodFreight.setFreightId(freight.getId());
						tgoodfreightdao.insertSelective(goodFreight);
					}
					TOssOperateLog logs=new TOssOperateLog();
					logs.setModuleType(1);
					logs.setOperationRemake("修改商品");
					logs.setRecordId(gid);
					logs.setOperationDt(new Date());
					logs.setCreateDt(new Date());
					logs.setOperationUsername(user.getUsername());
					tossoperatelogdao.insertSelective(logs);
				}
			}
		}
		return list;
	}
	
	/**
	 * 持久化商品价格
	 */
	public void saveGoodPrice(Long goodsId)
	{
		
	}

	private int ifNull(Integer obj, int ret)
	{
		if (null == obj)
		{
			return ret;
		} else
		{
			return obj;
		}
	}

	/**
	 * 添加赠品
	 */
	private void addPremiums(String goodspids, long goodid) throws SQLException
	{
		if (StringUtils.hasText(goodspids))
		{
			String[] pids = goodspids.split(",");
			for (int i = 0; i < pids.length; i++)
			{
				if (StringUtils.hasText(pids[i]))
				{
					String[] pdata = pids[i].split(":");
					TGoodsPremiums record = new TGoodsPremiums();
					record.setGoodsId(goodid);
					record.setPremiumsId(Long.parseLong(pdata[0]));
					record.setGoodsum(Integer.parseInt(pdata[1]));
					tgoodspremiumsdao.insertSelective(record);
				}
			}
		}
	}

	/**
	 * 添加图片
	 */
	private void addImgs(String goodsimgid, long goodid, int i)
			throws SQLException
	{
		if (StringUtils.hasText(goodsimgid))
		{
			String[] goodsimg = goodsimgid.split(",");
			if (0 == i)
			{
				for (int j = 0; j < goodsimg.length; j++)
				{
					if (StringUtils.hasText(goodsimg[j]))
					{
						TGoodsImages goodsi = new TGoodsImages();
						goodsi.setId(Long.parseLong(goodsimg[j].trim()));
						goodsi.setGoodsid(goodid);
						tgoodsimagesdao.updateByPrimaryKeySelective(goodsi);
					}
				}
			} else
			{
				for (int j = 0; j < goodsimg.length; j++)
				{
					if (StringUtils.hasText(goodsimg[j]))
					{
						TGoodsImages goodsi = tgoodsimagesdao
								.selectByPrimaryKey(Long.parseLong(goodsimg[j]
										.trim()));
						goodsi.setId(null);
						goodsi.setGoodsid(goodid);
						tgoodsimagesdao.insertSelective(goodsi);
					}
				}
			}
		}
	}

	/**
	 * 添加组合
	 */
	private void addGroup(String goodsgids, String goodsgidsm, long goodid)
			throws SQLException
	{
		if (StringUtils.hasText(goodsgids) && StringUtils.hasText(goodsgidsm))
		{
			String[] gids = goodsgids.split(",");
			for (int i = 0; i < gids.length; i++)
			{
				if (StringUtils.hasText(gids[i]))
				{
					String[] gdata = gids[i].split(":");
					TGroupGoods record = new TGroupGoods();
					record.setGroupId(goodid);
					record.setGoodsid(Long.parseLong(gdata[0]));
					record.setGoodsNum(Integer.parseInt(gdata[1]));
					if (gdata[0] == goodsgidsm || goodsgidsm.equals(gdata[0]))
					{
						record.setMainGoods(1);
					} else
					{
						record.setMainGoods(0);
					}
					tgroupgoodsdao.insertSelective(record);
				}
			}
		}
	}

	/**
	 * 查看商品详情
	 */
	public List<Map<String, Object>> selectByPrimaryKey2(Map<String, Object> mapv, String type, String isRX) throws SQLException
	{
		long starTime = System.currentTimeMillis();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		if (isRX.equals("1"))
		{
			listmap = (List<Map<String, Object>>) opensqldao.selectForListByMap(mapv, "t_goods.selectGoodsById2");
		} 
		else
		{
			listmap = (List<Map<String, Object>>) opensqldao.selectForListByMap(mapv, "t_goods.selectGoodsById");
		}
		for (Map<String, Object> map : listmap)
		{
			long id = (Long) map.get("id");
			byte[] instructions = (byte[]) map.get("instruction");
			if (null != instructions)
			{
				map.put("instructions", new String(instructions));
			}
			byte[] goodsDescribes = (byte[]) map.get("goodsDescribe");
			if (null != goodsDescribes)
			{
				map.put("goodsDescribes", new String(goodsDescribes));
			}
			TGoodsImagesExample examplei = new TGoodsImagesExample();
			examplei.createCriteria().andGoodsidEqualTo(id).andUserTypeEqualTo(1);
			examplei.setOrderByClause("sort desc");
			List<TGoodsImages> listi = tgoodsimagesdao.selectByExample(examplei);
			if (null != listi && 0 < listi.size())
			{
				map.put("defaultimg", listi.get(0).getArtworkUrl());
				if (type == "1" || "1".equals(type))
				{
					map.put("defaultimgs", listi.get(0).getImageUrl());
				}
			}
			Map<String, Object> valmap = new HashMap<String, Object>();
			valmap.put("id", id);
			List<GoodsGroupModel2> listg = opensqldao.selectForListByMap(valmap, "t_goods.selectGroupByGoodsId");
			List<Map<String, String>> listpc = getCategory(id);
			valmap.clear();
			for (GoodsGroupModel2 obj : listg)
			{
				valmap.put("id", obj.getId());
				List<GroupModel> listgg = opensqldao.selectForListByMap(valmap,"t_goods.selectGoodsGroupByGoodsId");
				obj.setList(listgg);
			}
			valmap.clear();
			valmap.put("skuid", map.get("skuid"));
			List<Map<Long, String>> lists = opensqldao.selectForListByMap(valmap, "t_goods.selectSpecByMap1");
			map.put("lists", lists);
			map.put("listi", listi);
			map.put("listg", listg);
			map.put("listpc", listpc);
			long endTime = System.currentTimeMillis();
			long Time = endTime - starTime;
			System.out.println(Time + "..................");
		}
		return listmap;
	}

	// 获取分类
	private List<Map<String, String>> getCategory(long id)
	{
		Map<String, Object> valmap = new HashMap<String, Object>();
		valmap.put("id", id);
		valmap.put("type", 2);
		Map<String, String> mappc = (Map<String, String>) opensqldao
				.selectForObjectByMap(valmap, "t_goods.selectCategoryByCId");
		List<Map<String, String>> listpc = new ArrayList<Map<String, String>>();
		if (null != mappc)
		{
			String[] ids = null;
			if (null != mappc.get("idall") && !"".equals(mappc.get("idall")))
			{
				ids = mappc.get("idall").split(",");
			}
			String level = mappc.get("clevel");
			if ("4" == level || "4".equals(level))
			{
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("name", mappc.get("name3"));
				map1.put("id", ids[2]);
				listpc.add(map1);
				Map<String, String> map2 = new HashMap<String, String>();
				map2.put("name", mappc.get("name2"));
				map2.put("id", ids[3]);
				listpc.add(map2);
				Map<String, String> map3 = new HashMap<String, String>();
				map3.put("name", mappc.get("name1"));
				map3.put("id", mappc.get("id"));
				listpc.add(map3);
			} else if ("3" == level || "3".equals(level))
			{
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("name", mappc.get("name2"));
				map1.put("id", ids[2]);
				listpc.add(map1);
				Map<String, String> map3 = new HashMap<String, String>();
				map3.put("name", mappc.get("name1"));
				map3.put("id", mappc.get("id"));
				listpc.add(map3);
			} else if ("2" == level || "2".equals(level))
			{
				Map<String, String> map3 = new HashMap<String, String>();
				map3.put("name", mappc.get("name1"));
				map3.put("id", mappc.get("id"));
				listpc.add(map3);
			}
		}
		return listpc;
	}

	@Override
	public Long insertSelectiveByGoods(TGoods tGoods) throws SQLException
	{
		Long goodId = tgoodsdao.insertSelective(tGoods);
		TGoodsExtendWithBLOBs tGoodsExtendWithBLOBs = new TGoodsExtendWithBLOBs();
		tGoodsExtendWithBLOBs.setGoodsid(goodId);
		tgoodsextenddao.insertSelective(tGoodsExtendWithBLOBs);
		return goodId;
	}
	@Override
	public int deleteGoodById(UserinfoModel user,long id) throws SQLException{
		TGoods tgoods = new TGoods();
		tgoods.setId(id);
		tgoods.setIsDelete(1);
		int flag = tgoodsdao.updateByPrimaryKeySelective(tgoods);
		TGoodsBrokerageExample gbe=new TGoodsBrokerageExample();
		gbe.createCriteria().andGoodsIdEqualTo(tgoods.getId());
		List<TGoodsBrokerage> gbList = tgoodsbrokeragedao.selectByExample(gbe);
		if(gbList!=null&&gbList.size()>0){
			TGoodsBrokerage goodsBrokerage = gbList.get(0);
			goodsBrokerage.setAuditStatus(0);
			goodsBrokerage.setAuditRemark("商品删除佣金失效");
			goodsBrokerage.setApplyUser(user.getUsername());
			tgoodsbrokeragedao.updateByPrimaryKeySelective(goodsBrokerage);
			TOssOperateLog logs=new TOssOperateLog();
			logs.setModuleType(2);
			logs.setOperationRemake("商品删除导致佣金失效");
			logs.setRecordId(goodsBrokerage.getGoodsId());
			logs.setOperationDt(new Date());
			logs.setCreateDt(new Date());
			logs.setOperationUsername(user.getUsername());
			tossoperatelogdao.insertSelective(logs);
		}
		//UserinfoModel user = (UserinfoModel) this.getSession().getAttribute(ConstantUtil.logincookiename);
		//记录操作日志
		TOssOperateLog tOssOperateLog = new TOssOperateLog();
		tOssOperateLog.setModuleType(1);
		tOssOperateLog.setRecordId(id);
		tOssOperateLog.setOperationRemake("删除商品操作,表:t_goods,id:"+id);
		tOssOperateLog.setOperationUsername(user.getUsername());
		Date operationDt = new Date();
		tOssOperateLog.setOperationDt(operationDt);
		tOssOperateLog.setCreateDt(operationDt);
		
		tossoperatelogdao.insertSelective(tOssOperateLog);
		
		return flag;
	}
	
	
	
}
