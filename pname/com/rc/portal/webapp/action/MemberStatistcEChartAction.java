package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;

public class MemberStatistcEChartAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	Map baseCount = new HashMap();
	private String chartdate="";
	private String url;
	public String getMemberCountIndex(){
		Map map=new HashMap();
		Calendar chart;
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		chart=new GregorianCalendar();
		chart.add(Calendar.DAY_OF_MONTH,-1);
		map.put("yesterday", sdf.format(chart.getTime()));
		chart=new GregorianCalendar();
		chart.add(Calendar.DAY_OF_MONTH,-7);
		map.put("yesterweek", sdf.format(chart.getTime()));
		chart=new GregorianCalendar();
		chart.add(Calendar.DAY_OF_MONTH, -32);
		map.put("yestermonth", sdf.format(chart.getTime()));
		baseCount=(Map)opensqlmanage.selectForObjectByMap_drug(map, "tj_member.selectMemberStatisticsBase");
		return "getMemberCountIndex";
	}
	
	public void echartsData() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Map map=new HashMap();
		Calendar chart;
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		if(model.getStartDate()!=null&&!"".equals(model.getStartDate().trim())){
			map.put("startDate", model.getStartDate().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -30);
			map.put("startDate", sdf.format(chart.getTime()));
		}
		
		if(model.getEndDate()!=null&&!"".equals(model.getEndDate().trim())){
			map.put("endDate", model.getEndDate().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -1);
			map.put("endDate", sdf.format(chart.getTime()));
		}
		
		if(model.getSource()!=null&&model.getSource()!=0){
			map.put("source", model.getSource());
		}else{
			map.put("source", 0);
		}
		
		List<Map> chartList=new ArrayList<Map>();
		Map tem=new HashMap();
		List ydate=new ArrayList();
		List xzyh=new ArrayList();
		List ljyh=new ArrayList();
			chartList=opensqlmanage.selectForListByMap_drug(map, "tj_member.selectEChartBase");
			for(int i=0;i<chartList.size();i++){
				ydate.add(chartList.get(i).get("date"));
				ljyh.add(chartList.get(i).get("ljyh"));
				xzyh.add(chartList.get(i).get("xzyh"));
			}
			tem.put("ydate", ydate);
			tem.put("ljyh", ljyh);
			tem.put("xzyh", xzyh);
		JSONObject ja=JSONObject.fromObject(tem);
		write.write(ja.toString());
		write.close();
	}
	
	public void echartsContrastData() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Map map=new HashMap();
		Calendar chart;
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		if(model.getStartDate1()!=null&&!"".equals(model.getStartDate1().trim())){
			map.put("startDate1", model.getStartDate1().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -30);
			map.put("startDate1", sdf.format(chart.getTime()));
		}
		
		if(model.getEndDate1()!=null&&!"".equals(model.getEndDate1().trim())){
			map.put("endDate1", model.getEndDate1().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -1);
			map.put("endDate1", sdf.format(chart.getTime()));
		}
	
		if(model.getStartDate2()!=null&&!"".equals(model.getStartDate2().trim())){
			map.put("startDate2", model.getStartDate2().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -31);
			map.put("startDate2", sdf.format(chart.getTime()));
		}
		
		if(model.getEndDate2()!=null&&!"".equals(model.getEndDate2().trim())){
			map.put("endDate2", model.getEndDate2().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -60);
			map.put("endDate2", sdf.format(chart.getTime()));
		}
		
		List<Map> chartList1=new ArrayList<Map>();
		List<Map> chartList2=new ArrayList<Map>();
		Map tem=new HashMap();
		List ydate=new ArrayList();
		List xzyh1=new ArrayList();
		List xzyh2=new ArrayList();
		List ljyh1=new ArrayList();
		List ljyh2=new ArrayList();
			chartList1=opensqlmanage.selectForListByMap_drug(map, "tj_member.selectContrastEChartBase1");
			chartList2=opensqlmanage.selectForListByMap_drug(map, "tj_member.selectContrastEChartBase2");
			if(chartList1.size()>=chartList2.size()){
				for(int i=0;i<chartList1.size();i++){
					Integer j=i+1;
					ydate.add(j.toString());
					ljyh1.add(chartList1.get(i).get("ljyh"));
					xzyh1.add(chartList1.get(i).get("xzyh"));
				}
				for(int i=0;i<chartList2.size();i++){
					ljyh2.add(chartList2.get(i).get("ljyh"));
					xzyh2.add(chartList2.get(i).get("xzyh"));
				}
			}else{
				for(int i=0;i<chartList2.size();i++){
					Integer j=i+1;
					ydate.add(j.toString());
					ljyh2.add(chartList2.get(i).get("ljyh"));
					xzyh2.add(chartList2.get(i).get("xzyh"));
				}
				for(int i=0;i<chartList1.size();i++){
					ljyh1.add(chartList1.get(i).get("ljyh"));
					xzyh1.add(chartList1.get(i).get("xzyh"));
				}
				
			}
			tem.put("ydate", ydate);
			tem.put("ljyh1", ljyh1);
			tem.put("xzyh1", xzyh1);
			tem.put("ljyh2", ljyh2);
			tem.put("xzyh2", xzyh2);
		JSONObject ja=JSONObject.fromObject(tem);
		write.write(ja.toString());
		write.close();
	}
	
	public void getDateList() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Map map=new HashMap();
		Calendar chart;
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		if(model.getStartDate3()!=null&&!"".equals(model.getStartDate3().trim())){
			map.put("startDate", model.getStartDate3().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -30);
			map.put("startDate", sdf.format(chart.getTime()));
		}
		if(model.getEndDate3()!=null&&!"".equals(model.getEndDate3().trim())){
			map.put("endDate", model.getEndDate3().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -1);
			map.put("endDate", sdf.format(chart.getTime()));
		}
		if(model.getSource()!=null){
			map.put("source", model.getSource());
		}
		pw = opensqlmanage.selectForPageByMap_drug(map, "tj_member.selectDateListCount", "tj_member.selectDateListByPage", rs.getP_curPage(), rs.getP_pageSize());
		pw.getPageInfo().getPage();
		Map tem=new HashMap();
		tem.put("pageInfo", pw.getPageInfo());
		tem.put("result", pw.getResult());
		JSONObject ja=JSONObject.fromObject(tem);
		write.write(ja.toString());
		write.close();
	}
	public String exportDateList() throws Exception{
		Map map=new HashMap();
		Calendar chart;
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		if(model.getStartDate3()!=null&&!"".equals(model.getStartDate3().trim())){
			map.put("startDate", model.getStartDate3().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -30);
			map.put("startDate", sdf.format(chart.getTime()));
		}
		if(model.getEndDate3()!=null&&!"".equals(model.getEndDate3().trim())){
			map.put("endDate", model.getEndDate3().trim());
		}else{
			chart=new GregorianCalendar();
			chart.add(Calendar.DAY_OF_MONTH, -1);
			map.put("endDate", sdf.format(chart.getTime()));
		}
		if(model.getSource()!=null){
			map.put("source", model.getSource());
		}else{
			map.put("source", 0);
		}
		List<Map> list=opensqlmanage.selectForListByMap_drug(map, "tj_member.selectDateListExcel");
		String path=getRequest().getSession().getServletContext().getRealPath("");
		if(model.getSource()!=null){
			if(model.getSource()==1){
				path=path+"/excel_temp/"+"PC"+UtilDate.getDate()+System.currentTimeMillis()+".xls";
			}else if(model.getSource()==2){
				path=path+"/excel_temp/"+"WAP"+UtilDate.getDate()+System.currentTimeMillis()+".xls";
			}else if(model.getSource()==3){
				path=path+"/excel_temp/"+"Android"+UtilDate.getDate()+System.currentTimeMillis()+".xls";
			}else if(model.getSource()==4){
				path=path+"/excel_temp/"+"IOS"+UtilDate.getDate()+System.currentTimeMillis()+".xls";
			}else{
				path=path+"/excel_temp/"+"全部来源"+UtilDate.getDate()+System.currentTimeMillis()+".xls";
			}
		}else{
			path=path+"/excel_temp/"+"全部来源"+UtilDate.getDate()+System.currentTimeMillis()+".xls";
		}
		List lists = export();
		String s[][] = getArray(list);
		ExcelUtil.createExcel(path, lists, s);
		getRequest().setAttribute("xlsname", path);
		url = "/WEB-INF/pages/down/down.jsp";
		return "xls";
	}
	public List<String> export() {
		List list = new ArrayList();
		list.add("时间");
		list.add("新增加人数");
		list.add("活跃人数");
		list.add("购买人数");
		list.add("累计人数");
		return list;
	}
	
	
	//存放二维数组
		public String[][] getArray(List couponList) {
			String[][] s = new String[couponList.size()][5];
			for (int i = 0; i < couponList.size(); i++) {
				Map m = (Map)couponList.get(i);
				if(m.get("date")!=null&&!"".equals(m.get("date").toString())){
					s[i][0]=m.get("date").toString();
				}else{
					s[i][0]="";
				}
				if(m.get("xzyh")!=null&&!"".equals(m.get("xzyh").toString())){
					s[i][1]=m.get("xzyh").toString();
				}else{
					s[i][1]="0";
				}
				if(m.get("day_active")!=null&&!"".equals(m.get("day_active").toString())){
					s[i][2]=m.get("day_active").toString();
				}else{
					s[i][2]="0";
				}
				if(m.get("ps")!=null&&!"".equals(m.get("ps").toString())){
					s[i][3]=m.get("ps").toString();
				}else{
					s[i][3]="0";
				}
				if(m.get("day_cumulative_sum")!=null&&!"".equals(m.get("day_cumulative_sum").toString())){
					s[i][4]=m.get("day_cumulative_sum").toString();
				}else{
					s[i][4]="0";
				}
			}
			return s;
		}
	public class Condition {
		private String startDate;
		private String endDate;
		private String startDate1;
		private String endDate1;
		private String startDate2;
		private String endDate2;
		private String startDate3;
		private String endDate3;
		private Integer source;
		private Integer type;
		private Integer chartType;
		
		
		public String getStartDate3() {
			return startDate3;
		}
		public void setStartDate3(String startDate3) {
			this.startDate3 = startDate3;
		}
		public String getEndDate3() {
			return endDate3;
		}
		public void setEndDate3(String endDate3) {
			this.endDate3 = endDate3;
		}
		public String getStartDate1() {
			return startDate1;
		}
		public void setStartDate1(String startDate1) {
			this.startDate1 = startDate1;
		}
		public String getEndDate1() {
			return endDate1;
		}
		public void setEndDate1(String endDate1) {
			this.endDate1 = endDate1;
		}
		public String getStartDate2() {
			return startDate2;
		}
		public void setStartDate2(String startDate2) {
			this.startDate2 = startDate2;
		}
		public String getEndDate2() {
			return endDate2;
		}
		public void setEndDate2(String endDate2) {
			this.endDate2 = endDate2;
		}
		public Integer getChartType() {
			return chartType;
		}
		public void setChartType(Integer chartType) {
			this.chartType = chartType;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public Integer getSource() {
			return source;
		}
		public void setSource(Integer source) {
			this.source = source;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
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




	public Map getBaseCount() {
		return baseCount;
	}

	public void setBaseCount(Map baseCount) {
		this.baseCount = baseCount;
	}

	public String getChartdate() {
		return chartdate;
	}


	public void setChartdate(String chartdate) {
		this.chartdate = chartdate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
}
