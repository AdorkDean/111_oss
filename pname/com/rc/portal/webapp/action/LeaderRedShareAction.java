package com.rc.portal.webapp.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.InfoUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLxRedShareManager;
import com.rc.portal.vo.TLxRedShare;
import com.rc.portal.vo.TLxRedShareExample;
import com.rc.portal.webapp.util.PageResult;
/*
 * 领秀红包分享语
 */
public class LeaderRedShareAction extends BaseAction{
	private static final long serialVersionUID = 121313l;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private TLxRedShare lxredshare;
	private TLxRedShareManager tlxredsharemanager;
	
	
	private File image ;
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
    private final String diskPath=InfoUtil.getInstance().getInfo("images", "images.public.redshare.path");
    private final Long MAXSIZE=1024*1024*5L;//限制图片大小为5M
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
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
	public TLxRedShare getLxredshare() {
		return lxredshare;
	}
	public void setLxredshare(TLxRedShare lxredshare) {
		this.lxredshare = lxredshare;
	}
	public TLxRedShareManager getTlxredsharemanager() {
		return tlxredsharemanager;
	}
	public void setTlxredsharemanager(TLxRedShareManager tlxredsharemanager) {
		this.tlxredsharemanager = tlxredsharemanager;
	}
	/**
	 * 添加跳转
	 */
	public String add() throws Exception{
		return "add";
	}
	/**
	 * 添加保存
	 */
	public String save() throws Exception{
		fileImage();
		lxredshare.setCreateDt(new Date());
		lxredshare.setIsDelete(0);//删除状态  0 未删除  1 已删除
		//lxredshare.setSendDt(new Date());
		lxredshare.setStatus(0);//发布状态 0 未发布 1 已发布
		tlxredsharemanager.insertSelective(lxredshare);
		return "save";
	}
	/**
	 * 修改
	 */
	public String update() throws Exception{
		fileImage();
		lxredshare.setStatus(0);
		tlxredsharemanager.updateByPrimaryKeySelective(lxredshare);
		return "save";
	}
	/**
	 * 页面跳转
	 */
	public String view() throws Exception{
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			Map<String, String> map =new HashMap<String, String>();
			map.put("id", id);
			lxredshare = tlxredsharemanager.selectByPrimaryKey(Long.valueOf(id));
		}
		return "view";
	}
	/*
	 * 删除
	 */
	public void delete() throws Exception{
		String id = getRequest().getParameter("id");
		if(StringUtils.hasText(id)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			long n = tlxredsharemanager.deleteByPrimaryKey(Long.valueOf(id));
			out.print(n);
			out.close();
		}
	}
	/*
	 * 发布
	 */
	public void release() throws Exception{
		String ids = getRequest().getParameter("id");
		if(StringUtils.hasText(ids)){
			this.getRequest().setCharacterEncoding("utf-8");
		    this.getResponse().setContentType("text/plain;charset=utf-8");
			PrintWriter out =  getResponse().getWriter();
			String[] id = ids.split(",");
			List<Long> list = new ArrayList<Long>();
			for (String idstr : id) {
				list.add(Long.valueOf(idstr));
			}
			TLxRedShare record = new TLxRedShare();
			record.setStatus(1);//已发布
			TLxRedShareExample example = new TLxRedShareExample();
			example.createCriteria().andIdIn(list);
			long n = tlxredsharemanager.updateByExampleSelective(record, example);
			out.print(n);
			out.close();
		}
	}
	/**
	 * 列表
	 */
	public String list() throws Exception{
		Map<String, String> map =new HashMap<String, String>();
		rs.setP_pageSize(20);
	    pw = opensqlmanage.selectForPageByMap_drug(map, "t_lx_red_share.selectRedShareRecordCount", "t_lx_red_share.selectRedShareRecord", rs.getP_curPage(), rs.getP_pageSize());
		return "list";
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
	/**
	 * 检查文件类型
	 * @param type
	 * @return
	 */
	public boolean checkFileType(String type){
		boolean flag=false;
		type = type.toLowerCase();
		String[] arrType={"jpg","png","gif","jpeg"};
		for(String s:arrType){
			if(type.equals(s)){
				return true;
			}
		}
		return flag;
	}
	public static String getDiskName(){
		String diskname="";
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		diskname =df.format(new Date());
		return diskname;
		
	}
	public void fileImage() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
	    this.getResponse().setContentType("text/plain;charset=utf-8");
		String basePath = this.getRequest().getSession().getServletContext().getRealPath("/");
		String filename = getImageFileName();
		String fileType= FilenameUtils.getExtension(filename);
		File file =getImage();
		try {
			if(checkFileType(fileType)){
				if(file.length()<MAXSIZE){
					String fullPath=diskPath+getDiskName()+"/"+DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()));
					String imgUrl=fullPath+"."+fileType;
					String fullName=basePath+imgUrl;
					File uploadFile = new File(fullName);
					FileUtils.copyFile(file, uploadFile);//上传图片
					lxredshare.setShareImageUrl(imgUrl);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
