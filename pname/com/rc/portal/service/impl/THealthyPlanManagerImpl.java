package com.rc.portal.service.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.THealthyPlanClassificationDAO;
import com.rc.portal.dao.THealthyPlanCombinationDAO;
import com.rc.portal.dao.THealthyPlanDAO;
import com.rc.portal.dao.THealthyPlanGoodsDAO;
import com.rc.portal.dao.THealthyPlanRecommendDAO;
import com.rc.portal.dao.THealthyPlanSymptomDAO;
import com.rc.portal.service.THealthyPlanManager;
import com.rc.portal.vo.THealthyPlan;
import com.rc.portal.vo.THealthyPlanCombination;
import com.rc.portal.vo.THealthyPlanCombinationExample;
import com.rc.portal.vo.THealthyPlanExample;
import com.rc.portal.vo.THealthyPlanGoods;
import com.rc.portal.vo.THealthyPlanGoodsExample;
import com.rc.portal.vo.THealthyPlanRecommend;
import com.rc.portal.vo.THealthyPlanRecommendExample;
import com.rc.portal.vo.THealthyPlanSymptom;
import com.rc.portal.vo.THealthyPlanSymptomExample;
import com.rc.portal.webapp.model.HealthyPlanContentModel;
import com.rc.portal.webapp.model.HealthyPlanContentModel.Combination;
import com.rc.portal.webapp.model.HealthyPlanContentModel.Goods;
import com.rc.portal.webapp.model.HealthyPlanContentModel.Symptom;

public class THealthyPlanManagerImpl  implements THealthyPlanManager {
	private OpenSqlDAO opensqldao;
    private THealthyPlanDAO thealthyplandao;
        
    private THealthyPlanSymptomDAO thealthyplansymptomdao;
    
    private THealthyPlanGoodsDAO thealthyplangoodsdao;
    
    private THealthyPlanCombinationDAO thealthyplancombinationdao;
    
    private THealthyPlanRecommendDAO thealthyplanrecommenddao;
    
