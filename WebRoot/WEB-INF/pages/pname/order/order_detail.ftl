<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>订单详情</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" href="${base}/web/plugins/jquery-ui-1.8.14.custom/css/ui-lightness/jquery-ui-1.8.14.custom.css" rel="stylesheet" /> 
	  <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
	 <#include "/WEB-INF/pages/inc/taglibs.ftl">
	<#include "/WEB-INF/pages/inc/common.ftl">
		<style type="text/css">
body { 
	background: #f0f0f0; 
	margin: 0; 
	padding: 0; 
	font: 10px normal Verdana, Arial, Helvetica, sans-serif; 
	color: #444; 
} 
h1 {font-size: 3em; margin: 20px 0;} 
.container {width: 500px; margin: 10px auto;} 
	ul.tabs { 
	margin: 0; 
	padding: 0; 
	float: left; 
	list-style: none; 
	height: 32px; 
	border-bottom: 1px solid #999; 
	border-left: 1px solid #999; 
	width: 100%; 
} 
ul.tabs li { 
	float: left; 
	margin: 0; 
	padding: 0; 
	height: 31px; 
	line-height: 31px; 
	border: 1px solid #999; 
	border-left: none; 
	margin-bottom: -1px; 
	background: #e0e0e0; 
	overflow: hidden; 
	position: relative; 
} 
ul.tabs li a { 
	text-decoration: none; 
	color: #000; 
	display: block; 
	font-size: 1.2em; 
	padding: 0 20px; 
	border: 1px solid #fff; 
	outline: none; 
} 
ul.tabs li a:hover { 
	background: #ccc; 
} 
html ul.tabs li.active, html ul.tabs li.active a:hover { 
	background: #fff; 
	border-bottom: 1px solid #fff; 
} 
.tab_container { 
	border: 1px solid #999; 
	border-top: none; 
	clear: both; 
	float: left; 
	width: 100%; 
	background: #fff; 
	-moz-border-radius-bottomright: 5px; 
	-khtml-border-radius-bottomright: 5px; 
	-webkit-border-bottom-right-radius: 5px; 
	-moz-border-radius-bottomleft: 5px; 
	-khtml-border-radius-bottomleft: 5px; 
	-webkit-border-bottom-left-radius: 5px; 
	border-left-width: 1px; border-bottom-width: 1px; border-right-width: 1px;
} 
.tab_content { 
padding: 20px; 
font-size: 1.2em; 
} 
.tab_content h2 { 
font-weight: normal; 
padding-bottom: 10px; 
border-bottom: 1px dashed #ddd; 
font-size: 1.8em; 
} 
.tab_content h3 a{ 
color: #254588; 
} 
.tab_content img { 
float: left; 
margin: 0 20px 20px 0; 
border: 1px solid #ddd; 
padding: 5px; 
} 
input{height:35px;width:180px;padding-left:3px; border: 1px solid #cbcbcb;}
</style>
</head> 

<body> 
<div>
   <#if order.verifyStatus ==0> 
      <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_QUEREN">	 
      <input type="button"   id="queren" value="确认" class="btn05" onclick="confirmOrder()" /> 
      </@security.authorize>
   </#if>
   <#if order.orderStatus ==0> 
   <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_ZHIFU">	
      <input type="button"   id="pay" value="支付" class="btn05" onclick="payOrder()" /> 
      </@security.authorize>
    </#if>
   <#if order.orderStatus ==1>
   <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_CHUKU">	
      <input type="button"   id="fahuo" value="发货" class="btn05" onclick="shipmentOrderView()" /> 
      </@security.authorize>
    </#if>
   <#if order.orderStatus ==2>
   <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_QUERENSHOUHUO">	
     <input type="button"   id="wancheng" value="确认收货" class="btn05" onclick="finishOrder()" />
     </@security.authorize>
   </#if>
   <#if order.orderStatus !=4 && order.orderStatus !=5>
   <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_QUXIAO">	
     <input type="button"   id="quxiaoorder" value="取消订单" class="btn05" onclick="cancelOrder()" />
     </@security.authorize>
   </#if>
   <input type="button"   id="buttonsub" value="返回" class="btn05" onclick="goback()" /> 
	<div id="nav">
		<ul class="tabs"> 
		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_DINGDANXINXI">
			<li><a href="#tab1">订单信息</a></li> 
		 </@security.authorize>	
		 <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_SHANGPINXINXI">
			<li><a href="#tab2">商品信息</a></li> 
		</@security.authorize>	
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_ZHIFUXINXI">
			<li><a href="#tab3">支付信息</a></li> 
		</@security.authorize>	
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_DINGDANGUANLI_CHAKAN_FAHUOXINXI">
			<li><a href="#tab4">发货信息</a></li> 
		</@security.authorize>	
		</ul>  
	</div>
	<input name="order.id" id="orderId" value="${order.id?default('')}" type="hidden" />
	<div class="tab_container" id="shop_main11"> 
		<div class="tab_container" style="border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px;"> 
				<div id="tab1" class="tab_content">
					 <table border="1" cellspacing="0" cellpadding="0" width="95%" style="border-color:rgba(218, 228, 243, 0.44);"> 
			          <tr style="height:40px"> 
			           <td align="center" style="width:15%">订单编号:</td> 
			           <td>${order.orderSn?if_exists}
			            </td> 
			           <td align="center" style="width:15%">下单时间:</td> 
			           <td><#if order.createDate?exists>${order.createDate?string('yyyy-MM-dd HH:mm:ss')}</#if></td> 
			          </tr> 
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">订单状态:</td> 
			           <td>
			               <#if order.orderStatus ==0>待支付
			               <#elseif order.orderStatus ==1>待发货
			               <#elseif order.orderStatus ==2>待收货
			               <#elseif order.orderStatus ==3>已完成
			               <#elseif order.orderStatus ==4>已取消
			               <#elseif order.orderStatus ==5>已过期
			               </#if>
			           </td> 
			           <td align="center" style="width:15%">确认状态:</td> 
			           <td>
			               <#if order.verifyStatus ==0>未确认
			               <#elseif order.verifyStatus ==1>已确认
			               </#if>
			           </td> 
			          </tr> 
			           <tr style="height:40px"> 
			          <td align="center" style="width:15%">订单来源:</td> 
			           <td>
			               <#if order.orderSource ==1>PC
			               <#elseif order.orderSource ==2>WAP
			               <#elseif order.orderSource ==3>安卓
			               <#elseif order.orderSource ==4>IOS
			               <#elseif order.orderSource ==5>手动下单
			               </#if>
			           </td> 
			           <td align="center" style="width:15%">是否推送海典:</td> 
			           <td>
			               <#if order.isPush ==0>否
			               <#elseif order.isPush ==1>是
			               </#if>
			           </td> 
			          </tr>
			           <tr style="height:40px"> 
			          	<td align="center" style="width:15%">支付方式:</td> 
			           <td>
			           ${paymentway.name?default('')}
			           </td> 
			           <td align="center" style="width:15%">配送方式:</td> 
			           <td>
			           ${deliveryway.name?default('')}
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          	<td align="center" style="width:15%">会员名称:</td> 
			           <td>
			            <#if member?exists><#if member.userName?exists>${member.userName?default('')}</#if></#if>
			           </td> 
			           <td align="center" style="width:15%">订单类型:</td> 
			           <td>
			           <#if order.orderType ==1>正常
			               <#elseif order.orderType ==2>秒杀
			               <#elseif order.orderType ==3>团购
			               </#if>
			           </td> 
			          </tr>
			           <tr style="height:40px"> 
			          <td align="center" style="width:15%">促销折扣:</td> 
			           <td>
			           ${currency(order.promotionalDiscount?default(''))}
			           </td> 
			           <td align="center" style="width:15%">优惠券折扣:</td> 
			           <td>
			           ${currency(order.couponDiscount?default(''))}
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">调整金额:</td> 
				           <td>
				             ${currency(order.adjustAmount?default(''))}
				           </td> 
			           <td align="center" style="width:15%">运费:</td> 
			           <td>
			           ${currency(order.shippingFee?default(''))}
			           </td> 
			          </tr>
			           <tr style="height:40px"> 
			          <td align="center" style="width:15%">应付金额:</td> 
			           <td>
			           <span id="payableAmountSpan">${currency(order.payableAmount?default(''))}</span>
			           </td> 
			           <td align="center" style="width:15%">订单金额:</td> 
			           <td>
			           <span id="orderAmountSpan">${currency(order.orderAmount?default(''))}</span>
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">收货人:</td> 
			           <td>
			          ${order.receiver?default('')}
			           </td> 
			          <td align="center" style="width:15%">电话:</td> 
			           <td>
			           ${order.phone?default('')}
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">收货地址:</td> 
			           <td colspan="3">
			            ${order.areaName?default('')}${order.detailedAddress?default('')}
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">发票内容:</td> 
			           <td>
					      	<#if order.invoiceType?exists><#if order.invoiceType==0>不开发票</#if></#if>
					      	<#if order.invoiceType?exists><#if order.invoiceType==1>明细</#if></#if>
					      	<#if order.invoiceType?exists><#if order.invoiceType==2>药品</#if></#if>
					      	<#if order.invoiceType?exists><#if order.invoiceType==3>保健品</#if></#if>
					      	<#if order.invoiceType?exists><#if order.invoiceType==4>医疗器械</#if></#if>
			           </td> 
			           <td align="center" style="width:15%">发票抬头:</td> 
			           <td>
			           ${order.invoiceTitle?default('')}
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">备注:</td> 
			           <td colspan="3">
			           ${order.remark?default('')}
			           </td> 
			          </tr>
			        </table>
		        </div>
	    		<div id="tab2" class="tab_content"> 
	    		  <table class="table-list"> 
		       <thead style="background:#dbf1fc"> 
		        <tr> 
		         <th> 商品编号 </th> 
		         <th> 商品名称</th> 
		         <th> 商品价格 </th>
		          <th> 数量</th> 
		         <th> 是否赠品 </th> 
		          <th> 小计</th>
		        </tr> 
		       </thead> 
		       <tbody> 
		        <tr> <td colspan="6" class="blank"></td></tr> 
		        <#list orderItemList?if_exists as resc>
		        <tr class="content"> 
		         <td align="center"> 
		            ${resc.goodsno?default('')}	
		         </td> 
		           <td align="center"> 
		         ${resc.goodsName?default('')}		
		         </td> 
		        <td align="center"> 
		         ${currency(resc.price?default(''))}
		         </td> 
		         <td align="center"> 
		       ${resc.quantity?default('')}	
		         </td> 
		           <td align="center"> 
	                 <#if resc.ifPremiums ==0>否
		               <#elseif resc.ifPremiums ==1>是
		               </#if>
		         </td> 
		          <td align="center"> 
		       ${currency(resc.priceSum?default(''))}
		         </td> 
		        </tr> 
		        </#list>
		       </tbody> 
		      </table>
	    		</div>
    		<div id="tab3" class="tab_content"> 
    		       <table class="table-list"> 
		       <thead style="background:#dbf1fc"> 
		        <tr> 
		         <th> 支付方式 </th> 
		         <th> 支付状态</th> 
		         <th> 支付金额 </th>
		          <th>付款日期</th> 
		         <th> 支付流水号 </th> 
		        </tr> 
		       </thead> 
		       <tbody> 
		        <tr> <td colspan="5" class="blank"></td></tr> 
		        <#list orderFlowList?if_exists as resc>
		        <tr class="content"> 
		         <td align="center"> 
		            ${resc.payMethod?default('')}	
		         </td> 
		           <td align="center"> 
		         <#if resc.paymentStatus ==0>未支付
		               <#elseif resc.paymentStatus ==1>已支付
		               </#if>	
		         </td> 
		        <td align="center"> 
		         ${currency(resc.havePay?default(''))}
		         </td> 
		         <td align="center"> 
		       <#if resc.payDate?exists>${resc.payDate?string('yyyy-MM-dd HH:mm:ss')}</#if>
		         </td> 
		           <td align="center"> 
	                 ${resc.thirdPaySerialnumber?default('')}
		         </td> 
		        </tr> 
		        </#list>
		       </tbody> 
		      </table>
    		</div>
    		<div id="tab4" class="tab_content"> 
    		       <table class="table-list"> 
		       <thead style="background:#dbf1fc"> 
		        <tr> 
		         <th> 物流公司 </th> 
		         <th> 物流单号</th> 
		         <th> 发货时间 </th>
		        </tr> 
		       </thead> 
		       <tbody> 
		        <tr> <td colspan="3" class="blank"></td></tr> 
		        <#list orderShipmentList?if_exists as resc>
		        <tr class="content"> 
		         <td align="center"> 
		            ${resc.logisticsName?default('')}	
		         </td> 
		           <td align="center">
		            ${resc.logisticsNo?default('')}	
		         </td> 
		         <td align="center"> 
		            <#if resc.shipmentDt?exists>${resc.shipmentDt?string('yyyy-MM-dd HH:mm:ss')}</#if>
		         </td> 
		        </tr> 
		        <input type="hidden" id="lp" value='${resc.logisticsPosition?default('')}'/>
		        </#list>
		       </tbody> 
		         <thead style="background:#dbf1fc"> 
		         <tr>
		        <th align="center"  colspan="2" >物流详情</th>
		        <th align="center">记录时间</th>
		        </tr>
		        </thead>
		        <tbody id="postion">
		        </tbody>
		      </table>
    		</div>
    	</div> 
    </div>   
</div>
<!-- ----------------退款弹出层开始---------------- -->
    <div class="s_wp" style="display:none" id="refunding">
	<div class="s_con">
		<div class="s_int">
			<form id="form1" name="form1" method="post">
				 <table border="0" cellspacing="0" cellpadding="0" width="95%" style="border-color:rgba(218, 228, 243, 0.44);"> 
					 <tr style="height:40px"> 
			          <td align="center" style="width:30%">物流公司:</td> 
			           <td>
			           <input name="orderShipment.logisticsName" id="logisticsName" value=""  />
			           <span><font color="red" id="logisticsNameSpanFont"></font></span>
			           </td> 
			          </tr>
			           <tr style="height:40px"> 
			           <td align="center" style="width:30%">物流单号:</td> 
			            <td>
			           <input name="orderShipment.logisticsNo" id="logisticsNo" value=""  />
			           <span><font color="red" id="logisticsNoSpanFont"></font></span>
			           </td> 
			          </tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" onclick="shipmentOrder()" value="提交" class="tex_1" style="height:26px;width:60px" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	</div>
    <!-- ----------------退款弹出层结束---------------- -->
</body>
<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript">

      $('#refunding').dialog({
			autoOpen: false,
			width: 470,
			title:'发货',
			resizable: false,
			modal: true //遮蔽
		});
		//弹出出库隐藏div
	function shipmentOrderView(){
	     $('#refunding').dialog('open');
	}
		$(function(){
			//Default Action 
			$(".tab_content").hide(); //Hide all content 
			$("ul.tabs li:first").addClass("active").show(); //Activate first tab 
			$(".tab_content:first").show(); //Show first tab content 
			//On Click Event 
			$("ul.tabs li").click(function() { 
				$("ul.tabs li").removeClass("active"); //Remove any "active" class 
				$(this).addClass("active"); //Add "active" class to selected tab 
				$(".tab_content").hide(); //Hide all tab content 
				var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content 
				$(activeTab).fadeIn(); //Fade in the active content 
				return false; 
			}); 
		})
		 
	function goback(){
	  window.location.href="${base}/order/order!getOrderList.action";
	}	
	//确认订单
	function confirmOrder(){
	if(confirm("确定要确认该订单吗？"))
	 {
		 var orderId = $("#orderId").val();
		  $.ajax({
					url:"${base}/order/order!confirmOrder.action",
					type:"post",
					data:{"orderId":orderId},
					success:function(data){
						if(data!='0'){ //
							$alert("success","操作成功！");
							 window.location.href = "${base}/order/order!toUpdateOrder.action?typeflag=detail&orderId=" + orderId;
						}else if(data=='0'){
							$alert("error","该订单不存在！");
						}else{
						    $alert("error","操作失败！");
						}
					},
					error:function(){
					      $alert("error","系统异常，请稍后再试！");
					}
				});
	 }
	  
	}
	//支付订单
	function payOrder(){
	if(confirm("确定要支付该订单吗？"))
	 {
		 var orderId = $("#orderId").val();
		  $.ajax({
					url:"${base}/order/order!payOrder.action",
					type:"post",
					data:{"orderId":orderId},
					success:function(data){
						if(data=='1'){ //
							$alert("success","支付成功！");
							 window.location.href = "${base}/order/order!toUpdateOrder.action?typeflag=detail&orderId=" + orderId;
						}else if(data=='0'){
							$alert("error","该订单不存在！");
						}else if(data=='2'){
							$alert("error","该订单未处于待支付环节！");
						}else if(data=='3'){
							$alert("error","该订单没有支付流水不能支付！");
						}
					},
					error:function(){
					      $alert("error","系统异常，请稍后再试！");
					}
				});
	 }
	}
	
	
	
	//出库	
	function shipmentOrder(){
	   var logisticsName=$.trim($("#logisticsName").val());
	   var logisticsNo=$.trim($("#logisticsNo").val());
	   if(logisticsName==''){
	      $("#logisticsNameSpanFont").html("物流公司不能为空！");
	      retrun;
	   }else{
	     $("#logisticsNameSpanFont").html("");
	   }
	    if(logisticsNo==''){
	      $("#logisticsNoSpanFont").html("物流单号不能为空！");
	      retrun;
	    }else{
	       $("#logisticsNoSpanFont").html("");
	    }
	      var formInfo=$("#form1").serialize();
	      var orderId = $("#orderId").val();
			$.ajax({
				url:"${base}/order/order!shipmentOrder.action?orderId="+orderId,
				type:"post",
				data:formInfo,
				success:function(data){
					if(data=='1'){ 
					    $('#refunding').dialog('close');
						 $alert("success","发货成功！");
						 window.location.href = "${base}/order/order!toUpdateOrder.action?typeflag=detail&orderId=" + orderId;
					}else if(data=='0'){
						$alert("error","操作失败，请稍后再试！");
					}
				},
				error:function(){
				      $alert("error","系统异常，请稍后再试！");
				}
			});
	
	}	
		
	
	//完成订单
	function finishOrder(){
	if(confirm("确定要完成该订单吗？"))
	 {
	   var orderId = $("#orderId").val();
			$.ajax({
				url:"${base}/order/order!finishOrder.action",
				type:"post",
				data:{"orderId":orderId},
				success:function(data){
					if(data=='1'){ 
						$alert("success","操作成功！");
						 window.location.href = "${base}/order/order!toUpdateOrder.action?typeflag=detail&orderId=" + orderId;
					}else if(data=='0'){
						$alert("error","操作失败，请稍后再试！");
					}
				},
				error:function(){
				      $alert("error","系统异常，请稍后再试！");
				}
			});
	 }
	}	
	//取消订单
	function cancelOrder(){
	  if(confirm("确定要取消订单吗？"))
	 {
	   var orderId = $("#orderId").val();
			$.ajax({
				url:"${base}/order/order!cancelOrder.action",
				type:"post",
				data:{"orderId":orderId},
				success:function(data){
					if(data=='1'){ 
						 $alert("success","操作成功！");
						 window.location.href = "${base}/order/order!toUpdateOrder.action?typeflag=detail&orderId=" + orderId;
					}else if(data=='2'){
						$alert("error","该订单已经取消或是过期了！");
					}else if(data=='0'){
						$alert("error","订单不存在！");
					}else{
					  $alert("error","操作失败，请稍后再试！");
					}
				},
				error:function(){
				      $alert("error","系统异常，请稍后再试！");
				}
			});
	 }
	}	
		
$(function (){
	var postion=$("#lp").val();
	var posdiv=$("#postion");
	if(postion==null||postion==""){
		posdiv.append("暂时没有物流信息");
	}else{
	var posList=eval(postion);
	for(var i=0;i<posList.length;i++){
		posdiv.append(
		"<tr><td align='center'  colspan='2' >"+posList[i].context+"</td><td align='center'>"+posList[i].time+"</td></tr>"
		 );
	}
	}
		})
		
</script>
</html>