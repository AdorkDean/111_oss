package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.StringUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLxContentManager;
import com.rc.portal.vo.TLxContent;
import com.rc.portal.vo.TLxContentExample;
import com.rc.portal.webapp.util.PageResult;
/**
 * 领袖内容管理
 *
 */
public class LeaderContentAction extends BaseAction{
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private TLxContent obj;
	private TLxContentManager tlxcontentmanager;
	private String tile;
	/**
	 * 修改
	 */
	public String update() throws Exception{
		obj.setStatus(0);
		tlxcontentmanager.updateByPrimaryKeySelective(obj);
		return "save";
	}
	/**
	 * 视图跳转
	 */
	public String view() throws Exception{
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			Map<String, String> map =new HashMap<String, String>();
			map.put("id", id);
			obj = tlxcontentmanager.selectByPrimaryKey(Integer.parseInt(id));
		}
		return "view";
	}
	/**
	 * 分页查询
	 */
	public String list() throws Exception{
		Map<String, String> map =new HashMap<String, String>();
		if(!StringUtil.isEmpty(tile)){
			map.put("tile", tile);
		}
		rs.setP_pageSize(20);
	    pw = opensqlmanage.selectForPageByMap_drug(map, "t_lx_content.query_record_count", "t_lx_content.query_record", rs.getP_curPage(), rs.getP_pageSize());
		return "list";
	}
	/**
	 * 添加跳转
	 */
	public String add() throws Exception{
		return "add";
	}
	/**
	 * 添加保存
	 */
	public String save() throws Exception{
		obj.setCreateDt(new Date());
		obj.setStatus(0);
		tlxcontentmanager.insertSelective(obj);
		return "save";
	}
	/**
	 * ajax删除
	 */
	public void ajaxdel() throws Exception{
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			long n = tlxcontentmanager.deleteByPrimaryKey(Integer.parseInt(id));
			out.print(n);
			out.close();
		}
	}
	/**
	 * ajax发布
	 */
	public void ajaxfabu() throws Exception{
		String ids = getRequest().getParameter("id");
		if(StringUtils.hasText(ids)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			String[] id = ids.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (String idstr : id) {
				list.add(Integer.parseInt(idstr));
			}
			TLxContent record = new TLxContent();
			record.setStatus(1);
			TLxContentExample example = new TLxContentExample();
			example.createCriteria().andIdIn(list);
			long n = tlxcontentmanager.updateByExampleSelective(record, example);
			out.print(n);
			out.close();
		}
	}
	public String getTile() {
		return tile;
	}
	public void setTile(String tile) {
		this.tile = tile;
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
	
	public TLxContent getObj() {
		return obj;
	}
	public void setObj(TLxContent obj) {
		this.obj = obj;
	}
	public TLxContentManager getTlxcontentmanager() {
		return tlxcontentmanager;
	}
	public void setTlxcontentmanager(TLxContentManager tlxcontentmanager) {
		this.tlxcontentmanager = tlxcontentmanager;
	}
	@Override
	public Object getModel() {
		return null;
	}
	@Override
	public void setModel(Object o) {
		
	}

}
