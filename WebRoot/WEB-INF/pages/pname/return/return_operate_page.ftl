<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>退换货操作</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
} 
.tab_content { 
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
/*#9370DB*/
#optBtns input {background-color:#ccc;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;cursor: pointer;}
#goBack{
background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;
}

.f input{
	background-color: #fff;
	height:26px;
	width:200px;
}
.f td{
	padding-top: 8px;
}
.f textarea{
	background-color: #fff;
	width:200px;
}
</style>


<link type="text/css" href="${base}/web/plugins/jquery-ui-1.8.14.custom/css/ui-lightness/jquery-ui-1.8.14.custom.css" rel="stylesheet" /> 
<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">

</head> 

<body> 
	<div id="nav">
		<!--<ul> 
			<li class="ll" title="d1">退货信息</li> 
			<li title="d2">退货项</li> 
			<li title="d3">退货图片</li> 
		</ul>-->
		
		
		<ul class="tabs"> 
			<li><a href="#tab1">退货信息</a></li> 
			<li><a href="#tab2">退货项</a></li> 
			<li><a href="#tab3">退货图片</a></li> 
		</ul>  
	</div>
	<div class="shop_main111" id="shop_main11"> 
			<div class="tab_container"> 
			<!-- ------------------------退货信息开始---------------------------------------- -->
			<!--<div id="d1">-->
			
				<div id="tab1" class="tab_content" style="margin-top: 12px;">
		    	<input type="hidden" id="id" name="id" value="${tReturn.id}"/>
		    	<div class="m-contents">
			    	<div class="labelnamese">订单号：</div>
			    	<input type="text" name="orderSn" disabled="true" class="i-text-i" id="orderSn" value="${tReturn.orderSn?if_exists}"/>
			    	<div class="labelnamese">服务类型：</div>
			    	<input id="serviceType" type="hidden" value="${tReturn.serviceType?if_exists}"/>
		    		<#if tReturn?exists && tReturn.serviceType?exists && tReturn.serviceType==0>
						<input class="i-text-i" value="退货"  disabled="true"/>
					<#elseif  tReturn?exists && tReturn.serviceType?exists && tReturn.serviceType==1>
						<input class="i-text-i" disabled="true" value="换货"/>
					<#elseif  tReturn?exists && tReturn.serviceType?exists && tReturn.serviceType==2>
						<input class="i-text-i" disabled="true" value="退款(无需退货)"/>
					<#else>
						<input class="i-text-i" disabled="true" value="状态有误"/>
					</#if>
		    	</div>
		    	
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">快递单号：</div>
			    	<input type="text" name="expressDelivery" disabled="true" class="i-text-i" id="expressDelivery" value="${tReturn.expressDelivery?if_exists}"/>
			    	<div class="labelnamese">快递公司：</div>
			    	<input type="text" name="expressCompany" disabled="true" class="i-text-i" id="expressCompany" value="${tReturn.expressCompany?if_exists}"/>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">发货人电话：</div>
			    	<input type="text" name="shipperPhone" disabled="true" class="i-text-i" id="shipperPhone" value="${tReturn.shipperPhone?if_exists}"/>
			    	<div class="labelnamese">用户名：</div>
			    	<input type="text" name="userName" disabled="true" class="i-text-i" id="userName" value="<#if tMember?exists>${tMember.userName?if_exists}</#if>"/>
		    	</div>
		    	
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">退款金额：</div>
			    	<input class="i-text-i" disabled="true" value="${tReturn.refundAmount?default('')}"/></span>
			    	<div class="labelnamese">订单状态：</div>
						<input type="hidden" name="orderStatus" disabled="true" class="i-text-i" id="orderStatus" value="${tReturn.orderStatus?if_exists}"/>
			    		
			    		<#if tReturn.orderStatus?exists && tReturn.orderStatus==0>
							<input type="text" disabled="true" class="i-text-i" value="未处理"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==1>
							<input type="text" disabled="true" class="i-text-i" value="已通过"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==2>
							<input type="text" disabled="true" class="i-text-i" value="未通过"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==3>
							<input type="text" disabled="true" class="i-text-i" value="验货通过"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==4>
							<input type="text" disabled="true" class="i-text-i" value="验货未通过"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==5>
							<input type="text" disabled="true" class="i-text-i" value="退款中"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==6>
							<input type="text" disabled="true" class="i-text-i" value="处理中"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==7>
							<input type="text" disabled="true" class="i-text-i" value="验货中"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==8>
							<input type="text" disabled="true" class="i-text-i" value="换货中"/>
						<#elseif tReturn.orderStatus?exists && tReturn.orderStatus==9>
							<input type="text" disabled="true" class="i-text-i" value="结束"/>
						<#else>
							<input type="text" disabled="true" class="i-text-i" value="状态有误"/>
						</#if>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">退款账号：</div>
			    	<input type="text" name="refundAccount" disabled="true" class="i-text-i" id="refundAccount" value="${tReturn.refundAccount?if_exists}"/>
			    	<div class="labelnamese">退款银行：</div>
			    	<input type="text" name="refundBank" disabled="true" class="i-text-i" id="refundBank" value="${tReturn.refundBank?if_exists}"/>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
			    	<div class="labelnamese">原订单编号：</div>
			    	<input type="text" name="oldOrderId" disabled="true" class="i-text-i" id="oldOrderId" value="<#if tOrder?exists>${tOrder.orderSn?if_exists}</#if>"/>
			    	<div class="labelnamese">创建时间：</div>
			    	<input type="text" name="createTime" disabled="true" class="i-text-i" id="createTime" value="<#if tReturn?exists && tReturn.createTime?exists>${tReturn.createTime?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if>"/>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;">
		    		<div class="labelnamese">退款描述：</div>
			    	<!--<input type="text" name="refundDescribe" disabled="true" class="i-text-i" id="refundDescribe" value="${tReturn.refundDescribe?if_exists}"/>-->
			    	<textarea name="refundDescribe" id="refundDescribe" style="line-height: 20px;width:400px;height:40px;" class="i-text-i">${tReturn.refundDescribe?if_exists}</textarea>
		    	</div>
		    	<div class="m-contents" style="margin-top:10px;margin-bottom: 12px;">
			    	<div class="labelnamese">备注：</div>
			    	<textarea class="i-text-i" name="refundRemark" id="refundRemark" style="background-color:#fff;width:400px;height:100%;line-height: 20px; ">${tReturn.refundRemark?if_exists}</textarea>
		    	</div>
		    </div>
		    <!-- ------------------------退货信息结束---------------------------------------- -->
		    
		    <!-- ------------------------退货项开始---------------------------------------- -->
    		<!--<div id="d2" style="display:none">-->
    		<div id="tab2" class="tab_content"> 
    			<table class="table-list">
    				<thead style="background:#dbf1fc;"> 
						<tr style="height:40px;" class="content">
							<th style="width:4%;">序号</th>
							<th>商品编号</th>
							<th>商品名称</th>
							<th>数量</th>
						</tr>
					</thead>
					<tbody> 
						<#list itemList as item>
							<tr class="content" style="text-align:center;">
								<td>${item_index+1}</td>
								<td>${item.goodsno?if_exists}</td>
								<td>${item.goodsName?if_exists}</td>
								<td>${item.quantity?if_exists}</td>
							</tr>
						</#list>
					</tbody>
    			</table>
    		</div>
    		<!-- ------------------------退货项结束---------------------------------------- -->
			
			
			<!-- ------------------------退货图片开始---------------------------------------- -->
			<!--<div id="d3" style="display:none">-->
			<div id="tab3" class="tab_content">
				<div>
					<table class="table-list">
						<thead style="background:#dbf1fc;"> 
							<tr style="height:40px;" class="content">
								<th style="width:4%;">退货图片</th>
							</tr>
						</thead>
						<tbody> 
							<tr class="content" style="text-align:center;">
								<td>
									<#list imageList as image>
											<image width="500px" src="${base}${image.imageUrl?if_exists}"/>
									</#list>
								</td>
							</tr>
						</tbody>
	    			</table>
					
				</div>
			</div> 
			<!-- ------------------------退货图片结束---------------------------------------- -->
    	</div> 
    	
    </div>   
    	
    
    <!-- ----------------弹出层<填写物流信息>开始---------------- -->
