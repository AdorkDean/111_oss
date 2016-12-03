package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.service.TGoodsPriceManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.util.MemCached;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TCategoryExample;
import com.rc.portal.webapp.model.GoodsFormBeanModel;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;

public class NewGoodsListAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private TCategoryManager tcategorymanager;
	private TGoodsPriceManager tgoodspricemanager;
	private TSysParameterManager tsysparametermanager;
	private List<Map> cate1List=new ArrayList<Map>();
	private List<TCategory> cateList=new ArrayList<TCategory>();
	private String url;
	private GoodsFormBeanModel goodsf = new GoodsFormBeanModel();
	
	public String getGoodsList() throws Exception{
		MemCachedClient cache = MemCached.getmcc();
		
		Map map=new HashMap();
		if(model.getGoodsName()!=null&&!"".equals(model.getGoodsName().trim())){
			map.put("goodsName", model.getGoodsName().trim());
			goodsf.setShortname(model.getGoodsName().trim());
		}
		if(model.getGoodsNo()!=null&&!"".equals(model.getGoodsNo().trim())){
			map.put("goodsNo", model.getGoodsNo().trim());
			goodsf.setGoodsno(model.getGoodsNo().trim());
		}
		if(model.getCategoryid()!=null&&!"".equals(model.getCategoryid().trim())&&!"-1".equals(model.getCategoryid().trim())){
			map.put("categoryId", model.getCategoryid().trim());
		}
		if(model.getStatus()!=null&&model.getStatus()!=-1){
			map.put("status", model.getStatus());
			map.put("price_type", model.getPrice_type().trim());
		}
		if(model.getGoodsType()!=null&&model.getGoodsType()!=-1){
			map.put("goodType", model.getGoodsType());
		}
		if(model.getType()!=null&&model.getType()!=0){
			map.put("type", 2);
		}else{
			map.put("type", 0);
		}
		String bili = tsysparametermanager.getKeys("fanyong_default_bili");
		map.put("bili", bili);
		cate1List=getCheckCateList();
//		cache.delete("cateList");
		if(cache.get("cateList")!=null){
			String json=(String)cache.get("cateList");
			net.sf.json.JSONArray ja=net.sf.json.JSONArray.fromObject(json);
			cateList=net.sf.json.JSONArray.toList(ja);
		}else{
			TCategoryExample tce=new TCategoryExample();
			tce.createCriteria();
			cateList = tcategorymanager.selectByExample(tce);
			net.sf.json.JSONArray jo=net.sf.json.JSONArray.fromObject(cateList);
			cache.set("cateList",jo.toString());
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_goods.query_record_new_count", "t_goods.query_record_new", rs.getP_curPage(), rs.getP_pageSize());
		if(model.getType()!=null&&model.getType()!=0){
			String path=getRequest().getSession().getServletContext().getRealPath("");
			path=path+"/excel_temp/"+UtilDate.getDate()+"商品列表"+System.currentTimeMillis()+".xls";	
				List lists = goodsExport();
				String s[][] = getGoodsArray(pw.getResult());
				ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		}else{
			return "getGoodsList";
		}
	}
	
	
	 public List<String> goodsExport() {
			List list = new ArrayList();
			list.add("商品编号");
			list.add("海典编号");
			list.add("商品名称");
			list.add("规格");
			list.add("市场价");
			list.add("销售价");
			list.add("返佣金额");
			list.add("返佣比例");
			list.add("库存");
			list.add("上架状态");
			list.add("是否发布");
			list.add("处方药");
			return list;
		}
		//存放二维数组
		public String[][] getGoodsArray(List goodsList) {
			String[][] s = new String[goodsList.size()][12];
			for (int i = 0; i < goodsList.size(); i++) {
				Map m = (Map)goodsList.get(i);
				if(m.get("goodscode")!=null){
					s[i][0]=m.get("goodscode").toString();
				}else{
					s[i][0] = " ";
				}
				if(m.get("goodsno")!=null){
					s[i][1]=m.get("goodsno").toString();
				}else{
					s[i][1] = " ";
				}
				if(m.get("short_name")!=null){
					s[i][2]=m.get("short_name").toString();
				}else{
					s[i][2] = " ";
				}
				if(m.get("spec")!=null){
					s[i][3]=m.get("spec").toString();
				}else{
					s[i][3] = " ";
				}
				if(m.get("price")!=null){
					s[i][4]=m.get("price").toString();
				}else{
					s[i][4]="0.00";
				}
				
				String price="PC:";
				if(m.get("pc_price")!=null){
					price+=m.get("pc_price").toString();
				}else{
					price+="0.00";
				}
				price+="\nWAP:";
				if(m.get("wap_price")!=null){
					price+=m.get("wap_price").toString();
				}else{
					price+="0.00";
				}
				price+="\nAPP:";
				if(m.get("app_price")!=null){
					price+=m.get("app_price").toString();
				}else{
					price+="0.00";
				}
				s[i][5]=price;
				
				String rebate_amount="PC:";
				if(m.get("pc_rebate_amount")!=null){
					rebate_amount+=m.get("pc_rebate_amount").toString();
				}else{
					rebate_amount+="0.00";
				}
				rebate_amount+="\nWAP:";
				if(m.get("wap_rebate_amount")!=null){
					rebate_amount+=m.get("wap_rebate_amount").toString();
				}else{
					rebate_amount+="0.00";
				}
				rebate_amount+="\nAPP:";
				if(m.get("app_rebate_amount")!=null){
					rebate_amount+=m.get("app_rebate_amount").toString();
				}else{
					rebate_amount+="0.00";
				}
				s[i][6]=rebate_amount;
				
				
				String bili="PC:";
				if(m.get("pc_rebate_amount")!=null){
					if(m.get("pc_price")!=null&&new BigDecimal(m.get("pc_price").toString()).compareTo(new BigDecimal(0))!=0){
						bili+=new BigDecimal(m.get("pc_rebate_amount").toString()).divide(new BigDecimal(m.get("pc_price").toString()),2).multiply(new BigDecimal(100))+"%";
					}else{
						bili+="0%";	
					}
				}else{
					bili+="0%";
				}
				bili+="\nWAP:";
				if(m.get("wap_rebate_amount")!=null){
					if(m.get("wap_price")!=null&&new BigDecimal(m.get("wap_price").toString()).compareTo(new BigDecimal(0))!=0){
					bili+=new BigDecimal(m.get("wap_rebate_amount").toString()).divide(new BigDecimal(m.get("wap_price").toString()),2).multiply(new BigDecimal(100))+"%";
					}else{
						bili+="0%";
					}
				}else{
					bili+="0%";
				}
				bili+="\nAPP:";
				if(m.get("app_rebate_amount")!=null){
					if(m.get("app_price")!=null&&new BigDecimal(m.get("app_price").toString()).compareTo(new BigDecimal(0))!=0){
					bili+=new BigDecimal(m.get("app_rebate_amount").toString()).divide(new BigDecimal(m.get("app_price").toString()),2).multiply(new BigDecimal(100))+"%";
					}else{
						bili+="0%";
					}
				}else{
					bili+="0%";
				}
				s[i][7]=bili;
				
				
				if(m.get("stock")!=null){
					s[i][8]=m.get("stock").toString();
				}else{
					s[i][8]="0";
				}
				
				String status="PC:";
				
				if(m.get("pc_status")!=null){
					if(m.get("pc_status").toString().equals("2")){
						status+="是";
					}else{
						status+="否";
					}
				}else{
					status+="否";
				}
				status+="\nWAP:";
				if(m.get("wap_status")!=null){
					if(m.get("wap_status").toString().equals("2")){
						status+="是";
					}else{
						status+="否";
					}
				}else{
					status+="否";
				}
				status+="\nAPP:";
				if(m.get("app_status")!=null){
					if(m.get("app_status").toString().equals("2")){
						status+="是";
					}else{
						status+="否";
					}
				}else{
					status+="否";
				}
				s[i][9]=status;
				if(m.get("is_release")!=null){
					if(m.get("is_release").toString().equals("1")){
						s[i][10]="是";
					}else{
						s[i][10]="否";
					}
				}else{
					s[i][10]="否";
				}
				if(m.get("type")!=null){
					if(m.get("type").toString().equals("2")||m.get("type").toString().equals("3")){
						s[i][11]="是";
					}else{
						s[i][11]="否";
					}
				}else{
					s[i][11]="否";
				}
				
			}
			return s;
		}
	
	
	public List<Map> getCheckCateList() throws Exception{
		Map mapTem=new HashMap();
		if(model.getCategoryid()!=null&&!"".equals(model.getCategoryid().trim())&&!"-1".equals(model.getCategoryid().trim())){
			mapTem.put("categoryid", model.getCategoryid().trim());
			String allPra=(String)opensqlmanage.selectForObjectByMap(mapTem, "t_goods.selectparentByID");
			if(allPra!=null&&!"".equals(allPra.trim())){
				String[] ss = allPra.split(",");
				for(int i=1;i<ss.length;i++){
					TCategory category = tcategorymanager.selectByPrimaryKey(new Long(ss[i].trim()));
					if(category!=null){	
						Map mapp=new HashMap();
						mapp.put("id", category.getId());
						mapp.put("categoryName", category.getCategoryName());
						mapp.put("level", category.getCategoryLevel());
						cate1List.add(mapp);
					}
				}
			}
			TCategory ca = tcategorymanager.selectByPrimaryKey(new Long(model.getCategoryid().trim()));
			if(ca!=null){
				Map mapp1=new HashMap();
				mapp1.put("id", ca.getId());
				mapp1.put("categoryName", ca.getCategoryName());
				mapp1.put("level", ca.getCategoryLevel());
				cate1List.add(mapp1);
			}
		}
		return cate1List;
	}
	
	public void setGoodsStatus() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		flag=tgoodspricemanager.setStatus(model.getType(),new Long(model.getGoodsid()),user);
		write.write(flag.toString());
		write.close();
	}
	public class Condition {
	private String goodsName;
	private String goodsNo;
	private String categoryid;
	private Integer status;
	private Integer type;
	private Integer goodsType;
	private String  price_type;
	private String goodsid;
	
	
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public String getPrice_type() {
		return price_type;
	}
	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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

	public TCategoryManager getTcategorymanager() {
		return tcategorymanager;
	}

	public void setTcategorymanager(TCategoryManager tcategorymanager) {
		this.tcategorymanager = tcategorymanager;
	}

	public List<Map> getCate1List() {
		return cate1List;
	}

	public void setCate1List(List<Map> cate1List) {
		this.cate1List = cate1List;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public List<TCategory> getCateList() {
		return cateList;
	}


	public void setCateList(List<TCategory> cateList) {
		this.cateList = cateList;
	}


	public TGoodsPriceManager getTgoodspricemanager() {
		return tgoodspricemanager;
	}


	public void setTgoodspricemanager(TGoodsPriceManager tgoodspricemanager) {
		this.tgoodspricemanager = tgoodspricemanager;
	}


	public GoodsFormBeanModel getGoodsf() {
		return goodsf;
	}


	public void setGoodsf(GoodsFormBeanModel goodsf) {
		this.goodsf = goodsf;
	}


	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}


	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}





}
