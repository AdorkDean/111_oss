package com.rc.portal.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.util.StringUtils;

import com.rc.commons.util.InfoUtil;
import com.rc.portal.dao.CDeliveryWayDAO;
import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.RxTOrderDAO;
import com.rc.portal.dao.RxTOrderGoodDAO;
import com.rc.portal.dao.RxTReserveOrderDAO;
import com.rc.portal.dao.TGoodsDAO;
import com.rc.portal.hd.JingWeiDuUtil;
import com.rc.portal.hd.Order;
import com.rc.portal.hd.OrderDetail;
import com.rc.portal.service.RxTOrderManager;
import com.rc.portal.vo.CDeliveryWay;
import com.rc.portal.vo.RxTOrder;
import com.rc.portal.vo.RxTOrderExample;
import com.rc.portal.vo.RxTOrderGood;
import com.rc.portal.vo.RxTOrderGoodExample;
import com.rc.portal.vo.RxTReserveOrder;
import com.rc.portal.vo.TGoods;

public class RxTOrderManagerImpl  implements RxTOrderManager {

    private RxTOrderDAO rxtorderdao;
    private RxTOrderGoodDAO rxtordergooddao;
    private OpenSqlDAO opensqldao;
    private RxTReserveOrderDAO rxtreserveorderdao;
    
    private CDeliveryWayDAO cdeliverywaydao;
    private TGoodsDAO tgoodsdao;
    
