package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.opensymphony.webwork.ServletActionContext;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.CLocationCityManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TStoreDispatchingRangeManager;
import com.rc.portal.vo.CLocationCity;
import com.rc.portal.vo.CLocationCityExample;
import com.rc.portal.vo.TStoreDispatchingRange;
import com.rc.portal.webapp.util.PageResult;

public class StoreDispatchingRangeAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private TStoreDispatchingRangeManager tstoredispatchingrangemanager;
	private TStoreDispatchingRange store=new TStoreDispatchingRange();
	private CLocationCityManager clocationcitymanager;
	//查询门店列表
	public String getDispatchList(){
		Map map=new HashMap();
		
		if(model.getStoreName()!=null&&!"".equals(model.getStoreName().trim())){
			map.put("storeName", model.getStoreName().trim());
		}
		
		if(model.getStoreHD()!=null&&!"".equals(model.getStoreHD().trim())){
			map.put("storeHd", model.getStoreHD().trim());
		}
		pw = opensqlmanage.selectForPageByMap_drug(map, "t_store_dispatching_range.selectStoreListCount", "t_store_dispatching_range.selectStoreListByPage", rs.getP_curPage(), rs.getP_pageSize());
		return "getDispatchList";
	}
	//跳转添加修改页面
	public String addOrEditStore() throws Exception{
		if(model.getStoreid()!=null&&model.getStoreid()!=-1){
			store=tstoredispatchingrangemanager.selectByPrimaryKey(new Long(model.getStoreid()));
			if(store!=null){
				if(store.getAreaCityId()!=null&&store.getAreaCityId()!=0){
					CLocationCity city = clocationcitymanager.selectByPrimaryKey(store.getAreaCityId().intValue());
					if(city!=null){
						model.setArea(city.getName());
						model.setCityCode(city.getCitycode());
					}
				}
			}
		}
		CLocationCityExample cityexample = new CLocationCityExample();
		cityexample.createCriteria().andGradeEqualTo(2);
		cityexample.setOrderByClause(" pinyin asc");
		List<CLocationCity> cityList =this.clocationcitymanager.selectByExample(cityexample);
		List<CLocationCity> cityzimuList = null;
		TreeMap<String,List<CLocationCity>> zimuMap = new TreeMap<String,List<CLocationCity>>();
		for(CLocationCity city:cityList){
			if(zimuMap.get(city.getPinyin())!=null){
				cityzimuList = zimuMap.get(city.getPinyin());
				cityzimuList.add(city);
				zimuMap.put(city.getPinyin(), cityzimuList);
			}else{
				cityzimuList = new ArrayList<CLocationCity>();
				cityzimuList.add(city);
				zimuMap.put(city.getPinyin(), cityzimuList);
			}
		}
		this.getRequest().setAttribute("zimuMap", zimuMap);
		return "addOrEditStore";
	}
	
	//根据cityCode 获取该城市所有的门店范围
	
	public void getStoreRangeByCity() throws SQLException, Exception{
		HttpServletResponse  response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Map<String,Object> result=new HashMap<String,Object>();
		String code="1";
		String content="";
		TStoreDispatchingRange st = null;
		Map map=new HashMap();
		if(model.getCityCode()!=null&&!"".equals(model.getCityCode().trim())){
			map.put("cityCode", model.getCityCode().trim());
			if(model.getStoreid()!=null&&model.getStoreid()!=-1){
				st=tstoredispatchingrangemanager.selectByPrimaryKey(new Long(model.getStoreid()));
				result.put("store", st);
			}else{
				result.put("store", null);
			}
			List<Map> mapList = new ArrayList<Map>();
			mapList=opensqlmanage.selectForListByMap_drug(map, "t_store_dispatching_range.selectStoreListByCityCode");
			if(mapList.size()>0){
				if(st!=null){
					List<Map> temMapList = new ArrayList<Map>();
					for(Map temap:mapList){
						if(new Long(temap.get("id").toString()).longValue()!=st.getId()){
							temMapList.add(temap);
						}
					}
					result.put("storeRange", temMapList);
				}else{
					result.put("storeRange", mapList);
				}
				code="1";
				content="返回结果正常";
			}else{
				code="0";
				content="该城市还没有门店";
			}
			
		}else{
			code="-1";
			content="城市编码为空";
		}
		result.put("code", code);
		result.put("content", content);
		JSONObject jo=JSONObject.fromObject(result);
		out.write(jo.toString());
		out.close();
	}
	//保存门店信息
	public void saveStore() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(model.getCityCode()!=null&&!"".equals(model.getCityCode().trim())){
			CLocationCityExample cityexample = new CLocationCityExample();
			cityexample.createCriteria().andGradeEqualTo(2).andCitycodeEqualTo(model.getCityCode().trim());
			List<CLocationCity> cityList = clocationcitymanager.selectByExample(cityexample);
			if(cityList!=null&&cityList.size()>0){
				store.setAreaCityId(new Long(cityList.get(0).getId()));
			}
		}
		flag=tstoredispatchingrangemanager.saveStore(store);
		write.write(flag);
		write.close();
	}
	//删除门店信息
	public void deleteStore() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(model.getStoreid()!=null&&model.getStoreid()!=-1){
			flag=tstoredispatchingrangemanager.deleteByPrimaryKey(new Long(model.getStoreid()));
		}
		write.write(flag);
		write.close();
	}
	public class Condition {
		private String storeHD;//门店海典ID
		private String storeName;//门店名称
		private Integer storeid;//门店ID
		private String cityCode;
		private String area;
		
		
		
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public Integer getStoreid() {
			return storeid;
		}
		public void setStoreid(Integer storeid) {
			this.storeid = storeid;
		}
		public String getStoreHD() {
			return storeHD;
		}
		public void setStoreHD(String storeHD) {
			this.storeHD = storeHD;
		}
		public String getStoreName() {
			return storeName;
		}
		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
		
		
		
	}
	@Override
	public Object getModel() {
		return this.model;
	}

	@Override
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
	public TStoreDispatchingRangeManager getTstoredispatchingrangemanager() {
		return tstoredispatchingrangemanager;
	}
	public void setTstoredispatchingrangemanager(
			TStoreDispatchingRangeManager tstoredispatchingrangemanager) {
		this.tstoredispatchingrangemanager = tstoredispatchingrangemanager;
	}
	public TStoreDispatchingRange getStore() {
		return store;
	}
	public void setStore(TStoreDispatchingRange store) {
		this.store = store;
	}
	public CLocationCityManager getClocationcitymanager() {
		return clocationcitymanager;
	}
	public void setClocationcitymanager(CLocationCityManager clocationcitymanager) {
		this.clocationcitymanager = clocationcitymanager;
	}
	
}
