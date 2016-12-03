package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.oss.service.impl.OpenSqlManager;
import com.rc.oss.service.impl.TAdmModulesManager;
import com.rc.oss.service.impl.TAdmOperationLogsManager;
import com.rc.oss.service.impl.TAdmPostManager;
import com.rc.oss.service.impl.TAdmUserPostManager;
import com.rc.oss.service.impl.TAdmUserRolesManager;
import com.rc.oss.service.impl.TAdmUsersManager;
import com.rc.oss.service.impl.TAdmUsersModulesManager;
import com.rc.oss.vo.TAdmModules;
import com.rc.oss.vo.TAdmPost;
import com.rc.oss.vo.TAdmUserPost;
import com.rc.oss.vo.TAdmUsers;
import com.rc.oss.vo.TAdmUsersModules;
import com.rc.portal.dao.TAdmUserRolesDAO;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.impl.TAdmModulesManagerImpl;
import com.rc.portal.service.impl.TAdmOperationLogsManagerImpl;
import com.rc.portal.service.impl.TAdmRolesManagerImpl;
import com.rc.portal.service.impl.TAdmUserRolesManagerImpl;
import com.rc.portal.service.impl.TAdmUsersManagerImpl;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.util.WebConfigFactory;
import com.rc.portal.vo.TAdmRoles;
import com.rc.portal.vo.TAdmUsersExample;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.MD;
import com.rc.portal.webapp.util.PageResult;

/**  
 * @Title: ShowUser.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2011-4-18 下午07:23:59
 * @version V1.0  
 */

public class ShowUser extends BaseAction {
	

	private TAdmUserRolesDAO tadmuserrolesdao;
	private TAdmUsersManagerImpl tadmusersmanager;
	private TAdmUserRolesManagerImpl tadmuserrolesmanager;
	private TAdmRolesManagerImpl tadmrolesmanager;
	private TAdmModulesManagerImpl tadmmodulesmanager;
	private TAdmOperationLogsManagerImpl tadmoperationlogsmanager;
	private OpenSqlManage opensqlmanage;
	private PageWraper pw=new PageWraper();
	private PageResult rs = new PageResult();
	private Condition model=new Condition();	
	private TAdmUsers userinfo;
	private OpenSqlManager openSqlManager;
	private TAdmOperationLogsManager tAdmOperationLogsManager;
	private TAdmUsersManager tAdmUsersManager; 
	private TAdmUserRolesManager tAdmUserRolesManager;
	private TAdmUserPostManager tAdmUserPostManager;
	private TAdmPostManager tAdmPostManager;
	private TAdmUsersModulesManager tAdmUsersModulesManager;
	private TAdmModulesManager tAdmModulesManager;
	
	
/*	public String listUsers() throws SQLException{
		TAdmUsersExample tue=new TAdmUsersExample();
		tue.setOrderByClause(" id desc");
		Criteria criteria = tue.createCriteria();
		if(model.getLoginname()!=null && model.getLoginname().trim().length()>0){
			criteria.andLoginNameLike("%"+model.getLoginname().trim()+"%");
		}
		if(model.getDepartment()!=null && model.getDepartment().trim().length()>0){
			criteria.andDepartmentLike("%"+model.getDepartment().trim()+"%");
		}
		if(model.getMobile()!=null && model.getMobile().trim().length()>0){
			criteria.andTelephoneLike("%"+model.getMobile().trim()+"%");
		}
		if(model.getEmail()!=null && model.getEmail().trim().length()>0){
			criteria.andEmailLike("%"+model.getEmail().trim()+"%");
		}
		tue.getPageInfo().setPage(rs.getP_curPage());
		tue.getPageInfo().setPageSize(rs.getP_pageSize());
		pw = tadmusersmanager.selectByRepositorybypage(tue);
		tadmoperationlogsmanager.record(getRequest(), "用户管理-列表");
		return SUCCESS;
	}*/
	
