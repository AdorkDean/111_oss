package com.rc.portal.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.action.BaseAction;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.commons.excel.ExcelUtil;
import com.rc.commons.util.InfoUtil;
import com.rc.dst.client.util.ClientSubmitPublic;
import com.rc.portal.jms.MessageSender;
import com.rc.portal.service.CDeliveryWayManager;
import com.rc.portal.service.CLocationCityManager;
import com.rc.portal.service.CPaymentWayManager;
import com.rc.portal.service.OpenSqlManage;
import com.rc.portal.service.TGoodsManager;
import com.rc.portal.service.TGoodsPriceManager;
import com.rc.portal.service.TLeaderStayMoneyManager;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.service.TMemberManager;
import com.rc.portal.service.TMemberReceiverLatLonManager;
import com.rc.portal.service.TOrderFlowManager;
import com.rc.portal.service.TOrderItemManager;
import com.rc.portal.service.TOrderManager;
import com.rc.portal.service.TOrderShipmentManager;
import com.rc.portal.service.TSysParameterManager;
import com.rc.portal.vo.CDeliveryWay;
import com.rc.portal.vo.CLocationCity;
import com.rc.portal.vo.CLocationCityExample;
import com.rc.portal.vo.CPaymentWay;
import com.rc.portal.vo.CPaymentWayExample;
import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsPrice;
import com.rc.portal.vo.TGoodsPriceExample;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberReceiverLatLon;
import com.rc.portal.vo.TOrder;
import com.rc.portal.vo.TOrderExample;
import com.rc.portal.vo.TOrderFlow;
import com.rc.portal.vo.TOrderFlowExample;
import com.rc.portal.vo.TOrderShipment;
import com.rc.portal.vo.TOrderShipmentExample;
import com.rc.portal.webapp.util.PageResult;
import com.rc.portal.webapp.util.UtilDate;
/**
 * 订单action
 * @author user3
 *
 */
public class OrderAction extends BaseAction{
	private static final long serialVersionUID = 8229477343937508644L;
	/**
	 * 订单manage
	 */
	private TOrderManager tordermanager;
	/**
	 * 支付方式 manage
	 */
	private CPaymentWayManager cpaymentwaymanager;
	/**
	 * 配送方式 manage
	 */
	private  CDeliveryWayManager cdeliverywaymanager;
	/**
	 * 会员收货地址  manage
	 */
	private TMemberReceiverLatLonManager tmemberreceiverlatlonmanager;
	/**
	 * 会员manage
	 */
	private TMemberManager tmembermanager;
	/**
	 * 商品 manage
	 */
	private TGoodsManager tgoodsmanager;
	/**
	 * 商品价格manage
	 */
	private TGoodsPriceManager tgoodspricemanager;
	
	
	private OpenSqlManage opensqlmanage;
	/**
	 * 订单商品关联manager
	 */
	private TOrderItemManager torderitemmanager;
	
	/**
	 * 支付方式list
	 */
	private List<CPaymentWay> paymentWayList;
	
	/**
	 * 支付流水
	 */
	private TOrderFlowManager torderflowmanager;
	
	/**
	 * 待入账流水Service
	 */
	private TLeaderStayMoneyManager tleaderstaymoneymanager;
	
	/**
	 * 配送方式list
	 */
	private List<CDeliveryWay> deliveryWayList;
	
	private PageWraper pw = new PageWraper();
	
	private PageResult rs = new PageResult(); 
	
	private Condition model = new Condition();
	
	private TOrder order;
	/**
	 * 地区
	 */
	private List<Map<String,Object>> clocationList;
	/**
	 * 收获地址
	 */
	private TMemberReceiverLatLon tmemberreceiverlatlon;
	
	/**
	 * 订单发货信息
	 */
	private TOrderShipment orderShipment;
	
	
	private TOrderShipmentManager tordershipmentmanager;
	
	/**
	 * 用户id
	 */
	private Long memberId;
	
	/**
	 * 生成excl
	 */
	private String url;
	
	
	private MessageSender topicMessageSender;

	private TMemberAccountManager tmemberaccountmanager;
	
	
	/**
	 * 系统配置参数
	 */
	private TSysParameterManager tsysparametermanager;
	
	/**
	 * 地区城市码表
	 */
	private CLocationCityManager clocationcitymanager;
	
	
	
	private String pushOrderSwitch = InfoUtil.getInstance().getInfo("config", "hd.push.order.switch");
	
	/**
	 * 跳转到手动下单页面
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public String addOrder() throws SQLException{
		CPaymentWayExample paymentWayExample = new CPaymentWayExample();
		paymentWayExample.createCriteria().andPaymentWayEqualTo(1);//线下支付
		paymentWayList = cpaymentwaymanager.selectByExample(paymentWayExample);
		deliveryWayList=cdeliverywaymanager.selectByExample(null);
		return "order_add";
	}
	
	
	/**
	 * 手动下单选择用户
	 * @return
	 */
	public String orderMemberList(){
		Map map=new HashMap();
		if(StringUtils.hasText(model.getUsername())){
			map.put("username", model.getUsername().trim());
		}
		if(StringUtils.hasText(model.getMobile())){
			map.put("mobile", model.getMobile().trim());
		}
		pw = opensqlmanage.selectForPageByMap_drug(map, "t_member.selectOrderMemberCountByMap", "t_member.selectOrderMemberListByMap", rs.getP_curPage(), 10);
		return "order_memberList";
	}
	
	
	/**
	 * 根据用户 查询收获地址
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void getMemberReceiver() throws Exception{
		String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("member_id", String.valueOf(memberId));
	    String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"getReceiverAddress");
	    List<Map<String, Object>> resultList = null;
	    JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
		if (jsonObject != null) {
			JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("list"));
			resultList = (List<Map<String, Object>>) JSONArray.toCollection(jsonArray, HashMap.class);
		}
		if(resultList!=null&&resultList.size()>0){
			for(Map<String, Object> map:resultList){
				if(map.get("latitude") instanceof JSONNull && map.get("longitude") instanceof JSONNull){
					map.put("latitude","");
					map.put("longitude","");
				}
				if(map.get("location_address") instanceof JSONNull){
					map.put("location_address","");
				}
				if(map.get("store_id") instanceof JSONNull){
					map.put("store_id","");
				}
			}
		}
		this.writeObjectToResponse(resultList,ContentType.application_json);
	}
	
	/**
	 * 跳转到收货地址添加页面
	 * @return
	 * @throws SQLException 
	 */
	public String addReceiver() throws SQLException{
		CLocationCityExample cityexample = new CLocationCityExample();
		cityexample.createCriteria().andGradeEqualTo(2);
		cityexample.setOrderByClause(" pinyin asc");
		List<CLocationCity> cityList =this.clocationcitymanager.selectByExample(cityexample);
		List<CLocationCity> cityzimuList = null;
		TreeMap<String,List<CLocationCity>> zimuMap = new TreeMap<String,List<CLocationCity>>();
		for(CLocationCity city:cityList){
			if(zimuMap.get(city.getPinyin())!=null){
				cityzimuList = zimuMap.get(city.getPinyin());
				cityzimuList.add(city);
				zimuMap.put(city.getPinyin(), cityzimuList);
			}else{
				cityzimuList = new ArrayList<CLocationCity>();
				cityzimuList.add(city);
				zimuMap.put(city.getPinyin(), cityzimuList);
			}
		}
		this.getRequest().setAttribute("zimuMap", zimuMap);
		
		
		
		
		return "add_receiver";
	}
	
//	/**
//	 * 选择地区
//	 * @throws IOException
//	 */
//	public void clocationSelect() throws IOException{
//		getResponse().setContentType("text/html;charset=utf-8");
//		PrintWriter write = getResponse().getWriter();
//		String parentid = this.getRequest().getParameter("parentid");
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("parentid", Integer.valueOf(parentid));
//		clocationList = opensqlmanage.selectForListByMap(paramMap, "c_location.selectLocationByMap");
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("clocationList", clocationList);
//		if(clocationList!=null&&clocationList.size()>0){
//			map.put("level", clocationList.get(0).get("grade"));
//		}
//		JSONObject json = JSONObject.fromObject(map);
//		write.write(json.toString());
//		write.close();
//	}
	
