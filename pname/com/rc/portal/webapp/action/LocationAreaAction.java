package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.portal.service.CLocationManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.vo.CLocation;

/**
 * 处理前台展示地区
 * @author 刘天灵
 *
 */
public class LocationAreaAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	public Object getModel() {
		return null;
	}

	private OpenSqlManage opensqlmanage;
	
	private CLocationManager clocationmanager;
	
	public CLocationManager getClocationmanager() {
		return clocationmanager;
	}

	public void setClocationmanager(CLocationManager clocationmanager) {
		this.clocationmanager = clocationmanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public void setModel(Object o) {}
	
	/**
	 * 根据地区id获取上级区域下面区域数据
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void get() throws NumberFormatException, SQLException{
		String parentId = this.getRequest().getParameter("parentId");
		
		CLocation parent = null;
		
		if(StringUtils.isNotEmpty(parentId)){
			parent = clocationmanager.selectByPrimaryKey(Integer.parseInt(parentId));
		}
		
		List<CLocation> locations = null;
		if(parent!=null){
			locations = (List)opensqlmanage.selectListByObject(parentId, "c_location.selectChildById");
		}else{
			locations = (List)opensqlmanage.selectListByObject(null, "c_location.selectRoot");
		}
		
		Map<Integer, String> options = new HashMap<Integer, String>();
		
		for (CLocation location : locations) {
			options.put(location.getId(), location.getName());
		}
		
		this.writeObjectToResponse(options, ContentType.application_json);
		
	}
	/**
	 * 获取地址结构树名称和id树
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void getTreePathAndName() throws NumberFormatException, SQLException{
		String id = this.getRequest().getParameter("id");
		if(StringUtils.isNumeric(id)){
			
			CLocation location = clocationmanager.selectByPrimaryKey(Integer.parseInt(id));
			String idPath = "";
			String namePath = "";
			if(location!=null){
				
				while(location!=null){
					idPath =  location.getId() + "," + idPath;
					namePath =location.getName() + "-" + namePath;
					if(location.getParentid()>0){
						location = clocationmanager.selectByPrimaryKey(location.getParentid());
					}else{
						location = null;
					}
				}
				idPath =  "," + idPath;
				namePath = namePath.substring(0, namePath.length()-1);
				Map<String,String> result = new HashMap<String,String>();
				result.put("idPath", idPath);
				result.put("namePath", namePath);
				this.writeObjectToResponse(result, ContentType.application_json);
			}else{
				Map<String,String> result = new HashMap<String,String>();
				result.put("idPath", idPath);
				result.put("namePath", namePath);
				this.writeObjectToResponse(result, ContentType.application_json);
			}
			
		}
	}
}
