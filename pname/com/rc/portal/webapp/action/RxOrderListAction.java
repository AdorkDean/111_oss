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
import com.rc.portal.service.RxTOrderManager;
import com.rc.portal.service.TOrderShipmentManager;
import com.rc.portal.vo.RxTOrder;
import com.rc.portal.vo.TOrderShipment;
import com.rc.portal.vo.TOrderShipmentExample;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;

public class RxOrderListAction extends BaseAction {
	
	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult(); 
	private OpenSqlManage opensqlmanage;
	private TOrderShipmentManager tordershipmentmanager;
	private RxTOrderManager rxtordermanager;
	private Map orderMap=new HashMap();
	private List<Map> orderGoodsList=new ArrayList<Map>();
	private List<TOrderShipment> orderShipmentList=new ArrayList<TOrderShipment>();
	/*导出excel文件地址*/
	private String url;
	
	public String getReOrderList(){
		Map map=new HashMap();
		if(model.getUname()!=null&&!"".equals(model.getUname().trim())){
			map.put("uname",model.getUname().trim());
		}
		if(model.getOrderNo()!=null&&!"".equals(model.getOrderNo().trim())){
			map.put("orderNo",model.getOrderNo().trim());
		}
		if(model.getMobile()!=null&&!"".equals(model.getMobile().trim())){
			map.put("mobile", model.getMobile().trim());
		}
		if(model.getReceiver()!=null&&!"".equals(model.getReceiver().trim())){
			map.put("receiver", model.getReceiver().trim());
		}
		if(model.getStartDate()!=null&&!"".equals(model.getStartDate().trim())){
			map.put("startDate", model.getStartDate().trim());
		}else{
			map.put("startDate","1900-01-01");
		}
		if(model.getEndDate()!=null&&!"".equals(model.getEndDate().trim())){
			map.put("endDate", model.getEndDate().trim());
		}else{
			map.put("endDate", "9999-01-01");
		}
		if(model.getStatus()!=null&&model.getStatus()!=-1){
			map.put("status", model.getStatus());
		}
		/*判断是否点击导出excel*/
		if (model.getType() != null && model.getType() != 0) {
			map.put("type", 2);
		} else {
			map.put("type", 0);
		}
		pw= opensqlmanage.selectForPageByMap_drug(map, "rx_t_order.selectRxOrderListCount", "rx_t_order.selectRxOrderListByPage", rs.getP_curPage(), rs.getP_pageSize());
		/*导出excel*/
		if (model.getType() != null && model.getType() != 0) {
			String path = getRequest().getSession().getServletContext()
					.getRealPath("");
			path = path + "/excel_temp/" + UtilDate.getDate()
					+ System.currentTimeMillis() + ".xls";
			List lists = excelExport("订单编号#配送方式#应付金额#会员名称#订单状态#收货人/手机号#下单时间#推送海典");
			String s[][] = getOrderArray(pw.getResult());
			ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		} else {//普通查询
			return "getReOrderList";
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
			if (m.get("order_sn") != null) {
				s[i][0] = m.get("order_sn").toString();
			} else {
				s[i][0] = " ";
			}
			if (m.get("delivery") != null) {
				s[i][1] = m.get("delivery").toString();
			} else {
				s[i][1] = " ";
			}
			if (m.get("order_amount") != null) {
				s[i][2] = m.get("order_amount").toString();
			} else {
				s[i][2] = " ";
			}
			if (m.get("user_name") != null) {
				s[i][3] = m.get("user_name").toString();
			} else {
				s[i][3] = " ";
			}
			if (m.get("order_status") != null) {
				if(Integer.parseInt(m.get("order_status").toString())==0){
					s[i][4] = "已下单";
				}else if(Integer.parseInt(m.get("order_status").toString())==1){
					s[i][4] = "待收货";
				}else if(Integer.parseInt(m.get("order_status").toString())==2){
					s[i][4] = "已取消";
				}else if(Integer.parseInt(m.get("order_status").toString())==3){
					s[i][4] = "拒收";
				}else{
					s[i][4] = "已完成";
				}
			} else {
				s[i][4] = " ";
			}
			if (m.get("receiver") != null&&m.get("mobile") != null) {
				s[i][5] = m.get("receiver").toString()+"/"+m.get("mobile").toString();
			} else {
				s[i][5] = " ";
			}
			if (m.get("create_dt") != null) {
				s[i][6] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) m.get("create_dt"));
			} else {
				s[i][6] = "";
			}
			if (m.get("is_push") != null) {
				if(Integer.parseInt(m.get("is_push").toString())==0){
					s[i][7] = "否";
				}else {
					s[i][7] = "是";
				}
			} else {
				s[i][7] = " ";
			}
		}
		return s;
	}
	
	public String getOrderDetail() throws Exception{
		Map map=new HashMap();
		if(model.getOrderid()!=null&&model.getOrderid()!=0){
			map.put("orderid", model.getOrderid());
		}
		orderMap =(Map)opensqlmanage.selectForObjectByMap_drug(map,"rx_t_order.selectRxOrderDetail");
		orderGoodsList=opensqlmanage.selectForListByMap_drug(map, "rx_t_order.selectRxOrderGoods");
		TOrderShipmentExample orderShipmentExample = new TOrderShipmentExample();
		orderShipmentExample.createCriteria().andOrderIdEqualTo(model.getOrderid()).andOrderTypeEqualTo(1);
		orderShipmentList =tordershipmentmanager.selectByExample(orderShipmentExample);
		return "getOrderDetail";
	}
	
	/**
	 * 订单完成
	 * @throws Exception
	 */
	public void finishOrder() throws Exception {
		 RxTOrder order = rxtordermanager.selectByPrimaryKey(model.getOrderid());
		if (order != null) {
			rxtordermanager.finishOrder(order);
			writeObjectToResponse(1, ContentType.text_html);
		} else {
			writeObjectToResponse(0, ContentType.text_html);
		}
	}
	/**
	 * 拒收
	 * @throws Exception
	 */
	public void refuseOrder() throws Exception {
		RxTOrder order = rxtordermanager.selectByPrimaryKey(model.getOrderid());
		if (order != null) {
			rxtordermanager.refuseOrder(order);
			writeObjectToResponse(1, ContentType.text_html);
		} else {
			writeObjectToResponse(0, ContentType.text_html);
		}
	}
	
	/*取消订单*/ 
	public void cancelOrder() throws Exception {
		long flag = -1;
		try {
		 RxTOrder order = rxtordermanager.selectByPrimaryKey(model.getOrderid());
			if (order != null) {
				if (order.getOrderStatus()!=2&&order.getOrderStatus()!=3&&order.getOrderStatus()!=4) {
					rxtordermanager.cancelOrder(order);
					flag = 1;
				} else {
					flag = 2;// 订单已经取消或是已经过期了
				}
			} else {
				flag = 0;// 订单不存在
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeObjectToResponse(flag, ContentType.text_html);
	}
	
	
	/**
	 * 推送海淀
	 * @throws SQLException 
	 * @throws Exception
	 */
	public void pushOrder(){
		try {
		RxTOrder order = rxtordermanager.selectByPrimaryKey(model.getOrderid());
		if(order!=null){
			if(order.getOrderStatus()!=null){
				if(order.getOrderStatus()==0){
						rxtordermanager.pushRxOrder(order.getId());
					writeObjectToResponse(1, ContentType.text_html);//推送正常
				}else if(order.getOrderStatus()==1){
					writeObjectToResponse(3, ContentType.text_html);//订单已发货
				}else if(order.getOrderStatus()==2){
					writeObjectToResponse(4, ContentType.text_html);//订单已取消
				}else if(order.getOrderStatus()==3){
					writeObjectToResponse(5, ContentType.text_html);//订单已拒收
				}else if(order.getOrderStatus()==4){
					writeObjectToResponse(6, ContentType.text_html);//订单已完成
				}
			}else{
				writeObjectToResponse(2, ContentType.text_html);//订单状态异常
			}
		}else{
			writeObjectToResponse(0, ContentType.text_html);//订单不存在
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public class Condition {
	private String uname;
	private String orderNo;
	private String startDate;
	private String endDate;
	private String receiver;
	private String mobile;
	private Integer status;
	private Long orderid;
	private Integer type;// 0 订单列表 2 导出excel
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
		
		
	}
	@Override
	public Object getModel() {
		return this.model;
	}

	@Override
	public void setModel(Object o) {
		this.model = (Condition) o;
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

	public Map getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map orderMap) {
		this.orderMap = orderMap;
	}

	public List<Map> getOrderGoodsList() {
		return orderGoodsList;
	}

	public void setOrderGoodsList(List<Map> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}

	public TOrderShipmentManager getTordershipmentmanager() {
		return tordershipmentmanager;
	}

	public void setTordershipmentmanager(TOrderShipmentManager tordershipmentmanager) {
		this.tordershipmentmanager = tordershipmentmanager;
	}

	public List<TOrderShipment> getOrderShipmentList() {
		return orderShipmentList;
	}

	public void setOrderShipmentList(List<TOrderShipment> orderShipmentList) {
		this.orderShipmentList = orderShipmentList;
	}

	public RxTOrderManager getRxtordermanager() {
		return rxtordermanager;
	}

	public void setRxtordermanager(RxTOrderManager rxtordermanager) {
		this.rxtordermanager = rxtordermanager;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
