package com.rc.portal.webapp.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.InfoUtil;
import com.rc.commons.util.StringUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TPhysicianManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.vo.TPhysician;
import com.rc.portal.vo.TPhysicianExample;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;

public class PhysicianAction extends BaseAction {

	private static final long serialVersionUID = 17767535L;
	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private TPhysicianManager tphysicianmanager;
	private TPhysician physician = new TPhysician();
	
	private File image ;
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
    private final String diskPath=InfoUtil.getInstance().getInfo("images", "images.public.head.path");
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

	public TPhysicianManager getTphysicianmanager() {
		return tphysicianmanager;
	}

	public void setTphysicianmanager(TPhysicianManager tphysicianmanager) {
		this.tphysicianmanager = tphysicianmanager;
	}

	public TPhysician getPhysician() {
		return physician;
	}

	public void setPhysician(TPhysician physician) {
		this.physician = physician;
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

	public String add() {

		return "add";
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
	 * 药师列表
	 * @return
	 */
	public String list() throws Exception{
		
		TPhysicianExample example = new TPhysicianExample();
		if(physician != null){
			if(!StringUtil.isEmpty(physician.getRealName()))
			example.createCriteria().andRealNameLike("%"+physician.getRealName()+"%");
		}
		
		pw = opensqlmanage.selectForPageByMap_drug(example, "t_physician.ibatorgenerated_countByExample", "t_physician.selectByExampleByPage", rs.getP_curPage(), rs.getP_pageSize());
		
		return "list";
	}
	/**
	 * 修改药师信息
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	
	public String edit() throws Exception{
		String physicianId = this.getRequest().getParameter("id");
		if(physicianId!=null){
			physician = tphysicianmanager.selectByPrimaryKey(Long.valueOf(physicianId));
			/*Object selectObjectByObject = this.opensqlmanage.selectObjectByObject(physician, "t_physician.ibatorgenerated_selectByPrimaryKey");
			if(selectObjectByObject!=null){
				physician = (TPhysician) this.opensqlmanage.selectObjectByObject(physician, "t_physician.ibatorgenerated_selectByPrimaryKey");
			}	*/		
		}
		return "edit";
	}
	/**
	 * 更新
	 * @return
	 */
	public String updatePhysicianInfo() throws Exception{
		if(this.physician!=null){
			fileImage();
			this.tphysicianmanager.updateByPrimaryKeySelective(this.physician);
			this.addFlashMessage(true);
		}
		return "redirect";
	}
	/**
	 * 添加药师信息
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public String savePhysicianInfo() throws Exception {
		Integer count = (Integer) this.opensqlmanage.selectForObjectByMap(null, "t_physician.selectPhysicianCount");
		if(count>=10){
			this.getRequest().setAttribute("count", count);
		}else{
			fileImage();
			UserinfoModel user = (UserinfoModel) this.getSession().getAttribute(
					ConstantUtil.logincookiename);
			physician.setCardUrl(null);
			physician.setCardCode(null);
			physician.setSeniorityUrl(null);
			physician.setAuditType(null);
			physician.setAuditRemark(null);
			physician.setAdminId(Long.valueOf(user.getUserid()));
			tphysicianmanager.insert(physician);
		}
		return "redirect";
	}
	/*
	 * 获取药师数量
	 */
	public void getPhysicianCount() throws Exception {
		int flag=-1;
		PrintWriter out = this.getResponse().getWriter();
		Integer count = (Integer) this.opensqlmanage.selectForObjectByMap(null, "t_physician.selectPhysicianCount");
		if(count>=10){
			flag=0;
		}
		out.print(flag);
		out.close();
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
		//String filename = this.getRequest().getParameter("image");
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
					physician.setHeadUrl(imgUrl);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
