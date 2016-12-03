package com.rc.portal.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.webwork.ServletActionContext;
import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.freemarker.InfoUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TBrandManager;
import com.rc.portal.vo.TBrand;
import com.rc.portal.vo.TBrandExample;
import com.rc.portal.webapp.util.MD5;
import com.rc.portal.webapp.util.PageResult;

public class BrandAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private TBrandManager tbrandmanager;
	private TBrand brand;
	private File file;
	
	//查询品牌列表
	public String getBrandList(){
		Map map=new HashMap();
		if(model.getBrandName()!=null&&!"".equals(model.getBrandName().trim())){
			map.put("brandName", model.getBrandName().trim());
		}
		if(model.getPinyin()!=null&&!"".equals(model.getPinyin().trim())){
			map.put("pinyin", model.getPinyin().trim());
		}
		if(model.getUrl()!=null&&!"".equals(model.getUrl().trim())){
			map.put("url", model.getUrl().trim());
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_brand.selectBrandCountByMap", "t_brand.selectBrandListByMap", rs.getP_curPage(), rs.getP_pageSize());
		
		return "getBrandList";
	}
	//进入品牌添加或者修改
	public String addOrUpdateBrand() throws Exception{
		if(model.getId()!=null&&model.getId()!=0){
			brand= tbrandmanager.selectByPrimaryKey(model.getId());
		}
		return "addOrUpdateBrand";
	}
	
	//保存品牌添加或者修改
	public void saveOrEditBrand() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		Integer flag=0;
		flag=tbrandmanager.saveOrEditBrand(brand);
		write.write(flag.toString());
		write.close();
		
	}
	
	
	public void like() throws SQLException, IOException{
		
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String content = this.getRequest().getParameter("q")!=null?this.getRequest().getParameter("q").trim():"";
		
		TBrandExample e = new TBrandExample();
		e.createCriteria().andBrandNameLike("%"+content+"%");
		List<TBrand> list = tbrandmanager.selectByExample(e);
		
		StringBuilder sb = new StringBuilder();
		for(TBrand tb : list){
			sb.append(tb.getBrandName()+"|"+tb.getId()+ "\n");
		}
		out.write(sb.toString()); //修改失败
		out.close();	
		
		  
	}
	
	public void saveImage() {
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		FileOutputStream outputStream = null;
		String filename = "brand_" + MD5.MD5(System.currentTimeMillis() + "");
		try {
			out = this.getResponse().getWriter();
			String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
			String path = basePath+InfoUtil.getInstance().getInfo("config", "brand.img.path");
			File newFilePathFile = new File(path); 
			if(!newFilePathFile.exists()){
				newFilePathFile.mkdir();
				newFilePathFile.mkdirs();
			}
			String p = path + filename + ".jpg";
			if (null != file) {
				System.out.println( file.getName());
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
			out.print(InfoUtil.getInstance().getInfo("config", "brand.img.path")+filename + ".jpg");
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
		private String brandName;
		private String pinyin;
		private String url;
		private Long id;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getBrandName() {
			return brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
		public String getPinyin() {
			return pinyin;
		}
		public void setPinyin(String pinyin) {
			this.pinyin = pinyin;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
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
	public TBrand getBrand() {
		return brand;
	}
	public void setBrand(TBrand brand) {
		this.brand = brand;
	}
	public TBrandManager getTbrandmanager() {
		return tbrandmanager;
	}
	public void setTbrandmanager(TBrandManager tbrandmanager) {
		this.tbrandmanager = tbrandmanager;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	
}
