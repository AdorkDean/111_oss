package com.rc.portal.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.InfoUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TManufacturerManager;
import com.rc.portal.vo.TManufacturer;
import com.rc.portal.webapp.util.MD5;
import com.rc.portal.webapp.util.PageResult;

public class ManufacturerAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private TManufacturerManager tmanufacturermanager;
	private TManufacturer manu;
	private File file;
	
	//查询生产厂家列表
	public String getManufacturerList(){
		Map map=new HashMap();
		if(model.getManuName()!=null&&!"".equals(model.getManuName().trim())){
			map.put("manuName", model.getManuName().trim());
		}
		if(model.getStatus()!=null&&model.getStatus()!=-1){
			map.put("status", model.getStatus());
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_manufacturer.selectManuCountByMap", "t_manufacturer.selectManuListByMap",rs.getP_curPage(), rs.getP_pageSize());
		return "getManufacturerList";
	}
	
	//跳转到添加或者修改页面
	public String addOrUpdateManu() throws Exception{
		if(model.getId()!=null&&model.getId()!=0){
			manu = tmanufacturermanager.selectByPrimaryKey(model.getId());
		}
		return "addOrUpdateManu";
	}
	//保存添加修改生产厂家数据
	public void saveOrEditManu() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		flag=tmanufacturermanager.saveOrEditManu(manu);
		write.write(flag.toString());
		write.close();
	}
	
	public void saveImage() {
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		FileOutputStream outputStream = null;
		String filename = "manu_" + MD5.MD5(System.currentTimeMillis() + "");
		try {
			out = this.getResponse().getWriter();
			String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
			String path = basePath+InfoUtil.getInstance().getInfo("config", "manu.img.path");
			String p = path + filename + ".jpg";
			File newFilePathFile = new File(path); 
			if(!newFilePathFile.exists()){
				newFilePathFile.mkdir();
				newFilePathFile.mkdirs();
			}
			if (null != file) {
				outputStream = new FileOutputStream(p);
				FileInputStream fileIn = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fileIn.read(buffer)) > 0) {
					outputStream.write(buffer, 0, len);
				}
				fileIn.close();
				outputStream.close();
			}
			out.print(InfoUtil.getInstance().getInfo("config", "manu.img.path")+filename + ".jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("-1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("-1");
		} finally {
			System.out.println("finally......");
			out.close();
		}

	}
	public class Condition {
		private String manuName;
		private Integer status;
		private Long id;
		
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getManuName() {
			return manuName;
		}
		public void setManuName(String manuName) {
			this.manuName = manuName;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
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

	public TManufacturerManager getTmanufacturermanager() {
		return tmanufacturermanager;
	}

	public void setTmanufacturermanager(TManufacturerManager tmanufacturermanager) {
		this.tmanufacturermanager = tmanufacturermanager;
	}

	public TManufacturer getManu() {
		return manu;
	}

	public void setManu(TManufacturer manu) {
		this.manu = manu;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


	
}
