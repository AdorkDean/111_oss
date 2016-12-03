package com.rc.portal.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.oss.service.TAdmRescManagerItf;
import com.rc.oss.service.impl.OpenSqlManager;
import com.rc.oss.service.impl.TAdmOperationLogsManager;
import com.rc.oss.vo.TAdmResc;
import com.rc.portal.service.impl.TAdmOperationLogsManagerImpl;
import com.rc.portal.service.impl.TAdmRescManagerImpl;
import com.rc.portal.service.impl.TAdmRescRolesManagerImpl;
import com.rc.portal.vo.TAdmRescExample;
import com.rc.portal.vo.TAdmRescRoles;
import com.rc.portal.vo.TAdmRescRolesExample;
import com.rc.portal.webapp.util.PageResult;

/**  
 * @Title: ShowResc.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2011-4-19 下午03:16:06
 * @version V1.0  
 */

public class ShowResc extends BaseAction {
	private TAdmRescManagerImpl tadmrescmanager;
	private TAdmRescRolesManagerImpl tadmrescrolesmanager;
	private TAdmRescManagerItf tAdmRescManager;
	private TAdmOperationLogsManagerImpl tadmoperationlogsmanager;
	private TAdmOperationLogsManager tAdmOperationLogsManager;//hibernate
	private PageWraper pw=new PageWraper();
	private PageResult rs=new PageResult();
	private Condition model=new Condition();
	private TAdmResc resc=new TAdmResc();
	private String priority;
	private Long id ;
	private OpenSqlManager openSqlManager;
	/*public String listResc(){
		TAdmRescExample tae=new TAdmRescExample();
		tae.getPageInfo().setPage(rs.getP_curPage());
		tae.getPageInfo().setPageSize(rs.getP_pageSize());
		tae.setOrderByClause(" id desc ");
		Criteria criteria = tae.createCriteria();
		if(model.getName()!=null && model.getName().trim().length()>0){
//			criteria.andNameEqualTo(model.getName().trim());
			criteria.andNameLike("%"+model.getName().trim()+"%");
		}
		if(model.getUrl()!=null && model.getUrl().trim().length()>0){
//			criteria.andResStringEqualTo(model.getUrl().trim());
			criteria.andResStringLike("%"+model.getUrl().trim()+"%");
		}
		if(model.getId()!=null && model.getId().trim().length()>0){
			criteria.andIdEqualTo(Long.parseLong(model.getId()));			
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			criteria.andWebidEqualTo(new Short(model.getWebid().trim()));
		}
		try {
			pw=tadmrescmanager.selectByRepositoryByPage(tae);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tadmoperationlogsmanager.record(getRequest(), "资源管理-列表");
		return SUCCESS;
	}*/
	public String listResc(){
		Map map=new HashMap();
		PageInfo pageInfo=new PageInfo();
		pageInfo.setPage(rs.getP_curPage());
		pageInfo.setPageSize(rs.getP_pageSize());
		if(model.getName()!=null && model.getName().trim().length()>0){
			map.put("name", model.getName());
		}
		if(model.getUrl()!=null && model.getUrl().trim().length()>0){
			map.put("url", model.getUrl());
		}
		if(model.getId()!=null && model.getId().trim().length()>0){
			map.put("id", model.getId());
		}
		if(model.getWebid()!=null && model.getWebid().trim().length()>0){
			map.put("webid", model.getWebid());
		}
		try {
			pw = openSqlManager.getListForDynamicsPageHql("resc.list", "resc.count", map, pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tAdmOperationLogsManager.record(getRequest(), "资源管理-列表");
		return SUCCESS;
	}
	
	public String addResc(){
		try {
			resc.setEditable(1);			
			tAdmRescManager.save(resc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tAdmOperationLogsManager.record(getRequest(), "资源管理-新增保存");
		return  "skip";
	}
	
	public String toAddEditResc(){

		if(resc!=null && resc.getId()!=null && resc.getId()>0){
			try {
				resc=tAdmRescManager.findById(resc.getId());
//				priority = resc.getPriority().toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return "toEditResc";
	}
	
	//删除
	public String deleteResc(){
		if(resc!=null && resc.getId()!=null && resc.getId()>0){
			   resc=tAdmRescManager.findById(resc.getId());
			   if(resc!=null){
				   tAdmRescManager.delete(resc);
			   }else{
				   return "skip";
			   }
		}
		tAdmOperationLogsManager.record(getRequest(), "资源管理-删除资源 ");
		return "skip";
	}
	
	
	public String updateResc(){		
			tAdmRescManager.save(resc);
			tAdmOperationLogsManager.record(getRequest(), "资源管理-修改保存 ");
		return  "skip";
	}
	
	public String updateSkip(){
		
		if(resc!=null && resc.getId()!=null && resc.getId()>0){
			try {
				resc=tAdmRescManager.findById(resc.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "updateResc" ;
	}
	
	
	public String editResc(){
		TAdmRescExample tae=new TAdmRescExample();
		tae.createCriteria().andIdEqualTo(resc.getId());
		resc.setId(null);
		try {
//			tadmrescmanager.updateByRepositorySelective(resc, tae);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tAdmOperationLogsManager.record(getRequest(), "资源管理-修改保存 ");
		return SUCCESS;
	}
	
	public String toEditResc(){
		TAdmRescRolesExample tre=new TAdmRescRolesExample();
		tre.createCriteria().andRoleIdEqualTo(Long.parseLong(model.getId()));
		List<Long> listL=new ArrayList<Long>();
		try {
			List<TAdmRescRoles> list=tadmrescrolesmanager.selectByRepository(tre);
			for(TAdmRescRoles t:list){
				listL.add(t.getRescId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TAdmRescExample te=new TAdmRescExample();
		te.createCriteria().andIdIn(listL);
		try {
			model.setUserResc(tadmrescmanager.selectByRepository(te));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TAdmRescExample tea=new TAdmRescExample();
		
		try {
			model.setSysResc(tadmrescmanager.selectByRepository(tea));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public TAdmRescManagerImpl getTadmrescmanager() {
		return tadmrescmanager;
	}

	public void setTadmrescmanager(TAdmRescManagerImpl tadmrescmanager) {
		this.tadmrescmanager = tadmrescmanager;
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

	public TAdmResc getResc() {
		return resc;
	}

	public void setResc(TAdmResc resc) {
		this.resc = resc;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setModel(Condition model) {
		this.model = model;
	}



	public class Condition{
		private String name;
		private String url;
		private String desc;
		private String id;
		private List<TAdmResc> sysResc;
		private List<TAdmResc> userResc;
		private String webid;		
		
		public String getWebid() {
			return webid;
		}
		public void setWebid(String webid) {
			this.webid = webid;
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
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TAdmRescManagerItf gettAdmRescManager() {
		return tAdmRescManager;
	}

	public void settAdmRescManager(TAdmRescManagerItf tAdmRescManager) {
		this.tAdmRescManager = tAdmRescManager;
	}

	public TAdmOperationLogsManager gettAdmOperationLogsManager() {
		return tAdmOperationLogsManager;
	}

	public void settAdmOperationLogsManager(TAdmOperationLogsManager tAdmOperationLogsManager) {
		this.tAdmOperationLogsManager = tAdmOperationLogsManager;
	}
	
}