<div class="s_wp" style="display:none" id="audit">
	<div class="s_con">
		<div class="s_int">
			<form class="f" method="post">
				<input type="hidden" name="flag" id="flag" />
				<table width="100%" height="130px" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15%" align="right" valign="top"><span style="color:red;font-size:;">*</span>收货人：</td>
						<td width="35%"><input class="i-text-i" type="text" name="receiverInput" value="<#if tOrder?exists>${tOrder.receiver?if_exists}</#if>" /></td>
						<td width="15%" align="right" valign="top"><span style="color:red;font-size:;">*</span>手机号：</td>
						<td width="35%"><input class="i-text-i" type="text" name="phoneInput" value="<#if tOrder?exists>${tOrder.phone?if_exists}</#if>" /></td>
					</tr>
					<tr>
						<td width="15%" align="right" valign="top"><span style="color:red;font-size:;">*</span>收货地址：</td>
						<td width="35%"><input class="i-text-i" type="text" name="addressInput" value="<#if tOrder?exists>${tOrder.areaName?if_exists}${tOrder.detailedAddress?if_exists}</#if>"  /></td>
						<td width="15%" align="right" valign="top" ><span style="color:red;font-size:;">*</span>配送方式：</td>
						<td width="35%"><input class="i-text-i" disable="true" type="text" id="expressDelivery" name="expressDelivery" value="普通快递"/></td>
					</tr>
					<!-- 收货人,手机号,收货地址自动带出 -->
					
					<tr>
						<td width="15%" align="right" valign="top"><span style="color:red;font-size:;">*</span>物流公司：</td>
						<td width="35%"><input class="i-text-i" type="text" id="expressCompanyInput" name="expressCompanyInput" /></td>
						<td width="15%" align="right" valign="top"><span style="color:red;font-size:;">*</span>快递单号：</td>
						<td width="35%"><input class="i-text-i" type="text" id="expressDeliveryInput" name="expressDeliveryInput" /></td>
					</tr>
					<tr>
						<td width="30%" align="right" valign="top">备注：</td>
						<td width="70%" colspan="3"><textarea id="remark" name="remark" class="i-text-i" style="line-height: 20px;width:450px;height: 67px;">${tReturn.refundRemark?if_exists}</textarea></td>
					</tr>
					<tr>
						<td colspan="4" align="center" style="margin-top: 12px; padding-top: 24px; padding-bottom: 12px;">
							<input type="button" class="button" onclick="mySumbit()" value="添加" class="tex_1" style="height:26px;width:60px;height:26px;width:60px;margin-left:10px;background-color: #9370DB;border-radius: 5px;cursor:pointer;" />
							<input type="button" class="button" value="关闭" class="tex_1" onclick="gb()" style="height:26px;width:60px;height:26px;width:60px;margin-left:10px;background-color: #9370DB;border-radius: 5px;cursor:pointer" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
    <!-- ----------------弹出层<填写物流信息>结束---------------- -->
  </div>
    <!-- ----------------退款弹出层开始---------------- -->
    
    <div class="s_wp" style="display:none" id="refunding">
	<div class="s_con">
		<div class="s_int">
			<form class="f" method="post">
				<input type="hidden" name="flag1" id="flag1" />
				<table width="100%" height="130px" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="23%" align="right" valign="top" style="padding-top: 8px;"><span style="color:red;">*</span>金额：</td>
						<td width="77%"><input class="i-text-i" style="background-color: #fff;height:26px;width:200px" type="text" name="refundAmountInput" id="refundAmountInput" onkeypress="validateMaxMoney(this)" onkeyup="validateMaxMoney(this)" style="height:26px;width:200px" />&nbsp;&nbsp;<span id="amountTip" style="color:red;font-size:14px;"></span></td>
					</tr>
					<tr>
						<td width="23%" align="right" valign="top" style="padding-top: 8px;">转入银行：</td>
						<td width="77%"><input class="i-text-i" type="text" name="refundBankInput" id="refundBankInput" value="" style="background-color: #fff;height:26px;width:200px" /></td>
					</tr>
					<tr>
						<td width="23%" align="right" valign="top" style="padding-top: 8px;">转入账号：</td>
						<td width="77%"><input class="i-text-i" type="text" name="refundAccountInput" id="refundAccountInput" value="" style="background-color: #fff;height:26px;width:200px" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" class="button" onclick="refundingSumbit()" value="添加" class="tex_1" style="height:26px;width:60px;margin-left:10px;background-color: #9370DB;border-radius: 5px;cursor:pointer" />
							<input type="button" class="button" value="关闭" class="tex_1" onclick="gb1()" style="height:26px;width:60px;margin-left:10px;background-color: #9370DB;border-radius: 5px;cursor:pointer" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	</div>
    <!-- ----------------退款弹出层结束---------------- -->
    
