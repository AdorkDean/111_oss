package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.UtilStatic;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderArticleGoodsManager;
import com.rc.portal.service.TLeaderArticleManager;
import com.rc.portal.vo.TLeaderArticle;
import com.rc.portal.vo.TLeaderArticleGoods;
import com.rc.portal.vo.TLeaderArticleGoodsExample;
import com.rc.portal.webapp.util.PageResult;

public class NewArticleLeaderAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private TLeaderArticleManager tleaderarticlemanager;
	private TLeaderArticleGoodsManager tleaderarticlegoodsmanager;
	
	
	public String geiArticleList(){
		Map map=new HashMap();
		if(model.getCheckStatus()!=null&&model.getCheckStatus()!=-1){
			map.put("checkStatus", model.getCheckStatus());
		}
		if(model.getLeaderName()!=null&&!"".equals(model.getLeaderName().trim())){
			map.put("leaderName", model.getLeaderName().trim());
		}
		if(model.getLeaderMobile()!=null&&!"".equals(model.getLeaderMobile().trim())){
			map.put("leaderMobile", model.getLeaderMobile().trim());
		}
		if(model.getLeaderCode()!=null&&!"".equals(model.getLeaderCode().trim())){
			map.put("leaderCode", model.getLeaderCode().trim());
		}
		
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_leader_article.selectArticleCount", "t_leader_article.selectArticleListByPage", rs.getP_curPage(), rs.getP_pageSize());
		
		return "geiArticleList";
	}
	
	public String articleDetail() throws Exception{
		TLeaderArticle article=new TLeaderArticle();
		String content="暂无文章内容";
		List<TLeaderArticleGoods> goodsList=new ArrayList<TLeaderArticleGoods>();
		if(model.getId()!=null&&!"".equals(model.getId().trim())){
		article = tleaderarticlemanager.selectByPrimaryKey(new Long(model.getId().trim()));
		if(article!=null&&article.getContent()!=null){
			content=new String(article.getContent(),"UTF-8");;
		}
		if(article!=null&&article.getId()!=null){
			TLeaderArticleGoodsExample ex=new TLeaderArticleGoodsExample();
			ex.createCriteria().andArticleIdEqualTo(article.getId());
			goodsList = tleaderarticlegoodsmanager.selectByExample(ex);
		}
		}
		this.getRequest().setAttribute("article", article);
		this.getRequest().setAttribute("goodsList", goodsList);
		this.getRequest().setAttribute("content", content);
		return "articleDetail";
	}
	
	
	@SuppressWarnings("unchecked")
	public void checkAricle() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		Integer flag=0;
		TLeaderArticle article=new TLeaderArticle();
		article.setId(new Long(model.getId().trim()));
		if(model.getCheckStatus()==1){
			article.setAuditStatus(2);//审核通过
		}else{
			article.setAuditStatus(3);//审核不通过
		}
		article.setAuditDate(new Date());
		if(model.getCheck_idear()!=null&&!"".equals(model.getCheck_idear().trim())){
			article.setAuditOpinion(model.getCheck_idear().trim());	
		}
		flag = tleaderarticlemanager.updateByPrimaryKeySelective(article);
		if(flag > 0)
		{
			//发布文章
			Long articleId = article.getId();
			TLeaderArticle tla = tleaderarticlemanager.selectByPrimaryKey(articleId);
			String content = "";
			byte[] bContent = tla.getContent();
			if(bContent != null)
			{
				content = new String(bContent);
			}
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("articleid", articleId);
			List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_leader_article.selecctTuiJianGoodsByArticleId_1");
			Map<Object, Object> root = new HashMap<Object, Object>();
			root.put("tla", tla);
			root.put("acontent", content);
			root.put("datas", datas);
			String base = getRequest().getSession().getServletContext().getRealPath("/");
			String staticPageUrl = base + "/static/leader/user/"+articleId+".html";
			String fileName = "view3.ftl";
			UtilStatic.doTemplatePage(root,base + "/WEB-INF/pages/pname/leader_article/",fileName,staticPageUrl);
			tla.setStatus(1);
			tleaderarticlemanager.updateByPrimaryKeySelective(tla);
		}
		out.write(flag.toString());
		out.close();
	}
	
	/**
	 * 发布文章
	 */
	@SuppressWarnings("unchecked")
	public void publishArticle() throws Exception
	{
		Long articleId = Long.parseLong(getRequest().getParameter("articleId"));
		TLeaderArticle tla = tleaderarticlemanager.selectByPrimaryKey(articleId);
		String content = "";
		byte[] bContent = tla.getContent();
		if(bContent != null)
		{
			content = new String(bContent);
		}
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("articleid", articleId);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_leader_article.selecctTuiJianGoodsByArticleId_1");
		Map<Object, Object> root = new HashMap<Object, Object>();
		root.put("tla", tla);
		root.put("acontent", content);
		root.put("datas", datas);
		String base = getRequest().getSession().getServletContext().getRealPath("/");
		String staticPageUrl = base + "/static/leader/user/"+articleId+".html";
		//String visitUrl = getRequest().getContextPath() + "/static/leader/user/"+articleId+".html";
		String fileName = "view3.ftl";
		boolean f = UtilStatic.doTemplatePage(root,base + "/WEB-INF/pages/pname/leader_article/",fileName,staticPageUrl);
		tla.setStatus(1);
		tleaderarticlemanager.updateByPrimaryKeySelective(tla);
		writeObjectToResponse(f, ContentType.application_json);
	}
	
	
	
	public class Condition {
		private Integer checkStatus;
		private String leaderName;
		private String leaderMobile;
		private String leaderCode;
		private String id;
		private String check_idear;
		
		
		
		
		
		
		public String getCheck_idear() {
			return check_idear;
		}
		public void setCheck_idear(String check_idear) {
			this.check_idear = check_idear;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLeaderName() {
			return leaderName;
		}
		public void setLeaderName(String leaderName) {
			this.leaderName = leaderName;
		}
		public String getLeaderMobile() {
			return leaderMobile;
		}
		public void setLeaderMobile(String leaderMobile) {
			this.leaderMobile = leaderMobile;
		}
		public String getLeaderCode() {
			return leaderCode;
		}
		public void setLeaderCode(String leaderCode) {
			this.leaderCode = leaderCode;
		}
		public Integer getCheckStatus() {
			return checkStatus;
		}
		public void setCheckStatus(Integer checkStatus) {
			this.checkStatus = checkStatus;
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

	public TLeaderArticleManager getTleaderarticlemanager() {
		return tleaderarticlemanager;
	}

	public void setTleaderarticlemanager(TLeaderArticleManager tleaderarticlemanager) {
		this.tleaderarticlemanager = tleaderarticlemanager;
	}

	public TLeaderArticleGoodsManager getTleaderarticlegoodsmanager() {
		return tleaderarticlegoodsmanager;
	}

	public void setTleaderarticlegoodsmanager(
			TLeaderArticleGoodsManager tleaderarticlegoodsmanager) {
		this.tleaderarticlegoodsmanager = tleaderarticlegoodsmanager;
	}


}
