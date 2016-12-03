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
import com.rc.dst.client.util.ClientSubmitPublic;
import com.rc.portal.dao.CDeliveryWayDAO;
import com.rc.portal.dao.CLocationDAO;
import com.rc.portal.dao.CPaymentWayDAO;
import com.rc.portal.dao.HydeeDao;
import com.rc.portal.dao.OpenSqlDAO;
import com.rc.portal.dao.TGoodsBrokerageDAO;
import com.rc.portal.dao.TGoodsDAO;
import com.rc.portal.dao.TGoodsPriceDAO;
import com.rc.portal.dao.TMemberDAO;
import com.rc.portal.dao.TMemberIntegralDAO;
import com.rc.portal.dao.TMemberLeaderDAO;
import com.rc.portal.dao.TMemberReceiverLatLonDAO;
import com.rc.portal.dao.TOrderDAO;
import com.rc.portal.dao.TOrderFlowDAO;
import com.rc.portal.dao.TOrderItemDAO;
import com.rc.portal.dao.TOrderLeaderDAO;
import com.rc.portal.dao.TOrderShipmentDAO;
import com.rc.portal.dao.TReturnDAO;
import com.rc.portal.dao.TReturnItemDAO;
import com.rc.portal.dao.TShortOrderDAO;
import com.rc.portal.hd.JingWeiDuUtil;
import com.rc.portal.hd.Order;
import com.rc.portal.hd.OrderDetail;
import com.rc.portal.jms.MessageSender;
import com.rc.portal.service.TOrderManager;
import com.rc.portal.vo.CDeliveryWay;
import com.rc.portal.vo.CLocation;
import com.rc.portal.vo.CPaymentWay;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsBrokerage;
import com.rc.portal.vo.TGoodsBrokerageExample;
import com.rc.portal.vo.TGoodsPrice;
import com.rc.portal.vo.TGoodsPriceExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberIntegral;
import com.rc.portal.vo.TMemberReceiverLatLon;
import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TOrderExample;
import com.rc.portal.vo.TOrderFlow;
import com.rc.portal.vo.TOrderFlowExample;
import com.rc.portal.vo.TOrderItem;
import com.rc.portal.vo.TOrderItemExample;
import com.rc.portal.vo.TOrderShipment;
import com.rc.portal.vo.TReturn;
import com.rc.portal.vo.TReturnItem;
import com.rc.portal.vo.TShortOrder;
import com.rc.portal.vo.TShortOrderExample;

public class TOrderManagerImpl  implements TOrderManager {

	private TOrderDAO torderdao;

	private CDeliveryWayDAO cdeliverywaydao;

	private TGoodsDAO tgoodsdao;

	private OpenSqlDAO opensqldao;

	private TMemberReceiverLatLonDAO tmemberreceiverlatlondao;

	private TOrderItemDAO torderitemdao;

	private CLocationDAO clocationdao;

	private TOrderFlowDAO torderflowdao;
	private TOrderShipmentDAO tordershipmentdao;
	private CPaymentWayDAO cpaymentwaydao;

	private TReturnDAO treturndao;
    private TReturnItemDAO treturnitemdao;
    private TShortOrderDAO tshortorderdao;
    
    private TMemberDAO tmemberdao;

    private TMemberIntegralDAO tmemberintegraldao;
    
    private TOrderLeaderDAO torderleaderdao;
    
    private TMemberLeaderDAO tmemberleaderdao;
    private TGoodsBrokerageDAO tgoodsbrokeragedao;
    
    private HydeeDao hydeeDao;
    
    private MessageSender topicMessageSender;
    
    private String PUSH_URL = InfoUtil.getInstance().getInfo("application", "hd.http.push.url");
	
	private String OLSHOPID = InfoUtil.getInstance().getInfo("application", "hd.http.olshopid");

	private String FUKUANTYPE = InfoUtil.getInstance().getInfo("application", "hd.http.fukuan_type");

	 private TGoodsPriceDAO tgoodspricedao;
    public TOrderManagerImpl() {
        super();
    }
    public void  setTorderdao(TOrderDAO torderdao){
        this.torderdao=torderdao;
    }
    public TOrderDAO getTorderdao(){
        return this.torderdao;
    }
    public     int countByExample(TOrderExample example) throws SQLException{
        return torderdao. countByExample( example);
    }

    public     int deleteByExample(TOrderExample example) throws SQLException{
        return torderdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return torderdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TOrder record) throws SQLException{
        return torderdao. insert( record);
    }

    public     Long insertSelective(TOrder record) throws SQLException{
        return torderdao. insertSelective( record);
    }

    public     List selectByExample(TOrderExample example) throws SQLException{
        return torderdao. selectByExample( example);
    }