</div>    

<div id="optBtns" style="clear: both; display: block; padding: 10px 0; text-align:center;background: #fff none repeat scroll 0 0;">
	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_CAOZUO_TONGGUO">
		<input type="button" disabled class="button statusbtn passed" id="passed" value="通过" style="border: 1;"/>&nbsp;&nbsp;&nbsp;
	</@security.authorize>
	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_CAOZUO_BUTONGGUO">
		<input type="button" disabled class="button statusbtn noPass" id="noPass" value="不通过" />&nbsp;&nbsp;&nbsp;
	</@security.authorize>
	<#if tReturn?exists && tReturn.serviceType?exists && (tReturn.serviceType==0||tReturn.serviceType==1)>
		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_CAOZUO_YANHUOTONGGUO">
			<input type="button" disabled class="button statusbtn passInspection" id="passInspection" value="验货通过"  />&nbsp;&nbsp;&nbsp;
		</@security.authorize>
		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_CAOZUO_YANHUOWEITONGGUO">
			<input type="button" disabled class="button validateNotPass" id="validateNotPass" value="验货未通过"  />&nbsp;&nbsp;&nbsp;
		</@security.authorize>
	</#if>
	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_CAOZUO_TUIKUANZHONG">
		<input type="button" disabled class="button statusbtn refund" id="refund" value="退款中"  />&nbsp;&nbsp;&nbsp;
	</@security.authorize>
	<#if tReturn?exists && tReturn.serviceType?exists && (tReturn.serviceType==0||tReturn.serviceType==1)>
		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_CAOZUO_HUANHUOZHONG">
			<input type="button" disabled class="button statusbtn replaceGoods" id="replaceGoods" value="换货中"  />&nbsp;&nbsp;&nbsp;
		</@security.authorize>
	</#if>
	<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_TUIHUANHUO_CAOZUO_JIESHU">
		<input type="button" class="button statusbtn end " id="end" value="结束"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</@security.authorize>
	<input type="button" id="goBack" class="button" value="返回" onclick="goList()" style="margin-left:10px;background-color: #9370DB;"/>
