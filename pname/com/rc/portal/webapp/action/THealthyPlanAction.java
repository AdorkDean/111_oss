package com.rc.portal.webapp.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TGoodsPriceManager;
import com.rc.portal.service.THealthyPlanClassificationManager;
import com.rc.portal.service.THealthyPlanCombinationManager;
import com.rc.portal.service.THealthyPlanGoodsManager;
import com.rc.portal.service.THealthyPlanManager;
import com.rc.portal.service.THealthyPlanRecommendManager;
import com.rc.portal.service.THealthyPlanSymptomManager;
import com.rc.portal.service.TPhysicianManager;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExample;
import com.rc.portal.vo.TGoodsPrice;
import com.rc.portal.vo.TGoodsPriceExample;
import com.rc.portal.vo.THealthyPlan;
import com.rc.portal.vo.THealthyPlanClassification;
import com.rc.portal.vo.THealthyPlanClassificationExample;
import com.rc.portal.vo.THealthyPlanCombination;
import com.rc.portal.vo.THealthyPlanRecommend;
import com.rc.portal.vo.THealthyPlanRecommendExample;
import com.rc.portal.vo.THealthyPlanSymptom;
import com.rc.portal.vo.THealthyPlanSymptomExample;
import com.rc.portal.vo.TPhysicianExample;
import com.rc.portal.webapp.util.PageResult;
/**
 * 健康方案
 * @author user3
 *
 */
public class THealthyPlanAction extends BaseAction{
	private static final long serialVersionUID = -9177538258303111573L;

   private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	private Condition model = new Condition();
	
	private OpenSqlManage opensqlmanage;
	
	private THealthyPlanClassificationManager thealthyplanclassificationmanager;
	
	private THealthyPlanManager  thealthyplanmanager;
	
	private List<THealthyPlanClassification> classificationList;
	
	private  THealthyPlan thealthyplan;
	
	private  THealthyPlanSymptom  thealthyplansymptom;
	
	private  THealthyPlanCombination thealthyplancombination;
	
    private  THealthyPlanSymptomManager  thealthyplansymptommanager;
	
	private  THealthyPlanCombinationManager thealthyplancombinationmanager;
	
	private THealthyPlanGoodsManager thealthyplangoodsmanager;
	
	private THealthyPlanRecommendManager thealthyplanrecommendmanager;
	
	
	private TCategoryManager tcategorymanager;
	
	private TGoodsManager tgoodsmanager;
	private String[] uploadifyFileName;
	private File[] uploadify; // 上传的文件
	
	private String[] goodsWeights;
	private String[] goodsids;
	
	private String wxfileFileName;
	private File wxfile; // 上传的文件
	
	
	private THealthyPlanClassification thealthyplanclassification;
	
	private TPhysicianManager  tphysicianmanager;
	
	private TGoodsPriceManager tgoodspricemanager;
	
	/**
	 * 推荐分类健康方案列表
	 * @return
	 * @throws SQLException 
	 */
	public String healthyPlanRecommendList() throws SQLException{
//	    Map<String,Object> paramMap = new HashMap<String,Object>();
//		if(model.getClassificationId()!=null){
//			paramMap.put("classificationId", model.getClassificationId());
//		}
//		if(StringUtils.hasText(model.getGoodsName())){
//			paramMap.put("planName", model.getGoodsName().trim());
//		}
//		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "t_healthy_plan.selectHealthyPlanRecommendCountByMap", "t_healthy_plan.selectHealthyPlanRecommendListByMap", rs.getP_curPage(), rs.getP_pageSize());
		THealthyPlanClassificationExample example = new THealthyPlanClassificationExample();
		example.createCriteria().andTypeEqualTo(2);
		classificationList = thealthyplanclassificationmanager.selectByExample(example);
		thealthyplanclassification = new THealthyPlanClassification();
		if(classificationList!=null&&classificationList.size()>0){
			classificationList.get(0).getId();
			thealthyplanclassification = this.thealthyplanclassificationmanager.selectByPrimaryKey(classificationList.get(0).getId());
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("classificationId", classificationList.get(0).getId());
			List list = this.opensqlmanage.selectForListByMap(paramMap, "t_healthy_plan.selectHealthyPlanRecommendListByclassificationId");
			this.getRequest().setAttribute("list", list);
			
		}
		return "healthy_plan_cecommend_add";
//		return "healthy_plan_cecommend_list";
	}
	
	
	/**
	 * 跳转到推荐分类方案完善页面
	 * @return
	 * @throws SQLException
	 */
	public String healthyPlanRecommendAdd() throws SQLException{
		String classId = this.getRequest().getParameter("classId");
		thealthyplanclassification = new THealthyPlanClassification();
		if(StringUtils.hasText(classId)){
			thealthyplanclassification = this.thealthyplanclassificationmanager.selectByPrimaryKey(Long.valueOf(classId.trim()));
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("classificationId", Long.valueOf(classId.trim()));
			List list = this.opensqlmanage.selectForListByMap(paramMap, "t_healthy_plan.selectHealthyPlanRecommendListByclassificationId");
			this.getRequest().setAttribute("list", list);
		}
		return "healthy_plan_cecommend_add";
	}
	
