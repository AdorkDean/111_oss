package com.rc.portal.webapp.action;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.UtilStatic;
import com.rc.commons.util.InfoUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLxGoodsArticleBrokerageManager;
import com.rc.portal.service.TLxGoodsArticleManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TLxGoodsArticle;
import com.rc.portal.vo.TLxGoodsArticleBrokerage;
import com.rc.portal.webapp.util.ConnectionUtil;
import com.rc.portal.webapp.util.Des;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.ParametersCommon;

/**
 * 微信文章管理
 * @author LGP&WM
 * @date 2015年11月7日
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LeaderArticleManageAction extends BaseAction
{
	private static final long serialVersionUID = 45345345345341L;
	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private final Long MAXSIZE = 1024*1024*2L;
	Condition model = new Condition();
	private final String diskPath = InfoUtil.getInstance().getInfo("images", "images.leader.path");
    private String title ;
    private String sn;
	private String start_date;
	private String end_date;
	//健康领袖发送通知统计
	int sucnum = 0;
	int failnum = 0;
	
	/**推荐文章商品佣金组合表*/
	private TLxGoodsArticleBrokerage tlxgoodsarticlebrokerage;
	private TLxGoodsArticleBrokerageManager tlxgoodsarticlebrokeragemanager;
	
    /**推荐文章商品表*/
	private TLxGoodsArticle tlxgoodsarticle;
	private TLxGoodsArticleManager tlxgoodsarticlemanager;
	
	//获取系统参数
	private TSysParameterManager tsysparametermanager;
	
	public TSysParameterManager getTsysparametermanager() 
	{
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) 
	{
		this.tsysparametermanager = tsysparametermanager;
	}
	public Long getMAXSIZE()
	{
		return MAXSIZE;
	}
	public String getDiskPath()
	{
		return diskPath;
	}
	public TLxGoodsArticleBrokerage getTlxgoodsarticlebrokerage()
	{
		return tlxgoodsarticlebrokerage;
	}
	public void setTlxgoodsarticlebrokerage(
			TLxGoodsArticleBrokerage tlxgoodsarticlebrokerage)
	{
		this.tlxgoodsarticlebrokerage = tlxgoodsarticlebrokerage;
	}
	
	public TLxGoodsArticleBrokerageManager getTlxgoodsarticlebrokeragemanager()
	{
		return tlxgoodsarticlebrokeragemanager;
	}
	public void setTlxgoodsarticlebrokeragemanager(
			TLxGoodsArticleBrokerageManager tlxgoodsarticlebrokeragemanager)
	{
		this.tlxgoodsarticlebrokeragemanager = tlxgoodsarticlebrokeragemanager;
	}
	public void setModel(Condition model)
	{
		this.model = model;
	}
	public TLxGoodsArticle getTlxgoodsarticle()
	{
		return tlxgoodsarticle;
	}
	public void setTlxgoodsarticle(TLxGoodsArticle tlxgoodsarticle)
	{
		this.tlxgoodsarticle = tlxgoodsarticle;
	}
	public TLxGoodsArticleManager getTlxgoodsarticlemanager()
	{
		return tlxgoodsarticlemanager;
	}
	public void setTlxgoodsarticlemanager(
			TLxGoodsArticleManager tlxgoodsarticlemanager)
	{
		this.tlxgoodsarticlemanager = tlxgoodsarticlemanager;
	}
	
	public OpenSqlManage getOpensqlmanage()
	{
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage)
	{
		this.opensqlmanage = opensqlmanage;
	}
	public PageWraper getPw()
	{
		return pw;
	}
	public void setPw(PageWraper pw)
	{
		this.pw = pw;
	}
	public PageResult getRs()
	{
		return rs;
	}
	public void setRs(PageResult rs)
	{
		this.rs = rs;
	} 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
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
	/**
	 * 根据商品海典编号查找商品
	 */
	public void checkgoodsexistbygoodsno()
	{
		String param = getRequest().getParameter("param");
		if(StringUtils.hasText(param))
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsno", param);
			List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "t_goods.selectGoodsByHaidianCode");
			if(datas != null && datas.size() > 0)
			{
				writeObjectToResponse(1, ContentType.application_json);
			}
			else
			{
				writeObjectToResponse(2, ContentType.application_json);
			}
		}
		else
		{
			writeObjectToResponse(3, ContentType.application_json);
		}
	}

	/**
	 * 保存数据
	 */
	public void save() throws Exception
	{
		//文章标题
		String title = getRequest().getParameter("title");
		//分享语
		String shareTitle = getRequest().getParameter("shareTitle");
		//文章权重
		String weight_str = getRequest().getParameter("weight2");
		int weight2 = 0;
		if(StringUtils.hasText(weight_str))
		{
			weight2 = Integer.parseInt(weight_str);
		}
		//分享图片链接
		String shareImageUrl = getRequest().getParameter("shareImageUrl");
		//文章内容
		String content = getRequest().getParameter("content");
		content = new Des().strDec(content, "1", "2", "3");
		
		TLxGoodsArticle tla = new TLxGoodsArticle();
		tla.setTitle(title);
		tla.setShareTitle(shareTitle);
		tla.setShareImageUrl(shareImageUrl);
		tla.setContent(content.getBytes());
		tla.setCreateDt(new Date());
		tla.setStatus(0);
		tla.setWeight(weight2);
		tla.setIsDelete(0);
		tla.setFormatType(1);
		tlxgoodsarticlemanager.insert(tla);
		
		//文章ID
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("shareimgurl", shareImageUrl);
		Map<String, Object> idmap = (Map<String, Object>) opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selectIDByTitle").get(0);
		String goodsAticleId = idmap.get("id").toString();
		
		/**保存商品-文章-佣金中间表数据*/
		String params = getRequest().getParameter("gs");
		JSONArray jsonarray = JSONArray.fromObject(params);
		List<Map<String,Object>> paramsarray =  (List<Map<String, Object>>) JSONArray.toCollection(jsonarray, Map.class);
		if(paramsarray != null && paramsarray.size() > 0)
		{
			for(Map<String, Object> m : paramsarray)
			{
				//海典编号
				String hcode = String.valueOf(m.get("hcode"));
				//权重
				String weight = String.valueOf(m.get("weight"));
				//商品图片
				String goodsImgUrl = String.valueOf(m.get("goodsImgUrl"));
				
				//商品ID
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("goodsno", hcode);
				Map<String, Object> _idmap = (Map<String, Object>) opensqlmanage.selectForListByMap(param, "t_lx_goods_article.selectGoodsIdByHaiDianCode").get(0);
				Long goodsId = Long.parseLong(_idmap.get("id").toString());
				
				//佣金ID
				Long yongJinId = null;
				Map<String, Object> yjp = new HashMap<String, Object>();
				yjp.put("gid", goodsId);
				List<Map<String, Object>> idlists = opensqlmanage.selectForListByMap(yjp, "t_lx_goods_article.selectYongJinIDByGoodID");
				if(idlists != null && idlists.size() > 0)
				{
					Map<String, Object> $idmap = (Map<String, Object>) idlists.get(0);
					yongJinId = Long.parseLong($idmap.get("id").toString());
				}
				
				//持久化推荐商品
				TLxGoodsArticleBrokerage tab = new TLxGoodsArticleBrokerage();
				tab.setGoodsId(goodsId);
				tab.setBrokerageId(yongJinId);
				tab.setArticleId(Long.parseLong(goodsAticleId));
				tab.setWeight(Integer.parseInt(weight));
				tab.setGoodImageUrl(goodsImgUrl);
				tlxgoodsarticlebrokeragemanager.insert(tab);
			}
		}
	}
	
	/**
	 * 更新数据
	 */
	public void update() throws Exception
	{
		//文章ID
		Long articleId = Long.parseLong(getRequest().getParameter("articleId"));
		
		//文章标题
		String title = getRequest().getParameter("title");
		//分享语
		String shareTitle = getRequest().getParameter("shareTitle");
		//文章权重
		String weight_str = getRequest().getParameter("weight2");
		int weight2 = 0;
		if(StringUtils.hasText(weight_str))
		{
			weight2 = Integer.parseInt(weight_str);
		}
		//分享图片链接
		String shareImageUrl = getRequest().getParameter("shareImageUrl");
		//文章内容
		String content = getRequest().getParameter("content");
		content = new Des().strDec(content, "1", "2", "3");
		
		TLxGoodsArticle tla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
		tla.setTitle(title);
		tla.setShareTitle(shareTitle);
		tla.setShareImageUrl(shareImageUrl);
		tla.setContent(content.getBytes());
		tla.setUpdateDt(new Date());
		tla.setCreateDt(null);
		tla.setStatus(0);
		tla.setWeight(weight2);
		tla.setIsDelete(0);
		tla.setFormatType(1);
		tlxgoodsarticlemanager.updateByPrimaryKeySelective(tla);
		
		//更新商品-文章-佣金中间表数据
		String params = getRequest().getParameter("gs");
		JSONArray jsonarray = JSONArray.fromObject(params);
		List<Map<String,Object>> paramsarray =  (List<Map<String, Object>>) JSONArray.toCollection(jsonarray, Map.class);
		if(paramsarray != null && paramsarray.size() > 0)
		{
			for(Map<String, Object> m : paramsarray)
			{
				Long middleId = Long.parseLong(String.valueOf(m.get("id")));
				//海典编号
				String hcode = String.valueOf(m.get("hcode"));
				//权重
				String weight = String.valueOf(m.get("weight"));
				//商品图片
				String goodsImgUrl = String.valueOf(m.get("goodsImgUrl"));
				//商品ID
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("goodsno", hcode);
				Map<String, Object> _idmap = (Map<String, Object>) opensqlmanage.selectForListByMap(param, "t_lx_goods_article.selectGoodsIdByHaiDianCode").get(0);
				Long goodsId = Long.parseLong(_idmap.get("id").toString());
				
				//佣金ID
				Long yongJinId = null;
				Map<String, Object> yjp = new HashMap<String, Object>();
				yjp.put("gid", goodsId);
				List<Map<String, Object>> idlists = opensqlmanage.selectForListByMap(yjp, "t_lx_goods_article.selectYongJinIDByGoodID");
				if(idlists != null && idlists.size() > 0)
				{
					Map<String, Object> $idmap = (Map<String, Object>) idlists.get(0);
					yongJinId = Long.parseLong($idmap.get("id").toString());
				}
				TLxGoodsArticleBrokerage tab = null;
				//更新/保存推荐商品
				if(middleId != 0)
				{
					tab = tlxgoodsarticlebrokeragemanager.selectByPrimaryKey(middleId);
					tab.setGoodsId(goodsId);
					tab.setBrokerageId(yongJinId);
					tab.setArticleId(articleId);
					tab.setWeight(Integer.parseInt(weight));
					tab.setGoodImageUrl(goodsImgUrl);
					tlxgoodsarticlebrokeragemanager.updateByPrimaryKey(tab);
				}
				else
				{
					tab = new TLxGoodsArticleBrokerage();
					tab.setGoodsId(goodsId);
					tab.setBrokerageId(yongJinId);
					tab.setArticleId(articleId);
					tab.setWeight(Integer.parseInt(weight));
					tab.setGoodImageUrl(goodsImgUrl);
					tlxgoodsarticlebrokeragemanager.insert(tab);
				}
			}
		}
	}
	
	/**
	 * 弹出编辑页面
	 */
	public String edit() throws NumberFormatException, SQLException 
	{
		String aId = getRequest().getParameter("aId");
		if(StringUtils.hasText(aId))
		{
			Long articleId = Long.parseLong(aId);
			TLxGoodsArticle tace = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
			getRequest().setAttribute("tace", tace);
			getRequest().setAttribute("acontent", new String(tace.getContent()));
			
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("aId", aId);
			List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selecctTuiJianGoods");
			getRequest().setAttribute("datas", datas);
		}
		return "edit";
	}
	
	/**
	 * 删除推荐商品
	 */
	public void delimg() throws SQLException
	{
		String imgId = getRequest().getParameter("imgId");
		if(StringUtils.hasText(imgId))
		{
			Long _imgId = Long.parseLong(imgId);
			tlxgoodsarticlebrokeragemanager.deleteByPrimaryKey(_imgId);
		}
	}
	
	public static String getDiskName()
	{
		String diskname = "";
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		diskname = df.format(new Date());
		return diskname;
	}
	
	/**
	 * 弹出添加文章页面
	 */
	public String add()
	{
		return "add";
	}
	
	public class Condition
	{
		private File imgfile;
		private String imgfileFileName;
		public File getImgfile()
		{
			return imgfile;
		}
		public void setImgfile(File imgfile)
		{
			this.imgfile = imgfile;
		}
		public String getImgfileFileName()
		{
			return imgfileFileName;
		}
		public void setImgfileFileName(String imgfileFileName)
		{
			this.imgfileFileName = imgfileFileName;
		}
	}

	@Override
	public Object getModel()
	{
		return this.model;
	}
	@Override
	public void setModel(Object o)
	{
		this.model = (Condition) o;
	}
	
	/**
	 * 文章列表
	 * @return
	 */
	public String list()
	{
		Map map=new HashMap();
		if(null != title && !("").equals(title))
		{
			map.put("title", title);
		}
		if(null != sn && !("").equals(sn))
		{
			map.put("sn", sn);
		}
		if(null != start_date && !("").equals(start_date))
		{
			map.put("start_date", start_date);
		}
		if(null != end_date && !("").equals(end_date))
		{
			map.put("end_date", end_date);
		}
		pw = opensqlmanage.selectForPageByMap_drug(map,"t_lx_goods_article.selectByExampleByPageCount","t_lx_goods_article.selectByExampleByPageList",rs.getP_curPage(), rs.getP_pageSize());
		return "list";
	}
	
	/**
	 * 访问常规模板
	 */
	public String view1() throws SQLException
	{
		Long articleId = Long.parseLong(getRequest().getParameter("articleId"));
		TLxGoodsArticle tla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
		getRequest().setAttribute("tla", tla);
		getRequest().setAttribute("acontent", new String(tla.getContent()));
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("articleid", articleId);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selecctTuiJianGoodsByArticleId");
		getRequest().setAttribute("datas", datas);
		return "view1";
	}
	
	/**
	 * 访问通屏模板
	 */
	public String view2() throws SQLException
	{
		Long articleId = Long.parseLong(getRequest().getParameter("articleId"));
		TLxGoodsArticle tla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
		getRequest().setAttribute("tla", tla);
		getRequest().setAttribute("acontent", new String(tla.getContent()));
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("articleid", articleId);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selecctTuiJianGoodsByArticleId");
		getRequest().setAttribute("datas", datas);
		return "view2";
	}
	
	/**
	 * 预览微信文章页面
	 */
	public void preview() throws SQLException
	{
		String ratio = "4.5";
		String str = tsysparametermanager.getKeys("fanyong_default_bili");
		if(StringUtils.hasText(str))
		{
			ratio = str;
		}
		String type = getRequest().getParameter("type");
		Long articleId = Long.parseLong(getRequest().getParameter("articleId"));
		TLxGoodsArticle tla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
		getRequest().setAttribute("tla", tla);
		getRequest().setAttribute("acontent", new String(tla.getContent()));
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("articleid", articleId);
		p.put("ratio", ratio);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selecctTuiJianGoodsByArticleId");
		getRequest().setAttribute("datas", datas);
		
		//获取推荐商品权重最大的商品ID
		//String goodId = datas.get(0).get("goodsid").toString();
		
		Map<Object, Object> root = new HashMap<Object, Object>();
		root.put("tla", tla);
		root.put("acontent", new String(tla.getContent()));
		root.put("datas", datas);
		String base = getRequest().getSession().getServletContext().getRealPath("/");
		String staticPageUrl = base + "/static/leader/preview.html";
		String visitUrl = getRequest().getContextPath() + "/static/leader/preview.html";
		String fileName = "view1.ftl";
		if(type != null && type.trim().equals("2"))
		{
			fileName = "view2.ftl";
		}
		UtilStatic.doTemplatePage(root,base + "/WEB-INF/pages/pname/leader_article/",fileName,staticPageUrl);
		writeObjectToResponse(visitUrl, ContentType.application_json);
	}
	
	/**
	 * 发布微信文章页面
	 */
	public void publish() throws SQLException
	{
		String ratio = "4.5";
		String str = tsysparametermanager.getKeys("fanyong_default_bili");
		if(StringUtils.hasText(str))
		{
			ratio = str;
		}
		String type = getRequest().getParameter("type");
		Long articleId = Long.parseLong(getRequest().getParameter("articleId"));
		TLxGoodsArticle tla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
		getRequest().setAttribute("tla", tla);
		getRequest().setAttribute("acontent", new String(tla.getContent()));
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("articleid", articleId);
		p.put("ratio", ratio);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selecctTuiJianGoodsByArticleId");
		getRequest().setAttribute("datas", datas);
		
		//获取推荐商品权重最大的商品ID
		//String goodId = datas.get(0).get("goodsid").toString();
		
		Map<Object, Object> root = new HashMap<Object, Object>();
		root.put("tla", tla);
		root.put("acontent", new String(tla.getContent()));
		root.put("datas", datas);
		String base = getRequest().getSession().getServletContext().getRealPath("/");
		String staticPageUrl = base + "/static/leader/"+articleId+".html";
		String visitUrl = getRequest().getContextPath() + "/static/leader/"+articleId+".html";
		String fileName = "view1.ftl";
		if(type != null && type.trim().equals("2"))
		{
			fileName = "view2.ftl";
		}
		UtilStatic.doTemplatePage(root,base + "/WEB-INF/pages/pname/leader_article/",fileName,staticPageUrl);
		TLxGoodsArticle ntla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
		ntla.setStatus(1);
		ntla.setSendDt(new Date());
		ntla.setFormatType(Integer.parseInt(type));
		tlxgoodsarticlemanager.updateByPrimaryKeySelective(ntla);
		writeObjectToResponse(visitUrl, ContentType.application_json);
	}
	
	/**生成全部文章静态页**/
	public void staticPages() throws SQLException
	{
		String ratio = "4.5";
		String str = tsysparametermanager.getKeys("fanyong_default_bili");
		if(StringUtils.hasText(str))
		{
			ratio = str;
		}
		String type = getRequest().getParameter("type");
		List<Map<String, Object>> articles = opensqlmanage.selectForListByMap(null, "t_lx_goods_article.selectallarticleid");
		if(articles != null)
		{
			for(Map<String, Object> m : articles)
			{
				Long articleId = Long.parseLong(m.get("id").toString());
				TLxGoodsArticle tla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
				getRequest().setAttribute("tla", tla);
				getRequest().setAttribute("acontent", new String(tla.getContent()));
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("articleid", articleId);
				p.put("ratio", ratio);
				List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selecctTuiJianGoodsByArticleId");
				getRequest().setAttribute("datas", datas);
				Map<Object, Object> root = new HashMap<Object, Object>();
				root.put("tla", tla);
				root.put("acontent", new String(tla.getContent()));
				root.put("datas", datas);
				String base = getRequest().getSession().getServletContext().getRealPath("/");
				String staticPageUrl = base + "/static/leader/"+articleId+".html";
				String fileName = "view1.ftl";
				if(type != null && type.trim().equals("2"))
				{
					fileName = "view2.ftl";
				}
				UtilStatic.doTemplatePage(root,base + "/WEB-INF/pages/pname/leader_article/",fileName,staticPageUrl);
				TLxGoodsArticle ntla = tlxgoodsarticlemanager.selectByPrimaryKey(articleId);
				ntla.setStatus(1);
				ntla.setSendDt(new Date());
				ntla.setFormatType(Integer.parseInt(type));
				tlxgoodsarticlemanager.updateByPrimaryKeySelective(ntla);
			}
		}
		writeObjectToResponse(1, ContentType.application_json);
	}
	
	/**
	 * 通过文章ID查找推荐商品
	 */
	public void lookTuiJianGoodsByArticleId() throws SQLException
	{
		String ratio = "4.5";
		String str = tsysparametermanager.getKeys("fanyong_default_bili");
		if(StringUtils.hasText(str))
		{
			ratio = str;
		}
		Long articleId = Long.parseLong(getRequest().getParameter("articleId"));
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("ratio", ratio);
		p.put("articleid", articleId);
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(p, "t_lx_goods_article.selecctTuiJianGoodsByArticleId");
		writeObjectToResponse(datas, ContentType.application_json);
	}
	
	
	/**
	 * Sending message to health wechat user
	 */
	public void sendMessageToLeader() throws MalformedURLException, IOException 
	{
		//Get leader username
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(null, "t_lx_goods_article.selectUserNameWithWx");
		
		if(datas != null && datas.size() > 0)
		{
			for(Map<String, Object> m : datas)
			{
				String username = m.get("user_name").toString();
				
				//Parameters are encapsulated
				Map<String,String> p = new HashMap<String,String>();
				p.put("action", "query_user_info_snsapi_base");
				p.put("appIdInt", InfoUtil.getInstance().getInfo("config", "appIdInt"));
				p.put("unionId", username);
				String mySign1 = ParametersCommon.getWXSign(p, InfoUtil.getInstance().getInfo("config", "securitycode"));
				
				//Create request url
				String requestUrl = InfoUtil.getInstance().getInfo("config", "wsrequeurl") + "/9ea2c65dab8f/wxPublish.do?action=query_user_info_snsapi_base&appIdInt="
						+ InfoUtil.getInstance().getInfo("config", "appIdInt") + "&unionId=" + username + "&sign=" + mySign1;
				
				//Send request from server
				String jsonStr = ConnectionUtil.getContentFromUrlByGetMethod(requestUrl);
				
				//Jump out of the current cycle when condition bad
				if(jsonStr != null && !jsonStr.trim().equals("") && jsonStr.trim().equals("fail"))
				{
					continue;
				}
				
				//Analytic parameter
				JSONObject json = JSONObject.fromObject(jsonStr);
				Object obj = json.get("openId");
				if(obj == null)
				{
					continue;
				}
				
				//Request the second method and Sending message to health wachat leader
				String openId = obj.toString();
				String use_set = InfoUtil.getInstance().getInfo("config", "use_set");
				SimpleDateFormat sd = new SimpleDateFormat("M月d日");
				String articleDate = sd.format(new Date());
				String articleTitles = getRequest().getParameter("titles");
				String input_charset = InfoUtil.getInstance().getInfo("config", "input_charset");
				
				
				Map<String,String> s = new HashMap<String,String>();
				s.put("use_set", use_set);
				s.put("toUser", openId);
				s.put("articleDate", articleDate);
				s.put("articleTitles", articleTitles);
				s.put("input_charset", input_charset);
				String mySign2 = ParametersCommon.getWXSign(s, InfoUtil.getInstance().getInfo("config", "securitycode"));
				
				String sendMsgUrl = InfoUtil.getInstance().getInfo("config", "wsrequeurl") + "/wxTemplateMsgSend.do?" 
								  +	"use_set=" + use_set + "&toUser=" + openId + "&articleDate=" + URLEncoder.encode(articleDate, input_charset) + "&articleTitles=" + URLEncoder.encode(articleTitles, input_charset)
				                  + "&input_charset=" + input_charset + "&sign=" + mySign2;
				
				//Send request from server
				String result = ConnectionUtil.getContentFromUrlByGetMethod(sendMsgUrl);
				if(result != null && !result.trim().equals("") && result.trim().equals("fail"))
				{
					++failnum;
					continue;
				}
				else
				{
					++sucnum;
				}
			}
		}
		Map<String,String> r = new HashMap<String,String>();
		r.put("ok", sucnum+"");
		r.put("fail", failnum+"");
		writeObjectToResponse(r, ContentType.application_json);
	}
	
	/**
	 * 保存单个图片
	 */
	public void saveImage() throws IOException
	{
		String imgUrl = null;
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/html;charset=utf-8");
		String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
		File file = model.getImgfile();
		String fullName = null;
		String filename = model.getImgfileFileName();
		String fileType = FilenameUtils.getExtension(filename);
		if(file.length() < MAXSIZE)
		{
			String fullPath = diskPath + getDiskName() + "/" + DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()));
			imgUrl = fullPath+"."+fileType;
			fullName = basePath+imgUrl;
			File uploadFile = new File(fullName);
			FileUtils.copyFile(file, uploadFile);
		}
		writeObjectToResponse(imgUrl, ContentType.text_html);
	}
	
	/**
	 * 删除文章数据
	 */
	public void delDate() throws Exception
	{
		String id_str = getRequest().getParameter("id");
		if(StringUtils.hasText(id_str))
		{
			Long id = Long.parseLong(id_str);
			TLxGoodsArticle tlxgoodsarticle = tlxgoodsarticlemanager.selectByPrimaryKey(id);
			tlxgoodsarticle.setIsDelete(1);
			tlxgoodsarticlemanager.updateByPrimaryKeySelective(tlxgoodsarticle);
		}
	}
	
}
