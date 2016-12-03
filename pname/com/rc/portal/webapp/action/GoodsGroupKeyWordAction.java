package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.opensymphony.webwork.ServletActionContext;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TGroupGoodsKeywordManager;
import com.rc.portal.vo.TGroupGoodsKeyword;
import com.rc.portal.vo.TGroupGoodsKeywordExample;
import com.rc.portal.webapp.util.PageResult;

@SuppressWarnings("unchecked")
public class GoodsGroupKeyWordAction extends BaseAction{
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private TGroupGoodsKeywordManager tgroupgoodskeywordmanager;
	private String keyword;
	private String newkeyword;
	private String goodsids;
	private TGoodsManager tgoodsmanager;
	private List<Map<String, Object>> list;
	public void onlyVerification()throws Exception{
		if(StringUtils.hasText(keyword)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyword", keyword.trim());
			int n = (Integer) opensqlmanage.selectForObjectByMap(map, "t_group_goods_keyword.query_keyword_count");
			out.print(n);
			out.close();
		}
	}
	/**
	 * 修改视图
	 */
	public String view() throws Exception{
		if(StringUtils.hasText(keyword)){
			Map<String, String> map =new HashMap<String, String>();
			map.put("keyword", keyword);
			list = opensqlmanage.selectForListByMap(map, "t_group_goods_keyword.query_t_group_goods_keyword_goods_name");
		}
		return "view";
	}
	/**
	 * 删除
	 */
	public void del() throws Exception{
		if(StringUtils.hasText(keyword)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			TGroupGoodsKeywordExample example = new TGroupGoodsKeywordExample();
			example.createCriteria().andKeywordEqualTo(keyword);
			int n = tgroupgoodskeywordmanager.deleteByExample(example);
			out.print(n);
			out.close();
		}
	}
	/**
	 * 分页查询
	 */
	public String list() throws Exception{
		Map<String, String> map =new HashMap<String, String>();
		if(StringUtils.hasText(keyword)){
			map.put("keyword",keyword);
		}
		rs.setP_pageSize(10);
	    pw = opensqlmanage.selectForPageByMap_drug(map, "t_group_goods_keyword.query_record_count", "t_group_goods_keyword.query_record", rs.getP_curPage(), rs.getP_pageSize());
	    List<Map<String, Object>> list = pw.getResult();
	    map.clear();
	    for (Map<String, Object> objm : list) {
	    	String key = (String) objm.get("keyword");
	    	map.put("keyword", key);
	    	List<Map<String, Object>> listname = opensqlmanage.selectForListByMap(map, "t_group_goods_keyword.query_t_group_goods_keyword_goods_name");
	    	objm.put("list", listname);
		}
		return "list";
	}
	/**
	 * 保存
	 */
	public String save() throws Exception{
		if(StringUtils.hasText(keyword)&&StringUtils.hasText(goodsids)){
			String[] ids = goodsids.split(",");
			for (String id : ids) {
				if(isInteger(id.trim())){
					TGroupGoodsKeyword record = new TGroupGoodsKeyword();
					record.setGoodsId(Long.parseLong(id.trim()));
					record.setKeyword(keyword);
					tgroupgoodskeywordmanager.insertSelective(record);
				}
			}
		}
		return "add";
	}
	/**
	 * ajax添加
	 */
	public void ajaxsave() throws Exception{
		if(StringUtils.hasText(keyword)&&StringUtils.hasText(goodsids)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			TGroupGoodsKeyword record = new TGroupGoodsKeyword();
			record.setKeyword(keyword);
			record.setGoodsId(Long.parseLong(goodsids));
			long n = tgroupgoodskeywordmanager.insertSelective(record);
			out.print(n);
			out.close();
		}
	}
	/**
	 * ajax删除
	 */
	public void ajaxdel() throws Exception{
		if(StringUtils.hasText(keyword)&&StringUtils.hasText(goodsids)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			TGroupGoodsKeywordExample example = new TGroupGoodsKeywordExample();
			example.createCriteria().andKeywordEqualTo(keyword).andGoodsIdEqualTo(Long.parseLong(goodsids));
			long n = tgroupgoodskeywordmanager.deleteByExample(example);
			out.print(n);
			out.close();
		}
	}
	/**
	 * ajax修改
	 */
	public void ajaxupdata() throws Exception{
		if(StringUtils.hasText(keyword)&&StringUtils.hasText(newkeyword)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			TGroupGoodsKeyword record = new TGroupGoodsKeyword();
			record.setKeyword(newkeyword);
			TGroupGoodsKeywordExample example = new TGroupGoodsKeywordExample();
			example.createCriteria().andKeywordEqualTo(keyword);
			int n = tgroupgoodskeywordmanager.updateByExampleSelective(record, example);
			out.print(n);
			out.close();
		}
	}
	/**
	 * 添加跳转页面
	 */
	public String add() throws Exception{
		return "add_page";
	}
	private boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	} 
	public void like() throws SQLException, IOException{
		
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String content = this.getRequest().getParameter("q")!=null?this.getRequest().getParameter("q").trim():"";
		Map openMap = new HashMap();
		 openMap.put("goodsname", "%"+content+"%");
		 openMap.put("is_suit", 1);
	     List<Map> list = opensqlmanage.selectForListByMap(openMap, "t_goods.getGoodsByName");
	     StringBuilder sb = new StringBuilder();
			for(Map m : list){
				//sb.append(tp.getGoodsName()+"|"+tp.getId()+"_"+tp.getPcPrice()+"_"+tp.getGoodsno()+ "\n");
				sb.append(m.get("goods_name")+"|"+m.get("id")+"_"+m.get("price")+ "\n");
			}
//		TGoodsExample e = new TGoodsExample();
//		e.createCriteria().andShortNameLike("%"+content+"%").andIsSuitEqualTo(1);
//		List<TGoods> list = tgoodsmanager.selectByExample(e);
//		
//		StringBuilder sb = new StringBuilder();
//		for(TGoods tp : list){
//			sb.append(tp.getShortName()+"|"+tp.getId()+"_"+tp.getPcPrice()+ "\n");
//		}
		out.write(sb.toString()); //修改失败
		out.close();	
		
		  
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public TGroupGoodsKeywordManager getTgroupgoodskeywordmanager() {
		return tgroupgoodskeywordmanager;
	}

	public void setTgroupgoodskeywordmanager(
			TGroupGoodsKeywordManager tgroupgoodskeywordmanager) {
		this.tgroupgoodskeywordmanager = tgroupgoodskeywordmanager;
	}

	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}
	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
	}
	
	public String getGoodsids() {
		return goodsids;
	}
	public void setGoodsids(String goodsids) {
		this.goodsids = goodsids;
	}
	
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {
	}
	public String getNewkeyword() {
		return newkeyword;
	}
	public void setNewkeyword(String newkeyword) {
		this.newkeyword = newkeyword;
	}

}
