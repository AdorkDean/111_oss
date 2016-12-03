package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExtendWithBLOBs;
import com.rc.portal.vo.TGoodsImages;
import com.rc.portal.vo.TGoodsPrice;
import com.rc.portal.webapp.model.GoodsFormBeanModel;
import com.rc.portal.webapp.model.GoodsGroupModel;
import com.rc.portal.webapp.model.GoodsPremiumsModel;
import com.rc.portal.webapp.model.NewGoodsFormBean;
import com.rc.portal.webapp.model.PriceIDFormBean;
import com.rc.portal.webapp.util.PageResult;

/**
 * 商品回收站
 * 
 * @author WWF
 * @createTime 2016-8-1 下午2:59:45
 */
public class GoodsTrashAction extends BaseAction {

	private static final long serialVersionUID = 2651637623345254611L;
	private GoodsFormBeanModel goodsf = new GoodsFormBeanModel();
	private Integer goodsType = -1;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private String type;
	/** 图片集合 */
	private List<TGoodsImages> listi;
	/** 赠品集合 */
	private List<GoodsPremiumsModel> listp;
	/** 组合商品集合 */
	private List<GoodsGroupModel> listg;

	/** 商品 */
	private TGoods goods;
	/** 商品封装 */
	private NewGoodsFormBean newGoods;
	/** 价格ID封装 */
	private PriceIDFormBean priceidformbean;
	/** 商品扩展 */
	private TGoodsExtendWithBLOBs goodse;
	/** 商品价格扩展 */
	private TGoodsPrice tgoodsprice;

	private TGoodsManager tgoodsmanager;