    private String PUSH_URL = InfoUtil.getInstance().getInfo("application", "hd.http.push.url");
	private String OLSHOPID = InfoUtil.getInstance().getInfo("application", "hd.http.olshopid");
	
	
    public TGoodsDAO getTgoodsdao() {
		return tgoodsdao;
	}
	public void setTgoodsdao(TGoodsDAO tgoodsdao) {
		this.tgoodsdao = tgoodsdao;
	}
	public CDeliveryWayDAO getCdeliverywaydao() {
		return cdeliverywaydao;
	}
	public void setCdeliverywaydao(CDeliveryWayDAO cdeliverywaydao) {
		this.cdeliverywaydao = cdeliverywaydao;
	}
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}
	public RxTOrderGoodDAO getRxtordergooddao() {
		return rxtordergooddao;
	}
	public void setRxtordergooddao(RxTOrderGoodDAO rxtordergooddao) {
		this.rxtordergooddao = rxtordergooddao;
	}
	public RxTOrderManagerImpl() {
        super();
    }
    public void  setRxtorderdao(RxTOrderDAO rxtorderdao){
        this.rxtorderdao=rxtorderdao;
    }
    public RxTOrderDAO getRxtorderdao(){
        return this.rxtorderdao;
    }
    public     int countByExample(RxTOrderExample example) throws SQLException{
        return rxtorderdao. countByExample( example);
    }

    public     int deleteByExample(RxTOrderExample example) throws SQLException{
        return rxtorderdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return rxtorderdao. deleteByPrimaryKey( id);
    }

    public     Long insert(RxTOrder record) throws SQLException{
        return rxtorderdao. insert( record);
    }

    public     Long insertSelective(RxTOrder record) throws SQLException{
        return rxtorderdao. insertSelective( record);
    }

    public     List selectByExample(RxTOrderExample example) throws SQLException{
        return rxtorderdao. selectByExample( example);
    }

    public     RxTOrder selectByPrimaryKey(Long id) throws SQLException{
        return rxtorderdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(RxTOrder record, RxTOrderExample example) throws SQLException{
        return rxtorderdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(RxTOrder record, RxTOrderExample example) throws SQLException{
        return rxtorderdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(RxTOrder record) throws SQLException{
        return rxtorderdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(RxTOrder record) throws SQLException{
        return rxtorderdao. updateByPrimaryKey( record);
    }
    
	public void cancelOrder(RxTOrder order) throws Exception {
		RxTOrder rxOrder=new RxTOrder();
		rxOrder.setId(order.getId());
		rxOrder.setOrderStatus(2);
		rxtorderdao.updateByPrimaryKeySelective(rxOrder);
		
		RxTOrderGoodExample rge=new RxTOrderGoodExample();
		rge.createCriteria().andOrderIdEqualTo(order.getId());
		List<RxTOrderGood> orderGoodsList = rxtordergooddao.selectByExample(rge);
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		if(orderGoodsList!=null&&orderGoodsList.size()>0){
			for(RxTOrderGood goods:orderGoodsList){
				paramMap.clear();
				paramMap.put("id", goods.getGoodid());
				opensqldao.selectForListByMap_drug(paramMap, "t_goods.selectByPrimaryKeyForUpdate");
				paramMap.clear();
				paramMap.put("id", goods.getGoodid());
				paramMap.put("quantity", goods.getNum());
				opensqldao.updateByMap_drug(paramMap, "t_goods.updateGoodAddStockById");
			}
		}
	}
	
	public void finishOrder(RxTOrder order) throws Exception {
		order.setOrderStatus(4);
		rxtorderdao.updateByPrimaryKeySelective(order);
	}
	public void refuseOrder(RxTOrder order) throws SQLException {
		order.setOrderStatus(3);
		rxtorderdao.updateByPrimaryKeySelective(order);
	}

	public boolean createRxTOrder(long orderId,List<Map> orderList, String freight)
			throws Exception {
		boolean flag = true;
		try{
			RxTReserveOrder reserveOrder =rxtreserveorderdao.selectByPrimaryKey(orderId);
			RxTOrderGood rxgood = null;
			List<RxTOrderGood> goodList = new ArrayList<RxTOrderGood>();
			BigDecimal orderAmount = new BigDecimal("0");
			
			List<RxTReserveOrder> updateList = new ArrayList<RxTReserveOrder>();
			RxTReserveOrder  updateOrder = null;
			for(Map map :orderList){
				rxgood = new RxTOrderGood();
				rxgood.setReserveOrderId((Long)map.get("id"));
				rxgood.setGoodid((Long)map.get("goodid"));
				rxgood.setGoodPrice((BigDecimal)map.get("good_price"));
				rxgood.setNum((Integer)map.get("num"));
				rxgood.setMemberId((Long)map.get("member_id"));
				orderAmount = orderAmount.add(rxgood.getGoodPrice().multiply(new BigDecimal(rxgood.getNum())));
				goodList.add(rxgood);
				updateOrder = new RxTReserveOrder();
				updateOrder.setId(rxgood.getReserveOrderId());
				updateOrder.setOrderStatus(8);
				updateOrder.setIsEnd(1);
				updateList.add(updateOrder);
			}
			RxTOrder rxtorder = new RxTOrder();
			rxtorder.setOrderSn(rxtorderdao.getRxOrderSn(6));//订单号
			rxtorder.setMemberId(reserveOrder.getMemberId());
			rxtorder.setReceiver(reserveOrder.getReceiver());
			rxtorder.setAreaId(reserveOrder.getAreaId());
			rxtorder.setAreaName(reserveOrder.getAreaName());
			rxtorder.setDetailedAddress(reserveOrder.getDetailedAddress());
			rxtorder.setLongitude(reserveOrder.getLongitude());
			rxtorder.setLatitude(reserveOrder.getLatitude());
			rxtorder.setStoreId(reserveOrder.getStoreId());
			rxtorder.setMobile(reserveOrder.getMobile());
			rxtorder.setPaymentId(4L);//货到付款
			if(StringUtils.hasText(rxtorder.getStoreId())){
				rxtorder.setDeliveryId(2L);//药士达
			}else{
				rxtorder.setDeliveryId(3L);//货到付款
			}
			rxtorder.setOrderStatus(0);//已下单
			rxtorder.setCreateDt(new Date());//创建时间
			rxtorder.setFreight(new BigDecimal(freight));//运费
			rxtorder.setOrderAmount(orderAmount.add(rxtorder.getFreight()));//订单金额
			rxtorder.setPromotionalDiscount(new BigDecimal("0"));//促销折扣
			rxtorder.setCouponDiscount(new BigDecimal("0"));//优惠券折扣
			rxtorder.setIntegrationDiscount(new BigDecimal("0"));//积分折扣
			rxtorder.setUseIntegration(0);//使用积分
			rxtorder.setAdjustAmount(new BigDecimal("0"));//调整金额
			rxtorder.setRewardIntegration(0);//赠送积分
			rxtorder.setIsPush(0);//是否推送海典 0 否 1 是
			long id =this.rxtorderdao.insertSelective(rxtorder);
			for(RxTOrderGood rxgoodSave :goodList){
				rxgoodSave.setOrderId(id);
				this.rxtordergooddao.insertSelective(rxgoodSave);
			}
			for(RxTReserveOrder  updateOrdernew :updateList){
				updateOrdernew.setOrderId(id);
				this.rxtreserveorderdao.updateByPrimaryKeySelective(updateOrdernew);
			}
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
			throw new Exception();
		}
		return flag;
	}
	public RxTReserveOrderDAO getRxtreserveorderdao() {
		return rxtreserveorderdao;
	}
	public void setRxtreserveorderdao(RxTReserveOrderDAO rxtreserveorderdao) {
		this.rxtreserveorderdao = rxtreserveorderdao;
	}
	
	
	/**
	 * 手动推送海典
	 * @throws SQLException 
	 */
	public void pushRxOrder(Long orderid) throws SQLException, ClientProtocolException, IOException,Exception {
		if(orderid==null){
			return;
		}
		RxTOrder rxTOrder = rxtorderdao.selectByPrimaryKey(orderid);
		if(rxTOrder==null){
			throw new SQLException("订单为空");
		}
		RxTOrderGoodExample rxTOrderGoodExample = new RxTOrderGoodExample();
		rxTOrderGoodExample.createCriteria().andOrderIdEqualTo(rxTOrder.getId());
		List<RxTOrderGood> rxGoods = rxtordergooddao.selectByExample(rxTOrderGoodExample);
		if(rxTOrder !=null && rxGoods!=null && rxGoods.size()>0){
			//修改本地数据,保证本地数据修改成功,再请求第三方
			RxTOrder updateOrder = new RxTOrder();
			updateOrder.setIsPush(1);
			updateOrder.setPushTime(new Date());
			updateOrder.setId(rxTOrder.getId());
			rxtorderdao.updateByPrimaryKeySelective(updateOrder);
			//封装json
			String json = fengzhuangJson(rxTOrder,rxGoods);
			System.out.println("[手动推送]提交海典前封装("+rxTOrder.getOrderSn()+")json:\t\t"+json);
			//调用海典接口,insert
			callHdMethod(json,rxTOrder.getOrderSn());
		}else{
			throw new Exception("[手动推送]处方药订单:["+rxTOrder.getOrderSn()+"]没有订单项.");
		}
	}
	/**
	 * 调用海典推送接口
	 * @param json
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean callHdMethod(String json,String orderSn) throws ClientProtocolException, IOException,Exception{
		boolean flag = false;
		URL postUrl = new URL(PUSH_URL);
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection
                .getOutputStream());
        String content = "order=" + URLEncoder.encode(json, "UTF-8");
        out.writeBytes(content);
        out.flush();
        out.close(); 
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        String resultJson = "";
        while ((line = reader.readLine()) != null){
            resultJson += line;
        }
        reader.close();
        connection.disconnect();
		System.out.println("[手动推送]调用海典接口返回值:\t\t"+URLDecoder.decode(resultJson, "UTF-8"));
		String _result = URLDecoder.decode(resultJson, "UTF-8");
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONObject _jsonObject = JSONObject.fromObject(_result);
		Iterator<Object> it = _jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			Object key = it.next();
			Object value =  _jsonObject.get(key);
			map.put(key, value);
		}
		if(map.get("statusCode")!=null && map.get("statusCode").toString().equals("0")){
			System.out.println("[手动推送]海典返回成功");
			flag = true;
		}else{
			throw new Exception("[手动推送]推送海典异常,订单号【"+orderSn+"】,海典接口返回异常信息:"+map.get("msg"));
		}
		return flag;
	}
	
	/**
	 * 根据订单信息封装调用海典接口参数的json数据
	 * @param rxTOrder
	 * @param rxGoods
	 * @return
	 * @throws Exception 
	 */
	private String fengzhuangJson(RxTOrder rxTOrder,List<RxTOrderGood> rxGoods) throws Exception {
		BigDecimal totalFee = new BigDecimal(0);
		BigDecimal amountTotal = new BigDecimal(0);
		for (RxTOrderGood rxTOrderGood : rxGoods) {
			if(rxTOrderGood!=null && rxTOrderGood.getGoodPrice()!=null && rxTOrderGood
					.getNum()!=null){
				amountTotal = amountTotal.add(rxTOrderGood.getGoodPrice().multiply(
						new BigDecimal(rxTOrderGood.getNum())));
				totalFee = totalFee.add(rxTOrderGood.getGoodPrice().multiply(new BigDecimal(rxTOrderGood.getNum())));
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mendianId = "6007";
		if (rxTOrder != null && rxTOrder.getStoreId() != null && !"".equals(rxTOrder.getStoreId())) {
			mendianId = rxTOrder.getStoreId();
		} else if (rxTOrder != null && rxTOrder.getAreaId() == 3218L) {// 望京地区
			mendianId = "8134";
		} else if (rxTOrder != null && rxTOrder.getAreaId() == 3281L) {// 亦庄地区
			mendianId = "8262";
		}
		BigDecimal zhekouAll = new BigDecimal(0);//整单折扣
		BigDecimal cuxiaoZhekou = new BigDecimal(0);//促销折扣
		BigDecimal youhuiquanZhekou = new BigDecimal(0);//优惠券折扣
		//@payment = @total_fee - @discount_fee_sum + @adjust_fee + @post_fee + @buyer_cod_fee
//		计算公式说明
//		@total_fee = @num * @price
//		@payment = @total_fee - @discount_fee + @adjust_fee

		if (rxTOrder != null && rxTOrder.getFreight() != null) {// 运费
			amountTotal = amountTotal.add(rxTOrder.getFreight());
		}
		if (rxTOrder != null && rxTOrder.getPromotionalDiscount() != null) {// 促销折扣
			amountTotal = amountTotal.subtract(rxTOrder.getPromotionalDiscount());
			zhekouAll = zhekouAll.add(rxTOrder.getPromotionalDiscount());
			cuxiaoZhekou = cuxiaoZhekou.add(rxTOrder.getPromotionalDiscount());
		}
		if (rxTOrder != null && rxTOrder.getCouponDiscount() != null) {// 优惠券折扣
			amountTotal = amountTotal.subtract(rxTOrder.getCouponDiscount());
			zhekouAll = zhekouAll.add(rxTOrder.getCouponDiscount());
			youhuiquanZhekou = youhuiquanZhekou.add(rxTOrder.getPromotionalDiscount());
		}
		if (rxTOrder != null && rxTOrder.getIntegrationDiscount() != null) {// 积分抵扣
			amountTotal = amountTotal.subtract(rxTOrder.getIntegrationDiscount());
			zhekouAll = zhekouAll.add(rxTOrder.getIntegrationDiscount());
		}
		if (rxTOrder != null && rxTOrder.getAdjustAmount() != null) {// 调整金额
			amountTotal = amountTotal.add(rxTOrder.getAdjustAmount());
			//经过联调,确定为调整金额放在整单优惠金额里面去;
			zhekouAll = zhekouAll.subtract(rxTOrder.getAdjustAmount());
		}
		amountTotal = amountTotal.compareTo(new BigDecimal(0)) > 0 ? amountTotal : new BigDecimal(0);
		String[] shengshiqu = rxTOrder.getAreaName().split("-");
		if(shengshiqu.length<3){
			if (rxTOrder.getLatitude() != null && !"".equals(rxTOrder.getLatitude()) && rxTOrder.getLongitude() != null
					&& !"".equals(rxTOrder.getLongitude())) {
				String ssq = JingWeiDuUtil.getAreaShengShiQu(
						Double.valueOf(rxTOrder.getLongitude()),Double.valueOf(rxTOrder.getLatitude()));
				if(ssq==null||ssq.split("-").length!=3){
					throw new Exception("[手动推送]处方药收货地址省市区为空,单号:"+rxTOrder.getOrderSn());
				}else{
					shengshiqu = ssq.split("-");	
				}
			}
//			throw new Exception("[手动推送]处方药订单省市区有误,请确认,订单号["+rxTOrder.getOrderSn()+"]");
		}
		String peisong = "普通到付";
		//101:线上支付;102:货到付款;103:oto到付;104:处方到付
		String fukuanType = "处方到付";//FUKUANTYPE + "104";
		CDeliveryWay cDeliveryWay = (CDeliveryWay) cdeliverywaydao.selectByPrimaryKey(rxTOrder.getDeliveryId());
		if (cDeliveryWay.getDeliveryCode().equals("ysd") ) {
			fukuanType = "O2O到付";//FUKUANTYPE + "103";
			peisong = "药士达";
		}
		if (cDeliveryWay.getDeliveryCode().equals("ysdj") ) {
			fukuanType = "O2O到付";//FUKUANTYPE + "103";
			peisong = "药师到家";
		}
		
		String olshopid = OLSHOPID;
		Order hdOrder = new Order();
		hdOrder.setOlorderno(rxTOrder.getOrderSn());//订单号(不为空)
		hdOrder.setOlshopid(olshopid);//网店编码(不为空)(海典提供)
		hdOrder.setStatus(1);//状态0不处理；1待处理；2终止处理；3开始拣货；4已出库(不为空)
		hdOrder.setOlcusid(mendianId);//网店客户ID(不为空)
		hdOrder.setCreatetime(sdf.format(new Date()));//生成日期(不为空)当前时间
		hdOrder.setDiscount_fee("0");//折扣金额(不为空)
		hdOrder.setTotal_fee(totalFee.toString());//订单总额(不为空)
		hdOrder.setAdjust_fee(rxTOrder.getAdjustAmount() == null ? "0" : String.valueOf(rxTOrder.getAdjustAmount()
				.doubleValue()));// 调整金额(不为空)
		hdOrder.setPay_time(null);//付款时间(不为空) 
		hdOrder.setModified(rxTOrder.getCreateDt()==null?sdf.format(new Date()):sdf.format(rxTOrder.getCreateDt()));//修改时间(不为空) 
		hdOrder.setAlipay_id(null);//支付宝用户ID 
		hdOrder.setAlipay_no(null);//支付宝交易号
		hdOrder.setBuyer_nick(rxTOrder.getReceiver());//买家昵称(不为空)
		hdOrder.setReceiver_name(rxTOrder.getReceiver());//买家姓名(不为空)
		hdOrder.setReceiver_state(shengshiqu[0]);//买家省(不为空)
		hdOrder.setReceiver_city(shengshiqu[1]);//买家市(不为空)
		hdOrder.setReceiver_district(shengshiqu[2]);//买家区(不为空)
		hdOrder.setReceiver_address(rxTOrder.getDetailedAddress());//买家街道(不为空)
		hdOrder.setReceiver_zip(null);//买家邮编
		hdOrder.setReceiver_mobile(rxTOrder.getMobile());//买家电话1(不为空)
		hdOrder.setReceiver_phone(null);//买家电话2
		hdOrder.setBuyer_message(null);//买家备注
		hdOrder.setNotes(null);//备注
		hdOrder.setPost_fee(rxTOrder.getFreight()==null?"0":rxTOrder.getFreight().toString());//邮费(不为空)
		hdOrder.setBuyer_email(null);//买家邮箱
		hdOrder.setTrade_from(null);//订单来源
		hdOrder.setExpcmpname(peisong);//快递名称
		hdOrder.setPaytype("2");//付款类型(不为空),1:线上支付;2货到付款；3:O2O自提；
		hdOrder.setOl_status(rxTOrder.getOrderStatus()==null?"-1":rxTOrder.getOrderStatus().toString());//线上状态(不为空)即第三方系统中的状态
		hdOrder.setEccode("18");//平台编码(不为空)(海典提供) 
		hdOrder.setSeller_code_fee("0");//卖家货到付款服务费()
		hdOrder.setDiscount_fee_sum(cuxiaoZhekou.toString());//整单折扣金额(不为空)
		hdOrder.setPayment(amountTotal.toString());//实付金额(不为空)
		hdOrder.setInvoice_name(null);//发票抬头
		hdOrder.setInvoice_type(null);//发票内容类型
		hdOrder.setBuyer_cod_fee("0");//买家货到付款服务费
		hdOrder.setDiscount_fee_dtl("0");//明细折扣合计(不为空)
		hdOrder.setBuyer_obtain_point_fee("0");//买家获得积分
		hdOrder.setPayway(fukuanType);//付款方式(不为空),第三方系统支付方式（ID或名称均可）
		hdOrder.setYfx_fee("0");//运费险金额 
		hdOrder.setYfx_id(null);//运费险交易ID
		hdOrder.setYfx_type(null);//运费险类型  海典联调临时添加值
		hdOrder.setDelivery_type(null);//送货类型
		hdOrder.setAvailable_confirm_fee("0");//剩余确认收货金额
		hdOrder.setReal_point_fee("0");//买家实际使用积分
		hdOrder.setCommission("0");//佣金比例
		hdOrder.setCommission_fee("0");//交易佣金
		hdOrder.setBuyer_cod("0");//货到付款服务费比率
		hdOrder.setConfirm_time(sdf.format(new Date()));//确认时间(不为空);
		hdOrder.setOrder_start_time(rxTOrder.getCreateDt()==null?sdf.format(new Date()):sdf.format(rxTOrder.getCreateDt()));//订单开始时间(不为空)//
		hdOrder.setSeller_cod("0");//卖家货到付款服务费比例
		hdOrder.setCredit_card_fee("0");//信用卡支付金额
		hdOrder.setEnd_time(null);//结束时间(不为空)//
		hdOrder.setSeller_memo(null);//卖家留言;
		hdOrder.setInvoice_kind(null);//发票类型
		hdOrder.setOlbusno(mendianId);//作业点ID(不为空)(海典提供)
		hdOrder.setInvoice_contect(null);//发票内容
		hdOrder.setDiscount_fee_eccode(youhuiquanZhekou.toString());//平台优惠金额
		hdOrder.setBill_type(null);//单据类型
		hdOrder.setRx_audit_status("1");//处方药状态;0取消，1 审核通过；2，审核不通过
		hdOrder.setHavecfy("1");///是否是处方药,1，是 ；0，否
		hdOrder.setGroupid("1001");//海典提供;
		
		List<OrderDetail> itemList = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = null;
		Map<String, OrderDetail> goodsNoMap = new HashMap<String, OrderDetail>();
		TGoods tGoods = null;
		for (RxTOrderGood item : rxGoods) {
			tGoods = tgoodsdao.selectByPrimaryKey(item.getGoodid());
			if(goodsNoMap.get(tGoods.getGoodsno())==null && item.getGoodPrice()!=null&&item.getNum()!=null
					&& item.getGoodid()!=null){//没重复的,直接处理
				orderDetail = new OrderDetail();
				orderDetail.setOlorderno(rxTOrder.getOrderSn());//订单号(不为空)
				orderDetail.setOid(item.getId().toString());//子订单编号(不为空)(海典提供)
				orderDetail.setNeedinvoice(null);//是否需要发票
				orderDetail.setPrice(item.getGoodPrice().toString());//单价(不为空);
				orderDetail.setNum_iid(item.getGoodid().toString());//商品数字ID(不为空),第三方系统中的商品ID
				orderDetail.setItem_meal_id(null);//套餐ID(不为空)
				orderDetail.setNum(item.getNum().toString());//数量(不为空)
				orderDetail.setTotal_fee(item.getGoodPrice().multiply(new BigDecimal(item.getNum())).toString());//总金额(不为空)
				orderDetail.setPayment(item.getGoodPrice().multiply(new BigDecimal(item.getNum())).toString());//实付金额(不为空)
				orderDetail.setDiscount_fee("0");//折扣金额(不为空)
				orderDetail.setAdjust_fee("0");//调整金额(不为空)
				orderDetail.setModified(sdf.format(new Date()));//修改时间(不为空)
				orderDetail.setNotes(null);//备注
				orderDetail.setTwarename(tGoods==null?null:tGoods.getGoodsName());//商品名称(不为空)
				orderDetail.setEccode("18");//平台编码(不为空)(海典提供)
				orderDetail.setOlshopid(olshopid);//作业点(不为空)(海典提供)
				orderDetail.setSku_id(null);//SKUID
				orderDetail.setOuter_iid(tGoods.getGoodsno().toString());//外部编码(不为空)对应海典系统中的商品ID
				goodsNoMap.put(tGoods.getGoodsno(), orderDetail);
			} else {// 有重复的,处理;
				OrderDetail orderDetail2 = goodsNoMap.get(tGoods.getGoodsno());
				orderDetail2.setNum(orderDetail2.getNum()==null?item.getNum().toString():String.valueOf(Integer.valueOf(orderDetail2.getNum())+item.getNum()));
				orderDetail2.setTotal_fee(((new BigDecimal(orderDetail2.getTotal_fee())).add(item.getGoodPrice().multiply(new BigDecimal(item.getNum())))).toString());
				orderDetail2.setPayment(((new BigDecimal(orderDetail2.getPayment())).add(item.getGoodPrice().multiply(new BigDecimal(item.getNum())))).toString());
				goodsNoMap.remove(tGoods.getGoodsno());
				goodsNoMap.put(tGoods.getGoodsno(), orderDetail2);
			}
		}
		for (String key : goodsNoMap.keySet()) {
			itemList.add(goodsNoMap.get(key));
		}
		if(itemList==null||itemList.size()<=0){
			throw new Exception("[手动推送]处方药订单项有误,主订单号:["+rxTOrder.getOrderSn()+"]");
		}
		hdOrder.setOrderDetail(itemList);//明细
		JSONObject jsonObject = JSONObject.fromObject(hdOrder);
		return jsonObject.toString();
	}

}
