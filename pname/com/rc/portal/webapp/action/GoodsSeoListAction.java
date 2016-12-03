package com.rc.portal.webapp.action;

import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.webapp.util.PageResult;

public class GoodsSeoListAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	
	public String getGoodsSeoList(){
		Map map=new HashMap();
		if(model.getGoodsno()!=null&&!"".equals(model.getGoodsno().trim())){
			map.put("goodsno", model.getGoodsno().trim());
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "", "", rs.getP_curPage(), rs.getP_pageSize());
		return "getGoodsSeoList";
	}
	
	public class Condition {
		private String goodsno;//海典编号

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
