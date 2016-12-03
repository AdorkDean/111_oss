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
<#if orderMap.order_status ==1>
 <input type="button"   id="拒收" value="拒收" class="btn05" onclick="refuseOrder()" />
</#if>
<#if orderMap.order_status ==1>
 <input type="button"   id="wancheng" value="完成订单" class="btn05" onclick="finishOrder()" />
</#if>
<#if orderMap.order_status ==0||orderMap.order_status ==1>
 <input type="button"   id="quxiaoorder" value="取消订单" class="btn05" onclick="cancelOrder()" />
</#if>
   <input type="button"   id="buttonsub" value="返回" class="btn05" onclick="goback()" /> 
	<div id="nav">
		<ul class="tabs"> 
			<li><a href="#tab1">订单信息</a></li> 
			<li><a href="#tab2">商品信息</a></li> 
			<li><a href="#tab3">发货信息</a></li> 
		</ul>  
	</div>
	<input name="orderid" id="orderid" value="${orderMap.id?default()}" type="hidden" />
	<div class="tab_container" id="shop_main11"> 
		<div class="tab_container" style="border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px;"> 
				<div id="tab1" class="tab_content">
					 <table border="1" cellspacing="0" cellpadding="0" width="95%" style="border-color:rgba(218, 228, 243, 0.44);"> 
			          <tr style="height:40px"> 
			           <td align="center" style="width:15%">订单编号:</td> 
			           <td>${orderMap.order_sn?default('')}
			            </td> 
			           <td align="center" style="width:15%">下单时间:</td> 
			           <td><#if orderMap.create_dt?exists>${orderMap.create_dt?string('yyyy-MM-dd HH:mm:ss')}</#if></td> 
			          </tr> 
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">订单状态:</td> 
			           <td>
			               <#if orderMap.order_status?default(0) ==0>已下单
			               <#elseif orderMap.order_status?default(0) ==1>待收货
			               <#elseif orderMap.order_status?default(0) ==2>已取消
			               <#elseif orderMap.order_status?default(0) ==3>拒收
			               <#elseif orderMap.order_status?default(0) ==4>已完成
			               </#if>
			           </td> 
			           <td align="center" style="width:15%">是否推送海典:</td> 
			           <td>
			               <#if orderMap.is_push?default(0) ==0>否
			               <#elseif orderMap.is_push?default(0) ==1>是
			               </#if>
			           </td> 
			          </tr> 
			          <tr style="height:40px"> 
			          	<td align="center" style="width:15%">会员名称:</td> 
			           <td>
			          ${orderMap.user_name?default('')}
			           </td> 
			          <td align="center" style="width:15%">配送方式:</td> 
			           <td>
			           ${orderMap.delivery?default('')}
			           </td>
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">订单金额:</td> 
			           <td>
			           ${currency(orderMap.order_amount?default('0.00'))}
			           </td> 
			           <td align="center" style="width:15%">运费:</td> 
			           <td>
			           ${currency(orderMap.freight?default('0.00'))}
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">收货人:</td> 
			           <td>
			          ${orderMap.receiver?default('')}
			           </td> 
			          <td align="center" style="width:15%">电话:</td> 
			           <td>
			           ${orderMap.mobile?default('')}
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">收货地址:</td> 
			           <td colspan="3">
			            ${orderMap.area_name?default('')}${orderMap.detailed_address?default('')}
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
		          <th> 小计</th>
		        </tr> 
		       </thead> 
		       <tbody> 
		        <tr> <td colspan="5" class="blank"></td></tr> 
		        <#list orderGoodsList?if_exists as resc>
		        <tr class="content"> 
		         <td align="center"> 
		           <a target="_blank" href="${www1}${resc.goodid?default('')}.html"> ${resc.goodsno?default('')}	</a>
		         </td> 
		           <td align="center"> 
		        <a target="_blank" href="${www1}${resc.goodid?default('')}.html"> ${resc.goods_name?default('')}	</a>	
		         </td> 
		        <td align="center"> 
		         ${currency(resc.good_price?default('0.00'))}
		         </td> 
		         <td align="center"> 
		       ${resc.num?default(0)}	
		         </td> 
		          <td align="center"> 
		       ${currency(resc.price?default(''))}
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
	  window.location.href="${base}/order/rxorder!getReOrderList.action";
	}	
	//拒收
	function refuseOrder(){
	if(confirm("确定要拒收该订单吗？"))
	 {
	   var orderid = $("#orderid").val();
			$.ajax({
				url:"${base}/order/rxorder!refuseOrder.action",
				type:"post",
				data:{"orderid":orderid},
				success:function(data){
					if(data=='1'){ 
						$alert("success","操作成功！");
						window.location.href = "${base}/order/rxorder!getOrderDetail.action?orderid=" + orderid;
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
	//完成订单
	function finishOrder(){
	if(confirm("确定要完成该订单吗？"))
	 {
	   var orderid = $("#orderid").val();
			$.ajax({
				url:"${base}/order/rxorder!finishOrder.action",
				type:"post",
				data:{"orderid":orderid},
				success:function(data){
					if(data=='1'){ 
						$alert("success","操作成功！");
						window.location.href = "${base}/order/rxorder!getOrderDetail.action?orderid=" + orderid;
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
	   var orderid = $("#orderid").val();
			$.ajax({
				url:"${base}/order/rxorder!cancelOrder.action",
				type:"post",
				data:{"orderid":orderid},
				success:function(data){
					if(data=='1'){ 
						 $alert("success","操作成功！");
						 window.location.href = "${base}/order/rxorder!getOrderDetail.action?orderid=" + orderid;
					}else if(data=='2'){
						$alert("error","订单状态为已取消，拒收，已完成的订单不能取消订单！");
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