package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsBrokerageManager;
import com.rc.portal.vo.TGoodsBrokerage;
import com.rc.portal.webapp.util.PageResult;

public class NewLeaderGoodsCheckAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private Map goodsMap=new HashMap();
	private TGoodsBrokerageManager tgoodsbrokeragemanager;
	private TGoodsBrokerage  brokerage;
	
	public String getLeaderGoodsCheckList(){
		Map map=new HashMap();
		if(model.getGoodsName()!=null&&!"".equals(model.getGoodsName().trim())){
			map.put("goodsName", model.getGoodsName().trim());
		}
		
		if(model.getGoodsno()!=null&&!"".equals(model.getGoodsno().trim())){
			map.put("goodsno", model.getGoodsno().trim());
		}
		
		if(model.getStartDate()!=null&&!"".equals(model.getStartDate().trim())){
			map.put("startDate", model.getStartDate().trim());
		}else{
			map.put("startDate","1900-01-01");
		}
		
		if(model.getEndDate()!=null&&!"".equals(model.getEndDate().trim())){
			map.put("endDate", model.getEndDate().trim());
		}else{
			map.put("endDate", "9999-01-01");
		}
		
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_goods_brokerage.selectGoodsBrokerageCheckListCount", "t_goods_brokerage.selectGoodsBrokerageCheckListByPage", rs.getP_curPage(), rs.getP_pageSize());
		return "getLeaderGoodsCheckList";
	}
	
	
	
	
	
	
	public void updateGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		Integer flag = tgoodsbrokeragemanager.updateByPrimaryKeySelective(brokerage);
		out.write(flag.toString());
		out.close();
	}
	public void deleteGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		Integer flag=0;
		if(model.getId()!=null&&!"0".equals(model.getId().trim())){
			flag=tgoodsbrokeragemanager.deleteByPrimaryKey(new Long(model.getId().trim()));
		}
		out.write(flag.toString());
		out.close();
	}
	
	public void checkGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		Integer flag=0;
		brokerage=tgoodsbrokeragemanager.selectByPrimaryKey(new Long(model.getId().trim()));
		brokerage.setAuditStatus(2);
		brokerage.setAuditDt(new Date());
		brokerage.setAuditRemark("审核通过");
		flag=tgoodsbrokeragemanager.updateByPrimaryKeySelective(brokerage);
		out.write(flag.toString());
		out.close();
	}
	public String goNoCheckGoods(){
		return "goNoCheckGoods";
	}
	
	
	public void noCheckGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		Integer flag=0;
		brokerage=tgoodsbrokeragemanager.selectByPrimaryKey(new Long(model.getId().trim()));
		brokerage.setAuditStatus(3);
		brokerage.setAuditDt(new Date());
		brokerage.setAuditRemark(model.getCheck_idear().trim());
		flag=tgoodsbrokeragemanager.updateByPrimaryKeySelective(brokerage);
		out.write(flag.toString());
		out.close();
	}
	public class Condition {
		private String goodsno;//海典编号
		private String goodsName;//商品名称
		private String id;//领秀商品ID
		private String startDate;
		private String endDate;
		private String check_idear;
		
		
		
		public String getCheck_idear() {
			return check_idear;
		}

		public void setCheck_idear(String check_idear) {
			this.check_idear = check_idear;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

	public Map getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(Map goodsMap) {
		this.goodsMap = goodsMap;
	}

	public TGoodsBrokerage getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(TGoodsBrokerage brokerage) {
		this.brokerage = brokerage;
	}

	public TGoodsBrokerageManager getTgoodsbrokeragemanager() {
		return tgoodsbrokeragemanager;
	}

	public void setTgoodsbrokeragemanager(
			TGoodsBrokerageManager tgoodsbrokeragemanager) {
		this.tgoodsbrokeragemanager = tgoodsbrokeragemanager;
	}

}
