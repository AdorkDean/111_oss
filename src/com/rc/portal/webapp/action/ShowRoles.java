package com.rc.portal.webapp.action;

import java.math.BigDecimal;
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
import com.rc.oss.service.impl.TAdmRescManager;
import com.rc.oss.service.impl.TAdmRescRolesManager;
import com.rc.oss.service.impl.TAdmRolesManager;
import com.rc.oss.vo.TAdmResc;
import com.rc.oss.vo.TAdmRescRoles;
import com.rc.oss.vo.TAdmRoles;
import com.rc.portal.dao.TAdmUserRolesDAO;
import com.rc.portal.service.impl.TAdmOperationLogsManagerImpl;
import com.rc.portal.service.impl.TAdmRescManagerImpl;
import com.rc.portal.service.impl.TAdmRescRolesManagerImpl;
import com.rc.portal.service.impl.TAdmRolesManagerImpl;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.util.WebConfigFactory;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;

/**  
 * @Title: ShowRoles.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2011-4-19 下午01:17:40
 * @version V1.0  
 */

public class ShowRoles extends BaseAction {
	
	private TAdmUserRolesDAO tadmuserrolesdao;
	private TAdmRescRolesManagerImpl tadmrescrolesmanager;
	private PageWraper pw=new PageWraper();
	private PageResult rs = new PageResult();
	private Condition model=new Condition();
	private TAdmRoles role;
	private TAdmRolesManagerImpl tadmrolesmanager;
	private TAdmRescManagerImpl tadmrescmanager;
	private TAdmOperationLogsManagerImpl tadmoperationlogsmanager;
	private TAdmOperationLogsManager tAdmOperationLogsManager;
	private OpenSqlManager openSqlManager;
	private TAdmRolesManager tAdmRolesManager; 
	private TAdmRescRolesManager tAdmRescRolesManager;
	private TAdmRescManager tAdmRescManager;
	/*public String listRoles(){
		TAdmRolesExample tre=new TAdmRolesExample();
		Criteria criteria = tre.createCriteria();
		tre.setOrderByClause(" id desc");
		if(model.getName()!=null && model.getName().trim().length()>0){
			criteria.andRoleNameLike("%"+model.getName().trim()+"%");
		}
		if(model.getDesc()!=null && model.getDesc().trim().length()>0){
			criteria.andRoleDescLike("%"+model.getDesc().trim()+"%");
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			criteria.andWebidEqualTo(new Short(model.getWebid().trim()));
		}
		tre.getPageInfo().setPage(rs.getP_curPage());
		tre.getPageInfo().setPageSize(rs.getP_pageSize());
		try {
			pw=tadmrolesmanager.selectByRepositoryByPage(tre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tadmoperationlogsmanager.record(getRequest(), "权限管理-列表");
		return SUCCESS;
	}*/
	public String listRoles(){
		PageInfo pageInfo=new PageInfo();
		pageInfo.setPage(rs.getP_curPage());
		pageInfo.setPageSize(rs.getP_pageSize());
		Map map=new HashMap();
		if(model.getName()!=null && model.getName().trim().length()>0){
			map.put("name", model.getName());
		}
		if(model.getDesc()!=null && model.getDesc().trim().length()>0){
			map.put("desc", model.getDesc());
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			map.put("webid", model.getWebid());
		}
		try {
			pw= openSqlManager.getListForDynamicsPageHql("roles.list", "roles.count", map, pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		tadmoperationlogsmanager.record(getRequest(), "权限管理-列表");
		tAdmOperationLogsManager.record(getRequest(), "权限管理-列表");
		
		return SUCCESS;
	}
	
	
	/**
	 * 保存
	 * @return
	 */
	public String saveRole(){
		if(role!=null && role.getId()!=null){
			TAdmRoles tr = new TAdmRoles();
			tr = tAdmRolesManager.findById(role.getId());
//			if (role.getId()!=null && role.getId()>0) {
//				tr.setId(role.getId());
//			}
			tr.setRoleName(role.getRoleName());
			tr.setRoleDesc(role.getRoleDesc());
			tr.setStatus(role.getStatus());
			tr.setWebid(role.getWebid());
			tr.setLastUpdateTime(new Date());
			
//			TAdmRolesExample t=new TAdmRolesExample();
//			t.createCriteria().andIdEqualTo(role.getId());
			try {
//				tadmrolesmanager.updateByRepositorySelective(tr, t);
				tAdmRolesManager.save(tr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			tadmoperationlogsmanager.record(getRequest(), "权限管理-修改保存");
			tAdmOperationLogsManager.record(getRequest(), "权限管理-修改保存");
		}else{
//			UserinfoModel userinfo = HttpSessionUtil.getLoginUserModel();
			UserinfoModel userinfo =(UserinfoModel) getRequest().getSession().getAttribute(ConstantUtil.logincookiename);
			role.setLastUpdateTime(new Date());		
			role.setCreateTime(new Date());
			role.setStatus(1);
			role.setCreateBy(new BigDecimal(userinfo.getUserid()));
			role.setEditable(1);
			role.setLastUpdateBy(new Long(userinfo.getUserid()));
			try {
//				tadmrolesmanager.insert(role);
				tAdmRolesManager.save(role);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			tadmoperationlogsmanager.record(getRequest(), "权限管理-新增保存");
			tAdmOperationLogsManager.record(getRequest(), "权限管理-新增保存");
		}
		return "redirectToList";
	}
	
	
	//删除
	public String deleteRoles(){
		
		if(role!=null && role.getId()!=null){
			
//			TAdmUserRolesExample tur = new  TAdmUserRolesExample();
//			tur.createCriteria().andRoleIdEqualTo(role.getId());
			try {
				TAdmRoles trs = new TAdmRoles();
//				int i = tadmuserrolesdao.countByRepository(tur);
				trs = tAdmRolesManager.findById(role.getId());
				if(trs==null || trs.equals("")){
//					System.out.println("i===="+i);
					return "错误提示";
				}else{
//					TAdmUserRolesExample tur = new  TAdmUserRolesExample();
//					tre.createCriteria().andRoleIdEqualTo(role.getId());
//					int j = tadmrescrolesmanager.countByRepository(tre);
//					TAdmRoles tar = new TAdmRoles();
//					tar = tAdmRolesManager.findById(role.getId());
//					if(tar==null || tar.equals("")){
//						System.out.println("j===="+j);
//						return "错误提示";
//					}else{
//					 	int c = tadmrolesmanager.deleteByPrimaryKey(role.getId());
						TAdmRoles tr = new TAdmRoles();
						tr = tAdmRolesManager.findById(role.getId());
						tAdmRolesManager.delete(tr);
//						if(tr==null || tr.equals("")){
//							return "redirectToList";
//						}else{
//							System.out.println("c===="+c);
//							return "错误提示";
//						}
//					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		tadmoperationlogsmanager.record(getRequest(), "权限管理-删除权限");
		tAdmOperationLogsManager.record(getRequest(), "权限管理-删除权限");
		return "redirectToList";
	}
	
	
	/**
	 * 修改增加初始化
	 * @return
	 */
	public String toEditAddRole(){
		if(role!=null && role.getId()!=null && role.getId()>0){
			try {
//				role=tadmrolesmanager.selectByPrimaryKey(role.getId());
				role = tAdmRolesManager.findById(role.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "toaddeditmodule";		
	}
	
	/**
	 * 修改权限拥有的资源
	 * @return
	 */
	public String editRoleResc() {
		try {
//			tadmrescrolesmanager.editRoleResc(Long.parseLong(model.getId()), model.getRescid(),model.getResciddels());
//			Map map=new HashMap();
//			map.put("id", Long.parseLong(model.getId()));
//			List<TAdmRescRoles> persistentInstances = openSqlManager.getListByDynamicsHql("roles.select_rr_byroleid", map);
//			tAdmRescRolesManager.deleteAll(persistentInstances);
			if(model.getResciddels()!=null){
				for(String rescid:model.getResciddels()){
					openSqlManager.ByDynamicsDeleteHql("delete from TAdmRescRoles p where p.TAdmRoles.id="+Long.parseLong(model.getId())+" and p.TAdmResc.id="+Long.parseLong(rescid));
				}
			}
			if(model.getRescid()!=null){
				for(String rescid:model.getRescid()){
					TAdmRescRoles tr=new TAdmRescRoles();
					tr.setTAdmResc(tAdmRescManager.findById(new Long(rescid)));
					tr.setTAdmRoles(tAdmRolesManager.findById(Long.parseLong(model.getId())));
					tAdmRescRolesManager.save(tr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//刷新内存
		WebConfigFactory.getFlashAuthorization().flash(this.getRequest().getSession().getServletContext());
//		tadmoperationlogsmanager.record(getRequest(), "权限管理-修改资源");
		tAdmOperationLogsManager.record(getRequest(), "权限管理-修改资源");
		return "redirectToList";
	}
	
	
	public String toEditResc(){
		TAdmRoles role = tAdmRolesManager.findById(Long.parseLong(model.getId()));
		Set tAdmRescRoleses = role.getTAdmRescRoleses();
		List<TAdmRescRoles> listL=new ArrayList<TAdmRescRoles>(tAdmRescRoleses);
		List<TAdmResc> list=new ArrayList<TAdmResc>();
		try {
//			List<TAdmRescRoles> listL = tAdmRescRolesManager.findByProperty("TAdmRoles.id", );
//			List<TAdmRescRoles> listL=tadmrescrolesmanager.selectByRepository(te);
			for(TAdmRescRoles t:listL){
				list.add(t.getTAdmResc());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			model.setUserResc(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*TAdmRescExample tre2=new TAdmRescExample();
		com.rc.portal.vo.TAdmRescExample.Criteria Criteria = tre2.createCriteria();
		if(model.getRescname()!=null && model.getRescname().trim().length()>0){
			Criteria.andNameLike("%"+model.getRescname().trim()+"%");
		}
		if(model.getRescurl()!=null && model.getRescurl().trim().length()>0){
			Criteria.andResStringLike("%"+model.getRescurl()+"%");
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			Criteria.andWebidEqualTo(new Short(model.getWebid().trim()));
		}
		tre2.getPageInfo().setPage(rs.getP_curPage());
		tre2.getPageInfo().setPageSize(rs.getP_pageSize());
		tre2.setOrderByClause(" id desc ");*/
		PageInfo pageInfo=new PageInfo();
		pageInfo.setPage(rs.getP_curPage());
		pageInfo.setPageSize(rs.getP_pageSize());
		Map map=new HashMap();
		if(model.getRescname()!=null && model.getRescname().trim().length()>0){
			map.put("name", model.getRescname());
		}
		if(model.getRescurl()!=null && model.getRescurl().trim().length()>0){
			map.put("url", model.getRescurl());
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			map.put("webid", model.getWebid());
		}
		try {
			pw = openSqlManager.getListForDynamicsPageHql("resc.list", "resc.count", map, pageInfo);
			model.setSysRescPw(pw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toeditresc";
	}
	
	public TAdmRescRolesManagerImpl getTadmrescrolesmanager() {
		return tadmrescrolesmanager;
	}


	public void setTadmrescrolesmanager(
			TAdmRescRolesManagerImpl tadmrescrolesmanager) {
		this.tadmrescrolesmanager = tadmrescrolesmanager;
	}


	public TAdmOperationLogsManagerImpl getTadmoperationlogsmanager() {
		return tadmoperationlogsmanager;
	}


	public void setTadmoperationlogsmanager(
			TAdmOperationLogsManagerImpl tadmoperationlogsmanager) {
		this.tadmoperationlogsmanager = tadmoperationlogsmanager;
	}


	public TAdmRoles getRole() {
		return role;
	}


	public void setRole(TAdmRoles role) {
		this.role = role;
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

	public TAdmRolesManagerImpl getTadmrolesmanager() {
		return tadmrolesmanager;
	}

	public void setTadmrolesmanager(TAdmRolesManagerImpl tadmrolesmanager) {
		this.tadmrolesmanager = tadmrolesmanager;
	}

	public void setModel(Condition model) {
		this.model = model;
	}

	public TAdmRescManagerImpl getTadmrescmanager() {
		return tadmrescmanager;
	}


	public void setTadmrescmanager(TAdmRescManagerImpl tadmrescmanager) {
		this.tadmrescmanager = tadmrescmanager;
	}






	public class Condition{
		private String name;
		private String id;
		private String desc;
		private String[] rescid;
		private String[] resciddels; 
		private List<TAdmResc> sysResc;
		private List<TAdmResc> userResc;
		private PageWraper sysRescPw;//系统资源
		private String rescname;
		private String rescurl;
		private String webid;
		
	
		public String getWebid() {
			return webid;
		}
		public Integer getStatus() {
			// TODO Auto-generated method stub
			return null;
		}
		public void setWebid(String webid) {
			this.webid = webid;
		}
		public String[] getResciddels() {
			return resciddels;
		}
		public void setResciddels(String[] resciddels) {
			this.resciddels = resciddels;
		}
		public String getRescname() {
			return rescname;
		}
		public void setRescname(String rescname) {
			this.rescname = rescname;
		}

		public String getRescurl() {
			return rescurl;
		}
		public void setRescurl(String rescurl) {
			this.rescurl = rescurl;
		}
		public PageWraper getSysRescPw() {
			return sysRescPw;
		}
		public void setSysRescPw(PageWraper sysRescPw) {
			this.sysRescPw = sysRescPw;
		}
		public List<TAdmResc> getSysResc() {
			return sysResc;
		}
		public void setSysResc(List<TAdmResc> sysResc) {
			this.sysResc = sysResc;
		}
		public List<TAdmResc> getUserResc() {
			return userResc;
		}
		public void setUserResc(List<TAdmResc> userResc) {
			this.userResc = userResc;
		}
		public String[] getRescid() {
			return rescid;
		}
		public void setRescid(String[] rescid) {
			this.rescid = rescid;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	}
	
	public TAdmRolesManager gettAdmRolesManager() {
		return tAdmRolesManager;
	}


	public void settAdmRolesManager(TAdmRolesManager tAdmRolesManager) {
		this.tAdmRolesManager = tAdmRolesManager;
	}


	public TAdmOperationLogsManager gettAdmOperationLogsManager() {
		return tAdmOperationLogsManager;
	}


	public void settAdmOperationLogsManager(
			TAdmOperationLogsManager tAdmOperationLogsManager) {
		this.tAdmOperationLogsManager = tAdmOperationLogsManager;
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


	public TAdmRescRolesManager gettAdmRescRolesManager() {
		return tAdmRescRolesManager;
	}


	public void settAdmRescRolesManager(TAdmRescRolesManager tAdmRescRolesManager) {
		this.tAdmRescRolesManager = tAdmRescRolesManager;
	}


	public TAdmRescManager gettAdmRescManager() {
		return tAdmRescManager;
	}


	public void settAdmRescManager(TAdmRescManager tAdmRescManager) {
		this.tAdmRescManager = tAdmRescManager;
	}

}
