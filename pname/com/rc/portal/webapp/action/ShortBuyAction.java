package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.webwork.ServletActionContext;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TShortBuyManager;
import com.rc.portal.service.TShortGoodsManager;
import com.rc.portal.vo.TShortBuy;
import com.rc.portal.vo.TShortGoods;
import com.rc.portal.vo.TShortGoodsExample;
import com.rc.portal.webapp.model.ShortGoods;
import com.rc.portal.webapp.util.PageResult;

public class ShortBuyAction extends BaseAction {
	
	private TShortBuy shortBuyBean = new TShortBuy();
	private TShortGoods shortGoodsBean = new TShortGoods();
	private TShortBuyManager tshortbuymanager;
	private TShortGoodsManager tshortgoodsmanager;
	private TGoodsManager tgoodsmanager;
	private List<TShortGoods> goodsList;
	
	private OpenSqlManage opensqlmanage;
	
	private String id;
	
	public static String format = "yyyy-MM-dd HH:mm:ss";
	
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	
	private Map map = new HashMap();
	
	
	public static void main(String[] args) {
		
		ShortBuyAction o = new ShortBuyAction();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		o.opensqlmanage = (OpenSqlManage) context.getBean("opensqlmanage");
		o.list();
	}
	
	
	public String insert_page(){
		
		return "insert_page";
	}
	
	public String list(){
		
		String stimestr = this.getRequest().getParameter("stime");
    	String etimestr = this.getRequest().getParameter("etime");
    	
    	System.out.println("============>"+shortBuyBean.getName());
    	
    	if(!"".equals(shortBuyBean.getName())){
    		map.put("name", shortBuyBean.getName());
    	}
    	
    	
    	if(shortBuyBean.getStatus()!=null && shortBuyBean.getStatus()!=0){
    		map.put("status", shortBuyBean.getStatus());
    	}
    	
    	if(null!=stimestr && !"".equals(stimestr)){
//    		Date stime = com.rc.portal.util.DateUtil.strDateConvertToDate(stimestr, format);
    		map.put("stime",stimestr);
    	}
    	
    	if(null!=etimestr && !"".equals(etimestr)){
//    		Date etime = com.rc.portal.util.DateUtil.strDateConvertToDate(etimestr, format);
    		map.put("etime",etimestr);
    	}
    	
		pw = opensqlmanage.selectForPageByMap_drug(map,"t_short_buy.list-count", "t_short_buy.list",  rs.getP_curPage(), rs.getP_pageSize());
	    this.getRequest().setAttribute("stime", stimestr);
	    this.getRequest().setAttribute("etime", etimestr);
		return "lists";
	}
	
    public void insertShortBuy() throws SQLException, IOException{
    	
    	System.out.println("**************************");
    	
    	HttpServletResponse  response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		long mainId = 0;
    	
    	String action = this.getRequest().getParameter("action");
    	
    	String stimestr = this.getRequest().getParameter("stime");
    	String etimestr = this.getRequest().getParameter("etime");
    	
    	Date stime = com.rc.portal.util.DateUtil.strDateConvertToDate(stimestr, format);
    	Date etime = com.rc.portal.util.DateUtil.strDateConvertToDate(etimestr, format);
    	
    	if(!"update".equals(action))
    	{
    		Map map = new HashMap();
        	map.put("status", "1");
        	int n = (Integer)opensqlmanage.selectForObjectByMap(map, "t_short_buy.curr-is-promotion");
        	
        	if(n>0){
        		mainId = -1; // 存在创建状态的秒杀
        		out.print(mainId);
            	out.close();
        		return ;
        	}
        	
        	map.clear();
        	map.put("status", 2+"");
        	int m = (Integer)opensqlmanage.selectForObjectByMap(map, "t_short_buy.curr-is-promotion");
        	
        	if(m>0){
        		mainId = -2; // 存在进行状态的秒杀
        		out.print(mainId);
            	out.close();
        		return ;
        	}
    	}
    	
    	shortBuyBean.setStartTime(stime);
    	shortBuyBean.setEndTime(etime);
    	shortBuyBean.setStatus(1);
    	
    	
    	if("update".equals(action)){
    		mainId = shortBuyBean.getId();
    		tshortbuymanager.updateByPrimaryKeySelective(shortBuyBean);
    	}else{
    		shortBuyBean.setCreateTime(new Date());
    		mainId = tshortbuymanager.insertSelective(shortBuyBean);
    	}
    	
    	out.print(mainId);
    	out.close();
    }
    
    
    public void set() throws SQLException, IOException{
    	
    	
    	PrintWriter out = this.getResponse().getWriter();
    	String[] goodsId = this.getRequest().getParameterValues("goodsid");
       
    	String[] price = this.getRequest().getParameterValues("price");
    	String[] limit_count = this.getRequest().getParameterValues("limit_count");
    	String[] total_count = this.getRequest().getParameterValues("total_count");
    	String parentid = getRequest().getParameter("parentid");
    	String flag = getRequest().getParameter("flag");
    	
    	long mainId = Long.parseLong(parentid);
    	
    	TShortGoodsExample e = new TShortGoodsExample();
		e.createCriteria().andShortBuyIdEqualTo(mainId);
		tshortgoodsmanager.deleteByExample(e);
    	int n = 0;
    	long x = 0;
    	
    	for(String gid : goodsId){
    		System.out.println("gid-"+gid+"----"+price[n]);
    		shortGoodsBean = new TShortGoods(Long.parseLong(gid),new BigDecimal(price[n]),Integer.parseInt(limit_count[n]),Integer.parseInt(total_count[n]),mainId);
    		x = tshortgoodsmanager.insertSelective(shortGoodsBean);
    		n++;
    	}
    	
    	out.print(x);
    	out.close();
    }
    
