package com.rc.portal.webapp.action;

import java.io.PrintWriter;
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
import com.rc.dst.client.util.ClientSubmit;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TLeaderStayMoneyManager;
import com.rc.portal.service.TMemberAmountOutManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.TLeaderStayMoney;
import com.rc.portal.vo.TMemberAmountOut;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;

public class NewAccountLeaderAction extends BaseAction {

	private Condition model = new Condition();
	private PageWraper pw = new PageWraper();
	private PageResult rs = new PageResult();
	private OpenSqlManage opensqlmanage;
	private List<Map> accGoodsList = new ArrayList<Map>();
	private TMemberAmountOutManager tmemberamountoutmanager;
	private TLeaderStayMoneyManager tleaderstaymoneymanager;
	private TSysParameterManager tsysparametermanager;
	/*导出excel文件地址*/
	private String url;

	/**
	 * 提现管理列表
	 * 方法名：getAccountList<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-8-2-下午2:23:22 <BR>
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public String getAccountList() {
		Map map = new HashMap();
		if (model.getLeaderName() != null
				&& !"".equals(model.getLeaderName().trim())) {
			map.put("leaderName", model.getLeaderName().trim());
		}
		if (model.getLeaderMobile() != null
				&& !"".equals(model.getLeaderMobile().trim())) {
			map.put("leaderMobile", model.getLeaderMobile().trim());
		}
		if (model.getSerialNumber() != null
				&& !"".equals(model.getSerialNumber().trim())) {
			map.put("serialNumber", model.getSerialNumber().trim());
		}
		if (model.getStatus() != null && model.getStatus() != -1) {
			map.put("status", model.getStatus());
		}
		pw = opensqlmanage.selectForPageByMap_drug(map,
				"t_member_amount_out.selectAmountOutCount",
				"t_member_amount_out.selectAmountOutByPageList",
				rs.getP_curPage(), rs.getP_pageSize());
		return "getAccountList";
	}

	/**
	 * 订单明细列表
	 * 方法名：getAccountOrderList<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-8-2-下午2:25:29 <BR>
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public String getAccountOrderList() {
		Map map = new HashMap();
		boolean flag = false;//切换公共页面标识
		if (model.getLeaderName() != null
				&& !"".equals(model.getLeaderName().trim())) {
			map.put("leaderName", model.getLeaderName().trim());
		}
		if (model.getSerialNumber() != null
				&& !"".equals(model.getSerialNumber().trim())) {
			map.put("serialNumber", model.getSerialNumber().trim());
		}
		if (model.getOrderStatus() != null && model.getOrderStatus() != -1) {
			map.put("orderStatus", model.getOrderStatus());
			flag = true;
		}
		if (model.getNickName() != null
				&& !"".equals(model.getNickName().trim())) {
			map.put("nickName", model.getNickName().trim());
		}
		if (model.getOrderNo() != null && !"".equals(model.getOrderNo().trim())) {
			map.put("orderNo", model.getOrderNo().trim());
		}
		if (model.getLeaderId() != null
				&& !"".equals(model.getLeaderId().trim())) {
			map.put("leaderId", model.getLeaderId().trim());
		}
		if (model.getType() != null && model.getType() != 0) {
			map.put("type", 2);
		} else {
			map.put("type", 0);
		}
		this.getRequest().setAttribute("flag", flag);
		List<String> tm = opensqlmanage.selectForListByMap_drug(map,"t_member_account.selectAccountCashList");
		this.getRequest().setAttribute("tm", tm);
		pw = opensqlmanage.selectForPageByMap_drug(map,
				"t_leader_stay_money.selectAccountCount",
				"t_leader_stay_money.selectAccountListByPage",
				rs.getP_curPage(), rs.getP_pageSize());
		if (model.getType() != null && model.getType() != 0) {
			String path = getRequest().getSession().getServletContext()
					.getRealPath("");
			path = path + "/excel_temp/" + UtilDate.getDate()
					+ System.currentTimeMillis() + ".xls";
			List lists = excelExport("收益流水号#订单编号#支付时间#订单状态#订单金额#返佣时间#返佣金额#用户昵称#领秀姓名");
			String s[][] = getLeaderArray(pw.getResult());
			ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		} else {
			return "getAccountOrderList";
		}
	}

	/**
	 * 会员返佣列表
	 * 方法名：getGoodsList<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-8-2-下午2:27:14 <BR>
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public String getGoodsList() {
		Map map = new HashMap();
		if (model.getId() != null && !"".equals(model.getId().trim())) {
			map.put("id", model.getId().trim());
			this.getRequest().setAttribute("id", model.getId().trim());
		}
		/*判断是否点击导出excel*/
		if (model.getType() != null && model.getType() != 0) {
			map.put("type", 2);
		} else {
			map.put("type", 0);
		}
		accGoodsList = opensqlmanage.selectForListByMap_drug(map,
				"t_leader_stay_money.selectAccountGoodsList");
		/*导出excel*/
		if (model.getType() != null && model.getType() != 0) {
			String path = getRequest().getSession().getServletContext()
					.getRealPath("");
			path = path + "/excel_temp/" + UtilDate.getDate()
					+ System.currentTimeMillis() + ".xls";
			List lists = excelExport("海典编码#商品名称#商品数量#返佣比例(排序)#商品金额#商品总额#商品佣金");
			String s[][] = getArray(accGoodsList,"goodsno#goods_name#quantity#percent#price#total#am");
			ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		} else {//普通查询
			return "getGoodsList";
		}
	}

	public void checkOrder() throws Exception {
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out = getResponse().getWriter();
		Integer flag = 0;
		if (model.getId() != null && !"0".equals(model.getId().trim())) {
			TLeaderStayMoney lsm = tleaderstaymoneymanager
					.selectByPrimaryKey(new Long(model.getId().trim()));
			lsm.setAuditStatus(1);
			lsm.setAuditDate(new Date());
			lsm.setAuditOpinion("审核通过");
			flag = tleaderstaymoneymanager.updateByPrimaryKeySelective(lsm);
		}
		out.write(flag.toString());
		out.close();
	}

	/**
	 * 提现审核通过  Status=2
	 * 方法名：checkAccount<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-8-2-下午2:30:17 <BR>
	 * @throws Exception void<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public void checkAccount() throws Exception {
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out = getResponse().getWriter();
		Integer flag = 0;
		if (model.getId() != null && !"0".equals(model.getId().trim())) {
			TMemberAmountOut mao = tmemberamountoutmanager
					.selectByPrimaryKey(new Long(model.getId().trim()));
			mao.setStatus(2);
			flag = tmemberamountoutmanager.updateByPrimaryKeySelective(mao);
			/* 点击审核发送短信 */
			if (flag > 0) {
				String phone = model.getPhone();
				if (phone != null && !"0".equals(phone.trim())) {
					flag = sendMessage(phone,"您的提现申请已通过，我们将于1个工作日内为您打款，具体到账时间为3-7个工作日，请注意查收。");
				}
			}
		}
		out.write(flag.toString());
		out.close();
	}

	/**
	 * 发送短信 
	 * 方法名：sendMessage<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-8-2-下午12:00:31 <BR>
	 * @param phone
	 * @param messageCode
	 * @return
	 * Integer<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	private Integer sendMessage(String phone, String messageCode)
			throws SQLException, Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobiles", phone);
		map.put("smsContent", messageCode);
		ClientSubmit.buildRequestBySMS(map, tsysparametermanager.getKeys("sms"));
		return 1;
	}

	/**
	 * 提现驳回
	 * 方法名：goNoCheckAccount<BR>
	 * 创建人：Marlon <BR>
	 * 时间：2016-8-5-上午9:22:12 <BR>
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public String goNoCheckAccount() {
		return "goNoCheckAccount";
	}
	
	/**
	 * 提现审核不通过
	 * Status=4
	 * @throws Exception
	 */
	public void noCheckAccount() throws Exception {
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out = getResponse().getWriter();
		Integer flag = 0;
		if (model.getId() != null && !"0".equals(model.getId().trim())) {
			TMemberAmountOut mao = tmemberamountoutmanager
					.selectByPrimaryKey(new Long(model.getId().trim()));
			mao.setStatus(4);
			if (model.getCheck_idear() != null
					&& !"".equals(model.getCheck_idear().trim())) {
				mao.setRemark(model.getCheck_idear().trim());
			}
			flag = tmemberamountoutmanager.audit(mao);
			/*点击驳回后将驳回内容以短信方式 */
			if (flag > 0) {
				String phone = model.getPhone();
				if (phone != null && !"0".equals(phone.trim())) {
					flag = sendMessage(phone,"您的提现申请被驳回，驳回原因："+model.getCheck_idear()+"。客服电话：400-606-3111");
				}
			}
		}
		out.write(flag.toString());
		out.close();
	}

	/**
	 * 提现完成
	 * Status=3
	 * @throws Exception
	 */
	public void overAccount() throws Exception {
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/plain;charset=utf-8");
		PrintWriter out = getResponse().getWriter();
		Integer flag = 0;
		if (model.getId() != null && !"0".equals(model.getId().trim())) {
			// TMemberAmountOut mao =
			// tmemberamountoutmanager.selectByPrimaryKey(new
			// Long(model.getId().trim()));
			// mao.setStatus(3);
			flag = tmemberamountoutmanager.finish(new Long(model.getId().trim()));
		}
		out.write(flag.toString());
		out.close();
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
	public String[][] getLeaderArray(List couponList) {
		String[][] s = new String[couponList.size()][14];
		for (int i = 0; i < couponList.size(); i++) {
			Map m = (Map) couponList.get(i);
			if (m.get("serial_number") != null) {
				s[i][0] = m.get("serial_number").toString();
			} else {
				s[i][0] = "";
			}
			if (m.get("order_sn") != null) {
				s[i][1] = m.get("order_sn").toString();
			} else {
				s[i][1] = "";
			}
			if (m.get("pay_date") != null) {
				s[i][2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format((Date) m.get("pay_date"));
			} else {
				s[i][2] = "";
			}
			if (m.get("order_status") != null) {
				if (Integer.parseInt(m.get("order_status").toString()) == 0) {
					s[i][3] = "待支付";
				} else if (Integer.parseInt(m.get("order_status").toString()) == 1) {
					s[i][3] = "待发货";
				} else if (Integer.parseInt(m.get("order_status").toString()) == 2) {
					s[i][3] = "待收货";
				} else if (Integer.parseInt(m.get("order_status").toString()) == 3) {
					s[i][3] = "已完成";
				} else if (Integer.parseInt(m.get("order_status").toString()) == 4) {
					s[i][3] = "已取消";
				} else if (Integer.parseInt(m.get("order_status").toString()) == 5) {
					s[i][3] = "已过期";
				}
			} else {
				s[i][3] = "未知";
			}
			if (m.get("order_amount") != null) {
				s[i][4] = m.get("order_amount").toString();
			} else {
				s[i][4] = "";
			}
			if (m.get("create_dt") != null) {
				s[i][5] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format((Date) m.get("create_dt"));
			} else {
				s[i][5] = "";
			}
			if (m.get("amount") != null) {
				s[i][6] = m.get("amount").toString();
			} else {
				s[i][6] = "0.00";
			}
			if (m.get("nick_name") != null) {
				s[i][7] = m.get("nick_name").toString();
			} else {
				s[i][7] = "";
			}
			if (m.get("real_name") != null) {
				s[i][8] = m.get("real_name").toString();
			} else {
				s[i][8] = "";
			}
		}
		return s;
	}

	// 存放二维数组
	public String[][] getArray(List couponList,String paramStr){
		String str[] = paramStr.split("#");
		String[][] s = new String[couponList.size()][14];
		for (int i = 0; i < couponList.size(); i++) {
			Map m = (Map) couponList.get(i);
			for (int j = 0; j < str.length; j++) {
				if (m.get(str[j]) == "create_dt") {
					if (m.get("create_dt") != null) {
						s[i][j] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) m.get("create_dt"));
					} else {
						s[i][j] = "";
					}
				}else {
					if (m.get(str[j]) != null) {
						s[i][j] = m.get(str[j]).toString();
					} else {
						s[i][j] = "";
					}
				}
			}
		}
		return s;
	}
	
	public class Condition {
		private String leaderName;
		private String leaderMobile;
		private String serialNumber;
		private Integer status;
		private Integer orderStatus;
		private String nickName;
		private String orderNo;
		private String leaderId;
		private String id;
		private String phone;
		private String check_idear;

		private Integer type;// 0 订单列表 2 导出excel

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getCheck_idear() {
			return check_idear;
		}

		public void setCheck_idear(String check_idear) {
			this.check_idear = check_idear;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLeaderId() {
			return leaderId;
		}

		public void setLeaderId(String leaderId) {
			this.leaderId = leaderId;
		}

		public Integer getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(Integer orderStatus) {
			this.orderStatus = orderStatus;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getLeaderName() {
			return leaderName;
		}

		public void setLeaderName(String leaderName) {
			this.leaderName = leaderName;
		}

		public String getLeaderMobile() {
			return leaderMobile;
		}

		public void setLeaderMobile(String leaderMobile) {
			this.leaderMobile = leaderMobile;
		}

		public String getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
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

	public List<Map> getAccGoodsList() {
		return accGoodsList;
	}

	public void setAccGoodsList(List<Map> accGoodsList) {
		this.accGoodsList = accGoodsList;
	}

	public TMemberAmountOutManager getTmemberamountoutmanager() {
		return tmemberamountoutmanager;
	}

	public void setTmemberamountoutmanager(
			TMemberAmountOutManager tmemberamountoutmanager) {
		this.tmemberamountoutmanager = tmemberamountoutmanager;
	}

	public TLeaderStayMoneyManager getTleaderstaymoneymanager() {
		return tleaderstaymoneymanager;
	}

	public void setTleaderstaymoneymanager(
			TLeaderStayMoneyManager tleaderstaymoneymanager) {
		this.tleaderstaymoneymanager = tleaderstaymoneymanager;
	}

	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}

	public void setTsysparametermanager(
			TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
