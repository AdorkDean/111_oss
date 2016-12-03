package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.webwork.ServletActionContext;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TCategoryManager;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TPromGiftManager;
import com.rc.portal.service.TPromItemManager;
import com.rc.portal.service.TPromotionManager;
import com.rc.portal.vo.TCategory;
import com.rc.portal.vo.TCategoryExample;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExample;
import com.rc.portal.vo.TPromGift;
import com.rc.portal.vo.TPromGiftExample;
import com.rc.portal.vo.TPromItem;
import com.rc.portal.vo.TPromItemExample;
import com.rc.portal.vo.TPromotion;
import com.rc.portal.webapp.util.PageResult;

public class PromotionAction extends BaseAction {
	
	private TPromotion prom = new TPromotion();
	private TPromItem promItem = new TPromItem();
	private TPromGift promGift = new TPromGift();
	
	private TPromotionManager tpromotionmanager;
	private TPromItemManager tpromitemmanager;
	private TPromGiftManager tpromgiftmanager;
	private TGoodsManager tgoodsmanager;
	private OpenSqlManage opensqlmanage;
	private TCategoryManager tcategorymanager;
	
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	
	private Map map = new HashMap();
	private String id;
	
	private String flag;
	
    public static void main(String[] args) throws SQLException {
        
       PromotionAction o = new PromotionAction();
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
       o.opensqlmanage = (OpenSqlManage) context.getBean("opensqlmanage");
       List<Long> l = new ArrayList<Long>();
       l.add(60L);
       l.add(61L);
       
       o.addPromToGoods(l, 1);
 }
    
    
    public String getdetail() throws NumberFormatException, SQLException{
    	
    	String id = this.getRequest().getParameter("id");
		TPromotion obj = tpromotionmanager.selectByPrimaryKey(Long.parseLong(id));
		
		System.out.println();
		
		this.getRequest().setAttribute("o", obj);
    	
    	return "detail";
    }
 
   public void cancel() throws SQLException, IOException{
	   
	   PrintWriter out = this.getResponse().getWriter();
	   String id = this.getRequest().getParameter("id");
	   TPromotion record = new TPromotion();
	   record.setId(Long.parseLong(id));
	   record.setStatus(4);
	   int n =  tpromotionmanager.updateByPrimaryKeySelective(record);
	   
	   System.out.println("n-------------"+n);
	 
	   if(n>0)
	   {
		   Map map = new HashMap();
		   map.put("promid", id);
		   opensqlmanage.updateByMap(map, "t_promotion.update-goods-status");
		   
		   TPromItemExample example = new TPromItemExample();
		   example.createCriteria().andPidEqualTo(Long.parseLong(id));
		   tpromitemmanager.deleteByExample(example);
	   }
	   out.print(n);
	   out.close();
   }
   
   
   

   public void giftIsExist() throws NumberFormatException, SQLException, IOException{
	   
	   PrintWriter out = this.getResponse().getWriter();
	   String id = this.getRequest().getParameter("id");
	   TGoods g = tgoodsmanager.selectByPrimaryKey(Long.parseLong(id));
	   System.out.println(g);
	   int n = 0;
	   if(g==null){
		   n = 1;
	   }
	   System.out.println("n=========="+n);
	   out.print(n);
	   out.close();
   }
	

	


	public String  inser_page(){
		return "insert_page";
	}
	
	
	public String list(){
		
		String stimestr = this.getRequest().getParameter("stime");
    	String etimestr = this.getRequest().getParameter("etime");
    	
    	System.out.println("=============>"+prom.getName());
    	if(!"".equals(prom.getName())){
    		map.put("name", prom.getName());
    	}
    	
    	
    	if(prom.getStatus()!=null && prom.getStatus()!=0){
    		map.put("status", prom.getStatus());
    	}
    	
    	if(null!=stimestr && !"".equals(stimestr)){
    		map.put("stime",stimestr);
    	}
    	
    	if(null!=etimestr && !"".equals(etimestr)){
    		map.put("etime",etimestr);
    	}
    	
    	
		pw = opensqlmanage.selectForPageByMap_drug(map,"t_promotion.data-count", "t_promotion.data",  rs.getP_curPage(), rs.getP_pageSize());
		
		this.getRequest().setAttribute("stime", stimestr);
		this.getRequest().setAttribute("etime", etimestr);
		    
		return "list";
	}
	
	public void insert() throws SQLException, IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		
		String action = this.getRequest().getParameter("action");
		String stimestr = this.getRequest().getParameter("stime");
    	String etimestr = this.getRequest().getParameter("etime");
    	String isticket = this.getRequest().getParameter("isticket");
    	String platfrom = this.getRequest().getParameter("platfrom");
    	
    	System.out.println("platfrom----------->"+platfrom);
    	
