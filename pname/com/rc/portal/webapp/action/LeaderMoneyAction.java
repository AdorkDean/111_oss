package com.rc.portal.webapp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.commons.util.StringUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderManager;
import com.rc.portal.vo.TLeader;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;

public class LeaderMoneyAction extends BaseAction {

	private static final long serialVersionUID = 13313213L;

	private TLeader tleader=new TLeader();
	private TLeaderManager tleadermanager;
	private OpenSqlManage opensqlmanage;
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private List result=new ArrayList();
	private String type;
	private String url;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public TLeader getTleader() {
		return tleader;
	}

	public void setTleader(TLeader tleader) {
		this.tleader = tleader;
	}

	private String realname;
	private String phone;
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public TLeaderManager getTleadermanager() {
		return tleadermanager;
	}

	public void setTleadermanager(TLeaderManager tleadermanager) {
		this.tleadermanager = tleadermanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	/*
	 * 领袖会员历史列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list() {
		Map moneymap = new HashMap();
		Map map = new HashMap();
		String startDate = this.getRequest().getParameter("startDate");
		if(StringUtils.hasText(startDate)){
			moneymap.put("start_date", startDate.trim());
			this.getRequest().setAttribute("startDate", startDate);
		}else{
			moneymap.put("start_date", "1900-01-01 00:00:00");
		}
		String endDate = this.getRequest().getParameter("endDate");
		if(StringUtils.hasText(endDate)){
			moneymap.put("end_date", endDate.trim());
			this.getRequest().setAttribute("endDate", endDate);
		}else{
			moneymap.put("end_date", "9999-12-01 00:00:00");
		}
		if(!StringUtil.isEmpty(realname)){
			moneymap.put("realname", realname);
		}
		if(!StringUtil.isEmpty(phone)){
			moneymap.put("phone", phone);
		}
		if(StringUtils.hasText(type)){
			moneymap.put("type", type);
		}
		Object totalAmount =  opensqlmanage.selectObjectByObject(map, "t_member_amount_out.selectTotalAmount");
		Object finishAmount =  opensqlmanage.selectObjectByObject(map, "t_member_amount_out.selectFinishAmount");
		this.getRequest().setAttribute("totalAmount", totalAmount);
		this.getRequest().setAttribute("finishAmount", finishAmount);
		rs.setP_pageSize(20);
		pw = opensqlmanage.selectForPageByMap_drug(moneymap,"t_leader.selectLeaderCount","t_leader.selectByleader", rs.getP_curPage(),
				rs.getP_pageSize());
		if("2"==type||"2".equals(type)){
	    	String path=getRequest().getSession().getServletContext().getRealPath("");
			path=path+"/excel_temp/"+UtilDate.getDate()+System.currentTimeMillis()+".xls";	
				List lists = exportLeader();
				String s[][] = getLeaderArray(pw.getResult());
				ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
	    }else {
	    	return "list";
		}
	}
	/*
	 * 秀粉列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String showlist() {
		Map map = new HashMap();
		String startDate = this.getRequest().getParameter("startDate");
		if(StringUtils.hasText(startDate)){
			map.put("start_date", startDate.trim());
			this.getRequest().setAttribute("startDate", startDate);
		}else{
			map.put("start_date", "1900-01-01 00:00:00");
		}
		String endDate = this.getRequest().getParameter("endDate");
		if(StringUtils.hasText(endDate)){
			map.put("end_date", endDate.trim());
			this.getRequest().setAttribute("endDate", endDate);
		}else{
			map.put("end_date", "9999-12-01 00:00:00");
		}
		if(!StringUtil.isEmpty(realname)){
			map.put("sfname", realname);
		}
		if(!StringUtil.isEmpty(phone)){
			map.put("mobile", phone);
		}
		if(StringUtils.hasText(type)){
			map.put("type", type);
		}
		rs.setP_pageSize(20);
		pw = opensqlmanage.selectForPageByMap_drug(map,"t_leader.selectAllShowFenCount","t_leader.selectAllShowFen", rs.getP_curPage(),rs.getP_pageSize());
		   if("2"==type||"2".equals(type)){
		    	String path=getRequest().getSession().getServletContext().getRealPath("");
				path=path+"/excel_temp/"+UtilDate.getDate()+System.currentTimeMillis()+".xls";	
					List lists = export();
					String s[][] = getArray(pw.getResult());
					ExcelUtil.createExcel(path, lists, s);
				getRequest().setAttribute("xlsname", path);
				url = "/WEB-INF/pages/down/down.jsp";
				return "xls";
		    }else {
		    	return "list";
			}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> exportLeader() {
		List list = new ArrayList();
		list.add("用户名");
		list.add("领秀昵称");
		list.add("姓名");
		list.add("手机号");
		list.add("注册时间");
		list.add("秀粉数");
		list.add("状态");
		list.add("待入账佣金");
		list.add("可提现佣金");
		list.add("提现中佣金");
		list.add("累计");
		return list;
	}
	//存放领秀会员信息
	@SuppressWarnings("rawtypes")
	public String[][] getLeaderArray(List leaderList) {
				String[][] s = new String[leaderList.size()][15];
				for (int i = 0; i < leaderList.size(); i++) {
					Map m = (Map)leaderList.get(i);
					s[i][0]=m.get("user_name")==null?" ":m.get("user_name").toString();
					s[i][1]=m.get("nick_name")==null?" ":m.get("nick_name").toString();
					s[i][2]=m.get("real_name")==null?" ":m.get("real_name").toString();
					s[i][3]=m.get("phone")==null?" ":m.get("phone").toString();
					s[i][4]=m.get("create_dt")==null?" ":m.get("create_dt").toString();
					s[i][5]=m.get("count_sub")==null?"0":m.get("count_sub").toString();
					if((Integer)m.get("status")==1){
						s[i][6]="正常";
					}else{
						s[i][6]="停用";
					}
					s[i][7]=m.get("wait_amount")==null?"0":m.get("wait_amount").toString();
					s[i][8]=m.get("remaining_amount").toString()==null?"0":m.get("remaining_amount").toString();
					s[i][9]=m.get("freeze_amount")==null?"0":m.get("freeze_amount").toString();
					s[i][10]=m.get("total_amount").toString()==null?"0":m.get("total_amount").toString();
				}
				return s;
		}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> export() {
		List list = new ArrayList();
		list.add("用户名");
		list.add("秀粉手机号");
		list.add("注册时间");
		list.add("秀粉消费金额");
		list.add("领秀姓名");
		list.add("领秀手机号");
		return list;
	}
	//存放二维数组
		@SuppressWarnings("rawtypes")
		public String[][] getArray(List showfenList) {
			String[][] s = new String[showfenList.size()][15];
			for (int i = 0; i < showfenList.size(); i++) {
				Map m = (Map)showfenList.get(i);
				s[i][0]=m.get("user_name")==null?" ":m.get("user_name").toString();
				s[i][1]=m.get("mobile")==null?" ":m.get("mobile").toString();
				s[i][2]=m.get("register_date").toString()==null?" ":m.get("register_date").toString();
				s[i][3]=m.get("totlprice")==null?"0":m.get("totlprice").toString();
				s[i][4]=m.get("sfname").toString()==null?" ":m.get("sfname").toString();
				s[i][5]=m.get("sfphone").toString()==null?" ":m.get("sfphone").toString();
			}
			return s;
		}
	
	/*
	 * 查看领袖资料
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String view() throws Exception{
		String leaderId = this.getRequest().getParameter("id");
		Map map = new HashMap();
		map.put("id",leaderId );
		Object imageList = opensqlmanage.selectForListByMap_drug(map, "t_leader.selectleaderImage");
	    result.add(imageList);
		//this.getRequest().setAttribute("leaderImage", leaderImage);
		tleader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
		return "view";
	}
	/*
	 * 改变领袖状态
	 */
	public void changeStatus() throws Exception{
		String leaderStatus = this.getRequest().getParameter("leaderStatus");
		String leaderId = this.getRequest().getParameter("id");
		PrintWriter out = this.getResponse().getWriter();
		int flag=-1;
		tleader = tleadermanager.selectByPrimaryKey(Long.valueOf(leaderId));
		if(tleader!=null){
			if(leaderStatus.equalsIgnoreCase("1")){
				tleader.setStatus(1);
			}else if(leaderStatus.equalsIgnoreCase("2")){
				tleader.setStatus(2);
			}else{
				flag=0;
			}
			tleadermanager.updateByPrimaryKeySelective(tleader);
		}
		out.print(flag);
		out.close();
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