	public String trashGoodsList() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(goodsf.getShortname())) {
			map.put("shortname", goodsf.getShortname().trim());
		}
		if (StringUtils.hasText(goodsf.getGoodsno())) {
			map.put("goodsno", goodsf.getGoodsno().trim());
		}
		if (StringUtils.hasText(type)) {
			map.put("type", type);
		}
		if (goodsType != null && goodsType != -1) {
			map.put("goodsType", goodsType);
		}
		rs.setP_pageSize(20);

		String status = this.getRequest().getParameter("status");
		if (status != null && !"-1".equals(status)) {
			String price_type = this.getRequest().getParameter("price_type");
			map.put("status", status);
			map.put("price_type", price_type);
			this.getRequest().setAttribute("status", status);
			this.getRequest().setAttribute("price_type", price_type);
		}
		map.put("is_delete", 1);
		pw = opensqlmanage.selectForPageByMap_drug(map, "t_goods_trash.query_trash_record_count_bywhile",
				"t_goods_trash.query_trash_record_bywhile", rs.getP_curPage(), rs.getP_pageSize());
		return "trash_goods_list";
	}

	/**
	 * 还原回收站商品
	 * 
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void reductionGoods() throws NumberFormatException, SQLException {
		String id = this.getRequest().getParameter("id");
		ReturnData returnData = new ReturnData();
		if (id == null || "".equals(id.trim())) {
			returnData.setStatus(0);
			returnData.setMessage("请选择正确的回收站数据!");
			this.getResponse().setCharacterEncoding("UTF-8");
		} else {
			TGoods tGoods = tgoodsmanager.selectByPrimaryKey(Long.valueOf(id));
			if (tGoods != null) {
				TGoods updateG = new TGoods();
				updateG.setId(tGoods.getId());
				updateG.setIsDelete(0);
				tgoodsmanager.updateByPrimaryKeySelective(updateG);
				returnData.setStatus(1);
				returnData.setMessage("还原成功!");
			} else {
				returnData.setStatus(0);
				returnData.setMessage("请选择正确的回收站数据!");
			}
		}
		this.getResponse().setCharacterEncoding("UTF-8");
		this.writeObjectToResponse(returnData, ContentType.application_json);
	}

	/**
	 * 查看内容信息
	 * @return
	 * @throws Exception
	 */
	public String trashGoodsGetView() throws Exception {
		String id = getRequest().getParameter("id");
		String shortname = getRequest().getParameter("shortname");
		String goodsno = getRequest().getParameter("goodsno");
		if (StringUtils.hasText(id)) {
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
			if (null != listg && 0 < listg.size()) {
				for (GoodsGroupModel obj : listg) {
					if (1 == obj.getMain_goods()) {
						goodsf.setGoodsgidsm(obj.getGoodsid() + "");
					}
				}
			}
		}
		return "trash_goods_view";
	}
	
	public List<String> export() {
		List<String> list = new ArrayList<String>();
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
	public String[][] getArray(List<Map<String, Object>> couponList) {
		String[][] s = new String[couponList.size()][15];
		for (int i = 0; i < couponList.size(); i++) {
			Map<String, Object> m = (Map<String, Object>) couponList.get(i);
			s[i][0] = m.get("short_name") == null ? " " : m.get("short_name").toString();
			s[i][1] = m.get("goods_name") == null ? " " : m.get("goods_name").toString();
			if (m.get("goodsno") != null) {
				s[i][2] = m.get("goodsno").toString();
			} else {
				s[i][2] = "";

			}
			if (m.get("pc_price") != null) {
				s[i][3] = m.get("pc_price").toString();
			} else {
				s[i][3] = "";
			}
			if (m.get("wap_price") != null) {
				s[i][4] = m.get("wap_price").toString();
			} else {
				s[i][4] = "";
			}

			if (m.get("app_price") != null) {
				s[i][5] = m.get("app_price").toString();
			} else {
				s[i][5] = "";
			}
			if (m.get("pc_status") != null) {
				if ((Long) m.get("pc_status") == 1) {
					s[i][6] = "上架";
				} else {
					s[i][6] = "下架";
				}
			} else {
				s[i][6] = "";
			}

			if (m.get("wap_status") != null) {
				if ((Long) m.get("wap_status") == 1) {
					s[i][7] = "上架";
				} else {
					s[i][7] = "下架";
				}
			} else {
				s[i][7] = "";
			}

			if (m.get("app_status") != null) {
				if ((Long) m.get("app_status") == 1) {
					s[i][8] = "上架";
				} else {
					s[i][8] = "下架";
				}
			} else {
				s[i][8] = "";
			}
			s[i][9] = m.get("spec") == null ? " " : m.get("spec").toString();
			s[i][10] = m.get("approval_number") == null ? " " : m.get("approval_number").toString();
			s[i][11] = "http://www.111yao.com/p/" + m.get("id").toString() + ".html";
		}
		return s;
	}

	class ReturnData {
		private int status;
		private String message;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {
	}

	public GoodsFormBeanModel getGoodsf() {
		return goodsf;
	}

	public void setGoodsf(GoodsFormBeanModel goodsf) {
		this.goodsf = goodsf;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}

	public List<TGoodsImages> getListi() {
		return listi;
	}

	public void setListi(List<TGoodsImages> listi) {
		this.listi = listi;
	}

	public List<GoodsPremiumsModel> getListp() {
		return listp;
	}

	public void setListp(List<GoodsPremiumsModel> listp) {
		this.listp = listp;
	}

	public List<GoodsGroupModel> getListg() {
		return listg;
	}

	public void setListg(List<GoodsGroupModel> listg) {
		this.listg = listg;
	}

	public TGoods getGoods() {
		return goods;
	}

	public void setGoods(TGoods goods) {
		this.goods = goods;
	}

	public NewGoodsFormBean getNewGoods() {
		return newGoods;
	}

	public void setNewGoods(NewGoodsFormBean newGoods) {
		this.newGoods = newGoods;
	}

	public PriceIDFormBean getPriceidformbean() {
		return priceidformbean;
	}

	public void setPriceidformbean(PriceIDFormBean priceidformbean) {
		this.priceidformbean = priceidformbean;
	}

	public TGoodsExtendWithBLOBs getGoodse() {
		return goodse;
	}

	public void setGoodse(TGoodsExtendWithBLOBs goodse) {
		this.goodse = goodse;
	}

	public TGoodsPrice getTgoodsprice() {
		return tgoodsprice;
	}

	public void setTgoodsprice(TGoodsPrice tgoodsprice) {
		this.tgoodsprice = tgoodsprice;
	}

}
