package com.rc.portal.webapp.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.service.TCouponRelevanceManager;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TCategoryExample;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;

public class CouponAction extends BaseAction{

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private TCouponManager tcouponmanager;
	private TCoupon coupon;
	private OpenSqlManage opensqlmanage;
	private String couponName;
	private Condition model = new Condition();
	private String url;
	private TCouponRelevanceManager tcouponrelevancemanager;
	
	private TCategoryManager tcategorymanager;
	/**
	 * 编辑优惠券
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public String couponEdit() throws IOException, SQLException{
		
		
		TCategoryExample example = new TCategoryExample();
		/*
		example.createCriteria().andCategoryLevelEqualTo(1);
		List<TCategory> list1 = tcategorymanager.selectByExample(example);
		
		example.clear();
		example.createCriteria().andCategoryLevelEqualTo(2);
		List<TCategory> list2 = tcategorymanager.selectByExample(example);
		
		example.clear();
		example.createCriteria().andCategoryLevelEqualTo(3);
		List<TCategory> list3 = tcategorymanager.selectByExample(example);*/
		
		example.clear();
		example.createCriteria().andCategoryLevelEqualTo(2).andParentIdEqualTo(1L);
		List<TCategory> list1 = tcategorymanager.selectByExample(example);
		
		System.out.println("===="+list1.size());
		
		example.clear();
		example.createCriteria().andCategoryLevelEqualTo(3);
		List<TCategory> list2 = tcategorymanager.selectByExample(example);
		
		
		example.clear();
		example.createCriteria().andCategoryLevelEqualTo(4);
		List<TCategory> list3 = tcategorymanager.selectByExample(example);
		
		System.out.println("list3==========="+list3.size());
		
		this.getRequest().setAttribute("list1",list1);
		this.getRequest().setAttribute("list2",list2);
		this.getRequest().setAttribute("list3",list3);
		