	/**
	 * 保存收获地址
	 * @throws IOException
	 */
	public void saveReceiver() throws Exception{
//		getResponse().setContentType("text/html;charset=utf-8");
//		PrintWriter write = getResponse().getWriter();
//		Long id =this.tmemberreceivermanager.insertSelective(this.tmemberreceiver);
//		write.write(id+"");
//		write.close();
		String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		Map<String,String> publicMap = new HashMap<String,String>();
		publicMap.put("member_id", String.valueOf(tmemberreceiverlatlon.getMemberId()));
		publicMap.put("mobile", tmemberreceiverlatlon.getMobile());
		publicMap.put("receiver", tmemberreceiverlatlon.getReceiver());
		publicMap.put("longitude", tmemberreceiverlatlon.getLongitude());
		publicMap.put("latitude", tmemberreceiverlatlon.getLatitude());
		publicMap.put("locationAddress", tmemberreceiverlatlon.getLocationAddress());
		publicMap.put("address", tmemberreceiverlatlon.getAddress());
		publicMap.put("zipCode", "");
		String memberReceiver_adcode = this.getRequest().getParameter("memberReceiver_adcode");
		CLocationCityExample example = new CLocationCityExample();
		example.createCriteria().andCitycodeEqualTo(memberReceiver_adcode).andGradeEqualTo(2);
		List<CLocationCity> cityList =this.clocationcitymanager.selectByExample(example);
		if(cityList!=null&&cityList.size()>0){
			publicMap.put("areaId",String.valueOf(cityList.get(0).getId()));
		}
		String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicServiceUrl+"addReceiverAddress");
		JSONObject jsonObject = JSONObject.fromObject(resultJsonstr);
		if (jsonObject != null && jsonObject.get("statusCode") != null && "1".equals(jsonObject.get("statusCode"))) {
			Long id = Long.valueOf(String.valueOf(jsonObject.get("id")));
			this.writeObjectToResponse(id,ContentType.application_json);
		}else{
			this.writeObjectToResponse("",ContentType.application_json);
		}
	}
	/**
	 * 保存订单
	 */
	public void saveOrder() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		//商品id字符串
		String goodsids = this.getRequest().getParameter("goodsidsS");
		//商品数量字符串
		String goodsSums = this.getRequest().getParameter("goodsSumsS");	
		//收获地址id
		String receiverId = this.getRequest().getParameter("receiverId");
		String message ="";
		if(!StringUtils.hasText(goodsids)){
			message ="请添加商品信息!";
		}
		
		if(!StringUtils.hasText(receiverId)){
			message ="请选择收获地址!";
		}
		 if(StringUtils.hasText(goodsids)&&StringUtils.hasText(goodsSums)){
			goodsids = goodsids.substring(0, goodsids.length()-1);
			goodsSums = goodsSums.substring(0, goodsSums.length()-1);
			String[] ids = goodsids.split(",");
			String[] sums = goodsSums.split(",");
			TGoods good =null;
			for(int i=0;i<ids.length;i++){
				if(StringUtils.hasText(sums[i])){
					good = this.tgoodsmanager.selectByPrimaryKey(Long.valueOf(ids[i].trim()));
					if(!(good.getStock()!=null&&good.getStock().longValue()>=Long.valueOf((sums[i].trim())).longValue())){
						message +=good.getGoodsName()+",";
					}
				}
			}
			if(StringUtils.hasText(message)){
				message= message.substring(0, message.length()-1)+"库存不足";
			}
			goodsids=goodsids+",";
			goodsSums=goodsSums+",";
		}
		if(message==""&&order!=null&&order.getMemberId()!=null){
			long id = this.getTordermanager().saveOrder(order, goodsids, goodsSums,receiverId);
			message = String.valueOf(id);
		}
		write.write(message);
		write.close();
		
		
		
	}
	/**
	 * 根据商品id查询商品信息
	 * @throws Exception
	 */
	public void getGoodsById() throws Exception{
		String goodsid = this.getRequest().getParameter("goodsid");
		TGoods goods = tgoodsmanager.selectByPrimaryKey(Long.parseLong(goodsid));
		TGoodsPriceExample priceExample = new TGoodsPriceExample();
		priceExample.createCriteria().andGoodsIdEqualTo(Long.parseLong(goodsid)).andPriceTypeEqualTo("pc").andPlatformTypeEqualTo("111yao");
		List<TGoodsPrice> priceList =tgoodspricemanager.selectByExample(priceExample);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("good", goods);
		TGoodsPrice goodsPrice = new TGoodsPrice();
		if(priceList!=null&&priceList.size()>0){
			goodsPrice = priceList.get(0);
		}
		resultMap.put("goodprice", goodsPrice);
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		JSONObject json = JSONObject.fromObject(resultMap);
		write.write(json.toString());
		write.close();
	}
	/**
	 * 计算商品金额、订单相关金额
	 */
	public void orderGoodsPrice() throws Exception{
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter write = getResponse().getWriter();
		String goodsids = this.getRequest().getParameter("goodsids");
		String goodsSums = this.getRequest().getParameter("goodsSums");
		String deliveryId = this.getRequest().getParameter("deliveryId");
		Map<String,Object> resultMap = this.tordermanager.orderGoodsPrice(goodsids, goodsSums, deliveryId);
		JSONObject json = JSONObject.fromObject(resultMap);
		write.write(json.toString());
		write.close();
	}
	/**
	 * 跳转到订单修改页面
	 * @return
	 * @throws Exception
	 */
	public String toUpdateOrder() throws Exception{
		//订单id
		String orderId = this.getRequest().getParameter("orderId");
		order = this.tordermanager.selectByPrimaryKey(Long.parseLong(orderId));
		this.getRequest().setAttribute("paymentway", cpaymentwaymanager.selectByPrimaryKey(order.getPaymentId()));
		this.getRequest().setAttribute("deliveryway", cdeliverywaymanager.selectByPrimaryKey(order.getDeliveryId()));
		this.getRequest().setAttribute("member", this.tmembermanager.selectByPrimaryKey(order.getMemberId()));
//		paymentWayList = cpaymentwaymanager.selectByExample(null);
//		deliveryWayList=cdeliverywaymanager.selectByExample(null);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orderId", order.getId());
		List orderItemList =this.opensqlmanage.selectForListByMap(paramMap, "t_order_item.selectOrderItemGoodsByOrderId");
		this.getRequest().setAttribute("orderItemList", orderItemList);
		if(this.getRequest().getParameter("typeflag").equals("update")){
			CLocationCity locationcity =this.clocationcitymanager.selectByPrimaryKey(order.getAreaId().intValue());
			if(locationcity!=null&&locationcity.getCitycode()!=null){
				this.getRequest().setAttribute("city_code", locationcity.getCitycode());
			}else{
				this.getRequest().setAttribute("city_code","");
			}
			
			return "order_update";
		}else{
			TOrderFlowExample orderFlowExample = new TOrderFlowExample();
			orderFlowExample.createCriteria().andOrderIdEqualTo(order.getId());
			List<TOrderFlow> orderFlowList =this.torderflowmanager.selectByExample(orderFlowExample);
			this.getRequest().setAttribute("orderFlowList", orderFlowList);
			TOrderShipmentExample orderShipmentExample = new TOrderShipmentExample();
			orderShipmentExample.createCriteria().andOrderIdEqualTo(order.getId()).andOrderTypeEqualTo(0);
			List<TOrderShipment> orderShipmentList =this.tordershipmentmanager.selectByExample(orderShipmentExample);
			this.getRequest().setAttribute("orderShipmentList", orderShipmentList);
			return "order_detail";
		}
	}
	/**
	 * 确认订单
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public void confirmOrder() throws Exception{
		String orderId = this.getRequest().getParameter("orderId");
		order = this.tordermanager.selectByPrimaryKey(Long.parseLong(orderId));
		if(order!=null){
			TOrder torder = new TOrder();
			torder.setId(order.getId());
			torder.setVerifyStatus(1);//已确认
			this.tordermanager.updateByPrimaryKeySelective(torder);
			writeObjectToResponse(1, ContentType.text_html);
		}else{
			writeObjectToResponse(0, ContentType.text_html);
		}
	}
	/**
	 * 支付订单
	 */
	public void payOrder() throws Exception{
		String orderId = this.getRequest().getParameter("orderId");
		order = this.tordermanager.selectByPrimaryKey(Long.parseLong(orderId));
		if(order!=null){
			if(order.getOrderStatus()!=null&&order.getOrderStatus()==0){
				TOrderFlowExample orderFlowExample = new TOrderFlowExample();
				orderFlowExample.createCriteria().andOrderIdEqualTo(order.getId());
				List<TOrderFlow> orderFlowList =this.torderflowmanager.selectByExample(orderFlowExample);
				if(orderFlowList!=null&&orderFlowList.size()>0){
					this.tordermanager.payOrder(order.getId(), orderFlowList.get(0).getId());
					writeObjectToResponse(1, ContentType.text_html);//该订单没有支付流水不能支付
				}else{
					writeObjectToResponse(3, ContentType.text_html);//该订单没有支付流水不能支付
				}
			}else{
				writeObjectToResponse(2, ContentType.text_html);//该订单不能支付 订单未处于未支付状态
			}
		}else{
			writeObjectToResponse(0, ContentType.text_html);
		}
	}
	/**
	 * 订单发货
	 * @throws Exception
	 */
	public void shipmentOrder() throws Exception{
		String orderId = this.getRequest().getParameter("orderId");
		order = this.tordermanager.selectByPrimaryKey(Long.parseLong(orderId));
		if(order!=null){
			this.tordermanager.shipmentOrder(order.getId(), orderShipment);
			writeObjectToResponse(1, ContentType.text_html);
		}else{
			writeObjectToResponse(0, ContentType.text_html);
		}
	}
	/**
	 * 订单完成
	 * @throws Exception
	 */
	public void finishOrder() throws Exception {
		String orderId = this.getRequest().getParameter("orderId");
		order = this.tordermanager.selectByPrimaryKey(Long.parseLong(orderId));
		if (order != null) {
			try{
				String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");;//"http://localhost:8080/111_yao_common/sltRouter?method="
				this.tordermanager.finishOrder(order,publicServiceUrl);
				writeObjectToResponse(1, ContentType.text_html);
			}catch(Exception e){
				e.printStackTrace();
				writeObjectToResponse(0, ContentType.text_html);
			}
		} else {
			writeObjectToResponse(0, ContentType.text_html);
		}
	}
	
	/**
	 * 取消订单
	 * @throws Exception
	 */
	public void cancelOrder() throws Exception {
		long flag = -1;
		try {
			String orderId = this.getRequest().getParameter("orderId");
			order = this.tordermanager.selectByPrimaryKey(Long.parseLong(orderId));
			if (order != null) {
				if (order.getOrderStatus().intValue() != 4 && order.getOrderStatus().intValue() != 5) {
					this.tordermanager.cancelOrder(order);
					// 写流水的条件为：1:支付过的;2:秀粉的订单;3:流水不存在该流水的;
//					if (order != null && order.getPaymentId() != null) {
//						// 1:支付过的;
//						CPaymentWay cPaymentWay = cpaymentwaymanager.selectByPrimaryKey(order.getPaymentId());
//						if (cPaymentWay != null && cPaymentWay.getPaymentCode() != null&& "xszf".equals(cPaymentWay.getPaymentCode())&& order.getOrderStatus()!=null && (order.getOrderStatus()==1||order.getOrderStatus()==2)) {
//							// 2:秀粉的订单;
//							Map<String, Object> _paramMap = new HashMap<String, Object>();
//							_paramMap.put("memberId", order.getMemberId());// 订单用户
//							Map<String, Object> phoneMap = (Map<String, Object>) opensqlmanage.selectForObjectByMap(_paramMap, "t_member_leader.selectLeaderPhoneByXiufenId");
//							tleaderstaymoneymanager.cancelOrderInsertStayByHDF(phoneMap, order);
//						}
//					}
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
	 * 修改订单
	 * @throws Exception
	 */
	public void updateOrder() throws Exception{
		try{
			this.tordermanager.updateOrder(order);
			writeObjectToResponse("1", ContentType.text_html);
		}catch(Exception e){
			writeObjectToResponse("0", ContentType.text_html);
		}
	}
	
	
	
	/**
	 * @deprecated
	 * 接口,提供给支付完成之后写待入账流水
	 * TODO 提供给支付完之后写待入账流水；
	 * @throws Exception
	 */
	public void updateOrderByPaymenting() throws Exception{
		String orderSn = this.getRequest().getParameter("orderSn");
		if(orderSn!=null&& !"".equals(orderSn)){
			TOrderExample _tExample = new TOrderExample();
			_tExample.createCriteria().andOrderSnEqualTo(orderSn);
			List<TOrder> _orderList = tordermanager.selectByExample(_tExample);
			if(_orderList!=null && _orderList.size()==1){
				order = _orderList.get(0);
				if (order!=null) {
					Map<String, Object> _paramMap = new HashMap<String, Object>();
					_paramMap.put("memberId", order.getMemberId());
					// 根据订单查领袖，(非秀粉返回null)
					Map<String, Object> phoneMap = (Map<String, Object>) opensqlmanage.selectForObjectByMap(
							_paramMap, "t_member_leader.selectLeaderPhoneByXiufenId");
					//给领袖算待入账佣金-----------------开始
					if(phoneMap != null && phoneMap.get("member_id") != null
							&& !"".equals(phoneMap.get("member_id").toString())){
						Long memberId = Long.valueOf(phoneMap.get("member_id").toString());
						if(memberId!=null && memberId>0L){
							Double waitingAmount = 0D;
							
							Map<String, Object> countMap = new HashMap<String, Object>();
							countMap.put("orderId", order.getId());
							waitingAmount = (Double) opensqlmanage.selectForObjectByMap(countMap, "t_order_item.countFanxianAmount");
							if(waitingAmount!=null && waitingAmount>0L){
								//给领袖加带入账金额
								tmemberaccountmanager.updateLeaderMemberAccount(waitingAmount,memberId);
							}
						}
					}
					//给领袖算待入账佣金-----------------结束
					//给领袖发短信提示---------------开始
					String phone = null;
					if (phoneMap != null && phoneMap.get("m_phone") != null
							&& !"".equals(phoneMap.get("m_phone").toString())) {
						phone = phoneMap.get("m_phone").toString();
					} else if (phoneMap != null && phoneMap.get("l_phone") != null
							&& !"".equals(phoneMap.get("l_phone").toString())) {
						phone = phoneMap.get("l_phone").toString();
					}
					if (phone != null && !"".equals(phone)) {
						Map<String, String> messageMap = new HashMap<String, String>();
						messageMap.put("mobiles", phone);
						messageMap.put("version", "v6");
						messageMap.put("smsContent", "领秀" + phoneMap.get("nick_name")
								+ "您好，您有秀粉产生了订单返佣，请到待入账金额中查看！");
						topicMessageSender.sendMapMessage(messageMap);
					}
					//给领袖发短信提示---------------开始
				}
				
			}
		}
	}
	/**
	 * 推送海淀
	 * @throws Exception
	 */
	public void pushOrder() throws Exception{
		String orderId = this.getRequest().getParameter("orderId");
		if(StringUtils.hasText(orderId)){
			order =this.tordermanager.selectByPrimaryKey(Long.parseLong(orderId.trim()));
		    if(order!=null){
		    	TMember tMember = tmembermanager.selectByPrimaryKey(order.getMemberId());
		    	if(order.getDeleteStatus().intValue()==0){
		    		//订单状态 0 待支付 1 待发货 2 待收货  3 已完成  4 已取消 5 已过期
		    		if(order.getOrderStatus().intValue()==3){
		    			writeObjectToResponse(3, ContentType.text_html);
		    		}else if(order.getOrderStatus().intValue()==4){
		    			writeObjectToResponse(4, ContentType.text_html);
		    		}else if(order.getOrderStatus().intValue()==5){
		    			writeObjectToResponse(5, ContentType.text_html);
		    		}else if(order.getOrderStatus().intValue()==0){
		    			writeObjectToResponse(6, ContentType.text_html);
		    		}else if(order.getOrderStatus().intValue()==2){
		    			writeObjectToResponse(7, ContentType.text_html);
		    		}else{
//		    			this.tordermanager.pushOrderByHttp(order,tMember);
		    			if(pushOrderSwitch!=null&&pushOrderSwitch.equals("1")){//接口
			    			this.tordermanager.pushOrderByHttp(order,tMember);
		    			}else{
		    				this.tordermanager.pushOrder(order);
		    			}
		    			writeObjectToResponse(1, ContentType.text_html);
		    		}
		    	}else{
		    		writeObjectToResponse(2, ContentType.text_html);
		    	}
		    }else{
		    	writeObjectToResponse(0, ContentType.text_html);
		    }
		}else{
			writeObjectToResponse(0, ContentType.text_html);
		}
	}
	/**
	 * 领秀订单统计
	 * @return
	 * @throws SQLException
	 */
	public String leaderOrderList() throws SQLException{
		Map map=new HashMap();
		if(model.getOrderNo()!=null&&!"".equals(model.getOrderNo().trim())){
			map.put("orderNo", model.getOrderNo().trim());
		}
		if(model.getSource()!=null&&model.getSource()!=-1){
			map.put("source", model.getSource());
		}
		if(model.getStart_date()!=null&&!"".equals(model.getStart_date())){
			map.put("start_date", model.getStart_date().trim());
		}else{
			map.put("start_date", "1900-01-01 00:00:00");
			
		}
		if(model.getEnd_date()!=null&&!"".equals(model.getEnd_date())){
			map.put("end_date", model.getEnd_date().trim());
		}else{
			map.put("end_date", "9999-12-01 00:00:00");
		}
		if(model.getMemberName()!=null&&!"".equals(model.getMemberName().trim())){
			
			map.put("memberName", model.getMemberName().trim());
		}
		if(StringUtils.hasText(model.getReceiver())){
			map.put("receiver", model.getReceiver().trim());
		}
		if(StringUtils.hasText(model.getPhone())){
			map.put("phone", model.getPhone().trim());
		}
		if(model.getOrderStatus()!=null&&model.getOrderStatus()!=-1){
			map.put("orderStatus", model.getOrderStatus());
		}
		if(model.getDeliveryId()!=null&&model.getDeliveryId()!=-1){
			map.put("deliveryId", model.getDeliveryId());
		}
		if(model.getVerifyStatus()!=null&&model.getVerifyStatus()!=-1){
			map.put("verifyStatus", model.getVerifyStatus());
		}
		if(model.getType()!=null&&model.getType()!=0){
			map.put("type", 2);
		}else{
			map.put("type", 0);
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_order.selectLeaderOrderCountByMap", "t_order.selectLeaderOrderListByMap", rs.getP_curPage(), rs.getP_pageSize());
		List<CDeliveryWay> deliveryWayList =cdeliverywaymanager.selectByExample(null);
		this.getRequest().setAttribute("deliveryWayList", deliveryWayList);
		if(model.getType()!=null&&model.getType()!=0){
			String path=getRequest().getSession().getServletContext().getRealPath("");
			path=path+"/excel_temp/"+UtilDate.getDate()+System.currentTimeMillis()+".xls";	
				List lists = leaderExport();
				String s[][] = getLeaderArray(pw.getResult());
				ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		}else{
			return "leader_orderList";
		}
	}
	
	//设置表头
	 public List<String> leaderExport() {
			List list = new ArrayList();
			list.add("订单编号");
			list.add("订单来源");
			list.add("支付方式");
			list.add("应付金额");
			list.add("配送方式");
			list.add("确认状态");
			list.add("订单状态");
			list.add("下单时间");
			list.add("推送海典");
			list.add("收货人");
			list.add("收货人手机号");
			list.add("收货地址");
			list.add("秀粉名称");
			list.add("领秀名称");
			return list;
		}
		//存放二维数组
		public String[][] getLeaderArray(List couponList) {
			String[][] s = new String[couponList.size()][14];
			for (int i = 0; i < couponList.size(); i++) {
				Map m = (Map)couponList.get(i);
				if(m.get("order_sn")!=null){
					s[i][0]=m.get("order_sn").toString();
				}else{
					s[i][0] = " ";
				}
				if(m.get("order_source")!=null){
					if(Integer.parseInt(m.get("order_source").toString())==1){
						s[i][1]="PC";
					}else if(Integer.parseInt(m.get("order_source").toString())==2){
						s[i][1]="wap";
					}else if(Integer.parseInt(m.get("order_source").toString())==3){
						s[i][1]="安卓";
					}else if(Integer.parseInt(m.get("order_source").toString())==4){
						s[i][1]="ios";
					}else if(Integer.parseInt(m.get("order_source").toString())==5){
						s[i][1]="手动下单";
					}
				}else{
					s[i][1]="未知";
				}
				
				if(m.get("payName")!=null){
					s[i][2]=m.get("payName").toString();
				}else{
					s[i][2]="";
				}
				
				if(m.get("payable_amount")!=null){
					s[i][3]=m.get("payable_amount").toString();
				}else{
					s[i][3]="0.00";
				}
				
				if(m.get("deliName")!=null){
					s[i][4]=m.get("deliName").toString();
				}else{
					s[i][4]="";
				}
				
				
				if(m.get("verifyStatus")!=null){
					if(Integer.parseInt(m.get("verifyStatus").toString())==0){
						s[i][5]="未确认";
					}else if(Integer.parseInt(m.get("verifyStatus").toString())==1){
						s[i][5]="已确认";
					}
				}else{
					s[i][5]="未确认";
				}
				
				if(m.get("order_status")!=null){
					if(Integer.parseInt(m.get("order_status").toString())==0){
						s[i][6]="待支付";
					}else if(Integer.parseInt(m.get("order_status").toString())==1){
						s[i][6]="待发货";
					}else if(Integer.parseInt(m.get("order_status").toString())==2){
						s[i][6]="待收货";
					}else if(Integer.parseInt(m.get("order_status").toString())==3){
						s[i][6]="已完成";
					}else if(Integer.parseInt(m.get("order_status").toString())==4){
						s[i][6]="已取消";
					}else if(Integer.parseInt(m.get("order_status").toString())==5){
						s[i][6]="已过期";
					}
				}else{
					s[i][6]="未知";
				}
				
				if(m.get("create_date")!=null){
					s[i][7]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("create_date"));
				}else{
					s[i][7]="";
				}
				if(m.get("is_push")!=null){
					if(Integer.parseInt(m.get("is_push").toString())==0){
						s[i][8]="否";
					}else if(Integer.parseInt(m.get("is_push").toString())==1){
						s[i][8]="是";
					}
				}else{
					s[i][8]="否";
				}
				
				if(m.get("receiver")!=null){
					s[i][9]=m.get("receiver").toString();
				}else{
					s[i][9]="";
				}
				if(m.get("phone")!=null){
					s[i][10]=m.get("phone").toString();
				}else{
					s[i][10]="";
				}
				if(m.get("deliveryName")!=null){
					s[i][11]=m.get("deliveryName").toString();
				}else{
					s[i][11]="";
				}
				if(m.get("xiufenname")!=null){
					s[i][12]=m.get("xiufenname").toString();
				}else{
					s[i][12]="";
				}
				if(m.get("lingxiuname")!=null){
					s[i][13]=m.get("lingxiuname").toString();
				}else{
					s[i][13]="";
				}
			}
			return s;
		}
	
	/**
	 * 订单列表页
	 * @throws SQLException 
	 */
	public String getOrderList() throws SQLException{
		Map map=new HashMap();
		if(model.getOrderNo()!=null&&!"".equals(model.getOrderNo().trim())){
			map.put("orderNo", model.getOrderNo().trim());
		}
		if(model.getSource()!=null&&model.getSource()!=-1){
			map.put("source", model.getSource());
		}
		if(model.getStart_date()!=null&&!"".equals(model.getStart_date())){
			map.put("start_date", model.getStart_date().trim());
		}else{
			map.put("start_date", "1900-01-01 00:00:00");
			
		}
		if(model.getEnd_date()!=null&&!"".equals(model.getEnd_date())){
			map.put("end_date", model.getEnd_date().trim());
		}else{
			map.put("end_date", "9999-12-01 00:00:00");
		}
		if(model.getMemberName()!=null&&!"".equals(model.getMemberName().trim())){
			
			map.put("memberName", model.getMemberName().trim());
		}
		if(StringUtils.hasText(model.getReceiver())){
			map.put("receiver", model.getReceiver().trim());
		}
		if(StringUtils.hasText(model.getPhone())){
			map.put("phone", model.getPhone().trim());
		}
		if(model.getOrderStatus()!=null&&model.getOrderStatus()!=-1){
			map.put("orderStatus", model.getOrderStatus());
		}
		if(model.getDeliveryId()!=null&&model.getDeliveryId()!=-1){
			map.put("deliveryId", model.getDeliveryId());
		}
		if(model.getVerifyStatus()!=null&&model.getVerifyStatus()!=-1){
			map.put("verifyStatus", model.getVerifyStatus());
		}
		if(model.getType()!=null&&model.getType()!=0){
			map.put("type", 2);
		}else{
			map.put("type", 0);
		}
		pw=opensqlmanage.selectForPageByMap_drug(map, "t_order.selectOrderCountByMap", "t_order.selectOrderListByMap", rs.getP_curPage(), rs.getP_pageSize());
		List<CDeliveryWay> deliveryWayList =cdeliverywaymanager.selectByExample(null);
		this.getRequest().setAttribute("deliveryWayList", deliveryWayList);
		if(model.getType()!=null&&model.getType()!=0){
			String path=getRequest().getSession().getServletContext().getRealPath("");
			path=path+"/excel_temp/"+UtilDate.getDate()+System.currentTimeMillis()+".xls";	
				List lists = export();
				String s[][] = getArray(pw.getResult());
				ExcelUtil.createExcel(path, lists, s);
			getRequest().setAttribute("xlsname", path);
			url = "/WEB-INF/pages/down/down.jsp";
			return "xls";
		}else{
			return "getOrderList";
		}
	}
	
	//设置表头
		public List<String> export() {
			List list = new ArrayList();
			list.add("订单编号");
			list.add("订单来源");
			list.add("支付方式");
			list.add("应付金额");
			list.add("配送方式");
			list.add("会员名称");
			list.add("确认状态");
			list.add("订单状态");
			list.add("删除状态");
			list.add("下单时间");
			list.add("推送海典");
			list.add("是否使用优惠劵");
			list.add("收货人");
			list.add("收货人手机号");
			list.add("收货地址");
			return list;
		}
		//存放二维数组
			public String[][] getArray(List couponList) {
				String[][] s = new String[couponList.size()][15];
				for (int i = 0; i < couponList.size(); i++) {
					Map m = (Map)couponList.get(i);
					if(m.get("order_sn")!=null){
						s[i][0]=m.get("order_sn").toString();
					}else{
						s[i][0] = " ";
					}
					if(m.get("order_source")!=null){
						if(Integer.parseInt(m.get("order_source").toString())==1){
							s[i][1]="PC";
						}else if(Integer.parseInt(m.get("order_source").toString())==2){
							s[i][1]="wap";
						}else if(Integer.parseInt(m.get("order_source").toString())==3){
							s[i][1]="安卓";
						}else if(Integer.parseInt(m.get("order_source").toString())==4){
							s[i][1]="ios";
						}else if(Integer.parseInt(m.get("order_source").toString())==5){
							s[i][1]="手动下单";
						}
					}else{
						s[i][1]="未知";
					}
					
					if(m.get("payName")!=null){
						s[i][2]=m.get("payName").toString();
					}else{
						s[i][2]="";
					}
					
					if(m.get("payable_amount")!=null){
						s[i][3]=m.get("payable_amount").toString();
					}else{
						s[i][3]="0.00";
					}
					
					if(m.get("deliName")!=null){
						s[i][4]=m.get("deliName").toString();
					}else{
						s[i][4]="";
					}
					
					if(m.get("user_name")!=null){
						s[i][5]=m.get("user_name").toString();
					}else{
						s[i][5]="";
					}
					
					if(m.get("verifyStatus")!=null){
						if(Integer.parseInt(m.get("verifyStatus").toString())==0){
							s[i][6]="未确认";
						}else if(Integer.parseInt(m.get("verifyStatus").toString())==1){
							s[i][6]="已确认";
						}
					}else{
						s[i][6]="未确认";
					}
					
					if(m.get("order_status")!=null){
						if(Integer.parseInt(m.get("order_status").toString())==0){
							s[i][7]="待支付";
						}else if(Integer.parseInt(m.get("order_status").toString())==1){
							s[i][7]="待发货";
						}else if(Integer.parseInt(m.get("order_status").toString())==2){
							s[i][7]="待收货";
						}else if(Integer.parseInt(m.get("order_status").toString())==3){
							s[i][7]="已完成";
						}else if(Integer.parseInt(m.get("order_status").toString())==4){
							s[i][7]="已取消";
						}else if(Integer.parseInt(m.get("order_status").toString())==5){
							s[i][7]="已过期";
						}
					}else{
						s[i][7]="未知";
					}
					
					if(m.get("delete_status")!=null){
						if(Integer.parseInt(m.get("delete_status").toString())==0){
							s[i][8]="未删除";
						}else if(Integer.parseInt(m.get("delete_status").toString())==1){
							s[i][8]="已删除";
						}
					}else{
						s[i][8]="未删除";
					}
					
					if(m.get("create_date")!=null){
						s[i][9]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)m.get("create_date"));
					}else{
						s[i][9]="";
					}
					
					if(m.get("is_push")!=null){
						if(Integer.parseInt(m.get("is_push").toString())==0){
							s[i][10]="否";
						}else if(Integer.parseInt(m.get("is_push").toString())==1){
							s[i][10]="是";
						}
					}else{
						s[i][10]="否";
					}
					
					if(m.get("couponName")!=null){
						if(!"-1".equals(m.get("couponName").toString())){
							s[i][11]=m.get("couponName").toString();
						}else{
							s[i][11]="否";
						}
					}else{
						s[i][11]="否";
					}
					
					if(m.get("receiver")!=null){
						s[i][12]=m.get("receiver").toString();
					}else{
						s[i][12]="";
					}
					if(m.get("phone")!=null){
						s[i][13]=m.get("phone").toString();
					}else{
						s[i][13]="";
					}
					if(m.get("deliveryName")!=null){
						s[i][14]=m.get("deliveryName").toString();
					}else{
						s[i][14]="";
					}
				}
				return s;
			}
	public class Condition {
		private String username;
		private String mobile;
		private String orderNo;//订单编号
		private Integer source;//订单来源
		private String start_date;
		private String end_date;
		private String memberName;
		//核实状态  0 未核实  1 已核实
		private Integer verifyStatus;
		private Integer deliveryId;//配送方式
		//订单状态 0 待支付 1 待发货 2 待收货  3 已完成  4 已取消 5 已过期
		private Integer orderStatus;//
		
		private Integer type;//0 订单列表  2 导出excel
		
		private String receiver;//收货人
		
		private String phone;//收货人电话
		
		
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public Integer getSource() {
			return source;
		}
		public void setSource(Integer source) {
			this.source = source;
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
		public String getMemberName() {
			return memberName;
		}
		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public Integer getVerifyStatus() {
			return verifyStatus;
		}
		public void setVerifyStatus(Integer verifyStatus) {
			this.verifyStatus = verifyStatus;
		}
		public Integer getDeliveryId() {
			return deliveryId;
		}
		public void setDeliveryId(Integer deliveryId) {
			this.deliveryId = deliveryId;
		}
		public Integer getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(Integer orderStatus) {
			this.orderStatus = orderStatus;
		}
		public String getReceiver() {
			return receiver;
		}
		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
	}
	/**
	 * 订单统计页面
	 * @return
	 * @throws SQLException 
	 */
	public String orderStatistics() throws SQLException{
		List<CDeliveryWay> deliveryWayList =cdeliverywaymanager.selectByExample(null);
		this.getRequest().setAttribute("deliveryWayList", deliveryWayList);
		return "order_statistics";
	}
	/**
	 * 订单统计查询方法
	 * @throws Exception
	 */
   public void ajaxOrderStatistics() throws Exception{
	   //开始时间
	   String startDate = this.getRequest().getParameter("startDate");
	   //结束时间
	   String endDate = this.getRequest().getParameter("endDate");
	   // 统计类型   1 按月  2 按天   3 按小时 
	   String tongjiType = this.getRequest().getParameter("tongjiType");
	   //订单状态 0 待支付 1 待发货 2 待收货  3 已完成  4 已取消 5 已过期
	   String orderStatus  =this.getRequest().getParameter("orderStatus");
	   //核实状态  0 未核实  1 已核实
	   String  verifyStatus = this.getRequest().getParameter("verifyStatus");
	   
	   String deliveryId = this.getRequest().getParameter("deliveryId");
	   
	   if(StringUtils.hasText(startDate)&&StringUtils.hasText(endDate)){
		   Map<String,Object> resultMap = new HashMap<String,Object>();
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		   String sdfStr ="'%Y-%m-%d'";
		   List<String> lDate = null;
		   if(tongjiType.equals("1")){//1 按月 
			   sdf = new SimpleDateFormat("yyyy-MM"); 
			   lDate = findDates(startDate, endDate,Calendar.MONTH,sdf);  
			   sdfStr ="'%Y-%m'";
		   }else if(tongjiType.equals("2")){//2 按天
			   sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			   lDate = findDates(startDate, endDate,Calendar.DAY_OF_MONTH,sdf); 
			   sdfStr ="'%Y-%m-%d'";
		   }else if(tongjiType.equals("3")){//3 按小时
			   sdf = new SimpleDateFormat("yyyy-MM-dd HH");  
			   startDate = startDate+" 00";
			   endDate = endDate+" 23";
			   lDate = findDates(startDate, endDate,Calendar.HOUR_OF_DAY,sdf);
			   sdfStr ="'%Y-%m-%d %H'";
		   }
		   resultMap.put("xname", lDate);
		   Map<String,String> paramMap= new HashMap<String,String>();
		   paramMap.put("startDate", "'"+sdf.format(sdf.parse(startDate))+"'");
		   paramMap.put("endDate", "'"+sdf.format(sdf.parse(endDate))+"'");
		   paramMap.put("sdfStr", sdfStr);
		   if(StringUtils.hasText(orderStatus)){
			   paramMap.put("orderStatus", orderStatus);
		   }
		   if(StringUtils.hasText(verifyStatus)){
			   paramMap.put("verifyStatus", verifyStatus);
		   }
		   if(StringUtils.hasText(deliveryId)){
			   paramMap.put("deliveryId", deliveryId);
		   }
		   
		   //1 pc 2 wap 3 安卓 4 ios 5 手动下单
		   Map<Integer,String> sourceMap = new HashMap<Integer,String>();
		   sourceMap.put(1, "PC");
		   sourceMap.put(2, "WAP");
		   sourceMap.put(3, "Android");
		   sourceMap.put(4, "IOS");
		   sourceMap.put(5, "手动下单");
		   List<Map<String,Object>> priceList = this.opensqlmanage.selectForListByMap( paramMap, "t_order.selectTongjiPriceByMap");
		   if(priceList!=null&&priceList.size()>0){
			   List<Map<String,Object>>  priceMapList = new ArrayList<Map<String,Object>>();
			   for(Integer key : sourceMap.keySet()){
				   Map<String,Object> priceMap = new HashMap<String,Object>();
				   priceMap.put("name", sourceMap.get(key));
				   List<Double> douList = new ArrayList<Double>();
				   for(String date:lDate){
					   double price = 0.00;
					   for(Map<String,Object> map:priceList){
						   if((Integer)map.get("order_source")==key){
							   if(map.get("riqi").equals("'"+date+"'")){
								   price = ((BigDecimal)map.get("price")).doubleValue();
							   }
						   }
					   }
					   douList.add(price);
				   }
				   priceMap.put("data", douList);
				   priceMapList.add(priceMap);
			   }
			   resultMap.put("priceMapList", priceMapList);
		   }
		   
		   List<Map<String,Object>> countList = this.opensqlmanage.selectForListByMap( paramMap, "t_order.selectTongjiCountByMap");
		   if(countList!=null&&countList.size()>0){
			   List<Map<String,Object>> tongjiMapList = new ArrayList<Map<String,Object>>();
			   for(Integer key : sourceMap.keySet()){
				   Map<String,Object> tongjiMap = new HashMap<String,Object>();
				   tongjiMap.put("name", sourceMap.get(key));
				   List<Integer> douList = new ArrayList<Integer>();
				   for(String date:lDate){
					   int tongji = 0;
					   for(Map<String,Object> map:countList){
						   if((Integer)map.get("order_source")==key){
							   if(map.get("riqi").equals("'"+date+"'")){
								   tongji = ((Long)map.get("tongji")).intValue();
							   }
						   }
					   }
					   douList.add(tongji);
				   }
				   tongjiMap.put("data", douList);
				   tongjiMapList.add(tongjiMap);
			   }
			   resultMap.put("tongjiMapList", tongjiMapList);
		   }
		   writeObjectToResponse(resultMap, ContentType.application_json);
	   }
   }
	
 public static List<String> findDates(String dBegin, String dEnd,int calendar,SimpleDateFormat sdf) throws ParseException {  
	        List lDate = new ArrayList();  
	        lDate.add(sdf.format(sdf.parse(dBegin)));  
	        Calendar calBegin = Calendar.getInstance();  
	        // 使用给定的 Date 设置此 Calendar 的时间    
        calBegin.setTime(sdf.parse(dBegin));  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calEnd.setTime(sdf.parse(dEnd));  
        // 测试此日期是否在指定日期之后    
        while (sdf.parse(dEnd).after(calBegin.getTime())) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
//		            calBegin.add(Calendar.DAY_OF_MONTH, 1);  
        	calBegin.add(calendar, 1);
            lDate.add(sdf.format(calBegin.getTime()));  
        }  
        return lDate;  
    } 
	
	public List<CPaymentWay> getPaymentWayList() {
		return paymentWayList;
	}
	public void setPaymentWayList(List<CPaymentWay> paymentWayList) {
		this.paymentWayList = paymentWayList;
	}
	public List<CDeliveryWay> getDeliveryWayList() {
		return deliveryWayList;
	}
	public void setDeliveryWayList(List<CDeliveryWay> deliveryWayList) {
		this.deliveryWayList = deliveryWayList;
	}
	public Object getModel() {
		return this.model;
	}
	public void setModel(Object o) {
		this.model = (Condition) o;
	}
	public TOrderManager getTordermanager() {
		return tordermanager;
	}
	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
	}
	public CPaymentWayManager getCpaymentwaymanager() {
		return cpaymentwaymanager;
	}
	public void setCpaymentwaymanager(CPaymentWayManager cpaymentwaymanager) {
		this.cpaymentwaymanager = cpaymentwaymanager;
	}
	public CDeliveryWayManager getCdeliverywaymanager() {
		return cdeliverywaymanager;
	}
	public void setCdeliverywaymanager(CDeliveryWayManager cdeliverywaymanager) {
		this.cdeliverywaymanager = cdeliverywaymanager;
	}
	
	public TMemberManager getTmembermanager() {
		return tmembermanager;
	}
	public void setTmembermanager(TMemberManager tmembermanager) {
		this.tmembermanager = tmembermanager;
	}
	public TGoodsManager getTgoodsmanager() {
		return tgoodsmanager;
	}
	public void setTgoodsmanager(TGoodsManager tgoodsmanager) {
		this.tgoodsmanager = tgoodsmanager;
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

	public TOrder getOrder() {
		return order;
	}

	public void setOrder(TOrder order) {
		this.order = order;
	}


	public Long getMemberId() {
		return memberId;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public List<Map<String, Object>> getClocationList() {
		return clocationList;
	}


	public void setClocationList(List<Map<String, Object>> clocationList) {
		this.clocationList = clocationList;
	}




	public TOrderItemManager getTorderitemmanager() {
		return torderitemmanager;
	}


	public void setTorderitemmanager(TOrderItemManager torderitemmanager) {
		this.torderitemmanager = torderitemmanager;
	}


	public TOrderFlowManager getTorderflowmanager() {
		return torderflowmanager;
	}


	public void setTorderflowmanager(TOrderFlowManager torderflowmanager) {
		this.torderflowmanager = torderflowmanager;
	}


	public TOrderShipment getOrderShipment() {
		return orderShipment;
	}


	public void setOrderShipment(TOrderShipment orderShipment) {
		this.orderShipment = orderShipment;
	}


	public TOrderShipmentManager getTordershipmentmanager() {
		return tordershipmentmanager;
	}


	public void setTordershipmentmanager(TOrderShipmentManager tordershipmentmanager) {
		this.tordershipmentmanager = tordershipmentmanager;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public MessageSender getTopicMessageSender() {
		return topicMessageSender;
	}


	public void setTopicMessageSender(MessageSender topicMessageSender) {
		this.topicMessageSender = topicMessageSender;
	}


	public TMemberAccountManager getTmemberaccountmanager() {
		return tmemberaccountmanager;
	}


	public void setTmemberaccountmanager(TMemberAccountManager tmemberaccountmanager) {
		this.tmemberaccountmanager = tmemberaccountmanager;
	}


	public TLeaderStayMoneyManager getTleaderstaymoneymanager() {
		return tleaderstaymoneymanager;
	}


	public void setTleaderstaymoneymanager(TLeaderStayMoneyManager tleaderstaymoneymanager) {
		this.tleaderstaymoneymanager = tleaderstaymoneymanager;
	}


	public TMemberReceiverLatLonManager getTmemberreceiverlatlonmanager() {
		return tmemberreceiverlatlonmanager;
	}


	public void setTmemberreceiverlatlonmanager(
			TMemberReceiverLatLonManager tmemberreceiverlatlonmanager) {
		this.tmemberreceiverlatlonmanager = tmemberreceiverlatlonmanager;
	}


	public TMemberReceiverLatLon getTmemberreceiverlatlon() {
		return tmemberreceiverlatlon;
	}


	public void setTmemberreceiverlatlon(TMemberReceiverLatLon tmemberreceiverlatlon) {
		this.tmemberreceiverlatlon = tmemberreceiverlatlon;
	}


	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}


	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}


	public CLocationCityManager getClocationcitymanager() {
		return clocationcitymanager;
	}


	public void setClocationcitymanager(CLocationCityManager clocationcitymanager) {
		this.clocationcitymanager = clocationcitymanager;
	}


	public TGoodsPriceManager getTgoodspricemanager() {
		return tgoodspricemanager;
	}


	public void setTgoodspricemanager(TGoodsPriceManager tgoodspricemanager) {
		this.tgoodspricemanager = tgoodspricemanager;
	}
	
	
	
}
