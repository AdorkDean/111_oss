package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.YktGoodsManager;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExample;
import com.rc.portal.vo.YktGoods;
import com.rc.portal.vo.YktGoodsExample;
import com.rc.portal.webapp.util.PageResult;

public class YktGoodsAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private YktGoodsManager yktgoodsmanager;
	private TGoodsManager tgoodsmanager;
	private Map goodsMap=new HashMap();
	
	public String yktGoodsList(){
		Map map=new HashMap();
		
		if(model.getGoodsno()!=null&&!"".equals(model.getGoodsno().trim())){
			map.put("goodsno", model.getGoodsno().trim());
		}
		
		if(model.getGoodsname()!=null&&!"".equals(model.getGoodsname().trim())){
			map.put("goodsname", model.getGoodsname().trim());
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "ykt_goods.yktGoodsListByPageCount","ykt_goods.yktGoodsListByPage", rs.getP_curPage(), rs.getP_pageSize());
		return "yktGoodsList";
	}
	
	public void deleteYktGoods() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(model.getId()!=null&&model.getId()!=0){
			flag=yktgoodsmanager.deleteByPrimaryKey(new Long(model.getId().toString()));
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("isYkt", 0);//不支持医卡通
			paraMap.put("yktId", Long.valueOf(model.getId()));
			opensqlmanage.updateByMap(paraMap, "t_cart_item.updateIsYkt");
		}else{
			flag=-1;//添加失败
		}
		write.write(flag.toString());
		write.close();
	}
	
	public String addOrEditYktGoods(){
		Map map=new HashMap();
		if(model.getId()!=null&&model.getId()!=0){
			map.put("id", model.getId());
			goodsMap = (Map)opensqlmanage.selectForObjectByMap_drug(map, "ykt_goods.yktGoodsById");
		}
		return "addOrEditYktGoods";
	}
	
	
	public void selectGoodsByGoodsno() {
		String goodsno = this.getRequest().getParameter("goodsno");
		if (goodsno == null || "".equals(goodsno)) {
			return;
		}
		Map<String, Object> _paramMap = new HashMap<String, Object>();
		_paramMap.put("goodsno", goodsno);
		Map<String, Object> map = (Map<String, Object>) opensqlmanage.selectForObjectByMap(_paramMap,
				"t_goods.selectGoodsByGoodsno");
		ResultData resultData = new ResultData();

		if (map != null && map.get("id") != null && !"".equals(map.get("id").toString())) {
			resultData.setStatus(1);
			resultData.setGoodsName(map.get("goods_name") == null ? "" : map.get("goods_name").toString());
			resultData.setGoodsno(map.get("goodsno") == null ? "" : map.get("goodsno").toString());
			resultData.setId(Long.valueOf(map.get("id").toString()));
			this.writeObjectToResponse(resultData, ContentType.application_json);
		} else {
			resultData.setStatus(0);
			this.writeObjectToResponse(resultData, ContentType.application_json);
		}
	}
	
	public void saveYkrGoods() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		if(model.getGoodsno()!=null&&!"".equals(model.getGoodsno().trim())){
			YktGoodsExample yge=new YktGoodsExample();
			yge.createCriteria().andGoodsNoEqualTo(model.getGoodsno().trim());
			List l = yktgoodsmanager.selectByExample(yge);
			if(l!=null&&l.size()>0){
				flag=-1;//该商品已经添加过
			}else{
				TGoodsExample tge=new TGoodsExample();
				tge.createCriteria().andGoodsnoEqualTo(model.getGoodsno().trim());
				List<TGoods> tg = tgoodsmanager.selectByExample(tge);
				if(tg!=null&&tg.size()>0){
					if(model.getYktid()!=null){
						YktGoods oldYktGoods = yktgoodsmanager.selectByPrimaryKey(Long.valueOf(model.getYktid()));
						if(oldYktGoods!=null&&oldYktGoods.getId()!=null){
							Map<String, Object> paraMap = new HashMap<String, Object>();
							paraMap.put("isYkt", 0);//不支持医卡通
							paraMap.put("yktId", Long.valueOf(oldYktGoods.getId()));
							opensqlmanage.updateByMap(paraMap, "t_cart_item.updateIsYkt");
						}
					}
					
					YktGoods goods=new YktGoods();
					goods.setGoodsNo(tg.get(0).getGoodsno());
					if(model.getYktid()!=null&&model.getYktid()!=0){
						goods.setId(new Long(model.getYktid().toString()));
						flag=yktgoodsmanager.updateByPrimaryKeySelective(goods);
						
						Map<String, Object> paraMap = new HashMap<String, Object>();
						paraMap.put("isYkt", 1);//支持医卡通
						paraMap.put("yktId", Long.valueOf(model.getYktid()));
						opensqlmanage.updateByMap(paraMap, "t_cart_item.updateIsYkt");
					}else{
						Date date=new Date();
						goods.setCreateTime(date);
						Long yktId = yktgoodsmanager.insertSelective(goods);
						flag= yktId.intValue();
						
						Map<String, Object> paraMap = new HashMap<String, Object>();
						paraMap.put("isYkt", 1);//支持医卡通
						paraMap.put("yktId", yktId);
						opensqlmanage.updateByMap(paraMap, "t_cart_item.updateIsYkt");
					}
				}else{
					flag=-3;//商品编号有误
				}
			}
		}else{
			flag=-2;//添加失败
		}
		write.write(flag.toString());
		write.close();
	}
	public class Condition {
		private String goodsno;
		private String goodsname;
		private Integer id;
		private Integer yktid;
		
		
		
		
		
		public Integer getYktid() {
			return yktid;
		}
		public void setYktid(Integer yktid) {
			this.yktid = yktid;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getGoodsno() {
			return goodsno;
		}
		public void setGoodsno(String goodsno) {
			this.goodsno = goodsno;
		}
		public String getGoodsname() {
			return goodsname;
		}
		public void setGoodsname(String goodsname) {
			this.goodsname = goodsname;
		}
		
		
		
	}
	
	
	
	class ResultData{
		private int status;
		private String goodsName;
		private String goodsno;
		private Long id;
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}
		public String getGoodsno() {
			return goodsno;
		}
		public void setGoodsno(String goodsno) {
			this.goodsno = goodsno;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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

	public Map getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(Map goodsMap) {
		this.goodsMap = goodsMap;
	}

	public YktGoodsManager getYktgoodsmanager() {
		return yktgoodsmanager;
	}

	public void setYktgoodsmanager(YktGoodsManager yktgoodsmanager) {
		this.yktgoodsmanager = yktgoodsmanager;
	}

	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}

	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}



	
}