		return "couponEdit";
	}
	
	/**
	 * 编辑优惠劵跳转页面
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	public String couponUpEdit() throws Exception{
		String id = this.getRequest().getParameter("id"); 
		if(null != id && !("").equals(id)){
			coupon = tcouponmanager.selectByPrimaryKey(new Long(id));
			if(coupon.getScope()==2){
				Map<String,Object> querMap = new HashMap<String,Object>();
				querMap.put("id", coupon.getId());
				String rname = (String) opensqlmanage.selectForObjectByMap(querMap, "t_coupon_relevance.selectByCategory");
			    this.getRequest().setAttribute("rname", rname);
			}else if(coupon.getScope()==3){
				Map<String,Object> querMap = new HashMap<String,Object>();
				querMap.put("id", coupon.getId());
				String rname = (String) opensqlmanage.selectForObjectByMap(querMap, "t_coupon_relevance.selectByBrand");
			    this.getRequest().setAttribute("rname", rname);
			}else if(coupon.getScope()==4){
				Map<String,Object> querMap = new HashMap<String,Object>();
				querMap.put("id", coupon.getId());
				String rname = (String) opensqlmanage.selectForObjectByMap(querMap, "t_coupon_relevance.selectByGoods");
			    this.getRequest().setAttribute("rname", rname);
			}
			return "upcouponEdit";
		}else{
			return "list";
		}
	}
	
	/**
	 * 修改优惠劵
	 * @return
	 * @throws SQLException 
	 */
	public String upcoupon() throws SQLException{
		if(null !=coupon){
			System.out.println(coupon.getId());
			SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
	        String time=formatter1.format(coupon.getEndTime());  
	         System.out.println(time); 
	         coupon.setEndTime(UtilDate.strToDateLong(time+" 23:59:59"));
			tcouponmanager.updateConpon(coupon);
			this.addFlashMessage(true);
		}else{
			this.addFlashMessage(false);
		}
		return "list";
	}
	
	/**
	 * 添加优惠券
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public String addCoupon() throws IOException, SQLException{
		Date date = new Date();
		coupon.setCreateTime(date);
		String tid = null;
		if(coupon.getScope()==2){
			tid = this.getRequest().getParameter("hcategory");
		}else if(coupon.getScope()==3){
			tid = this.getRequest().getParameter("brandid");
		}else if(coupon.getScope()==4){
			tid = this.getRequest().getParameter("goodsid");
		}
		SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
        String time=formatter1.format(coupon.getEndTime());  
         System.out.println(time); 
         coupon.setEndTime(UtilDate.strToDateLong(time+" 23:59:59"));
		Long id = tcouponmanager.insertOrTCouponCard(coupon,tid);
		this.addFlashMessage(true);
		return "list";
	}
	
	/**
	 * 优惠券列表
	 * @return
	 */
	public String list(){
		Map<String,Object> querMap = new HashMap<String,Object>();
		if(null != couponName && !("").equals(couponName)){
			querMap.put("couponName", "%"+couponName+"%");
		}
		pw = opensqlmanage.selectForPageByMap_drug(querMap, "t_coupon.selectByOpenByPageCount", "t_coupon.selectByOpenByPage", rs.getP_curPage(), rs.getP_pageSize());
		Date dt = new Date();
		this.getRequest().setAttribute("dt", dt);
		return "couponList";
	}
	
	/**
	 * 生成优惠劵编辑页面
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String createCardEdit() throws Exception{
		String id = this.getRequest().getParameter("id"); 
		if(null != id && !("").equals(id)){
			coupon = tcouponmanager.selectByPrimaryKey(new Long(id));
			Map<String,Object> querMap = new HashMap<String,Object>();
			querMap.put("couponid", id);
			Integer  ccount = (Integer)opensqlmanage.selectForObjectByMap_drug(querMap, "t_coupon_card.selectCountIsUse");
			this.getRequest().getSession().setAttribute("ccount", ccount);
//			querMap.put("isUse", "0"); //未使用优惠劵
//			Integer  wcount = (Integer)opensqlmanage.selectForObjectByMap_drug(querMap, "t_coupon_card.selectCountIsUse");
//			System.out.println("未使用优惠劵有="+wcount);
//			this.getRequest().getSession().setAttribute("wcount", wcount);
			querMap.put("isUse", "1"); //已使用优惠劵
			Integer  ycount = (Integer)opensqlmanage.selectForObjectByMap_drug(querMap, "t_coupon_card.selectCountIsUse");
			this.getRequest().getSession().setAttribute("ycount", ycount);
			querMap.remove("isUse");
			querMap.put("binding", "1"); //已绑定优惠劵
			Integer  bcount = (Integer)opensqlmanage.selectForObjectByMap_drug(querMap, "t_coupon_card.selectCountIsUse");
			this.getRequest().getSession().setAttribute("bcount", bcount);		
			return "createCardEdit";
		}else{
			return "list";
		}
	}
	
	/**
	 * 生成优惠劵
	 * @return
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String createCard() throws Exception{
		String id = this.getRequest().getParameter("id"); 
		String quantity = this.getRequest().getParameter("quantity"); 
		if(null != id && !("").equals(id) && null != quantity && !("").equals(quantity)){
			coupon = tcouponmanager.selectByPrimaryKey(new Long(id));
			coupon.setQuantity(new Integer(quantity));
			tcouponmanager.createCardEdit(coupon);
			this.addFlashMessage(true);
			return "list";
		}
		this.addFlashMessage(false);
		return "list";
	}
	
	/**
	 * 优惠券卡卷列表
	 */
	public String getCouponCard(){
		Map map=new HashMap();
		if(model.getCouponId()!=null&&model.getCouponId()!=0){
			map.put("couponid", model.getCouponId());
			if(model.getType()!=null&&model.getType()!=0){
				map.put("type", 2);
			}else{
				map.put("type", 0);
			}
				pw=opensqlmanage.selectForPageByMap_drug(map, "t_coupon_card.selectCouponCardCountByMap", "t_coupon_card.selectCouponCardListByMap", rs.getP_curPage(), rs.getP_pageSize());
		}
		if(model.getType()!=null&&model.getType()!=0){
			String path=getRequest().getSession().getServletContext().getRealPath("");
			path=path+"/excel_temp/"+UtilDate.getDate()+System.currentTimeMillis()+".xls";	
			if(pw.getResult().size()>0){
				List lists = export();
				String s[][] = getArray(pw.getResult());
				ExcelUtil.createExcel(path, lists, s);
			}
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		}else{
			return "getCouponCard";
		}
	}
	
	//设置表头
	public List<String> export() {
		List list = new ArrayList();
		list.add("优惠券卡号");
		list.add("使用状态");
		list.add("使用时间");
		list.add("记录时间");
		list.add("用户名");
		list.add("优惠券名称");
		list.add("优惠券开始时间");
		list.add("优惠券结束时间");
		return list;
	}
	//存放二维数组
		public String[][] getArray(List couponList) {
			String[][] s = new String[couponList.size()][8];
			for (int i = 0; i < couponList.size(); i++) {
				Map m = (Map)couponList.get(i);
				if(m.get("card_no")!=null){
					s[i][0]=m.get("card_no").toString();
				}else{
					s[i][0] = " ";
				}
				if(m.get("is_use")!=null){
					if(Integer.parseInt(m.get("is_use").toString())==0){
						s[i][1]="未使用";
					}else if(Integer.parseInt(m.get("is_use").toString())==1){
						s[i][1]="已使用";
					}
				}else{
					s[i][1]="未使用";
				}
				
				if(m.get("use_time")!=null){
					s[i][2]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("use_time"));
				}else{
					s[i][2]="";
				}
				
				if(m.get("create_time")!=null){
					s[i][3]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("create_time"));
				}else{
					s[i][3]="";
				}
				
				if(m.get("user_name")!=null){
					s[i][4]=m.get("user_name").toString();
				}else{
					s[i][4]="";
				}
				
				if(m.get("couponName")!=null){
					s[i][5]=m.get("couponName").toString();
				}else{
					s[i][5]="";
				}
				
				if(m.get("start_time")!=null){
					s[i][6]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("start_time"));
				}else{
					s[i][6]="";
				}
				
				if(m.get("end_time")!=null){
					s[i][7]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("end_time"));
				}else{
					s[i][7]="";
				}
			}
			return s;
		}
	public class Condition {
		private Long couponId;
		private Integer type;

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Long getCouponId() {
			return couponId;
		}

		public void setCouponId(Long couponId) {
			this.couponId = couponId;
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

	public TCouponManager getTcouponmanager() {
		return tcouponmanager;
	}

	public void setTcouponmanager(TCouponManager tcouponmanager) {
		this.tcouponmanager = tcouponmanager;
	}

	public TCoupon getCoupon() {
		return coupon;
	}

	public void setCoupon(TCoupon coupon) {
		this.coupon = coupon;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TCategoryManager getTcategorymanager() {
		return tcategorymanager;
	}

	public void setTcategorymanager(TCategoryManager tcategorymanager) {
		this.tcategorymanager = tcategorymanager;
	}

	public TCouponRelevanceManager getTcouponrelevancemanager() {
		return tcouponrelevancemanager;
	}

	public void setTcouponrelevancemanager(
			TCouponRelevanceManager tcouponrelevancemanager) {
		this.tcouponrelevancemanager = tcouponrelevancemanager;
	}
	
}