	public String listUsers() throws SQLException{
		PageInfo pageInfo =new PageInfo();
		pageInfo.setPage(rs.getP_curPage());
		pageInfo.setPageSize(rs.getP_pageSize());
		Map map=new HashMap();
		if(model.getLoginName()!=null && model.getLoginName().trim().length()>0){
			map.put("loginName", model.getLoginName());
		}
		if(model.getDepartment()!=null && model.getDepartment().trim().length()>0){
			map.put("department", model.getDepartment());
		}
		if(model.getMobile()!=null && model.getMobile().trim().length()>0){
			map.put("mobile", model.getMobile());
		}
		if(model.getEmail()!=null && model.getEmail().trim().length()>0){
			map.put("email", model.getEmail());
		}
		try {
			pw = openSqlManager.getListForDynamicsPageHql("users.list", "users.count", map, pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		tadmoperationlogsmanager.record(getRequest(), "用户管理-列表");
		tAdmOperationLogsManager.record(getRequest(), "用户管理-列表");
		return SUCCESS;
	}
	
	
	public String editUser(){
		TAdmUsersExample tue=new TAdmUsersExample();
		tue.createCriteria().andIdEqualTo(userinfo.getId());
//		String pwd=MD.MD5(userinfo.getPassword());
//		userinfo.setPassword(pwd);
		userinfo.setId(null);
		try {
//			tadmusersmanager.updateByRepositorySelective(userinfo, tue);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	public void chgpwd() throws IOException, SQLException{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = this.getResponse().getWriter();
		String pwd = this.getRequest().getParameter("pwd");
		String userid = this.getRequest().getParameter("userid");
		if(pwd==null || pwd.trim().length()==0){
			out.print("请输入新密码!");
		}else{
			String pwd_md5=MD.MD5(pwd.trim());
			TAdmUsers user=tAdmUsersManager.findById(Long.parseLong(userid));
			user.setPassword(pwd_md5);
//			tadmusersmanager.updateByPrimaryKeySelective(user);
			tAdmUsersManager.save(user);
			out.print("修改成功!");
		}
	}
	
	/**
	 * 修改增加初始化
	 * @return
	 */
	public String toedituser(){		
		if(userinfo!=null && userinfo.getId()!=null){
			try {
//				userinfo=tadmusersmanager.selectByPrimaryKey(userinfo.getId());
				userinfo = tAdmUsersManager.findById(userinfo.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "toedituser";
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String addUser() throws Exception{
		if(userinfo!=null && userinfo.getId()!=null){
//			TAdmUsersExample tae=new TAdmUsersExample();
//			tae.createCriteria().andIdEqualTo(userinfo.getId());
			TAdmUsers tu = new TAdmUsers();
			tu = tAdmUsersManager.findById(userinfo.getId());
			String pwd=MD.MD5(userinfo.getPassword());
			tu.setLoginName(userinfo.getLoginName());
			tu.setName(userinfo.getName());
			tu.setPassword(pwd);
			tu.setDepartment(userinfo.getDepartment());
			tu.setTelephone(userinfo.getTelephone());
			tu.setEmail(userinfo.getEmail());
			tu.setStatus(userinfo.getStatus());
			tu.setLastEditTime(new Date());
//			userinfo.setId(null);
//			tadmusersmanager.updateByRepositorySelective(userinfo,tae);
//			tadmoperationlogsmanager.record(getRequest(), "用户管理-修改保存");
			tAdmUsersManager.save(tu);
			tAdmOperationLogsManager.record(getRequest(), "用户管理-修改保存");
			return "skip";
		}else{
			boolean f = isRegedit(userinfo.getName());
			
	    	String password = userinfo.getPassword()!=null?userinfo.getPassword():"";
	    	userinfo.setPassword(MD.MD5(password.toLowerCase()));
//	        int s1 = 1;
	        userinfo.setAllowDelete(1);
	    	userinfo.setCreateTime(new Date());
	    	userinfo.setLastEditTime(new Date());
//	    	UserinfoModel user = HttpSessionUtil.getLoginUserModel();	
	    	UserinfoModel user =(UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
//			Long id = tadmusersmanager.insert(userinfo);
//			tadmuserrolesdao.afford_login_role(id,Long.parseLong(user.getUserid()));
//			tadmoperationlogsmanager.record(getRequest(), "用户管理-新增保存");
	    	tAdmUsersManager.save(userinfo);
	    	//需要给新增用户增加权限
	    	tAdmOperationLogsManager.record(getRequest(), "用户管理-新增保存");
			return "skip";
		}
	}
	
	//删除用户
	public String deleteUser(){
		if(userinfo!=null && userinfo.getId()!=null){
			
//			TAdmUsersModulesExample tum = new TAdmUsersModulesExample();
//			tum.createCriteria().andUseridEqualTo(userinfo.getId());
		    try {
		    	TAdmUsers tus = new TAdmUsers();
		    	tus = tAdmUsersManager.findById(userinfo.getId());
//				int i =	tadmusersmodulesmanager.countByRepository(tum);
				if(tus==null || tus.equals("")){
					
					return "错误提示";
				}else{
//					TAdmUserRolesExample tur = new TAdmUserRolesExample();
//					tur.createCriteria().andUserIdEqualTo(userinfo.getId());
//					int j = tadmuserrolesmanager.countByRepository(tur);
//					if(j>0){
//						return "错误提示";
//					}else{
					  //int c = tadmusersmanager.deleteByPrimaryKey(userinfo.getId());
					  TAdmUsers tdu=new TAdmUsers();
					  tdu = tAdmUsersManager.findById(userinfo.getId());
					  tAdmUsersManager.delete(tdu);
//					  TAdmUsersExample tex=new TAdmUsersExample();
//					  t.setStatus(Short.parseShort("2"));
//					  tex.createCriteria().andIdEqualTo(userinfo.getId());
//					  int c=tadmusersmanager.updateByRepositorySelective(t, tex);
//					  if(c>0){
//						  return "skip";
//					  }else{
//						  return "错误提示"; 
//					  }
//					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
//		tadmoperationlogsmanager.record(getRequest(), "用户管理-删除用户");
		tAdmOperationLogsManager.record(getRequest(), "用户管理-删除用户");
		return "skip";
	}
	
	
	/**
	 * 用户名是否可以注册
	 * @param username
	 * @return true 可以注册/false 不可以注册
	 */
	public boolean isRegedit(String username){
//		TAdmUsersExample user=new TAdmUsersExample();
//		user.createCriteria().andNameEqualTo(username);
		Map map = new HashMap();
		map.put("loginName", username);
		List<TAdmUsers> list = null;
		try {
//			 count=tadmusersmanager.countByRepository(user);
			list = openSqlManager.getListByDynamicsHql("users.count", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list==null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改用户模块
	 * @return
	 */
	public String editUserModules(){
//			tadmusersmodulesmanager.editUserModules(Long.parseLong(model.getUserid()), model.getModuleId(),model.getModuleall());
			TAdmUsers user = tAdmUsersManager.findById(Long.parseLong(model.getUserid()));
//			Set set = user.getTAdmUsersModuleses();
//			List<TAdmUsersModules> persistentInstances=new ArrayList<TAdmUsersModules>(set);
//			tAdmUsersModulesManager.deleteAll(persistentInstances);
			tAdmUsersModulesManager.saveAdminMoudel(model.getUserid(),model.getModuleId(),user,model.getModuleall());
		WebConfigFactory.getFlashAuthorization().flash(this.getRequest().getSession().getServletContext());//刷新内存权限配置
		tAdmOperationLogsManager.record(getRequest(), "用户管理-修改用户模块保存");
		return "skip";
	}
	
	
	
	/**
	 * 修改模块初始化
	 * @return
	 */
	public  String toEditModule(){
		String userid = model.getUserid();
		Map map=new HashMap();
		if(model!=null && model.getUserid()!=null && model.getUserid().trim().length()>0){
			map.put("userid", model.getUserid());
		}
		if(model!=null && model.getWebid()!=null && model.getWebid().trim().length()>0){
			map.put("webid", model.getWebid());
		}else{
			map.put("webid", "1");
			model.setWebid("-1");
		}
		if(model!=null && model.getModulename()!=null && model.getModulename().trim().length()>0){
			map.put("name", model.getModulename());
		}
		TAdmUsers user = tAdmUsersManager.findById(new Long(userid));
		Set set = user.getTAdmUsersModuleses();
		List<TAdmUsersModules> listUM=new ArrayList<TAdmUsersModules>(set);
		List<TAdmModules> listParent = openSqlManager.getListByDynamicsHql("modules.listparent", map);//查询出所有的父模块
		List<TAdmModules> listM = openSqlManager.getListByDynamicsHql("modules.list", map);//查询出所有的模块
		this.getRequest().setAttribute("listUM", listUM);
		this.getRequest().setAttribute("listParent", listParent);
		this.getRequest().setAttribute("listM", listM);
		return "editModules";
	}
	
	/**
	 * 修改岗位保存
	 * @return
	 */
	public String editUserPost(){		
		try {
//			tadmuserpostmanager.editUserPost(Long.parseLong(model.getUserid()), model.getPostid(),model.getShowpost());
//			Map map=new HashMap();
//			map.put("id", new Long(model.getUserid()));
//			List<TAdmUserPost> persistentInstances = openSqlManager.getListByDynamicsHql("users.select_up_byuserid", map);
			openSqlManager.ByDynamicsDeleteHql("delete from TAdmUserPost p where p.TAdmUsers.id="+new Long(model.getUserid()));
			//			tAdmUserPostManager.deleteAll(persistentInstances);
			if(model.getPostid()!=null){
				for(String postid:model.getPostid()){
					TAdmUserPost tr=new TAdmUserPost();
					tr.setTAdmUsers(tAdmUsersManager.findById(new Long(model.getUserid())));
					tr.setTAdmPost(tAdmPostManager.findById(new Long(postid)));
					tAdmUserPostManager.save(tr);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebConfigFactory.getFlashAuthorization().flash(this.getRequest().getSession().getServletContext());//刷新内存权限配置
		tAdmOperationLogsManager.record(getRequest(), "用户管理-修改权限");
		return "skip";
	}
	
	/**
	 * 修改岗位初始化
	 * @return
	 */
	public String toEditPost(){
//		TAdmUserPostExample tre=new TAdmUserPostExample();
//		tre.createCriteria().andUseridEqualTo(Long.parseLong(model.getUserid()));
		TAdmUsers user = tAdmUsersManager.findById(new Long(model.getUserid()));
		Set set = user.getTAdmUserPosts();
		List<TAdmUserPost> list=new ArrayList<TAdmUserPost>(set);
		List<TAdmPost> listL=new ArrayList<TAdmPost>();//用户拥有的岗位
			for(TAdmUserPost t:list){
				listL.add(t.getTAdmPost());
			}
		try {
			model.setUserPost(listL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*TAdmPostExample tarsys=new TAdmPostExample();
		com.rc.portal.vo.TAdmPostExample.Criteria postc = tarsys.createCriteria();
		if(model.getPostname()!=null && model.getPostname().trim().length()>0){
			postc.andNameLike("%"+model.getPostname().trim()+"%");
		}
		if(model.getPostremark()!=null && model.getPostremark().trim().length()>0){
			postc.andRemarkLike("%"+model.getPostremark().trim()+"%");
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			postc.andWebidEqualTo(new Short(model.getWebid().trim()));
		}
		tarsys.getPageInfo().setPage(rs.getP_curPage());
		tarsys.getPageInfo().setPageSize(rs.getP_pageSize());
		
		tarsys.setOrderByClause("id desc");*/
		
		PageInfo pageInfo =new PageInfo();
		pageInfo.setPage(rs.getP_curPage());
		pageInfo.setPageSize(rs.getP_pageSize());
		Map map=new HashMap();
		if(model.getPostname()!=null && model.getPostname().trim().length()>0){
			map.put("name", model.getPostname());
		}
		if(model.getPostremark()!=null && model.getPostremark().trim().length()>0){
			map.put("remark", model.getPostremark());
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			map.put("webid", model.getWebid());
		}
		pageInfo.setPageSize(100);
		try {
			pw = openSqlManager.getListForDynamicsPageHql("post.list", "post.count", map, pageInfo);
			model.setSysPost(pw.getResult());
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return "toeditpost";
	}
	
	/**
	 * 当前用户修改密码
	 * @return
	 */
	public String getSessionUser(){
		UserinfoModel usermodel = (UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		getRequest().setAttribute("userinfo", usermodel);
			return "tochanagepassword";
	}
	public void updatePassword() throws NumberFormatException, SQLException, IOException{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = this.getResponse().getWriter();
		UserinfoModel usermodel = (UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
		if(usermodel.getUserid()!=null&&!"".equals(usermodel.getUserid().trim())){
			  userinfo = tAdmUsersManager.findById(new Long(usermodel.getUserid().trim()));
			  if(userinfo!=null){
				  userinfo.setPassword(MD.MD5(model.getPassword().trim()));
				  userinfo.setLastEditTime(new Date());
				  tAdmUsersManager.save(userinfo);
				  out.write("密码修改成功");
			  }else{
				  out.write("登录过期，请重新登录后修改密码");
			  }
		}else{
			out.write("登录过期，请重新登录后修改密码");
		}
		
	}
	public String formatString(String obj){
		if(obj==null){
			return "";
		}
		return obj;
	}
	




	public TAdmRolesManagerImpl getTadmrolesmanager() {
		return tadmrolesmanager;
	}


	public void setTadmrolesmanager(TAdmRolesManagerImpl tadmrolesmanager) {
		this.tadmrolesmanager = tadmrolesmanager;
	}


	public TAdmUsersManagerImpl getTadmusersmanager() {
		return tadmusersmanager;
	}


	public void setTadmusersmanager(TAdmUsersManagerImpl tadmusersmanager) {
		this.tadmusersmanager = tadmusersmanager;
	}


	public TAdmUsersModulesManager gettAdmUsersModulesManager() {
		return tAdmUsersModulesManager;
	}


	public void settAdmUsersModulesManager(TAdmUsersModulesManager tAdmUsersModulesManager) {
		this.tAdmUsersModulesManager = tAdmUsersModulesManager;
	}


	public TAdmUserRolesManagerImpl getTadmuserrolesmanager() {
		return tadmuserrolesmanager;
	}


	public void setTadmuserrolesmanager(
			TAdmUserRolesManagerImpl tadmuserrolesmanager) {
		this.tadmuserrolesmanager = tadmuserrolesmanager;
	}

	public TAdmModulesManagerImpl getTadmmodulesmanager() {
		return tadmmodulesmanager;
	}


	public void setTadmmodulesmanager(TAdmModulesManagerImpl tadmmodulesmanager) {
		this.tadmmodulesmanager = tadmmodulesmanager;
	}

	
	
	public TAdmOperationLogsManagerImpl getTadmoperationlogsmanager() {
		return tadmoperationlogsmanager;
	}


	public void setTadmoperationlogsmanager(
			TAdmOperationLogsManagerImpl tadmoperationlogsmanager) {
		this.tadmoperationlogsmanager = tadmoperationlogsmanager;
	}



	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}


	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}



	public class Condition{
		private String userid;
		private String username;
		private String loginName;
		private String password;
		private String department;
		private String mobile;
		private String email;
		private String[] moduleId;
		private String[] rolesid;//被选中的角色数组
		private String[] showroles;//当前页里显示出来的的角色数组	
		private List<TAdmModules> sysModules;
		private List<TAdmModules> userModules;
		private List<TAdmRoles> sysRoles;
		private List<TAdmRoles> userRoles;
		private String roledesc;
		private String rolename;
		private List<TAdmPost> sysPost;
		private List<TAdmPost> userPost;
		private String postname;
		private String postremark;
		private String[] postid;//被选中的角色数组
		private String[] showpost;//当前页里显示出来的的角色数组	
		private String webid;//应用id
		private String modulename;
		private String[] moduleall;
		
		
		
		public String[] getModuleall() {
			return moduleall;
		}
		public void setModuleall(String[] moduleall) {
			this.moduleall = moduleall;
		}
		public String getModulename() {
			return modulename;
		}
		public void setModulename(String modulename) {
			this.modulename = modulename;
		}
		public String getWebid() {
			return webid;
		}
		public void setWebid(String webid) {
			this.webid = webid;
		}
		public String[] getPostid() {
			return postid;
		}
		public void setPostid(String[] postid) {
			this.postid = postid;
		}
		public String[] getShowpost() {
			return showpost;
		}
		public void setShowpost(String[] showpost) {
			this.showpost = showpost;
		}
		public String getPostname() {
			return postname;
		}
		public void setPostname(String postname) {
			this.postname = postname;
		}
		public String getPostremark() {
			return postremark;
		}
		public void setPostremark(String postremark) {
			this.postremark = postremark;
		}
		public List<TAdmPost> getSysPost() {
			return sysPost;
		}
		public void setSysPost(List<TAdmPost> sysPost) {
			this.sysPost = sysPost;
		}
		public List<TAdmPost> getUserPost() {
			return userPost;
		}
		public void setUserPost(List<TAdmPost> userPost) {
			this.userPost = userPost;
		}
		public String getRolename() {
			return rolename;
		}
		public void setRolename(String rolename) {
			this.rolename = rolename;
		}
		public String[] getShowroles() {
			return showroles;
		}
		public void setShowroles(String[] showroles) {
			this.showroles = showroles;
		}
		public String getRoledesc() {
			return roledesc;
		}
		public void setRoledesc(String roledesc) {
			this.roledesc = roledesc;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public List<TAdmRoles> getSysRoles() {
			return sysRoles;
		}
		public void setSysRoles(List<TAdmRoles> sysRoles) {
			this.sysRoles = sysRoles;
		}
		public List<TAdmRoles> getUserRoles() {
			return userRoles;
		}
		public void setUserRoles(List<TAdmRoles> userRoles) {
			this.userRoles = userRoles;
		}
		public List<TAdmModules> getSysModules() {
			return sysModules;
		}
		public void setSysModules(List<TAdmModules> sysModules) {
			this.sysModules = sysModules;
		}
		public List<TAdmModules> getUserModules() {
			return userModules;
		}
		public void setUserModules(List<TAdmModules> userModules) {
			this.userModules = userModules;
		}
		public String[] getRolesid() {
			return rolesid;
		}
		public void setRolesid(String[] rolesid) {
			this.rolesid = rolesid;
		}
		public String[] getModuleId() {
			return moduleId;
		}
		public void setModuleId(String[] moduleId) {
			this.moduleId = moduleId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getLoginName() {
			return loginName;
		}
		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
	}
	
	public TAdmUserRolesManager gettAdmUserRolesManager() {
		return tAdmUserRolesManager;
	}


	public void settAdmUserRolesManager(TAdmUserRolesManager tAdmUserRolesManager) {
		this.tAdmUserRolesManager = tAdmUserRolesManager;
	}


	public TAdmOperationLogsManager gettAdmOperationLogsManager() {
		return tAdmOperationLogsManager;
	}


	public void settAdmOperationLogsManager(
			TAdmOperationLogsManager tAdmOperationLogsManager) {
		this.tAdmOperationLogsManager = tAdmOperationLogsManager;
	}


	public TAdmUsersManager gettAdmUsersManager() {
		return tAdmUsersManager;
	}


	public void settAdmUsersManager(TAdmUsersManager tAdmUsersManager) {
		this.tAdmUsersManager = tAdmUsersManager;
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


	public TAdmUsers getUserinfo() {
		return userinfo;
	}


	public void setUserinfo(TAdmUsers userinfo) {
		this.userinfo = userinfo;
	}


	public void setModel(Condition model) {
		this.model = model;
	}

	public TAdmUserRolesDAO getTadmuserrolesdao() {
		return tadmuserrolesdao;
	}


	public void setTadmuserrolesdao(TAdmUserRolesDAO tadmuserrolesdao) {
		this.tadmuserrolesdao = tadmuserrolesdao;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return this.model;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		this.model=(Condition)o;
	}


	public OpenSqlManager getOpenSqlManager() {
		return openSqlManager;
	}


	public void setOpenSqlManager(OpenSqlManager openSqlManager) {
		this.openSqlManager = openSqlManager;
	}


	public TAdmUserPostManager gettAdmUserPostManager() {
		return tAdmUserPostManager;
	}


	public void settAdmUserPostManager(TAdmUserPostManager tAdmUserPostManager) {
		this.tAdmUserPostManager = tAdmUserPostManager;
	}


	public TAdmPostManager gettAdmPostManager() {
		return tAdmPostManager;
	}


	public void settAdmPostManager(TAdmPostManager tAdmPostManager) {
		this.tAdmPostManager = tAdmPostManager;
	}


	public TAdmModulesManager gettAdmModulesManager() {
		return tAdmModulesManager;
	}


	public void settAdmModulesManager(TAdmModulesManager tAdmModulesManager) {
		this.tAdmModulesManager = tAdmModulesManager;
	}

}
