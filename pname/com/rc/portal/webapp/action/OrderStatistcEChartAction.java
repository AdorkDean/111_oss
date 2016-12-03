package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.webapp.util.PageResult;

public class OrderStatistcEChartAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private Map statistic=new HashMap();
	private String chartdate="";
	public String getOrderEChart(){
		Map map=new HashMap();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		Calendar c=new GregorianCalendar();
		c.add(Calendar.DAY_OF_MONTH, -1);
		String yesterday = sdf.format(c.getTime());
		map.put("yesterday", yesterday);
		Date date=new Date();
		String today=sdf.format(date);
		map.put("today", today);
		statistic=(Map)opensqlmanage.selectForObjectByMap_drug(map, "t_order.selectOrderStatisticBase");
		List<Map> statisticSourceTodayList = new ArrayList<Map>();
		statisticSourceTodayList=opensqlmanage.selectForListByMap_drug(map, "t_order.selectOrderSourceToday");
			for(int i=1;i<6;i++){
				boolean f=false;
				if(statisticSourceTodayList.size()>0){
					for(Map m:statisticSourceTodayList){
						if((Integer)m.get("today")==i){
							f=true;
						}
					}
				}
				if(!f){
					Map m=new HashMap();
					m.put("today", i);
					m.put("todaySource", 0);
					statisticSourceTodayList.add(m);
				}
				
		}
		List<Map> statisticSourceYesterdayList = new ArrayList<Map>();
		statisticSourceYesterdayList=opensqlmanage.selectForListByMap_drug(map, "t_order.selectOrderSourceYesterday");
		for(int i=1;i<6;i++){
			boolean f=false;
			if(statisticSourceYesterdayList.size()>0){
				for(Map m:statisticSourceYesterdayList){
					if((Integer)m.get("yesterday")==i){
						f=true;
					}
				}
			}
			if(!f){
				Map m=new HashMap();
				m.put("yesterday", i);
				m.put("yesterdaySource", 0);
				statisticSourceYesterdayList.add(m);
			}
			
	}
		List<Map> statisticTodayDeliveryList = new ArrayList<Map>();
		statisticTodayDeliveryList=opensqlmanage.selectForListByMap_drug(map, "t_order.selectOrderDeliveryToday");
		for(int i=1;i<5;i++){
			boolean f=false;
			if(statisticTodayDeliveryList.size()>0){
				for(Map m:statisticTodayDeliveryList){
					if(new Long(m.get("delivery_id").toString()).intValue()==i){
						f=true;
					}
				}
			}
			if(!f){
				Map m=new HashMap();
				if(i==1){
					m.put("deliveryName", "普通快递");
					m.put("delivery_id", 1);
				}else if(i==2){
					m.put("deliveryName", "药士达");
					m.put("delivery_id", 2);
				}else if(i==3){
					m.put("deliveryName", "货到付款");
					m.put("delivery_id", 3);
				}else if(i==4){
					m.put("deliveryName", "药师到家");
					m.put("delivery_id", 4);
				}
				m.put("todayDelivery", 0);
				statisticTodayDeliveryList.add(m);
			}
		}
		List<Map> statisticYesterdayDeliveryList = new ArrayList<Map>();
		statisticYesterdayDeliveryList=opensqlmanage.selectForListByMap_drug(map, "t_order.selectOrderDeliveryYesterday");
		for(int i=1;i<5;i++){
			boolean f=false;
			if(statisticYesterdayDeliveryList.size()>0){
				for(Map m:statisticYesterdayDeliveryList){
					if(new Long(m.get("delivery_id").toString()).intValue()==i){
						f=true;
					}
				}
			}
			if(!f){
				Map m=new HashMap();
				if(i==1){
					m.put("deliveryName", "普通快递");
					m.put("delivery_id", 1);
				}else if(i==2){
					m.put("deliveryName", "药士达");
					m.put("delivery_id", 2);
				}else if(i==3){
					m.put("deliveryName", "货到付款");
					m.put("delivery_id", 3);
				}else if(i==4){
					m.put("deliveryName", "药师到家");
					m.put("delivery_id", 4);
				}
				m.put("yesterdayDelivery", 0);
				statisticYesterdayDeliveryList.add(m);
			}
		}
		statistic.put("statisticSourceToday", statisticSourceTodayList);
		statistic.put("statisticSourceYesterday", statisticSourceYesterdayList);
		statistic.put("statisticTodayDelivery", statisticTodayDeliveryList);
		statistic.put("statisticYesterdayDelivery", statisticYesterdayDeliveryList);
		return "getOrderEChart";
	}
	
	public void echartsData() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Map map=new HashMap();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		Calendar chart=new GregorianCalendar();
		if(model.getDateType()!=null&&model.getDateType()!=0){
				if(model.getDateType()==1){
					chart.add(Calendar.DAY_OF_MONTH, -7);
				}else if(model.getDateType()==2){
					chart.add(Calendar.DAY_OF_MONTH, -15);
				}else if(model.getDateType()==3){
					chart.add(Calendar.DAY_OF_MONTH,-30);
				}
		}else{
			chart.add(Calendar.DAY_OF_MONTH, -7);
		}
		map.put("chartdate",sdf.format(chart.getTime()));
		Date date=new Date();
		String today=sdf.format(date);
		map.put("today", today);
		List<Map> chartList=new ArrayList<Map>();
		chartList=opensqlmanage.selectForListByMap_drug(map, "t_order.selectOrderChart");
		Map tem=new HashMap();
		List ydate=new ArrayList();
		List oa=new ArrayList();
		List oc=new ArrayList();
		for(int i=0;i<chartList.size();i++){
			ydate.add(chartList.get(i).get("date"));
			oa.add(chartList.get(i).get("oa"));
				oc.add(chartList.get(i).get("oc"));
		}
			tem.put("ydate", ydate);
			tem.put("oa", oa);
			tem.put("oc", oc);
		JSONObject ja=JSONObject.fromObject(tem);
		write.write(ja.toString());
		write.close();
	}
	public static void main(String args[]){
		OrderStatistcEChartAction o=new OrderStatistcEChartAction();
		o.getOrderEChart();
	}
	public class Condition {
		private Integer dateType;//1 7日，2 15日,3 30日
		private Integer chartType;//1 订单金额 2订单数量
		
		
		
		
		public Integer getDateType() {
			return dateType;
		}
		public void setDateType(Integer dateType) {
			this.dateType = dateType;
		}
		public Integer getChartType() {
			return chartType;
		}
		public void setChartType(Integer chartType) {
			this.chartType = chartType;
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


	public Map getStatistic() {
		return statistic;
	}


	public void setStatistic(Map statistic) {
		this.statistic = statistic;
	}


	public String getChartdate() {
		return chartdate;
	}


	public void setChartdate(String chartdate) {
		this.chartdate = chartdate;
	}


	
}
