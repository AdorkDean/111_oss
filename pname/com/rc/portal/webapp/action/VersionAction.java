package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.CVersionManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.vo.CVersion;
import com.rc.portal.webapp.util.PageResult;

public class VersionAction extends BaseAction {
	
	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private CVersion cversion = new CVersion();
	private CVersionManager cversionmanager;
	private String phone;
	private CVersion v = new CVersion();
	private String flag ;
	
	public static void main(String[] args) throws Exception {
			
		    VersionAction o = new VersionAction();
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
			o.opensqlmanage = (OpenSqlManage) context.getBean("opensqlmanage");
			o.verionList();
			
	}
	
	public void deleteRecord(){
		String id = getRequest().getParameter("id");
		PrintWriter out = null;
		this.getResponse().setContentType("text/html; charset=utf-8");
		
		int backid = 0;
		try {
			out= this.getResponse().getWriter();
			backid = cversionmanager.deleteByPrimaryKey(Integer.parseInt(id));
			out.print(backid);
			out.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			out.print(backid);
			out.close();
			e.printStackTrace();
		} catch (SQLException e) {
			out.print(0);
			out.close();
			e.printStackTrace();
		} catch (IOException e) {
			out.print(0);
			out.close();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void getVersionRecord(){
		
		String id = getRequest().getParameter("id");
		PrintWriter out = null;
		this.getResponse().setContentType("text/html; charset=utf-8");
		try {
			this.getRequest().setCharacterEncoding("utf-8");
			out = this.getResponse().getWriter();
			cversion = cversionmanager.selectByPrimaryKey(Integer.parseInt(id));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.print(JSONObject.fromObject(cversion).toString());
		out.close();
		
	}

	//健康测试列表
	public String verionList(){
		Map map = new HashMap();
		if(!"-1".equals(phone)){
			map.put("phone", phone);
		}
		pw = opensqlmanage.selectForPageByMap_drug(map, "c_version.get-version-count", "c_version.get-version", rs.getP_curPage(), rs.getP_pageSize());
		return "version_list";
	}
	
	public void addNewVersion() throws IOException{
		PrintWriter out = this.getResponse().getWriter();
		
		
		int  id = 0;
		try {
			if("add".equals(flag)){
				Map m = new HashMap();
				m.put("info", cversion.getVersionInfo());
				m.put("phone", cversion.getType());
				int exists = (Integer) opensqlmanage.selectForObjectByMap(m, "c_version.is-exits");
				if(exists>0){
					out.print("-2");	// 要添加的版本号已存在 
					out.close();
					return;
				}
				cversion.setCreateDate(new Date());
				cversion.setStatus(1);
				cversion.setModifyDate(new Date());
				
				id = cversionmanager.insert(cversion);
				
			}else{
				cversion.setId(cversion.getId());
				id = cversionmanager.updateByPrimaryKeySelective(cversion);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print(id);
		out.close();
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

	public CVersion getCversion() {
		return cversion;
	}

	public void setCversion(CVersion cversion) {
		this.cversion = cversion;
	}

	public CVersionManager getCversionmanager() {
		return cversionmanager;
	}

	public void setCversionmanager(CVersionManager cversionmanager) {
		this.cversionmanager = cversionmanager;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CVersion getV() {
		return v;
	}

	public void setV(CVersion v) {
		this.v = v;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	

}
