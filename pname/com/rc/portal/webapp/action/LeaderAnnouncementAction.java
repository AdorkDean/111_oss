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
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLxAnnouncementManager;
import com.rc.portal.vo.TLxAnnouncement;
import com.rc.portal.vo.TLxAnnouncementExample;
import com.rc.portal.webapp.util.PageResult;
/*
 * 领秀通告
 */
public class LeaderAnnouncementAction extends BaseAction{
	private static final long serialVersionUID = 13244111111L;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private TLxAnnouncement tlxAnnouncement;
	private TLxAnnouncementManager tlxannouncementmanager;
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

	public TLxAnnouncement getTlxAnnouncement() {
		return tlxAnnouncement;
	}

	public void setTlxAnnouncement(TLxAnnouncement tlxAnnouncement) {
		this.tlxAnnouncement = tlxAnnouncement;
	}

	public TLxAnnouncementManager getTlxannouncementmanager() {
		return tlxannouncementmanager;
	}

	public void setTlxannouncementmanager(
			TLxAnnouncementManager tlxannouncementmanager) {
		this.tlxannouncementmanager = tlxannouncementmanager;
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
		tlxAnnouncement.setCreateDt(new Date());
		tlxAnnouncement.setIsDelete(0);//删除状态  0 未删除  1 已删除
		tlxAnnouncement.setStatus(0);//发布状态 0 未发布 1 已发布
		tlxannouncementmanager.insertSelective(tlxAnnouncement);
		return "save";
	}
	/**
	 * 列表
	 */
	public String list() throws Exception{
		Map<String, String> map =new HashMap<String, String>();
		rs.setP_pageSize(20);
	    pw = opensqlmanage.selectForPageByMap_drug(map, "t_lx_announcement.selectAnnouncementCount", "t_lx_announcement.selectAnnouncementRecord", rs.getP_curPage(), rs.getP_pageSize());
		return "list";
	}
	/**
	 * 修改
	 */
	public String update() throws Exception{
		tlxAnnouncement.setStatus(0);
		tlxannouncementmanager.updateByPrimaryKeySelective(tlxAnnouncement);
		return "save";
	}
	/**
	 * 页面跳转
	 */
	public String view() throws Exception{
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			Map<String, String> map =new HashMap<String, String>();
			map.put("id", id);
			tlxAnnouncement = tlxannouncementmanager.selectByPrimaryKey(Long.valueOf(id));
		}
		return "view";
	}
	/*
	 * 删除
	 */
	public void delete() throws Exception{
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			long n = tlxannouncementmanager.deleteByPrimaryKey(Long.valueOf(id));
			out.print(n);
			out.close();
		}
	}
	/*
	 * 发布
	 */
	public void release() throws Exception{
		String ids = getRequest().getParameter("id");
		if(StringUtils.hasText(ids)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			String[] id = ids.split(",");
			List<Long> list = new ArrayList<Long>();
			for (String idstr : id) {
				list.add(Long.valueOf(idstr));
			}
			TLxAnnouncement record = new TLxAnnouncement();
			record.setStatus(1);//已发布
			TLxAnnouncementExample example = new TLxAnnouncementExample();
			example.createCriteria().andIdIn(list);
			long n = tlxannouncementmanager.updateByExampleSelective(record, example);
			out.print(n);
			out.close();
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

}
