package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsSellingRecAdvertisementManager;
import com.rc.portal.vo.TGoodsSellingRecAdvertisement;
import com.rc.portal.webapp.util.PageResult;

/**
 * 商品推荐
 * GoodsSellingRecAction<BR>
 * 创建人:Marlon <BR>
 * 时间：2016-7-20-下午3:21:05 <BR>
 * @version 1.0.0
 */
public class GoodsSellingRecAction extends BaseAction{
	private static final long serialVersionUID = -5721947620579353723L;
	/* 分页 */
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	/*service对象 */
	private TGoodsSellingRecAdvertisementManager tgoodssellingrecadvertisementmanager;
	/*vo对象 */
	private TGoodsSellingRecAdvertisement obj=new TGoodsSellingRecAdvertisement();
	
	private Integer platformType = 1;//平台类型
	
	/**
	 * 根据platformType加载页面
	 * 方法名：list<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-7-22-下午5:01:55 <BR>
	 * @return
	 * @throws Exception String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public String list() throws Exception {
		Map<String,Object> map =new HashMap<String,Object>();
		if(platformType != null){
			map.put("platformType", platformType);
		}
		rs.setP_pageSize(20);
		pw = opensqlmanage.selectForPageByMap_drug(map,
				"t_goods_selling_rec_advertisement.selectAllPushSellingCount",
				"t_goods_selling_rec_advertisement.selectAllPushSelling",
				rs.getP_curPage(), rs.getP_pageSize());
		List list = opensqlmanage.selectForListByMap(map, "t_goods_selling_rec_advertisement.selectCount");
		this.getRequest().setAttribute("listcount", list.get(0));
		return "list";
	}
	
	/**
	 * 根据海典编号查询商品名称
	 * 方法名：ajaxGoodNameBycode<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-7-22-下午5:02:14 <BR>
	 * @throws Exception void<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public void ajaxGoodNameBycode() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String goodsno = getRequest().getParameter("goodsno");
		if(StringUtils.hasText(goodsno)){
			map.put("goodsno", goodsno);
			List list = opensqlmanage.selectForListByMap(map, "t_goods.getGoodsNameByHDNO");
			/* 以流的方式将查询出来的listname返回给ajax的data参数 */
			this.writeObjectToResponse((list==null||list.size()<=0)?"":list.get(0), ContentType.application_json);
		}
	}
	
	/**
	 * 批量保存商品推荐
	 * 方法名：batchSave<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-7-25-下午6:06:55 <BR>
	 * @throws Exception void<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public void batchSave() throws Exception {
		String paramStr = this.getRequest().getParameter("params");
		String str[] = paramStr.split("#");
		PrintWriter out =  getResponse().getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		int num = 0;
		Boolean flag = false;
		for (String s : str) {
			if(s.contains(",")){
				String[] sp = s.split(",");
				/*根据id==sp[0]去查询一次看该id是否存在，存在就执行更新操作*/
				TGoodsSellingRecAdvertisement o=tgoodssellingrecadvertisementmanager.selectByPrimaryKey(new Long(sp[0]));
				/*执行更新操作*/
				if(o!=null&&o.getId()!=null){
					map.clear();
					map.put("id", Long.parseLong(sp[0]));
					map.put("goodsId", Long.parseLong(sp[1]));
					map.put("platformType", Integer.parseInt(sp[3]));
					/*过滤更新端口时重复的goodsno*/
					List l = opensqlmanage.selectForListByMap(map, "t_goods_selling_rec_advertisement.selectExist");
					if(l!=null&&l.size()>0){
						flag = true;//去重操纵
						break;
					}else {
						o.setGoodsId(Long.parseLong(sp[1]));
						o.setWeight(Integer.parseInt(sp[2]));
						o.setPosition(1);
						o.setPlatformType(Integer.parseInt(sp[3]));
						if(tgoodssellingrecadvertisementmanager.updateByPrimaryKeySelective(o)>0){
							num++;
						}
					}
				}else{/*执行插入操作*/
					TGoodsSellingRecAdvertisement ob=new TGoodsSellingRecAdvertisement();
					ob.setGoodsId(Long.parseLong(sp[1]));
					ob.setWeight(Integer.parseInt(sp[2]));
					ob.setPosition(1);
					ob.setPlatformType(Integer.parseInt(sp[3]));
					ob.setCreateDt(new Date());
					if(tgoodssellingrecadvertisementmanager.insertSelective(ob)>0){
						num++;
					}
				}
			}
		}
		if (flag) {
			out.print("warn");
		}else {
			out.print(num==str.length?num:-1);
		}
		out.close();
	}
	
	/**
	 * 删除商品推荐
	 * 方法名：ajaxDel<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-7-26-上午9:34:31 <BR>
	 * @throws Exception void<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public void ajaxDel() throws Exception {
		String opid = this.getRequest().getParameter("opid");
		if(opid != null || !"".equals(opid.trim())){
			PrintWriter out =  getResponse().getWriter();
			int n = tgoodssellingrecadvertisementmanager.deleteByPrimaryKey(Long.parseLong(opid));
			out.print(n);
			out.close();
		}
	}

	/* 利用setter注入对service层对象进行初始化 */
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

	public TGoodsSellingRecAdvertisementManager getTgoodssellingrecadvertisementmanager() {
		return tgoodssellingrecadvertisementmanager;
	}

	public void setTgoodssellingrecadvertisementmanager(
			TGoodsSellingRecAdvertisementManager tgoodssellingrecadvertisementmanager) {
		this.tgoodssellingrecadvertisementmanager = tgoodssellingrecadvertisementmanager;
	}

	public TGoodsSellingRecAdvertisement getObj() {
		return obj;
	}

	public void setObj(TGoodsSellingRecAdvertisement obj) {
		this.obj = obj;
	}
	
	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {
	}

	public Integer getPlatformType() {
		return platformType;
	}

	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}

}