</div>
<script type="text/javascript" src="${base}/web/plugins/jquery-ui-1.8.14.custom/js/jquery-ui-1.8.14.custom.min.js"></script>
 <script type="text/javascript">
		var noPass_huanHuo_status=4;
		function goList(){
			window.location.href="${base}/returns/returns!returnPage.action";
		}
		/*$(function() { 
			$("ul > li").click(tab); 
			$("ul > li").hover(tab); 
			function tab() { 
				$(this).addClass("ll").siblings().removeClass("ll"); 
				var tab = $(this).attr("title"); 
				$("#" + tab).show().siblings().hide(); 
			}; 
		});*/
		
		$(function(){
			initBtn();
			
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
		});
		
		function initBtn(){
			var orderStatus = $("#orderStatus").val();
			var serviceType = $("#serviceType").val();
			//退货,换货的流程
			if(serviceType=='1'||serviceType=='0'){
				if(orderStatus == '0'){//未处理
					$("#passed").attr('disabled',false);
					$("#noPass").attr('disabled',false);
	
					$("#passed").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#noPass").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					//$("#end").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '1'){//已通过
					$("#passInspection").attr('disabled',false);
					$("#validateNotPass").attr('disabled',false);
					$("#passInspection").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#validateNotPass").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					//$("#end").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus =='7'){//验货中
					$("#passInspection").attr('disabled',false);
					$("#validateNotPass").attr('disabled',false);
					$("#passInspection").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#validateNotPass").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#end").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '3'){//验货通过
					if($("#serviceType").val() == '0'){//退货,需要退款
						$("#refund").attr('disabled',false);
						$("#refund").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					}else if($("#serviceType").val() == '1'){//换货
						$("#replaceGoods").attr('disabled',false);
						$("#replaceGoods").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					}
					//$("#end").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '9'){
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '8'){
					$("#end").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '5'){//退款中,可点击
					$("#end").attr('disabled',false);
					$("#end").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '2'){//审核未通过
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '4'){//验货未通过
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}
			}else{//退款无需退货的流程(0:未处理,1:已通过,2:未通过,5:退款中,9:结束)
				if(orderStatus == '0'){//未处理
					$("#passed").attr('disabled',false);
					$("#noPass").attr('disabled',false);
					$("#passed").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#noPass").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '1'){
					$("#refund").attr('disabled',false);
					$("#refund").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '2'){
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '5'){//退款中
					$("#end").attr('style','background-color:#9370DB;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}else if(orderStatus == '9'){//
					$("#end").attr('disabled',true);
					$("#end").attr('style','background-color:#CCC;color:#fff;padding:5px 8px 5px 8px;border-radius:5px;');
				}
			}
			
		}
		$(function(){
			//1通过
			$("#passed").click(function(){
				updateOrderStatus(1);//审核通过
			});
			//不通过
			$("#noPass").click(function(){
				updateOrderStatus(2);//审核不通过
			});
			//验货通过
			$("#passInspection").click(function(){
				updateOrderStatus(3);//验货通过
			});
			//验货未通过
			$("#validateNotPass").click(function(){
				noPass_huanHuo_status = 4;
				$('#audit').dialog('open');
				
				//updateOrderStatus(4);//验货未通过,弹层,输入物流信息
			});
			//退款中
			$("#refund").click(function(){
				//退款金额前台页面计算不出,此处手工填写金额,
				
				//refunding(5);
				$('#refunding').dialog('open');
				//updateOrderStatus(5);//退款中
			});
			//处理中
			$("#statusbtn").click(function(){
				updateOrderStatus(6);//处理中
			});
			//验货中
			$("#statusbtn").click(function(){
				updateOrderStatus(7);//验货中
			});
			//换货中
			$("#replaceGoods").click(function(){
				noPass_huanHuo_status = 8;
				$('#audit').dialog('open');
			
				//updateOrderStatus(8);//换货中
			});
			//结束
			$("#end").click(function(){
				updateOrderStatus(9);//结束
			});
		});
		
		function updateOrderStatus(status){
			var conf = confirm("您确定要修改?");
			if(!conf){
				return false;
			}
			var id = $("#id").val();
			var refundRemark = $("#refundRemark").val();
			$.ajax({//returns/returns!returnPage.action
				url: "${base}/returns/updateStatus!updateStatus.action",
				type: "POST",
				data: {'id':id,'status':status,'refundRemark':refundRemark} ,
				success: function(data){
					if(data.resultStatus=='1'){
						$alert('success',data.message);
					}else{
						$alert('warn',data.message);
					}
					window.location.href="${base}/returns/returns!returnPage.action";
				},error:function(){
					alert("内部错误,请稍后重试!");
					window.location.href="${base}/returns/returns!returnPage.action";
				}
			});
		}
		
		$('#audit').dialog({
			autoOpen: false,
			width: 620,
			title:'填写物流信息',
			resizable: false,
			modal: true //遮蔽
		});
		function shenhe(){
			$('#flag').val("add");
			$('#audit').dialog('open');
		}
		function gb(){
			$('#audit').dialog('close');
		}
		
		/**
		 * 验货不通过/换货中,提交
		 */
		function mySumbit(){
			var id = $("#id").val();
			var receiver = $("#receiver").val();//收货人
			var phone = $("#phone").val();//手机号
			var addresss = $("#address").val();//收货地址
			var expressDelivery = $("#expressDeliveryInput").val();//快递单号
			var expressCompany = $("#expressCompanyInput").val();//物流公司
			var remark = $("#remark").val();//备注
			if(expressDelivery==null || expressDelivery==""){
				alert("快递单号不能为空!");
				return false;
			}
			if(expressCompany==null || expressCompany==""){
				alert("物流公司不能为空!");
				return false;
			}
			
			var status = noPass_huanHuo_status;
			$.ajax({
				url: "${base}/returns/validateNotPass!validateNotPass.action",
				type: "POST",
				data: {'id':id,'status':status,'expressDelivery':expressDelivery,'expressCompany':expressCompany,'refundRemark':remark} ,
				success: function(data){
					if(data.resultStatus=='1'){
						$alert('success',data.message);
					}else{
						$alert('warn',data.message);
					}
					window.location.href="${base}/returns/returns!returnPage.action";
				},error:function(){
					alert("内部错误,请稍后重试!");
					window.location.href="${base}/returns/returns!returnPage.action";
				}
			});
		}
		
		
		
		
		$('#refunding').dialog({
			autoOpen: false,
			width: 470,
			title:'退款中',
			resizable: false,
			modal: true //遮蔽
		});
		function shenhe(){
			$('#flag1').val("add");
			$('#refunding').dialog('open');
		}
		function gb1(){
			$('#refunding').dialog('close');
		}
		
		/**
		  * 退款,弹框填写金额,退款行,退款账号,提交
		  */
		function refundingSumbit(){//status=5
			var status = 5;
			var refundAmount = $("#refundAmountInput").val();
			var refundAccount = $("#refundAccountInput").val();
			var refundBank = $("#refundBankInput").val();
			if(refundAmount==null || refundAmount==""){
				$("#amountTip").html("退款金额不能为空!");
				return false;
			}
			var id = $("#id").val();
			var status=5;
			$.ajax({//returns/returns!returnPage.action
				url: "${base}/returns/updateStatus!updateStatus.action",
				type: "POST",
				data: {'id':id,'status':status,'refundAmount':refundAmount,'refundAccount':refundAccount,
					'refundBank':refundBank
				} ,
				success: function(data){
					if(data.resultStatus=='1'){
						$alert('success',data.message);
					}else{
						$alert('warn',data.message);
					}
					window.location.href="${base}/returns/returns!returnPage.action";
				},error:function(){
					alert("内部错误,请稍后重试!");
					window.location.href="${base}/returns/returns!returnPage.action";
				}
			});
		}
		
		
		function validateMaxMoney(obj){
			//先把非数字的都替换掉，除了数字和.
			obj.value = obj.value.replace(/[^\d.]/g,"");
			//必须保证第一个为数字而不是.
			obj.value = obj.value.replace(/^\./g,"");
			//保证只有出现一个.而没有多个.
			obj.value = obj.value.replace(/\.{2,}/g,".");
			//保证.只出现一次，而不能出现两次以上
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			if(obj.value.indexOf(".") != -1){
				var subVar = obj.value.substring(obj.value.indexOf(".")+1,obj.value.length);
				if(subVar.length > 6){
					obj.value = obj.value.substring(0,obj.value.length-(subVar.length-6));
				}
			}
		}
		
		$(function(){
			function BodyOnLoad() { 
				var textarea= document.getElementById("refundRemark"); 
				//textarea.style.posHeight=textarea.scrollHeight;
				textarea.css("height",$(this).attr("scrollHeight")); 
			} 
			$("#refundAmountInput").focus(function(){
				$(this).siblings("span").html("");
			});
			$("#refundAmountInput").blur(function(){
				if($("#refundAmountInput").val()==""){
					$(this).siblings("span").html("退款金额不能为空!");
				}
			});
		})
</script>
</body>

</html>