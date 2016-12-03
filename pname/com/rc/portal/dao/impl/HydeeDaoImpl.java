package com.rc.portal.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.commons.util.InfoUtil;
import com.rc.portal.dao.HydeeDao;
import com.rc.portal.vo.CDeliveryWay;
import com.rc.portal.vo.CPaymentWay;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TOrderFlow;
import com.rc.portal.vo.TOrderFlowExample;
import com.rc.portal.vo.TOrderItem;
import com.rc.portal.vo.TOrderItemExample;

/**
 * 海典dao实现
 * 
 * @author user00
 * @createTime 2015-9-6 上午10:44:19
 */
public class HydeeDaoImpl implements HydeeDao {
	private SqlMapClient sqlMapClient;
	
	public final String hd_jdbc_driver = InfoUtil.getInstance().getInfo("application", "hd.jdbc.driver");

	public String hd_jdbc_url = InfoUtil.getInstance().getInfo("application", "hd.jdbc.url");

	public String hd_jdbc_username = InfoUtil.getInstance().getInfo("application", "hd.jdbc.username");

	public String hd_jdbc_password = InfoUtil.getInstance().getInfo("application", "hd.jdbc.password");
	private static final Logger logger = LoggerFactory.getLogger(HydeeDaoImpl.class);

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
	public HydeeDaoImpl() {
		super();
		/**
		 * 加载jdbc驱动；
		 */
	}
	
	public HydeeDaoImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * 插入海典库
	 * 
	 * @param dstOrder
	 * @return
	 */
	@Override
	public boolean insertDstOrderToHD(TOrder order,TMember tMember) {
		JDBCExecutor executor = new JDBCExecutor();
		return executor.insertDstOrder(order,tMember);
	}

	public class JDBCExecutor {
		private Connection connection;
		private Statement stmt;
		
		private CallableStatement callStmt = null;

		private JDBCExecutor() {
			try {
				Class.forName(hd_jdbc_driver);
				connection = DriverManager.getConnection(hd_jdbc_url, hd_jdbc_username, hd_jdbc_password);
				
				stmt = connection.createStatement();
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("---------------------连接海典JDBC异常:" + e.getMessage());
			}
		}

		/**
		 * 插入订单
		 */
		public boolean insertDstOrder(TOrder tOrder,TMember tMember) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				if(tMember==null){
					logger.error("订单号为:"+tOrder.getOrderSn()+"的订单会员账号有误");
					return false;
				}
				if(tOrder.getAreaId()==null){
					logger.error("订单号为:"+tOrder.getOrderSn()+"的订单地址信息有误");
					return false;
				}
				String province = null;
				String city = null;
				String area = null;
				if(tOrder.getAreaName()!=null && !"".equals(tOrder.getAreaName())){
					String shengshiqu = tOrder.getAreaName();
					String[] str = shengshiqu.split("-");
					if(str!=null && str.length>0){
						province = str[0];
					}
					if(str!=null && str.length>1){
						city = str[1];
					}
					if(str!=null && str.length>2){
						area = str[2];
					}
				}
				
				
//				String get_busno_sql = "select defbusno from u_olshop where olshopid = 17";
//				ResultSet res = stmt.executeQuery(get_busno_sql);
//				String busno = "6007";
//				while (res.next()) {
//					busno = res.getString(1);
//				}
				
				String peisong = "普通快递";
				CDeliveryWay selectCDeliveryWay = new CDeliveryWay();
				selectCDeliveryWay.setId(tOrder.getDeliveryId());
				CDeliveryWay cDeliveryWay = (CDeliveryWay) sqlMapClient.queryForObject(
						"c_delivery_way.ibatorgenerated_selectByPrimaryKey", selectCDeliveryWay);
				String olshopid = "17";
				
				if (cDeliveryWay.getDeliveryCode().equals("ysd") ) {
					peisong = "药士达";
				}
				if (cDeliveryWay.getDeliveryCode().equals("ysdj") ) {
					peisong = "药师到家";
				}

