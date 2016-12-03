package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.UtilStatic;
import com.rc.portal.service.CPositionListManager;
import com.rc.portal.service.CPositionManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.vo.CPosition;
import com.rc.portal.vo.CPositionExample;
import com.rc.portal.vo.CPositionList;
import com.rc.portal.webapp.model.Rule;
import com.rc.portal.webapp.util.PageResult;

public class PositionManageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private CPosition cposition;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	//模板规则
	private Rule rule;
	private CPositionManager cpositionmanager;
	private CPositionListManager cpositionlistmanager;
	private OpenSqlManage opensqlmanage;
	//是否需要发布
	private String isrelease;
	private String channel;
	private Integer id;
	
	/**
	 * 跳转到添加模板数据页面
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public String addPosition() throws IOException, SQLException{
		channel = this.getRequest().getParameter("channel");//
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("channel",channel);
		Map resultMap =(Map) opensqlmanage.selectForObjectByMap_drug(querMap,"c_position_list.queryRule");
		if(null!=resultMap){
			String jsonStr =String.valueOf(resultMap.get("rule"));
			JSONObject jsonObj = JSONObject.fromObject(jsonStr);
			rule =(Rule) JSONObject.toBean(jsonObj,Rule.class);//将规则转化为Rule对象
		}
		String cpId = this.getRequest().getParameter("id");
		if(cpId!=null&&!"".equals(cpId)){
			id = Integer.parseInt(cpId);
			cposition = cpositionmanager.selectByPrimaryKey(id);
		}
		return "addPosition";
	}
	/**
	 * 添加或更新position表信息
	 * return '1'添加成功,'2'修改成功,'-1'程序出现异常
	 */
	public void addPositionInfo() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		channel = this.getRequest().getParameter("channel");
		if(null != cposition)
		{
			if(StringUtils.hasText(channel))
			{
				cposition.setChannel(Integer.parseInt(channel));
			}
			try
			{
				if(cposition.getWeight() == null)
				{
					cposition.setWeight(0);
				}
				if(null == cposition.getId())
				{
					cpositionmanager.insertSelective(cposition);
					flag = 1;
				}
				else
				{
					cpositionmanager.updateByPrimaryKeySelective(cposition);
					flag = 2;
				}
			} 
			catch (Exception e) 
			{
				out.print("0");
				out.close();
				e.printStackTrace();
			}
		}
		out.print(flag);
		out.close();
	}
	
	/**
	 * @Title: queryInfoByid
	 * @Description: TODO(根据position Id查询 position信息)
	 */
	public void queryInfoByid() throws IOException{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out =this.getResponse().getWriter();
		String psid=this.getRequest().getParameter("psid");
		CPositionExample example = new CPositionExample();
		CPosition jp;
		try {
			jp = cpositionmanager.selectByPrimaryKey(Integer.valueOf(psid));
			if(null!=jp){
			JSONObject jsonObj=JSONObject.fromObject(jp);
			String jsonStr =String.valueOf(jsonObj);
			out.print(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("-1");
		}
		out.close();
	}
	
	/**
	 * 
	 * @Title: deletePositionInfo
	 * @Description: TODO(删除 position 信息)
	 * @throws IOException
	 */
	public void deletePositionInfo() throws IOException{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		String pids = this.getRequest().getParameter("pids");
		String[] pid=pids.split(",");
		for (int i = 0; i < pid.length; i++) {
			try {
				cpositionmanager.deleteByPrimaryKey(Integer.valueOf(pid[i]));
				flag=1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=0;
				out.print(flag);
				out.close();
			}
		}
		out.print(flag);
		out.close();
	}
	
	/**
	 * 复制数据
	 * @throws IOException
	 */
	public void copyPositionInfo() throws IOException
	{
		int flag = -1;
		String pids = this.getRequest().getParameter("pids");
		if(StringUtils.hasText(pids))
		{
			try 
			{
				CPosition cp = cpositionmanager.selectByPrimaryKey(Integer.parseInt(pids));
				CPosition newCp = new CPosition();
				newCp.setCreateDate(new Date());
				newCp.setChannel(cp.getChannel());
				newCp.setImgLink(cp.getImgLink());
				newCp.setImgUrl(cp.getImgUrl());
				newCp.setIssue(cp.getIssue());
				newCp.setTitle(cp.getTitle());
				newCp.setTitleUrl(cp.getTitleUrl());
				newCp.setTxt(cp.getTxt());
				newCp.setTxtLink(cp.getTxtLink());
				newCp.setType(cp.getType());
				newCp.setWeight(cp.getWeight());
				newCp.setMarketPrice(cp.getMarketPrice());
				newCp.setPrice(cp.getPrice());
				newCp.setPriceUrl(cp.getPriceUrl());
				newCp.setTitleAll(cp.getTitleAll());
				cpositionmanager.insert(newCp);
				flag = 1;
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
				flag = 0;
			}
			writeObjectToResponse(flag, ContentType.application_json);
		}
	}
	
	/**
	 * 
	 * @Title: queryPostionInfo
	 * @Description: TODO(查询位置信息)
	 */
	@SuppressWarnings("rawtypes")
	public String queryPostionInfo() throws IOException
	{
		channel = getRequest().getParameter("id");
		Map<String,Object> querMap = new HashMap<String,Object>();
		querMap.put("channel",channel);
		
		String searchType_hidden = getRequest().getParameter("searchType_hidden");
		String searchValue_hidden = getRequest().getParameter("searchValue_hidden");
		if(StringUtils.hasText(searchType_hidden))
		{
			if(searchType_hidden.equals("img_url"))
			{
				querMap.put("img_url",searchType_hidden);
			}
			
			if(searchType_hidden.equals("img_link"))
			{
				querMap.put("img_link",searchType_hidden);
			}
			
			if(searchType_hidden.equals("txt"))
			{
				querMap.put("txt",searchType_hidden);
			}
			
			if(searchType_hidden.equals("txt_link"))
			{
				querMap.put("txt_link",searchType_hidden);
			}
			
			if(searchType_hidden.equals("title"))
			{
				querMap.put("title",searchType_hidden);
			}
			
			if(searchType_hidden.equals("title_url"))
			{
				querMap.put("title_url",searchType_hidden);
			}
			
			if(searchType_hidden.equals("title_all"))
			{
				querMap.put("title_all",searchType_hidden);
			}
			
			if(searchType_hidden.equals("price"))
			{
				querMap.put("price",searchType_hidden);
			}
			
			if(searchType_hidden.equals("price_url"))
			{
				querMap.put("price_url",searchType_hidden);
			}
			
			if(searchType_hidden.equals("market_price"))
			{
				querMap.put("market_price",searchType_hidden);
			}
		}
		querMap.put("searchValue_hidden",searchValue_hidden);
		
		pw = opensqlmanage.selectForPageByMap_drug(querMap, "c_position.countPositionInfo", "c_position.queryPositionInfo", rs.getP_curPage(), 100);
		Map resultMap = (Map) opensqlmanage.selectForObjectByMap_drug(querMap,"c_position_list.queryRule");
		if(null != resultMap)
		{
			String jsonStr =String.valueOf(resultMap.get("rule"));
			JSONObject jsonObj = JSONObject.fromObject(jsonStr);
			//将规则转化为Rule对象
			rule = (Rule) JSONObject.toBean(jsonObj,Rule.class);
			//是否需要发布
			isrelease = String.valueOf(resultMap.get("isrelease"));
		}
		getRequest().setAttribute("searchType_hidden", searchType_hidden);
		getRequest().setAttribute("searchValue_hidden", searchValue_hidden);
		return "poslist";
	}
	
	/**
	 * 位置管理发布生成静态页
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void releasePage() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		//-1：发布失败、-2：查询模板信息异常
		int flag = -1;
		PrintWriter out = this.getResponse().getWriter();
		String pids = this.getRequest().getParameter("pids");
		String[] pid = pids.split(",");
		
		//封装需要发布的属性参数
		Map<Object,Object> mapParm = new HashMap<Object, Object>();
		List<Map> list = new ArrayList<Map>();
		for (int i = 0; i < pid.length; i++) 
		{
			Map<Object, Object> map = new TreeMap<Object, Object>();
			try 
			{
				CPosition cposition = cpositionmanager.selectByPrimaryKey(Integer.valueOf(pid[i]));
				
				//判断用海典编码查询信息
				String title = cposition.getTitle();
				if(title != null && !title.equals("") && title.equals("HD"))
				{
					String hDCode = cposition.getTitleAll().trim();
					Map<String, Object> nmap = new HashMap<String, Object>();
					nmap.put("goodsno", hDCode);
					List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(nmap, "t_goods.selectGoodsByhdCode");
					Map<String, Object> ndata = null;
					if(datas != null && datas.size() > 0)
					{
						ndata = datas.get(0);
						//商品缩略图
						String imgUrl = "/static/image/temp/20160225/4689ba05cb49cf65f981bf9f93090250.jpg";
						String abbreviation_picture = ndata.get("abbreviation_picture") + "";
						if(abbreviation_picture != null && StringUtils.hasText(abbreviation_picture))
						{
							imgUrl = abbreviation_picture;
						}
						cposition.setImgUrl(imgUrl);
						//商品链接
						cposition.setImgLink(ndata.get("id").toString());
						//商品名称
						String goodsName = "none";
						String goods_name = ndata.get("goods_name")+"";
						if(goods_name != null && StringUtils.hasText(goods_name))
						{
							goodsName = goods_name;
						}
						cposition.setTxtLink(goodsName);
						//主标题
						String mainTitle = "none";
						String main_title = ndata.get("main_title")+"";
						if(main_title != null && StringUtils.hasText(main_title))
						{
							mainTitle = main_title;
						}
						cposition.setPriceUrl(mainTitle);
						//销售价格
						String price = "none";
						String pc_price = ndata.get("pc_price")+"";
						if(pc_price != null && StringUtils.hasText(pc_price))
						{
							price = pc_price;
						}
						cposition.setPrice(price);
						//市场价格
						String marketPrice = "none";
						String market_price = ndata.get("price")+"";
						if(market_price != null && StringUtils.hasText(market_price))
						{
							marketPrice = market_price;
						}
						cposition.setMarketPrice(marketPrice);
					}
				}
				map.put("cposition",cposition);
				map.put("weight", cposition.getWeight());
				list.add(map);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				System.out.println(e);
				out.print(flag);
				out.close();
			}
		}
		mapParm.put("attInfo", list);
		
		//查询模板信息
		String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
		String ftlPath = "";
		String htmlPath = "";
		String htmlTemplateName = "";
		try 
		{
			CPositionList zpl = cpositionlistmanager.selectByPrimaryKey(Integer.valueOf(channel));
			if(null != zpl)
			{
				ftlPath = zpl.getmPath();
				htmlPath = zpl.getOutPath();
				htmlTemplateName = zpl.getmName();
			}
		} 
		catch (SQLException e) 
		{
			flag = -2;
			e.printStackTrace();
		}
		
		//生成静态页
		String templatePath = basePath+ftlPath;
		String outPath = basePath+htmlPath;
		
		try
		{
			UtilStatic.doTemplatePage(mapParm, templatePath, htmlTemplateName, outPath);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		//修改position表的发布状态
		CPositionExample je = new CPositionExample();
		je.createCriteria().andIssueEqualTo(1).andChannelEqualTo(Integer.valueOf(channel));
		CPosition jpostion1 = new CPosition();
		//将已发布状态改为未发布
		jpostion1.setIssue(0);
		cpositionmanager.updateByExampleSelective(jpostion1, je);

		for(int i=0;i<pid.length;i++)
		{
			CPosition jpostion = new CPosition();
			jpostion.setId(Integer.valueOf(pid[i]));
			//将选项的发布状态改为已发布
			jpostion.setIssue(1);
			cpositionmanager.updateByPrimaryKeySelective(jpostion);
		}
		
		flag=1;
		out.print(flag);
		out.close();
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
	
	public class Condition{
		
	}

	public CPosition getJposition() {
		return cposition;
	}

	public void setJposition(CPosition cposition) {
		this.cposition = cposition;
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

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}


	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public String getIsrelease() {
		return isrelease;
	}

	public void setIsrelease(String isrelease) {
		this.isrelease = isrelease;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	

	public CPosition getCposition() {
		return cposition;
	}

	public void setCposition(CPosition cposition) {
		this.cposition = cposition;
	}

	public CPositionManager getCpositionmanager() {
		return cpositionmanager;
	}

	public void setCpositionmanager(CPositionManager cpositionmanager) {
		this.cpositionmanager = cpositionmanager;
	}

	
	public CPositionListManager getCpositionlistmanager() {
		return cpositionlistmanager;
	}

	public void setCpositionlistmanager(CPositionListManager cpositionlistmanager) {
		this.cpositionlistmanager = cpositionlistmanager;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		  /**
         * "测试字符串"的UTF-8编码数组
         */
        byte[] bUTF_8= "测试字符串".getBytes("UTF-8");
        /**
         * "测试字符串"的GBK编码数组
         */
        byte[] bGBK= "测试字符串".getBytes("GBK");
         
        String sbUTF_8 =new String(bUTF_8,"UTF-8");
         
        String sbGBK =new String(bGBK,"GBK");
         
        /**
         * 假如你不小心把UTF-8编码数组通过GBK解码字符串，那你只有先反向得到字节数组，再用UTF-8获取字符串
         */
        String sb_wrong =new String(bUTF_8,"GBK");
        String sb_right =new String(sb_wrong.getBytes("GBK"),"UTF-8");
        System.out.println(sb_wrong);
        System.out.println(sb_right);
        System.out.println(sbUTF_8);
        System.out.println(sbGBK);
	}
}
