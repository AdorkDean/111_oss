package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.sf.json.JSONArray;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TPharmacistManager;
import com.rc.portal.vo.TPharmacist;
import com.rc.portal.webapp.model.TPharmacistBean;
import com.rc.portal.webapp.util.PageResult;

/**
 * 药师点评管理
 * @author LGP
 * @date 2015年8月21日
 */
@SuppressWarnings("unchecked")
public class PharmacistCommentAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private TPharmacistManager tpharmacistmanager;
	private String goodsno;
	private String searchType;
	
	/**
	 * 后台商品列表点评商品
	 */
	public void phamnpro() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		String goodsid = this.getRequest().getParameter("goodsid");
		if(goodsid != null && !goodsid.trim().equals(""))
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("goodsid", goodsid);
			List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "t_pharmacist.selectPharmacistCommentListMap");
			//药师已点评过该商品
			if(datas != null && datas.size() > 0)
			{
				JSONArray jsons = JSONArray.fromObject(datas);
				reJSON(jsons.toString(), this.getRequest(), this.getResponse());
			}
		}
	}
	
	/**
	 * 后台商品列表点评中的医师列表
	 */
	public void phclist() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "t_pharmacist.selectphysicianListMap");
		if(datas != null && datas.size() > 0)
		{
			JSONArray jsons = JSONArray.fromObject(datas);
			reJSON(jsons.toString(), this.getRequest(), this.getResponse());
		}
	}
	
	/**
	 * 医师点评内容持久化
	 */
	public void savepharmacistcomment() throws NumberFormatException, SQLException, IOException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		//商品ID
		String goodsid = this.getRequest().getParameter("goodsid");
		//中间表主键ID
		String pha_phy_good_id = this.getRequest().getParameter("pha_phy_good_id");
		//点评内容
		String content = java.net.URLDecoder.decode(this.getRequest().getParameter("content"), "utf-8");
		//是否显示
		String isView = this.getRequest().getParameter("isView");
		//药师ID
		String pharmacistId = this.getRequest().getParameter("pharmacistId");
		TPharmacist tct = null;
		//已点评过某商品
		if(pha_phy_good_id != null && !pha_phy_good_id.trim().equals(""))
		{
			tct = tpharmacistmanager.selectByPrimaryKey(Long.parseLong(pha_phy_good_id));
			tct.setGoodsid(Long.parseLong(goodsid));
			tct.setIsView(Integer.parseInt(isView));
			tct.setPharmacistId(Long.parseLong(pharmacistId));
			tct.setCommentContent(content);
			tpharmacistmanager.updateByPrimaryKey(tct);
			out.println(0);
		}
		//未点评过某商品
		else
		{
			tct = new TPharmacist();
			tct.setGoodsid(Long.parseLong(goodsid));
			tct.setIsView(Integer.parseInt(isView));
			tct.setPharmacistId(Long.parseLong(pharmacistId));
			tct.setCommentContent(content);
			tct.setCommentTime(new Date());
			tpharmacistmanager.insert(tct);
			//更新商品属性表信息
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("goodsid", goodsid);
			opensqlmanage.updateByMap(map, "t_goods_property.updateEvaluateByMap");
			out.println(1);
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 药师点评列表
	 */
	public String pharmacistlist()
	{
		Map<String, Object> mapData = new HashMap<String, Object>();
		if(StringUtils.hasText(goodsno))
		{
			if(goodsno!=null&&!"".equals(goodsno.trim()))
			{
				mapData.put("goodsno", goodsno.trim());
			}
		}
		pw = opensqlmanage.selectForPageByMap_drug(mapData, "t_pharmacist.selectCount", "t_pharmacist.selectDatasByPage", rs.getP_curPage(), rs.getP_pageSize());
		//Packaging data again
		List<Map<String, Object>> datas = pw.getResult();
		List<TPharmacistBean> tcbs = new ArrayList<TPharmacistBean>();
		for(Map<String, Object> m : datas)
		{
			TPharmacistBean tb = new TPharmacistBean(String.valueOf(m.get("id")), String.valueOf(m.get("phyheaderurl")), String.valueOf(m.get("realname")), String.valueOf(m.get("content")), String.valueOf(m.get("ctime")), String.valueOf(m.get("isview")), String.valueOf(m.get("goodsid")), String.valueOf(m.get("shortname")),String.valueOf(m.get("sn")),String.valueOf(m.get("is_pc")),String.valueOf(m.get("is_wap")),String.valueOf(m.get("is_app")),String.valueOf(m.get("goodsno")));
			tcbs.add(tb);
		}
		pw.setResult(tcbs);
		return "list";
	}
	
	
	/**
	 * 删除医师点评信息数据
	 */
	public void delete() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		String strId = this.getRequest().getParameter("id");
		if(strId != null && !strId.trim().equals(""))
		{
			int dbc = tpharmacistmanager.deleteByPrimaryKey(Long.parseLong(strId));
			if(dbc > 0)
			{
				out.print(0);
			}
			else
			{
				out.print(1);
			}
		}
		else
		{
			out.print(2);
		}
		out.flush();
		out.close();
	}
	
	@Override
	public Object getModel() 
	{
		return null;
	}
	@Override
	public void setModel(Object o) 
	{
	}
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
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
	
	/**
	 * 给客户端返回JSON数据
	 */
	public void reJSON(String txt,ServletRequest request,ServletResponse response) throws IOException
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = response.getWriter();		
		out.print(txt);
		out.flush();
		out.close();
	}

	public TPharmacistManager getTpharmacistmanager()
	{
		return tpharmacistmanager;
	}

	public void setTpharmacistmanager(TPharmacistManager tpharmacistmanager)
	{
		this.tpharmacistmanager = tpharmacistmanager;
	}


	public String getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	public String getSearchType()
	{
		return searchType;
	}

	public void setSearchType(String searchType)
	{
		this.searchType = searchType;
	}

	
}
