package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.commons.util.DateUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.util.StringSearchHelper;

@SuppressWarnings("unchecked")
public class OrderStatisticAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private OpenSqlManage opensqlmanage;
	
	public void orderAddressStatistic()throws Exception{
		
		String beginDate = getRequest().getParameter("beginDate");
		String endDate = getRequest().getParameter("endDate");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		List list  =  opensqlmanage.selectForListByMap(map, "order_statistic.statisticReceiverCnt");
		
		Map statisticCntMap = new HashMap();
		
		Map orderAddressMap = null;
		
		StringBuilder jsonStr = new StringBuilder();
		if(list != null){
			for(int i=0; i<list.size(); i++){
				orderAddressMap = (Map) list.get(i);
				String address = (String) orderAddressMap.get("area_name");
				String cityName = StringSearchHelper.search(address, "(?<=-|^)[^-$\n]+(?=(市|自治州|地区)-)", 0);
				if(org.apache.commons.lang.StringUtils.isEmpty(cityName)){
					continue;
				}
				if(statisticCntMap.get(cityName) == null || statisticCntMap.get(cityName).equals( "null")){
					statisticCntMap.put(cityName, 1);
				}else{
					statisticCntMap.put(cityName, (Integer)statisticCntMap.get(cityName) + 1);
				}
			}
		}
		
		if(statisticCntMap != null){
			StringBuilder markPointdata = new StringBuilder("[");
			
			jsonStr.append(" {\"markLinedata\":");
			jsonStr.append("[");
			
			Set keySet = statisticCntMap.keySet();
			Iterator ite = keySet.iterator();
			while(ite.hasNext()){
				String areaName = (String) ite.next();
				int cnt = (Integer) statisticCntMap.get(areaName);
				jsonStr.append("[{\"name\":\"北京\"}, {\"name\":\"").append(areaName).append("\"").append(",\"value\":").append(cnt).append("}],");
				markPointdata.append("{\"name\":\"").append(areaName).append("\"").append(",\"value\":").append(cnt).append("},");
			}
			if(keySet.size() > 0){
				jsonStr = new StringBuilder(jsonStr.substring(0, jsonStr.length()-1));
				markPointdata = new StringBuilder(markPointdata.substring(0, markPointdata.length()-1));
			}
			markPointdata.append("]");
			
			jsonStr.append("]");
			jsonStr.append(",\"markPointdata\":").append(markPointdata).append("}");
		}
		
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out =  getResponse().getWriter();
		out.print(jsonStr);
		out.close();
	}
	
	/**
	 * 商品编号,批准文号,条形码 唯一验证
	 */
	public String toOrderAddressStatistic()throws Exception{
		Date beginDate = com.rc.portal.util.DateUtil.dateSubDays(new Date(), 7);
		getRequest().setAttribute("beginDate", DateUtil.getPlusTime(beginDate));
		return "order_address_statistic";
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {
		
	}
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}
}


