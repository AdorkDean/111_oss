package com.rc.portal.webapp.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.UtilStatic;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.THealthyPlanManager;
import com.rc.portal.service.TStaticManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TGoodsExtendWithBLOBs;
import com.rc.portal.vo.TGoodsImages;
import com.rc.portal.vo.TStatic;
import com.rc.portal.webapp.model.GoodsFormBeanModel2;
import com.rc.portal.webapp.model.GoodsGroupModel2;
import com.rc.portal.webapp.model.GoodsPremiumsModel;
import com.rc.portal.webapp.model.HealthyPlanContentModel;
import com.rc.portal.webapp.util.PageResult;

/**
 * 静态化管理
 * @author LGP
 * @date 2015年8月25日
 */
@SuppressWarnings("unchecked")
public class StaticManageAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private TCategoryManager tcategorymanager;
	private TStaticManager tstaticmanager;
	private TGoodsManager tgoodsmanager;
	private THealthyPlanManager thealthyplanmanager;
	/**商品类别*/
	private List<TCategory> cateList;
	/**静态化实体*/
	private TStatic tstatic;
	/**图片集合*/
	private List<TGoodsImages> listi;
	/**赠品集合*/
	private List<GoodsPremiumsModel> listp;
	/**组合商品集合*/
	private List<GoodsGroupModel2> listg;
	/**规格*/
	private List<Map<Long,String>> lists;
	/**规格*/
	private List<Map<String,String>> listpc;
	/**商品*/
	Map<String, Object> goods =new HashMap<String, Object>();
	/**商品集合*/
	private List<Map<String, Object>> goodsArray;
	/**商品扩展*/
	private TGoodsExtendWithBLOBs goodse;
	/**组合商品主商品id*/
	private String goodsgidsm;
	/**商品属性封装Bean*/
	private GoodsFormBeanModel2 goodsf = new GoodsFormBeanModel2();
	/**全部商品分类*/
	private List<Map<String, Object>> allcates;
	/**系统读取配置文件*/
	private TSysParameterManager tsysparametermanager;
	/**生成结果返回码*/
	private int result = -1;
	
	/**
	 * 查询静态化资源列表
	 */
	public String list()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		pw = opensqlmanage.selectForPageByMap_drug(map, "t_static.ibatorgenerated_countByExample", "t_static.selectByExampleByPage", rs.getP_curPage(), rs.getP_pageSize());
		return "list";
	}

	/**
	 * 显示添加页面
	 */
	public String add()
	{
		return "add";
	}
	
	/**
	 * 保存数据
	 */
	public void save() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		tstatic.setCreateDate(new Date());
		tstatic.setUpdateDate(null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("staticName", tstatic.getStaticName());
		List<Map<String, Object>> datas = opensqlmanage.selectForListByMap(map, "t_static.selectByname");
		if(datas != null && datas.size() > 0)
		{
			out.print("1");
		}
		else
		{
			tstaticmanager.insert(tstatic);
			out.print("0");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 弹出编辑页面
	 */
	public String edit() throws NumberFormatException, SQLException
	{
		String strId = this.getRequest().getParameter("id");
		if(strId != null && !strId.trim().equals(""))
		{
			tstatic = tstaticmanager.selectByPrimaryKey(Long.valueOf(strId));
		}
		return "edit";
	}
	
	/**
	 * 更新
	 */
	public void update() throws NumberFormatException, SQLException, IOException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		String strId = this.getRequest().getParameter("id");
		if(strId != null && !strId.trim().equals(""))
		{
			TStatic tst = tstaticmanager.selectByPrimaryKey(Long.valueOf(strId));
			int flag = 0;
			if(tst != null)
			{
				tst.setStaticName(tstatic.getStaticName());
				tst.setStaticMehtod(tstatic.getStaticMehtod());
				tst.setTemplateName(tstatic.getTemplateName());
				tst.setTemplatePath(tstatic.getTemplatePath());
				tst.setOutPath(tstatic.getOutPath());
				tst.setUpdateDate(new Date());
				flag = tstaticmanager.updateByPrimaryKey(tst);
				if(flag > 0)
				{
					out.print(1);
				}
				else
				{
					out.print(2);
				}
			}
			else
			{
				out.print(3);
			}
		}
		else
		{
			out.print(4);
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 删除数据
	 */
	public void delete() throws IOException, SQLException
	{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		PrintWriter out = this.getResponse().getWriter();
		String strId = this.getRequest().getParameter("id");
		if(strId != null && !strId.trim().equals(""))
		{
			int dbc = tstaticmanager.deleteByPrimaryKey(Long.parseLong(strId));
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
	
	/**
	 * 检查模板文件是否存在
	 */
	public void isfilexist() throws IOException, SQLException
	{
		setUTF8(getRequest(),getResponse());
		PrintWriter out = this.getResponse().getWriter();
		String strId = this.getRequest().getParameter("id");
		if(strId != null && !strId.trim().equals(""))
		{
			File file = new File(this.getTemplateUrl(strId, getRequest(), "3"));
			if(file.exists())
			{
				out.println(0);
			}
			else
			{
				out.println(1);
			}
		}
		else
		{
			out.println(2);
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 显示生成静态化页面
	 */
	public String showstaticp() throws SQLException
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		pw = opensqlmanage.selectForPageByMap_drug(map,"t_static.ibatorgenerated_countByExample","t_static.selectByExampleByPage",1,50);
		Map<String, Object> cm = new HashMap<String, Object>();
		cm.put("parentid", 1);
		allcates = opensqlmanage.selectForListByMap(cm, "t_category.selectallproductcategory");
		return "showstaticp";
	}
	
	/**
	 * 生成所有商品分类静态页
	 */
	public void buildallprocates() throws SQLException, Exception
	{
		long startTime = System.currentTimeMillis();
		String strId = this.getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		int result = -1;
		int count = 0;
		try
		{
			this.setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				String parentid = tsysparametermanager.getKeys("homepage");
				Map<String, Object> cm = new HashMap<String, Object>();
				cm.put("parentid", parentid);
				allcates = opensqlmanage.selectForListByMap(cm, "t_category.selectallproductcategory");
				List<Map<String,Object>> rss = new ArrayList<Map<String,Object>>();
				for(int i=0; i<allcates.size(); i++)
				{
					if(Integer.parseInt(String.valueOf(allcates.get(i).get("category_level"))) == 2)
					{
						Map<String, Object> n = new HashMap<String, Object>();
						n.put("id", allcates.get(i).get("id"));
						n.put("category_name", allcates.get(i).get("category_name"));
						n.put("category_describe", allcates.get(i).get("category_describe"));
						n.put("category_level", allcates.get(i).get("category_level"));
						n.put("all_parent_id", allcates.get(i).get("all_parent_id"));
						n.put("parent_id", allcates.get(i).get("parent_id"));
						n.put("is_wap", allcates.get(i).get("is_wap"));
						n.put("is_app", allcates.get(i).get("is_app"));
						n.put("is_pc", allcates.get(i).get("is_pc"));
						n.put("create_time", allcates.get(i).get("create_time"));
						rss.add(n);
					}
					if(rss.size() == 16)
					{
						break;
					}
				}
				HashMap<Object, Object> root = new HashMap<Object, Object>();
				root.put("allcates", allcates);
				root.put("rss", rss);
				/** Delete origin page */
				String staticPageUrl = getTemplateUrl(strId, getRequest(),"4").replaceAll("\\\\", "/");
				File oldFile = new File(staticPageUrl);
				if(oldFile.exists())
				{
					oldFile.delete();
				}
				UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),staticPageUrl);
				result = 0;
				count = 1;
			}
			else
			{
				result = 3;
			}
		} 
		catch (Exception e)
		{
			result = 4;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime)/1000);
		map.put("result", result);
		map.put("count", count);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		JSONArray jsons = JSONArray.fromObject(datas);
		reJSON(jsons.toString(), this.getRequest(), this.getResponse());
	}
	
	/**
	 * 生成保健馆商品分类静态页
	 */
	public void buildheathcare() throws SQLException, Exception
	{
		long startTime = System.currentTimeMillis();
		String strId = this.getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		int result = -1;
		int count = 0;
		try
		{
			this.setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				String parentid = tsysparametermanager.getKeys("healthcare");
				Map<String, Object> cm = new HashMap<String, Object>();
				cm.put("parentid", parentid);
				allcates = opensqlmanage.selectForListByMap(cm, "t_category.selectallproductcategory");
				List<Map<String,Object>> rss = new ArrayList<Map<String,Object>>();
				for(int i=0; i<allcates.size(); i++)
				{
					if(Integer.parseInt(String.valueOf(allcates.get(i).get("category_level"))) == 2)
					{
						Map<String, Object> n = new HashMap<String, Object>();
						n.put("id", allcates.get(i).get("id"));
						n.put("category_name", allcates.get(i).get("category_name"));
						n.put("category_describe", allcates.get(i).get("category_describe"));
						n.put("category_level", allcates.get(i).get("category_level"));
						n.put("all_parent_id", allcates.get(i).get("all_parent_id"));
						n.put("parent_id", allcates.get(i).get("parent_id"));
						n.put("is_wap", allcates.get(i).get("is_wap"));
						n.put("is_app", allcates.get(i).get("is_app"));
						n.put("is_pc", allcates.get(i).get("is_pc"));
						n.put("create_time", allcates.get(i).get("create_time"));
						rss.add(n);
					}
					if(rss.size() == 6)
					{
						break;
					}
				}
				/** Delete origin page */
				String staticPageUrl = getTemplateUrl(strId, getRequest(),"4").replaceAll("\\\\", "/");
				File oldFile = new File(staticPageUrl);
				if(oldFile.exists())
				{
					oldFile.delete();
				}
				HashMap<Object, Object> root = new HashMap<Object, Object>();
				root.put("allcates", allcates);
				root.put("rss", rss);
				root.put("parentid", parentid);
				UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),staticPageUrl);
				result = 0;
				count = 1;
			}
			else
			{
				result = 3;
			}
		} 
		catch (Exception e)
		{
			result = 4;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime)/1000);
		map.put("result", result);
		map.put("count", count);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		JSONArray jsons = JSONArray.fromObject(datas);
		reJSON(jsons.toString(), this.getRequest(), this.getResponse());
	}
	
	/**
	 * 生成母婴馆商品分类静态页
	 */
	public void buildinfantmom() throws SQLException, Exception
	{
		long startTime = System.currentTimeMillis();
		String strId = this.getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		int result = -1;
		int count = 0;
		try
		{
			this.setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				String parentid = tsysparametermanager.getKeys("infantmom");
				Map<String, Object> cm = new HashMap<String, Object>();
				cm.put("parentid", parentid);
				allcates = opensqlmanage.selectForListByMap(cm, "t_category.selectallproductcategory");
				List<Map<String,Object>> rss = new ArrayList<Map<String,Object>>();
				for(int i=0; i<allcates.size(); i++)
				{
					if(Integer.parseInt(String.valueOf(allcates.get(i).get("category_level"))) == 2)
					{
						Map<String, Object> n = new HashMap<String, Object>();
						n.put("id", allcates.get(i).get("id"));
						n.put("category_name", allcates.get(i).get("category_name"));
						n.put("category_describe", allcates.get(i).get("category_describe"));
						n.put("category_level", allcates.get(i).get("category_level"));
						n.put("all_parent_id", allcates.get(i).get("all_parent_id"));
						n.put("parent_id", allcates.get(i).get("parent_id"));
						n.put("is_wap", allcates.get(i).get("is_wap"));
						n.put("is_app", allcates.get(i).get("is_app"));
						n.put("is_pc", allcates.get(i).get("is_pc"));
						n.put("create_time", allcates.get(i).get("create_time"));
						rss.add(n);
					}
					if(rss.size() == 6)
					{
						break;
					}
				}
				/** Delete origin page */
				String staticPageUrl = getTemplateUrl(strId, getRequest(),"4").replaceAll("\\\\", "/");
				File oldFile = new File(staticPageUrl);
				if(oldFile.exists())
				{
					oldFile.delete();
				}
				HashMap<Object, Object> root = new HashMap<Object, Object>();
				root.put("allcates", allcates);
				root.put("rss", rss);
				root.put("parentid", parentid);
				UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),staticPageUrl);
				result = 0;
				count = 1;
			}
			else
			{
				result = 3;
			}
		} 
		catch (Exception e)
		{
			result = 4;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime)/1000);
		map.put("result", result);
		map.put("count", count);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		JSONArray jsons = JSONArray.fromObject(datas);
		reJSON(jsons.toString(), this.getRequest(), this.getResponse());
	}
	
	/**
	 * 生成商品分类静态页面
	 */
	public void builtcategory() throws IOException
	{
		long startTime = System.currentTimeMillis();
		String strId = this.getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		int result = -1;
		int count = 0;
		try
		{
			this.setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				cateList = tcategorymanager.selectByExample(null);
				HashMap<Object, Object> root = new HashMap<Object, Object>();
				root.put("cateList", cateList);
				UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),getTemplateUrl(strId, getRequest(),"4"));
				result = 0;
				count = 1;
			}
			else
			{
				result = 3;
			}
		} 
		catch (Exception e)
		{
			result = 4;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime)/1000);
		map.put("result", result);
		map.put("count", count);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		JSONArray jsons = JSONArray.fromObject(datas);
		reJSON(jsons.toString(), this.getRequest(), this.getResponse());
	}
	
	/**
	 * 生成PC站商品详情静态页
	 */
	public void builtgoods() throws Exception
	{
		String isRX = getRequest().getParameter("RX");
		long startTime = System.currentTimeMillis();
		String strId = getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		String beginDate = null;
		String endDate = null;
		int num = 0;
		int all = 0;
		boolean flag = false;
		try
		{
			setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				//程序控制变量
				String tParam = getRequest().getParameter("tParam");
				//商品分类ID
				String categoryId = getRequest().getParameter("categoryType");
				//开始日期
				beginDate = getRequest().getParameter("beginDate");
				//结束日期
				endDate = getRequest().getParameter("endDate");
				//分页参数
				String offSet = getRequest().getParameter("offSet");
				//商品总数
				all = Integer.parseInt(String.valueOf(getRequest().getParameter("total")));
				//生成静态页数量
				num = Integer.parseInt(String.valueOf(getRequest().getParameter("buildnum")));
				//分条件查询
				HashMap<String, Object> ction = new HashMap<String, Object>();
				if(!categoryId.trim().equals("0"))
				{
					ction.put("categoryid", categoryId);
				}
				if(StringUtils.hasText(beginDate))
				{
					ction.put("beginDate", beginDate);
				}
				if(StringUtils.hasText(endDate))
				{
					ction.put("endDate", endDate);
				}
				if(StringUtils.hasText(offSet))
				{
					ction.put("offSet", offSet);
					ction.put("pageSize", 5);
				}
				//获取商品总数
				if(tParam.trim().equals("0"))
				{
					List<Map<String, Object>> mtotal = null;
					if(isRX.equals("1"))
					{
						mtotal = opensqlmanage.selectForListByMap(null, "t_goods.selectGoodsCount2");
					}
					else
					{
						mtotal = opensqlmanage.selectForListByMap(null, "t_goods.selectGoodsCount");
					}
					all = Integer.parseInt(String.valueOf(mtotal.get(0).get("total")));
				}
				List<Map<String, Object>> list = null;
				if(flag == false)
				{
					list = tgoodsmanager.selectByPrimaryKey2(ction, "1", isRX);
				}
				if(list != null && list.size() > 0)
				{
					for(Map<String, Object> tg : list)
					{
						goods = tg;
						goodse = (TGoodsExtendWithBLOBs) goods.get("goodse");
						listi = (List<TGoodsImages>) goods.get("listi");
						listg = (List<GoodsGroupModel2>) goods.get("listg");
						lists = (List<Map<Long, String>>) goods.get("lists");
						listpc = (List<Map<String, String>>) goods.get("listpc");
						//重新封装模板数据
						HashMap<Object, Object> root = new HashMap<Object, Object>();
						root.put("goods", goods);
						root.put("listi", listi);
						root.put("listg", listg);
						root.put("lists", lists);
						root.put("listpc", listpc);
						String staticPageUrl = getTemplateUrl(strId, getRequest(),"4")+tg.get("id").toString()+".html";
						//删除原来静态页
						File oldFile = new File(staticPageUrl);
						if(oldFile.exists())
						{
							oldFile.delete();
						}
						//生成新静态页
						UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),staticPageUrl);
						//生成静态页个数
						++num;
						//设置完成状态
						if(all == num)
						{
							result = 0;
							flag = true;
							break;
						}
					}
				}
				else
				{
					if(!StringUtils.hasText(beginDate) && !StringUtils.hasText(endDate))
					{
						//此类别下暂时没有添加商品
						result = 1;
						flag = true;
					}
					else
					{
						//此日期之间没有任何商品
						result = 2;
						flag = true;
					}
				}
			}
			else
			{
				//传递参数为空操作错误
				result = 3;
				flag = true;
			}
		} 
		catch (Exception e)
		{
			//网络异常
			result = 4;
			flag = true;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime));
		map.put("result", result);
		map.put("count", num);
		map.put("total", all);
		map.put("iscompleted", flag);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		writeObjectToResponse(datas, ContentType.application_json);
	}
	
	/**
	 * 生成WAP站商品详情静态页
	 */
	public void builtwapgoods() throws Exception
	{
		String isRX = getRequest().getParameter("RX");
		long startTime = System.currentTimeMillis();
		String strId = getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		String beginDate = null;
		String endDate = null;
		int num = 0;
		int all = 0;
		boolean flag = false;
		try
		{
			this.setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				//程序控制变量
				String tParam = getRequest().getParameter("tParam");
				//商品分类ID
				String categoryId = getRequest().getParameter("categoryType");
				//开始日期
				beginDate = getRequest().getParameter("beginDate");
				//结束日期
				endDate = getRequest().getParameter("endDate");
				//分页参数
				String offSet = getRequest().getParameter("offSet");
				//商品总数
				all = Integer.parseInt(String.valueOf(getRequest().getParameter("total")));
				//生成静态页数量
				num = Integer.parseInt(String.valueOf(getRequest().getParameter("buildnum")));
				//分条件查询
				HashMap<String, Object> ction = new HashMap<String, Object>();
				if(!categoryId.trim().equals("0"))
				{
					ction.put("categoryid", categoryId);
				}
				if(StringUtils.hasText(beginDate))
				{
					ction.put("beginDate", beginDate);
				}
				if(StringUtils.hasText(endDate))
				{
					ction.put("endDate", endDate);
				}
				if(StringUtils.hasText(offSet))
				{
					ction.put("offSet", offSet);
					ction.put("pageSize", 5);
				}
				//获取商品总数
				if(tParam.trim().equals("0"))
				{
					List<Map<String, Object>> mtotal = null;
					if(isRX.equals("1"))
					{
						mtotal = opensqlmanage.selectForListByMap(null, "t_goods.selectGoodsCount2");
					}
					else
					{
						mtotal = opensqlmanage.selectForListByMap(null, "t_goods.selectGoodsCount");
					}
					all = Integer.parseInt(String.valueOf(mtotal.get(0).get("total")));
				}
				List<Map<String, Object>> list = null;
				if(flag == false)
				{
					list = tgoodsmanager.selectByPrimaryKey2(ction, "2", isRX);
				}
				if(list != null && list.size() > 0)
				{
					for(Map<String, Object> tg : list)
					{
						goods = tg;
						listi = (List<TGoodsImages>) goods.get("listi");
						//重新封装模板数据
						HashMap<Object, Object> root = new HashMap<Object, Object>();
						root.put("goods", goods);
						root.put("listi", listi);
						String staticPageUrl = getTemplateUrl(strId, getRequest(),"4")+tg.get("id").toString()+".html";
						//删除原来静态页
						File oldFile = new File(staticPageUrl);
						if(oldFile.exists())
						{
							oldFile.delete();
						}
						//生成新静态页
						UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),staticPageUrl);
						//生成静态页个数
						++num;
						//设置完成状态
						if(all == num)
						{
							result = 0;
							flag = true;
							break;
						}
					}
				}
				else
				{
					if(!StringUtils.hasText(beginDate) && !StringUtils.hasText(endDate))
					{
						//此类别下暂时没有添加商品
						result = 1;
						flag = true;
					}
					else
					{
						//此日期之间没有任何商品
						result = 2;
						flag = true;
					}
				}
			}
			else
			{
				//传递参数为空操作错误
				result = 3;
				flag = true;
			}
		} 
		catch (Exception e)
		{
			//网络异常
			result = 4;
			flag = true;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime));
		map.put("result", result);
		map.put("count", num);
		map.put("total", all);
		map.put("iscompleted", flag);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		writeObjectToResponse(datas, ContentType.application_json);
	}
	
	/**
	 * 生成APP站商品详情静态页
	 */
	public void builtappgoods() throws Exception
	{
		String isRX = getRequest().getParameter("RX");
		long startTime = System.currentTimeMillis();
		String strId = getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		String beginDate = null;
		String endDate = null;
		int num = 0;
		int all = 0;
		boolean flag = false;
		try
		{
			this.setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				//程序控制变量
				String tParam = getRequest().getParameter("tParam");
				//商品分类ID
				String categoryId = getRequest().getParameter("categoryType");
				//开始日期
				beginDate = getRequest().getParameter("beginDate");
				//结束日期
				endDate = getRequest().getParameter("endDate");
				//分页参数
				String offSet = getRequest().getParameter("offSet");
				//商品总数
				all = Integer.parseInt(String.valueOf(getRequest().getParameter("total")));
				//生成静态页数量
				num = Integer.parseInt(String.valueOf(getRequest().getParameter("buildnum")));
				//分条件查询
				HashMap<String, Object> ction = new HashMap<String, Object>();
				if(!categoryId.trim().equals("0"))
				{
					ction.put("categoryid", categoryId);
				}
				if(StringUtils.hasText(beginDate))
				{
					ction.put("beginDate", beginDate);
				}
				if(StringUtils.hasText(endDate))
				{
					ction.put("endDate", endDate);
				}
				if(StringUtils.hasText(offSet))
				{
					ction.put("offSet", offSet);
					ction.put("pageSize", 5);
				}
				//获取商品总数
				if(tParam.trim().equals("0"))
				{
					List<Map<String, Object>> mtotal = null;
					if(isRX.equals("1"))
					{
						mtotal = opensqlmanage.selectForListByMap(null, "t_goods.selectGoodsCount2");
					}
					else
					{
						mtotal = opensqlmanage.selectForListByMap(null, "t_goods.selectGoodsCount");
					}
					all = Integer.parseInt(String.valueOf(mtotal.get(0).get("total")));
				}
				List<Map<String, Object>> list = null;
				if(flag == false)
				{
					list = tgoodsmanager.selectByPrimaryKey2(ction, "1", isRX);
				}
				if(list != null && list.size() > 0)
				{
					for(Map<String, Object> tg : list)
					{
						goods = tg;
						goodse = (TGoodsExtendWithBLOBs) goods.get("goodse");
						listi = (List<TGoodsImages>) goods.get("listi");
						listg = (List<GoodsGroupModel2>) goods.get("listg");
						lists = (List<Map<Long, String>>) goods.get("lists");
						listpc = (List<Map<String, String>>) goods.get("listpc");
						//重新封装模板数据
						HashMap<Object, Object> root = new HashMap<Object, Object>();
						root.put("goods", goods);
						root.put("listi", listi);
						root.put("listg", listg);
						root.put("lists", lists);
						root.put("listpc", listpc);
						String staticPageUrl = getTemplateUrl(strId, getRequest(),"4")+"a_"+tg.get("id").toString()+".html";
						//删除原来静态页
						File oldFile = new File(staticPageUrl);
						if(oldFile.exists())
						{
							oldFile.delete();
						}
						//生成新静态页
						UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),staticPageUrl);
						//生成静态页个数
						++num;
						//设置完成状态
						if(all == num)
						{
							result = 0;
							flag = true;
							break;
						}
					}
				}
				else
				{
					if(!StringUtils.hasText(beginDate) && !StringUtils.hasText(endDate))
					{
						//此类别下暂时没有添加商品
						result = 1;
						flag = true;
					}
					else
					{
						//此日期之间没有任何商品
						result = 2;
						flag = true;
					}
				}
			}
			else
			{
				//传递参数为空操作错误
				result = 3;
				flag = true;
			}
		} 
		catch (Exception e)
		{
			//网络异常
			result = 4;
			flag = true;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime));
		map.put("result", result);
		map.put("count", num);
		map.put("total", all);
		map.put("iscompleted", flag);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		writeObjectToResponse(datas, ContentType.application_json);
	}
	/**
	 * 生成健康方案静态页
	 */
	public void healthypla() throws Exception
	{
		long startTime = System.currentTimeMillis();
		String strId = getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		String beginDate = null;
		String endDate = null;
		int num = 0;
		int all = 0;
		boolean flag = false;
		try
		{
			this.setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				//程序控制变量
				String tParam = getRequest().getParameter("tParam");
				//开始日期
				beginDate = getRequest().getParameter("beginDate");
				//结束日期
				endDate = getRequest().getParameter("endDate");
				//分页参数
				String offSet = getRequest().getParameter("offSet");
				//查询总数
				all = Integer.parseInt(String.valueOf(getRequest().getParameter("total")));
				//生成静态页数量
				num = Integer.parseInt(String.valueOf(getRequest().getParameter("buildnum")));
				//分条件查询
				Map<String, Object> ction = new HashMap<String, Object>();
				if(StringUtils.hasText(beginDate))
				{
					ction.put("beginDate", beginDate);
				}
				if(StringUtils.hasText(endDate))
				{
					ction.put("endDate", endDate);
				}
				if(StringUtils.hasText(offSet))
				{
					ction.put("offSet", offSet);
					ction.put("pageSize", 5);
				}
				//ction.put("id", 1);
				//获取商品总数
				if(tParam.trim().equals("0"))
				{
					List<Map<String, Object>> mtotal = opensqlmanage.selectForListByMap(ction, "t_healthy_plan.selectHealthyPlanCount");
					all = Integer.parseInt(String.valueOf(mtotal.get(0).get("total")));
				}
				List<HealthyPlanContentModel> list = null;
				if(flag == false)
				{
					list = thealthyplanmanager.getHealthyPlanContent(ction);
				}
				if(list != null && list.size() > 0)
				{
					for(HealthyPlanContentModel obj : list)
					{
						//重新封装模板数据
						HashMap<Object, Object> root = new HashMap<Object, Object>();
						root.put("obj", obj);
						String staticPageUrl = getTemplateUrl(strId, getRequest(),"4")+obj.getId()+".html";
						//删除原来静态页
						File oldFile = new File(staticPageUrl);
						if(oldFile.exists())
						{
							oldFile.delete();
						}
						//生成新静态页
						UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),staticPageUrl);
						//生成静态页个数
						++num;
						//设置完成状态
						if(all == num)
						{
							result = 0;
							flag = true;
							break;
						}
					}
				}
				else
				{
					if(!StringUtils.hasText(beginDate) && !StringUtils.hasText(endDate))
					{
						//此类别下暂时没有添加商品
						result = 1;
						flag = true;
					}
					else
					{
						//此日期之间没有任何商品
						result = 2;
						flag = true;
					}
				}
			}
			else
			{
				//传递参数为空操作错误
				result = 3;
				flag = true;
			}
		} 
		catch (Exception e)
		{
			//网络异常
			result = 4;
			flag = true;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime));
		map.put("result", result);
		map.put("count", num);
		map.put("total", all);
		map.put("iscompleted", flag);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		writeObjectToResponse(datas, ContentType.application_json);
	}
	
	/**
	 * 生成新健康方案静态页
	 */
	public void newHealthyPlan() throws Exception
	{
		long startTime = System.currentTimeMillis();
		String strId = getRequest().getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		String beginDate = null;
		String endDate = null;
		int num = 0;
		int all = 0;
		boolean flag = false;
		try
		{
			this.setUTF8(getRequest(), getResponse());
			if(strId != null && !strId.trim().equals(""))
			{
				//程序控制变量
				String tParam = getRequest().getParameter("tParam");
				//开始日期
				beginDate = getRequest().getParameter("beginDate");
				//结束日期
				endDate = getRequest().getParameter("endDate");
				//分页参数
				String offSet = getRequest().getParameter("offSet");
				//查询总数
				all = Integer.parseInt(String.valueOf(getRequest().getParameter("total")));
				//生成静态页数量
				num = Integer.parseInt(String.valueOf(getRequest().getParameter("buildnum")));
				//分条件查询
				Map<String, Object> ction = new HashMap<String, Object>();
				if(StringUtils.hasText(beginDate))
				{
					ction.put("beginDate", beginDate);
				}
				if(StringUtils.hasText(endDate))
				{
					ction.put("endDate", endDate);
				}
				if(StringUtils.hasText(offSet))
				{
					ction.put("offSet", offSet);
					ction.put("pageSize", 5);
				}
				//ction.put("id", 22);
				//获取商品总数
				if(tParam.trim().equals("0"))
				{
					List<Map<String, Object>> mtotal = opensqlmanage.selectForListByMap(ction, "t_healthy_plan.selectHealthyPlanCount");
					all = Integer.parseInt(String.valueOf(mtotal.get(0).get("total")));
				}
				List<HealthyPlanContentModel> list = null;
				if(flag == false)
				{
					list = thealthyplanmanager.getHealthyPlanContent(ction);
				}
				if(list != null && list.size() > 0)
				{
					for(HealthyPlanContentModel obj : list)
					{
						//重新封装模板数据
						HashMap<Object, Object> root = new HashMap<Object, Object>();
						root.put("obj", obj);
						String staticPageUrl = getTemplateUrl(strId, getRequest(),"4")+obj.getId()+".html";
						//删除原来静态页
						File oldFile = new File(staticPageUrl);
						if(oldFile.exists())
						{
							oldFile.delete();
						}
						//生成新静态页
						UtilStatic.doTemplatePage(root,getTemplateUrl(strId, getRequest(),"1"),getTemplateUrl(strId, getRequest(),"2"),staticPageUrl);
						//生成静态页个数
						++num;
						//设置完成状态
						if(all == num)
						{
							result = 0;
							flag = true;
							break;
						}
					}
				}
				else
				{
					if(!StringUtils.hasText(beginDate) && !StringUtils.hasText(endDate))
					{
						//此类别下暂时没有添加商品
						result = 1;
						flag = true;
					}
					else
					{
						//此日期之间没有任何商品
						result = 2;
						flag = true;
					}
				}
			}
			else
			{
				//传递参数为空操作错误
				result = 3;
				flag = true;
			}
		} 
		catch (Exception e)
		{
			//网络异常
			result = 4;
			flag = true;
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		map.put("buildTime", (endTime - startTime));
		map.put("result", result);
		map.put("count", num);
		map.put("total", all);
		map.put("iscompleted", flag);
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		datas.add(map);
		writeObjectToResponse(datas, ContentType.application_json);
	}
	
	
	
	public String test() throws Exception
	{
		Map<String, Object> ction = new HashMap<String, Object>();
		ction.put("offSet", 1);
		ction.put("pageSize", 5);
		List<HealthyPlanContentModel> list = thealthyplanmanager.getHealthyPlanContent(ction);
		HealthyPlanContentModel obj = list.get(0);
		getRequest().setAttribute("obj", obj);
		return "test";
	}
	
	public THealthyPlanManager getThealthyplanmanager() {
		return thealthyplanmanager;
	}

	public void setThealthyplanmanager(THealthyPlanManager thealthyplanmanager) {
		this.thealthyplanmanager = thealthyplanmanager;
	}

	public OpenSqlManage getOpensqlmanage()
	{
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage)
	{
		this.opensqlmanage = opensqlmanage;
	}

	public TCategoryManager getTcategorymanager()
	{
		return tcategorymanager;
	}

	public void setTcategorymanager(TCategoryManager tcategorymanager)
	{
		this.tcategorymanager = tcategorymanager;
	}

	public List<Map<String, Object>> getAllcates()
	{
		return allcates;
	}

	public void setAllcates(List<Map<String, Object>> allcates)
	{
		this.allcates = allcates;
	}

	public List<TCategory> getCateList()
	{
		return cateList;
	}

	public void setCateList(List<TCategory> cateList)
	{
		this.cateList = cateList;
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

	public TStatic getTstatic()
	{
		return tstatic;
	}

	public void setTstatic(TStatic tstatic)
	{
		this.tstatic = tstatic;
	}

	public TStaticManager getTstaticmanager()
	{
		return tstaticmanager;
	}

	public void setTstaticmanager(TStaticManager tstaticmanager)
	{
		this.tstaticmanager = tstaticmanager;
	}
	
	/**
	 * 根据需要获取模板文件路径
	 */
	public String getTemplateUrl(String strId, HttpServletRequest req, String returnType)
	{
		TStatic tst = null;
		String basePath = null;
		String filePath = null;
		try
		{
			tst = tstaticmanager.selectByPrimaryKey(Long.parseLong(strId));
			basePath = req.getSession().getServletContext().getRealPath("/");
			filePath = basePath+tst.getTemplatePath()+"/"+tst.getTemplateName();
			//获取模板文件所在路径
			if(returnType.trim().equals("1"))
			{
				return basePath+tst.getTemplatePath();
			}
			//获取模板文件名称
			if(returnType.trim().equals("2"))
			{
				return tst.getTemplateName();
			}
			//获取模板文件所在全路径
			if(returnType.trim().equals("3"))
			{
				return filePath;
			}
			//获取模板文件输出全路径
			if(returnType.trim().equals("4"))
			{
				return basePath+tst.getOutPath();
			}
		} 
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return filePath;
	}
	
	/**
	 * 设置服务器客服端请求字符集
	 */
	public void setUTF8(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
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

	public TGoodsManager getTgoodsmanager()
	{
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager)
	{
		this.tgoodsmanager = tgoodsmanager;
	}

	public List<TGoodsImages> getListi()
	{
		return listi;
	}

	public void setListi(List<TGoodsImages> listi)
	{
		this.listi = listi;
	}

	public List<GoodsPremiumsModel> getListp()
	{
		return listp;
	}

	public void setListp(List<GoodsPremiumsModel> listp)
	{
		this.listp = listp;
	}

	public List<GoodsGroupModel2> getListg()
	{
		return listg;
	}

	public void setListg(List<GoodsGroupModel2> listg)
	{
		this.listg = listg;
	}

	public List<Map<Long, String>> getLists()
	{
		return lists;
	}

	public void setLists(List<Map<Long, String>> lists)
	{
		this.lists = lists;
	}

	public List<Map<String, String>> getListpc()
	{
		return listpc;
	}

	public void setListpc(List<Map<String, String>> listpc)
	{
		this.listpc = listpc;
	}

	public Map<String, Object> getGoods()
	{
		return goods;
	}

	public void setGoods(Map<String, Object> goods)
	{
		this.goods = goods;
	}

	public TGoodsExtendWithBLOBs getGoodse()
	{
		return goodse;
	}

	public void setGoodse(TGoodsExtendWithBLOBs goodse)
	{
		this.goodse = goodse;
	}

	public String getGoodsgidsm()
	{
		return goodsgidsm;
	}

	public void setGoodsgidsm(String goodsgidsm)
	{
		this.goodsgidsm = goodsgidsm;
	}

	public GoodsFormBeanModel2 getGoodsf()
	{
		return goodsf;
	}

	public void setGoodsf(GoodsFormBeanModel2 goodsf)
	{
		this.goodsf = goodsf;
	}

	public List<Map<String, Object>> getGoodsArray()
	{
		return goodsArray;
	}

	public void setGoodsArray(List<Map<String, Object>> goodsArray)
	{
		this.goodsArray = goodsArray;
	}

	public TSysParameterManager getTsysparametermanager()
	{
		return tsysparametermanager;
	}

	public void setTsysparametermanager(TSysParameterManager tsysparametermanager)
	{
		this.tsysparametermanager = tsysparametermanager;
	}
	
}