package com.rc.portal.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.RxTOrderManager;
import com.rc.portal.service.RxTReserveOrderManager;
import com.rc.portal.service.TMemberBaseMessageExtManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.util.ConstantUtil;
import com.rc.portal.vo.RxTReserveOrder;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;
import com.rc.portal.webapp.model.UserinfoModel;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;
/**
 * 处方药预订单  
 * @author user3
 *
 */
public class RxReserveOrderAction extends BaseAction{
	private static final long serialVersionUID = 7278221845511172501L;
    private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	
	private OpenSqlManage opensqlmanage;
	private String user_name;
	private String receiver;
	private String mobile;
	private String audit_status;
	private String order_status;
	private Integer type;
	
	private String title;
	private String colum;
	
	private String url;
	/**
	 * 会员manage
	 */
	private TMemberManager tmembermanager;
	
	/**
	 * 会员基本信息扩展
	 */
	private TMemberBaseMessageExtManager tmemberbasemessageextmanager;
	
	/**
	 * 预订单manager
	 */
	private RxTReserveOrderManager rxtreserveordermanager;
	
	/**
	 * 处方药订单
	 */
	private RxTOrderManager rxtordermanager;
	
	public Object getModel() {
		return null;
	}

	public void setModel(Object o) {
	}

	/**
	 * 根据用户查询处方药预定单列表
	 * @return
	 * @throws Exception
	 */
	public String reserverOrderListByUser() throws Exception{
		String username = this.getRequest().getParameter("username");//用户名
		String realname = this.getRequest().getParameter("realname");//真实姓名
		String mobile = this.getRequest().getParameter("mobile");//手机号
		Map<String,String> paramMap = new HashMap<String,String>();
		if(StringUtils.hasText(username)){
			paramMap.put("username", username.trim());
		}
		if(StringUtils.hasText(realname)){
			paramMap.put("realname", realname.trim());
		}
		if(StringUtils.hasText(mobile)){
			paramMap.put("mobile", mobile.trim());
		}
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "rx_t_reserve_order.selectReserverOrderByUserCountByMap", "rx_t_reserve_order.selectReserverOrderByUserListByMap", rs.getP_curPage(), rs.getP_pageSize());
		this.getRequest().setAttribute("username", username);
		this.getRequest().setAttribute("realname", realname);
		this.getRequest().setAttribute("mobile", mobile);
		return "reserverOrderList";
	}

	/**
	 * 根据用户查询 该用户未处理处方药待预定  以及 已处理待预定订单
	 * @return
	 * @throws Exception
	 */
	public String reserverOrderByUser() throws Exception{
		String memberId = this.getRequest().getParameter("memberId");
		String source = this.getRequest().getParameter("source"); // 1 表示从用户待处理列表   2 表示从全部待处理订单
		if(StringUtils.hasText(memberId)){
			TMember tmember =tmembermanager.selectByPrimaryKey(Long.valueOf(memberId.trim()));
			TMemberBaseMessageExt tmemberbasemessageext =tmemberbasemessageextmanager.selectByPrimaryKey(Long.valueOf(memberId.trim()));
			this.getRequest().setAttribute("tmember",tmember);
			this.getRequest().setAttribute("tmemberbasemessageext",tmemberbasemessageext);
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("memberId", String.valueOf(tmember.getId()));
			List weichulilist =this.opensqlmanage.selectForListByMap(paramMap, "rx_t_reserve_order.selectReserverOrderByUserByMap");
			this.getRequest().setAttribute("weichulilist", weichulilist);
			this.getRequest().setAttribute("source", source);
			return "reserverOrderHandle";
		}else{
			return null;
		}
	}
	/**
	 * 撤回订单
	 * @return
	 * @throws Exception
	 */
	public void revokeReserverOrder() throws Exception {
		String id = this.getRequest().getParameter("id");
		String flag ="0";
		String message="服务器异常,操作失败";
		if(StringUtils.hasText("id")){
			RxTReserveOrder reserverOtder =this.rxtreserveordermanager.selectByPrimaryKey(Long.valueOf(id));
			if(reserverOtder.getIsEnd()!=null&&reserverOtder.getIsEnd().intValue()==1){
				message="该预定单已经完结,无法撤回";
			}else{
				reserverOtder.setUpdateDt(new Date());
				UserinfoModel userinfo = (UserinfoModel) this.getSession().getAttribute(ConstantUtil.logincookiename);
				reserverOtder.setUpdateUser(userinfo.getUsername());
				String resultFlag =rxtreserveordermanager.revokeReserverOrder(reserverOtder);
				if("1".equals(resultFlag)){
					flag="1";
					message="撤回成功";
				}else if("2".equals(resultFlag)){
					message="商品库存不足,撤回失败";
				}
			}
		}
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("flag", flag);
		resultMap.put("message", message);
		this.writeObjectToResponse(resultMap, ContentType.application_json);
	}
	
	
	
	/**
	 * 审核订单 
	 */
	public void changeOrderStatus() throws Exception{
		String orderStatus = this.getRequest().getParameter("orderStatus");
		String auditStatus = this.getRequest().getParameter("auditStatus");
		String cancelRemark = this.getRequest().getParameter("cancelRemark");
		String orderId = this.getRequest().getParameter("orderId");
		String flag ="0";
		String message="服务器异常,操作失败";
		try{
			if(StringUtils.hasText(orderId)){
				RxTReserveOrder reserverOtder =new RxTReserveOrder();
				RxTReserveOrder oldOrder = this.rxtreserveordermanager.selectByPrimaryKey(Long.valueOf(orderId));
				if(oldOrder.getIsEnd()!=null&&oldOrder.getIsEnd().intValue()==1){
					message="该预定单已经完结,无法撤回";
				}else{
					reserverOtder.setGoodid(oldOrder.getGoodid());
					reserverOtder.setNum(oldOrder.getNum());
					if(StringUtils.hasText(auditStatus)){
						reserverOtder.setId(Long.valueOf(orderId));
						if("1".equals(auditStatus)){
							reserverOtder.setAuditStatus(1);//审核通过
						}else if("2".equals(auditStatus)){
							reserverOtder.setAuditStatus(2);//审核不通过
							reserverOtder.setIsEnd(1);//完结
						}
					}else if(StringUtils.hasText(orderStatus)){
						if("9".equals(orderStatus)){
							reserverOtder.setId(Long.valueOf(orderId));
							reserverOtder.setOrderStatus(9);//订单状态 取消
							reserverOtder.setCancelRemark(cancelRemark);//取消备注
							reserverOtder.setCreateDt(new Date());//取消时间
						}
					}
					if(reserverOtder.getId()!=null){
						reserverOtder.setUpdateDt(new Date());
						UserinfoModel userinfo = (UserinfoModel) this.getSession().getAttribute(ConstantUtil.logincookiename);
						reserverOtder.setUpdateUser(userinfo.getUsername());
						boolean resultflag =this.rxtreserveordermanager.changeOrderStatus(reserverOtder);
						if(resultflag){
							flag ="1";
							message="操作成功";
						}
					}
				}
			}else{
				message="该预定单不存在,操作失败!";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("flag", flag);
		resultMap.put("message", message);
		this.writeObjectToResponse(resultMap, ContentType.application_json);
	}
	/**
	 * 计算运费
	 * @throws Exception
	 */
	public void getFreight() throws Exception{
		String orderIds = this.getRequest().getParameter("orderIds");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(StringUtils.hasText(orderIds)){
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("orderIds", orderIds.substring(0, orderIds.length()-1));
			resultMap = (Map<String,Object>) this.opensqlmanage.selectForObjectByMap(paramMap, "rx_t_reserve_order.getFreightByMap");
		}
		this.writeObjectToResponse(resultMap, ContentType.application_json);
	}
	
	/**
	 * 生成订单
	 * @throws Exception
	 */
	public void createReserveOrder() throws Exception{
		String freight = this.getRequest().getParameter("freight");//运费
		String orderIds = this.getRequest().getParameter("orderIds");//选中订单
		boolean checkFlag = true;
		String isover="0";
		String resultFlag ="0";
		String message ="服务器异常,操作失败";
		if(!StringUtils.hasText(freight)){
			checkFlag=false;
			message="请填写运费";
		}else{
			if(Double.valueOf(freight.trim())<0){
				checkFlag=false;
				message="运费必须大于等于0";
			}
		}
		if(checkFlag&&!StringUtils.hasText(orderIds)){
			checkFlag=false;
			message="请选择要下单的预定单";
		}
		if(checkFlag){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderIds", orderIds.substring(0, orderIds.length()-1));
			List<Map> orderList  = this.opensqlmanage.selectForListByMap(paramMap, "rx_t_reserve_order.getOrderByids");
			if(orderList!=null&&orderList.size()>0){
				Map firstMap = orderList.get(0);
				Map<String,String> checkGoodMap= new HashMap<String,String>();
				for(Map map:orderList){
					if(!String.valueOf(map.get("audit_status")).equals("1")){
						checkFlag=false;
						message="选择的预定单中存在未审核或是审核不通过的预定单,请重新选择";
						break;
					}else if(!String.valueOf(map.get("order_status")).equals("0")){
						checkFlag=false;
						message="选择的预定单中存在已处理的预定单,请重新选择";
					    break;	
					}else if(!checkReceiver(firstMap,map)){
						checkFlag=false;
						message="选择的预定单中存在收货地址不一致,请重新选择";
					    break;	
					}
				}
				
				if(checkFlag){
					List<Map> goodOrderList  = this.opensqlmanage.selectForListByMap(paramMap, "rx_t_reserve_order.getGroupGoodidOrderByids");
					if(goodOrderList!=null&&goodOrderList.size()>0){
						for(Map map:goodOrderList){
							paramMap.put("goodid", map.get("goodid"));
							paramMap.put("good_price", map.get("goodprice"));
							List goodpriceList = this.opensqlmanage.selectForListByMap(paramMap, "rx_t_reserve_order.getGroupGoodidPriceOrderByids");
						    if(goodpriceList!=null&&goodpriceList.size()>0){
						    	checkFlag=false;
								message="选择的预定单中存在同一商品价格不同,请重新选择";
							    break;	
						    }
						}
					}
				}
				if(checkFlag){
					boolean flag =this.rxtordermanager.createRxTOrder((Long)firstMap.get("id"), orderList, freight);
					if(flag){
						resultFlag="1";
						message="生成订单成功";
						paramMap.clear();
						paramMap.put("memberId", this.getRequest().getParameter("memberId"));
						List weichulilist =this.opensqlmanage.selectForListByMap(paramMap, "rx_t_reserve_order.selectReserverOrderByUserByMap");
						if(weichulilist!=null&&weichulilist.size()>0){
							isover="1";
						}
					}else{
						message="生成订单失败";
					}
				}
			}else{
				checkFlag=false;
				message="选择的预订单不存在,请重新选择";
			}
		}
		
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("resultFlag", resultFlag);
		resultMap.put("message", message);
		resultMap.put("isover", isover);
		this.writeObjectToResponse(resultMap, ContentType.application_json);
	}
	/**
	 * 检查收货地址是否一致
	 * @param firstMap
	 * @param twoMap
	 * @return
	 */
	public boolean checkReceiver(Map firstMap,Map twoMap) {
	    boolean flag = true;
		if(!String.valueOf(firstMap.get("member_id")).equals(String.valueOf(twoMap.get("member_id")))){
			flag= false;
		}else if(!firstMap.get("receiver").equals(twoMap.get("receiver"))){
			flag= false;
		}else if(!String.valueOf(firstMap.get("area_id")).equals(String.valueOf(twoMap.get("area_id")))){
			flag= false;
		}else if(!firstMap.get("area_name").equals(twoMap.get("area_name"))){
			flag= false;
		}else if(!firstMap.get("detailed_address").equals(twoMap.get("detailed_address"))){
			flag= false;
		}else if(firstMap.get("longitude")!=null&&twoMap.get("longitude")!=null&&!firstMap.get("longitude").equals(twoMap.get("longitude"))){
			flag= false;
		}else if(firstMap.get("latitude")!=null&&twoMap.get("latitude")!=null&&!firstMap.get("latitude").equals(twoMap.get("latitude"))){
			flag= false;
		}else if(firstMap.get("store_id")!=null&&twoMap.get("store_id")!=null&&!firstMap.get("store_id").equals(twoMap.get("store_id"))){
			flag= false;
		}else if(!firstMap.get("mobile").equals(twoMap.get("mobile"))){
			flag= false;
		}
		return flag;
	}
	
	public String detail(){
		String id = this.getRequest().getParameter("id");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("id", id);
		Map map =(Map) opensqlmanage.selectForObjectByMap_drug(paramMap, "rx_t_reserve_order.selectReserverOrderObject");
		this.getRequest().setAttribute("orderMap", map);
		return "detail";
	}
	
	/**
	 * 处方药预订单列表
	 * @return
	 */
	public String reserverOrderList()throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		if(null != user_name && !("").equals(user_name)){
			paramMap.put("user_name", user_name.trim());
		}
		if(null != receiver && !("").equals(receiver)){
			paramMap.put("receiver", receiver.trim());
		}
		if(null != mobile && !("").equals(mobile)){
			paramMap.put("mobile", mobile.trim());
		}
		if(null != audit_status && !("").equals(audit_status) && !("-1").equals(audit_status)){
			paramMap.put("audit_status", audit_status.trim());
		}
		if(null != order_status && !("").equals(order_status) && !("-1").equals(order_status)){
			paramMap.put("order_status", order_status.trim());
		}
		/*判断是否点击导出excel*/
		if(type != null && type != 0){
			paramMap.put("type", String.valueOf(type));
		}
		pw=opensqlmanage.selectForPageByMap_drug(paramMap, "rx_t_reserve_order.selectReserverOrderCountByMap", "rx_t_reserve_order.selectReserverOrderListByMap", rs.getP_curPage(), rs.getP_pageSize());
		/*导出excel*/
		if (type != null && type != 0) {
			String path = getRequest().getSession().getServletContext()
					.getRealPath("");
			path = path + "/excel_temp/" + UtilDate.getDate()
					+ System.currentTimeMillis() + ".xls";
			//List lists = excelExport(StringUtil.spaceSplit(title));
			List lists = excelExport("用户名#收货人/手机号#药品名称#价格#数量#合计#取消原因#审核状态#处理状态#完结状态#预定时间");
			String s[][] = getOrderArray(pw.getResult());
			ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		} else {//普通查询
			return "list";
		}
	}
	
	/**
	 * 设置表头（传入以#分割的表头字符串）
	 * 方法名：excelExport<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-8-3-上午9:51:27 <BR>
	 * @param paramStr
	 * @return List<String><BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public List<String> excelExport(String paramStr) {
		String str[] = paramStr.split("#");
		List<String> list = new ArrayList<String>();
		for (String string : str) {
			list.add(string);
		}
		return list;
	}
	// 存放二维数组
	public String[][] getOrderArray(List couponList) {
		String[][] s = new String[couponList.size()][14];
		for (int i = 0; i < couponList.size(); i++) {
			Map m = (Map) couponList.get(i);
			if (m.get("user_name") != null) {
				s[i][0] = m.get("user_name").toString();
			} else {
				s[i][0] = " ";
			}
			if (m.get("receiver") != null&&m.get("mobile") != null) {
				s[i][1] = m.get("receiver").toString()+"/"+m.get("mobile").toString();
			} else {
				s[i][1] = " ";
			}
			if (m.get("short_name") != null) {
				s[i][2] = m.get("short_name").toString();
			} else {
				s[i][2] = " ";
			}
			if (m.get("good_price") != null) {
				s[i][3] = m.get("good_price").toString();
			} else {
				s[i][3] = " ";
			}
			if (m.get("num") != null) {
				s[i][4] = m.get("num").toString();
			} else {
				s[i][4] = " ";
			}
			if (m.get("order_amount") != null) {
				s[i][5] = m.get("order_amount").toString();
			} else {
				s[i][5] = " ";
			}
			if (m.get("cancel_remark") != null) {
				s[i][6] = m.get("cancel_remark").toString();
			} else {
				s[i][6] = " ";
			}
			if (m.get("audit_status") != null) {
				if(Integer.parseInt(m.get("audit_status").toString())==0){
					s[i][7] = "待审核";
				}else if(Integer.parseInt(m.get("audit_status").toString())==1){
					s[i][7] = "审核通过";
				}else {
					s[i][7] = "审核不通过";
				}
			} else {
				s[i][7] = " ";
			}
			if (m.get("order_status") != null) {
				if(Integer.parseInt(m.get("order_status").toString())==0){
					s[i][8] = "待处理";
				}else if(Integer.parseInt(m.get("order_status").toString())==8){
					s[i][8] = "已下单";
				}else {
					s[i][8] = "已取消";
				}
			} else {
				s[i][8] = " ";
			}
			if (m.get("is_end") != null) {
				if(Integer.parseInt(m.get("is_end").toString())==0){
					s[i][9] = "未完结";
				}else {
					s[i][9] = "已完结";
				}
			} else {
				s[i][9] = " ";
			}
			if (m.get("create_dt") != null) {
				s[i][10] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format((Date) m.get("create_dt"));
			} else {
				s[i][10] = "";
			}
		}
		return s;
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

	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}

	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}

	
	public String getColum() {
		return colum;
	}

	public void setColum(String colum) {
		this.colum = colum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RxTReserveOrderManager getRxtreserveordermanager() {
		return rxtreserveordermanager;
	}

	public void setRxtreserveordermanager(
			RxTReserveOrderManager rxtreserveordermanager) {
		this.rxtreserveordermanager = rxtreserveordermanager;
	}

	public RxTOrderManager getRxtordermanager() {
		return rxtordermanager;
	}

	public void setRxtordermanager(RxTOrderManager rxtordermanager) {
		this.rxtordermanager = rxtordermanager;
	}

	public TMemberBaseMessageExtManager getTmemberbasemessageextmanager() {
		return tmemberbasemessageextmanager;
	}

	public void setTmemberbasemessageextmanager(
			TMemberBaseMessageExtManager tmemberbasemessageextmanager) {
		this.tmemberbasemessageextmanager = tmemberbasemessageextmanager;
	}
	
	
}