	public String setGoods() throws SQLException{
		
		id = getRequest().getParameter("id");
		
		map.put("parentid", id);
    	List<ShortGoods> list = opensqlmanage.selectForListByMap(map, "t_short_buy.short-buy-goods");
		
//		TShortGoodsExample example = new TShortGoodsExample();
//    	example.createCriteria().andShortBuyIdEqualTo(Long.parseLong(id));
//    	goodsList = tshortgoodsmanager.selectByExample(example);
    	
    	this.getRequest().setAttribute("list", list);
	
		return "set_goods";
	}
    
    public String getRecord() throws SQLException{
    	String id = getRequest().getParameter("id");
    	System.out.println("======"+id);
    	shortBuyBean = tshortbuymanager.selectByPrimaryKey(Long.parseLong(id));
//    	TShortGoodsExample example = new TShortGoodsExample();
//    	example.createCriteria().andShortBuyIdEqualTo(shortBuyBean.getId());
//    	goodsList = tshortgoodsmanager.selectByExample(example);
    	map.put("parentid", id);
    	List<ShortGoods> list = opensqlmanage.selectForListByMap(map, "t_short_buy.short-buy-goods");
    	this.getRequest().setAttribute("list", list);
    	this.getRequest().setAttribute("id", id);
    	return "only_record";
    }
    
    
	public void like() throws SQLException, IOException{
		
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String content = this.getRequest().getParameter("q")!=null?this.getRequest().getParameter("q").trim():"";
		
		
		 Map openMap = new HashMap();
		 openMap.put("goodsname", "%"+content+"%");
	     List<Map> list = opensqlmanage.selectForListByMap(openMap, "t_goods.getGoodsByName");
	     StringBuilder sb = new StringBuilder();
			for(Map m : list){
				//sb.append(tp.getGoodsName()+"|"+tp.getId()+"_"+tp.getPcPrice()+"_"+tp.getGoodsno()+ "\n");
				sb.append(m.get("goods_name")+"|"+m.get("id")+"_"+m.get("price")+ "\n");
			}	
//		TGoodsExample e = new TGoodsExample();
//		e.createCriteria().andGoodsNameLike("%"+content+"%");
//		List<TGoods> list = tgoodsmanager.selectByExample(e);
//		StringBuilder sb = new StringBuilder();
//		for(TGoods tp : list){
//			sb.append(tp.getGoodsName()+"|"+tp.getId()+"_"+tp.getPcPrice()+ "\n");
//		}
		out.write(sb.toString()); //修改失败
		out.close();	
		
		  
	}
	
	public void getGoodsByHDNO() throws SQLException, IOException{
		
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String sn = this.getRequest().getParameter("q")!=null?this.getRequest().getParameter("q").trim():"";
	    Map openMap = new HashMap();
	    openMap.put("goodsno", "%"+sn+"%");
		List<Map> list = opensqlmanage.selectForListByMap(openMap, "t_goods.getGoodsByHDNO");
//		TGoodsExample e = new TGoodsExample();
//		e.createCriteria().andGoodsnoLike(sn);
//		List<TGoods> list = tgoodsmanager.selectByExample(e);
		StringBuilder sb = new StringBuilder();
		for(Map m : list){
			//sb.append(tp.getGoodsName()+"|"+tp.getId()+"_"+tp.getPcPrice()+"_"+tp.getGoodsno()+ "\n");
			sb.append(m.get("goods_name")+"|"+m.get("id")+"_"+m.get("price")+"_"+m.get("goodsno")+ "\n");
		}
		out.write(sb.toString()); //修改失败
		out.close();
	}
	
    
    

	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}


	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
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

	public TShortBuy getShortBuyBean() {
		return shortBuyBean;
	}

	public void setShortBuyBean(TShortBuy shortBuyBean) {
		this.shortBuyBean = shortBuyBean;
	}

	public TShortGoods getShortGoodsBean() {
		return shortGoodsBean;
	}

	public void setShortGoodsBean(TShortGoods shortGoodsBean) {
		this.shortGoodsBean = shortGoodsBean;
	}

	public TShortBuyManager getTshortbuymanager() {
		return tshortbuymanager;
	}

	public void setTshortbuymanager(TShortBuyManager tshortbuymanager) {
		this.tshortbuymanager = tshortbuymanager;
	}

	public TShortGoodsManager getTshortgoodsmanager() {
		return tshortgoodsmanager;
	}

	public void setTshortgoodsmanager(TShortGoodsManager tshortgoodsmanager) {
		this.tshortgoodsmanager = tshortgoodsmanager;
	}

	public List<TShortGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<TShortGoods> goodsList) {
		this.goodsList = goodsList;
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	

}
