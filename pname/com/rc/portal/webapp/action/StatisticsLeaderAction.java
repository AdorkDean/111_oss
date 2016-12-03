package com.rc.portal.webapp.action;

import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.webapp.util.PageResult;

public class StatisticsLeaderAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	
	//根据文章统计
	
	public String statisticArticleList(){
		Map map=new HashMap();
		if(model.getTitle()!=null&&!"".equals(model.getTitle().trim())){
			map.put("title", model.getTitle().trim());
		}
		if(model.getStart_date()!=null&&!"".equals(model.getStart_date())){
			map.put("start_date", model.getStart_date().trim());
		}else{
			map.put("start_date", "1900-01-01 00:00:00");
			
		}
		if(model.getEnd_date()!=null&&!"".equals(model.getEnd_date())){
			map.put("end_date", model.getEnd_date().trim());
		}else{
			map.put("end_date", "9999-12-01 00:00:00");
		}
		if(model.getSort()!=null&&model.getSort()!=-1){
			map.put("sort", model.getSort());
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_lx_look_collection.selectStatisticArticeLeaderListCount", "t_lx_look_collection.selectStatisticArticeLeaderList", rs.getP_curPage(), rs.getP_pageSize());
		return "statisticArticleList";
	}
	//根据商品统计
	
	public String statisticGoodsList(){
		Map map=new HashMap();
		if(model.getGoodsno()!=null&&!"".equals(model.getGoodsno().trim())){
			map.put("goodsno", model.getGoodsno().trim());
		}
		if(model.getStart_date()!=null&&!"".equals(model.getStart_date())){
			map.put("start_date", model.getStart_date().trim());
		}else{
			map.put("start_date", "1900-01-01 00:00:00");
			
		}
		if(model.getEnd_date()!=null&&!"".equals(model.getEnd_date())){
			map.put("end_date", model.getEnd_date().trim());
		}else{
			map.put("end_date", "9999-12-01 00:00:00");
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_lx_look_collection.selectStatisticGoodsLeaderListCount", "t_lx_look_collection.selectStatisticGoodsLeaderList", rs.getP_curPage(), rs.getP_pageSize());
		return "statisticGoodsList";
	}
	//统计文章分享
	public String statisticList(){
		Map map=new HashMap();
		if(model.getGoodsno()!=null&&!"".equals(model.getGoodsno().trim())){
			map.put("goodsno", model.getGoodsno().trim());
		}
		if(model.getSort()!=null&&model.getSort()!=-1){
			map.put("sort", model.getSort());
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_lx_look_collection.selectStatisticLeaderCount", "t_lx_look_collection.selectStatisticLeader", rs.getP_curPage(), rs.getP_pageSize());
		return "statisticList";
	}
	
	
	public class Condition {
		private String goodsno;
		private Integer sort;
		private String start_date;
		private String end_date;
		private String title;
		
		
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getStart_date() {
			return start_date;
		}

		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}

		public String getEnd_date() {
			return end_date;
		}

		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}

		public Integer getSort() {
			return sort;
		}

		public void setSort(Integer sort) {
			this.sort = sort;
		}

		public String getGoodsno() {
			return goodsno;
		}

		public void setGoodsno(String goodsno) {
			this.goodsno = goodsno;
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



	
}
