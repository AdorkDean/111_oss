package com.rc.portal.webapp.action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import com.opensymphony.webwork.ServletActionContext;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.commons.freemarker.UtilStatic;
import com.rc.portal.commons.DataUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.service.TGoodsImagesManager;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TGoodsPremiumsManager;
import com.rc.portal.service.TGroupGoodsManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TCategoryExample;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExtendWithBLOBs;
import com.rc.portal.vo.TGoodsImages;
import com.rc.portal.vo.TGoodsImagesExample;
import com.rc.portal.vo.TGoodsPremiums;
import com.rc.portal.vo.TGoodsPrice;
import com.rc.portal.vo.TGroupGoods;
import com.rc.portal.webapp.model.GoodsFormBeanModel;
import com.rc.portal.webapp.model.GoodsGroupModel;
import com.rc.portal.webapp.model.GoodsGroupModel2;
import com.rc.portal.webapp.model.GoodsPremiumsModel;
import com.rc.portal.webapp.model.NewGoodsFormBean;
import com.rc.portal.webapp.model.PriceFormBeanModel;
import com.rc.portal.webapp.model.PriceIDFormBean;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings({"unchecked","static-access","rawtypes"})
public class GoodsAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	/**商品*/
	private TGoods goods;
	/**商品封装*/
	private NewGoodsFormBean newGoods;
	/**价格ID封装*/
	private PriceIDFormBean priceidformbean;
	/**商品扩展*/
	private TGoodsExtendWithBLOBs goodse;
	/**商品价格扩展*/
	private TGoodsPrice tgoodsprice;
	private TGoodsManager tgoodsmanager;
	private TGoodsImagesManager tgoodsimagesmanager;
	private TGoodsPremiumsManager tgoodspremiumsmanager;
	private TGroupGoodsManager tgroupgoodsmanager;
	/**图片集合*/
	private List<TGoodsImages> listi;
	/**赠品集合*/
	private List<GoodsPremiumsModel> listp;
	/**组合商品集合*/
	private List<GoodsGroupModel> listg;
	private GoodsFormBeanModel goodsf = new GoodsFormBeanModel();
	private String fileFileName;
	private String fileContentType;
	private String fileDatas;
	private File file;
	private String type;
	private Integer goodsType = -1;
	
	/**生成excl*/
	private String url;
	/**限制图片大小为5M*/
	private final Long MAXSIZE = 1024 * 1024 * 50L;
	private TCategoryManager tcategorymanager;

	/**价格封装实体*/
	private PriceFormBeanModel priceformbeanmodel;
	
	public NewGoodsFormBean getNewGoods()
	{
		return newGoods;
	}

	public void setNewGoods(NewGoodsFormBean newGoods)
	{
		this.newGoods = newGoods;
	}

	/**
	 * 修改图片信息
	 */
	public void upImg() throws Exception
	{
		String id = getRequest().getParameter("id");
		String sort = getRequest().getParameter("sort");
		if (StringUtils.hasText(id) && StringUtils.hasText(sort))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			TGoodsImages record = new TGoodsImages();
			record.setSort(Integer.parseInt(sort));
			TGoodsImagesExample example = new TGoodsImagesExample();
			example.createCriteria().andIdEqualTo(Long.parseLong(id));
			long n = tgoodsimagesmanager.updateByExampleSelective(record,
					example);
			PrintWriter out = getResponse().getWriter();
			out.print(n);
			out.close();
		}
	}

	/**
	 * 商品编号,批准文号,条形码 唯一验证
	 */
	public void onlyVerification() throws Exception
	{
		// goodsno approvalNumber barCode
		String id = getRequest().getParameter("id");
		String vals = getRequest().getParameter("vals");
		String type = getRequest().getParameter("type");
		if (StringUtils.hasText(vals))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", Long.parseLong(id));
			if ("1".equals(type))
			{
				map.put("goodsno", vals);
			} else if ("2".equals(type))
			{
				map.put("approvalNumber", vals);
			} else if ("3".equals(type))
			{
				map.put("barCode", vals);
			}
			int n = (Integer) opensqlmanage.selectForObjectByMap(map,
					"t_goods.selectGoodsBybab");
			out.print(n);
			out.close();
		}
	}

	/**
	 * 添加规格
	 */
	public void addSpec() throws Exception
	{
		String id = getRequest().getParameter("id");
		String specName = getRequest().getParameter("specName");
		if (StringUtils.hasText(id) && StringUtils.hasText(specName))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			long n = tgoodsmanager.insertSpec(Long.parseLong(id), specName);
			PrintWriter out = getResponse().getWriter();
			out.print(n);
			out.close();
		}
	}

	/**
	 * 显示修改商品页面
	 */
	public String goodsGetView() throws Exception
	{
		String id = getRequest().getParameter("id");
		String shortname = getRequest().getParameter("shortname");
		String goodsno = getRequest().getParameter("goodsno");
		if (StringUtils.hasText(id))
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.clear();
			map = tgoodsmanager.selectByPrimaryKey1(Long.parseLong(id));
			goods = (TGoods) map.get("goods");
			newGoods = (NewGoodsFormBean) map.get("newGoods");
			goodse = (TGoodsExtendWithBLOBs) map.get("goodse");
			listi = (List<TGoodsImages>) map.get("listi");
			listp = (List<GoodsPremiumsModel>) map.get("listp");
			listg = (List<GoodsGroupModel>) map.get("listg");
			goodsf = (GoodsFormBeanModel) map.get("goodsf");
			goodsf.setShortname(shortname);
			goodsf.setGoodsno(goodsno);
			if (null != listg && 0 < listg.size())
			{
				for (GoodsGroupModel obj : listg)
				{
					if (1 == obj.getMain_goods())
					{
						goodsf.setGoodsgidsm(obj.getGoodsid() + "");
					}
				}
			}
		}
		return "goods_view";
	}

	/**
	 * 是否组合商品
	 */
	public void ifGroup() throws Exception
	{
		String id = getRequest().getParameter("id");
		if (StringUtils.hasText(id))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", Long.parseLong(id));
			int n = (Integer) opensqlmanage.selectForObjectByMap(map,
					"t_goods.query_group_by_goodsid1_count");
			out.print(n);
			out.close();
		}
	}

	/**
	 * 添加组合
	 */
	public void addGroup() throws Exception
	{
		String gid = getRequest().getParameter("gid");
		String ggid = getRequest().getParameter("ggid");
		String ggnum = getRequest().getParameter("ggnum");
		String ifm = getRequest().getParameter("ifm");
		if (StringUtils.hasText(gid) && StringUtils.hasText(ggid)
				&& StringUtils.hasText(ggnum) && StringUtils.hasText(ifm))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			TGroupGoods record = new TGroupGoods();
			record.setGroupId(Long.parseLong(gid));
			record.setGoodsid(Long.parseLong(ggid));
			record.setGoodsNum(Integer.parseInt(ggnum));
			record.setMainGoods(Integer.parseInt(ifm));
			Long n = tgroupgoodsmanager.insertSelective(record);
			out.print(n);
			out.close();
		}
	}

	/**
	 * 删除组合
	 */
	public void delGroup() throws Exception
	{
		String id = getRequest().getParameter("id");
		String gid = getRequest().getParameter("gid");
		if (StringUtils.hasText(id) && StringUtils.hasText(gid))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			int n = tgroupgoodsmanager.deleteByPrimaryKey(Long.parseLong(id),
					Long.parseLong(gid));
			out.print(n);
			out.close();
		}
	}

	/**
	 * 添加赠品
	 */
	public void addPremiums() throws Exception
	{
		String gid = getRequest().getParameter("gid");
		String gpid = getRequest().getParameter("gpid");
		String gpnum = getRequest().getParameter("gpnum");
		if (StringUtils.hasText(gid) && StringUtils.hasText(gpid)
				&& StringUtils.hasText(gpnum))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			TGoodsPremiums record = new TGoodsPremiums();
			record.setGoodsId(Long.parseLong(gid));
			record.setGoodsum(Integer.parseInt(gpnum));
			record.setPremiumsId(Long.parseLong(gpid));
			Long n = tgoodspremiumsmanager.insertSelective(record);
			out.print(n);
			out.close();
		}
	}

	/**
	 * 删除赠品
	 */
	public void delPremiums() throws Exception
	{
		String id = getRequest().getParameter("id");
		if (StringUtils.hasText(id))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			int n = tgoodspremiumsmanager
					.deleteByPrimaryKey(Long.parseLong(id));
			out.print(n);
			out.close();
		}
	}

	/**
	 * 删除商品图片
	 */
	public void delImgs() throws Exception
	{
		String id = getRequest().getParameter("id");
		if (StringUtils.hasText(id))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			int n = tgoodsimagesmanager.deleteByPrimaryKey(Long.parseLong(id));
			out.print(n);
			out.close();
		}
	}

	/**
	 * 上传商品图片
	 */
	public void upImgs() throws Exception
	{
		long id = 0l;
		String path = null;
		if (StringUtils.hasText(fileDatas))
		{
			String[] datas = fileDatas.split(",");
			Map<String, String> imgmap = upImg(Integer.parseInt(datas[1]));
			path = imgmap.get("imgUrl");
			if (StringUtils.hasText(path))
			{
				TGoodsImages record = new TGoodsImages();
				record.setArtworkUrl(path);
				record.setTitel(datas[0]);
				record.setUserType(Integer.parseInt(datas[1]));
				if (record.getUserType() == 1)
				{
					record.setImageUrl(imgmap.get("scaleUrl"));
				}
				record.setIsdefault(Integer.parseInt(datas[2]));
				record.setSort(Integer.parseInt(datas[3]));
				String goodsid = getRequest().getParameter("goodsid");
				if (StringUtils.hasText(goodsid))
				{
					record.setGoodsid(Long.parseLong(goodsid));
				}
				id = tgoodsimagesmanager.insertSelective(record);
			}
		}
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		Map map = new HashMap();
		map.put("ids", id);
		map.put("path", path);
		map.put("msg", "成功!");
		PrintWriter pu = getResponse().getWriter();
		pu.write(JSONObject.fromObject(map).toString());
		pu.close();
	}

	/**
	 * 修改商品
	 */
	public String goodsUpdate() throws Exception
	{
		UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		Map<String, String> imgmap = upImg(0);
		goods.setAbbreviationPicture(imgmap.get("imgUrl"));
		tgoodsmanager.updateByPrimaryKeySelective1(priceidformbean, priceformbeanmodel, goods, goodse, goodsf,user);
		goodsf.setShortname(URLEncoder.encode(goodsf.getShortname(), "utf-8"));
		return "goods_add";
	}

	/**
	 * 商品发布
	 */
	public void goodsRelease() throws Exception
	{
		String msg = "0";
		String id = getRequest().getParameter("id");
		String type = getRequest().getParameter("type");
		String isRX = "0";
		if (type.equals("2") || type.equals("3"))
		{
			isRX = "1";
		}
		if (StringUtils.hasText(id))
		{
			this.actionStatic(Long.parseLong(id), isRX);
			TGoods record = new TGoods();
			record.setId(Long.parseLong(id));
			record.setIsRelease(1);
			tgoodsmanager.updateByPrimaryKeySelective(record);
			msg = "1";
		}
		PrintWriter pu = getResponse().getWriter();
		pu.write(msg);
		pu.close();
	}

	/**
	 * 添加商品
	 */
	public String goodsAdd() throws Exception
	{
		UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		Map<String, String> imgmap = upImg(0);
		goods.setAbbreviationPicture(imgmap.get("imgUrl"));
		tgoodsmanager.insertSelective(priceformbeanmodel, goods, goodse, goodsf,user);
		return "goods_add";
	}

	public void setUTF8(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException
	{
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
	}

	/**
	 * 商品发布
	 * @param id ：商品ID
	 * @param isRX ：是否处方药
	 * @throws Exception 
	 */
	private void actionStatic(Long id, String isRX) throws Exception
	{
		setUTF8(getRequest(), getResponse());
		HashMap<String, Object> ction = new HashMap<String, Object>();
		ction.put("id", id);
		ction.put("offSet", 0);
		ction.put("pageSize", 1);
		List<Map<String, Object>> mapGlist = tgoodsmanager.selectByPrimaryKey2(ction, "1", isRX);
		if (null != mapGlist && 0 < mapGlist.size())
		{
			Map<String, Object> mapG = mapGlist.get(0);
			Integer isRelease = (Integer) mapG.get("isRelease");
			if (0 == isRelease)
			{//是否发布
				List<TGoodsImages> listi = (List<TGoodsImages>) mapG.get("listi");
				List<GoodsGroupModel2> listg = (List<GoodsGroupModel2>) mapG.get("listg");
				List<Map<Long, String>> lists = (List<Map<Long, String>>) mapG.get("lists");
				List<Map<String, String>> listpc = (List<Map<String, String>>) mapG.get("listpc");
				//重新封装模板数据
				HashMap<Object, Object> root = new HashMap<Object, Object>();
				root.put("goods", mapG);
				root.put("listi", listi);
				root.put("listg", listg);
				root.put("lists", lists);
				root.put("listpc", listpc);

				String staticPageUrl = getSession().getServletContext().getRealPath("/") + "static/product/p/" + id + ".html";
				String templateFileSourceUrl = getSession().getServletContext().getRealPath("/") + "WEB-INF/T/static";
				//删除原来静态页
				File oldFile = new File(staticPageUrl);
				if (oldFile.exists())
				{
					oldFile.delete();
				}
				// 生成新静态页
				String pcFileName = "pc_goods_detail.ftl";
				if (isRX.equals("1"))
				{
					pcFileName = "pc_rx_goods_detail.ftl";
				}
				UtilStatic.doTemplatePage(root, templateFileSourceUrl,pcFileName, staticPageUrl);

				String staticPageUrlwap = getSession().getServletContext().getRealPath("/") + "static/product/m/" + id + ".html";
				String templateFileSourceUrlwap = getSession().getServletContext().getRealPath("/") + "WEB-INF/T/static";
				// 删除原来静态页
				File oldFilewap = new File(staticPageUrlwap);
				if (oldFilewap.exists())
				{
					oldFilewap.delete();
				}
				// 生成新静态页
				String wapFileName = "wapgoodsdetail.ftl";
				if (isRX.equals("1"))
				{
					wapFileName = "rxGoodsdetail.ftl";
				}
				UtilStatic.doTemplatePage(root, templateFileSourceUrlwap, wapFileName, staticPageUrlwap);

				String staticPageUrlapp = getSession().getServletContext().getRealPath("/") + "static/product/a1/a_" + id + ".html";
				String templateFileSourceUrlapp = getSession().getServletContext().getRealPath("/") + "WEB-INF/T/static";
				// 删除原来静态页
				File oldFileapp = new File(staticPageUrlapp);
				if (oldFileapp.exists())
				{
					oldFileapp.delete();
				}
				// 生成新静态页
				UtilStatic.doTemplatePage(root, templateFileSourceUrlapp, "content.ftl", staticPageUrlapp);

				String staticPageUrlapp1 = getSession().getServletContext() .getRealPath("/") + "static/product/a/a_" + id + ".html";
				String templateFileSourceUrlapp1 = getSession().getServletContext().getRealPath("/") + "WEB-INF/T/static";
				// 删除原来静态页
				File oldFileapp1 = new File(staticPageUrlapp1);
				if (oldFileapp1.exists())
				{
					oldFileapp1.delete();
				}
				// 生成新静态页
				UtilStatic.doTemplatePage(root, templateFileSourceUrlapp1, "content1.ftl", staticPageUrlapp1);
			}
		}
	}

	/**
	 * 模糊查询生产厂家名称
	 */
	public void likeManufacturer() throws SQLException, IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = this.getRequest().getParameter("q") != null ? this
				.getRequest().getParameter("q").trim() : "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("manuName", content);
		List<Map<String, Object>> list = opensqlmanage.selectForListByMap(map,
				"t_goods.selectManufacturerByMap");
		StringBuilder sb = new StringBuilder();
		for (Map<String, Object> obj : list)
		{
			sb.append(obj.get("name") + "|" + obj.get("id") + "\n");
		}
		out.write(sb.toString()); // 修改失败
		out.close();

	}

	/**
	 * 添加页面跳转
	 */
	public String goodsAddPage() throws Exception
	{
		// goodsf.setYyg(getOption("goods_yiyaoguan_id",2));
		goodsf.setFl(getOption("goods_category_id", 2));
		goodsf.setBrand(getSelectOption("selectBrandByMap"));
		goodsf.setDose(getSelectOption("selectDoseByMap"));
		// goodsf.setManufacturer(getSelectOption("selectManufacturerByMap"));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("syskey", "goods_category_id");
		String flid = null;
		Object obj1 = opensqlmanage.selectForObjectByMap(map,
				"t_sys_parameter.query_sysc");
		if (null != obj1)
		{
			Map<String, String> objmap = (Map<String, String>) obj1;
			flid = objmap.get("sys_value");
		}
		TCategoryExample example = new TCategoryExample();
		example.createCriteria().andIdNotEqualTo(Long.parseLong(flid))
				.andCategoryLevelEqualTo(1);
		List<TCategory> list1 = tcategorymanager.selectByExample(example);
		if (null != list1 && 0 < list1.size())
		{
			example.clear();
			List<Long> listid = new ArrayList<Long>();
			for (TCategory obj : list1)
			{
				listid.add(obj.getId());
			}
			if (null != listid && 0 < listid.size())
			{
				example.createCriteria().andParentIdIn(listid);
				List<TCategory> list2 = tcategorymanager
						.selectByExample(example);
				example.clear();
				List<Long> listid1 = new ArrayList<Long>();
				for (TCategory obj : list2)
				{
					listid1.add(obj.getId());
				}
				if (null != listid1 && 0 < listid1.size())
				{
					example.createCriteria().andParentIdIn(listid1);
					List<TCategory> list3 = tcategorymanager
							.selectByExample(example);
					List<Long> listid2 = new ArrayList<Long>();
					for (TCategory obj : list3)
					{
						listid2.add(obj.getId());
					}
					if (null != listid2 && 0 < listid2.size())
					{
						example.clear();
						example.createCriteria().andParentIdIn(listid2);
						List<TCategory> list4 = tcategorymanager
								.selectByExample(example);
						this.getRequest().setAttribute("list4", list4);
					}

					this.getRequest().setAttribute("list3", list3);
				}
				this.getRequest().setAttribute("list2", list2);
			}
			this.getRequest().setAttribute("list1", list1);
		}
		return "goods_add_page";
	}

	private String getOption(String sqlmap, int level)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("syskey", sqlmap);
		Object obj1 = opensqlmanage.selectForObjectByMap(map,
				"t_sys_parameter.query_sysc");
		String flid = null;
		if (null != obj1)
		{
			Map<String, String> objmap = (Map<String, String>) obj1;
			flid = objmap.get("sys_value");
		}
		map.clear();
		String fl = "";
		map.put("clevel", level);
		map.put("pid", Long.parseLong(flid));
		List<Map<String, Object>> flmap = opensqlmanage.selectForListByMap(map,
				"t_category.selectCategoryByMap");
		for (Map<String, Object> obj : flmap)
		{
			long id = (Long) obj.get("id");
			String name = (String) obj.get("category_name");
			fl = fl + "<option value='" + id + "'>" + name + "</option>";
		}
		return fl;
	}

	private String getSelectOption(String sqlMap)
	{
		String data = "";
		List<Map<String, Object>> flmap = opensqlmanage.selectForListByMap(
				new HashMap(), "t_goods." + sqlMap);
		for (Map<String, Object> obj : flmap)
		{
			long id = (Long) obj.get("id");
			String name = (String) obj.get("name");
			String pinyin = (String) obj.get("pinyin");
			data = data + "<option value='" + id + "'>" + pinyin + "|" + name
					+ "</option>";
		}
		return data;
	}

	/**
	 * 获取下级分类
	 */
	public void getCategory() throws Exception
	{
		String zid = getRequest().getParameter("id");
		if (StringUtils.hasText(zid))
		{
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out = getResponse().getWriter();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pid", Long.parseLong(zid));
			List<Map<String, Object>> flmap = opensqlmanage.selectForListByMap(
					map, "t_category.selectCategoryByMap");

			String zfl = "";
			for (Map<String, Object> obj : flmap)
			{
				long id = (Long) obj.get("id");
				String name = (String) obj.get("category_name");
				zfl = zfl + "<option value='" + id + "'>" + name + "</option>";
			}
			out.print(zfl);
			out.close();
		}
	}

	/**
	 * 分页查询所有商品、商品管理
	 */
	public String goodsList() throws Exception
	{
		Map map = new HashMap();
		if(StringUtils.hasText(goodsf.getShortname()))
		{
			map.put("shortname", goodsf.getShortname().trim());
		}
		if(StringUtils.hasText(goodsf.getGoodsno()))
		{
			map.put("goodsno", goodsf.getGoodsno().trim());
		}
		if(StringUtils.hasText(type))
		{
			map.put("type", type);
		}
		if (goodsType != null && goodsType != -1)
		{
			map.put("goodsType", goodsType);
		}
		rs.setP_pageSize(20);
		
		String status = this.getRequest().getParameter("status");
		if (status != null && !"-1".equals(status))
		{
			String price_type = this.getRequest().getParameter("price_type");
			map.put("status", status);
			map.put("price_type", price_type);
			this.getRequest().setAttribute("status", status);
			this.getRequest().setAttribute("price_type", price_type);
		}
		pw = opensqlmanage.selectForPageByMap_drug(map,"t_goods.query_record_count_bywhile", "t_goods.query_record_bywhile",rs.getP_curPage(), rs.getP_pageSize());
		if ("2" == type || "2".equals(type))
		{
			String path = getRequest().getSession().getServletContext().getRealPath("");
			path = path + "/excel_temp/" + UtilDate.getDate() + System.currentTimeMillis() + ".xls";
			List lists = export();
			String s[][] = getArray(pw.getResult());
			ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		} 
		else
		{
			return "goods_list";
		}
	}

	public List<String> export()
	{
		List list = new ArrayList();
		list.add("简称");
		list.add("全称");
		list.add("商品编码");
		list.add("PC销售价");
		list.add("WAP销售价");
		list.add("APP销售价");
		list.add("PC上下架状态");
		list.add("WAP上下架状态");
		list.add("APP上下架状态");
		list.add("规格");
		list.add("批准文号");
		list.add("访问地址");
		return list;
	}

	// 存放二维数组
	public String[][] getArray(List couponList)
	{
		String[][] s = new String[couponList.size()][15];
		for (int i = 0; i < couponList.size(); i++)
		{
			Map m = (Map) couponList.get(i);
			s[i][0] = m.get("short_name") == null ? " " : m.get("short_name")
					.toString();
			s[i][1] = m.get("goods_name") == null ? " " : m.get("goods_name")
					.toString();
			if (m.get("goodsno") != null)
			{
				s[i][2] = m.get("goodsno").toString();
			} else
			{
				s[i][2] = "";

			}
			if (m.get("pc_price") != null)
			{
				s[i][3] = m.get("pc_price").toString();
			} else
			{
				s[i][3] = "";
			}
			if (m.get("wap_price") != null)
			{
				s[i][4] = m.get("wap_price").toString();
			} else
			{
				s[i][4] = "";
			}

			if (m.get("app_price") != null)
			{
				s[i][5] = m.get("app_price").toString();
			} else
			{
				s[i][5] = "";
			}
			if (m.get("pc_status") != null)
			{
				if ( (Long)m.get("pc_status") == 1)
				{
					s[i][6] = "上架";
				} else
				{
					s[i][6] = "下架";
				}
			} else
			{
				s[i][6] = "";
			}

			if (m.get("wap_status") != null)
			{
				if ((Long) m.get("wap_status") == 1)
				{
					s[i][7] = "上架";
				} else
				{
					s[i][7] = "下架";
				}
			} else
			{
				s[i][7] = "";
			}

			if (m.get("app_status") != null)
			{
				if ((Long) m.get("app_status") == 1)
				{
					s[i][8] = "上架";
				} else
				{
					s[i][8] = "下架";
				}
			} else
			{
				s[i][8] = "";
			}
			s[i][9] = m.get("spec") == null ? " " : m.get("spec").toString();
			s[i][10] = m.get("approval_number") == null ? " " : m.get(
					"approval_number").toString();
			s[i][11] = "http://www.111yao.com/p/" + m.get("id").toString()
					+ ".html";
		}
		return s;
	}

	/**
	 * 查询赠品商品
	 */
	public void goodsPremiumsList() throws Exception
	{
		String goodspids = getRequest().getParameter("goodspids");
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.hasText(goodsf.getShortname()))
		{
			map.put("shortname", goodsf.getShortname());
		}
		if (StringUtils.hasText(goodsf.getGoodsno()))
		{
			map.put("goodsno", goodsf.getGoodsno());
		}
		if (StringUtils.hasText(goodspids))
		{
			map.put("goodspid", goodspids.substring(0, goodspids.length() - 1));
		}
		List list = opensqlmanage.selectForListByMap(map,
				"t_goods.query_premiums_record");
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		map.clear();
		StringBuffer html = new StringBuffer();
		for (Object object : list)
		{
			Map<String, Object> objmap = (Map<String, Object>) object;
			long id = (Long) objmap.get("id");
			String short_name = (String) objmap.get("short_name");
			String spec = (String) objmap.get("spec");
			html.append("<tr>");
			html.append("<td>" + short_name + "</td>");
			html.append("<td>" + spec + "</td>");
			html.append("<td><input type='text' name='trds' id='num" + id
					+ "' value='1'/></td>");
			html.append("<td id='ptds" + id
					+ "'><a href='javascript:void(0)' onclick='addpre(&quot;"
					+ id + "&quot;,&quot;" + short_name + "&quot;,&quot;"
					+ spec + "&quot;)'>添加</a></td>");
			html.append("</tr>");
		}
		PrintWriter pu = getResponse().getWriter();
		pu.write(html.toString());
		pu.close();
	}

	/**
	 * 查询组合商品
	 */
	public void goodsGroupList() throws Exception
	{
		String goodsgid = getRequest().getParameter("goodsgid");
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.hasText(goodsf.getShortname()))
		{
			map.put("shortname", goodsf.getShortname());
		}
		if (StringUtils.hasText(goodsf.getGoodsno()))
		{
			map.put("goodsno", goodsf.getGoodsno());
		}
		if (StringUtils.hasText(goodsgid))
		{
			map.put("goodsgid", goodsgid.substring(0, goodsgid.length() - 1));
		}
		List list = opensqlmanage.selectForListByMap(map,
				"t_goods.query_group_record");
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		map.clear();
		StringBuffer html = new StringBuffer();
		for (Object object : list)
		{
			Map<String, Object> objmap = (Map<String, Object>) object;
			long id = (Long) objmap.get("id");
			String short_name = (String) objmap.get("short_name");
			String spec = (String) objmap.get("spec");
			html.append("<tr>");
			html.append("<td>" + short_name + "</td>");
			html.append("<td>" + spec + "</td>");
			html.append("<td><select id='ism"
					+ id
					+ "'><option value='0'>否</option><option value='1'>是</option></select></td>");
			html.append("<td><input type='text' name='trds' id='num" + id
					+ "' value='1'/></td>");
			html.append("<td id='gtds" + id
					+ "'><a href='javascript:void(0)' onclick='addgroup(&quot;"
					+ id + "&quot;,&quot;" + short_name + "&quot;,&quot;"
					+ spec + "&quot;)'>添加</a></td>");
			html.append("</tr>");
		}
		PrintWriter pu = getResponse().getWriter();
		pu.write(html.toString());
		pu.close();
	}

	private Map<String, String> upImg(int type) throws Exception
	{
		String imgUrl = null;
		String scaleUrl = null;
		String basePath = this.getRequest().getSession().getServletContext()
				.getRealPath("/");
		if (null != fileFileName && !"".equals(fileFileName))
		{
			String[] itype = fileFileName.split("\\.");
			if (checkFileType(itype[itype.length - 1]))
			{
				if (file.length() <= MAXSIZE)
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("syskey", "goods_abbreviationpicture_path");
					Object obj = opensqlmanage.selectForObjectByMap(map,
							"t_sys_parameter.query_sysc");
					String diskPath = "";
					if (null != obj)
					{
						Map<String, String> objmap = (Map<String, String>) obj;
						diskPath = objmap.get("sys_value");
					}
					String fullPath = diskPath
							+ "/"
							+ getDiskName()
							+ "/"
							+ DigestUtils.md5Hex(String.valueOf(System
									.currentTimeMillis() + Math.random()));
					imgUrl = fullPath + "." + itype[itype.length - 1];
					String fullName = basePath + imgUrl;
					// System.out.println(fullName);
					File uploadFile = new File(fullName);
					FileUtils.copyFile(file, uploadFile);// 上传图片

					map.clear();
					map.put("syskey", "is_watermark");
					Object obj3 = opensqlmanage.selectForObjectByMap(map,
							"t_sys_parameter.query_sysc");
					String watermark = "0";
					if (null != obj3)
					{
						Map<String, String> objmap = (Map<String, String>) obj3;
						watermark = objmap.get("sys_value");
					}
					if ("1" == watermark || watermark.equals("1"))
					{
						pressImage(fullName, 0, 0);// 水印
					}
					if (1 == type)
					{//pc图片压缩
						map.clear();
						map.put("syskey", "goods_pcimg_ys");
						Object obj1 = opensqlmanage.selectForObjectByMap(map,
								"t_sys_parameter.query_sysc");
						String ysb = null;
						if (null != obj)
						{
							Map<String, String> objmap = (Map<String, String>) obj1;
							ysb = objmap.get("sys_value");
						}
						if (null != ysb)
						{
							String[] ysbs = ysb.split(",");
							for (int i = 0; i < ysbs.length; i++)
							{
								String[] ys = ysbs[i].split(":");
								scaleUrl = fullPath;
								scale(fullName, basePath + fullPath + ys[0]
										+ "x" + ys[1] + ".jpg",
										Integer.parseInt(ys[0]),
										Integer.parseInt(ys[1]), true);
							}
						}
					}
				} else
				{
				}
			} else
			{
			}
		}
		Map<String, String> imgmap = new HashMap<String, String>();
		imgmap.put("imgUrl", imgUrl);
		imgmap.put("scaleUrl", scaleUrl);
		return imgmap;
	}

	/**
	 * 检查文件类型
	 * 
	 * @param type
	 * @return
	 */
	public boolean checkFileType(String type)
	{
		boolean flag = false;
		type = type.toLowerCase();
		String[] arrType = { "jpg", "png", "jpeg", "gif" };
		for (String s : arrType)
		{
			if (type.equals(s))
			{
				return true; 
			}
		}
		return flag;
	}

	/**
	 * 缩放图像（按高度和宽度缩放）
	 * 
	 * @param srcImageFile
	 *            源图像文件地址
	 * @param result
	 *            缩放后的图像地址
	 * @param height
	 *            缩放后的高度
	 * @param width
	 *            缩放后的宽度
	 * @param bb
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 */
	public void scale(String srcImageFile, String result, int height, int width, boolean bb) throws Exception
	{
		double ratio = 0.0; // 缩放比例
		File f = new File(srcImageFile);
		BufferedImage bi = ImageIO.read(f);
		Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
		// 计算比例
		if ((bi.getHeight() > height) || (bi.getWidth() > width))
		{
			if (bi.getHeight() > bi.getWidth())
			{
				ratio = (new Integer(height)).doubleValue() / bi.getHeight();
			} else
			{
				ratio = (new Integer(width)).doubleValue() / bi.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			itemp = op.filter(bi, null);
		}
		if (bb)
		{// 补白
			BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			if (width == itemp.getWidth(null))
			{
				g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,itemp.getWidth(null), itemp.getHeight(null),Color.white, null);
			}
			else
			{
				g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,itemp.getWidth(null), itemp.getHeight(null),Color.white, null);
			}
			g.dispose();
			itemp = image;
		}
		ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
	}

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param targetImg
	 *            -- 目标文件
	 * @param x
	 *            --x坐标
	 * @param y
	 *            --y坐标
	 * @throws Exception
	 */
	public void pressImage(String targetImg, int x, int y) throws Exception
	{
		String basePath = this.getRequest().getSession().getServletContext()
				.getRealPath("/");
		Map<String, String> map = new HashMap<String, String>();
		map.put("syskey", "goods_sy_path");
		Object obj = opensqlmanage.selectForObjectByMap(map,
				"t_sys_parameter.query_sysc");
		String diskPath = "";
		if (null != obj)
		{
			Map<String, String> objmap = (Map<String, String>) obj;
			diskPath = objmap.get("sys_value");
		}
		File _filebiao = new File(basePath + diskPath);
		if (_filebiao.exists())
		{
			// 目标文件
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			// 水印文件
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.drawImage(src_biao, wideth - wideth_biao, height - height_biao,
					wideth_biao, height_biao, null);// 右下角
			// 水印文件结束
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		}
	}

	public String getDiskName()
	{
		String diskname = "";
		DateFormat df = new SimpleDateFormat("yyyyMM");
		diskname = df.format(new Date());
		return diskname;
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

	public OpenSqlManage getOpensqlmanage()
	{
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage)
	{
		this.opensqlmanage = opensqlmanage;
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

	public TGoods getGoods()
	{
		return goods;
	}

	public void setGoods(TGoods goods)
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

	public TGoodsManager getTgoodsmanager()
	{
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager)
	{
		this.tgoodsmanager = tgoodsmanager;
	}

	public String getFileFileName()
	{
		return fileFileName;
	}

	public void setFileFileName(String fileFileName)
	{
		this.fileFileName = fileFileName;
	}

	public String getFileContentType()
	{
		return fileContentType;
	}

	public void setFileContentType(String fileContentType)
	{
		this.fileContentType = fileContentType;
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public TGoodsImagesManager getTgoodsimagesmanager()
	{
		return tgoodsimagesmanager;
	}

	public void setTgoodsimagesmanager(TGoodsImagesManager tgoodsimagesmanager)
	{
		this.tgoodsimagesmanager = tgoodsimagesmanager;
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

	public TGoodsPremiumsManager getTgoodspremiumsmanager()
	{
		return tgoodspremiumsmanager;
	}

	public void setTgoodspremiumsmanager(
			TGoodsPremiumsManager tgoodspremiumsmanager)
	{
		this.tgoodspremiumsmanager = tgoodspremiumsmanager;
	}

	public TGroupGoodsManager getTgroupgoodsmanager()
	{
		return tgroupgoodsmanager;
	}

	public void setTgroupgoodsmanager(TGroupGoodsManager tgroupgoodsmanager)
	{
		this.tgroupgoodsmanager = tgroupgoodsmanager;
	}

	public List<GoodsGroupModel> getListg()
	{
		return listg;
	}

	public void setListg(List<GoodsGroupModel> listg)
	{
		this.listg = listg;
	}

	public GoodsFormBeanModel getGoodsf()
	{
		return goodsf;
	}

	public void setGoodsf(GoodsFormBeanModel goodsf)
	{
		this.goodsf = goodsf;
	}

	public String getFileDatas()
	{
		return fileDatas;
	}

	public void setFileDatas(String fileDatas)
	{
		this.fileDatas = fileDatas;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Integer getGoodsType()
	{
		return goodsType;
	}

	public void setGoodsType(Integer goodsType)
	{
		this.goodsType = goodsType;
	}

	public TGoodsPrice getTgoodsprice()
	{
		return tgoodsprice;
	}

	public void setTgoodsprice(TGoodsPrice tgoodsprice)
	{
		this.tgoodsprice = tgoodsprice;
	}
	
	public TCategoryManager getTcategorymanager()
	{
		return tcategorymanager;
	}

	public void setTcategorymanager(TCategoryManager tcategorymanager)
	{
		this.tcategorymanager = tcategorymanager;
	}

	public PriceFormBeanModel getPriceformbeanmodel()
	{
		return priceformbeanmodel;
	}

	public void setPriceformbeanmodel(PriceFormBeanModel priceformbeanmodel)
	{
		this.priceformbeanmodel = priceformbeanmodel;
	}

	public Long getMAXSIZE()
	{
		return MAXSIZE;
	}

	public PriceIDFormBean getPriceidformbean()
	{
		return priceidformbean;
	}

	public void setPriceidformbean(PriceIDFormBean priceidformbean)
	{
		this.priceidformbean = priceidformbean;
	}

	/**
	 * 功能: ajax调用,逻辑删除商品并记录日志
	 * 
	 * 返回: {"message":"good is slaing","salesites":"pc,wap,app","statuscode":"3"}
	 * 
	 * 说明 :
	 * 		statuscode : 0 参数错误,1 删除成功,2 商品不存在,3 商品未下架,4 系统异常,删除失败
	 * 		message    : 操作结果说明信息
	 * 		salesites  : 仅当statuscode=3(商品未下架)时 记录未下架的客户端(以','分隔),如 pc,wap,app
	 * 
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public void ajaxDeleteGoodById() throws IOException, SQLException{
		long id = DataUtil.getLongValueFromObject(getRequest().getParameter("id"));
		if(id <= 0){
			Map resultMap = new HashMap();
			resultMap.put("message", "invalid id");
			resultMap.put("statuscode", "0");//参数错误
			writeObjectToResponse(resultMap,ContentType.application_json);
			return;
		}
		
		
		Map paraMap = new HashMap();
		paraMap.put("id", id);
		Object goodsStatusObj = opensqlmanage.selectForObjectByMap(paraMap,
				"t_goods.selectGoodsStatus");
		if(goodsStatusObj != null){
			Map goodsStatusMap = (Map) goodsStatusObj;
			int pc_status = DataUtil.getIntValueFromObject(goodsStatusMap.get("pc_status"));
			int app_status = DataUtil.getIntValueFromObject(goodsStatusMap.get("app_status"));
			int wap_status = DataUtil.getIntValueFromObject(goodsStatusMap.get("wap_status"));
			
			StringBuilder saleSitesSB = new StringBuilder();
			if(pc_status != 2){
				saleSitesSB.append("pc,");
			}
			if(wap_status != 2){
				saleSitesSB.append("wap,");
			}
			if(app_status != 2){
				saleSitesSB.append("app,");
			}
			
			String saleSites = DataUtil.removeLastStr(saleSitesSB,",");
			if(saleSites.length() > 0){
				Map resultMap = new HashMap();
				resultMap.put("salesites", saleSites);				
				resultMap.put("message", "good is slaing");
				resultMap.put("statuscode", "3");//商品未下架
				writeObjectToResponse(resultMap,ContentType.application_json);
				return;
			}else{
				//删除商品，记录操作日志
				UserinfoModel user = (UserinfoModel) this.getSession().getAttribute(ConstantUtil.logincookiename);
				int delFlag = tgoodsmanager.deleteGoodById(user, id);
				
				if(delFlag > 0){
					Map resultMap = new HashMap();
					resultMap.put("message", "success");
					resultMap.put("statuscode", "1");//删除成功
					writeObjectToResponse(resultMap,ContentType.application_json);
					return;
				}else{
					Map resultMap = new HashMap();
					resultMap.put("message", "system exception");
					resultMap.put("statuscode", "4");//系统异常,删除失败
					writeObjectToResponse(resultMap,ContentType.application_json);
					return;
				}
			}
		}else{
			Map resultMap = new HashMap();
			resultMap.put("message", "not exist good");
			resultMap.put("statuscode", "2");//商品不存在
			writeObjectToResponse(resultMap,ContentType.application_json);
			return;
		}
	}


	
}
