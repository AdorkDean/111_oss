package com.rc.portal.webapp.action;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.commons.freemarker.UtilStatic;
import com.rc.portal.service.THealthyPlanManager;
import com.rc.portal.vo.THealthyPlan;
import com.rc.portal.webapp.model.HealthyPlanContentModel;

public class HealthyPlaContentAction extends BaseAction{
	private THealthyPlanManager thealthyplanmanager;
	private HealthyPlanContentModel obj;
	
	public String view() throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
//    	map.put("id", 1);
//    	map.put("offSet", 0);
//    	map.put("pageSize", 1);
		List<HealthyPlanContentModel> objs = thealthyplanmanager.getHealthyPlanContent(map);
		obj=objs.get(0);
		return "view";
	}
	private void actionStatic(Long id) throws Exception{
		 setUTF8(getRequest(), getResponse());
		 HashMap<String, Object> ction = new HashMap<String, Object>();
		 ction.put("id", id);
		 ction.put("offSet", 0);
		 ction.put("pageSize", 1);
		 List<HealthyPlanContentModel> list = thealthyplanmanager.getHealthyPlanContent(ction);
		 if(null!=list&&0<list.size()){
			 HealthyPlanContentModel obj = list.get(0);
			 if(0==obj.getStatus()){//是否发布
				 //重新封装模板数据
				 HashMap<Object, Object> root = new HashMap<Object, Object>();
				 root.put("obj", obj);
				 String staticPageUrl = getSession().getServletContext().getRealPath("/")+"static/healthypla/h/"+id+".html";
				 String templateFileSourceUrl = getSession().getServletContext().getRealPath("/")+ "WEB-INF/T/static";
				 //删除原来静态页
				 File oldFile = new File(staticPageUrl);
				 if(oldFile.exists()){
					 oldFile.delete();
				 }
				 //生成新静态页
				 UtilStatic.doTemplatePage(root,templateFileSourceUrl,"healthyPlaContent.ftl",staticPageUrl);
			 }
		}
	 }
	public void setUTF8(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
	}
	public void countpricebyplatform() throws Exception{
		getResponse().setCharacterEncoding("utf-8");
		PrintWriter out =  getResponse().getWriter();
		//解析JSONP参数组
		String jsoncallback = getRequest().getParameter("jsoncallback");
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		out.println(jsoncallback+"("+JSONArray.fromObject(result)+");");
		out.flush();
		out.close();
	}
	/**
	 * 异步跨域处理数据获取商品相关信息
	 */
	public void getHealthyPlaContentInfo() throws Exception{
		String id = getRequest().getParameter("id");
		Map<String, Object> mapv = new HashMap<String, Object>();
		mapv.put("id", Long.parseLong(id));
		Map<String, Object> map = thealthyplanmanager.getHealthyPlaContentInfo(mapv);
		
		getResponse().setCharacterEncoding("utf-8");
		PrintWriter out =  getResponse().getWriter();
		//解析JSONP参数组
		String jsoncallback = getRequest().getParameter("jsoncallback");//与前台ajax定义相同
		out.println(jsoncallback+"("+JSONArray.fromObject(map)+");");
		out.flush();
		out.close();
		
//		writeObjectToResponse(map, ContentType.application_json);
	}
	/**
	 * 异步获取商品相关信息废弃
	 */
//	public void getHealthyPlaContentInfo() throws Exception{
//		String id = getRequest().getParameter("id");
//		Map<String, Object> mapv = new HashMap<String, Object>();
//		mapv.put("id", Long.parseLong(id));
//		Map<String, Object> map = thealthyplanmanager.getHealthyPlaContentInfo(mapv);
//		writeObjectToResponse(map, ContentType.application_json);
//	}
	public void pushPlan(){
		String planId = this.getRequest().getParameter("planId");
		String flag ="0";
		if(StringUtils.hasText(planId)){
			try {
				this.actionStatic(Long.valueOf(planId.trim()));
				THealthyPlan thealthyplan = new THealthyPlan();
				thealthyplan.setId(Long.valueOf(planId.trim()));
				thealthyplan.setStatus(1);
				this.thealthyplanmanager.updateByPrimaryKeySelective(thealthyplan);
				flag="1";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		writeObjectToResponse(flag, ContentType.application_json);
	}
	
	
	public THealthyPlanManager getThealthyplanmanager() {
		return thealthyplanmanager;
	}

	public void setThealthyplanmanager(THealthyPlanManager thealthyplanmanager) {
		this.thealthyplanmanager = thealthyplanmanager;
	}

	public HealthyPlanContentModel getObj() {
		return obj;
	}

	public void setObj(HealthyPlanContentModel obj) {
		this.obj = obj;
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	public void setModel(Object o) {
	}

}