	/**
	 * 增加单个或是多个 方案
	 * @throws SQLException
	 */
	public void ajaxSaveRecommend() throws SQLException{
		String classId = this.getRequest().getParameter("classId");
		String planId = this.getRequest().getParameter("planId");
		String flag ="0";
		if(StringUtils.hasText(classId)&&StringUtils.hasText(planId)){
			String[] planIds = planId.split(",");
			THealthyPlanRecommendExample example = null;
			for(int i=0;i<planIds.length;i++){
				 example = new THealthyPlanRecommendExample();
				example.createCriteria().andClassificationIdEqualTo(Long.valueOf(classId.trim())).andPlanIdEqualTo(Long.valueOf(planIds[i].trim()));
				List list =this.thealthyplanrecommendmanager.selectByExample(example);
				if(list==null||(list!=null&&list.size()==0)){
					THealthyPlanRecommend bean = new THealthyPlanRecommend();
					bean.setClassificationId(Long.valueOf(classId.trim()));
					bean.setPlanId(Long.valueOf(planIds[i].trim()));
					bean.setStatus(1);
					bean.setWeight(0);
					this.thealthyplanrecommendmanager.insertSelective(bean);
				}
			}
			flag="1";
		}
		writeObjectToResponse(flag, ContentType.application_json);
	}
	
	/**
	 * 删除单个 推荐方案
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void ajaxDeleteRecommend() throws NumberFormatException, SQLException {
		String recommendId = this.getRequest().getParameter("recommendId");
		String flag ="0";
		if(StringUtils.hasLength(recommendId)){
			 this.thealthyplanrecommendmanager.deleteByPrimaryKey(Long.valueOf(recommendId.trim()));
			 flag="1";
		}
		writeObjectToResponse(flag, ContentType.application_json);
	}
	
	/**
	 * ajax 查询推荐方案
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void ajaxRecommendList() throws NumberFormatException, SQLException{
		String classId = this.getRequest().getParameter("classId");
		String flag ="0";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(StringUtils.hasText(classId)){
			thealthyplanclassification = this.thealthyplanclassificationmanager.selectByPrimaryKey(Long.valueOf(classId.trim()));
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("classificationId", Long.valueOf(classId.trim()));
			List list = this.opensqlmanage.selectForListByMap(paramMap, "t_healthy_plan.selectHealthyPlanRecommendListByclassificationId");
			flag="1";
			resultMap.put("list", list);
		}
		resultMap.put("flag", flag);
		writeObjectToResponse(resultMap, ContentType.application_json);
	}
	/**
	 * 保存
	 * @return
	 * @throws SQLException 
	 */
	public String healthyPlanRecommendSave() throws SQLException {
		if(goodsids!=null&&goodsids.length>0){
			THealthyPlanRecommend bean = null;
			for(int i=0;i<goodsids.length;i++){
				bean = new THealthyPlanRecommend();
				bean.setId(Long.valueOf(goodsids[i].trim()));
				bean.setWeight(Integer.valueOf(goodsWeights[i].trim()));
				thealthyplanrecommendmanager.updateByPrimaryKeySelective(bean);
			}
		}
		this.getRequest().setAttribute("status", 1);
		return "returnMsg";
	}
	