    public     TOrder selectByPrimaryKey(Long id) throws SQLException{
        return torderdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TOrder record, TOrderExample example) throws SQLException{
        return torderdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TOrder record, TOrderExample example) throws SQLException{
        return torderdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TOrder record) throws SQLException{
        return torderdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TOrder record) throws SQLException{
        return torderdao. updateByPrimaryKey( record);
    }
	public Map<String, Object> orderGoodsPrice(String goodsids,String goodsSums,String deliveryId) throws SQLException {
		    BigDecimal  shippingFee = new BigDecimal("0"); //运费
	        BigDecimal  promotionalDiscount = new BigDecimal("0");//优惠折扣金额
	        BigDecimal  payableAmount = new BigDecimal("0");//应付金额
	        BigDecimal  goodsAmount = new BigDecimal("0");//商品金额
			Map<String,Object> resultMap = new HashMap();
			List<Map<String,Object>> goodMapList = new ArrayList<Map<String,Object>>();
			try{
			BigDecimal weightSum = new BigDecimal("0");//总重量
			if(StringUtils.hasText(goodsids)&&StringUtils.hasText(goodsSums)){
				goodsids = goodsids.substring(0, goodsids.length()-1);
				goodsSums = goodsSums.substring(0, goodsSums.length()-1);
				String[] ids = goodsids.split(",");
				String[] sums = goodsSums.split(",");
				TGoods good =null;
				Map<String,Object> goodMap = null;
				BigDecimal goodSumPrice = null;
				TGoodsPriceExample priceExample = null;
				for(int i=0;i<ids.length;i++){
					if(StringUtils.hasText(sums[i])){
						good = this.tgoodsdao.selectByPrimaryKey(Long.valueOf(ids[i].trim()));
						goodMap = new HashMap<String,Object>();
						goodMap.put("goodid", good.getId());
						priceExample = new TGoodsPriceExample();
						priceExample.createCriteria().andGoodsIdEqualTo(good.getId()).andPriceTypeEqualTo("pc").andPlatformTypeEqualTo("111yao");
						List<TGoodsPrice> priceList =tgoodspricedao.selectByExample(priceExample);
						if(priceList!=null&&priceList.size()>0){
							goodSumPrice = priceList.get(0).getPrice().multiply(new BigDecimal(sums[i].trim()));
						}
						goodsAmount = goodsAmount.add(goodSumPrice);
						goodMap.put("goodSumPrice", goodSumPrice);
						if(priceList!=null&&priceList.size()>0){
							goodMap.put("goodPcprice", priceList.get(0).getPrice());
						}else{
							goodMap.put("goodPcprice", new BigDecimal("0"));
						}
						
						goodMapList.add(goodMap);
						weightSum = weightSum.add((good.getWeight()==null?new BigDecimal("0"):good.getWeight()).multiply(new BigDecimal(sums[i].trim())));
					}
				}
			}
			if(StringUtils.hasText(deliveryId)){//判断配送方式
				CDeliveryWay delivery =cdeliverywaydao.selectByPrimaryKey(Long.parseLong(deliveryId.trim()));
				if((delivery.getIsFree()==null)||(delivery.getIsFree()!=null&&delivery.getIsFree().intValue()==0)){//不免邮费
					shippingFee =delivery.calculateFreight(weightSum);
				}
			}
			//这里需要计算 promotionalDiscount 优惠金额
	      		
			}catch(Exception e){
				e.printStackTrace();
			}
			resultMap.put("shippingFee", shippingFee);
			resultMap.put("promotionalDiscount", promotionalDiscount);
			resultMap.put("orderAmount", goodsAmount.add(shippingFee));
			resultMap.put("payableAmount", goodsAmount.add(shippingFee).subtract(promotionalDiscount));
			resultMap.put("goodMapList", goodMapList);
		return resultMap;
	}
	/**
	 * 获取订单号
	 * @return
	 */
	public String getOrderSn(){
		   Map<String,Object> paramMap = new HashMap<String,Object>();
		   paramMap.put("len", 6);
		   String ordersn =(String) opensqldao.selectForObjectByMap(paramMap, "t_order.select_order_sn_call");
		   if("-1".equals(ordersn)){
			  return getOrderSn();
		   }else{
			   return ordersn;
		   }
	}
	
	
	public long saveOrder(TOrder order, String goodsids, String goodsSums,String receiverId)
			throws SQLException {
		long orderId = 0;
		try{
			//计算各种运费
			Map<String,Object> resultMap = orderGoodsPrice(goodsids, goodsSums, String.valueOf(order.getDeliveryId()));
			 String orderSn =getOrderSn();
			 order.setOrderSn(orderSn);//订单号
			 order.setOrderSource(5);//订单来源 1 pc 2 wap 3 安卓 4 ios 5 手动下单
//			 order.setShippingFee((BigDecimal)resultMap.get("shippingFee"));//运费
			 order.setPromotionalDiscount((BigDecimal)resultMap.get("promotionalDiscount"));//促销折扣
			 order.setCouponDiscount(new BigDecimal("0"));//优惠券折扣
			 order.setIntegrationDiscount(new BigDecimal("0"));//积分折扣
			 order.setUseIntegration(0);//使用积分
			 order.setAdjustAmount(new BigDecimal("0"));//调整金额
			 order.setPaidAmount(new BigDecimal("0"));//已付金额
			 order.setRewardIntegration(0);//赠送积分
			  //查询收货地址
			 TMemberReceiverLatLon  memberReceiverLatLon =tmemberreceiverlatlondao.selectByPrimaryKey(Long.valueOf(receiverId.trim()));
			 if(memberReceiverLatLon!=null){
				 order.setReceiver(memberReceiverLatLon.getReceiver());//收货人 
				 order.setAreaId(memberReceiverLatLon.getAreaId());//地区id
				 order.setAreaName(memberReceiverLatLon.getArea());//地区名称
				 order.setDetailedAddress(memberReceiverLatLon.getLocationAddress()+memberReceiverLatLon.getAddress());//详细地址
				 order.setZipCode(memberReceiverLatLon.getZipCode());//邮编
				 order.setPhone(memberReceiverLatLon.getMobile());//联系电话
				 order.setLongitude(memberReceiverLatLon.getLongitude());
				 order.setLatitude(memberReceiverLatLon.getLatitude());
				 order.setStoreId(memberReceiverLatLon.getStoreId());
			 }
			 if(order.getInvoiceType()!=null&&order.getInvoiceType()>0){
				 order.setIfInvoice(1);
			 }else{
				 order.setIfInvoice(0);
				 order.setInvoiceTitle("");
			 }
			 order.setIsPush(0);//是否推送海典 0 否 1 是
			 order.setPushTime(null);//推送海淀时间
			 order.setDeleteStatus(0);//删除状态 0 未删除 1 已删除
			 order.setOrderStatus(1);//订单状态 待发货
			 order.setOrderType(1);//订单类型  1-正常 2-秒杀 3-团购
			 order.setOrderAmount(((BigDecimal)resultMap.get("orderAmount")).subtract((BigDecimal)resultMap.get("shippingFee")).add(order.getShippingFee()));//订单金额
			 order.setPayableAmount(((BigDecimal)resultMap.get("payableAmount")).subtract((BigDecimal)resultMap.get("shippingFee")).add(order.getShippingFee()));//应付金额
			 order.setReturnType(1);//返佣状态 1 未返佣  2 已返佣
			 order.setCreateDate(new Date());//下单时间
			 order.setVerifyStatus(1);//核实状态  0 未核实  1 已核实
			 //保存订单
			 orderId =this.torderdao.insertSelective(order);
			 Map<String,Object> goodMap = null;
			 Map<String, Object> paramMap = new HashMap<String, Object>();
			 if(StringUtils.hasText(goodsids)&&StringUtils.hasText(goodsSums)){
					goodsids = goodsids.substring(0, goodsids.length()-1);
					goodsSums = goodsSums.substring(0, goodsSums.length()-1);
					String[] ids = goodsids.split(",");
					String[] sums = goodsSums.split(",");
					TOrderItem orderItem = null;
					TGoodsBrokerageExample brokerageExample = null;
					TGoodsPriceExample priceExample = null;
					for(int i=0;i<ids.length;i++){
						if(StringUtils.hasText(sums[i])){
							 paramMap.clear();
							paramMap.put("id", Long.valueOf(ids[i].trim()));
							goodMap = (Map<String, Object>) this.opensqldao.selectForObjectByMap(paramMap, "t_goods.selectByPrimaryKeyForUpdate");
							orderItem = new TOrderItem();
							orderItem.setOrderId(orderId);
							orderItem.setCreateTime(new Date());
							orderItem.setGoodsId((Long)goodMap.get("id"));
							priceExample = new TGoodsPriceExample();
							priceExample.createCriteria().andGoodsIdEqualTo(Long.valueOf(ids[i].trim())).andPriceTypeEqualTo("pc").andPlatformTypeEqualTo("111yao");
							List<TGoodsPrice> priceList =tgoodspricedao.selectByExample(priceExample);
							if(priceList!=null&&priceList.size()>0){
								orderItem.setPrice(priceList.get(0).getPrice());
							}else{
								orderItem.setPrice(new BigDecimal("0"));
							}
							orderItem.setIfPremiums(0);
							orderItem.setIfReviews(0);
							orderItem.setQuantity(Integer.parseInt(sums[i].trim()));
							
							brokerageExample = new TGoodsBrokerageExample();
							 brokerageExample.createCriteria().andGoodsIdEqualTo(orderItem.getGoodsId());
							 List<TGoodsBrokerage> brokerageList= tgoodsbrokeragedao.selectByExample(brokerageExample);
							 if(brokerageList!=null&&brokerageList.size()>0){
								 orderItem.setBrokerage(brokerageList.get(0).getBrokerage());
							 }else{
								 orderItem.setBrokerage(Long.valueOf(InfoUtil.getInstance().getInfo("config", "good_yongjin_lilv").trim()));
							 }
							
							this.torderitemdao.insertSelective(orderItem);
							paramMap.clear();
						    paramMap.put("id",  Long.valueOf(ids[i].trim()));
						    paramMap.put("quantity",  Long.valueOf(sums[i].trim()));
						    this.opensqldao.updateByMap_drug(paramMap, "t_goods.updateGoodStockById");
						}
					}
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return orderId;
	}
	
	public CDeliveryWayDAO getCdeliverywaydao() {
		return cdeliverywaydao;
	}
	public void setCdeliverywaydao(CDeliveryWayDAO cdeliverywaydao) {
		this.cdeliverywaydao = cdeliverywaydao;
	}
	public TGoodsDAO getTgoodsdao() {
		return tgoodsdao;
	}
	public void setTgoodsdao(TGoodsDAO tgoodsdao) {
		this.tgoodsdao = tgoodsdao;
	}
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}
	
	public TMemberReceiverLatLonDAO getTmemberreceiverlatlondao() {
		return tmemberreceiverlatlondao;
	}
	public void setTmemberreceiverlatlondao(
			TMemberReceiverLatLonDAO tmemberreceiverlatlondao) {
		this.tmemberreceiverlatlondao = tmemberreceiverlatlondao;
	}
	public TOrderItemDAO getTorderitemdao() {
		return torderitemdao;
	}
	public void setTorderitemdao(TOrderItemDAO torderitemdao) {
		this.torderitemdao = torderitemdao;
	}
	@Override
	public void updateOrder(TOrder order) throws SQLException {
		
		TOrder orderold = this.torderdao.selectByPrimaryKey(order.getId());
		TOrder ordernew = new TOrder();
		ordernew.setId(order.getId());//
		ordernew.setAdjustAmount(order.getAdjustAmount());
		BigDecimal orderAmount =orderold.getOrderAmount().subtract(orderold.getAdjustAmount()).add(order.getAdjustAmount());
		BigDecimal payableAmount=orderold.getPayableAmount().subtract(orderold.getAdjustAmount()).add(order.getAdjustAmount());
		ordernew.setOrderAmount(orderAmount.compareTo(new BigDecimal("0"))>=0?orderAmount:new BigDecimal("0"));
		ordernew.setPayableAmount(payableAmount.compareTo(new BigDecimal("0"))>=0?payableAmount:new BigDecimal("0"));
		ordernew.setReceiver(order.getReceiver());//收货人 
//		ordernew.setAreaId(order.getAreaId());//地区id
//		ordernew.setAreaName(selectAreaName(order.getAreaId()));//地区名称
//		ordernew.setDetailedAddress(order.getDetailedAddress());//详细地址
//		ordernew.setZipCode(order.getZipCode());//邮编
		ordernew.setPhone(order.getPhone());//联系电话
		ordernew.setRemark(order.getRemark());
		ordernew.setInvoiceType(order.getInvoiceType());
		ordernew.setRewardIntegration(ordernew.getPayableAmount().intValue()/2);//修改赠送积分
		ordernew.setInvoiceTitle(order.getInvoiceTitle());
		if(order.getInvoiceType()!=null&&order.getInvoiceType()>0){
			ordernew.setIfInvoice(1);
		 }else{
			 ordernew.setIfInvoice(0);
			 ordernew.setInvoiceTitle("");
		 }
		this.torderdao.updateByPrimaryKeySelective(ordernew);
	}
	
	public String selectAreaName(long id) throws SQLException {
		String allAreaName ="";
		List<CLocation> locationList = new ArrayList<CLocation>();
		locationList =selectAllArea(id,locationList);
		for (int i = locationList.size()-1; i >=0; i--) {
			allAreaName=allAreaName+locationList.get(i).getName()+"-";
		}
		if(StringUtils.hasText(allAreaName)){
			allAreaName = allAreaName.substring(0,allAreaName.length()-1);
		}
		return allAreaName;
	}

	public List<CLocation> selectAllArea(long id,List<CLocation> locationList) throws SQLException{
		CLocation location = this.clocationdao.selectByPrimaryKey(Integer.valueOf(String.valueOf(id)));
		locationList.add(location);
		if(location.getParentid()!=-1){
			selectAllArea(location.getParentid(),locationList);
		}else{
			return locationList;
		}
		return locationList;
	}
	public CLocationDAO getClocationdao() {
		return clocationdao;
	}
	public void setClocationdao(CLocationDAO clocationdao) {
		this.clocationdao = clocationdao;
	}
	@Override
	public void payOrder(long orderid,long orderFlowId) throws SQLException {
		
		TOrder order = new TOrder();
		order.setId(orderid);
		order.setOrderStatus(1);//待发货
		this.torderdao.updateByPrimaryKeySelective(order);
		
		TOrderFlow orderFlow = new TOrderFlow();
		
		orderFlow.setId(orderFlowId);
		orderFlow.setPayDate(new Date());
		orderFlow.setPaymentStatus(1);//已支付
		this.torderflowdao.updateByPrimaryKeySelective(orderFlow);
	}
	
	/**
	 * 订单发货
	 */
	public void shipmentOrder(long orderid, TOrderShipment orderShipment)
			throws SQLException {
		orderShipment.setOrderId(orderid);
		orderShipment.setOrderType(0);
		orderShipment.setShipmentDt(new Date());
		tordershipmentdao.insertSelective(orderShipment);
		
		TOrder order = new TOrder();
		order.setId(orderid);
		order.setOrderStatus(2);//待收货
		this.torderdao.updateByPrimaryKeySelective(order);
	}
	/**
	 * 确认收获
	 */
	public void finishOrder(TOrder order,String publicServiceUrl) throws Exception {
		TOrder torder = new TOrder();
		torder.setId(order.getId());
		torder.setOrderStatus(3);//已完成
		torder.setFinishDate(new Date());
		this.torderdao.updateByPrimaryKeySelective(torder);
		long memberId = order.getMemberId();
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", String.valueOf(memberId));
		map.put("jifen", String.valueOf(order.getRewardIntegration()));
		map.put("version", "v7");
		topicMessageSender.sendMapMessage(map);
		
		TMemberIntegral memberIntegral = new TMemberIntegral();
		memberIntegral.setMemberId(memberId);
		memberIntegral.setCreateDate(new Date());
		memberIntegral.setSource(9);
		memberIntegral.setIntegral(order.getRewardIntegration());
		tmemberintegraldao.insertSelective(memberIntegral);
		
		if (order.getPaymentId() != null && order.getPaymentId() > 0L) {
			CPaymentWay _cPaymentWay = cpaymentwaydao.selectByPrimaryKey(order.getPaymentId());
			if (_cPaymentWay != null && _cPaymentWay.getPaymentCode() != null && order.getMemberId() != null) {
				Map<String,String> publicMap = new HashMap<String,String>();
				publicMap.put("orderId", String.valueOf(order.getId()));
				String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"leaderStayMoneyURLService");
				if(JSONObject.fromObject(resultJsonstr).get("statsCode").equals("0")){
					throw new Exception(String.valueOf(order.getId())+":佣金计算失败,失败原因:"+JSONObject.fromObject(resultJsonstr).get("message"));
				}
			}
		}
	}
	public TOrderFlowDAO getTorderflowdao() {
		return torderflowdao;
	}
	public void setTorderflowdao(TOrderFlowDAO torderflowdao) {
		this.torderflowdao = torderflowdao;
	}
	public TOrderShipmentDAO getTordershipmentdao() {
		return tordershipmentdao;
	}
	public void setTordershipmentdao(TOrderShipmentDAO tordershipmentdao) {
		this.tordershipmentdao = tordershipmentdao;
	}
	/**
	 * 取消订单
	 */
	public void cancelOrder(TOrder orderold) throws Exception {
		
		TOrder order = new TOrder();
		order.setId(orderold.getId());
		order.setOrderStatus(4);
		this.torderdao.updateByPrimaryKeySelective(order);
		
		TOrderItemExample orderItemExample = new TOrderItemExample();
		orderItemExample.createCriteria().andOrderIdEqualTo(orderold.getId());
		List<TOrderItem> orderItemList =  this.torderitemdao.selectByExample(orderItemExample);
		if(orderItemList!=null&&orderItemList.size()>0){
			 Map<String, Object> paramMap = new HashMap<String, Object>();
			for(TOrderItem orderItem:orderItemList){
				paramMap.clear();
				paramMap.put("id", orderItem.getGoodsId());
				this.opensqldao.selectForObjectByMap(paramMap, "t_goods.selectByPrimaryKeyForUpdate");
				paramMap.clear();
				paramMap.put("id", orderItem.getGoodsId());
				paramMap.put("quantity", orderItem.getQuantity());
				this.opensqldao.updateByMap_drug(paramMap, "t_goods.updateGoodAddStockById");
			}
			
		}
		if(orderold.getOrderType()!=null&&orderold.getOrderType().intValue()==2){//秒杀
			TShortOrderExample shortOrderExample = new TShortOrderExample();
			shortOrderExample.createCriteria().andOrderIdEqualTo(orderold.getId()).andMemberIdEqualTo(orderold.getMemberId());
			List<TShortOrder> shortOrderList =this.tshortorderdao.selectByExample(shortOrderExample);
			if(shortOrderList!=null&&shortOrderList.size()>0){
				TShortOrder shortOrder = shortOrderList.get(0);
				shortOrder.setStatus(1);
				shortOrder.setOrderId(null);
				this.tshortorderdao.updateByPrimaryKey(shortOrder);
			}
		}
		
		//退换货
		TReturn tReturn = new TReturn();
		if((orderold.getOrderStatus().intValue()==1||orderold.getOrderStatus().intValue()==2||orderold.getOrderStatus().intValue()==3) && orderold.getPaymentId() != 4){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("len", 6);
			String returnOrderSn = (String) opensqldao.selectForObjectByMap(paramMap,
					"t_return.select_new_return_ordersn");
			while (returnOrderSn == null || "".equals(returnOrderSn) || "-1".equals(returnOrderSn)) {
				returnOrderSn = (String) opensqldao.selectForObjectByMap(paramMap, "t_return.select_new_return_ordersn");
			}
			tReturn.setOrderSn(returnOrderSn);
			if(orderold.getOrderStatus().intValue()==2||orderold.getOrderStatus().intValue()==3){
				tReturn.setServiceType(0);
			}else if(orderold.getOrderStatus().intValue()==1){
				tReturn.setServiceType(2);
			}
			tReturn.setShipperPhone(orderold.getPhone());
			tReturn.setMemberId(orderold.getMemberId());
			tReturn.setRefundDescribe(orderold.getOrderSn()+"取消订单");
			tReturn.setOrderStatus(0);
			tReturn.setCreateTime(new Date());
			tReturn.setOldOrderId(orderold.getId());
			CPaymentWay paymentWay =cpaymentwaydao.selectByPrimaryKey(orderold.getPaymentId());
			if(paymentWay.getPaymentWay().intValue()==0){//线上
				tReturn.setRefundAmount(orderold.getPayableAmount());
			}else if(paymentWay.getPaymentWay().intValue()==1){//线下
				tReturn.setRefundAmount(new BigDecimal("0"));
			}
			Long returnId=this.treturndao.insertSelective(tReturn);
			TReturnItem returnItem = null;
			for(TOrderItem orderItem:orderItemList){
				returnItem = new TReturnItem();
				returnItem.setCreateTime(new Date());
				returnItem.setGoodsId(orderItem.getGoodsId());
				returnItem.setProductAmount(orderItem.getPrice());
				returnItem.setQuantity(orderItem.getQuantity());
				returnItem.setReturnId(returnId);
				this.treturnitemdao.insertSelective(returnItem);
			}
		}
	}
	
