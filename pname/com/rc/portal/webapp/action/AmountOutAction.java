package com.rc.portal.webapp.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;
/**
 * 提现管理
 * @author Administrator
 *
 */
public class AmountOutAction extends BaseAction{

	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 

	private OpenSqlManage opensqlmanage;
	private String userName;
	private TMemberAmountOutManager tmemberamountoutmanager;
	private TMemberAmountOut amountOut;
	private String start_date;
	private String end_date;
	private String type;
	private String url;
	
	/**
	 * 提现列表
	 * @return
	 */
	public String list(){
		Map<String,Object> querMap = new HashMap<String,Object>();
		if(null != userName && !("").equals(userName)){
			querMap.put("userName", "%"+userName+"%");
		}
		pw = opensqlmanage.selectForPageByMap_drug(querMap, "t_member_amount_out.selectListPageCount", "t_member_amount_out.selectListPage", rs.getP_curPage(), rs.getP_pageSize());
		return "outList";
	}
	
	/**
	 * 提现明细
	 * @return
	 */
	public String details(){
		String id = this.getRequest().getParameter("id");
		Map<String,Object> querMap = new HashMap<String,Object>();
		if(null != id && !("").equals(id)){
			querMap.put("amountid", id);
		}
		Map map = (Map) opensqlmanage.selectForObjectByMap(querMap, "t_member_amount_out.selectDetails");
		this.getRequest().setAttribute("outMap", map);
		System.out.println("==="+map.get("user_name"));
		return "details";
	}
	
	/**
	 * 提现审核跳转
	 * @return
	 */
	public String auditEdit(){
		String id = this.getRequest().getParameter("id");
		Map<String,Object> querMap = new HashMap<String,Object>();
		if(null != id && !("").equals(id)){
			querMap.put("amountid", id);
		}
		Map map = (Map) opensqlmanage.selectForObjectByMap(querMap, "t_member_amount_out.selectDetails");
		this.getRequest().setAttribute("outMap", map);
		return "audit";
	}
	
//	/**
//	 * 提现审核
//	 * @return
//	 * @throws SQLException 
//	 */
//	public String audit() throws SQLException{
//        if(null != amountOut && null != amountOut.getId()){
//        	int row = tmemberamountoutmanager.audit(amountOut);
//        	if(row >0){
//        		this.addFlashMessage(true);
//        	}else{
//        		this.addFlashMessage(false);
//        	}
//        }
//		return "list";
//	}
	
	/**
	 * 提现完成跳转
	 * @return
	 * @throws SQLException
	 */
	public String outFinish() throws SQLException{
		String id = this.getRequest().getParameter("id");
		Map<String,Object> querMap = new HashMap<String,Object>();
		if(null != id && !("").equals(id)){
			querMap.put("amountid", id);
		}
		Map map = (Map) opensqlmanage.selectForObjectByMap(querMap, "t_member_amount_out.selectDetails");
		this.getRequest().setAttribute("outMap", map);
		return "finish";
	}
	
	/**
	 * 提现完成
	 * @return
	 * @throws SQLException
	 */
	public String finish() throws SQLException{
		String id = this.getRequest().getParameter("id");
		int row = tmemberamountoutmanager.finish(new Long(id));
    	if(row >0){
    		this.addFlashMessage(true);
    	}else{
    		this.addFlashMessage(false);
    	}
		return "list";
	}	
	

	public String brokerageOrderList(){
		Map<String,Object> querMap = new HashMap<String,Object>();
		if(null != userName && !("").equals(userName)){
			querMap.put("userName", userName);
		}
		if(null != start_date && !("").equals(start_date)){
			querMap.put("start_date", start_date);
		}
		if(null != end_date  && !("").equals(end_date)){
			querMap.put("end_date", end_date);
		}
		if(null != type && !("").equals(type) && type.equals("2")){
			querMap.put("type", 2);
		}else{
			querMap.put("type", 0);
		}
		pw = opensqlmanage.selectForPageByMap_drug(querMap, "t_leader_stay_money.brokerageOrderListCount", "t_leader_stay_money.brokerageOrderList", rs.getP_curPage(), rs.getP_pageSize());
		if(null != type &&!("").endsWith(type) && (type).equals("2")){
			String path=getRequest().getSession().getServletContext().getRealPath("");
			path=path+"/excel_temp/"+UtilDate.getDate()+System.currentTimeMillis()+".xls";	
				List lists = export();
				String s[][] = getArray(pw.getResult());
				ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		}else{
			return "brokerageOrderList";
		}
	}
	
	//设置表头
		public List<String> export() {
			List list = new ArrayList();
			list.add("订单编号 ");
			list.add("订单会员名称");
			list.add("支付方式");
			list.add("支付金额");
			list.add("下单时间");
			list.add("订单状态 ");
			list.add("领秀会员名称");
			list.add("订单佣金");
			return list;
		}
		//存放二维数组
		public String[][] getArray(List list) {
			String[][] s = new String[list.size()][8];
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map)list.get(i);
				if(m.get("order_sn")!=null){
					s[i][0]=m.get("order_sn").toString();
				}else{
					s[i][0] = " ";
				}
				if(m.get("ouser_name")!=null){
					s[i][1]=m.get("ouser_name").toString();
				}else{
					s[i][1]="";
				}
				
				if(m.get("pay_method")!=null){
					s[i][2]=m.get("pay_method").toString();
				}else{
					s[i][2]="";
				}
				
				if(m.get("payable_amount")!=null){
					s[i][3]=m.get("payable_amount").toString();
				}else{
					s[i][3]="";
				}
				
				if(m.get("create_date")!=null){
					s[i][4]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("create_date"));
				}else{
					s[i][4]="";
				}
				
				if(m.get("order_status")!=null){
					if(Integer.parseInt(m.get("order_status").toString())==0){
						s[i][5]="待支付";
					}else if(Integer.parseInt(m.get("order_status").toString())==1){
						s[i][5]="待发货";
					}else if(Integer.parseInt(m.get("order_status").toString())==2){
						s[i][5]="待收货";
					}else if(Integer.parseInt(m.get("order_status").toString())==3){
						s[i][5]="已完成";
					}else if(Integer.parseInt(m.get("order_status").toString())==4){
						s[i][5]="已取消";
					}else if(Integer.parseInt(m.get("order_status").toString())==5){
						s[i][5]="已过期";
					}
				}else{
					s[i][5]="";
				}
				
				if(m.get("luser_name")!=null){
					s[i][6]=m.get("luser_name").toString();
				}else{
					s[i][6]="";
				}
				
				if(m.get("amount")!=null){
					s[i][7]=m.get("amount").toString();
				}else{
					s[i][7]="";
				}
			}
			return s;
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


	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public TMemberAmountOutManager getTmemberamountoutmanager() {
		return tmemberamountoutmanager;
	}

	public void setTmemberamountoutmanager(
			TMemberAmountOutManager tmemberamountoutmanager) {
		this.tmemberamountoutmanager = tmemberamountoutmanager;
	}

	public TMemberAmountOut getAmountOut() {
		return amountOut;
	}

	public void setAmountOut(TMemberAmountOut amountOut) {
		this.amountOut = amountOut;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