	/**
	 * 健康方案列表
	 * @return
	 * @throws SQLException 
	 */
	public String healthyPlanListOpen() throws SQLException{
	    Map<String,Object> paramMap = new HashMap<String,Object>();
		if(model.getClassificationId()!=null){
			paramMap.put("classificationId", model.getClassificationId());
		}
		if(StringUtils.hasText(model.getPlanName())){
			paramMap.put("planName", model.getPlanName().trim());
		}
		if(model.getPlanStatus()!=null){
			paramMap.put("planStatus", model.getPlanStatus());
		}
		if(StringUtils.hasText(model.getGoodsName())){
			paramMap.put("goodsName", model.getGoodsName().trim());
		}
		if(StringUtils.hasText(model.getGoodsno())){
			paramMap.put("goodsno", model.getGoodsno().trim());
		}
		paramMap.put("status", 1);
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "t_healthy_plan.selectHealthyPlanCountByMap", "t_healthy_plan.selectHealthyPlanListByMap", rs.getP_curPage(), rs.getP_pageSize());
		THealthyPlanClassificationExample example = new THealthyPlanClassificationExample();
		example.createCriteria().andTypeEqualTo(1);
		classificationList = thealthyplanclassificationmanager.selectByExample(example);
		return "healthy_plan_list_open";
	}
	
	
	/**
	 * 健康方案列表
	 * @return
	 * @throws SQLException 
	 */
	public String healthyPlanList() throws SQLException{
	    Map<String,Object> paramMap = new HashMap<String,Object>();
		if(model.getClassificationId()!=null){
			paramMap.put("classificationId", model.getClassificationId());
		}
		if(StringUtils.hasText(model.getPlanName())){
			paramMap.put("planName", model.getPlanName().trim());
		}
		if(model.getPlanStatus()!=null){
			paramMap.put("planStatus", model.getPlanStatus());
		}
		if(StringUtils.hasText(model.getGoodsName())){
			paramMap.put("goodsName", model.getGoodsName().trim());
		}
		if(StringUtils.hasText(model.getGoodsno())){
			paramMap.put("goodsno", model.getGoodsno().trim());
		}
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "t_healthy_plan.selectHealthyPlanCountByMap", "t_healthy_plan.selectHealthyPlanListByMap", rs.getP_curPage(), rs.getP_pageSize());
		THealthyPlanClassificationExample example = new THealthyPlanClassificationExample();
		example.createCriteria().andTypeEqualTo(1);
		classificationList = thealthyplanclassificationmanager.selectByExample(example);
		return "healthy_plan_list";
	}
	
	/**
	 * 跳转到方案添加页面
	 * @return
	 * @throws SQLException 
	 */
	public String healthyPlanAdd() throws SQLException {
		THealthyPlanClassificationExample example = new THealthyPlanClassificationExample();
		example.createCriteria().andTypeEqualTo(1);
		classificationList = thealthyplanclassificationmanager.selectByExample(example);
		thealthyplan = new THealthyPlan();
		String planId = this.getRequest().getParameter("planId");
		if(StringUtils.hasText(planId)){
			thealthyplan = this.thealthyplanmanager.selectByPrimaryKey(Long.valueOf(planId.trim()));
			
			THealthyPlanSymptomExample symptomexample = new THealthyPlanSymptomExample();
			symptomexample.createCriteria().andTypeEqualTo(1).andPlanIdEqualTo(thealthyplan.getId());
			List<THealthyPlanSymptom> symList =thealthyplansymptommanager.selectByExample(symptomexample);
			if(symList!=null&&symList.size()>0){
				THealthyPlanSymptom sym = symList.get(0);
				Map<String,Object> paramMap= new HashMap<String,Object>();
				paramMap.put("relationId", sym.getId());
				paramMap.put("type", 1);
			    List<Map> list = this.opensqlmanage.selectForListByMap(paramMap, "t_healthy_plan_goods.selectPlanGoodsListByMap");
			    TGoodsPriceExample priceExample = null;
			    for(Map plangoodsmap:list){
			    	 priceExample = new TGoodsPriceExample();
					priceExample.createCriteria().andPlatformTypeEqualTo("111yao").andGoodsIdEqualTo((Long)plangoodsmap.get("id"));
					List<TGoodsPrice> goodsPriceList =tgoodspricemanager.selectByExample(priceExample);
					if(goodsPriceList!=null&&goodsPriceList.size()>0){
						for(TGoodsPrice goodsprice:goodsPriceList){
							if("pc".equals(goodsprice.getPriceType())){
								plangoodsmap.put("pc_price", goodsprice.getPrice());
							}else if("wap".equals(goodsprice.getPriceType())){
								plangoodsmap.put("wap_price", goodsprice.getPrice());
							}else if("app".equals(goodsprice.getPriceType())){
								plangoodsmap.put("app_price", goodsprice.getPrice());
							}
						}
					}
			    }
			    this.getRequest().setAttribute("list", list);
			}
			if(thealthyplan.getGoodsCategoryId()!=null){
				TCategory cobj = tcategorymanager.selectByPrimaryKey(thealthyplan.getGoodsCategoryId());
	    		String apis = cobj.getAllParentId();
	    		String apiv = apis.substring(1);
	    		if(StringUtils.hasText(apiv)){
	    			String[] apisv = apiv.split(",");
	    			if(null!=apisv&&0<apisv.length){
	    				for (int i = 0; i < apisv.length; i++) {
							if(3==apisv.length){
								if(0==i){
									this.getRequest().setAttribute("fl1", getfl(i, Long.parseLong(apisv[1]),0l,2));
								}else if (1==i) {
									this.getRequest().setAttribute("fl2",getfl(i, Long.parseLong(apisv[2]),Long.parseLong(apisv[1]),2));
								}else if (2==i) {
									this.getRequest().setAttribute("fl3",getfl(i, thealthyplan.getGoodsCategoryId(),Long.parseLong(apisv[2]),2));
								}
							}else if (2==apisv.length) {
								if(0==i){
									this.getRequest().setAttribute("fl1", getfl(i, Long.parseLong(apisv[1]),0l,2));
								}else if (1==i) {
									this.getRequest().setAttribute("fl2",getfl(i, thealthyplan.getGoodsCategoryId(),Long.parseLong(apisv[1]),2));
								}
							}else if (1==apisv.length) {
								this.getRequest().setAttribute("fl1", getfl(i, thealthyplan.getGoodsCategoryId(),Long.parseLong(apisv[0]),2));
							}
						}
	    			}
	    		}
			}else{
				this.getRequest().setAttribute("fl1", getOption("goods_category_id",2));
			}
		}else{
			this.getRequest().setAttribute("fl1", getOption("goods_category_id",2));
		}
		return "healthy_plan_add";
	}
		public String getfl(int i,long cid,long sid,int type){
			String sqlmap = "";
			if(type==1){
	//			sqlmap="goods_yiyaoguan_id";
			}else if (type==2) {
				sqlmap="goods_category_id";
			}
			String fl="";
			String flid = "";
			Map<String, Object> map = new HashMap<String, Object>();
			if(i==0){
				map.put("syskey", sqlmap);
				Object obj1 = opensqlmanage.selectForObjectByMap(map, "t_sys_parameter.query_sysc");
				if(null!=obj1){
					Map<String, String> objmap = (Map<String, String>) obj1;
					flid = objmap.get("sys_value");
				}
				map.clear();
				map.put("clevel", 2);
				map.put("pid", Long.parseLong(flid));
			}else {
				map.put("pid", sid);
			}
			List<Map<String,Object>> flmap = opensqlmanage.selectForListByMap(map, "t_category.selectCategoryByMap");
			for (Map<String, Object> obj2 : flmap) {
				long id1 = (Long) obj2.get("id");
				String name = (String) obj2.get("category_name");
				if(cid==id1){
					fl=fl+"<option value='"+id1+"' selected='selected'>"+name+"</option>";
				}else {
					fl=fl+"<option value='"+id1+"'>"+name+"</option>";
				}
			}
			return fl;
		}
	
	private String getOption(String sqlmap,int level){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("syskey", sqlmap);
		Object obj1 = opensqlmanage.selectForObjectByMap(map, "t_sys_parameter.query_sysc");
		String flid = null;
		if(null!=obj1){
			Map<String, String> objmap = (Map<String, String>) obj1;
			flid = objmap.get("sys_value");
		}
		map.clear();
		String fl="";
		map.put("clevel", level);
		map.put("pid", Long.parseLong(flid));
		List<Map<String,Object>> flmap = opensqlmanage.selectForListByMap(map, "t_category.selectCategoryByMap");
		for (Map<String, Object> obj : flmap) {
			long id = (Long) obj.get("id");
			String name = (String) obj.get("category_name");
			fl=fl+"<option value='"+id+"'>"+name+"</option>";
		}
		return fl;
	}
	
	public String getDiskName(){
		String diskname="";
		DateFormat df = new SimpleDateFormat("yyyyMM");
		diskname =df.format(new Date());
		return diskname;
	}
	/**
	 * 保存 健康方案信息
	 * @return
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public String healthyPlanSave() throws IOException, SQLException{
		String fileflag = this.getRequest().getParameter("fileflag");// 1 缩略图  2 Banner图
		Map<String,String> map = new HashMap<String, String>();
		map.put("syskey", "healthyplan_abbreviationpicture_path");
		Object obj = opensqlmanage.selectForObjectByMap(map, "t_sys_parameter.query_sysc");
		String diskPath = "";
		if(null!=obj){
			Map<String, String> objmap = (Map<String, String>) obj;
			diskPath = objmap.get("sys_value");
		}
		String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
		String fullPath=diskPath+"/"+getDiskName()+"/";
		if (uploadify != null && uploadify.length > 0) {
			File createFile = new File(basePath+fullPath);
			if(! createFile.exists()) {  
				createFile.mkdirs();
			}  
            for (int i = 0; i < uploadify.length; i++) {
				String fullName=fullPath+DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()+Math.random()))+"."+uploadifyFileName[i].split("\\.")[1];
				File newFile = new File(basePath+fullName);
				FileUtils.copyFile(uploadify[i], newFile);
				if(uploadify.length>1){
					if(i==0){
						thealthyplan.setPlanImgUrl(fullName);
					}else{
						thealthyplan.setBannerImgUrl(fullName);
					}
				}else{
					if(fileflag.equals("1")){
						thealthyplan.setPlanImgUrl(fullName);
					}else if(fileflag.equals("2")){
						thealthyplan.setBannerImgUrl(fullName);
					}
				}
				
            }
        }
		
		if(wxfile!=null){
			File createFile = new File(basePath+fullPath);
			if(! createFile.exists()) {  
				createFile.mkdirs();
			}  
				String fullName=fullPath+DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()+Math.random()))+"."+wxfileFileName.split("\\.")[1];
				File newFile = new File(basePath+fullName);
				FileUtils.copyFile(wxfile, newFile);
				thealthyplan.setWxShareUrl(fullName);
		}
		
		

		long planid =this.thealthyplanmanager.saveTHealthyPlan(thealthyplan, goodsWeights, goodsids);
		this.getRequest().setAttribute("status", planid);
		return "returnMsg";
	}
	/**
	 * 根据海典编号查询商品
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public void getGoodsByGoodsno() throws SQLException {
		String goodsno = this.getRequest().getParameter("goodsno");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String flag ="0";
		if(StringUtils.hasText(goodsno)){
			TGoodsExample example = new TGoodsExample();
			example.createCriteria().andGoodsnoEqualTo(goodsno);
			List<TGoods> goodsList =this.tgoodsmanager.selectByExample(example);
			if(goodsList!=null&&goodsList.size()>0){
				TGoods tg = goodsList.get(0);
				int type = tg.getType();
				if(type == 2 || type == 3){
					flag = "2";
				}
				if(type == 1 || type == 4){
					flag="1";
					resultMap.put("goods", tg);
					TGoodsPriceExample priceExample = new TGoodsPriceExample();
					priceExample.createCriteria().andPlatformTypeEqualTo("111yao").andGoodsIdEqualTo(tg.getId());
					List<TGoodsPrice> goodsPriceList =tgoodspricemanager.selectByExample(priceExample);
					if(goodsPriceList!=null&&goodsPriceList.size()>0){
						for(TGoodsPrice goodsprice:goodsPriceList){
							if("pc".equals(goodsprice.getPriceType())){
								resultMap.put("pcPrice", goodsprice.getPrice());
							}else if("wap".equals(goodsprice.getPriceType())){
								resultMap.put("wapPrice", goodsprice.getPrice());
							}else if("app".equals(goodsprice.getPriceType())){
								resultMap.put("appPrice", goodsprice.getPrice());
							}
						}
					}
					if(resultMap.get("pcPrice")==null){
						resultMap.put("pcPrice", "0");
					}
					if(resultMap.get("pcPrice")==null){
						resultMap.put("wapPrice", "0");
					}
					if(resultMap.get("pcPrice")==null){
						resultMap.put("appPrice", "0");
					}
				}
			}
		}
		resultMap.put("flag", flag);
		writeObjectToResponse(resultMap, ContentType.application_json);
	}
	
	
	/**
	 * ajax 获取健康方案套餐
	 */
	public  void ajaxPlanCombination(){
		String planId = this.getRequest().getParameter("planId");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String flag ="0";
		if(StringUtils.hasText(planId)){
			Map<String,Object> paramMap= new HashMap<String,Object>();
			paramMap.put("planId", Long.valueOf(planId.trim()));
			paramMap.put("deleteStatus", 0);
		    List list = this.opensqlmanage.selectForListByMap(paramMap, "t_healthy_plan_combination.selectHealthyCombinationListByMap");
		    if(list!=null&&list.size()>0){
		    	flag ="1";
		    	resultMap.put("list", list);
		    }
		}
		resultMap.put("flag", flag);
	    writeObjectToResponse(resultMap, ContentType.application_json);
	}
	
	/**
	 * ajax获取伴随症
	 */
	public void ajaxPlanSymptom(){
		String planId = this.getRequest().getParameter("planId");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String flag ="0";
		if(StringUtils.hasText(planId)){
			Map<String,Object> paramMap= new HashMap<String,Object>();
			paramMap.put("planId", Long.valueOf(planId.trim()));
		    List list = this.opensqlmanage.selectForListByMap(paramMap, "t_healthy_plan_symptom.selectHealthySymptomListByMap");
		    if(list!=null&&list.size()>0){
		    	flag ="1";
		    	resultMap.put("list", list);
		    }
		}
		resultMap.put("flag", flag);
	    writeObjectToResponse(resultMap, ContentType.application_json);
	}
	
	/**
	 * 删除 伴随症状
	 * @return
	 */
	public void ajaxDeletePlanSymptom(){
		String symptomId = this.getRequest().getParameter("symptomId");
		String flag ="0";
		if(StringUtils.hasText(symptomId)){
			try {
				this.thealthyplanmanager.deletePlanSymptom(Long.valueOf(symptomId.trim()));
				flag ="1";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		writeObjectToResponse(flag, ContentType.application_json);
	}
	
	/**
	 * 删除 套餐
	 * @return
	 */
	public void ajaxDeletePlanCombination(){
		String combinationId = this.getRequest().getParameter("combinationId");
		String flag ="0";
		if(StringUtils.hasText(combinationId)){
			try {
				this.thealthyplanmanager.deletePlanCombination(Long.valueOf(combinationId.trim()));
				flag ="1";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		writeObjectToResponse(flag, ContentType.application_json);
	}
	
	/**
	 * 跳转到 伴随症状添加页面
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String planSymptomAdd() throws NumberFormatException, SQLException {
		String symptomId = this.getRequest().getParameter("symptomId");
		String planId = this.getRequest().getParameter("planId");
		thealthyplansymptom = new THealthyPlanSymptom();
		if(StringUtils.hasText(planId)){
			thealthyplansymptom.setPlanId(Long.valueOf(planId.trim()));	
		}
		if(StringUtils.hasText(symptomId)){
			thealthyplansymptom = this.thealthyplansymptommanager.selectByPrimaryKey(Long.valueOf(symptomId.trim()));
			Map<String,Object> paramMap= new HashMap<String,Object>();
			paramMap.put("relationId", thealthyplansymptom.getId());
			paramMap.put("type", 1);
		    List<Map> list = this.opensqlmanage.selectForListByMap(paramMap, "t_healthy_plan_goods.selectPlanGoodsListByMap");
		    TGoodsPriceExample priceExample = null;
		    for(Map plangoodsmap:list){
		    	 priceExample = new TGoodsPriceExample();
				priceExample.createCriteria().andPlatformTypeEqualTo("111yao").andGoodsIdEqualTo((Long)plangoodsmap.get("id"));
				List<TGoodsPrice> goodsPriceList =tgoodspricemanager.selectByExample(priceExample);
				if(goodsPriceList!=null&&goodsPriceList.size()>0){
					for(TGoodsPrice goodsprice:goodsPriceList){
						if("pc".equals(goodsprice.getPriceType())){
							plangoodsmap.put("pc_price", goodsprice.getPrice());
						}else if("wap".equals(goodsprice.getPriceType())){
							plangoodsmap.put("wap_price", goodsprice.getPrice());
						}else if("app".equals(goodsprice.getPriceType())){
							plangoodsmap.put("app_price", goodsprice.getPrice());
						}
					}
				}
		    }
		    
		    this.getRequest().setAttribute("list", list);
		}
		
		return "healthy_plan_symptom_add";
	}
	
	/**
	 * 保存或是修改伴随症状
	 * @return
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public String planSymptomSave() throws IOException, SQLException {
		if (uploadify != null && uploadify.length > 0) {
        	Map<String,String> map = new HashMap<String, String>();
			map.put("syskey", "healthyplan_abbreviationpicture_path");
			Object obj = opensqlmanage.selectForObjectByMap(map, "t_sys_parameter.query_sysc");
			String diskPath = "";
			if(null!=obj){
				Map<String, String> objmap = (Map<String, String>) obj;
				diskPath = objmap.get("sys_value");
			}
			String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
			String fullPath=diskPath+"/"+getDiskName()+"/";
			File createFile = new File(basePath+fullPath);
			if(! createFile.exists()) {  
				createFile.mkdirs();
			}  
            for (int i = 0; i < uploadify.length; i++) {
				String fullName=fullPath+DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()+Math.random()))+"."+uploadifyFileName[i].split("\\.")[1];
				File newFile = new File(basePath+fullName);
				FileUtils.copyFile(uploadify[i], newFile);
				thealthyplansymptom.setBannerImgUrl(fullName);
            }
        }
		long symptomId = this.thealthyplanmanager.saveTHealthyPlanSymptom(thealthyplansymptom, goodsWeights, goodsids);
		this.getRequest().setAttribute("status", symptomId);
		return "returnMsg";
	}
	
	
	/**
	 * 跳转到用药套餐添加页面
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String planCombinationAdd() throws NumberFormatException, SQLException{
		String combinationId =this.getRequest().getParameter("combinationId");
		thealthyplancombination = new THealthyPlanCombination();
		String planId = this.getRequest().getParameter("planId");
		if(StringUtils.hasText(planId)){
			thealthyplancombination.setPlanId(Long.valueOf(planId.trim()));	
		}
		if(StringUtils.hasText(combinationId)){
			thealthyplancombination = this.thealthyplancombinationmanager.selectByPrimaryKey(Long.valueOf(combinationId.trim()));
			Map<String,Object> paramMap= new HashMap<String,Object>();
			paramMap.put("relationId", thealthyplancombination.getId());
			paramMap.put("type", 2);
		    List<Map> list = this.opensqlmanage.selectForListByMap(paramMap, "t_healthy_plan_goods.selectPlanGoodsListByMap");
		    TGoodsPriceExample priceExample = null;
		    for(Map plangoodsmap:list){
		    	 priceExample = new TGoodsPriceExample();
				priceExample.createCriteria().andPlatformTypeEqualTo("111yao").andGoodsIdEqualTo((Long)plangoodsmap.get("id"));
				List<TGoodsPrice> goodsPriceList =tgoodspricemanager.selectByExample(priceExample);
				if(goodsPriceList!=null&&goodsPriceList.size()>0){
					for(TGoodsPrice goodsprice:goodsPriceList){
						if("pc".equals(goodsprice.getPriceType())){
							plangoodsmap.put("pc_price", goodsprice.getPrice());
						}else if("wap".equals(goodsprice.getPriceType())){
							plangoodsmap.put("wap_price", goodsprice.getPrice());
						}else if("app".equals(goodsprice.getPriceType())){
							plangoodsmap.put("app_price", goodsprice.getPrice());
						}
					}
				}
		    }
		    
		    this.getRequest().setAttribute("list", list);
		}
		TPhysicianExample example = new TPhysicianExample();
		example.createCriteria().andAuditTypeEqualTo(1);
		List phyList =this.tphysicianmanager.selectByExample(example);
		this.getRequest().setAttribute("phyList", phyList);
		return "healthy_plan_combination_add";
	}
	
	/**
	 * 保存用药套餐
	 * @return
	 * @throws SQLException 
	 */
	public String planCombinationSave() throws SQLException{
		long combinationId = this.thealthyplanmanager.saveTHealthyPlanCombination(thealthyplancombination, goodsWeights, goodsids);
		this.getRequest().setAttribute("status", combinationId);
		return "returnMsg";
	}
	
	
	/**
	 * 删除健康方案
	 */
	public void deletePlan(){
		 String planId = this.getRequest().getParameter("planId");
		 String flag ="0";
		 if(StringUtils.hasText("planId")){
			 try {
				this.thealthyplanmanager.deleteTHealthyPlan(Long.valueOf(planId.trim()));
				flag="1";
			 } catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 writeObjectToResponse(flag, ContentType.application_json);
	}
	
	
	public class Condition {
		//方案分类
		private Long classificationId;
		//方案名称
		private String planName;
		//方案发布状态
		private Integer planStatus;
		//主药商品名称
		private String goodsName;
		//主药商品海典编号
		private String goodsno;
		public Long getClassificationId() {
			return classificationId;
		}
		public void setClassificationId(Long classificationId) {
			this.classificationId = classificationId;
		}
		public String getPlanName() {
			return planName;
		}
		public void setPlanName(String planName) {
			this.planName = planName;
		}
		public Integer getPlanStatus() {
			return planStatus;
		}
		public void setPlanStatus(Integer planStatus) {
			this.planStatus = planStatus;
		}
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}
		public String getGoodsno() {
			return goodsno;
		}
		public void setGoodsno(String goodsno) {
			this.goodsno = goodsno;
		}
		
	}
	
	public Object getModel() {
		return this.model;
	}
	public void setModel(Object o) {
		this.model = (Condition) o;
	}

	public PageWraper getPw() {
		return pw;
	}

	public void setPw(PageWraper pw) {
		this.pw = pw;
	}

	public PageResult getRs() {
		return rs;
	}

	public void setRs(PageResult rs) {
		this.rs = rs;
	}
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}
	public THealthyPlanClassificationManager getThealthyplanclassificationmanager() {
		return thealthyplanclassificationmanager;
	}
	public void setThealthyplanclassificationmanager(
			THealthyPlanClassificationManager thealthyplanclassificationmanager) {
		this.thealthyplanclassificationmanager = thealthyplanclassificationmanager;
	}
	public List<THealthyPlanClassification> getClassificationList() {
		return classificationList;
	}
	public void setClassificationList(
			List<THealthyPlanClassification> classificationList) {
		this.classificationList = classificationList;
	}

	public THealthyPlan getThealthyplan() {
		return thealthyplan;
	}

	public void setThealthyplan(THealthyPlan thealthyplan) {
		this.thealthyplan = thealthyplan;
	}

	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}

	public String[] getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String[] uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public File[] getUploadify() {
		return uploadify;
	}

	public void setUploadify(File[] uploadify) {
		this.uploadify = uploadify;
	}

	public THealthyPlanManager getThealthyplanmanager() {
		return thealthyplanmanager;
	}

	public void setThealthyplanmanager(THealthyPlanManager thealthyplanmanager) {
		this.thealthyplanmanager = thealthyplanmanager;
	}

	public String[] getGoodsWeights() {
		return goodsWeights;
	}

	public void setGoodsWeights(String[] goodsWeights) {
		this.goodsWeights = goodsWeights;
	}

	public String[] getGoodsids() {
		return goodsids;
	}

	public void setGoodsids(String[] goodsids) {
		this.goodsids = goodsids;
	}

	public THealthyPlanSymptom getThealthyplansymptom() {
		return thealthyplansymptom;
	}

	public void setThealthyplansymptom(THealthyPlanSymptom thealthyplansymptom) {
		this.thealthyplansymptom = thealthyplansymptom;
	}

	public THealthyPlanCombination getThealthyplancombination() {
		return thealthyplancombination;
	}

	public void setThealthyplancombination(
			THealthyPlanCombination thealthyplancombination) {
		this.thealthyplancombination = thealthyplancombination;
	}

	public THealthyPlanSymptomManager getThealthyplansymptommanager() {
		return thealthyplansymptommanager;
	}

	public void setThealthyplansymptommanager(
			THealthyPlanSymptomManager thealthyplansymptommanager) {
		this.thealthyplansymptommanager = thealthyplansymptommanager;
	}

	public THealthyPlanCombinationManager getThealthyplancombinationmanager() {
		return thealthyplancombinationmanager;
	}

	public void setThealthyplancombinationmanager(
			THealthyPlanCombinationManager thealthyplancombinationmanager) {
		this.thealthyplancombinationmanager = thealthyplancombinationmanager;
	}

	public TPhysicianManager getTphysicianmanager() {
		return tphysicianmanager;
	}

	public void setTphysicianmanager(TPhysicianManager tphysicianmanager) {
		this.tphysicianmanager = tphysicianmanager;
	}

	public THealthyPlanGoodsManager getThealthyplangoodsmanager() {
		return thealthyplangoodsmanager;
	}
	public void setThealthyplangoodsmanager(
			THealthyPlanGoodsManager thealthyplangoodsmanager) {
		this.thealthyplangoodsmanager = thealthyplangoodsmanager;
	}
	public THealthyPlanClassification getThealthyplanclassification() {
		return thealthyplanclassification;
	}
	public void setThealthyplanclassification(
			THealthyPlanClassification thealthyplanclassification) {
		this.thealthyplanclassification = thealthyplanclassification;
	}
	public void setModel(Condition model) {
		this.model = model;
	}


	public THealthyPlanRecommendManager getThealthyplanrecommendmanager() {
		return thealthyplanrecommendmanager;
	}
	public void setThealthyplanrecommendmanager(
			THealthyPlanRecommendManager thealthyplanrecommendmanager) {
		this.thealthyplanrecommendmanager = thealthyplanrecommendmanager;
	}
	public String getWxfileFileName() {
		return wxfileFileName;
	}
	public void setWxfileFileName(String wxfileFileName) {
		this.wxfileFileName = wxfileFileName;
	}
	public File getWxfile() {
		return wxfile;
	}

	public void setWxfile(File wxfile) {
		this.wxfile = wxfile;
	}


	public TCategoryManager getTcategorymanager() {
		return tcategorymanager;
	}


	public void setTcategorymanager(TCategoryManager tcategorymanager) {
		this.tcategorymanager = tcategorymanager;
	}


	public TGoodsPriceManager getTgoodspricemanager() {
		return tgoodspricemanager;
	}


	public void setTgoodspricemanager(TGoodsPriceManager tgoodspricemanager) {
		this.tgoodspricemanager = tgoodspricemanager;
	}
	

	
}