	/**
	 * 推送海典(通过接口调用)
	 */
	public void pushOrderByHttp(TOrder tOrder,TMember tMember) throws SQLException, ClientProtocolException, IOException,Exception {
		//tOrder符合推送海典
				//1:修改本地tOrder状态;
		tOrder.setIsPush(1);// 更新
		tOrder.setPushTime(new Date());
//		tOrder.setOrderStatus(2);// 待收货
		torderdao.updateByPrimaryKeySelective(tOrder);
		//2:封装参数,torder数据
		String json = fengzhuangJson(tOrder,tMember);
		
		callHdMethod(json,tOrder.getOrderSn());
		
	}
	
	
	
	/**
	 * 调用海典推送接口
	 * @param json
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean callHdMethod(String json,String orderSn) throws ClientProtocolException, IOException,Exception {
		boolean flag = false;
		URL postUrl = new URL(PUSH_URL);
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		String content = "order=" + URLEncoder.encode(json, "UTF-8");
		out.writeBytes(content);
		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		String resultJson = "";
		while ((line = reader.readLine()) != null) {
			resultJson += line;
		}
		reader.close();
		connection.disconnect();
		System.out.println("[手动推送]调用海典接口返回值:\t\t" + URLDecoder.decode(resultJson, "UTF-8"));
		String _result = URLDecoder.decode(resultJson, "UTF-8");
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONObject _jsonObject = JSONObject.fromObject(_result);
		Iterator<Object> it = _jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			Object key = it.next();
			Object value = _jsonObject.get(key);
			map.put(key, value);
		}
		if(map.get("statusCode")!=null && map.get("statusCode").toString().equals("0")){
			System.out.println("[手动推送]海典返回成功");
		}else{
			throw new Exception("[手动推送]推送海典异常,订单号【"+orderSn+"】,海典接口返回异常信息"+map.get("msg"));
		}
		return flag;
	}

	private String fengzhuangJson(TOrder tOrder,TMember tMember) throws SQLException,Exception {
		String province = null;
		String city = null;
		String area = null;
		if(tOrder.getAreaName()!=null && !"".equals(tOrder.getAreaName())){
			String shengshiqu = tOrder.getAreaName();
			String[] str = shengshiqu.split("-");
			if (str != null && str.length >= 3) {
				province = str[0];
				city = str[1];
				area = str[2];
			} else if (tOrder.getLatitude() != null && !"".equals(tOrder.getLatitude()) && tOrder.getLongitude() != null
					&& !"".equals(tOrder.getLongitude())) {
				String ssq = JingWeiDuUtil.getAreaShengShiQu(Double.valueOf(tOrder.getLongitude()),
						Double.valueOf(tOrder.getLatitude()));
				if(ssq==null){
					throw new Exception("[普通订单]收货地址省市区为空,单号:"+tOrder.getOrderSn());
				}else{
					String[] _ssq = ssq.split("-");
					province = _ssq[0];
					city = _ssq[1];
					area = _ssq[2];
				}
			}
		}
		if(province==null||"".equals(province)||"[]".equals(province)){
			throw new Exception("[普通订单]收货地址省市区为空,单号:"+tOrder.getOrderSn());
		}
		String peisong = "普通快递";
		CDeliveryWay cDeliveryWay = (CDeliveryWay) cdeliverywaydao.selectByPrimaryKey(tOrder.getDeliveryId());
		String olshopid = OLSHOPID;
		
		if (cDeliveryWay.getDeliveryCode().equals("ysd") ) {
			peisong = "药士达";
		}
		if (cDeliveryWay.getDeliveryCode().equals("ysdj") ) {
			peisong = "药师到家";
		}
		
		String zhifubaoLiushui = "";
		Date payTime = null;
		String alipay_no = "";
		String alipay_id = "";
		String paytype = "2";
		
		CPaymentWay cPaymentWay = (CPaymentWay) cpaymentwaydao.selectByPrimaryKey(tOrder.getPaymentId());
		
		if (cPaymentWay.getPaymentWay() == 0) {// 线上支付
			paytype = "1";
			TOrderFlowExample tOrderFlowExample = new TOrderFlowExample();
			tOrderFlowExample.createCriteria().andOrderIdEqualTo(tOrder.getId());
			List<TOrderFlow> tOrderFlowList = torderflowdao.selectByExample(tOrderFlowExample);
			for (TOrderFlow tOrderFlow : tOrderFlowList) {

				if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("pay99billBankPlugin") || tOrderFlow
								.getPaymentPlugin().equals("pay99billPlugin"))) {
					alipay_no = "快钱支付";
					alipay_id = "1";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("alipayBankPlugin")
								|| tOrderFlow.getPaymentPlugin().equals("alipayDirectPlugin")
								|| tOrderFlow.getPaymentPlugin().equals("alipayDualPlugin") || tOrderFlow
								.getPaymentPlugin().equals("alipayPartnerPlugin"))) {
					alipay_no = "支付宝";
					alipay_id = "2";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("alipayWapPlugin"))) {
					alipay_no = "手机支付宝";
					alipay_id = "3";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("payWapYktPlugin") || tOrderFlow
								.getPaymentPlugin().equals("payYktPlugin"))) {
					alipay_no = "医卡通支付";
					alipay_id = "6";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("wzfWapPlugin") || tOrderFlow
								.getPaymentPlugin().equals("payWzfPlugin"))) {
					alipay_no = "微信支付";
					alipay_id = "8";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("unionpayPlugin"))) {
					alipay_no = "银联支付";
					alipay_id = "10";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("yeepayPlugin"))) {
					alipay_no = "易宝支付";
					alipay_id = "11";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("tenpayPartnerPlugin")
								|| tOrderFlow.getPaymentPlugin().equals("tenpayDirectPlugin") || tOrderFlow
								.getPaymentPlugin().equals("tenpayBankPlugin"))) {
					alipay_no = "财付通(担保交易)";
					alipay_id = "12";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("paypalPlugin"))) {
					alipay_no = "Paypal";
					alipay_id = "13";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("paySxpayPlugin"))) {
					// 闪白条
					alipay_no = "闪白条";
					alipay_id = "20";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("wzfMobilePlugin"))) {
					// 微信支付移动端
					alipay_no = "微信支付移动端";
					alipay_id = "21";
				} else if (tOrderFlow.getPaymentPlugin() != null
						&& (tOrderFlow.getPaymentPlugin().equals("alipayMobilePlugin"))) {
					// 支付宝移动端
					alipay_no = "支付宝移动端";
					alipay_id = "22";
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
			//101:线上支付;102:货到付款;103:oto到付;104:处方到付
			if (cDeliveryWay.getDeliveryCode().equals("ysd") ) {
				alipay_no = "O2O到付";
				alipay_id = FUKUANTYPE + "103";
			}else if (cDeliveryWay.getDeliveryCode().equals("ysdj") ) {
				alipay_no = "O2O到付";
				alipay_id = FUKUANTYPE + "102";
			}else{
//				alipay_no = "货到付款";
				alipay_no = "普通到付";
				alipay_id = FUKUANTYPE + "102";
			}
		}

		BigDecimal amountTotal = new BigDecimal(0);
		BigDecimal getPrice = new BigDecimal(0);
		BigDecimal zhekouAll = new BigDecimal(0);//折扣总金额
		BigDecimal cuxiaoZhekou = new BigDecimal(0);//促销折扣
		BigDecimal youhuiquanZhekou = new BigDecimal(0);//优惠券折扣
		
		
		// 所有订单项
		TOrderItemExample tOrderItemExample = new TOrderItemExample();
		tOrderItemExample.createCriteria().andOrderIdEqualTo(tOrder.getId());
		List<TOrderItem> orderItemList = torderitemdao.selectByExample(tOrderItemExample);
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
			cuxiaoZhekou = cuxiaoZhekou.add(tOrder.getPromotionalDiscount());
		}
		if (tOrder != null && tOrder.getCouponDiscount() != null) {// 优惠券折扣
			amountTotal = amountTotal.subtract(tOrder.getCouponDiscount());
			zhekouAll = zhekouAll.add(tOrder.getCouponDiscount());
			youhuiquanZhekou = youhuiquanZhekou.add(tOrder.getCouponDiscount());
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
		
		List<TOrderItem> tOrderItems = new ArrayList<TOrderItem>();
		Map<String, Integer> productMap = new HashMap<String, Integer>();
		TGoods tGoods = null;
		TGoods selectTGoods = null;
		for (TOrderItem item : orderItemList) {
			selectTGoods = new TGoods();
			selectTGoods.setId(item.getGoodsId());
			tGoods = (TGoods) tgoodsdao.selectByPrimaryKey(item.getGoodsId());
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
			throw new Exception("[手动推送]订单:["+tOrder.getOrderSn()+"]没有订单项.");
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
		for (TOrderItem tOrderItem : tOrderItems) {
			if(tOrderItem!=null && tOrderItem.getPrice()!=null && tOrderItem
					.getQuantity()!=null){
				totalFee = totalFee.add(tOrderItem.getPrice().multiply(new BigDecimal(tOrderItem.getQuantity())));
			}
		}
		////////////////////////////////////////////////////////////////////////////////////
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Order hdOrder = new Order();
		hdOrder.setOlorderno(tOrder.getOrderSn());//订单号(不为空)
		hdOrder.setOlshopid(olshopid);//网店编码(不为空)(海典提供)
		hdOrder.setStatus(1);//状态0不处理；1待处理；2终止处理；3开始拣货；4已出库(不为空)
		hdOrder.setOlcusid(mendianId);//网店客户ID(不为空)
		hdOrder.setCreatetime(tOrder.getCreateDate()==null?sdf.format(new Date()):sdf.format(tOrder.getCreateDate()));//生成日期(不为空)当前时间
		hdOrder.setDiscount_fee("0");//折扣金额(不为空)
		hdOrder.setPay_time(payTime==null?null:sdf.format(payTime));//付款时间(不为空) TODO
		hdOrder.setModified(tOrder.getCreateDate()==null?null:sdf.format(tOrder.getCreateDate()));//修改时间(不为空) TODO
		hdOrder.setAlipay_id(null);//支付宝用户ID 
		hdOrder.setAlipay_no(zhifubaoLiushui);//支付宝交易号
		hdOrder.setBuyer_nick(tMember.getRealName()==null?"":tMember.getRealName());//买家昵称(不为空)
		hdOrder.setReceiver_name(tOrder.getReceiver());//买家姓名(不为空)
		hdOrder.setReceiver_state(province);//买家省(不为空)
		hdOrder.setReceiver_city(city);//买家市(不为空)
		hdOrder.setReceiver_district(area);//买家区(不为空)
		hdOrder.setReceiver_address(tOrder.getDetailedAddress());//买家街道(不为空)
		hdOrder.setReceiver_zip(tOrder.getZipCode());//买家邮编
		hdOrder.setReceiver_mobile(tOrder.getPhone());//买家电话1(不为空)
		hdOrder.setReceiver_phone(null);//买家电话2
		//索要发票。
		if(StringUtils.hasText(fapiaoType)){
			hdOrder.setBuyer_message("索要发票。"+tOrder.getRemark());//买家备注
			hdOrder.setNotes("索要发票。"+tOrder.getRemark());//备注
		}else {
			hdOrder.setBuyer_message(tOrder.getRemark());//买家备注
			hdOrder.setNotes(tOrder.getRemark());//备注
		}
		hdOrder.setBuyer_email(tMember.getEmail());//买家邮箱
		hdOrder.setTrade_from(_orderSource);//订单来源
		hdOrder.setExpcmpname(peisong);//快递名称
		hdOrder.setPaytype(paytype);//付款类型(不为空),1:线上支付;2货到付款；3:O2O自提；
		hdOrder.setOl_status(tOrder.getOrderStatus()==null?"-1":tOrder.getOrderStatus().toString());//线上状态(不为空)即第三方系统中的状态
		hdOrder.setEccode("18");//平台编码(不为空)(海典提供) TODO
		hdOrder.setSeller_code_fee("0");//卖家货到付款服务费()
		
		hdOrder.setInvoice_name(tOrder.getInvoiceTitle()==null?"":tOrder.getInvoiceTitle());//发票抬头
		hdOrder.setInvoice_type(fapiaoType);//发票内容类型
		hdOrder.setBuyer_cod_fee("0");//买家货到付款服务费
		hdOrder.setDiscount_fee_dtl("0");//明细折扣合计(不为空)
		hdOrder.setBuyer_obtain_point_fee("0");//买家获得积分
		hdOrder.setPayway(alipay_no);//付款方式(不为空),第三方系统支付方式（ID或名称均可）
		hdOrder.setYfx_fee("0");//运费险金额
		hdOrder.setYfx_id(null);//运费险交易ID
		hdOrder.setYfx_type(null);//运费险类型
		hdOrder.setDelivery_type(null);//送货类型
		hdOrder.setAvailable_confirm_fee("0");//剩余确认收货金额
		hdOrder.setReal_point_fee(tOrder.getUseIntegration()==null?"0":tOrder.getUseIntegration().toString());//买家实际使用积分
		hdOrder.setCommission("0");//佣金比例
		hdOrder.setCommission_fee("0");//交易佣金
		hdOrder.setBuyer_cod("0");//货到付款服务费比率
		hdOrder.setConfirm_time(sdf.format(new Date()));//确认时间(不为空);
		hdOrder.setOrder_start_time(tOrder.getCreateDate()==null?null:sdf.format(tOrder.getCreateDate()));//订单开始时间(不为空)//TODO
		hdOrder.setSeller_cod("0");//卖家货到付款服务费比例
		hdOrder.setCredit_card_fee("0");//信用卡支付金额
		hdOrder.setEnd_time(null);//结束时间(不为空)//TODO
		hdOrder.setSeller_memo(null);//卖家留言;
		hdOrder.setInvoice_kind(null);//发票类型
		hdOrder.setOlbusno(mendianId);//作业点ID(不为空)(海典提供)
		hdOrder.setInvoice_contect(tOrder.getInvoiceTitle());//发票内容
		hdOrder.setBill_type(null);//单据类型
		hdOrder.setRx_audit_status(null);//处方药状态;0取消，1 审核通过；2，审核不通过
		hdOrder.setHavecfy("0");///是否是处方药,1，是 ；0，否
		hdOrder.setGroupid("1001");//海典提供;
		
		
		
		hdOrder.setTotal_fee(totalFee.toString());//订单总额(不为空)
		hdOrder.setAdjust_fee("0");// 调整金额(不为空)
		hdOrder.setDiscount_fee_eccode("0");//平台优惠金额
//		hdOrder.setPayment(amountTotal.toString());//实付金额(不为空)
		hdOrder.setPayment(amountTotal.toString());//实付金额(不为空)
		hdOrder.setPost_fee(tOrder.getShippingFee()==null?"0":tOrder.getShippingFee().toString());//邮费(不为空)
		hdOrder.setDiscount_fee_sum(zhekouAll.toString());//整单折扣金额(不为空)
		
		
		List<OrderDetail> itemList = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = null;
		TGoods _tGoods = null;
		for (TOrderItem item : orderItemList) {
			_tGoods = tgoodsdao.selectByPrimaryKey(item.getGoodsId());
			if(_tGoods!=null && _tGoods.getGoodsName()!=null && _tGoods.getGoodsno()!=null && item!=null && item.getQuantity()!=null && item.getPrice()!=null){
				orderDetail = new OrderDetail();
				orderDetail.setOlorderno(tOrder.getOrderSn());//订单号(不为空)
				orderDetail.setOid(item.getId().toString());//子订单编号(不为空)(海典提供)
				orderDetail.setNeedinvoice(tOrder.getIfInvoice().toString());//是否需要发票
				orderDetail.setPrice(item.getPrice().toString());//单价(不为空);
				orderDetail.setNum_iid(item.getGoodsSn());//商品数字ID(不为空),第三方系统中的商品ID
				orderDetail.setItem_meal_id(null);//套餐ID(不为空)TODO
				orderDetail.setNum(item.getQuantity().toString());//数量(不为空)
				orderDetail.setTotal_fee(item.getPrice().multiply(new BigDecimal(item.getQuantity())).toString());//总金额(不为空)
				orderDetail.setPayment(item.getPrice().multiply(new BigDecimal(item.getQuantity())).toString());//实付金额(不为空)
				orderDetail.setDiscount_fee("0");//折扣金额(不为空)
				orderDetail.setAdjust_fee("0");//调整金额(不为空)
				orderDetail.setModified(sdf.format(new Date()));//修改时间(不为空)
				orderDetail.setNotes(null);//备注
				orderDetail.setTwarename(_tGoods==null?"":_tGoods.getGoodsName());//商品名称(不为空)
				orderDetail.setEccode("18");//平台编码(不为空)(海典提供)
				orderDetail.setOlshopid(olshopid);//作业点(不为空)(海典提供)
				orderDetail.setSku_id(null);//SKUID
				orderDetail.setOuter_iid(_tGoods.getGoodsno());//外部编码(不为空)对应海典系统中的商品ID
				itemList.add(orderDetail);
			}
		}
		hdOrder.setOrderDetail(itemList);//明细
		JSONObject jsonObject = JSONObject.fromObject(hdOrder);
		System.out.println("[手动推送]提交海典前转json格式:\t\t"+jsonObject);
		return jsonObject.toString();
	}
	
	
	/**
	 * 存储过程版本
	 */
	public void pushOrder(TOrder order) throws Exception {
		TOrder orderUpdate = new TOrder();		
		orderUpdate.setId(order.getId());
		orderUpdate.setPushTime(new Date());
		orderUpdate.setIsPush(1);
		this.torderdao.updateByPrimaryKeySelective(orderUpdate);	
		boolean flag =hydeeDao.insertDstOrderToHD(order, this.tmemberdao.selectByPrimaryKey(order.getMemberId()));
		if(!flag){
			throw new Exception("推送海淀失败：订单编号"+order.getOrderSn());
		}
		
	}
	
	
	public CPaymentWayDAO getCpaymentwaydao() {
		return cpaymentwaydao;
	}
	public void setCpaymentwaydao(CPaymentWayDAO cpaymentwaydao) {
		this.cpaymentwaydao = cpaymentwaydao;
	}
	public TReturnDAO getTreturndao() {
		return treturndao;
	}
	public void setTreturndao(TReturnDAO treturndao) {
		this.treturndao = treturndao;
	}
	public TReturnItemDAO getTreturnitemdao() {
		return treturnitemdao;
	}
	public void setTreturnitemdao(TReturnItemDAO treturnitemdao) {
		this.treturnitemdao = treturnitemdao;
	}
	public TShortOrderDAO getTshortorderdao() {
		return tshortorderdao;
	}
	public void setTshortorderdao(TShortOrderDAO tshortorderdao) {
		this.tshortorderdao = tshortorderdao;
	}
	public TMemberDAO getTmemberdao() {
		return tmemberdao;
	}
	public void setTmemberdao(TMemberDAO tmemberdao) {
		this.tmemberdao = tmemberdao;
	}
	public TMemberIntegralDAO getTmemberintegraldao() {
		return tmemberintegraldao;
	}
	public void setTmemberintegraldao(TMemberIntegralDAO tmemberintegraldao) {
		this.tmemberintegraldao = tmemberintegraldao;
	}
	public TOrderLeaderDAO getTorderleaderdao() {
		return torderleaderdao;
	}
	public void setTorderleaderdao(TOrderLeaderDAO torderleaderdao) {
		this.torderleaderdao = torderleaderdao;
	}
	public TMemberLeaderDAO getTmemberleaderdao() {
		return tmemberleaderdao;
	}
	public void setTmemberleaderdao(TMemberLeaderDAO tmemberleaderdao) {
		this.tmemberleaderdao = tmemberleaderdao;
	}
	public TGoodsBrokerageDAO getTgoodsbrokeragedao() {
		return tgoodsbrokeragedao;
	}
	public void setTgoodsbrokeragedao(TGoodsBrokerageDAO tgoodsbrokeragedao) {
		this.tgoodsbrokeragedao = tgoodsbrokeragedao;
	}
	public HydeeDao getHydeeDao() {
		return hydeeDao;
	}
	public void setHydeeDao(HydeeDao hydeeDao) {
		this.hydeeDao = hydeeDao;
	}
	public MessageSender getTopicMessageSender() {
		return topicMessageSender;
	}
	public void setTopicMessageSender(MessageSender topicMessageSender) {
		this.topicMessageSender = topicMessageSender;
	}
	public TGoodsPriceDAO getTgoodspricedao() {
		return tgoodspricedao;
	}
	public void setTgoodspricedao(TGoodsPriceDAO tgoodspricedao) {
		this.tgoodspricedao = tgoodspricedao;
	}
	
}