    private THealthyPlanClassificationDAO thealthyplanclassificationdao;
    
    
    public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}
	public THealthyPlanManagerImpl() {
        super();
    }
    public void  setThealthyplandao(THealthyPlanDAO thealthyplandao){
        this.thealthyplandao=thealthyplandao;
    }
    public THealthyPlanDAO getThealthyplandao(){
        return this.thealthyplandao;
    }
    public     int countByExample(THealthyPlanExample example) throws SQLException{
        return thealthyplandao. countByExample( example);
    }

    public     int deleteByExample(THealthyPlanExample example) throws SQLException{
        return thealthyplandao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return thealthyplandao. deleteByPrimaryKey( id);
    }

    public     Long insert(THealthyPlan record) throws SQLException{
        return thealthyplandao. insert( record);
    }

    public     Long insertSelective(THealthyPlan record) throws SQLException{
        return thealthyplandao. insertSelective( record);
    }

    public     List selectByExample(THealthyPlanExample example) throws SQLException{
        return thealthyplandao. selectByExample( example);
    }

    public     THealthyPlan selectByPrimaryKey(Long id) throws SQLException{
        return thealthyplandao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(THealthyPlan record, THealthyPlanExample example) throws SQLException{
        return thealthyplandao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(THealthyPlan record, THealthyPlanExample example) throws SQLException{
        return thealthyplandao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(THealthyPlan record) throws SQLException{
        return thealthyplandao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(THealthyPlan record) throws SQLException{
        return thealthyplandao. updateByPrimaryKey( record);
    }
    /**
     * 获取健康方案内容
     * @param id    方案id
     * @return
     */
    public List<HealthyPlanContentModel> getHealthyPlanContent(Map<String, Object> map){
    	List<HealthyPlanContentModel> objlist = new ArrayList<HealthyPlanContentModel>();
    	List<Map<String, Object>> listo = opensqldao.selectForListByMap(map, "t_healthy_plan.selectHealthyPlanByMap");
    	for (Map<String, Object> lo: listo) {
    		HealthyPlanContentModel obj = new HealthyPlanContentModel(); 
    		//主要相关信息
    		Long hid = (Long)lo.get("id");
			obj.setId(hid);
			if(lo.get("cname") != null)
			{
				obj.setCategoryName(lo.get("cname").toString());
			}
			else
			{
				obj.setCategoryName("");
			}
			if(lo.get("wx_share_url") != null)
			{
				obj.setShareImageUrl(lo.get("wx_share_url").toString());
			}
			else
			{
				obj.setShareImageUrl("");
			}
			if(lo.get("wx_share_content") != null)
			{
				obj.setShareWords(lo.get("wx_share_content").toString());
			}
			else
			{
				obj.setShareWords("");
			}
			obj.setGoodsCategoryId((Long)lo.get("goods_category_id"));
			obj.setStatus((Integer)lo.get("status"));
			obj.setName((String)lo.get("name"));
			obj.setExplain(null==lo.get("plan_explain")?"":(String)lo.get("plan_explain"));
			obj.setBannerImgUrl(null==lo.get("banner_img_url")?"":(String)lo.get("banner_img_url"));
			obj.setReminder(null==lo.get("reminder")?"":(String)lo.get("reminder"));
			//伴随症
			obj.setLisths(getHealthyPlanSymptom(obj));
			obj.setSsum(obj.getLisths()==null?0:obj.getLisths().size());
			//组合套装
			obj.setListhc(getHealthyPlanCombination(obj));
			obj.setCsum(obj.getListhc()==null?0:obj.getListhc().size());
			objlist.add(obj);
		}
    	System.out.println(listo.size());
    	return objlist;
    }
    //组合套装
    private List<Combination> getHealthyPlanCombination(HealthyPlanContentModel obj){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Combination> listhc = new ArrayList<Combination>();
    	map.clear();
		map.put("id", obj.getId());
		List<Map<String, Object>> listc = opensqldao.selectForListByMap(map, "t_healthy_plan.selectHealthyPlanCombinationByMap");
		Map<String, Object> mapv = new HashMap<String, Object>();
		for (Map<String, Object> loc : listc) {
			HealthyPlanContentModel.Combination hc = obj.new Combination();
			//id,package_name,comment_content 
			Long cid = (Long)loc.get("id");
			hc.setCid(cid);
			hc.setCname(null==loc.get("package_name")?"":(String)loc.get("package_name"));
			hc.setCommentContent(null==loc.get("comment_content")?"":(String)loc.get("comment_content"));
			hc.setRealName(null==loc.get("real_name")?"":(String)loc.get("real_name"));
			hc.setWorkYear(null==loc.get("work_year")?"":(String)loc.get("work_year"));
			hc.setHeadUrl(null==loc.get("head_url")?"http://img.zdfei.com/static/image/temp/20160127/73569e0fe07f5e60234179baddec94eb.jpg":(String)loc.get("head_url"));
			
			mapv.clear();
			mapv.put("id", cid);
			mapv.put("type", 2);//1 伴随证商品  2 套餐商品
			List<Map<String, Object>> listg = opensqldao.selectForListByMap(mapv, "t_healthy_plan.selectHealthyPlanGoodsByMap");
			List<Goods> lg = new ArrayList<Goods>();
			for (Map<String, Object> losg : listg) {
				HealthyPlanContentModel.Goods hg = obj.new Goods();
				//tg.id,tg.abbreviation_picture,tg.app_price,tg.wap_price,tg.price,tg.short_name,tg.main_title,thpg.weight
				hg.setGid((Long)losg.get("id"));
				hg.setgImgUrl(null==losg.get("abbreviation_picture")?"":(String)losg.get("abbreviation_picture"));
				hg.setAppPrice((BigDecimal)(losg.get("app_price")));
				hg.setWapPrice((BigDecimal)(losg.get("wap_price")));
				hg.setPrice((BigDecimal)(losg.get("price")));
				hg.setMainTitle(null==losg.get("main_title")?"":(String)losg.get("main_title"));
				hg.setGname(null==losg.get("goods_name")?"":(String)losg.get("goods_name"));
				hg.setSname(null==losg.get("short_name")?"":(String)losg.get("short_name"));
				lg.add(hg);
			}
			hc.setGsum(null==lg?0:lg.size());
			hc.setListc(lg);
			hc.setcPrice(new BigDecimal(0));
			hc.setCcPrice(new BigDecimal(0));
			hc.setSavePrice(new BigDecimal(0));
			listhc.add(hc);
		}
		return listhc;
    }
    //伴随症
    private List<Symptom> getHealthyPlanSymptom(HealthyPlanContentModel obj){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.clear();
		map.put("id", obj.getId());
		List<Map<String, Object>> lists = opensqldao.selectForListByMap(map, "t_healthy_plan.selectHealthyPlanSymptomByMap");
		List<Symptom> lisths = new ArrayList<Symptom>();
		Map<String, Object> mapv = new HashMap<String, Object>();
		for (Map<String, Object> los : lists) {
			HealthyPlanContentModel.Symptom hs = obj.new Symptom();
			//id,name,symptom_explain,banner_img_url,weight,type
			Long sid = (Long)los.get("id");
			hs.setSid(sid);
			hs.setSname((String)los.get("name"));
			hs.setSexplain(null==los.get("symptom_explain")?"":(String)los.get("symptom_explain"));
			hs.setSbannerImgUrl(null==los.get("banner_img_url")?"":(String)los.get("banner_img_url"));
			hs.setWeight((Integer)los.get("weight"));//权重  从大到小
			hs.setType((Integer)los.get("type"));//类型    0 辅药   1 主药
			
			mapv.clear();
			mapv.put("id", sid);
			mapv.put("type", 1);//1 伴随证商品  2 套餐商品
			List<Map<String, Object>> listg = opensqldao.selectForListByMap(mapv, "t_healthy_plan.selectHealthyPlanGoodsByMap");
			List<Goods> lg = new ArrayList<Goods>();
			for (Map<String, Object> losg : listg) {
				HealthyPlanContentModel.Goods hg = obj.new Goods();
				//tg.id,tg.abbreviation_picture,tg.app_price,tg.wap_price,tg.price,tg.short_name,tg.main_title,thpg.weight
				hg.setGid((Long)losg.get("id"));
				hg.setgImgUrl(null==losg.get("abbreviation_picture")?"":(String)losg.get("abbreviation_picture"));
				hg.setAppPrice((BigDecimal)(losg.get("app_price")));
				hg.setWapPrice((BigDecimal)(losg.get("wap_price")));
				hg.setPrice((BigDecimal)(losg.get("price")));
				hg.setMainTitle(null==losg.get("main_title")?"":(String)losg.get("main_title"));
				hg.setGname(null==losg.get("goods_name")?"":(String)losg.get("goods_name"));
				hg.setSname(null==losg.get("short_name")?"":(String)losg.get("short_name"));
				lg.add(hg);
			}
			hs.setGsum(null==lg?0:lg.size());
			hs.setLists(lg);
			lisths.add(hs);
		}
		HealthyPlanContentModel.Symptom hs = obj.new Symptom();
		for (int i = 0; i < lisths.size(); i++) {
			Symptom symptom = lisths.get(i);
			if(1==symptom.getType()){
				hs = symptom;
				lisths.remove(symptom);
			}
		}
		lisths.add(0, hs);
		return lisths;
    }
	public Map<String, Object> getHealthyPlaContentInfo(Map<String, Object> map) {
		Map<String, Object> listobj = new HashMap<String, Object>();
		List<Map<String, Object>> lists = opensqldao.selectForListByMap(map, "t_healthy_plan.selectHealthyPlanGoodsInfos");
		listobj.put("lists", lists);
		List<Map<String, Object>> listc = opensqldao.selectForListByMap(map, "t_healthy_plan.selectHealthyPlanGoodsInfoc");
		listobj.put("listc", listc);
		return listobj;
	}
	public Long saveTHealthyPlan(THealthyPlan plan, String[] goodsWeights,
			String[] goodsids) throws SQLException {
		long planid =0;
		if(plan.getId()==null){
			plan.setCreateDt(new Date());
			plan.setDeleteStatus(0);
			plan.setStatus(0);
			planid =this.thealthyplandao.insertSelective(plan);
			THealthyPlanSymptom planSymptom = new THealthyPlanSymptom();
			planSymptom.setType(1);//主药
			planSymptom.setCreateDt(new Date());
			planSymptom.setPlanId(planid);
			long symid =this.thealthyplansymptomdao.insertSelective(planSymptom);
			if(goodsids!=null&&goodsids.length>0){
				THealthyPlanGoods goods = null;
				for(int i=0;i<goodsids.length;i++){
					goods = new THealthyPlanGoods();
					goods.setCreateDt(new Date());
					goods.setGoodsId(Long.parseLong(goodsids[i].trim()));
					goods.setRelationId(symid);
					goods.setWeight(Integer.parseInt(goodsWeights[i].trim()));
					goods.setType(1);
					this.thealthyplangoodsdao.insertSelective(goods);
				}
			}
		}else{
			plan.setStatus(0);//设置为 未发布
			this.thealthyplandao.updateByPrimaryKeySelective(plan);
			THealthyPlanSymptomExample example = new THealthyPlanSymptomExample();
			example.createCriteria().andTypeEqualTo(1).andPlanIdEqualTo(plan.getId());
			List<THealthyPlanSymptom> symList =thealthyplansymptomdao.selectByExample(example);
			if(symList!=null&&symList.size()>0){
				THealthyPlanSymptom sym = symList.get(0);
				
				THealthyPlanGoodsExample goodsExample = new THealthyPlanGoodsExample();
				goodsExample.createCriteria().andRelationIdEqualTo(sym.getId()).andTypeEqualTo(1);
				List<THealthyPlanGoods> goodsList = thealthyplangoodsdao.selectByExample(goodsExample);
				for(THealthyPlanGoods goods:goodsList){
					this.thealthyplangoodsdao.deleteByPrimaryKey(goods.getId());
				}
				if(goodsids!=null&&goodsids.length>0){
					THealthyPlanGoods goods = null;
					for(int i=0;i<goodsids.length;i++){
						goods = new THealthyPlanGoods();
						goods.setCreateDt(new Date());
						goods.setGoodsId(Long.parseLong(goodsids[i].trim()));
						goods.setRelationId(sym.getId());
						goods.setWeight(Integer.parseInt(goodsWeights[i].trim()));
						goods.setType(1);
						this.thealthyplangoodsdao.insertSelective(goods);
					}
				}
			}
			planid = plan.getId();
		}
		return planid;
	}
	
	public Long saveTHealthyPlanSymptom(THealthyPlanSymptom symptom,
			String[] goodsWeights, String[] goodsids) throws SQLException {
		long symptomid =0;
		THealthyPlan plan = new THealthyPlan();
		plan.setId(symptom.getPlanId());
		plan.setStatus(0);
		this.thealthyplandao.updateByPrimaryKeySelective(plan);
		if(symptom.getId()==null){//保存
			symptom.setCreateDt(new Date());
			symptom.setType(0);//辅药
			symptomid =this.thealthyplansymptomdao.insertSelective(symptom);
			if(goodsids!=null&&goodsids.length>0){
				THealthyPlanGoods goods = null;
				for(int i=0;i<goodsids.length;i++){
					goods = new THealthyPlanGoods();
					goods.setCreateDt(new Date());
					goods.setGoodsId(Long.parseLong(goodsids[i].trim()));
					goods.setRelationId(symptomid);
					goods.setWeight(Integer.parseInt(goodsWeights[i].trim()));
					goods.setType(1);
					this.thealthyplangoodsdao.insertSelective(goods);
				}
			}
		}else{//修改
			thealthyplansymptomdao.updateByPrimaryKeySelective(symptom);
			THealthyPlanGoodsExample goodsExample = new THealthyPlanGoodsExample();
			goodsExample.createCriteria().andRelationIdEqualTo(symptom.getId()).andTypeEqualTo(1);
			List<THealthyPlanGoods> goodsList = thealthyplangoodsdao.selectByExample(goodsExample);
			for(THealthyPlanGoods goods:goodsList){
				this.thealthyplangoodsdao.deleteByPrimaryKey(goods.getId());
			}
			if(goodsids!=null&&goodsids.length>0){
				THealthyPlanGoods goods = null;
				for(int i=0;i<goodsids.length;i++){
					goods = new THealthyPlanGoods();
					goods.setCreateDt(new Date());
					goods.setGoodsId(Long.parseLong(goodsids[i].trim()));
					goods.setRelationId(symptom.getId());
					goods.setWeight(Integer.parseInt(goodsWeights[i].trim()));
					goods.setType(1);
					this.thealthyplangoodsdao.insertSelective(goods);
				}
			}
			symptomid =symptom.getId();
		}
		return symptomid;
	}

	public Long saveTHealthyPlanCombination(
			THealthyPlanCombination combination, String[] goodsWeights,
			String[] goodsids) throws SQLException {
		long combinationId =0;
		THealthyPlan plan = new THealthyPlan();
		plan.setId(combination.getPlanId());
		plan.setStatus(0);
		this.thealthyplandao.updateByPrimaryKeySelective(plan);
		if(combination.getId()==null){
			combination.setStatus(1);//显示
			combination.setDeleteStatus(0);//未删除
			combination.setCreateDt(new Date());
			combination.setCommentTime(new Date());
			combinationId =this.thealthyplancombinationdao.insertSelective(combination);
			if(goodsids!=null&&goodsids.length>0){
				THealthyPlanGoods goods = null;
				for(int i=0;i<goodsids.length;i++){
					goods = new THealthyPlanGoods();
					goods.setCreateDt(new Date());
					goods.setGoodsId(Long.parseLong(goodsids[i].trim()));
					goods.setRelationId(combinationId);
					goods.setWeight(Integer.parseInt(goodsWeights[i].trim()));
					goods.setType(2);
					this.thealthyplangoodsdao.insertSelective(goods);
				}
			}
		}else{
			combinationId = combination.getId();
			thealthyplancombinationdao.updateByPrimaryKeySelective(combination);
			
			THealthyPlanGoodsExample goodsExample = new THealthyPlanGoodsExample();
			goodsExample.createCriteria().andRelationIdEqualTo(combinationId).andTypeEqualTo(2);
			List<THealthyPlanGoods> goodsList = thealthyplangoodsdao.selectByExample(goodsExample);
			for(THealthyPlanGoods goods:goodsList){
				this.thealthyplangoodsdao.deleteByPrimaryKey(goods.getId());
			}
			if(goodsids!=null&&goodsids.length>0){
				THealthyPlanGoods goods = null;
				for(int i=0;i<goodsids.length;i++){
					goods = new THealthyPlanGoods();
					goods.setCreateDt(new Date());
					goods.setGoodsId(Long.parseLong(goodsids[i].trim()));
					goods.setRelationId(combinationId);
					goods.setWeight(Integer.parseInt(goodsWeights[i].trim()));
					goods.setType(2);
					this.thealthyplangoodsdao.insertSelective(goods);
				}
			}
		}
		return combinationId;
	}
	
	public void deletePlanSymptom(long symptomId) throws SQLException {
		THealthyPlanSymptom bean =this.thealthyplansymptomdao.selectByPrimaryKey(symptomId);
		THealthyPlan plan = new THealthyPlan();
		plan.setId(bean.getPlanId());
		plan.setStatus(0);
		this.thealthyplandao.updateByPrimaryKeySelective(plan);
		
		THealthyPlanGoodsExample goodsExample = new THealthyPlanGoodsExample();
		goodsExample.createCriteria().andRelationIdEqualTo(symptomId).andTypeEqualTo(1);
		List<THealthyPlanGoods> goodsList = thealthyplangoodsdao.selectByExample(goodsExample);
		for(THealthyPlanGoods goods:goodsList){
			this.thealthyplangoodsdao.deleteByPrimaryKey(goods.getId());
		}
		thealthyplansymptomdao.deleteByPrimaryKey(symptomId);
	}
	public void deletePlanCombination(long combinationId) throws SQLException {
		THealthyPlanCombination bean =thealthyplancombinationdao.selectByPrimaryKey(combinationId);
		THealthyPlan plan = new THealthyPlan();
		plan.setId(bean.getPlanId());
		plan.setStatus(0);
		this.thealthyplandao.updateByPrimaryKeySelective(plan);
		THealthyPlanCombination  beannew = new THealthyPlanCombination();
		beannew.setId(combinationId);
		beannew.setDeleteStatus(1);
		thealthyplancombinationdao.updateByPrimaryKeySelective(beannew);
	}
	
	public void deleteTHealthyPlan(long planId) throws Exception {
		THealthyPlan plan = new THealthyPlan();
		 plan.setId(planId);
		 plan.setDeleteStatus(1);
		 this.thealthyplandao.updateByPrimaryKeySelective(plan);
		 THealthyPlanRecommendExample example = new THealthyPlanRecommendExample();
		 example.createCriteria().andPlanIdEqualTo(planId);
		 List<THealthyPlanRecommend> list =thealthyplanrecommenddao.selectByExample(example);
		 if(list!=null){
			 for(THealthyPlanRecommend planRecommend:list){
				 thealthyplanrecommenddao.deleteByPrimaryKey(planRecommend.getId());
			 }
		 }
	}
	/**
	 * 删除健康方案分类
	 */
	public void deleteTHealthyPlanClassification(long classId) throws Exception {
		THealthyPlanExample example = new THealthyPlanExample();
		example.createCriteria().andClassificationIdEqualTo(classId).andDeleteStatusEqualTo(1);
		List<THealthyPlan> planlist =this.thealthyplandao.selectByExample(example);
		if(planlist!=null&&planlist.size()>0){
			THealthyPlanSymptomExample  symptomExample = null;//伴随正
			THealthyPlanCombinationExample combinationExample = null; //套餐
			THealthyPlanGoodsExample   planGoodsExample= null;//关联商品
			for(THealthyPlan plan:planlist){
				symptomExample = new THealthyPlanSymptomExample();
				symptomExample.createCriteria().andPlanIdEqualTo(plan.getId());//伴随正
				List<THealthyPlanSymptom> symptomList = this.thealthyplansymptomdao.selectByExample(symptomExample);
				if(symptomList!=null&&symptomList.size()>0){
					for(THealthyPlanSymptom symptom :symptomList){
						planGoodsExample =new THealthyPlanGoodsExample();
						planGoodsExample.createCriteria().andRelationIdEqualTo(symptom.getId()).andTypeEqualTo(1);//伴随正
						List<THealthyPlanGoods> planGoodslist =this.thealthyplangoodsdao.selectByExample(planGoodsExample);
						if(planGoodslist!=null&&planGoodslist.size()>0){
							for(THealthyPlanGoods planGoods:planGoodslist){
								this.thealthyplangoodsdao.deleteByPrimaryKey(planGoods.getId());
							}
						}
						this.thealthyplansymptomdao.deleteByPrimaryKey(symptom.getId());
					}
				}
				combinationExample =new THealthyPlanCombinationExample();//套餐
				combinationExample.createCriteria().andPlanIdEqualTo(plan.getId());
				List<THealthyPlanCombination> combinationList =this.thealthyplancombinationdao.selectByExample(combinationExample);
				if(combinationList!=null&&combinationList.size()>0){
					for(THealthyPlanCombination combination :combinationList){
						planGoodsExample =new THealthyPlanGoodsExample();
						planGoodsExample.createCriteria().andRelationIdEqualTo(combination.getId()).andTypeEqualTo(2);//套餐商品
						List<THealthyPlanGoods> planGoodslist =this.thealthyplangoodsdao.selectByExample(planGoodsExample);
						if(planGoodslist!=null&&planGoodslist.size()>0){
							for(THealthyPlanGoods planGoods:planGoodslist){
								this.thealthyplangoodsdao.deleteByPrimaryKey(planGoods.getId());
							}
						}
						
						this.thealthyplancombinationdao.deleteByPrimaryKey(combination.getId());
					}
				}
				this.thealthyplandao.deleteByPrimaryKey(plan.getId());
			}
		}
		this.thealthyplanclassificationdao.deleteByPrimaryKey(classId);
	}
	
	
	public THealthyPlanSymptomDAO getThealthyplansymptomdao() {
		return thealthyplansymptomdao;
	}
	public void setThealthyplansymptomdao(
			THealthyPlanSymptomDAO thealthyplansymptomdao) {
		this.thealthyplansymptomdao = thealthyplansymptomdao;
	}
	public THealthyPlanGoodsDAO getThealthyplangoodsdao() {
		return thealthyplangoodsdao;
	}
	public void setThealthyplangoodsdao(THealthyPlanGoodsDAO thealthyplangoodsdao) {
		this.thealthyplangoodsdao = thealthyplangoodsdao;
	}
	public THealthyPlanCombinationDAO getThealthyplancombinationdao() {
		return thealthyplancombinationdao;
	}
	public void setThealthyplancombinationdao(
			THealthyPlanCombinationDAO thealthyplancombinationdao) {
		this.thealthyplancombinationdao = thealthyplancombinationdao;
	}
	public THealthyPlanRecommendDAO getThealthyplanrecommenddao() {
		return thealthyplanrecommenddao;
	}
	public void setThealthyplanrecommenddao(
			THealthyPlanRecommendDAO thealthyplanrecommenddao) {
		this.thealthyplanrecommenddao = thealthyplanrecommenddao;
	}
	public THealthyPlanClassificationDAO getThealthyplanclassificationdao() {
		return thealthyplanclassificationdao;
	}
	public void setThealthyplanclassificationdao(
			THealthyPlanClassificationDAO thealthyplanclassificationdao) {
		this.thealthyplanclassificationdao = thealthyplanclassificationdao;
	}
	
	
	
	
	
   
}
