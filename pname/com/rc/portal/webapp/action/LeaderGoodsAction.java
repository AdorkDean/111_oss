package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsBrokerageManager;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.vo.TGoodsBrokerage;
import com.rc.portal.vo.TGoodsBrokerageExample;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.webapp.util.PageResult;
/**
 * 领袖商品佣金管理
 * @author Administrator
 *
 */
public class LeaderGoodsAction extends BaseAction{

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 

	private OpenSqlManage opensqlmanage;
	
	private String name;
	private TMemberAmountOutManager tmemberamountoutmanager;
	private TMemberAmountOut amountOut;
	
	private TGoodsBrokerageManager tgoodsbrokeragemanager;
	
	private TGoodsBrokerage brokerage;
	private String url ;
	
	/**
	 * 商品佣金列表
	 * @return
	 */
	public String list(){
		Map<String,Object> querMap = new HashMap<String,Object>();
		if(null != name && !("").equals(name)){
			querMap.put("name", "%"+name+"%");
		}
		String goodsno = this.getRequest().getParameter("goodsno");
		if(null != goodsno && !("").equals(goodsno)){
			querMap.put("goodsno", "%"+goodsno+"%");
		}
		pw = opensqlmanage.selectForPageByMap_drug(querMap, "t_goods_brokerage.selectListPageCount", "t_goods_brokerage.selectListPage", rs.getP_curPage(), rs.getP_pageSize());
		return "list";
	}
	
	
	/**
	 * 添加商品佣金跳转
	 * @return
	 */
	public String leaderGoodsEdit(){
		
		return "leaderGoodsEdit";
	}
	
	/**
	 * 编辑佣金商品跳转
	 * @return
	 */
	public String leaderGoodsUpEdit(){
		String id = this.getRequest().getParameter("id");
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("id", id);
		Map map = (Map) opensqlmanage.selectForObjectByMap(querMap, "t_goods_brokerage.selectObjectPage");
		this.getRequest().setAttribute("lmap", map);
		return "leaderGoodsUpEdit";
	}
	
	/**
	 * 添加商品佣金
	 * @return
	 * @throws SQLException 
	 */
	public void leaderGoodsAdd() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		String bro = this.getRequest().getParameter("bro");
		String goodsId = this.getRequest().getParameter("goodsId");
		if(null != bro && null != goodsId && !("").equals(goodsId) && !("").equals(bro)){
			try {
				brokerage = new TGoodsBrokerage();
				brokerage.setBrokerage(new Long(bro));
				brokerage.setGoodsId(new Long(goodsId));
				TGoodsBrokerageExample tbe = new TGoodsBrokerageExample();
				tbe.createCriteria().andGoodsIdEqualTo(brokerage.getGoodsId());
				List list = new ArrayList();
				list = tgoodsbrokeragemanager.selectByExample(tbe);
				if (null != list && list.size() > 0) {
					out.print("-1"); //已经参加过佣金商品
				} else {
					Long id = tgoodsbrokeragemanager.insertSelective(brokerage);
					System.out.println(id);
					if (null != id && id > 0) {
						out.print("1");
					} else {
						out.print("-2"); //添加失败
					}
				}
			} catch (Exception e) {
				out.print("-3"); //系统错误
				e.printStackTrace();
			}
		}else{
			out.print("-4"); //参数有误
		}
		out.close();
	}
	

	/**
	 * 修改商品佣金
	 */
	public String leaderGoodsUpdate() throws SQLException{
		int row = tgoodsbrokeragemanager.updateByPrimaryKeySelective(brokerage);
		if(row>0){
			this.addFlashMessage(true);
		}else{
			this.addFlashMessage(false);
		}
		return "redirect";
	}
	
	/**
	 * 删除商品佣金
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public String leaderGoodsDelete() throws SQLException{
		String id = this.getRequest().getParameter("id");
		int row = tgoodsbrokeragemanager.deleteByPrimaryKey(new Long(id));
		if(row>0){
			this.addFlashMessage(true);
		}else{
			this.addFlashMessage(false);
		}
		url = "/leader/leaderGoods!list.action";
//		if(null != name && !("").equals(name)){
//			url +="?name="+name;
//		}
		return "delete_list";
	}
	
	public void selectGoodsByHaidianCode() {
		String goodsno = this.getRequest().getParameter("goodsno");
		if (goodsno == null || "".equals(goodsno)) {
			return;
		}
		Map<String, Object> _paramMap = new HashMap<String, Object>();
		_paramMap.put("goodsno", goodsno);
		Map<String, Object> map = (Map<String, Object>) opensqlmanage.selectForObjectByMap(_paramMap,
				"t_goods.selectGoodsByHaidianCode");
		// /id,goods_name,goodsno
		ResultData resultData = new ResultData();

		if (map != null && map.get("id") != null && !"".equals(map.get("id").toString())) {
			resultData.setStatus(1);
			resultData.setGoodsName(map.get("goods_name") == null ? "" : map.get("goods_name").toString());
			resultData.setGoodsno(map.get("goodsno") == null ? "" : map.get("goodsno").toString());
			resultData.setId(Long.valueOf(map.get("id").toString()));
			resultData.setPc_price(new Double(map.get("pc_price").toString()));
			resultData.setWap_price(new Double(map.get("wap_price").toString()));
			resultData.setApp_price(new Double(map.get("app_price").toString()));
			this.writeObjectToResponse(resultData, ContentType.application_json);
		} else {
			resultData.setStatus(0);
			this.writeObjectToResponse(resultData, ContentType.application_json);
		}
	}
	
	
	class ResultData{
		private int status;
		private String goodsName;
		private String goodsno;
		private Long id;
		private Double pc_price;
		private Double wap_price;
		private Double app_price;
		
		
		
		public Double getPc_price() {
			return pc_price;
		}
		public void setPc_price(Double pc_price) {
			this.pc_price = pc_price;
		}
		public Double getWap_price() {
			return wap_price;
		}
		public void setWap_price(Double wap_price) {
			this.wap_price = wap_price;
		}
		public Double getApp_price() {
			return app_price;
		}
		public void setApp_price(Double app_price) {
			this.app_price = app_price;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
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
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TMemberAmountOutManager getTmemberamountoutmanager() {
		return tmemberamountoutmanager;
	}

	public void setTmemberamountoutmanager(
			TMemberAmountOutManager tmemberamountoutmanager) {
		this.tmemberamountoutmanager = tmemberamountoutmanager;
	}

	public TMemberAmountOut getAmountOut() {
		return amountOut;
	}

	public void setAmountOut(TMemberAmountOut amountOut) {
		this.amountOut = amountOut;
	}

	public TGoodsBrokerageManager getTgoodsbrokeragemanager() {
		return tgoodsbrokeragemanager;
	}

	public void setTgoodsbrokeragemanager(
			TGoodsBrokerageManager tgoodsbrokeragemanager) {
		this.tgoodsbrokeragemanager = tgoodsbrokeragemanager;
	}

	public TGoodsBrokerage getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(TGoodsBrokerage brokerage) {
		this.brokerage = brokerage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
