package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.CPositionListManager;
import com.rc.portal.service.CPositionTypeManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.vo.CPositionList;
import com.rc.portal.vo.CPositionType;
import com.rc.portal.webapp.model.Rule;
import com.rc.portal.webapp.util.PageResult;

public class TemplateManageAcation extends BaseAction{
	private static final long serialVersionUID = 1L;
	private OpenSqlManage opensqlmanage;
	private CPositionListManager cpositionlistmanager;
	private CPositionTypeManager cpositiontypemanager;
	private CPositionList cpositionlist ;
	private CPositionType cPositionType;
	private PageWraper pw = new PageWraper();
	private PageWraper pwPosType = new PageWraper();
	private PageResult rs = new PageResult();
	private Rule rule;//模板规则
	Condition model = new Condition();
	private int gid;
	/**
	 * 跳转到编辑页
	 */
	public String gotoPage() throws SQLException
	{
		String strId=this.getRequest().getParameter("id");
		if(null != strId)
		{
			Integer positionListId = Integer.valueOf(strId);
			cpositionlist = cpositionlistmanager.selectByPrimaryKey(positionListId);
			if(null!=cpositionlist && null!=cpositionlist.getRule() && !"".equals(cpositionlist.getRule().trim()))
			{
				JSONObject jsonObj = JSONObject.fromObject(cpositionlist.getRule());
				rule =(Rule) JSONObject.toBean(jsonObj,Rule.class);
			}
		}
		//查询位置类别集合
		pwPosType = opensqlmanage.selectForPageByMap_drug(new HashMap<String, Object>(), "c_position_type.ibatorgenerated_countByExample", "c_position_type.selectByExampleByPage", rs.getP_curPage(), 50);
		return "editPage";
	}
	/**
	 * 复制模板
	 */
	public void copyTemplates() throws SQLException
	{
		String strId = this.getRequest().getParameter("id");
		Integer result = 0;
		if(StringUtils.hasText(strId))
		{
			Integer positionListId = Integer.valueOf(strId);
			cpositionlist = cpositionlistmanager.selectByPrimaryKey(positionListId);
			CPositionList cp = new CPositionList();
			cp.setName(cpositionlist.getName()+"副本");
			cp.setmPath(cpositionlist.getmPath());
			cp.setmName(cpositionlist.getmName());
			cp.setOutPath(cpositionlist.getOutPath());
			cp.setCreateDt(new Date());
			cp.setRule(cpositionlist.getRule());
			cp.setIsrelease(cpositionlist.getIsrelease());
			cp.setGenre(cpositionlist.getGenre());
			result = cpositionlistmanager.insertSelective(cp);
		}
		writeObjectToResponse(result, ContentType.application_json);
	}
	/**
	 * 添加或修改JPostionList表
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void managePostionList() throws SQLException, IOException{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		int flag=-1;
		JSON jsonRule=JSONSerializer.toJSON(rule);
		cpositionlist.setRule(jsonRule.toString());

			if(null==cpositionlist.getId()){
				//添加
				cpositionlist.setCreateDt(new Date());
				cpositionlistmanager.insertSelective(cpositionlist);
				flag=1;
			}else{
				//修改
				cpositionlistmanager.updateByPrimaryKeySelective(cpositionlist);
				flag=2;
			}
			out.print(flag);
			out.close();
	}
	
	/**
	 * 模板列表信息
	 */
	public String selectPostionList() throws NumberFormatException, SQLException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != model)
		{
			String positionName = model.getPositionName();
			Integer genre = model.getGenre();
			if(null != positionName && !"".equals(positionName))
			{
				map.put("positionName", positionName);
			}
			if(null != genre && genre != -1 && genre != 0)
			{
				map.put("genre", genre);
				gid = genre;
			}
			else
			{
				gid = 0;
			}
		}
		pw = opensqlmanage.selectForPageByMap_drug(map, "c_position_list.count_selectByname", "c_position_list.selectByname", rs.getP_curPage(), rs.getP_pageSize());
		pwPosType = opensqlmanage.selectForPageByMap_drug(new HashMap<String, Object>(), "c_position_type.ibatorgenerated_countByExample", "c_position_type.selectByExampleByPage", 1, 50);
		return "postionlist";
	}
	
	/**
	 * 删除模板
	 */
	public void deleteTemplate() throws IOException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		String strId = this.getRequest().getParameter("id");
		if(null != strId && !strId.trim().equals(""))
		{
			//判断该位置管理下是否存在内容
			Map<String,Object> querMap = new HashMap<String,Object>();
			querMap.put("channel",strId);
			pw = opensqlmanage.selectForPageByMap_drug(querMap, "c_position.countPositionInfo", "c_position.queryPositionInfo", 1, 1);
			if(pw.getResult() != null && pw.getResult().size() > 0)
			{
				out.print(0);
			}
			else
			{
				try
				{
					cpositionlistmanager.deleteTemplate(Integer.valueOf(strId));
					out.print(1);
				} 
				catch (Exception e)
				{
					out.print(2);
					e.printStackTrace();
				} 
			}
		}
		else
		{
			out.print(3);
		}
		out.flush();
		out.close();
	}
	
	@Override
	public Object getModel() {
		return model;
	}

	@Override
	public void setModel(Object o) {
		this.model=(Condition) o;
	}
	public class Condition{
		private String positionName;
		public String getPositionName() {
			return positionName;
		}
		public void setPositionName(String positionName) {
			this.positionName = positionName;
		}
		private Integer genre;

		public Integer getGenre() {
			return genre;
		}
		public void setGenre(Integer genre) {
			this.genre = genre;
		}
	}
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}


	public CPositionListManager getCpositionlistmanager() {
		return cpositionlistmanager;
	}

	public void setCpositionlistmanager(CPositionListManager cpositionlistmanager) {
		this.cpositionlistmanager = cpositionlistmanager;
	}

	public CPositionList getCpositionlist() {
		return cpositionlist;
	}

	public void setCpositionlist(CPositionList cpositionlist) {
		this.cpositionlist = cpositionlist;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
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

	public CPositionType getcPositionType()
	{
		return cPositionType;
	}

	public void setcPositionType(CPositionType cPositionType)
	{
		this.cPositionType = cPositionType;
	}

	public PageWraper getPwPosType()
	{
		return pwPosType;
	}

	public void setPwPosType(PageWraper pwPosType)
	{
		this.pwPosType = pwPosType;
	}

	public CPositionTypeManager getCpositiontypemanager()
	{
		return cpositiontypemanager;
	}

	public void setCpositiontypemanager(CPositionTypeManager cpositiontypemanager)
	{
		this.cpositiontypemanager = cpositiontypemanager;
	}

	public int getGid()
	{
		return gid;
	}

	public void setGid(int gid)
	{
		this.gid = gid;
	}
	
}