				CPaymentWay selectCPaymentWay = new CPaymentWay();
				selectCPaymentWay.setId(tOrder.getPaymentId());
				CPaymentWay cPaymentWay = (CPaymentWay) sqlMapClient.queryForObject(
						"c_payment_way.ibatorgenerated_selectByPrimaryKey", selectCPaymentWay);
				String zhifubaoLiushui = "";
				Date payTime = null;
				String alipay_no = "";
				String paytype = "2";
				if (cPaymentWay.getPaymentWay() == 0) {// 线上支付
					paytype = "1";
					TOrderFlowExample tOrderFlowExample = new TOrderFlowExample();
					tOrderFlowExample.createCriteria().andOrderIdEqualTo(tOrder.getId());
					List<TOrderFlow> tOrderFlowList = sqlMapClient.queryForList(
							"t_order_flow.ibatorgenerated_selectByExample", tOrderFlowExample);
					for (TOrderFlow tOrderFlow : tOrderFlowList) {

						if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("pay99billBankPlugin") || tOrderFlow
										.getPaymentPlugin().equals("pay99billPlugin"))) {
							alipay_no = "快钱支付";
//							alipay_id = "1";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("alipayBankPlugin")
										|| tOrderFlow.getPaymentPlugin().equals("alipayDirectPlugin")
										|| tOrderFlow.getPaymentPlugin().equals("alipayDualPlugin") || tOrderFlow
										.getPaymentPlugin().equals("alipayPartnerPlugin"))) {
							alipay_no = "支付宝";
//							alipay_id = "2";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("alipayWapPlugin"))) {
							alipay_no = "手机支付宝";
//							alipay_id = "3";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("payWapYktPlugin") || tOrderFlow
										.getPaymentPlugin().equals("payYktPlugin"))) {
							alipay_no = "医卡通支付";
//							alipay_id = "6";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("wzfWapPlugin") || tOrderFlow
										.getPaymentPlugin().equals("payWzfPlugin"))) {
							alipay_no = "微信支付";
//							alipay_id = "8";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("unionpayPlugin"))) {
							alipay_no = "银联支付";
//							alipay_id = "10";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("yeepayPlugin"))) {
							alipay_no = "易宝支付";
//							alipay_id = "11";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("tenpayPartnerPlugin")
										|| tOrderFlow.getPaymentPlugin().equals("tenpayDirectPlugin") || tOrderFlow
										.getPaymentPlugin().equals("tenpayBankPlugin"))) {
							alipay_no = "财付通(担保交易)";
//							alipay_id = "12";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("paypalPlugin"))) {
							alipay_no = "Paypal";
//							alipay_id = "13";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("paySxpayPlugin"))) {
							// 闪白条
							alipay_no = "闪白条";
//							alipay_id = "20";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("wzfMobilePlugin"))) {
							// 微信支付移动端
							alipay_no = "微信支付移动端";
//							alipay_id = "21";
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("alipayMobilePlugin"))) {
							// 支付宝移动端
							alipay_no = "支付宝移动端";
//							alipay_id = "22";
						}
						if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("alipayBankPlugin")
										|| tOrderFlow.getPaymentPlugin().equals("alipayDirectPlugin")
										|| tOrderFlow.getPaymentPlugin().equals("alipayDualPlugin") || tOrderFlow
										.getPaymentPlugin().equals("alipayPartnerPlugin"))) {
							zhifubaoLiushui = tOrderFlow.getThirdPaySerialnumber();
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("alipayWapPlugin"))) {
							zhifubaoLiushui = tOrderFlow.getThirdPaySerialnumber();
						} else if (tOrderFlow.getPaymentPlugin() != null
								&& (tOrderFlow.getPaymentPlugin().equals("alipayMobilePlugin"))) {
							// 支付宝移动端
							zhifubaoLiushui = tOrderFlow.getThirdPaySerialnumber();
						}
						
						if(tOrderFlow.getCreateTime()!=null){
							payTime = tOrderFlow.getCreateTime();
						}
					}
				}else{
					if (cDeliveryWay.getDeliveryCode().equals("ysd") || cDeliveryWay.getDeliveryCode().equals("ysdj") ) {
						alipay_no = "线下支付";
					}else{
						alipay_no = "京东到付";
					}
				}

				BigDecimal amountTotal = new BigDecimal(0);
				BigDecimal getPrice = new BigDecimal(0);
				BigDecimal zhekouAll = new BigDecimal(0);//折扣总金额
				// 所有订单项
				TOrderItemExample tOrderItemExample = new TOrderItemExample();
				tOrderItemExample.createCriteria().andOrderIdEqualTo(tOrder.getId());
				List<TOrderItem> orderItemList = sqlMapClient.queryForList(
						"t_order_item.ibatorgenerated_selectByExample", tOrderItemExample);
				for (TOrderItem tOrderItem : orderItemList) {
					if (tOrderItem != null && tOrderItem.getQuantity() != null && tOrderItem.getPrice() != null) {
						amountTotal = amountTotal.add(tOrderItem.getPrice().multiply(
								new BigDecimal(tOrderItem.getQuantity())));
						getPrice = getPrice.add(tOrderItem.getPrice()
								.multiply(new BigDecimal(tOrderItem.getQuantity())));
					}
				}
				if (tOrder != null && tOrder.getShippingFee() != null) {// 运费
					amountTotal = amountTotal.add(tOrder.getShippingFee());
				}
				if (tOrder != null && tOrder.getPromotionalDiscount() != null) {// 促销折扣
					amountTotal = amountTotal.subtract(tOrder.getPromotionalDiscount());
					zhekouAll = zhekouAll.add(tOrder.getPromotionalDiscount());
				}
				if (tOrder != null && tOrder.getCouponDiscount() != null) {// 优惠券折扣
					amountTotal = amountTotal.subtract(tOrder.getCouponDiscount());
					zhekouAll = zhekouAll.add(tOrder.getCouponDiscount());
				}

				if (tOrder != null && tOrder.getIntegrationDiscount() != null) {// 积分抵扣
					amountTotal = amountTotal.subtract(tOrder.getIntegrationDiscount());
					zhekouAll = zhekouAll.add(tOrder.getIntegrationDiscount());
				}
				if (tOrder != null && tOrder.getAdjustAmount() != null) {// 调整金额
					amountTotal = amountTotal.add(tOrder.getAdjustAmount());
					//经过联调,确定为调整金额放在整单优惠金额里面去;
					zhekouAll = zhekouAll.subtract(tOrder.getAdjustAmount());
				}
				if (tOrder != null && tOrder.getTax() != null) {// 税金
					amountTotal = amountTotal.add(tOrder.getTax());
				}

				amountTotal = amountTotal.compareTo(new BigDecimal(0)) > 0 ? amountTotal : new BigDecimal(0);
				String _orderSource = tOrder.getOrderSource()==null?"":tOrder.getOrderSource()==1?"pc":tOrder.getOrderSource()==2?"wap":
					tOrder.getOrderSource()==3?"安卓":tOrder.getOrderSource()==4?"IOS":tOrder.getOrderSource()==5?"手动下单":"";
				String fapiaoType = tOrder.getInvoiceType()==null?"":tOrder.getInvoiceType()==1?"明细":tOrder.getInvoiceType()==2?"药品":tOrder.getInvoiceType()==3?"保健品"
						:tOrder.getInvoiceType()==4?"医疗器械":"";
				//发票内容类型 1 明细 2 药品 3 保健品 4 医疗器械
				// 插入主订单
				
				

				// 去除订单项里重复商品的不同订单项
				List<TOrderItem> tOrderItems = new ArrayList<TOrderItem>();
				Map<String, Integer> productMap = new HashMap<String, Integer>();
				TGoods tGoods = null;
				TGoods selectTGoods = null;
				for (TOrderItem item : orderItemList) {
					selectTGoods = new TGoods();
					selectTGoods.setId(item.getGoodsId());

					tGoods = (TGoods) sqlMapClient.queryForObject("t_goods.ibatorgenerated_selectByPrimaryKey",
							selectTGoods);
					if(tGoods!=null){
						if (tOrderItems.isEmpty()) {
							productMap.put(tGoods.getGoodsno(), item.getQuantity());
							item.setGoodsSn(tGoods.getGoodsno());
							item.setGoodsName(tGoods.getGoodsName());
							tOrderItems.add(item);
						} else {
							if (productMap.containsKey(tGoods.getGoodsno())) {
								productMap.put(tGoods.getGoodsno(),
										item.getQuantity() + productMap.get(tGoods.getGoodsno()));
							} else {
								productMap.put(tGoods.getGoodsno(), item.getQuantity());
								item.setGoodsSn(tGoods.getGoodsno());
								item.setGoodsName(tGoods.getGoodsName());
								tOrderItems.add(item);
							}
						}
					}
				}
				// 存海典订单项数据
				if(tOrderItems==null||tOrderItems.size()<=0){
					logger.error("订单号为"+tOrder.getOrderSn()+"的订单无商品信息");
				}
				String mendianId = "6007";
				if (tOrder != null && tOrder.getStoreId() != null && !"".equals(tOrder.getStoreId())) {
					mendianId = tOrder.getStoreId();
				} else if (tOrder != null && tOrder.getAreaId() == 3218L) {// 望京地区
					mendianId = "8134";
				} else if (tOrder != null && tOrder.getAreaId() == 3281L) {// 亦庄地区
					mendianId = "8262";
				}
				BigDecimal totalFee = new BigDecimal(0);
				String sql_c = " ";
				for (TOrderItem tOrderItem : tOrderItems) {
					if(tOrderItem!=null && tOrderItem.getPrice()!=null && tOrderItem
							.getQuantity()!=null){
						totalFee = totalFee.add(tOrderItem.getPrice().multiply(new BigDecimal(tOrderItem.getQuantity())));
					}
					boolean imFlag = false;
					ResultSet resultSet = stmt.executeQuery("select olorderno from t_ol_srcorder_c where olorderno='"
							+ tOrder.getOrderSn() + "' and oid = '"+tOrderItem.getId()+"' and olshopid='"+olshopid+"'");
					while (resultSet != null && resultSet.next()) {// 库里已经存在；
						imFlag = true;
						System.out.println("已经存在");
						break;
					}
					if(!imFlag){
						sql_c += "exec p_ol_srcorder_io_c '" +
								olshopid+"','" +				//--网店编码		主键
								tOrder.getOrderSn()+"','" +		//--网店订单号	主键
								tOrderItem.getId()+"','" +		//--子订单编号	主键
								tOrderItem.getGoodsSn()+"','" +	//--外部编码		不为空
								tOrderItem.getGoodsSn()+"'," +	//--商品数字编号
								"null,'" +						//--sku_id
								tOrderItem.getPrice()+"','" +	//--单价			不为空
								tOrderItem.getQuantity()+"','" +//--数量			不为空
								"0','" +						//--折扣金额		不为空
								"0','" +						//--调整金额		不为空
								""+(tOrderItem.getPrice().multiply(new BigDecimal(tOrderItem.getQuantity())))+"','" +						//--实收金额		不为空
								""+(tOrderItem.getPrice().multiply(new BigDecimal(tOrderItem.getQuantity())))+"','" +						//--实付金额		不为空
								(tOrderItem.getGoodsName()==null?"":tOrderItem.getGoodsName().replaceAll("'", "''"))+"'," +//--品名			不为空
								"null,'" +						//--sku值
								(tOrder.getIfInvoice())+"'," +	//--是否需要发票
								"null" +						//--备注
								""+
								" if @@ERROR<>0 " +
								"begin " +
								"rollback " +
								"raiserror('添加明细失败',16,1) " +
								"return " +
								"end ";
//						System.out.println("订单号为:["+tOrder.getOrderSn()+"]的订单项\t\t"+tOrderItem.getGoodsName()+"\r\n--->>>"+sql_c);
//						callStmt = connection.prepareCall(sql_c);
//						callStmt.execute();
					}
				}
				if("".equals(sql_c.trim())){
					logger.error("订单号为:"+tOrder.getOrderSn()+"的明细为空");
					return false;
				}
				
				String sqlQuery = sql_c+
						"exec p_ol_srcorder_io_m '"+
						tOrder.getOrderSn() + "','" + //--网店订单号		主键
						olshopid + "','" + //--网店编码			主键
						mendianId + "','" + //--网店客户id		不为空
						paytype+"','" + //--支付类型(1:在线;2:到付;3:自提)			不为空
						amountTotal+"','" + //--实付金额			不为空
						totalFee+"','" + //--实收金额			不为空
						zhekouAll+"','" + //--整单折扣金额		不为空
						0+"','" + //--调整金额			不为空
						tOrder.getShippingFee()+"','" + //--邮费				不为空
						"0','" + //--平台优惠			不为空
						sdf.format(tOrder.getCreateDate())+//--订单开始时间		不为空
						(payTime==null?"','"+sdf.format(new Date())+"','":"','"+ sdf.format(payTime)+"','") + //--付款时间
						sdf.format(new Date())+"'," + //--确认时间			不为空
						"null," + //--结束时间
						"null,'" + //--支付宝用户id
						zhifubaoLiushui+"','" + //--支付宝交易号
						(tMember.getRealName()==null?"":tMember.getRealName().replaceAll("'", "''"))+"','" + //--购买人姓名
						(tOrder.getReceiver()==null?"":tOrder.getReceiver().replaceAll("'", "''"))+"','" + //--收货人姓名		不为空
						province + "','" + //--收货地址_省		不为空
						city + "','" + //--收货地址_市		不为空
						area + "','" + //--收货地址_区		不为空
						(tOrder.getDetailedAddress() == null ? "" : tOrder.getDetailedAddress().replaceAll("'", "''"))+"','" + //--收货地址			不为空
						(tOrder.getZipCode() == null ? "" : tOrder.getZipCode().replaceAll("'", "''"))+"','" + //--邮编
						(tOrder.getPhone() == null ? "" : tOrder.getPhone().replaceAll("'", "''")+"','") + //--联系手机			不为空
						(tOrder.getPhone() == null ? "" : tOrder.getPhone().replaceAll("'", "''")+"',") + //--联系电话
						"null ,'" + //--线下会员号
						(tOrder.getRemark() == null?"":tOrder.getRemark().replaceAll("'", "''"))+"','" + //--购买备注
						(tMember.getEmail()==null?"":tMember.getEmail().replaceAll("'", "''"))+"'," + //--购买人邮箱
						"null ,'" + //--卖家留言
						_orderSource+"','" + //--订单来源
						peisong+"','" + //--快递企业名称
						(alipay_no)+"'," + //--交易类型(0 线上 1 线下)
						tOrder.getOrderStatus()+"," + //--网单状态
						"null,'" + //--发票类型
						(tOrder.getInvoiceTitle()==null?"":tOrder.getInvoiceTitle().replaceAll("'", "''"))+"','" + //--发票抬头
						fapiaoType+"'," + //--发票类型
						"null," + //--发票内容
						"null," + //--卖家货到付款服务费
						"null," + //--买家货到付款服务费
						"null,'" + //--交易佣金
						(tOrder.getRewardIntegration()==null?"":tOrder.getRewardIntegration())+"'," + //--积分
						"null," + //--运费险
						"null," + //--运费险支付号
						"null," + //--运费险类型
						"null,'" + //--送货类型
						tOrder.getUseIntegration()+"'," + //--买家实际使用积分
						"null," + //--信用卡支付金额
						"null," + //--买家备注旗帜
						"null," + //--卖家备注旗帜
						"'"+mendianId+"' " + //--电商作业点编码(门店编码)
						" if @@ERROR<>0 "+
						" begin "+
						"	rollback "+
						"	raiserror('添加订单失败',16,1) "+
						"	return "+
						" end ";
				
				System.out.println("订单号为:["+tOrder.getOrderSn()+"]的订单执行:\t\t"+sqlQuery);
				callStmt = connection.prepareCall(sqlQuery);
				callStmt.execute();
				connection.commit();
				stmt.close();
				connection.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("-----------订单号:【" + tOrder.getOrderSn()+"】执行插入海典数据异常<HDDAOImpl.insertDstOrder>" + e.getMessage());
				return false;
			} finally {
				if (stmt != null) {
					try {
						if (!stmt.isClosed()) {
							stmt.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (connection != null) {
					try {
						if (!connection.isClosed()) {
							connection.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public enum PayType {
		// 1:快钱支付;
		// 2:支付宝;
		// 3:手机支付宝;
		// 6:医卡通支付;
		// 8:微信支付;
		// 10:银联支付;
		// 11:易宝支付;
		// 12:财付通(担保交易);
		// 13:Paypal;
		// 20:闪白条;
		// 21:微信支付移动端;
		// 22:支付宝移动端;
		// 7:线下支付;

		kuaiqian(1), alipay(2), mobileAlipay(3), yikatong(6), weixin(8), yinlian(10), yibao(11), caifutong(12), Paypal(
				13), shanbaitiao(20), weixinyidong(21), alipayyidong(22), xianxia(7);
		private int index;

		private PayType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}
}