    	Date stime = com.rc.portal.util.DateUtil.strDateConvertToDate(stimestr, ShortBuyAction.format);
    	Date etime = com.rc.portal.util.DateUtil.strDateConvertToDate(etimestr, ShortBuyAction.format);
    	
    	prom.setCreateTime(new Date());
    	prom.setStartTime(stime);
    	prom.setEndTime(etime);
    	prom.setStatus(1);
    	prom.setScope(1);
    	prom.setDescrib(platfrom);
    	
    	if("1".equals(isticket)){
			prom.setIsTiket(1);
		}else{
			prom.setIsTiket(2);
		}
    	
    	long mainId =0;
    	
		if("update".equals(action)){
			mainId = tpromotionmanager.updateByPrimaryKey(prom);
		}else{
			mainId = tpromotionmanager.insertSelective(prom);
		}
		
		out.print(mainId);
		out.close();
	}
	
	public String updateRecord() throws NumberFormatException, SQLException{
		
		String id = this.getRequest().getParameter("id");
		TPromotion obj = tpromotionmanager.selectByPrimaryKey(Long.parseLong(id));
		
		System.out.println();
		
		this.getRequest().setAttribute("o", obj);
		return "update_record";
	}
	
	
	
	
	public String setDetialPage() throws SQLException{
		
		
		
		String type = this.getRequest().getParameter("type");
		
		map.put("pid", id);
		List<HashMap> list = opensqlmanage.selectForListByMap(map, "t_promotion.single-goods");
		
		this.getRequest().setAttribute("l", list);
		
		if(!"1".equals(type)){
			prom = tpromotionmanager.selectByPrimaryKey(Long.parseLong(id));
			
		}
		
		if("2".equals(type)){
		
			
		}
		
		
		if("3".equals(type)){	// 查询分类
			
			TCategoryExample example = new TCategoryExample();
//			example.createCriteria().andCategoryLevelEqualTo(1).andIdEqualTo(1L);
//			List<TCategory> list1 = tcategorymanager.selectByExample(example);
			
			example.clear();
			example.createCriteria().andCategoryLevelEqualTo(2).andParentIdEqualTo(1L);
			List<TCategory> list1 = tcategorymanager.selectByExample(example);
			
			System.out.println("===="+list1.size());
			
			example.clear();
			example.createCriteria().andCategoryLevelEqualTo(3);
			List<TCategory> list2 = tcategorymanager.selectByExample(example);
			
			
			this.getRequest().setAttribute("list1",list1);
			this.getRequest().setAttribute("list2",list2);
//			this.getRequest().setAttribute("list3",list3);
			
			TPromItemExample e = new TPromItemExample();
			e.createCriteria().andPidEqualTo(Long.parseLong(id));
			List<TPromItem> l = tpromitemmanager.selectByExample(e);
			this.getRequest().setAttribute("idlist", l);
		}
		
		
		// 赠品查询 
		TPromGiftExample example = new TPromGiftExample();
		example.createCriteria().andPidEqualTo(Long.parseLong(id));
		List<TPromGift> giftlist = tpromgiftmanager.selectByExample(example);
		Long giftid = null;
		if(giftlist.size()>0){
			giftid = giftlist.get(0).getGoodsId();
			this.getRequest().setAttribute("giftid", giftid);
			TGoods g = tgoodsmanager.selectByPrimaryKey(giftid);
			if(g!=null)
			this.getRequest().setAttribute("giftname", g.getGoodsName());
		}
		
		
		
		System.out.println("type---------->"+type); 
		if("1".equals(type)){
			return "single";
		}else if("2".equals(type)){
			return "mix";
		}else{
			return "category";
		}
		
	}

	
	
	public void setMixGoods() throws SQLException, IOException{
		
		PrintWriter out = this.getResponse().getWriter();
		
		List<Long> l = new ArrayList<Long>();
		
//		String min = this.getRequest().getParameter("min_price");
//		String price = this.getRequest().getParameter("price");
		String promid = this.getRequest().getParameter("promid");
		String gift = this.getRequest().getParameter("gift");
		String[] goodsId = this.getRequest().getParameterValues("goodsid");
		String type = this.getRequest().getParameter("ptype");
		
		String categorystr = this.getRequest().getParameter("category");
		String[] category = null;
		if(categorystr!=null){
			category  = categorystr.split(",");
		}
		
		Map map = new HashMap();
		map.put("promid", Long.parseLong(promid));
		
		
//		prom.setMinAmount(new BigDecimal(min));
//		prom.setAmount(new BigDecimal(price));
//		prom.setId(Long.parseLong(promid));
//		long n = tpromotionmanager.updateByPrimaryKeySelective(prom);
		
		TPromItemExample example = new TPromItemExample();
		example.createCriteria().andPidEqualTo(Long.parseLong(promid));
		
		List<TPromItem> list = tpromitemmanager.selectByExample(example);
		
		if(list.size()>0)
		{
			StringBuilder builder = new StringBuilder("");
			for(TPromItem ti : list){
				builder.append(ti.getTargetId()+",");
			}
			map.put("ids", builder.toString().substring(0,builder.toString().length()-1));
			opensqlmanage.updateByMap(map, "t_promotion.add-promid-null");
		}
		
		
		tpromitemmanager.deleteByExample(example);
	
		
		long n = 0;
		
		// 分类设置
		if("3".equals(type) && category!=null)
		{
			for(String id : category)
			{
				promItem = new TPromItem();
				promItem.setTargetId(Long.parseLong(id));
				promItem.setPid(Long.parseLong(promid));
				n = tpromitemmanager.insert(promItem);
			}
		}
		
		
		// 分类促销和组合促销的 --组合商品
		if(goodsId!=null)
		{
			StringBuilder sb = new StringBuilder("");
			for(String id : goodsId)
			{
				promItem = new TPromItem();
				promItem.setTargetId(Long.parseLong(id));
				promItem.setPid(Long.parseLong(promid));
				n = tpromitemmanager.insert(promItem);
//				sb.append(n+",");
				l.add(Long.parseLong(id));
			}
			addPromToGoods(l,Long.parseLong(promid));
			
//			map.put("ids", sb.toString().substring(0,sb.toString().length()-1));
//			int x = opensqlmanage.updateByMap(map, "t_promotion.add-promid");
//			System.out.println("x------------->"+x);
		}
		
	
		
		// 赠品
		if(gift!=null && !"".equals(gift))
		{
			//  gift
			TPromGiftExample e = new TPromGiftExample();
			e.createCriteria().andPidEqualTo(Long.parseLong(promid));
		    int	delgift = tpromgiftmanager.deleteByExample(e);
			promGift.setGoodsId(Long.parseLong(gift));
			promGift.setQuantity(1);
			promGift.setPid(Long.parseLong(promid));
			tpromgiftmanager.insert(promGift);
		}
		
		out.print(n);
		out.close();
		
	}
	
	
	public void setSingleGoods() {
		
		long rs = 1;
		PrintWriter out = null;
		try{
			
			out = this.getResponse().getWriter();
			String promid = this.getRequest().getParameter("promid");
			String[] goodsId = this.getRequest().getParameterValues("goodsid");
	    	String[] price = this.getRequest().getParameterValues("price");
	    	String[] limit_count = this.getRequest().getParameterValues("limit_count");
	    	String[] total_count = this.getRequest().getParameterValues("sale_count");
	    	
	    	TPromItemExample example = new TPromItemExample();
	    	example.createCriteria().andPidEqualTo(Long.parseLong(promid));
	    	tpromitemmanager.deleteByExample(example);
	    	
	    	List<Long> l = new ArrayList<Long>();
	    	int n = 0;
	    
	    	
	    	if(goodsId!=null)
	    	{
	    		for(String goodsid : goodsId){
	    			
	    			promItem = new TPromItem();
	    			promItem.setTargetId(Long.parseLong(goodsid));
	    			promItem.setPrice(new BigDecimal(price[n]));
	    			promItem.setLimitCount(Integer.parseInt(limit_count[n]));
	    			promItem.setSaleCount(Integer.parseInt(total_count[n]));
	    			promItem.setPid(Long.parseLong(promid));
	    			
	    			rs = tpromitemmanager.insert(promItem);
	    			if(rs>0)l.add(Long.parseLong(goodsid));
	    			n++;
	    		}
	    		addPromToGoods(l,Long.parseLong(promid));
	    	}
		}catch(Exception e){
			rs = 0;
			e.printStackTrace();
		}
		
		
		
		out.print(rs);
		if(out!=null)
		out.close();
		
	}
	
	
	public int addPromToGoods(List<Long> goods,long promid) throws SQLException{
		
		StringBuilder sb = new StringBuilder("-100");
		for(long id : goods ){
			sb.append(","+id);
		}
		Map map = new HashMap();
		map.put("promid", promid);
		map.put("ids", sb.toString());
		int n = opensqlmanage.updateByMap(map, "t_promotion.add-promid");
		System.out.println("n----->"+n);
		return n;
	}
	
	
	public void like() throws SQLException, IOException{
		
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String content = this.getRequest().getParameter("q")!=null?this.getRequest().getParameter("q").trim():"";
		
		TGoodsExample e = new TGoodsExample();
		e.createCriteria().andGoodsNameLike("%"+content+"%").andIsReleaseEqualTo(1);
		List<TGoods> list = tgoodsmanager.selectByExample(e);
		
		StringBuilder sb = new StringBuilder();
		for(TGoods tp : list){
			sb.append(tp.getGoodsName()+"|"+tp.getId()+ "\n");
		}
		out.write(sb.toString()); //修改失败
		out.close();	
		
		  
	}
	
	
	public void like2() throws SQLException, IOException{
		
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String content = new String(this.getRequest().getParameter("q").getBytes("iso8859-1"),"UTF-8")!=null?new String(this.getRequest().getParameter("q").getBytes("iso8859-1"),"UTF-8"):"";
		
//		TGoodsExample e = new TGoodsExample();
//		e.createCriteria().andGoodsNameLike("%"+content+"%").andPromotionIdIsNull().andIsReleaseEqualTo(1);
//		List<TGoods> list = tgoodsmanager.selectByExample(e);
//		
//		StringBuilder sb = new StringBuilder();
//		for(TGoods tp : list){
//			sb.append(tp.getGoodsName()+"|"+tp.getId()+"_"+tp.getPcPrice()+"\n");
//		}
		 Map<String,Object> openMap = new HashMap<String,Object>();
		 openMap.put("goodsname", "%"+content+"%");
		 openMap.put("is_release", 1);
		 openMap.put("promotion_id", -1);
	     List<Map<String,Object>> list = opensqlmanage.selectForListByMap(openMap, "t_goods.getGoodsByName");
	     StringBuilder sb = new StringBuilder();
		for(Map<String,Object> m : list){
			sb.append(m.get("goods_name")+"|"+m.get("id")+"_"+m.get("price")+ "\n");
		}
		out.write(sb.toString()); //修改失败
		out.close();	
		
		  
	}
	
	
	public void getGoodsBySN() throws SQLException, IOException{
		
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String sn = this.getRequest().getParameter("gsn");
		TGoodsExample e = new TGoodsExample();
		e.createCriteria().andGoodsnoEqualTo(sn);
		List<TGoods> list = tgoodsmanager.selectByExample(e);
		if(list.size()==0){
			JSONObject json =new JSONObject();
			json.accumulate("key", 0);
			out.print(json.toString());
			out.close();
			return;
		}
		
		TGoods goods = list.get(0);
		JSONObject json = JSONObject.fromObject(goods);
		json.accumulate("key", 1);
		out.print(json.toString());
		out.close();
		
	}

	
	public void likeWithHaidianCode() throws SQLException, IOException{
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String content = this.getRequest().getParameter("q")!=null?this.getRequest().getParameter("q").trim():"";
		
		TGoodsExample e = new TGoodsExample();
		e.createCriteria().andGoodsNameLike("%"+content+"%");
		Map<String, Object> paramMap =new HashMap<String, Object>();
		paramMap.put("goodsName", "%"+content+"%");
		
		StringBuilder sb = new StringBuilder();
		List<Map<String, Object>> resultList = opensqlmanage.selectForListByMap(paramMap, "t_goods.selectGoodsByLianxiangName");
		for (Map<String, Object> map : resultList) {
			sb.append(map.get("goods_name")+"|"+map.get("id")+"|"+map.get("goodsno")+"|"+map.get("wap_price")+ "\n");
		}
//		List<TGoods> list = tgoodsmanager.selectByExample(e);
//		StringBuilder sb = new StringBuilder();
//		for(TGoods tp : list){
//			sb.append(tp.getGoodsName()+"|"+tp.getId()+"|"+tp.getGoodsno()+ "\n");
//		}
		out.write(sb.toString()); //修改失败
		out.close();	
	}


	public TPromotion getProm() {
		return prom;
	}

	public void setProm(TPromotion prom) {
		this.prom = prom;
	}

	public TPromItem getPromItem() {
		return promItem;
	}

	public void setPromItem(TPromItem promItem) {
		this.promItem = promItem;
	}

	public TPromGift getPromGift() {
		return promGift;
	}

	public void setPromGift(TPromGift promGift) {
		this.promGift = promGift;
	}

	public TPromotionManager getTpromotionmanager() {
		return tpromotionmanager;
	}

	public void setTpromotionmanager(TPromotionManager tpromotionmanager) {
		this.tpromotionmanager = tpromotionmanager;
	}

	public TPromItemManager getTpromitemmanager() {
		return tpromitemmanager;
	}

	public void setTpromitemmanager(TPromItemManager tpromitemmanager) {
		this.tpromitemmanager = tpromitemmanager;
	}

	public TPromGiftManager getTpromgiftmanager() {
		return tpromgiftmanager;
	}

	public void setTpromgiftmanager(TPromGiftManager tpromgiftmanager) {
		this.tpromgiftmanager = tpromgiftmanager;
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


	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}


	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}


	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}


	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
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


	public Map getMap() {
		return map;
	}


	public void setMap(Map map) {
		this.map = map;
	}


	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public TCategoryManager getTcategorymanager() {
		return tcategorymanager;
	}



	public void setTcategorymanager(TCategoryManager tcategorymanager) {
		this.tcategorymanager = tcategorymanager;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
	
}
