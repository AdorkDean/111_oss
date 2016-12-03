package com.rc.portal.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.oss.service.impl.OpenSqlManager;
import com.rc.oss.service.impl.TAdmModulesManager;
import com.rc.oss.service.impl.TAdmOperationLogsManager;
import com.rc.oss.vo.TAdmModules;
import com.rc.portal.service.impl.TAdmModulesManagerImpl;
import com.rc.portal.webapp.util.PageResult;

/**  
 * @Title: ShowModules.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2011-4-19 上午11:47:15
 * @version V1.0  
 */

public class ShowModules extends BaseAction{
	

	private TAdmModulesManagerImpl tadmmodulesmanager;
	private PageWraper pw=new PageWraper();
	private PageResult rs = new PageResult();
	private Condition model=new Condition();
	private TAdmModules module;
	private OpenSqlManager openSqlManager;
	private TAdmOperationLogsManager tAdmOperationLogsManager;
	private TAdmModulesManager tAdmModulesManager;
	
	public String listModules(){
//		TAdmModulesExample tme=new TAdmModulesExample(); 
//		tme.setOrderByClause(" id desc");
//		Criteria criteria = tme.createCriteria();
//		if(model.getModulename()!=null && model.getModulename().trim().length()>0){
//			criteria.andModuleNameLike("%"+model.getModulename().trim()+"%");
//		}
//		if(model.getUrl()!=null && model.getUrl().trim().length()>0){
//			criteria.andUrlLike("%"+model.getUrl().trim()+"%");
//		}
//		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
//			criteria.andWebidEqualTo(new Short(model.getWebid().trim()));
//		}
//
//		tme.getPageInfo().setPage(rs.getP_curPage());
//		tme.getPageInfo().setPageSize(rs.getP_pageSize());
//		try {
//			pw=tadmmodulesmanager.selectByRepositoryByPage(tme);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		tadmoperationlogsmanager.record(getRequest(), "模块管理-列表 ");
		PageInfo pageInfo =new PageInfo();
		pageInfo.setPage(rs.getP_curPage());
		pageInfo.setPageSize(rs.getP_pageSize());
		Map map = new HashMap();
		if (model != null) {
			if (model.getModulename() != null && model.getModulename().trim().length() > 0) {
				map.put("moduleName",model.getModulename());
			}
			if (model.getUrl() != null && model.getUrl().trim().length() > 0) {
				map.put("url", model.getUrl());
			}
			if (model.getWebid() != null && model.getWebid().trim().length() > 0) {
				map.put("webid", model.getWebid());
			}
		}
		try {
			pw = openSqlManager.getListForDynamicsPageHql("modules.list", "modules.count", map ,pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		tAdmOperationLogsManager.record(getRequest(), "模块管理-列表 ");
		
		return SUCCESS;
		
	}
	
	
	/**
	 * 保存模块
	 * @return
	 */
	public String saveModule(){		
		if(module!=null && module.getId()!=null){
//			TAdmModulesExample tm=new TAdmModulesExample();
//			tm.createCriteria().andIdEqualTo(module.getId());
			TAdmModules tmd=new TAdmModules();
			tmd = tAdmModulesManager.findById(module.getId());
			
			tmd.setModuleName(module.getModuleName());
			tmd.setMemo(module.getMemo());
			tmd.setUrl(module.getUrl());
			tmd.setParentid(module.getParentid());
			tmd.setModuleType(module.getModuleType());
			tmd.setWebid(module.getWebid());//平台id
			tmd.setDispnum(module.getDispnum());
			try {
//				tadmmodulesmanager.updateByRepositorySelective(t, tm);
				tAdmModulesManager.save(tmd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			tadmoperationlogsmanager.record(getRequest(), "模块管理-修改保存");
			tAdmOperationLogsManager.record(getRequest(), "模块管理-修改保存");
		}else{			
			try {
				module.setCreateTime(new Date());
				module.setForLog(1);
				module.setEditable(1);
//				tadmmodulesmanager.insert(module);
				tAdmModulesManager.save(module);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			tadmoperationlogsmanager.record(getRequest(), "模块管理-新增保存");
			tAdmOperationLogsManager.record(getRequest(), "模块管理-新增保存");
		}
		return "redirectToList";
	}
	
	
	public String editModule(){
//		TAdmModulesExample tm=new TAdmModulesExample();
//		tm.createCriteria().andIdEqualTo(module.getId());
		TAdmModules tdm = new TAdmModules();
		tdm = tAdmModulesManager.findById(module.getId());
		module.setId(null);
		try {
//			tadmmodulesmanager.updateByRepositorySelective(module, tm);
			tAdmModulesManager.save(tdm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//删除模块
   public String deleteModule(){
	   
	   if(module!=null && module.getId()!=null){
//			TAdmUsersModulesExample tum = new TAdmUsersModulesExample();
//			tum.createCriteria().andModuleidEqualTo(module.getId());
			try {
//				int i =	tadmusersmodulesmanager.countByRepository(tum);
				TAdmModules tms = new TAdmModules();
				tms = tAdmModulesManager.findById(module.getId());
				if(tms==null || tms.equals("")){
//					System.out.println("zhongjian==="+i);
					return "错误提示";
				}else{
//					int j =	tadmmodulesmanager.deleteByPrimaryKey(module.getId());
					TAdmModules tm = new TAdmModules();
					tm = tAdmModulesManager.findById(module.getId());
					tAdmModulesManager.delete(tm);
//					if(j>0){
//						
//						return "redirectToList";
//					}else{
//							System.out.println("aaaaa==="+j);
//						 return "错误提示"; 
//					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }
//	   System.out.println("======");
//	   tadmoperationlogsmanager.record(getRequest(), "模块管理-删除模块");
	   tAdmOperationLogsManager.record(getRequest(), "模块管理-删除模块");
	   return "redirectToList"; 
   }
	
	/**
	 * 增加或修改初始化进入
	 * @return
	 */
	public String toAddEditModule(){
		List<TAdmModules> list = new ArrayList<TAdmModules>();
		Map map=new HashMap();
		map.put("i", 1);
		list = openSqlManager.getListByDynamicsHql("modules.listparent", map);
		if(module!=null && module.getId()!=null && module.getId()>0){						
//			model.setParent(getParentModule(tadmmodulesmanager));
//			model.setParent(getParentModule(tAdmModulesManager));
			model.setParent(list);
			try {
//				module=tadmmodulesmanager.selectByPrimaryKey(module.getId());
				module= tAdmModulesManager.findById(module.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
//			model.setParent(getParentModule(tadmmodulesmanager));
//			model.setParent(getParentModule(tAdmModulesManager));
			model.setParent(list);
		}
		return "toaddeditmodule";
	}
	
	
	
	
	/**
	 * 取所有父模块
	 * @param manager
	 * @return
	 */
//	public List getParentModule(TAdmModulesManagerImpl manager){
//		List<TAdmModules> list=new ArrayList<TAdmModules>();
//		TAdmModulesExample te=new TAdmModulesExample();
//		te.createCriteria().andParentIdIsNull();
//		try {
//			list= manager.selectByRepository(te);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
//	public List getParentModule(TAdmModulesManager manager){
//		List<TAdmModules> list=new ArrayList<TAdmModules>();
//		TAdmModules tms = new TAdmModules();
//		try {
//			list = (List<TAdmModules>) manager.findById(tms.getId());
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	
	
	public TAdmModulesManagerImpl getTadmmodulesmanager() {
		return tadmmodulesmanager;
	}


	public void setTadmmodulesmanager(TAdmModulesManagerImpl tadmmodulesmanager) {
		this.tadmmodulesmanager = tadmmodulesmanager;
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


	public TAdmModules getModule() {
		return module;
	}


	public void setModule(TAdmModules module) {
		this.module = module;
	}


	public void setModel(Condition model) {
		this.model = model;
	}




	public OpenSqlManager getOpenSqlManager() {
		return openSqlManager;
	}


	public void setOpenSqlManager(OpenSqlManager openSqlManager) {
		this.openSqlManager = openSqlManager;
	}




	public class Condition{
		private String modulename;
		private String moduleid;
		private String url;
		private List<TAdmModules> parent;
		private String webid;		
		
		public String getWebid() {
			return webid;
		}
		public void setWebid(String webid) {
			this.webid = webid;
		}
		public List<TAdmModules> getParent() {
			return parent;
		}
		public void setParent(List<TAdmModules> parent) {
			this.parent = parent;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getModulename() {
			return modulename;
		}
		public void setModulename(String modulename) {
			this.modulename = modulename;
		}
		public String getModuleid() {
			return moduleid;
		}
		public void setModuleid(String moduleid) {
			this.moduleid = moduleid;
		}
		
	}
	
	
	public TAdmModulesManager gettAdmModulesManager() {
		return tAdmModulesManager;
	}


	public void settAdmModulesManager(TAdmModulesManager tAdmModulesManager) {
		this.tAdmModulesManager = tAdmModulesManager;
	}


	public TAdmOperationLogsManager gettAdmOperationLogsManager() {
		return tAdmOperationLogsManager;
	}


	public void settAdmOperationLogsManager(
			TAdmOperationLogsManager tAdmOperationLogsManager) {
		this.tAdmOperationLogsManager = tAdmOperationLogsManager;
	}


	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		this.model=(Condition)o;
	}
}
