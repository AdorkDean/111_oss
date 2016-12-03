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
import com.rc.portal.service.TLongManager;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TLong;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;

public class LongCradAction extends BaseAction{

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private TLongManager tlongmanager;
	private TCoupon coupon;
	private Condition model = new Condition();
	private TLong tlong;
	
	private OpenSqlManage opensqlmanage;
	private String longName;
	private String url;
	
	/**
	 * 编辑龙卡跳转
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public String longCardEdit() throws IOException, SQLException{
		
		return "longCardEdit";
	}
	
	/**
	 * 编辑优惠劵跳转页面
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	public String longUpEdit() throws Exception{
		String id = this.getRequest().getParameter("id"); 
		if(null != id && !("").equals(id)){
			tlong = tlongmanager.selectByPrimaryKey(new Long(id));
			return "upLongCardEdit";
		}else{
			return "list";
		}
	}
	
	/**
	 * 修改优惠劵
	 * @return
	 * @throws SQLException 
	 */
	public String upLong() throws SQLException{
		if(null !=tlong){
			System.out.println(tlong.getId());
			tlongmanager.updateLong(tlong);
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
	public String addLongCard() throws IOException, SQLException{
		Date date = new Date();
		tlong.setCreateTime(date);
		Long id = tlongmanager.insertOrLongCard(tlong);
		this.addFlashMessage(true);
		return "list";
	}
	
	/**
	 * 优惠券列表
	 * @return
	 */
	public String list(){
		Map<String,Object> querMap = new HashMap<String,Object>();
		if(null != longName && !("").equals(longName)){
			querMap.put("longName", "%"+longName+"%");
		}
		pw = opensqlmanage.selectForPageByMap_drug(querMap, "t_long.selectListPageCount", "t_long.selectListPage", rs.getP_curPage(), rs.getP_pageSize());
		return "longCardList";
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
			tlong = tlongmanager.selectByPrimaryKey(new Long(id));
			Map<String,Object> querMap = new HashMap<String,Object>();
			querMap.put("longid", id);
			Integer  ccount = (Integer)opensqlmanage.selectForObjectByMap_drug(querMap, "t_long_card.selectLongIsUse");
			this.getRequest().getSession().setAttribute("ccount", ccount);
			querMap.put("isUse", "1"); //已使用
			Integer  ycount = (Integer)opensqlmanage.selectForObjectByMap_drug(querMap, "t_long_card.selectLongIsUse");
			this.getRequest().getSession().setAttribute("ycount", ycount);
			querMap.remove("isUse");
			querMap.put("binding", "1"); //已绑定
			Integer  bcount = (Integer)opensqlmanage.selectForObjectByMap_drug(querMap, "t_long_card.selectLongIsUse");
			this.getRequest().getSession().setAttribute("bcount", bcount);		
			return "createLongCardEdit";
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
			tlong = tlongmanager.selectByPrimaryKey(new Long(id));
			tlong.setQuantity(new Integer(quantity));
			tlongmanager.createLongCardEdit(tlong);
			this.addFlashMessage(true);
			return "list";
		}
		this.addFlashMessage(false);
		return "list";
	}
	
	
	
	/**
	 * 龙卡列表
	 */
	public String getLongCard(){
		Map map=new HashMap();
		if(model.getLongId()!=null&&model.getLongId()!=0){
			map.put("longid", model.getLongId());
			if(model.getType()!=null&&model.getType()!=0){
				map.put("type", 2);
			}else{
				map.put("type", 0);
			}
				pw=opensqlmanage.selectForPageByMap_drug(map, "t_long_card.selectLongCardCountByMap", "t_long_card.selectLongCardListByMap", rs.getP_curPage(), rs.getP_pageSize());
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
			return "getLongCard";
		}
	}
	
	//设置表头
	public List<String> export() {
		List list = new ArrayList();
		list.add("龙卡卡号");
		list.add("使用状态");
		list.add("使用时间");
		list.add("创建时间");
		list.add("用户名");
		list.add("龙卡名称");
		list.add("龙卡开始时间");
		list.add("龙卡结束时间");
		return list;
	}
	//存放二维数组
		public String[][] getArray(List couponList) {
			String[][] s = new String[couponList.size()][8];
			for (int i = 0; i < couponList.size(); i++) {
				Map m = (Map)couponList.get(i);
				if(m.get("card")!=null){
					s[i][0]=m.get("card").toString();
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
				
				if(m.get("used_time")!=null){
					s[i][2]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("used_time"));
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
				
				if(m.get("name")!=null){
					s[i][5]=m.get("name").toString();
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
		private Long longId;
		private Integer type;

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Long getLongId() {
			return longId;
		}

		public void setLongId(Long longId) {
			this.longId = longId;
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

	public TLongManager getTlongmanager() {
		return tlongmanager;
	}

	public void setTlongmanager(TLongManager tlongmanager) {
		this.tlongmanager = tlongmanager;
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

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public TLong getTlong() {
		return tlong;
	}

	public void setTlong(TLong tlong) {
		this.tlong = tlong;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
