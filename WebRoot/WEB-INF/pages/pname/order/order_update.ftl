<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>订单修改</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
input{height:28px;width:180px;padding-left:3px; border: 1px solid #cbcbcb;}
</style>
</head> 

<body> 
<div>
	<div id="nav">
		<ul class="tabs"> 
			<li><a href="#tab1">订单信息</a></li> 
			<li><a href="#tab2">商品信息</a></li> 
		</ul>  
	</div>
	<input name="adjustAmount_old" id="adjustAmount_old" type="hidden" value="${currency(order.adjustAmount?default(''))}"  />
	<input name="payableAmount_old" id="payableAmount_old" type="hidden" value="${currency(order.payableAmount?default(''))}"  />
	<input name="orderAmount_old" id="orderAmount_old" type="hidden" value="${currency(order.orderAmount?default(''))}"  />
	<form action="" method="post" name="form1" id="form1">
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
			               <#if order.orderStatus ==0>未支付
			               <#elseif order.orderStatus ==1>已支付
			               <#elseif order.orderStatus ==2>待发货
			               <#elseif order.orderStatus ==3>已发货
			               <#elseif order.orderStatus ==4>完成
			               <#elseif order.orderStatus ==5>已取消
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
				              <input name="order.adjustAmount" onchange="orderPrice()" id="adjustAmount" value="${currency(order.adjustAmount?default(''))}"  />
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
			           <input name="order.receiver" id="receiver" value="${order.receiver?default('')}"  />
			           </td> 
			           <td align="center" style="width:15%">电话:</td> 
			           <td>
			           <input name="order.phone" id="phone" value="${order.phone?default('')}"  maxlength="14"/>
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
			          	 <select name="order.invoiceType" id="invoiceType" style="height:35px;width:200px;">
					      	<option value="0" <#if order.invoiceType?exists><#if order.invoiceType==0>selected</#if></#if>>不开发票</option>
					      	<option value="1" <#if order.invoiceType?exists><#if order.invoiceType==1>selected</#if></#if>>明细</option>
					      	<option value="2" <#if order.invoiceType?exists><#if order.invoiceType==2>selected</#if></#if>>药品</option>
					      	<option value="3" <#if order.invoiceType?exists><#if order.invoiceType==3>selected</#if></#if>>保健品</option>
					      	<option value="4" <#if order.invoiceType?exists><#if order.invoiceType==4>selected</#if></#if>>医疗器械</option>
					      </select>
			           </td> 
			           <td align="center" style="width:15%">发票抬头:</td> 
			           <td>
			           <input type="text" id="invoiceTitle" name="order.invoiceTitle"  value="${order.invoiceTitle?default('')}" />
			           </td> 
			          </tr>
			          <tr style="height:40px"> 
			          <td align="center" style="width:15%">备注:</td> 
			           <td colspan="3">
			           <input name="order.remark" style="width:300px;" id="remark" value="${order.remark?default('')}"  />
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
    	</div> 
    </div>   
    </form>
</div>
<div id="optBtns" style="height:30px; line-height:30px;text-align:center;">
	<input type="button"   id="buttonsub" value="提交" class="btn01" onclick="orderSubmit()" /> 
	<input type="button"   id="buttonsub" value="返回" class="btn01" onclick="goback()" /> 
</div>
</body>
<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript">
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
	//提交修改订单
	function orderSubmit(){
		var formInfo=$("#form1").serialize();
		   if(!checkDouble()){
		      return ;
		   }
			$.ajax({
				url:"${base}/order/order!updateOrder.action",
				type:"post",
				data:formInfo,
				success:function(data){
					if(data!='0'){ //'1'更新,'2'修改
						$alert("success","订单提交成功！");
						window.location.href="${base}/order/order!getOrderList.action";
					}else if(data=='0'){
						$alert("error","操作失败，请稍后再试！");
					}else{
					    $alert("error","订单提交成功！");
					}
				},
				error:function(){
				      $alert("error","系统异常，请稍后再试！");
				}
			});
	
	}
	//计算订单金额
	function orderPrice(){
	  if(checkDouble()){
	     var adjustAmount =$.trim($("#adjustAmount").val());
	     var adjustAmount_old =$.trim($("#adjustAmount_old").val());
	     var payableAmount_new = parseFloat($("#payableAmount_old").val())-parseFloat(adjustAmount_old)+parseFloat(adjustAmount);
	     var orderAmount_new =parseFloat($("#orderAmount_old").val())-parseFloat(adjustAmount_old)+parseFloat(adjustAmount);
	     if(payableAmount_new>=0){
	         $("#payableAmountSpan").html(orderAmount_new.toFixed(2));
	     }else{
	         $("#payableAmountSpan").html("0.00");
	     }
	     if(orderAmount_new>=0){
	        $("#orderAmountSpan").html(orderAmount_new.toFixed(2));
	     }else{
	        $("#payableAmountSpan").html("0.00");
	     }
	  }
	}
	
	
	
	//校验金额
	function checkDouble(){
		var re = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
	    var adjustAmount =$.trim($("#adjustAmount").val());
	    if(adjustAmount==''){
	       $alert("warn","调整金额不能为空！");
	       return false;
	    }else{
	       if(!re.test(adjustAmount)){
	         $alert("warn","调整金额格式不正确！");
	         return false;
	       }
	    }
	    $("#adjustAmount").val(parseFloat($("#adjustAmount").val()).toFixed(2));
	    return true;
	}
		
</script>
</html>