package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.util.StringUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberIntegralManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberExample;
import com.rc.portal.vo.TMemberIntegral;
import com.rc.portal.webapp.util.PageResult;

public class IntegralAction extends BaseAction {

	private static final long serialVersionUID = 78131837l;

	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private TMemberIntegralManager tmemberintegralmanager;
	private TMemberManager tmembermanager;

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}

	private TMemberIntegral memberIntegral = new TMemberIntegral();

	public TMemberIntegral getMemberIntegral() {
		return memberIntegral;
	}

	public void setMemberIntegral(TMemberIntegral memberIntegral) {
		this.memberIntegral = memberIntegral;
	}

	private TMember tm = new TMember();

	public TMember getTm() {
		return tm;
	}

	public void setTm(TMember tm) {
		this.tm = tm;
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

	public TMemberIntegralManager getTmemberintegralmanager() {
		return tmemberintegralmanager;
	}

	public void setTmemberintegralmanager(
			TMemberIntegralManager tmemberintegralmanager) {
		this.tmemberintegralmanager = tmemberintegralmanager;
	}

	/*
	 * 列表
	 */
	public String list() throws Exception {
		TMemberExample memberExample = new TMemberExample();
		if (memberExample != null) {
			if (!StringUtil.isEmpty(tm.getUserName()))
				memberExample.createCriteria().andUserNameLike(
						"%" + tm.getUserName() + "%");

		}
		if (!StringUtil.isEmpty(tm.getMobile())) {
			memberExample.createCriteria().andMobileLike(
					"%" + tm.getMobile() + "%");
		}
		pw = opensqlmanage.selectForPageByMap_drug(memberExample,
				"t_member.ibatorgenerated_countByExample",
				"t_member.selectByExampleByPage", rs.getP_curPage(),
				rs.getP_pageSize());
		System.out.println("11111111");
		return "list";
	}

	/*
	 * 详情
	 */
	public String show() throws NumberFormatException, SQLException {
		String strId = this.getRequest().getParameter("id");
		tm = tmembermanager.selectByPrimaryKey(Long.parseLong(strId));
		Map map = new HashMap();
		map.put("memberid", Long.parseLong(strId));
		pw = this.opensqlmanage.selectForPageByMap_drug(map,
				"t_member_integral.selectIntegeralcount",
				"t_member_integral.selectIntegeral", rs.getP_curPage(),
				rs.getP_pageSize());

		return "show";
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
