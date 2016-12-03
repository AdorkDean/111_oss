package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderAgentManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.vo.TLeaderAgent;
import com.rc.portal.vo.TLeaderAgentExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.qrcode.QRCode;

public class LeaderAgentAction extends BaseAction {
	
	private TLeaderAgentManager tleaderagentmanager;
	private TMemberManager tmembermanager;
	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private TLeaderAgent bean;
	private Query query = new Query();
	private String flag;
	
	
	
	
	public String addPage(){
		this.getRequest().setAttribute("flag", "add");
		return "leader-agent-insert";
	}
	
	
	public void add(){
		
		PrintWriter out = null;
		long back = 0;
		String path = this.getRequest().getSession().getServletContext().getRealPath("/");
		
		try{
			out = this.getResponse().getWriter();
			String username = this.getRequest().getParameter("username");
			TMemberExample example = new TMemberExample();
			example.createCriteria().andUserNameEqualTo(username);
			List<TMember> list = tmembermanager.selectByExample(example);
			
			if(list.size()>0)
			{
				if(list.size()>1)	// 存两个username用户
				{	
					back = -1;
				}else{	// 正常
					TMember member = list.get(0);
					
					// 是否存在
					TLeaderAgentExample e = new TLeaderAgentExample();
					e.createCriteria().andMemberIdEqualTo(member.getId());
					List l = tleaderagentmanager.selectByExample(e);
					if(l.size()>0){
						back = -2;
					}else{
						bean.setMemberId(member.getId());
						String imgPath = path+member.getId()+".jpg";  
					    String encoderContent = "http://www.111yao.com";//bean.getCodeUrl();
					    QRCode handler = new QRCode();  
					    handler.encoderQRCode(encoderContent, imgPath, "jpg");  
						long id = tleaderagentmanager.insert(bean);
						back= id; 
					}
					
				}
			}else{	// 不存在用户
				back = 0;
			}
			
		}catch(Exception e){
			back = 0;
			e.printStackTrace();
		}
		
		out.print(back);
		out.close();
		
	}
	
	
	public String list(){
		
		Map map = new HashMap();
		if(query.username!=null && !"".equals(query.username)){
			map.put("username", query.username);
		}
		
		pw = opensqlmanage.selectForPageByMap_drug(map,"t_leader_agent.list-count", "t_leader_agent.list",  rs.getP_curPage(), rs.getP_pageSize());
		
		return "list";
	}
	
	
	public void delete() throws NumberFormatException, SQLException, IOException{
		
		PrintWriter out  = this.getResponse().getWriter();
		String id = this.getRequest().getParameter("id");
		int n = tleaderagentmanager.deleteByPrimaryKey(Long.parseLong(id));
		out.print(n);
		out.close();
		
	}
	
	
	public String record() throws NumberFormatException, SQLException{
		
		String id = this.getRequest().getParameter("id");
		TLeaderAgent record = tleaderagentmanager.selectByPrimaryKey(Long.parseLong(id));
		
		TMember member = tmembermanager.selectByPrimaryKey(record.getMemberId());
		
		this.getRequest().setAttribute("record", record);
		this.getRequest().setAttribute("uname", member.getUserName());
		this.getRequest().setAttribute("flag", "update");
		
		return "record";
	}
	

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return query;
	}

	@Override
	public void setModel(Object o) {
		// TODO Auto-generated method stub
		this.query = (Query)o;
	}
	
	

	public TLeaderAgentManager getTleaderagentmanager() {
		return tleaderagentmanager;
	}


	public void setTleaderagentmanager(TLeaderAgentManager tleaderagentmanager) {
		this.tleaderagentmanager = tleaderagentmanager;
	}


	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}


	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
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


	public TLeaderAgent getBean() {
		return bean;
	}


	public void setBean(TLeaderAgent bean) {
		this.bean = bean;
	}


	public Query getQuery() {
		return query;
	}


	public void setQuery(Query query) {
		this.query = query;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}





	class Query{
		
		private String username = "";

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		
	}

}
