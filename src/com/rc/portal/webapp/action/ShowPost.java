package com.rc.portal.webapp.action;

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
import com.rc.oss.service.impl.TAdmOperationLogsManager;
import com.rc.oss.service.impl.TAdmPostManager;
import com.rc.oss.service.impl.TAdmPostRoleManager;
import com.rc.oss.service.impl.TAdmRolesManager;
import com.rc.oss.service.impl.TAdmUsersManager;
import com.rc.oss.vo.TAdmPost;
import com.rc.oss.vo.TAdmPostRole;
import com.rc.oss.vo.TAdmRoles;
import com.rc.oss.vo.TAdmUsers;
import com.rc.portal.service.TAdmUserPostManager;
import com.rc.portal.service.impl.TAdmOperationLogsManagerImpl;
import com.rc.portal.service.impl.TAdmPostManagerImpl;
import com.rc.portal.service.impl.TAdmRolesManagerImpl;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.util.WebConfigFactory;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;
 
/**  
 * @Title: ShowPost.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2011-4-19 下午01:17:40
 * @version V1.0  
 */

public class ShowPost extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -46406744438924633L;
	private PageWraper pw=new PageWraper();
	private PageResult rs = new PageResult();
	private Condition model=new Condition();
	private TAdmOperationLogsManagerImpl tadmoperationlogsmanager;
	private TAdmPostManagerImpl tadmpostmanager;
	private TAdmUserPostManager tadmuserpostmanager;
	private TAdmRolesManagerImpl tadmrolesmanager;
	private OpenSqlManager openSqlManager;
	private TAdmPost post;
	private TAdmPostManager tAdmPostManager;
	private TAdmOperationLogsManager tAdmOperationLogsManager;
	private TAdmUsers user;
	private TAdmUsersManager tAdmUsersManager;
	private TAdmPostRoleManager tAdmPostRoleManager;
	private TAdmRolesManager tAdmRolesManager; 
	/*public String listPost(){
		TAdmPostExample tpe=new TAdmPostExample();
		 Criteria criteria = tpe.createCriteria();
		tpe.setOrderByClause(" id desc");
		if(model.getName()!=null && model.getName().trim().length()>0){
			criteria.andNameLike("%"+model.getName().trim()+"%");
		}
		if(model.getRemark()!=null && model.getRemark().trim().length()>0){
			criteria.andRemarkLike("%"+model.getRemark().trim()+"%");
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			criteria.andWebidEqualTo(new Short(model.getWebid().trim()));
		}
		tpe.getPageInfo().setPage(rs.getP_curPage());
		tpe.getPageInfo().setPageSize(rs.getP_pageSize());
		
		try {
			pw=tadmpostmanager.selectByRepositoryByPage(tpe);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tadmoperationlogsmanager.record(getRequest(), "岗位管理-列表");
		return SUCCESS;
	}*/
	public String listPost(){
		PageInfo pageInfo=new PageInfo();
		pageInfo.setPage(rs.getP_curPage());
		pageInfo.setPageSize(rs.getP_pageSize());
		Map map=new HashMap();
		if(model.getName()!=null && model.getName().trim().length()>0){
		map.put("name", model.getName());
		}
		if(model.getRemark()!=null && model.getRemark().trim().length()>0){
		map.put("remark", model.getRemark());
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			map.put("webid", model.getWebid());
		}
		try {
			pw=openSqlManager.getListForDynamicsPageHql("post.list", "post.count", map, pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		tadmoperationlogsmanager.record(getRequest(), "岗位管理-列表");
		tAdmOperationLogsManager.record(getRequest(), "岗位管理-列表");
		return SUCCESS;
	}
	/**
	 * 修改增加初始化
	 * @return
	 */
	public String toEditAddPost(){
		if(post!=null && post.getId()!=null && post.getId()>0){
			try {
//				post=tadmpostmanager.selectByPrimaryKey(post.getId());
				post = tAdmPostManager.findById(post.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "toeditadd";		
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String savePost(){
		if(post!=null && post.getId()!=null){
			TAdmPost tr=new TAdmPost();
			tr = tAdmPostManager.findById(post.getId());
			
			tr.setName(post.getName());
			tr.setRemark(post.getRemark());
			tr.setEnable(post.getEnable());
			tr.setWebid(post.getWebid());
//			TAdmPostExample t=new TAdmPostExample();
//			t.createCriteria().andIdEqualTo(post.getId());
			try {
//				tadmpostmanager.updateByRepositorySelective(tr, t);
				tAdmPostManager.save(tr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			tadmoperationlogsmanager.record(getRequest(), "岗位管理-修改保存");
			tAdmOperationLogsManager.record(getRequest(), "岗位管理-修改保存");
		}else{
			UserinfoModel userinfo =(UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
			post.setCreateDt(new Date());		
			try {
//				tadmpostmanager.insert(post);
				tAdmPostManager.save(post);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			tadmoperationlogsmanager.record(getRequest(), "岗位管理-新增保存");
			tAdmOperationLogsManager.record(getRequest(), "岗位管理-新增保存");
		}
		return "skip";
	}
	
	
	//删除
	public String deletePost(){
		
		if(post!=null && post.getId()!=null){
			
//			TAdmUserPostExample tur = new  TAdmUserPostExample();
//			TAdmPostRoleExample tpr = new  TAdmPostRoleExample();
//			tur.createCriteria().andPostidEqualTo(post.getId());
//			tpr.createCriteria().andPostidEqualTo(post.getId());
			try {
				TAdmPost tpt = new TAdmPost();
				tpt = tAdmPostManager.findById(post.getId());
//				int i = tadmuserpostmanager.countByRepository(tur);
//				int m=tadmpostrolemanager.countByRepository(tpr);
				if(tpt==null || tpt.equals("")){
//					System.out.println("i===="+i);
					return "错误提示";
				}else{
//					TAdmPostExample tre = new TAdmPostExample();
//					tre.createCriteria().andIdEqualTo(post.getId());
//					int j = tadmpostmanager.countByRepository(tre);
//					TAdmPost tps = new TAdmPost();
//					tps = tAdmPostManager.findById(post.getId());
//					if(tps==null || tps.equals("")){
//						System.out.println("j===="+j);
//						return "错误提示";
//					}else{
//						 int c = tadmpostmanager.deleteByPrimaryKey(post.getId());
						TAdmPost tp = new TAdmPost();
						tp = tAdmPostManager.findById(post.getId());
						tAdmPostManager.delete(tp);
//						 if(c>0){
//							 return "skip";
//						 }else{
//							 System.out.println("c===="+c);
//							 return "错误提示";
//						 }
//					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		tadmoperationlogsmanager.record(getRequest(), "岗位管理-删除权限");
		tAdmOperationLogsManager.record(getRequest(), "岗位管理-删除权限");
		return "skip";
	}
	
	
	/**
	 * 修改岗位_权限初始化
	 * @return
	 */
	public String toEditRole(){
//		TAdmPostRoleExample tre=new TAdmPostRoleExample();
		//tre.setOrderByClause("id desc");
//		tre.createCriteria().andPostidEqualTo(model.getId());
			List<TAdmRoles> listL=new ArrayList<TAdmRoles>();//岗位拥有的角色
			TAdmPost post = tAdmPostManager.findById(model.getId());
			Set set = post.getTAdmPostRoles();
			List<TAdmPostRole> list=new ArrayList<TAdmPostRole>(set);
//			List<TAdmPostRole> list=tadmpostrolemanager.selectByRepository(tre);
			for(TAdmPostRole t:list){
				listL.add(t.getTAdmRoles());
			}
//		TAdmRolesExample tar=new TAdmRolesExample();
//		//tar.setOrderByClause("id desc");
//		if(listL==null || listL.size()==0){
//			listL.add(-1L);
//			tar.createCriteria().andIdIn(listL);
//		}else{
//			tar.createCriteria().andIdIn(listL);
//		}
		
		try {
			model.setPostRole(listL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		TAdmRolesExample tarsys=new TAdmRolesExample();
		com.rc.portal.vo.TAdmRolesExample.Criteria rolec = tarsys.createCriteria();
		if(model.getRolename()!=null && model.getRolename().trim().length()>0){
			rolec.andRoleNameLike("%"+model.getRolename().trim()+"%");
		}
		if(model.getRoledesc()!=null && model.getRoledesc().trim().length()>0){
			rolec.andRoleDescLike("%"+model.getRoledesc().trim()+"%");
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			rolec.andWebidEqualTo(new Short(model.getWebid().trim()));
		}
		tarsys.getPageInfo().setPage(rs.getP_curPage());
		tarsys.getPageInfo().setPageSize(rs.getP_pageSize());
		
		tarsys.setOrderByClause("ROLE_DESC desc");
		
		try {
			pw=tadmrolesmanager.selectByRepositoryByPage(tarsys);*/
		PageInfo pageInfo=new PageInfo();
		pageInfo.setPage(rs.getP_curPage());
		pageInfo.setPageSize(rs.getP_pageSize());
		Map map=new HashMap();
		if(model.getRolename()!=null && model.getRolename().trim().length()>0){
			map.put("name", model.getRolename());
		}
		if(model.getRoledesc()!=null &&model.getRoledesc().trim().length()>0){
			map.put("desc", model.getRoledesc());
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			map.put("webid", model.getWebid());
		}
		try {
			pw = openSqlManager.getListForDynamicsPageHql("roles.list", "roles.count", map, pageInfo);
			model.setSysRoles(pw.getResult());
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		return "toeditPostroles";
	}
	
	
	/**
	 * 修改权限
	 * @return
	 */
	public String editPostRoles(){		
		try {
//			tadmpostrolemanager.editPostRoles(model.getId(), model.getRolesid(),model.getShowroles());
			Map map=new HashMap();
			map.put("id", model.getId());
			List<TAdmPostRole> persistentInstances = openSqlManager.getListByDynamicsHql("post.select_pr_bypostid", map);
//			tAdmPostRoleManager.deleteAll(persistentInstances);
			if(model.getShowroles()!=null){
				for(String roleid:model.getShowroles()){
					openSqlManager.ByDynamicsDeleteHql("delete from TAdmPostRole p where  p.TAdmPost.id="+model.getId()+" and p.TAdmRoles.id="+Long.parseLong(roleid));
				}
			}
			if(model.getRolesid()!=null){
				for(String roleid:model.getRolesid()){
					TAdmPostRole tr=new TAdmPostRole();
					tr.setTAdmPost(tAdmPostManager.findById(model.getId()));
					tr.setTAdmRoles(tAdmRolesManager.findById(new Long(roleid)));
					tAdmPostRoleManager.save(tr);
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

	public TAdmRolesManagerImpl getTadmrolesmanager() {
		return tadmrolesmanager;
	}
	public void setTadmrolesmanager(TAdmRolesManagerImpl tadmrolesmanager) {
		this.tadmrolesmanager = tadmrolesmanager;
	}
	public TAdmUserPostManager getTadmuserpostmanager() {
		return tadmuserpostmanager;
	}
	public void setTadmuserpostmanager(TAdmUserPostManager tadmuserpostmanager) {
		this.tadmuserpostmanager = tadmuserpostmanager;
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

	public TAdmOperationLogsManagerImpl getTadmoperationlogsmanager() {
		return tadmoperationlogsmanager;
	}

	public void setTadmoperationlogsmanager(TAdmOperationLogsManagerImpl tadmoperationlogsmanager) {
		this.tadmoperationlogsmanager = tadmoperationlogsmanager;
	}

	public TAdmPostManagerImpl getTadmpostmanager() {
		return tadmpostmanager;
	}

	public void setTadmpostmanager(TAdmPostManagerImpl tadmpostmanager) {
		this.tadmpostmanager = tadmpostmanager;
	}

	public TAdmPost getPost() {
		return post;
	}
	public void setPost(TAdmPost post) {
		this.post = post;
	}
	public String formatString(String obj){
		if(obj==null){
			return "";
		}
		return obj;
	}
	public class Condition{
		private Long id;
		private String name;
		private String remark;
		private List<TAdmRoles> postRole;
		private List<TAdmRoles> sysRoles;
		private String roledesc;
		private String rolename;
		private String[] rolesid;//被选中的角色数组
		private String[] showroles;//当前页里显示出来的的角色数组	
		private String webid;
		
		
		public String getWebid() {
			return webid;
		}
		public void setWebid(String webid) {
			this.webid = webid;
		}
		
		
		public String[] getRolesid() {
			return rolesid;
		}
		public void setRolesid(String[] rolesid) {
			this.rolesid = rolesid;
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
		public String getRolename() {
			return rolename;
		}
		public void setRolename(String rolename) {
			this.rolename = rolename;
		}
		public List<TAdmRoles> getPostRole() {
			return postRole;
		}
		public void setPostRole(List<TAdmRoles> postRole) {
			this.postRole = postRole;
		}
		public List<TAdmRoles> getSysRoles() {
			return sysRoles;
		}
		public void setSysRoles(List<TAdmRoles> sysRoles) {
			this.sysRoles = sysRoles;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
	}
	
	public TAdmUsers getUser() {
		return user;
	}
	public void setUser(TAdmUsers user) {
		this.user = user;
	}
	public TAdmUsersManager gettAdmUsersManager() {
		return tAdmUsersManager;
	}
	public void settAdmUsersManager(TAdmUsersManager tAdmUsersManager) {
		this.tAdmUsersManager = tAdmUsersManager;
	}
	public TAdmOperationLogsManager gettAdmOperationLogsManager() {
		return tAdmOperationLogsManager;
	}
	public void settAdmOperationLogsManager(
			TAdmOperationLogsManager tAdmOperationLogsManager) {
		this.tAdmOperationLogsManager = tAdmOperationLogsManager;
	}
	public TAdmPostManager gettAdmPostManager() {
		return tAdmPostManager;
	}
	public void settAdmPostManager(TAdmPostManager tAdmPostManager) {
		this.tAdmPostManager = tAdmPostManager;
	}
	public OpenSqlManager getOpenSqlManager() {
		return openSqlManager;
	}
	public void setOpenSqlManager(OpenSqlManager openSqlManager) {
		this.openSqlManager = openSqlManager;
	}
	public TAdmPostRoleManager gettAdmPostRoleManager() {
		return tAdmPostRoleManager;
	}
	public void settAdmPostRoleManager(TAdmPostRoleManager tAdmPostRoleManager) {
		this.tAdmPostRoleManager = tAdmPostRoleManager;
	}
	public TAdmRolesManager gettAdmRolesManager() {
		return tAdmRolesManager;
	}
	public void settAdmRolesManager(TAdmRolesManager tAdmRolesManager) {
		this.tAdmRolesManager = tAdmRolesManager;
	}
	
}
