package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.service.CFeedbackManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.vo.CFeedback;
import com.rc.portal.vo.CFeedbackExample;
import com.rc.portal.webapp.model.Rule;
import com.rc.portal.webapp.util.PageResult;

/**
 * 意见反馈
 * @author wm
 *
 */
public class FeedbackAction extends BaseAction {
	private static final long serialVersionUID = 2L;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private Rule rule;//模板规则
	private OpenSqlManage opensqlmanage;
	private CFeedbackManager cfeedbackmanager;
	private String collect;



	/**
	 * 意见反馈
	 * @return
	 */
	public String feedbackList(){
		String collect = this.getRequest().getParameter("collect");
		Map map = new HashMap();
		if(null != collect && !("").equals(collect)){
			map.put("collect", new Integer(collect));
		}
		rs.setP_pageSize(20);
		pw = opensqlmanage.selectForPageByMap_drug(map, "c_feedback.count_selectFeedback", "c_feedback.selectFeedback", rs.getP_curPage(), rs.getP_pageSize());
		return "feedbacklist";
	}
	
	public void collect() throws SQLException, IOException{
		this.getResponse().setContentType("text/html; charset=utf-8");
		this.getRequest().setCharacterEncoding("utf-8");
		String id  = this.getRequest().getParameter("id");
		String type  = this.getRequest().getParameter("type");
		int flag=-1;
		
		PrintWriter out = this.getResponse().getWriter();
		try {
			CFeedbackExample cfe = new CFeedbackExample();
			cfe.createCriteria().andIdEqualTo(new Integer(id));
			CFeedback cf = new CFeedback();
			cf.setCollect(new Integer(type));
			flag = cfeedbackmanager.updateByExampleSelective(cf, cfe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(flag);
		out.close();
	}
	

	public CFeedbackManager getCfeedbackmanager() {
		return cfeedbackmanager;
	}

	public void setCfeedbackmanager(CFeedbackManager cfeedbackmanager) {
		this.cfeedbackmanager = cfeedbackmanager;
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

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public String getCollect() {
		return collect;
	}

	public void setCollect(String collect) {
		this.collect = collect;
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
}
