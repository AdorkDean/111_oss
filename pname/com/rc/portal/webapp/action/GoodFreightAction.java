package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TFreightManager;
import com.rc.portal.service.TGoodFreightManager;
import com.rc.portal.vo.TFreight;
import com.rc.portal.vo.TFreightExample;
import com.rc.portal.vo.TGoodFreight;
import com.rc.portal.webapp.util.PageResult;
/**
 * 商品运费
 * @author user3
 *
 */
public class GoodFreightAction extends BaseAction{
	private static final long serialVersionUID = 7278221845511133501L;
    private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	
	private OpenSqlManage opensqlmanage;
    private TGoodFreightManager tgoodfreightmanager;
    private TFreightManager tfreightmanager;
    private String goodsno;
    
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {
	}

	
	/**
	 * 运费列表
	 */
	public String freightList(){
		Map<String,String> paramMap = new HashMap<String,String>();
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "t_freight.selectFreightCountByMap", "t_freight.selectFreightListByMap", rs.getP_curPage(), rs.getP_pageSize());
		return "list";
	}
	
	
	/**
	 * 添加运费页面
	 */
	public String freight(){
		return "add";
	}
	
	/**
	 * 添加运费
	 * @throws Exception 
	 */
	public void addFreight() throws Exception{
		Long n = -1L;
		String freight = this.getRequest().getParameter("freight");
		String remark = this.getRequest().getParameter("remark");
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  getResponse().getWriter();
	    try {
		    TFreight f = new TFreight();
		    f.setCreateDt(new Date());
		    f.setFreight(new BigDecimal(freight));
		    f.setRemark(remark);
		    f.setIsDelete(0);
			n = tfreightmanager.insertSelective(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(n);
		out.close();
	}
	
	/**
	 * 修改运费页面
	 * @throws Exception 
	 */
	public String upFreight() throws Exception{
		String id = this.getRequest().getParameter("id");
		TFreight freight = tfreightmanager.selectByPrimaryKey(new Long(id));
	 	this.getRequest().setAttribute("freight", freight);
		return "upfreight";
	}
	
	
	/**
	 * 修改页面
	 * @throws Exception 
	 */
	public void updateFreight() throws Exception{
		int n = -1;
		String freight = this.getRequest().getParameter("freight");
		String remark = this.getRequest().getParameter("remark");
		String id = this.getRequest().getParameter("id");
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  getResponse().getWriter();
	    try {
		    TFreight f = new TFreight();
		    f.setId(new Long(id));
		    f.setFreight(new BigDecimal(freight));
		    f.setRemark(remark);
			n = tfreightmanager.updateByPrimaryKeySelective(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(n);
		out.close();
	}
	
	
	/**
	 * 商品运费列表
	 */
	public String goodsFreight(){
		Map<String,String> paramMap = new HashMap<String,String>();
		if(null != goodsno && !("").equals(goodsno.trim())){
			paramMap.put("goodsno", goodsno);
		}
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "t_freight.selectGoodFreightCountByMap", "t_freight.selectGoodFreightListByMap", rs.getP_curPage(), rs.getP_pageSize());
		return "goodFreightList";
	}
	
	
	
	/**
	 * 修改商品运费页面
	 * @throws Exception 
	 */
	public String goodFreightPage() throws Exception{
		String id = this.getRequest().getParameter("id");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("id", id);
		Map map = (Map) opensqlmanage.selectForObjectByMap(paramMap, "t_freight.selectGoodFreightIdByMap");
	 	this.getRequest().setAttribute("map", map);
	 	TFreightExample example = new TFreightExample();
	 	List list = tfreightmanager.selectByExample(example);
	 	this.getRequest().setAttribute("list", list);
		return "goodFreightPage";
	}
	
	/**
	 * 修改商品运费
	 * @throws Exception 
	 */
	public void updateGoodFreight() throws Exception{
		String gid = this.getRequest().getParameter("gid");
		String fid = this.getRequest().getParameter("fid");
		int n = -1;
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
	    PrintWriter out =  getResponse().getWriter();
	    try {
	    	TGoodFreight goodFreiht = new TGoodFreight();
	    	goodFreiht.setId(new Long(gid));
	    	goodFreiht.setFreightId(new Long(fid));
			n = tgoodfreightmanager.updateByPrimaryKeySelective(goodFreiht);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(n);
		out.close();
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

	public TGoodFreightManager getTgoodfreightmanager() {
		return tgoodfreightmanager;
	}

	public void setTgoodfreightmanager(TGoodFreightManager tgoodfreightmanager) {
		this.tgoodfreightmanager = tgoodfreightmanager;
	}

	public TFreightManager getTfreightmanager() {
		return tfreightmanager;
	}

	public void setTfreightmanager(TFreightManager tfreightmanager) {
		this.tfreightmanager = tfreightmanager;
	}

	public String getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

}
