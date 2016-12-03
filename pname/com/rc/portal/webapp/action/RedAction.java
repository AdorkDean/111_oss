package com.rc.portal.webapp.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;
/**
 * 红包统计类
 * @author user3
 *
 */
public class RedAction extends BaseAction{
	private static final long serialVersionUID = -1388016748230565899L;
    private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	/**
	 * 生成excl
	 */
	private String url;
	/**
	 * 红包查询
	 * @return
	 */
	public  String  redList(){
		String cardname = this.getRequest().getParameter("cardname");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtils.hasText(cardname)){
			paramMap.put("cardname", cardname.trim());
			this.getRequest().setAttribute("cardname", cardname);
		}
		String startDate = this.getRequest().getParameter("startDate");
		if(StringUtils.hasText(startDate)){
			paramMap.put("start_date", startDate.trim());
			this.getRequest().setAttribute("startDate", startDate);
		}else{
			paramMap.put("start_date", "1900-01-01 00:00:00");
		}
		String endDate = this.getRequest().getParameter("endDate");
		if(StringUtils.hasText(endDate)){
			paramMap.put("end_date", endDate.trim());
			this.getRequest().setAttribute("endDate", endDate);
		}else{
			paramMap.put("end_date", "9999-12-01 00:00:00");
		}
		String realName = this.getRequest().getParameter("realName");
		if(StringUtils.hasText(realName)){
			paramMap.put("realName", realName.trim());
			this.getRequest().setAttribute("realName", realName.trim());
		}
		String type = this.getRequest().getParameter("type");
		if(StringUtils.hasText(type)&&!"0".equals(type)){
			paramMap.put("type", 2);
		}else{
			paramMap.put("type", 0);
		}
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "t_lx_receive_red.selectCountByMapByPage", "t_lx_receive_red.selectListByMapByPage", rs.getP_curPage(), rs.getP_pageSize());
		List<Map<String,Object>> list =opensqlmanage.selectForListByMap(paramMap, "t_lx_receive_red.selectTongjiByMapByPage");
		int lingquCount = 0;
		BigDecimal lingquMoney= new BigDecimal("0");
		int useCount =0;
		BigDecimal useMoney= new BigDecimal("0");
		Integer redCount =0;
		if(list.size()>0){
			BigDecimal money = null;
			for(Map<String,Object> map:list){
				int count= 0;
				money = new BigDecimal("0");
				if(map.get("dis_price")!=null){
					money =(BigDecimal)map.get("dis_price");
				}
				if(map.get("count")!=null){
					count = ((Long)map.get("count")).intValue();
				}
				lingquCount = lingquCount+count;
				lingquMoney = lingquMoney.add(money);
				if(map.get("is_use")!=null){
					if(String.valueOf(map.get("is_use")).equals("1")){
						useCount=useCount+count;
						useMoney = useMoney.add(money);
					}
				}
			}
		}
		redCount =(Integer) this.opensqlmanage.selectForObjectByMap(paramMap, "t_lx_receive_red.selectSendRedCountByMap");
		this.getRequest().setAttribute("lingquCount", lingquCount);
		this.getRequest().setAttribute("lingquMoney", lingquMoney);
		this.getRequest().setAttribute("useCount", useCount);
		this.getRequest().setAttribute("useMoney", useMoney);
		this.getRequest().setAttribute("redCount", redCount);
		if(StringUtils.hasText(type)&&!"0".equals(type)){//导出
			String path=getRequest().getSession().getServletContext().getRealPath("");
			    path=path+"/excel_temp/redsend"+UtilDate.getDate()+System.currentTimeMillis()+".xls";	
				List lists = export();
				String s[][] = getArray(pw.getResult());
				ExcelUtil.createExcel(path, lists, s);
			    getRequest().setAttribute("xlsname", path);
			    url = "/WEB-INF/pages/down/down.jsp";
			    return "xls";
			
		}else{//查询
			return "red_list";
		}
	}
	
	//设置表头
	public List<String> export() {
		List list = new ArrayList();
		list.add("秀粉名称");
		list.add("所属领秀");
		list.add("红包名称");
		list.add("红包金额");
		list.add("红包编码");
		list.add("领取时间");
		list.add("优惠券开始时间");
		list.add("优惠券结束时间");
		list.add("是否使用");
		list.add("使用时间");
		return list;
	}
	
	//存放二维数组
	public String[][] getArray(List couponList) {
		String[][] s = new String[couponList.size()][10];
		for (int i = 0; i < couponList.size(); i++) {
			Map m = (Map)couponList.get(i);
			if(m.get("user_name")!=null){
				s[i][0]=m.get("user_name").toString();
			}else{
				s[i][0] = " ";
			}
			if(m.get("real_name")!=null){
				s[i][1]=m.get("real_name").toString();
			}else{
				s[i][1]="";
			}
			if(m.get("name")!=null){
				s[i][2]=m.get("name").toString();
			}else{
				s[i][2]=" ";
			}
			if(m.get("dis_price")!=null){
				s[i][3]=m.get("dis_price").toString();
			}else{
				s[i][3]=" ";
			}
			if(m.get("card_no")!=null){
				s[i][4]=m.get("card_no").toString();
			}else{
				s[i][4]="";
			}
			if(m.get("receive_dt")!=null){
				s[i][5]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("receive_dt"));
			}else{
				s[i][5]="";
			}
			if(m.get("start_time")!=null){
				s[i][6]=new SimpleDateFormat("yyyy-MM-dd").format((Date)m.get("start_time"));
			}else{
				s[i][6]="";
			}
			if(m.get("end_time")!=null){
				s[i][7]=new SimpleDateFormat("yyyy-MM-dd").format((Date)m.get("end_time"));
			}else{
				s[i][7]="";
			}
			if(m.get("is_use")!=null){
				if(Integer.parseInt(m.get("is_use").toString())==0){
					s[i][8]="否";
				}else if(Integer.parseInt(m.get("is_use").toString())==1){
					s[i][8]="是";
				}
			}else{
				s[i][8]="";
			}
			if(m.get("use_time")!=null){
				s[i][9]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("use_time"));
			}else{
				s[i][9]="";
			}
		}
		return s;
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

	public Object getModel() {
		return null;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setModel(Object o) {
		
	}

}
