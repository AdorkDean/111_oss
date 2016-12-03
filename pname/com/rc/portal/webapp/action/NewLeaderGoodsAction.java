package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsBrokerageManager;
import com.rc.portal.service.TOssOperateLogManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.vo.TGoodsBrokerage;
import com.rc.portal.vo.TGoodsBrokerageExample;
import com.rc.portal.vo.TOssOperateLog;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;

public class NewLeaderGoodsAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private Map goodsMap=new HashMap();
	private TGoodsBrokerageManager tgoodsbrokeragemanager;
	private TGoodsBrokerage  brokerage;
	private TOssOperateLogManager tossoperatelogmanager;
	public String getLeaderGoodsList(){
		Map map=new HashMap();
		if(model.getGoodsName()!=null&&!"".equals(model.getGoodsName().trim())){
			map.put("goodsName", model.getGoodsName().trim());
		}
		
		if(model.getGoodsno()!=null&&!"".equals(model.getGoodsno().trim())){
			map.put("goodsno", model.getGoodsno().trim());
		}
		if(model.getStatus()!=null&&model.getStatus()!=-1){
			map.put("status", model.getStatus());
		}
		if(model.getStart_date()!=null&&!"".equals(model.getStart_date())){
			map.put("start_date", model.getStart_date().trim());
		}
		if(model.getEnd_date()!=null&&!"".equals(model.getEnd_date())){
			map.put("end_date", model.getEnd_date().trim());
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_goods_brokerage.selectGoodsBrokerageByPageCount", "t_goods_brokerage.selectGoodsBrokerageByPage", rs.getP_curPage(), rs.getP_pageSize());
		return "getLeaderGoodsList";
	}
	
	public String addOrEdit(){
		Map map=new HashMap();
		if(model.getId()!=null&&!"".equals(model.getId().trim())){
			map.put("id", model.getId().trim());
			goodsMap=(Map) opensqlmanage.selectForObjectByMap_drug(map, "t_goods_brokerage.selectLeaderGoodsById");
			return "edit";
		}else{
			String fl = getOption();
			this.getRequest().setAttribute("fl", fl);
			return "add";
		}
	}
	
	
	
	private String getOption()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String fl = "";
		map.put("clevel", 1);
		List<Map<String, Object>> flmap = opensqlmanage.selectForListByMap(map,"t_category.selectCategoryByMap");
		for (Map<String, Object> obj : flmap)
		{
			long id = (Long) obj.get("id");
			String name = (String) obj.get("category_name");
			fl = fl + "<option value='" + id + "'>" + name + "</option>";
		}
		return fl;
	}
	
	
	public void updateGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		brokerage.setAuditStatus(0);
		brokerage.setAuditRemark("改变商品佣金价格");
		brokerage.setApplyUser(user.getUsername());
		brokerage.setCreateDt(new Date());
		Integer flag = tgoodsbrokeragemanager.updateByPrimaryKeySelective(brokerage);
		addLogs(2, brokerage.getId(), "修改佣金商品价格", user.getUsername().trim());
		out.write(flag.toString());
		out.close();
	}
	public void deleteGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		Integer flag=0;
		if(model.getId()!=null&&!"0".equals(model.getId().trim())){
			flag=tgoodsbrokeragemanager.deleteByPrimaryKey(new Long(model.getId().trim()));
			addLogs(2, new Long(model.getId().trim()), "删除佣金商品", user.getUsername().trim());
		}
		out.write(flag.toString());
		out.close();
	}
	public void plDeleteGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		Integer flag=0;
		Integer i=0;
		Integer j=0;
		if(model.getIds()!=null&&!"".equals(model.getIds().trim())){
			String idss=model.getIds().trim();
			if(idss.contains(",")){
				String[] ss = idss.split(",");
				for(String s:ss){
					flag=tgoodsbrokeragemanager.deleteByPrimaryKey(new Long(s));
					addLogs(2, new Long(s.trim()), "删除佣金商品", user.getUsername().trim());
					if(flag>0){
						i++;
					}else{
						j++;
					}
				}
			}else{
				flag=tgoodsbrokeragemanager.deleteByPrimaryKey(new Long(idss));
				addLogs(2, new Long(idss.trim()), "删除佣金商品", user.getUsername().trim());
				if(flag>0){
					i++;
				}else{
					j++;
				}
			}
		}
		
		String message="已经成功删除"+i+"条数据，有"+j+"条数据删除失败";		
		out.write(message);
		out.close();
	}
	
	
	public void tijiaoCheck() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		Integer flag=0;
		Integer i=0;
		Integer j=0;
		if(user!=null){
			if(model.getType()==1){
				brokerage = tgoodsbrokeragemanager.selectByPrimaryKey(new Long(model.getId().trim()));
				brokerage.setAuditStatus(1);
				brokerage.setApplyUser(user.getUsername());
				brokerage.setApplyDt(new Date());
				flag = tgoodsbrokeragemanager.updateByPrimaryKeySelective(brokerage);
				if(flag>0){
					i++;
				}
			}else{
				if(model.getIds()!=null&&!"".equals(model.getIds().trim())){
					if(model.getIds().contains(",")){
						String[] ss = model.getIds().trim().split(",");
						for(String s:ss){
							brokerage = tgoodsbrokeragemanager.selectByPrimaryKey(new Long(s.trim()));
							brokerage.setAuditStatus(1);
							brokerage.setApplyUser(user.getUsername());
							brokerage.setApplyDt(new Date());
							flag = tgoodsbrokeragemanager.updateByPrimaryKeySelective(brokerage);
							addLogs(2, new Long(s.trim()), "提交佣金审核", user.getUsername().trim());
							if(flag>0){
								i++;
							}else{
								j++;
							}
						}
					}else{
						brokerage = tgoodsbrokeragemanager.selectByPrimaryKey(new Long(model.getIds().trim()));
						brokerage.setAuditStatus(1);
						brokerage.setApplyUser(user.getUsername());
						brokerage.setApplyDt(new Date());
						flag = tgoodsbrokeragemanager.updateByPrimaryKeySelective(brokerage);
						addLogs(2, new Long(model.getIds().trim()), "提交佣金审核", user.getUsername().trim());
						if(flag>0){
							i++;
						}else{
							j++;
						}
					}
				}
			}
			
		}
		String message="提交审核成功"+i+"条数据,提交失败"+j+"条数据";
		out.write(message);
		out.close();
		
	}
	public void addGoods() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		String goodsId = this.getRequest().getParameter("goodsId");
		String rebateAmount = this.getRequest().getParameter("rebateAmount");
		
		if(null != rebateAmount && null != goodsId && !("").equals(goodsId) && !("").equals(rebateAmount)){
			try {
				brokerage = new TGoodsBrokerage();
				brokerage.setRebateAmount(new BigDecimal(rebateAmount));
				brokerage.setGoodsId(new Long(goodsId));
				UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
				brokerage.setAuditStatus(0);
				brokerage.setAuditRemark("新添加商品佣金");
				brokerage.setApplyUser(user.getUsername());
				brokerage.setCreateDt(new Date());
				TGoodsBrokerageExample tbe = new TGoodsBrokerageExample();
				tbe.createCriteria().andGoodsIdEqualTo(brokerage.getGoodsId());
				List list = new ArrayList();
				list = tgoodsbrokeragemanager.selectByExample(tbe);
				if (null != list && list.size() > 0) {
					out.print("-1"); //已经参加过佣金商品
				} else {
					Long id = tgoodsbrokeragemanager.insertSelective(brokerage);
					addLogs(2, id, "添加商品佣金", user.getUsername().trim());
					System.out.println(id);
					if (null != id && id > 0) {
						out.print("1");
					} else {
						out.print("-2"); //添加失败
					}
				}
			} catch (Exception e) {
				out.print("-3"); //系统错误
				e.printStackTrace();
			}
		}else{
			out.print("-4"); //参数有误
		}
		out.close();
	}
	
	public void addCategory() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		Integer f=0;//添加成功商品
		Integer j=0;//已参加佣金商品
		Integer i=0;//添加失败商品
		String message="";
		String categoryId = (String) this.getRequest().getParameter("categoryId");
		String rebateAmount = this.getRequest().getParameter("rebateAmount");
		if(categoryId!=null&&!"".equals(categoryId)){
		Map map=new HashMap();
		map.put("categoryid", categoryId);
		List<Map> goodsList = opensqlmanage.selectForListByMap_drug(map, "t_goods_brokerage.selectGoodsByCategoryId");
		UserinfoModel user = (UserinfoModel)getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		if(goodsList!=null&&goodsList.size()>0){
			for(Map m:goodsList){
				TGoodsBrokerageExample tbe = new TGoodsBrokerageExample();
				tbe.createCriteria().andGoodsIdEqualTo(new Long( m.get("goodsid").toString()));
				List list =tgoodsbrokeragemanager.selectByExample(tbe);
				if(list!=null&&list.size()>0){
					j++;
				}else{
					try {
					brokerage = new TGoodsBrokerage();
					brokerage.setRebateAmount(new BigDecimal(rebateAmount));
					brokerage.setGoodsId(new Long( m.get("goodsid").toString()));
					brokerage.setAuditStatus(0);
					brokerage.setAuditRemark("新添加商品佣金");
					brokerage.setApplyUser(user.getUsername());
					brokerage.setCreateDt(new Date());
					Long id = tgoodsbrokeragemanager.insertSelective(brokerage);
					addLogs(2, id, "添加商品佣金", user.getUsername().trim());
					if (null != id && id > 0) {
						f++;
					} else {
						i++;
					}
					} catch (Exception e) {
						message="系统错误" ;//系统错误
						e.printStackTrace();
					}
				}
			}
		}
		message="添加成功"+f+"条数据，已经添加过的商品有"+j+"个，添加失败"+i+"条数据";
		}else{
			message="参数错误";
		}
		
		out.write(message);
		out.close();
	}
	public void addLogs(Integer module,Long id,String remak,String username) throws Exception{
		TOssOperateLog logs=new TOssOperateLog();
		logs.setModuleType(module);
		logs.setRecordId(id);
		logs.setOperationRemake(remak);
		logs.setOperationUsername(username);
		logs.setOperationDt(new Date());
		logs.setCreateDt(new Date());
		tossoperatelogmanager.insertSelective(logs);
	}
	public class Condition {
		private String goodsno;//海典编号
		private String goodsName;//商品名称
		private String id;//领秀商品ID
		private Integer type;//1 单个提交，2为批量提交
		private Integer status;
		private String start_date;
		private String end_date;
		private String ids;//批量提交id集合
		
		
		
		public String getIds() {
			return ids;
		}

		public void setIds(String ids) {
			this.ids = ids;
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

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

	public TGoodsBrokerage getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(TGoodsBrokerage brokerage) {
		this.brokerage = brokerage;
	}

	public TGoodsBrokerageManager getTgoodsbrokeragemanager() {
		return tgoodsbrokeragemanager;
	}

	public void setTgoodsbrokeragemanager(
			TGoodsBrokerageManager tgoodsbrokeragemanager) {
		this.tgoodsbrokeragemanager = tgoodsbrokeragemanager;
	}

	public TOssOperateLogManager getTossoperatelogmanager() {
		return tossoperatelogmanager;
	}

	public void setTossoperatelogmanager(TOssOperateLogManager tossoperatelogmanager) {
		this.tossoperatelogmanager = tossoperatelogmanager;
	}

}
